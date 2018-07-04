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

public class ToyotaPerMinuteFragment extends Fragment {

	FragmentCallBack fragmentCallBack = null;
	private TextView m_TextPerminuteTime0;
	private TextView m_TextPerminuteTime1;
	private TextView m_TextPerminuteTime2;
	private TextView m_TextPerminuteTime3;
	private MySeekBar m_MySeekInstantaneous;
	private MySeekBar[] m_MySeekMinute = new MySeekBar[30];
	private TextView m_TextUnitFuel;
	private TextView m_TextSpeed;
	private TextView m_TextDrvingtime;
	private TextView m_TextCruisingRange;
	private Button m_BtnClear, m_BtnHistory;
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		fragmentCallBack = (ActivityCarPC)activity;
		PsaToyotaParseData.getInstance().setPerMinuteHandler(mHandler);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.toyota_perminute_layout, container, false);
		init(view);
		setPerMinuteFuel(CarInfo.getInstance());
		return view;
	}

	private void init(View view) {
		
		//4个时间段数据
		m_TextPerminuteTime0 = (TextView)view.findViewById(R.id.text_perminute_value1);
		m_TextPerminuteTime1 = (TextView)view.findViewById(R.id.text_perminute_value2);
		m_TextPerminuteTime2 = (TextView)view.findViewById(R.id.text_perminute_value3);
		m_TextPerminuteTime3 = (TextView)view.findViewById(R.id.text_perminute_value4);
		//瞬时和30分钟柱形
		m_MySeekInstantaneous = (MySeekBar)view.findViewById(R.id.progress_current);
		m_MySeekMinute[0] = (MySeekBar)view.findViewById(R.id.progress_minute1);
		m_MySeekMinute[1] = (MySeekBar)view.findViewById(R.id.progress_minute2);
		m_MySeekMinute[2] = (MySeekBar)view.findViewById(R.id.progress_minute3);
		m_MySeekMinute[3] = (MySeekBar)view.findViewById(R.id.progress_minute4);
		m_MySeekMinute[4] = (MySeekBar)view.findViewById(R.id.progress_minute5);
		m_MySeekMinute[5] = (MySeekBar)view.findViewById(R.id.progress_minute6);
		m_MySeekMinute[6] = (MySeekBar)view.findViewById(R.id.progress_minute7);
		m_MySeekMinute[7] = (MySeekBar)view.findViewById(R.id.progress_minute8);
		m_MySeekMinute[8] = (MySeekBar)view.findViewById(R.id.progress_minute9);
		m_MySeekMinute[9] = (MySeekBar)view.findViewById(R.id.progress_minute10);
		m_MySeekMinute[10] = (MySeekBar)view.findViewById(R.id.progress_minute11);
		m_MySeekMinute[11] = (MySeekBar)view.findViewById(R.id.progress_minute12);
		m_MySeekMinute[12] = (MySeekBar)view.findViewById(R.id.progress_minute13);
		m_MySeekMinute[13] = (MySeekBar)view.findViewById(R.id.progress_minute14);
		m_MySeekMinute[14] = (MySeekBar)view.findViewById(R.id.progress_minute15);
		m_MySeekMinute[15] = (MySeekBar)view.findViewById(R.id.progress_minute16);
		m_MySeekMinute[16] = (MySeekBar)view.findViewById(R.id.progress_minute17);
		m_MySeekMinute[17] = (MySeekBar)view.findViewById(R.id.progress_minute18);
		m_MySeekMinute[18] = (MySeekBar)view.findViewById(R.id.progress_minute19);
		m_MySeekMinute[19] = (MySeekBar)view.findViewById(R.id.progress_minute20);
		m_MySeekMinute[20] = (MySeekBar)view.findViewById(R.id.progress_minute21);
		m_MySeekMinute[21] = (MySeekBar)view.findViewById(R.id.progress_minute22);
		m_MySeekMinute[22] = (MySeekBar)view.findViewById(R.id.progress_minute23);
		m_MySeekMinute[23] = (MySeekBar)view.findViewById(R.id.progress_minute24);
		m_MySeekMinute[24] = (MySeekBar)view.findViewById(R.id.progress_minute25);
		m_MySeekMinute[25] = (MySeekBar)view.findViewById(R.id.progress_minute26);
		m_MySeekMinute[26] = (MySeekBar)view.findViewById(R.id.progress_minute27);
		m_MySeekMinute[27] = (MySeekBar)view.findViewById(R.id.progress_minute28);
		m_MySeekMinute[28] = (MySeekBar)view.findViewById(R.id.progress_minute29);
		m_MySeekMinute[29] = (MySeekBar)view.findViewById(R.id.progress_minute30);
		
		
		m_MySeekInstantaneous.setTouch(false);
		for (int i = 0; i < 30; i++) {
			m_MySeekMinute[i].setTouch(false);
		}
		
		m_TextUnitFuel = (TextView)view.findViewById(R.id.text_fuelunit);
		m_TextSpeed = (TextView)view.findViewById(R.id.text_perminute_speed_values);
		m_TextDrvingtime = (TextView)view.findViewById(R.id.text_perminute_drvingtime_values);
		m_TextCruisingRange = (TextView)view.findViewById(R.id.text_perminute_curisingrange_values);
		
		//清除
		m_BtnClear = (Button)view.findViewById(R.id.btn_perminute_clear_toyoyta);
		m_BtnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.callbackCMD(new int[] {0x03,0x6a,0x04,0x01,0x01}, 5);
				}
			}
		});
		
		m_BtnHistory = (Button) view.findViewById(R.id.btn_perminute_faragment_toyota);
		m_BtnHistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragmentCallBack != null) {
					fragmentCallBack.jumpFragment(1);
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
					setPerMinuteFuel(stComputerInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};
	
	
	protected void setPerMinuteFuel(CarInfo stComputerInfo) {
		
		if (stComputerInfo != null) {
			int max = 30;
			//油耗单位 每分钟 ComID	0x17
			int Unit = stComputerInfo.getTrip2FuleUnit();
			switch (Unit) {
			case 0x00:
				max = 60;
				break;
			case 0x01:
				max = 30;
				break;
			case 0x02:
				max = 30;
				break;
			default:
				break;
			}
			
			//上面4个时间段
			switch (max) {
			case 30:
				m_TextPerminuteTime0.setText("0");
				m_TextPerminuteTime1.setText("10");
				m_TextPerminuteTime2.setText("20");
				m_TextPerminuteTime3.setText("30");
				break;
			case 60:
				m_TextPerminuteTime0.setText("0");
				m_TextPerminuteTime1.setText("20");
				m_TextPerminuteTime2.setText("40");
				m_TextPerminuteTime3.setText("60");
				break;
			default:
				break;
			}
			//柱形范围
			m_MySeekInstantaneous.setMax(max);
			for (int i = 0; i < 30; i++) {
				m_MySeekMinute[i].setMax(max);
			}
			//设置柱形当前值
			int value = (int)stComputerInfo.getTrip1AverageFuel();
			if (value != 0xffff) {
				m_MySeekInstantaneous.setProgress(value);
			}
			
			m_MySeekMinute[0].setProgress((int)stComputerInfo.getTrip2Minute1Fuel());
			m_MySeekMinute[1].setProgress((int)stComputerInfo.getTrip2Minute2Fuel());
			m_MySeekMinute[2].setProgress((int)stComputerInfo.getTrip2Minute3Fuel());
			m_MySeekMinute[3].setProgress((int)stComputerInfo.getTrip2Minute4Fuel());
			m_MySeekMinute[4].setProgress((int)stComputerInfo.getTrip2Minute5Fuel());
			m_MySeekMinute[5].setProgress((int)stComputerInfo.getTrip2Minute6Fuel());
			m_MySeekMinute[6].setProgress((int)stComputerInfo.getTrip2Minute7Fuel());
			m_MySeekMinute[7].setProgress((int)stComputerInfo.getTrip2Minute8Fuel());
			m_MySeekMinute[8].setProgress((int)stComputerInfo.getTrip2Minute9Fuel());
			m_MySeekMinute[9].setProgress((int)stComputerInfo.getTrip2Minute10Fuel());
			m_MySeekMinute[10].setProgress((int)stComputerInfo.getTrip2Minute11Fuel());
			m_MySeekMinute[11].setProgress((int)stComputerInfo.getTrip2Minute12Fuel());
			m_MySeekMinute[12].setProgress((int)stComputerInfo.getTrip2Minute13Fuel());
			m_MySeekMinute[13].setProgress((int)stComputerInfo.getTrip2Minute14Fuel());
			m_MySeekMinute[14].setProgress((int)stComputerInfo.getTrip2Minute15Fuel());
			m_MySeekMinute[15].setProgress((int)stComputerInfo.getTrip2Minute16Fuel());
			m_MySeekMinute[16].setProgress((int)stComputerInfo.getTrip2Minute17Fuel());
			m_MySeekMinute[17].setProgress((int)stComputerInfo.getTrip2Minute18Fuel());
			m_MySeekMinute[18].setProgress((int)stComputerInfo.getTrip2Minute19Fuel());
			m_MySeekMinute[19].setProgress((int)stComputerInfo.getTrip2Minute20Fuel());
			m_MySeekMinute[20].setProgress((int)stComputerInfo.getTrip2Minute21Fuel());
			m_MySeekMinute[21].setProgress((int)stComputerInfo.getTrip2Minute22Fuel());
			m_MySeekMinute[22].setProgress((int)stComputerInfo.getTrip2Minute23Fuel());
			m_MySeekMinute[23].setProgress((int)stComputerInfo.getTrip2Minute24Fuel());
			m_MySeekMinute[24].setProgress((int)stComputerInfo.getTrip2Minute25Fuel());
			m_MySeekMinute[25].setProgress((int)stComputerInfo.getTrip2Minute26Fuel());
			m_MySeekMinute[26].setProgress((int)stComputerInfo.getTrip2Minute27Fuel());
			m_MySeekMinute[27].setProgress((int)stComputerInfo.getTrip2Minute28Fuel());
			m_MySeekMinute[28].setProgress((int)stComputerInfo.getTrip2Minute29Fuel());
			m_MySeekMinute[29].setProgress((int)stComputerInfo.getTrip2Minute30Fuel());
			
//			//最上面单位
//			Unit = stComputerInfo.getFuleUnit();
			switch (Unit) {
			case 0x00:
				m_TextUnitFuel.setText("MPG");
				break;
			case 0x01:
				m_TextUnitFuel.setText("Km/L");
				break;
			case 0x02:
				m_TextUnitFuel.setText("L/100Km");
				break;
			default:
				break;
			}
			
			//平均车速
			if (stComputerInfo.getTrip1AverageSpeed() == 0xff) {
				m_TextSpeed.setText("- - Km/h");
			} else {
				m_TextSpeed.setText(String.format("%d Km/h", stComputerInfo.getTrip1AverageSpeed()));
			}
			
			//行驶时间
			//Log.d("time", "time values ="+stComputerInfo.getDrivingTime());
			int hour = stComputerInfo.getDrivingTime()/60;//小时
			int minute = stComputerInfo.getDrivingTime()%60;//分钟
			m_TextDrvingtime.setText(String.format("%02d", hour)+":"+String.format("%02d", minute));
			//续航里程
			//float mileage = (float) (stComputerInfo.getMileage());
			//里程单位
			switch (stComputerInfo.getMileageUnit()) {
			case 0x00:
				if (stComputerInfo.getMileage() == 0xffff) {
					m_TextCruisingRange.setText("-- Km");
				} else {
					m_TextCruisingRange.setText(Integer.toString(stComputerInfo.getMileage())+"Km");
				}
				
				break;
            case 0x01:
               if (stComputerInfo.getMileage() == 0xffff) {
            	   m_TextCruisingRange.setText("- - Mile");
				} else {
					m_TextCruisingRange.setText(Integer.toString(stComputerInfo.getMileage())+"Mile");
				}
            	
				break;
			default:
				break;
			}
		}
	}

}
