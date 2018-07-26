package com.hiworld.canbus.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MainViewPager extends ViewPager{

	private Point downP = new Point();
	private Point moveP = new Point();
	
	public MainViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MainViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		
		if(arg0.getAction() == MotionEvent.ACTION_DOWN)
        {
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = (int) arg0.getX();
            downP.y = (int) arg0.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
//            getParent().requestDisallowInterceptTouchEvent(true);
        }
		
		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			moveP.x = (int) arg0.getX();
			moveP.y = (int) arg0.getY();
			if (Math.abs(moveP.x - downP.x) < Math.abs(moveP.y - downP.y)) {
				return super.onInterceptTouchEvent(arg0);
			}
		}
		if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
			moveP.x = (int) arg0.getX();
			moveP.y = (int) arg0.getY();
			if (Math.abs(moveP.x - downP.x) > Math.abs(moveP.y - downP.y)) {
				return true;
			}
		}
		return super.onInterceptTouchEvent(arg0);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		
		
		return super.onTouchEvent(arg0);
	}
}
