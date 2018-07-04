package com.ex.hiworld.aidl.task;
 
import com.ex.canbus.DataCanbus;
import com.ex.hiworld.aidl.RemoteTaskProxy;

/**
 * Created by APP03 on 2018/6/23.
 */

public class Task_HandlerCanbus {

    public static final RemoteTaskProxy PROXY = RemoteTaskProxy.getInstance();

    public static void sendCmd(int cmd) {
        PROXY.cmd(cmd);
    }

    public static void sendCmd(int cmd, int val) {
        PROXY.cmd(cmd, val);
    }

    public static void sendCmd(int cmd, int val1, int val2) {
        PROXY.cmd(cmd, val1, val2);
    }
    
    public static void sendCmd(int cmd, int[] vals) {
        PROXY.cmd(cmd, vals, null, null);
    }

    public static void sendCmd(int cmd, int[] ints, float[] floats, String[] strings) {
        PROXY.cmd(cmd, ints, floats, strings);
    }


    public static void update(int updateCode, int[] ints) {
        if (ints == null || ints.length == 0)
            return;
        if (DataCanbus.DATA[updateCode] != ints[0]) {
            DataCanbus.DATA[updateCode] = ints[0];
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void update(int updateCode, int value) {
        if (DataCanbus.DATA[updateCode] != value) {
            DataCanbus.DATA[updateCode] = value;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
        }
    }

    public static void update(int updateCode, int[] ints, float[] flts, String[] strs) {
        if ((ints == null || ints.length == 0) && (strs == null || strs.length == 0))
            return;
        if (ints != null) {
            if (DataCanbus.DATA[updateCode] != ints[0]) {
                DataCanbus.DATA[updateCode] = ints[0];
            }
        }
        //	Print.log("LG", "ints strs update");
        DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, flts, strs);
    }


}
