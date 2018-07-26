package com.hiworld.canbus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

public class MyRadioButton extends RadioButton{

	private OnBoxClickListener listener;
	
	public void setOnBoxCLickListener(OnBoxClickListener listener){
		this.listener = listener;
	}
	
	public MyRadioButton(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
		
	}
	public MyRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
