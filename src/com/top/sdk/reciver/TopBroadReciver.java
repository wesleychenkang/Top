package com.top.sdk.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.text.TextUtils;

import com.top.sdk.db.impservice.ImpPopDbService;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.entity.PopData;
import com.top.sdk.http.business.InstallHttpBusiness;
import com.top.sdk.http.business.InterfaceHttpbusiness;
import com.top.sdk.http.reqentity.InstallReqPragam;
import com.top.sdk.utils.LogUtil;
import com.top.sdk.utils.SharedPrefUtil;
import com.top.xutils.exception.HttpException;
import com.top.xutils.http.ResponseInfo;
import com.top.xutils.http.callback.RequestCallBack;

public class TopBroadReciver extends BroadcastReceiver {

	public Handler handler = new Handler();

	@Override
	public void onReceive(Context context, Intent intent) {
		final Context c = context;
		// 接收安装广播
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			final String packageName = intent.getData().getSchemeSpecificPart();
			if (!TextUtils.isEmpty(packageName)) {
				LogUtil.d("监听到了安装应用====" + packageName);
				if (!packageName.equals("com.top.sdk")) {
					handler.post(new ExRunnable(context, packageName));
				}

			}

		}
		// 接收卸载广播
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
		}
		// 监听开机广播
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			// 在此需要启动服务
		}

	}

	class ExRunnable implements Runnable {
		private Context context;
		private String packageName;

		public ExRunnable(Context context, String packageName) {
			this.context = context;
			this.packageName = packageName;
		}

		@Override
		public void run() {
			PopDbService service = new ImpPopDbService(context);
			PopData pop = service.getPopDataByPackage(packageName.trim());
			LogUtil.d("查询到已安装的pop" + pop);
			
			if (null != pop) {
				service.deletePopData(pop.getPopId());
				InstallReqPragam param = new InstallReqPragam(context);
				param.setPackageName(packageName);
				InterfaceHttpbusiness business = new InstallHttpBusiness();
				business.httpBusiness(new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						LogUtil.d("成功安装"+responseInfo.result);
						String t = responseInfo.result;

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				}, param);

				SharedPrefUtil.saveInstalledAdKey(context, pop.getPopId() + "");
				String key = pop.getChannelKey();
				String value = pop.getChannelValue();
				if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {

					try {
						Context c = context.createPackageContext(packageName,
								Context.CONTEXT_INCLUDE_CODE
										| Context.CONTEXT_IGNORE_SECURITY);
						PackageManager packageManager = c.getPackageManager();
						ApplicationInfo applicationInfo = packageManager
								.getApplicationInfo(c.getPackageName(),
										PackageManager.GET_META_DATA);
						String channelValue = "";
						if (applicationInfo != null) {
							if (applicationInfo.metaData != null) {
								channelValue = applicationInfo.metaData
										.getString(key);
							}
						}
						LogUtil.d("test", "channelValue" + channelValue);

						if (!TextUtils.isEmpty(channelValue)) {
							boolean r = channelValue.equals(pop
									.getChannelValue());
							if (r) {
								LogUtil.d("本渠道的安装");

							}

						}

					} catch (NameNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}

}
