package com.top.sdk.http.reqentity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.top.sdk.utils.DESCoder;
import com.top.sdk.utils.SharedPrefUtil;

public class PopReqPragam extends BaseReqPragam {
	private Context context;

	public PopReqPragam(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toJson() {
		JSONObject obj = getBaseJson();
		try {
			obj.put("IDs", SharedPrefUtil.getInstalledAdKey(context));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = DESCoder.ebotongEncrypto(obj.toString());
		return json;
	}

}
