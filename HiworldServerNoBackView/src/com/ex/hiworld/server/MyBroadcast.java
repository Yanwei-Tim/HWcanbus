package com.ex.hiworld.server;

import com.ex.hiworld.server.tools.LogsUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by APP03 on 2018/6/20.
 */

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogsUtils.i("onReceive " + getClass().getSimpleName() + ":" + intent.getAction());
    }
}
