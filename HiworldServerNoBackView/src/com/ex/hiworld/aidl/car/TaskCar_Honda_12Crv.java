package com.ex.hiworld.aidl.car;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;

import android.os.RemoteException;
import android.text.format.DateFormat;

public class TaskCar_Honda_12Crv extends BaseCar {
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
	
	public static final int U_MEDIA_SRC 					= U_ENGINE_SPEED + 1; 
	public static final int U_MEDIA_PlAY_INDEX 				= U_MEDIA_SRC + 1; 
	public static final int U_MEDIA_PlAY_CNT 				= U_MEDIA_SRC + 2; 
	public static final int U_MEDIA_PlAY_TIME 				= U_MEDIA_SRC + 3; 
	public static final int U_MEDIA_PlAY_PERCENT 			= U_MEDIA_SRC + 4; 
	public static final int U_MEDIA_PlAY_STATE 				= U_MEDIA_SRC + 5; 
	
	public static final int U_CNT_MAX						= U_MEDIA_SRC + 7;
	
	public final static int C_CMD_MEDIA = 1;

	 int KeyCanKeyTable[][]=
		 {
	 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
	 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
	 			{3, 		FinalKeyCode.KEY_CODE_NULL},
	 			{4,			FinalKeyCode.KEY_CODE_NULL},
	 			{5,			FinalKeyCode.KEY_CODE_PHONE}, // 接听 和上一曲， 挂断和下一曲同一个按键
	 			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
	 			{7,			FinalKeyCode.KEY_CODE_NULL},
	 			{9,			FinalKeyCode.KEY_CODE_PREV},
	 			{8,			FinalKeyCode.KEY_CODE_NEXT},
	 			{0x0A,		FinalKeyCode.KEY_CODE_MODE},	
	 			{0x0B,		FinalKeyCode.KEY_CODE_VA},	
		 };
	@Override
	public void onHandler(int[] data) {
		int start  = 0;
		switch (data[start + 1]) {
		case 0x11: {
			int data6 = data[start + 8];
			int data7 = data[start + 9];
			CarBackTrackHandle(data6, data7);
			break;
		}
		case 0x12: {
			int B0 = data[start + 4];
			HandlerTaskCanbus.update(U_DOOR_FL, B0 >>7 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_FR, B0 >>6 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RL, B0 >>5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B0 >>4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B0 >>3 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_ENGINE, B0 >>2 & 0x01);
			break;
		}	
		case 0x72: {
			int data0 = data[start + 2];
			int data1 = data[start + 3];
			int data2 = data[start + 4];
			HandlerTaskCanbus.update(U_CUR_SPEED, data1);
			if(DataHost.sAppid == FinalMain.APP_ID_CAR_USB) {
				if(data2 == 0x08) {// next
					SendFunc.send2Canbus(0xAC, new int[] {0x07, 1});
					return;
				}else if(data2 == 0x09){ // prev
					SendFunc.send2Canbus(0xAC, new int[] {0x07, 0});
					return;
				}
			}
			CanInfos.onKeyEvent(KeyCanKeyTable, data2);
			break;
		}
		case 0xA4: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];

			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			int B8 = data[start + 10];
			int B9 = data[start + 11];
			int B10 = data[start + 12];

			HandlerTaskCanbus.update(U_MEDIA_SRC, B0 & 0xF);
			HandlerTaskCanbus.update(U_MEDIA_PlAY_INDEX, Utils.combine(B4, B3));
			HandlerTaskCanbus.update(U_MEDIA_PlAY_CNT, Utils.combine(B6, B5));
			HandlerTaskCanbus.update(U_MEDIA_PlAY_TIME, Utils.combine(B7, B8));
			HandlerTaskCanbus.update(U_MEDIA_PlAY_PERCENT, B9);
			HandlerTaskCanbus.update(U_MEDIA_PlAY_STATE, B10);

			break;
		}
		}
	}

	int lastOffset = 0;

	private void CarBackTrackHandle(int data6, int data7) {
		int pos = data6 >> 7 & 0x01;
		int value = (data6 & 0x7F) << 8 | data7; // 0 ~ 5200
		int step = 5200 / 20;
		int offset = 0;
		
		
		if (pos == 1) { // left
			offset = 20 - value / step;
		} else { // right
			offset = 20 + value / step;
		}
		if (lastOffset != offset) {
			lastOffset = offset;
			LogsUtils.i("mM " + lastOffset);
			SendFunc.sendGuiji(offset);
		}
	}

	@Override
	public void in() {
		EventNotify.NE_ID3_ALBUM.addNotify(N_ID3_ALBUM, 1);
		EventNotify.NE_ID3_TITLE.addNotify(N_ID3_TITLE, 1);
		EventNotify.NE_ID3_ARTIST.addNotify(N_ID3_ARTIST, 1);
		EventNotify.NE_RADIO_BAND.addNotify(nAPP, 1);
		EventNotify.NE_RADIO_FREQS.addNotify(nAPP, 1);
		Ticks.addTicks1s(TIMESET);
		Ticks.addTicks1s(nAPP);
	}

	@Override
	public void out() {
		EventNotify.NE_ID3_ALBUM.removeNotify(N_ID3_ALBUM);
		EventNotify.NE_ID3_TITLE.removeNotify(N_ID3_TITLE);
		EventNotify.NE_ID3_ARTIST.removeNotify(N_ID3_ARTIST);
		EventNotify.NE_RADIO_BAND.removeNotify(nAPP);
		EventNotify.NE_RADIO_FREQS.removeNotify(nAPP);
		Ticks.removeTicks1s(TIMESET);
		Ticks.removeTicks1s(nAPP);
	}

	int tempData[];
	Runnable nAPP = new Runnable() {
		@Override
		public void run() {
			carDisplayInfo();
		}
	};
	int getSourceId() {
		byte sourceid;
		sourceid = 0x00;
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			sourceid = 0x08;
			break;
		case FinalMain.APP_ID_DVD:// 2
			 sourceid=0x0d;
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
		case FinalMain.APP_ID_CAR_RADIO:// 18// 原车收音
			break;
		case FinalMain.APP_ID_CAR_BTPHONE:// 19// 原车蓝牙
			sourceid = (byte) 0x85;
			break;
		case FinalMain.APP_ID_CAR_USB:
			sourceid = (byte) 0x15;
			break;
		default:
			break;
		}
		if (DataHost.sBackCar == 1) {// 不管任何情况下倒车，都要发送显示"CAREMA"否则协议盒会关闭视频
			sourceid = 0x10;// Carmera
		}
		return sourceid;
	}
	void carDisplayInfo() {
		int[] cmds = new int[0xD];
		Arrays.fill(cmds, ' ');
		cmds[0] = getSourceId();
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_RADIO:
			int freq = DataHost.sRadioFreq;
			if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
				cmds[1] = DataHost.sRadioChannel / 10 + '0';
				cmds[2] = (DataHost.sRadioChannel+1) % 10 + '0';
				cmds[4] = DataHost.sRadioFreq / 10000 + '0';
				cmds[5] = DataHost.sRadioFreq / 1000 % 10 + '0';
				cmds[6] = DataHost.sRadioFreq / 100 % 10 + '0';
				cmds[7] = '.';
				cmds[8] = DataHost.sRadioFreq / 10 % 10 + '0';
 			} else {
				cmds[1] = DataHost.sRadioChannel / 10 + '0';
				cmds[2] = (DataHost.sRadioChannel+1) % 10 + '0';
				freq %= 10000;
				cmds[4] = freq / 1000 == 0 ? ' ' : freq / 1000 + '0';
				cmds[5] = DataHost.sRadioFreq / 100 % 10 + '0';
				cmds[6] = DataHost.sRadioFreq / 10 % 10 + '0';
				cmds[7] = DataHost.sRadioFreq % 10 + '0';
			}
			break;
		case FinalMain.APP_ID_VIDEO_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:{// 15
			if (DataHost.sPlayTotal == 0)
				break;// 没曲目信息时，就不要显示曲目了
			int temp16 = DataHost.sCurPlayIndex % 1000;
			cmds[1] = temp16 / 100 + '0';
			cmds[2] = (temp16 / 10) % 10 + '0';
			cmds[3] = (temp16 % 10) + '0';
			temp16 = DataHost.sCurPlayTime / 60;
			temp16 %= 60;
			cmds[5] = temp16 / 10 + '0'; 
			cmds[6] = temp16 % 10 + '0'; 
			temp16 = DataHost.sCurPlayTime;
			temp16 %= 60;
			cmds[7] = temp16 / 10 + '0'; 
			cmds[8] = temp16 % 10 + '0'; 
			break;
		}
		default:
			break;
		}
		if(tempData == null || !Arrays.equals(tempData, cmds)) {
			SendFunc.send2Canbus(0xE1, cmds);
			tempData = cmds;
		}
	}
	
	private Runnable TIMESET = new Runnable() {
		long lastMin;
		long lastFormat;

		@Override
		public void run() {
			GregorianCalendar calendar = new GregorianCalendar();
			int min = calendar.get(Calendar.MINUTE);
			int format = DateFormat.is24HourFormat(MyApp.getInstance()) ? 0 : 1;
			if (min != lastMin || lastFormat != format) {
				lastFormat = format;
				lastMin = min;

				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR);
				int sec = calendar.get(Calendar.SECOND);
				SendFunc.sendTime3(hour, min, sec);
			}
		}
	};
    private IUiNotify N_ID3_TITLE = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            sendHostMediaInfo(0xE2, DataHost.sId3Title);
        }
    };
    
    private IUiNotify N_ID3_ARTIST = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
        	sendHostMediaInfo(0xE4, DataHost.sId3Artist);
        }
    };

    private IUiNotify N_ID3_ALBUM = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) { 
        	sendHostMediaInfo(0xE3, DataHost.sId3Album);
        }
    };
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_CMD_MEDIA:
			if(ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0xAC, ints);
			}
			break;

		default:
			break;
		}
	};
	

	protected void sendHostMediaInfo(int cmd, String inf) {
		int[] convert = ccFormat(inf, 0x20);
		SendFunc.send2Canbus(cmd, convert);
	}

	public static int[] ccFormat(String s, int needLen) {
		if (s == null)
			s = "";
		System.out.println(s + " . " + s.length());
		int mm[] = new int[needLen];
		int strlen = s.length();
		for (int i = 0, j = 0; i < needLen - 2; j++) {
			if (j < strlen) {
				int c = s.codePointAt(j);
				mm[i++] = c & 0xFF;
				mm[i++] = c >> 8 & 0xFF;
			}else {
				break;
			}
		}
		return mm;
	}
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if (code >= 0 && code < U_CNT_MAX) {
			if(DataCanbus.DATA[code] != 0) {
				HandlerTaskCanbus.update(code);
			}
		}
	}
}
