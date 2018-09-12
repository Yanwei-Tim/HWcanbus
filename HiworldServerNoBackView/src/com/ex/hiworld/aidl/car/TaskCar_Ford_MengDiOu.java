package com.ex.hiworld.aidl.car;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalBt;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;
import android.os.RemoteException;

public class TaskCar_Ford_MengDiOu extends BaseCar {	
	
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
	public static final int U_CAR_SRC						= U_DOOR_BEGIN+9;
	
	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= U_CAR_SRC;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+1;
	public static final int U_AIR_CYCLE						= U_AIR_BEGIN+2;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+3;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_AC						= U_AIR_BEGIN+5;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_AUTO_18YIBO				= U_AIR_BEGIN+9;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+10;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+12;
	public static final int U_AIR_POWER						= U_AIR_BEGIN+13;
	public static final int U_AIR_BLOW_WIN_LEFT				= U_AIR_BEGIN+16;
	public static final int U_AIR_SEAT_HEAT_LEFT			= U_AIR_BEGIN+19;
	public static final int U_AIR_SEAT_HEAT_RIGHT			= U_AIR_BEGIN+20;
	public static final int U_AIR_TEMP_UNIT					= U_AIR_BEGIN+21;
	public static final int U_AIR_MAXHEAT					= U_AIR_BEGIN+22;
	public static final int U_AIR_DUAL 						= U_AIR_BEGIN+24;
	public static final int U_AIR_WIND_AUTO 				= U_AIR_BEGIN+25;
	public static final int U_AIR_BLOW_AUTO 				= U_AIR_BEGIN+26;
	public static final int U_AIR_BLOW_MODE 				= U_AIR_BEGIN+27; 
	public static final int U_AIR_END						= U_AIR_BEGIN+30;
	
	public static final int U_SET_UNIT_MILES 				= U_AIR_END+1;
	public static final int U_SET_UNIT_TEMP 				= U_SET_UNIT_MILES+1;
	public static final int U_SET_LANG 						= U_SET_UNIT_MILES+2;
	

	public static final int U_CNT_MAX						= U_SET_LANG	+ 10;
	
	int KeyCanKeyTable[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
//		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{6,			FinalKeyCode.KEY_CODE_NEXT}, // 接听 和上一曲， 挂断和下一曲同一个按键
		 			{5, 		FinalKeyCode.KEY_CODE_PREV}, 
		 			{7,			FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},

		 			{0x0A,		FinalKeyCode.KEY_CODE_PHONE},	
		 			{0x0B,		FinalKeyCode.KEY_CODE_HANG},	
		 			{0x0C,		FinalKeyCode.KEY_CODE_MODE},	
		 			
		 			
		 			{0x0d,		FinalKeyCode.KEY_CODE_UP},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_DOWN},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x17,		FinalKeyCode.KEY_CODE_PLAYER},		
		 			{0x28,		FinalKeyCode.KEY_CODE_VA},		
		 			{0x30,		FinalKeyCode.KEY_CODE_CARSETTTING},		
		 			{0x31,		FinalKeyCode.KEY_CODE_HOME},		

		 };

	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	 
	private static final int C_SET_AIR 			= 2;
	private static final int C_SET_AIR_REMOVE 	= 3;
	private static final int C_SET_CAR 			= 4;
	private static final int C_SET_CARTYPE 		= 5;
	
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		int length = data.length;
		switch (data[start + 1]) {
		case 0x11: {
			CanInfos.onKeyEvent(KeyCanKeyTable, data[start + 4], data[start + 5]);
			break;
		}
		case 0x12: {
			int B0 = data[start + 4];
			// if (DataCanbus.sDriverOnRight == 1) {
			// HandlerTaskCanbus.update(U_DOOR_FR, B0 >>7 & 0x01);
			// HandlerTaskCanbus.update(U_DOOR_FL, B0 >>6 & 0x01);
			// } else {
			HandlerTaskCanbus.update(U_DOOR_FL, B0 >> 7 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_FR, B0 >> 6 & 0x01);
			// }
			HandlerTaskCanbus.update(U_DOOR_RL, B0 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B0 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B0 >> 3 & 0x01);

			SendFunc.setExistDoor(B0 & 0x01);
			break;
		}
		case 0x31: {
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B4 = data[start+6];
			int B5 = data[start+7];
			int B6 = data[start+8];
			int B7 = data[start+9];
			int B8 = data[start+10];
			int B9 = data[start+11];
			int B10 = data[start+12];
			HandlerTaskCanbus.update(U_AIR_POWER, 		B0>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_DUAL, 		B0>>2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC, 			B0>>0 & 0x03); // off 0, on 1, maxacon(3)

			HandlerTaskCanbus.update(U_AIR_MAXHEAT,		B1>>6 & 0x01); 
			HandlerTaskCanbus.update(U_AIR_CYCLE, 		B1>>4&0x01);
			HandlerTaskCanbus.update(U_AIR_AUTO, 		B1>>3 & 0x01);

			HandlerTaskCanbus.update(U_AIR_WIND_AUTO,	B1>>2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_AUTO,	B1>>1 & 0x01);

			HandlerTaskCanbus.update(U_AIR_AUTO_18YIBO, B2>>6 & 0x03); // 18翼博 0 ~ 3
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, B2>>5 & 0x01);
			
//			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, B2>>4 & 0x01);
			
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_RIGHT, B2>>2 & 0x03); // 0~3
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEFT, B2>>0 & 0x03);
			
			int auto = 0,win = 0,foot = 0,body = 0;
			switch (B4&0xff) { 
//			case 0x01:	auto = 1;	break;
			case 0x02:	win = 1;	break;
			case 0x03:	foot = 1;	break;
			case 0x04:	win = foot = 1;	break;
			case 0x05:	body = foot = 1;	break;
			case 0x06:	body =1;	break;
			case 0x07:	win = body = 1;	break;
			case 0x0A:	win = 1;	foot = 1;	body = 1;	break;
			}
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, 		body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, 		foot);
			HandlerTaskCanbus.update(U_AIR_BLOW_WIN_LEFT, 		win);
			HandlerTaskCanbus.update(U_AIR_BLOW_MODE, 			B4);
			
			//前排风量
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, 		B5&0xff); // 7
			
			int value =  B6&0xff;
			switch (value) {
			case 0xFE:
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	TEMPERATURE_LOW);
				break;
			case 0xFF:
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	TEMPERATURE_HIGH);
				break;
			default: // 自动 ： B *0.5， 手动： (90 - B)*0.5
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 	value*5);
				break;
			}
			
			value =  B7&0xff;
			switch (value) {
			case 0xFE:
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	TEMPERATURE_LOW);
				break;
			case 0xFF:
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	TEMPERATURE_HIGH);
				break;
			default:
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	value*5);
				break;
			}
			
			 
			updateOutTemp(data[start + 13], iUnit);
			break;
		}
		case 0x41:{
			CanInfos.radarRl(TypeWC2_Data.CarGetRadarDistance(data[start+2]&0xff));
			CanInfos.radarRml(TypeWC2_Data.CarGetRadarDistance(data[start+3]&0xff)); 
			CanInfos.radarRmr(TypeWC2_Data.CarGetRadarDistance(data[start+4]&0xff));
			CanInfos.radarRr(TypeWC2_Data.CarGetRadarDistance(data[start+5]&0xff));
			CanInfos.radarFl(TypeWC2_Data.CarGetRadarDistance(data[start+6]&0xff));  
			CanInfos.radarFml(TypeWC2_Data.CarGetRadarDistance(data[start+7]&0xff));
			CanInfos.radarFmr(TypeWC2_Data.CarGetRadarDistance(data[start+8]&0xff));
			CanInfos.radarFr(TypeWC2_Data.CarGetRadarDistance(data[start+9]&0xff)); 
			
			SendFunc.setRadarOnOff(data[start+12]&0x01);
			break;
		}
		case 0x3F: {
			int B0 = data[start+2];
			enableEngine = B0 >> 5 & 0x01;
			enableEngine = B0 >> 4 & 0x01;
			break;
		}
		case 0x32: {
			HandlerTaskCanbus.update(U_ENGINE_SPEED, Utils.combine(data[start + 4], data[start + 5]));
			HandlerTaskCanbus.update(U_CUR_SPEED, Utils.combine(data[start + 6], data[start + 7]));
			break;
		}
		case 0x68: {
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_SET_UNIT_MILES, B1 >> 7 &0x01);
			HandlerTaskCanbus.update(U_SET_UNIT_TEMP, B1 >> 4 &0x01);
			
			updateOutTemp(outTemp, B1 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_SET_LANG, B1 >> 3 &0x01);
			
			break;
		}
		}
	}


	private void updateOutTemp(int val, int unit) {
		outTemp  = val;
		iUnit = unit;
		CanInfos.updateTempOut(val, unit);
	}
	
	
	private int outTemp = 0;
	private int iUnit = 0; 

	private int enableEngine, enbaleSpeed;
	
	@Override
	public void in() {
		Ticks.addTicks1s(TypeWC2_Data.mCarDisNormal);
		EventNotify.NE_VOL_SRC.addNotify(TypeWC2_Data.mCarDisVolume, 1);
		EventNotify.NE_MUTE_SRC.addNotify(TypeWC2_Data.mCarDisVolume, 0);
		EventNotify.NE_RADIO_FREQS.addNotify(TypeWC2_Data.mCarDisNormal, 1);
		EventNotify.NE_RADIO_BAND.addNotify(TypeWC2_Data.mCarDisNormal, 1);

		EventNotify.NE_BT_STATE.addNotify(mBtPhoneStateAndNumber, 1);
		EventNotify.NE_BT_PHONENUM.addNotify(mBtPhoneStateAndNumber, 1);
		EventNotify.NE_ID3_ALBUM.addNotify(mId3ALBUM, 1);
		EventNotify.NE_ID3_TITLE.addNotify(mId3Song, 1);
		EventNotify.NE_ID3_ARTIST.addNotify(mId3Artist, 1); 
		 
	}
	 
	@Override
	public void out() {
		Ticks.removeTicks1s(TypeWC2_Data.mCarDisNormal);
        EventNotify.NE_VOL_SRC.removeNotify(TypeWC2_Data.mCarDisVolume); 
		EventNotify.NE_MUTE_SRC.removeNotify(TypeWC2_Data.mCarDisVolume);
		EventNotify.NE_RADIO_FREQS.removeNotify(TypeWC2_Data.mCarDisNormal);
		EventNotify.NE_RADIO_BAND.removeNotify(TypeWC2_Data.mCarDisNormal);
        EventNotify.NE_BT_STATE.removeNotify(mBtPhoneStateAndNumber); 
        EventNotify.NE_BT_PHONENUM.removeNotify(mBtPhoneStateAndNumber); 
        EventNotify.NE_ID3_ALBUM.removeNotify(mId3ALBUM);
        EventNotify.NE_ID3_TITLE.removeNotify(mId3Song);
        EventNotify.NE_ID3_ARTIST.removeNotify(mId3Artist);

        DataCanbus.sExistFullView = false;
//         BackCarUtils.getInstance().destroyContent();
	}

	private String mBtPhoneNumber = "";
	private Runnable mBtPhoneStateAndNumber = new Runnable() {
		@Override
		public void run() {
			boolean sendCmd = false;
			{
				mBtPhoneNumber = DataHost.sPhoneNum;
				if (mBtPhoneNumber == null) {
					mBtPhoneNumber = "";
				}
				else
					sendCmd = true;
			}
			
			if (sendCmd) 
				writeBtNumber(mBtPhoneNumber);
			
		}
	};

	private Runnable mId3Song = new Runnable() {
		@Override
		public void run() {
			id3Cmd((byte)0x92, DataHost.sId3Title);
		}
	};
	
	private Runnable mId3Artist = new Runnable() {
		@Override
		public void run() {
			id3Cmd((byte)0x94, DataHost.sId3Artist);
		}
	};
	
	private Runnable mId3ALBUM = new Runnable() {
		@Override
		public void run() {
			id3Cmd((byte)0x93, DataHost.sId3Album);
		}
	};
	// 以字符串Unicode编码方式发送 32个字节， 16个字符
	private void id3Cmd(byte cmd, String str) {
		
		if (str == null) str = ""; 
		int start = 0;
		int [] data = new int[32];
		int len = str.length() >= 15 ? 15 : str.length();
		int unicode;
		for (int i = 0; i < len; i++) {
			unicode = str.codePointAt(i);
			data[(i<<1)+start + 1] = unicode>>8&0xFF;
			data[(i<<1)+start] = unicode&0xFF;
		} 
		SendFunc.send2Canbus(cmd, data);
	}
	private byte getBtState(){
		switch (DataHost.sPhoneSate) {
		case FinalBt.PHONE_STATE_DISCONNECTED:
			return 0x06;
		case FinalBt.PHONE_STATE_CONNECTED://挂断
			return 0x07;
		case FinalBt.PHONE_STATE_LINK:
			return 0x07;
//		case FinalBt.PHONE_STATE_INVALID:
//		case FinalBt.PHONE_STATE_LOAD:
//		case FinalBt.PHONE_STATE_PAIR:
		case FinalBt.PHONE_STATE_RING://来电
			return 0x01;
		case FinalBt.PHONE_STATE_TALK:
			return 0x04;
		case FinalBt.PHONE_STATE_DIAL://去电
			return 0x02;
		}
		return 0x07;
		
	}
	private void writeBtNumber(String num) {
		if (num == null) return;
		int [] cmds = new int[0x1B];
//		cmds[0] = (byte)0xe3;
//		cmds[1] = (byte)0x1b;
//		cmds[2] = (byte)0xcd;
//		
		cmds[0] = getBtState();
		cmds[1] = 0;
		cmds[2] =  0;
		int unicode;
		int start = 3;
		int len = num.length() > 12 ? 12 : num.length();
		for (int i = 0; i < len; i++) {
			unicode = num.codePointAt(i);
			cmds[(i<<1)+start+1] = unicode>>8&0xFF;
			cmds[(i<<1)+start] = unicode&0xFF;
		}

		SendFunc.send2Canbus(0xCD, cmds); 
	}

	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_SET_AIR:{
			if(ints!= null && ints.length > 0)
				SendFunc.send2Canbus(0x3D, ints);
			break;
		}
		case C_SET_AIR_REMOVE:
			if(ints!= null && ints.length > 0)
				SendFunc.send2Canbus(0x3B, ints);
			break;
		case C_SET_CAR:
			if(ints!= null && ints.length > 0)
				SendFunc.send2Canbus(0x6D, ints);
			break;
		case C_SET_CARTYPE:
			if(ints!= null && ints.length > 0)
				SendFunc.send2Canbus(0x24, ints);
			break; 
		}
	};
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if (code >= 0 && code < U_CNT_MAX) {
			if(DataCanbus.DATA[code] != 0) {
				HandlerTaskCanbus.update(code);
			}
		}
	}
}
