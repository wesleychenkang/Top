package com.top.sdk.actvity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.top.sdk.db.impservice.ImpPopDbService;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.entity.PopData;
import com.top.sdk.view.PopView;
import com.top.sdk.view.PopView.ClickCallBack;

@SuppressLint("NewApi")
public class PopActivity extends Activity {
	private LinearLayout ly = null;
    private List<PopData> popList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		PopDbService server = new ImpPopDbService(this);
		popList = server.getPopDataList();
		PopView view = new PopView(getApplicationContext());
	   if(popList!=null&& popList.size()>0)
		 view.setADContent(popList.get(0));
		setContentView(view);
		view.setClickCallBack(new ClickCallBack() {
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				PopActivity.this.finish();
			}

			@Override
			public void download() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "点击到广告了",
						Toast.LENGTH_LONG).show();
			}
		});
		// ly = (LinearLayout) findViewById(R.id.pop);
	}
}
