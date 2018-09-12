package com.ex.hiworld.aidl.car;


import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalBt;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;

import android.os.RemoteException;

public class TaskCar_Ford_All extends BaseCar {

	//车速信息
	public static final int U_CARINFO_BEGIN					= 0;
	public static final int U_CAR_CURRENT_SPEED				= U_CARINFO_BEGIN + 1;
	public static final int U_DRIVING_TIME					= U_CARINFO_BEGIN + 2;
	public static final int U_OPTIMAL_OIL_EXPEND			= U_CARINFO_BEGIN + 3;
	public static final int U_DISPLAY_MODE					= U_CARINFO_BEGIN + 4;
	public static final int U_CAR_BT_STATE					= U_CARINFO_BEGIN + 5;
	public static final int U_PLAY_TIME						= U_CARINFO_BEGIN + 6;
	public static final int U_SCREEN_ID						= U_CARINFO_BEGIN + 7;
	public static final int U_SCREEN_ICON					= U_CARINFO_BEGIN + 8;
	public static final int U_LINE_GROUP					= U_CARINFO_BEGIN + 9;
	public static final int U_CAR_WARN						= U_CARINFO_BEGIN + 10;//车身警告
	public static final int U_CARINFO_END					= U_CARINFO_BEGIN + 11;
	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= U_CARINFO_END;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+1;
//	public static final int U_AIR_BLOW_WIN_LEFT				= U_AIR_BEGIN+1;
	public static final int U_AIR_CYCLE						= U_AIR_BEGIN+2;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+3;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_AC						= U_AIR_BEGIN+5;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+8;
//	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+8;
//	public static final int U_AIR_BLOW_BODY_RIGHT			= U_AIR_BEGIN+5;
//	public static final int U_AIR_BLOW_FOOT_RIGHT			= U_AIR_BEGIN+6;
//	public static final int U_AIR_BLOW_UP_RIGHT				= U_AIR_BEGIN+7;
	public static final int U_AIR_MAX						= U_AIR_BEGIN+9;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+10;
	public static final int U_AIR_REAR_WIND_LEVEL			= U_AIR_BEGIN+11;
//	public static final int U_AIR_SEAT_BLOW_HEAT_LEFT		= U_AIR_BEGIN+13;
//	public static final int U_AIR_SEAT_BLOW_HEAT_RIGHT		= U_AIR_BEGIN+14;
//	public static final int U_AIR_DUAL						= U_AIR_BEGIN+10;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+12;
	public static final int U_AIR_POWER						= U_AIR_BEGIN+13;
//	public static final int U_AIR_SHOW						= U_AIR_BEGIN+11;
//	public static final int U_AIR_BIG_LIGHT					= U_AIR_BEGIN+20;
	public static final int U_AIR_REAR_TEMP						= U_AIR_BEGIN+14;
//	public static final int U_AIR_CONTROL					= U_AIR_BEGIN+13;	
//	public static final int U_AIR_TEMP_UNIT					= U_AIR_BEGIN+21;
//	public static final int U_AIR_FRONT_WIND_MODE			= U_AIR_BEGIN+22;
//	public static final int U_AIR_REAR_WIND_MODE			= U_AIR_BEGIN+23;
//	public static final int U_AIR_REAR_TEMP_LEFT			= U_AIR_BEGIN+24;
//	public static final int U_AIR_REAR_TEMP_RIGHT			= U_AIR_BEGIN+25;
	public static final int U_AIR_BLOW_AUTO_LEFT			= U_AIR_BEGIN+15;
	public static final int U_AIR_BLOW_WIN_LEFT				= U_AIR_BEGIN+16;
//	public static final int U_AIR_SYNC						= U_AIR_BEGIN+15;	
	public static final int U_AIR_REAR_PANEL_ENBALE			= U_AIR_BEGIN+17;
	public static final int U_AIR_REAR_POWER				= U_AIR_BEGIN+18;
	public static final int U_AIR_SEAT_HEAT_LEFT			= U_AIR_BEGIN+19;
	public static final int U_AIR_SEAT_HEAT_RIGHT			= U_AIR_BEGIN+20;
	public static final int U_AIR_TEMP_UNIT					= U_AIR_BEGIN+21;
	public static final int U_AIR_MAXHEAT					= U_AIR_BEGIN+22;
	public static final int U_AIR_END						= U_AIR_BEGIN+23;
	
	/**
	 * 车门
//	 */
	public static final int U_DOOR_BEGIN					= U_AIR_END;
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

	public static final int U_FLASH_LIGHT 			= U_CAR_SRC + 1; // 转向闪烁灯
	public static final int U_PULL_CONTROL 			= U_CAR_SRC + 2; // 牵引力控制
	public static final int U_MILES_UNIT 			= U_CAR_SRC + 3; // 里程单位
	public static final int U_MESSAGE_SOUND 		= U_CAR_SRC + 4; // 消息提示音
	public static final int U_WARN_SOUND 			= U_CAR_SRC + 5; // 警告提示音
	
	public static final int U_CARINFO_DRIVERS_TOTAL_MILES 	= U_CAR_SRC + 6;
	public static final int U_CARINFO_CARMERA_DELAY 		= U_CAR_SRC + 7;
	public static final int U_CARINFO_CAR_NUMBER 			= U_CAR_SRC + 8;
	
	public static final int U_CNT_MAX						= U_CAR_SRC	+ 9;
	
	public static final int CYCLE_OUTER			= 0;
	public static final int CYCLE_INNER_MANUAL	= 1;
	public static final int CYCLE_AUTO			= 2;
	
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	
	private static final int C_BUTTON_TOUCHED = 0;
	private static final int C_RESEND_CMD = 1;
//	private static final int C_CHANNEL_NAVI = 2;
	private static final int C_LANGUAGE_SET = 3;  //语言设置
	private static final int C_TIPS_SET_CMD = 4;	//提示设置命令
	private static final int C_CAR_SET		= 5;	//原车设置命令
	private static final int C_CAR_LIGHT_SET = 7;
	private static final int C_CAR_SPORT_SET = 8;
	private static final int C_CAR_SYNC_KEYCMD = 9;
	private static final int C_CAR_SYNC_REQUEST = 10;
	
//-------------------------------------------------------------------------------------------------
//	private static final String TAG = "FOCUS";
//	private static final String ACTION_FOCUS_RECEIVER = "com.syu.canbus.focus.sync";
//	private static final String KEY_VOICE_MODE = "key.voice.mode";
//	private static final String KEY_DISPLAY_MODE = "key.display.mode";
	int[] mWarnStatus = new int[57];
	
	 int KeyCanKeyTable[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_NEXT}, // 接听 和上一曲， 挂断和下一曲同一个按键
		 			{6, 		FinalKeyCode.KEY_CODE_PREV}, 
		 			{7,			FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0d,		FinalKeyCode.KEY_CODE_UP},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_DOWN},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x62,		FinalKeyCode.KEY_CODE_PLAYPAUSE},		
		 			{0x65,		FinalKeyCode.KEY_CODE_EJECT},		
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},		

		 			{0x01|0x80,		FinalKeyCode.KEY_CODE_POWER},
		 			{0x02|0x80,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x03|0x80,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x05|0x80,		FinalKeyCode.KEY_CODE_EQ},	
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},	
		 			{0x12|0x80,		FinalKeyCode.KEY_CODE_NAVI},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_UP},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_DOWN},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_LEFT},		
		 			{0x1a|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x1f|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_ENTER},		
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},		
		 			{0x2d|0x80,		FinalKeyCode.KEY_RADIO},		
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_SEARCH},		
		 			{0x30|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x34|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x35|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x36|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x37|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x38|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x39|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x3a|0x80,		FinalKeyCode.KEY_CODE_PLAYER},		
		 			{0x3b|0x80,		FinalKeyCode.KEY_CODE_EQ},		
		 			{0x3c|0x80,		FinalKeyCode.KEY_CODE_FB},		
		 			{0x3d|0x80,		FinalKeyCode.KEY_CODE_FF},		
		 			{0x3e|0x80,		FinalKeyCode.KEY_CODE_SEARCH},		
		 			{0x3f|0x80,		FinalKeyCode.KEY_CODE_CARSETTTING},	
		 			{0x10|0x80,		FinalKeyCode.KEY_CODE_PLAY},
		 			{0x13|0x80,		FinalKeyCode.KEY_CODE_TIMESETTINT},
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_PLAYER},				
		 			
		 };
	private int[] linesGroupInts;
	private String[] linesGroupStrs = new String[16];
	 
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		int length = data.length;
		switch (data[start + 1]) {
		case 0x11: {
			HandlerTaskCanbus.update(U_CUR_SPEED, data[start + 1 + 2] & 0xff); // 车速 km/h
			switch (data[start+4]) {
			case 0x5: // next hang
			{
				switch (DataHost.sPhoneSate) {
				case FinalBt.PHONE_STATE_DIAL:
				case FinalBt.PHONE_STATE_RING:
				case FinalBt.PHONE_STATE_TALK:
					CanInfos.canbusKeyHang();
					return; 
				} 
				break;
			}
			case 0x6: // prev pick
			{
				switch (DataHost.sPhoneSate) { 
				case FinalBt.PHONE_STATE_RING: 
					CanInfos.canbusKeyPick();
					return; 
				}
			} 
			}
			
			CanInfos.onKeyEvent(KeyCanKeyTable, data[start + 4], data[start + 5]);
			break;
		}
		case 0x12: {
			int B0 = data[start + 4];
//			if (DataCanbus.sDriverOnRight == 1) {
//				HandlerTaskCanbus.update(U_DOOR_FR, B0 >>7 & 0x01);
//				HandlerTaskCanbus.update(U_DOOR_FL, B0 >>6 & 0x01);
//			} else {
				HandlerTaskCanbus.update(U_DOOR_FL, B0 >>7 & 0x01);
				HandlerTaskCanbus.update(U_DOOR_FR, B0 >>6 & 0x01);
//			}
			HandlerTaskCanbus.update(U_DOOR_RL, B0 >>5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B0 >>4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B0 >>3 & 0x01);

			SendFunc.setExistDoor(B0 &0x01);
			break;
		}	
		case 0x13: {
			HandlerTaskCanbus.update(U_CARINFO_DRIVERS_TOTAL_MILES, Utils.combine(data[start+8], data[start+9], data[start+10]));
			break;	
		}
		case 0x21: {
			CanInfos.onKeyEvent(KeyCanKeyTable, data[start + 2] | 0x80, data[start + 3]);
			break;
		}
		case 0x22: {
			if (data[start + 2] == 0x01 && data[start + 3] != 0)// Volume
			{
				if ((data[start + 3] & 0xff) < 0x80) {
					CanInfos.mcuKeyVolUp();
				} else {
					CanInfos.mcuKeyVolDown();
				}
			} else if (data[start + 2] == 0x02 && data[start + 3] != 0)// TUNE
			{
				if ((data[start + 3] & 0xff) < 0x80) {
					CanInfos.canbusKeyNext();
				} else {
					CanInfos.canbusKeyPrev();
				}
			}
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
			HandlerTaskCanbus.update(U_AIR_MAXHEAT,	B1>>7 & 0x01); // 仅 原翼虎 翼博
			HandlerTaskCanbus.update(U_AIR_MAX, 		B1>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_CYCLE, 		(B1>>4&0x01)==1? 0  : 1);
			HandlerTaskCanbus.update(U_AIR_AUTO, 		B1>>3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC, 			B1>>0 & 0x01);

			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, B2>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, B2>>4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_RIGHT, B2>>2 & 0x03);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEFT, B2>>0 & 0x03);
			
			int auto = 0,win = 0,foot = 0,body = 0;
			switch (B4&0xff) { 
			case 0x01:	auto = 1;	break;
			case 0x02:	win = 1;	break;
			case 0x03:	foot = 1;	break;
			case 0x05:	body = 1;	foot = 1;	break;
			case 0x06:	body =1;	break;
			case 0x0B:	win = 1;	break;
			case 0x0C:	win = 1;	foot =1;	break;
			case 0x0D:	win = 1;	body = 1;	break;
			case 0x0E:	win = 1;	foot = 1;	body = 1;	break;
			}
			HandlerTaskCanbus.update(U_AIR_BLOW_AUTO_LEFT, 		auto);			
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, 		body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, 		foot);
			HandlerTaskCanbus.update(U_AIR_BLOW_WIN_LEFT, 		win);
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
			default:
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
			//后排空调控制开关
			HandlerTaskCanbus.update(U_AIR_REAR_PANEL_ENBALE, 		B8>> 7 &0x01);
			//后排空调开关
			HandlerTaskCanbus.update(U_AIR_REAR_POWER, 		B8>> 6 &0x01);
			//后排风速
			HandlerTaskCanbus.update(U_AIR_REAR_WIND_LEVEL, 		B9&0xff); // 7
			//	后排设定温度
			HandlerTaskCanbus.update(U_AIR_REAR_TEMP, 		B10&0xff);
			break;
		}
		case 0x32: {
			HandlerTaskCanbus.update(U_CUR_SPEED, Utils.combine(data[start + 6], data[start + 7]));
			HandlerTaskCanbus.update(U_ENGINE_SPEED, Utils.combine(data[start + 4], data[start + 5]));
			break;
		}
		case 0x34: {// for 15focus
			HandlerTaskCanbus.update(U_CARINFO_DRIVERS_TOTAL_MILES,
					Utils.combine(data[start + 6], data[start + 7], data[start + 8]));
			break;
		}
		case 0x38: {
			HandlerTaskCanbus.update(U_CARINFO_CAR_NUMBER, Utils.getStringFromInts(data, 2, data[0]), "");
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
			break;
		}
		case 0x42:{
			CanInfos.radarRrf(TypeWC2_Data.CarGetRadarDistance(data[start+2]&0xff));
			CanInfos.radarRrmf(TypeWC2_Data.CarGetRadarDistance(data[start+3]&0xff)); 
			CanInfos.radarRrmr(TypeWC2_Data.CarGetRadarDistance(data[start+4]&0xff));
			CanInfos.radarRrr(TypeWC2_Data.CarGetRadarDistance(data[start+5]&0xff));
			CanInfos.radarLf(TypeWC2_Data.CarGetRadarDistance(data[start+6]&0xff));  
			CanInfos.radarLmf(TypeWC2_Data.CarGetRadarDistance(data[start+7]&0xff));
			CanInfos.radarLmr(TypeWC2_Data.CarGetRadarDistance(data[start+8]&0xff));
			CanInfos.radarLr(TypeWC2_Data.CarGetRadarDistance(data[start+9]&0xff)); 
			break;
		}
		case 0x67:{ // 照明设定信息
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_FLASH_LIGHT, 	 B1&0x01);
			break;
		}
		case 0x68:{			//提示信息
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_MILES_UNIT,		 	B1>>7&0x01);
			HandlerTaskCanbus.update(U_MESSAGE_SOUND,		B1>>6&0x01);
			HandlerTaskCanbus.update(U_WARN_SOUND,		 	B1>>5&0x01);
			//温度单位  0 华氏 1 摄氏
			HandlerTaskCanbus.update(U_OPTIMAL_OIL_EXPEND, 	 B1>>4&0x01);
			HandlerTaskCanbus.update(U_AIR_TEMP_UNIT,		 B1>>4&0x01);
			break;
		}
		case 0x74: {// warning
			
			break;
		}
		case 0x85: {
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_PULL_CONTROL, 	 B1&0x01);
			break;
		}
		/**********************************************************************************
		 * 以下SYNC增补协议   电话号码只能显示8位   （18 - 2）/ 2
		 ************************************************************************************/
		case 0xD0: {// 显示信息
			isSameScreen = false;
			if (sScreenId == (data[start + 2] & 0x0ff)) {
				isSameScreen = true;
			}
			if(!isSameScreen) {
				for (int i = 0; i < linesGroupStrs.length; i++) {
					linesGroupStrs[i] = null;
				}
			}
			sScreenId = data[start + 2] & 0x0ff;
 
			HandlerTaskCanbus.update(U_SCREEN_ID, sScreenId);
			int B1 = data[start + 3];
			String str = "";
			if ((B1 >> 4 & 0x0f) == 0x0f) {
				int[] ints = new int[16];
				for (int i = 0; i < 0x10; i++) {
					ints[i] = data[start + 4 + i];
				} 
				HandlerTaskCanbus.update(U_SCREEN_ICON, ints, null, null);
				lastScreenIcons = ints;
			} else {
				StringBuilder sb = new StringBuilder();
				int i, end;
				for (i = start + 4, end = start + length - 1; i < end; i += 2) {
					if (data[i] == 0 && data[i + 1] == 0)
						break;
					sb.append((char) ((data[i] << 8 & 0xffff) | (data[i + 1] & 0xff)));
				}
				str = sb.toString();

			}
			lineIndex = (B1 >> 4) & 0x0f - 1;
			groupIndex = B1 & 0x0f ;
			
			LogsUtils.i("屏号：" + sScreenId + " 行号:" + lineIndex + "  组号："+ groupIndex + " unicode:" + str);		
			
			if (groupIndex >= 0 && groupIndex < 4) { // 2018-8-9 上车发现 group 0 ~ 3
				if(isSameScreen  && groupIndex > 0) //同屏 同行 不同组的数据 需要拼接在一起
				{
					if (lineIndex >= 0 && lineIndex < 5) {
						if (sLine != null && sLine[lineIndex] != null) {
							sLine[lineIndex][groupIndex] = str;
						}
						str = getStrs(sLine); 
					} else if (lineIndex >= 0x0a && lineIndex <= 0x0d) {
						lineIndex = lineIndex - 0x0a;
						if (sFun != null && sFun[lineIndex] != null) {
							str = sFun[lineIndex][groupIndex] = str;
						}
						
						str = getStrs(sFun); 
					}

				} else // 不同屏的 需要将所有数据清空
				{
					initStrings(sLine);
					initStrings(sFun);
					if (lineIndex >= 0 && lineIndex < 5) {
						if (sLine != null && sLine[lineIndex] != null) {
							sLine[lineIndex][groupIndex] = str;
						}
					} else if (lineIndex >= 0x0a && lineIndex <= 0x0d) {
						lineIndex = lineIndex - 0x0a;
						if (sFun != null && sFun[lineIndex] != null) {
							sFun[lineIndex][groupIndex] = str;
						}
					}
				}

			} 
			linesGroupInts = new int[] {B1 >> 4 &0xF, B1 &0x0F};
			linesGroupStrs[(B1 >> 4) & 0x0f] = str;
			HandlerTaskCanbus.update(U_LINE_GROUP, linesGroupInts, null,
					new String[] { str });

			break;
		}
		case   0xD2: {// 播放信息
			int playTime = ((data[start + 4] & 0x0ff) | (data[start +5]  << 8) & 0x0ffff );
//			DataCarInfo.sHandlerGeneric.updateTimeInfo(playTime / 60,playTime % 60);
//			int time1 = ((byte)data[start + 5] << 8) ;
//			int time2 = ((byte) (data[start + 5] << 8) ) & 0x0ff00;
			HandlerTaskCanbus.update(U_PLAY_TIME, playTime);
			break;
		}
		case  0xD3: {// SYNC状态
//			if(DataCanbus.sExistSync == 0)
//				break; 
			if (sDisplayMode != data[start + 3]) {
				sDisplayMode = data[start + 3];
				setDisplayMode(data[start + 3]);
			}
			HandlerTaskCanbus.update(U_DISPLAY_MODE, data[start + 3]); // 显示模式
			HandlerTaskCanbus.update(U_CAR_BT_STATE,   data[start + 4]); // 蓝牙状态
			break;
			}
		case (byte) 0xE0: {//智能语音信息
			onFuncVAInfo(data[start+ 2], data[start+3], data[start+4]);
			break; 
		}
		case 0xE8: {
			HandlerTaskCanbus.update(U_CARINFO_CARMERA_DELAY,   data[start + 4]&0x01); // 蓝牙状态
			break;
		} 
		}
	}

	private void onFuncVAInfo(int cmdCode, int val1, int val2) {
		switch (cmdCode) {
		case 0x01:
			CanInfos.jumpRadio();
			switch (val1&0xff) {
				case 1:		CanInfos.bandFM(0);	break;
				case 2:		CanInfos.bandFM(1);	break;
				case 3:		CanInfos.bandAM(0);	break;
				case 4:		CanInfos.bandAM(1);	break;
			}
			break;
		case 0x02:
			int freq = Utils.combine(val1, val2);
			//8750 511
			CanInfos.freqAM(freq);
			break;
		case 0x03: {
			int dataLow = val2 & 0xff;
			if (dataLow > 99) {
				dataLow = 99;
			}
			freq = (val1 & 0xff) * 100 + dataLow;
			CanInfos.freqFM(freq);
			break;
		}
		case 0x04:
			CanInfos.selectRadioChannel(val1);
			break;
//		case 0x06:
//			DataDvd.sCmd.track(val1&0xff);	
//			break;
//		case 0x07:
//			JumpPage.dvd();
//			switch (val1&0xff) {
//				case 1:		DataDvd.sCmd.play();	break;
//				case 2:		DataDvd.sCmd.pause();	break;
//			}
//			break;
//		case 0x08:
//		case 0x09:
//			DataDvd.sCmd.random(1);
//			break;
//		case 0x0A:
//			DataDvd.sCmd.random(0);
//			break;
//		case 0x0B:
//		case 0x0C:
//		case 0x0D:
//			DataDvd.sCmd.repeat();	
//			break;
//		case 0x0E:
//			DataIpod.sCmd.track(val1&0xff);
//			break;
//		case 0x0F:
//			JumpPage.ipod();
//			break;
		case 0x10:
			break;
		case 0x11:
			break;
		case 0x12:
			break;
		case 0x13:
			break;
//		case 0x14:
//			DataIpod.sCmd.repeat(FinalIpod.REPEAT_ONE);
//			break;
//		case 0x15:
//			DataIpod.sCmd.repeat(FinalIpod.REPEAT_OFF);
//			break;
		case 0x16:
			CanInfos.jumpBtAv();
			break;
		case 0x17:
			break;
		case 0x18:
			break;
		case 0x19:
			break;
		case 0x1A:
			break; 
		} 
	}

	int[] lastScreenIcons = new int[16];
	public int sDisplayMode = -1;
	private static int sScreenId;
	boolean isSameScreen;
	int lineIndex, groupIndex;
	String[][] sLine = new String[5][4];
	String[][] sFun = new String[4][4];

	private void initStrings(String[][] strs) {
		for (int i = 0; i < strs.length; i++) {
			for (int j = 0; j < strs[i].length; j++) {
				strs[i][j] = "";
			}
		}
	}

	private String getStrs(String[][] strs) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			for (int j = 0; j < strs[i].length; j++) {
				buf.append(strs[i][j]);
			}
		}
		return buf.toString();
	}

	private void setDisplayMode(int value) {
		if (DataHost.sAccon == 0)
			return;

		// 电话状态时，不允许切走状态
		if (DataHost.sPhoneSate == FinalBt.PHONE_STATE_DIAL 
				|| DataHost.sPhoneSate == FinalBt.PHONE_STATE_RING
				|| DataHost.sPhoneSate == FinalBt.PHONE_STATE_TALK) {
			return;
		}
		HandlerTaskCanbus.update(U_CAR_SRC, value);
	}

	static void send_sync_cmd(int cmd){
		int sync_cmd = 0x00;
		if(cmd ==FinalKeyCode.KEY_CODE_LEFT )			sync_cmd =0x0E;
		else if(cmd ==FinalKeyCode.KEY_CODE_RIGHT )		sync_cmd =0x0F;
		else if(cmd ==FinalKeyCode.KEY_CODE_UP )		sync_cmd =0x0C;
		else if(cmd ==FinalKeyCode.KEY_CODE_DOWN)		sync_cmd =0x0D;
		else if(cmd ==FinalKeyCode.KEY_CODE_PREV )		sync_cmd =0x0A;
		else if(cmd ==FinalKeyCode.KEY_CODE_NEXT )		sync_cmd =0x0B;
		else if(cmd ==FinalKeyCode.KEY_CODE_ENTER )		sync_cmd =0x10;
		SendFunc.send2Canbus(0xDA, new int[] {sScreenId, (byte) 0x02, (byte) sync_cmd});
		
	}
	static int key_state(int data){
		if(data == FinalKeyCode.KEY_CODE_LEFT ||data ==  FinalKeyCode.KEY_CODE_RIGHT
		||	data == FinalKeyCode.KEY_CODE_UP || data == FinalKeyCode.KEY_CODE_DOWN
		||  data == FinalKeyCode.KEY_CODE_FF || data == FinalKeyCode.KEY_CODE_FB
		||  data == FinalKeyCode.KEY_CODE_PREV || data == FinalKeyCode.KEY_CODE_NEXT
		||  data == FinalKeyCode.KEY_CODE_ENTER )
			return 1;
		else 
			return 0;
	}
	@Override
	public void in() {
		initStrings(sLine);
		initStrings(sFun);
		EventNotify.NE_LANG.addNotify(mLanguage, 1);
		Ticks.addTicks1s(mCarDisNormal);
		EventNotify.NE_APPID.addNotify(N_APP, 1);
	}

	@Override
	public void out() {
		EventNotify.NE_LANG.removeNotify(mLanguage);
		Ticks.removeTicks1s(mCarDisNormal);
		EventNotify.NE_APPID.removeNotify(N_APP);
	}
	Runnable N_APP =  new Runnable() {
		public void run() {
			if(DataHost.sAppid != FinalMain.APP_ID_CAR_BTPHONE 
					&& DataHost.sAppid != FinalMain.APP_ID_CAR_USB) {
				;SendFunc.send2Canbus(0x91, new int[]{ 00, 0x00, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
			}
		}
	};

	int cnt = 0;
	private Runnable mCarDisNormal = new Runnable() {
		@Override
		public void run() {
			cnt++;
			if (cnt == 5 || DataHost.sIsAppidChange) {
				cnt = 0;
				TypeWC2_Data.CarSendSourceID();
			}
		}
	};
	
	private Runnable mLanguage = new Runnable() {
		@Override
		public void run() {
            int val = 1;
            if (DataHost.sLang == FinalMain.LANG_ZH) {
                val = 2;
            }
            SendFunc.send2Canbus(0x9A, new int[]{0x01, val});
		}
	}; 
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_BUTTON_TOUCHED:// (byte) 0xDA:按键命令
			sendCmd(0xDA, sScreenId, ints[0], ints[1]);
			break;
		case C_RESEND_CMD:// (byte) 0xDC:命令重发
			sendCmd(0xDC, sScreenId, 0xD0, 0);
			break;
		case C_LANGUAGE_SET:
			SendFunc.send2Canbus(0x9A, ints);
			break;
		case C_TIPS_SET_CMD:
			SendFunc.send2Canbus(0x6D, ints);
			break;
		case C_CAR_SET:
			SendFunc.send2Canbus(0xF2, ints);
			break;
		case C_CAR_LIGHT_SET:
			SendFunc.send2Canbus(0x6C, ints);
			break;
		case C_CAR_SPORT_SET:
			SendFunc.send2Canbus(0x8A, ints);
			break;
//		case C_FESTIA_CAR_SET:
			// if(intsOk(ints, 3))
			// ToolkitDev.writeMcu(0xE3, 0x02, (byte) ints[0],(byte) ints[1], ints[2]);
			// break;
		case C_CAR_SYNC_KEYCMD:
			SendFunc.send2Canbus(0xDA, sScreenId, ints[0], ints[1]);
			break;
		case C_CAR_SYNC_REQUEST:
			SendFunc.send2Canbus(0xDC, 0XD0, 1,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 2,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 3,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 4,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 5,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 0xA,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 0xB,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 0xC,  0);
			SendFunc.send2Canbus(0xDC, 0XD0, 0xD,  0);
			break;
		}
	}

	private void sendCmd(int cmd, int param1, int param2, int param3) {
		int[] data = new int[] {(byte) param1, (byte) param2, (byte) param3};
		SendFunc.send2Canbus(cmd, data);
	};
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if (code >= 0 && code < U_CNT_MAX) {
			switch (code) {
			case U_SCREEN_ICON:
				HandlerTaskCanbus.update(code, lastScreenIcons, null, null);
				break;
			case U_LINE_GROUP:
				for (int i = 0; i < linesGroupStrs.length; i++) {
					if(linesGroupStrs[i] != null)
						HandlerTaskCanbus.update(code, new int[] { i, 0 }, null, new String[] { linesGroupStrs[i] });
				}
				break;
			default:
				if(DataCanbus.DATA[code] != 0) {
					HandlerTaskCanbus.update(code);
				}
				break;
			}
		}
	}
}
