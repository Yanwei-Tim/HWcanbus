package com.ex.hiworld.aidl;

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
    // for simple use
    public void cmd(int cmdCode) {
        cmd(cmdCode, null, null, null);
    }

    public void cmd(int cmdCode, int value) {
        cmd(cmdCode, new int[]{value}, null, null);
    }

    public void cmd(int cmdCode, int value1, int value2) {
        cmd(cmdCode, new int[]{value1, value2}, null, null);
    }

//    public ModuleObject get(int cmdCode, int value) {
//        return get(cmdCode, new int[]{value}, null, null);
//    }
//
//    public int getI(int cmdCode, int defValue) {
//        ModuleObject obj = get(cmdCode, null, null, null);
//        if (obj != null && obj.ints != null && obj.ints.length > 0) {
//            return obj.ints[0];
//        }
//        return defValue;
//    }
//
//    public String getS(int cmdCode, int value) {
//        ModuleObject obj = get(cmdCode, new int[]{value}, null, null);
//        if (obj != null && obj.ints != null && obj.ints.length > 0) {
//            return obj.strs[0];
//        }
//        return null;
//    }
//
//    public String getS(int cmdCode, int value1, int value2) {
//        ModuleObject obj = get(cmdCode, new int[]{value1, value2}, null, null);
//        if (obj != null && obj.ints != null && obj.ints.length > 0) {
//            return obj.strs[0];
//        }
//        return null;
//    }

    @Override
    public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) {
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
