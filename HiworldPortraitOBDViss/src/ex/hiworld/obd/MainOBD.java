package ex.hiworld.obd;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ex.hiworld.MyApp;
import ex.hiworld.obd.entity.CarPcInfo;
import ex.hiworld.obd.entity.DataCanbus;
import tools.IUiNotify;
import tools.LogsUtils;

public class MainOBD extends OBDLayoutBase {
	private ViewGroup lay_enginespeed, lay_speed, lay_main;
	private TextView curSpeed, curSpeedUnit, engineSpeed, engineUnit, tvlimitSpeed;
	private ImageView curGear, handBrake, limitSpeed;
	
	private ImageView doorFL, doorFR, doorRL, doorRR, doorFront, doorBack,
		windowFL, windowFR, windowRL, windowRR;
	private ImageView lightLeft, lightRight, lightStop, lightFar, lightDoubleFlash, lightWide, lightNearby;
	private ImageView icoLeft, icoRight, icoiLL, icoDoubleFlash, icoFar, icoSafeLeft, icoBattery, icoWater ;

	@Override
	int getLayoutID() {
		return R.layout.layout_obd_main;
	}

	@Override
	void initView() {
		tvlimitSpeed = viewFinder(R.id.tv_limit_speed); 
		tvlimitSpeed.setVisibility(View.GONE);
		lay_main = viewFinder(R.id.lay_main);
		lay_enginespeed = viewFinder(R.id.lay_enginespeed);
		engineSpeed = (TextView) lay_enginespeed.findViewById(R.id.tv_number);
		engineUnit = (TextView) lay_enginespeed.findViewById(R.id.tv_unit);
		lay_speed = viewFinder(R.id.lay_curspeed);
		curSpeed = (TextView) lay_speed.findViewById(R.id.tv_number);
		curSpeedUnit = (TextView) lay_speed.findViewById(R.id.tv_unit);
		curGear = (ImageView) lay_speed.findViewById(R.id.iv_image);
 
		curSpeedUnit.setText("KM/H");
		engineUnit.setText("RPM");
		
		ViewGroup layCarState = viewFinder(R.id.lay_car_state);
		icoLeft = (ImageView) layCarState.findViewById(R.id.iv_light_left);
		icoRight = (ImageView) layCarState.findViewById(R.id.iv_light_right);
		icoiLL = (ImageView) layCarState.findViewById(R.id.iv_light_ill);
		icoDoubleFlash = (ImageView) layCarState.findViewById(R.id.iv_light_double_light);
		icoFar = (ImageView) layCarState.findViewById(R.id.iv_light_far_light);
		icoSafeLeft = (ImageView) layCarState.findViewById(R.id.iv_light_safebelt_left);
		icoBattery = (ImageView) layCarState.findViewById(R.id.iv_light_battery);
		icoWater = (ImageView) layCarState.findViewById(R.id.iv_light_water);
		
		ViewGroup layCarDoor = viewFinder(R.id.lay_car_door);
		layCarDoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				jump2App();
			}
		});
		
		doorFL = (ImageView) layCarDoor.findViewById(R.id.iv_fl_door);
		windowFL = (ImageView) layCarDoor.findViewById(R.id.iv_fl_window);
		doorFR = (ImageView) layCarDoor.findViewById(R.id.iv_fr_door);
		windowFR = (ImageView) layCarDoor.findViewById(R.id.iv_fr_window);
		doorRL = (ImageView) layCarDoor.findViewById(R.id.iv_rl_door);
		windowRL = (ImageView) layCarDoor.findViewById(R.id.iv_rl_window);
		doorRR = (ImageView) layCarDoor.findViewById(R.id.iv_rr_door);
		windowRR = (ImageView) layCarDoor.findViewById(R.id.iv_rr_window);
		doorFront = (ImageView) layCarDoor.findViewById(R.id.iv_door_front);
		doorBack = (ImageView) layCarDoor.findViewById(R.id.iv_door_back);
		
		lightLeft = (ImageView) layCarDoor.findViewById(R.id.iv_car_leftlight);
		lightRight = (ImageView) layCarDoor.findViewById(R.id.iv_car_rightlight);
		lightNearby = (ImageView) layCarDoor.findViewById(R.id.iv_car_nearlight);
		lightStop = (ImageView) layCarDoor.findViewById(R.id.iv_car_stoplight);
		lightWide = (ImageView) layCarDoor.findViewById(R.id.iv_car_widelight);
		lightFar = (ImageView) layCarDoor.findViewById(R.id.iv_car_farlight);
		lightDoubleFlash = (ImageView) layCarDoor.findViewById(R.id.iv_car_doublelight);
		
		handBrake = viewFinder(R.id.iv_handbrake);
		limitSpeed = viewFinder(R.id.iv_limit_speed);
		
	}
	
	protected void jump2App() {
		String PKG = "com.hiworld.mycar.t11";
		String CLS = "com.hiworld.canbus.activity.WelcomeActivity";
		Intent ii = new Intent(PKG);
		ii.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_NEW_TASK);
		ii.setClassName(PKG, CLS);
		try {
			MyApp.getInstance().startActivity(ii);
		} catch (Exception e) {
		}
	}

	@Override
	void funcClick(View v) { }

	@Override
	void in() {
		viewShow(curGear, 1);
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_DOOR_LIGHT].addNotify(notifyDOOR, 1);
//		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_FUEL_COST].addNotify(notifyFUEL, 1);
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_SPEED].addNotify(notifySPEED, 1);
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_LIMIT_SPEED].addNotify(notifyLimiteSPEED, 1);
	}

	@Override
	void out() {
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_DOOR_LIGHT].removeNotify(notifyDOOR);
//		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_FUEL_COST].removeNotify(notifyFUEL);
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_SPEED].removeNotify(notifySPEED);
		DataCanbus.NOTIFY_EVENTS[FinalOBD.U_LIMIT_SPEED].removeNotify(notifyLimiteSPEED);
	}

	private IUiNotify notifyDOOR = new IUiNotify() {

		@Override
		public void onNotify(int updateCode, int[] ints) {
			onDoor();
		}
	};

	private IUiNotify notifySPEED = new IUiNotify() {

		@Override
		public void onNotify(int updateCode, int[] ints) {
			onSpeed();
		}
	}; 
	private IUiNotify notifyLimiteSPEED = new IUiNotify() {
		
		@Override
		public void onNotify(int updateCode, int[] ints) {
			onLimitSpeed();
		}
	}; 

	protected void onSpeed() { 
		engineSpeed.setText(CarPcInfo.getInstance().getM_iEngineSpeed() + "");
		int val = (int) CarPcInfo.getInstance().getM_fInstantSpeed();
		
		curSpeed.setText(val + "");
		viewShow(icoWater , CarPcInfo.getInstance().getM_iCooltemp() > 110 ?  1: 0); 
	}


	
	protected void onLimitSpeed() { 
		int id = 0;
		switch (DataCanbus.sLimiteSpeed) {
		case 30:  id = R.drawable.ic_limit_30;break;
		case 35: id = R.drawable.ic_limit_35;break;
		case 40: id = R.drawable.ic_limit_40;break;
		case 45: id = R.drawable.ic_limit_45;break;
		case 50: id = R.drawable.ic_limit_50;break;
		case 60: id = R.drawable.ic_limit_60;break;
		case 70: id = R.drawable.ic_limit_70;break;
		case 80: id = R.drawable.ic_limit_80;break;
		case 90: id = R.drawable.ic_limit_90;break;
		case 100: id = R.drawable.ic_limit_100;break;
		case 110: id = R.drawable.ic_limit_110;break;
		case 120: id = R.drawable.ic_limit_120;break;
		default:
			break;
		}
		
		if (curSpeed != null) {
			if (id != 0) {
				int val = (int) CarPcInfo.getInstance().getM_fInstantSpeed();
				if (val > DataCanbus.sLimiteSpeed) {
					curSpeed.setTextColor(Color.RED);
				} else {
					curSpeed.setTextColor(Color.WHITE);
				}
			} else {
				curSpeed.setTextColor(Color.WHITE);
			}

			tvlimitSpeed.setText("" + DataCanbus.sLimiteSpeed);

			limitSpeed.setImageResource(id);
		}
	}

	protected void onDoor() {
		viewShow(doorFront, CarPcInfo.getInstance().getM_iHood());
		viewSelect(doorFL, CarPcInfo.getInstance().getM_iLFDoor()); 
		viewSelect(doorFR, CarPcInfo.getInstance().getM_iRFDoor()); 
		viewSelect(doorRL , CarPcInfo.getInstance().getM_iLRDoor()); 
		viewSelect(doorRR , CarPcInfo.getInstance().getM_iRRDoor()); 
		viewShow(doorBack , CarPcInfo.getInstance().getM_iTailBox()); 
		
		
		viewShow(windowFL , CarPcInfo.getInstance().getM_iLFWindow()); 
		viewShow(windowFR , CarPcInfo.getInstance().getM_iRFWindow()); 
		viewShow(windowRL , CarPcInfo.getInstance().getM_iLRWindow()); 
		viewShow(windowRR , CarPcInfo.getInstance().getM_iRRWindow()); 

		LogsUtils.i(" windowFL " + windowFL +" "+  CarPcInfo.getInstance().getM_iLFWindow());
		
		viewShow(handBrake , CarPcInfo.getInstance().getM_iHanderBrake()); 

		viewShow(icoiLL , CarPcInfo.getInstance().getM_iILL()); 
		setBg(CarPcInfo.getInstance().getM_iILL()); 
		viewShow(icoBattery, CarPcInfo.getInstance().getM_iACC() != 0 &&   CarPcInfo.getInstance().getM_iEngineSpeed() == 0  ? 1 : 0); 
		viewShow(lightStop , CarPcInfo.getInstance().getM_iStopLight()); 
		viewShow(lightWide , CarPcInfo.getInstance().getM_iWideLight()); 
		viewShow(lightFar , CarPcInfo.getInstance().getM_iFarLight()); 
		viewShow(icoFar , CarPcInfo.getInstance().getM_iFarLight()); 
		viewShow(lightNearby , CarPcInfo.getInstance().getM_iNearLight()); 
	
		viewShow(lightDoubleFlash , CarPcInfo.getInstance().getM_iDoubleLight()); 
		viewShow(icoDoubleFlash, CarPcInfo.getInstance().getM_iDoubleLight()); 
		

		viewShow(lightLeft , CarPcInfo.getInstance().getM_iLeftTrunLight()); 
		viewShow(icoLeft , CarPcInfo.getInstance().getM_iLeftTrunLight()); 
		viewShow(lightRight, CarPcInfo.getInstance().getM_iRightTrunLight()); 
		viewShow(icoRight , CarPcInfo.getInstance().getM_iRightTrunLight()); 
		
		int gear = CarPcInfo.getInstance().getM_iGear();
//		if(gear> 0 && gear <= 5) {
//			curGear.setImageResource(R.drawable.d_gear_state);
//			curGear.setImageLevel(gear);
//		}else {
//			curGear.setImageResource(0);
//		}
 
		int i  = 0; 
		switch (gear) {
		case 1: i = R.drawable.ic_main_gearp; break;
		case 2: i = R.drawable.ic_main_gearn; break;
		case 3: i = R.drawable.ic_main_gearr; break;
		case 4: i = R.drawable.ic_main_geard; break;
		case 5: i = R.drawable.ic_main_gears; break;
 		default:
			break;
		}
		curGear.setImageResource(i);
		 
		viewShow(icoSafeLeft , CarPcInfo.getInstance().getM_iLFSafebelt() == 0 ? 1: 0); 
		
	}


	private int ill;
	private void setBg(int val) {
		if(ill != val) {
			ill = 	val; 
			lay_main.setBackgroundResource(ill == 1 ? R.drawable.ic_bk_purple : R.drawable.ic_bk_blue);
		}
	}

	private void viewShow(View v, int val) {
		if (v != null)
			v.setVisibility(val == 1 ? View.VISIBLE : View.INVISIBLE);
	}
	
	private void viewSelect(View v, int val) {
		if (v != null)
			v.setSelected(val == 1 ? true : false);
	}
}
