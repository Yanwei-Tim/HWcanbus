package com.hiworld.canbus.activity;


import com.hiworld.canbus.parse.RequestCmdUtil;
import com.hiworld.mycar.t11.R;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LightActivity extends BaseActivity implements OnClickListener{

	private Button mBtnBack;
	private TextView mTitleTv;
	private Button mBtnFarlightOpen, mBtnNearlylightOpen, mBtnDoublelightOpen,
	                        mBtnFarlightClose, mBtnNearlylightClose, mBtnDoublelightClose;
	//private CheckBox mCheckNearlyLight, mCheckFarLight, mCheckDoubleLight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_light);
		
		initView();
	}

	private void initView() {
		
		mTitleTv = (TextView) this.findViewById(R.id.tv_headerTitle);
		mTitleTv.setText(R.string.light_control);
		
		mBtnFarlightOpen = (Button) this.findViewById(R.id.btn_farlight_open);
		mBtnFarlightClose = (Button) this.findViewById(R.id.btn_farlight_close);
		mBtnNearlylightOpen = (Button) this.findViewById(R.id.btn_nearlylight_open);
		mBtnNearlylightClose = (Button) this.findViewById(R.id.btn_nearlylight_close);
		mBtnDoublelightOpen = (Button) this.findViewById(R.id.btn_doublelight_open);
		mBtnDoublelightClose = (Button) this.findViewById(R.id.btn_doublelight_close);
		
//		mCheckNearlyLight = (CheckBox) this.findViewById(R.id.chb_nearlylight);
//		mCheckFarLight = (CheckBox) this.findViewById(R.id.chb_farlight);
//		mCheckDoubleLight = (CheckBox) this.findViewById(R.id.chb_doublelight);
		
		mBtnBack.setOnClickListener(this);
//		mCheckNearlyLight.setOnClickListener(this);
//		mCheckFarLight.setOnClickListener(this);
//		mCheckDoubleLight.setOnClickListener(this);
		mBtnFarlightOpen.setOnClickListener(this);
		mBtnFarlightClose.setOnClickListener(this);
		mBtnNearlylightOpen.setOnClickListener(this);
		mBtnNearlylightClose.setOnClickListener(this);
		mBtnDoublelightOpen.setOnClickListener(this);
		mBtnDoublelightClose.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x01, 0x02, 0});
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x02, 0x02, 0});
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x05, 0x02, 0});
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		

		
	}

	@Override
	public void onClick(View v) {
		
		try {
			switch (v.getId()) {
			case R.id.btn_farlight_open:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x01, 0x01, 0});
				break;
			case R.id.btn_farlight_close:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x01, 0x02, 0});
				break;
			case R.id.btn_nearlylight_open:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x02, 0x01, 0});
				break;
			case R.id.btn_nearlylight_close:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x02, 0x02, 0});
				break;
			case R.id.btn_doublelight_open:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x05, 0x01, 0});
				break;
			case R.id.btn_doublelight_close:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x05, 0x02, 0});
				break;
//			case R.id.chb_nearlylight:
//				if (CarPcInfo.getInstance().getM_iNearLight() == 1) {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x02, 0x02, 0});
//				} else {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x02, 0x01, 0});
//				}
//				break;
//			case R.id.chb_farlight:
//				if (CarPcInfo.getInstance().getM_iFarLight() == 1) {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x01, 0x02, 0});
//				} else {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x01, 0x01, 0});
//				}
//				break;
//			case R.id.chb_doublelight:
//				if (CarPcInfo.getInstance().getM_iDoubleLight() == 1) {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x05, 0x02, 0});
//				} else {
//					RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x04, 0x05, 0x01, 0});
//				}
//				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
