package com.hiworld.fragment.honda;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.hiworld.adapter.HondaCarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.HondaParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HondaHistoryFragment extends Fragment{
	
	FragmentCallBack fragmentCallBack = null;
	private TextView tv_tripADistance,tv_trip1Distance,tv_trip2Distance,tv_trip3Distance;
	private ProgressBar pb_tripaFuel,pb_trip1Fuel,pb_trip2Fuel,pb_trip3Fuel;
	private TextView tv_tripAFuel,tv_trip1Fuel,tv_trip2Fuel,tv_trip3Fuel;
	private TextView tv_tripaFuelUnit,tv_trip1FuelUint,tv_trip2FuelUnit,tv_trip3FuelUnit;
	private TextView tv_fuelRage;//量程
	private String tripDistanceUnit;//里程单位
	private String averageFuelUnit;//平均油耗单位
	private String drivingMileageUnit;//续航里程单位
	private TextView tv_tripaDisUnit,tv_trip1DisUnit,tv_trip2DisUnit,tv_trip3DisUnit;
	private TextView tv_drivingMileage;
	private Button btn_deleteHistory;
	
	private AlertDialog.Builder mBuilder;
	
	private Activity activity;
	//ziai19950209
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC)activity;
		if (mHandler != null) {
			HondaParseData.getInstance().setTripAHandler(mHandler);
		}
		
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.Honda_MESSAGE_SEND_INFO:
				HondaCarInfo carInfo = (HondaCarInfo)msg.obj;
				if (carInfo != null) {
					setHondaHisCarInfo(carInfo);
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
		
		View view = inflater.inflate(R.layout.honda_history_fragment, container,false);
		initView(view);
		setHondaHisCarInfo(HondaCarInfo.getInstance());
		
		return view;
	}

	private void initView(View view) {
		tv_drivingMileage = (TextView) view.findViewById(R.id.tv_his_drivingDis);
		tv_fuelRage = (TextView) view.findViewById(R.id.tv_tripa_volum);
		
		tv_tripADistance = (TextView) view.findViewById(R.id.tv_honda_tripaDis);
		tv_trip1Distance = (TextView) view.findViewById(R.id.tv_honda_trip1Dis);
		tv_trip2Distance = (TextView) view.findViewById(R.id.tv_honda_trip2Dis);
		tv_trip3Distance = (TextView) view.findViewById(R.id.tv_honda_trip3Dis);
		
		tv_tripAFuel = (TextView) view.findViewById(R.id.tv_honda_tripa_fuel);
		tv_trip1Fuel = (TextView) view.findViewById(R.id.tv_honda_trip1_fuel);
		tv_trip2Fuel = (TextView) view.findViewById(R.id.tv_honda_trip2_fuel);
		tv_trip3Fuel = (TextView) view.findViewById(R.id.tv_honda_trip3_fuel);
		
		tv_tripaFuelUnit = (TextView) view.findViewById(R.id.tv_honda_tripa_unit);
		tv_trip1FuelUint = (TextView) view.findViewById(R.id.tv_honda_trip1_unit);
		tv_trip2FuelUnit = (TextView) view.findViewById(R.id.tv_honda_trip2_unit);
		tv_trip3FuelUnit = (TextView) view.findViewById(R.id.tv_honda_trip3_unit);
		
		tv_tripaDisUnit = (TextView) view.findViewById(R.id.tv_honda_tripaDis_unit);
		tv_trip1DisUnit = (TextView) view.findViewById(R.id.tv_honda_trip1Dis_unit);
		tv_trip2DisUnit = (TextView) view.findViewById(R.id.tv_honda_trip2Dis_unit);
		tv_trip3DisUnit = (TextView) view.findViewById(R.id.tv_honda_trip3Dis_unit);
		
		pb_tripaFuel = (ProgressBar) view.findViewById(R.id.pb_honda_tripa_fuel);
		pb_trip1Fuel = (ProgressBar) view.findViewById(R.id.pb_honda_trip1_fuel);
		pb_trip2Fuel = (ProgressBar) view.findViewById(R.id.pb_honda_trip2_fuel);
		pb_trip3Fuel = (ProgressBar) view.findViewById(R.id.pb_honda_trip3_fuel);
		btn_deleteHistory = (Button) view.findViewById(R.id.btn_deletehistory);
		
		mBuilder = new AlertDialog.Builder(activity);
		mBuilder.setTitle("删除数据");
		mBuilder.setMessage("是否要删除历史数据？");
		mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.callbackCMD(new int[]{0x02,0xF2,0x06,0xFF}, 4);
				}
			}
		});
		mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				dialog.dismiss();
			}
		});
		
		btn_deleteHistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				mBuilder.show();
			}
		});
	}
	
	protected void setHondaHisCarInfo(HondaCarInfo carInfo) {
		
		if (carInfo.getM_drivingMileageUnit() == 0x00) {
			drivingMileageUnit = "km";
		}else {
			drivingMileageUnit = "mile";
		}
		if (carInfo.getM_tripAverageFuelUnit() == 0x00) {
			averageFuelUnit = "mpg";
		}else if (carInfo.getM_tripAverageFuelUnit() == 0x01) {
			averageFuelUnit = "km/L";
		}else {
			averageFuelUnit = "L/100km";
		}
		
		if (carInfo.getM_tripDistanceUnit() == 0x00) {
			tripDistanceUnit = "Km";
		}else {
			tripDistanceUnit = "mile";
		}
		tv_tripaFuelUnit.setText(averageFuelUnit);
		tv_trip1FuelUint.setText(averageFuelUnit);
		tv_trip2FuelUnit.setText(averageFuelUnit);
		tv_trip3FuelUnit.setText(averageFuelUnit);
		
		tv_tripaDisUnit.setText(tripDistanceUnit);
		tv_trip1DisUnit.setText(tripDistanceUnit);
		tv_trip2DisUnit.setText(tripDistanceUnit);
		tv_trip3DisUnit.setText(tripDistanceUnit);
		int fuelRange = carInfo.getM_fuelRange();
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
		tv_fuelRage.setText(max+"");
		pb_tripaFuel.setMax(max);
		pb_trip1Fuel.setMax(max);
		pb_trip2Fuel.setMax(max);
		pb_trip3Fuel.setMax(max);
		
		float tripADistance = (float) ((carInfo.getM_tripAdistanceHigh()*256*256 + carInfo.getM_tripAdistanceMedium()*256 + carInfo.getM_tripAdistanceLow())*0.1);
		tv_tripADistance.setText(tripADistance +"");
		float tripAFuel = (float) ((carInfo.getM_tripAaveFuelHigh()*256 + carInfo.getM_tripAaveFuelLow())*0.1);
		tv_tripAFuel.setText(tripAFuel+"");
		pb_tripaFuel.setProgress((int)tripAFuel);
		float trip1Dis = (float) ((carInfo.getM_trip1distanceHigh()*256*256 + carInfo.getM_trip1distanceMedium()*256 + carInfo.getM_trip1distanceLow())*0.1);
		tv_trip1Distance.setText(trip1Dis+"");
		float trip1Fuel = (float) ((carInfo.getM_trip1aveFuelHigh()*256 + carInfo.getM_trip1aveFuelLow())*0.1);
		tv_trip1Fuel.setText(trip1Fuel+"");
		pb_trip1Fuel.setProgress((int)trip1Fuel);
		float trip2Dis = (float) ((carInfo.getM_trip2distanceHigh()*256*256 + carInfo.getM_trip2distanceMedium()*256 + carInfo.getM_trip2distanceLow())*0.1);
		tv_trip2Distance.setText(trip2Dis+"");
		
		float trip2Fuel = (float) ((carInfo.getM_trip2aveFuelHigh()*256 + carInfo.getM_trip2aveFuelLow())*0.1);
		tv_trip2Fuel.setText(trip2Fuel+"");
		pb_trip2Fuel.setProgress((int)trip2Fuel);
		
		float trip3Dis = (float) ((carInfo.getM_trip3distanceHigh()*256*256 + carInfo.getM_trip3distanceMedium()*256 + carInfo.getM_trip3distanceLow())*0.1);
		tv_trip3Distance.setText(trip3Dis+"");
		
		float trip3Fuel = (float) ((carInfo.getM_trip3aveFuelHigh()*256 + carInfo.getM_trip3aveFuelLow())*0.1);
		tv_trip3Fuel.setText(trip3Fuel+"");
		pb_trip3Fuel.setProgress((int)trip3Fuel);
		
		int drivingMileage = carInfo.getM_drivingMileageHigh()*256 + carInfo.getM_drivingMileageLow();
		tv_drivingMileage.setText(activity.getString(R.string.drivingmileage)+drivingMileage + drivingMileageUnit);
	}
}
