package com.hiworld.canbus.carpc;



import com.ex.hiworld.aidl.task.TaskServerConnect;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Handler;

public class TheApp extends Application {
	private static TheApp sInstance;
	private static Handler sHandler;
	private static ActivityManager sActivityManager;
	
	public static TheApp getInstance() {
		return sInstance;
	}
	
	public static void postDelayed(Runnable runnable, int delay) {
		if (runnable == null) {
			return;
		}
		sHandler.postDelayed(runnable, delay);
	}
	
	public static void removeCallbacks(Runnable runnable) {
		if (runnable == null) {
			return;
		}
		sHandler.removeCallbacks(runnable);
	}
	
	public static void postDelayedSingle(Runnable runnable, int delay) {
		if (runnable == null) {
			return;
		}
		sHandler.removeCallbacks(runnable);
		sHandler.postDelayed(runnable, delay);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		initStatic(); 
		connectServer();
	}
	
	private void connectServer() { 
		TaskServerConnect.connect(this);
	}

	private void initStatic() {
		sInstance = this;
		sHandler = new Handler();
	}
	
	public static ActivityManager getActivityManager() {
		return sActivityManager;
	}
}
