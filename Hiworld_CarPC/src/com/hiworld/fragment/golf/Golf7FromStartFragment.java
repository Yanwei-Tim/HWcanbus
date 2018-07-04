package com.hiworld.fragment.golf;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.ex.canbus.DataCanbus;
import com.ex.hiworld.aidl.task.Task_HandlerCanbus;
import com.hiworld.adapter.Golf7CarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.carcomputer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import tools.IUiNotify;
import tools.Utils;

public class Golf7FromStartFragment extends BaseFragment {

	private TextView tv_AverageFuel, tv_cruisingDistance, tv_travelDistance, tv_TravelTime, tv_AverageSpeed;
	private ImageButton ibtn_ClearZero;
	FragmentCallBack fragmentCallBack = null;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC) activity;
//		if (mHandler != null) {
//			Golf7ParseData.getInstance().setFromStartHandler(mHandler);
//		} 
	}
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.golf7_fromstart_fragment, container, false);
		initView(view);
//		setGolf7CarInfo(Golf7CarInfo.getInstance());
		return view;
	}

	private void initView(View view) {
		tv_AverageFuel = (TextView) view.findViewById(R.id.tv_trip1_averageFuel);
		tv_cruisingDistance = (TextView) view.findViewById(R.id.tv_trip1_cruisingDistance);
		tv_travelDistance = (TextView) view.findViewById(R.id.tv_trip1_travelDistance);
		tv_TravelTime = (TextView) view.findViewById(R.id.tv_trip1_travelTime);
		tv_AverageSpeed = (TextView) view.findViewById(R.id.tv_trip1_averagespeed);
		ibtn_ClearZero = (ImageButton) view.findViewById(R.id.ib_clearstart_golf7);
		ibtn_ClearZero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Task_HandlerCanbus.sendCmd(ConstGolf.C_CARSET, new int[] { 0x7b, 0x0B, 0});
			}
		});
	}

	protected void setGolf7CarInfo(Golf7CarInfo golf7CarInfo) {
		if (golf7CarInfo.getM_trip1AverageFuelHigh() != 0xff || golf7CarInfo.getM_trip1AverageFuelLow() != 0xff) {
			float averageFuel = (float) ((golf7CarInfo.getM_trip1AverageFuelHigh() * 256
					+ golf7CarInfo.getM_trip1AverageFuelLow()) * 0.1);
			tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) + averageFuel + "L/Km");
		}
		if (golf7CarInfo.getM_trip1cruisingDistanceHigh() != 0xff
				|| golf7CarInfo.getM_trip1cruisingDistanceLow() != 0xff) {
			int cruisingDistance = golf7CarInfo.getM_trip1cruisingDistanceHigh() * 256
					+ golf7CarInfo.getM_trip1cruisingDistanceLow();
			tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + cruisingDistance + "Km");
		}
		int travelDistance = (int) ((golf7CarInfo.getM_trip1travelDistanceHigh() * 256
				+ golf7CarInfo.getM_trip1travelDistanceLow()) * 0.1);
		tv_travelDistance.setText(activity.getString(R.string.golf7_traveldistance) + travelDistance + "Km");
		int high = golf7CarInfo.getM_trip1TravelTimeHigh();
		int low = golf7CarInfo.getM_trip1TravelTimeLow();

		int minute = (high * 16 + low) % 60;
		int hour = high * 4 + (high * 16 + low) / 60;
		if (minute < 10) {
			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":0" + minute);
		} else {
			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":" + minute);
		}
		tv_AverageSpeed.setText(
				activity.getString(R.string.golf7_averagespeed) + golf7CarInfo.getM_trip1AverageSpeed() + "Km/h");
	}


	private int[] nIDs = new int[] {
		ConstGolf.U_0_BY_START,
		ConstGolf.U_1_BY_START,
		ConstGolf.U_2_BY_START,
		ConstGolf.U_3_BY_START,
		ConstGolf.U_4_BY_START,
		 
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
			case ConstGolf.U_0_BY_START: {
				if (val == 0xFFFF)
					tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) +"----");
				else
					tv_AverageFuel
							.setText(activity.getString(R.string.golf7_averagefuel) + " " + (val / 10.0f) + " L/100Km");
				break;
			}
			case ConstGolf.U_1_BY_START: {
				if (val == 0xFFFF)
					tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + "----");
				else
					tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + " " + val + " Km");
				break;
			}
			case ConstGolf.U_2_BY_START: {
				tv_travelDistance
						.setText(activity.getString(R.string.golf7_traveldistance) + " " + (val / 10.f) + " Km");
				break;
			}
			case ConstGolf.U_3_BY_START: {
				tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + " " + Utils.getTimeFromRealVal(val));
				break;
			}
			case ConstGolf.U_4_BY_START: {
				tv_AverageSpeed.setText(activity.getString(R.string.golf7_averagespeed) + " " + val + " Km/h");
			}
			}
		}
	};
	
}
