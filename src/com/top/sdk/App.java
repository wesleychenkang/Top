package com.top.sdk;

import android.app.Application;
import android.content.Intent;

import com.top.sdk.logic.UserAction;
import com.top.sdk.service.LTService;
import com.top.sdk.utils.LogUtil;
import com.top.sdk.utils.SharedPrefUtil;

public class App extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LogUtil.d("app", "appplicat  oncreate");
		userAction(); // 用户激活
		CrashHandler handler = CrashHandler.getInstance();
		handler.init(getApplicationContext());
		SharedPrefUtil.setAdDownLoading(getApplicationContext(), -1);// 清空处理

		loadService();
	}

	public void userAction() {
		UserAction user = new UserAction();
		user.userActivation(getApplicationContext());

	}

	private void loadService() {

		Intent intent = new Intent(this, LTService.class);
		startService(intent);
	}
}
