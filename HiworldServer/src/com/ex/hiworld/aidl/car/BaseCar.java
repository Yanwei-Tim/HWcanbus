package com.ex.hiworld.aidl.car;

import android.os.RemoteException;

import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.ITaskCallback;
import com.syu.ipc.IRemoteModule;

/**
 * Created by APP03 on 2018/6/23.
 */

public class BaseCar extends ITaskBinder.Stub {
    public void in(){}
    public void out(){}
    public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws RemoteException {    }
    public void onHandler(int[] data){};
    @Override
    public void sendByte(int code, byte[] bytes) throws RemoteException {

    }

    @Override
    public void sendCmd(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void sendUpdate(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void registerCallback(ITaskCallback cb, int code) throws RemoteException {

    }

    @Override
    public void unregisterCallback(ITaskCallback cb, int code) throws RemoteException {

    }
}
