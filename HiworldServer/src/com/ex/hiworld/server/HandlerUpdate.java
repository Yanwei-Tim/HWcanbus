package com.ex.hiworld.server;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by APP03 on 2018/6/8.
 */

public class HandlerUpdate {
    private final static Handler HANDLER = new Handler(Looper.getMainLooper());

    public void update(int cmd, int val) {
        update(cmd, new int[]{val});
    }

    public void update(int cmd, int[] vals) {
        update(cmd, vals, null, null);
    }

    public void update(int cmd, float flt) {
        update(cmd, null, new float[]{flt}, null);
    }

    public void update(int cmd, float[] vals) {
        update(cmd, null, vals, null);
    }

    public void update(int cmd, String val) {
        update(cmd, new String[]{val});
    }

    public void update(int cmd, String[] vals) {
        update(cmd, null, null, vals);
    }

    public void update(int cmd, int[] ints, float[] flts, String[] val) {
    }
}
