package com.ex.hiworld.aidl;

import android.os.RemoteException;
@Deprecated
public class RemoteTaskProxy extends ITaskBinder.Stub {
    private static final RemoteTaskProxy INSTANCE = new RemoteTaskProxy();

    public RemoteTaskProxy() {

    }

    public static RemoteTaskProxy getInstance() {
        return INSTANCE;
    }

    private ITaskBinder mTaskBinder;

    public RemoteTaskProxy(ITaskBinder iTaskBinder) {
        mTaskBinder = iTaskBinder;
    }

    public ITaskBinder getTaskBinder() {
        return mTaskBinder;
    }

    public void setTaskBinder(ITaskBinder iTaskBinder) {
        mTaskBinder = iTaskBinder;
    }

    @Override
    public void registerCallback(ITaskCallback cb, int code) {
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.registerCallback(cb, code);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void unregisterCallback(ITaskCallback cb, int code){
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.unregisterCallback(cb, code);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void sendByte(int code, byte[] bytes)  {
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.sendByte(code, bytes);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.cmd(cmdCode, ints, flts, strs);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void sendCmd(int code, int[] ints) {
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.sendCmd(code, ints);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void sendUpdate(int code, int[] ints){
        final ITaskBinder iTaskBinder = mTaskBinder;
        try {
            if (iTaskBinder != null) {
                iTaskBinder.sendUpdate(code, ints);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
