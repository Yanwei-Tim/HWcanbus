package com.ex.hiworld.aidl.task;

import android.os.RemoteException;

import com.ex.hiworld.aidl.ITaskCallback;

/**
 * Created by APP03 on 2018/6/22.
 */

public class Task_CallbackCanBusBase extends ITaskCallback.Stub {
    public void in() {
    }

    public void out() {
    }

    @Override
    public void getByte(int code, byte[] bytes, int size, String strs) throws RemoteException {
    }

    @Override
    public void getCmd(int code, int[] ints, int size, String strs) throws RemoteException {
    }

    @Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {

    }

}
