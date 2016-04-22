package com.top.sdk.logic;

/**
 * 广告相关
 * @author Administrator
 *
 */
public class PopAction {
	
	/**
	 * 包名是否符合
	 * @param packageName
	 * @return
	 */
	public static boolean checkPackageName(String packageName){
		 if(packageName.equals("com.tencent.mm")){
			 return true;
		 }
		return false;
		
	}
	
	/**
	 * 检查是否在白名单
	 * @param packageName
	 * @return
	 */
	public static boolean checkWhitePackageName(String packageName){
		
		return true;
	}
	
	
	
	

}
