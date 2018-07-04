package com.hiworld.fragment.saic;


import com.hiworld.adapter.SaicCarInfo;
import com.hiworld.canbus.parsedata.SaicParseData;
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
import android.view.ViewGroup;
import android.widget.TextView;


public class SaicFuelFragment extends Fragment{

	private Activity activity;
	private TextView tv_trip1_averageFuel1,tv_trip1_averageFuel2,tv_trip1_averageFuel3,tv_trip1_mileage;
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		if (mHandler != null) {
			SaicParseData.getInstance().setFuelHadler(mHandler);
		}
	}

	
	
	@Override
	public void onResume() {
		
		super.onResume();
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.saic_fuel_fragment, container, false);
		initView(view);
		setInfo(SaicCarInfo.getInstance());
		return view;
	}

	private void initView(View view) {
		
		tv_trip1_averageFuel1 = (TextView)view.findViewById(R.id.tv_trip1_averageFuel1);
		tv_trip1_averageFuel2 = (TextView)view.findViewById(R.id.tv_trip1_averageFuel2);
		tv_trip1_averageFuel3 = (TextView)view.findViewById(R.id.tv_trip1_averageFuel3);
		tv_trip1_mileage = (TextView)view.findViewById(R.id.tv_trip1_mileage);
	}


	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.HANDLER_MESSAGE_FUEL:
				SaicCarInfo stInfo = (SaicCarInfo) msg.obj;
				if (stInfo != null) {
					setInfo(stInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};

	protected void setInfo(SaicCarInfo instance) {
		
		float ff = 0;
		String str = null;
		if (instance.getM_iUnitFuel() == 1) {
			str = "Mpg";
		}else if (instance.getM_iUnitFuel() == 2) {
			str = "Km/L";
		}else {
			str = "L/100Km";
		}
		int temp = instance.getM_iAvgFuel1();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			tv_trip1_averageFuel1.setText(activity.getString(R.string.saic_averagefuel1)+ff+str);
		} else {
			tv_trip1_averageFuel1.setText(activity.getString(R.string.saic_averagefuel1)+"--"+str);
		}
		temp = instance.getM_iAvgFuel2();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			tv_trip1_averageFuel2.setText(activity.getString(R.string.saic_averagefuel2)+ff+str);
		} else {
			tv_trip1_averageFuel2.setText(activity.getString(R.string.saic_averagefuel2)+"--"+str);
		}
		temp = instance.getM_iAvgFuel3();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			tv_trip1_averageFuel3.setText(activity.getString(R.string.saic_averagefuel3)+ff+str);
		} else {
			tv_trip1_averageFuel3.setText(activity.getString(R.string.saic_averagefuel3)+"--"+str);
		}
		
		if (instance.getM_iUnitMileage() == 1) {
			str = "Mile";
		} else {
			str = "Km";
		}
		temp = instance.getM_iMileage();
		if (temp != 0xffffff) {
			ff = (float) (temp*0.1);
			tv_trip1_mileage.setText(activity.getString(R.string.accum_mileage)+ff+str);
		} else {
			tv_trip1_mileage.setText(activity.getString(R.string.accum_mileage)+"--"+str);
		}
		
	}
}
