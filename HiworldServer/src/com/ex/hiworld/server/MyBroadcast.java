package com.ex.hiworld.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ex.hiworld.server.tools.LogsUtils;

/**
 * Created by APP03 on 2018/6/20.
 */

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogsUtils.i("onReceive " + getClass().getSimpleName() + ":" + intent.getAction());
    }
}
