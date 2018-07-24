package com.ex.hiworld.aidl.car;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.spUtils;

import android.R.integer;
import android.text.format.DateFormat;

public class TaskCar_PSA extends BaseCar {
	public static final int U_CUR_OIL_EXPEND				= 0;	// 当前油耗
	public static final int U_DRIVING_MILEAGE				= 1;	// 续航里程
	public static final int U_OPTIMAL_OIL_EXPEND			= 2;	// 最佳油耗
	public static final int U_DRIVING_TIME					= 3;	// 行车时间
	public static final int U_AVERAGE_SPEED					= 4;	// 平均速度
	public static final int U_CUR_TRIP_OIL_EXPEND			= 5;	// 当前行程油耗
	public static final int U_CAR_CAMERA_MODE				= 6;
	public static final int U_SYSTEM_KEY_WITH_EKEY_ENABLED	= 7;	// 带电子钥匙的进入系统钥匙
	public static final int U_FEEDBACK_LAMP_ENABLED 		= 8;	// 锁止/解锁反馈指示灯
	public static final int U_TPMS_STATE					= 9;	// TPMS状态 TPMS_STATE_NORMAL/TPMS_STATE_WARNNIG
	public static final int U_TIRE_SHOW_STATE				= 10;	// 轮胎显示方式 TIRE_SHOW_LINE/TIRE_SHOW_CAR
	public static final int U_LOCK_UNLOCK_FEEDBACK_TONE		= 11;	// 锁/解锁反馈的声音大小?
	public static final int U_2TIMES_KEY_UNLOCK				= 12;// new int[]{0/1}, 操作钥匙两次解锁
	public static final int U_UNLOCK_BY_DRIVERS_DOOR_OPEN	= 13;// new int[]{0/1}, 驾驶席开门联动解锁
	public static final int U_MIRROR_WIPERS_REAR_WIN_WIPING_IN_RESERVE = 14; // value 倒车档时后窗玻璃刮水
	public static final int U_MIRROR_WIPERS_AUTO_WIPING_IN_RAIN = 15; //	
	public static final int U_DRIVER_ACC					= 16;	
	public static final int U_CAR_MID_ENABLE				= 17;	//MDI使能
	public static final int U_CAR_PLAY_STATE				= 18;	//播放状态
	public static final int U_OPEN_CLOSE_CONVENIENCE		= 19;	//播放状态
	public static final int U_OPEN_CLOSE_DOOR_UNLOCK		= 20;
	public static final int U_OPEN_CLOSE_AUTO_LOCK			= 21;
	public static final int U_MIRROR_WIPERS_FOLDIN_WHEN_PARKED	= 22;
	public static final int U_MIRROR_WIPERS_LOW_WHILE_REVESING	= 23;
	public static final int U_AUTO_AC_ENABLED 				= 24; 	// 自动A/C模式
	public static final int U_CURRENT_SPEED					= 25; // 当前车速
	public static final int U_AUTOLOCK_BY_SPEED				= 26;	// 车速感应车门自动锁定
	public static final int U_AUTOLOCK_BY_SHIFT_FROM_P		= 27;	// 换档联动车门自动锁定
	public static final int U_AUTOLOCK_BY_SHIFT_TO_P		= 28;	// P档联动解锁
	public static final int U_OPEN_BIGLAMP_BY_WIPER			= 29; // new int[]{0/1}，启动雨刮时启动大灯
	public static final int U_DAYTIME_RUNING_LIGHTS_ON		= 30;	// 日间行车灯
	public static final int U_VALID_VENTILATION_ENABLED 	= 31; 	// 有效通风模式
	public static final int U_CAR_BT_STATE					= 32;	//原车蓝牙状态
	public static final int U_LOCK_UNLOCK_LAMP_FLASH		= 33; // new int[]{0/1} 上锁,解锁,紧急闪烁灯响应 
	public static final int U_REMOTE_2PRESS_UNLOCK			= 34;	// 操作按钮两次时解锁
	public static final int U_UNLOCK_BY_SMART_DOOR			= 35;// new int[]{DOOR_DRIVER or DOOR_ALL} 智能车门解锁
	public static final int U_SMARTLOCK_AND_ONE_KEY_BOOT	= 36; // new int[]{0/1}	智能车锁和一键启动
	public static final int U_CLOSE_INSIDELAMP_TIME			= 37;	// 自动关闭车内灯光时间
	public static final int U_SENSITIVITY_OPEN_BIGLAMP 		= 38; 	// 大灯打开灵敏度
	public static final int U_CLOSE_BIGLAMP_TIME			= 39;	// 自动关闭大灯时间
	public static final int U_PRESSURE_FL					= 40;	// 前左胎压 单位PRESSURE_UNIT_BAR等  当值&0xFFFFFF为PRESSURE_NONE，代表TPMS还没获取到数据
	public static final int U_PRESSURE_FR					= 41;	// 前右胎压 单位PRESSURE_UNIT_BAR等  当值&0xFFFFFF为PRESSURE_NONE，代表TPMS还没获取到数据
	public static final int U_PRESSURE_RL					= 42;	// 后左胎压 单位PRESSURE_UNIT_BAR等  当值&0xFFFFFF为PRESSURE_NONE，代表TPMS还没获取到数据
	public static final int U_PRESSURE_RR					= 43;	// 后右胎压 单位PRESSURE_UNIT_BAR等  当值&0xFFFFFF为PRESSURE_NONE，代表TPMS还没获取到数据
	public static final int U_PRESSURE_BACKUP				= 44;	// 备胎胎压 单位PRESSURE_UNIT_BAR等  当值&0xFFFFFF为PRESSURE_NONE，代表TPMS还没获取到数据
	public static final int U_EXIST_TPMS					= 45;	// 有无TPMS设备 0/1
	public static final int U_SHOW_TIRE_BACKUP				= 46;	// 是否显示备胎 0/1
	public static final int U_MUTIL_CURRENT_CONSUMPTION		= 47;
	public static final int U_MUTIL_AVERAGE_CONSUMPTION		= 48;
	public static final int U_MUTIL_CONVENIENCE_CONSUMER	= 49;
	public static final int U_MUTIL_ECO_TIPS				= 50;
	public static final int U_MUTIL_TRAVELLING_TIME			= 51;
	public static final int U_MUTIL_DISTANCE_TRAVELLED		= 52;
	public static final int U_MUTIL_AVERAGE_SPEED			= 53;
	public static final int U_AIR_BY_AUTO_KEY				= 54; // new int[]{0/1} 空调与AUTO键联动
	public static final int U_IN_OUT_AIR_BY_AUTO_KEY		= 55; // new int[]{0/1} 内外气切换与AUTO键联动
	public static final int U_WARNNING_LOW_OIL				= 56; // 0/1, 油量过低报警开关
	public static final int U_WARNNING_LOW_BATTERY			= 57; // 0/1, 电池电压过低报警开关
	public static final int U_WARNNING_LIFE_BELT			= 58; // 0/1, 安全带报警开关
	public static final int U_WARNNING_CLEANNING_FLUID		= 59; // 0/1, 清洁液报警开关
	public static final int U_WARNNING_HANDLE_BRAKE			= 60; // 0/1, 手刹报警开关
	public static final int U_LAST_OIL						= 61; // value, 剩余油量
	public static final int U_BATTERY_VOLTAGE				= 62; // value, 电池电压
	public static final int U_ENGINE_SPEED					= 63; // value, 发动机转速
	public static final int U_PARK_BESIDE_ROAD				= 64; // 0/1, 路边驻车
	public static final int U_PARK_IN_CARPORT				= 65; // 0/1, 入库驻车
	public static final int U_RADAR_MUTE					= 66; // 0/1, 雷达静音
	public static final int U_DRIVER_DISPLAY_DISTANCE_WARNNING	= 67; // 0/1, 雷达静音
	public static final int U_MIRROR_WIPERS_SYN_ADJUST		= 68;	//播放进度
	public static final int U_CAR_PLAY_PROGRESS				= 69;	//播放进度
	public static final int U_CAR_PE_ENABLE					= 70;	//原车油电混合使能
	public static final int U_LIGHT_LEAVING_HOME			= 71;
	public static final int U_LIGHT_COMING_HOME				= 72;
	public static final int U_SHOW_RADAR					= 73; // new int[]{0/1} 显示雷达
	public static final int U_RADAR_VOL						= 74; // new int[]{level} 雷达音量警报等级
	public static final int U_JUMP_CARINFO					= 75; // new int[]{level} 雷达音量警报等级
	public static final int U_JUMP_ALAMINFO					= 76; // new int[]{level} 雷达音量警报等级
	public static final int U_CAR_RIGHTCAMERA_STATE			= 77;
	public static final int U_CARINFO_END					= 78; // new int[]{level} 雷达音量警报等级
	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= U_CARINFO_END;
	public static final int U_AIR_AUTO						= U_AIR_BEGIN+1;
	public static final int U_AIR_CYCLE						= U_AIR_BEGIN+2;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+3;
	public static final int U_AIR_REAR_DEFROST				= U_AIR_BEGIN+4;
	public static final int U_AIR_AC						= U_AIR_BEGIN+5;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+9;
//	public static final int U_AIR_BLOW_BODY_RIGHT			= U_AIR_BEGIN+5;
//	public static final int U_AIR_BLOW_FOOT_RIGHT			= U_AIR_BEGIN+6;
//	public static final int U_AIR_BLOW_UP_RIGHT				= U_AIR_BEGIN+7;
	public static final int U_AIR_MAX						= U_AIR_BEGIN+10;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+11;
//	public static final int U_AIR_WIND_LEVEL_RIGHT			= U_AIR_BEGIN+15;
//	public static final int U_AIR_SEAT_HEAT_LEFT			= U_AIR_BEGIN+11;
//	public static final int U_AIR_SEAT_HEAT_RIGHT			= U_AIR_BEGIN+12;
//	public static final int U_AIR_SEAT_BLOW_HEAT_LEFT		= U_AIR_BEGIN+13;
//	public static final int U_AIR_SEAT_BLOW_HEAT_RIGHT		= U_AIR_BEGIN+14;
//	public static final int U_AIR_DUAL						= U_AIR_BEGIN+9;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+12;
//	public static final int U_AIR_POWER						= U_AIR_BEGIN+10;
//	public static final int U_AIR_SHOW						= U_AIR_BEGIN+11;
//	public static final int U_AIR_BIG_LIGHT					= U_AIR_BEGIN+20;
	public static final int U_AIR_ECO						= U_AIR_BEGIN+13;
//	public static final int U_AIR_CONTROL					= U_AIR_BEGIN+13;	
//	public static final int U_AIR_TEMP_UNIT					= U_AIR_BEGIN+21;
//	public static final int U_AIR_FRONT_WIND_MODE			= U_AIR_BEGIN+22;
//	public static final int U_AIR_REAR_WIND_MODE			= U_AIR_BEGIN+23;
//	public static final int U_AIR_REAR_TEMP_LEFT			= U_AIR_BEGIN+24;
//	public static final int U_AIR_REAR_TEMP_RIGHT			= U_AIR_BEGIN+25;
//	public static final int U_AIR_BLOW_AUTO_LEFT			= U_AIR_BEGIN+17;
//	public static final int U_AIR_BLOW_WIN_LEFT				= U_AIR_BEGIN+11;
//	public static final int U_AIR_SYNC						= U_AIR_BEGIN+18;	
	public static final int U_AIR_MONO						= U_AIR_BEGIN+14;
	public static final int U_AIR_TEMP_UNIT					= U_AIR_BEGIN+15;
	public static final int U_AIR_END						= U_AIR_BEGIN+16;
	
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
	
	
	/**
	 *  17款4008
	 */
	
	public static final int U_174008_START			= U_DOOR_END;
	// 车道保持负责
	public static final int U_174008_LANE_MAINTAIN_ENABLE	= U_174008_START+1;
	// 疲劳检测系统 Fatigue detection system
	public static final int U_174008_FATIGUE_DETECTION_ENABLE= U_174008_START+2;
	// 限速提示
	public static final int U_174008_SPEED_LIMIT_ENABLE		= U_174008_START+3;
	// 主题颜色设置
	public static final int U_174008_THEME_COLOR_SET_ENABLE	= U_174008_START+4;
	// 氛围模式
	public static final int U_174008_AMBIENT_MODE_ENABLE		= U_174008_START+5;
	// 驾驶模式
	public static final int U_174008_DRIVE_MODE_ENABLE		= U_174008_START+6;
	// 离子净化器 Ion purifier
	public static final int U_174008_ION_PURIFIER_ENABLE		= U_174008_START+7;
	// 熏香类型 Incense type
	public static final int U_174008_INCENSE_TYPE_ENABLE		= U_174008_START+8;
	// 熏香浓度 Incense concentration
	public static final int U_174008_INCENSE_CONCENTRATION = U_174008_START+9;
	// 车道保持负责
	public static final int U_174008_LANE_MAINTAIN	= U_174008_START+10;
	// 疲劳检测系统 Fatigue detection system
	public static final int U_174008_FATIGUE_DETECTION= U_174008_START+11;
	// 限速提示
	public static final int U_174008_SPEED_LIMIT		= U_174008_START+12;
	// 主题颜色设置
	public static final int U_174008_THEME_COLOR_SET	= U_174008_START+13;
	// 氛围模式
	public static final int U_174008_AMBIENT_MODE		= U_174008_START+14;
	// 驾驶模式
	public static final int U_174008_DRIVE_MODE		= U_174008_START+15;
	// 离子净化器 Ion purifier
	public static final int U_174008_ION_PURIFIER		= U_174008_START+16;
	// 熏香类型 Incense type
	public static final int U_174008_INCENSE_TYPE		= U_174008_START+17;
	
	public static final int U_AQS						= U_174008_START+18;
	public static final int U_POWER 					= U_174008_START+19;
	public static final int U_174008_END				= U_174008_START+20;
	public static final int U_BACK_CAR_VOL				= U_174008_START+21;
	//屏幕亮度设定使能
	public static final int U_174008_PANNEL_BREGHT_ENABLE= U_174008_START+22;
	public static final int U_174008_PANNEL_BREGHT    	 = U_174008_START+23;
	public static final int U_174008_PANNEL_LEFT    	 = U_174008_START+24;
	public static final int U_174008_PANNEL_RIGHT    	 = U_174008_START+25;
	

	public static final int U_508_SOUND_0    	 = U_174008_PANNEL_RIGHT+1;
	public static final int U_508_SOUND_1    	 = U_508_SOUND_0+1;
	public static final int U_508_SOUND_2    	 = U_508_SOUND_0+2;
	public static final int U_508_SOUND_3    	 = U_508_SOUND_0+3;
	public static final int U_508_SOUND_4    	 = U_508_SOUND_0+4;
	public static final int U_508_SOUND_5    	 = U_508_SOUND_0+5;
	public static final int U_508_SOUND_6    	 = U_508_SOUND_0+6;
	public static final int U_508_SOUND_7    	 = U_508_SOUND_0+7;
	
	public static final int U_CNT_MAX					= U_508_SOUND_7+1;
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	public static final int C_AUTOLOCK_BY_SPEED					= 9; 	// 倒车自动后雨刮 
	public static final int C_AUTOLOCK_BY_SHIFT_FROM_P			= 10;	// 驻车辅助
	public static final int C_AUTOLOCK_BY_SHIFT_TO_P			= 11;	// 倒车是否发声
	public static final int C_REMOTE_2PRESS_UNLOCK				= 12;	// 车门解锁
	public static final int C_LOCK_UNLOCK_FEEDBACK_TONE			= 13;	// 日间行车灯
	public static final int C_CLOSE_INSIDELAMP_TIME				= 14;	// 伴我回家照明/大灯延迟
	public static final int C_CLOSE_BIGLAMP_TIME				= 15;	// 自动驻车
	public static final int C_2TIMES_KEY_UNLOCK					= 16;	//迎宾照明
	public static final int C_UNLOCK_BY_DRIVERS_DOOR_FUNCTION	= 17;	//氛围照明功能 
	public static final int C_UNLOCK_BY_SMART_DOOR				= 18;	//倒车雷达
	public static final int C_SMARTLOCK_AND_ONE_KEY_BOOT		= 19;	//车门自动上锁
	public static final int C_EXIST_EDOOR_BACK					= 20;	//车门上锁
	public static final int C_LOCK_UNLOCK_LAMP_FLASH			= 21; 	// 仅行李箱解锁
	public static final int C_AIR_BY_AUTO_KEY					= 22; 	// 随动转向大灯
	public static final int C_IN_OUT_AIR_BY_AUTO_KEY			= 23; 	// 胎压标定
	public static final int C_SHOW_RADAR						= 24; 	// 变道辅助
	public static final int C_RADAR_VOL							= 25; 	// 迎宾功能
	public static final int C_UNLOCK_BY_DRIVERS_DOOR_OPEN		= 26;	//氛围照明 
	public static final int C_MODEL_CHOICE_VALUE				= 27;	//车型选择
	public static final int C_AC 				= 30;
	public static final int C_AC_MAX			= 31;
	public static final int C_AUTO 			= 32;
	public static final int C_FRONT_DEFROST	= 33;	// 前除霜			
	public static final int C_REAR_DEFROST		= 34;	// 后除霜			
	public static final int C_CYCLE			= 35;	// 循环			
	public static final int C_BLOW_WIN			= 36;	// 吹窗
	public static final int C_BLOW_BODY		= 37;	// 吹身
	public static final int C_BLOW_FOOT		= 38;	// 吹脚
	public static final int C_WIND_RATE		= 39;	// 吹风量
	public static final int C_TEMPERATURE_LEFT = 40;	// 左温度
	public static final int C_TEMPERATURE_RIGHT= 41;	// 右温度
	public static final int C_WIND_LEVEL		= 42;	// 风量等级
	public static final int C_MONO				= 43;	// 单一温区
	public static final int C_TEM_VALUE				= 45;	// 温度
	public static final int C_OIL_VALUE				= 46;	// 油耗
	public static final int C_WARNING_INFO		= 50;	//警告信息
	public static final int C_Lauguage			= 51;	//语言设置
	public static final int C_MOTOR_FUCTION		= 52;	//发动机启停停止功能
	public static final int C_MEM_SPEED_ALL	= 60;	// 已记忆的速度值启用
	public static final int C_MEM_SPEED_ONE	= 61;	// 启用记忆速度1
	public static final int C_MEM_SPEED_ONE_VALUE	= 62;	// 启用记忆速度1
	public static final int C_MEM_SPEED_TWO		= 63;		// 启用记忆速度2
	public static final int C_MEM_SPEED_TWO_VALUE	= 64;	// 启用记忆速度2
	public static final int C_MEM_SPEED_THREE		= 65;		// 启用记忆速度3
	public static final int C_MEM_SPEED_THREE_VALUE= 66;	// 启用记忆速度3
	public static final int C_MEM_SPEED_FOUR		= 67;		// 启用记忆速度4
	public static final int C_MEM_SPEED_FOUR_VALUE	= 68;	// 启用记忆速度4
	public static final int C_MEM_SPEED_FIRE		= 69;		// 启用记忆速度5
	public static final int C_MEM_SPEED_FIRE_VALUE	= 70;	// 启用记忆速度5
	public static final int C_MEM_SPEED_SIX		= 71;		// 启用记忆速度6
	public static final int C_MEM_SPEED_SIX_VALUE	= 72;	// 启用记忆速度6
	
	public static final int C_CRUISE_SPEED_ALL			= 73;	// 航运速度值启用
	public static final int C_CRUISE_SPEED_ONE			= 74;	// 启用航运速度1
	public static final int C_CRUISE_SPEED_ONE_VALUE	= 75;	// 启用记忆速度1
	public static final int C_CRUISE_SPEED_TWO			= 76;	// 启用记忆速度2
	public static final int C_CRUISE_SPEED_TWO_VALUE	= 77;	// 启用记忆速度2
	public static final int C_CRUISE_SPEED_THREE		= 78;	// 启用记忆速度3
	public static final int C_CRUISE_SPEED_THREE_VALUE	= 79;	// 启用记忆速度3
	public static final int C_CRUISE_SPEED_FOUR		= 80;	// 启用记忆速度4
	public static final int C_CRUISE_SPEED_FOUR_VALUE	= 81;	// 启用记忆速度4
	public static final int C_CRUISE_SPEED_FIRE		= 82;	// 启用记忆速度5
	public static final int C_CRUISE_SPEED_FIRE_VALUE	= 83;	// 启用记忆速度5
	public static final int C_CRUISE_SPEED_SIX			= 84;	// 启用记忆速度6
	public static final int C_CRUISE_SPEED_SIX_VALUE	= 85;	// 启用记忆速度6
	public static final int C_PAGE_CLEAR		= 90;	// 清除当前界面 
	public static final int C_MENU 	= 91;
	public static final int C_UP 		= 92;
	public static final int C_DOWN 	= 93;
	public static final int C_LEFT 	= 94;
	public static final int C_RIGHT 	= 95;
	public static final int C_OK 		= 96; 
	public static final int C_ESC 		= 97; 
	public static final int C_MODE 	= 98; 
	public static final int C_DARK 	= 99; 
	

	public static final int C_AQS = 100; // 17款4008  
	public static final int C_174008_CARSET = 101; // 17款4008 用于命令统一
	public static final int  C_BACK_CAR_VOL = 102;//倒车是否发声
	public static final int C_AIR_POWER		= 103;	// 空调开关

	public static final int C_SET_VOICE		= 104;	// 功放
	public static final int C_SET_1B					= 105; // 行车信息命令 1B
	public static final int C_SET_24					= 106; // 车型设置 24
	public static final int C_SET_3B					= 107; // 空调控制 3B
	public static final int C_SET_7B					= 108; // 车辆设置命令1  
	public static final int C_SET_7D					= 109; // 车辆设置命令2 
	public static final int C_SET_8A					= 110; // 已记忆的速度值设置
	public static final int C_SET_8B					= 111; // 巡航速度值设置
	public static final int C_SET_8C					= 112; // 运动模式设置
	public static final int C_SET_9A					= 113; // 语言设置命令 
	public static final int C_SET_CA					= 114; // 单位设置 
	
	
	private static int mSpeedValueEnable = 0;
	private static int mSpeedValue1Enable = 0;
	private static int mSpeedValue1 = 0;
	private static int mSpeedValue2Enable = 0;
	private static int mSpeedValue2 = 0;
	private static int mSpeedValue3Enable = 0;
	private static int mSpeedValue3 = 0;
	private static int mSpeedValue4Enable = 0;
	private static int mSpeedValue4 = 0;
	private static int mSpeedValue5Enable = 0;
	private static int mSpeedValue5 = 0;
	private static int mSpeedValue6Enable = 0;
	private static int mSpeedValue6 = 0;
	
	private static int mCruiseValueEnable = 0;
	private static int mCruiseValue1Enable = 0;
	private static int mCruiseValue1 = 0;
	private static int mCruiseValue2Enable = 0;
	private static int mCruiseValue2 = 0;
	private static int mCruiseValue3Enable = 0;
	private static int mCruiseValue3 = 0;
	private static int mCruiseValue4Enable = 0;
	private static int mCruiseValue4 = 0;
	private static int mCruiseValue5Enable = 0;
	private static int mCruiseValue5 = 0;
	private static int mCruiseValue6Enable = 0;
	private static int mCruiseValue6 = 0;
	

	 int Vol_dis_cnt = 0;
	 int TemCanKey,CanKey,CanKey2,TemCanKey2 = 0xff;
	 int KeyCanKeyTableDS5L[][]=
	 {
			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
			{3, 		FinalKeyCode.KEY_CODE_MUTE},
			{4,			FinalKeyCode.KEY_CODE_VA},
			{5,			FinalKeyCode.KEY_CODE_PHONE},
			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
			{7, 		FinalKeyCode.KEY_CODE_NULL},
			{8,			FinalKeyCode.KEY_CODE_NEXT},
			{9,			FinalKeyCode.KEY_CODE_PREV},
			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
			{0x0d,		FinalKeyCode.KEY_CODE_UP},	
			{0x0e,		FinalKeyCode.KEY_CODE_DOWN},	
			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
			{0x11,		FinalKeyCode.KEY_CODE_PREV},	
			{0x12,		FinalKeyCode.KEY_CODE_NEXT},	
			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK},
			{0x07|0x80,		FinalKeyCode.KEY_CODE_BAND},
			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x09|0x80,		FinalKeyCode.KEY_CODE_MUTE},
			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
			{0x17|0x80,		FinalKeyCode.KEY_CODE_LEFT},	
			{0x18|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
			{0x19|0x80,		FinalKeyCode.KEY_CODE_PREV},	//	KEY_CODE_PREV
			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NEXT},		//KEY_CODE_NEXT
			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x2a|0x80,		FinalKeyCode.KEY_CODE_ENTER},
			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
			{0x2f|0x80,		FinalKeyCode.KEY_CODE_HOME},
			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
	 };
	
	 int KeyCanKeyTableDS5H[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_NEXT},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_NULL},//KEY_CODE_BRIGHT_DEC	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NULL},	//KEY_CODE_BRIGHT_INC	//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_HANG},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_BAND},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_PREV},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 };
	 
	 int KeyCanKeyTableDS5LSL[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_HOME},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_LEFT},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_PREV},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NEXT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_BAND},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 };
	 
	 int KeyCanKeyTableDS5LSH[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NULL},//KEY_CODE_BRIGHT_INC
		 			{0x07|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_PREV},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_NULL},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NULL},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_BAND},//KEY_CODE_BRIGHT_DEC
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 };

	 
	 int KeyCanKeyTableDS4L[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_HOME},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_SEARCH},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_LEFT},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_PREV},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NEXT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_BAND},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 };
	 
	 int KeyCanKeyTableDS4H[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_CARSETTTING},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_LEFT},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_PREV},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NEXT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2f|0x80,		FinalKeyCode.KEY_RADIO},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 };
	 int KeyCanKeyTable408[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_NULL},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NULL},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 };
	 int KeyCanKeyTable508L[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_BAND},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_UP},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_DOWN},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_LEFT},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_ENTER},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_BACK},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_SEARCH},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2f|0x80,		FinalKeyCode.KEY_RADIO},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 }; 
	 int KeyCanKeyTable508H[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x07|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_BAND},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_UP},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_DOWN},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_LEFT},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_ENTER},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_BACK},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_SEARCH},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2a|0x80,		FinalKeyCode.KEY_RADIO},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_HANG},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_CARSETTTING},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_HOME},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x32|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 };
	 
	 int KeyCanKeyTableC4L[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_HOME},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_HOME},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_N1},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_N2},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_N3},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_N4},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_N5},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_N6},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_EJECT},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_NEXT},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_LEFT},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_RIGHT},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_PLAYPAUSE},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_MODE},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_BAND},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 }; 
	 int KeyCanKeyTable40817[][]=
		 {
		 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
		 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
		 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
		 			{4,			FinalKeyCode.KEY_CODE_VA},
		 			{5,			FinalKeyCode.KEY_CODE_PHONE},
		 			{6, 		FinalKeyCode.KEY_CODE_NULL}, 
		 			{7, 		FinalKeyCode.KEY_CODE_NULL},
		 			{8,			FinalKeyCode.KEY_CODE_RIGHT},
		 			{9,			FinalKeyCode.KEY_CODE_LEFT},
		 			{0x0a,		FinalKeyCode.KEY_CODE_HOME},	
		 			{0x0b,		FinalKeyCode.KEY_CODE_MODE},	
		 			{0x0d,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x0e,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x0f,		FinalKeyCode.KEY_CODE_ENTER},
		 			{0x10,		FinalKeyCode.KEY_CODE_BACK},	
		 			{0x11,		FinalKeyCode.KEY_CODE_NEXT},	
		 			{0x12,		FinalKeyCode.KEY_CODE_PREV},	
		 			{0x13,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x14,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x15,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x16,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x40,		FinalKeyCode.KEY_CODE_PHONE},	

		 			{0x01|0x80,		FinalKeyCode.KEY_BLACK_SCREEN},
		 			{0x06|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x07|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x08|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x09|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0b|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0c|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0d|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0e|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x0f|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x11|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x17|0x80,		FinalKeyCode.KEY_CODE_NULL},	
		 			{0x18|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x19|0x80,		FinalKeyCode.KEY_CODE_NULL},	//	KEY_CODE_PREV
		 			{0x1A|0x80,		FinalKeyCode.KEY_CODE_NULL},		//KEY_CODE_NEXT
		 			{0x24|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x25|0x80,		FinalKeyCode.KEY_CODE_NULL},		
		 			{0x27|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x28|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x29|0x80,		FinalKeyCode.KEY_CODE_MUTE},
		 			{0x2a|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x2b|0x80,		FinalKeyCode.KEY_CODE_PHONE},
		 			{0x2c|0x80,		FinalKeyCode.KEY_CODE_HOME},
		 			{0x2d|0x80,		FinalKeyCode.KEY_CODE_PLAYER},
		 			{0x2e|0x80,		FinalKeyCode.KEY_CODE_CARSETTTING},
		 			{0x2f|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x31|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x32|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 			{0x33|0x80,		FinalKeyCode.KEY_CODE_NAVI},
		 			{0x40|0x80,		FinalKeyCode.KEY_CODE_NULL},
		 }; 
	 private final int RESUME_DATA = 1;
	private final int BACKCAR_VOL = 2;
	private final int PANNLELEFT_SHOW = 3;
	private final int PANNELRIGHT_SHOW = 4;
	private final int SOUND_SET0 = 5;
	private final int SOUND_SET1 = 6;
	private final int SOUND_SET2 = 7;
	private final int SOUND_SET3 = 8;
	private final int SOUND_SET4 = 9;
	private final int SOUND_SET5 = 10;
	private final int SOUND_SET6 = 11;
	private final int SOUND_SET7 = 12;
	private int CarTurboKeyVolPre;
	private int CarTurboKeyOtherPre;
	
	private static int BvolData = 0;
	private static int BPanelLeft = 0;
	private static int BPanelRight = 0;
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start+1]) {
		case 0x11:{
//			 仅处理0x14 雨刷器手柄按下
			int value = data[start+4] & 0xff;
			int action = data[start+5] & 0xff;
//			if((value == 0x14|value == 0x16) && action == 0x01)
//			{
//				if(mSph.get(RESUME_DATA, 0)!= 0x11 || 
//				DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_7 ||
//				DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_8 ||
//				DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_9 )	//3008留小屏   不进入油耗界面，以免跟原车屏显示油耗不对应
//					DataCanbus.MCLS[U_JUMP_CARINFO].update(U_JUMP_CARINFO, new int[]{1}, null, null);
//			}
			
			if(value == 0x15 && action == 0x01) { 
				HandlerTaskCanbus.update(U_JUMP_ALAMINFO, 1);
			}
			 
			SendFunc.setRadarOnOff(data[start+2]>>5&0x01); 
			
				CanKey = data[start+4]&0xFF;
				int i,j = 0;
				for(i=0;i<KeyCanKeyTable40817.length;i++){
					if(CanKey == KeyCanKeyTable40817[i][0]){
						j = i;
						if(CanKey!=0)
						TemCanKey = j;
						break;
					}
				}
					
				int[][] keyTable = getPanelKeys(); 
				 
				CanInfos.onKeyEvent(keyTable, data[start + 4]&0xFF, data[start +5] &0xFF); 
				
				CanInfos.CarBackTrackHandle(data[start+8]&0xFF, data[start+9]&0xFF);
			
			break;
		}
		case 0x12: // 车身详细信息 门状态 安全带报警状态
		{
			int B7 = data[start + 4];
			// 司机门
			HandlerTaskCanbus.update(U_DOOR_FL, B7 >> 7 & 0x01);
			// 副驾驶门
			HandlerTaskCanbus.update(U_DOOR_FR, B7 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RL, B7 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B7 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B7 >> 3 & 0x01);
			break;
		}
		case 0x13: {
			// 行车电脑信息 page0
			HandlerTaskCanbus.update(U_CUR_OIL_EXPEND, ((data[start + 2] & 0xff) << 8) | (data[start + 3] & 0xff));
			HandlerTaskCanbus.update(U_DRIVING_MILEAGE, ((data[start + 4] & 0xff) << 8) | (data[start + 5] & 0xff));
			HandlerTaskCanbus.update(U_CAR_CAMERA_MODE, ((data[start + 6] & 0xff) << 8) | (data[start + 7] & 0xff));
			break;
		}
		case 0x14: {
			// 行车电脑信息 page1
			HandlerTaskCanbus.update(U_OPTIMAL_OIL_EXPEND, ((data[start + 2] & 0xff) << 8) | (data[start + 3] & 0xff));
			HandlerTaskCanbus.update(U_AVERAGE_SPEED, data[start + 5] & 0xff);
			HandlerTaskCanbus.update(U_DRIVING_TIME, ((data[start + 6] & 0xff) << 8) | (data[start + 7] & 0xff));
			break;
		}
		case 0x15:{
			// 行车电脑信息 page2
			HandlerTaskCanbus.update(U_CUR_TRIP_OIL_EXPEND, 			((data[start+2] & 0xff) << 8) | (data[start+3] & 0xff));
			HandlerTaskCanbus.update(U_SYSTEM_KEY_WITH_EKEY_ENABLED, 	data[start+5] & 0xff);
			HandlerTaskCanbus.update(U_FEEDBACK_LAMP_ENABLED, 			((data[start+6] & 0xff) << 8) | (data[start+7] & 0xff));
			break;
		}
		
		case 0x21:{
//			 仅处理0x14 雨刷器手柄按下
			int value = data[start+2] & 0xff;
			int action = data[start+3] & 0xff;
			if(value == 0x40 && action == 0x01)
//			{
//				if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_1 || 
//						DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_3||
//						DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_4 ||
//						DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_5)
//				{
//					DataCanbus.MCLS[U_JUMP_CARINFO].update(U_JUMP_CARINFO, new int[]{1}, null, null);
//				}	
//			}
			
//			if(value == 0x27 && action == 0x01)
//			{
//				if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_7 ||
//				   DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_8 ||
//				   DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_9 ){
//					
//					DataCanbus.MCLS[U_JUMP_CARINFO].update(U_JUMP_CARINFO, new int[]{1}, null, null);
//				}	
//			}
			
//			if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_0)
//			{
//				if(value == 0x19 && action == 0x01)
//				{
//					HandlerMain.brightLevelCmd(FinalMain.BRIGHT_LEVEL_STEP_DOWN);
//				}
//				
//				if(value == 0x1a && action == 0x01)
//				{
//					HandlerMain.brightLevelCmd(FinalMain.BRIGHT_LEVEL_STEP_UP);
//				}
//				
//			}
//			
//			if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_2)
//			{
//				if(value == 0x2f && action == 0x01)
//				{
//					HandlerMain.brightLevelCmd(FinalMain.BRIGHT_LEVEL_STEP_DOWN);
//				}
//				
//				if(value == 0x06 && action == 0x01)
//				{
//					HandlerMain.brightLevelCmd(FinalMain.BRIGHT_LEVEL_STEP_UP);
//				}
//			}
//			
//			if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_11){ // 空调设置
//				if(value == 0x28) 
//				{
//					jumpAirSet();
//				}
//			}

			CanKey2 = (data[start+2]&0xFF)|0x80;
			int[][] keyTable = getPanelKeys(); 
			CanInfos.onKeyEvent(keyTable, CanKey2, data[start +3] &0xFF); 
//			if(DataCanbus.isAnalysisByServer()){
//				CanKey2 = (data[start+2]&0xFF)|0x80;
//				int i,j = 0;
//				for(i=0;i<KeyCanKeyTable408.length;i++){
//					if(CanKey2 == KeyCanKeyTable408[i][0]){
//						j = i;
//						if(CanKey2!=0)
//						TemCanKey2 = j;
//						break;
//					}
//				}
//				
//				Log.d("LG", "j="+j+" i="+i+"(data[start+2]&0xFF) ="+(data[start+2]&0xFF) );
//				Log.d("LG", "CanKey2="+CanKey2+"KeyCanKeyTable = "+KeyCanKeyTable408[j][1]+" TemCanKey2="+TemCanKey2);
//				
//				if(CanKey2!= 0x80){
//					if(i<KeyCanKeyTable408.length){
//
//						if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_1)//DS5L	
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS5L[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_0)//DS5H
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS5H[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_3)//DS5LSL
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS5LSL[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_2)//DS5LSH
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS5LSH[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_5)//DS4L
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS4L[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_4)//DS4H
//						HandlerAnalysis.keyEvent(KeyCanKeyTableDS4H[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_6)//408
//						HandlerAnalysis.keyEvent(KeyCanKeyTable408[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_8)//508L
//						HandlerAnalysis.keyEvent(KeyCanKeyTable508L[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_7)//508H
//						HandlerAnalysis.keyEvent(KeyCanKeyTable508H[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_9)//c4L
//						HandlerAnalysis.keyEvent(KeyCanKeyTableC4L[i][1], KeyEvent.ACTION_DOWN);
//						else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_11)//17 4008
//						HandlerAnalysis.keyEvent(KeyCanKeyTable40817[i][1], KeyEvent.ACTION_DOWN);
//						else 
//						HandlerAnalysis.keyEvent(KeyCanKeyTable408[i][1], KeyEvent.ACTION_DOWN);
//					}
//				}
//				else {
//					if((i==KeyCanKeyTable408.length)&&(TemCanKey2!=0xff)){
//
//						if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_1)//DS5L	
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS5L[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_0)//DS5H
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS5H[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_3)//DS5LSL
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS5LSL[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_2)//DS5LSH
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS5LSH[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_5)//DS4L
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS4L[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_4)//DS4H
//							HandlerAnalysis.keyEvent(KeyCanKeyTableDS4H[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_6)//408
//							HandlerAnalysis.keyEvent(KeyCanKeyTable408[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_8)//508L
//							HandlerAnalysis.keyEvent(KeyCanKeyTable508L[TemCanKey][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_7)//508H
//							HandlerAnalysis.keyEvent(KeyCanKeyTable508H[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_9)//c4L
//							HandlerAnalysis.keyEvent(KeyCanKeyTableC4L[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_PSAALL_11)//17 4008
//							HandlerAnalysis.keyEvent(KeyCanKeyTable40817[TemCanKey2][1], KeyEvent.ACTION_UP);
//							else 
//							HandlerAnalysis.keyEvent(KeyCanKeyTable408[TemCanKey2][1], KeyEvent.ACTION_UP);
//					}
//					
//					TemCanKey2 = 0xff;
//				}
//		}
			
			break;
		}
		case 0x22: {
			int action = data[start + 3] & 0xFF;
			switch (data[start + 2] & 0xFF) {
			case 1: {
				if (CarTurboKeyVolPre != action) {

					if ((action & 0xff) == 0xff && CarTurboKeyVolPre == 0) {
						CanInfos.mcuKeyVolDown();
					} else if ((action == 0 && CarTurboKeyVolPre == 0xFF) || (CarTurboKeyVolPre < action)) {
						CanInfos.mcuKeyVolUp();
					} else {
						CanInfos.mcuKeyVolDown();
					}
					CarTurboKeyVolPre = action;
				}
				break;
			}
			case 2:
			case 3:
			case 4:
			case 5: {

				if (CarTurboKeyOtherPre != (data[start + 3] & 0xff)) {
					if (action == 0xff && CarTurboKeyOtherPre == 0) {
						CanInfos.canbusKeyPrev();
					} else if ((data[start + 3] & 0xff) == 0x00 && CarTurboKeyOtherPre == 0xff) {
						CanInfos.canbusKeyNext();
					} else if (CarTurboKeyOtherPre < (data[start + 3] & 0xff)) {
						CanInfos.canbusKeyNext();
					} else {
						CanInfos.canbusKeyPrev();
					}
					CarTurboKeyOtherPre = (data[start + 3] & 0xff);
				}
				break;
			}
			default:
				break;
			}

			break;
		}
		case 0x31: {
			int B0 = data[start+2] & 0xff;		
			
			HandlerTaskCanbus.update(U_POWER, 				B0>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_MAX, 			B0>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AUTO, 			B0>>3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_MONO, 			B0>>2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC, 				B0>>0 & 0x01);
			int B1 = data[start+3] & 0xff;	
			HandlerTaskCanbus.update(U_AIR_CYCLE, 			B1>>4 & 0x01);
			HandlerTaskCanbus.update(U_AQS, 			B1>>3 & 0x01);

			int B2 = data[start+4] & 0xff;	
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, 			B2>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, 			B2>>4 & 0x01);
			
			int B3 = data[start+5] & 0xff;	
			int wind_level = B3 & 0x03;
			HandlerTaskCanbus.update(U_AIR_ECO, 			wind_level);
			
			int B4 = data[start+6] & 0xff;	
			int foot = 0, front = 0, body = 0;
			switch (B4) 
			{
			case 0x03: foot = 1;  			break;
			case 0x05: foot = body = 1;		break;
			case 0x06: body = 1; 			break;
			case 0x0b: front = 1; 			break;
			case 0x0c: foot = front = 1; 	break;
			case 0x0d: front = body = 1; 	break;
			case 0x0e: foot = front = body = 1; break;
			}
			
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, 			body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, 			foot);
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_LEFT, 			front);
			
			int B5 = data[start + 7] & 0xff;
			if (B5 < 0) {
				B5 = 0;
			} else if (B5 > 8) {
				B5 = 8;
			}
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, 			B5);
			
			int B6 = data[start + 8] & 0xff;
			if (B6 == 0xfe) {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, TEMPERATURE_LOW);
			} else if (B6 == 0xff) {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, TEMPERATURE_HIGH);
			} else {
				int value6 = B6 * 5;
				if (value6 > 200 && value6 < 260) {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, value6 / 5 * 5);
				} else {
					HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, value6 / 10 * 10);
				}
			}
			
			int B7 = data[start + 9] & 0xff;
			if (B7 == 0xfe) {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_LOW);
			} else if (B7 == 0xff) {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_HIGH);
			} else {
				int value7 = B7 * 5;
				if (value7 > 200 && value7 < 260) {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, value7 / 5 * 5);
				} else {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, value7 / 10 * 10);
				}
			}
			 
			int B11 = data[start+13]&0xFF;
			TypeWC2_Data.CarTemperature(B11);  
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
		case  0x42:{		// 告警信息
			
			int value = (data[start+2] << 8) & 0xff00 | data[start+3] & 0xff;
			HandlerTaskCanbus.update(U_TPMS_STATE, 			value);
			break;
		}
		
		case   0x71:{		// 车辆信息使能
			
			int B71_2 = data[start+2] & 0xff;
			
			HandlerTaskCanbus.update(U_TIRE_SHOW_STATE, 			B71_2>>7 & 0X01);
			HandlerTaskCanbus.update(U_LOCK_UNLOCK_FEEDBACK_TONE, 	B71_2>>6 & 0X01);
			HandlerTaskCanbus.update(U_2TIMES_KEY_UNLOCK, 			B71_2>>5 & 0X01);
			HandlerTaskCanbus.update(U_UNLOCK_BY_DRIVERS_DOOR_OPEN, B71_2>>3 & 0X01);
			int B71_3 = data[start+3] & 0xff;
	
			HandlerTaskCanbus.update(U_MIRROR_WIPERS_REAR_WIN_WIPING_IN_RESERVE, B71_3>>7 & 0X01);
			HandlerTaskCanbus.update(U_MIRROR_WIPERS_AUTO_WIPING_IN_RAIN,		 B71_3>>6 & 0X01);
			HandlerTaskCanbus.update(U_CAR_RIGHTCAMERA_STATE,					 B71_3>>5 & 0X01);
			HandlerTaskCanbus.update(U_DRIVER_ACC,								 B71_3>>4 & 0X01);
			HandlerTaskCanbus.update(U_CAR_MID_ENABLE,							 B71_3>>3 & 0X01);
			HandlerTaskCanbus.update(U_CAR_PLAY_STATE,							 B71_3>>2 & 0X01);
			HandlerTaskCanbus.update(U_OPEN_CLOSE_CONVENIENCE,					 B71_3>>1 & 0X01);
		
			break;
		}
		
		case  0x72:{		// 车辆信息使能
			
			int B72_2 = data[start+2] & 0xff;
			int b1  = data[start+3] & 0xff; 
			HandlerTaskCanbus.update(U_OPEN_CLOSE_DOOR_UNLOCK,					 B72_2>>7 & 0X01);
			HandlerTaskCanbus.update(U_OPEN_CLOSE_AUTO_LOCK,					 B72_2>>6 & 0X01);
			HandlerTaskCanbus.update(U_MIRROR_WIPERS_FOLDIN_WHEN_PARKED,		 B72_2>>5 & 0X01);
			HandlerTaskCanbus.update(U_MIRROR_WIPERS_LOW_WHILE_REVESING,		 B72_2>>4 & 0X01);
			
			
			// 17款4008
			HandlerTaskCanbus.update(U_174008_LANE_MAINTAIN_ENABLE,		B72_2>>3 & 0x01);
			HandlerTaskCanbus.update(U_174008_FATIGUE_DETECTION_ENABLE,	B72_2>>2 & 0x01);
			HandlerTaskCanbus.update(U_174008_SPEED_LIMIT_ENABLE,		B72_2>>1 & 0x01);
			HandlerTaskCanbus.update(U_174008_THEME_COLOR_SET_ENABLE,	B72_2 & 0x01);

			HandlerTaskCanbus.update(U_174008_AMBIENT_MODE_ENABLE,		b1>>7 & 0x01);  
			HandlerTaskCanbus.update(U_174008_ION_PURIFIER_ENABLE,		b1>>6 & 0x01);  
			HandlerTaskCanbus.update(U_174008_DRIVE_MODE_ENABLE,		b1>>5 & 0x01);  
			HandlerTaskCanbus.update(U_174008_INCENSE_TYPE_ENABLE,		b1>>4 & 0x01);
			HandlerTaskCanbus.update(U_174008_PANNEL_BREGHT_ENABLE,		b1>>3 & 0x01);
			
			
			
			break;
		}
		
		case 0x76:{		// 车辆设置信息
			int b0 = data[start+2] & 0xff;

			HandlerTaskCanbus.update(U_AUTO_AC_ENABLED,					 b0>>7 & 0X01);
			HandlerTaskCanbus.update(U_CURRENT_SPEED,					 b0>>6 & 0X01);
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SPEED,				 b0>>4 & 0X03);
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SHIFT_FROM_P,		 b0>>3 & 0X01);
			HandlerTaskCanbus.update(U_AUTOLOCK_BY_SHIFT_TO_P,			 b0>>0 & 0X07);
			
			int b1 = data[start+3] & 0xff;

			HandlerTaskCanbus.update(U_OPEN_BIGLAMP_BY_WIPER,			 b1>>7 & 0X01);
			HandlerTaskCanbus.update(U_DAYTIME_RUNING_LIGHTS_ON,		 b1>>6 & 0X01);
			HandlerTaskCanbus.update(U_VALID_VENTILATION_ENABLED,		 b1>>5 & 0X01);
			HandlerTaskCanbus.update(U_CAR_BT_STATE,					 b1>>4 & 0X01);
			HandlerTaskCanbus.update(U_LOCK_UNLOCK_LAMP_FLASH,			 b1>>3 & 0X01);
			HandlerTaskCanbus.update(U_REMOTE_2PRESS_UNLOCK,			 b1>>2 & 0X01);
			HandlerTaskCanbus.update(U_UNLOCK_BY_SMART_DOOR,			 b1>>0 & 0X03);
			break;
		}
		
		case 0x79:{		// 车辆设置信息2
			int  b0 = data[start+2] & 0xff;
			int  b1 = data[start+3] & 0xff;
			int  b2 = data[start+4] & 0xff;
			HandlerTaskCanbus.update(U_SMARTLOCK_AND_ONE_KEY_BOOT,		b0>>7 & 0X01);
			HandlerTaskCanbus.update(U_CLOSE_INSIDELAMP_TIME,			b0>>6 & 0X01);
			HandlerTaskCanbus.update(U_SENSITIVITY_OPEN_BIGLAMP,		b0>>5 & 0X01);
			HandlerTaskCanbus.update(U_CLOSE_BIGLAMP_TIME,			 	b0>>4 & 0X01);
			
			// 17款4008
			HandlerTaskCanbus.update(U_174008_LANE_MAINTAIN,			b0>>3 & 0x01);
			HandlerTaskCanbus.update(U_174008_FATIGUE_DETECTION,		b0>>2 & 0x01);
			HandlerTaskCanbus.update(U_174008_SPEED_LIMIT,			 	b0>>1 & 0x01);
			HandlerTaskCanbus.update(U_174008_THEME_COLOR_SET,			b0 & 0x01);

			HandlerTaskCanbus.update(U_174008_AMBIENT_MODE,			 	b1>>6 & 0x03); // 00 01 02
			HandlerTaskCanbus.update(U_174008_DRIVE_MODE,			 	b1>>5 & 0x01); // 0 1
			HandlerTaskCanbus.update(U_174008_ION_PURIFIER,			 	b1>>3 & 0x03); // 00 01 10
			HandlerTaskCanbus.update(U_174008_INCENSE_TYPE,			 	b1>>1 & 0x03); // 00 01 10

			HandlerTaskCanbus.update(U_174008_INCENSE_CONCENTRATION,	b2>>6 & 0x03); // 00 01 10 11
			HandlerTaskCanbus.update(U_174008_PANNEL_BREGHT,			b2>>2 & 0x0f); //
			
			
			break;
		}
		
		case  0x81:{
			int b0 = data[start+2] & 0xff;
			mSpeedValueEnable = (b0 & 0x80) == 0 ? 0 : 1;
			mSpeedValue1Enable = (b0 & 0x40) == 0 ? 0 : 1;
			mSpeedValue2Enable = (b0 & 0x20) == 0 ? 0 : 1;
			mSpeedValue3Enable = (b0 & 0x10) == 0 ? 0 : 1;
			mSpeedValue4Enable = (b0 & 0x08) == 0 ? 0 : 1;
			mSpeedValue5Enable = (b0 & 0x04) == 0 ? 0 : 1;
			mSpeedValue6Enable = (b0 & 0x02) == 0 ? 0 : 1;
			HandlerTaskCanbus.update(U_PRESSURE_FL,			 	b0>>7 & 0X01);
			HandlerTaskCanbus.update(U_PRESSURE_FR,			 	b0>>6 & 0X01);
			HandlerTaskCanbus.update(U_PRESSURE_RL,			 	b0>>5 & 0X01);
			HandlerTaskCanbus.update(U_PRESSURE_RR,			 	b0>>4 & 0X01);
			HandlerTaskCanbus.update(U_PRESSURE_BACKUP,			b0>>3 & 0X01);
			HandlerTaskCanbus.update(U_EXIST_TPMS,			 	b0>>2 & 0X01);
			HandlerTaskCanbus.update(U_SHOW_TIRE_BACKUP,		b0>>1 & 0X01);
			
			mSpeedValue1 = data[start+3] & 0xff;
			mSpeedValue2 = data[start+4] & 0xff;
			mSpeedValue3 = data[start+5] & 0xff;
			mSpeedValue4 = data[start+6] & 0xff;
			mSpeedValue5 = data[start+7] & 0xff;
			mSpeedValue6 = data[start+8] & 0xff;
			HandlerTaskCanbus.update(U_MUTIL_CURRENT_CONSUMPTION,		data[start+3] & 0xff);
			HandlerTaskCanbus.update(U_MUTIL_AVERAGE_CONSUMPTION,		data[start+4] & 0xff);
			HandlerTaskCanbus.update(U_MUTIL_CONVENIENCE_CONSUMER,		data[start+5] & 0xff);
			HandlerTaskCanbus.update(U_MUTIL_ECO_TIPS,					data[start+6] & 0xff);
			HandlerTaskCanbus.update(U_MUTIL_TRAVELLING_TIME,			data[start+7] & 0xff);
			HandlerTaskCanbus.update(U_MUTIL_DISTANCE_TRAVELLED,		data[start+8] & 0xff);
			int value81 = data[start+11] & 0xff;
			HandlerTaskCanbus.update(U_MUTIL_AVERAGE_SPEED,				value81>>7 & 0X01);
			
			break;
		}
		
		case  0x82:{
			// 巡航速度限值
			int b0 = data[start+2];
			mCruiseValueEnable = (b0 & 0x80) == 0 ? 0 : 1;
			mCruiseValue1Enable = (b0 & 0x40) == 0 ? 0 : 1;
			mCruiseValue2Enable = (b0 & 0x20) == 0 ? 0 : 1;
			mCruiseValue3Enable = (b0 & 0x10) == 0 ? 0 : 1;
			mCruiseValue4Enable = (b0 & 0x08) == 0 ? 0 : 1;
			mCruiseValue5Enable = (b0 & 0x04) == 0 ? 0 : 1;
			mCruiseValue6Enable = (b0 & 0x02) == 0 ? 0 : 1;	
			HandlerTaskCanbus.update(U_AIR_BY_AUTO_KEY,			 		b0>>7 & 0X01);
			HandlerTaskCanbus.update(U_IN_OUT_AIR_BY_AUTO_KEY,			b0>>6 & 0X01);
			HandlerTaskCanbus.update(U_WARNNING_LOW_OIL,			 	b0>>5 & 0X01);
			HandlerTaskCanbus.update(U_WARNNING_LOW_BATTERY,			b0>>4 & 0X01);
			HandlerTaskCanbus.update(U_WARNNING_LIFE_BELT,				b0>>3 & 0X01);
			HandlerTaskCanbus.update(U_WARNNING_CLEANNING_FLUID,		b0>>2 & 0X01);
			HandlerTaskCanbus.update(U_WARNNING_HANDLE_BRAKE,			b0>>1 & 0X01);
			
			mCruiseValue1 = data[start+3] & 0xff;
			mCruiseValue2 = data[start+4] & 0xff;
			mCruiseValue3 = data[start+5] & 0xff;
			mCruiseValue4 = data[start+6] & 0xff;
			mCruiseValue5 = data[start+7] & 0xff;
			mCruiseValue6 = data[start+8] & 0xff;
			HandlerTaskCanbus.update(U_LAST_OIL,							data[start+3] & 0xff);
			HandlerTaskCanbus.update(U_BATTERY_VOLTAGE,						data[start+4] & 0xff);
			HandlerTaskCanbus.update(U_ENGINE_SPEED,			 			data[start+5] & 0xff);
			HandlerTaskCanbus.update(U_PARK_BESIDE_ROAD,					data[start+6] & 0xff);
			HandlerTaskCanbus.update(U_PARK_IN_CARPORT,						data[start+7] & 0xff);
			HandlerTaskCanbus.update(U_RADAR_MUTE,							data[start+8] & 0xff);
			int value82 = data[start+11] & 0xff;
			HandlerTaskCanbus.update(U_DRIVER_DISPLAY_DISTANCE_WARNNING,	value82>>7 & 0X01);
			break;
		}
		
		case  0x85:		// 发动机启停停止功能
		{
			int D0 = data[start+2] & 0xff;
			int D1 = data[start+3] & 0xff;

			HandlerTaskCanbus.update(U_MIRROR_WIPERS_SYN_ADJUST,			D0>>7 & 0X01);
			HandlerTaskCanbus.update(U_CAR_PLAY_PROGRESS,					D1>>7 & 0X01);
			break;
		}
		
		case  0x94: // 语言信息设置
		{
			HandlerTaskCanbus.update(U_CAR_PE_ENABLE, data[start + 2] & 0xff);
			break;
		}
		
		case 0xc1:{
			int C0 = data[start+2] & 0xff;
			HandlerTaskCanbus.update(U_LIGHT_LEAVING_HOME,					C0>>5 & 0X01);
			HandlerTaskCanbus.update(U_LIGHT_COMING_HOME,					C0>>3 & 0X01);
			int C1 = data[start + 3] & 0xff;
			if ((C1 & 0x20) == 0) {
				HandlerTaskCanbus.update(U_SHOW_RADAR, 2);
			} else {
				HandlerTaskCanbus.update(U_SHOW_RADAR, 1);
			}
			
			int value_c1 = (C1 >> 1) & 0x03;
			if (value_c1 == 0) {
				HandlerTaskCanbus.update(U_RADAR_VOL, 1);
			} else if (value_c1 == 1) {
				HandlerTaskCanbus.update(U_RADAR_VOL, 2);
			} else {
				HandlerTaskCanbus.update(U_RADAR_VOL, 3);
			}
			break;
		}
//		case  0xF0: {
//			HandlerTaskCanbus.canbusVer(new String(data, start+2, length-2));
//			break;
//		}
		case  0x83:{
			CarRtProc(data[start+2]&0x01);
			break;
		} 
		}
	} 

	int CarOriBTphone = 0xff;
	int CarOriBTphonepre = 0xff;

	void CarRtProc(int value) {
		CarOriBTphone = value;
		int tempFlag = 0;
		if (CarOriBTphone == 0x01) {
			tempFlag = 1;
		} else {
			tempFlag = 0;
		}
		if (CarOriBTphonepre != CarOriBTphone) {
			CanInfos.setCarBtOn(tempFlag != 0);
		}
		CarOriBTphonepre = CarOriBTphone;
	}
	
	@Override
	public void in() {
		spUtils.init(""+getClass().getName(), MyApp.getInstance());
		EventNotify.NE_APPID.addNotify(NE_APP);
		EventNotify.NE_VOL_SRC.addNotify(NE_APP);
		EventNotify.NE_MUTE_SRC.addNotify(NE_APP);
        Ticks.addTicks1s(TIMESET);
        EventNotify.NE_LANG.addNotify(N_LANG); 
	}

	@Override
	public void out() {
		EventNotify.NE_APPID.removeNotify(NE_APP);
		EventNotify.NE_VOL_SRC.removeNotify(NE_APP);
		EventNotify.NE_MUTE_SRC.removeNotify(NE_APP);
        EventNotify.NE_LANG.removeNotify(N_LANG);
        Ticks.removeTicks1s(TIMESET);
	}

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

	private int[][] getPanelKeys(){
		int[][] keyTable;
		switch (DataCanbus.canbusId) {
		case FinalCanbus.CAR_PSA_DS5:
			keyTable = KeyCanKeyTableDS5L;
			break;
		case FinalCanbus.CAR_PSA_DS5_H:
			keyTable = KeyCanKeyTableDS5H;
			break;
		case FinalCanbus.CAR_PSA_DS5LS:
			keyTable = KeyCanKeyTableDS5LSL;
			break;
		case FinalCanbus.CAR_PSA_DS4:
			keyTable = KeyCanKeyTableDS4L;
			break;
		case FinalCanbus.CAR_PSA_DS4_H:
			keyTable = KeyCanKeyTableDS4H;
			break;
		case FinalCanbus.CAR_PSA_408:
			keyTable = KeyCanKeyTable408;
			break;
		case FinalCanbus.CAR_PSA_508_L:
			keyTable = KeyCanKeyTable508L;
			break;
		case FinalCanbus.CAR_PSA_508_H:
			keyTable = KeyCanKeyTable508H;
			break;
		case FinalCanbus.CAR_PSA_C4L:
			keyTable = KeyCanKeyTableC4L;
			break;
		case FinalCanbus.CAR_PSA_4008:
			keyTable = KeyCanKeyTable40817;
			break;
		default:
			keyTable = KeyCanKeyTable408;
			break;
		}
		return keyTable;
	}
	
	IUiNotify NE_APP = new IUiNotify() {
		
		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			CarDisNormal();
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
            if (min != lastMin || lastFormat != format) {
                lastFormat = format;
                lastMin = min;

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR);
                int sec = calendar.get(Calendar.SECOND); 
                SendFunc.sendTime2(year, month, day, hour, min, sec, format, 0);
            }
        }
    };
	static void CarsSendID() {
		int[] cmds = new int[3];
		int t1 = 0;
		t1 = DataHost.sAccon == 1 ? 0x80 : 0;
		cmds[0] = t1 | 0x40;// set the menu bit
		cmds[1] = CarDisSourceIdGet();
		cmds[2] = DataHost.sVolDst;

		if (DataHost.sVolDst > 30)
			cmds[2] = 30;
		SendFunc.send2Canbus(0xA1, cmds);
	}
	
	static int CarDisSourceIdGet() {
		int sourceid = 0x00;
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			sourceid = 0x05;
			break;

		case FinalMain.APP_ID_DVD:// 2
			sourceid = 0x02; 
			break;
		case FinalMain.APP_ID_IPOD:// 4//IPOD
			sourceid = 0x05;
			break;
		case FinalMain.APP_ID_AUX:// 5//AUX
			sourceid = 0x05;
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
			sourceid = 0x05;
			break;
		case FinalMain.APP_ID_NULL:// 14
			sourceid = 0x05;
			break;
		case FinalMain.APP_ID_THIRD_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:// 15
			sourceid = 0x05;
			break;
		case FinalMain.APP_ID_DVR:// 17
		case FinalMain.APP_ID_CAR_RADIO:// 18// 原车收音
		case FinalMain.APP_ID_CAR_BTPHONE:// 19// 原车蓝牙
		case FinalMain.APP_ID_CAR_USB:// 20// 原车USB
		default:
			break;
		}
		
		if (DataHost.sBackCar == 1) {
			sourceid = 0x05;// Carmera
		}

		return sourceid;
	}
	
	static void CarDisNormal() {

		CarsSendID();
		switch (DataHost.sAppid) {
		// case FinalMain.APP_ID_DVD:
		// CarCanDisDvdInfo();
		// break;

		case FinalMain.APP_ID_RADIO:
			CarCanDisRadioInfo();
			break;

		default:
			break;
		}

	}

	private static void CarCanDisRadioInfo() {
		int[] cmds = new int[8];

		if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
			cmds[0] = 0x01;
		} else if (1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
				|| 2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
			cmds[0] = 0x02;
		} else {
			cmds[0] = 0x04;
		}
		cmds[0] = DataHost.sRadioFreq & 0xFF;
		cmds[1] = (DataHost.sRadioFreq >> 8) & 0xFF;
		cmds[2] = ((DataHost.sRadioChannel & 0xff)) % 6 + 1;
		cmds[3] = 0x00;
		cmds[4] = 0x00;

		SendFunc.send2Canbus(0xa2, cmds);
	}

	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_BACK_CAR_VOL: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				BackCar_Vol_Set(ints[0]);
			}
			break;
		}
		case C_AUTOLOCK_BY_SPEED: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x01, ints[0]);
			}
			break;
		}

		case C_AUTOLOCK_BY_SHIFT_FROM_P: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x02, ints[0]);
			}
			break;
		}

		case C_AUTOLOCK_BY_SHIFT_TO_P: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x03, ints[0]);
			}
			break;
		}

		case C_REMOTE_2PRESS_UNLOCK: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x04, ints[0]);
			}
			break;
		}

		case C_LOCK_UNLOCK_FEEDBACK_TONE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x05, ints[0]);
			}
			break;
		}

		case C_CLOSE_INSIDELAMP_TIME: {
			if (ints != null && ints.length > 0) {
				carSet(0x7b, 0x06, ints[0]);
			}
			break;
		}

		case C_CLOSE_BIGLAMP_TIME: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x08, ints[0]);
			}
			break;
		}

		case C_2TIMES_KEY_UNLOCK: {
			if (ints != null && ints.length > 0) {
				carSet(0x7b, 0x09, ints[0]);
			}
			break;
		}

		case C_UNLOCK_BY_DRIVERS_DOOR_FUNCTION: {
			if (ints != null && ints.length > 0) {
				carSet(0x7b, 0x0a, ints[0]);
			}
			break;
		}

		case C_UNLOCK_BY_DRIVERS_DOOR_OPEN: {
			if (ints != null && ints.length > 0) {
				carSet(0x7b, 0x0a, ints[0]);
			}
			break;
		}

		case C_UNLOCK_BY_SMART_DOOR: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x0b, ints[0]);
			}
			break;
		}

		case C_SMARTLOCK_AND_ONE_KEY_BOOT: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x0c, ints[0]);
			}
			break;
		}

		case C_EXIST_EDOOR_BACK: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7b, 0x0d, ints[0]);
			}
			break;
		}

		case C_LOCK_UNLOCK_LAMP_FLASH: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7d, 0x01, ints[0]);
			}
			break;
		}

		case C_AIR_BY_AUTO_KEY: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7d, 0x02, ints[0]);
			}
			break;
		}

		case C_IN_OUT_AIR_BY_AUTO_KEY: {
			if (ints != null && ints.length > 0) {
				carSet(0x7d, 0x03, ints[0]);
			}
			break;
		}

		case C_SHOW_RADAR: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7d, 0x04, ints[0]);
			}
			break;
		}

		case C_RADAR_VOL: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				carSet(0x7d, 0x05, ints[0]);
			}
			break;
		}

		case C_MODEL_CHOICE_VALUE: {
			if (ints != null && ints.length > 0) {
				saveValue(ints[0]);
				carSet(0x24, ints[0], 0);
			}
			break;
		}

		case C_AC: // AC
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x02, ints[0]);
			}
			break;
		}

		case C_AC_MAX: // AC-MAX
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x03, ints[0]);
			}
			break;
		}

		case C_AUTO: // AUTO
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x04, ints[0]);
			}
			break;
		}

		case C_FRONT_DEFROST: // AUTO
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x05, ints[0]);
			}
			break;
		}

		case C_REAR_DEFROST: // AUTO
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x06, ints[0]);
			}
			break;
		}

		case C_CYCLE: // AUTO
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x07, ints[0]);
			}
			break;
		}

		case C_BLOW_WIN: // 吹窗
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x08, ints[0]);
			}
			break;
		}

		case C_BLOW_BODY: // 吹身
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x09, ints[0]);
			}
			break;
		}

		case C_BLOW_FOOT: // 吹脚
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x0a, ints[0]);
			}
			break;
		}

		case C_WIND_RATE: // 吹风量
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] == 0) {
					airSet(0x0B, 1);
				} else {
					airSet(0x0b, 2);
				}
			}
			break;
		}

		case C_TEMPERATURE_LEFT: // 左温度
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] == 0) {
					airSet(0x0c, 1);
				} else {
					airSet(0x0c, 2);
				}
			}
			break;
		}

		case C_TEMPERATURE_RIGHT: // 右温度
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] == 0) {
					airSet(0x0d, 1);
				} else {
					airSet(0x0d, 2);
				}
			}
			break;
		}

		case C_WIND_LEVEL: // 风等级
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] < 0)
					ints[0] = 0;
				if (ints[0] > 2)
					ints[0] = 2;
				airSet(0x0e, ints[0]);
			}
			break;
		}

		case C_MONO: // 单一温区
		{
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x0f, ints[0]);
			}
			break;
		}

		case C_TEM_VALUE: // 温度
		{
			if (ints != null && ints.length > 0) {
				carSet(0xca, 0x03, ints[0]);
			}
			break;
		}

		case C_OIL_VALUE: // 油耗
		{
			if (ints != null && ints.length > 0) {
				carSet(0xca, 0x05, ints[0]);
			}
			break;
		}

		case C_Lauguage: // 语言设置
		{
			if (ints != null && ints.length > 0) {
				carSet(0x9a, 0x01, ints[0] + 1);
			}
			break;
		}

		case C_MOTOR_FUCTION: // 发动机启停停止功能
		{
			if (ints != null && ints.length > 0) {
				carSet(0x8C, 0x01, 0XFF);
			}
			break;
		}

		case C_MEM_SPEED_ALL: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValueEnable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_ONE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue1Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_TWO: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue2Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_THREE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue3Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_FOUR: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue4Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_FIRE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue5Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_SIX: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mSpeedValue6Enable = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_ONE_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue1 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_TWO_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue2 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_THREE_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue3 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_FOUR_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue4 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_FIRE_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue5 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_MEM_SPEED_SIX_VALUE: {
			if (ints != null && ints.length > 0) {
				mSpeedValue6 = ints[0];
				memSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_ALL: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValueEnable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_ONE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue1Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_TWO: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue2Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_THREE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue3Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_FOUR: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue4Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_FIRE: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue5Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_SIX: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				mCruiseValue6Enable = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_ONE_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue1 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_TWO_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue2 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_THREE_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue3 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_FOUR_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue4 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_FIRE_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue5 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}
		case C_CRUISE_SPEED_SIX_VALUE: {
			if (ints != null && ints.length > 0) {
				mCruiseValue6 = ints[0];
				CruiseSpeedSet();
			}
			break;
		}

		case C_PAGE_CLEAR: {
			if (ints != null && ints.length > 0) {
				clearPage(ints[0]);
			}
			break;
		}

		case C_MENU: {
			carSet(0x2A, 0x16, 0x01);
			carSet(0x2A, 0x16, 0x00);
			carSet(0x21, 0x16, 0x01);
			carSet(0x21, 0x16, 0x00);
			break;
		}

		case C_UP: {
			carSet(0x2A, 0x17, 0x01);
			carSet(0x2A, 0x17, 0x00);
			carSet(0x21, 0x17, 0x01);
			carSet(0x21, 0x17, 0x00);
			break;
		}

		case C_DOWN: {
			carSet(0x2A, 0x18, 0x01);
			carSet(0x2A, 0x18, 0x00);
			carSet(0x21, 0x18, 0x01);
			carSet(0x21, 0x18, 0x00);
			break;
		}

		case C_LEFT: {
			carSet(0x2A, 0x19, 0x01);
			carSet(0x2A, 0x19, 0x00);
			carSet(0x21, 0x19, 0x01);
			carSet(0x21, 0x19, 0x00);
			break;
		}

		case C_RIGHT: {
			carSet(0x2A, 0x1A, 0x01);
			carSet(0x2A, 0x1A, 0x00);
			carSet(0x21, 0x1A, 0x01);
			carSet(0x21, 0x1A, 0x00);
			break;
		}

		case C_OK: {
			carSet(0x2A, 0x24, 0x01);
			carSet(0x2A, 0x24, 0x00);
			carSet(0x21, 0x24, 0x01);
			carSet(0x21, 0x24, 0x00);
			break;
		}

		case C_ESC: {
			carSet(0x2A, 0x25, 0x01);
			carSet(0x2A, 0x25, 0x00);
			carSet(0x21, 0x25, 0x01);
			carSet(0x21, 0x25, 0x00);
			break;
		}

		case C_MODE: {
			carSet(0x2A, 0x26, 0x01);
			carSet(0x2A, 0x26, 0x00);
			carSet(0x21, 0x26, 0x01);
			carSet(0x21, 0x26, 0x00);
			break;
		}
		case C_DARK: {
			carSet(0x2A, 0x27, 0x01);
			carSet(0x2A, 0x27, 0x00);
			carSet(0x21, 0x27, 0x01);
			carSet(0x21, 0x27, 0x00);
			break;
		}
		case C_174008_CARSET: {
			if (ints != null && ints.length > 1) {
				if (ints[0] == 0x11) {
					PanelLeft_Set(ints[1]);
				} else if (ints[0] == 0x12) {
					PanelRight_Set(ints[1]);
				} else
					carSet(0x7d, ints[0], ints[1]);
			}
			break;
		}
		case C_AQS: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x10, ints[0]);
			}
			break;
		}
		case C_AIR_POWER: {
			if (ints != null && ints.length > 0) {
				if (ints[0] != 0) {
					ints[0] = 1;
				}
				airSet(0x01, ints[0]);
			}
			break;
		}
		case C_SET_VOICE:{
			switch(ints[0]){
			case 0x02:	set_Sound(ints[1],SOUND_SET0,U_508_SOUND_0,ints[0]);	break;
			case 0x03:	set_Sound(ints[1],SOUND_SET1,U_508_SOUND_1,ints[0]);    break;
			case 0x0B:	set_Sound(ints[1],SOUND_SET2,U_508_SOUND_2,ints[0]);    break;
			case 0x0C:	set_Sound(ints[1],SOUND_SET3,U_508_SOUND_3,ints[0]);    break;
			case 0x0D:	set_Sound(ints[1],SOUND_SET4,U_508_SOUND_4,ints[0]);    break;
			case 0x0E:	set_Sound(ints[1],SOUND_SET5,U_508_SOUND_5,ints[0]);    break;
			case 0x0F:	set_Sound(ints[1],SOUND_SET6,U_508_SOUND_6,ints[0]);    break;
			case 0x10:	set_Sound(ints[1],SOUND_SET7,U_508_SOUND_7,ints[0]);    break;
			}
			break;
		}	
		case C_SET_1B:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x1B, ints);
            }
            break;
        case C_SET_24:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x24, ints);
            }
            break;
        case C_SET_3B:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x3B, ints);
            }
            break;
        case C_SET_7B:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x7B, ints);
            }
            break;
        case C_SET_7D:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x7D, ints);
            }
            break;
        case C_SET_8A:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x8a, ints);
            }
            break;
        case C_SET_8B:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x8B, ints);
            }
            break;
        case C_SET_8C:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x8C, ints);
            }
            break;
        case C_SET_9A:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0x9a, ints);
            }
            break;
        case C_SET_CA:
            if (ints != null && ints.length > 0) {
                SendFunc.send2Canbus(0xca, ints);
            }
            break;
		
		
		}
	};
	

	private void memSpeedSet() {
		byte data0 = (byte) ((mSpeedValueEnable << 7) | (mSpeedValue1Enable << 6) | (mSpeedValue2Enable << 5)
				| (mSpeedValue3Enable << 4) | (mSpeedValue4Enable << 3)
				| (mSpeedValue5Enable << 2 | (mSpeedValue6Enable << 1)));
		byte data1 = (byte) mSpeedValue1;
		byte data2 = (byte) mSpeedValue2;
		byte data3 = (byte) mSpeedValue3;
		byte data4 = (byte) mSpeedValue4;
		byte data5 = (byte) mSpeedValue5;
		byte data6 = (byte) mSpeedValue6;
		byte data7 = 0x00;
		byte data8 = 0x00;
		byte data9 = 0x00;
		int[] is = new int[] { data0, data1, data2, data3, data4, data5, data6, data7, data8, data9 };
		SendFunc.send2Canbus(0x8a, is);
	}
	
	private void CruiseSpeedSet() {
		byte data0 = (byte) ((mCruiseValueEnable << 7)|(mCruiseValue1Enable << 6)|(mCruiseValue2Enable << 5)|
				(mCruiseValue3Enable << 4)|(mCruiseValue4Enable << 3)|(mCruiseValue5Enable << 2|(mCruiseValue6Enable << 1)));
		byte data1 = (byte) mCruiseValue1;
		byte data2 = (byte) mCruiseValue2;
		byte data3 = (byte) mCruiseValue3;
		byte data4 = (byte) mCruiseValue4;
		byte data5 = (byte) mCruiseValue5;
		byte data6 = (byte) mCruiseValue6;
		byte data7 = 0x00;
		byte data8 = 0x00;
		byte data9 = 0x00;
		int[] is = new int[] { data0, data1, data2, data3, data4, data5, data6, data7, data8, data9 };
		SendFunc.send2Canbus(0x8B, is);
	}
	
	private void carSet(int command, int cmd, int param) {
		// if((command == 0x24)&&(DataCanbus.sCanbusId !=
		// FinalCanbus.CAR_WC2_PSAALL_11)){//雷刚更新16/4/18日更新主动空调
		// if(cmd == 16||cmd == 6){
		// CANBUS_INFO.mExistAirControl = 1;
		// ModuleCallbackList.update(DataCanbus.MCLS, FinalCanbus.U_EXIST_AIR_CONTROL,
		// 1);
		// }
		// else {
		// CANBUS_INFO.mExistAirControl = 0;
		// ModuleCallbackList.update(DataCanbus.MCLS, FinalCanbus.U_EXIST_AIR_CONTROL,
		// 0);
		// }
		// }
		// ToolkitDev.writeMcu(0xE3, 0x02, (byte)command, (byte) cmd, (byte) param);
		SendFunc.send2Canbus(command, cmd, param);
	}

	private void airSet(int cmd, int param) {
		SendFunc.send2Canbus(0x3B, cmd, param);
	}

	private void clearPage(int page) {
		SendFunc.send2Canbus(0x1B, new int[] { page, page, 0x01, 0xff });
	}

	private void set_Sound(int value, int key, int U_ID, int C_ID) {
		// ToolkitDev.writeMcu(0xE3, 0x02, (byte) 0xad, (byte)C_ID, (byte)value);
		SendFunc.send2Canbus(0xad, C_ID, value);
		HandlerTaskCanbus.update(U_ID, value);
		if (spUtils.get(key, 0) != value) {
			spUtils.set(key, value);
		}
	}

	public void saveValue(int value) {
		if (spUtils.get(RESUME_DATA, 0) != value)
			spUtils.set(RESUME_DATA, value);

	}

	public void BackCar_Vol_Set(int value) {
		BvolData = value;
		HandlerTaskCanbus.update(U_BACK_CAR_VOL, BvolData);
		if (spUtils.get(BACKCAR_VOL, 0) != BvolData) {
			spUtils.set(BACKCAR_VOL, BvolData);
		}
		SendFunc.send2Canbus(0x7b, 0x03, value);
	}

	public void PanelLeft_Set(int value) {

		BPanelLeft = value;
		HandlerTaskCanbus.update(U_174008_PANNEL_LEFT, BPanelLeft);
		if (spUtils.get(PANNLELEFT_SHOW, 0) != BPanelLeft) {
			spUtils.set(PANNLELEFT_SHOW, BPanelLeft);
		}
		// ToolkitDev.writeMcu(0xE3, 0x02, (byte)0x7d, 0x11, (byte) value);
		SendFunc.send2Canbus(0x7D, 0x11, value);
	}

	public void PanelRight_Set(int value) {

		BPanelRight = value;
		HandlerTaskCanbus.update(U_174008_PANNEL_RIGHT, BPanelRight);
		if (spUtils.get(PANNELRIGHT_SHOW, 0) != BPanelRight) {
			spUtils.set(PANNELRIGHT_SHOW, BPanelRight);
		}
		// ToolkitDev.writeMcu(0xE3, 0x02, (byte)0x7d, 0x12, (byte) value);
		SendFunc.send2Canbus(0x7D, 0x11, value);
	}
}
