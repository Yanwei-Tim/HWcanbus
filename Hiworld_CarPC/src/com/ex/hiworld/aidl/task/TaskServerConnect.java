package com.ex.hiworld.aidl.task;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper; 
import tools.IUiNotify;
import tools.LogsUtils;
import tools.Utils;

import com.ex.canbus.DataCanbus;
import com.ex.canbus.FinalCanbus;
import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.RemoteTaskProxy;

import java.util.Random;

/**
 * Created by APP03 on 2018/6/23.
 */

public class TaskServerConnect implements ServiceConnection {

    private static Context mContext;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable C = new Runnable() {
        @Override
        public void run() {
            connect(mContext);
            mHandler.postDelayed(C, 1000 + new Random().nextInt(3000));
        }
    };

    private static final TaskServerConnect OBJ = new TaskServerConnect();

    private static boolean isConnect = false;

    public static void connect(Context c) {
        mContext = c;
        if (!Utils.checkAppExist(mContext, Utils.HW_SRV_PKG)) return;

        Intent ii = new Intent(Utils.HW_SRV_ACTION);
        ii.setPackage(Utils.HW_SRV_PKG);
        try {
            c.bindService(ii, OBJ, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            LogsUtils.e("bindHWServer error " + e.getLocalizedMessage());
        }

        if (!isConnect)
            LogsUtils.i("connect to TaskServerConnect .... ");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isConnect = true;
        ITaskBinder iTaskBinder = ITaskBinder.Stub.asInterface(service);
        LogsUtils.i("onServiceConnected " + iTaskBinder);
        RemoteTaskProxy.getInstance().setTaskBinder(iTaskBinder);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_CANBUS_ID);
            }
        }, 500);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogsUtils.i("onServiceDisconnected TaskServerConnect");
        isConnect = false;
        mHandler.postDelayed(C, 1000 + new Random().nextInt(5000));
        RemoteTaskProxy.getInstance().setTaskBinder(null);
    }


    private static Task_CallbackCanBusBase getCallbackCanBusById(int id) {
        if(id != 10)
            return new Task_CallBack_Golf();

        return null;
    }

    private static final IUiNotify _CANBUS_ID = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            LogsUtils.i("Task_CallBackProxy 0000 . _CANBUS_ID" );
            Task_CallbackCanBusBase callbackCanBus = getCallbackCanBusById(DataCanbus.DATA[updateCode]);
            Task_CallBackProxy.getObj().setCanbus(callbackCanBus);
        }
    };

    static {
        DataCanbus.NOTIFY_EVENTS[FinalCanbus.U_CANBUS_ID].addNotify(_CANBUS_ID, 1);
    }
}
