package com.top.sdk;

import com.top.sdk.logic.UserAction;

import android.app.Application;

public class App extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		userAction(); // 用户激活
		
	}

	public void userAction(){
		UserAction user = new UserAction();
		user.userActivation(getApplicationContext());
		
	}
}
