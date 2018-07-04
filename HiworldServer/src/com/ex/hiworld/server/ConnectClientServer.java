package com.ex.hiworld.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import com.ex.hiworld.server.tools.Utils;

import java.util.Random;

/**
 * Created by APP03 on 2018/6/22.
 */

public class ConnectClientServer implements ServiceConnection, Runnable {
    static Handler mH;

    static {
        HandlerThread handlerThread = new HandlerThread("c-client");
        handlerThread.start();
        mH = new Handler(handlerThread.getLooper());
    }

    public void startCanBusSrv() {
        if (!Utils.checkAppExist(FinalData.HW_CLIENT_PKG)) return;
        Intent ii = new Intent(FinalData.HW_CLIENT_ACTION);
        ii.setPackage(FinalData.HW_CLIENT_PKG);
        try {
            MyApp.getInstance().bindService(ii, this, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        LogsUtils.i(" startCanBusSrv ");
    }

    public void startCarPcSrv() {
        if (!Utils.checkAppExist(FinalData.HW_CLIENT_CARPC_PKG)) return;
        Intent ii = new Intent(FinalData.HW_CLIENT_CARPC_ACTION);
        ii.setPackage(FinalData.HW_CLIENT_CARPC_PKG);
        try {
            MyApp.getInstance().bindService(ii, this, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        startCanBusSrv();
        startCarPcSrv();
        mH.postDelayed(this, 1000 + new Random().nextInt(3000));
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
