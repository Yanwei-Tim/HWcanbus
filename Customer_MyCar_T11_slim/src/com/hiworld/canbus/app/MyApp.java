package com.hiworld.canbus.app; 
import com.hiworld.canbus.receiver.WarnBroadCast;
import com.hiworld.canbus.util.CrashApphandler;
import com.youzi.customer.connect.TheApp;

public class MyApp extends TheApp {

	@Override
	public void onCreate() {

		super.onCreate();

		System.out.println("onCreate  MyApp!");
		// WarnBroadcastReceiver.getInstance().init(this);
		WarnBroadCast.getOBJ().initSoundPool(this);

		// 崩溃日志收集
		CrashApphandler.getInstance().init(this);

	}

}
