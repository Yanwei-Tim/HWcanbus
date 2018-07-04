package com.ex.hiworld.server.canbus;

import com.ex.hiworld.server.syu.SendFunc;

/**
 * Created by APP03 on 2018/6/20.
 */

public class HandlerBackCarTrack {
    public static void CarBackTrackHandle(int data0, int data1) {
        int t1, WheelAngle, t2;

        //#if defined(FYT3000_SOHANG)||defined(SUPPORT_BACK_70_FRAME)
        // wheel angle  索航的需要转化为 0 到 70.暂时先这样
        t2 = 0;
        if ((data0 & 0x80) == 0x80) {
            t1 = data0 << 8 | data1;
            t1 = (~t1 + 1) & 0xffff;
            t2 = 1;
        } else {
            t1 = ((data0 & 0x7f) << 8) | data1;
        }

        WheelAngle = t1 / 25;
        if (WheelAngle > 20)
            WheelAngle = 20;

        if (t2 == 1) {
            WheelAngle = 20 - WheelAngle;
        } else {
            WheelAngle = WheelAngle + 20;
        }
        SendFunc.sendGuiji(WheelAngle % (40 + 1));
    }
}
