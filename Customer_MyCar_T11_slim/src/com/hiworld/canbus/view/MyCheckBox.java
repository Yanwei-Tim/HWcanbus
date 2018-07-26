package com.hiworld.canbus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CheckBox;

//自定义CheckBox，实现点击后不改变check的状态

public class MyCheckBox extends CheckBox{

	private OnBoxClickListener listener;
	
	public void setOnBoxCLickListener(OnBoxClickListener listener){
		this.listener = listener;
	}
	
	public MyCheckBox(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
		
	}
	public MyCheckBox(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (listener != null) {
				listener.onBoxClick(this);
			}
		}
		return super.onTouchEvent(event);
	}
}
