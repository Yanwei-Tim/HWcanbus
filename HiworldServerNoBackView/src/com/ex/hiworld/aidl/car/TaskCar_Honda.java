package com.ex.hiworld.aidl.car;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ex.hiworld.aidl.ITaskCallback; 
import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalBt;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;
import com.ex.hiworld.server.tools.spUtils;
import android.annotation.SuppressLint;
import android.os.RemoteException;
import android.text.format.DateFormat;
import android.util.Log;

public class TaskCar_Honda extends BaseCar {
private final int[] MAX = new int[]{600,100,120,200,300,400,500,700,800,900,1000};
	
	/**
	 * 本田类油耗信息公用更新文件
	 */
	public static final int U_MISC_BEGIN							= 0;
	// 即时油耗 单位1
	public static final int U_CURRENT_OIL_CONSUMPTION				= U_MISC_BEGIN+0;
	// 当前平均油耗 单位0.1
	public static final int U_CURRENT_AVERAGE_OIL_CONSUMPTION		= U_MISC_BEGIN+1;
	// 历史平均油耗 单位0.1
	public static final int U_HISTORY_AVERAGE_OIL_CONSUMPTION		= U_MISC_BEGIN+2;
	// 平均油耗 单位0.1
	public static final int U_AVERAGE_OIL_CONSUMPTION				= U_MISC_BEGIN+3;
	// TRIPA 单位0.1
	public static final int U_TRIPA									= U_MISC_BEGIN+4;
	// 续航里程 单位1
	public static final int U_LIFE_MILEAGE							= U_MISC_BEGIN+5;
	// 即时油耗单位 单位0:MPG 1：KM/L 2：L/100KM
	public static final int U_CURRENT_OIL_CONSUMPTION_UNIT			= U_MISC_BEGIN+6;
	// 当前平均油耗 /历史平均油耗 单位0:MPG 1：KM/L 2：L/100KM
	public static final int U_HISTORY_AVERAGE_OIL_CONSUMPTION_UNIT	= U_MISC_BEGIN+7;
	// 平均油耗 单位0:MPG 1：KM/L 2：L/100KM
	public static final int U_AVERAGE_OIL_CONSUMPTION_UNIT			= U_MISC_BEGIN+8;
	// TRIPA 单位0:KM 1：M
	public static final int U_TRIPA_UNIT							= U_MISC_BEGIN+9;
	// 续航里程 单位0:KM 1：M
	public static final int U_LIFE_MILEAGE_UNIT						= U_MISC_BEGIN+10;
	// 油耗量程
	public static final int U_OIL_CONSUMPTION_COUNT					= U_MISC_BEGIN+11;
	// 第一条TRIPA记录 单位0.1
	public static final int U_TRIPA_1								= U_MISC_BEGIN+12;
	// 第一条平均油耗记录 单位0.1
	public static final int U_AVERAGE_OIL_CONSUMPTION_1				= U_MISC_BEGIN+13;
	// 第二条TRIPA记录 单位0.1
	public static final int U_TRIPA_2								= U_MISC_BEGIN+14;
	// 第二条平均油耗记录 单位0.1
	public static final int U_AVERAGE_OIL_CONSUMPTION_2				= U_MISC_BEGIN+15;
	// 第三条TRIPA记录 单位0.1
	public static final int U_TRIPA_3								= U_MISC_BEGIN+16;
	// 第三条平均油耗记录 单位0.1
	public static final int U_AVERAGE_OIL_CONSUMPTION_3				= U_MISC_BEGIN+17;
	public static final int U_SYSTEM_KEY_WITH_EKEY_ENABLED			= U_MISC_BEGIN+18;
	public static final int U_MISC_END								= U_MISC_BEGIN+19;
	
	public static final int OIL_CONSUMPTION_UNIT_MPG				= 0;//MPG
	public static final int OIL_CONSUMPTION_UNIT_KML				= 1;//KM/L
	public static final int OIL_CONSUMPTION_UNIT_L100KM				= 2;//L/100KM
	
	public static final int MILEAGE_KM								= 0;//KM
	public static final int MILEAGE_M								= 1;//M
	
	public final String []OIL_CONSUMPTION_UNIT = new String[]{"MPG","KM/L","L/100KM"};
	public final String []MILEAGE_UNIT = new String[]{"KM","M"};
	

	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= 25+0;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+1;
	public static final int U_AIR_CYCLE						= U_AIR_BEGIN+2;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+3;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_AC						= U_AIR_BEGIN+5;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+9;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+10;
	public static final int U_AIR_DUAL						= U_AIR_BEGIN+11;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+12;
	public static final int U_AIR_POWER						= U_AIR_BEGIN+13;
	public static final int U_AIR_SYNC						= U_AIR_BEGIN+14;	
	public static final int U_AIR_END						= U_AIR_BEGIN+15;
	/**
	 * 车门
	 */
//	public static final int U_DOOR_BEGIN					= U_MISC_END;
	public static final int U_DOOR_BEGIN					= U_AIR_END;
	public static final int U_DOOR_ENGINE					= U_DOOR_BEGIN+0;
	public static final int U_DOOR_FL						= U_DOOR_BEGIN+1;
	public static final int U_DOOR_FR						= U_DOOR_BEGIN+2;
	public static final int U_DOOR_RL						= U_DOOR_BEGIN+3;
	public static final int U_DOOR_RR						= U_DOOR_BEGIN+4;
	public static final int U_DOOR_BACK						= U_DOOR_BEGIN+5;
	public static final int U_DOOR_END						= U_DOOR_BEGIN+6;
	public static final int U_TURN_RIGHT_ENTER_CAMERA		= U_DOOR_END+1;
	public static final int U_RIGHT_CAREAR_STATE			= U_DOOR_END+2;
	public static final int U_SETTING_67D03					= U_DOOR_END+3;
	public static final int U_SETTING_67D00					= U_DOOR_END+4;
	public static final int U_SETTING_67D14					= U_DOOR_END+5;
	public static final int U_SETTING_67D12					= U_DOOR_END+6;
	public static final int U_SETTING_67D10					= U_DOOR_END+7;
	public static final int U_SETTING_66D13					= U_DOOR_END+8;
	public static final int U_SETTING_66D12					= U_DOOR_END+9;
	public static final int U_SETTING_66D11					= U_DOOR_END+10;
	public static final int U_SETTING_66D10					= U_DOOR_END+11;
	public static final int U_SETTING_65D13					= U_DOOR_END+12;
	public static final int U_SETTING_65D11					= U_DOOR_END+13;
	public static final int U_SETTING_65D10					= U_DOOR_END+14;
	public static final int U_SETTING_68D14					= U_DOOR_END+15;
	public static final int U_SETTING_68D13					= U_DOOR_END+16;
	public static final int U_SETTING_68D12					= U_DOOR_END+17;
	public static final int U_SETTING_68D10					= U_DOOR_END+18;
	public static final int U_SETTING_69D05					= U_DOOR_END+19;
	public static final int U_SETTING_69D04					= U_DOOR_END+20;
	public static final int U_SETTING_69D03					= U_DOOR_END+21;
	public static final int U_SETTING_69D02					= U_DOOR_END+22;
	public static final int U_SETTING_69D00					= U_DOOR_END+23;
	public static final int U_SETTING_69D15					= U_DOOR_END+24;
	public static final int U_SETTING_69D13					= U_DOOR_END+25;
	public static final int U_SETTING_69D10					= U_DOOR_END+26;
	public static final int U_AIR_MODE						= U_DOOR_END+27;
	public static final int U_SHOW_AIR_KEY					= U_DOOR_END+28;
	public static final int U_CUR_SPEED						= U_DOOR_END+29;
	public static final int U_ENGINE_SPEED					= U_DOOR_END+30;
	
	//冠道新加
	public static final int U_AIR_ADD					    = U_DOOR_END+31;
	public static final int U_AIR_SEAT_BLOW_HEAT_LEFT		= U_AIR_ADD+1;
	public static final int U_AIR_SEAT_BLOW_HEAT_RIGHT		= U_AIR_ADD+2;
	public static final int U_AIR_BLOW_BODY_RIGHT			= U_AIR_ADD+3;
	public static final int U_AIR_BLOW_FOOT_RIGHT			= U_AIR_ADD+4;
	public static final int U_AIR_BLOW_UP_RIGHT				= U_AIR_ADD+5;
	public static final int U_AIR_MODE_RIGHT				= U_AIR_ADD+6;
	public static final int U_AIR_WIND_LEVEL_RIGHT			= U_AIR_ADD+7;
	public static final int U_CARINFO_RIGHT_TURN_LIGHT_TIME = U_AIR_ADD+8;
	public static final int U_SETTING_65D16					= U_AIR_ADD+9;
	public static final int U_SETTING_65D14					= U_AIR_ADD+10;
	public static final int U_SETTING_69D06					= U_AIR_ADD+11;
	public static final int U_SETTING_75D10					= U_AIR_ADD+12;
	public static final int U_SETTING_75D11					= U_AIR_ADD+13;
	public static final int U_SETTING_A6D61					= U_AIR_ADD+14;
	public static final int U_SETTING_A6D60					= U_AIR_ADD+15;
	
	//17CRV新增
	public static final int U_LANE_WATCH_LIGHT				= U_AIR_ADD+16;//盲点显示与转向灯联动
	public static final int U_LANE_WATCH_DURTION			= U_AIR_ADD+17;//盲点显示与转向灯联动
	public static final int U_REAR_VIEW_REMINDER			= U_AIR_ADD+18;//后视动态提醒系统设置 
	public static final int U_RISE_WARNING					= U_AIR_ADD+19;//抬头警告
	public static final int U_RISE_DRIVER_ATTENTION_MONITOR	= U_AIR_ADD+20;//抬头警告
	public static final int U_MEMORY_POSITION_SEAT			= U_AIR_ADD+21;//记忆位置座椅联动
	public static final int U_SEAT_BELT_MODE_SET			= U_AIR_ADD+22;//电子预紧式安全带运动模式设定
	public static final int U_SWITCH_LOCK					= U_AIR_ADD+23;//SWITCH LOCK
	
	//18雅阁新增
	public static final int U_SETTING_68D031				= U_AIR_ADD+24;//车道偏移抑制系统
	public static final int U_SETTING_69D17					= U_AIR_ADD+25;//交通标志识别系统
	
	private static final int U_SET_AMP_CARVOL 		= U_AIR_ADD+31;
	private static final int U_SET_AMP_FAD 			= U_SET_AMP_CARVOL + 1;
	private static final int U_SET_AMP_BAL 			= U_SET_AMP_CARVOL + 2;
	private static final int U_SET_AMP_BASS 		= U_SET_AMP_CARVOL + 3;
	private static final int U_SET_AMP_MID 			= U_SET_AMP_CARVOL + 4;
	private static final int U_SET_AMP_TREBLE 		= U_SET_AMP_CARVOL + 5;
	private static final int U_SET_AMP_MEGABASS  	= U_SET_AMP_CARVOL + 6;
	
	private static final int U_SET_CAR_TYPE		  	= U_SET_AMP_MEGABASS + 1;
	private static final int U_SET_FULLVIEW_SATE_DYNAMIC  	= U_SET_AMP_MEGABASS + 2;
	private static final int U_SET_FULLVIEW_SATE_STATIC  	= U_SET_AMP_MEGABASS + 3;
	private static final int U_SET_FULLVIEW_SATE_BACK_VIDEO  	= U_SET_AMP_MEGABASS + 4;
	private static final int U_SET_FULLVIEW_SATE_BACK_WIDTH  	= U_SET_AMP_MEGABASS + 5;
	
	public static final int U_CNT_MAX				= U_SET_AMP_MEGABASS+ 7;
	
	public static final int CYCLE_OUTER			= 0;
	public static final int CYCLE_INNER_MANUAL	= 1;
	public static final int CYCLE_AUTO			= 2;
	
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;

	int Vol_dis_cnt = 0;
	 int TemCanKey,CanKey = 0xff;
	int KeyCanKeyTable[][]=
	 {
	 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
	 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
	 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
	 			{4,			FinalKeyCode.KEY_CODE_VA},
	 			{5,			FinalKeyCode.KEY_CODE_PHONE},
	 			{6, 		FinalKeyCode.KEY_CODE_BACK}, 
	 			{7, 		FinalKeyCode.KEY_CODE_NULL}, 
	 			{8,			FinalKeyCode.KEY_CODE_LEFT},
	 			{9,			FinalKeyCode.KEY_CODE_RIGHT},
	 			{0x0a,		FinalKeyCode.KEY_CODE_MUTE},
	 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},
	 			{0x0C,		FinalKeyCode.KEY_CODE_NAVI},	//KEY_CODE_DISPLAY
	 			{0x0d,		FinalKeyCode.KEY_CODE_UP},	
	 			{0x0e,		FinalKeyCode.KEY_CODE_DOWN},	
	 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},	
	 			{0x22,		FinalKeyCode.KEY_CODE_HOME},	
	 			{0x23,		FinalKeyCode.KEY_CODE_FM},	
	 			{0x24,		FinalKeyCode.KEY_CODE_AM},	
	 			{0x25,		FinalKeyCode.KEY_CODE_PLAYER},	
	 			{0x26,		FinalKeyCode.KEY_CODE_EQ},	
	 			{0x27,		FinalKeyCode.KEY_CODE_EQ},	
	 			
	 			{0x45|0x80,		FinalKeyCode.KEY_CODE_VOL_UP},	
	 			{0x46|0x80,		FinalKeyCode.KEY_CODE_VOL_DOWN},	
	 			{0x57|0x80,		FinalKeyCode.KEY_CODE_SEEK_UP},	
	 			{0x58|0x80,		FinalKeyCode.KEY_CODE_SEEK_DOWN},	
	 			{0x5B|0x80,		FinalKeyCode.KEY_CODE_DOWN},	
	 			{0x5C|0x80,		FinalKeyCode.KEY_CODE_UP},	
	 			{0x5D|0x80,		FinalKeyCode.KEY_CODE_PREV},	
	 			{0x5E|0x80,		FinalKeyCode.KEY_CODE_NEXT},	
	 };
	 
	private final int CAMERA_MODE = 1;
	private final int RIGHT_ENTER_CAMERA = 2;
	private final int RIGHT_TURN_TIME	 =3;
	private static final int S_CAR_TYPE = 4;
	 
	
	private static int mRightEnterCamera = 1;
	private static int mCameraMode = 0;
	private static int mRightTurnTime = 1;
	public static final int C_TURN_RIGHT_ENTER_CAMERA 				= 0;
	public static final int C_BATTERY_DOOR_SWITCH					= 1;
	public static final int C_SETTINGS_AD							= 2; //功放设置
	public static final int C_CAMEAR								= 99;
	public static final int C_GET_ALL_INFO							= 100;
	public static final int C_DEL_ALL_INFO							= 101;
	public static final int C_LIGHT_SETTING							= 102;
	public static final int C_REMOTE_SETTING						= 103;
	public static final int C_DOOR_SETTING							= 104;
	public static final int C_ASSIT_SETTING							= 105;
	public static final int C_PANNEL_SETTING						= 106;
	public static final int C_AIR_CONTROL							= 107;
	public static final int C_TIRE_CLEAN							= 108;
	public static final int C_RIGHT_TURN_LIGHT_TIME					= 109;
	public static final int C_CAR_INFO_SET							= 110;
	public static final int C_AIR_CONTROL_CMD						= 255;
	public static final int C_SET_CAR_TYPE							= 3;
	public static final int C_SET_TIRE								= 4; // 胎压


		
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start+1]) {
		case 0x11:{
			int data0 = data[start+4];
			int data1 = data[start+5];
			if(data0 == 0x0C){
				if(data1 == 1){
//					if(JumpPage.topApp().equals("com.syu.canbus")){
//						JumpPage.bringFrontToBack();
//					}else{
//						JumpPage.canbus();
//					}
				}
			}
//			if((data[start+2]&0x04)!=0){
//				HandlerTaskCanbus.ArmBackCar(1);
//			}else{
//				HandlerTaskCanbus.ArmBackCar(0);
//			} 
			CarBackTrackHandle(data[start+8]&0xFF,data[start+9]&0xFF);
			switch (data[start + 4]) {
			case 0x6: // back hang
			{
				LogsUtils.i("DataHost.sPhoneSate " + DataHost.sPhoneSate);
				switch (DataHost.sPhoneSate) {
				case FinalBt.PHONE_STATE_DIAL:
				case FinalBt.PHONE_STATE_RING:
				case FinalBt.PHONE_STATE_TALK:
					CanInfos.canbusKeyHang();
					return;
				}
				break;
			}
			}
			//
			onKeyEvent(KeyCanKeyTable, data[start+4]&0xFF, data[start+5]&0xFF);
//			CanInfos.onKeyEvent(); 
			break;
		}
		case 0x21:{
			onKeyEvent(KeyCanKeyTable, (data[start+2]&0xFF)|0x80 , data[start+3]&0xFF);  
			break;
		}
		case 0x12:{
			// 门状态

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
		case 0x16:{
			// 瞬时油耗
			int value = data[start+2] & 0xff;
			HandlerTaskCanbus.update(U_CURRENT_OIL_CONSUMPTION, value);
			// 当前平均油耗
			value = (data[start+3] & 0xff) << 8 | data[start+4] & 0xff;
			HandlerTaskCanbus.update(U_CURRENT_AVERAGE_OIL_CONSUMPTION, value);
			// 历史平均油耗
			value = (data[start+5] & 0xff) << 8 | data[start+6] & 0xff;
			HandlerTaskCanbus.update(U_HISTORY_AVERAGE_OIL_CONSUMPTION, value);
			// 平均油耗
			value = (data[start+7] & 0xff) << 8 | data[start+8] & 0xff;
			HandlerTaskCanbus.update(U_AVERAGE_OIL_CONSUMPTION, value);
			// tripa
			value = (data[start+9] & 0xff) << 16 | (data[start+10] & 0xff) << 8 | data[start+11] & 0xff;
			HandlerTaskCanbus.update(U_TRIPA, value);
			// 续航里程 
			value = (data[start+12] & 0xff) << 8 | data[start+13] & 0xff;
			HandlerTaskCanbus.update(U_LIFE_MILEAGE, value);
			
			int b13 = data[start+14];
			value = (b13 & 0x03) & 0xff;
			HandlerTaskCanbus.update(U_CURRENT_OIL_CONSUMPTION_UNIT, value);
			value = ((b13 >> 2) & 0x03) & 0xff;
			HandlerTaskCanbus.update(U_HISTORY_AVERAGE_OIL_CONSUMPTION_UNIT, value);
//			HandlerTaskCanbus.update(U_LIGHT_FOOTWELL, value);
			
			value = ((b13 >> 4) & 0x03) & 0xff;
			HandlerTaskCanbus.update(U_AVERAGE_OIL_CONSUMPTION_UNIT, value);
			value = ((b13 >> 6) & 0x01) & 0xff;
//			HandlerTaskCanbus.update(U_UNIT_CONSUMPTION, value);
			HandlerTaskCanbus.update(U_TRIPA_UNIT, value);
			value = ((b13 >> 7) & 0x01) & 0xff;
			HandlerTaskCanbus.update(U_LIFE_MILEAGE_UNIT, value);
			
			value = data[start+15] & 0xff;
			if(value < 0){
				value = 0;
			}else if(value > 10){
				value = 10;
			}
			HandlerTaskCanbus.update(U_OIL_CONSUMPTION_COUNT, MAX[value]);
			break;
		}
		case 0x17:{
			int value = (data[start+2] & 0xff) << 16 | (data[start+3] & 0xff) << 8 | (data[start+4] & 0xff);
			HandlerTaskCanbus.update(U_TRIPA_1, value);
			value = (data[start+5] & 0xff) << 8 | (data[start+6] & 0xff);
			HandlerTaskCanbus.update(U_AVERAGE_OIL_CONSUMPTION_1, value);
			value = (data[start+7] & 0xff) << 16 | (data[start+8] & 0xff) << 8 | (data[start+9] & 0xff);
			HandlerTaskCanbus.update(U_TRIPA_2, value);
			value = (data[start+10] & 0xff) << 8 | (data[start+11] & 0xff);
			HandlerTaskCanbus.update(U_AVERAGE_OIL_CONSUMPTION_2, value);
			value = (data[start+12] & 0xff) << 16 | (data[start+13] & 0xff) << 8 | (data[start+14] & 0xff);
			HandlerTaskCanbus.update(U_TRIPA_3, value);
			value = (data[start+15] & 0xff) << 8 | (data[start+16] & 0xff);
			HandlerTaskCanbus.update(U_AVERAGE_OIL_CONSUMPTION_3, value);
			break;
		}
		case 0x31:{
			// 空调信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			HandlerTaskCanbus.update(U_AIR_POWER, 	B0>>6 & 0x01);
			
//			if(!ToolkitPlatform.isScreenPort()){
			HandlerTaskCanbus.update(U_AIR_AUTO, 	B0>>3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_SYNC, 	B0>>2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_CYCLE, 		((B1 >> 4) & 0x01) == 0 ? CYCLE_OUTER:CYCLE_INNER_MANUAL);
//			}
			HandlerTaskCanbus.update(U_AIR_AC, 		B0>>0 & 0x01);
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, 		B2>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, 		B2>>4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_SEAT_BLOW_HEAT_RIGHT, B2 >> 2 & 0x03); //冠道
			HandlerTaskCanbus.update(U_AIR_SEAT_BLOW_HEAT_LEFT, B2 & 0x03);	//冠道	

//			ModuleCallbackList.update(DataCanbus.MCLS, U_SHOW_AIR_KEY, B3 & 0x01);
			HandlerTaskCanbus.update(U_SHOW_AIR_KEY, 		B3  & 0x01);
			
			
			int b4 = (data[start+6] & 0x0f);
			int up = 0,body = 0,foot = 0;
			switch(b4){
			case 0x03:	foot = 1;	break;
			case 0x04:	up = 1;foot = 1;	break;
			case 0x05:	body = 1;foot = 1;	break;
			case 0x06:	body = 1;	break;
			case 0x07:	up = 1;body = 1;	break;
			case 0x0A:	up = 1;body = 1;foot = 1;	break;
			}
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_LEFT, 		up);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, 		body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, 		foot);
			HandlerTaskCanbus.update(U_AIR_MODE, 				b4);
			int right = (data[start+6] >> 4 & 0x0f);
			up = 0;body = 0;foot = 0;
			switch(right){
			case 0x03:	foot = 1;	break;
			case 0x04:	up = 1;foot = 1;	break;
			case 0x05:	body = 1;foot = 1;	break;
			case 0x06:	body = 1;	break;
			case 0x07:	up = 1;body = 1;	break;
			case 0x0A:	up = 1;body = 1;foot = 1;	break;
			}
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_RIGHT, 		up);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_RIGHT, 		body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_RIGHT, 		foot);
			HandlerTaskCanbus.update(U_AIR_MODE_RIGHT, 				right);
//			Log.d("air", "server right:" + right +" up:" +up + " body:" + body + " foot:"+foot);
			
			int windLevel = data[start+7] & 0xff;
			if (windLevel > 7) {
				windLevel = 7;
			}
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, 		windLevel);
			
			switch (data[start+8]) {
				case 0xFE: {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 		TEMPERATURE_LOW);
					break;
				}
				case 0xFF: {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 		TEMPERATURE_HIGH);
					break;
				}
				default: {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, 		(data[start+8] & 0xff) * 5);
					break;
				}
			}
			
			switch (data[start+9]) {
				case 0xFE: {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 		TEMPERATURE_LOW);
					break;
				}
				case 0xFF: {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 		TEMPERATURE_HIGH);
					break;
				}
				default: {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 		(data[start+9] & 0xff) * 5);
					break;
				}
			}
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_RIGHT, data[start + 10] & 0x0f);
			break;
		}
		case 0x41:{
			CanInfos.radarRl(TypeWC2_Data.CarGetRadarDistancef(data[start+2]&0xff));
			CanInfos.radarRml(TypeWC2_Data.CarGetRadarDistancef(data[start+3]&0xff)); 
			CanInfos.radarRmr(TypeWC2_Data.CarGetRadarDistancef(data[start+4]&0xff));
			CanInfos.radarRr(TypeWC2_Data.CarGetRadarDistancef(data[start+5]&0xff));
			CanInfos.radarFl(TypeWC2_Data.CarGetRadarDistancef(data[start+6]&0xff));  
			CanInfos.radarFml(TypeWC2_Data.CarGetRadarDistancef(data[start+7]&0xff));
			CanInfos.radarFmr(TypeWC2_Data.CarGetRadarDistancef(data[start+8]&0xff));
			CanInfos.radarFr(TypeWC2_Data.CarGetRadarDistancef(data[start+9]&0xff)); 
			break;
		}
		case 0xe0:{
			CarMediaSrcTypeHandle(data[start+2]&0xff);
			break;
		}
		case 0xE8: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];

			HandlerTaskCanbus.update(U_RIGHT_CAREAR_STATE, B0 & 0xff);
			HandlerTaskCanbus.update(FinalCanbus.U_HW_CAMERA_MODE, (B1 & 0xff));

			HandlerTaskCanbus.update(U_LANE_WATCH_LIGHT, B3 & 0x01);
			HandlerTaskCanbus.update(U_LANE_WATCH_DURTION, B3 >> 1 & 0x03);// 文档 2 模拟 1
			HandlerTaskCanbus.update(U_REAR_VIEW_REMINDER, B5 & 0xff);

			// B4 全景摄像头状态， 这里只支持17CRV 
			// 
			setShowFullView(B4&0x01);
		
			HandlerTaskCanbus.update(U_SET_FULLVIEW_SATE_STATIC, B6&0x01);
			HandlerTaskCanbus.update(U_SET_FULLVIEW_SATE_DYNAMIC, B6>>1&0x01);
			HandlerTaskCanbus.update(U_SET_FULLVIEW_SATE_BACK_VIDEO, B6>>2&0x01);
			HandlerTaskCanbus.update(U_SET_FULLVIEW_SATE_BACK_WIDTH, B6>>3&0x01);
			
			int rightState = B2 & 0x01;
			// if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING || DataBt.sPhoneState ==
			// FinalBt.PHONE_STATE_TALK)
			// return;
			if (mRightEnterCamera == 0)
				return;

			if (DataHost.sBackCar == 1) {

			} else {
				CanInfos.jumpRightCarmera(rightState == 1);
			}
			// if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_Honda_AllCom_LINGPAI_LO)
			// return;
			// // 倒车状态下，直接切视频源；非倒车状态下，直接拉起倒车应用出视频
			// if(DataMain.sHostBackcar != 0){
			// // 倒车状态
			//// if(rightState != 0){
			//// HandlerMain.video3188Cmd(FinalMain.VIDEO_ID_RIGHTCAMERA,
			// FinalMain.VIDEO_ID_RIGHTCAMERA);
			//// }else{
			//// HandlerMain.video3188Cmd(FinalMain.VIDEO_ID_BACKCAR,
			// FinalMain.VIDEO_ID_BACKCAR);
			//// }
			// }else{
			// // 非倒车
			// if(rightState != 0){
			// // 右视开
			// if(!JumpPage.isAppTopByPrefix(DataApp.sAppRightCameraPrefix))
			// JumpPage.rightCamera();
			//
			//// setRightCamera(1);
			// HandlerTaskCanbus.update(FinalCanbus.U_RIGHT_CAMERA_ON_OFF, 1);
			// }else{
			// if(JumpPage.isAppTopByPrefix(DataApp.sAppRightCameraPrefix)){
			// JumpPage.bringFrontToBack();
			// HandlerMain.videoIdCmd(FinalMain.VIDEO_ID_RIGHTCAMERA,
			// FinalMain.VIDEO_ID_NULL);
			//// setRightCamera(0);
			// HandlerTaskCanbus.update(FinalCanbus.U_RIGHT_CAMERA_ON_OFF, 0);
			// }
			// }
			// }
			break;
		}
		case 0x67: {//灯光设定
			HandlerTaskCanbus.update(U_SETTING_67D03, 	data[start + 2]>>3 & 0x01); // 雨刷和自动大灯联动个性化设定
			HandlerTaskCanbus.update(U_SETTING_67D00, 	data[start + 2] & 0x07); 	// 自动车内照明灵敏度
			HandlerTaskCanbus.update(U_SETTING_67D14, 	data[start + 3]>>4 & 0x07);	// 自动点灯灵敏度
			HandlerTaskCanbus.update(U_SETTING_67D12, 	data[start + 3]>>2 & 0x03);	// 前大灯自动熄灭时间
			HandlerTaskCanbus.update(U_SETTING_67D10, 	data[start + 3] & 0x03);	// 车内灯光减光时间
			break;
		}
		case 0x66: {//遥控设定状态
			HandlerTaskCanbus.update(U_SETTING_66D13, 	data[start + 3]>>3 & 0x01);	// 遥控门锁蜂鸣器提示
			HandlerTaskCanbus.update(U_SETTING_66D12, 	data[start + 3]>>2 & 0x01);	// 遥控门锁车边灯提示
			HandlerTaskCanbus.update(U_SETTING_66D11, 	data[start + 3]>>1 & 0x01); // 语音报警系统的音量
			HandlerTaskCanbus.update(U_SETTING_66D10, 	data[start + 3]>>0 & 0x01);	// 遥控启动系统
			break;
		}
		case 0x65: {//门锁设定状态
			//冠道
			HandlerTaskCanbus.update(U_SETTING_65D16, 	data[start + 3]>>6 & 0x03);// 车门自动开锁
			HandlerTaskCanbus.update(U_SETTING_65D14, 	data[start + 3]>>4 & 0x03);// 车门自动落锁
			
			HandlerTaskCanbus.update(U_SETTING_65D13, 	data[start + 3]>>3 & 0x01);	// 离开锁止个性化设定
			HandlerTaskCanbus.update(U_SETTING_65D11, 	data[start + 3]>>1 & 0x03); // 自动重锁时间
			HandlerTaskCanbus.update(U_SETTING_65D10, 	data[start + 3]>>0 & 0x01);	// 遥控落锁提示
			break;
		}
		case 0x68: {//驾驶辅助系统设定状态
			HandlerTaskCanbus.update(U_SETTING_68D14, 	data[start + 3]>>4 & 0x03); // 车道偏离辅助系统设定
			HandlerTaskCanbus.update(U_SETTING_68D13, 	data[start + 3]>>3 & 0x01); // 暂停LKAS提示音
			HandlerTaskCanbus.update(U_SETTING_68D12, 	data[start + 3]>>2 & 0x01); // ACC前车探知提示音
			HandlerTaskCanbus.update(U_SETTING_68D10, 	data[start + 3]>>0 & 0x03);	// 设定前方危险警告距离
			
			HandlerTaskCanbus.update(U_RISE_WARNING, 	data[start + 2]>>0 & 0x01);	// 抬头警告
			HandlerTaskCanbus.update(U_SETTING_68D031, 	data[start + 2]>>1 & 0x07);	// 车道偏移抑制系统
			HandlerTaskCanbus.update(U_RISE_DRIVER_ATTENTION_MONITOR, 	data[start + 3]>>6 & 0x03);	// 驾驶员注意力监视器
			break;
		}
		case 0x64:{
			HandlerTaskCanbus.update(U_MEMORY_POSITION_SEAT, 	data[start + 4]>>1 & 0x01);	// 记忆位置座椅联动
			HandlerTaskCanbus.update(U_SEAT_BELT_MODE_SET, 	data[start + 4]>>0 & 0x01);	// 电子预紧式安全带运动模式设定
			break;
		}
		case 0x69: {//显示屏设定状态
			HandlerTaskCanbus.update(U_SETTING_69D06,   data[start + 2] >> 6 & 0x01);	// 倒挡提示音
			HandlerTaskCanbus.update(U_SETTING_69D05, 	data[start + 2]>>5 & 0x01);	// 转速及提示
			HandlerTaskCanbus.update(U_SETTING_69D04, 	data[start + 2]>>4 & 0x01); // 新消息提示
			HandlerTaskCanbus.update(U_SETTING_69D03, 	data[start + 2]>>3 & 0x01); // 发动机节能自动启动提示
			HandlerTaskCanbus.update(U_SETTING_69D02, 	data[start + 2]>>2 & 0x01); // 节能模式的背光照明
			HandlerTaskCanbus.update(U_SETTING_69D00, 	data[start + 2]>>0 & 0x03); // 调整报警音量
			
			HandlerTaskCanbus.update(U_SETTING_69D17, 	data[start + 3]>>7 & 0x01); // 交通标志识别系统
			HandlerTaskCanbus.update(U_SETTING_69D15, 	data[start + 3]>>5 & 0x03); // 里程B重设条件的切换
			HandlerTaskCanbus.update(U_SETTING_69D13, 	data[start + 3]>>3 & 0x03); // 里程A重设条件的切换
			HandlerTaskCanbus.update(U_SETTING_69D10, 	data[start + 3]>>0 & 0x07); // 调节外部气温显示
			
			HandlerTaskCanbus.update(U_SWITCH_LOCK, 	data[start + 2]>>7 & 0x01);	// SWITCH LOCK
			break;
		}
		case 0x75:{
			HandlerTaskCanbus.update(U_SETTING_75D10, data[start + 3] & 0x01); 		// 使用外手柄电动打开
			HandlerTaskCanbus.update(U_SETTING_75D11, data[start + 3] >> 1 & 0x01);	// 遥控开启条件设定
			break;
		}
		case 0x32: {
			HandlerTaskCanbus.update(U_ENGINE_SPEED , 	(((data[start + 4]<<8)&0xFF00)|(data[start + 5]&0xFF)));
			HandlerTaskCanbus.update(FinalCanbus.U_ENGINE_SPEED ,(((data[start + 4]<<8)&0xFF00)|(data[start + 5]&0xFF)));
			HandlerTaskCanbus.update(U_CUR_SPEED, 	(((data[start + 6]<<8)&0xFF00)|(data[start + 7]&0xFF)));
			break;
		}
		case 0xA6:{
			HandlerTaskCanbus.update(U_SETTING_A6D61, data[start + 8] >> 1 & 0x03);
			HandlerTaskCanbus.update(U_SETTING_A6D60, data[start + 8] & 0x01);
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			int B4 = data[start+6];
			int B5 = data[start+7];
			int B6 = data[start+8];
//			//主音量
//			mCarVol = ToolkitMath.clamp((B0&0xFF), 0, 40);
//			//FADE-BAL处理
//			mCarFad = ToolkitMath.clamp((B2&0xff), 0, 0x12);
//			mCarBal = ToolkitMath.clamp((B1&0xff), 0, 0x12);
//			//EQ-BAND处理
//			mCarBass = ToolkitMath.clamp((B3&0xff), 0, 0x0C);
//			mCarSenor = ToolkitMath.clamp((B4&0xff), 0, 0x0C);
//			mCarTreble = ToolkitMath.clamp((B5&0xff), 0, 0x0C); 
			

			HandlerTaskCanbus.update(U_SET_AMP_CARVOL, B0);
			HandlerTaskCanbus.update(U_SET_AMP_FAD, B2);
			HandlerTaskCanbus.update(U_SET_AMP_BAL, B1);
			HandlerTaskCanbus.update(U_SET_AMP_BASS, B3);
			HandlerTaskCanbus.update(U_SET_AMP_MID, B4);
			HandlerTaskCanbus.update(U_SET_AMP_TREBLE, B5);
			HandlerTaskCanbus.update(U_SET_AMP_MEGABASS, B6 >> 4 & 0xF);
			
			break;
		}
	} 
	}

	

	private void setShowFullView(int i) {
		if(i != 0) {
			CanInfos.jumpFullView();
		}else {
			CanInfos.exitFullView();
		}
	} 
	private void onKeyEvent(int[][] table, int keycode, int action) {
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
	static void CarMediaSrcTypeHandle(int Mtype) {
		switch (Mtype) {
		case 0x20:// AM
		case 0x21:// FM1
			if (DataHost.sAppid != FinalMain.APP_ID_RADIO)
				CanInfos.jumpRadio();
			if (Mtype == 0x21)
				CanInfos.jumpRadioFM();
			else if (Mtype == 0x20) {
				CanInfos.jumpRadioAM();
			}
			break;
		case 0x22: // USB2
			CanInfos.jumpMediaPlayer();
			break;
		case 0x23:// BlueMusic
			CanInfos.jumpBtAv();
			break;
		case 0x24:// AUX
			CanInfos.jumpAux();
			break;

		default:
			break;
		}
	}
	 


	public static final int CAN_17CRV = 21;
	public static final int CAN_15SIBORUI = 22;
	@Override
	public void in() {
		LogsUtils.i(getClass().getSimpleName() + " in");
		spUtils.init("" + getClass().getSimpleName(), MyApp.getInstance());
		Ticks.addTicks1s(TIMESET);
		EventNotify.NE_LANG.addNotify(N_LANG);

		setCarType(spUtils.get(S_CAR_TYPE, 0));
		setCameraMode(spUtils.get(CAMERA_MODE, 0));
		setRightEnterCamera(spUtils.get(RIGHT_ENTER_CAMERA, 1));
		setRightTurnTime(spUtils.get(RIGHT_TURN_TIME, 0));

		EventNotify.NE_ID3_TITLE.addNotify(mId3Song, 1);

		Ticks.addTicks1s(mCarDisHostInfosE1);
		EventNotify.NE_VOL_SRC.addNotify(mCarDisVolumeE1, 0);
		EventNotify.NE_MUTE_SRC.addNotify(mCarDisVolumeE1, 0);
		EventNotify.NE_RADIO_BAND.addNotify(mCarDisHostInfosE1, 1);
		EventNotify.NE_RADIO_FREQS.addNotify(mCarDisHostInfosE1, 1);

		DataCanbus.sExistFullView = true;
		EventNotify.NE_BACKCAR.addNotify(notify_backcar, 1);

		switch (mCarOffset) {
		case CAN_17CRV:
			LogsUtils.i(" CAN_17CRV in");
			break;
		case CAN_15SIBORUI:
			LogsUtils.i(" CAN_15SIBORUI in");
			break;
		default:
			LogsUtils.i(" default in");
			break;
		}

	}	
	
	private boolean update = false;
	private void setCarType(int value) {
		if (mCarOffset != value) {
			LogsUtils.i(" set cartype: " + value);
			mCarOffset = value;
			spUtils.set(S_CAR_TYPE, mCarOffset);
			HandlerTaskCanbus.update(U_SET_CAR_TYPE, value);
			if (update) {
				update = false;
				updateCanbusIdOffSet(value);
			}
		}
	}
	
	@Override
	public void out() { 
        EventNotify.NE_LANG.removeNotify(N_LANG);
        Ticks.removeTicks1s(TIMESET);
        
		EventNotify.NE_ID3_TITLE.removeNotify(mId3Song);
		Ticks.removeTicks1s(mCarDisHostInfosE1);
		EventNotify.NE_VOL_SRC.removeNotify(mCarDisVolumeE1);
		EventNotify.NE_MUTE_SRC.removeNotify(mCarDisVolumeE1);
		EventNotify.NE_RADIO_BAND.removeNotify(mCarDisHostInfosE1);
		EventNotify.NE_RADIO_FREQS.removeNotify(mCarDisHostInfosE1);

		DataCanbus.sExistFullView = false;  
		EventNotify.NE_BACKCAR.removeNotify(notify_backcar);
	}

	private Runnable notify_backcar = new Runnable() {
		public void run() {
			LogsUtils.i("sBackCar " + DataHost.sBackCar);
			HandlerTaskCanbus.update(FinalCanbus.U_HW_CHANGE_PANORAMA, DataHost.sBackCar);
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
	
	private Runnable TIMESET = new Runnable() {
		long lastMin;
		long lastFormat;

		@Override
		public void run() {
			GregorianCalendar calendar = new GregorianCalendar();
			int min = calendar.get(Calendar.MINUTE);
			int format = DateFormat.is24HourFormat(MyApp.getInstance()) ? 0 : 1;
			int sec = calendar.get(Calendar.SECOND);
			boolean isSiboruiChange = false;

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (min != lastMin || lastFormat != format) {
				lastFormat = format;
				lastMin = min;
				SendFunc.send2Canbus(0xB5, hour, min, sec);
			}

			if (mCarOffset == CAN_15SIBORUI) {
				SendFunc.sendTime(year, month, day, hour, min, sec, format);
			}
		}
	};
	

	private Runnable mId3Song = new Runnable() {
		@Override
		public void run() {
			try {
				id3Cmd((byte)0xE4, DataHost.sId3Title);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(DataHost.sAppid == FinalMain.APP_ID_VIDEO_PLAYER){
//				id3Cmd((byte)0xE3, "");
//				id3Cmd((byte)0xE4, "");
			}
		}
	};
	
	
	private void id3Cmd(byte cmd, String str) throws UnsupportedEncodingException {
		if(str	== null)
				return;
		
		byte[] data = str.getBytes("Windows-936");
		if(mCarOffset == CAN_17CRV) {
			data = str.getBytes("UTF-8"); 
		}

		int [] datastr = new int[32];
		int len = data.length > 30 ? 30 : data.length;
		for (int i = 0; i < len; i++) {
			datastr[i] = data[i];
		}
		datastr[len]=(byte)'\0'; 
		SendFunc.send2Canbus(cmd, datastr);
	}
	
	private void setRightEnterCamera(int value){
		if(value < 0){
			value = 0;
		}else if(value > 1){
			value = 1;
		}
		mRightEnterCamera = value;
		HandlerTaskCanbus.update(U_TURN_RIGHT_ENTER_CAMERA, mRightEnterCamera);
		//雷刚添加，否则和下面的摄像头控制冲突
	//	if(DataCanbus.sCanbusId != FinalCanbus.CAR_WC2_Honda_AllCom_CIVIC)
//		SendFunc.send2Canbus(0xf2, new int[] {0x07, (byte) value});
		
		if(spUtils.get(RIGHT_ENTER_CAMERA, 1) != mRightEnterCamera){
			spUtils.set(RIGHT_ENTER_CAMERA, mRightEnterCamera);
		}	
	}
	
	private void setRightTurnTime(int value){
		if(value < 0){
			value = 0;
		}else if(value > 1){
			value = 1;
		}
		mRightTurnTime = value;
		HandlerTaskCanbus.update(U_CARINFO_RIGHT_TURN_LIGHT_TIME, mRightTurnTime);
		//雷刚添加，否则和下面的摄像头控制冲突 
		SendFunc.send2Canbus(0xf2, new int[] {0x08, (byte) value});
		if(spUtils.get(RIGHT_TURN_TIME, 0) != mRightTurnTime){
			spUtils.set(RIGHT_TURN_TIME, mRightTurnTime);
		}
	}
	
	private void setCameraMode(int value) {
		mCameraMode = value;
		SendFunc.send2Canbus(0xF2, new int[] { mCameraMode, 0xFF });
		if (spUtils.get(CAMERA_MODE, 0) != mCameraMode) {
			spUtils.set(CAMERA_MODE, mCameraMode);
		}
	}	

	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		// 空调控制命令
		case C_AIR_CONTROL_CMD:
			// Log.d("LG", "ints[0]="+ints[0]);
			if (ints != null && ints.length > 1) {
				// if(ints[0] == TOUCH_STATE_DOWN){
//				writeObd(new byte[] { 0x5A, (byte) 0xA5, 0x02, 0x3D, (byte) ints[0], (byte) ints[1], 0x00 });
				// }else if(ints[0] == TOUCH_STATE_NULL){
				// writeObd(new byte[]{0x5A,(byte) 0xA5,0x02,0x3D,(byte)
				// ints[0],(byte)ints[1],0x00});
				// }
				// 威驰 竖屏空调控制命令与协议盒相同 不走OBD
//				ToolkitDev.writeMcu(0xE3, (byte) 0x02, 0x3D, (byte) ints[0], (byte) ints[1], 0x00);
				SendFunc.send2Canbus(0x3D, ints);
			}
			break;
		case FinalCanbus.C_HW_SCREEN_TOUCH: {
			if (ints != null && ints.length == 2) {
				int touch_x = ints[0] * 1560 / MyApp.getWidth();
				int touch_y = ints[1] * 1900 / MyApp.getHeight();
				SendFunc.send2Canbus(0x2C,
						new int[] { 0x01, touch_x >> 8, touch_x & 0xff, touch_y >> 8, touch_y & 0xff, 0 });
			}
			break;
		}
//		case FinalCanbus.C_CHANGE_PANORAMA: {
//			ToolkitDev.writeMcu(0xE3, 0x02, 0xF2, (byte) 0x0F, 0x01);
//			break;
//		}
		case FinalCanbus.C_HW_CAMERA_MODE: {
			if (ints != null && ints.length > 0) {
				setCameraMode(ints[0]);
			}
			break;
		} 
		case C_CAR_INFO_SET: 
		case C_CAMEAR:
		case C_DEL_ALL_INFO: 
			if (ints != null && ints.length > 0) {
					SendFunc.send2Canbus(0xf2, ints);
			}
			break; 
		case C_RIGHT_TURN_LIGHT_TIME:
			if (ints != null && ints.length > 0) {
				setRightTurnTime(ints[0]); 
			}
			break;
		case C_SET_TIRE:
			if (ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0x4B, new int[] {0x04, ints[0]});
			}
			break;
		case C_TURN_RIGHT_ENTER_CAMERA:
			if (ints != null && ints.length > 0) {
				setRightEnterCamera(ints[0]);
			}
			break;
		case C_LIGHT_SETTING:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x6c, ints);
			}
			break;
		case C_REMOTE_SETTING:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x6B, ints);
			}
			break;
		case C_DOOR_SETTING:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x6D, ints);
			}
			break;
		case C_ASSIT_SETTING:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x6E, ints);
			}
			break;
		case C_PANNEL_SETTING:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x6F, ints);
			}
			break;
		case C_AIR_CONTROL:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x3D, ints);
			}
			break;
		case C_TIRE_CLEAN:
			if (ints != null && ints.length > 0) {
				// Print.log("LG", "ints[0] = "+ints[0]);
				SendFunc.send2Canbus(0x48, ints);
			}
			break;
		case C_BATTERY_DOOR_SWITCH:
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0x7a, ints);
			}
			break;
		case C_SETTINGS_AD: {
			if (ints != null && ints.length > 1) {
				SendFunc.send2Canbus(0xAD, ints);
			}
			break;
		}
		case C_SET_CAR_TYPE: {
			if(ints != null && ints.length > 0) {
				update = true;
				setCarType(ints[0]);
			}
			break;
		}
		}
	};
	
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if (code >= 0 && code < U_CNT_MAX) {
			if (DataCanbus.DATA[code] != 0)
				HandlerTaskCanbus.update(code);
		}
	} 
	
	
	public Runnable mCarDisVolumeE1 = new Runnable() {
		@Override
		public void run() {
			CarDisVolumeE1();
		}
	};
	public Runnable mCarDisHostInfosE1 = new Runnable() {
		@Override
		public void run() {
			CarDisInfosE1();
			CarDisMediaTime();
		}
	};

	@SuppressLint("DefaultLocale")
	protected void CarDisVolumeE1() {
		int[] cmds = new int[0x0D];
		Vol_dis_cnt = 4;
		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x20;// Source Id

		int valVol = DataHost.sVolDst;
		if (DataHost.sMuteSrc == 1 || DataHost.sVolDst == 0) {
			valVol = 0;
		}  
		
		String volStr = String.format("VOL %02d", valVol);
		LogsUtils.i("{"+ volStr+"}");
		byte[] bytes = volStr.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			cmds[1 + i] = bytes[i];
		}
		
		if(lastDatas == null || !Arrays.equals(lastDatas, cmds)) {
		SendFunc.send2Canbus(0xE1, cmds);
		lastDatas = cmds;
		}
	}

	protected void CarDisMediaTime() {
		if (DataHost.sAppid == FinalMain.APP_ID_AUDIO_PLAYER || DataHost.sAppid == FinalMain.APP_ID_VIDEO_PLAYER) {
			int[] cmds = new int[0x10];

			cmds[4] = DataHost.sCurPlayTime/60;
			cmds[5] = DataHost.sCurPlayTime%60;
			SendFunc.send2Canbus(0x98, cmds);
		}
	}
 
	private int sourceIdGet() {
		byte sourceid = 0x00;
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			sourceid = 0x08;
			break;
		case FinalMain.APP_ID_DVD:// 2
			sourceid = 0x0d;
			break;
		case FinalMain.APP_ID_IPOD:// 4//IPOD
			sourceid = 0x0b;
			break;
		case FinalMain.APP_ID_AUX:// 5//AUX
			sourceid = 0x0c;
			break;
		case FinalMain.APP_ID_RADIO:
			// band
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
			sourceid = (byte) 0xfe;
			break;

		case FinalMain.APP_ID_CAR_USB:// 20// 原车USB, 2013.10.19 Add
			sourceid = (byte) 0xff;
			break;
		default:
			break;
		}
		if (DataHost.sBackCar == 1) {// 不管任何情况下倒车，都要发送显示"CAREMA"否则协议盒会关闭视频
			sourceid = 0x10;// Carmera
		}

		return sourceid;
	}

	int[] lastDatas;

	protected void CarDisInfosE1() {
		if (Vol_dis_cnt > 0)
			Vol_dis_cnt--;
		if (Vol_dis_cnt == 0) {
			int[] cmds = new int[0xD];
			for (int a = 0; a < cmds.length; a++)
				cmds[a] = ' ';
			cmds[0] = sourceIdGet(); 
			switch (DataHost.sAppid) {
			case FinalMain.APP_ID_RADIO:{
				int freq = DataHost.sRadioFreq;
				if(DataHost.isAM) {
					// am pass
				}else { // fm
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append(String.format("%02d ", DataHost.sRadioChannel+1));
					sBuffer.append(String.format("%3d.%1d", freq/100, freq/10%10));
//					LogsUtils.i("radio:[" + sBuffer.toString()+"] -> " + freq);
					byte[] bytes = sBuffer.toString().getBytes();
					for (int i = 0; i < bytes.length; i++) {
						cmds[1 + i] = bytes[i];
					}
				}
				break; 
			}
			case FinalMain.APP_ID_VIDEO_PLAYER:
			case FinalMain.APP_ID_AUDIO_PLAYER:{// 15
				if (DataHost.sPlayTotal == 0)
					break;// 没曲目信息时，就不要显示曲目了   
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append(String.format("%03d %02d", DataHost.sCurPlayIndex, 0));
				LogsUtils.i("mp3:[" + sBuffer.toString()+"]");
				byte[] bytes = sBuffer.toString().getBytes();
				for (int i = 0; i < bytes.length; i++) {
					cmds[1 + i] = bytes[i];
				}
				break;
			}
			}
 

			if (lastDatas != null && Arrays.equals(lastDatas, cmds)) {
				return;
			}

			lastDatas = cmds;
			SendFunc.send2Canbus(0xe1, cmds);
		}
	}
}
