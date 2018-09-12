package com.ex.hiworld.aidl.car;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by APP03 on 2018/6/23.
 */

public class TaskServer extends Service {
    @Override
    public IBinder onBind(Intent intent) {
//        LogsUtils.i("TaskServer onBind");
        return TaskCarRemote.getOBJ();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        LogsUtils.i("TaskServer onCreate");
    }
}
