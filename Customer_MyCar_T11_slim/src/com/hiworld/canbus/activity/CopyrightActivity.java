package com.hiworld.canbus.activity;

import java.util.ArrayList;
import java.util.List;

import com.hiworld.canbus.util.CopyViewPagerAdapter;
import com.hiworld.mycar.t11.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CopyrightActivity extends BaseActivity implements OnPageChangeListener
{

	private ViewPager vp;
	private CopyViewPagerAdapter vpAdapter;
    private List<View> views;

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htpms_copyright);
		Log.d("CopyrightActivity", "initViews");
		// 初始化页面
        initViews();

        Log.d("CopyrightActivity", "initDots");
        // 初始化底部小点
        initDots();
	}

	private void initDots()
	{
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll_Copyright);
		//Log.d("CopyrightActivity", "initDots1");
        dots = new ImageView[views.size()];
       // Log.d("CopyrightActivity", "initDots2");
        // 循环取得小点图片
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            //dots[i].setEnabled(true);// 都设为灰色
            dots[i].setImageResource(R.drawable.dot);
        }
        //Log.d("CopyrightActivity", "initDots3");
        currentIndex = 0;
       // dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
        dots[currentIndex].setImageResource(R.drawable.dot_d);
	}

	private void initViews()
	{
		
		LayoutInflater inflater = LayoutInflater.from(this);
		//Log.d("CopyrightActivity", "initViews1");
        views = new ArrayList<View>();
        // 初始化引导图片列表

        views.add(inflater.inflate(R.layout.activity_copyright_one, null));
        views.add(inflater.inflate(R.layout.activity_copyright_two, null));

        //Log.d("CopyrightActivity", "initViews2");
       // 初始化Adapter
        vpAdapter = new CopyViewPagerAdapter(views, this);
        //Log.d("CopyrightActivity", "initViews3");
        vp = (ViewPager) findViewById(R.id.viewpagerCopyright);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);
	}

	@Override
	protected void onDestroy()
	{
		
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		
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
