package com.ex.hiworld.aidl.car;

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
import com.ex.hiworld.server.tools.Utils;

import android.R.integer;
 

public class TaskCar_Toyota extends BaseCar {


	
	public static final int U_CUR_OIL_EXPEND				= 0;	// 当前油耗
	public static final int U_DRIVING_MILEAGE				= 1;	// 续航里程
	public static final int U_AVERAGE_SPEED					= 2;	// 平均车速
	public static final int U_DRIVING_TIME					= 3;	// 行车时间
	public static final int U_CLOSE_INSIDELAMP_TIME			= 4;	// 自动关闭车内灯光时间
	public static final int U_AUTOLOCK_BY_SPEED				= 5;	// 车速感应车门自动锁定
	public static final int U_AUTOLOCK_BY_SHIFT_FROM_P		= 6;	// 换档联动车门自动锁定
	public static final int U_AUTOLOCK_BY_SHIFT_TO_P		= 7;	// P档联动解锁
	public static final int U_AUTO_AC_ENABLED 				= 8; 	// 自动A/C模式
	public static final int U_VALID_VENTILATION_ENABLED 	= 9; 	// 有效通风模式
	public static final int U_REMOTE_2PRESS_UNLOCK			= 10;	// 操作按钮两次时解锁
	public static final int U_SENSITIVITY_OPEN_BIGLAMP 		= 11; 	// 大灯打开灵敏度
	public static final int U_2TIMES_KEY_UNLOCK				= 12;// new int[]{0/1}, 操作钥匙两次解锁
	public static final int U_UNLOCK_BY_DRIVERS_DOOR_OPEN	= 13;// new int[]{0/1}, 驾驶席开门联动解锁
	public static final int U_LOCK_UNLOCK_LAMP_FLASH		= 14; // new int[]{0/1} 上锁,解锁,紧急闪烁灯响应 
	public static final int U_UNLOCK_BY_SMART_DOOR			= 15;// new int[]{DOOR_DRIVER or DOOR_ALL} 智能车门解锁
	public static final int U_SMARTLOCK_AND_ONE_KEY_BOOT	= 16; // new int[]{0/1}	智能车锁和一键启动
	public static final int U_CUR_TRIP_OIL_EXPEND			= 17;
	public static final int U_TRIP_OIL_EXPEND				= 18;
	public static final int U_LAST_N_MINUTE_OIL_EXPEND		= 19;
	public static final int U_OPTIMAL_OIL_EXPEND			= 20;	// 最佳油耗
	public static final int U_SHOW_RADAR					= 21; // new int[]{0/1} 显示雷达
	public static final int U_RADAR_VOL						= 22; // new int[]{level} 雷达音量警报等级
	public static final int U_RADAR_DISTANCE				= 23; // new int[]{RADAR_DISTANCE_XXX, value} 距离(前后雷达显示方式)
	public static final int U_FRONT_RADAR_DISTANCE			= 24;
	public static final int U_REAR_RADAR_DISTANCE			= 25;
	public static final int U_SYSTEM_KEY_WITH_EKEY_ENABLED	= 26;	// 带电子钥匙的进入系统钥匙
	public static final int U_FEEDBACK_LAMP_ENABLED			= 27;
	public static final int	U_DSP_VOL_LINK_SPEED			= 28;
	public static final int U_DSP_SURROUND  				= 29;
	public static final int U_CAR_PE_ENABLE					= 30;
	public static final int U_CAR_PE_STATE					= 31;
	public static final int U_BATTERY_VOLTAGE				= 32;

	public static final int U_EXTERIOR_LIGHT_OFF_TIME		= 33;//车外照明关闭时间
	
	
	
	public static final int U_AIR_BEGIN						= 40;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+1;
	public static final int U_AIR_AC						= U_AIR_BEGIN+2;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+3;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+5;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+9;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+10;
	public static final int U_AIR_END						= U_AIR_BEGIN+11;
	
	/**
	 * 车门
	 */
	public static final int U_DOOR_BEGIN					= U_AIR_END+1;
	public static final int U_DOOR_ENGINE					= U_DOOR_BEGIN+0;
	public static final int U_DOOR_FL						= U_DOOR_BEGIN+1;
	public static final int U_DOOR_FR						= U_DOOR_BEGIN+2;
	public static final int U_DOOR_RL						= U_DOOR_BEGIN+3;
	public static final int U_DOOR_RR						= U_DOOR_BEGIN+4;
	public static final int U_DOOR_BACK						= U_DOOR_BEGIN+5;
	public static final int U_DOOR_END						= U_DOOR_BEGIN+6;
	
	public static final int U_AIR_PLD_BEGIN						= U_DOOR_END+1;
	public static final int U_AIR_POWER						= U_AIR_PLD_BEGIN+1;
	public static final int U_AIR_REAR_AUTO						= U_AIR_PLD_BEGIN+2;
	public static final int U_AIR_REAR_POWER				= U_AIR_PLD_BEGIN+3;
	public static final int U_AIR_REAR_LOCK				= U_AIR_PLD_BEGIN+4;
	public static final int U_AIR_CYCLE				= U_AIR_PLD_BEGIN+5;
	public static final int U_AIR_DUAL			= U_AIR_PLD_BEGIN+6;
	public static final int U_AIR_BLOW_BODY_REAR			= U_AIR_PLD_BEGIN+7;
	public static final int U_AIR_BLOW_FOOT_REAR			= U_AIR_PLD_BEGIN+8;
	public static final int U_AIR_WIND_REAR					= U_AIR_PLD_BEGIN+9;
	public static final int U_AIR_TEMP_REAR				= U_AIR_PLD_BEGIN+10;
	public static final int U_AIR_PLD_END					= U_AIR_PLD_BEGIN+11;
	
	
	public static final int U_TIRE_WARN						= U_AIR_PLD_END+1;
	public static final int U_PRESSURE_FL					= U_AIR_PLD_END+2;
	public static final int U_PRESSURE_FR					= U_AIR_PLD_END+3;
	public static final int U_PRESSURE_RL					= U_AIR_PLD_END+4;
	public static final int U_PRESSURE_RR					= U_AIR_PLD_END+5;

	private static final int U_AMP_VOLUME 					= U_PRESSURE_RR + 1;
	private static final int U_AMP_BALANCE_LeftRight 		= U_AMP_VOLUME + 1;
	private static final int U_AMP_BALANCE_FrontRear 		= U_AMP_VOLUME + 2;
	private static final int U_AMP_BALANCE_BASS 			= U_AMP_VOLUME + 3;
	private static final int U_AMP_BALANCE_MID 				= U_AMP_VOLUME + 4;
	private static final int U_AMP_BALANCE_TREMBLE 			= U_AMP_VOLUME + 5;
	private static final int U_ENGIN_SPEED 					= U_AMP_VOLUME + 6;
	private static final int U_CAR_SPEED 					= U_AMP_VOLUME + 7;
	
	public static final int U_CNT_MAX						= U_CAR_SPEED+1;

	
	public static final int  C_AUTOLOCK_BY_SPEED				= 0; 
	public static final int C_UNLOCK_BY_SMART_DOOR				= 1; 
	public static final int C_UNLOCK_BY_DRIVERS_DOOR_OPEN		= 2; 
	public static final int C_AUTOLOCK_BY_SHIFT_TO_P			= 3; 
	public static final int C_AUTOLOCK_BY_SHIFT_FROM_P			= 4; 
	public static final int C_LOCK_UNLOCK_LAMP_FLASH			= 5; 
	public static final int C_SMARTLOCK_AND_ONE_KEY_BOOT		= 6; 
	public static final int C_2TIMES_KEY_UNLOCK					= 7; 
	public static final int C_REMOTE_2PRESS_UNLOCK				= 8; 
	public static final int C_AUTO_AC_ENABLED					= 9; 
	public static final int C_VALID_VENTILATION_ENABLED			= 10; 
	public static final int C_SENSITIVITY_OPEN_BIGLAMP			= 11; 
	public static final int C_CLOSE_INSIDELAMP_TIME				= 12; 
	public static final int C_UPDATE_LAST_N_MINUTE_OIL_EXPEND	= 13; 
	public static final int C_CLEAR_LAST_N_MINUTE_OIL_EXPEND	= 14; 
	public static final int C_UPDATE_TRIP_OIL_EXPEND			= 15; 
	public static final int C_CLEAR_TRIP_OIL_EXPEND				= 16; 
	public static final int C_SYSTEM_KEY_WITH_EKEY_ENABLED 		= 17; // 带电子钥匙的进入系统钥匙 new int[]{0/1}
	public static final int C_FEEDBACK_LAMP_ENABLED				= 18;
	public static final int C_DSP_VOL_LINK_SPEED				= 19;
	public static final int C_DSP_SURROUND						= 20;
	public static final int C_EXTERIOR_LIGHT_OFF_TIME			= 21;
	public static final int C_SHOW_RADAR						= 24; // new int[]{0/1} 显示雷达
	public static final int C_RADAR_VOL							= 25; // new int[]{level} 雷达音量警报等级
	public static final int C_RADAR_DISTANCE					= 26; // new int[]{RADAR_DISTANCE_XXX} 距离(前后雷达显示方式)
	

	public static final int C_AMP_SET					= 27; // 功放设置命令 AD
	public static final int C_SET_2D					= 28; // 车型设置 横屏
	public static final int C_SET_6A					= 29; // 设定命令
	public static final int C_SET_24					= 30; // 竖屏车型设置
	public static final int C_SET_FA					= 31; // 摄像头显示切换命令
	
	public static final int DOOR_ALL						= 0;
	public static final int DOOR_DRIVER						= 1;
	
	
	private static final int TRIPOILEXPENDMAX = 5;
	private int [][]mTripoilexpend = new int[TRIPOILEXPENDMAX][];
	private static final int MINUTEOILEXPENDMAX = 30;
	private int [][]mMinuteoilexpend = new int[MINUTEOILEXPENDMAX][];
	
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;

	int Vol_dis_cnt = 0;
	 int TemCanKey,CanKey = 0xff;
	int KeyCanKeyTable[][]=
	 {
	 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
	 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
	 			{3, 		FinalKeyCode.KEY_CODE_NULL},
	 			{4,			FinalKeyCode.KEY_CODE_MUTE},
	 			{5,			FinalKeyCode.KEY_CODE_PHONE},
	 			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
	 			{7, 		FinalKeyCode.KEY_CODE_NULL}, 
	 			{8,			FinalKeyCode.KEY_CODE_FF},
	 			{9,			FinalKeyCode.KEY_CODE_FB},
	 			{0x0C,		FinalKeyCode.KEY_CODE_MODE},	//KEY_CODE_DISPLAY
	 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
	 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
	 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},	
	 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
	 };
	 
	private final int CAMERA_MODE = 1;
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start+1]) { // type 
		case 0x11: { 
			int B0 = data[start + 6];
			HandlerTaskCanbus.update(U_DOOR_ENGINE, B0 >> 2 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B0 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RL, B0 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B0 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_FL, B0 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_FR, B0 >> 7 & 0x01);

			CanInfos.onKeyEvent(KeyCanKeyTable, data[start + 4], data[start + 5]);
			CanInfos.CarBackTrackHandle(data[start + 8] & 0xFF, data[start + 9] & 0xFF);
			break;
		}
		case 0x13: { // 油耗/里程信息1
			int unitOilExpand = (data[start+12] & 0xff) << 24;
			int unitMileage = (data[start+13] & 0xff) << 24;
			HandlerTaskCanbus.update(U_CUR_OIL_EXPEND, 		((data[start+2] & 0xff) << 8) | (data[start+3] & 0xff) | unitOilExpand	);
			HandlerTaskCanbus.update(U_DRIVING_MILEAGE,		 (((data[start+4] & 0xff) << 8) | (data[start+5] & 0xff)) | unitMileage	);
			HandlerTaskCanbus.update(U_OPTIMAL_OIL_EXPEND,		((data[start+6] & 0xff) << 8) | (data[start+7] & 0xff) | unitOilExpand	);
			HandlerTaskCanbus.update(U_DRIVING_TIME,		((data[start+8] & 0xff) << 8) | (data[start+9] & 0xff)	);
			HandlerTaskCanbus.update(U_AVERAGE_SPEED,		((data[start+10] & 0xff) << 8) | (data[start+11] & 0xff)	);
			break;
		}
		case 0x16: { // 油耗/里程信息2 记录
			int unitOilExpand = (data[start+14] & 0xff) << 24;
			
			HandlerTaskCanbus.update(U_CUR_TRIP_OIL_EXPEND, 		((data[start+2] & 0xff) << 8) | (data[start+3] & 0xff) | unitOilExpand);
			for (int i = 0; i < TRIPOILEXPENDMAX; i++) {
				int []value = new int[]{i, ((data[start+2*i+4] & 0xff) << 8) | (data[start+2*i+5] & 0xff) | unitOilExpand};
				if (HandlerTaskCanbus.update(U_TRIP_OIL_EXPEND, value, mTripoilexpend[i])) {
					mTripoilexpend[i] = value;		
				}
			}
			break;
		}
		case 0x17: { // 油耗/里程信息3 记录
			int unitOilExpand = (data[start+62] & 0xff) << 24;
			for (int i = 0; i < MINUTEOILEXPENDMAX; i++){
				int []value = new int[]{i, ((data[start+2*i+2] & 0xff) << 8) | (data[start+2*i+3] & 0xff) | unitOilExpand};
				if (HandlerTaskCanbus.update(U_LAST_N_MINUTE_OIL_EXPEND, value, mMinuteoilexpend[i])) {
					mMinuteoilexpend[i] = value;		
				}
			}
			break;
		
		}
		case 0x32:{
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			
			HandlerTaskCanbus.update(U_ENGIN_SPEED, Utils.combine(B2, B3));
			HandlerTaskCanbus.update(U_CAR_SPEED, Utils.combine(B4, B5));
			break;
		}
		case 0x41: {
			CanInfos.radarRl(TypeWC2_Data.CarGetRadarDistancef2(data[start + 2] & 0xff));
			CanInfos.radarRml(TypeWC2_Data.CarGetRadarDistancef2(data[start + 3] & 0xff));
			CanInfos.radarRmr(TypeWC2_Data.CarGetRadarDistancef2(data[start + 3] & 0xff));
			CanInfos.radarRr(TypeWC2_Data.CarGetRadarDistancef2(data[start + 5] & 0xff));
			CanInfos.radarFl(TypeWC2_Data.CarGetRadarDistancef2(data[start + 6] & 0xff));
			CanInfos.radarFml(TypeWC2_Data.CarGetRadarDistancef2(data[start + 7] & 0xff));
			CanInfos.radarFmr(TypeWC2_Data.CarGetRadarDistancef2(data[start + 7] & 0xff));
			CanInfos.radarFr(TypeWC2_Data.CarGetRadarDistancef2(data[start + 9] & 0xff));
			
			SendFunc.setRadarOnOff(data[start + 12]&0x01);
			break;
		}

		case 0x61: {
			// 使能标志 全兼容 无使能标记
			
			break;
		}
		case 0x62: {
			// 设定信息 全兼容
			int b1 = data[start+2];
			// 雷达开关
			HandlerTaskCanbus.update(U_SHOW_RADAR, b1 & 0x80);
			// 警报音量
			HandlerTaskCanbus.update(U_RADAR_VOL, (b1 & 0x70) >> 4);
			// 前雷达距离
			HandlerTaskCanbus.update(U_FRONT_RADAR_DISTANCE, (b1 & 0x0C) >> 2);
			// 后雷达距离
			HandlerTaskCanbus.update(U_REAR_RADAR_DISTANCE, (b1 & 0x03));
			
			int b2 = data[start+3];
			// 自动落锁
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SPEED, (b2 & 0x40));
			// 智能自动解锁
			HandlerTaskCanbus.update(U_UNLOCK_BY_SMART_DOOR, ((b2 & 0x20) != 0 ? DOOR_DRIVER : DOOR_ALL));
			// 驾驶员开门联动解锁
			HandlerTaskCanbus.update(U_UNLOCK_BY_DRIVERS_DOOR_OPEN, (b2 & 0x10));
			// P档自动解锁
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SHIFT_TO_P, (b2 & 0x08));
			// P档自动落锁
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SHIFT_FROM_P, (b2 & 0x04));
			// 空调与Auto联动
			HandlerTaskCanbus.update(U_AUTO_AC_ENABLED, (b2 & 0x02));
			// 内外循环与auto联动
			HandlerTaskCanbus.update(U_VALID_VENTILATION_ENABLED, (b2 & 0x01));
			
			int b3 = data[start+4];
			// 上锁开锁时紧急闪烁灯相应
			HandlerTaskCanbus.update(U_LOCK_UNLOCK_LAMP_FLASH, (b3 & 0x80));
			// 按钮两次按下解锁
			HandlerTaskCanbus.update(U_REMOTE_2PRESS_UNLOCK, (b3 & 0x40));
			// 智能车锁和一键启动
			HandlerTaskCanbus.update(U_SMARTLOCK_AND_ONE_KEY_BOOT, (b3 & 0x20));
			// 钥匙两次按下解锁
			HandlerTaskCanbus.update(U_2TIMES_KEY_UNLOCK, (b3 & 0x10));
			
			int b4 = data[start+5];
			int sensitivity = (b4 & 0x07) & 0xff;
			if (sensitivity > 4) {
				sensitivity = 4;
			}
			// 自动头灯灵敏度
			HandlerTaskCanbus.update(U_SENSITIVITY_OPEN_BIGLAMP, (sensitivity));
			// 车内照明关闭时间
			HandlerTaskCanbus.update(U_CLOSE_INSIDELAMP_TIME, ((b4 & 0x18) >> 3));
			
			HandlerTaskCanbus.update(U_EXTERIOR_LIGHT_OFF_TIME, b4>>5 & 0x03);
//			
//			int b5 = data[start+6];
//			//汉兰达摄像头模式设置
//			HandlerTaskCanbus.update(FinalCanbus.U_CAMERA_MODE, (b5 &0x03)-1);
			break;
		}
		case (int)0x82:{
		//	Log.d("LG","HandlerTaskCanbus 0x82AAAA");
			HandlerTaskCanbus.update(U_AIR_AUTO, 		data[start+2] & 0x08);
			HandlerTaskCanbus.update(U_AIR_AC, 			data[start+3] & 0x40);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, data[start+3] & 0x10);
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, data[start+3] & 0x20);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, data[start+6] & 0x40);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, data[start+6] & 0x20);
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_LEFT, data[start+6] & 0x10);
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, data[start+6] & 0x0f);
			int temp = data[start+4] & 0xff;
		//	Log.d("LG","tempL="+temp);
			if (temp == 1) {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT,		TEMPERATURE_LOW); // 00:LOW FF:HIGH//TEMPERATURE_LOW
			}else if (temp == 0xff) {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT,		TEMPERATURE_HIGH); // 00:LOW FF:HIGH
			} else if ((temp == 0)||(temp < 0x74)||(temp > 0x90)) {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT,		TEMPERATURE_NONE); // 00:LOW FF:HIGH//TEMPERATURE_NONE
			} else {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT,		temp * 5 - 400); // 00:LOW FF:HIGH
			}
//			HandlerTaskCanbus.update(U_AIR_TEMP_LEFT,		data[start+5] & 0xff);	
			temp = data[start+5] & 0xff;
			//Log.d("LG","tempR="+temp);
			 if (temp == 1) {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT,		TEMPERATURE_LOW); // 00:LOW FF:HIGH TEMPERATURE_LOW
			} else if (temp == 0xff) {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT,		TEMPERATURE_HIGH); // 00:LOW FF:HIGH
			}else if ((temp == 0)||(temp < 0x74)||(temp > 0x90)){
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT,		TEMPERATURE_NONE); // 00:LOW FF:HIGH TEMPERATURE_NONE
			} else {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT,		temp * 5 - 400); // 00:LOW FF:HIGH
			}
			 
			temp = data[start+9] & 0xff;
			//Log.d("LG","tempR="+temp);
			 if (temp == 1) {
				HandlerTaskCanbus.update(U_AIR_TEMP_REAR,		TEMPERATURE_LOW); // 00:LOW FF:HIGH TEMPERATURE_LOW
			} else if (temp == 0xff) {
				HandlerTaskCanbus.update(U_AIR_TEMP_REAR,		TEMPERATURE_HIGH); // 00:LOW FF:HIGH
			}else if ((temp == 0)||(temp < 0x74)||(temp > 0x90)){
				HandlerTaskCanbus.update(U_AIR_TEMP_REAR,		TEMPERATURE_NONE); // 00:LOW FF:HIGH TEMPERATURE_NONE
			} else {
				HandlerTaskCanbus.update(U_AIR_TEMP_REAR,		temp * 5 - 400); // 00:LOW FF:HIGH
			}
			HandlerTaskCanbus.update(U_AIR_POWER		, 		data[start+2] & 0x40);	
			HandlerTaskCanbus.update(U_AIR_REAR_AUTO	, 		data[start+2] & 0x20);	
			HandlerTaskCanbus.update(U_AIR_REAR_POWER	, 		data[start+2] & 0x10);
			HandlerTaskCanbus.update(U_AIR_REAR_LOCK	,		data[start+3] & 0x80);
			HandlerTaskCanbus.update(U_AIR_CYCLE		,		data[start+3] & 0x08);	
			HandlerTaskCanbus.update(U_AIR_DUAL			,		data[start+3] & 0x04);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_REAR,		data[start+7]>>1 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_REAR,		data[start+7] & 0x01);
			HandlerTaskCanbus.update(U_AIR_WIND_REAR	,		data[start+8] & 0x0f);	
			break;
		}
		case (int)0xa6:{
			int B0 = data[start+2] & 0xFF;
			int B1 = data[start+3] & 0xFF;
			int B2 = data[start+4] & 0xFF;
			int B3 = data[start+5] & 0xFF;
			int B4 = data[start+6] & 0xFF;
			int B5 = data[start+7] & 0xFF;
//			//主音量
//			mCarVol = ToolkitMath.clamp((B0&0xFF), 0, 63);
//			//FADE-BAL处理
//			mCarFad = ToolkitMath.clamp((B2&0xff), 0, 14);
//			mCarBal = ToolkitMath.clamp((B1&0xff), 0, 14);
//			//EQ-BAND处理
//			mCarBass = ToolkitMath.clamp((B3&0xff), 0, 10);
//			mCarSenor = ToolkitMath.clamp((B4&0xff), 0, 10);
//			mCarTreble = ToolkitMath.clamp((B5&0xff), 0, 10);
//			
			

			HandlerTaskCanbus.update(U_AMP_VOLUME,		B0);	
			HandlerTaskCanbus.update(U_AMP_BALANCE_LeftRight,	B1);	
			HandlerTaskCanbus.update(U_AMP_BALANCE_FrontRear,	B2);	
			HandlerTaskCanbus.update(U_AMP_BALANCE_BASS,	B3);	// 低音
			HandlerTaskCanbus.update(U_AMP_BALANCE_MID,		B4);	// 中音
			HandlerTaskCanbus.update(U_AMP_BALANCE_TREMBLE,	B5);	// 高音
			 
			HandlerTaskCanbus.update(U_DSP_VOL_LINK_SPEED	,		(data[start+8]>>1) & 0x01);	
			HandlerTaskCanbus.update(U_DSP_SURROUND	,				data[start+8] & 0x01);	
		
			break;
		}
		case 0x1f:{
			HandlerTaskCanbus.update(U_CAR_PE_ENABLE,			data[start+2] >>7 & 0x01);
			HandlerTaskCanbus.update(U_BATTERY_VOLTAGE,			data[start+2] &0x0F);
			HandlerTaskCanbus.update(U_CAR_PE_STATE,			data[start+3] & 0x3F);
			break;
		}
		case (int)0xe8:{ 
//			if((data[start+5]&0xff)!=0){
//				HandlerTaskCanbus.ArmBackCar(1);
//			}else{
//				HandlerTaskCanbus.ArmBackCar(0);
//			}
		
//			if(DataCanbus.isAnalysisByServer()){
//				if(DataCanbus.sCanbusId !=FinalCanbus.CAR_WC2_17ChuanQiGS4)
//					break;
//				if((data[start + 5]&0xff) == 0x01){
//				if (DataMain.sHostBackcar == 0){
//					// 右视开
//					if(!JumpPage.isAppTopByPrefix(DataApp.sAppRightCameraPrefix)){
//						JumpPage.rightCamera();
//						setRightCameraOn(1);
//						HandlerTaskCanbus.update(FinalCanbus.U_RIGHT_CAMERA_ON_OFF, 	1);
//					}
//				}
//				}else {
//						if(JumpPage.isAppTopByPrefix(DataApp.sAppRightCameraPrefix)){
//							JumpPage.bringFrontToBack();
//							HandlerMain.videoIdCmd(FinalMain.VIDEO_ID_RIGHTCAMERA, FinalMain.VIDEO_ID_NULL);
//							setRightCameraOn(0);
//							HandlerTaskCanbus.update(FinalCanbus.U_RIGHT_CAMERA_ON_OFF, 	0);
//						}
//				}
//			}
			
			CanInfos.switchFullViews(data[start + 5 ] &0x01);
			break;
		}
		case 0x48:{
			HandlerTaskCanbus.update(U_TIRE_WARN,			data[start+2]>>6 &0x01);
			HandlerTaskCanbus.update(U_PRESSURE_FL,			data[start+4] &0xff);
			HandlerTaskCanbus.update(U_PRESSURE_FR,			data[start+5] &0xff);
			HandlerTaskCanbus.update(U_PRESSURE_RL,			data[start+6] &0xff);
			HandlerTaskCanbus.update(U_PRESSURE_RR,			data[start+7] &0xff);
			break;
		}
	}
	}

	@Override
	public void in() {
        EventNotify.NE_APPID.addNotify(mCarDisNormal, 1); 
        EventNotify.NE_RADIO_BAND.addNotify(mCarDisNormal, 1); 
        EventNotify.NE_RADIO_FREQS.addNotify(mCarDisNormal, 1); 
        EventNotify.NE_BT_STATE.addNotify(mBtPhoneStateAndNumber, 1); 
        EventNotify.NE_BT_PHONENUM.addNotify(mBtPhoneStateAndNumber, 1); 
        EventNotify.NE_VOL_SRC.addNotify(mCarDisVolume, 1); 
        EventNotify.NE_MUTE_SRC.addNotify(mCarDisVolume, 1);
        
        EventNotify.NE_ID3_ALBUM.addNotify(mId3ALBUM, 1);
        EventNotify.NE_ID3_TITLE.addNotify(mId3Song, 1);
        EventNotify.NE_ID3_ARTIST.addNotify(mId3Artist, 1);
	}

	@Override
	public void out() {
        EventNotify.NE_APPID.removeNotify(mCarDisNormal); 
        EventNotify.NE_RADIO_BAND.removeNotify(mCarDisNormal); 
        EventNotify.NE_RADIO_FREQS.removeNotify(mCarDisNormal); 
        EventNotify.NE_BT_STATE.removeNotify(mBtPhoneStateAndNumber); 
        EventNotify.NE_BT_PHONENUM.removeNotify(mBtPhoneStateAndNumber); 
        EventNotify.NE_VOL_SRC.removeNotify(mCarDisVolume); 
        EventNotify.NE_MUTE_SRC.removeNotify(mCarDisVolume); 
        EventNotify.NE_ID3_ALBUM.removeNotify(mId3ALBUM);
        EventNotify.NE_ID3_TITLE.removeNotify(mId3Song);
        EventNotify.NE_ID3_ARTIST.removeNotify(mId3Artist);
	} 

	private Runnable mCarDisNormal = new Runnable() {
		@Override
		public void run() {
			if (Vol_dis_cnt > 0)
				Vol_dis_cnt--;
			if (Vol_dis_cnt == 0)
				CarDisNormal();
		}
	};
	
	private Runnable mCarDisVolume = new Runnable() {
		@Override
		public void run() {
			Vol_dis_cnt = 2;
			int[] cmds = new int[16];
			cmds[0] = 0xE3;
			cmds[1] = 0x0d;// leng
			cmds[2] = 0x91;// fid
			cmds[3] = 0x20;
			if (DataHost.sMuteSrc == 1 || DataHost.sVolDst == 0) {
				cmds[4] = '0';
				cmds[5] = '0';
			} else {
				cmds[4] = DataHost.sVolDst / 10 + '0';
				cmds[5] = DataHost.sVolDst % 10 + '0';
			}
			
			int[] data = new int[0x0D];
			
			System.arraycopy(cmds, 3, data, 0, 0x0D);
			SendFunc.send2Canbus(0x91, data);
		}
	};
	
	static int CarDisSourceIdGet()
	{
		int sourceid;

		sourceid = 0x00;
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			sourceid = 0x08;
			break;

		case FinalMain.APP_ID_DVD:// 2
//			if (DataDvd.sDeviceType == FinalDvd.DEVICE_TYPE_DISC) {
//				sourceid = 0x06;
//			} else 
			{
				sourceid = 0x0d;
			}
			break;

		// case SYS_ID_CDC:// 3
		// sourceid=0x06;
		// break;

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

		// case SYS_ID_RADAR:// 7
		// sourceid=0x10;
		// break;

		case FinalMain.APP_ID_BTPHONE:// 8
			sourceid = 0x0a;
			break;

		case FinalMain.APP_ID_BTAV:// 11//蓝牙音乐
			sourceid = 0x85;
			break;

		// case SYS_ID_GPS:// 12
		// sourceid=0x09;
		// break;

		// case SYS_ID_AIR:// 13
		// //sourceid=0x08;
		// break;
		//
		// case FinalMain.APP_ID_NULL:// 14
		// sourceid=0x09;
		// break;
		case FinalMain.APP_ID_THIRD_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:// 15
			sourceid = 0x0d;
			break;

		// case SYS_ID_MP5:// 16
		// //sourceid=0x08;
		// break;

		case FinalMain.APP_ID_DVR:// 17
			// sourceid=0x08;
			break;

		case FinalMain.APP_ID_CAR_RADIO:// 18// 原车收音，2013.10.19 Add
			// sourceid=0x08;
			break;

		case FinalMain.APP_ID_CAR_BTPHONE:// 19// 原车蓝牙，2013.10.19 Add
			sourceid = 0xfe;
			break;

		case FinalMain.APP_ID_CAR_USB:// 20// 原车USB, 2013.10.19 Add
			sourceid = 0xff;
			break;

		default:
			break;
		}
		/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
		/* <2016.3.2.01 tlm> */
		// if(CanBackDetFlag || CanRadarDetCurFlag)
		if (DataHost.sBackCar == 1) {// 不管任何情况下倒车，都要发送显示"CAREMA"否则协议盒会关闭视频
			sourceid = 0x10;// Carmera
		}
		return sourceid;
	}
	
	static void CarDisNormal()
	{
		int[] cmds = new int[15];
		byte i;
		int charlong;
		int temp16;

		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x0d;// leng
		cmds[1] = 0x91;// fid
		cmds[2] = CarDisSourceIdGet();// Source Id
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_RADIO:
			// radio freq
			int freq = DataHost.sRadioFreq;
			// Log.d("btt", " 1111 sFreq = "+DataHost.sRadioFreq +"
			// DataHost.sRadioChannel="+DataHost.sRadioChannel);
			if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
				int channel = DataHost.sRadioChannel - FinalRadio.BAND_FM_INDEX_BEGIN + 1;
				cmds[3] = channel / 10 + '0';
				cmds[4] = channel % 10 + '0';
				cmds[10] = (DataHost.sRadioFreq % 100000) / 10000 + '0';
				cmds[11] = (DataHost.sRadioFreq % 10000) / 1000 + '0';
				cmds[12] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[13] = '.';
				cmds[14] = (DataHost.sRadioFreq % 100) / 10 + '0';
			} else {
				cmds[3] = ((DataHost.sRadioChannel / 10) + 1) + '0';
				cmds[4] = ((DataHost.sRadioChannel % 10) + 1) + '0';
				cmds[10] = DataHost.sRadioFreq / 10000 + '0';
				cmds[11] = (DataHost.sRadioFreq % 10000) / 1000 + '0';
				cmds[12] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[13] = (DataHost.sRadioFreq % 100) / 10 + '0';
				cmds[14] = (DataHost.sRadioFreq % 10) + '0';
			}
			break;
		default:
			break;
		}

		int[] data = new int[0x0D]; 

		System.arraycopy(cmds, 2, data, 0, 0x0D);
		SendFunc.send2Canbus(0x91, data);
	}
	
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

//		ToolkitDev.writeMcu(cmds);
		SendFunc.send2Canbus(0xCD, cmds); 
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
	 
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		 switch (cmd) {
//			case FinalCanbus.C_CMD_360_TOUCH: {
//				int touch_x = ints[1] * 1023 / ObjApp.getWidth();
//				int touch_y = ints[2] * 1000 / ObjApp.getHeight();
//				if (intsOk(ints, 2)) {
//					if (ints[0] == 0)
//						ToolkitDev.writeMcu(0xE3, 0x06, (byte) 0x2c, (byte) 0x01, touch_x >> 8, touch_x & 0xff, touch_y >> 8, touch_y & 0xff, 0);
//				}
//				Log.d("touch"," C_CMD_360_TOUCH ");
//				break;
//			}
			case C_AUTOLOCK_BY_SPEED:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x01,(byte)ints[0]);
				}
				break;
			}
			case C_UNLOCK_BY_SMART_DOOR:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x02,(byte)ints[0]);
				}
				break;
			}
			case C_UNLOCK_BY_DRIVERS_DOOR_OPEN:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x03,(byte)ints[0]);
				}
				break;
			}
			case C_AUTOLOCK_BY_SHIFT_TO_P:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x04,(byte)ints[0]);
				}
				break;
			}
			case C_AUTOLOCK_BY_SHIFT_FROM_P:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x05,(byte)ints[0]);
				}
				break;
			}
			case C_LOCK_UNLOCK_LAMP_FLASH:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x02,(byte)0x01,(byte)ints[0]);
				}
				break;
			}
			case C_SMARTLOCK_AND_ONE_KEY_BOOT:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x02,(byte)0x02,(byte)ints[0]);
				}
				break;
			}
			case C_2TIMES_KEY_UNLOCK:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x02,(byte)0x03,(byte)ints[0]);
				}
				break;
			}
			case C_REMOTE_2PRESS_UNLOCK:{
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x02,(byte)0x04,(byte)ints[0]);
				}
				break;
			}
			case C_AUTO_AC_ENABLED: {
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x06,(byte)ints[0]);
				}
				break;
			}
			case C_VALID_VENTILATION_ENABLED: {
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					settings((byte)0x01,(byte)0x07,(byte)ints[0]);
				}
				break;
			}
			case C_SENSITIVITY_OPEN_BIGLAMP: {
				if (ints != null && ints.length > 0) {
					if (ints[0] < 0) {
						ints[0] = 0;
					} else if (ints[0] > 4) {
						ints[0] = 4;
					}
					settings((byte)0x03,(byte)0x01,(byte)ints[0]);
				}
				break;
			}
			case C_CLOSE_INSIDELAMP_TIME: {
				if (ints != null && ints.length > 0) {
					if (ints[0] < 0) {
						ints[0] = 0;
					} else if (ints[0] > 3) {
						ints[0] = 3;
					}
					settings((byte)0x03,(byte)0x02,(byte)ints[0]);
				}
				break;
			}
			case C_DSP_VOL_LINK_SPEED: {
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					DspSetting(0x07, ints[0]);
				}
				break;
			}	case C_DSP_SURROUND: {
				if (ints != null && ints.length > 0) {
					if (ints[0] != 0) {
						ints[0] = 1;
					}
					DspSetting(0x08, ints[0]);
				}
				break;
			}		
			case C_UPDATE_LAST_N_MINUTE_OIL_EXPEND: {
				settings((byte)0x03,(byte)0x02,(byte)ints[0]);
				break;
			}
			case C_CLEAR_LAST_N_MINUTE_OIL_EXPEND: {
				settings((byte)0x04,(byte)0x01,(byte)0x01);
				break;
			}
			case C_UPDATE_TRIP_OIL_EXPEND: {
				settings((byte)0x04,(byte)0x02,(byte)0x02);
				break;
			}
			case C_CLEAR_TRIP_OIL_EXPEND: {
				settings((byte)0x04,(byte)0x02,(byte)0x01);
				break;
			}
			case FinalCanbus.C_CAMERA_MODE:{
				setCameraMode(ints[0]);
				break;
			}
			case C_EXTERIOR_LIGHT_OFF_TIME:{
				settings((byte)0x03,(byte)0x03,(byte)ints[0]);
				break;
			}
			case C_SHOW_RADAR:{
				settings((byte)0x01,(byte)0x08,(byte)ints[0]);
				break;
			}
			case C_RADAR_VOL:{
				settings((byte)0x01,(byte)0x09,(byte)ints[0]);
				break;
			}
			case C_RADAR_DISTANCE:{
				settings((byte)0x01,(byte)0x0a,(byte)ints[0]);
				break;
			}
			
		
         case C_AMP_SET:
             if (ints != null && ints.length > 1) {
                 SendFunc.send2Canbus(0xAD, ints);
             }
             break;
         case C_SET_2D:
             if (ints != null && ints.length > 0) {
                 SendFunc.send2Canbus(0x2D, ints);
             }
             break;
         case C_SET_6A:
             if (ints != null && ints.length > 0) {
                 SendFunc.send2Canbus(0x6A, ints);
             }
             break;
         case C_SET_24:
             if (ints != null && ints.length > 0) {
                 SendFunc.send2Canbus(0x24, ints);
             }
             break;
         case C_SET_FA:
        	 if (ints != null && ints.length > 0) {
        		 SendFunc.send2Canbus(0xFA, ints);
        	 }
        	 break;
         
		 }
		  
	};
	

	private static int mCameraMode = 0;
	// private static int mTempmode = 0;
	private void setCameraMode(int value) {
		// 后摄像头引导线设置：1、模式1；2、模式2 ；3、模式3；
		mCameraMode = value;
		// if(carId == 7||carId == 8){
		//// if(value == 0)mTempmode = 1;
		//// else if(value == 2)mTempmode =3;
		//// else if(value == 3)mTempmode = 4;
		// ToolkitDev.writeMcu(0xE3, 0x03,0xFA, 0xFF, value+1, 0x01);
		// ToolkitDev.writeMcu(0xE3, 0x03,0xFA, 0xFF, value+1, 0x00);
		// }else
		// ToolkitDev.writeMcu(0xE3, 0x03,0x6A, 0x06, 0x01, value+1);

		// if(mSph.get(CAMERA_MODE, 0) != mCameraMode){
		// mSph.putDelay(CAMERA_MODE, mCameraMode);
		// }
	}

	// 原车音效设置
	private void DspSetting(int cmd, int param) {
		// ToolkitDev.writeMcu(0xE3, 0x02, (byte) 0xad, (byte) cmd, (byte) param);

		SendFunc.send2Canbus(0xad, cmd, param);
	}

	private void settings(byte type, byte cmd, byte value) {
		// ToolkitDev.writeMcu(0xE3, 0x03,0x6A, type, cmd, value);
		SendFunc.send2Canbus(0x6A, type, cmd, value);
	}
	
}
