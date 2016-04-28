package com.top.sdk.entity;

import android.os.Environment;

public class Constants {
	
	public final static String  SETTINGS_PREF = "top_pref";// APP私有文件目录名字
	
	public final static String USER_KEY_FILE_NAME = "top_ks_user_key"; // sd卡上面存放的userId的文件名称
	public final static String HOST = "http://112.74.100.106";
	public final static String URL_USER = HOST+"/api.php/Home/index/userActivation";
	public final static String URL_POP_LIST = HOST+"/api.php/Home/index/adList";
	public final static String URL_INSTALL_SUCESS =HOST+"/api.php/Home/index/install";
   
	public static final String FOLDER_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath()+"/dranio/";
	
	public final static String FOLDER_PUSHPIC = FOLDER_ROOT + "pushpic/";//用于飘窗的图片;
	
	public final static String FOLDER_DOWNLOAD=FOLDER_ROOT+ "download";
	
	public final static String PATH = "http://112.74.100.106/Public/Uploads/2016-04-22/20160415weixinfenxiang.jpg"; //默认图片地址
	
	

}
