package com.ex.hiworld.aidl.car;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.HandlerNotRemove;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;
import com.ex.hiworld.server.tools.spUtils;

import android.R.integer;
import android.net.NetworkInfo.State;
import android.os.RemoteException;
public class TaskCar_Mazida_All extends BaseCar {
	
	public static final int U_DOOR_BEGIN					= 0;
	public static final int U_DOOR_ENGINE					= U_DOOR_BEGIN+0;
	public static final int U_DOOR_FL						= U_DOOR_BEGIN+1;
	public static final int U_DOOR_FR						= U_DOOR_BEGIN+2;
	public static final int U_DOOR_RL						= U_DOOR_BEGIN+3;
	public static final int U_DOOR_RR						= U_DOOR_BEGIN+4;
	public static final int U_DOOR_BACK						= U_DOOR_BEGIN+5;
	public static final int U_DOOR_END						= U_DOOR_BEGIN+6;
	public static final int U_CUR_SPEED						= U_DOOR_BEGIN+7;
	public static final int U_ENGINE_SPEED					= U_DOOR_BEGIN+8; 
	
	public static final int U_AIR_BEGIN						= U_ENGINE_SPEED + 1;
	public static final int U_AIR_POWER_ON					= U_AIR_BEGIN+0;
	public static final int U_AIR_SHOW						= U_AIR_BEGIN+1;
	public static final int U_AIR_AC						= U_AIR_BEGIN+2;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+3;
	public static final int U_AIR_CYCLE_TYPE				= U_AIR_BEGIN+4;
	public static final int U_AIR_CYCLE_AUTO				= U_AIR_BEGIN+5;
	public static final int U_AIR_DUAL						= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+9;
	public static final int U_AIR_REAR_DEFROG				= U_AIR_BEGIN+10;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+11;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+12;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+13;
	public static final int U_AIR_FRONT_DEFROG				= U_AIR_BEGIN+14;
	public static final int U_AIR_HEATSEAT_RIGHT			= U_AIR_BEGIN+15;
	public static final int U_AIR_HEATSEAT_LEFT				= U_AIR_BEGIN+16;
	public static final int U_AIR_WIND_LEVEL_RIGHT			= U_AIR_BEGIN+17;
	public static final int U_AIR_BLOW_FOOT_RIGHT			= U_AIR_BEGIN+18;
	public static final int U_AIR_BLOW_BODY_RIGHT			= U_AIR_BEGIN+19;
	public static final int U_AIR_BLOW_UP_RIGHT				= U_AIR_BEGIN+20;
	public static final int U_AIR_END						= U_AIR_BEGIN+21;

	public static final int U_SET_AMP_CARVOL 		= U_AIR_END + 1;
	public static final int U_SET_AMP_FAD 			= U_SET_AMP_CARVOL + 1;
	public static final int U_SET_AMP_BAL 			= U_SET_AMP_CARVOL + 2;
	public static final int U_SET_AMP_BASS 			= U_SET_AMP_CARVOL + 3;
	public static final int U_SET_AMP_MID 			= U_SET_AMP_CARVOL + 4;
	public static final int U_SET_AMP_TREBLE 		= U_SET_AMP_CARVOL + 5;
	public static final int U_SET_AMP_PLT  			= U_SET_AMP_CARVOL + 6;
	public static final int U_SET_AMP_END  			= U_SET_AMP_CARVOL + 7;

	public static final int U_SET_AUTOLOCK 				= U_SET_AMP_END + 1;
	public static final int U_SET_OPENDOOR_POWEROFF 	= U_SET_AUTOLOCK + 1;
	public static final int U_SET_LEAVE_LOCKUP 			= U_SET_AUTOLOCK + 2;
	public static final int U_SET_KEYLESS_SOUND 		= U_SET_AUTOLOCK + 3;
	public static final int U_SET_AUTO_RELOCK_TIME 		= U_SET_AUTOLOCK + 4;
	public static final int U_SET_UNLOCK_MODE 			= U_SET_AUTOLOCK + 5;
	public static final int U_SET_3TIMES_TURN 			= U_SET_AUTOLOCK + 6;
	public static final int U_SET_TURN_VOLUME 			= U_SET_AUTOLOCK + 7;
	public static final int U_SET_RAIN_BRUSH 			= U_SET_AUTOLOCK + 8;
	public static final int U_SET_CLOSEDOOR_LIGHT_TIMEOUT = U_SET_AUTOLOCK + 9;
	public static final int U_SET_AUTO_BIGLIGHT_ON 		= U_SET_AUTOLOCK + 10;
	public static final int U_SET_LIGHTON_WARN 			= U_SET_AUTOLOCK + 11;
	public static final int U_SET_ACC_TRUN_FRONT_LIGHT 	= U_SET_AUTOLOCK + 12;
	
	public static final int U_CD_DISC_STATE 			= U_SET_AUTOLOCK + 13;
	public static final int U_CD_PLAY_STATE 			= U_SET_AUTOLOCK + 14;
	public static final int U_CD_TRACK_STATE 			= U_SET_AUTOLOCK + 15;
	public static final int U_CD_TRACK_INDEX 			= U_SET_AUTOLOCK + 16;
	public static final int U_CD_TRACK_DURATION 		= U_SET_AUTOLOCK + 17;
	public static final int U_CD_INFO 					= U_SET_AUTOLOCK + 18;
	
	public static final int U_SET_CAR_TYPE 					= U_CD_INFO + 1;
	public static final int U_CNT_MAX  	= 		U_CD_INFO + 10;
	

	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	public static final int C_SET_CMD = 0;
	public static final int C_SET_CAR_TYPE = 1;
	public static final int C_SET_CLOCK = 2;
	public static final int C_SET_AMP = 3;
	public static final int C_SET_CD = 4;
	private static final int S_CAR_TYPE = 0;
	
	int KeyCanKeyTable[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
		 			{8, 		FinalKeyCode.KEY_CODE_PREV}, 
		 			{9, 		FinalKeyCode.KEY_CODE_NEXT}, 
		 			{0xA, 		FinalKeyCode.KEY_CODE_MODE},
		 			
		 			{0x6 |0x80, FinalKeyCode.KEY_CODE_BACK},
		 			{0x9 |0x80, FinalKeyCode.KEY_CODE_POWER},
		 			{0x16 |0x80, FinalKeyCode.KEY_CODE_MODE},
		 			{0x17 |0x80, FinalKeyCode.KEY_CODE_UP},
		 			{0x18 |0x80, FinalKeyCode.KEY_CODE_DOWN},
		 			{0x19 |0x80, FinalKeyCode.KEY_CODE_LEFT},
		 			{0x1A |0x80, FinalKeyCode.KEY_CODE_RIGHT},
		 			{0x20 |0x80, FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2B |0x80, FinalKeyCode.KEY_CODE_HOME},
		 			{0x2D |0x80, FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x54 |0x80, FinalKeyCode.KEY_CODE_NULL},
		 			
		 			
		 };
	
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start + 1]) {
		case 0x11: {
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			HandlerTaskCanbus.update(U_CUR_SPEED, B1);

			onkeyDown(KeyCanKeyTable, B2, B3); 
			CanInfos.CarBackTrackHandle(B6, B7);
			break;
		}
		case 0x12: {
			int B0 = data[start+4];
			// 司机门
			HandlerTaskCanbus.update(U_DOOR_FL, 	B0>>7&0x01);
			// 副驾驶门
			HandlerTaskCanbus.update(U_DOOR_FR, 	B0>>6&0x01);
			
			HandlerTaskCanbus.update(U_DOOR_RL, 		B0>>5&0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, 		B0>>4&0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, 		B0>>3&0x01);
			HandlerTaskCanbus.update(U_DOOR_ENGINE, 	B0>>2&0x01);
			break;
		}
		case 0x21: {
			int CanKey = (data[start + 2] & 0xFF) | 0x80;
			onkeyDown(KeyCanKeyTable, CanKey, data[start + 3] & 0xFF);
			break;
		}
		case 0x22: {
			int event = data[start + 2] & 0xFF;
			int action = data[start + 3] & 0xFF;
			switch (event) {
			case 1: {
				if (action < 0x80) {
					CarKeyVolPlusCnt = CarKeyVolPlusCnt + (data[start + 3] & 0xff);
					HandlerNotRemove.getInstance().post(CarDisNormal);
				} else {
					CarKeyVolMinusCnt = CarKeyVolMinusCnt + (0x100 - (data[start + 3] & 0xff));
					HandlerNotRemove.getInstance().post(CarDisNormal);
				}
				break;
			}
			case 2: {
				if (action < 0x80) {
					CanInfos.canbusKeyNext();
				} else {
					CanInfos.canbusKeyPrev();
				}
				break;
			}
			}
			break;
		}
		case 0x31: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			int B8 = data[start + 10];
			int B9 = data[start + 11];
			int B10 = data[start + 12];
			HandlerTaskCanbus.update(U_AIR_SHOW, B0 >> 7 & 0x01);
			HandlerTaskCanbus.update(U_AIR_POWER_ON, B0 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AUTO, B0 >> 3 & 0x01);
			
			HandlerTaskCanbus.update(U_AIR_AC, B1 >> 6 & 0x03);
			HandlerTaskCanbus.update(U_AIR_CYCLE_TYPE, B1 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_CYCLE_AUTO, B1 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_DUAL, B1 >> 2 & 0x01);
			
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROG, B2 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROG, B2 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_HEATSEAT_RIGHT, B2 >> 2 & 0x03);
			HandlerTaskCanbus.update(U_AIR_HEATSEAT_LEFT, B2 & 0x03);

			int wind = 0,head = 0,body = 0,foot = 0;
			
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, B4 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, B4 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_LEFT, B4 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, B4  & 0x0F);
			
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_RIGHT, B5 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_RIGHT, B5 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_RIGHT, B5 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_RIGHT, B5 & 0x0F);
			
			int value = B6&0xff;
			switch (value) {
				case 0xFE:
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	TEMPERATURE_LOW); break;
				case 0xFF:{
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	TEMPERATURE_HIGH); break;
				}
				default: {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	value*5);
					break;
				}
			}
			value = B7&0xff;
			switch (value) { // GM 的温度！  25以后 不可能为-1 ！！！！
			case 0xFE:
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_LOW);
				break;
			case 0xFF:
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_HIGH);
				break;
			default: {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, value * 5);
				break;
			}
			}
			break;
		}
		case 0x41: {
			SendFunc.sendRadar(SendFunc.FL_RARA_RL, CarGetRadarDistance(data[start + 2] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_RML, CarGetRadarDistance2(data[start + 3] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_RMR, CarGetRadarDistance2(data[start + 4] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_RR, CarGetRadarDistance(data[start + 5] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_FL, CarGetRadarDistance(data[start + 6] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_FML, CarGetRadarDistance2(data[start + 7] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_FMR, CarGetRadarDistance2(data[start + 8] & 0xff));
			SendFunc.sendRadar(SendFunc.FL_RARA_FR, CarGetRadarDistance(data[start + 9] & 0xff));

			SendFunc.setRadarOnOff(data[start + 12]);
			break;
		}
		case 0x78: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			int B8 = data[start + 10];
			int B9 = data[start + 11];
			int B10 = data[start + 12];

			updateVA(U_SET_AUTOLOCK, 				B2, 7, B7, 5, 0x07);
			updateVA(U_SET_OPENDOOR_POWEROFF, 		B2, 6, B7, 3, 0x03);
			updateVA(U_SET_LEAVE_LOCKUP, 			B2, 5, B7, 2 );
			updateVA(U_SET_KEYLESS_SOUND, 			B2, 4, B7, 0, 0x03);
			
			updateVA(U_SET_AUTO_RELOCK_TIME, 		B2, 3, B8, 6, 0x03);
			updateVA(U_SET_UNLOCK_MODE, 			B2, 2, B8, 5);
			updateVA(U_SET_3TIMES_TURN, 			B2, 1, B8, 4);
			updateVA(U_SET_TURN_VOLUME, 			B2, 0, B8, 3);
			updateVA(U_SET_RAIN_BRUSH, 				B3, 7, B8, 2);
			updateVA(U_SET_CLOSEDOOR_LIGHT_TIMEOUT, B3, 6, B8, 0, 0x03);
			
			updateVA(U_SET_AUTO_BIGLIGHT_ON, 		B3, 5, B9, 5, 0x07);
			updateVA(U_SET_LIGHTON_WARN, 			B3, 4, B9, 2, 0x03);
			updateVA(U_SET_ACC_TRUN_FRONT_LIGHT, 	B3, 3, B9, 1); 
			break;
		}
		case 0xA6: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B5 = data[start + 7];
			int B7 = data[start + 9];

			HandlerTaskCanbus.update(U_SET_AMP_CARVOL, B0);
			HandlerTaskCanbus.update(U_SET_AMP_BAL, B1);
			HandlerTaskCanbus.update(U_SET_AMP_FAD, B2);
			HandlerTaskCanbus.update(U_SET_AMP_BASS, B3);
			HandlerTaskCanbus.update(U_SET_AMP_TREBLE, B5);
			HandlerTaskCanbus.update(U_SET_AMP_PLT, B7 & 0x01);
			
			break;
		}
		case 0xAE: { // CD Infos
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			int B8 = data[start + 10];
			int B9 = data[start + 11];
			int B10 = data[start + 12];
			int B11 = data[start + 13];
			int B12 = data[start + 14];
			int B13 = data[start + 15];
			int B14 = data[start + 16];

			HandlerTaskCanbus.update(U_CD_DISC_STATE, (B0 & 0x01) << 8 | B3&0xFF);
			HandlerTaskCanbus.update(U_CD_PLAY_STATE, (B1 & 0x01)<<8 | B4 & 0xFF);
			HandlerTaskCanbus.update(U_CD_TRACK_STATE,	Utils.combine(B2 & 0x01, B13, B14));
			HandlerTaskCanbus.update(U_CD_TRACK_INDEX, Utils.combine(B5, B6) << 16 | Utils.combine(B7, B8));
			HandlerTaskCanbus.update(U_CD_TRACK_DURATION, Utils.combine(B9, B10) << 16 | Utils.combine(B11, B12));
			break;
		}
		case 0xA5: { // CD info repeat request 0xF2!!!
			int B0 = data[start + 2];
			if (B0 < 4) {
				String ifn = "";
				StringBuffer sBuffer = new StringBuffer();

				int len = 0;
				for (int i = 5; i < 46; i++) {
					if (data[start + i] == 0 && data[start + i + 1] == 0)
						break;
					len++;
				}
				int[] ar = new int[len];
				ifn = getStringGB2312(data, start + 5, len);
				LogsUtils.i(" cd infos: " + B0 + ":" + ifn);
				int index = B0 % 5;
				if (mCDInfo[index] == null || mCDInfo[index].equals(ifn)) {
					mCDInfo[index] = ifn;
					HandlerTaskCanbus.update(U_CD_INFO, new int[] { index }, new String[] { ifn });
				}
			}
			break;
		}
		}

	}

	
	private String getStringGB2312(int[] ints, int start, int cnt) {
		String str = "";
		try {
			str = new String(Utils.I2B(ints), start, cnt, "gb2312");
		} catch (UnsupportedEncodingException e) {
		}
		return str;
	}
	
	private String[] mCDInfo = new String[5];
	private void updateVA(int updatecode, int enable, int enablebit, int value, int offset) {
		HandlerTaskCanbus.update(updatecode, ((enable>> enablebit & 0x1) << 8) | (value >> offset & 0x01));
	}
	private void updateVA(int updatecode, int enable, int enablebit, int value, int offset, int bit) {
		HandlerTaskCanbus.update(updatecode, ((enable>> enablebit & 0x1) << 8) | (value >> offset & bit));
	}

	// 1 - 3 
	static int CarGetRadarDistance(int data) {
		return TypeWC2_Data.CarGetRadarDistancef5(data);
	}
	// 1 - 4 
	static int CarGetRadarDistance2(int data) {
		return TypeWC2_Data.CarGetRadarDistancef2(data);
	}
	static int CarKeyVolPlusCnt = 0;
	static int CarKeyVolMinusCnt = 0;
	int Vol_dis_cnt = 0;
	public static Runnable CarDisNormal = new Runnable() {
		@Override
		public void run() {
			if (CarKeyVolPlusCnt != 0) {
				CarKeyVolPlusCnt--;
				CanInfos.mcuKeyVolUp();
			} else if (CarKeyVolMinusCnt != 0) {
				CarKeyVolMinusCnt--;
				CanInfos.mcuKeyVolDown();
			}
			if (CarKeyVolPlusCnt != 0 || CarKeyVolMinusCnt != 0)
				HandlerNotRemove.getInstance().postDelayed(this, 10);
		}
	};
	int canCode, tempKey;
	private void onkeyDown(int[][] table, int code, int action) {
		if(code == 0x80) code = 0;
		if (canCode != code) {
			canCode = code;
			int i, j = -1;
			for (i = 0; i < KeyCanKeyTable.length; i++) {
				if (canCode == KeyCanKeyTable[i][0]) {
					tempKey = j = i;
					break;
				}
			}
			if (j != -1) {
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[j][1], FinalKeyCode.ACTION_DOWN);
			}else{
				SendFunc.sendKeyCode2Host(KeyCanKeyTable[tempKey][1], FinalKeyCode.ACTION_UP);
				tempKey = 0;
				
			}
		}
	}

	@Override
	public void in() {
		Ticks.addTicks1s(R_HostState);
		spUtils.init(getClass().getSimpleName(), MyApp.getInstance());
		setCarType(spUtils.get(S_CAR_TYPE, 0), false);
	}

	@Override
	public void out() {
		Ticks.removeTicks1s(R_HostState);
	}
	
	Runnable R_HostState = new Runnable() {

		@Override
		public void run() {
			
			int[] data = new int[0x0D];
			data[0] = getHostMode();

			switch (DataHost.sAppid) {
			case FinalMain.APP_ID_RADIO: {
				int freq = DataHost.sRadioFreq;
				StringBuffer sBuffer = new StringBuffer();
				if (!DataHost.isAM) {
					sBuffer.append(String.format("%7.2f", freq / 100f));
					sBuffer.append(" Mhz");
				} else {
					sBuffer.append(String.format("%8d", freq));
					sBuffer.append(" Khz");
				}
				byte[] bytes = sBuffer.toString().getBytes();
				for (int i = 0; i < bytes.length; i++) {
					data[i + 1] = bytes[i];
				}
				break;
			}
			case FinalMain.APP_ID_AUDIO_PLAYER:
			case FinalMain.APP_ID_VIDEO_PLAYER: {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append(String.format("%02d/%02d", DataHost.sCurPlayIndex % 1000, DataHost.sPlayTotal % 1000));
				sBuffer.append(String.format(" %02d:%02d", DataHost.sCurPlayTime / 60, DataHost.sCurPlayTime % 60));
				byte[] bytes = sBuffer.toString().getBytes();
				for (int i = 0; i < bytes.length; i++) {
					data[i + 1] = bytes[i];
				}
				break;
			}
			default:
				break;
			}
			
			if(iSendTime == 1) {
				
			}else if (iSendTime == 2) {
				iSendTime = 0;
				SendFunc.send2Canbus(0x91, data);
			}else {
				if (lastData == null || !Arrays.equals(lastData, data)) {
					lastData = data;
					SendFunc.send2Canbus(0x91, data);
				}
			}

		}

		int[] lastData;

		private int getHostMode() {
			int sourceid = 0;
			switch (DataHost.sAppid) {
			case FinalMain.APP_ID_TV:// 1
				sourceid = 0x08;
				break;
			case FinalMain.APP_ID_DVD:// 2
				sourceid = 0x07;
				break;
			case FinalMain.APP_ID_IPOD:// 4//IPOD
				sourceid = 0x0b;
				break;
			case FinalMain.APP_ID_AUX:// 5//AUX
				sourceid = 0x0c;
				break;
			case FinalMain.APP_ID_RADIO:
				if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x01;
				} else if (1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x02;
				} else if (2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x03;
				} else if (0 == (DataHost.sRadioBand - FinalRadio.BAND_AM_INDEX_BEGIN)) {
					sourceid = 0x04;
				} else if (1 == (DataHost.sRadioBand - FinalRadio.BAND_AM_INDEX_BEGIN)) {
					sourceid = 0x05;
				}

				break;
			case FinalMain.APP_ID_BTPHONE:// 8
				sourceid = 0x0a;
				break;
			case FinalMain.APP_ID_BTAV:// 11//蓝牙音乐
				sourceid = 0x0a;
				break;
			case FinalMain.APP_ID_NULL:// 14
				sourceid = 0x09;
				break;
			case FinalMain.APP_ID_THIRD_PLAYER:
			case FinalMain.APP_ID_AUDIO_PLAYER:// 15
				sourceid = 0x0d;
				break;
			case FinalMain.APP_ID_DVR:// 17
				break;
			case FinalMain.APP_ID_CAR_RADIO:// 18// 原车收音
				break;
			case FinalMain.APP_ID_CAR_BTPHONE:// 19// 原车蓝牙
				sourceid = (byte) 0xfe;
				break;
			case FinalMain.APP_ID_CAR_USB:// 20// 原车USB, 2013.10.19 Add
				sourceid = (byte) 0xff;
				break;
			}
			if (DataHost.sBackCar == 1) {
				sourceid = 0x10;// Carmera
			}
			return sourceid;
		}
	};

	public static int iSendTime = 0;
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_SET_CMD: {
			if (ints != null && ints.length == 2)
				SendFunc.send2Canbus(0x7E, new int[] { 0x0A, 0, ints[0], ints[1] });
			break;
		}
		case C_SET_CAR_TYPE: {
			if (ints != null && ints.length == 1)
				setCarType(ints[0], true);
			break;
		}
		case C_SET_CLOCK: {
			if (ints != null && ints.length == 2) {
				int[] data = new int[0x0D];
				data[0] = ints[0];
				SendFunc.send2Canbus(0x91, data);
//				LogsUtils.i(" send ... " + isSendClockCmd);
				if(ints[1] == 0) {
					 iSendTime = 1;
				}else iSendTime = 2;
			break;
			}
		}
		case C_SET_AMP: {
			if (ints != null && ints.length == 2)
				SendFunc.send2Canbus(0xAD, ints);
			break;
		}
		case C_SET_CD: {
			if (ints != null && ints.length == 2)
				SendFunc.send2Canbus(0xF2, ints);
			break;
		}
		default:
			break;
		}
	};

	private void setCarType(int value, boolean update) {
		if (mCarOffset != value) {
			mCarOffset = value;
			spUtils.set(S_CAR_TYPE, mCarOffset);
			HandlerTaskCanbus.update(U_SET_CAR_TYPE, value);
			SendFunc.send2Canbus(0x24, new int[] { value, 0x0A });
			if (update) {
				updateCanbusIdOffSet(value);
			}
		}
	}
	
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if (code >= 0 && code < U_CNT_MAX) {
			if (code == U_CD_INFO) {
				for (int i = 0; i < mCDInfo.length; i++) {
					if (mCDInfo[i] != null) {
						HandlerTaskCanbus.update(U_CD_INFO, new int[] { i }, new String[] { mCDInfo[i] });
					}
				}
			} else {
				if (DataCanbus.DATA[code] != 0) {
					HandlerTaskCanbus.update(code);
				}
			}
		}

	}
}
