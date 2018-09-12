package com.ex.hiworld.aidl.car;

import java.nio.charset.Charset;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.HandlerNotRemove;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;

import android.R.integer;
import android.os.RemoteException;
import android.widget.Toast;

public class TaskCar_GM_All extends BaseCar {

	/**
	 * 注意威驰别克通用的是把使能和值 enable<<8|value 
	 * 发给canbus的，所以不用单独弄使能更新
	 */
	//自动模式风量
	public static final int U_AUTO_WIND_MODE  					=   0;
	//空调模式设置
	public static final int U_AIR_MODE_SET 						=	1;
	//空气质量传感器设置1
	public static final int U_AIR_QUALITY_SENSOR1				=   2;
	//空气质量传感器设置2
	public static final int U_AIR_QUALITY_SENSOR2				=   3;
	//自动区域温度设定
	public static final int U_AUTO_ZOOM_TEMP_SET				=   4;
	//座椅自动通风设定
	public static final int U_AUTO_SEAT_TONGFENG_SET			=   5;
	//座椅自动加热设定
	public static final int U_AUTO_HEAT_SEAT					=	6;
	//遥控启动座椅自动通风
	public static final int U_REMOTE_START_SEAT_AUTO_TONFEGN	=	7;
	//遥控启动座椅自动加热
	public static final int U_REMOTE_START_SEAT_AUTO_HOT		=	8;
	//瞬时油耗
	public static final int U_SHUNSHI_YOUHAO					=	9;
	//后座区域温度设定
	public static final int U_REAR_ZOOM_TEMP_SET				=	10;
	//前窗自动除雾
	public static final int U_AUTO_FRONT_GOFOG_ON 				=   11;
	//后窗自动除雾
	public static final int U_AUTO_REAR_GOFOG_ON				=	12;
	//车速信息
	public static final int U_CAR_CURRENT_SPEED					=	13;
	//遥控启动空调设
	public static final int U_REMOTE_START_AIR					=	14;
	//泊车辅助系统设定
	public static final int U_BOCHE_HELP_SYSTEM_SET				=	15;
	//侧盲区报警系统设定
	public static final int U_CEMANG_WARN_SYSTEM_SET			=	16;
	//防撞警报类型设定
	public static final int U_FANGZHUANG_WARN_TYPE_SET			=	17;
	//泊车辅助系统设定（带拖卡补偿）
	public static final int U_BOCHE_SYSTEM_SET_TUOKA			=	18;
	//24GHZ雷达设定
	public static final int U_24GHZ_RADAR_SET					=	19;
	//驾驶员座椅停车移位设定
	public static final int U_DRIVER_SEAT_YIWEI					=	20;
	//转向管柱离车移位设定
	public static final int U_ZHUANGXIANG_GUANZHU_YIWEI			=	21;
	//外后视镜倒车自动倾斜设定
	public static final int U_AUTO_MIRROR_QINGXIE				=	22;
	//外后视镜自动折叠设定
	public static final int U_AUTO_MIRROR_FOLDING				=	23;
	//驾驶员个性设置设定
	public static final int U_DRIVER_PERSONAL_SET				=	24;
	//倒挡自动后窗雨刷设定
	public static final int U_AUTO_YUSHUA_SHEDING				=	25;
	//转向管柱离车倾斜设定
	public static final int U_ZHUANGXIANG_GUANZHU_QINGXIE		=	26;
	//防止开门时自动落锁
	public static final int U_PREVENT_AUTO_LUOSUO				=   27;
	//自动落锁
	public static final int U_AUTO_LUOSUO						=   28;
	//自动解锁（自动档）
	public static final int U_AUTO_UNLOCK_ZIDONG				=   29;
	//延时落锁
	public static final int U_DELAY_TO_LOCK_SET					=	30;
	//自动解锁（手动档）
	public static final int U_AUTO_UNLOCK_SHOUDONG				=   31;
	//遥控落锁反馈
	public static final int U_REMOTE_LOCK_FEEDBACK				=	32;
	//遥控解锁反馈
	public static final int U_REMOTE_UNLOCK_FEEDBACK			=	33;
	//遥控解锁设定
	public static final int U_REMOTE_LOCK_SET					=	34;
	//远程解锁车门自动重锁设定
	public static final int U_REMOTE_UNLOCK_AUTO_LOCK			=	35;
	//重锁遥控打开的门 设定
	public static final int U_RELOCK_REMOTE_OPEN_DOOR			=	36;
	//驾驶员钥匙自动识别
	public static final int U_DRIVER_KEY_AUTO_SHIBIE	 		= 	37;
	//远程启动设定
	public static final int U_YUANCHENG_START_SET	 			= 	38;
	//智能近车解锁
	public static final int U_NEAR_CAR_UNLOCK					=	39;
	//离车落锁
	public static final int U_LEARVER_CAR_LOCK					=	40;
	//钥匙遗忘提醒
	public static final int U_FORGET_KEY						=	41;
	//遥控滑移门设置
	public static final int U_REMOTE_HUAYIMEN_SET    			= 	42;
	//寻车灯功能
	public static final int U_LOOKING_FOR_LIGHT 				=	43;
	//落锁大灯延时设置
	public static final int U_LUOSUO_DELAY_SET					= 	44;
	//混合动力ECO指示设定
	public static final int U_ECO_ZHISHI_SET					= 	45;
	//仪表导航信息显示设定
	public static final int U_YIBIAO_INFO_SET					= 	46;
	//速度范围提示模式设定
	public static final int U_SPEED_MODE_SET					= 	47;
	//运动模式发动机状态
	public static final int U_SPEED_MODE_FADONGJI_SET			= 	48;
	//运动模式背光设定
	public static final int U_SPEED_MODE_BEIGUANG_SET			= 	49;
	//发生请求命令
	public static final int U_SOUND_CMD_REQUEST					= 	50;
	//安吉星状态信息
	public static final int U_ANJIXING_STATE					=	51;
	//安吉星通话类型
	public static final int U_ANJIXING_PHONE_TYPE				=	52;
	//安吉星通话标志
	public static final int U_ANJIXING_PHONE_FLAG				=	53;
	//安吉星通话时间
	public static final int U_ANJIXING_PHONE_HOUR				=	54;
	public static final int U_ANJIXING_PHONE_MINUTE				=	55;
	public static final int U_ANJIXING_PHONE_SECOND				=	56;
	//安吉星剩余时间 有效期 年月日
	public static final int U_ANJIXING_SHENGYU_TIME				=	57;
	public static final int U_ANJIXING_SHENGYU_YEAR				=	58;
	public static final int U_ANJIXING_SHENGYU_MOUNTH			=	59;
	public static final int U_ANJIXING_SHENGYU_DAY				=	60;
	//警告信息类型
	public static final int U_ANJIXING_WARN_TYPE				=	61;
	public static final int U_ANJIXING_NUMBER					=	62;
	public static final int U_ANJIXING_BT_PASSWORD				=	63;
	public static final int U_ANJIXING_BT_NUMBER				=	64;
	//面板选择状态
	public static final int U_CAR_TYPE_SELECT					=   65;
	//威驰威朗中需要新增几个更新变量
	//自动防撞准备
	public static final int U_AUTO_FANGZHUANG					=   66;
	//汽车状态通知
	public static final int U_CAR_STATE_NOTIFY					=   67;
	//遥控启动座椅自动加热1
	public static final int U_REMOTE_START_SEAT_AUTO_HEAT1		=   68;
	//自动雨刮
	public static final int U_AUTO_YUGUA						=   69;
	//遥控车窗控制
	public static final int U_REMOTE_WINDOW_CONTROL				=   70;
	
	/**
	 * 空调
	 */
	public static final int U_AIR_BEGIN						= 71;
	public static final int U_AIR_POWER_ON					= U_AIR_BEGIN+0;
	public static final int U_AIR_AC_ON						= U_AIR_BEGIN+1;
	//hy 没用到
	public static final int U_AIR_AC_HY						= U_AIR_BEGIN+2;
	public static final int U_AIR_AIR_QULITY				= U_AIR_BEGIN+3;
	public static final int U_AIR_CYCLE_TYPE				= U_AIR_BEGIN+4;
	public static final int U_AIR_CYCLE_AUTO				= U_AIR_BEGIN+5;
	public static final int U_AIR_FRONT_DEFROST				= U_AIR_BEGIN+6;
	public static final int U_AIR_BLOW_UP_LEFT				= U_AIR_BEGIN+7;
	public static final int U_AIR_BLOW_BODY_LEFT			= U_AIR_BEGIN+8;
	public static final int U_AIR_BLOW_FOOT_LEFT			= U_AIR_BEGIN+9;
	public static final int U_AIR_BLOW_AUTO_LEFT			= U_AIR_BEGIN+10;
	public static final int U_AIR_WIND_LEVEL_LEFT			= U_AIR_BEGIN+11;
	public static final int U_AIR_TEMP_LEFT					= U_AIR_BEGIN+12;
	public static final int U_AIR_TEMP_RIGHT				= U_AIR_BEGIN+13;
	public static final int U_AIR_BLOW_AUTO_REAR			= U_AIR_BEGIN+14;
	public static final int U_AIR_BLOW_BODY_REAR			= U_AIR_BEGIN+15;
	public static final int U_AIR_BLOW_FOOT_REAR			= U_AIR_BEGIN+16;
	public static final int U_AIR_TEMP_LEFT_REAR			= U_AIR_BEGIN+17;
	public static final int U_AIR_WIND_LEVEL_REAR			= U_AIR_BEGIN+18;
	public static final int U_AIR_END						= U_AIR_BEGIN+19;
	/**
	 * 车门
	 */
	public static final int U_DOOR_BEGIN					= 91;
	public static final int U_DOOR_ENGINE					= U_DOOR_BEGIN+0;
	public static final int U_DOOR_FL						= U_DOOR_BEGIN+1;
	public static final int U_DOOR_FR						= U_DOOR_BEGIN+2;
	public static final int U_DOOR_RL						= U_DOOR_BEGIN+3;
	public static final int U_DOOR_RR						= U_DOOR_BEGIN+4;
	public static final int U_DOOR_BACK						= U_DOOR_BEGIN+5;
	public static final int U_DOOR_END						= U_DOOR_BEGIN+6;
	public static final int U_CUR_SPEED						= U_DOOR_BEGIN+7;
	public static final int U_ENGINE_SPEED					= U_DOOR_BEGIN+8;
	public static final int U_AIR_SEAT_HEAT_LEFT			= U_DOOR_BEGIN+9;
	public static final int U_AIR_SEAT_HEAT_RIGHT			= U_DOOR_BEGIN+10;
	public static final int U_AIR_SEAT_HEAT_LEVEL_LEFT		= U_DOOR_BEGIN+11;
	public static final int U_AIR_SEAT_HEAT_LEVEL_RIGHT		= U_DOOR_BEGIN+12;
	public static final int U_AIR_SYNC_ON					= U_DOOR_BEGIN+13;
	public static final int U_SETTING_ASSIST				= U_DOOR_BEGIN+14;
	public static final int U_AUTO_CRUISE_CONTROL			= U_DOOR_BEGIN+15;
	public static final int U_AIR_BLOW_FRONT_MODE			= U_DOOR_BEGIN+16;
	public static final int U_AIR_BLOW_REAR_MODE			= U_DOOR_BEGIN+17;
	public static final int U_AIR_SHOW						= U_DOOR_BEGIN+18;
	
	

	//左前胎压值//id 36,308,357,254,345
	public static final int U_FRONT_LEFT_PRESS					=	120;//id 36,308,357,254,345
	//右前胎压值
	public static final int U_FRONT_RIGHT_PRESS					=	121;
	//左后胎压值
	public static final int U_REAR_LEFT_PRESS					=	122;
	//右后胎压值
	public static final int U_REAR_RIGHT_PRESS					=	123;
	//左前报警信息
	public static final int U_FRONT_LEFT_HIGH_WARN_INFO			=	124;
	public static final int U_FRONT_LEFT_LOW_WARN_INFO			=	125;
	public static final int U_FRONT_LEFT_WARN_INFO				=	126;
	//右前报警信息
	public static final int U_FRONT_RIGHT_HIGH_WARN_INFO		=	127;
	public static final int U_FRONT_RIGHT_LOW_WARN_INFO			=	128;
	public static final int U_FRONT_RIGHT_WARN_INFO				=	129;
	//左后报警信息
	public static final int U_REAR_LEFT_HIGH_WARN_INFO			=	130;
	public static final int U_REAR_LEFT_LOW_WARN_INFO			=	131;
	public static final int U_REAR_LEFT_WARN_INFO				=	132;
	//右后报警信息
	public static final int U_REAR_RIGHT_HIGH_WARN_INFO			=	133;
	public static final int U_REAR_RIGHT_LOW_WARN_INFO			=	134;
	public static final int U_REAR_RIGHT_WARN_INFO				=	135;

	public static final int U_WARN_ENABLE						=	136;
	//GL6
	public static final int U_PREVENT_ANTI_LOCK					=	137;
	public static final int U_LOCATION_LIGHTS					=	138;
	public static final int U_ANJIXING_TTS						=	139;
	public static final int U_ANJIXING_TTS_SHOW					=	140;
	public static final int U_ANJIXING_PHONE_NUMBER				=	141;

	public static final int U_AIR_FRONT_GL6_DEFROST				= 142;
	public static final int U_AIR_REAR_DEFROST					= 143;

	private static final int U_COSUME_INSTANT 					= U_AIR_REAR_DEFROST + 1;
	private static final int U_COSUME_RECHARGE_MILES 			= U_COSUME_INSTANT + 1;
	private static final int U_COSUME_TOTAL_MILES 				= U_COSUME_INSTANT + 2;
	private static final int U_COSUME_AVERAGE_COST1 			= U_COSUME_INSTANT + 3;
	private static final int U_COSUME_TRIP_MILES1 				= U_COSUME_INSTANT + 4;
	private static final int U_COSUME_AVERAGE_COST2 			= U_COSUME_INSTANT + 5;
	private static final int U_COSUME_TRIP_MILES2 				= U_COSUME_INSTANT + 6;
	private static final int U_COSUME_AVERAGE_COST3 			= U_COSUME_INSTANT + 7;
	private static final int U_COSUME_TRIP_MILES3 				= U_COSUME_INSTANT + 8;
	private static final int U_COSUME_MILE_UNIT 				= U_COSUME_INSTANT + 9;
	private static final int U_COSUME_FUEL_UNIT 				= U_COSUME_INSTANT + 10;
	
	public static final int U_CNT_MAX							= U_COSUME_FUEL_UNIT + 1;
	
	final int C_SET_CAR_TYPE = 0; // int[0]
	final int C_CAR_SYS_SETUP = 2; // int[0] int[1] 系统设置命令
	final int C_CAR_COLLISION = 3; // int[0] int[1] 冲撞设置命令
	final int C_CAR_CONVINIECE = 4; // int[0] int[1] 舒适便捷性设置命令
	final int C_CAR_DOOR_LOCK = 5; // int[0] int[1] 门锁设定命令
	final int C_CAR_REMOTE_SETUP = 6; // int[0] int[1] 遥控设定命令
	final int C_CAR_LIGHTING_SETUP = 7; // int[0] int[1] 照明设定命令
	final int C_CAR_DIAL_SHOW_SETUP = 8; // int[0] int[1] 仪表显示设定命令
	final int C_CAR_MOVE_MODE_SETUP = 9; // int[0] int[1] 运动模式设定命令
	final int C_CAR_LANGUAGE_SETUP = 96; // int[0] int[1] 语言设定命令
	final int C_CAR_ONSTAR_CMD = 11; // 安吉星命令
	final int C_CAR_ONSTAR_CALL = 12; // 安吉星拨号
	final int C_CAR_BT_KEY = 13; // 安吉星拨号
	final int C_AIR_CONTROL_CMD = 14; //空调控制命令 长度0x02，int[0] 命令 int[1] 参数
	final int C_CAR_DEFAULT_SETUP = 15; // 恢复出厂设置
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	int onStartState = 0;
	int isWork = 0;
	int sChannelPre;
	
	
	int KeyCanKeyTable[][]=
	 {
	 			{1|0x80,		FinalKeyCode.KEY_CODE_VOL_UP},
	 			{2|0x80, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
	 			{3|0x80, 		FinalKeyCode.KEY_CODE_MUTE},
	 			{4|0x80,		FinalKeyCode.KEY_CODE_VA},//KEY_CODE_VA
	 			{5|0x80,		FinalKeyCode.KEY_CODE_PHONE},
	 			{6|0x80, 		FinalKeyCode.KEY_CODE_HANG}, 
	 			{7|0x80, 		FinalKeyCode.KEY_CODE_NULL}, 
	 			{8|0x80,		FinalKeyCode.KEY_CODE_PREV},
	 			{9|0x80,		FinalKeyCode.KEY_CODE_NEXT},
	 			{0x0a|0x80,		FinalKeyCode.KEY_CODE_MODE},	
	 			
	 			
	 			{0x01,		FinalKeyCode.KEY_CODE_POWER},	// power 短按静音， 长按黑屏
	 			{0x02,		FinalKeyCode.KEY_CODE_PREV},	//KEY_CODE_DISPLAY
	 			{0x03,		FinalKeyCode.KEY_CODE_NEXT},	
	 			{0x04,		FinalKeyCode.KEY_CODE_CARSETTTING},	
	 			{0x05,		FinalKeyCode.KEY_CODE_EQ},//KEY_CODE_AUDIOSETTING	
	 			{0x06,		FinalKeyCode.KEY_CODE_BACK},	
	 			{0x07,		FinalKeyCode.KEY_CODE_BAND},	
	 			{0x08,		FinalKeyCode.KEY_CODE_MODE},	
	 			{0x09,		FinalKeyCode.KEY_CODE_MUTE},	
	 			{0x0a,		FinalKeyCode.KEY_CODE_N1},	
	 			{0x0b,		FinalKeyCode.KEY_CODE_N2},	
	 			{0x0c,		FinalKeyCode.KEY_CODE_N3},	
	 			{0x0d,		FinalKeyCode.KEY_CODE_N4},	
	 			{0x0e,		FinalKeyCode.KEY_CODE_N5},	
	 			{0x0f,		FinalKeyCode.KEY_CODE_N6},	
	 			{0x10,		FinalKeyCode.KEY_CODE_PLAYPAUSE},	
	 			{0x11,		FinalKeyCode.KEY_CODE_MUTE},	
	 			{0x12,		FinalKeyCode.KEY_CODE_SYSTEMINFO},	
	 			{0x13,		FinalKeyCode.KEY_CODE_TIMESETTINT},	//KEY_CODE_TIMESETTINT
	 			{0x14,		FinalKeyCode.KEY_CODE_NAVI},	
	 			{0x15,		FinalKeyCode.KEY_CODE_SEARCH},
	 			{0x16,		FinalKeyCode.KEY_CODE_RECENT_TASK}, //select
	 			{0x17,		FinalKeyCode.KEY_CODE_UP},//KEY_CODE_UP
	 			{0x18,		FinalKeyCode.KEY_CODE_DOWN},	//KEY_CODE_DOWN
	 			{0x19,		FinalKeyCode.KEY_CODE_LEFT},//	KEY_CODE_LEFT
	 			{0x1a,		FinalKeyCode.KEY_CODE_RIGHT},//	KEY_CODE_RIGHT
	 			{0x1b,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x1c,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x1d,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x1e,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x1f,		FinalKeyCode.KEY_CODE_AUX},	
	 			{0x20,		FinalKeyCode.KEY_CODE_NAVI},	
	 			{0x21,		FinalKeyCode.KEY_CODE_HOME},	 // destination
	 			{0x22,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x23,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x24,		FinalKeyCode.KEY_CODE_PLAYER},	// media
	 			{0x25,		FinalKeyCode.KEY_CODE_REPEAT},//repeat	
	 			{0x26,		FinalKeyCode.KEY_CODE_EQ},	
	 			{0x27,		FinalKeyCode.KEY_CODE_EJECT},	
	 			{0x28,		FinalKeyCode.KEY_CODE_PHONE},	
	 			{0x29,		FinalKeyCode.KEY_CODE_NULL},	
	 			{0x2a,		FinalKeyCode.KEY_CODE_ENTER},	
	 			{0x2b,		FinalKeyCode.KEY_CODE_HOME},	
	 			{0x2c,		FinalKeyCode.KEY_CODE_MODE},	
	 			{0x2d,		FinalKeyCode.KEY_CODE_MENU},	
	
	 };

	
	static int CarKeyVolPlusCnt = 0;
	static int CarKeyVolMinusCnt = 0;
	int Vol_dis_cnt = 0;
//	int TemCanKey,CanKey,CanKey2,TemCanKey2 = 0xff;

	@Override
	public void onHandler(int[] data) {
		int start = 0;
		int length = data.length;
		switch (data[start+1]) {
		case 0x11:{ // 车辆基本信息
//			if(DataCanbus.isAnalysisByServer()){
//				if((data[start+2]&0x02)!=0){
//					HandlerTaskCanbus.ArmLamplet(1);
//				}else{
//					HandlerTaskCanbus.ArmLamplet(0);
//				}
//				if((data[start+2]&0x04)!=0){
//					HandlerTaskCanbus.ArmBackCar(1);
//				}else{
//					HandlerTaskCanbus.ArmBackCar(0);
//				}
//			} 
			HandlerTaskCanbus.update(U_CAR_CURRENT_SPEED, data[start + 3] & 0xff); // 车速 km/h
//			if(action0 == 1){
//				if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_GM_GL6){
//					if((key0 == 0x04)){//接电话
//						
//						if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING)
//							DataBt.sCmd.pickup();
//						else if(DataBt.sPhoneState != FinalBt.PHONE_STATE_DISCONNECTED){
//							HandlerMain.mcuKeyBtPhone();
//						}
//						else {
//							if(isWork == 1){ //Mute
//								ToolkitDev.writeMcu(0xE3, 0x02,0xBA, 0x01, 0x00);
//							}else{ 
//								JumpPage.va();
//								//
//							}
//						}
//					}else if(key0 == 0x03){
//						if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING
//								||DataBt.sPhoneState ==FinalBt.PHONE_STATE_TALK||DataBt.sPhoneState ==FinalBt.PHONE_STATE_DIAL){
//								DataBt.sCmd.hang();
//							} else{
//							if(isWork == 1){ //Mute
//								ToolkitDev.writeMcu(0xE3, 0x02,0xBA, 0x03, 0x00);
//							}else
//								HandlerMain.mcuKeyVolMute();
//						}
//					}
//				}else{
//					if((key0 == 0x03)||(key0 == 0x06)){
//						if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING
//							||DataBt.sPhoneState ==FinalBt.PHONE_STATE_TALK||DataBt.sPhoneState ==FinalBt.PHONE_STATE_DIAL){
//							DataBt.sCmd.hang();
//						} else {
//							if(isWork == 1){ //Mute
//							ToolkitDev.writeMcu(0xE3, 0x02,0xBA, 0x01, 0x00);
//						}else{ 
//							HandlerMain.mcuKeyVolMute();
//						}
//					}
//				}else if(key0 == 0x05){//接电话
//					if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING)
//						DataBt.sCmd.pickup();
//					else if(DataBt.sPhoneState != FinalBt.PHONE_STATE_DISCONNECTED){
//							HandlerMain.mcuKeyBtPhone();
//						}else{
//							JumpPage.va();
//						}
//					}else if(key0 == 0x04){//接电话
//						JumpPage.va();
//					}
//				}
//			}
			  
				// 0 0
				CanInfos.onKeyEvent2(KeyCanKeyTable,  (data[start+4]&0xFF)|0x80, data[start + 5]);
				
//				Log.d("LG", "j="+j+" i="+i);
//				Log.d("LG", "CanKey="+CanKey+"KeyCanKeyTable = "+KeyCanKeyTable[j][1]+" TemCanKey="+TemCanKey);
				
//				if(CanKey!= 0x80){
//					if(i<KeyCanKeyTable.length)
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[i][1], KeyEvent.ACTION_DOWN);
//				}
//				else {
//					if((i==KeyCanKeyTable.length)&&(TemCanKey!=0xff))
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[TemCanKey][1], KeyEvent.ACTION_UP);	
//					
//					TemCanKey = 0xff;
//				}
				
				CanInfos.CarBackTrackHandle(data[start+8]&0xFF, data[start+9]&0xFF);
//				int CanWheelAngle =  CarBackTrackHandle(data[start+8]&0xFF,data[start+9]&0xFF);
//					if(DataHost.sBackCar == 1){
//						ModuleCallbackList.update(DataMain.MCLS, FinalMain.U_STEER_ANGLE, CanWheelAngle);
//					}	
				
				
				
			break;
			}
		case 0x12:{// 车辆详细信息
			// 门状态
			int B2 = data[start+4];
			int B3 = data[start+5];
			int B4 = data[start+6];
//			if(DataCanbus.sDriverOnRight == 0){
				HandlerTaskCanbus.update(U_DOOR_FL, 		B2>>7&0x01);
				HandlerTaskCanbus.update(U_DOOR_FR, 		B2>>6&0x01);
////			}else {
//				HandlerTaskCanbus.update(U_DOOR_FL, 		B2>>6&0x01);
//				HandlerTaskCanbus.update(U_DOOR_FR, 		B2>>7&0x01);	
////			}
			HandlerTaskCanbus.update(U_DOOR_RL, 		B2>>5&0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, 		B2>>4&0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, 		B2>>3&0x01);
			HandlerTaskCanbus.update(U_DOOR_ENGINE, 	B2>>2&0x01);
			
			// 瞬时油耗
			HandlerTaskCanbus.update(U_SHUNSHI_YOUHAO, (B3 & 0xff) * 100 + B4);
			break;
		}
		case 0x21:{ //面板按键 有些需要我们处理
			final int key = data[start+2] & 0xff;
			final int action = data[start+3] & 0xff;
			if(action == 1) {
				switch (key) {
//				case 0x04://config
//					JumpPage.carSettings();
//					break;
//				case 0x05: //Tone
//					JumpPage.eq();
//					break;
//				case 0x09://Mute
//					if(DataBt.sPhoneState == FinalBt.PHONE_STATE_RING){
//						DataBt.sCmd.pickup();
//					} else {
//						if(isWork == 1){ //Mute
//							ToolkitDev.writeMcu(0xE3, 0x02,0xBA, 0x01, 0x00);
//						}else{ 
//							HandlerMain.mcuKeyVolMute();
//						}
//					}
//				
//					break;
//				case 0x12://info
//					JumpPage.go2AndroidInfo();
//					break;
					
//				case 0x13://Time
//					JumpPage.timeSetting();
//					break;
				
//				case 0x16://select;
//					
//					break;
//				case 0x17://up
//					HandlerMain.mcuKeyUp();
//					break;
//				case 0x18://Down
//					HandlerMain.mcuKeyDown();
//					break;
//				case 0x19://Left
//					HandlerMain.mcuKeyLeft();
//					break;
//				case 0x1A://Right
//					HandlerMain.mcuKeyRight();
//					break;
//				case 0x1B://Upleft
//					
//					break;
//				case 0x1C://UpRight
//					
//					break;
//				case 0x1D://DownLeft
//					
//					break;
//				case 0x1E://DownLeft
//					
//					break;
//				case 0x25://RPT/NAV
////					JumpPage.changeMusicPlayMode();
//					break;
//				default:
//					break;
				}
			}
			
//			if(DataCanbus.isAnalysisByServer()){
//				int i,j = 0;
//				for(i=0;i<KeyCanKeyTable.length;i++){
//					if(CanKey2 == KeyCanKeyTable[i][0]){
//						j = i;
//						if(CanKey2!=0)
//						TemCanKey2 = j;
//						break;
//					}
//				}
			{
//				Log.d("LG", "j="+j+" i="+i);
//				Log.d("LG", "CanKey2="+CanKey2+"KeyCanKeyTable = "+KeyCanKeyTable[j][1]+" TemCanKey2="+TemCanKey2);
				
//				if((CanKey2!= 0)&&((data[start+3]&0xff) !=0)){
//					if(i<KeyCanKeyTable.length)
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[i][1], KeyEvent.ACTION_DOWN);
//				}
//				 else if((CanKey2== 0)&&((data[start+3]&0xff) ==0)){
//					if((i==KeyCanKeyTable.length)&&(TemCanKey2!=0xff)){
//					HandlerAnalysis.keyEvent(KeyCanKeyTable[TemCanKey2][1], KeyEvent.ACTION_UP);	
//					}
//					
//					TemCanKey2 = 0xff;
//				}
//		}
			 panelKeysEvents(KeyCanKeyTable, (data[start+2]&0xFF), data[start + 3]);	
			}
			break;
		}

		case 0x22: { // 旋钮
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
				if ((data[start + 3] & 0xff) < 0x80) {
					CanInfos.canbusKeyNext();
				} else {
					CanInfos.canbusKeyPrev();
				}
				break;
			}
			case 3: {
				if ((data[start + 3] & 0xff) < 0x80) {
					CanInfos.canbusKeyRight();
				} else {
					CanInfos.canbusKeyLeft();
				}
				break;
			}
			default:
				break;
			}
			break;
		}
		case 0x23:{// 面板选择状态
			HandlerTaskCanbus.update(U_CAR_TYPE_SELECT, data[start+2]&0xff);
			break;
		}
		case 0x31:{// 空调信息
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
			
//			HandlerTaskCanbus.update(U_AIR_SHOW, 			B0>>7&0x01);
//			ModuleCallbackList.update(DataCanbus.MCLS, U_AIR_SHOW, new int[]{B0>>7&0x01}, null, null);
			HandlerTaskCanbus.update(U_AIR_SHOW, 		B0>>7&0x01);
			HandlerTaskCanbus.update(U_AIR_POWER_ON, 		B0>>6&0x01);
			HandlerTaskCanbus.update(U_AIR_AC_HY, 			B0>>4&0x01);
			HandlerTaskCanbus.update(U_AIR_SYNC_ON, 		B0>>2&0x01);
			HandlerTaskCanbus.update(U_AIR_AC_ON, 			B0&0x03);
			
			//空气质量
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_RIGHT, B1 >> 7 & 0x01);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEFT,  B1 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AIR_QULITY, 		B1>>5&0x01);
			HandlerTaskCanbus.update(U_AIR_CYCLE_TYPE, 		B1>>4&0x01);
			if(DataCanbus.canbusId == FinalCanbus.CAR_GM_GL6){
				if((B1>>3&0x01)==1 && (B4&0xff)==1 && (B5&0xff)>18 && (B0>>4&0x01)==1)
					HandlerTaskCanbus.update(U_AIR_CYCLE_AUTO,  	0x01);
				else
					HandlerTaskCanbus.update(U_AIR_CYCLE_AUTO,  	0x00);
			}else			
			HandlerTaskCanbus.update(U_AIR_CYCLE_AUTO,  	B1>>3&0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, 	B2>>6&0x01);
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, 	B2>>5&0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_GL6_DEFROST, 	B2>>4&0x01);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEVEL_RIGHT, B2 >> 2 & 0x03);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEVEL_LEFT, B2 >> 0 & 0x03);
			

			int wind = 0,head = 0,body = 0,foot = 0;
			switch (B4&0xff) {
			case 0x01: wind = 1; break;
			case 0x02: head = 1; break;
			case 0x03: foot = 1; break;
			case 0x05: body = 1; foot = 1; break;
			case 0x06: body = 1; break;
			case 0x0B: head = 1; break;
			case 0x0C: head = 1; foot = 1; break;
			case 0x0D: head = 1; body = 1; break;
			case 0x0E: head = 1; body = 1; foot = 1; break;
			}

			HandlerTaskCanbus.update(U_AIR_BLOW_FRONT_MODE, B4&0xff);
			HandlerTaskCanbus.update(U_AIR_BLOW_AUTO_LEFT, 	wind);
			HandlerTaskCanbus.update(U_AIR_BLOW_UP_LEFT, 	head);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_LEFT, 	body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_LEFT, 	foot);

			int value = B5&0xff;
			if (value < 0) {
				value = 0;
			} else if (value > 0x13) {
				value = 0x13;
			}
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_LEFT, 	value);

			value = B6&0xff;
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
			switch (value) {
				case 0xFE: 
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	TEMPERATURE_LOW); break;
				case 0xFF:{
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	TEMPERATURE_HIGH); break;
				}
				default: {
					HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, 	value*5);
					break;
				}
			}

			value = B8&0xff;
			int auto = 0, body1 = 0, foot1 = 0;
			switch (value) {
			case 0x01: auto = 1; break;
			case 0x02: foot1 = 1; break;
			case 0x03: foot1 = 1; body1 = 1; break;
			case 0x04: body1 = 1; break;
			}
			HandlerTaskCanbus.update(U_AIR_BLOW_AUTO_REAR, 	auto);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY_REAR, 	body1);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT_REAR, 	foot1);
			HandlerTaskCanbus.update(U_AIR_BLOW_REAR_MODE, B8&0xff);
			
			value  = B9 & 0xff;
			if (value < 0) {
				value = 0;
			} else if (value > 0x13) {
				value = 0x13;
			}
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_REAR, value);

			value = B10 & 0xff;
			switch (value) {// -1 现实none -2 显示Low -3 显示 High
			case 0xFE:
			case 0xFF: {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT_REAR, value);
				break;
			}
			default: {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT_REAR, value * 5);
				break;
			}
			}
			CanInfos.updateTempOut(data[start+13]&0xff); 
			break;
		}

		case 0x32: {
			HandlerTaskCanbus.update(U_ENGINE_SPEED , 		(((data[start + 4]<<8)&0xFF00)|(data[start + 5]&0xFF)));
			HandlerTaskCanbus.update(FinalCanbus.U_ENGINE_SPEED, 	(((data[start + 4]<<8)&0xFF00)|(data[start + 5]&0xFF)));
			
			HandlerTaskCanbus.update(U_CUR_SPEED, 	(((data[start + 6]<<8)&0xFF00)|(data[start + 7]&0xFF)));
			break;
		}

		case 0x34:{
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			int B4 = data[start+6];
			int B5 = data[start+7];
			int B6 = data[start+8];
			int B7 = data[start+9];
			int B8 = data[start+10];
			int B9 = data[start+11];
			int B10 = data[start+12];
			int B11 = data[start+13];
			int B12 = data[start+14];
			int B13 = data[start+15];
			int B14 = data[start+16];
			int B15 = data[start+17];
			int B16 = data[start+18];
			int B17 = data[start+19];
			int B18 = data[start+20];
			int B19 = data[start+21];
			int B20 = data[start+22]; 
			int B21 = data[start+23]; 
			int B22 = data[start+24]; 
			
			HandlerTaskCanbus.update(U_COSUME_MILE_UNIT, 	B22 >> 2 & 0x01);
			HandlerTaskCanbus.update(U_COSUME_FUEL_UNIT, 	B22 & 0x03);
		

			HandlerTaskCanbus.update(U_COSUME_INSTANT, 					Utils.combine(B0, B1));
			HandlerTaskCanbus.update(U_COSUME_RECHARGE_MILES, 			Utils.combine(B2, B3));
			HandlerTaskCanbus.update(U_COSUME_TOTAL_MILES, 				Utils.combine(B4, B5, B6));
		
			HandlerTaskCanbus.update(U_COSUME_AVERAGE_COST1,	Utils.combine(B7, B8));
			HandlerTaskCanbus.update(U_COSUME_TRIP_MILES1,		Utils.combine(B9, B10, B11));
			
			HandlerTaskCanbus.update(U_COSUME_AVERAGE_COST2,	Utils.combine(B12, B13));
			HandlerTaskCanbus.update(U_COSUME_TRIP_MILES2,		Utils.combine(B14, B15, B16));
			
			HandlerTaskCanbus.update(U_COSUME_AVERAGE_COST3,	Utils.combine(B17, B18));
			HandlerTaskCanbus.update(U_COSUME_TRIP_MILES3,		Utils.combine(B19, B20, B21));
			
			break;
		}
		case 0x35:{// 空调系统设置信息
			int B0 = (int)(data[start+2]&0xff);
			int B1 = (int)(data[start+3]&0xff);
			int B2 = (int)(data[start+4]&0xff);
			int B3 = (int)(data[start+5]&0xff);
			int B4 = (int)(data[start+6]&0xff);
			//Print.logHex("LG", data, start, length);
			HandlerTaskCanbus.update(U_AUTO_WIND_MODE, 					(B0>>7&0x01)<<8|(B2>>6&0x03));
			HandlerTaskCanbus.update(U_AIR_MODE_SET, 					(B0>>6&0x01)<<8|(B2>>4&0x03));
			HandlerTaskCanbus.update(U_AIR_QUALITY_SENSOR1, 			(B0>>5&0x01)<<8|(B2>>2&0x03));
			//注意在威驰威朗中是 0 --单区 1---双区 2--最后设置
			HandlerTaskCanbus.update(U_AUTO_ZOOM_TEMP_SET, 				(B0>>4&0x01)<<8|(B2&0x03));
			HandlerTaskCanbus.update(U_AUTO_SEAT_TONGFENG_SET, 			(B0>>3&0x01)<<8|(B3>>7&0x01));
			HandlerTaskCanbus.update(U_AUTO_HEAT_SEAT, 					(B0>>2&0x01)<<8|(B3>>6&0x01));
			HandlerTaskCanbus.update(U_REMOTE_START_SEAT_AUTO_TONFEGN, 	(B0>>1&0x01)<<8|(B3>>5&0x01));
			HandlerTaskCanbus.update(U_REMOTE_START_SEAT_AUTO_HOT, 		(B0&0x01)<<8|(B3>>4&0x01));
			HandlerTaskCanbus.update(U_REAR_ZOOM_TEMP_SET, 				(B1>>7&0x01)<<8|(B3>>2&0x03));
			HandlerTaskCanbus.update(U_AUTO_FRONT_GOFOG_ON, 			(B1>>6&0x01)<<8|(B3>>1&0x01));
			HandlerTaskCanbus.update(U_AUTO_REAR_GOFOG_ON, 				(B1>>5&0x01)<<8|(B3&0x01));
			HandlerTaskCanbus.update(U_REMOTE_START_AIR, 				(B1>>4&0x01)<<8|(B4>>7&0x01));
			HandlerTaskCanbus.update(U_AIR_QUALITY_SENSOR2, 			(B1>>3&0x01)<<8|(B4>>5&0x03));
			HandlerTaskCanbus.update(U_REMOTE_START_SEAT_AUTO_HEAT1,	(B1>>2&0x01)<<8|(B4>>3&0x03));
			break;
			}
		case 0x41:{ 
				 SendFunc.sendRadar(SendFunc.FL_RARA_RL,CarGetRadarDistance(data[start+2]&0xff));
				 SendFunc.sendRadar(SendFunc.FL_RARA_RML,CarGetRadarDistance(data[start+3]&0xff)); 
				 SendFunc.sendRadar(SendFunc.FL_RARA_RMR,CarGetRadarDistance(data[start+4]&0xff));
				 SendFunc.sendRadar(SendFunc.FL_RARA_RR,CarGetRadarDistance(data[start+5]&0xff));
				 SendFunc.sendRadar(SendFunc.FL_RARA_FL,CarGetRadarDistance(data[start+6]&0xff));  
				 SendFunc.sendRadar(SendFunc.FL_RARA_FML,CarGetRadarDistance(data[start+7]&0xff));
				 SendFunc.sendRadar(SendFunc.FL_RARA_FMR,CarGetRadarDistance(data[start+8]&0xff));
				 SendFunc.sendRadar(SendFunc.FL_RARA_FR,CarGetRadarDistance(data[start+9]&0xff));  
			break;
		}
		case 0x45:{// 冲撞/检测系统设定信息
			
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_BOCHE_HELP_SYSTEM_SET,			(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_CEMANG_WARN_SYSTEM_SET,			(B0>>6&0x01)<<8|(B1>>6&0x01));
			HandlerTaskCanbus.update(U_FANGZHUANG_WARN_TYPE_SET,		(B0>>5&0x01)<<8|(B1>>5&0x01));
			HandlerTaskCanbus.update(U_BOCHE_SYSTEM_SET_TUOKA,			(B0>>4&0x01)<<8|(B1>>3&0x03));
			HandlerTaskCanbus.update(U_24GHZ_RADAR_SET,					(B0>>3&0x01)<<8|(B1>>2&0x01));
			HandlerTaskCanbus.update(U_AUTO_FANGZHUANG,					(B0>>2&0x01)<<8|(B1&0x03));
			
			break;
			}
		case 0x46:{ //冲撞/检测系统设定信息2
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_CAR_STATE_NOTIFY,				(B0>>7&0x01)<<8 | B1>>7&0x01);
			HandlerTaskCanbus.update(U_SETTING_ASSIST, 					(B0>>6&0x01)<<8|(B1>>6&0x01));
			HandlerTaskCanbus.update(U_AUTO_CRUISE_CONTROL, 			(B0>>5&0x01)<<8|(B1>>5&0x01));
			break;
		}
		
		case 0x55:{//舒适性、方便性
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_DRIVER_SEAT_YIWEI,				(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_ZHUANGXIANG_GUANZHU_YIWEI,		(B0>>6&0x01)<<8|(B1>>5&0x03));
			HandlerTaskCanbus.update(U_AUTO_MIRROR_QINGXIE,				(B0>>5&0x01)<<8|(B1>>4&0x01));
			HandlerTaskCanbus.update(U_AUTO_MIRROR_FOLDING,				(B0>>4&0x01)<<8|(B1>>3&0x01));
			HandlerTaskCanbus.update(U_DRIVER_PERSONAL_SET,				(B0>>3&0x01)<<8|(B1>>2&0x01));
			HandlerTaskCanbus.update(U_AUTO_YUSHUA_SHEDING,				(B0>>2&0x01)<<8|(B1>>1&0x01));
			HandlerTaskCanbus.update(U_ZHUANGXIANG_GUANZHU_QINGXIE,		(B0>>1&0x01)<<8|(B1&0x01));
			break;
		}
		case 0x56:{ // 舒适性与方便性设定信息2
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_AUTO_YUGUA, 						(B0>>7&0x01)<<8|(B1>>7&0x01));
//			HandlerTaskCanbus.update(U_SETTING_ASSIST, 					(B0>>6&0x01)<<8|(B1>>6&0x01));
			
			break;
		}
		case 0x65:{// 门锁设定信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_PREVENT_AUTO_LUOSUO,				(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_AUTO_LUOSUO,						(B0>>6&0x01)<<8|(B1>>6&0x01));
			HandlerTaskCanbus.update(U_AUTO_UNLOCK_ZIDONG,				(B0>>5&0x01)<<8|(B1>>4&0x03));
			HandlerTaskCanbus.update(U_DELAY_TO_LOCK_SET,				(B0>>4&0x01)<<8|(B1>>3&0x01));
			HandlerTaskCanbus.update(U_AUTO_UNLOCK_SHOUDONG,			(B0>>3&0x01)<<8|(B1&0x03));
			HandlerTaskCanbus.update(U_PREVENT_ANTI_LOCK,				(B0>>2&0x01)<<8|(B1>>2&0x01));
			break;
			}

		case 0x66:{// 遥控设定信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			HandlerTaskCanbus.update(U_REMOTE_LOCK_FEEDBACK, 			(B0>>7&0x01)<<8|(B2>>6&0x03));
			HandlerTaskCanbus.update(U_REMOTE_UNLOCK_FEEDBACK, 			(B0>>6&0x01)<<8|(B2>>5&0x01));
			HandlerTaskCanbus.update(U_REMOTE_LOCK_SET, 				(B0>>5&0x01)<<8|(B2>>4&0x01));
			HandlerTaskCanbus.update(U_REMOTE_UNLOCK_AUTO_LOCK, 		(B0>>4&0x01)<<8|(B2>>3&0x01));
			HandlerTaskCanbus.update(U_RELOCK_REMOTE_OPEN_DOOR, 		(B0>>3&0x01)<<8|(B2>>2&0x01));
			HandlerTaskCanbus.update(U_DRIVER_KEY_AUTO_SHIBIE, 			(B0>>2&0x01)<<8|(B2>>1&0x01));
			HandlerTaskCanbus.update(U_YUANCHENG_START_SET, 			(B0>>1&0x01)<<8|(B2&0x01));
			HandlerTaskCanbus.update(U_NEAR_CAR_UNLOCK, 				(B0&0x01)<<8|(B3>>7&0x01));
			HandlerTaskCanbus.update(U_LEARVER_CAR_LOCK, 				(B1>>7&0x01)<<8|(B3>>4&0x03));
			HandlerTaskCanbus.update(U_FORGET_KEY, 						(B1>>6&0x01)<<8|(B3>>6&0x01));
			HandlerTaskCanbus.update(U_REMOTE_HUAYIMEN_SET, 			(B1>>5&0x01)<<8|(B3>>3&0x01));
			HandlerTaskCanbus.update(U_REMOTE_WINDOW_CONTROL, 			(B1>>4&0x01)<<8|(B3>>2&0x01));
			break;
			}

		case 0x67:{// 照明设定信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_LOOKING_FOR_LIGHT, 				(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_LUOSUO_DELAY_SET, 				(B0>>6&0x01)<<8|(B1>>5&0x03));
			HandlerTaskCanbus.update(U_LOCATION_LIGHTS, 				(B0>>4&0x01)<<8|(B1>>2&0x01));
			break;
			}
		case 0x68:{
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			int B4 = data[start+6];
			int B5 = data[start+7];
			int B6 = data[start+8];
			int B7 = data[start+9];
			int B8 = data[start+10];
//			int B9 = data[start+11];
//			int B10 = data[start+12];
			int B11 = data[start+13];
			int B12 = data[start+14];
			int B13 = data[start+15];
			int B14 = data[start+16];
			int B15 = data[start+17];
			HandlerTaskCanbus.update(U_WARN_ENABLE, 				B0&0xff);
			HandlerTaskCanbus.update(U_FRONT_LEFT_PRESS, 			((B1&0xff)<<8)|(B2&0xff));
			HandlerTaskCanbus.update(U_FRONT_RIGHT_PRESS, 			((B3&0xff)<<8)|(B4&0xff));
			HandlerTaskCanbus.update(U_REAR_LEFT_PRESS, 			((B5&0xff)<<8)|(B6&0xff));
			HandlerTaskCanbus.update(U_REAR_RIGHT_PRESS, 			((B7&0xff)<<8)|(B8&0xff));
			//左前胎压报警信息 
			HandlerTaskCanbus.update(U_FRONT_LEFT_HIGH_WARN_INFO, 	B11&0x01);
			HandlerTaskCanbus.update(U_FRONT_LEFT_LOW_WARN_INFO, 	B11>>1&0x01);
			HandlerTaskCanbus.update(U_FRONT_LEFT_WARN_INFO,		B11>>2&0x01);
			//右前胎压报警信息
			HandlerTaskCanbus.update(U_FRONT_RIGHT_HIGH_WARN_INFO,	B12&0x01);
			HandlerTaskCanbus.update(U_FRONT_RIGHT_LOW_WARN_INFO,	B12>>1&0x01);
			HandlerTaskCanbus.update(U_FRONT_RIGHT_WARN_INFO,		B12>>2&0x01);
			//左后胎压报警信息 
			HandlerTaskCanbus.update(U_REAR_LEFT_HIGH_WARN_INFO,	B13&0x01);
			HandlerTaskCanbus.update(U_REAR_LEFT_LOW_WARN_INFO,		B13>>1&0x01);
			HandlerTaskCanbus.update(U_REAR_LEFT_WARN_INFO,			B13>>2&0x01);
			//右后胎压报警信息 
			HandlerTaskCanbus.update(U_REAR_RIGHT_HIGH_WARN_INFO,	B14&0x01);
			HandlerTaskCanbus.update(U_REAR_RIGHT_LOW_WARN_INFO,	B14>>1&0x01);
			HandlerTaskCanbus.update(U_REAR_RIGHT_WARN_INFO,		B14>>2&0x01);
			break;
			}
		case 0x75:{// 仪表显示设定信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_ECO_ZHISHI_SET, 					(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_YIBIAO_INFO_SET, 				(B0>>6&0x01)<<8|(B1>>6&0x01));
			HandlerTaskCanbus.update(U_SPEED_MODE_SET, 					(B0>>5&0x01)<<8|(B1>>5&0x01));
			break;
			}

		case (int) 0x85:{// 运动模式设定信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			HandlerTaskCanbus.update(U_SPEED_MODE_FADONGJI_SET, 		(B0>>7&0x01)<<8|(B1>>7&0x01));
			HandlerTaskCanbus.update(U_SPEED_MODE_BEIGUANG_SET, 		(B0>>6&0x01)<<8|(B1>>6&0x01));
			break;
			}

		case (int) 0x90:{// 发声请求命令
			//UI部分没用到啊
//			reqeutVoice(data[sIndex] & 0xff, data[sIndex + 1] & 0xff);
//			HandlerTaskCanbus.update(U_SOUND_CMD_REQUEST, data[start+2]&0xff)
			break;
			}

		case (int) 0xB1:{// 安吉星信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
//			if(DataCanbus.sExistSync == 0)
//				break;
			updateOnStarState(B0&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_PHONE_TYPE, 	B1&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_PHONE_FLAG, 	B2&0x01);
			break;
			}

		case (int) 0xB2:{// 安吉星通话信息
			int B0 = data[start+2];
			int B1 = data[start+3];
			int B2 = data[start+4];
			int B3 = data[start+5];
			int B4 = data[start+6];
			int B5 = data[start+7];
			int B6 = data[start+8];
			int B7 = data[start+9];
			int B8 = data[start+10];
			HandlerTaskCanbus.update(U_ANJIXING_PHONE_HOUR, 	B0&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_PHONE_MINUTE, 	B1&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_PHONE_SECOND, 	B2&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_SHENGYU_TIME, 	(B3&0xff)<<8|(B4&0xff));
			HandlerTaskCanbus.update(U_ANJIXING_SHENGYU_YEAR, 	(B5&0xff)<<8|(B6&0xff));
			HandlerTaskCanbus.update(U_ANJIXING_SHENGYU_MOUNTH, B7&0xff);
			HandlerTaskCanbus.update(U_ANJIXING_SHENGYU_DAY, 	B8&0xff);
			break;
			}

		case (int) 0xB3:{// 安吉星警告信息
			int B0 = data[start+2];
			HandlerTaskCanbus.update(U_ANJIXING_WARN_TYPE, 		(B0>>7&0x01)<<8|(B0&0x7f));
			break;
			}
		case (int) 0xB4:{// 安吉星接收号码
			updateOnStarNumber(data, start+2, length - 2);
			break;
			}
		case (int) 0xBC:{// 安吉星TTS 语音播报
			HandlerTaskCanbus.update(U_ANJIXING_TTS_SHOW, 	data[start+2]&0xff);
			updateOnStarTTS(data, start+3, length - 3);
			break;
			}
		case (int) 0xBD:{// 安吉星本机号码
			updateOnStarPhoneNumber(data, start+2, length - 2);
			break;
			}
		case (int) 0xC2:{// 蓝牙配对密码
			updatePairingBtPassword(data, start+2, length - 2);
			break;
			}

		case (int) 0xC3:{// 蓝牙电话名称
			updateBtPhoneName(data, start+2, length - 2);
			break;
			}
//		case (int) 0xF0: {
//			HandlerTaskCanbus.canbusVer(new String(data, start+2, length-2));
		default:
			break;
		}
	}

	int oldkey, tempKey;
	private void panelKeysEvents(int[][] keyCanKeyTable2, int keycode, int action) {
		if (oldkey != keycode) {
			oldkey = keycode;
			int i, j = 0;
			for (i = 0; i < KeyCanKeyTable.length; i++) {
				if (oldkey == KeyCanKeyTable[i][0]) {
					j = i;
					if (oldkey != 0)
						tempKey = j;
			break;
				}
			}
			if ((oldkey != 0) && (action != 0)) {
				if (i < KeyCanKeyTable.length)
					SendFunc.sendKeyCode2Host(KeyCanKeyTable[i][1], FinalKeyCode.ACTION_DOWN);
			} else {
				if (((tempKey != 0xff) && (action & 0xff) == 0))
					SendFunc.sendKeyCode2Host(KeyCanKeyTable[tempKey][1], FinalKeyCode.ACTION_UP);
				tempKey = 0xff;
			}
		}
	}

	private void updatePairingBtPassword(final int[] password, int start, int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (password[start + i] == '\0')
				break;
			buffer.append((char)password[start + i]);
		}
		final String str = buffer.toString();
		if(!str.equals("")){
			HandlerTaskCanbus.update(U_ANJIXING_BT_PASSWORD, str, mBtPassword);
			mBtPassword = str;
		}
	}
	private void updateBtPhoneName(final int[] name, int start, int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (name[start + i] == '\0')
				break;
			buffer.append((char) name[start + i]);
		}
		final String str = buffer.toString();
		if (!str.equals("")) {
			HandlerTaskCanbus.update(U_ANJIXING_BT_NUMBER, str, mBtNumber);
			mBtNumber = str;

		}
	}
	private String mBtPassword;
	private String mBtNumber;
	
//	String byteToString(byte b){
//		String [] chas = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
//		return "" + chas[((b & 0xf0) >> 4) & 0xff] + chas[(b & 0x0f) & 0xff];
//	}
	
	public static int CarBackTrackHandle(int data0, int data1) {
		int t1;

		int temp = data0 & 0xff;
		temp <<= 8;
		temp = (temp | (data1 & 0xff)) & 0xFFFF;

		if ((data0 & 0x80) != 0)// left
		{
			temp = 0xFFFF - temp;
		}

		t1 = temp / 26; // 540 20

		if (t1 > 20)
			t1 = 20;

		if ((data0 & 0x80) != 0) {
			t1 = 20 - t1;
		} else {
			t1 = 20 + t1;
		}
		return t1;
	}
	 

	static int CarGetRadarDistance(int data) {
		int t1;
//		if (data >= 226) // 2260xe2
//		{
//			t1 = 10; // 将数值转换成 0-10
//		} else if (data >= 213) //
//		{
//			t1 = 9; // 将数值转换成 0-10
//		} else if (data >= 200) //
//		{
//			t1 = 8; // 将数值转换成 0-10
//		} else {
		if(data >= 180) t1 = 0xF;
		else
		t1 = data / 18;  //(0 ~ 10)
		return t1;
	}
	
	private void updateOnStarState(int state) {
		HandlerTaskCanbus.update(U_ANJIXING_STATE,		    state);
		onStartState = state;
		
//		switch (state) {
//		case 1:
//		case 2:
//		case 3:
//		case 4:{
//			// 记录当前通道，用于挂电话时，恢复通道
//			sChannelPre = DataHost.sAppid;
//			if (DataHost.sAppid != FinalMain.APP_ID_CAR_BTPHONE) {
//				CanInfos.switchAudio2Indicate(FinalMain.APP_ID_CAR_BTPHONE);
//			}
//			
//			break;
//		} 
//		default:
//			break;
//		}
//		switch (state) {
//		case 1:
//		case 2:
//		case 3:
//		case 4:
//			if (isWork == 0) {
//				// 记录当前通道，用于挂电话时，恢复通道
//				sChannelPre = DataHost.sAppid;
//				if (DataHost.sAppid != FinalMain.APP_ID_CAR_BTPHONE) {
//					CanInfos.switchAudio2Indicate(FinalMain.APP_ID_CAR_BTPHONE);
//				}
//				isWork = 1;
//			}
//			
////			if (DataBt.sPhoneWork == 0 &&  isWork == 0) {
////				/*StatusApp.setCarPhoneWork(state);
////				DataBt.sCmd.cut();*/
////				isWork = 1;
////				// 记录当前通道，用于挂电话时，恢复通道
////				sChannelPre = DataMain.sAppId;;
////				
////				if (DataMain.sBlackScreen != 0) {
////					HandlerMain.blackScreen(0);
////				}
////				
////				if (DataMain.sAppId == FinalMain.APP_ID_CAR_BTPHONE && (isWork != 0)) {
////					if(DataSound.sMuteSrc != 0){
////						HandlerSound.volSrcCmd(FinalSound.VOL_UNMUTE);
////					}
////				}
////				
////				// UI不切通道，所以我在这里切通道
////				if (DataMain.sAppId != FinalMain.APP_ID_CAR_BTPHONE) {
////					DataMain.sCmd.appId(FinalMain.APP_ID_CAR_BTPHONE);
////				}
////				
////				String naviPackage = DataMain.sNaviPackage;
////				
////				if (JumpPage.isAppTop(naviPackage)) {// 导航应用在最前端
////					// 显示拨号画中画界面
////					App.getInstance().sendOrderedBroadcast(new Intent("com.syu.onstar.showpip"),"com.syu.canbus");
//////					UtilsApp.sendBroadcast("com.syu.onstar.showpip", "com.syu.carinfo");
////				} else {
////					// 显示拨号界面
////					Intent intent = new Intent();
////					ComponentName componentName = new ComponentName("com.syu.canbus", "com.syu.carinfo.klc.KlcOnStarAct");
////					if(DataCanbus.sCanbusId == FinalCanbus.CAR_WC2_GM_GL6)
////						componentName = new ComponentName("com.syu.canbus", "com.syu.carinfo.klc.KlcOnStarGl6Act");
////					intent.setComponent(componentName);
////					intent.putExtra("onstar", "on");
////					intent.setFlags(intent.getFlags() &~ Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
////					try{
////						App.getInstance().startActivity(intent);
////					}catch(Exception e){
////						
////					}
////				}
////				
////			}else if(DataBt.sPhoneWork != 0 &&  isWork == 0){
////				ToolkitDev.writeMcu(0xE3,0x02, 0xBA, 0x03, 0x00);
////			}
//			
//			break;
//
//		case 0:
//			if (isWork == 1 && sChannelPre != FinalMain.APP_ID_CAR_BTPHONE) {
//				CanInfos.switchAudio2Indicate(sChannelPre);
//			}
//			isWork = 0;
//			onStartState = 0;
//			break;
//			
//		default:
//			break;
//		}
	}
	
	private void updateOnStarNumber(final int[] number, int start, int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (number[start + i] == '\0')
				break;
			else
				buffer.append((char) number[start + i]);
		}
		String num = buffer.toString();
		HandlerTaskCanbus.update(U_ANJIXING_NUMBER, num, mNumber);
		mNumber = num;
	}
	
	private String mNumber;
	private void updateOnStarPhoneNumber(final int[] number, int start, int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (number[start + i] == '\0')
				break;
			else
				buffer.append((char) number[start + i]);
		}
		String num = buffer.toString();
		HandlerTaskCanbus.update(U_ANJIXING_PHONE_NUMBER, num, mPhoneNumber);
		mPhoneNumber = num;
	}
	
	private String mPhoneNumber;
	private String Str_TTS, Str_TTS_temp;
	private String Str_TTS_Ring;
	private boolean TTS_END;

	private void updateOnStarTTS(final int[] data, int start, int len) {
		byte[] data_tts = new byte[data.length];
		for (int i = 0; i < data.length; i++) {
			data_tts[i] = (byte) data[i];
		}

		int length = len;
		for (int i = 0; i < (len - 2); i++) {
			if (data_tts[start + i] == '\0' && data_tts[start + i + 1] == '\0' && data_tts[start + i + 2] == '\0') {
				length = i + 3;
				TTS_END = true;
				break;
			}
		}
		String str = new String(data_tts, start, length, Charset.forName("UTF-8"));// UTF8转str
		if (Str_TTS == null)
			Str_TTS = str;
		else
			Str_TTS = Str_TTS + str;
		if (TTS_END) {
			if (!Str_TTS.equals("")) {
				Str_TTS_Ring = Str_TTS;
				stopVoice();
				HandlerNotRemove.getInstance().postDelayed(new Runnable() {
					@Override
					public void run() {
						exectue_tts(Str_TTS_Ring);
					}
				}, 1500);
				HandlerTaskCanbus.update(U_ANJIXING_TTS, Str_TTS, Str_TTS_temp);
				Str_TTS_temp = Str_TTS;
			}
			Str_TTS = null;
			TTS_END = false;
			
			SendFunc.send2Canbus(0xBA, 0x06, 00); 
		}
	}
	
	private void exectue_tts(String set_ring){ 
		Toast.makeText(MyApp.getInstance(), "读：" + set_ring, Toast.LENGTH_LONG).show();
		CanInfos.startSpeek(set_ring);
//		Intent intent = new Intent("com.syu.execute_tts");
//		intent.putExtra("raw_text", set_ring);
//		intent.setPackage("com.syu.voice");
//		MyApp.getInstance().startService(intent);
	}
	public void stopVoice() {
		CanInfos.endSpeek();
//		Intent intent = new Intent("com.syu.cancle_tts");
//		intent.setPackage("com.syu.voice");
//		MyApp.getInstance().startService(intent);

	} 
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
	@Override
	public void in() {
		EventNotify.NE_LANG.addNotify(mLanguage, 1);
		Ticks.addTicks1s(TypeWC2_Data.mCarDisNormal);
		 EventNotify.NE_MUTE_SRC.addNotify(TypeWC2_Data.mCarDisVolume,0);
		EventNotify.NE_RADIO_FREQS.addNotify(TypeWC2_Data.mCarDisNormal, 1);
		EventNotify.NE_RADIO_BAND.addNotify(TypeWC2_Data.mCarDisNormal, 1);
	}

	@Override
	public void out() {
		EventNotify.NE_LANG.removeNotify(mLanguage);
		Ticks.removeTicks1s(TypeWC2_Data.mCarDisNormal);
		 EventNotify.NE_MUTE_SRC.removeNotify(TypeWC2_Data.mCarDisVolume);
		EventNotify.NE_RADIO_FREQS.removeNotify(TypeWC2_Data.mCarDisNormal);
		EventNotify.NE_RADIO_BAND.removeNotify(TypeWC2_Data.mCarDisNormal);

	}

	IUiNotify mLanguage = new IUiNotify() {
		
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
		case C_SET_CAR_TYPE:
			if (ints != null && ints.length > 0)
				SendFunc.send2Canbus(0x2A, ints);
			break;

		case C_CAR_SYS_SETUP:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x3A, ints);
			break;

		case C_CAR_COLLISION:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x4A, ints);
			break;

		case C_CAR_CONVINIECE:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x5A, ints);
			break;

		case C_CAR_DOOR_LOCK:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x6A, ints);
			break;

		case C_CAR_REMOTE_SETUP:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x6B, ints);
			break;

		case C_CAR_LIGHTING_SETUP:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x6C, ints);
			break;

		case C_CAR_DIAL_SHOW_SETUP:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x7A, ints);
			break;

		case C_CAR_MOVE_MODE_SETUP:
			if (ints != null && ints.length > 1)
				// writeCarCmd(0x8A, (byte) 0x02, ints[0], ints[1]);
				SendFunc.send2Canbus(0x8A, ints);
			break;

		case C_CAR_ONSTAR_CMD:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0xBA, ints);
			break;

		case C_CAR_ONSTAR_CALL:
			if (ints != null && ints.length > 0)
				writeCarCmd(0xBB, (byte) 0x20, ints);
			break;
		case C_CAR_BT_KEY:
			if (ints != null && ints.length > 0)
				SendFunc.send2Canbus(0xCA, ints);
			break;
		case C_CAR_LANGUAGE_SETUP:
			if (ints != null && ints.length > 0) {
				int langu = ints[0] == 0 ? 0x01 : 0x02;
				SendFunc.send2Canbus(0x9A, ints);
			}
			break;
		case C_CAR_DEFAULT_SETUP:
			SendFunc.send2Canbus(0x9A, ints);
			break;
		// 空调控制命令
		case C_AIR_CONTROL_CMD:
			if (ints != null && ints.length > 1)
				SendFunc.send2Canbus(0x3B, ints);
			break;
		default:
			break;
		}
	};

	private void writeCarCmd(int cmdid, byte size, int... params) {
		if (((int) size & 0xff) < params.length)
			throw new IllegalArgumentException("params data length > size!");
		int[] cmds = new int[((int) size & 0xff) + 3];
		cmds[0] = (byte) 0xe3;
		cmds[1] = (byte) size;
		cmds[2] = (byte) cmdid;
		int start = 3;
		final int len = params.length;
		for (int i = 0; i < len; i++) {
			cmds[start + i] = (byte) (params[i] & 0xff);
		}

		for (int i = start + len; i < cmds.length; i++)
			cmds[i] = 0x00;
		
//		ToolkitDev.writeMcu(cmds);
		int[] candata = new int[cmds.length - 3];
		System.arraycopy(cmds, 3, candata, 0, candata.length);
		SendFunc.send2Canbus(cmdid, candata);
	}
	
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if(code >= 0 && code < U_CNT_MAX) {
			switch (code) {
			case U_ANJIXING_PHONE_NUMBER:
				HandlerTaskCanbus.update(code, mPhoneNumber, "");
				break;
			case U_ANJIXING_TTS:
				HandlerTaskCanbus.update(code, Str_TTS, "");
				break;
			case U_ANJIXING_NUMBER:
				HandlerTaskCanbus.update(code, mNumber, "");
				break;
			case U_ANJIXING_BT_PASSWORD:
				HandlerTaskCanbus.update(code, mBtPassword, "");
				break;
			case U_ANJIXING_BT_NUMBER: 
				HandlerTaskCanbus.update(code, mBtNumber, "");
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
