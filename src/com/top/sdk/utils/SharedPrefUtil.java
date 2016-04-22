package com.top.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.top.sdk.entity.Constants;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

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

	private static String getTodayAdKey() {
		SimpleDateFormat foramat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String k = foramat.format(today) + "adkey";
		return k;
	}

	private static String getYestodayAdkey() {
		Date today = new Date();
		SimpleDateFormat foramat = new SimpleDateFormat("yyyy-MM-dd");
		Date yes = new Date(today.getTime() - 86400000L);
		String k = foramat.format(yes) + "adkey";
		return k;
	}

}
