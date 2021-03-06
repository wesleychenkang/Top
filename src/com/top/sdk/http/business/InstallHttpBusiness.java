package com.top.sdk.http.business;

import com.top.sdk.entity.Constants;
import com.top.sdk.http.reqentity.BaseReqPragam;
import com.top.sdk.http.reqentity.InstallReqPragam;
import com.top.sdk.http.respone.entity.BaseResult;
import com.top.xutils.HttpUtils;
import com.top.xutils.http.RequestParams;
import com.top.xutils.http.callback.RequestCallBack;
import com.top.xutils.http.client.HttpRequest.HttpMethod;

public class InstallHttpBusiness implements InterfaceHttpbusiness{
	@Override
	public void httpBusiness(RequestCallBack<String> call, BaseReqPragam pargam) {
		HttpUtils http = new HttpUtils();
		RequestParams requst = new RequestParams();
		requst.addBodyParameter("appId","1");
		requst.addBodyParameter("channelId","1046");
		requst.addBodyParameter("header", pargam.toJson());
		http.send(HttpMethod.POST, Constants.URL_INSTALL_SUCESS, requst, call);
	}
	
}
