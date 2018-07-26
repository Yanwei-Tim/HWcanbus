package com.hiworld.canbus.activity;

import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
* 启动页
* 判断是否同意服务条款，（1）是进入功能介绍，否直接退出。（2）是进入主页
* Copyright: Hiworld
* Author: Hardy.lai
* Date: 10/22/2015
*/
public class BootPageActivity extends BaseActivity {

	private static final String TAG = BootPageActivity.class.getSimpleName();
	//是否勾选同意条款
	boolean isAgreeServer = false;
	//是否勾选功能条款
	boolean isGuide = false;
	//根据不同值跳转不同页面
	private static final int GO_HOME = 1000;//主页
	private static final int GO_SERVER = 1001;//服务条款
    private static final int GO_GUIDE = 1002;//功能简介
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 1500;
 
    private ImageView img_apalsh_welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acvity_welcome);
		Log.d(TAG, "onCreate()!");
		init();
	}
	
	
	

	@Override
	protected void onResume() {
		
		super.onResume();
	}




	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	/**
	* 初始化进入界面
	* 判断是否确定勾选,进入到不同的页面
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	private void init() {
		
		// 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
        		ConstData.SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用false作为默认值,默认没有勾选
        isAgreeServer = preferences.getBoolean(ConstData.ISAGREESERVER, false);
        isGuide = preferences.getBoolean(ConstData.ISGUIDE, false);
        
        Log.d(TAG, "isAgreeServer ="+isAgreeServer+",isGuide ="+isGuide);
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isAgreeServer) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_SERVER, SPLASH_DELAY_MILLIS);
        } else {
        	if (!isGuide) {
        		mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
			} else {
				mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
			}
        }
        
        img_apalsh_welcome = (ImageView)findViewById(R.id.img_slider_spalsh);
        //设置启动时候的动画效果
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        img_apalsh_welcome.setVisibility(View.VISIBLE);
        img_apalsh_welcome.startAnimation(animation);
	}
	
	/**
	* Handle线程
	* 根据不同消息跳转界面
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case GO_HOME://进入主页
				goHome();
				break;
			case GO_SERVER://进入服务条款
				goServer();
				break;
			case GO_GUIDE://进入功能简介
				goGuide();
				break;
			default:
				break;
			}
		}
		
	};

	/**
	* 跳转进入主页
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	protected void goHome() {
		
		Intent intent = new Intent(BootPageActivity.this, MainActivity.class);
		BootPageActivity.this.startActivity(intent);
		BootPageActivity.this.finish();
	}

	/**
	* 跳转进入服务条款
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	protected void goServer() {
		
		Intent intent = new Intent(BootPageActivity.this, ServerActivity.class);
		BootPageActivity.this.startActivity(intent);
		BootPageActivity.this.finish();
	}
	
	/**
	* 跳转进入功能简介
	* Copyright: Hiworld
	* Author: Hardy.lai
	* Date: 10/22/2015
	*/
	protected void goGuide() {
		
		Intent intent = new Intent(BootPageActivity.this, GuideActivity.class);
		BootPageActivity.this.startActivity(intent);
		BootPageActivity.this.finish();
	}

}
