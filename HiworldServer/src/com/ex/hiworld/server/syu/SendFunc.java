package com.ex.hiworld.server.syu;

import android.R.integer;
import android.os.SystemClock;

import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.tools.LogsUtils;
import com.syu.remote.Remote;

/**
 * Created by APP03 on 2018/6/20.
 */

public class SendFunc {
    private static Remote autoTools;

    public static final int C_CANBUS_CMD_CANBUSDATA = 1000 + 13; // 所有的协议数据从这里发送出去
    //    public static final int C_CANBUS_CMD_KEYCODE = 1000 + 17; // 按键信息通过这个发送出去 FOR WC
    public static final int C_CANBUS_CMD_KEYCODE = 1000 + 14; // 按键信息通过这个发送出去  the old cmd

    // new int[]{cmd , type, para0}
    // cmd : 0 轨迹, 级数, 40/70级类型(忽略)
    // cmd : 1 雷达, 前后左右雷达类型( 0雷达开关, 1前左,2前左中,3前右中,4前右, 5后左,6后左中,7后右中,8后右), 等级数(0-9 , 0是不显示),
    // cmd : 2 外部温度, 0温度开关/1外部温度值, 0/1或具体温度值  温度值转成1000
    // cmd : 5 全景状态		状态值
    public static final int C_CANBUS_HOST_SET = 1000 + 15; // 有一些协议需要用到主机的 从这里发出 如外部温度，雷达，轨迹

    public static final int FL_RARA_ONOFF = 0;
    public static final int FL_RARA_FL = 1;
    public static final int FL_RARA_FML = 2;
    public static final int FL_RARA_FMR = 3;
    public static final int FL_RARA_FR = 4;
    public static final int FL_RARA_RL = 5;
    public static final int FL_RARA_RML = 6;
    public static final int FL_RARA_RMR = 7;
    public static final int FL_RARA_RR = 8;

	public static final int FL_RARA_RRF = 9; // 右前
 	public static final int FL_RARA_RRMF = 10; // 右中前
	public static final int FL_RARA_RRMR = 11; // 右中后
	public static final int FL_RARA_RRR = 12; // 右后
	public static final int FL_RARA_LF = 13;
	public static final int FL_RARA_LMF = 14;
	public static final int FL_RARA_LMR = 15;
	public static final int FL_RARA_LR = 16; 
	
	
    public static final int CMD_TYPE_GUIJI = 0;
    public static final int CMD_TYPE_RADAR = 1;
    public static final int CMD_TYPE_OUTTEMP = 2;
    public static final int CMD_TYPE_FULLVIEW = 5;


    // canbus data
	public static void send2Canbus(int cmd, int... params) {
		int len = params.length;
		byte sum = (byte) (len + cmd);
		int[] data = new int[len + 5];
		if (DataCanbus.isHead5A) {
			data[0] = 0x5A;
			data[1] = 0xA5;
		} else {
			data[0] = 0xAA;
			data[1] = 0x55;
		}
		data[2] = len;
		data[3] = cmd;
		for (int i = 0; i < len; i++) {
			sum += params[i];
			data[4 + i] = params[i];
		}
		sum = (byte) ((sum - 1) & 0xFF);
		data[data.length - 1] = sum;

		SystemClock.sleep(100);
		send(C_CANBUS_CMD_CANBUSDATA, data);
	}

    // key code
    public static void sendKeyCode2Host(int keycode, int action) {
        send(C_CANBUS_CMD_KEYCODE, keycode, action);
    }

    // guiji 轨迹
    public static void sendGuiji(int level) {
        send(C_CANBUS_HOST_SET, CMD_TYPE_GUIJI, level);
    }

    // level : 0-9 ,0 不显示
    public static void sendRadar(int position, int level) {
        switch (position) {
            case FL_RARA_FL:
            case FL_RARA_FML:
            case FL_RARA_FMR:
            case FL_RARA_FR:
            case FL_RARA_RL:
            case FL_RARA_RML:
            case FL_RARA_RMR:
            case FL_RARA_RR:
                send(C_CANBUS_HOST_SET, CMD_TYPE_RADAR, position, level);
                break;
        }
    }
    
	public static void setRadarOnOff(int i) {
		if (i != 0)
			i = 1;
		send(C_CANBUS_HOST_SET, CMD_TYPE_RADAR, 1, FL_RARA_ONOFF, i);
	} 

    public static void sendOutTemp(int type, int value) {
        switch (type) {
            case 0: // 是否存在外部温度
                break;
            case 1: // 外部温度值
                break;
        }
        send(C_CANBUS_HOST_SET, 2, type, value);
    }

    private static void send(int cmdid, int... params) {
        if (autoTools != null) { 
            autoTools.commad(FinalSyuModule.MODULE_CODE_CANBUS, cmdid, params);
            LogsUtils.d("send: " + cmdid + " " + LogsUtils.toHexString(params));
        }
    }

    private static void sendMain(int cmd, int... params) {
        if (autoTools != null) {
            autoTools.commad(FinalSyuModule.MODULE_CODE_MAIN, cmd, params);
            LogsUtils.d("sendMain: " + cmd + " " + LogsUtils.toHexString(params));
        }
    }

    public static void setAutoTools(Remote tool) {
        autoTools = tool;
    }
// GOLF
    public static void sendTime(int year, int month, int day, int hour, int min, int sec, int format) {
        send2Canbus(0xCB, 0x80, hour, min, 0, 0x08, format, year-2000, month+1, day, 0x02);
    }
    
// PSA Nissan
	public static void sendTime2(int year, int month, int day, int hour, int min, int sec, int format, int am) {
		send2Canbus(0xCB, year, month + 1, day, hour, min, format, am, 0, 0, 0);
	}

    public static void sendEngineSpeed(int i) {

    }

    
    //  是否存在全景图标
	public static void setExistFullView(int i) { 
		
	}

	public static void jumpFullView() {
		
	}

	public static void exitFullView() {
		
	}
}
