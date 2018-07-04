package com.ex.hiworld.aidl;

import android.os.RemoteException;

import tools.LogsUtils;

public class CallbackTaskProxy extends ITaskCallback.Stub {

    private static final CallbackTaskProxy INSTANCE = new CallbackTaskProxy();

    public static CallbackTaskProxy getInstance() {
        return INSTANCE;
    }

    private OnReceiveCanbusListener onDataReceiveListener = null;
    private OnReciverIntsAble monitorIntsCallBack = null;//真数据

    public interface OnReceiveCanbusListener {
        public void onDataReceive(byte[] buffer, int size);

        public void OnGetServiceName(String serviceName);
    }

    public void setOnReceiveCanbusListener(
            OnReceiveCanbusListener dataReceiveListener) {
        onDataReceiveListener = dataReceiveListener;
    }


    public interface OnReciverIntsAble {
        public int getIntsCmd(int[] ints, int size, String strs);

        public int update(int[] ints, int size);
    }

    public void registerIntsCallBack(int getCode, OnReciverIntsAble vissByteCallBack) {
        monitorIntsCallBack = vissByteCallBack;
    }

    public void unregisterIntsCallBack(int getCode) {
        monitorIntsCallBack = null;
    }

    @Override
    public void getByte(int code, byte[] bytes, int size, String strs) throws RemoteException {
//        if (monitorIntsCallBack != null) {
//            monitorIntsCallBack.getByteReciver(bytes, size, strs);
//        }
    }

    @Override
    public void getCmd(int code, int[] ints, int size, String strs) throws RemoteException {
//        LogsUtils.i("callbacktaskproxy: getcmd" + code);
        if (monitorIntsCallBack != null) {
            monitorIntsCallBack.getIntsCmd(ints, size, strs);
        }
    }

    @Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        if (monitorIntsCallBack != null) {
            monitorIntsCallBack.update(ints, 0);
        }
    }

}
