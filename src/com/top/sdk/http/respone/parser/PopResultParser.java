package com.top.sdk.http.respone.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.top.sdk.entity.PopData;
import com.top.sdk.entity.WhiteData;
import com.top.sdk.http.respone.entity.PopResult;
import com.top.sdk.utils.DESCoder;
import com.top.sdk.utils.LogUtil;

public class PopResultParser implements ParserInterface<PopResult> {
	@Override
	public PopResult parserJson(String json) {
		PopResult result = new PopResult();
		if (json != null) {
			try {
				JSONObject obj = new JSONObject(json);
				int code = obj.getInt("code");
				String msg = obj.getString("msg");
				String data = DESCoder.ebotongDecrypto(obj.getString("data"));
				LogUtil.d("返回的data json字符"+"code=="+code+" ]msg=="+msg+data);
				result.setCode(code);
				JSONObject jsonData = new JSONObject(data);
				result.setDownTime(jsonData.getInt("downTime"));
				result.setShowTime(jsonData.getInt("showTime"));
				result.setInstallTime(jsonData.getInt("installTime"));
				result.setMsg(msg);

				// 广告列表开始解析
				JSONArray arrayPop = jsonData.getJSONArray("popList");
			
				PopData pop = null;
				List<PopData> listPop = new ArrayList<PopData>();
	
				if (arrayPop != null && arrayPop.length() > 0) {
					for (int i = 0; i < arrayPop.length(); i++) {
						JSONObject j = arrayPop.getJSONObject(i);
						pop = new PopData();
						pop.setImgUrl(j.getString("imgUrl"));
						pop.setPopType(j.getInt("popType"));
						pop.setPackageName(j.getString("packageName"));
						pop.setChannelName(j.getString("channelName"));
						pop.setPopUrl(j.getString("popUrl"));
						pop.setWhiteId(j.getInt("whiteId"));
						listPop.add(pop);
					}
					result.setListPop(listPop);
				}

				// 白名单开始解析
				JSONArray arrayWhite = jsonData.getJSONArray("whiteList");
				WhiteData white = null;
				List<WhiteData> array = null;
				if (arrayWhite != null) {
					array = new ArrayList<WhiteData>();
					for (int i = 0; i < arrayWhite.length(); i++) {
						white = new WhiteData();
						JSONObject z = arrayWhite.getJSONObject(i);
						white.setChannleKey(z.getString("channelKey"));
						white.setPopId(z.getInt("id"));
						white.setListPackageName(z.getString("listPackageName"));
						array.add(white);
						LogUtil.d("===列表中的白名单列表数据"+white.toString());
					}
					result.setListWhite(array);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

}
