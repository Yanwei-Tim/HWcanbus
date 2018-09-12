package com.ex.hiworld.server;

import com.ex.hiworld.server.tools.DBUtils;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.PrintScreenView;
import com.ex.hiworld.server.tools.Ticks;
import android.app.Application;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by APP03 on 2018/6/8.
 */

public class MyApp extends Application {

    private static MyApp ins;
	private static WindowManager wm;
	private static int widthPixels;
	private static int heightPixels;
    public static MyApp getInstance() {
        return ins;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        new Ticks().run();
        connectServer(); 
    }

    private void init() {
        ins = this;
        LogsUtils.i("ServerAPP Create");
        PrintScreenView.init(this);           
        PrintScreenView.getMsgView().msg(" DEBUGMODE.. " + BuildConfig.DEBUG);  
        DBUtils.getInstance().init(this);
        

		wm = (WindowManager) getInstance().getSystemService(WINDOW_SERVICE);
		DisplayMetrics o =new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(o);
		widthPixels = o.widthPixels;
		heightPixels = o.heightPixels;
	}

	private void connectServer() { 
        // 对下
        Intent intent = new Intent();
        intent.setClass(this, MyLinkHostServer.class);
        startService(intent);
    }

	public static int getWidth() {
		return widthPixels;
	}

	public static int getHeight() {
		return heightPixels;
	}
}
