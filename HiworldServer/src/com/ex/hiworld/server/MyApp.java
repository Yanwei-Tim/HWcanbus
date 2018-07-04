package com.ex.hiworld.server;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.ex.hiworld.server.tools.PrintScreenView;
import com.ex.hiworld.server.tools.Ticks;

/**
 * Created by APP03 on 2018/6/8.
 */

public class MyApp extends Application {

    private static MyApp ins;
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
        PrintScreenView.init(this);           
        PrintScreenView.getMsgView().msg(" DEBUGMODE.. " + BuildConfig.DEBUG);  
	}

	private void connectServer() { 
        // 对下
        Intent intent = new Intent();
        intent.setClass(this, MyLinkHostServer.class);
        startService(intent);


    }
}
