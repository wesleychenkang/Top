package com.top.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.top.sdk.entity.Constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

@SuppressLint("CommitPrefEdits")
public class SharedPrefUtil {

	public static SharedPreferences getAppPreferences(final Context context) {
		return context.getSharedPreferences(Constants.SETTINGS_PREF,
				Context.MODE_PRIVATE);
	}

	/**
	 * 保存一个String字符串
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setString(Context context, String key, String value) {
		try {
			Editor er = getPreferenceEditor(context);
			er.putString(key, value);
			er.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存一个int
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */

	public static void setInt(Context context, String key, int value) {
		try {
			Editor er = getPreferenceEditor(context);
			er.putInt(key, value);
			er.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 布尔
	public static void setBoolean(Context context, String key, boolean value) {
		try {
			Editor er = getPreferenceEditor(context);
			er.putBoolean(key, value);
			er.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 长整型
	public static void setLong(Context context, String key, long value) {
		try {
			Editor er = getPreferenceEditor(context);
			er.putLong(key, value);
			er.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getInt(Context context, String key, int defValue) {
		return getAppPreferences(context).getInt(key, defValue);
	}

	public static long getLong(Context context, String key, long defValue) {
		return getAppPreferences(context).getLong(key, defValue);
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		return getAppPreferences(context).getBoolean(key, defValue);
	}

	public static String getString(Context context, String key, String defValue) {
		return getAppPreferences(context).getString(key, defValue);
	}

	private static Editor getPreferenceEditor(Context context) {
		SharedPreferences pref = getAppPreferences(context);
		Editor editor = pref.edit();
		return editor;
	}

	/**
	 * 需要删除的应用包名
	 */
	private static final String KEY_REPLACE_PACKAGE = "key_replace_package";

	/**
	 * 保存需要卸载的包名
	 * 
	 * @param context
	 * @param packageName
	 */
	public static void saveDeltePackageName(Context context, String packageName) {
		setString(context, KEY_REPLACE_PACKAGE, packageName);
	}

	/**
	 * 获取需要卸载的包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeltePackageName(Context context) {
		return getString(context, KEY_REPLACE_PACKAGE, "");
	}
    
	/**
	 * 保存需要安装的时间KEY
	 */
	private static final String KEY_INSTALL_TIME = "key_install_time";
  
	/**
	 * 保存需要安装的时间
	 */
	public static void saveInstallTime(Context context, int time) {

		setInt(context, KEY_INSTALL_TIME, time);
	}
	/**
	 * 获取需要安装的时间
	 * @param context
	 * @return
	 */
	public static int getInstallTime(Context context) {

		return getInt(context, KEY_INSTALL_TIME, -1);
	}

	
	public static  final String AD_REQUEST_TIME = "ad_request_time";
	public static long getAdRequestTime(Context context){
		 
		return getLong(context, AD_REQUEST_TIME, -1);
	}
	
	public static void setAdRequestTime(Context context, long time){
		
		setLong(context, AD_REQUEST_TIME, time);
	}
	
	public static  final String AD_SHOW_TIME = "ad_show_time";
	public static long getAdShowTime(Context context){
		 
		return getLong(context, AD_SHOW_TIME, -1);
	}
	
	public static void setAdShowTime(Context context, long time){
		
		setLong(context, AD_SHOW_TIME, time);
	}
	
	/**
	 * 设置一下当前是否在下载中;
	 */
	public static  final String AD_DOWN_LOADING = "ad_down_loading";
	public static synchronized int getAdDownLoading(Context context){
		 
		return getInt(context, AD_DOWN_LOADING, -1);
	}
	
	public static  synchronized void setAdDownLoading(Context context, int time){
		setInt(context, AD_DOWN_LOADING, time);
	}
	
	
	
	/**
	 * 获取安装过的广告key;
	 * 
	 * @param context
	 * @return
	 */

	public static final String AD_INSTALLED_KEY = "ad_installed_key";

	public static String getInstalledAdKey(Context context) {
		return getString(context, AD_INSTALLED_KEY, "");
	}
	public synchronized static boolean saveInstalledAdKey(Context context, String adKey) {
		String intalledAdKey = getInstalledAdKey(context);
		if (TextUtils.isEmpty(intalledAdKey)) {
			setString(context, AD_INSTALLED_KEY, adKey);// 保存当前安装过的广告
		} else {
			if(!intalledAdKey.contains(adKey))
			setString(context, AD_INSTALLED_KEY, intalledAdKey + "," + adKey);
		}
		return true;
	}


}
