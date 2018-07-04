package com.hiworld.fragment.golf;

import android.app.Fragment;
import tools.LogsUtils;

public abstract class BaseFragment extends Fragment{
	@Override
	public void onResume() {
		LogsUtils.i(getClass().getName() + ": onResume");
		addNotify();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		LogsUtils.i(getClass().getName() + "onPause");
		removeNotify();
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		LogsUtils.i(getClass().getName() + "onDestroy");
		super.onDestroy();
	}
	
	public abstract void addNotify();
	public abstract void removeNotify();
}
