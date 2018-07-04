package com.hiworld.myview;

import com.hiworld.carcomputer.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class IconCheckBoxPreference extends CheckBoxPreference {
	private Context mContext;
	private CheckBox mCheckBox;

	public IconCheckBoxPreference(Context context) {
		super(context);
		
		this.mContext = context;
	}

	public IconCheckBoxPreference(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		this.setLayoutResource(R.layout.preference_chk_layout);
	}

	public IconCheckBoxPreference(final Context context, final AttributeSet attrs) {
		this(context, attrs, 0);
		this.mContext = context;
	}

	@Override
	protected void onBindView(final View view) {
		super.onBindView(view);

		final SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(mContext);
		mCheckBox = (CheckBox) view.findViewById(R.id.wallpaper_ismove);
		mCheckBox.setChecked(s.getBoolean(getKey(), false));

	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		return LayoutInflater.from(getContext()).inflate(R.layout.preference_chk_layout, parent, false);
	}

	// 这个方法是方便在点击事件的做处理
	public CheckBox getIconCheckbox() {
		return mCheckBox;
	}
}
