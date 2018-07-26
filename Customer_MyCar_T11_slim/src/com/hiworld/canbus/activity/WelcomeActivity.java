package com.hiworld.canbus.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hiworld.canbus.util.UtilityClass;
import com.hiworld.mycar.t11.R;

//娆㈣繋椤�
public class WelcomeActivity extends BaseActivity{

	private ViewPager mViewPager;
	private TextView text_start;
	private RadioGroup mGroup;
	private int[] firstImgId = {R.drawable.welcome_1_land,R.drawable.welcome_2_land,
			R.drawable.welcome_3_land,R.drawable.welcome_4_land};
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		preferences = getSharedPreferences("viss",MODE_PRIVATE);
		boolean isFirst = preferences.getBoolean("isFirst", true);
		if (isFirst) {
			setContentView(R.layout.activity_welcome);
			initView();
			setAdapter();
			setListener();
			UtilityClass.setPrefrenceBoolean(WelcomeActivity.this, "sendInfo", true);
		}else {
			setContentView(R.layout.activity_start);
			ImageView img_rotate = (ImageView) findViewById(R.id.img_rotate);
			//鍔ㄧ敾  
	        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);  
	        LinearInterpolator lin = new LinearInterpolator();//璁剧疆鍔ㄧ敾鍖�閫熻繍鍔�  
	        animation.setInterpolator(lin);  
	        img_rotate.startAnimation(animation);  
	        animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					
					Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
					startActivity(intent);
//					overridePendingTransition(R.anim.fade, R.anim.hold);//娣″叆娣″嚭
//					overridePendingTransition(R.anim.my_scale_action,//鏀惧ぇ
//							R.anim.my_alpha_action);
//					overridePendingTransition(R.anim.scale_rotate,//鏃嬭浆
//							R.anim.my_alpha_action);
					/*overridePendingTransition(R.anim.scale_translate_rotate,
							R.anim.my_alpha_action);*/
//					overridePendingTransition(R.anim.scale_translate,//宸︿笂瑙掕繘鍏�
//							R.anim.my_alpha_action);
//					overridePendingTransition(R.anim.hyperspace_in,
//							R.anim.hyperspace_out);
//					overridePendingTransition(R.anim.push_left_in,//宸﹁竟鎺ㄥ叆
//							R.anim.push_left_out);
//					overridePendingTransition(R.anim.push_up_in,//涓婅竟鎺ㄥ叆
//							R.anim.push_up_out);
//					overridePendingTransition(R.anim.slide_left,//涓よ竟鎺ㄥ叆
//							R.anim.slide_right);
//					overridePendingTransition(R.anim.wave_scale,
//							R.anim.my_alpha_action);
//					overridePendingTransition(R.anim.zoom_enter,
//							R.anim.zoom_exit);
					WelcomeActivity.this.finish();
				}
			});
		}
	}

	private void initView() {
		
		mViewPager = (ViewPager) findViewById(R.id.viewpage_first);
		text_start = (TextView) findViewById(R.id.text_exp);
		mGroup = (RadioGroup) findViewById(R.id.rg_first);
		((RadioButton)mGroup.getChildAt(0)).setChecked(true);
	}
	
	private void setAdapter() {
		
		mViewPager.setAdapter(new FirstAdapter());
	}
	
	class FirstAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			
			return firstImgId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			//super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView img = new ImageView(WelcomeActivity.this);
			img.setBackgroundResource(firstImgId[position]);
			container.addView(img);
			return img;
		}
	}
	
	private void setListener() {
		
		text_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
				preferences.edit().putBoolean("isFirst", false).commit();
			}
		});
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
				if (arg0 == 3) {
					text_start.setVisibility(View.VISIBLE);
				}else {
					text_start.setVisibility(View.GONE);
				}
				((RadioButton)mGroup.getChildAt(arg0)).setChecked(true);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
	}
}
