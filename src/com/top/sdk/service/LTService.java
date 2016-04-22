package com.top.sdk.service;

import com.top.sdk.entity.Constants;
import com.top.sdk.utils.ImageLoader;
import com.top.sdk.utils.LogUtil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

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
		Intent intent = new Intent(LTService.this,LTService.class);
		PendingIntent pend = PendingIntent.getService(context, 0, intent, 0);
		AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
	    long firstTime = SystemClock.currentThreadTimeMillis();
	    am.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, startAlarm, pend);
	}
	
	
	private void getListPopData() {
		 long nowTime = System.currentTimeMillis();
		 LogUtil.d("nowTime---"+nowTime);
		 long verTime = 20*1000*60; //时间间隔
		 if(nowTime-popShowTime>verTime){
	     LogUtil.d("--------------------获取到了广告列表数据---------------");
	     ImageLoader imagerLaoder = ImageLoader.getInstance(getApplicationContext());
	     imagerLaoder.queuePhoto(Constants.PATH, null);
	      popShowTime = nowTime; // 拉取列表的时间
		 }
		
	}
	

}
