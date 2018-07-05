package com.ex.hiworld.aidl.car;

import android.os.RemoteException;

import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.tools.LogsUtils;

/**
 * Created by APP03 on 2018/6/23.
 */

public class TaskCarRemote extends ITaskBinder.Stub {
    public static final TaskCarRemote OBJ = new TaskCarRemote();
    private BaseCar mCanbus;

    public void setCanbus(BaseCar car) {
        this.mCanbus = car;
    }

    public BaseCar getCanbus() {
        return mCanbus;
    }

    public static TaskCarRemote getOBJ() {
        return OBJ;
    }

    @Override
    public void sendByte(int code, byte[] bytes) throws RemoteException {

    }

    @Override
    public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        LogsUtils.i("cmd ... " + cmdCode + " >> " + LogsUtils.toHexString(ints) + " @ "+mCanbus );
        if (mCanbus != null) {
            mCanbus.cmd(cmdCode, ints, flts, strs);
        } 
    }

    @Override
    public void sendCmd(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void sendUpdate(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void registerCallback(ITaskCallback callback, int updateCode) throws RemoteException {
        if (callback == null) return;
        HandlerTaskCanbus.reg(callback);

        if (updateCode < FinalCanbus.U_MISC_BEGIN) {
            BaseCar canbus = mCanbus;
            if (canbus != null) {
                canbus.registerCallback(callback, updateCode);
            }

//            LogsUtils.i(" registerCallback and update");
            HandlerTaskCanbus.update(updateCode);
        } else {
            switch (updateCode) {
                case FinalCanbus.U_CANBUS_ID:
                    LogsUtils.i("reg U_CANBUS_ID");
                    HandlerTaskCanbus.update(updateCode);
                    break;
                case FinalCanbus.U_CANBUS_VER:
                    LogsUtils.i("reg U_CANBUS_VER");
                    HandlerTaskCanbus.update(updateCode);
                    break;
            }
        }
    }

    @Override
    public void unregisterCallback(ITaskCallback cb, int code) throws RemoteException {
        HandlerTaskCanbus.unreg(cb);
    }
}
