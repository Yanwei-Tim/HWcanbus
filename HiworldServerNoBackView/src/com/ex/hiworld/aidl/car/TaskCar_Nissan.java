package com.ex.hiworld.aidl.car;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.canbus.TypeWC1_Data;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;

import android.os.RemoteException;
import android.text.format.DateFormat;

public class TaskCar_Nissan extends BaseCar {
	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= 0;
	public static final int U_AIR_POWER_ON					= U_AIR_BEGIN+0;
	public static final int U_AIR_AC_ON						= U_AIR_BEGIN+1;
	public static final int U_AIR_CYCLE_TYPE				= U_AIR_BEGIN+2;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+3;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+5;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_AUTO_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+9;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+10;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+11;
	public static final int U_AIR_SYNC						= U_AIR_BEGIN+12;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+13;
	public static final int U_AIR_BLOW_WIN_LEFT				= U_AIR_BEGIN+14;
	public static final int U_AIR_WIND_MODE					= U_AIR_BEGIN+15;
	public static final int U_CUR_SPEED						= U_AIR_BEGIN+16;
	public static final int U_ENGINE_SPEED					= U_AIR_BEGIN+17;
	
	

	private static final int U_SET_AMP_CARVOL 				= U_ENGINE_SPEED + 1;
	private static final int U_SET_AMP_LeftRight 			= U_SET_AMP_CARVOL + 1;
	private static final int U_SET_AMP_FrontRear 			= U_SET_AMP_CARVOL + 2;
	private static final int U_SET_AMP_LOW 					= U_SET_AMP_CARVOL + 3;
	private static final int U_SET_AMP_MID 					= U_SET_AMP_CARVOL + 4;
	private static final int U_SET_AMP_HIGH 				= U_SET_AMP_CARVOL + 5;
	

	private static final int U_SET_AMP_DRIVER_FIELD 	= U_SET_AMP_CARVOL + 6;
	private static final int U_SET_AMP_BOSE_CENTER 		= U_SET_AMP_CARVOL + 7;
	private static final int U_SET_AMP_LINK_SPEED 		= U_SET_AMP_CARVOL + 8;
	private static final int U_SET_AMP_SURROUND  		= U_SET_AMP_CARVOL + 9;
	
	public static final int U_CNT_MAX						= U_SET_AMP_SURROUND+1;
	
	
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	 
	
	

	private static final int C_AIR_CONTROL_CMD = 0; //空调控制命令 长度0x02，int[0] 命令 int[1] 参数
	private static final int C_AMP_SET = 1;
	private static final int C_CAMERA = 2;
	
	 int  RadarDistance;
	 int Vol_dis_cnt = 0;
	 int CarAroundCam,TemCanKey,TemCanKey2,TemCanKey3,CanKey,CarTurboStpValueTemp,CanKey2= 0;
	
	  int KeyCanKeyTable[][]=
			 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_VA},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
		 			{7,			FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_PREV},
		 			{9,			FinalKeyCode.KEY_CODE_NEXT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},		
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},		
		 			{0x0f,		FinalKeyCode.KEY_CODE_PLAYPAUSE},		
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},		
		 			
		 			{0x01|0x80,		FinalKeyCode.KEY_CODE_UP},		
		 			{0x02|0x80,		FinalKeyCode.KEY_CODE_DOWN},		
		 			{0x03|0x80,		FinalKeyCode.KEY_CODE_LEFT},		
		 			{0x04|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x05|0x80,		FinalKeyCode.KEY_CODE_PREV},		
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NEXT},		
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_LEFT},		
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_BACK},		
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_MENU},//setup		
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_NAVI},		
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_MENU},		
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_MENU},		
			 }; 
	
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start + 1]) {
		case 0x72: {
			CanInfos.setExistFullViewFloatBtn(data[start + 2] >> 7 & 0x01);
			CanInfos.CarBackTrackHandle(data[start + 6] & 0xFF, data[start + 7] & 0xFF);

			CanKey = data[start + 4] & 0xFF;
			CanInfos.onKeyEvent2(KeyCanKeyTable, CanKey);

			HandlerTaskCanbus.update(U_ENGINE_SPEED, Utils.combine(data[start + 10], data[start + 11]));
			HandlerTaskCanbus.update(U_CUR_SPEED, data[start + 3]);
			break;
		}
		case 0xF2: {
			if (data[start + 2] == 1)
				CanInfos.jumpFullView();
			else
				CanInfos.exitFullView();
			break;
		}
		case 0xE0: {
			CarMediaSrcTypeHandle(data[start + 2] & 0xff);
			break;
		}
		case 0xA6: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];

			HandlerTaskCanbus.update(U_SET_AMP_CARVOL, B0);
			HandlerTaskCanbus.update(U_SET_AMP_LeftRight, B2);
			HandlerTaskCanbus.update(U_SET_AMP_FrontRear, B1);
			HandlerTaskCanbus.update(U_SET_AMP_LOW, B3);
			HandlerTaskCanbus.update(U_SET_AMP_MID, B4);
			HandlerTaskCanbus.update(U_SET_AMP_HIGH, B5);

			HandlerTaskCanbus.update(U_SET_AMP_DRIVER_FIELD, B6 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_SET_AMP_BOSE_CENTER, B6 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_SET_AMP_LINK_SPEED, B6 & 0x07);
			HandlerTaskCanbus.update(U_SET_AMP_SURROUND, B7);
			break;
		}
		case 0x41: {
			CanInfos.radarRl(TypeWC2_Data.CarGetRadarDistancef(data[start + 2] & 0xff));
			CanInfos.radarRml(TypeWC2_Data.CarGetRadarDistancef(data[start + 3] & 0xff));
			CanInfos.radarRmr(TypeWC2_Data.CarGetRadarDistancef(data[start + 4] & 0xff));
			CanInfos.radarRr(TypeWC2_Data.CarGetRadarDistancef(data[start + 5] & 0xff));
			CanInfos.radarFl(TypeWC2_Data.CarGetRadarDistancef(data[start + 6] & 0xff));
			CanInfos.radarFml(TypeWC2_Data.CarGetRadarDistancef(data[start + 7] & 0xff));
			CanInfos.radarFmr(TypeWC2_Data.CarGetRadarDistancef(data[start + 8] & 0xff));
			CanInfos.radarFr(TypeWC2_Data.CarGetRadarDistancef(data[start + 9] & 0xff));
			break;
		}
		case 0x74: {

			CanKey2 = data[start + 2] & 0xFF;

			if (CanKey2 != 0)
				CanKey2 = CanKey2 | 0x80;
			
//			int i, j = 0;
//			for (i = 0; i < KeyCanKeyTable.length; i++) {
//				if (CanKey2 == KeyCanKeyTable[i][0]) {
//					j = i;
//					if (CanKey2 != 0)
//						TemCanKey2 = j;
//					break;
//				}
//			} 
//			
//			if (CanKey2 != 0) {
//				if (i < KeyCanKeyTable.length)
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[i][1], KeyEvent.ACTION_DOWN);
//			} else {
//				if (i == KeyCanKeyTable.length)
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[TemCanKey2][1], KeyEvent.ACTION_UP);
//			}
			
			CanInfos.onKeyEvent2(KeyCanKeyTable, CanKey2);

//			if (CanKey2 == 0) {
//
//				if ((data[start + 3] & 0xff) != CarTurboStpValueTemp) {
//					if ((CarTurboStpValueTemp == 0x00) && ((data[start + 3] & 0xff) == 0xff))
//						CanKey2 = 2;
//					else if ((CarTurboStpValueTemp == 0xff) && ((data[start + 3] & 0xff) == 0x00))
//						CanKey2 = 1;
//					else {
//						if ((data[start + 3] & 0xff) > CarTurboStpValueTemp) {
//							CanKey2 = 1;
//						} else {
//							CanKey2 = 2;
//						}
//					}
//				}
//				CarTurboStpValueTemp = (data[start + 3] & 0xff);
//
////				i = 0;
////				j = 0;
////				for (i = 0; i < KeyCanKeyTable.length; i++) {
////					if (CanKey2 == KeyCanKeyTable[i][0]) {
////						j = i;
////						if (CanKey != 0)
////							TemCanKey3 = j;
////						break;
////					}
////				}
////				// Log.d("LG", "i2="+j);
////				// Log.d("LG", "CanKey="+(CanKey&0xff)+"KeyCanKeyTable =
////				// "+KeyCanKeyTable[j][1]+" TemCanKey="+TemCanKey);
////
////				if (CanKey2 != 0) {
////					if (i < KeyCanKeyTable.length)
////						HandlerAnalysis.keyEvent(KeyCanKeyTable[i][1], KeyEvent.ACTION_DOWN);
////				} else {
////					if (i == KeyCanKeyTable.length)
////						HandlerAnalysis.keyEvent(KeyCanKeyTable[TemCanKey3][1], KeyEvent.ACTION_UP);
////				}
//
//				CanInfos.onKeyEvent(KeyCanKeyTable, CanKey2, data[start+3]);
//			}
			break;
		}
		default:
			break;
		}

	}
	
	void CarMediaSrcTypeHandle(int Mtype) {
		switch (Mtype) {
		case 0x20:// AM
		case 0x21:// FM1
		case 0x22:// FM2
			if (Mtype == 0x20) { 
				CanInfos.jumpRadioAM(); 
			} else if (Mtype == 0x21) { 
				CanInfos.jumpRadioFM();
			} else if (Mtype == 0x22) {
				CanInfos.jumpRadioFM();
			}
			break;
		case 0x23:// CD
			CanInfos.jumpDvd();
			break;
		case 0x24:// USB
			CanInfos.jumpMediaPlayer();
			break;
		case 0x25:// BT MUSIC
			CanInfos.jumpBtAv();
			break;
		case 0x26:// AUX
			CanInfos.jumpAux();
			break;
		case 0x27: // USB2
			CanInfos.jumpMediaPlayer();
			break;
		default:
			break;

		}
	}
	@Override
	public void in() {
		EventNotify.NE_LANG.addNotify(N_LANG);
		Ticks.addTicks1s(TIMESET);

		EventNotify.NE_ID3_TITLE.addNotify(mId3Song, 1);
		EventNotify.NE_RADIO_FREQS.addNotify(TypeWC1_Data.mCarDisNormal_V2, 1);
		EventNotify.NE_RADIO_BAND.addNotify(TypeWC1_Data.mCarDisNormal_V2, 1);
		Ticks.addTicks1s(TypeWC1_Data.mCarDisNormal_V2 );

	}

	@Override
	public void out() {
		CanInfos.setExistFullViewFloatBtn(0);
        Ticks.removeTicks1s(TIMESET);
        EventNotify.NE_LANG.removeNotify(N_LANG);

		EventNotify.NE_ID3_TITLE.removeNotify(mId3Song);
		EventNotify.NE_RADIO_FREQS.removeNotify(TypeWC1_Data.mCarDisNormal_V2);
		EventNotify.NE_RADIO_BAND.removeNotify(TypeWC1_Data.mCarDisNormal_V2);
		 Ticks.removeTicks1s(TypeWC1_Data.mCarDisNormal_V2);
	}


	private Runnable mId3Song = new Runnable() {
		@Override
		public void run() {
			writeID3Cmd(0xD3, DataHost.sId3Title);
		}
	};

	
	private void writeID3Cmd(int cmd, String str) {
		if (str == null)
			str = "";
		int[] data = new int[16];
		int len = str.length() > 7 ? 7 : str.length();
		int unicode;
		for (int i = 0; i < len; i++) {
			unicode = str.codePointAt(i);
			data[(i << 1)] = unicode >> 8 & 0xFF;
			data[(i << 1) + 1] = unicode & 0xFF;
		}

		SendFunc.send2Canbus(cmd, data);
	}
	private Runnable TIMESET = new Runnable() {
        long lastMin, lasthour;
        long lastFormat;

        @Override
        public void run() {
            GregorianCalendar calendar = new GregorianCalendar();
            int min = calendar.get(Calendar.MINUTE);
            int hour = calendar.get(Calendar.HOUR); 
            int format = DateFormat.is24HourFormat(MyApp.getInstance()) ? 1 : 0;
            if (min != lastMin || hour != lasthour || lastFormat != format) {
                lastFormat = format;
                lastMin = min;
                lasthour = hour;
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int sec = calendar.get(Calendar.SECOND); 
                int am = calendar.get(Calendar.AM_PM); 
                SendFunc.sendTime2(year, month, day, hour, min, sec, format, am);
            }
        }
    };
    private IUiNotify N_LANG = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            int val = 1;
            if (DataHost.sLang == FinalMain.LANG_ZH) {
                val = 2;
            }
            SendFunc.send2Canbus(0x9A, new int[]{0x01, val});
        }
    };
    
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		// case C_AIR_CONTROL_CMD:{
		// if(ints != null && ints.length> 0) {
		// SendFunc.send2Canbus(cmd, ints);
		// }
		// break;
		// }
		case C_AMP_SET:
			if (ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0xAD, ints);
			}
			break;
		case FinalCanbus.C_HW_CHANGE_PANORAMA:
		case C_CAMERA: {
			if (ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0XFD, 1, 0);
			}
			break;
		}
		default:
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
