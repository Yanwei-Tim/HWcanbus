package com.hiworld.canbus.fragment;

import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class TripViewPaperAdapter extends PagerAdapter{

	// 界面列表
    private List<View> views;
    private Activity activity;
    
	public TripViewPaperAdapter(List<View> views, Activity activity){
		this.views = views;
        this.activity = activity;
	}
	
	
	
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		
		((ViewPager) container).removeView(views.get(position));
	}




	@Override
	public void finishUpdate(View container) {
		
	}




	@Override
	public int getCount() {
		
		if (views != null) {
            return views.size();
        }
		return 0;
	}
	
	
	

	@Override
	public Object instantiateItem(View container, int position) {
		
		((ViewPager) container).addView(views.get(position), 0);
		//position
		return views.get(position);
	}




	@Override
	public boolean isViewFromObject(View view, Object arg1) {
		
		return (view == arg1);
	}

}
