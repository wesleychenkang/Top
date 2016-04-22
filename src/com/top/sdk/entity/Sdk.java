package com.top.sdk.entity;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

public class Sdk {
	private String sdkId;

	private String sdkVersion;
	private String channelId;
	private Context context;

	public Sdk(Context context) {
		this.context = context;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getSdkVersion() {
		if (TextUtils.isEmpty(sdkVersion))
			sdkVersion = Build.VERSION.RELEASE;
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getSdkId() {
		return sdkId;
	}

	public void setSdkId(String sdkId) {
		this.sdkId = sdkId;
	}

}
