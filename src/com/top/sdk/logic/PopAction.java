package com.top.sdk.logic;

import java.util.List;

import com.top.sdk.entity.PopData;
import com.top.sdk.entity.WhiteData;
import com.top.sdk.http.business.InterfaceHttpbusiness;
import com.top.sdk.http.business.PopHttpBusiness;
import com.top.sdk.http.reqentity.PopReqPragam;
import com.top.sdk.http.respone.entity.PopResult;
import com.top.sdk.http.respone.parser.PopResultParser;
import com.top.sdk.utils.LogUtil;
import com.top.xutils.exception.HttpException;
import com.top.xutils.http.ResponseInfo;
import com.top.xutils.http.callback.RequestCallBack;

import android.content.Context;

/**
 * 广告相关
 * 
 * @author Administrator
 * 
 */
public class PopAction {

	/**
	 * 包名是否符合
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean checkPackageName(String packageName) {
		if (packageName.equals("com.tencent.mm")) {
			return true;
		}
		return false;

	}

	/**
	 * 检查是否在白名单
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean checkWhitePackageName(String packageName) {

		return true;
	}

	
	

}
