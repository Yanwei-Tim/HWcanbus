package com.hiworld.canbus.activity;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.util.UtilityClass;
import com.hiworld.mycar.t11.R;

//关于界面
public class AboutActivity extends BaseActivity implements OnClickListener{
	
	private TextView mTextTitle, text_vissVersion,text_appVersion,text_serverVersion,text_copyRight;
	private ImageButton mBack;
	private RelativeLayout rl_about;
	//APP肤色图片资源集合
	private int[] skinColourList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initView();
		registerInterfilter();
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(AboutActivity.this);
		/////ILL背光
		//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
		if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				rl_about.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
			}else {
				rl_about.setBackgroundResource(skinColourList[22]);//main_backgrond
			}
		}else {
			rl_about.setBackgroundResource(skinColourList[22]);//main_backgrond
		}
	}
	
	private void initView() {
		
		rl_about = (RelativeLayout) findViewById(R.id.rl_about_bac);
		mTextTitle = (TextView) findViewById(R.id.iv_top_title);
		mTextTitle.setText(R.string.mine_about);
		mBack = (ImageButton) this.findViewById(R.id.iv_back);
		mBack.setOnClickListener(this);
		text_appVersion = (TextView) findViewById(R.id.text_appVersion);
		text_appVersion.setText(getResources().getText(R.string.text_softVersion)+":"+UtilityClass.getPrefrenceString(AboutActivity.this, "appVersion"));
		text_vissVersion = (TextView) findViewById(R.id.text_vissVersion);
		text_serverVersion = (TextView) findViewById(R.id.text_serverVersion);
		
		text_copyRight = (TextView) findViewById(R.id.text_ch_copyRight);
//		if (VissCmdParse.getInstance().bBound) {
//			text_vissVersion.setText(getString(R.string.device_version)+CarInfo.getInstance().getM_strVer());
//		}else{
			text_vissVersion.setText(getString(R.string.device_version)+CarInfo.getInstance().getVer());
//		}
		text_serverVersion.setText(getResources().getString(R.string.text_serverVersion)+":"
				+MainActivity.m_strService);//VissCmdParse.getInstance().serverVersion
		if (isEnglish()) {
			text_copyRight.setVisibility(View.GONE);
		}else {
			text_copyRight.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		
		if (v != null) {
			switch (v.getId()) {
			case R.id.iv_back:
				AboutActivity.this.finish();
				break;
			default:
				break;
			}
		}
	}
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			
			//获取皮肤图片资源 add by jiaqing.liu 
			skinColourList = TimeUtils.getInstance().getAllSkin(AboutActivity.this);
			String action = intent.getAction();
			if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					rl_about.setBackgroundResource(skinColourList[22]);//main_backgrond
				}else {
					rl_about.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
				}
			} else {
				rl_about.setBackgroundResource(skinColourList[22]);//main_backgrond
			}
		}
	};
	
	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_ILL);
		registerReceiver(mReceiver, infiter);
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	private boolean isEnglish() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("en"))
            return true;
        else
            return false;
    }
}
