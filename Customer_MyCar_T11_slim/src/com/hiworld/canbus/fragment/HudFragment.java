package com.hiworld.canbus.fragment;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class HudFragment extends Fragment {

	 private View mContentView; 
	 private Activity activity;
	 
	 private ImageView mPanel, mSpeedHigh, mSpeedMiddle, mSpeedLow,
	                                mSafeBelt, mHtpms, mHandBreak;
	 private TextView mCurrentTime, mAutoTime, mAutoMileage;
	 
	 private int num = 0;
	 private Timer timer; // 时间
	 private float maxDegree = 0.0f;
	 private float degree = 0.0f; // 记录指针旋转
	 private RotateAnimation animation;
	 private boolean flag = true;
	 
	 private int a =0;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setHudHandler(mHandler);
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				setHudInfo(hudInfo);
				break;
			case 0x100:
				a++;
				switch (a = a%2) {
				case 0:
					mSafeBelt.setVisibility(View.GONE);
					break;
                case 1:
                	mSafeBelt.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
				mHandler.sendEmptyMessageDelayed(0x100, 500);
				break;
			default:
				break;
			}
		}
		
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//Log.i("aaa", "mContentView ="+mContentView);
		 if (null == mContentView) {
	        	mContentView = inflater.inflate(R.layout.fragment_hud, container,false);
	        	initView(mContentView);
	        	activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						
						setHudInfo(CarPcInfo.getInstance());
					}
				});
	        }else  {  
	            ViewGroup parent = (ViewGroup) mContentView.getParent();  
	            if (parent != null)  {  
	                parent.removeView(mContentView);  
	            }  
	            //指针复原打补丁
                makePanelBug();
	        }  
			return mContentView;
	}

	private void makePanelBug() {
		
		maxDegree++;
		// 开始转动
		timer = new Timer();
		// 设置每5毫秒转动一下
		timer.schedule(new NeedleTask(), 0, 5);
		flag = true;
	}

	protected void setHudInfo(CarPcInfo instance) {
		
		Calendar c = Calendar.getInstance();  
		int hour = c.get(Calendar.HOUR_OF_DAY);  
		int minute = c.get(Calendar.MINUTE);  
		mCurrentTime.setText(String.format("%02d:%02d", hour, minute));
		
		//转速
		if (instance.getM_iEngineSpeed() > 8000){
			if (maxDegree != 0){
				maxDegree = 0;
				// 开始转动
				timer = new Timer();
				// 设置每5毫秒转动一下
				timer.schedule(new NeedleTask(), 0, 5);
				flag = true;
			}
		}else{
			if (maxDegree != (float)instance.getM_iEngineSpeed()){
				maxDegree = (float)instance.getM_iEngineSpeed();
				
				// 开始转动
				timer = new Timer();
				// 设置每5毫秒转动一下
				timer.schedule(new NeedleTask(), 0, 5);
				flag = true;
			}
		}
		
		int iSpeed = (int) instance.getM_fInstantSpeed();
		if (iSpeed == 0xffff) {
			iSpeed = 0;
		}
		int Single = iSpeed%10;//个位
		int Ten = (iSpeed/10)%10;//十位
		int Hundred = (iSpeed/100)%10;//百位
		
		mSpeedHigh.setImageResource(R.drawable.small_num0+Hundred);
		mSpeedMiddle.setImageResource(R.drawable.small_num0+Ten);
		mSpeedLow.setImageResource(R.drawable.small_num0+Single);
		
		int iAutoTime  = instance.getM_iSelfstart_drivertime();
		hour = iAutoTime/60;
		minute = iAutoTime%60;
		mAutoTime.setText(String.format("%02d:%02d", hour, minute));
		
		iAutoTime = instance.getM_iSelfstart_mileage();
		mAutoMileage.setText(iAutoTime+"  Km");
		
		if (instance.getM_iHanderBrake() == 1) {
			mHandBreak.setImageResource(R.drawable.handbreak_warn);
		} else {
			mHandBreak.setImageResource(R.drawable.handbreak_tip);
		}
		
		if (instance.getM_iLFSafebeltAble() == 1 ){
			if (instance.getM_iLFSafebelt() == 0) {
				mSafeBelt.setImageResource(R.drawable.safebelt_warn);
				if (instance.getM_fInstantSpeed() > 20) {
					mHandler.removeMessages(0x100);
					mHandler.sendEmptyMessageDelayed(0x100, 500);
				}
			}else{
				mHandler.removeMessages(0x100);
				mSafeBelt.setVisibility(View.VISIBLE);
				mSafeBelt.setImageResource(R.drawable.safebelt_tip);
			}
		}else{
			mSafeBelt.setImageResource(R.drawable.safebelt_tip);
		}
		
		if (instance.getM_iHtpmsWarn() == 1) {
			mHtpms.setImageResource(R.drawable.htpms_warn);
		} else {
			mHtpms.setImageResource(R.drawable.htpms_tip);
		}
	}

	private void initView(View view) {
		
		mPanel = (ImageView) view.findViewById(R.id.id_panel); 
		mSpeedHigh = (ImageView) view.findViewById(R.id.id_speed_high); 
		mSpeedMiddle = (ImageView) view.findViewById(R.id.id_speed_ten); 
		mSpeedLow = (ImageView) view.findViewById(R.id.id_speed_low); 
		
		mSafeBelt = (ImageView) view.findViewById(R.id.iv_safebelt_tip); 
		mHtpms = (ImageView) view.findViewById(R.id.iv_htpms_tip); 
		mHandBreak = (ImageView) view.findViewById(R.id.iv_handbreak_tip); 
		
		mCurrentTime = (TextView) view.findViewById(R.id.tv_currentTime);
		mAutoTime = (TextView) view.findViewById(R.id.tv_autoTime);
		mAutoMileage = (TextView) view.findViewById(R.id.tv_autoMileage);
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
		if (timer != null)	{
			timer.cancel();
			timer = null;
		}
	}
	
	private class NeedleTask extends TimerTask {
		@Override
		public void run() {
			
			if (degree <= maxDegree * (190 / 8000.0f)) {
				handler1.sendEmptyMessage(0);
			}
			if (degree > maxDegree * (190 / 8000.0f) && flag == true) {
				handler2.sendEmptyMessage(0);
			}
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 设置仪表盘指针转动动画
			// 仪表盘最大是190度
			if (degree >= maxDegree * (190 / 8000.0f)) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
				
			}else{
			degree += 3.0f;
			animation = new RotateAnimation(degree, maxDegree * (190 / 8000.0f),
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			}
			// 设置动画时间1秒
			animation.setDuration(10);
			animation.setFillAfter(true);
			mPanel.startAnimation(animation);
			flag = false;
		}
	};
	
	@SuppressLint("HandlerLeak")
	private Handler handler2 = new Handler() {
		@Override
		public void handleMessage(Message msg) { // 设置仪表盘指针转动动画
			// 仪表盘最大是190度，这个是自己测出来的
			if (degree <= maxDegree * (190 / 8000.0f)) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
			}else{
			degree += -3.0f;
			animation = new RotateAnimation(degree, maxDegree * (190 / 8000.0f),
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			}
			// 设置动画时间5毫秒
			animation.setDuration(10);
			animation.setFillAfter(true);
			mPanel.startAnimation(animation);
			flag = true;
		}
	};

}
