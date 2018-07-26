package com.hiworld.canbus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hiworld.mycar.t11.R;

//弹框报警View
public class MyKuangView extends RelativeLayout{

	private Paint mPaint;
	private float centerY;
	private AnimationDrawable animationDrawable;
	private int width;
	private boolean isKuang = false;
	private boolean verticalLine = false;
	private boolean drawText = false;
	private int addCount = 8;
	private float addY = 8;
	private String warnText;
	private boolean isVisible = false;
	private ImageView imageView;
	private ImageView warnIcon;
	private int totalTime;
	private int iconFlag;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 22) {
				isKuang = true;
				if (addCount <= width-(centerY+(centerY-2)*Math.cos(Math.PI/4))) {
					addCount += 20;
					mHandler.sendEmptyMessageDelayed(22, 25);
				}else {
					addCount = (int) (width-(centerY+(centerY-2)*Math.cos(Math.PI/4)));
					mHandler.removeMessages(22);
					verticalLine = true;
					mHandler.sendEmptyMessage(44);
				}
				invalidate();
			}else if (msg.what == 44) {
				if (addY <= (centerY-2)*2*Math.sin(Math.PI/4) -20) {
					addY += 20;
					mHandler.sendEmptyMessageDelayed(44, 25);
					invalidate();
				}else {
					addY = (float) ((centerY-2)*2*Math.sin(Math.PI/4));
					mHandler.removeMessages(44);
					drawText = true;
				}
			}else if (msg.what == 66) {
				if (iconFlag % 2 == 0) {
					if (warnIcon != null) {
						warnIcon.setVisibility(View.INVISIBLE);
					}
				}else {
					if (warnIcon != null) {
						warnIcon.setVisibility(View.VISIBLE);
					}
				}
				iconFlag++;
				mHandler.sendEmptyMessageDelayed(66, 400);
			}
		}
	};
	
	public MyKuangView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public MyKuangView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public MyKuangView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	private void initPaint() {
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#66C6FCFF"));
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(3);
		mPaint.setTextSize(getContext().getResources().getDimension(R.dimen.warnSize));
		System.out.println("=======字体大小"+getContext().getResources().getDimension(R.dimen.warnSize));
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a_cirlce_blue);
		centerY = bitmap.getHeight()/2;
		width = bitmap.getWidth();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		if (isVisible) {
			if (isKuang) {
				mPaint.setColor(Color.parseColor(lineColor));
				canvas.drawLine((float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+2), (float)(centerY-(centerY-2)*Math.sin(Math.PI/4)),(float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+addCount),(float)(centerY-(centerY-2)*Math.sin(Math.PI/4)), mPaint);
				canvas.drawLine((float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+2), (float)(centerY+(centerY-2)*Math.sin(Math.PI/4)), (float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+addCount), (float)(centerY+(centerY-2)*Math.sin(Math.PI/4)), mPaint);
				if (verticalLine) {
					mPaint.setColor(Color.parseColor(lineColor));
					canvas.drawLine((float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+addCount), (float)(centerY-(centerY-2)*Math.sin(Math.PI/4)), (float)(centerY+(centerY-2)*Math.cos(Math.PI/4)+addCount), (float)(centerY-(centerY-2)*Math.sin(Math.PI/4)+addY), mPaint);
					if (drawText) {
						mPaint.setStyle(Paint.Style.FILL);
						if (!TextUtils.isEmpty(warnText)) {
							mPaint.setColor(Color.parseColor("#FFFFFF"));
							System.out.println("======画字"+warnText);
							canvas.drawText(warnText, (width-mPaint.measureText(warnText))/2+centerY-3, centerY-(mPaint.ascent()+ mPaint.descent())/2, mPaint);
							//mHandler.sendEmptyMessageDelayed(66, 1000);
							System.out.println("========画文字");
						}
					}
				}
			}
		}
	}
	
	private void startAnim() {
		
		if (!isVisible) {
			mHandler.removeMessages(66);
			iconFlag = 0;
			isKuang = false;
			drawText = false;
			addCount = 8;
			addY = 8;
			animationDrawable = (AnimationDrawable)getBackground();
			for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
				totalTime += animationDrawable.getDuration(i);
			}
			System.out.println("====总时间"+totalTime);
			mHandler.sendEmptyMessageDelayed(22, 800);
			if (imageView.getParent() == null) {
				addView(imageView);
			}
			//初始化  
			Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);  
			alphaAnimation.setDuration(1500);  //设置动画时间  
			animationDrawable.start();//启动
			imageView.startAnimation(alphaAnimation);
			mHandler.sendEmptyMessageDelayed(66, 400);
		}else {
			mHandler.removeMessages(66);
			iconFlag = 0;
			addCount = 8;
			addY = 8;
			isKuang = false;
			drawText = false;
			mHandler.removeMessages(22);
			mHandler.removeMessages(44);
			mHandler.sendEmptyMessageDelayed(22,800);
			animationDrawable = (AnimationDrawable)getBackground();
			if (animationDrawable != null) {
				System.out.println("======");
				animationDrawable.stop();
				animationDrawable.start();
			}
			if (imageView != null) {
				//初始化  
				Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);  
				alphaAnimation.setDuration(1500);  //设置动画时间  
				imageView.startAnimation(alphaAnimation);
			}
			mHandler.sendEmptyMessageDelayed(66, 400);
		}
	}
	 
	public void setText(String text){
		warnText = text;
	}  
	
	public void setVisible(){
		startAnim();
		isVisible = true;
	}
	
	public void setGone(){
		isKuang = false;
		verticalLine = false;
		drawText = false;
		iconFlag = 0;
		addCount = 8;
		addY = 8;
		mPaint.setColor(Color.parseColor("#66C6FCFF"));
		if (imageView != null && imageView.getParent() != null) {
			removeView(imageView);
		}
		if (warnIcon != null && warnIcon.getParent() != null) {
			removeView(imageView);
		}
		mHandler.removeMessages(66);
		mHandler.removeCallbacksAndMessages(null);
		isVisible = false;
	}
	
	public boolean getVisibleState(){
		return isVisible;
	}
	
	public void isTpms(boolean istpms,int level){
		if (warnIcon == null) {
			warnIcon = new ImageView(getContext());
			addView(warnIcon);
		}
		if (istpms) {
			if (warnText.contains(getResources().getString(R.string.contain_door)) || warnText.contains(getResources().getString(R.string.contain_handBrake)) || warnText.contains(getResources().getString(R.string.contain_bonnet)) || warnText.contains(getResources().getString(R.string.contain_trunk))) {
				warnIcon.setImageResource(R.drawable.icon_normal_red);
			}else if (warnText.contains(getResources().getString(R.string.contain_tire))) {
				if (level == 0) {
					warnIcon.setImageResource(R.drawable.icon_tpms_yellow);
				}else {
					warnIcon.setImageResource(R.drawable.icon_tpms_red);
				}
			}else {
				warnIcon.setImageResource(R.drawable.icon_normal_blue);
			}
		}else {
			if (warnText.contains(getResources().getString(R.string.contain_door)) || warnText.contains(getResources().getString(R.string.contain_handBrake)) || warnText.contains(getResources().getString(R.string.contain_bonnet)) || warnText.contains(getResources().getString(R.string.contain_trunk))) {
				warnIcon.setImageResource(R.drawable.icon_normal_red);
			}else {
				warnIcon.setImageResource(R.drawable.icon_normal_blue);
			}
		}
	}
	
	private String lineColor;
	public void setTextColor(String color){
		lineColor = color;
		mPaint.setColor(Color.parseColor(color));
		if (imageView == null) {
			imageView = new ImageView(getContext());
		}
		if (color.contains("FF0000")) {//红色
			/*if (isVisible) {
				setBackgroundResource(R.drawable.a25_red);
			}else {*/
				setBackgroundResource(R.anim.kuang_red);
//			}
			if (warnIcon != null) {
				System.out.println("======warnText="+warnText);
				if (warnText.contains(getResources().getString(R.string.contain_tire))) {
					warnIcon.setImageResource(R.drawable.icon_tpms_red);
				}else {
					warnIcon.setImageResource(R.drawable.icon_normal_red);
				}
			}
			if (imageView != null) {
				imageView.setImageResource(R.drawable.a_circle_red);
			}
		}else if (color.contains("FFFF00")) {//黄色
//			if (isVisible) {
//				setBackgroundResource(R.drawable.a25_yellow);
//			}else {
				setBackgroundResource(R.anim.kuang_yellow);
//			}
			if (warnIcon != null) {
				warnIcon.setImageResource(R.drawable.icon_tpms_yellow);
			}
			if (imageView != null) {
				imageView.setImageResource(R.drawable.a_circle_yellow);
			}
		}else {
//			if (isVisible) {
//				setBackgroundResource(R.drawable.a25_blue);
//			}else {
				setBackgroundResource(R.anim.kuang);
//			}
			if (warnIcon != null) {
				if (warnText.contains(getResources().getString(R.string.contain_door)) || warnText.contains(getResources().getString(R.string.contain_handBrake)) || warnText.contains(getResources().getString(R.string.contain_trunk)) || warnText.contains(getResources().getString(R.string.contain_bonnet))) {
					warnIcon.setImageResource(R.drawable.icon_normal_red);
				}else {
					warnIcon.setImageResource(R.drawable.icon_normal_blue);
				}
			}
			if (imageView != null) {
				imageView.setImageResource(R.drawable.a_cirlce_blue);
			}
		}
	}
}
