package com.hiworld.fragment.honda;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.hiworld.adapter.HondaCarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.HondaParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HondaCurrentFragment extends Fragment{
	
	FragmentCallBack fragmentCallBack = null;
	private ProgressBar pb_instantFuel;//瞬时油耗
	private ProgressBar pb_nowAverageFuel,pb_hisAverageFuel;//本次平均油耗、历史平均油耗
	private String instantFuelUnit;//本次驾驶瞬时油耗单位
	private String drivingMileageUnit;//续航里程单位
	private String thisAverageFuelUnit;//本次平均油耗单位
	private TextView tv_drivingMileage;//续航里程
	private TextView tv_volume,tv_volumenow,tv_volumehis;//量程
	private TextView tv_instantFuelUnit,tv_averageFuelUnit;//瞬时油耗和平均油耗单位
	private TextView tv_instantFuel,tv_nowAverageFuel,tv_hisAverageFuel;
	private int fuelRange;//油耗的量程
	private Context context;
	
	public HondaCurrentFragment(Context context) {
		super();
		this.context = context;
		
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		fragmentCallBack = (ActivityCarPC)activity;
		if (mHandler != null) {
			HondaParseData.getInstance().setThisDrive(mHandler);
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.Honda_MESSAGE_SEND_INFO:
				//Log.e("info", "=========接收到Handler传递过来的消息");
				HondaCarInfo carInfo = (HondaCarInfo)msg.obj;
				if (carInfo != null) {
					setHondaCarInfo(carInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.honda_current_fragment, container,false);
		initView(view);
		setHondaCarInfo(HondaCarInfo.getInstance());
		return view;
	}

	private void initView(View view) {
		
		pb_instantFuel = (ProgressBar) view.findViewById(R.id.pb_thishonda_large);
		pb_nowAverageFuel = (ProgressBar) view.findViewById(R.id.pb_honda_nowsmall);
		pb_hisAverageFuel = (ProgressBar) view.findViewById(R.id.pb_honda_historysmall);
		
		
		tv_volume = (TextView) view.findViewById(R.id.tv_volum);
		tv_volumenow = (TextView) view.findViewById(R.id.tv_volum1);
		tv_volumehis = (TextView) view.findViewById(R.id.tv_volum2);
		tv_instantFuelUnit = (TextView) view.findViewById(R.id.tv_instantFuelUnit);
		tv_averageFuelUnit = (TextView) view.findViewById(R.id.tv_averageFuelUnit);
		
		tv_instantFuel = (TextView) view.findViewById(R.id.tv_instantFuel);
		tv_nowAverageFuel = (TextView) view.findViewById(R.id.tv_nowFuel);
		tv_hisAverageFuel = (TextView) view.findViewById(R.id.tv_historyFuel);
		
		tv_drivingMileage = (TextView) view.findViewById(R.id.tv_drivingDistance);
	}

	protected void setHondaCarInfo(HondaCarInfo carInfo) {
		
		if (carInfo.getM_drivingMileageUnit() == 0x00) {
			drivingMileageUnit = "km";
		}else {
			drivingMileageUnit = "mile";
		}
		if (carInfo.getM_InstantFuelUnit() == 0x00) {
			instantFuelUnit = "mpg";
		}else if (carInfo.getM_InstantFuelUnit() == 0x01) {
			instantFuelUnit = "km/L";
		}else {
			instantFuelUnit = "L/100km";
		}
		if (carInfo.getM_averageFuelUnit() == 0x00) {
			thisAverageFuelUnit = "mpg";
		}else if (carInfo.getM_averageFuelUnit() == 0x01) {
			thisAverageFuelUnit = "Km/L";
		}else {
			thisAverageFuelUnit = "L/Km";
		}
		int drivingMileage = carInfo.getM_drivingMileageHigh()*256 + carInfo.getM_drivingMileageLow();
		tv_drivingMileage.setText(context.getString(R.string.drivingmileage)+drivingMileage + drivingMileageUnit);
		tv_averageFuelUnit.setText(thisAverageFuelUnit);
		tv_instantFuelUnit.setText(instantFuelUnit);
		fuelRange = carInfo.getM_fuelRange();
		int max = 0;
		switch (fuelRange) {
		case 0:
			max = 60;
			break;
		case 1:
			max = 10;
			break;
		case 2:
			max = 12;
			break;
		case 3:
			max = 20;
			break;
		case 4:
			max = 30;
			break;
		case 5:
			max = 40;
			break;
		case 6:
			max = 50;
			break;
		case 7:
			max = 70;
			break;
		case 8:
			max = 80;
			break;
		case 9:
			max = 90;
			break;
		case 10:
			max = 100;
			break;
		default:
			break;
		}
		tv_volume.setText(max+"");
		tv_volumenow.setText(max+"");
		tv_volumehis.setText(max+"");
		pb_instantFuel.setMax(max);
		pb_nowAverageFuel.setMax(max);
		pb_hisAverageFuel.setMax(max);
		int instantFuel = carInfo.getM_nowInstantFuel();
		pb_instantFuel.setProgress(instantFuel);
		tv_instantFuel.setText(instantFuel+"");
		
		float nowAverageFuel = (float) ((carInfo.getM_nowAverageFuelHigh()*256 + carInfo.getM_nowAverageFuelLow())*0.1);
		pb_nowAverageFuel.setProgress((int)nowAverageFuel);
		tv_nowAverageFuel.setText(nowAverageFuel+"");
		
		float hisAverageFuel = (float) ((carInfo.getM_hisAverageFuelHigh()*256 + carInfo.getM_hisAverageFuelLow())*0.1);
		pb_hisAverageFuel.setProgress((int)hisAverageFuel);
		tv_hisAverageFuel.setText(hisAverageFuel+"");
		
	}
}
