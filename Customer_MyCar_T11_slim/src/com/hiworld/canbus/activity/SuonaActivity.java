package com.hiworld.canbus.activity;

import com.hiworld.canbus.parse.RequestCmdUtil;
import com.hiworld.mycar.t11.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SuonaActivity extends BaseActivity implements OnClickListener{

	private Button mBtnBack, mBtnSuona;
	private TextView mTitleTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_control_suona);
		
		initView();
	}

	private void initView() {
		
		mTitleTv = (TextView) this.findViewById(R.id.tv_headerTitle);
		mTitleTv.setText(R.string.suona_control);
		mBtnSuona= (Button) this.findViewById(R.id.btn_suona);
		
		mBtnBack.setOnClickListener(this);
		mBtnSuona.setOnClickListener(this);
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
			case R.id.btn_suona:
				RequestCmdUtil.getInstance().sendVissCmd(new int[] {0x04, 0x3b, 0x05, 0x01, 0x01, 0});
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
