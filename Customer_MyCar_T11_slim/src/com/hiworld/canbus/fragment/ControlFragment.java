package com.hiworld.canbus.fragment;

import com.hiworld.canbus.activity.DoorlockActivity;
import com.hiworld.canbus.activity.LightActivity;
import com.hiworld.canbus.activity.SuonaActivity;
import com.hiworld.canbus.activity.WindowActivity;
import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ControlFragment extends Fragment implements OnClickListener{

	private View mContentView; 
	private Activity activity;
	
	private ImageButton mBtnControlWindow, mBtnControlLight, 
	                                 mBtnControlDoorlock, mBtnControlSuona;
	private TextView mWindowTv, mLightTv, mDoorlockTv, mSuonaTv;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setControlHandler(mHandler);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if (null == mContentView) {
        	mContentView = inflater.inflate(R.layout.fragment_control, container,false);
        	initView(mContentView);
        	activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					setCarInfo(CarPcInfo.getInstance());
				}
			});
        }else  {  
            ViewGroup parent = (ViewGroup) mContentView.getParent();  
            if (parent != null)  {  
                parent.removeView(mContentView);  
            }  
        }  
		return mContentView;
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler  = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo carpc = (CarPcInfo) msg.obj;
				setCarInfo(carpc);
				break;
			default:
				break;
			}
		}
		
	};

	private void initView(View view) {
		
		mBtnControlWindow = (ImageButton) view.findViewById(R.id.btn_control_window);
		mBtnControlLight = (ImageButton) view.findViewById(R.id.btn_control_light);
		mBtnControlDoorlock = (ImageButton) view.findViewById(R.id.btn_control_doorlock);
		mBtnControlSuona = (ImageButton) view.findViewById(R.id.btn_control_suona);
		
		mWindowTv = (TextView) view.findViewById(R.id.tv_control_window);
		mLightTv = (TextView) view.findViewById(R.id.tv_control_light);
		mDoorlockTv = (TextView) view.findViewById(R.id.tv_control_doorlock);
		mSuonaTv = (TextView) view.findViewById(R.id.tv_control_suona);
		
		mBtnControlWindow.setOnClickListener(this);
		mBtnControlLight.setOnClickListener(this);
		mBtnControlDoorlock.setOnClickListener(this);
		mBtnControlSuona.setOnClickListener(this);
	}

	protected void setCarInfo(CarPcInfo carpc) {
		
        String carBrand = carpc.getM_strBrand();
		
		if (carBrand.equals("福特")){
			mBtnControlLight.setEnabled(false);
			mBtnControlDoorlock.setEnabled(false);
			mBtnControlSuona.setEnabled(false);
			mBtnControlLight.setVisibility(View.GONE);
			mBtnControlSuona.setVisibility(View.GONE);
			mBtnControlDoorlock.setVisibility(View.GONE);
			mLightTv.setVisibility(View.GONE);
			mDoorlockTv.setVisibility(View.GONE);
			mSuonaTv.setVisibility(View.GONE);
		}else if (carBrand.equals("通用")){
			mBtnControlLight.setEnabled(true);
			mBtnControlDoorlock.setEnabled(true);
			mBtnControlSuona.setEnabled(true);
			mBtnControlLight.setVisibility(View.VISIBLE);
			mBtnControlSuona.setVisibility(View.VISIBLE);
			mBtnControlDoorlock.setVisibility(View.VISIBLE);
			mLightTv.setVisibility(View.VISIBLE);
			mDoorlockTv.setVisibility(View.VISIBLE);
			mSuonaTv.setVisibility(View.VISIBLE);
		}else if (carBrand.equals("大众")){
			mBtnControlLight.setEnabled(true);
			mBtnControlDoorlock.setEnabled(true);
			mBtnControlSuona.setEnabled(true);
			
			mBtnControlLight.setVisibility(View.VISIBLE);
			mBtnControlSuona.setVisibility(View.VISIBLE);
			mBtnControlDoorlock.setVisibility(View.VISIBLE);
			mLightTv.setVisibility(View.VISIBLE);
			mDoorlockTv.setVisibility(View.VISIBLE);
			mSuonaTv.setVisibility(View.VISIBLE);
		}else{
			mBtnControlLight.setEnabled(true);
			mBtnControlDoorlock.setEnabled(true);
			mBtnControlSuona.setEnabled(true);
			mBtnControlLight.setVisibility(View.VISIBLE);
			mBtnControlSuona.setVisibility(View.VISIBLE);
			mBtnControlDoorlock.setVisibility(View.VISIBLE);
			mLightTv.setVisibility(View.VISIBLE);
			mDoorlockTv.setVisibility(View.VISIBLE);
			mSuonaTv.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		
		try {
			switch (v.getId()) {
			case R.id.btn_control_window://车窗控制
				Intent intentWindow = new Intent(activity, WindowActivity.class);
				startActivity(intentWindow);
				break;
			case R.id.btn_control_light://灯光控制
				Intent intentLight = new Intent(activity, LightActivity.class);
				startActivity(intentLight);
				break;
			case R.id.btn_control_doorlock://门锁控制
				Intent intentDoorlock = new Intent(activity, DoorlockActivity.class);
				startActivity(intentDoorlock);
				break;
			case R.id.btn_control_suona://喇叭控制
				Intent intentSuona = new Intent(activity, SuonaActivity.class);
				startActivity(intentSuona);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
