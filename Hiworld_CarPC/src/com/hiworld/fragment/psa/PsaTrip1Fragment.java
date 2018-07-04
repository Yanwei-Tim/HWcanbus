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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PsaTrip1Fragment extends Fragment {
    FragmentCallBack fragmentCallBack = null;
	private Activity activity;
    private TextView m_TextTrip1AverageFuel;//平均油耗
    private TextView m_TextTrip1AverageSpeed;//平均车速
    private TextView m_TextTrip1AccumMileage;//累计里程
    private Button   m_BtnClearZero;//清零
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC)activity;
		PsaToyotaParseData.getInstance().setTrip1Handler(mHandler);
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
		
		View view = inflater.inflate(R.layout.psa_trip1_layout, container, false);
		m_TextTrip1AverageFuel = (TextView)view.findViewById(R.id.value_average_fuel);
		m_TextTrip1AverageSpeed = (TextView)view.findViewById(R.id.value_average_speed);
		m_TextTrip1AccumMileage = (TextView)view.findViewById(R.id.value_accum_mileage);
		m_BtnClearZero = (Button)view.findViewById(R.id.btn_clearzero);
		m_BtnClearZero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.callbackCMD(new int[] {0x04,0x1b,0x02,0x02,0x01,0xff}, 6);
				}
			}
		});
		
		setComputerInfo(CarInfo.getInstance());
		return view;
	}

	private void setComputerInfo(CarInfo stCarInfo) {
		
		if (stCarInfo != null) {
			if (stCarInfo.getTrip1AverageFuel() == (float)0xffff) {
				m_TextTrip1AverageFuel.setText(activity.getString(R.string.average_fuel)+"-- L/100Km");
			} else {
				m_TextTrip1AverageFuel.setText(activity.getString(R.string.average_fuel)+Float.toString(stCarInfo.getTrip1AverageFuel())+" L/100Km");
			}
			
			if (stCarInfo.getTrip1AverageSpeed() == 0xff) {
				m_TextTrip1AverageSpeed.setText(activity.getString(R.string.average_speed)+"-- Km/h");
			} else {
				m_TextTrip1AverageSpeed.setText(activity.getString(R.string.average_speed)+Integer.toString(stCarInfo.getTrip1AverageSpeed())+" Km/h");
			}
			
			m_TextTrip1AccumMileage.setText(activity.getString(R.string.accum_mileage)+Integer.toString(stCarInfo.getTrip1AccumMileage())+" Km");
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
