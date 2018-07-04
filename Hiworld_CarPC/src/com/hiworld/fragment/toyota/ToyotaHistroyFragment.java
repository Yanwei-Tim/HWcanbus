package com.hiworld.fragment.toyota;

import com.hiworld.canbus.carpc.FragmentCallBack;
import com.hiworld.adapter.CarInfo;
import com.hiworld.adapter.MySeekBar;
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

public class ToyotaHistroyFragment extends Fragment {

	FragmentCallBack fragmentCallBack = null;
	private Activity activity;
	private MySeekBar m_MySeekBarCurrent;
	private MySeekBar m_MySeekBarTrip1;
	private MySeekBar m_MySeekBarTrip2;
	private MySeekBar m_MySeekBarTrip3;
	private MySeekBar m_MySeekBarTrip4;
	private MySeekBar m_MySeekBarTrip5;
	private TextView  m_TextTime1;
	private TextView  m_TextTime2;
	private TextView  m_TextTime3;
	private TextView  m_TextTime4;
	private TextView  m_TextBest;
	private TextView  m_TextHistroyUnit;
	private Button    m_BtnHistroyClear;
	private Button    m_BtnHistroyUpdate, m_BtnHistoryFragment;;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC)activity;
		PsaToyotaParseData.getInstance().setHistroyHandler(mHandler);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.toyota_histroy_layout, container, false);
		init(view);
		setHistroyFuel(CarInfo.getInstance());
		return view;
	}

	private void init(View view) {
		
		m_MySeekBarCurrent = (MySeekBar)view.findViewById(R.id.progress_histroy_current);
		m_MySeekBarTrip1 = (MySeekBar)view.findViewById(R.id.progress_histroy_trip1);
		m_MySeekBarTrip2 = (MySeekBar)view.findViewById(R.id.progress_histroy_trip2);
		m_MySeekBarTrip3 = (MySeekBar)view.findViewById(R.id.progress_histroy_trip3);
		m_MySeekBarTrip4 = (MySeekBar)view.findViewById(R.id.progress_histroy_trip4);
		m_MySeekBarTrip5 = (MySeekBar)view.findViewById(R.id.progress_histroy_trip5);
		
		m_MySeekBarCurrent.setTouch(false);
		m_MySeekBarTrip1.setTouch(false);
		m_MySeekBarTrip2.setTouch(false);
		m_MySeekBarTrip3.setTouch(false);
		m_MySeekBarTrip4.setTouch(false);
		m_MySeekBarTrip5.setTouch(false);
		
		
		
		m_TextTime1 = (TextView)view.findViewById(R.id.text_histroy_time1);
		m_TextTime2 = (TextView)view.findViewById(R.id.text_histroy_time2);
		m_TextTime3 = (TextView)view.findViewById(R.id.text_histroy_time3);
		m_TextTime4 = (TextView)view.findViewById(R.id.text_histroy_time4);
		m_TextBest = (TextView)view.findViewById(R.id.tv_bestfuel_values);
		m_TextHistroyUnit  = (TextView)view.findViewById(R.id.text_histroy_unit);
		m_BtnHistroyClear = (Button)view.findViewById(R.id.btn_history_clear_toyoyta);
		m_BtnHistroyUpdate = (Button)view.findViewById(R.id.btn_history_update_toyoyta);
		m_BtnHistoryFragment = (Button)view.findViewById(R.id.btn_history_faragment_toyota);
		
		m_BtnHistoryFragment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				System.out.println(" onclick  OnClickListener  ");
				if (fragmentCallBack != null) {
					fragmentCallBack.jumpFragment(0);
				}
			}
		});
		
		m_BtnHistroyClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.callbackCMD(new int[] {0x03,0x6a,0x04,0x02,0x01}, 5);
				}
			}
		});
		m_BtnHistroyUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.callbackCMD(new int[] {0x03,0x6a,0x04,0x02,0x02}, 5);
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	public void onPause() {
		
		super.onPause();
	}

	@Override
	public void onResume() {
		
		super.onResume();
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
					setHistroyFuel(stComputerInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};
	
	
	protected void setHistroyFuel(CarInfo stComputerInfo) {
		
		if (stComputerInfo != null) {
			int max = 30;
			//油耗单位 每分钟 ComID	0x17
			int Unit = stComputerInfo.getTrip1FuleUnit();
			switch (Unit) {
			case 0x00:
				max = 60;
				m_TextHistroyUnit.setText("MPG");
				break;
			case 0x01:
				max = 30;
				m_TextHistroyUnit.setText("Km/L");
				break;
			case 0x02:
				max = 30;
				m_TextHistroyUnit.setText("L/100Km");
				break;
			default:
				break;
			}
			
			//当前油耗和形成最大值
			m_MySeekBarCurrent.setMax(max);
			m_MySeekBarTrip1.setMax(max);
			m_MySeekBarTrip2.setMax(max);
			m_MySeekBarTrip3.setMax(max);
			m_MySeekBarTrip4.setMax(max);
			m_MySeekBarTrip5.setMax(max);
			//设置柱形的当前值
			int values = (int) stComputerInfo.getCurrentTripFuel();
			m_MySeekBarCurrent.setProgress(values);
			values = (int) stComputerInfo.getTrip1Fuel();
			m_MySeekBarTrip1.setProgress(values);
			values = (int) stComputerInfo.getTrip2Fuel();
			m_MySeekBarTrip2.setProgress(values);
			values = (int) stComputerInfo.getTrip3Fuel();
			m_MySeekBarTrip3.setProgress(values);
			values = (int) stComputerInfo.getTrip4Fuel();
			m_MySeekBarTrip4.setProgress(values);
			values = (int) stComputerInfo.getTrip5Fuel();
			m_MySeekBarTrip5.setProgress(values);
			
			//设置时间段
			switch (max) {
			case 30:
				m_TextTime1.setText("0");
				m_TextTime2.setText("10");
				m_TextTime3.setText("20");
				m_TextTime4.setText("30");
				break;
			case 60:
				m_TextTime1.setText("0");
				m_TextTime2.setText("20");
				m_TextTime3.setText("40");
				m_TextTime4.setText("60");
				break;
			default:
				break;
			}
			
			//最佳油耗
			int unit = stComputerInfo.getFuleUnit();//单位
			StringBuilder str = new StringBuilder();
			//str.append(activity.getString(R.string.histroy_bestfuel));
			if (stComputerInfo.getBestFuel() == 0xffff) {
				str.append("- -");
			} else {
				str.append(Float.toString(stComputerInfo.getBestFuel()));
			}
			switch (unit) {
			case 0x00:
				str.append("MPG");
				break;
			case 0x01:
				str.append("Km/L");
				break;
			case 0x02:
				str.append("L/100Km");
				break;
			default:
				break;
			}
			m_TextBest.setText(str);
			
		}
	}

}
