package com.hiworld.canbus.fragment;


import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHudFragment extends Fragment {

	private View mContentView; 
	private Activity activity;
	
	private ImageView mIvLight, mIvDoublelight, mIvTrunLight, mIvTrunRight, mIvFarlight
	                              , mIvSafeBelt, mIvWater, mIvBattery, mIvHand, mIvHtpms;
	private ImageView mIvDoorWarn, mIvDoorLf, mIvDoorRf, mDoorLr, mDoorRr;
	
	private ImageView mIvSpeedTen, mIvSpeedFloat, mIvSpeedHundred,
	                              mSmallTen, mSmallFloat, mSmallHundred,
	                              mBigDangwei, mSmallDangwei;
	
	private ImageView mIvAnima, mIvMeter, mIvZhuansu;

	private boolean bRunnable=false;
	private int mLeftNum, mRightNum, mSafeNum;
	
	private RelativeLayout mBigSpeedLL, mSmallSpeedLL, mHudBackground;
	private TextView mWarnTv;
	private int mDoor = 0, mSafeBelt, mHander, mHtpms;
	private int mWarnId;
	
	private int mZhuansuValue;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setHudHandler(mHandler);
		Log.d("ddd", "onAttach!");
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
			case 0x300:
				if (mIvAnima != null) {
					mIvAnima.setImageResource(R.anim.animation);
					AnimationDrawable anim = (AnimationDrawable) mIvAnima.getDrawable();
					if (anim.isRunning()) {
						anim.stop();
					}
					anim.start();
				}
				
				break;
			case 0x100:
				mLeftNum++;
				mLeftNum = mLeftNum % 2;
				if (mLeftNum == 0) {
					mIvTrunLight.setVisibility(View.GONE);
					mIvTrunRight.setVisibility(View.GONE);
				}else {
//                    if (CarPcInfo.getInstance().getM_iDoubleLight() == 1) {
//                    	mIvTrunLight.setVisibility(View.VISIBLE);
//                    	mIvTrunRight.setVisibility(View.VISIBLE);
//					}
//                    else 
                    	if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1&& CarPcInfo.getInstance().getM_iRightTrunLight()==1) {
                    	mIvTrunLight.setVisibility(View.VISIBLE);
                    	mIvTrunRight.setVisibility(View.VISIBLE);
					}else if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1) {
						mIvTrunLight.setVisibility(View.VISIBLE);
					}
					else if (CarPcInfo.getInstance().getM_iRightTrunLight() == 1) {
						mIvTrunRight.setVisibility(View.VISIBLE);
					}
				}
				
				bRunnable = true;
				mHandler.removeMessages(0x100);
				mHandler.sendEmptyMessageDelayed(0x100, 300);
				break;
			case 0x101:
				mRightNum++;
				switch (mRightNum = mRightNum%2) {
				case 0:
					mIvDoublelight.setVisibility(View.GONE);
					break;
                case 1:
                	mIvDoublelight.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
				mHandler.sendEmptyMessageDelayed(0x101, 300);
				break;
			case 0x102:
				mSafeNum++;
				switch (mSafeNum = mSafeNum%2) {
				case 0:
					mIvSafeBelt.setVisibility(View.GONE);
					break;
                case 1:
                	mIvSafeBelt.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
				mHandler.sendEmptyMessageDelayed(0x102, 300);
				break;
			default:
				break;
			}
		}
		
	};


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		Log.d("ddd", "onCreateView!");
		if (null == mContentView){
			mContentView = inflater.inflate(R.layout.fragment_new_hud, container,false);
        	initView(mContentView);
        	activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					setHudInfo(CarPcInfo.getInstance());
				}
			});
		}else{
			ViewGroup parent = (ViewGroup) mContentView.getParent();  
            if (parent != null)  {  
                parent.removeView(mContentView);  
            }  
		}

		return mContentView;
	}


	protected void setHudInfo(CarPcInfo instance) {
		
		int mWarnTemp = 0;
		boolean bDoor=false, bSafeBelt=false, bHanders=false, bHtpms=false;
		
		if (instance.getM_iFarLight() == 1) {
			mIvFarlight.setImageResource(R.drawable.hud_farlight_press);
		} else {
			mIvFarlight.setImageResource(R.drawable.hud_transparent);
		}
		
		if (instance.getM_iILL() == 1 ) {
			mIvLight.setImageResource(R.drawable.hud_light_press);
		} else {
			mIvLight.setImageResource(R.drawable.hud_transparent);
		}

		if (( instance.getM_iLeftTrunLight() == 0x01
				||instance.getM_iRightTrunLight() == 0x01) && !bRunnable) {//双闪灯
			bRunnable = true;
			mHandler.removeMessages(0x100);
			mHandler.sendEmptyMessageDelayed(0x100, 300);
		}else if (instance.getM_iDoubleLight() == 0x00 &&instance.getM_iLeftTrunLight() == 0x00
				&&instance.getM_iRightTrunLight() == 0x00){
			if ( bRunnable) {
				bRunnable = false;
				mLeftNum = 0;
				mHandler.removeMessages(0x100);
			}
			
			mIvTrunLight.setVisibility(View.GONE);
			mIvTrunRight.setVisibility(View.GONE);
		}
		
		if (instance.getM_iDoubleLight() == 1) {
			mHandler.removeMessages(0x101);
			mHandler.sendEmptyMessageDelayed(0x101, 300);
		} else {
			mRightNum = 0;
			mIvDoublelight.setVisibility(View.GONE);
			mHandler.removeMessages(0x101);
		}
		
//		if (instance.getM_iLeftTrunLight() == 1) {
//			mHandler.removeMessages(0x100);
//			mHandler.sendEmptyMessageDelayed(0x100, 500);
//		} else {
//			mIvTrunLight.setVisibility(View.GONE);
//			mHandler.removeMessages(0x100);
//		}
//		
//		
//		if (instance.getM_iRightTrunLight() == 1) {
//			mHandler.removeMessages(0x101);
//			mHandler.sendEmptyMessageDelayed(0x101, 500);
//		} else {
//			mIvTrunRight.setVisibility(View.GONE);
//			mHandler.removeMessages(0x101);
//		}
		
		if (mSafeBelt != instance.getM_iLFSafebelt()) {
			mSafeBelt = instance.getM_iLFSafebelt();
			bSafeBelt = true;
		}
		
		if (instance.getM_iLFSafebelt() == 0) {
			mIvSafeBelt.setVisibility(View.VISIBLE);
			mIvSafeBelt.setImageResource(R.drawable.hud_safebelt_press);
			if (instance.getM_fInstantSpeed() > 15) {
				mWarnTemp = 3;
				
				mHandler.removeMessages(0x102);
				mHandler.sendEmptyMessageDelayed(0x102, 300);
			} else{
				mHandler.removeMessages(0x102);
			}
		} else {
			mIvSafeBelt.setVisibility(View.VISIBLE);
			mIvSafeBelt.setImageResource(R.drawable.hud_transparent);
			mSafeNum = 0;
			mHandler.removeMessages(0x102);
		}
		
		mIvDoorWarn.setVisibility(View.GONE);
		mIvDoorLf.setVisibility(View.GONE);
		mIvDoorRf.setVisibility(View.GONE);
		mDoorLr.setVisibility(View.GONE);
		mDoorRr.setVisibility(View.GONE);
		
		int temp = 0;
		
		if (instance.getM_iLFDoor() == 1) {
			mIvDoorWarn.setVisibility(View.VISIBLE);
			mIvDoorLf.setVisibility(View.VISIBLE);
			temp++;
		}
		if (instance.getM_iRFDoor() == 1) {
			mIvDoorWarn.setVisibility(View.VISIBLE);
			mIvDoorRf.setVisibility(View.VISIBLE);
			temp++;
		}
		if (instance.getM_iLRDoor() == 1) {
			mIvDoorWarn.setVisibility(View.VISIBLE);
			mDoorLr.setVisibility(View.VISIBLE);
			temp++;
		}
		if (instance.getM_iRRDoor() == 1) {
			mIvDoorWarn.setVisibility(View.VISIBLE);
			mDoorRr.setVisibility(View.VISIBLE);
			temp++;
		}
		
		if (temp != mDoor) {
			mDoor = temp;
			bDoor = true;
		}
		
		if (temp > 0  ) {
			if (instance.getM_fInstantSpeed() > 0) {
				mWarnTemp = 4;
			}
		}
		

		
		if (instance.getM_iCooltemp() > 110 && instance.getM_iFire() ==1) {
			mIvWater.setImageResource(R.drawable.hud_water_high);
		}else if (instance.getM_iCooltemp() < 60 && instance.getM_iFire() ==1) {
			mIvWater.setImageResource(R.drawable.hud_water_low);
		}else{
			mIvWater.setImageResource(R.drawable.hud_transparent);
		}
		
		if (instance.getM_iEngineSpeed() == 0) {
			mIvBattery.setImageResource(R.drawable.hud_battery_warn);
		} else {
			mIvBattery.setImageResource(R.drawable.hud_transparent);
		}
		
		if (mHander != instance.getM_iHanderBrake()) {
			mHander = instance.getM_iHanderBrake();
			bHanders = true;
		}
		
		if (instance.getM_iHanderBrake() == 1) {
			if (instance.getM_fInstantSpeed() > 0) {
				mWarnTemp = 2;
			}
			mIvHand.setImageResource(R.drawable.hud_hand_warn);
		} else {
			mIvHand.setImageResource(R.drawable.hud_transparent);
		}
		
		if (mHtpms != instance.getM_iHtpmsWarn()) {
			mHtpms = instance.getM_iHtpmsWarn();
			bHtpms = true;
		}
		
		if (instance.getM_iHtpmsWarn() == 1) {
			mWarnTemp = 1;
			mIvHtpms.setImageResource(R.drawable.hud_htpms_warn);
		} else {
			mIvHtpms.setImageResource(R.drawable.hud_transparent);
		}
		
		if (mWarnTemp > 0) {
			mWarnId = mWarnTemp;
			if (bHtpms && instance.getM_iHtpmsWarn() == 1) {
				mWarnId = 1;
			}
			if (bHanders && instance.getM_iHanderBrake() == 1 && instance.getM_fInstantSpeed() > 0) {
				mWarnId = 2;
			}
			if (bSafeBelt && instance.getM_iLFSafebelt() == 0 && instance.getM_fInstantSpeed() > 15) {
				mWarnId = 3;
			}
			if (bDoor && mDoor > 0) {
				mWarnId = 4;
			}
			switch (mWarnId) {
			case 1:
				mWarnTv.setText("轮胎胎压异常");
				break;
			case 2:
				mWarnTv.setText("请放下手刹");
				break;
			case 3:
				mWarnTv.setText("请系好安全带");
				break;
			case 4:
				mWarnTv.setText("车门未关闭");
				break;
			default:
				break;
			}
			setWarnTankuan(true);
		}else{
			setWarnTankuan(false);
		}

		//档位
		if (instance.getM_iGearAble() == 1) {
			mIvAnima.setVisibility(View.VISIBLE);
			mBigDangwei.setVisibility(View.VISIBLE);
			mSmallDangwei.setVisibility(View.VISIBLE);
			mIvMeter.setImageResource(R.drawable.background_meter);
			mWarnTv.setBackgroundResource(R.drawable.tankuang);
			mIvZhuansu.setVisibility(View.GONE);
			mHudBackground.setBackgroundResource(R.drawable.background);
			
			switch (instance.getM_iGear()) {
			case 1:
				mBigDangwei.setImageResource(R.drawable.dangwei__p);
				mSmallDangwei.setImageResource(R.drawable.dangwei__p);
				break;
			case 2:
				mBigDangwei.setImageResource(R.drawable.dangwei__n);
				mSmallDangwei.setImageResource(R.drawable.dangwei__n);
				break;
			case 3:
				mBigDangwei.setImageResource(R.drawable.dangwei__r);
				mSmallDangwei.setImageResource(R.drawable.dangwei__r);
				break;
			case 4:
				mBigDangwei.setImageResource(R.drawable.dangwei__d);
				mSmallDangwei.setImageResource(R.drawable.dangwei__d);
				break;
			case 5:
				mBigDangwei.setImageResource(R.drawable.dangwei__s);
				mSmallDangwei.setImageResource(R.drawable.dangwei__s);
				mIvAnima.setVisibility(View.GONE);
				mIvMeter.setImageResource(R.drawable.yibiaopan_red);
				mWarnTv.setBackgroundResource(R.drawable.tankuang_red);
				mHudBackground.setBackgroundResource(R.drawable.background_red);
				mIvZhuansu.setVisibility(View.VISIBLE);
				break;
			default:
				mBigDangwei.setVisibility(View.GONE);
				mSmallDangwei.setVisibility(View.GONE);
				break;
			}
		} else {
			mBigDangwei.setVisibility(View.GONE);
			mSmallDangwei.setVisibility(View.GONE);
		}
		
		//Log.i("setZhuansuAnimation", "instance.getM_iEngineSpeed() ="+instance.getM_iEngineSpeed());
		setZhuansuAnimation(instance.getM_iEngineSpeed()/163);
		
		int iSpeed = (int) instance.getM_fInstantSpeed();
		if (iSpeed == 0xffff) {
			iSpeed = 0;
		}
		int Single = iSpeed%10;//个位
		int Ten = (iSpeed/10)%10;//十位
		int Hundred = (iSpeed/100)%10;//百位

		mIvSpeedTen.setVisibility(View.VISIBLE);
		mIvSpeedHundred.setVisibility(View.VISIBLE);
		mIvSpeedFloat.setVisibility(View.VISIBLE);

		 if (iSpeed  < 10) {
			 mIvSpeedHundred.setVisibility(View.GONE);
			 mIvSpeedFloat.setVisibility(View.GONE);
			 mIvSpeedTen.setImageResource(R.drawable.big_num0+Single);
	      }else if (iSpeed >= 100) {
			mIvSpeedHundred.setImageResource(R.drawable.big_num0+Hundred);
			mIvSpeedTen.setImageResource(R.drawable.big_num0+Ten);
			mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
	   	  }else{
			mIvSpeedHundred.setVisibility(View.GONE);
			mIvSpeedTen.setImageResource(R.drawable.big_num0+Ten);
			mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
		  }
		 
		mSmallFloat.setVisibility(View.VISIBLE);
		mSmallHundred.setVisibility(View.VISIBLE);
		mSmallTen.setVisibility(View.VISIBLE);

		 if (iSpeed  < 10) {
			 mSmallHundred.setVisibility(View.GONE);
			 mSmallFloat.setVisibility(View.GONE);
			 mSmallTen.setImageResource(R.drawable.small_num0+Single);
	      }else if (iSpeed >= 100) {
	    	  mSmallHundred.setImageResource(R.drawable.small_num0+Hundred);
	    	  mSmallTen.setImageResource(R.drawable.small_num0+Ten);
	    	  mSmallFloat.setImageResource(R.drawable.small_num0+Single);
	   	  }else{
	   		mSmallHundred.setVisibility(View.GONE);
	   		mSmallTen.setImageResource(R.drawable.small_num0+Ten);
			mSmallFloat.setImageResource(R.drawable.small_num0+Single);
		  }
		
	}

	private void setZhuansuAnimation(int value) {
		
		Log.i("setZhuansuAnimation", "value ="+value+", mZhuansuValue="+mZhuansuValue);

		AnimationDrawable draw = new AnimationDrawable();
		draw.setOneShot(true);
		int[] images = {R.drawable.zhuansubiao_01, R.drawable.zhuansubiao_02, R.drawable.zhuansubiao_03, R.drawable.zhuansubiao_04,
				R.drawable.zhuansubiao_05, R.drawable.zhuansubiao_06, R.drawable.zhuansubiao_07, R.drawable.zhuansubiao_08, R.drawable.zhuansubiao_09,
				R.drawable.zhuansubiao_10, R.drawable.zhuansubiao_11, R.drawable.zhuansubiao_12, R.drawable.zhuansubiao_13, R.drawable.zhuansubiao_14,
				R.drawable.zhuansubiao_15, R.drawable.zhuansubiao_16, R.drawable.zhuansubiao_17, R.drawable.zhuansubiao_18, R.drawable.zhuansubiao_19,
				R.drawable.zhuansubiao_20, R.drawable.zhuansubiao_21, R.drawable.zhuansubiao_22, R.drawable.zhuansubiao_23, R.drawable.zhuansubiao_24,
				R.drawable.zhuansubiao_25, R.drawable.zhuansubiao_26, R.drawable.zhuansubiao_27, R.drawable.zhuansubiao_28, R.drawable.zhuansubiao_29,
				R.drawable.zhuansubiao_30, R.drawable.zhuansubiao_31, R.drawable.zhuansubiao_32, R.drawable.zhuansubiao_33, R.drawable.zhuansubiao_34,
				R.drawable.zhuansubiao_35, R.drawable.zhuansubiao_36, R.drawable.zhuansubiao_37, R.drawable.zhuansubiao_38, R.drawable.zhuansubiao_39,
				R.drawable.zhuansubiao_40, R.drawable.zhuansubiao_41, R.drawable.zhuansubiao_42, R.drawable.zhuansubiao_43, R.drawable.zhuansubiao_44,
				R.drawable.zhuansubiao_45, R.drawable.zhuansubiao_46, R.drawable.zhuansubiao_47, R.drawable.zhuansubiao_48, R.drawable.zhuansubiao_49};
		if (value >= images.length) {
			value = images.length-1;
		}
		
		int temp = 100;
		if (value != mZhuansuValue) {
			temp =300/(Math.abs(value - mZhuansuValue));
		}
		
		if (value > mZhuansuValue) {
			for (int i = mZhuansuValue; i <= value; i++) {
				draw.addFrame(activity.getResources().getDrawable(images[i]), temp);
			}
		} else {
			for (int i = mZhuansuValue; i >= value; i--) {
				draw.addFrame(activity.getResources().getDrawable(images[i]), temp);
			}
		}
		
		mZhuansuValue = value;
		mIvZhuansu.setBackground(draw);
		if (draw.isRunning()){
			draw.stop();
		}
		draw.start();
	}


	private void setWarnTankuan(boolean bShow) {
		
		if (bShow) {
			mBigSpeedLL.setVisibility(View.GONE);
			mSmallSpeedLL.setVisibility(View.VISIBLE);
		} else {
			mBigSpeedLL.setVisibility(View.VISIBLE);
			mSmallSpeedLL.setVisibility(View.GONE);
		}
	}


	private void initView(View view) {
		
		mIvSpeedFloat =(ImageView) view.findViewById(R.id.iv_newhud_speed_float); 
		mIvSpeedTen =(ImageView) view.findViewById(R.id.iv_newhud_speed_ten); 
		mIvSpeedHundred =(ImageView) view.findViewById(R.id.iv_newhud_speed_hundred); 
		
		mIvFarlight = (ImageView) view.findViewById(R.id.img_hud_farlight);
		mIvLight = (ImageView) view.findViewById(R.id.img_hud_light);
		mIvDoublelight = (ImageView) view.findViewById(R.id.img_hud_double);
		mIvTrunLight = (ImageView) view.findViewById(R.id.img_hud_leftlight);
		mIvTrunRight = (ImageView) view.findViewById(R.id.img_hud_rightlight);
		mIvSafeBelt = (ImageView) view.findViewById(R.id.iv_newhud_safebelt);
		mIvWater = (ImageView) view.findViewById(R.id.iv_newhud_water);
		mIvBattery = (ImageView) view.findViewById(R.id.iv_newhud_battery);
		mIvHand =  (ImageView) view.findViewById(R.id.iv_newhud_hand);
		mIvHtpms = (ImageView) view.findViewById(R.id.iv_newhud_htpms);
		
		mIvDoorWarn = (ImageView) view.findViewById(R.id.iv_newhud_door_warn);
		mIvDoorLf = (ImageView) view.findViewById(R.id.iv_newhud_door_lfwarn);
		mIvDoorRf = (ImageView) view.findViewById(R.id.iv_newhud_door_rfwarn);
		mDoorLr = (ImageView) view.findViewById(R.id.iv_newhud_door_lrwarn);
		mDoorRr = (ImageView) view.findViewById(R.id.iv_newhud_door_rrwarn);
		
		mIvAnima = (ImageView) view.findViewById(R.id.iv_hud_anima);
		
		mBigDangwei = (ImageView) view.findViewById(R.id.iv_newhud_dangwei);
		mSmallDangwei = (ImageView) view.findViewById(R.id.iv_small_dangwei);
		mSmallFloat = (ImageView) view.findViewById(R.id.iv_nsmall_speed_float);
		mSmallTen= (ImageView) view.findViewById(R.id.iv_small_speed_ten);
		mSmallHundred = (ImageView) view.findViewById(R.id.iv_small_speed_hundred);
		mBigSpeedLL = (RelativeLayout) view.findViewById(R.id.rl_speed_ll);
		mSmallSpeedLL = (RelativeLayout) view.findViewById(R.id.rl_tankun_ll);
		mWarnTv = (TextView) view.findViewById(R.id.tv_newhud_warn);
		
		mIvMeter = (ImageView) view.findViewById(R.id.iv_newhud_meter);
		mIvZhuansu = (ImageView) view.findViewById(R.id.iv_newhud_zhuansu);
		
		mHudBackground = (RelativeLayout) view.findViewById(R.id.id_hud_background_ll);
	}

	
	public void setAima(){
		mHandler.removeMessages(0x300);
		mHandler.sendEmptyMessageDelayed(0x300, 100);
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

}
