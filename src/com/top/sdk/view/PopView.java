package com.top.sdk.view;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.top.sdk.R;
import com.top.sdk.entity.Constants;
import com.top.sdk.entity.PopData;
import com.top.sdk.utils.FileUtil;
import com.top.sdk.utils.ImageLoader;

public class PopView extends FrameLayout implements OnClickListener {
	private ImageView imgAd;
	private ImageView imgMove;
	private ClickCallBack callBack;
	private final int imgAdId = 0x1233432;
	private final int imgRemoveId = 0x1233437;
	private static final float AD_HEIGHT_PERCENTAGE = 3f / 4; // 高度的4分之3
	private static final float AD_WIDTH_PERCENTAGE = 11f / 12; // 宽度的 12分之11
	private static int screenWidth;
	private static int screenHeight;
	private int controlHeight;
	private int controlWidth;
	private Context context;
	private Bitmap screenImageBitmap = null;

	public PopView(Context context) {
		super(context);
		this.context = context;
	}

	@SuppressLint("NewApi")
	private void initView(Context context,int width,int height) {
		setBackgroundColor(Color.TRANSPARENT);
		
		FrameLayout lay = new FrameLayout(context);
		LayoutParams framelp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		framelp.gravity = Gravity.CENTER;
		addView(lay,framelp);
		
		imgAd = new ImageView(context);
		Drawable drawable = getResources().getDrawable(R.drawable.pop);
		imgAd.setBackground(drawable);
		imgAd.setId(imgAdId);
		imgAd.setOnClickListener(this);
		
		LayoutParams lpAd = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lpAd.height = height;
		lpAd.width = width;
		lpAd.gravity = Gravity.CENTER;
		lay.addView(imgAd,lpAd);
		
		Drawable drawable_move = getResources().getDrawable(
				R.drawable.lock_btn_remove_normal);
		imgMove = new ImageView(context);
		imgMove.setBackground(drawable_move);
		imgMove.setId(imgRemoveId);
		imgMove.setOnClickListener(this);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.RIGHT;
		lay.addView(imgMove, lp);
	}

	
	public boolean setADContent(PopData popData){
		
		boolean reuslt = calculateTargetWidthAndHeight();
		if(!reuslt){
			//计算图片结果失败直接返回;
			return false;
		}
		//填充图片内容
		initView(context,controlWidth,controlHeight);
		
		
		return true;
	}
	// 控制目标广告的高度与宽度

	private boolean calculateTargetWidthAndHeight() {
		calculateScreenWidthAndHeight(); // 去得屏幕的宽度与高度
		int controlAreaHeight = (int) (screenWidth * AD_WIDTH_PERCENTAGE);
		int controlAreaWidth = (int) (screenHeight * AD_HEIGHT_PERCENTAGE);
		Bitmap screenBitmap = getAdBitmap();
		if (screenBitmap == null) {
			ImageLoader imagerLoader = ImageLoader.getInstance(context);
			imagerLoader.queuePhoto(Constants.PATH, null);
			return false;
		}
		int picWidth = screenBitmap.getWidth();
		int picHeight = screenBitmap.getHeight();
		float picWidthAndHeightRatio = 1.0f * picWidth / picHeight; //图片的宽度与高度的比例
		float contolWidthAndHeightRatio = 1.0f * controlAreaWidth   //显示控制的区域的宽度与高度的比例
				/ controlAreaHeight;

		int newHeight = 0, newWidth = 0;

		if (picWidthAndHeightRatio > contolWidthAndHeightRatio) {
			newWidth = controlAreaWidth;
			newHeight = (int) (1.0 * newWidth * picHeight / picWidth);
		} else {
                     //宽图
			newHeight = controlAreaHeight;
			newWidth = (int) (1.0 * newHeight * picWidth / picHeight);
         
		}
		controlHeight = newHeight;
		controlWidth = newWidth;
		return true;
	}
	

	private Bitmap getAdBitmap() {
		 if(screenImageBitmap!=null){
			 return screenImageBitmap;
		 }
		 File file = FileUtil.getPushPicFile(Constants.PATH);
		 if(file!=null && file.exists()){
			 screenImageBitmap = ImageLoader.decodeFile(file);
		 }
		return screenImageBitmap;
	}
	
	// 计算一下屏幕的宽与高
	private void calculateScreenWidthAndHeight() {
		// TODO Auto-generated method stub
		if (screenWidth != 0 && screenHeight != 0) {
			// 已经计算过了
			return;
		}
		WindowManager window = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		window.getDefaultDisplay().getMetrics(metrics);
		if (metrics.heightPixels > metrics.widthPixels) {
			screenHeight = metrics.heightPixels;
			screenWidth = metrics.widthPixels;
		} else {
			screenHeight = metrics.widthPixels;
			screenWidth = metrics.heightPixels;
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case imgAdId:
			if (callBack != null) {
				callBack.download();
			}
			break;
		case imgRemoveId:
			if (callBack != null) {
				callBack.remove();
			}
			break;
		}
	}

	public void addAdView(Bitmap bitMap) {
		int width = bitMap.getWidth();
		int height = bitMap.getHeight();
		Drawable drawable = new BitmapDrawable(bitMap);
		imgAd.setBackgroundDrawable(drawable);
	}

	public void setClickCallBack(ClickCallBack callBack) {
		this.callBack = callBack;
	}

	public interface ClickCallBack {
		public void remove();

		public void download();

	}

}
