package com.hiworld.fragment.golf;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.ex.canbus.DataCanbus;
import com.hiworld.adapter.Golf7CarInfo;
import com.hiworld.carcomputer.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tools.IUiNotify;
import tools.Utils;

public class Golf7FromAddOilFragment extends BaseFragment {

	private TextView tv_AverageFuel, tv_cruisingDistance, tv_travelDistance, tv_TravelTime, tv_AverageSpeed;
	FragmentCallBack fragmentCallBack = null;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
//		fragmentCallBack = (ActivityCarPC) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.golf7_fromaddoil_fragment, container, false);
		initView(view);
//		setGolf7CarInfo(Golf7CarInfo.getInstance());
		return view;
	}

	private void initView(View view) {
		tv_AverageFuel = (TextView) view.findViewById(R.id.tv_trip3_averageFuel);
		tv_cruisingDistance = (TextView) view.findViewById(R.id.tv_trip3_cruisingDistance);
		tv_travelDistance = (TextView) view.findViewById(R.id.tv_trip3_travelDistance);
		tv_TravelTime = (TextView) view.findViewById(R.id.tv_trip3_travelTime);
		tv_AverageSpeed = (TextView) view.findViewById(R.id.tv_trip3_averagespeed);
	}

//	protected void setGolf7CarInfo(Golf7CarInfo golf7CarInfo) {
//		if (golf7CarInfo.getM_trip3AverageFuelHigh() != 0xff || golf7CarInfo.getM_trip3AverageFuelLow() != 0xff) {
//			float averageFuel = (float) ((golf7CarInfo.getM_trip3AverageFuelHigh() * 256
//					+ golf7CarInfo.getM_trip3AverageFuelLow()) * 0.1);
//			tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) + averageFuel + "L/Km");
//		}
//		if (golf7CarInfo.getM_trip3cruisingDistanceHigh() != 0xff
//				|| golf7CarInfo.getM_trip3cruisingDistanceLow() != 0xff) {
//			int cruisingDistance = golf7CarInfo.getM_trip3cruisingDistanceHigh() * 256
//					+ golf7CarInfo.getM_trip3cruisingDistanceLow();
//			tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + cruisingDistance + "Km");
//		}
//		int travelDistance = (int) ((golf7CarInfo.getM_trip3travelDistanceHigh() * 256
//				+ golf7CarInfo.getM_trip3travelDistanceLow()) * 0.1);
//		tv_travelDistance.setText(activity.getString(R.string.golf7_traveldistance) + travelDistance + "Km");
//		int high = golf7CarInfo.getM_trip3TravelTimeHigh();
//		int low = golf7CarInfo.getM_trip3TravelTimeLow();
//
//		int minute = (high * 16 + low) % 60;
//		int hour = high * 4 + (high * 16 + low) / 60;
//		if (minute < 10) {
//			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":0" + minute);
//		} else {
//			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":" + minute);
//		}
//		tv_AverageSpeed.setText(
//				activity.getString(R.string.golf7_averagespeed) + golf7CarInfo.getM_trip3AverageSpeed() + "Km/h");
//	}

	private int[] nIDs = new int[] {
		ConstGolf.U_10_BY_REFUELLING,
		ConstGolf.U_11_BY_REFUELLING,
		ConstGolf.U_12_BY_REFUELLING,
		ConstGolf.U_13_BY_REFUELLING,
		ConstGolf.U_14_BY_REFUELLING,
		 
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
			int val = DataCanbus.DATA[updateCode];
			switch (updateCode) {
			case ConstGolf.U_10_BY_REFUELLING:{
				if (val == 0xFFFF) {
					tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) + "----");
				}else {
					tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) +" "+ (val/ 10.0f) + " L/100Km");
				}
				break;
			}
			case ConstGolf.U_11_BY_REFUELLING:{
				tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) +" "+ val + " Km");
				break;
			}
			case ConstGolf.U_12_BY_REFUELLING:{
				tv_travelDistance.setText(activity.getString(R.string.golf7_traveldistance) +" "+ (val/10.0f) + " Km");
				break;
			}
			case ConstGolf.U_13_BY_REFUELLING:{
				tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) +" "+ Utils.getTimeFromRealVal(val));
				break;
			}
			case ConstGolf.U_14_BY_REFUELLING:{
				tv_AverageSpeed.setText(
						activity.getString(R.string.golf7_averagespeed) + " "+ val + " Km/h");
				break;
			}
			default:
				break;
			}
		
		}
	};

}
