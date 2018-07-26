package com.hiworld.canbus.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NaviFontTextView extends TextView {
	
	public NaviFontTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public NaviFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public NaviFontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		
		AssetManager assetMgr = context.getAssets();
		Typeface font = Typeface.createFromAsset(assetMgr, "fonts/BMWHelvetica-Bold.otf");
		setTypeface(font);
	}
}
