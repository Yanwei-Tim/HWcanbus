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
import android.widget.TextView;

public class DoorlockActivity extends BaseActivity implements OnClickListener{

	private Button mBtnBack, mBtnTaxiBox;
	private TextView mTitleTv;
	private Button mCheckDoorlock;
	//private Button mCheckLockAutoWindow, mCheckDoorAutoDouble;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_control_doorlock);
		
		initView();
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setCarInfo(CarPcInfo.getInstance());
			}
		});
		
		FragmentParseData.getInstance().setDoorlockHandler(mHandler);
	}

	private void initView() {
		
		mTitleTv = (TextView) this.findViewById(R.id.tv_headerTitle);
		mTitleTv.setText(R.string.doorlock_control);
		
		mCheckDoorlock = (Button) this.findViewById(R.id.chb_doorlock);
		mBtnTaxiBox = (Button) this.findViewById(R.id.btn_taixbox);
		
		//mCheckLockAutoWindow = (Button) this.findViewById(R.id.chb_lockautowindow);
		//mCheckDoorAutoDouble = (Button) this.findViewById(R.id.chb_doorautodouble);
		
		mBtnBack.setOnClickListener(this);
		mCheckDoorlock.setOnClickListener(this);
		mBtnTaxiBox.setOnClickListener(this);
		//mCheckLockAutoWindow.setOnClickListener(this);
		//mCheckDoorAutoDouble.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		//RequestCmdUtil.getInstance().removeCmd(0xE0);
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		//RequestCmdUtil.getInstance().addArrayCmd(0xE0);
	}

	@Override
	public void onClick(View v) {
		
		try {
			switch (v.getId()) {
			case R.id.chb_doorlock:
				if (CarPcInfo.getInstance().getM_iInnerlock() == 1) {
					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x03, 0x01, 0x02, 0});
				} else {
					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x03, 0x01, 0x01, 0});
				}
				break;
			case R.id.btn_taixbox:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x03, 0x02, 0x02, 0});
				break;
//			case R.id.chb_lockautowindow:
//				if (CarPcInfo.getInstance().getiLockAutoWindow() == 1) {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x10, 0x01, 0x02, 0});
//				} else {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x10, 0x01, 0x01, 0});
//				}
//				break;
//			case R.id.chb_doorautodouble:
//				if (CarPcInfo.getInstance().getiDoorAutoDouble() == 1) {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x10, 0x02, 0x02, 0});
//				} else {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x10, 0x02, 0x01, 0});
//				}
//				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	
	protected void setCarInfo(CarPcInfo instance) {
		
		//mCheckDoorlock.setChecked(instance.getM_iInnerlock() == 0);
		if (instance.getM_iInnerlock() == 0) {
			mCheckDoorlock.setBackgroundResource(R.drawable.switch_open_xml);
		} else {
			mCheckDoorlock.setBackgroundResource(R.drawable.switch_close_xml);
		}
		
//		if (instance.getiLockAutoWindow() == 1) {
//			mCheckLockAutoWindow.setBackgroundResource(R.drawable.switch_open_xml);
//		} else {
//			mCheckLockAutoWindow.setBackgroundResource(R.drawable.switch_close_xml);
//		}
//		if (instance.getiDoorAutoDouble() == 1) {
//			mCheckDoorAutoDouble.setBackgroundResource(R.drawable.switch_open_xml);
//		} else {
//			mCheckDoorAutoDouble.setBackgroundResource(R.drawable.switch_close_xml);
//		}

	}

}
