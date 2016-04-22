package com.top.sdk.service;

import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.top.sdk.db.impservice.ImpPopDbService;
import com.top.sdk.db.impservice.ImpWhiteDbService;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.db.service.WhiteDbService;
import com.top.sdk.entity.Constants;
import com.top.sdk.entity.PopData;
import com.top.sdk.entity.WhiteData;
import com.top.sdk.http.business.InterfaceHttpbusiness;
import com.top.sdk.http.business.PopHttpBusiness;
import com.top.sdk.http.reqentity.PopReqPragam;
import com.top.sdk.http.respone.entity.PopResult;
import com.top.sdk.http.respone.parser.PopResultParser;
import com.top.sdk.utils.ImageLoader;
import com.top.sdk.utils.LogUtil;
import com.top.xutils.exception.HttpException;
import com.top.xutils.http.ResponseInfo;
import com.top.xutils.http.callback.RequestCallBack;

public class LTService extends Service {
	private long popShowTime;
	private long startAlarm = 10000;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		setAlarmManager();
		new RunningThread(getApplicationContext()).start();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		getListPopData();
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
		long verTime = 20 * 1000 * 60; // 时间间隔
		if (nowTime - popShowTime > verTime) {
			LogUtil.d("--------------------获取到了广告列表数据---------------");
			popShowTime = nowTime; // 拉取列表的时间

			InterfaceHttpbusiness business = new PopHttpBusiness();
			business.httpBusiness(new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					PopResultParser parser = new PopResultParser();
					PopResult result = parser.parserJson(responseInfo.result);
					if (result.getCode() == 0) {
						List<PopData> l = result.getListPop();
						if (l != null && l.size() > 0) {
							PopDbService service = new ImpPopDbService(
									getApplicationContext());
							service.insertListPopData(l);
							for (int i = 0; i < l.size(); i++) {
								ImageLoader imagerLaoder = ImageLoader
										.getInstance(getApplicationContext());
								imagerLaoder.queuePhoto(l.get(i).getImgUrl(), null);
							}
							
						}
						List<WhiteData> y = result.getListWhite();
						if (y != null && y.size() > 0) {
							WhiteDbService service = new ImpWhiteDbService(
									getApplicationContext());
							service.saveWhiteDataList(y);
						}
					}

				}

				@Override
				public void onFailure(HttpException error, String msg) {
					// TODO Auto-generated method stub
                   
				}
			}, new PopReqPragam(getApplicationContext()));

		}

	}
	 //加载完毕后去下载指定的APK文件
	private void tryDownload(List<PopData> list){
		
		
		
		
	   
	}

}
