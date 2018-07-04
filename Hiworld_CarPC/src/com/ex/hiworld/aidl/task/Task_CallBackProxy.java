package com.ex.hiworld.aidl.task;

import android.os.RemoteException;

import com.ex.canbus.FinalCanbus;
import com.ex.hiworld.aidl.ITaskCallback;

/**
 * Created by APP03 on 2018/6/23.
 */

public class Task_CallBackProxy extends ITaskCallback.Stub {

    public static final Task_CallBackProxy OBJ = new Task_CallBackProxy();

    public static Task_CallBackProxy getObj() {
        return OBJ;
    }

    private Task_CallbackCanBusBase Canbus;

    public void setCanbus(Task_CallbackCanBusBase bus) {
        if (bus == null) return;
        if (Canbus != bus) {
            if (Canbus != null)
                Canbus.out();
            Canbus = bus;
            bus.in();
        }
    }

    @Override
    public void getByte(int code, byte[] bytes, int size, String strs) throws RemoteException {
        if (Canbus != null) {
            Canbus.getByte(code, bytes, size, strs);
        }
    }

    @Override
    public void getCmd(int code, int[] ints, int size, String strs) throws RemoteException {
//        LogsUtils.i("Task_CallBackProxy getcmd " + code);
        if (Canbus != null)
            Canbus.getCmd(code, ints, size, strs);
    }

    @Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
//        LogsUtils.i("Task_CallBackProxy: update " + updateCode + " " + (ints != null ? ints[0] : ""));

        if (updateCode >= 0 && updateCode < FinalCanbus.U_MISC_BEGIN) {
            if (Canbus != null)
                Canbus.update(updateCode, ints, flts, strs);
        } else {
            switch (updateCode) {
                case FinalCanbus.U_CANBUS_ID:
                    assert ints != null;
                    Task_HandlerCanbus.update(updateCode, ints[0]);
                    break;
            }
        }
    }
}
