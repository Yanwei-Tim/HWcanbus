package com.hiworld.canbus.fragment;


import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CarStateFragment extends Fragment{
	
	private ImageView iv_nearlight,iv_farlight,iv_widelight,iv_stoplight,
	iv_backlight,iv_daytimelight,iv_leftlight,iv_rightlight,iv_doublelight,iv_frontfloglight,iv_rearfloglight;
	//车门
	private ImageView iv_leftFrontDoor_close,iv_leftFrontDoor_open,iv_leftRealDoor_close,
		iv_leftRealDoor_open,iv_RightFrontDoor_close,iv_RightFrontDoor_open,
		iv_rightRealDoor_close,iv_rightRealDoor_open;
	private ImageView iv_hood,iv_taixbox;
	
	private TextView tv_cooltemp,tv_instantfuel,tv_battery,tv_enginespeed,
	tv_currentspeed,tv_distance,tv_safebaelt,tv_handstop,tv_doorlock;
	private ImageView iv_cooltemp,iv_instantfuel,iv_battery,iv_enginespeed,
	iv_currentspeed,iv_distance,iv_safebaelt,iv_handstop,iv_doorlock;
	
	private int a,b,c;
	
	private boolean bRunnable=false;
	private View mContentView; 
	private LinearLayout main_bg;
	
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		if (mHandler != null) {
			FragmentParseData.getInstance().setCarStateHandler(mHandler);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(mContentView == null){
			mContentView = inflater.inflate(R.layout.fragment_carstate, container,false);
			initView(mContentView);
			SendCarPcInfo(CarPcInfo.getInstance());
		}else{
			 ViewGroup parent = (ViewGroup) mContentView.getParent();  
	            if (parent != null)  {  
	                parent.removeView(mContentView);  
	            }  
		}
		return mContentView;
	}

	private void SendCarPcInfo(CarPcInfo instance) {
		
		mHandler.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, instance).sendToTarget();
	}

	private void initView(View view) {
		
		//灯光
		iv_nearlight = (ImageView) view.findViewById(R.id.iv_nearlight);
		iv_farlight = (ImageView) view.findViewById(R.id.iv_farlight);
		iv_widelight = (ImageView) view.findViewById(R.id.iv_showwidthlight);
		iv_backlight = (ImageView) view.findViewById(R.id.iv_backlight);
		iv_stoplight = (ImageView) view.findViewById(R.id.iv_stoplight);
		iv_daytimelight = (ImageView) view.findViewById(R.id.iv_daytimelight);
		iv_doublelight = (ImageView) view.findViewById(R.id.iv_doublelight);
		iv_leftlight = (ImageView) view.findViewById(R.id.iv_leftlight);
		iv_rightlight = (ImageView) view.findViewById(R.id.iv_rightlight);
		iv_frontfloglight = (ImageView) view.findViewById(R.id.iv_frontfloglight);
		iv_rearfloglight = (ImageView) view.findViewById(R.id.iv_rearfloglight);
		//车门
		iv_leftFrontDoor_close = (ImageView) view.findViewById(R.id.iv_leftfrontdoor_close);
		iv_leftFrontDoor_open = (ImageView) view.findViewById(R.id.iv_leftfrontdoor_open);
		iv_leftRealDoor_close = (ImageView) view.findViewById(R.id.iv_leftrealdoor_close);
		iv_leftRealDoor_open = (ImageView) view.findViewById(R.id.iv_leftrealdoor_open);
		iv_RightFrontDoor_close = (ImageView) view.findViewById(R.id.iv_rightfrontdoor_close);
		iv_RightFrontDoor_open = (ImageView) view.findViewById(R.id.iv_rightfrontdoor_open);
		iv_rightRealDoor_close = (ImageView) view.findViewById(R.id.iv_rightrealdoor_close);
		iv_rightRealDoor_open = (ImageView) view.findViewById(R.id.iv_rightrealdoor_open);
		iv_hood = (ImageView) view.findViewById(R.id.iv_hood);
		iv_taixbox = (ImageView) view.findViewById(R.id.iv_taixbox);
		//状态信息
		tv_cooltemp = (TextView)view.findViewById(R.id.tv_cooltemp);
		iv_cooltemp = (ImageView)view.findViewById(R.id.iv_cooltemp);
		tv_instantfuel = (TextView)view.findViewById(R.id.tv_instantfuel);
		iv_instantfuel = (ImageView)view.findViewById(R.id.iv_instantfuel);
		tv_battery = (TextView)view.findViewById(R.id.tv_battery);
		iv_battery = (ImageView)view.findViewById(R.id.iv_battery);
		tv_enginespeed = (TextView)view.findViewById(R.id.tv_enginespeed);
		iv_enginespeed = (ImageView)view.findViewById(R.id.iv_enginespeed);
		tv_currentspeed = (TextView)view.findViewById(R.id.tv_currentspeed);
		iv_currentspeed = (ImageView)view.findViewById(R.id.iv_currentspeed);
		tv_distance = (TextView)view.findViewById(R.id.tv_distance);
		iv_distance = (ImageView)view.findViewById(R.id.iv_distance);
		tv_safebaelt = (TextView)view.findViewById(R.id.tv_safebelt);
		iv_safebaelt = (ImageView)view.findViewById(R.id.iv_safebelt);
		tv_handstop = (TextView)view.findViewById(R.id.tv_handstop);
		iv_handstop = (ImageView)view.findViewById(R.id.iv_handstop);
		tv_doorlock = (TextView)view.findViewById(R.id.tv_doorlock);
		iv_doorlock = (ImageView)view.findViewById(R.id.iv_doorlock);
		
		main_bg = (LinearLayout) view.findViewById(R.id.main_bg);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo carPcInfo = (CarPcInfo) msg.obj;
				if (carPcInfo != null) {
					SetCarPcInfo(carPcInfo);
				}
				break;
//			case ConstData.HANDLER_LEFTLIGHT:
//				if (a % 2 == 0) {
//					iv_leftlight.setVisibility(View.GONE);
//				}else {
//					iv_leftlight.setVisibility(View.VISIBLE);
//				}
//				a++;
//				mHandler.removeMessages(ConstData.HANDLER_LEFTLIGHT);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_LEFTLIGHT, 500);
//				break;
//			case ConstData.HANDLER_RIGHTLIGHT:
//				if (b % 2 == 0) {
//					iv_rightlight.setVisibility(View.GONE);
//				}else {
//					iv_rightlight.setVisibility(View.VISIBLE);
//				}
//				b++;
//				mHandler.removeMessages(ConstData.HANDLER_RIGHTLIGHT);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_RIGHTLIGHT, 500);
//				break;
			case ConstData.HANDLER_DOUBLELIGHT:
				if (c % 2 == 0) {
					iv_leftlight.setVisibility(View.GONE);
                    iv_rightlight.setVisibility(View.GONE);
				}else {
                    if (CarPcInfo.getInstance().getM_iDoubleLight() == 1) {
                    	//iv_doublelight.setVisibility(View.VISIBLE);
                    	iv_leftlight.setVisibility(View.VISIBLE);
                    	iv_rightlight.setVisibility(View.VISIBLE);
					}
                    else if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1&& CarPcInfo.getInstance().getM_iRightTrunLight()==1) {
						iv_leftlight.setVisibility(View.VISIBLE);
                    	iv_rightlight.setVisibility(View.VISIBLE);
					}else if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1) {
						iv_leftlight.setVisibility(View.VISIBLE);
					}
					else if (CarPcInfo.getInstance().getM_iRightTrunLight() == 1) {
						iv_rightlight.setVisibility(View.VISIBLE);
					}
				}
				c++;
				bRunnable = true;
				mHandler.removeMessages(ConstData.HANDLER_DOUBLELIGHT);
				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_DOUBLELIGHT, 500);
					
//				if (c % 2 == 0) {
//					iv_doublelight.setVisibility(View.GONE);
//				}else {
//					iv_doublelight.setVisibility(View.VISIBLE);
//				}
//				c++;
//				mHandler.removeMessages(ConstData.HANDLER_DOUBLELIGHT);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_DOUBLELIGHT, 500);
				break;
			default:
				break;
			}
		}

	};

	protected void SetCarPcInfo(CarPcInfo vmCarInfo) {
		
//		if (vmCarInfo.getM_iILLAble() == 1 && vmCarInfo.getM_iILL() == 1) {
//			main_bg.setBackgroundResource(R.drawable.background_red);
//		}else{
//			main_bg.setBackgroundResource(R.drawable.background);
//		}
		//灯光
		if (vmCarInfo.getM_iOutLightAble() == 1) {
			if (vmCarInfo.getM_iNearLight() == 0x01) {//近光灯
				iv_nearlight.setVisibility(View.VISIBLE);
			}else {
				iv_nearlight.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iFarLight() == 0x01) {//远光灯
				iv_farlight.setVisibility(View.VISIBLE);
			}else {
				iv_farlight.setVisibility(View.GONE);
			}
			//Log.d("aaa", "vmCarInfo.getM_iFarLight()="+vmCarInfo.getM_iFarLight());
			if (vmCarInfo.getM_iWideLight() == 0x01) {//示宽灯
				iv_widelight.setVisibility(View.VISIBLE);
			}else {
				iv_widelight.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iRevLight() == 0x01) {//倒车灯
				iv_backlight.setVisibility(View.VISIBLE);
			}else {
				iv_backlight.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iStopLight() == 0x01) {//刹车灯
				iv_stoplight.setVisibility(View.VISIBLE);
			}else {
				iv_stoplight.setVisibility(View.GONE);
			}
			
			if ((vmCarInfo.getM_iDoubleLight() == 0x01||vmCarInfo.getM_iLeftTrunLight() == 0x01
					||vmCarInfo.getM_iRightTrunLight() == 0x01) && !bRunnable) {//双闪灯
				bRunnable = true;
				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_DOUBLELIGHT, 500);
			}else if (vmCarInfo.getM_iDoubleLight() == 0x00 &&vmCarInfo.getM_iLeftTrunLight() == 0x00
					&&vmCarInfo.getM_iRightTrunLight() == 0x00 && bRunnable){
				bRunnable = false;
				mHandler.removeMessages(ConstData.HANDLER_DOUBLELIGHT);
				iv_doublelight.setVisibility(View.GONE);
				iv_leftlight.setVisibility(View.GONE);
				iv_rightlight.setVisibility(View.GONE);
			}
//			if (vmCarInfo.getM_iLeftTrunLight() == 0x01) {//左转向灯
//				iv_leftlight.setVisibility(View.VISIBLE);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_LEFTLIGHT, 500);
//			}else {
//				mHandler.removeMessages(ConstData.HANDLER_LEFTLIGHT);
//				iv_leftlight.setVisibility(View.GONE);
//			}
//			if (vmCarInfo.getM_iRightTrunLight() == 0x01) {//右转向灯
//				iv_rightlight.setVisibility(View.VISIBLE);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_RIGHTLIGHT, 500);
//			}else {
//				mHandler.removeMessages(ConstData.HANDLER_RIGHTLIGHT);
//				iv_rightlight.setVisibility(View.GONE);
//			}
//			if (vmCarInfo.getM_iDoubleLight() == 0x01) {//双闪灯
//				iv_doublelight.setVisibility(View.VISIBLE);
//				mHandler.sendEmptyMessageDelayed(ConstData.HANDLER_DOUBLELIGHT, 500);
//			}else {
//				mHandler.removeMessages(ConstData.HANDLER_DOUBLELIGHT);
//				iv_doublelight.setVisibility(View.GONE);
//			}
			
			
			if (vmCarInfo.getM_iDayLight() == 0x01) {//日间行车灯
				iv_daytimelight.setVisibility(View.VISIBLE);
			}else {
				iv_daytimelight.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iFrontflogLight() == 0x01) {//前雾灯
				iv_frontfloglight.setVisibility(View.VISIBLE);
			}else {
				iv_frontfloglight.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iRearflogLight() == 0x01) {//后雾灯
				iv_rearfloglight.setVisibility(View.VISIBLE);
			}else {
				iv_rearfloglight.setVisibility(View.GONE);
			}
		}
		
		//车门
		if (vmCarInfo.getM_iDoorAble() == 1) {
			if (vmCarInfo.getM_iLFDoor() == 0x01) {//左前门
				iv_leftFrontDoor_close.setVisibility(View.GONE);
				iv_leftFrontDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_leftFrontDoor_close.setVisibility(View.VISIBLE);
				iv_leftFrontDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iRFDoor() == 0x01) {//右前门
				iv_RightFrontDoor_close.setVisibility(View.GONE);
				iv_RightFrontDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_RightFrontDoor_close.setVisibility(View.VISIBLE);
				iv_RightFrontDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iLRDoor() == 0x01) {//左后门
				iv_leftRealDoor_close.setVisibility(View.GONE);
				iv_leftRealDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_leftRealDoor_close.setVisibility(View.VISIBLE);
				iv_leftRealDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iRRDoor() == 0x01) {//右后门
				iv_rightRealDoor_close.setVisibility(View.GONE);
				iv_rightRealDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_rightRealDoor_close.setVisibility(View.VISIBLE);
				iv_rightRealDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iHood() == 1) {//前盖
				iv_hood.setVisibility(View.VISIBLE);
			} else {
				iv_hood.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iTailBox() == 1) {//
				iv_taixbox.setVisibility(View.VISIBLE);
			} else {
				iv_taixbox.setVisibility(View.GONE);
			}
		}
		
		int temp = 0;
		//冷却液温度
		if (vmCarInfo.getM_iCooltempAble() == 1) {
			temp = vmCarInfo.getM_iCooltemp();
			tv_cooltemp.setText(Integer.toString(temp)+"  ℃");
			if (temp > 110 ) {//|| temp < 80
				tv_cooltemp.setTextColor(Color.RED);
				iv_cooltemp.setImageResource(R.drawable.cooltemp_d);
			} else {
				tv_cooltemp.setTextColor(Color.WHITE);
				iv_cooltemp.setImageResource(R.drawable.cooltemp_enable);
			}
		}else{
			tv_cooltemp.setText("");
			iv_cooltemp.setImageResource(R.drawable.cooltemp_enable);
		}
		float ff = 0;
		//小计里程-平均车速
		if (vmCarInfo.getM_iLittleTripAble() == 1) {
			ff = vmCarInfo.getM_fTripmeter_avgspeed();
			tv_instantfuel.setText(Float.toString(ff)+"  Km/h");
			iv_currentspeed.setImageResource(R.drawable.instantspeed);
		}else{
			tv_instantfuel.setText("");
			iv_currentspeed.setImageResource(R.drawable.instantspeed_enable);
		}
//		//瞬时油耗
//		if (vmCarInfo.getM_iInstantFuelAble() == 1) {
//			ff = vmCarInfo.getM_fInstantFuel();
//			String str = null;
//			switch (vmCarInfo.getM_iFuelUnit()) {
//			case 0:
//				str = "Mpg";
//				break;
//			case 1:
//				str = "Km/L";
//				break;
//			case 2:
//				str = "L/100Km";
//				break;
//			case 3:
//				str = "L/H";
//				break;
//			default:
//				str = "Km/L";
//				break;
//			}
//			if (ff != 0xffff) {
//				tv_instantfuel.setText(Float.toString(ff)+str);
//			} else {
//				tv_instantfuel.setText("--"+str);
//			}
//			
//		}
		//电池电压
		if (vmCarInfo.getM_iBatteryVolAble() == 1) {
			ff = vmCarInfo.getM_fBatteryVol();
			tv_battery.setText(Float.toString(ff)+"  V");

			if (vmCarInfo.getM_iEngineSpeed() == 0 ) {
				//tv_battery.setTextColor(Color.RED);
				iv_battery.setImageResource(R.drawable.batteryvoltage_d);
			} else {
				tv_battery.setTextColor(Color.WHITE);
				iv_battery.setImageResource(R.drawable.batteryvoltage_enable);
			}
		}else{
			tv_battery.setText("");
			iv_battery.setImageResource(R.drawable.batteryvoltage_enable);
		}
		//发动机转速
		if (vmCarInfo.getM_iEnigineSpeedAble() == 1) {
			temp = vmCarInfo.getM_iEngineSpeed();
			tv_enginespeed.setText(Integer.toString(temp)+"  RPM");
			iv_enginespeed.setImageResource(R.drawable.enginespeed);
		}else{
			iv_enginespeed.setImageResource(R.drawable.enginespeed_enable);
			tv_enginespeed.setText("");
		}
		//当前车速
		if (vmCarInfo.getM_iSpeedAble() == 1) {
			ff = vmCarInfo.getM_fInstantSpeed();
			if (ff != 0xffff) {
				tv_currentspeed.setText(Float.toString(ff)+"  km/h");
			} else {
				tv_currentspeed.setText("--");
			}
			iv_instantfuel.setImageResource(R.drawable.currentspeed);
		}else{
			tv_currentspeed.setText("");
			iv_instantfuel.setImageResource(R.drawable.currentspeed_enable);
		}

		//续航里程
//		if (vmCarInfo.getM_iMileageAble() == 1) {
//			temp = vmCarInfo.getM_iMileage();
//			tv_distance.setText(Integer.toString(temp)+"  Km");
//			iv_distance.setImageResource(R.drawable.mileage);
//		}else{
//			tv_distance.setText("");
//			iv_distance.setImageResource(R.drawable.mileage_enable);
//		}
		//总里程
		if (vmCarInfo.getM_iTotalMileageAble() == 1) {
			temp = vmCarInfo.getM_iTotalMileage();
			tv_distance.setText(Integer.toString(temp)+"  km");
			iv_distance.setImageResource(R.drawable.mileage);
		}else{
			tv_distance.setText("");
			iv_distance.setImageResource(R.drawable.mileage_enable);
		}
		//左安全带
		if (vmCarInfo.getM_iLFSafebeltAble() == 1) {
			temp = vmCarInfo.getM_iLFSafebelt();
			if (temp == 0 ) {//未系
				tv_safebaelt.setText(R.string.text_safeBandOff);
				tv_safebaelt.setTextColor(Color.RED);
				iv_safebaelt.setImageResource(R.drawable.safebelt_d);
			} else {
				tv_safebaelt.setText(R.string.text_safeBandOn);
				tv_safebaelt.setTextColor(Color.WHITE);
				iv_safebaelt.setImageResource(R.drawable.safebelt_enable);
			}
		}else{
			tv_safebaelt.setText("");
			iv_safebaelt.setImageResource(R.drawable.safebelt_enable);
		}
		//手刹
		if (vmCarInfo.getM_iHandbrakeAble() == 1) {
			temp = vmCarInfo.getM_iHanderBrake();
			if (temp == 1) {//拉起
				tv_handstop.setText(R.string.text_handbrake_on);
				tv_handstop.setTextColor(Color.RED);
				iv_handstop.setImageResource(R.drawable.handbrake_d);
			} else {
				tv_handstop.setText(R.string.text_handbrake_off);
				tv_handstop.setTextColor(Color.WHITE);
				iv_handstop.setImageResource(R.drawable.handbrake_enable);
			}
//			//车速>0，手刹未放下
//			if (vmCarInfo.getM_fInstantSpeed() > 0 && vmCarInfo.getM_fInstantSpeed() != 0xffff){
//				if (vmCarInfo.getM_iHanderBrake() == 1){
//					tv_handstop.setTextColor(Color.RED);
//					iv_handstop.setImageResource(R.drawable.handbrake_d);
//				}
//			}else if (vmCarInfo.getM_iACC() == 0){
//				//停车手刹未拉起
//				if (vmCarInfo.getM_iHanderBrake() == 0){
//					tv_handstop.setTextColor(Color.RED);
//					iv_handstop.setImageResource(R.drawable.handbrake_d);
//				}
//			}
		}else{
			tv_handstop.setText("");
			iv_handstop.setImageResource(R.drawable.handbrake_enable);
		}
		//车内锁
		if (vmCarInfo.getM_iInnerlockAble() == 1) {
			if (vmCarInfo.getM_iInnerlock() == 0) {//未上锁
				iv_doorlock.setImageResource(R.drawable.doorlock_d);
				tv_doorlock.setTextColor(Color.RED);
				tv_doorlock.setText(R.string.text_doorlock_open);
			} else {
				iv_doorlock.setImageResource(R.drawable.doorlock_enable);
				tv_doorlock.setTextColor(Color.WHITE);
				tv_doorlock.setText(R.string.text_doorlock_close);
			}
		}else{
			tv_doorlock.setText("");
			iv_doorlock.setImageResource(R.drawable.doorlock_enable);
		}
		
		
	}
	
}
