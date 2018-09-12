package com.ex.hiworld.aidl;

import com.ex.hiworld.aidl.ITaskCallback;

interface ITaskBinder {   
    void sendByte(int code, in byte[] bytes);
    void cmd(int cmdCode, in int[] ints, in float[] flts, in String[] strs);
    void sendCmd(int code, in int[] ints);
    void sendUpdate(int code, in int[] ints);
    void registerCallback(ITaskCallback cb, int code);   
    void unregisterCallback(ITaskCallback cb, int code);
}  