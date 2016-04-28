package com.top.sdk.actvity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.top.sdk.apputils.PackageService;
import com.top.sdk.db.impservice.ImpFileInfoDbService;
import com.top.sdk.db.impservice.ImpPopDbService;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.entity.FileInfo;
import com.top.sdk.entity.PopData;
import com.top.sdk.http.business.FileDownload;
import com.top.sdk.utils.LogUtil;
import com.top.sdk.utils.SharedPrefUtil;
import com.top.sdk.view.PopView;
import com.top.sdk.view.PopView.ClickCallBack;

@SuppressLint("NewApi")
public class PopActivity extends Activity {
	private LinearLayout ly = null;
	private static List<PopData> popList = null;
	private PopData pop;
	private int count = 0;
	private int size;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		PopDbService server = new ImpPopDbService(this);
		if (popList == null || popList.size() == 0) {
			popList = server.getCanShowPopDataList();
		}
		PopView view = new PopView(getApplicationContext());
		if (popList != null) {
			size = popList.size();
		}

		if (popList != null && size > 0) {
			pop = popList.get(0);
//			do {
//				String name = pop.getPackageName();
//				boolean result = PackageService.checkPackageName(
//						getApplicationContext(), name);
//
//				if (!result) {
//					pop = popList.get(count);
//					break;
//				}
//				count++;
//			} while (count < size);
			boolean r = view.setADContent(pop);
			if (!r) {
				finish(); // 填充失败就直接finsh
			} else {
				LogUtil.d("弹出前的大小" + popList.size());
				// 填充成功的话
				popList.remove(0);
				LogUtil.d("弹出后的大小" + popList.size());
			}
		} else {
			LogUtil.d("没有广告可弹出");
			finish();
		}
		setContentView(view);
		view.setClickCallBack(new ClickCallBack() {
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				PopActivity.this.finish();
			}

			@Override
			public void download() {
				PopActivity.this.finish();
				String url = pop.getPopUrl();
				ImpFileInfoDbService info = new ImpFileInfoDbService(
						getApplicationContext());
				FileInfo file = info.getFileInfoByUrl(url);
				if (file.getIsSuccess() == 0) {
					PackageService service = PackageService.getInstance();
					service.installBySystem(getApplicationContext(), url);
				} else {
					if (SharedPrefUtil
							.getAdDownLoading(getApplicationContext()) != 1) {
						LogUtil.d("点击后再下载apk");
						// 下载指定url的文件
						FileDownload donwload = new FileDownload(
								getApplicationContext());
						donwload.download(pop);
					}
				}
			}
		});
	}
}
