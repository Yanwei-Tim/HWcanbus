package com.hiworld.fragment.golf;


import com.hiworld.canbus.carpc.FragmentCallBack;
import com.hiworld.adapter.VMCarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.canbus.parsedata.VmParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;
import com.hiworld.constant.ConstantCar;
import com.hiworld.myview.IconCheckBoxPreference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class VMRadarFragment extends PreferenceFragment implements  OnPreferenceClickListener,  OnPreferenceChangeListener{

	private SharedPreferences sharePreradar;
	private PreferenceScreen prefscreen;
	FragmentCallBack fragmentCallBack = null;
	private Activity activity;
	
	//大众全兼容
	private IconCheckBoxPreference m_chkRoadsideparking;//路边驻车
	private IconCheckBoxPreference m_chkParkingparking;//入库驻车
	private IconCheckBoxPreference m_chkRadarsilence;//雷达静音
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.radar_preference);
		sharePreradar = PreferenceManager.getDefaultSharedPreferences(activity);
		prefscreen = getPreferenceScreen();
		
		//setRadar(CarInfo.getInstance());
		setVmRadar(VMCarInfo.getInstance());
	}

	private void setVmRadar(VMCarInfo stCarInfo) {
		prefscreen.removeAll();
		Editor editor = sharePreradar.edit();
		
		switch (ActivityCarPC.CARTYPE_MODE) {
		case ConstantCar.CARTYPE_VWF0://大众全兼容
//			switch (MainActivity.CAR_MODELS) {
//			case Data.VWFO_MAGOTAN:
				//大众路边驻车
				m_chkRoadsideparking = new IconCheckBoxPreference(activity);
				m_chkRoadsideparking.setKey(activity.getString(R.string.key_Roadsideparking));
				m_chkRoadsideparking.setTitle(activity.getString(R.string.title_Roadsideparking));

				m_chkRoadsideparking.setOnPreferenceChangeListener(this);//设置监听
				prefscreen.addPreference(m_chkRoadsideparking);
				switch (stCarInfo.getM_byRoadsideparking()) {
				case 0:
					editor.putBoolean(activity.getString(R.string.key_Roadsideparking), false);
					m_chkRoadsideparking.setChecked(false);
					break;
				case 1:
					editor.putBoolean(activity.getString(R.string.key_Roadsideparking), true);
					m_chkRoadsideparking.setChecked(true);
					break;
				default:
					break;
				}
				
				//入库驻车
				m_chkParkingparking = new IconCheckBoxPreference(activity);
				m_chkParkingparking.setKey(activity.getString(R.string.key_Parkingparking));
				m_chkParkingparking.setTitle(activity.getString(R.string.title_Parkingparking));

				m_chkParkingparking.setOnPreferenceChangeListener(this);//设置监听
				prefscreen.addPreference(m_chkParkingparking);
				switch (stCarInfo.getM_byStorageparking()) {
				case 0:
					editor.putBoolean(activity.getString(R.string.key_Parkingparking), false);
					m_chkParkingparking.setChecked(false);
					break;
				case 1:
					editor.putBoolean(activity.getString(R.string.key_Parkingparking), true);
					m_chkParkingparking.setChecked(true);
					break;
				default:
					break;
				}
				//雷达静音
				m_chkRadarsilence = new IconCheckBoxPreference(activity);
				m_chkRadarsilence.setKey(activity.getString(R.string.key_Radarsilence));
				m_chkRadarsilence.setTitle(activity.getString(R.string.title_Radarsilence));

				m_chkRadarsilence.setOnPreferenceChangeListener(this);//设置监听
				prefscreen.addPreference(m_chkRadarsilence);
				switch (stCarInfo.getM_byRadarsilence()) {
				case 0:
					editor.putBoolean(activity.getString(R.string.key_Radarsilence), false);
					m_chkRadarsilence.setChecked(false);
					break;
				case 1:
					editor.putBoolean(activity.getString(R.string.key_Radarsilence), true);
					m_chkRadarsilence.setChecked(true);
					break;
				default:
					break;
				}
			/*	break;
			default:
				break;
			}*/
			break;
			
		default:
			break;
		}
		editor.commit();
		
	}

	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		fragmentCallBack = (ActivityCarPC)activity;
		if (mHandler != null){
			VmParseData.getInstance().setVmRadarHandler(mHandler);
		}
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object objValue) {
		
//		else if (preference.getKey().equals(getString(R.string.key_RadarWarnVolLevel))){          
//			if (fragmentCallBack != null) {
//				fragmentCallBack.callbackCMD(new int[]{0x03,0x6a,0x01,0x09,Integer.valueOf((String) objValue).intValue()}, 5);
//			}
//        }
		if (preference.getKey().equals(activity.getString(R.string.key_Roadsideparking))){          
			if (fragmentCallBack != null) {
				int values = 0;
				if ((Boolean) objValue) {
					values = 1;
				} else {
					values = 0;
				}
				fragmentCallBack.callbackCMD(new int[]{0x02,0x4c,0x09,values}, 4);
			}
        }
		if (preference.getKey().equals(activity.getString(R.string.key_Parkingparking))){          
			if (fragmentCallBack != null) {
				int values = 0;
				if ((Boolean) objValue) {
					values = 1;
				} else {
					values = 0;
				}
				fragmentCallBack.callbackCMD(new int[]{0x02,0x4c,0x0a,values}, 4);
			}
        }
		if (preference.getKey().equals(activity.getString(R.string.key_Radarsilence))){          
			if (fragmentCallBack != null) {
				int values = 0;
				if ((Boolean) objValue) {
					values = 1;
				} else {
					values = 0;
				}
				fragmentCallBack.callbackCMD(new int[]{0x02,0x4c,0x0b,values}, 4);
			}
        }
		return false;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		
		return false;
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.VWF0_MESSAGE_SEND_INFO:
				VMCarInfo vmCarifo = (VMCarInfo) msg.obj;
				if (vmCarifo != null) {
					setVmRadar(vmCarifo);
				}
				break;
			default:
				break;
			}
		}
		
	};

}
