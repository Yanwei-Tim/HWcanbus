package com.ex.hiworld.server.canbus;

import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.SendFunc;

/**
 * Created by APP03 on 2018/6/20.
 */

public class HandlerKeyCode {
    private static int TemCanKey;
    private static int CanKey;

    public static void onKeyEvent(int[][] KeyCanKeyTable, int keycode, int action) {
        CanKey = keycode;
        int i, j = 0;
        for (i = 0; i < KeyCanKeyTable.length; i++) {
            if (CanKey == KeyCanKeyTable[i][0]) {
                j = i;
                if (CanKey != 0)
                    TemCanKey = j;
                break;
            }
        }

        if ((CanKey != 0) && (action != 0)) {
            if (i < KeyCanKeyTable.length)
                SendFunc.sendKeyCode2Host(KeyCanKeyTable[i][1], FinalKeyCode.ACTION_DOWN);
        } else {
            if (((TemCanKey != 0xff) && (action & 0xff) == 0))
                SendFunc.sendKeyCode2Host(KeyCanKeyTable[TemCanKey][1], FinalKeyCode.ACTION_UP);
            TemCanKey = 0xff;
        }


    }
}
