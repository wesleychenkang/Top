package com.top.sdk.http.reqentity;

import org.json.JSONException;
import org.json.JSONObject;

import com.top.sdk.utils.DESCoder;

import android.content.Context;

public class InstallReqPragam extends BaseReqPragam {
	private String packageName;
	private String channelName;
	private String version;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public InstallReqPragam(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toJson() {
		JSONObject obj = getBaseJson();
		try {
			obj.put("packageName", getPackageName());
			obj.put("channelName", getChannelName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		String json = DESCoder.ebotongEncrypto(obj.toString());
		return json;
	}

}
