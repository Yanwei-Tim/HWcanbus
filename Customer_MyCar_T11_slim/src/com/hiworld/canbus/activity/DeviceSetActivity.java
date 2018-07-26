package com.hiworld.canbus.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ChangeDataUtil;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.util.UtilityClass;
import com.hiworld.canbus.view.MyCheckBox;
import com.hiworld.canbus.view.MyRadioButton;
import com.hiworld.canbus.view.OnBoxClickListener;
import com.hiworld.mycar.t11.R;

//设备设置界面
public class DeviceSetActivity extends BaseActivity implements OnCheckedChangeListener, OnBoxClickListener, OnClickListener{

	private TextView mTextTitle;
	private ImageButton mBack;
	private RelativeLayout rl_carPcWarn,rl_speedCheck,lay_oilWarn;
	private CheckBox check_carPc,check_speedCheck;//check_voiceSet,check_deviceSet,check_roadSet,
	/*private TextView voiceSet_t1,voiceSet_t2,voiceSet_t3,voiceSet_t4,
	roadSet_t1,roadSet_t2,roadSet_t3,deviceWarn_t1,deviceWarn_t2,deviceWarn_t3;*/
	//rl_voiceSet,rl_roadSet,rl_devicewarn,
	private CheckBox check_turnLight,
	check_door,check_oil,check_tiredDrive,/*check_bonnet,*/check_handBrake_pc,check_safeBelt_pc;
//	private MyCheckBox check_start,check_handbrake,check_safebelt;
	private SharedPreferences preferences;
//	private MyRadioButton box_city,box_town,box_village;//box_high,box_middle,box_low,box_auto,
	private Toast toast;
	private LinearLayout ll_deviceset_bac;
	private TextView text_speedCheck,text_skin;
	private Button btn_plus,btn_minus;
	//APP肤色图片资源集合
	private int[] skinColourList;
	
	@SuppressLint("HandlerLeak") 
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
//			case ConstData.MSG_GET_BLUESTATE:
//			case ConstData.MSG_GET_CARPCCHANGE:
//				initSetDeviceSet(CarInfo.getInstance());
//				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceset);
		preferences = getSharedPreferences("BlueService", MODE_PRIVATE);
		initView();
		setListener();
//		VissCmdParse.getInstance().setDeviceSetHandler(mHandler);
		setDefaultState();
		registerInterfilter();
		setShowOrHide();
	}
	
	private void setShowOrHide() {
		
		if (CarPcInfo.getInstance().getM_iOutLightAble() == 1) {
			check_turnLight.setVisibility(View.VISIBLE);
		}else {
			check_turnLight.setVisibility(View.GONE);
		}
		if (CarPcInfo.getInstance().getM_iDoorAble() == 1) {
			check_door.setVisibility(View.VISIBLE);
		}else {
			check_door.setVisibility(View.GONE);
		}
		if (CarPcInfo.getInstance().getM_iResidualOilAble() == 1) {
			lay_oilWarn.setVisibility(View.VISIBLE);
		}else {
			lay_oilWarn.setVisibility(View.GONE);
		}
		if (CarPcInfo.getInstance().getM_iSelfStartMileageAble() == 1) {
			check_tiredDrive.setVisibility(View.VISIBLE);
		}else {
			check_tiredDrive.setVisibility(View.GONE);
		}
	}

	private void setDefaultState() {
		
//		int voiceSet = preferences.getInt("voiceSet", 3);
//		if (voiceSet == 0) {
//			box_high.setChecked(true);
//		}else if (voiceSet == 1) {
//			box_middle.setChecked(true);
//		}else if (voiceSet == 2) {
//			box_low.setChecked(true);
//		}else {
//			box_auto.setChecked(true);
//		}
//		int sensity = preferences.getInt("sensity", 1);
//		if (sensity == 0) {
//			box_city.setChecked(true);
//		}else if (sensity == 1) {
//			box_town.setChecked(true);
//		}else {
//			box_village.setChecked(true);
//		}
		
//		int startWarn = preferences.getInt("startWarn", 1);
//		if (startWarn == 1) {
//			check_start.setChecked(true);
//		}else {
//			check_start.setChecked(false);
//		}
//		
//		int safeBelt = preferences.getInt("safebelt", 0);
//		if (safeBelt == 1) {
//			check_safebelt.setChecked(true);
//		}else {
//			check_safebelt.setChecked(false);
//		}
//		
//		int handBrake = preferences.getInt("handBrake", 0);
//		if (handBrake == 1) {
//			check_handbrake.setChecked(true);
//		}else {
//			check_handbrake.setChecked(false);
//		}
		
		check_turnLight.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcTurnWarn"));
		check_door.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcDoorWarn"));
		check_oil.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcOilnWarn"));
		check_tiredDrive.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcTiredDrive"));
//		check_bonnet.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcBonnetTrunk"));
		check_handBrake_pc.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcHandBrake"));
		check_safeBelt_pc.setChecked(UtilityClass.getPrefrenceBoolean(DeviceSetActivity.this, "pcSafeBelt"));
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
//		if (VissCmdParse.getInstance().getServiceConnect()) {
//			VissCmdParse.getInstance().requestCmd(BlueUtilData.ACTION_BLUETOOTH_ADDREQUEST, new int[] {0xE0,0x48,0xD2});
//		}
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(DeviceSetActivity.this);
		/////ILL背光
		if (CarPcInfo.getInstance().getM_iILLAble() == 1) {//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				ll_deviceset_bac.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
//				check_voiceSet.setBackgroundResource(skinColourList[69]);
//		        check_deviceSet.setBackgroundResource(skinColourList[69]);
//		        check_roadSet.setBackgroundResource(skinColourList[69]);
		        check_carPc.setBackgroundResource(skinColourList[69]);
		        check_speedCheck.setBackgroundResource(skinColourList[69]);
		        
		        
		        check_turnLight.setBackgroundResource(skinColourList[71]);
		    	check_door.setBackgroundResource(skinColourList[71]);
		    	check_oil.setBackgroundResource(skinColourList[71]);
		    	check_tiredDrive.setBackgroundResource(skinColourList[71]);
		    	check_handBrake_pc.setBackgroundResource(skinColourList[71]);
		    	check_safeBelt_pc.setBackgroundResource(skinColourList[71]);
//		    	check_start.setBackgroundResource(skinColourList[71]);
//		    	check_handbrake.setBackgroundResource(skinColourList[71]);
//		    	check_safebelt.setBackgroundResource(skinColourList[71]);
		    	
		    	text_skin.setBackgroundResource(skinColourList[67]);
		        
			}else {
				ll_deviceset_bac.setBackgroundResource(skinColourList[22]);//main_backgrond
//				check_voiceSet.setBackgroundResource(skinColourList[68]);
//		        check_deviceSet.setBackgroundResource(skinColourList[68]);
//		        check_roadSet.setBackgroundResource(skinColourList[68]);
		        check_carPc.setBackgroundResource(skinColourList[68]);
		        check_speedCheck.setBackgroundResource(skinColourList[68]);
		        
		        check_turnLight.setBackgroundResource(skinColourList[70]);
		    	check_door.setBackgroundResource(skinColourList[70]);
		    	check_oil.setBackgroundResource(skinColourList[70]);
		    	check_tiredDrive.setBackgroundResource(skinColourList[70]);
		    	check_handBrake_pc.setBackgroundResource(skinColourList[70]);
		    	check_safeBelt_pc.setBackgroundResource(skinColourList[70]);
//		    	check_start.setBackgroundResource(skinColourList[70]);
//		    	check_handbrake.setBackgroundResource(skinColourList[70]);
//		    	check_safebelt.setBackgroundResource(skinColourList[70]);
		    	
		    	text_skin.setBackgroundResource(skinColourList[66]);
			}
		}else {
			ll_deviceset_bac.setBackgroundResource(skinColourList[22]);//main_backgrond
//			check_voiceSet.setBackgroundResource(skinColourList[68]);
//	        check_deviceSet.setBackgroundResource(skinColourList[68]);
//	        check_roadSet.setBackgroundResource(skinColourList[68]);
	        check_carPc.setBackgroundResource(skinColourList[68]);
	        check_speedCheck.setBackgroundResource(skinColourList[68]);
	        
	        check_turnLight.setBackgroundResource(skinColourList[70]);
	    	check_door.setBackgroundResource(skinColourList[70]);
	    	check_oil.setBackgroundResource(skinColourList[70]);
	    	check_tiredDrive.setBackgroundResource(skinColourList[70]);
	    	check_handBrake_pc.setBackgroundResource(skinColourList[70]);
	    	check_safeBelt_pc.setBackgroundResource(skinColourList[70]);
//	    	check_start.setBackgroundResource(skinColourList[70]);
//	    	check_handbrake.setBackgroundResource(skinColourList[70]);
//	    	check_safebelt.setBackgroundResource(skinColourList[70]);
	    	
	    	text_skin.setBackgroundResource(skinColourList[66]);
		}
	}
	
	@SuppressLint("ShowToast") 
	private void initView() {
		
		
		ll_deviceset_bac = (LinearLayout) findViewById(R.id.ll_deviceset_bac);
		
		mTextTitle = (TextView) findViewById(R.id.iv_top_title);
		mTextTitle.setText(R.string.mine_deviceset);
		mBack = (ImageButton) this.findViewById(R.id.iv_back);
//		rl_voiceSet = (RelativeLayout) findViewById(R.id.rl_voiceSet);
//        rl_roadSet = (RelativeLayout) findViewById(R.id.rl_roadSet);
//        rl_devicewarn = (RelativeLayout) findViewById(R.id.rl_devicewarn);
        rl_carPcWarn = (RelativeLayout) findViewById(R.id.rl_carpcWarn);
        rl_speedCheck = (RelativeLayout) findViewById(R.id.rl_speedCheck);
        
        text_speedCheck = (TextView) findViewById(R.id.text_speed_check);
        int speedCheck = preferences.getInt("speedCheck", 0);
        text_speedCheck.setText(speedCheck+"");
        
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        
//        box_high = (MyRadioButton) findViewById(R.id.box_high);
//        box_middle = (MyRadioButton) findViewById(R.id.box_middle);
//        box_low = (MyRadioButton) findViewById(R.id.box_low);
//        box_auto = (MyRadioButton) findViewById(R.id.box_auto);
//        box_city = (MyRadioButton) findViewById(R.id.box_city);
//        box_town = (MyRadioButton) findViewById(R.id.box_town);
//        box_village = (MyRadioButton) findViewById(R.id.box_village);
//        
//        check_voiceSet = (CheckBox) findViewById(R.id.text_voiceSet);
//        check_deviceSet = (CheckBox) findViewById(R.id.text_deviceWarn);
//        check_roadSet = (CheckBox) findViewById(R.id.text_roadSet);
        check_carPc = (CheckBox) findViewById(R.id.text_carpcWarn);
        check_speedCheck = (CheckBox) findViewById(R.id.text_speedCheck);
        
//        check_start = (MyCheckBox) findViewById(R.id.box_startwarn);
//        check_handbrake = (MyCheckBox) findViewById(R.id.box_handbrake);
//        check_safebelt = (MyCheckBox) findViewById(R.id.box_safebelt);
        
        check_turnLight = (CheckBox)findViewById(R.id.box_turnLight);
        check_door = (CheckBox)findViewById(R.id.box_doorWarn);
        check_oil = (CheckBox)findViewById(R.id.box_oilWarn);
        check_tiredDrive = (CheckBox)findViewById(R.id.box_tiredDriving);
//        check_bonnet = (CheckBox)findViewById(R.id.box_bonnetTrunk);
        check_handBrake_pc = (CheckBox)findViewById(R.id.box_handBrake_carpc);
        check_safeBelt_pc = (CheckBox)findViewById(R.id.box_safeBelt_carpc);
        
        lay_oilWarn = (RelativeLayout) findViewById(R.id.lay_oilWarn);
        text_skin = (TextView) findViewById(R.id.text_skin);
        
        /*voiceSet_t1 = (TextView) findViewById(R.id.voiceset_t1);
        voiceSet_t2 = (TextView) findViewById(R.id.voiceset_t2);
        voiceSet_t3 = (TextView) findViewById(R.id.voiceset_t3);
        voiceSet_t4 = (TextView) findViewById(R.id.voiceset_t4);
        
        roadSet_t1 = (TextView) findViewById(R.id.roadset_t1);
        roadSet_t2 = (TextView) findViewById(R.id.roadset_t2);
        roadSet_t3 = (TextView) findViewById(R.id.roadset_t3);
        
        deviceWarn_t1 = (TextView) findViewById(R.id.devicewarn_t1);
        deviceWarn_t2 = (TextView) findViewById(R.id.devicewarn_t2);
        deviceWarn_t3 = (TextView) findViewById(R.id.devicewarn_t3);*/
        
//        box_high.setOnBoxCLickListener(this);
//        box_middle.setOnBoxCLickListener(this);
//        box_low.setOnBoxCLickListener(this);
//        box_auto.setOnBoxCLickListener(this);
        
//        box_city.setOnBoxCLickListener(this);
//        box_town.setOnBoxCLickListener(this);
//        box_village.setOnBoxCLickListener(this);
        
//        check_start.setOnBoxCLickListener(this);
//        check_handbrake.setOnBoxCLickListener(this);
//        check_safebelt.setOnBoxCLickListener(this);
        
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        
        check_turnLight.setOnCheckedChangeListener(this);
        check_door.setOnCheckedChangeListener(this);
        check_oil.setOnCheckedChangeListener(this);
        check_tiredDrive.setOnCheckedChangeListener(this);
//        check_bonnet.setOnCheckedChangeListener(this);
        check_handBrake_pc.setOnCheckedChangeListener(this);
        check_safeBelt_pc.setOnCheckedChangeListener(this);
        
	}
	
	private void setListener() {
		
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				DeviceSetActivity.this.finish();
			}
		});
		
//		check_voiceSet.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				
//				if (isChecked) {
//					rl_voiceSet.setVisibility(View.VISIBLE);
//				}else {
//					rl_voiceSet.setVisibility(View.GONE);
//				}
//			}
//		});
		
//		check_deviceSet.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				
//				if (isChecked) {
//					rl_devicewarn.setVisibility(View.VISIBLE);
//				}else {
//					rl_devicewarn.setVisibility(View.GONE);
//				}
//			}
//		});
		
//		check_roadSet.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				
//				if (isChecked) {
//					rl_roadSet.setVisibility(View.VISIBLE);
//				}else {
//					rl_roadSet.setVisibility(View.GONE);
//				}
//			}
//		});
		
		check_carPc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked) {
					rl_carPcWarn.setVisibility(View.VISIBLE);
				}else {
					rl_carPcWarn.setVisibility(View.GONE);
				}
			}
		});
		
		check_speedCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked) {
					rl_speedCheck.setVisibility(View.VISIBLE);
				}else {
					rl_speedCheck.setVisibility(View.GONE);
				}
			}
		});
		
		btn_minus.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		
		text_skin.setOnClickListener(this);
	}

	
	
	@Override
	protected void onPause() {
		
		super.onPause();
		if (toast != null) {
			toast.cancel();
		}
	}
	
	private void initSetDeviceSet(final CarInfo instance) {
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setCarInfo(instance);
			}
		});
	}
	
	private void setCarInfo(CarInfo carInfo){
//		if (carInfo.getVoiceSet() == 3) {
//			box_high.setChecked(true);
//			preferences.edit().putInt("voiceSet", 0).commit();
//			System.out.println("========声音高");
//		}else if (carInfo.getVoiceSet() == 2) {
//			box_middle.setChecked(true);
//			preferences.edit().putInt("voiceSet", 1).commit();
//			System.out.println("========声音中");
//		}else if (carInfo.getVoiceSet() == 1) {
//			box_low.setChecked(true);
//			preferences.edit().putInt("voiceSet", 2).commit();
//			System.out.println("========声音低");
//		}else {
//			box_auto.setChecked(true);
//			preferences.edit().putInt("voiceSet", 3).commit();
//			System.out.println("========声音自动");
//		}
//		
//		if (carInfo.getM_bySenis() == 1) {
//			box_village.setChecked(true);
//			System.out.println("========路况乡村");
//			preferences.edit().putInt("sensity", 2).commit();
//		}else if (carInfo.getM_bySenis() == 3) {
//			box_town.setChecked(true);
//			System.out.println("========路况城镇");
//			preferences.edit().putInt("sensity", 1).commit();
//		}else if (carInfo.getM_bySenis() == 5) {
//			box_city.setChecked(true);
//			System.out.println("========路况");
//			preferences.edit().putInt("sensity", 0).commit();
//		}
		
//		if (carInfo.getDeviceStartWarn() == 1) {
//			check_start.setChecked(true);
//			preferences.edit().putInt("startWarn", 1).commit();
//			System.out.println("==========cc设备启动开");
//		}else {
//			check_start.setChecked(false);
//			preferences.edit().putInt("startWarn", 0).commit();
//			System.out.println("==========cc设备启动关");
//		}
//		
//		if (carInfo.getSafeBeltWarn() == 1) {
//			check_safebelt.setChecked(true);
//			preferences.edit().putInt("safebelt", 1).commit();
//			System.out.println("==========cc安全带开");
//		}else {
//			check_safebelt.setChecked(false);
//			preferences.edit().putInt("safebelt", 0).commit();
//			System.out.println("==========cc安全带关");
//		}
//		
//		if (carInfo.getHandBrakeWarn() == 1) {
//			check_handbrake.setChecked(true);
//			preferences.edit().putInt("handBrake", 1).commit();
//			System.out.println("==========cc手刹开");
//		}else {
//			check_handbrake.setChecked(false);
//			preferences.edit().putInt("handBrake", 0).commit();
//			System.out.println("==========cc手刹关");
//		}
	}
	
	private byte[] getDeviceSetOrder(int cmdId,int param1,int param2){
		return new byte[]{0x04,0x3B,(byte) cmdId,(byte) param1,0x00,(byte) param2};
	}
	
	private byte[] getTpmsOrder(int cmdId, int param){
		return new byte[]{0x02, 0x4D, (byte) cmdId, (byte) param};
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if (buttonView != null) {
			switch (buttonView.getId()) {
			case R.id.box_turnLight:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcTurnWarn", isChecked);
				break;
			case R.id.box_doorWarn:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcDoorWarn", isChecked);
				break;
			case R.id.box_oilWarn:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcOilnWarn", isChecked);
				break;
			case R.id.box_tiredDriving:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcTiredDrive", isChecked);
				break;
			/*case R.id.box_bonnetTrunk:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcBonnetTrunk", isChecked);
				break;*/
			case R.id.box_handBrake_carpc:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcHandBrake", isChecked);
				break;
			case R.id.box_safeBelt_carpc:
				UtilityClass.setPrefrenceBoolean(DeviceSetActivity.this, "pcSafeBelt", isChecked);
				break;
			default:
				break;
			}
		}
	}
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			
			//获取皮肤图片资源 add by jiaqing.liu 
			skinColourList = TimeUtils.getInstance().getAllSkin(DeviceSetActivity.this);
			String action = intent.getAction();
			if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					ll_deviceset_bac.setBackgroundResource(skinColourList[22]);//main_backgrond
//					check_voiceSet.setBackgroundResource(skinColourList[68]);
//			        check_deviceSet.setBackgroundResource(skinColourList[68]);
//			        check_roadSet.setBackgroundResource(skinColourList[68]);
			        check_carPc.setBackgroundResource(skinColourList[68]);
			        check_speedCheck.setBackgroundResource(skinColourList[68]);
			        
			        check_turnLight.setBackgroundResource(skinColourList[70]);
			    	check_door.setBackgroundResource(skinColourList[70]);
			    	check_oil.setBackgroundResource(skinColourList[70]);
			    	check_tiredDrive.setBackgroundResource(skinColourList[70]);
			    	check_handBrake_pc.setBackgroundResource(skinColourList[70]);
			    	check_safeBelt_pc.setBackgroundResource(skinColourList[70]);
//			    	check_start.setBackgroundResource(skinColourList[70]);
//			    	check_handbrake.setBackgroundResource(skinColourList[70]);
//			    	check_safebelt.setBackgroundResource(skinColourList[70]);
			    	
			    	text_skin.setBackgroundResource(skinColourList[68]);
				}else {
					ll_deviceset_bac.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
//					check_voiceSet.setBackgroundResource(skinColourList[69]);
//			        check_deviceSet.setBackgroundResource(skinColourList[69]);
//			        check_roadSet.setBackgroundResource(skinColourList[69]);
			        check_carPc.setBackgroundResource(skinColourList[69]);
			        check_speedCheck.setBackgroundResource(skinColourList[69]);
			        
			        check_turnLight.setBackgroundResource(skinColourList[71]);
			    	check_door.setBackgroundResource(skinColourList[71]);
			    	check_oil.setBackgroundResource(skinColourList[71]);
			    	check_tiredDrive.setBackgroundResource(skinColourList[71]);
			    	check_handBrake_pc.setBackgroundResource(skinColourList[71]);
			    	check_safeBelt_pc.setBackgroundResource(skinColourList[71]);
//			    	check_start.setBackgroundResource(skinColourList[71]);
//			    	check_handbrake.setBackgroundResource(skinColourList[71]);
//			    	check_safebelt.setBackgroundResource(skinColourList[71]);
			    	
			    	text_skin.setBackgroundResource(skinColourList[69]);
				}
			}else {
				ll_deviceset_bac.setBackgroundResource(skinColourList[22]);//main_backgrond
//				check_voiceSet.setBackgroundResource(skinColourList[68]);
//		        check_deviceSet.setBackgroundResource(skinColourList[68]);
//		        check_roadSet.setBackgroundResource(skinColourList[68]);
		        check_carPc.setBackgroundResource(skinColourList[68]);
		        check_speedCheck.setBackgroundResource(skinColourList[68]);
		        
		        check_turnLight.setBackgroundResource(skinColourList[70]);
		    	check_door.setBackgroundResource(skinColourList[70]);
		    	check_oil.setBackgroundResource(skinColourList[70]);
		    	check_tiredDrive.setBackgroundResource(skinColourList[70]);
		    	check_handBrake_pc.setBackgroundResource(skinColourList[70]);
		    	check_safeBelt_pc.setBackgroundResource(skinColourList[70]);
//		    	check_start.setBackgroundResource(skinColourList[70]);
//		    	check_handbrake.setBackgroundResource(skinColourList[70]);
//		    	check_safebelt.setBackgroundResource(skinColourList[70]);
		    	
		    	text_skin.setBackgroundResource(skinColourList[68]);
			}
		}
	};
	
	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_ILL);
		registerReceiver(mReceiver, infiter);
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	@Override
	public void onBoxClick(View v) {
		
		if (v != null) {
			switch (v.getId()) {
//			case R.id.box_high:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0B, 0x01, 0x03)));
////					BluetoothControl.getInstance().sendDeviceInfo(0x0B, 0x01, 0x03);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=高");
//				break;
//			case R.id.box_middle:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0B, 0x01, 0x02)));
////					BluetoothControl.getInstance().sendDeviceInfo(0x0B, 0x01, 0x02);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=中");
//				break;
//			case R.id.box_low:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0B, 0x01, 0x01)));
////					BluetoothControl.getInstance().sendDeviceInfo(0x0B, 0x01, 0x01);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=低");
//				break;
//			case R.id.box_auto:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0B, 0x01, 0xFF)));
////					BluetoothControl.getInstance().sendDeviceInfo(0x0B, 0x01, 0xFF);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=自动");
//				break;
//			case R.id.box_startwarn:
//				if (VissCmdParse.getInstance().bConnect) {
//					if (!check_start.isChecked()) {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x01, 0x01)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x01, 0x01);
////						System.out.println("=======send=启动关");
//					}else {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x01, 0x00)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x01, 0x00);
////						System.out.println("=======send=启动开");
//					}
//				}else {
//					showToast();
//				}
//				break;
//			case R.id.box_handbrake:
//				if (VissCmdParse.getInstance().bConnect) {
//					if (!check_handbrake.isChecked()) {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x02, 0x01)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x02, 0x01);
////						System.out.println("=======send=手刹关");
//					}else {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x02, 0x00)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x02, 0x00);
////						System.out.println("=======send=手刹开");
//					}
//				}else {
//					showToast();
//				}
//				break;
//			case R.id.box_safebelt:
//				if (VissCmdParse.getInstance().bConnect) {
//					if (!check_safebelt.isChecked()) {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x03, 0x01)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x03, 0x01);
////						System.out.println("=======send=安全带关");
//					}else {
//						VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getDeviceSetOrder(0x0C, 0x03, 0x00)));
////						BluetoothControl.getInstance().sendDeviceInfo(0x0C, 0x03, 0x00);
////						System.out.println("=======send=安全带开");
//					}
//				}else {
//					showToast();
//				}
//				break;
//			case R.id.box_city:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getTpmsOrder(0x02, 0x05)));
////					BluetoothControl.getInstance().sendTpmsChekc(0x02, 0x05);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=城市");
//				break;
//			case R.id.box_town:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getTpmsOrder(0x02, 0x03)));
////					BluetoothControl.getInstance().sendTpmsChekc(0x02, 0x03);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=城镇");
//				break;
//			case R.id.box_village:
//				if (VissCmdParse.getInstance().bConnect) {
//					VissCmdParse.getInstance().sendIntsBuffer(ChangeDataUtil.bytes2Ints(getTpmsOrder(0x02, 0x01)));
////					BluetoothControl.getInstance().sendTpmsChekc(0x02, 0x01);
//				}else {
//					showToast();
//				}
////				System.out.println("=======send=乡村");
//				break;
			default:
				break;
			}
		}
	}
	
	private void showToast(){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
//				toast.setText(getResources().getString(R.string.tips_bt_unConnect));
//				toast.show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		
		if (v != null) {
			switch (v.getId()) {
			case R.id.btn_minus://减
				int speedCheck = preferences.getInt("speedCheck", 0);
				if (speedCheck >= -19) {
					preferences.edit().putInt("speedCheck", speedCheck-1).commit();
					text_speedCheck.setText((speedCheck-1)+"");
				}
				break;
			case R.id.btn_plus://加
				int speedCheck1 = preferences.getInt("speedCheck", 0);
				if (speedCheck1 <= 19) {
					preferences.edit().putInt("speedCheck", speedCheck1+1).commit();
					text_speedCheck.setText((speedCheck1+1)+"");
				}
				break;
			case R.id.text_skin://皮肤设置
				Intent intent = new Intent(DeviceSetActivity.this,SkinSettingActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.my_scale_action,R.anim.my_alpha_action);//放大
				break;
			default:
				break;
			}
		}
	}
}
