package com.hiworld.canbus.activity;


import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.parse.RequestCmdUtil;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WindowActivity extends FragmentActivity implements OnClickListener{

	private Button mBtnBack;
	private TextView mTitleTv;
	
	private RelativeLayout mLfWindow_ll, mRfWindow_ll, mLrWindow_ll, mRrWindow_ll,
	mSkyWindow_ll, mAllWindow_ll;
	
	private Button mLfWindowUp, mLfWindowDown, mRfWindowUp, mRfWindowDown,
	                        mLrWindowUp, mLrWindowDown, mRrWindowUp, mRrWindowDown,
	                        mSkyWindowUp, mSkyWindowDown, mAllWindowUp, mAllWindowDown;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_control_window);
		
		initView();
		
        runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setCarInfo(CarPcInfo.getInstance());
			}
		});
        
        FragmentParseData.getInstance().setWindowHandler(mHandler);
	}
	
	protected void setCarInfo(CarPcInfo instance) {
		
		String carBrand = instance.getM_strBrand();
		
		//Log.i("aaa", "carBrand =="+carBrand);
		mLfWindowUp.setEnabled(true);
		mLfWindowDown.setEnabled(true);
		mRfWindowUp.setEnabled(true);
		mRfWindowDown.setEnabled(true);
		mLrWindowUp.setEnabled(true);
		mLrWindowDown.setEnabled(true);
		mRrWindowUp.setEnabled(true);
		mRrWindowDown.setEnabled(true);
		mAllWindowUp.setEnabled(true);
		mAllWindowDown.setEnabled(true);
		
		mSkyWindowUp.setEnabled(true);
		mSkyWindowDown.setEnabled(true);
		mSkyWindow_ll.setVisibility(View.VISIBLE);
		
		if (carBrand.equals("大众")) {
			mLfWindowUp.setEnabled(true);
			mLfWindowDown.setEnabled(true);
			mRfWindowUp.setEnabled(true);
			mRfWindowDown.setEnabled(true);
			mLrWindowUp.setEnabled(true);
			mLrWindowDown.setEnabled(true);
			mRrWindowUp.setEnabled(true);
			mRrWindowDown.setEnabled(true);
			mAllWindowUp.setEnabled(true);
			mAllWindowDown.setEnabled(true);
			
//			mLfWindow_ll.setVisibility(View.VISIBLE);
//			mRfWindow_ll.setVisibility(View.VISIBLE);
//			mLrWindow_ll.setVisibility(View.VISIBLE);
//			mRrWindow_ll.setVisibility(View.VISIBLE);
//			mAllWindow_ll.setVisibility(View.VISIBLE);
			
			mSkyWindowUp.setEnabled(false);
			mSkyWindowDown.setEnabled(false);
			mSkyWindow_ll.setVisibility(View.GONE);
			
			
		}else if (carBrand.equals("通用")) {
			mLfWindowUp.setEnabled(true);
			mLfWindowDown.setEnabled(true);
			mRfWindowUp.setEnabled(true);
			mRfWindowDown.setEnabled(true);
			mLrWindowUp.setEnabled(true);
			mLrWindowDown.setEnabled(true);
			mRrWindowUp.setEnabled(true);
			mRrWindowDown.setEnabled(true);
			mAllWindowUp.setEnabled(true);
			mAllWindowDown.setEnabled(true);
//			mLfWindow_ll.setVisibility(View.VISIBLE);
//			mRfWindow_ll.setVisibility(View.VISIBLE);
//			mLrWindow_ll.setVisibility(View.VISIBLE);
//			mRrWindow_ll.setVisibility(View.VISIBLE);
//			mAllWindow_ll.setVisibility(View.VISIBLE);
			
			mSkyWindowUp.setEnabled(true);
			mSkyWindowDown.setEnabled(true);
			mSkyWindow_ll.setVisibility(View.VISIBLE);
		}else if (carBrand.equals("福特")) {
			mAllWindowDown.setEnabled(true);
			
			mLfWindowUp.setEnabled(true);
			mLfWindowDown.setEnabled(false);
			mRfWindowUp.setEnabled(true);
			mRfWindowDown.setEnabled(false);
			mLrWindowUp.setEnabled(true);
			mLrWindowDown.setEnabled(false);
			mRrWindowUp.setEnabled(true);
			mRrWindowDown.setEnabled(false);
			mAllWindowUp.setEnabled(true);
			mAllWindowDown.setEnabled(false);
			
//			mLfWindow_ll.setVisibility(View.GONE);
//			mRfWindow_ll.setVisibility(View.GONE);
//			mLrWindow_ll.setVisibility(View.GONE);
//			mRrWindow_ll.setVisibility(View.GONE);
//			mAllWindow_ll.setVisibility(View.VISIBLE);
			
			mSkyWindowUp.setEnabled(false);
			mSkyWindowDown.setEnabled(false);
			mSkyWindow_ll.setVisibility(View.GONE);
		}
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

	private void initView() {
		
		mTitleTv = (TextView) this.findViewById(R.id.tv_headerTitle);
		mTitleTv.setText(R.string.window_control);
		
		mLfWindowUp = (Button) this.findViewById(R.id.btn_lf_window_up);
		mLfWindowDown = (Button) this.findViewById(R.id.btn_lf_window_down);
		mRfWindowUp = (Button) this.findViewById(R.id.btn_rf_window_up);
		mRfWindowDown = (Button) this.findViewById(R.id.btn_rf_window_down);
		mLrWindowUp = (Button) this.findViewById(R.id.btn_lr_window_up);
		mLrWindowDown = (Button) this.findViewById(R.id.btn_lr_window_down);
		mRrWindowUp = (Button) this.findViewById(R.id.btn_rr_window_up);
		mRrWindowDown = (Button) this.findViewById(R.id.btn_rr_window_down);
		mSkyWindowUp = (Button) this.findViewById(R.id.btn_sky_window_up);
		mSkyWindowDown = (Button) this.findViewById(R.id.btn_sky_window_down);
		mAllWindowUp = (Button) this.findViewById(R.id.btn_all_window_up);
		mAllWindowDown = (Button) this.findViewById(R.id.btn_all_window_down);
		
		mLfWindow_ll = (RelativeLayout) this.findViewById(R.id.id_lfwindow_ll);
		mRfWindow_ll = (RelativeLayout) this.findViewById(R.id.id_rfwindow_ll);
		mLrWindow_ll = (RelativeLayout) this.findViewById(R.id.id_lrwindow_ll);
		mRrWindow_ll = (RelativeLayout) this.findViewById(R.id.id_rrwindow_ll);
		mSkyWindow_ll = (RelativeLayout) this.findViewById(R.id.id_skywindow_ll);
		mAllWindow_ll = (RelativeLayout) this.findViewById(R.id.id_allwindow_ll);
		
		mBtnBack.setOnClickListener(this);
		mLfWindowUp.setOnClickListener(this);
		mLfWindowDown.setOnClickListener(this);
		mRfWindowUp.setOnClickListener(this);
		mRfWindowDown.setOnClickListener(this);
		mLrWindowUp.setOnClickListener(this);
		mLrWindowDown.setOnClickListener(this);
		mRrWindowUp.setOnClickListener(this);
		mRrWindowDown.setOnClickListener(this);
		mSkyWindowUp.setOnClickListener(this);
		mSkyWindowDown.setOnClickListener(this);
		mAllWindowUp.setOnClickListener(this);
		mAllWindowDown.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		
		super.onPause();
	}

	@Override
	protected void onResume() {
		
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		
		try {
			switch (v.getId()) {
			case R.id.btn_lf_window_up://左前升窗
				sendWindowCmd(1, 2);
				break;
			case R.id.btn_lf_window_down://左前降窗
				sendWindowCmd(1, 1);
				break;
			case R.id.btn_rf_window_up://右前升窗
				sendWindowCmd(2, 2);
				break;
			case R.id.btn_rf_window_down://右前降窗
				sendWindowCmd(2, 1);
				break;
			case R.id.btn_lr_window_up://左后升窗
				sendWindowCmd(4, 2);
				break;
			case R.id.btn_lr_window_down://左后降窗
				sendWindowCmd(4, 1);
				break;
			case R.id.btn_rr_window_up://右后升窗
				sendWindowCmd(8, 2);
				break;
			case R.id.btn_rr_window_down://右后降窗
				sendWindowCmd(8, 1);
				break;
			case R.id.btn_sky_window_up://天窗
				sendWindowCmd(16, 1);
				break;
			case R.id.btn_sky_window_down://天窗
				sendWindowCmd(16, 2);
				break;
			case R.id.btn_all_window_up://所有升窗
				sendWindowCmd(0x1f, 2);
				break;
			case R.id.btn_all_window_down://所有降窗
				sendWindowCmd(0x1f, 1);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void sendWindowCmd(int cmd, int param) {
		
		RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x02, cmd, param, 0});
	}

}
