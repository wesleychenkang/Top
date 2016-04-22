package com.top.sdk.actvity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.top.sdk.view.PopView;
import com.top.sdk.view.PopView.ClickCallBack;

@SuppressLint("NewApi")
public class PopActivity extends Activity {
	private LinearLayout ly = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		PopView view = new PopView(getApplicationContext());
		view.setADContent(null);
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
