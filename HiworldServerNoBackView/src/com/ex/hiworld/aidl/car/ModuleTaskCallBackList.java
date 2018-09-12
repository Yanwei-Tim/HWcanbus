package com.ex.hiworld.aidl.car;

import java.util.BitSet;

import com.ex.hiworld.aidl.ITaskCallback;

import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

/**
 * Created by APP03 on 2018/6/20.
 */
@Deprecated
public class ModuleTaskCallBackList extends RemoteCallbackList<ITaskCallback> {

    @Override
    public synchronized boolean register(ITaskCallback callback) {
        return super.register(callback);
    }

    @Override
    public synchronized boolean unregister(ITaskCallback callback) {
        return super.unregister(callback);
    }

    public static void register(ModuleTaskCallBackList list, ITaskCallback callback, int updateCode) {
        synchronized (callback) {
            BitSet bitSet = getBitSet(list, callback);
            if (bitSet == null) {
                bitSet = new BitSet(128);
                bitSet.set(updateCode);
                list.register(callback, bitSet);
            } else {
                bitSet.set(updateCode);
            }
        }
    }

    public static void unregister(ModuleTaskCallBackList list, ITaskCallback callback, int updateCode) {
        synchronized (callback) {
            BitSet bitSet = getBitSet(list, callback);
            if (bitSet != null) {
                bitSet.clear(updateCode);
                if (bitSet.isEmpty())
                    list.unregister(callback);
            } else {
                bitSet.set(updateCode);
            }
        }
    }


    private static BitSet getBitSet(ModuleTaskCallBackList list, ITaskCallback callback) {
        BitSet bitSet = null;
        IBinder binder = callback.asBinder();
        int N = list.beginBroadcast();
        for (int i = 0; i < N; i++) {
            if (binder.equals(list.getBroadcastItem(i))) {
                bitSet = (BitSet) list.getBroadcastCookie(i);
                break;
            }
        }
        return bitSet;
    }


    public synchronized void getCmd(int update, int[] ints) {
        int N = beginBroadcast();
        for (int i = 0; i < N; i++) {
            if (((BitSet) getBroadcastCookie(i)).get(update)) {
                ITaskCallback callBack = getBroadcastItem(i);
                synchronized (callBack) {
                    try {
                        callBack.getCmd(update, ints,0, "");
                    } catch (Exception e) {

                    }
                }
            }
        }
        finishBroadcast();
    }

    public synchronized void update(int update, int[] ints, float[] floats, String[] strings) {
        int N = beginBroadcast();
        for (int i = 0; i < N; i++) {
            if (((BitSet) getBroadcastCookie(i)).get(update)) {
                ITaskCallback callBack = getBroadcastItem(i);
                synchronized (callBack) {
                    try {
                        callBack.update(update, ints, floats, strings);
                    } catch (Exception e) {

                    }
                }
            }
        }
        finishBroadcast();
    }

    public static void update(ModuleTaskCallBackList[] array, int updateCode, int value) {
        if (array != null && updateCode >= 0 && updateCode < array.length && array[updateCode] != null) {
            array[updateCode].update(updateCode, new int[]{value}, null, null);
        }
    }

    public static void update(ModuleTaskCallBackList[] array, int[] data, int updateCode, int value) { 
        if (data != null && updateCode >= 0 && updateCode < data.length) {
            if (data[updateCode] != value) {
                data[updateCode] = value;
                if (array != null && updateCode < array.length && array[updateCode] != null) {
                    array[updateCode].update(updateCode, new int[]{value}, null, null);
                }
            }
        }
    }

    public static void update(ITaskCallback callBack, int updateCode, int value) {
        if (callBack == null) return;
        synchronized (callBack) {
            try {
                callBack.update(updateCode, new int[]{value}, null, null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(ITaskCallback callBack, int updateCode, int val1, int val2) {
        if (callBack == null) return;
        synchronized (callBack) {
            try {
                callBack.update(updateCode, new int[]{val1, val2}, null, null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
