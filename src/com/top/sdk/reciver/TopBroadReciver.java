package com.top.sdk.reciver;

import com.top.sdk.utils.LogUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TopBroadReciver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {

		// 接收安装广播
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			String packageName = intent.getDataString();
			LogUtil.d("安装了:" + packageName + "包名的程序");
			// 先通过包名去本地白名单库，查询该渠到KEY值
			boolean hasKey = true;
			if (hasKey) { // 如果有对应KEY消息，还有存在的对应的广告ID

				boolean r = true; // 再去广告表里面查询对比包名和渠道名
				if (r) { // 如果一致，
					// 说明为成功推荐用户安装一份广告 可以删除一条广告数据
				} else {
					// 不一致，说明安装的非本渠道，可以考虑提示用户更新，或者强制更新

				}

			}

			// 如果不在白名单，而只在广告列表中

		}
		// 接收卸载广播
		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
			String packageName = intent.getDataString();
			LogUtil.d("卸载了:" + packageName + "包名的程序");
		}
		// 监听开机广播
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			// 在此需要启动服务

		}

	}
}
