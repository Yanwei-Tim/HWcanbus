package com.hiworld.fragment.golf;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.ex.canbus.DataCanbus;
import com.ex.hiworld.aidl.task.Task_HandlerCanbus;
import com.hiworld.adapter.SaicCarInfo;
import com.hiworld.adapter.VMCarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.BaseVechParseData;
import com.hiworld.canbus.parsedata.GMParseData;
import com.hiworld.canbus.parsedata.SaicParseData;
import com.hiworld.canbus.parsedata.VmParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;
import com.hiworld.constant.ConstantCar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import tools.IUiNotify;
import tools.LogsUtils;

public class VMUIFragment extends BaseFragment{

	FragmentCallBack fragmentCallBack = null;
	
	//灯光
	private ImageView iv_nearlight,iv_farlight,iv_showwidthlight,iv_stoplight,
		iv_backlight,iv_daytimelight,iv_leftlight,iv_rightlight,iv_doublelight;
	//车门
	private ImageView iv_leftFrontDoor_close,iv_leftFrontDoor_open,iv_leftRealDoor_close,
		iv_leftRealDoor_open,iv_RightFrontDoor_close,iv_RightFrontDoor_open,
		iv_rightRealDoor_close,iv_rightRealDoor_open;
	//报警图标
	private ImageView iv_safeBand,iv_handStop,iv_cleanWaterImageView;
	//显示文字
	private TextView tv_restOil,tv_outTemp,tv_battery,tv_engineSpeed,
		tv_currentSpeed,tv_distance,tv_safeBand,tv_handStop,tv_cleanWater;
	private int c;
	
	private ImageView iv_hood, iv_taixbox,iv_frontfloglight,iv_rearfloglight
	,iv_distance;
	private TextView tv_doorlock,tv_cooltemp,tv_instantfuel,tv_fuelpa;
	
	private boolean bRunnable=false;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		fragmentCallBack = (ActivityCarPC)activity;
//		if (mHandler != null) {
//			VmParseData.getInstance().setVmUIHadler(mHandler);
//			SaicParseData.getInstance().setVmUIHadler(mHandler);//上汽名爵
//			GMParseData.getInstance().setVmUIHadler(mHandler);//gm
//			BaseVechParseData.getInstance().setBaseVmHandler(mHandler);
//		} 
	}
	
//	@SuppressLint("HandlerLeak")
//	private Handler mHandler = new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case Constant.VWF0_MESSAGE_SEND_INFO:
//				VMCarInfo vmCarInfo = (VMCarInfo)msg.obj;
//				if (vmCarInfo != null) {
//					setVmCarInfo(vmCarInfo);
//				}
//				break;
////			case Constant.VW_LEFTLIGHT:
////				if (a % 2 == 0) {
////					iv_leftlight.setVisibility(View.GONE);
////				}else {
////					iv_leftlight.setVisibility(View.VISIBLE);
////				}
////				a++;
////				mHandler.removeMessages(Constant.VW_LEFTLIGHT);
////				mHandler.sendEmptyMessageDelayed(Constant.VW_LEFTLIGHT, 500);
////				break;
////			case Constant.VW_RIGHTLIGHT:
////				if (b % 2 == 0) {
////					iv_rightlight.setVisibility(View.GONE);
////				}else {
////					iv_rightlight.setVisibility(View.VISIBLE);
////				}
////				b++;
////				mHandler.removeMessages(Constant.VW_RIGHTLIGHT);
////				mHandler.sendEmptyMessageDelayed(Constant.VW_RIGHTLIGHT, 500);
////				break;
//			case Constant.VW_DOUBLELIGHT:
//				if (c % 2 == 0) {
//		
//					iv_leftlight.setVisibility(View.GONE);
//                    iv_rightlight.setVisibility(View.GONE);
//		
//					//iv_doublelight.setVisibility(View.GONE);
//				}else {
//                    if (VMCarInfo.getInstance().getM_doubleLight() == 1) {
//                    	//iv_doublelight.setVisibility(View.VISIBLE);
//                    	iv_leftlight.setVisibility(View.VISIBLE);
//                    	iv_rightlight.setVisibility(View.VISIBLE);
//					}
//                    else if (VMCarInfo.getInstance().getM_leftLight() == 1&& VMCarInfo.getInstance().getM_rightLight()==1) {
//						iv_leftlight.setVisibility(View.VISIBLE);
//                    	iv_rightlight.setVisibility(View.VISIBLE);
//					}else if (VMCarInfo.getInstance().getM_leftLight() == 1) {
//						iv_leftlight.setVisibility(View.VISIBLE);
//					}
//					else if (VMCarInfo.getInstance().getM_rightLight() == 1) {
//						iv_rightlight.setVisibility(View.VISIBLE);
//					}
//					//iv_doublelight.setVisibility(View.VISIBLE);
//				}
//				c++;
//				bRunnable = true;
//				mHandler.removeMessages(Constant.VW_DOUBLELIGHT);
//				mHandler.sendEmptyMessageDelayed(Constant.VW_DOUBLELIGHT, 500);
//				break;
//			default:
//				break;
//			}
//		}
//		
//	};
	protected void setVmCarInfo(VMCarInfo vmCarInfo) {
		
		//灯光
		if (vmCarInfo.getM_nearlyLight() == 0x01) {//近光灯
			iv_nearlight.setVisibility(View.VISIBLE);
		}else {
			iv_nearlight.setVisibility(View.GONE);
		}
		if (vmCarInfo.getM_farLight() == 0x01) {//远光灯
			iv_farlight.setVisibility(View.VISIBLE);
		}else {
			iv_farlight.setVisibility(View.GONE);
		}
		if (vmCarInfo.getM_showWidthLight() == 0x01) {//示宽灯
			iv_showwidthlight.setVisibility(View.VISIBLE);
		}else {
			iv_showwidthlight.setVisibility(View.GONE);
		}
		if (vmCarInfo.getM_backLight() == 0x01) {//倒车灯
			iv_backlight.setVisibility(View.VISIBLE);
		}else {
			iv_backlight.setVisibility(View.GONE);
		}
		if (vmCarInfo.getM_stopLight() == 0x01) {//刹车灯
			iv_stoplight.setVisibility(View.VISIBLE);
		}else {
			iv_stoplight.setVisibility(View.GONE);
		}
//		if (vmCarInfo.getM_leftLight() == 0x01) {//左转向灯
//			iv_leftlight.setVisibility(View.VISIBLE);
//			mHandler.sendEmptyMessageDelayed(Constant.VW_LEFTLIGHT, 500);
//		}else {
//			mHandler.removeMessages(Constant.VW_LEFTLIGHT);
//			iv_leftlight.setVisibility(View.GONE);
//		}
//		if (vmCarInfo.getM_rightLight() == 0x01) {//右转向灯
//			iv_rightlight.setVisibility(View.VISIBLE);
//			mHandler.sendEmptyMessageDelayed(Constant.VW_RIGHTLIGHT, 500);
//		}else {
//			mHandler.removeMessages(Constant.VW_RIGHTLIGHT);
//			iv_rightlight.setVisibility(View.GONE);
//		}
//		System.out.println("golf7  vmCarInfo.getM_rightLight()="+vmCarInfo.getM_rightLight()+", bRunnable ="+bRunnable);
//		if ((vmCarInfo.getM_doubleLight() == 0x01||vmCarInfo.getM_leftLight() == 0x01
//				||vmCarInfo.getM_rightLight() == 0x01) && !bRunnable) {//双闪灯
//			//iv_doublelight.setVisibility(View.VISIBLE);
//			bRunnable = true;
//			mHandler.sendEmptyMessageDelayed(Constant.VW_DOUBLELIGHT, 500);
//		}else if (vmCarInfo.getM_doubleLight() == 0x00 &&vmCarInfo.getM_leftLight() == 0x00
//				&&vmCarInfo.getM_rightLight() == 0x00 && bRunnable){
//			bRunnable = false;
//			mHandler.removeMessages(Constant.VW_DOUBLELIGHT);
//			iv_doublelight.setVisibility(View.GONE);
//			iv_leftlight.setVisibility(View.GONE);
//			iv_rightlight.setVisibility(View.GONE);
//		}
		if (vmCarInfo.getM_daytimeLight() == 0x01) {//日间行车灯
			iv_daytimelight.setVisibility(View.VISIBLE);
		}else {
			iv_daytimelight.setVisibility(View.GONE);
		}
		
		if (vmCarInfo.getM_frontfogLight() == 0x01) {//前雾灯
			iv_frontfloglight.setVisibility(View.VISIBLE);
		}else {
			iv_frontfloglight.setVisibility(View.GONE);
		}
		
		if (vmCarInfo.getM_realfogLight() == 0x01) {//后雾灯
			iv_rearfloglight.setVisibility(View.VISIBLE);
		}else {
			iv_rearfloglight.setVisibility(View.GONE);
		}
		
		//车门
		if (vmCarInfo.getM_doorFlag() == 0x01) {
			if (vmCarInfo.getM_leftFrontDoor() == 0x01) {//左前门
				iv_leftFrontDoor_close.setVisibility(View.GONE);
				iv_leftFrontDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_leftFrontDoor_close.setVisibility(View.VISIBLE);
				iv_leftFrontDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_rightFrontDoor() == 0x01) {//右前门
				iv_RightFrontDoor_close.setVisibility(View.GONE);
				iv_RightFrontDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_RightFrontDoor_close.setVisibility(View.VISIBLE);
				iv_RightFrontDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_leftRealDoor() == 0x01) {//左后门
				iv_leftRealDoor_close.setVisibility(View.GONE);
				iv_leftRealDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_leftRealDoor_close.setVisibility(View.VISIBLE);
				iv_leftRealDoor_open.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_rightRealDoor() == 0x01) {//右后门
				iv_rightRealDoor_close.setVisibility(View.GONE);
				iv_rightRealDoor_open.setVisibility(View.VISIBLE);
			}else {
				iv_rightRealDoor_close.setVisibility(View.VISIBLE);
				iv_rightRealDoor_open.setVisibility(View.GONE);
			}
			
			if (vmCarInfo.getM_iHood() == 1) {
				iv_hood.setVisibility(View.VISIBLE);
			} else {
				iv_hood.setVisibility(View.GONE);
			}
			if (vmCarInfo.getM_iTaixBox() == 1) {
				iv_taixbox.setVisibility(View.VISIBLE);
			} else {
				iv_taixbox.setVisibility(View.GONE);
			}
		}

		
		//车外温度
		tv_outTemp.setText((vmCarInfo.getM_outTemp()*0.5 - 40)+"℃");
		
		switch (ActivityCarPC.CAR_NUM) {
		case ConstantCar.GM_CARMODELS_ALL://GM
			//剩余油量
			tv_restOil.setText(vmCarInfo.getM_restOil()+"L");
			//电池电压
			tv_battery.setText(vmCarInfo.getM_fBatteryVol()+"V");
			//发动机转速
			if (vmCarInfo.getM_iEngineSpeed() != 0xffff) {
				tv_engineSpeed.setText(vmCarInfo.getM_iEngineSpeed()+"Rpm");
			} else {
				tv_engineSpeed.setText("--Rpm");
			}
			//瞬时车速
			if (vmCarInfo.getM_speed() == 0xffff) {
				tv_currentSpeed.setText("--Km/h");
			} else {
				tv_currentSpeed.setText(vmCarInfo.getM_speed()+"Km/h");
			}
			//瞬时油耗
			tv_instantfuel.setText(R.string.title_currentoil);
			iv_distance.setImageResource(R.drawable.instantfuel_n);
			tv_distance.setText(vmCarInfo.getM_currentOilInt()+"."+vmCarInfo.getM_currentOilFloat()+"L/100Km");
			//机油压力
			iv_safeBand.setImageResource(R.drawable.instantfuel_d);
			tv_fuelpa.setText(R.string.fuelpa);
			if (vmCarInfo.getM_speed() == 0xffff) {
				tv_safeBand.setText("--Pa");
			} else {
				tv_safeBand.setText(vmCarInfo.getM_iFuelPa()+"Pa");
			}
			//手刹状态
			if (vmCarInfo.getM_park_handStop() == 0x01) {
				tv_handStop.setText(R.string.tv_handStopOn);
				iv_handStop.setImageResource(R.drawable.handstop);
			}else {
				tv_handStop.setText(R.string.tv_handStopOff);
				iv_handStop.setImageResource(R.drawable.handstop_warn);
			}
			//冷却液温度
			tv_doorlock.setText(R.string.cooltempwarn);

			tv_cleanWater.setText(vmCarInfo.getM_iCoolantTemp()+"℃");
			iv_cleanWaterImageView.setImageResource(R.drawable.cooltemp_n);
			
			break;
		case ConstantCar.VWFO_MAGOTAN://大众
		case ConstantCar.VWFO_GOLF7:
			//剩余油量
			tv_restOil.setText(vmCarInfo.getM_restOil()+"L");
			//电池电压
			tv_battery.setText(vmCarInfo.getM_baterryVolInt()+"."+vmCarInfo.getM_batteryVolFloat()+"V");
			//发动机转速
			tv_engineSpeed.setText(vmCarInfo.getM_engineSpeedHigh()*256+vmCarInfo.getM_engineSpeedLow()+"RPM");
			//瞬时车速
			tv_currentSpeed.setText(vmCarInfo.getM_speed()+"Km/h");
			//行驶里程
			tv_distance.setText(vmCarInfo.getM_totalDistanceHigh() *256*256 + vmCarInfo.getM_totalDistance() *256 + vmCarInfo.getM_totalDistanceLow()+"Km");
			//安全带报警
			if (vmCarInfo.getM_lifebeltAlarm() == 0x01 ) {
				tv_safeBand.setText(R.string.tv_safeBandOn);
				iv_safeBand.setImageResource(R.drawable.safeband_warn);
			}else {
				tv_safeBand.setText(R.string.tv_safeBandOff);
				iv_safeBand.setImageResource(R.drawable.safeband);
			}
			//手刹状态
			if (vmCarInfo.getM_park_handStop() == 0x01) {
				tv_handStop.setText(R.string.tv_handStopOn);
				iv_handStop.setImageResource(R.drawable.handstop_warn);
			}else {
				tv_handStop.setText(R.string.tv_handStopOff);
				iv_handStop.setImageResource(R.drawable.handstop);
			}
			//清洗液报警
			if (vmCarInfo.getM_leanerAlarm() == 0x01) {
				tv_cleanWater.setText(R.string.tv_cleanWaterOn);
				iv_cleanWaterImageView.setImageResource(R.drawable.cleanwater_warn);
			}else {
				tv_cleanWater.setText(R.string.tv_cleanWaterOff);
				iv_cleanWaterImageView.setImageResource(R.drawable.cleanwater);
			}
			break;
		case ConstantCar.SAIC_CARMODELS_RUITON:
			//剩余油量
			tv_restOil.setText(vmCarInfo.getM_restOil()+"%");
			//电池电压
			tv_battery.setText(vmCarInfo.getM_fBatteryVol()+"V");
			//发动机转速
			if (vmCarInfo.getM_iEngineSpeed() != 0xffff) {
				tv_engineSpeed.setText(vmCarInfo.getM_iEngineSpeed()+"Rpm");
			} else {
				tv_engineSpeed.setText("--Rpm");
			}
			//瞬时车速
			if (vmCarInfo.getM_speed() == 0xffff) {
				tv_currentSpeed.setText("--Km/h");
			} else {
				tv_currentSpeed.setText(vmCarInfo.getM_speed()+"Km/h");
			}
			//安全带报警
			if (vmCarInfo.getM_lifebeltAlarm() == 0x01 || vmCarInfo.getM_iLeftBelt() == 0 
					|| vmCarInfo.getM_iRightBelt() == 0) {
				tv_safeBand.setText(R.string.tv_safeBandOn);
				iv_safeBand.setImageResource(R.drawable.safeband_warn);
			}else {
				tv_safeBand.setText(R.string.tv_safeBandOff);
				iv_safeBand.setImageResource(R.drawable.safeband);
			}
			//手刹
			tv_cooltemp.setText(R.string.cooltempwarn);
			if (vmCarInfo.getM_leanerAlarm() == 0x01) {
				tv_handStop.setText(R.string.tv_cleanWaterOn);
				iv_handStop.setImageResource(R.drawable.cooltemp_d);
			}else {
				tv_handStop.setText(R.string.tv_cleanWaterOff);
				iv_handStop.setImageResource(R.drawable.cooltemp_n);
			}
			//行驶里程
			if (SaicCarInfo.getInstance().getM_iUnitMileage() == 1) {
				if (vmCarInfo.getM_totalDistance() != 0xffffff) {
					tv_distance.setText(vmCarInfo.getM_totalDistance()+"Mile");
				} else {
					tv_distance.setText("--Mile");
				}
			} else {
				if (vmCarInfo.getM_totalDistance() != 0xffffff) {
					tv_distance.setText(vmCarInfo.getM_totalDistance()+"Km");
				} else {
					tv_distance.setText("--Km");
				}
			}
			
//			//安全带报警
//			if (vmCarInfo.getM_iLeftBelt() == 0 || vmCarInfo.getM_iRightBelt() == 0 ) {
//				tv_safeBand.setText(R.string.tv_safeBandOn);
//				iv_safeBand.setImageResource(R.drawable.safeband_warn);
//			}else {
//				tv_safeBand.setText(R.string.tv_safeBandOff);
//				iv_safeBand.setImageResource(R.drawable.safeband);
//			}
			
			tv_doorlock.setText(R.string.door_state);
			//门锁
			if (vmCarInfo.getM_iLFdoorlock() == 0 || vmCarInfo.getM_iRFdoorlock() == 0
					|| vmCarInfo.getM_iLRdoorlock() == 0 || vmCarInfo.getM_iRRdoorlock() == 0) {
				tv_cleanWater.setText(R.string.door_offlock);
				iv_cleanWaterImageView.setImageResource(R.drawable.doorlock_d);
			} else {
				tv_cleanWater.setText(R.string.door_onlock);
				iv_cleanWaterImageView.setImageResource(R.drawable.doorlock_n);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.vm_ui_fragment1, container, false);
		initView(view);
//		setVmCarInfo(VMCarInfo.getInstance());
		return view;
	}

	private void initView(View view) {
		
		//灯光
		iv_nearlight = (ImageView) view.findViewById(R.id.iv_nearlight);
		iv_farlight = (ImageView) view.findViewById(R.id.iv_farlight);
		iv_showwidthlight = (ImageView) view.findViewById(R.id.iv_showwidthlight);
		iv_stoplight = (ImageView) view.findViewById(R.id.iv_stoplight);
		iv_backlight = (ImageView) view.findViewById(R.id.iv_backlight);
		iv_daytimelight = (ImageView) view.findViewById(R.id.iv_daytimelight);
		iv_leftlight = (ImageView) view.findViewById(R.id.iv_leftlight);
		iv_rightlight = (ImageView) view.findViewById(R.id.iv_rightlight);
		iv_doublelight = (ImageView) view.findViewById(R.id.iv_doublelight);
		
		//车门
		iv_leftFrontDoor_close = (ImageView) view.findViewById(R.id.iv_leftfrontdoor_close);
		iv_leftFrontDoor_open = (ImageView) view.findViewById(R.id.iv_leftfrontdoor_open);
		iv_leftRealDoor_close = (ImageView) view.findViewById(R.id.iv_leftrealdoor_close);
		iv_leftRealDoor_open = (ImageView) view.findViewById(R.id.iv_leftrealdoor_open);
		iv_RightFrontDoor_close = (ImageView) view.findViewById(R.id.iv_rightfrontdoor_close);
		iv_RightFrontDoor_open = (ImageView) view.findViewById(R.id.iv_rightfrontdoor_open);
		iv_rightRealDoor_close = (ImageView) view.findViewById(R.id.iv_rightrealdoor_close);
		iv_rightRealDoor_open = (ImageView) view.findViewById(R.id.iv_rightrealdoor_open);
		
		//报警图标
		iv_safeBand =(ImageView) view.findViewById(R.id.iv_safeband);
		iv_handStop =(ImageView) view.findViewById(R.id.iv_handstop);
		iv_cleanWaterImageView =(ImageView) view.findViewById(R.id.iv_cleanwater);
		
		//显示文字
		tv_restOil = (TextView) view.findViewById(R.id.tv_restoil);
		tv_outTemp = (TextView) view.findViewById(R.id.tv_outtemp);
		tv_battery = (TextView) view.findViewById(R.id.tv_battery);
		tv_engineSpeed = (TextView) view.findViewById(R.id.tv_enginespeed);
		tv_currentSpeed = (TextView) view.findViewById(R.id.tv_currentspeed);
		tv_distance = (TextView) view.findViewById(R.id.tv_distance);
		tv_safeBand = (TextView) view.findViewById(R.id.tv_safeband);
		tv_handStop = (TextView) view.findViewById(R.id.tv_handstop);
		tv_cleanWater = (TextView) view.findViewById(R.id.tv_cleanwater);
		
		
		iv_hood =(ImageView) view.findViewById(R.id.iv_hood);
		iv_taixbox =(ImageView) view.findViewById(R.id.iv_taixbox);
		tv_doorlock = (TextView) view.findViewById(R.id.tv_doorlock);
		tv_cooltemp = (TextView) view.findViewById(R.id.tv_cooltemp);
		iv_frontfloglight=(ImageView) view.findViewById(R.id.iv_frontfloglight);
		iv_rearfloglight=(ImageView) view.findViewById(R.id.iv_rearfloglight);
		tv_instantfuel = (TextView) view.findViewById(R.id.tv_instantfuel);
		iv_distance = (ImageView) view.findViewById(R.id.iv_distance);
		//iv_safeband = (ImageView) view.findViewById(R.id.iv_safeband);
		tv_fuelpa = (TextView) view.findViewById(R.id.tv_fuelpa);
	}


	private int[] nIDs = new int[] {
		ConstGolf.U_SET_REMAINING_OIL,
		ConstGolf.U_ENGINE_SPEED,
		ConstGolf.U_LIFEBELT_STATE,
		ConstGolf.U_AIR_OUT_TEMP,
		ConstGolf.U_CUR_SPEED,
		ConstGolf.U_HANDPART_STATE,
		ConstGolf.U_BATTERY_VOLTAGE,
		ConstGolf.U_WASH_STATE,
		ConstGolf.U_2_BY_START,
		ConstGolf.U_LIGHT_TURN_LEFT,
		ConstGolf.U_LIGHT_TURN_RIGHT,
		ConstGolf.U_DOOR_ENGINE,
		ConstGolf.U_DOOR_FL,
		ConstGolf.U_DOOR_FR,
		ConstGolf.U_DOOR_RL,
		ConstGolf.U_DOOR_RR,
		ConstGolf.U_DOOR_BACK, 
		 
	};
	
	@Override
	public void addNotify() { 
		for (int i : nIDs) {
			DataCanbus.NOTIFY_EVENTS[i].addNotify(notify, 1);
		}		
	}

	@Override
	public void removeNotify() {
		for (int i : nIDs) {
			DataCanbus.NOTIFY_EVENTS[i].removeNotify(notify);
		}		
		
	}
	IUiNotify notify = new IUiNotify() {
		
		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			LogsUtils.i("uifragment IUiNotify: " + updateCode);
			int val = DataCanbus.DATA[updateCode];
			switch (updateCode) {
			case ConstGolf.U_SET_REMAINING_OIL: {
				tv_restOil.setText(val + " L");
				break;
			}
			case ConstGolf.U_ENGINE_SPEED: {
				if (val != 0xffff) {
					tv_engineSpeed.setText(val + " Rpm");
				} else {
					tv_engineSpeed.setText("-- Rpm");
				}
				break;
			}
			case ConstGolf.U_LIFEBELT_STATE: {
				tv_safeBand.setText(val == 0 ? R.string.tv_safeBandOff : R.string.tv_safeBandOn);
				iv_safeBand.setImageResource(val == 0 ? R.drawable.safeband : R.drawable.safeband_warn);
				break;
			}
			case ConstGolf.U_AIR_OUT_TEMP:{
				tv_outTemp.setText((val*0.5 - 40)+" ℃");
				break;
			}
			case ConstGolf.U_CUR_SPEED: {
				if (val == 0xffff) {
					tv_currentSpeed.setText("-- Km/h");
				} else {
					tv_currentSpeed.setText(val + " Km/h");
				}
				break;
			}
			case ConstGolf.U_HANDPART_STATE: {
				if (val == 0x01) {
					tv_handStop.setText(R.string.tv_handStopOn);
					iv_handStop.setImageResource(R.drawable.handstop);
				} else {
					tv_handStop.setText(R.string.tv_handStopOff);
					iv_handStop.setImageResource(R.drawable.handstop_warn);
				}
				break;
			}
			case ConstGolf.U_BATTERY_VOLTAGE: {
				// 电池电压
				tv_battery.setText((val >> 8 & 0xFF) + "." + (val & 0xFF) + " V");
				break;
			}
			case ConstGolf.U_WASH_STATE: {
				// 清洗液报警
				if (val == 0x01) {
					tv_cleanWater.setText(R.string.tv_cleanWaterOn);
					iv_cleanWaterImageView.setImageResource(R.drawable.cleanwater_warn);
				} else {
					tv_cleanWater.setText(R.string.tv_cleanWaterOff);
					iv_cleanWaterImageView.setImageResource(R.drawable.cleanwater);
				}
				break;
			}
			case ConstGolf.U_2_BY_START: {
				tv_distance.setText((val / 10.f) + " Km");
				break;
			}
			case ConstGolf.U_LIGHT_TURN_LEFT: {
				iv_leftlight.setVisibility(val == 1 ? View.VISIBLE : View.GONE);
				break;
			}
			case ConstGolf.U_LIGHT_TURN_RIGHT: {
				iv_rightlight.setVisibility(val == 1 ? View.VISIBLE : View.GONE);
				break;
			}
			case ConstGolf.U_DOOR_ENGINE: {
				if (val == 1) {
					iv_hood.setVisibility(View.VISIBLE);
				} else {
					iv_hood.setVisibility(View.GONE);
				}
				break;
			}
			case ConstGolf.U_DOOR_FL: {
				if (val == 0x01) {// 左前门
					iv_leftFrontDoor_close.setVisibility(View.GONE);
					iv_leftFrontDoor_open.setVisibility(View.VISIBLE);
				} else {
					iv_leftFrontDoor_close.setVisibility(View.VISIBLE);
					iv_leftFrontDoor_open.setVisibility(View.GONE);
				}
				break;
			}
			case ConstGolf.U_DOOR_FR: {
				if (val == 0x01) {// 右前门
					iv_RightFrontDoor_close.setVisibility(View.GONE);
					iv_RightFrontDoor_open.setVisibility(View.VISIBLE);
				} else {
					iv_RightFrontDoor_close.setVisibility(View.VISIBLE);
					iv_RightFrontDoor_open.setVisibility(View.GONE);
				}
				break;
			}
			case ConstGolf.U_DOOR_RL: {
				if (val == 0x01) {// 左后门
					iv_leftRealDoor_close.setVisibility(View.GONE);
					iv_leftRealDoor_open.setVisibility(View.VISIBLE);
				} else {
					iv_leftRealDoor_close.setVisibility(View.VISIBLE);
					iv_leftRealDoor_open.setVisibility(View.GONE);
				}
				break;
			}
			case ConstGolf.U_DOOR_RR: {
				if (val == 0x01) {// 右后门
					iv_rightRealDoor_close.setVisibility(View.GONE);
					iv_rightRealDoor_open.setVisibility(View.VISIBLE);
				} else {
					iv_rightRealDoor_close.setVisibility(View.VISIBLE);
					iv_rightRealDoor_open.setVisibility(View.GONE);
				}
				break;
			}
			case ConstGolf.U_DOOR_BACK: {
				if (val == 1) {
					iv_taixbox.setVisibility(View.VISIBLE);
				} else {
					iv_taixbox.setVisibility(View.GONE);
				}
				break;
			}
			default:
				break;
			}
		}
	};
}
