package com.ex.hiworld.server;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.ex.hiworld.server.tools.Ticks;

/**
 * Created by APP03 on 2018/6/8.
 */

public class MyApp extends Application {

    private static MyApp ins;
    private Handler mH = new Handler(Looper.getMainLooper());

    public static MyApp getInstance() {
        return ins;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ins = this;

        new Ticks().run();
        connectServer();
    }

    private void connectServer() {


        // 对下
        Intent intent = new Intent();
        intent.setClass(this, MyLinkHostServer.class);
        startService(intent);


    }
}
