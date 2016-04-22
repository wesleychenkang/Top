package com.top.sdk.http.business;

import com.top.xutils.HttpUtils;

public class FileDownload {
   public void download(String url){
	    HttpUtils http = new HttpUtils();
	    http.download(url, null, null);
   }
}
