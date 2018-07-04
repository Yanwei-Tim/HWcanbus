package com.ex.canbus;

import tools.UiNotifyEvent;
import com.ex.hiworld.aidl.RemoteTaskProxy;

public class DataCanbus {
	public static int sCanbusId, sCanbusIdOffset, sCanbusIdBase;
    public static final int[] DATA = new int[FinalCanbus.U_CNT_MAX];
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[FinalCanbus.U_CNT_MAX];

    static {
        for (int i = 0; i < FinalCanbus.U_CNT_MAX; i++)
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
    }
}