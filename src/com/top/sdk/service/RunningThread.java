package com.top.sdk.service;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.top.sdk.actvity.PopActivity;
import com.top.sdk.entity.PopData;
import com.top.sdk.logic.PopAction;
import com.top.sdk.processes.RunProcessManager;
import com.top.sdk.utils.LogUtil;
import com.top.sdk.utils.SharedPrefUtil;

public class RunningThread extends Thread {
	private Context context;
	private boolean keepRunning = true;
	private boolean appChangedFlag = false;

	public RunningThread(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		LogUtil.d("app", "检测线程启动了");
		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		while (keepRunning) {

			String packageName = "";

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				packageName = RunProcessManager.getForegroundApp();
			} else {
				packageName = mActivityManager.getRunningTasks(1).get(0).topActivity
						.getPackageName();
			}
			// 如果不是本应用记录栈顶应用的启动时间
			if (!packageName.equals("com.top.sdk"))
				recordTopPackage(packageName);

			// 判断当前能否弹出广告
			boolean result = false;
			if (!TextUtils.isEmpty(packageName)
					&& PopAction.checkPackageName(packageName)) {
				result = true;
			}
			long nowTime = 0;
			//
			if (appChangedFlag && result) { // 如果应用正切换为新的应用，并且在应用市场里面的话，就展示广告
				nowTime = System.currentTimeMillis(); // 记录当前弹出广告的时间
				if (nowTime - SharedPrefUtil.getLong(context, packageName, 0)> 1000 * 60) {
					showADFloatingWindow();
					appChangedFlag = false;
					SharedPrefUtil.setLong(context,packageName,
							System.currentTimeMillis());
				}
			} else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

	private void showADFloatingWindow() {
		Intent intent = new Intent();
		intent.setClass(context, PopActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);

	}

	private List<PopData> getPopDataList() {
		// TODO Auto-generated method stub
		return null;
	}

	private void recordTopPackage(String packageName) {
		if (packageName.equals(AppRecord.pName)) {
			long now = System.currentTimeMillis(); // 记录持续的时间
			if (now - AppRecord.startTime > 4 * 1000) {
				appChangedFlag = false;
			}
			 LogUtil.d("记录到了当前的应用========" + packageName);
		} else {
			if (AppRecord.pName != null) {
				// 换应用使用了
				appChangedFlag = true; // 开启了新的应用
			}
			AppRecord.pName = packageName;
			AppRecord.startTime = System.currentTimeMillis();

		}

	}

	static class AppRecord {
		static String pName = null;
		public static long startTime;
	}

}
