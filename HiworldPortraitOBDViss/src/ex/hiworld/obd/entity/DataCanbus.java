package ex.hiworld.obd.entity;

import ex.hiworld.obd.FinalOBD;
import tools.UiNotifyEvent;

public class DataCanbus { 
	
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[FinalOBD.U_CNT_MAX];
	public static int sLimiteSpeed = -1;

    static {
        for (int i = 0; i < FinalOBD.U_CNT_MAX; i++)
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
    }
}