package com.top.sdk.http.reqentity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class UpdateReqPragam extends BaseReqPragam {
	private String packageName;
	private String channelName;

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

	public UpdateReqPragam(Context context) {
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

		return obj.toString();
	}

}
