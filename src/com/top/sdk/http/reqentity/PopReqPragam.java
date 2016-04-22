package com.top.sdk.http.reqentity;

import android.content.Context;

import com.top.sdk.utils.DESCoder;

public class PopReqPragam extends BaseReqPragam {

	public PopReqPragam(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toJson() {
		String json = DESCoder.ebotongEncrypto(getBaseJson().toString());
		// TODO Auto-generated method stub
		return json;
	}

}
