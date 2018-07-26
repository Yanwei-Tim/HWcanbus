package com.hiworld.canbus.activity;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.canbus.util.ViewPagerAdapter;
import com.hiworld.mycar.t11.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
* 功能简介
* Copyright: Hiworld
* Author: Hardy.lai
* Date: 10/22/2015
*/
public class GuideActivity extends BaseActivity implements OnPageChangeListener{

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
    private List<View> views;

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htpms_guide);
		// 初始化页面
        initViews();

        // 初始化底部小点
        initDots();

	}

	private void initDots()
	{
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        dots = new ImageView[views.size()];

        // 循环取得小点图片
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            //dots[i].setEnabled(true);// 都设为灰色
            dots[i].setImageResource(R.drawable.dot);
        }

        currentIndex = 0;
       // dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
        dots[currentIndex].setImageResource(R.drawable.dot_d);

	}

	private void initViews()
	{
		
		LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        // 初始化引导图片列表

        views.add(inflater.inflate(R.layout.what_new_two, null));
        views.add(inflater.inflate(R.layout.what_new_one, null));
        views.add(inflater.inflate(R.layout.what_new_three, null));
        views.add(inflater.inflate(R.layout.what_new_four, null));


       // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views, this);

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);

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
	public void onPageScrollStateChanged(int arg0)
	{
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		
		
	}

	@Override
	public void onPageSelected(int position)
	{
		
		// 设置底部小点选中状态
        setCurrentDot(position);

	}

	private void setCurrentDot(int position)
	{
		
		if (position < 0 || position > views.size() - 1
                || currentIndex == position) {
            return;
        }

		dots[position].setImageResource(R.drawable.dot_d);
        //dots[position].setEnabled(false);
        //dots[currentIndex].setEnabled(true);
		dots[currentIndex].setImageResource(R.drawable.dot);

        currentIndex = position;

	}

	

}
