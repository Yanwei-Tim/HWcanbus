package com.ex.hiworld.server.canbus;

import com.ex.hiworld.aidl.car.HandlerTaskCanbus;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalBt;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Utils;


public class CanInfos {

	// -540 ~ 540
	
	public static void CarBackTrackHandle(int data0, int data1) {
		if (DataHost.sBackCar != 1)
			return;
		int t1, WheelAngle, t2;
		// #if defined(FYT3000_SOHANG)||defined(SUPPORT_BACK_70_FRAME)
		// wheel angle 索航的需要转化为 0 到 70.暂时先这样
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

	// left: 0 ~ 140 , right: 0-140
	public static void CarBackTrackHandle_LeftAndRight(int left, int right) {
		if (DataHost.sBackCar != 1)
			return;
		int wheel;

		int step = 140 / 20;

		if (left > 0) {
			wheel = 20 - left / step;
		} else {
			wheel = 20 + right / step;
		}

		SendFunc.sendGuiji(wheel);

	}

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
    
    private static int TemCanKey;
    private static int CanKey;

	public static void onKeyEvent(int[][] KeyCanKeyTable, int keycode, int action) {
//		if (CanKey != keycode) {
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

//		}

	}

	public static void onKeyEvent2(int[][] KeyCanKeyTable, int keycode, int action) {
		if (CanKey != keycode) {
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

	static int oldAction = 0;
	//按键状态 如此处理的时候 1， 1， 0 ，0 只处理一次，
	public static void onPannelEvent(int[][] KeyCanKeyTable, int keycode, int action) {
		if(oldAction == action) return;
		CanKey = keycode;
		LogsUtils.i("keyin " + Integer.toHexString(CanKey &0xFF) + "  - " + action);
		int i, j = 0;
		for (i = 0; i < KeyCanKeyTable.length; i++) {
			if (CanKey == KeyCanKeyTable[i][0]) {
				j = i;
				if (CanKey != 0)
					TemCanKey = j;
				break;
			}
		}
 
		if (i < KeyCanKeyTable.length) {
			LogsUtils.i("key " + KeyCanKeyTable[i][1] +"/"+ action +"/"+ oldAction);
			if (action == 0 && oldAction == 1) {
				LogsUtils.i("key " + KeyCanKeyTable[i][1]);
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[i][1], FinalKeyCode.ACTION_DOWN);
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[i][1], FinalKeyCode.ACTION_UP);
				oldAction = 0;
			}
			oldAction = action; 
		}
	}

	
    // 没有按键按下和抬起状态
	public static void onKeyEvent(int[][] KeyCanKeyTable, int keycode) {
//		if (CanKey != keycode) {
			CanKey = keycode;
			int i, j = -1;
			for (i = 0; i < KeyCanKeyTable.length; i++) {
				if (CanKey == KeyCanKeyTable[i][0]) {
					j = i;
					break;
				}
			}

			if (j != -1) {
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[j][1], FinalKeyCode.ACTION_DOWN);
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[j][1], FinalKeyCode.ACTION_UP);
			}
//		}
	}

	private static int tempKey2;
    // 没有按键按下和抬起状态 2 keycode  会等于0
	public static void onKeyEvent2(int[][] KeyCanKeyTable, int keycode) {
		if (CanKey != keycode) {
			CanKey = keycode;
			int i, j = -1;
			for (i = 0; i < KeyCanKeyTable.length; i++) {
				if (CanKey == KeyCanKeyTable[i][0]) {
					j = i;
					tempKey2 = j;
					break;
				}
			}

			if (keycode != 0) {
				if (j != -1)
					SendFunc.sendKeyCode2Host(KeyCanKeyTable[j][1], FinalKeyCode.ACTION_DOWN);
			} else {
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[tempKey2][1], FinalKeyCode.ACTION_UP);
			}
		}
	}
	

	public static void updateTempOut(int val, int Unit) {
		int tempature;
		tempature = 1000 + val * 5 - 400;
		if (Unit == 1) { // F
			float FTemp = Utils.convertC2F(val/2.0f - 40);
			tempature = (int) (1000 + FTemp * 10);
			tempature |= 1 << 15;
		}
		SendFunc.sendOutTemp(1, tempature);
	}
	
	public static void updateTempOut(int val) {
		int tempature;
		tempature = 1000 + val * 5 - 400;
		SendFunc.sendOutTemp(1, tempature);
	}

	public static void mcuKeyVolUp() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_VOL_UP, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_VOL_UP, FinalKeyCode.ACTION_UP);
	}

	public static void mcuKeyVolDown() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_VOL_DOWN, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_VOL_DOWN, FinalKeyCode.ACTION_UP);
	}

	public static void canbusKeyNext() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_NEXT, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_NEXT, FinalKeyCode.ACTION_UP);
		
	}

	public static void canbusKeyPrev() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PREV, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PREV, FinalKeyCode.ACTION_UP);
	}

	public static void canbusKeyRight() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_RIGHT, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_RIGHT, FinalKeyCode.ACTION_UP);
	}

	public static void canbusKeyLeft() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_LEFT, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_LEFT, FinalKeyCode.ACTION_UP);
	}

	public static void radarRml(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RML, level);
	}

	public static void radarRmr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RMR, level);
	}

	public static void radarRl(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RL, level);
	}

	public static void radarRr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RR, level);
	}

	public static void radarFl(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_FL, level);
	}

	public static void radarFml(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_FML, level);
	}

	public static void radarFmr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_FMR, level);
	}

	public static void radarFr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_FR, level);
	}
	
	
///
	
	public static void radarRrf(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RRF, level);
	}

	public static void radarRrmf(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RRMF, level);
	}

	public static void radarRrmr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RRMR, level);
	}

	public static void radarRrr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_RRR, level);
	}
	
	public static void radarLf(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_LF, level);
	}

	public static void radarLmf(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_LMF, level);
	}

	public static void radarLmr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_LMR, level);
	}

	public static void radarLr(int level) {
		SendFunc.sendRadar(SendFunc.FL_RARA_LR, level);
	} 

	public static void jumpRadio() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_RADIO, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_RADIO, FinalKeyCode.ACTION_UP);
		
	}
	public static void jumpAux() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_AUX, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_AUX, FinalKeyCode.ACTION_UP);
	}
	public static void jumpTv() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_TV, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_TV, FinalKeyCode.ACTION_UP);
	}
	public static void jumpBtAv() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_BT_AV, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_BT_AV, FinalKeyCode.ACTION_UP);
	}

	public static void jumpRadioFM() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_FM, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_FM, FinalKeyCode.ACTION_UP);
	}
	public static void jumpRadioAM() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_AM, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_AM, FinalKeyCode.ACTION_UP);
	}

	public static void jumpMediaPlayer() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PLAYER, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PLAYER, FinalKeyCode.ACTION_UP);
	}
	

	// 原车蓝牙开关状态
	public static void setCarBtOn(boolean b) { 
		
	}
 
	
	
	public static void setExistFullViewFloatBtn(int i) {
		boolean ex = i == 1;
		if (DataCanbus.sExistFullViewFloatBtn != ex) {
			DataCanbus.sExistFullViewFloatBtn = ex;
			HandlerTaskCanbus.update(FinalCanbus.U_HW_EXIST_FULLVIEW,
					new int[] { DataCanbus.sExistFullViewFloatBtn ? 1 : 0, DataCanbus.sExistFullView ? 1 : 0 });
		}
	}
	
	// 切换全景状态
	public static void switchFullViews(int i) { 
		
	}

	public static void jumpFullView() {
		SendFunc.sendVideo(SendFunc.V_BACK, 1);
	}

	public static void exitFullView() {
		SendFunc.sendVideo(SendFunc.V_BACK, 0);
	}

	public static void jumpDvd() { 
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_DVD, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_DVD, FinalKeyCode.ACTION_UP);
	}

	public static void switchAudio2Null() {
		SendFunc.sendMain(FinalMain.C_APP_ID, FinalMain.APP_ID_NULL);
	}
	
	public static void switchAudio2CarBt() {
		SendFunc.sendMain(FinalMain.C_APP_ID, FinalMain.APP_ID_CAR_BTPHONE);
	}
	
	public static void switchAudio2CarUSB() {
		SendFunc.sendMain(FinalMain.C_APP_ID, FinalMain.APP_ID_CAR_USB);
	}

	public static void switchAudio2CarRadio() {
		SendFunc.sendMain(FinalMain.C_APP_ID, FinalMain.APP_ID_CAR_RADIO);
		
	}
	
	public static void switchAudio2Indicate(int indicate) {
		SendFunc.sendMain(FinalMain.C_APP_ID, indicate);
	}
	public static void switchAudio2LastAppId() {
		switchAudio2Indicate(FinalMain.APP_ID_LAST);
	}
	public static void canbusKeyHang() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_HANG, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_HANG, FinalKeyCode.ACTION_UP);
	}
	public static void canbusKeyPick() {
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PHONE, FinalKeyCode.ACTION_DOWN);
		SendFunc.sendKeyCode2Host(FinalKeyCode.KEY_CODE_PHONE, FinalKeyCode.ACTION_UP);
	}

	public static void bandFM(int i) { 
		
	}

	public static void bandAM(int i) {
		
	}

	// 78.50 - > 7850
	public static void freqFM(int freq) {
		
	}
	public static void freqAM(int freq) {
		
	}

	// 1~ 6
	public static void selectRadioChannel(int val1) {
		
	}

	public static void startSpeek(String set_ring) {
		
	}

	public static void endSpeek() {
		
	}

	public static void jumpRightCarmera(boolean on) {
		SendFunc.sendVideo(SendFunc.V_RIGHT, on ? 1 : 0);
	}


}
