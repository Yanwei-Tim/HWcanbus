package com.ex.hiworld.server.canbus;

import com.ex.hiworld.server.syu.FinalBt;

/**
 * Created by APP03 on 2018/6/21.
 */

public class HandlerBtPhone {

    public static String getPhoneStateDes(int state){
        String Str = "";
        switch (state) {
            case FinalBt.PHONE_STATE_DISCONNECTED:
                Str = "BT DISCONNECT";
                break;
            case FinalBt.PHONE_STATE_CONNECTED:
                Str = "BT CONNECTED";
                break;
            case FinalBt.PHONE_STATE_LINK:
                Str = "BT LINK";
                break;
//			case FinalBt.PHONE_STATE_INVALID:
//			case FinalBt.PHONE_STATE_LOAD:
            case FinalBt.PHONE_STATE_PAIR:
                Str = "BT PAIRING";
                break;
            case FinalBt.PHONE_STATE_RING:
                Str = "BT RINGING";
                break;
            case FinalBt.PHONE_STATE_TALK:
                Str = "BT TALKING";
                break;
            case FinalBt.PHONE_STATE_DIAL:
                Str = "BT DIALING";
                break;
            default:
                Str = "  ";
                break;
        }
        return Str;
    }
}
