package com.hiworld.canbus.fragment;

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

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.view.CustomTextView;
import com.hiworld.mycar.t11.R;

//车辆状态fragment
public class CarStateFragmentnew extends Fragment{
	
	private ImageView iv_nearlight,iv_farlight,iv_widelight,iv_stoplight,
	iv_leftlight,iv_rightlight,iv_doublelight;
	//车门
	private ImageView iv_leftFrontDoor_close,iv_leftFrontDoor_open,iv_leftRealDoor_close,
		iv_leftRealDoor_open,iv_RightFrontDoor_close,iv_RightFrontDoor_open,
		iv_rightRealDoor_close,iv_rightRealDoor_open;
	private ImageView iv_hood,iv_taixbox, iv_lf_window, iv_lr_window, iv_rf_window, iv_rr_window;
	private CustomTextView tv_cooltemp,tv_enginespeed,tv_safebaelt,//,iv_htpms_warn
	tv_currentspeed, tv_instantfuel,tv_handstop, tv_battery;
	private CustomTextView tv_distance,tv_doorlock;
	@SuppressWarnings("unused")
	private ImageView iv_cooltemp,iv_instantfuel,iv_battery,iv_enginespeed,
	iv_currentspeed,iv_distance,iv_safebaelt,iv_handstop,iv_doorlock;
	
	TextView tv_title_cooltemp, tv_title_lock;
	
	@SuppressWarnings("unused")
	private int a,b,c;
	
	private boolean bRunnable=false;
	
	private View mContentView; 
	private Activity mActivity;
	private LinearLayout rl_carPc;
	
	private ImageView iv_block;
	
	//APP肤色图片资源集合
	private int[] skinColourList;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		mActivity = activity;
//		if (mHandler != null) {
//			VissCmdParse.getInstance().setStateHandler(mHandler);
//		}
		FragmentParseData.getInstance().setmHandlerCarStateFregment(mHandler);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		
		super.onResume();
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(mActivity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(mActivity);
		
		if(mContentView == null){
			mContentView = inflater.inflate(R.layout.carstate_fragment_layout, container,false);
			initView(mContentView);
			SendCarPcInfo(CarPcInfo.getInstance());
		}else{
			ViewGroup parent = (ViewGroup) mContentView.getParent();  
            if (parent != null)  {  
                parent.removeView(mContentView);  
            }  
		}
		
//		registerInterfilter();
		return mContentView;
	}

	private void SendCarPcInfo(final CarPcInfo instance) {
		
		if (mActivity == null) {
		   return;	
		}
		mActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				SetCarPcInfo(instance);
			}
		});
	}

	private void initView(View view) {
		
		//灯光
		iv_nearlight = (ImageView) view.findViewById(R.id.iv_nearlight);
		iv_farlight = (ImageView) view.findViewById(R.id.iv_farlight);
		iv_widelight = (ImageView) view.findViewById(R.id.iv_showwidthlight);
		iv_stoplight = (ImageView) view.findViewById(R.id.iv_stoplight);
		iv_doublelight = (ImageView) view.findViewById(R.id.iv_doublelight);
		iv_leftlight = (ImageView) view.findViewById(R.id.iv_leftlight);
		iv_rightlight = (ImageView) view.findViewById(R.id.iv_rightlight);
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
		
		iv_lf_window = (ImageView) view.findViewById(R.id.iv_lf_window);
		iv_lr_window = (ImageView) view.findViewById(R.id.iv_lr_window);
		iv_rf_window = (ImageView) view.findViewById(R.id.iv_rf_window);
		iv_rr_window = (ImageView) view.findViewById(R.id.iv_rr_window);
		//iv_htpms_warn = (ImageView) view.findViewById(R.id.iv_htpms_warn);
		
		//状态信息
		tv_cooltemp =  (CustomTextView) view.findViewById(R.id.tv_customer_cooltemp);
		//System.out.println("tv_cooltemp =="+tv_cooltemp);
		iv_cooltemp = (ImageView)view.findViewById(R.id.iv_cooltemp);
		tv_instantfuel = (CustomTextView)view.findViewById(R.id.tv_instantfuel);
		iv_instantfuel = (ImageView)view.findViewById(R.id.iv_instantfuel);
		tv_battery = (CustomTextView)view.findViewById(R.id.tv_battery);
		iv_battery = (ImageView)view.findViewById(R.id.iv_battery);
		tv_enginespeed = (CustomTextView)view.findViewById(R.id.tv_enginespeed);
		iv_enginespeed = (ImageView)view.findViewById(R.id.iv_enginespeed);
		tv_currentspeed = (CustomTextView)view.findViewById(R.id.tv_currentspeed);
		iv_currentspeed = (ImageView)view.findViewById(R.id.iv_currentspeed);
		tv_distance = (CustomTextView)view.findViewById(R.id.tv_distance);
		iv_distance = (ImageView)view.findViewById(R.id.iv_distance);
		tv_safebaelt = (CustomTextView)view.findViewById(R.id.tv_safebelt);
		iv_safebaelt = (ImageView)view.findViewById(R.id.iv_safebelt);
		tv_handstop = (CustomTextView)view.findViewById(R.id.tv_handstop);
		iv_handstop = (ImageView)view.findViewById(R.id.iv_handstop);
		tv_doorlock = (CustomTextView)view.findViewById(R.id.tv_doorlock);
		iv_doorlock = (ImageView)view.findViewById(R.id.iv_doorlock);
		
		rl_carPc = (LinearLayout) view.findViewById(R.id.ll_carpc_bac);
		iv_block = (ImageView) view.findViewById(R.id.iv_block);
		
		tv_title_cooltemp = (TextView) view.findViewById(R.id.tv_title_cooltemp);
		tv_title_lock = (TextView) view.findViewById(R.id.tv_title_lock);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
//			case ConstData.MSG_GET_BLUESTATE:
//			case ConstData.MSG_GET_CARPCCHANGE:
//				SendCarPcInfo(CarPcInfo.getInstance());
//				break;
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				SendCarPcInfo(hudInfo);
				break;
//			case ConstData.MESSAGE_TIRE_FAILURE://轮胎报警
//				CarInfo carInfo = (CarInfo) msg.obj;
//				initSetCar(carInfo);
//				break;
			case ConstData.MSG_GET_DOUBLELIGHT:
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
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_DOUBLELIGHT, 500);
				break;
			default:
				break;
			}
		}
	};

	protected void SetCarPcInfo(CarPcInfo vmCarInfo) {
		
//		boolean bound = VissCmdParse.getInstance().bBound;
//		boolean connect = VissCmdParse.getInstance().bConnect;
		/////ILL背光
		if (vmCarInfo.getM_iILLAble() == 1) {
			if (vmCarInfo.getM_iILL() == 1) {
				rl_carPc.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
				iv_block.setImageResource(skinColourList[21]);//block_ill
			}else {
				rl_carPc.setBackgroundResource(skinColourList[22]);//main_backgrond
				iv_block.setImageResource(skinColourList[23]);//block
			}
		}else {
			rl_carPc.setBackgroundResource(skinColourList[22]);//main_backgrond
			iv_block.setImageResource(skinColourList[23]);//block
		}
		
		//灯光
		if (vmCarInfo.getM_iOutLightAble() == 1 ) {
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
			if (vmCarInfo.getM_iStopLight() == 0x01) {//刹车灯
				iv_stoplight.setVisibility(View.VISIBLE);
			}else {
				iv_stoplight.setVisibility(View.GONE);
			}
			
			if ((vmCarInfo.getM_iDoubleLight() == 0x01||vmCarInfo.getM_iLeftTrunLight() == 0x01
					||vmCarInfo.getM_iRightTrunLight() == 0x01) && !bRunnable) {//双闪灯
				bRunnable = true;
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_DOUBLELIGHT, 500);
			}else if (vmCarInfo.getM_iDoubleLight() == 0x00 &&vmCarInfo.getM_iLeftTrunLight() == 0x00
					&&vmCarInfo.getM_iRightTrunLight() == 0x00 && bRunnable){
				bRunnable = false;
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				iv_doublelight.setVisibility(View.GONE);
				iv_leftlight.setVisibility(View.GONE);
				iv_rightlight.setVisibility(View.GONE);
			}
		}else{
			iv_nearlight.setVisibility(View.GONE);
			iv_farlight.setVisibility(View.GONE);
			iv_widelight.setVisibility(View.GONE);
			iv_stoplight.setVisibility(View.GONE);
			bRunnable = false;
			mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
			iv_doublelight.setVisibility(View.GONE);
			iv_leftlight.setVisibility(View.GONE);
			iv_rightlight.setVisibility(View.GONE);
		}
		
		//车门
		if (vmCarInfo.getM_iDoorAble() == 1 ) {
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
		}else{
			iv_leftFrontDoor_close.setVisibility(View.VISIBLE);
			iv_leftFrontDoor_open.setVisibility(View.GONE);
			iv_RightFrontDoor_close.setVisibility(View.VISIBLE);
			iv_RightFrontDoor_open.setVisibility(View.GONE);
			iv_leftRealDoor_close.setVisibility(View.VISIBLE);
			iv_leftRealDoor_open.setVisibility(View.GONE);
			iv_rightRealDoor_close.setVisibility(View.VISIBLE);
			iv_rightRealDoor_open.setVisibility(View.GONE);
			iv_hood.setVisibility(View.GONE);
			iv_taixbox.setVisibility(View.GONE);
		}
		
		if (vmCarInfo.getM_iWindowAble() == 1 ) {
			if (vmCarInfo.getM_iLFWindow() == 1 && vmCarInfo.getM_iLFDoor() == 0) {
				iv_lf_window.setVisibility(View.VISIBLE);
			} else {
				iv_lf_window.setVisibility(View.GONE);
			}
			
			if (vmCarInfo.getM_iLRWindow() == 1 && vmCarInfo.getM_iLRDoor() == 0) {
				iv_lr_window.setVisibility(View.VISIBLE);
			} else {
				iv_lr_window.setVisibility(View.GONE);
			}
			
			if (vmCarInfo.getM_iRFWindow() == 1 && vmCarInfo.getM_iRFDoor() == 0) {
				iv_rf_window.setVisibility(View.VISIBLE);
			} else {
				iv_rf_window.setVisibility(View.GONE);
			}
			
			if (vmCarInfo.getM_iRRWindow() == 1 && vmCarInfo.getM_iRRDoor() == 0) {
				iv_rr_window.setVisibility(View.VISIBLE);
			} else {
				iv_rr_window.setVisibility(View.GONE);
			}
		}else{
			iv_lf_window.setVisibility(View.GONE);
			iv_lr_window.setVisibility(View.GONE);
			iv_rf_window.setVisibility(View.GONE);
			iv_rr_window.setVisibility(View.GONE);
		}
		
		int temp = 0;
		//冷却液温度
		if (vmCarInfo.getM_iCooltempAble() == 1 ) {
			tv_title_cooltemp.setText(R.string.text_coolanttemp);
			temp = vmCarInfo.getM_iCooltemp();
			tv_cooltemp.setText(Integer.toString(temp)+" ");
			if (temp > 110) {
				tv_cooltemp.setTextColor(Color.RED);
				iv_cooltemp.setImageResource(skinColourList[24]);//cooltemp_d
			} else {
				tv_cooltemp.setTextColor(Color.WHITE);
				iv_cooltemp.setImageResource(skinColourList[25]);//cooltemp_n
			}
		}else if (vmCarInfo.getM_iResidualOilAble() == 1 ) {
			tv_title_cooltemp.setText(R.string.text_restoil);
			temp = vmCarInfo.getM_iResidualOil();
			tv_cooltemp.setText(Integer.toString(temp)+"%");
			if (temp < 15) {
				tv_cooltemp.setTextColor(Color.RED);
			} else {
				tv_cooltemp.setTextColor(Color.WHITE);
			}
			iv_cooltemp.setImageResource(skinColourList[26]);//icon_oil
		}else if (vmCarInfo.getM_iGearAble() == 1 ) {
			tv_title_cooltemp.setText("档位");
			temp = vmCarInfo.getM_iGear();
			switch (temp) {
			case 1:
				tv_cooltemp.setText("P");
				break;
			case 2:
				tv_cooltemp.setText("N");
				break;
			case 3:
				tv_cooltemp.setText("R");
				break;
			case 4:
				tv_cooltemp.setText("D");
				break;
			case 5:
				tv_cooltemp.setText("S");
				break;
			case 6:
				tv_cooltemp.setText("L");
				break;
			default:
				tv_cooltemp.setText("- -");
				break;
			}
			
			tv_cooltemp.setTextColor(Color.WHITE);
			iv_cooltemp.setImageResource(skinColourList[27]);//icon_gear
		}else{
			tv_title_cooltemp.setText(R.string.text_coolanttemp);
			tv_cooltemp.setText("- -");
			tv_cooltemp.setTextColor(Color.WHITE);
			iv_cooltemp.setImageResource(skinColourList[25]);//cooltemp_n
		}
		
		float ff = 0;
		//小计里程-平均车速
		if (vmCarInfo.getM_iLittleTripAble() == 1 ) {
//			temp = vmCarInfo.getM_fTripmeter_avgspeed();
			temp = vmCarInfo.getM_fSelfstart_avgspeed();//自启动后里程-平均车速
			tv_instantfuel.setText(temp+" ");
		}else{
			tv_instantfuel.setText("- -");
		}
		//电池电压
		if (vmCarInfo.getM_iBatteryVolAble() == 1 ) {
			ff = vmCarInfo.getM_fBatteryVol();
			tv_battery.setText(Float.toString(ff)+" ");
			if (ff < 11.5) {
				tv_battery.setTextColor(Color.RED);
				iv_battery.setImageResource(skinColourList[28]);//batteryvoltage_d
			} else {
				tv_battery.setTextColor(Color.WHITE);
				iv_battery.setImageResource(skinColourList[29]);//batteryvoltage_n
			}
		}else{
			tv_battery.setText("- -");
			tv_battery.setTextColor(Color.WHITE);
			iv_battery.setImageResource(skinColourList[29]);//batteryvoltage_n
		}
		
		
		//发动机转速
		if (vmCarInfo.getM_iEnigineSpeedAble() == 1 ) {
			temp = vmCarInfo.getM_iEngineSpeed();
			tv_enginespeed.setText(Integer.toString(temp)+" ");
		}else{
			tv_enginespeed.setText("- -");
		}
		
		
		//当前车速
		if (vmCarInfo.getM_iSpeedAble() == 1 ) {
			temp = (int) vmCarInfo.getM_fInstantSpeed();
			if (vmCarInfo.getM_iACC() == 0 || temp > 240 || temp < 0) {
				temp = 0;
			}
			if (temp != 0xffff) {
				tv_currentspeed.setText(temp+" ");
			} else {
				tv_currentspeed.setText("- -");
			}
		}else{
			tv_currentspeed.setText("- -");
		}
		
		//改为总里程
		if (vmCarInfo.getM_iTotalMileageAble() == 1 ) {
			temp = vmCarInfo.getM_iTotalMileage();
			tv_distance.setText(Integer.toString(temp)+" ");
		}else{
			tv_distance.setText("- -");
		}
		
		//左安全带
		if (vmCarInfo.getM_iLFSafebeltAble() == 1) {
			temp = vmCarInfo.getM_iLFSafebelt();
			if (temp == 0) {//未系
				tv_safebaelt.setText(R.string.text_safeBandOff);
				tv_safebaelt.setTextColor(Color.RED);
				iv_safebaelt.setImageResource(skinColourList[30]);//safebelt_d
			} else {
				tv_safebaelt.setText(R.string.text_safeBandOn);
				tv_safebaelt.setTextColor(Color.WHITE);
				iv_safebaelt.setImageResource(skinColourList[31]);//safebelt_n
			}
		}else{
			tv_safebaelt.setText("- -");
			tv_safebaelt.setTextColor(Color.WHITE);
			iv_safebaelt.setImageResource(skinColourList[31]);//safebelt_n
		}
		
		
		//手刹
		if (vmCarInfo.getM_iHandbrakeAble() == 1 ) {
			temp = vmCarInfo.getM_iHanderBrake();
			if (temp == 1) {//拉起
				tv_handstop.setText(R.string.text_handbrake_on);
				tv_handstop.setTextColor(Color.RED);
				iv_handstop.setImageResource(skinColourList[32]);//handbrake_d
			} else {
				tv_handstop.setText(R.string.text_handbrake_off);
				tv_handstop.setTextColor(Color.WHITE);
				iv_handstop.setImageResource(skinColourList[33]);//handbrake_n
			}
		}else{
			tv_handstop.setText("- -");
			tv_handstop.setTextColor(Color.WHITE);
			iv_handstop.setImageResource(skinColourList[33]);//handbrake_n
		}
		
		//车内锁
		if (vmCarInfo.getM_iInnerlockAble() == 1 ) {
			if ( vmCarInfo.getM_iInnerlock() == 0) {//未上锁
				iv_doorlock.setImageResource(skinColourList[34]);//doorlock_d
				tv_doorlock.setTextColor(Color.RED);
				tv_doorlock.setText(R.string.text_doorlock_open);
			} else {
				iv_doorlock.setImageResource(skinColourList[35]);//doorlock_n
				tv_doorlock.setTextColor(Color.WHITE);
				tv_doorlock.setText(R.string.text_doorlock_close);
			}
		}else if (vmCarInfo.getM_iSelfStartMileageAble() == 1 ) {
			temp = vmCarInfo.getM_iSelfstart_mileage();
			tv_title_lock.setText("单次里程");
			iv_doorlock.setImageResource(skinColourList[36]);
			tv_doorlock.setTextColor(Color.WHITE);
			tv_doorlock.setText(temp+"");
			
		}else{
			tv_title_lock.setText(R.string.text_doorlock);
			iv_doorlock.setImageResource(skinColourList[35]);//doorlock_n
			tv_doorlock.setTextColor(Color.WHITE);
			tv_doorlock.setText("- -");
		}
	}
	
	/*private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					rl_carPc.setBackgroundResource(R.drawable.main_backgrond);
					iv_block.setImageResource(R.drawable.block);
				}else {
					rl_carPc.setBackgroundResource(R.drawable.main_backgrond_ill);
					iv_block.setImageResource(R.drawable.block_ill);
				}
			}
		}
	};*/
	
	/*private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_ILL);
		mActivity.registerReceiver(mReceiver, infiter);
	}*/
	
	@Override
	public void onDestroyView() {
		
		super.onDestroyView();
//		mActivity.unregisterReceiver(mReceiver);
	}
}
