package com.hiworld.canbus.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.mycar.t11.R;

public class ServerActivitynew extends BaseActivity {

	private ImageButton mBack;
	private TextView mTextTitle;
	private LinearLayout mLinearLayout;
	//APP肤色图片资源集合
	private int[] skinColourList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_server);
		
		mLinearLayout =  (LinearLayout) findViewById(R.id.ll_server_bac);
		
		mTextTitle = (TextView) findViewById(R.id.iv_top_title);
		mTextTitle.setText(R.string.server_coycopy);
		mBack = (ImageButton) this.findViewById(R.id.iv_back);
        mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ServerActivitynew.this.finish();
			}
		});
        registerInterfilter();
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(ServerActivitynew.this);
		//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
		if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				mLinearLayout.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
			}else {
				mLinearLayout.setBackgroundResource(skinColourList[22]);//main_backgrond
			}
		}else {
			mLinearLayout.setBackgroundResource(skinColourList[22]);//main_backgrond
		}
	}
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			//获取皮肤图片资源 add by jiaqing.liu 
			skinColourList = TimeUtils.getInstance().getAllSkin(ServerActivitynew.this);
			//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
			if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
				if (CarPcInfo.getInstance().getM_iILL() == 1) {
					mLinearLayout.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
				}else {
					mLinearLayout.setBackgroundResource(skinColourList[22]);//main_backgrond
				}
			}else {
				mLinearLayout.setBackgroundResource(skinColourList[22]);//main_backgrond
			}
		}
	};
	
	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_ILL);
		registerReceiver(mReceiver, infiter);
	}

}
