package com.hiworld.canbus.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//设置app字体不随系统字体改变大小
	    Resources res = super.getResources();  
	    Configuration config=new Configuration();  
	    config.setToDefaults();  
	    res.updateConfiguration(config,res.getDisplayMetrics());
	}

}
