package com.hiworld.canbus.activity;

import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

/**
* 服务条款
* Copyright: Hiworld
* Author: Hardy.lai
* Date: 10/22/2015
*/
public class ServerActivity extends BaseActivity implements OnClickListener{

	//勾选
	private CheckBox m_ChkNext;
	private Button m_BtnAgree;
	private Button m_BtnUnagree;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htpms_server);
		init();
	}

	/**
	* 初始化控件
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	private void init()
	{
		
		m_ChkNext = (CheckBox)findViewById(R.id.check_agree);
		m_BtnAgree = (Button)findViewById(R.id.btn_agree);
		m_BtnUnagree = (Button)findViewById(R.id.btn_unagree);
		m_ChkNext.setOnClickListener(this);
		m_BtnAgree.setOnClickListener(this);
		m_BtnUnagree.setOnClickListener(this);
		
		SharedPreferences preferences = getSharedPreferences(
        		ConstData.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        boolean isServer = preferences.getBoolean(ConstData.ISAGREESERVER, false);
        m_ChkNext.setChecked(isServer);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onOptionsItemSelected(item);
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
		
		switch (v.getId())
		{
		case R.id.btn_agree:
			// 读取SharedPreferences中需要的数据
	        // 使用SharedPreferences来记录程序的使用次数
	        SharedPreferences preferences = getSharedPreferences(
	        		ConstData.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
	        boolean isGuide = preferences.getBoolean(ConstData.ISGUIDE, false);
	        Editor editor = preferences.edit();
	        editor.putBoolean(ConstData.ISAGREESERVER, m_ChkNext.isChecked());
	        editor.commit();
	        
	        Log.d("ddd", "isGuide ="+isGuide);
	        if (!isGuide)
			{
				goGuid();
			} else
			{
				goHome();
			}
			break;
		case R.id.btn_unagree:
			ServerActivity.this.finish();
			break;
		default:
			break;
		}
	}

	/**
	* 跳转到主页
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	private void goHome()
	{
		
		Log.i("ddd", "goHome");
		Intent intent = new Intent(ServerActivity.this, MainActivity.class);
		ServerActivity.this.startActivity(intent);
		ServerActivity.this.finish();
	}

	/**
	* 跳转到功能简介
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	private void goGuid()
	{
		
		Intent intent = new Intent(ServerActivity.this, GuideActivity.class);
		ServerActivity.this.startActivity(intent);
		ServerActivity.this.finish();
	}

}
