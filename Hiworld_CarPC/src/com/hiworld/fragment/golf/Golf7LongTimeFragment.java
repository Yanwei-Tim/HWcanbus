package com.hiworld.fragment.golf;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.ex.canbus.DataCanbus;
import com.ex.hiworld.aidl.task.Task_HandlerCanbus;
import com.hiworld.adapter.Golf7CarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.Golf7ParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import tools.IUiNotify;
import tools.LogsUtils;
import tools.Utils;

public class Golf7LongTimeFragment extends BaseFragment {

	private TextView tv_AverageFuel, tv_cruisingDistance, tv_travelDistance, tv_TravelTime, tv_AverageSpeed;
	private ImageButton ibtn_ClearZero;
	FragmentCallBack fragmentCallBack = null;
	private Activity activity;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		this.activity = activity;
//		fragmentCallBack = (ActivityCarPC) activity;
//		if (mHandler != null) {
//			Golf7ParseData.getInstance().setLongTimeHandler(mHandler);
//		} 
	}

//	@SuppressLint("HandlerLeak")
//	private Handler mHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case Constant.GOLF7_MESSAGE＿SEND_INFO:
//				Golf7CarInfo golf7CarInfo = (Golf7CarInfo) msg.obj;
//				if (golf7CarInfo != null) {
//					setGolf7CarInfo(golf7CarInfo);
//				}
//				break;
//			default:
//				break;
//			}
//		}
//	};
 
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
				Task_HandlerCanbus.sendCmd(ConstGolf.C_CARSET, new int[] { 0x7b, 0x0c, 0});
			}
		});
	}

//	protected void setGolf7CarInfo(Golf7CarInfo golf7CarInfo) {
//
//		if (golf7CarInfo.getM_trip2AverageFuelHigh() != 0xff || golf7CarInfo.getM_trip2AverageFuelLow() != 0xff) {
//			float averageFuel = (float) ((golf7CarInfo.getM_trip2AverageFuelHigh() * 256
//					+ golf7CarInfo.getM_trip2AverageFuelLow()) * 0.1);
//			tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) + averageFuel + "L/Km");
//		}
//		if (golf7CarInfo.getM_trip2cruisingDistanceHigh() != 0xff
//				|| golf7CarInfo.getM_trip2cruisingDistanceLow() != 0xff) {
//			int cruisingDistance = golf7CarInfo.getM_trip2cruisingDistanceHigh() * 256
//					+ golf7CarInfo.getM_trip2cruisingDistanceLow();
//			tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + cruisingDistance + "Km");
//		}
//		int travelDistance = (int) ((golf7CarInfo.getM_trip2travelDistanceHigh() * 256
//				+ golf7CarInfo.getM_trip2travelDistanceLow()) * 0.1);
//		tv_travelDistance.setText(activity.getString(R.string.golf7_traveldistance) + travelDistance + "Km");
//		int high = golf7CarInfo.getM_trip2TravelTimeHigh();
//		int low = golf7CarInfo.getM_trip2TravelTimeLow();
//
//		int minute = (high * 16 + low) % 60;
//		int hour = high * 4 + (high * 16 + low) / 60;
//		if (minute < 10) {
//			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":0" + minute);
//		} else {
//			tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + hour + ":" + minute);
//		}
//		tv_AverageSpeed.setText(
//				activity.getString(R.string.golf7_averagespeed) + golf7CarInfo.getM_trip2AverageSpeed() + "Km/h");
//	}


	private int[] nIDs = new int[] {
		ConstGolf.U_5_BY_LONG_TERM,
		ConstGolf.U_6_BY_LONG_TERM,
		ConstGolf.U_7_BY_LONG_TERM,
		ConstGolf.U_8_BY_LONG_TERM,
		ConstGolf.U_9_BY_LONG_TERM,
		 
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
			case ConstGolf.U_5_BY_LONG_TERM: {
				if (val == 0xFFFF)
					tv_AverageFuel.setText(activity.getString(R.string.golf7_averagefuel) +"----");
				else
					tv_AverageFuel
							.setText(activity.getString(R.string.golf7_averagefuel) + " " + (val / 10.0f) + " L/100Km");
				break;
			}
			case ConstGolf.U_6_BY_LONG_TERM: {
				if (val == 0xFFFF)
					tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) +"----");
				else
					tv_cruisingDistance.setText(activity.getString(R.string.golf7_cruisingdistance) + " " + val + " Km");
				break;
			}
			case ConstGolf.U_7_BY_LONG_TERM: {
				tv_travelDistance
						.setText(activity.getString(R.string.golf7_traveldistance) + " " + (val / 10.f) + " Km");
				break;
			}
			case ConstGolf.U_8_BY_LONG_TERM: {
				tv_TravelTime.setText(activity.getString(R.string.golf7_traveltime) + " " + Utils.getTimeFromRealVal(val));
				break;
			}
			case ConstGolf.U_9_BY_LONG_TERM: {
				tv_AverageSpeed.setText(activity.getString(R.string.golf7_averagespeed) + " " + val + " Km/h");
			}
			}
		}
	};
}
