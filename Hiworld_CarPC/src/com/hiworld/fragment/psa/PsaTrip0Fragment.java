package com.hiworld.fragment.psa;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.hiworld.adapter.CarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.PsaToyotaParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PsaTrip0Fragment extends Fragment {
	private static final String TAG = PsaTrip0Fragment.class.getSimpleName();
	FragmentCallBack fragmentCallBack = null;
	private Activity activity;
	private TextView m_TextInstantFuel;//瞬时油耗
	private TextView m_TextMileage;//续航里程
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		Log.d(TAG, "fragment onAttach!");
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC)activity;
		PsaToyotaParseData.getInstance().setPsaHandler(mHandler);
	}

	
	
	@Override
	public void onResume() {
		
		super.onResume();
		//Log.d(TAG, "fragment onResume!");
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//Log.d(TAG, "fragment oncreate!");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//Log.d(TAG, "fragment onCreateView!");
		
		View view = inflater.inflate(R.layout.psa_instant_layout, container, false);
		m_TextInstantFuel = (TextView)view.findViewById(R.id.value_instantfuel);
		m_TextMileage = (TextView)view.findViewById(R.id.value_mileage);
		setComputerInfo(CarInfo.getInstance());
		return view;
	}

	private void setComputerInfo(CarInfo stCarInfo) {
		
		if (stCarInfo != null) {
			//Log.d("setComputerInfo", "stCarInfo.getInstantFuel() ="+stCarInfo.getInstantFuel());
			if (stCarInfo.getInstantFuel() == 0xffff) {
				m_TextInstantFuel.setText(activity.getString(R.string.instant_fuel)+"-- L/100Km");
			} else {
				m_TextInstantFuel.setText(activity.getString(R.string.instant_fuel)+Float.toString(stCarInfo.getInstantFuel())+" L/100Km");
			}
			if (stCarInfo.getMileage() == 0xffff) {
				m_TextMileage.setText(activity.getString(R.string.instant_mileage)+"-- Km");
			} else {
				m_TextMileage.setText(activity.getString(R.string.instant_mileage)+Integer.toString(stCarInfo.getMileage())+" Km");
			}
			
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.Message_Instant_Handler:
				CarInfo stComputerInfo = (CarInfo) msg.obj;
				if (stComputerInfo != null) {
					setComputerInfo(stComputerInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};
}
