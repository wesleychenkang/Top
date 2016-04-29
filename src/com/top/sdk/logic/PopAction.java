package com.top.sdk.logic;


/**
 * 广告相关
 * 
 * @author Administrator
 * 
 */
public class PopAction {
   
	public static String whiteData = "com.android.vending,com.huawei.gamebox,com.oppo.market,com.pp.assistant,com.letv.letvshop,com.gionee.aora.market,com.lenovo.leos.appstore,com.tencent.mm,com.mappn.gfan,com.meitu.appmarket,com.letv.app.appstore,com.huawei.appmarket,com.meizu.mstore,com.qihoo.gameunion,com.aspire.mmui,com.aspire.mmservice,com.qihoo.appstore,com.xiaomi.gamecenter,com.xiaomi.market,com.tencent.android.qqdownloader,com.bbk.appstore,com.egame,com.wandoujia.phoenix2,com.infinit.wostore.u,cn.emagsoftware.gamehall,com.aspire.mm,com.muzhiwan.market,com.vivo.game,com.sogou.androidtool,cn.goapk.market,com.qihoo.secstore";
	/**
	 * 包名是否符合
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean checkPackageName(String packageName) {
         boolean r = checkWhitePackageName(packageName);
		// 检查当前的包名是否在所包含的应用商店里面
		return r;

	}

	/**
	 * 检查包名是否在白名单(应用商店列表);
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean checkWhitePackageName(String packageName) {
		if (whiteData.contains(packageName)) {
			return true;
		}
		return false;
	}

	public static boolean checkChannelName(String channnelKey,
			String channelName) {

		return true;
	}

}
