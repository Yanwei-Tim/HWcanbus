package com.ex.hiworld.aidl.car;

import android.os.RemoteException;
import android.text.format.DateFormat;

import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.EventNotify;
import com.ex.hiworld.server.canbus.HandlerBackCarTrack;
import com.ex.hiworld.server.canbus.HandlerBtPhone;
import com.ex.hiworld.server.canbus.HandlerKeyCode;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by APP03 on 2018/6/9.
 */

public class TaskCar_Golf extends BaseCar {

    public static final int U_0_BY_START                                = 0;
    public static final int U_1_BY_START                                = 1;
    public static final int U_2_BY_START                                = 2;
    public static final int U_3_BY_START                                = 3;
    public static final int U_4_BY_START                                = 4;
    public static final int U_5_BY_LONG_TERM                                = 5;
    public static final int U_6_BY_LONG_TERM                                = 6;
    public static final int U_7_BY_LONG_TERM                                = 7;
    public static final int U_8_BY_LONG_TERM                                = 8;
    public static final int U_9_BY_LONG_TERM                                = 9;
    public static final int U_10_BY_REFUELLING                                = 10;
    public static final int U_11_BY_REFUELLING                                = 11;
    public static final int U_12_BY_REFUELLING                                = 12;
    public static final int U_13_BY_REFUELLING                                = 13;
    public static final int U_14_BY_REFUELLING                                = 14;
    public static final int U_15                                = 15;
    public static final int U_OIL_MARK_MAX                                = 16;
    public static final int U_OIL_PROGRESS                                = 17;
    public static final int U_OIL_UNIT                                = 18;
    public static final int U_ACTIVATEAUTOMATICLLY                                = 19;
    public static final int U_FRONTVOL                                = 20;
    public static final int U_FRONTTONE                                = 21;
    public static final int U_REARVOL                                = 22;
    public static final int U_REARTONE                                = 23;
    public static final int U_PARK                                = 24;
    public static final int U_RADARMUTE                                = 25;
    public static final int U_TPMS                                = 26;
    public static final int U_SPEEDWARNNING                                = 27;
    public static final int U_WARNNINGAT                                = 28;
    public static final int U_DRIVERPROGRAM                                = 29;
    public static final int U_DRIVERLASTDISTANCESELECTED                                = 30;
    public static final int U_DRIVERACC                                = 31;
    public static final int U_DRIVERACTIVE                                = 32;
    public static final int U_DRIVERADVANCEWARNNINGSETTING                                = 33;
    public static final int U_DRIVERDISPLAYDISTANCEWARNNING                                = 34;
    public static final int U_DRIVERLANEASSIST                                = 35;
    public static final int U_DRIVERTRAFFICESIGNRECOGNITION                                = 36;
    public static final int U_DRIVERALERTSYSTEM                                = 37;
    public static final int U_DRIVERPROACTIVEOCCUPATION                                = 38;
    public static final int U_OPENCLOSECONVENIENCE                                = 39;
    public static final int U_OPENCLOSEDOORUNLOCK                                = 40;
    public static final int U_OPENCLOSEAUTOLOCK                                = 41;
    public static final int U_LIGHTSWITCHONTIME                                = 42;
    public static final int U_LIGHTAUTOMATICHEADLIGHTRAIN                                = 43;
    public static final int U_LIGHTLANECHANGEFLASH                                = 44;
    public static final int U_LIGHTTRAVELMODE                                = 45;
    public static final int U_LIGHTINSTRUMENT                                = 46;
    public static final int U_LIGHTDOORBACKGROUND                                = 47;
    public static final int U_LIGHTFOOTWELL                                = 48;
    public static final int U_LIGHTCOMINGHOME                                = 49;
    public static final int U_LIGHTLEAVINGHOME                                = 50;
    public static final int U_MIRRORWIPERSSYNADJUST                                = 51;
    public static final int U_MIRRORWIPERSLOWWHILEREVESING                                = 52;
    public static final int U_MORRORWIPERSFOLDINWHENPARKED                                = 53;
    public static final int U_MORRORWIPERSAUTOWIPINGINRAIN                                = 54;
    public static final int U_MIRRORWIPERSREARWINWIPINGINREV                                = 55;
    public static final int U_MUTILCURRENTCONSUMPTION                                = 56;
    public static final int U_MUTILAVERAGECONSUMPTION                                = 57;
    public static final int U_MUTILCONVENIENCECONSUMER                                = 58;
    public static final int U_MUTILECOTIPS                                = 59;
    public static final int U_MUTILTRAVERLLINGTIME                                = 60;
    public static final int U_MUTILDISTANCETRAVELLED                                = 61;
    public static final int U_MUTILAVERAGESPEED                                = 62;
    public static final int U_MUTIILDIGITSPEEDDISPLAY                                = 63;
    public static final int U_MUTILSPEEDWARNNING                                = 64;
    public static final int U_ESC_SYSTEM                                = 65;
    public static final int U_UNITTEMPERATURE                                = 66;
    public static final int U_UNITVOLUME                                = 67;
    public static final int U_UNITCONSUMPTION                                = 68;
    public static final int U_UNITPRESSURE                                = 69;
    public static final int U_CARDAY                                = 70;
    public static final int U_CARDISTANCE                                = 71;
    public static final int U_OILDAY                                = 72;
    public static final int U_OILDISTANCE                                = 73;
    public static final int U_WARNNING_VEHICLE                                = 74; // new int[]{index, value}
    public static final int U_WARNNING_START_STOP                                = 75; // new int[]{index, value}
    public static final int U_WARNNING_CONV_CONSUMER                                = 76; // new int[]{index, value}
    public static final int U_TEMPERATUREUNIT                                = 77;
    public static final int U_MUTILOILTEMP                                = 79;
    public static final int U_DAYTIME_RUNNING_LIGHTS                                = 78;
    public static final int U_UNITMILEAGE                                = 80;
    public static final int U_UNITSPEED                                = 81;
    public static final int U_IDCARNUM                                = 82;
    public static final int U_FEEDBACK_LAMP_ENABLED                                = 83;
    public static final int U_AUTO_AC_ENABLED                                = 84;
    public static final int U_SYSTEM_KEY_WITH_EKEY_ENABLED                                = 85;
    public static final int U_CARLOG_SET                                = 86;
    /**
     * 空调
     */
    public static final int U_AIR_BEGIN                                = 87;
    public static final int U_AIR_POWER                                = U_AIR_BEGIN + 0;
    public static final int U_AIR_AC_MAX                                = U_AIR_BEGIN + 1;
    public static final int U_AIR_AUTO                                = U_AIR_BEGIN + 2;
    public static final int U_AIR_SYNC                                = U_AIR_BEGIN + 3;
    public static final int U_AIR_AC                                = U_AIR_BEGIN + 4;
    public static final int U_AIR_SEAT_HEAT_LEFT                                = U_AIR_BEGIN + 5;
    public static final int U_AIR_SEAT_HEAT_RIGHT                                = U_AIR_BEGIN + 6;
    public static final int U_AIR_BLOW_AUTO                                = U_AIR_BEGIN + 7;
    public static final int U_AIR_BLOW_BODY                                = U_AIR_BEGIN + 8;
    public static final int U_AIR_BLOW_FOOT                                = U_AIR_BEGIN + 9;
    public static final int U_AIR_WIND_LEVEL                                = U_AIR_BEGIN + 10;
    public static final int U_AIR_TEMP_LEFT                                = U_AIR_BEGIN + 11;
    public static final int U_AIR_TEMP_RIGHT                                = U_AIR_BEGIN + 12;
    public static final int U_AIR_DUAL                                = U_AIR_BEGIN + 13;
    public static final int U_AIR_STEERHEAT                                = U_AIR_BEGIN + 14;
    public static final int U_AIR_AQS                                = U_AIR_BEGIN + 15;
    public static final int U_AIR_TEMPUNIT                                = U_AIR_BEGIN + 16;
    public static final int U_AIR_JUMPCON                                = U_AIR_BEGIN + 17;
    public static final int U_AIR                                = U_AIR_BEGIN + 18;
    public static final int U_AIR_MAX                                = U_AIR_BEGIN + 19;
    public static final int U_DISPLAY_ENABLE_0                                = U_AIR_BEGIN + 20;
    public static final int U_AIR_END                                = U_AIR_BEGIN + 21;

    /**
     * 车门
     */
    public static final int U_DOOR_BEGIN                                = U_AIR_END + 1;
    public static final int U_DOOR_ENGINE                                = U_DOOR_BEGIN + 0;
    public static final int U_DOOR_FL                                = U_DOOR_BEGIN + 1;
    public static final int U_DOOR_FR                                = U_DOOR_BEGIN + 2;
    public static final int U_DOOR_RL                                = U_DOOR_BEGIN + 3;
    public static final int U_DOOR_RR                                = U_DOOR_BEGIN + 4;
    public static final int U_DOOR_BACK                                = U_DOOR_BEGIN + 5;
    public static final int U_DOOR_END                                = U_DOOR_BEGIN + 6;

    public static final int U_TIRE_PRESSURE_FL                                = U_DOOR_END + 1;
    public static final int U_TIRE_PRESSURE_FR                                = U_DOOR_END + 2;
    public static final int U_TIRE_PRESSURE_RL                                = U_DOOR_END + 3;
    public static final int U_TIRE_PRESSURE_RR                                = U_DOOR_END + 4;

    //长远通主动空调
    public static final int U_AIR_BLOW_UP                                = U_DOOR_END + 5;
    public static final int U_AIR_WIND_MODE                                = U_DOOR_END + 6;
    public static final int U_AIR_CYCLE                                = U_DOOR_END + 7;
    public static final int U_AIR_REAR                                = U_DOOR_END + 8;
    public static final int U_AIR_FRONT_DEFROST                                = U_DOOR_END + 9;
    public static final int U_CUR_SPEED                                = U_DOOR_END + 10;
    public static final int U_ENGINE_SPEED                                = U_DOOR_END + 11;
//	public static final int U_CNT_MAX						= U_ENGINE_SPEED+1;

    public static final int U_SETTING_86D17                                = U_DOOR_END + 12; // 舒适
    public static final int U_SETTING_86D16                                = U_DOOR_END + 13; // 标准
    public static final int U_SETTING_86D15                                = U_DOOR_END + 14; // 运动
    public static final int U_SETTING_86D14                                = U_DOOR_END + 15; // 经济
    public static final int U_SETTING_86D13                                = U_DOOR_END + 16; // 个性化
    public static final int U_SETTING_86D26                                = U_DOOR_END + 17; // DCC
    public static final int U_SETTING_86D24                                = U_DOOR_END + 18; // 动态随车大灯
    public static final int U_SETTING_86D22                                = U_DOOR_END + 19; // 驱动装置
    public static final int U_SETTING_86D20                                = U_DOOR_END + 20; // 自适应巡航 acc
    public static final int U_SETTING_86D37                                = U_DOOR_END + 21; // 空调
    public static final int U_SETTING_86D36                                = U_DOOR_END + 22; // 方向盘 转向系统
    public static final int U_JUMP_CARINFO                                = U_DOOR_END + 23;
    public static final int U_LIGHT_COLOR                                = U_DOOR_END + 24;
    public static final int U_LIGHT_FAN                                = U_DOOR_END + 25;
    public static final int U_LIGHT_RIGHT                                = U_DOOR_END + 26;
    public static final int U_LIGHT_ASSIST                                = U_DOOR_END + 27;
    public static final int U_LIGHT_BEND                                = U_DOOR_END + 28;
    public static final int U_LIGHT_ALL                                = U_DOOR_END + 29;
    public static final int U_DOOR_MIND_PAIR                                = U_DOOR_END + 30;
    public static final int U_DOOR_SENSE_LAN                                = U_DOOR_END + 31;
    public static final int U_AIR_AUTO_SET                                = U_DOOR_END + 32;
    public static final int U_AIR_FRONT_SET                                = U_DOOR_END + 33;
    public static final int U_AIR_AUTOCYCLE_SET                                = U_DOOR_END + 34;
    public static final int U_AIR_REAR2                                = U_DOOR_END + 35;
    public static final int U_AIR_REAR_LOCK                                = U_DOOR_END + 36;
    public static final int U_AIR_FRONT                                = U_DOOR_END + 37;
    public static final int U_AIR_BLOW_WIN                                = U_DOOR_END + 38;
    public static final int U_AIR_BACK_TEMP                                = U_DOOR_END + 39;
    public static final int U_AIR_AUTO_WIND                                = U_DOOR_END + 40;
    public static final int U_AIR_UNIT                                = U_DOOR_END + 41;
    public static final int U_AIR_CYCLE2                                = U_DOOR_END + 42;
    public static final int U_AIR_SHOW                                = U_DOOR_END + 43;
    public static final int U_BACK_BRIGHT                                = U_DOOR_END + 44;
    public static final int U_BACK_CONTRAST                                = U_DOOR_END + 45;
    public static final int U_BACK_SATURATION                                = U_DOOR_END + 46;
    public static final int U_ACTIVATEMATICLLY                                = U_DOOR_END + 47;  //迈腾 驻车与调车  激活
    public static final int U_CAR_KEY_ACTIVATED                                = U_DOOR_END + 48;  //迈腾 0xB0 汽车钥匙已激活

    public static final int U_SET_RAIN_SENSOR                                = U_CAR_KEY_ACTIVATED + 1; // 雨量传感器
    public static final int U_SET_LEFT_DRIVE                                = U_SET_RAIN_SENSOR + 2; // 靠左行驶匹配
    public static final int U_SET_SMART_BIGLIGHT                                = U_SET_RAIN_SENSOR + 3; // 智能大灯
    public static final int U_SET_HOMELIGHT                                = U_SET_RAIN_SENSOR + 4; // 回家照明功能
    public static final int U_SET_LEAVEHOMELIGHT                                = U_SET_RAIN_SENSOR + 5; // 离家照明功能

    public static final int U_SET_FOOTLIGHT_VALUE                                = U_SET_RAIN_SENSOR + 6; // 脚部空间照明亮度值
    public static final int U_SET_INSIDECARLIGHT_VALUE                                = U_SET_RAIN_SENSOR + 7; // 车内照明亮度值
    public static final int U_SET_FRONT_WINDOW                                = U_SET_RAIN_SENSOR + 8; // 前部车窗
    public static final int U_SET_REAR_WINDOW                                = U_SET_RAIN_SENSOR + 9; // 后部车窗
    public static final int U_SET_SKY_WINDOW                                = U_SET_RAIN_SENSOR + 10; // 滑动天窗
    public static final int U_SET_REVERSE_REARMIRROR                                = U_SET_RAIN_SENSOR + 11; // 翻转后视镜
    public static final int U_SET_ONLYUNLOCK_LUGGAGE                                = U_SET_RAIN_SENSOR + 12; // 单为行李箱解锁

    public static final int U_SET_UNIT_DISTANCE                                = U_SET_RAIN_SENSOR + 13; // 距离
    public static final int U_SET_UNIT_SPEED                                = U_SET_RAIN_SENSOR + 14; // 车速
    public static final int U_SET_UNIT_TEMP                                = U_SET_RAIN_SENSOR + 15; // 温度
    public static final int U_SET_UNIT_CAPACITY                                = U_SET_RAIN_SENSOR + 16; // 容量
    public static final int U_SET_UNIT_ENERGEY_COMSUME                                = U_SET_RAIN_SENSOR + 17; // 能量消耗
    public static final int U_SET_UNIT_TIRE_PRESS                                = U_SET_RAIN_SENSOR + 18; // 轮胎压力

    public static final int U_SET_OIL_VOLUME                                = U_SET_RAIN_SENSOR + 19; // 机油量
    public static final int U_SET_MILES_UNIT                                = U_SET_RAIN_SENSOR + 20; // 里程单位
    public static final int U_SET_MAINTEN_CHECK_MILES                                = U_SET_RAIN_SENSOR + 21; // 保养检查于 公里数
    public static final int U_SET_MAINTEN_CHECK_DATE                                = U_SET_RAIN_SENSOR + 22; // 保养检查于 时间
    public static final int U_SET_MAINTEN_CHECK_PERIOD_MILES                                = U_SET_RAIN_SENSOR + 23; // 保养周期于 里程
    public static final int U_SET_MAINTEN_CHECK_PERIOD_DATE                                = U_SET_RAIN_SENSOR + 24; // 保养周期于 时间
    public static final int U_SET_RAINBRUSH_AT_MAITEN                                = U_SET_RAIN_SENSOR + 25; // 雨刷器在维护位置

    public static final int U_AIR_CLEAR_AIR                                = U_SET_RAIN_SENSOR + 26;  // 途观L空调

    //科迪亚克新加
    private static final int U_AIR_SEAT_BLOW_RIGHT                                = U_SET_RAIN_SENSOR + 27;
    private static final int U_AIR_SEAT_BLOW_LEFT                                = U_SET_RAIN_SENSOR + 28;
    private static final int U_AIR_STEER_SEAT_SYNC                                = U_SET_RAIN_SENSOR + 29;    // 座椅方向盘同步
    private static final int U_DIRIVEOUT_PARK_ASSIST                                = U_SET_RAIN_SENSOR + 30; // 使出车位辅助
    private static final int U_BLIND_DETECT                                = U_SET_RAIN_SENSOR + 31;
    private static final int U_SETTING_86D12                                = U_SET_RAIN_SENSOR + 32; // 雪地
    private static final int U_SETTING_86D11                                = U_SET_RAIN_SENSOR + 33; // 越野
    private static final int U_SETTING_86D10                                = U_SET_RAIN_SENSOR + 34; // 越野个性化
    public static final int U_CARINFO_OFF_ROAD_KEY                                = U_SET_RAIN_SENSOR + 35;
    public static final int U_CARINFO_OFF_ROAD_ICON                                = U_SET_RAIN_SENSOR + 36;
    private static final int U_TIRE_DISPLAY                                = U_SET_RAIN_SENSOR + 37;
    private static final int U_TIRE_UNIT                                = U_SET_RAIN_SENSOR + 38;
    private static final int U_DIRECT_TIRE_DETECT                                = U_SET_RAIN_SENSOR + 39;
    private static final int U_AIR_REAR_BLOW_FOOT                                = U_SET_RAIN_SENSOR + 40;
    private static final int U_AIR_REAR_WIND_LEVEL                                = U_SET_RAIN_SENSOR + 41;
    private static final int U_AIR_REAR_BLOW_BODY                                = U_SET_RAIN_SENSOR + 42;
    private static final int U_AIR_REAR_AUTO                                = U_SET_RAIN_SENSOR + 43;

    private static final int U_SET_DRIVEMODE                                = U_SET_RAIN_SENSOR + 44;  //驾驶模式
    private static final int U_SET_OFFROAD_ENGINE                                = U_SET_RAIN_SENSOR + 45;  //驱动装置 - 越野
    private static final int U_SET_OFFROAD_STEER                                = U_SET_RAIN_SENSOR + 46;  //转向 - 越野
    private static final int U_SET_OFFROAD_4ENGINE                                = U_SET_RAIN_SENSOR + 47;  //四轮驱动 - 越野
    private static final int U_SET_OFFROAD_AIRCONDITION                                = U_SET_RAIN_SENSOR + 48;  //空调 - 越野
    private static final int U_SET_OFFROAD_DOWNHILL_ASSIST                                = U_SET_RAIN_SENSOR + 49;  // 下坡辅助 - 越野
    private static final int U_SET_OFFROAD_RAMP_START                                = U_SET_RAIN_SENSOR + 50;  // 坡道起步 - 越野
    private static final int U_SET_OFFROAD_PARKING_ASSIST                           = U_SET_RAIN_SENSOR + 51;  // 驻车辅助 - 越野
    private static final int U_SET_OFFROAD_BEND                                     = U_SET_RAIN_SENSOR + 52;  // 动态大灯随动-越野
    private static final int U_SET_OFFROAD_ACC                                      = U_SET_RAIN_SENSOR + 53;  // 自适应巡航ACC-越野
    private static final int U_INFO_OIL_TEMP                                        = U_SET_RAIN_SENSOR + 54; // 油温
    private static final int U_INFO_WATER_TEMP                                      = U_SET_RAIN_SENSOR + 55; // 水温

    private static final int U_AIR_REAR_SEATHEAT_LEFT                                = U_SET_RAIN_SENSOR + 56;
    private static final int U_AIR_REAR_SEATHEAT_RIGHT                               = U_SET_RAIN_SENSOR + 57;
    private static final int U_AIR_REAR_BLOW_MODE                                   = U_SET_RAIN_SENSOR + 58;

    public static final int U_SET_BACKCAR_COLOR                                   = U_SET_RAIN_SENSOR + 59;    // 原车摄像头色彩
    public static final int U_SET_BACKCAR_BRIGTHNESS                              = U_SET_RAIN_SENSOR + 60;    // 原车摄像头亮度
    public static final int U_SET_BACKCAR_CONTRAST                                = U_SET_RAIN_SENSOR + 61;    // 原车摄像头对比度
    public static final int U_AIR_CLEAR_AIR_PROGRESS                              = U_SET_RAIN_SENSOR + 62;  // 途昂空调
    public static final int U_SETTING_86D13_ENABLE                                = U_SET_RAIN_SENSOR + 63;  // 威驰个性化使能标志位(独立)

    public static final int U_SET_PARKING_BRAKE                                = U_SET_RAIN_SENSOR + 70;    // 泊车制动
    public static final int U_SET_LIGHT_DISTANCE                               = U_SET_RAIN_SENSOR + 71;    // 大灯照明距离
    public static final int U_SET_REMAINING_OIL                                = U_SET_RAIN_SENSOR + 72;    // 剩余油量

    public static final int U_TIRE_ALARM                                         = 250;
    public static final int U_TIRE_PRESSURE_UNIT                                 = U_TIRE_ALARM + 1;
    public static final int U_TIRE_PRESSURE_CK_FL                                = U_TIRE_ALARM + 2;
    public static final int U_TIRE_PRESSURE_CK_FR                                = U_TIRE_ALARM + 3;
    public static final int U_TIRE_PRESSURE_CK_RL                                = U_TIRE_ALARM + 4;
    public static final int U_TIRE_PRESSURE_CK_RR                                = U_TIRE_ALARM + 5;
    public static final int U_DRIVE_MODE_OD                                      = U_TIRE_ALARM + 6;
    public static final int U_CHANGE_AIDS_OD                                     = U_TIRE_ALARM + 7;//变道辅助
    public static final int U_CHANGE_BRIGHT_OD                                   = U_TIRE_ALARM + 8;//变道亮度
    public static final int U_LIGHT_STATE                                        = 259;
    public static final int U_HANDPART_STATE                                     = 260;
    public static final int U_LIFEBELT_STATE                                     = 261;
    public static final int U_LIGHT_TURN_LEFT                                    = 262;
    public static final int U_LIGHT_TURN_RIGHT                                   = 263;
    public static final int U_DASHBOARD_SET                                      = 264;
    public static final int U_DASHBOARD_SHOW                                     = 265;
    public static final int U_OILBOX_SET                                         = 266;

    public static final int U_CARSET_47D137                                 = U_OILBOX_SET + 1;
    public static final int U_AIR_OUT_TEMP                                  = U_OILBOX_SET + 2;
    public static final int U_BATTERY_VOLTAGE                               = U_OILBOX_SET + 3;
    public static final int U_WASH_STATE                               		= U_OILBOX_SET + 4;
    public static final int U_CNT_MAX 										= U_OILBOX_SET + 5;


//    public static final int C_SYSTEM_KEY_WITH_EKEY_ENABLED 		= 0; // 带电子钥匙的进入系统钥匙 new int[]{0/1}
//    public static final int C_FEEDBACK_LAMP_ENABLED 			= 1; // 锁止/解锁反馈指示灯 new int[]{0/1}
//    public static final int C_AUTO_AC_ENABLED 					= 2; // 自动A/C模式 new int[]{0/1}
//    public static final int C_VALID_VENTILATION_ENABLED 		= 3; // 有效通风模式 new int[]{0/1}
//    public static final int C_SENSITIVITY_OPEN_BIGLAMP			= 4; // new int[]{0~4} 打开头灯灵敏度
//    public static final int C_UPDATE_LAST_N_MINUTE_OIL_EXPEND 	= 5; // 更新最近N分钟的油耗
//    public static final int C_CLEAR_LAST_N_MINUTE_OIL_EXPEND 	= 6; // 清除最近N分钟的油耗
//    public static final int C_UPDATE_TRIP_OIL_EXPEND 			= 7; // 更新最近旅程油耗
//    public static final int C_CLEAR_TRIP_OIL_EXPEND				= 8; // 清除最近旅程油耗
//    public static final int C_AUTOLOCK_BY_SPEED					= 9; // 车速感应车门自动锁定
//    public static final int C_AUTOLOCK_BY_SHIFT_FROM_P			= 10;// 换档联动车门自动锁定
//    public static final int C_AUTOLOCK_BY_SHIFT_TO_P			= 11;// P档联动解锁
//    public static final int C_REMOTE_2PRESS_UNLOCK				= 12;// 操作按钮两次时解锁
//    public static final int C_DAYTIME_RUNNING_LIGHTS			= 13;// 日间行车灯
//    public static final int C_LOCK_UNLOCK_FEEDBACK_TONE			= 14;// 锁/解锁反馈的声音大小?
//    public static final int C_CLOSE_INSIDELAMP_TIME				= 15;// 自动关闭车内灯光时间
//    public static final int C_CLOSE_BIGLAMP_TIME				= 16;// 自动关闭大灯时间
//    public static final int C_2TIMES_KEY_UNLOCK					= 17;// new int[]{0/1}, 操作钥匙两次解锁
//    public static final int C_UNLOCK_BY_DRIVERS_DOOR_OPEN		= 18;// new int[]{0/1}, 驾驶席开门联动解锁
//    public static final int C_UNLOCK_BY_SMART_DOOR				= 19;// new int[]{DOOR_DRIVER or DOOR_ALL} 智能车门解锁
//    public static final int C_SMARTLOCK_AND_ONE_KEY_BOOT		= 20; // new int[]{0/1}	智能车锁和一键启动
//    public static final int C_LOCK_UNLOCK_LAMP_FLASH			= 21; // new int[]{0/1} 上锁,解锁,紧急闪烁灯响应
//    public static final int C_AIR_BY_AUTO_KEY					= 22; // new int[]{0/1} 空调与AUTO键联动
//    public static final int C_IN_OUT_AIR_BY_AUTO_KEY			= 23; // new int[]{0/1} 内外气切换与AUTO键联动
//    public static final int C_SHOW_RADAR						= 24; // new int[]{0/1} 显示雷达
//    public static final int C_RADAR_VOL							= 25; // new int[]{level} 雷达音量警报等级
//    public static final int C_RADAR_DISTANCE					= 26; // new int[]{RADAR_DISTANCE_XXX} 距离(前后雷达显示方式)
//    public static final int C_OPEN_BIGLAMP_BY_WIPER				= 27; // new int[]{0/1} 启动雨刷时自动启动大灯
//    public static final int C_PARK_BESIDE_ROAD					= 28; // new int[]{0/1} 路边驻车
//    public static final int C_PARK_IN_CARPORT					= 29; // new int[]{0/1} 入库驻车
//    public static final int C_RADAR_MUTE						= 30; // new int[]{0/1}	雷达静音
//    public static final int C_ALL_SETTINGS						= 31; // new int[]{0/1} 所有设置
//    public static final int C_DRIVER_ASSISTANCE					= 32; // new int[]{0/1} 驾驶员辅佐系统
//    public static final int C_PARKING_AND_MANOEURVRING			= 33; // new int[]{0/1} 驻车和调车
//    public static final int C_LIGHT								= 34; // new int[]{0/1} 车灯
//    public static final int C_MIRRORS_AND_WIPERS				= 35; // new int[]{0/1} 后视镜和刮水器
//    public static final int C_OPENNING_AND_CLOSING				= 36; // new int[]{0/1} 打开和关闭
//    public static final int C_MULTIFUNCTION_DISPLAY				= 37; // new int[]{0/1} 多功能显示
//    public static final int C_AIR								= 38; // new int[]{0/1} 空调开关
//    public static final int C_ACTIVATE_AUTOMATICLLY				= 39; // new int[]{0/1} 驻车/调车自动激活
//    public static final int C_FRONT_VOL							= 40; // new int[]{value} 前部音量
//    public static final int C_FRONT_TONE						= 41; // new int[]{value} 前部音调
//    public static final int C_REAR_VOL							= 42; // new int[]{value} 后部音量
//    public static final int C_REAR_TONE							= 43; // new int[]{value} 后部音调
//    public static final int C_PARK								= 44; // new int[]{value} 泊车方式
//    public static final int C_TPMS								= 45; // null 轮胎压力监控显示
//    public static final int C_SPEED_WARRNING					= 46; // new int[]{0/1} 车速警报
//    public static final int C_WARNNING_AT						= 47; // new int[]{value}警报值
//    public static final int C_DRIVER_FRONT_ASSIST				= 48; // new int[]{0/1} 前部辅佐系统
//    public static final int C_DRIVER_ADVANCE_WARNNING_SETTING	= 49; // new int[]{0/1} 预警
//    public static final int C_DRIVER_DISPLAY_DISTANCE_WARNNING	= 50; // new int[]{0/1} 显示距离报警
//    public static final int C_DRIVER_LANE_ASSIST				= 51; // new int[]{0/1} 自适应车道导向
//    public static final int C_DRIVER_TRAFFICE_SIGN_RECOGNITION	= 52; // new int[]{0/1}
//    public static final int C_DRIVER_ALERT_SYSTEM				= 53; // new int[]{0/1}
//    public static final int C_DRIVER_PROACTIVE_OCCUPATION		= 54; // new int[]{0/1}
//    public static final int C_DRIVER_ACC						= 55; // new int[]{value} 车距
//    public static final int C_DRIVER_PROGRAM					= 56; // new int[]{value} 行使程序
//    public static final int C_DRIVER_LAST_DISTANCE_SELECTED		= 57; // new int[]{0/1} 上次选择的车距
//    public static final int C_LIGHT_SWITCH_ON_TIME				= 58; // new int[]{0/1}	接通时间
//    public static final int C_LIGHT_AUTOMATIC_HEAD_LIGHT_RAIN	= 59; // new int[]{0/1}	自动行车等雨天
//    public static final int C_LIGHT_LANE_CHANGE_FLASH			= 60; // new int[]{0/1}	变道转向灯
//    public static final int C_LIGHT_TRAVEL_MODE					= 61; // new int[]{0/1} 旅行模式
//    public static final int C_LIGHT_INSTRUMENT					= 62; // new int[]{0/1}	仪表/开关已照明
//    public static final int C_LIGHT_DOOR_BACKGROUND				= 63; // new int[]{0/1} 车门环境照明灯
//    public static final int C_LIGHT_FOOTWELL					= 64; // new int[]{0/1} 脚步空间照明灯
//    public static final int C_LIGHT_COMING_HOME					= 65; // new int[]{0/1}	回家模式
//    public static final int C_LIGHT_LEAVING_HOME				= 66; // new int[]{0/1} 离家模式
//    public static final int C_MIRROR_WIPERS_SYN_ADJUST			= 67; // new int[]{0/1} 同步调节
//    public static final int C_MIRROR_WIPERS_LOW_WHILE_REVESING	= 68; // new int[]{0/1} 倒车档时降低
//    public static final int C_MIRROR_WIPERS_FOLDIN_WHEN_PARKED	= 69; // new int[]{0/1} 驻车时内折
//    public static final int C_MIRROR_WIPERS_AUTO_WIPING_IN_RAIN	= 70; // new int[]{0/1} 雨天自动刮水
//    public static final int C_MIRROR_WIPERS_REAR_WIN_WIPING_IN_RESERVE = 71; // new int[]{0/1} 倒车档时后窗玻璃刮水
//    public static final int C_OPEN_CLOSE_CONVENIENCE			= 72; // new int[]{value} 便捷开启
//    public static final int C_OPEN_CLOSE_DOOR_UNLOCK			= 73; // new int[]{value} 车门解锁
//    public static final int C_OPEN_CLOSE_AUTO_LOCK				= 74; // new int[]{0/1} 自动锁止
//    public static final int C_MUTIL_CURRENT_CONSUMPTION			= 75; // new int[]{0/1} 当前油耗
//    public static final int C_MUTIL_AVERAGE_CONSUMPTION			= 76; // new int[]{0/1} 平均油耗
//    public static final int C_MUTIL_CONVENIENCE_CONSUMER		= 77; // new int[]{0/1} 舒适性用电器
//    public static final int C_MUTIL_ECO_TIPS					= 78; // new int[]{0/1} 经济运行提示
//    public static final int C_MUTIL_TRAVELLING_TIME				= 79; // new int[]{0/1} 行使时间
//    public static final int C_MUTIL_DISTANCE_TRAVELLED			= 80; // new int[]{0/1} 行使里程
//    public static final int C_MUTIL_AVERAGE_SPEED				= 81; // new int[]{0/1} 平均速度
//    public static final int C_MUTIL_DIGIT_SPEED_DISPLAY			= 82; // new int[]{0/1} 数字式车速显示
//    public static final int C_MUTIL_SPEED_WARNNING				= 83; // new int[]{0/1} 车速警报
//    public static final int C_MUTIL_RESET_SINCE_START			= 84; // 恢复"自启动以后"的行使数据
//    public static final int C_MUTIL_RESET_LONG_TERM				= 85; // 恢复"长时间"的行使数据
//    public static final int C_ESC_SYSTEM						= 86; // new int[]{0/1} 运动模式
//    public static final int C_UNIT_TEMPERATURE					= 87; // new int[]{value} 温度单位
//    public static final int C_UNIT_VOLUME						= 88; // new int[]{value} 容积单位
//    public static final int C_UNIT_CONSUMPTION					= 89; // new int[]{value} 油耗单位
//    public static final int C_UNIT_PRESSURE						= 90; // new int[]{value} 压力单位
//    public static final int C_ENTER_DOOR_PAGE					= 91; // new int[]{0/1}
//    public static final int C_CAR_SCREEN_BRIGHTNESS_MODE		= 92; //new int[]{value} 原车屏亮度 0最亮, 1一半, 2黑屏
//    public static final int C_CAR_CAMERA_MODE					= 93; //new int[]{value} 原车摄像头模式  0广角, 1标准, 2,俯角
//    public static final int C_CAR_BACKGROUND_COLOR				= 94; //new int[]{value} 背景颜色 0蓝色, 1琥珀色, 2红色, 3,紫罗兰色
//    public static final int C_CAR_PLAYER_CONTROL				= 95; //new int[]{0/1} 原车播放器控制上下曲
//    public static final int C_CAR_SET_LANG						= 96; //new int[]{0/1} 0:英语 1:中文
//    public static final int C_CAR_SET_QUANJING					= 97; //new int[]{0/1} 0:全景切换 1:语言切换
//    public static final int C_CAR_GET_ALL_INFO					= 98; //获取所有原车信息
//    public static final int C_CAR_RIGHTCAMERA_STATE				= 99;
//    public static final int C_CAR_CAMERA_LOUCS					= 100; //0：动态轨迹 1：静态轨迹 2：显示警告线
//    public static final int C_AIR_CON_PROFILE					= 101;
//    public static final int C_AIR_CONTIRE_CMD_SHUPING			= 102;
//    public static final int C_DRIVING_MODEL_INFO1				= 103;
//    public static final int C_DRIVING_MODEL_INFO2				= 104;
//    public static final int C_LIGHT_CONTROL						= 105;
//    public static final int C_OPEN_CLOSE_CONTROL				= 106;
//    public static final int C_AIR_CONTROL						= 107;
//    public static final int C_AIR_CONTIRE_CMD					= 108;//17款迈腾空调发送命令
//    public static final int C_OUT_OF_PACKING_ASSIST				= 110;
//    public static final int C_OFF_ROAD_ICON						= 111;
//    public static final int C_BLIND_DETECT						= 112;
//    public static final int C_DRIVING_MODEL_OFFROAD			= 113;
//    public static final int C_DIRECT_TIRE_MODE			= 114; // 直接式胎压监测负载
//    public static final int C_CAR_CAMERA_BRIGHTNESS	= 115; // 原车摄像头 亮度
//    public static final int C_CAR_CAMERA_CONTRAST		= 116; // 原车摄像头 对比度
//    public static final int C_CAR_CAMERA_COLOR			= 117; // 原车摄像头 色彩

//    public static final int C_JUMP_MEDIA_OD						= 130; // 德众尚杰定制 欧迪 高尔夫 7 跳多媒体界面
//    public static final int C_SET_PARKING_BRAKE			= 132;  	// 泊车制动
//    public static final int C_SET_LIGHT_DISTANCE		= 133;  	// 大灯照明距离

//    public static final int C_CHANGE_BRIGHT_OD 					= 135;//变道亮度
//    public static final int C_AIR_STEER_SEAT_SYNC					= 136;//方向盘座椅加热同步
//    public static final int C_CAR_LOGO_SET					= 137;//车图片设置
//    public static final int C_CAR_DASHBOARD_SET					= 138;//仪表显示开关
//    public static final int C_CAR_OILBOX_SET					= 139;//油箱类型设置

    public static final int TEMPERATURE_UNIT_CELSIUS = 0;
    public static final int TEMPERATURE_UNIT_FAHRENHEIT = 1;

//    public static final int S_SENDSETTING = 0x1A;
//    public static final int S_AIR = 0x3A;
//    public static final int S_PARKINGANDMANOEUVRING = 0x4A;
//    public static final int S_TIRESETTING = 0x4B;
//    public static final int S_DRIVERSETTING = 0x4C;
//    public static final int S_LIGHTSETTING = 0x6D;
//    public static final int S_MIRRORSETTING = 0x6E;
//    public static final int S_OPENSETTING = 0x6F;
//    public static final int S_MUTILSETTING = 0x7B;
//    public static final int S_ESC_SYSTEM = 0x8A;
//    public static final int S_CAR_SET_LANG = 0x9A;
//    public static final int S_UNITSETTING = 0xCA;
//    public static final int S_CAR_CAMERA = 0xF2;


    public static final int C_CARSET = 0x01;            // 0x1A(1, 0x4A, 0x4C, 0x6D, 0x6E 0x6F,0x7B, 0x8A, 0x8B(3, 0xCA, 0xF2
    public static final int C_AIR_CONTROL = 2;

    public static final int CAR_ID_DZSJ_MAITENG = 6;//dzsj 定制迈腾

//    static int carId;

//    private final int ID_TuAng = 5; // 途昂

//    {
//        CANBUS_INFO.mAppShow		= new String[]{DataCanbus.APP_CANBUS};
//        CANBUS_INFO.mExistDoor 		= 1;
//        CANBUS_INFO.mExistAir 		= 1;
//        CANBUS_INFO.mExistTempOut 	= 1;
//    }

    private static final int CONV_CONSUMER_MAX = 7;
    private int[][] mConvConsumer = new int[CONV_CONSUMER_MAX][];


    private static final int START_STOP_MAX = 7;
    private int[][] mSartStop = new int[START_STOP_MAX][];

    private static final int VEHICLE_WARNING_MAX = 16;
    private int[][] mVehicleWarning = new int[VEHICLE_WARNING_MAX][];


//    private SharedPreferencesHelper mSph = new SharedPreferencesHelper(getClass().getSimpleName(), 32);
//    private final int CAMERA_MODE = 1;
//    private final int LIGHT_MODE = 2;

    int carId;
    private final int ID_TuAng = 5; // 途昂
    int Vol_dis_cnt = 0;
    int TemCanKey, CanKey = 0xff;
    int KeyCanKeyTable[][] =
            {
                    {1, FinalKeyCode.KEY_CODE_VOL_UP},
                    {2, FinalKeyCode.KEY_CODE_VOL_DOWN},
                    {3, FinalKeyCode.KEY_CODE_MUTE},
                    {4, FinalKeyCode.KEY_CODE_VA},
                    {5, FinalKeyCode.KEY_CODE_PHONE},
                    {6, FinalKeyCode.KEY_CODE_HANG},
                    {7, FinalKeyCode.KEY_CODE_NULL},
                    {8, FinalKeyCode.KEY_CODE_NEXT},
                    {9, FinalKeyCode.KEY_CODE_PREV},
                    {0x0a, FinalKeyCode.KEY_CODE_MODE},
                    {0x0b, FinalKeyCode.KEY_CODE_MODE},
                    {0x0C, FinalKeyCode.KEY_CODE_NULL},
            };

    int mode = 0;
    int mode_pre = 9;
    int nTime = 9;
    boolean Yyflag = false; //是否带越野功能标记

    int door_rr = 0;
    int door_rl = 0;
    int door_fr = 0;
    int door_fl = 0;
    int door_back = 0;
    int door_engine = 0;

    @Override
    public void onHandler(int[] data) {
        int start = 0;
        LogsUtils.i("Golf " + LogsUtils.toHexString(data));

        switch (data[start + 1]) {
            case 0x11: {    // 车身基本信息
                HandlerTaskCanbus.update(U_CUR_SPEED, data[start + 3] & 0xff);
                HandlerTaskCanbus.update(U_LIGHT_STATE, data[start + 2] >> 1 & 0x01);
                HandlerTaskCanbus.update(U_HANDPART_STATE, data[start + 2] >> 3 & 0x01);

                // 仅处理0x14 雨刷器手柄按下
                int value = data[start + 4] & 0xff;
                int action = data[start + 5] & 0xff;
                if (value == 0x0c && action == 0x01) {
                    //HandlerTaskCanbus.update(U_JUMP_CARINFO, 	1);
//                    ModuleCallbackList.update(DataCanbus.MCLS, U_JUMP_CARINFO, new int[]{1}, null, null);//test 2016.0315
                    HandlerTaskCanbus.update(U_JUMP_CARINFO, 1);
                    if (nTime == 9) {
                        nTime = 8;
                        int n = 0;
                        switch (mode) {
                            case 0:
                                n = 3;
                                break;
                            case 1:
                                n = 5;
                                break;
                            case 2:
                                n = 1;
                                break;
                            case 3:
                                if (Yyflag == true) //是越野车的时候才切换到越野模式。否则调回到经济模式
                                    n = 8;
                                else
                                    n = 4;
                                break;
                            case 4:
                                n = 2;
                                break;
                            case 5:
                                n = 4;
                                break;
                            case 6:
                                n = 6;
                                break;
                            case 7:
                                n = 4;
                                break;
                        }

//                        ToolkitDev.writeMcu(0xE3, 0x03, 0x8b, n, 0x00,0x00);
                    }

                }

//                    if(((data[start+2]&0xFF)&0x20) == 0x20){
//                        HandlerMain.radar(1);
//                    }else{
//                        HandlerMain.radar(0);
//                    }

                if (DataHost.sBackCar == 1)
                    HandlerBackCarTrack.CarBackTrackHandle(data[start + 8] & 0xFF, data[start + 9] & 0xFF);

                HandlerKeyCode.onKeyEvent(KeyCanKeyTable, data[start + 4] & 0xFF, data[start + 5] & 0xFF);

                break;
            }
            case 0x12: {    // 车身详细信息
                int B2 = data[start + 4];

                int b_door_rr = 0;
                int b_door_rl = 0;
                int b_door_fr = 0;
                int b_door_fl = 0;
                int b_door_back = 0;
                int b_door_engine = 0;
                // 司机在左边
//                if (DataCanbus.sDriverOnRight == 0) {
                // 司机门
                HandlerTaskCanbus.update(U_DOOR_FL, B2 >> 7 & 0x01);
                // 副驾驶门
                HandlerTaskCanbus.update(U_DOOR_FR, B2 >> 6 & 0x01);

                b_door_fl = B2 >> 7 & 0x01;
                b_door_fr = B2 >> 6 & 0x01;
//                }
                HandlerTaskCanbus.update(U_DOOR_RL, B2 >> 5 & 0x01);
                HandlerTaskCanbus.update(U_DOOR_RR, B2 >> 4 & 0x01);
                HandlerTaskCanbus.update(U_DOOR_BACK, B2 >> 3 & 0x01);
                HandlerTaskCanbus.update(U_DOOR_ENGINE, B2 >> 2 & 0x01);

                b_door_rr = B2 >> 4 & 0x01;
                b_door_rl = B2 >> 5 & 0x01;
                b_door_back = B2 >> 3 & 0x01;
                b_door_engine = B2 >> 2 & 0x01;

                HandlerTaskCanbus.update(U_CARINFO_OFF_ROAD_KEY, ((data[start + 7] & 0x80) << 1) | ((data[start + 7] >> 6 & 0x01)));
                HandlerTaskCanbus.update(U_CARINFO_OFF_ROAD_ICON, ((data[start + 7] & 0x80) << 1) | ((data[start + 7] >> 5 & 0x01)));

//                if(carId == 6){
//                    if(( door_rr != b_door_rr && b_door_rr == 1)
//                            ||( door_rl != b_door_rl && b_door_rl == 1)
//                            ||( door_fr != b_door_fr && b_door_fr == 1)
//                            ||( door_fl != b_door_fl && b_door_fl == 1)
//                            ||( door_back != b_door_back && b_door_back == 1)
//                            ||( door_engine != b_door_engine && b_door_engine == 1))
//                        ModuleCallbackList.update(DataCanbus.MCLS, U_DASHBOARD_SHOW, new int[]{1}, null, null);
//                    else if(b_door_rr == 0 && b_door_rl == 0 && b_door_fr == 0 && b_door_fl == 0 && b_door_back == 0 && b_door_engine == 0)
//                        ModuleCallbackList.update(DataCanbus.MCLS, U_DASHBOARD_SHOW, new int[]{0}, null, null);
//
//                    door_rr = b_door_rr;
//                    door_rl = b_door_rl;
//                    door_fr = b_door_fr;
//                    door_fl = b_door_fl;
//                    door_back = b_door_back;
//                    door_engine = b_door_engine;
//                }

                HandlerTaskCanbus.update(U_LIFEBELT_STATE, (data[start + 8] >> 5 & 0x01));
                HandlerTaskCanbus.update(U_WASH_STATE, (data[start + 8] >> 4 & 0x01));
                HandlerTaskCanbus.update(U_SET_REMAINING_OIL, data[start + 9] & 0xFF);
                HandlerTaskCanbus.update(U_BATTERY_VOLTAGE, data[start + 10] << 8 & 0xFF00 | data[start + 11] & 0xFF);
                break;
            }
            case 0x13: {
                HandlerTaskCanbus.update(U_0_BY_START, data[start + 2] << 8 & 0xFF00 | data[start + 3] & 0xFF);
                HandlerTaskCanbus.update(U_1_BY_START, data[start + 4] << 8 & 0xFF00 | data[start + 5] & 0xFF);
                HandlerTaskCanbus.update(U_2_BY_START, data[start + 6] << 8 & 0xFF00 | data[start + 7] & 0xFF);
                HandlerTaskCanbus.update(U_3_BY_START, data[start + 8] << 8 & 0xFF00 | data[start + 9] & 0xFF);
                HandlerTaskCanbus.update(U_4_BY_START, data[start + 10] & 0xFF);
                break;
            }
            case 0x14: {
                HandlerTaskCanbus.update(U_5_BY_LONG_TERM, data[start + 2] << 8 & 0xFF00 | data[start + 3] & 0xFF);
                HandlerTaskCanbus.update(U_6_BY_LONG_TERM, data[start + 4] << 8 & 0xFF00 | data[start + 5] & 0xFF);
                HandlerTaskCanbus.update(U_7_BY_LONG_TERM, data[start + 6] << 8 & 0xFF00 | data[start + 7] & 0xFF);
                HandlerTaskCanbus.update(U_8_BY_LONG_TERM, data[start + 8] << 8 & 0xFF00 | data[start + 9] & 0xFF);
                HandlerTaskCanbus.update(U_9_BY_LONG_TERM, data[start + 10] & 0xFF);
                break;
            }
            case 0x15: {
                HandlerTaskCanbus.update(U_10_BY_REFUELLING, data[start + 2] << 8 & 0xFF00 | data[start + 3] & 0xFF);
                HandlerTaskCanbus.update(U_11_BY_REFUELLING, data[start + 4] << 8 & 0xFF00 | data[start + 5] & 0xFF);
                HandlerTaskCanbus.update(U_12_BY_REFUELLING, data[start + 6] << 8 & 0xFF00 | data[start + 7] & 0xFF);
                HandlerTaskCanbus.update(U_13_BY_REFUELLING, data[start + 8] << 8 & 0xFF00 | data[start + 9] & 0xFF);
                HandlerTaskCanbus.update(U_14_BY_REFUELLING, data[start + 10] & 0xFF);
                break;
            }
            case 0x16: {    // Convenience Consumers
                HandlerTaskCanbus.update(U_OIL_MARK_MAX, data[start + 2] & 0xFF);
                HandlerTaskCanbus.update(U_OIL_PROGRESS, data[start + 3] & 0xFF);
                HandlerTaskCanbus.update(U_OIL_UNIT, data[start + 11] & 0xFF);
                break;
            }
            case 0x19: {
                int B6 = data[start + 8];
                int B7 = data[start + 9];
                HandlerTaskCanbus.update(U_INFO_OIL_TEMP, B6 & 0xff);
                HandlerTaskCanbus.update(U_INFO_WATER_TEMP, B7 & 0xff);

                int value = (data[start + 10] << 8 & 0xff00) | (data[start + 11] & 0xff);
                HandlerTaskCanbus.update(U_ENGINE_SPEED, value & 0xffff);
                SendFunc.sendEngineSpeed(value & 0xffff);
                break;
            }
            case 0x1E: {    // 保养信息
                HandlerTaskCanbus.update(U_CARDAY, ((data[start + 3] & 0xC0) << 18) | ((data[start + 4] & 0xff) << 8) | (data[start + 5] & 0xff));
                HandlerTaskCanbus.update(U_CARDISTANCE, ((data[start + 3] & 0x30) << 20) | ((data[start + 2] & 0xff) << 16) | ((data[start + 6] & 0xff) << 8) | (data[start + 7] & 0xff));
                HandlerTaskCanbus.update(U_OILDAY, ((data[start + 3] & 0x0C) << 22) | ((data[start + 8] & 0xff) << 8) | (data[start + 9] & 0xff));
                HandlerTaskCanbus.update(U_OILDISTANCE, ((data[start + 3] & 0x03) << 24) | ((data[start + 2] & 0xff) << 16) | ((data[start + 10] & 0xff) << 8) | (data[start + 11] & 0xff));
                break;
            }
            case 0x1F: {    // 车辆识别号
                int dataL = data[0];
                String f = Utils.getStringFromInts(data, 2, dataL);
                if(mCarId != null && !mCarId.equalsIgnoreCase(f))
                    mCarId = f;

                LogsUtils.i("u idnum: " + mCarId );
                HandlerTaskCanbus.update(U_IDCARNUM, mCarId, mCarId_temp);
                break;
            }
            case 0x31: {    // 空调信息
                int B0 = data[start + 2];
                HandlerTaskCanbus.update(U_AIR_SHOW, B0 >> 7 & 0x01);
                HandlerTaskCanbus.update(U_AIR_POWER, B0 >> 6 & 0x01);
                HandlerTaskCanbus.update(U_AIR_AC_MAX, B0 >> 5 & 0x01);
                HandlerTaskCanbus.update(U_AIR_REAR, B0 >> 4 & 0x01);
                HandlerTaskCanbus.update(U_AIR_AUTO, B0 >> 3 & 0x01);
                HandlerTaskCanbus.update(U_AIR_SYNC, B0 >> 2 & 0x01);
                HandlerTaskCanbus.update(U_AIR_REAR_AUTO, B0 >> 1 & 0x01); //add tuang

                int B1 = data[start + 3];
                HandlerTaskCanbus.update(U_AIR_REAR_LOCK, B1 >> 7 & 0x01);
                HandlerTaskCanbus.update(U_AIR_AC, B1 >> 6 & 0x01);
                HandlerTaskCanbus.update(U_AIR_UNIT, (B1 >> 5 & 0x01) == 0 ? TEMPERATURE_UNIT_FAHRENHEIT : TEMPERATURE_UNIT_CELSIUS);
                HandlerTaskCanbus.update(U_AIR_CYCLE2, B1 >> 4 & 0x01);
                HandlerTaskCanbus.update(U_AIR_REAR_SEATHEAT_RIGHT, B1 >> 2 & 0x03); //add tuang
                HandlerTaskCanbus.update(U_AIR_REAR_SEATHEAT_LEFT, B1 & 0x03); //add tuang

                int B2 = data[start + 4];
                HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEFT, B2 >> 0 & 0x03);
                HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_RIGHT, B2 >> 2 & 0x03);
                HandlerTaskCanbus.update(U_AIR_SEAT_BLOW_LEFT, B2 >> 4 & 0x03);
                HandlerTaskCanbus.update(U_AIR_SEAT_BLOW_RIGHT, B2 >> 6 & 0x03);

                int B3 = data[start + 5];
                HandlerTaskCanbus.update(U_AIR_AUTO_WIND, B3 >> 6 & 0x03);
                HandlerTaskCanbus.update(U_AIR_CLEAR_AIR, B3 >> 5 & 0x01);
                HandlerTaskCanbus.update(U_AIR_STEERHEAT, B3 >> 4 & 0x01);
                HandlerTaskCanbus.update(U_AIR_STEER_SEAT_SYNC, B3 >> 3 & 0x01);

                int auto = 0, body = 0, foot = 0, front = 0, win = 0;
//                if (carId == 1 || carId == 3 || carId == 4 || carId == ID_TuAng || carId == 6)
                {
                    switch (data[start + 6]) {
                        case 0x01:
                            auto = 1;
                            break;
                        case 0x02:
                            front = 1;
                            break;
                        case 0x03:
                            foot = 1;
                            break;
                        case 0x05:
                            body = 1;
                            foot = 1;
                            break;
                        case 0x06:
                            body = 1;
                            break;
                        case 0x0B:
                            win = 1;
                            front = 1;
                            break;
                        case 0x0C:
                            win = 1;
                            foot = 1;
                            break;
                        case 0x0D:
                            win = 1;
                            body = 1;
                            break;
                        case 0x0E:
                            win = 1;
                            body = 1;
                            foot = 1;
                            break;
                    }
                    HandlerTaskCanbus.update(U_AIR_BLOW_AUTO, auto);
                    HandlerTaskCanbus.update(U_AIR_FRONT, front);
                    HandlerTaskCanbus.update(U_AIR_BLOW_WIN, win);
                    HandlerTaskCanbus.update(U_AIR_BLOW_BODY, body);
                    HandlerTaskCanbus.update(U_AIR_BLOW_FOOT, foot);
                }
                
//                else {
//                    switch (data[start + 6]) {
//                        case 0x01:
//                        case 0x02:
//                        case 0x0B:
//                            auto = 1;
//                            break;
//                        case 0x03:
//                            foot = 1;
//                            break;
//                        case 0x05:
//                            body = 1;
//                            foot = 1;
//                            break;
//                        case 0x06:
//                            body = 1;
//                            break;
//                        case 0x0C:
//                            auto = 1;
//                            foot = 1;
//                            break;
//                        case 0x0D:
//                            auto = 1;
//                            body = 1;
//                            break;
//                        case 0x0E:
//                            auto = 1;
//                            body = 1;
//                            foot = 1;
//                            break;
//                    }
//                    HandlerTaskCanbus.update(U_AIR_BLOW_AUTO, auto);
//                    HandlerTaskCanbus.update(U_AIR_BLOW_BODY, body);
//                    HandlerTaskCanbus.update(U_AIR_BLOW_FOOT, foot);
//                }
//
//                HandlerTaskCanbus.update(U_AIR_WIND_LEVEL, data[start + 7] & 0xFF);
//                HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, data[start + 8] & 0xFF);    // 0xFE:LOW 0xFF:HIGH OTHER:value*5/10.0f
//
//                HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, data[start + 9] & 0xFF);
//                HandlerTaskCanbus.update(U_AIR_CLEAR_AIR_PROGRESS, data[start + 10] & 0x0F);//add tuang
//
//                HandlerTaskCanbus.update(U_AIR_REAR_WIND_LEVEL, data[start + 11] >> 4 & 0x0F);//add tuang
//                HandlerTaskCanbus.update(U_AIR_REAR_BLOW_MODE, data[start + 11] & 0x0F);//add tuang
//                foot = body = 0;
//                switch (data[start + 11] & 0x0F) {
//                    case 0x03:
//                        foot = 1;
//                        break;
//                    case 0x05:
//                        foot = body = 1;
//                        break;
//                    case 0x06:
//                        body = 1;
//                        break;
//                }

                HandlerTaskCanbus.update(U_AIR_REAR_BLOW_FOOT, foot);
                HandlerTaskCanbus.update(U_AIR_REAR_BLOW_BODY, body);

                HandlerTaskCanbus.update(U_AIR_BACK_TEMP, data[start + 12] & 0xFF);
//                if(DataCanbus.isAnalysisByServer()){
                int B11 = data[start + 13] & 0xFF;
                int tempature = 0;
                if (B11 >= 80)// 正温度
                {
                    tempature = 1000 + (B11 * 5) - 400;
                } else        //负温度
                {
                    tempature = 1000 - (400 - (B11 * 5));
                }

                if (tempature > 600 /*&& tempOut < 1851*/) {
                    SendFunc.sendOutTemp(1, tempature);
                }
                HandlerTaskCanbus.update(U_AIR_OUT_TEMP, data[start + 13] & 0xFF);
//                }
                break;
            }
            case 0x35: {    // 空调设定信息
                HandlerTaskCanbus.update(U_AIR_AUTO_SET, ((data[start + 2] & 0x80) << 1) | ((data[start + 4] >> 6) & 0x03));
                HandlerTaskCanbus.update(U_AIR_FRONT_SET, ((data[start + 3] & 0x40) << 2) | ((data[start + 5] >> 1) & 0x01));
                HandlerTaskCanbus.update(U_AIR_AUTOCYCLE_SET, ((data[start + 3] & 0x08) << 5) | ((data[start + 6] >> 4) & 0x01));
                break;
            }
            case 0x36: {
                HandlerTaskCanbus.update(U_LIGHT_TURN_LEFT, data[start + 3] >> 1 & 0x01);
                HandlerTaskCanbus.update(U_LIGHT_TURN_RIGHT, data[start + 3] >> 0 & 0x01);
                break;
            }
            case 0x41: {
//                Log.d("LG", "data[start+2] ="+data[start+2]);
//                if(DataCanbus.isAnalysisByServer()){
                SendFunc.sendRadar(SendFunc.FL_RARA_RL, ((data[start + 2] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_RL, ((data[start + 2] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_RML, ((data[start + 3] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_RMR, ((data[start + 4] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_RR, ((data[start + 5] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_FL, ((data[start + 6] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_FML, ((data[start + 7] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_FMR, ((data[start + 8] & 0xff) * 10) / 0xff);
                SendFunc.sendRadar(SendFunc.FL_RARA_FR, ((data[start + 9] & 0xff) * 10) / 0xff);
//                }
                break;
            }
            case 0x42: {
//                if(DataCanbus.isAnalysisByServer()){
//                    HandlerMain.radarLSB(((data[start+9]&0xff)*10)/0xff);// 雷达信息: 左侧后
//                    HandlerMain.radarLSF(((data[start+6]&0xff)*10)/0xff); // 雷达信息: 左侧前
//                    HandlerMain.radarLSMB(((data[start+8]&0xff)*10)/0xff);// 雷达信息: 左侧中后
//                    HandlerMain.radarLSMF(((data[start+7]&0xff)*10)/0xff); // 雷达信息: 左侧中前
//                    HandlerMain.radarRSB(((data[start+5]&0xff)*10)/0xff);  // 雷达信息: 右侧后
//                    HandlerMain.radarRSF(((data[start+2]&0xff)*10)/0xff); // 雷达信息: 右侧前
//                    HandlerMain.radarRSMB(((data[start+4]&0xff)*10)/0xff);// 雷达信息: 右侧中后
//                    HandlerMain.radarRSMF(((data[start+3]&0xff)*10)/0xff); // 雷达信息: 右侧中前
//                }
                break;
            }
            case 0x45: {    // Parking & Manoeuring设定信息
                HandlerTaskCanbus.update(U_ACTIVATEAUTOMATICLLY, ((data[start + 2] & 0x80) << 1) | ((data[start + 3] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_FRONTVOL, ((data[start + 2] & 0x40) << 2) | fix((data[start + 4] & 0xff) - 1));
                HandlerTaskCanbus.update(U_FRONTTONE, ((data[start + 2] & 0x20) << 3) | fix((data[start + 5] & 0xff) - 1));
                HandlerTaskCanbus.update(U_REARVOL, ((data[start + 2] & 0x10) << 4) | fix((data[start + 6] & 0xff) - 1));
                HandlerTaskCanbus.update(U_REARTONE, ((data[start + 2] & 0x08) << 5) | fix((data[start + 7] & 0xff) - 1));

                HandlerTaskCanbus.update(U_DIRIVEOUT_PARK_ASSIST, ((data[start + 2] & 0x04) << 6) | (data[start + 3] >> 6 & 0x01));
                HandlerTaskCanbus.update(U_PARK, ((data[start + 2] & 0x02) << 7) | ((data[start + 8] & 0xC0) >> 6));
                HandlerTaskCanbus.update(U_RADARMUTE, ((data[start + 2] & 0x01) << 8) | ((data[start + 8] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_SET_PARKING_BRAKE, ((data[start + 3] & 0x20) << 3) | ((data[start + 3] & 0x10) >> 4));

                break;
            }
            case 0x46: {    // Tyres 设定信息//威驰说要去掉
                HandlerTaskCanbus.update(U_TPMS, ((0x80) << 1));
                HandlerTaskCanbus.update(U_SPEEDWARNNING, ((0x40) << 2) | ((data[start + 3] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_WARNNINGAT, ((0x40) << 2) | ((data[start + 4] & 0xff)));

                HandlerTaskCanbus.update(U_DIRECT_TIRE_DETECT, ((data[start + 2] >> 5 & 0x01) << 8) | ((data[start + 3] >> 5 & 0x03)));  //add tuang

                break;
            }
            case 0x47: {    // Driver Assistant设定信息
                HandlerTaskCanbus.update(U_DRIVERPROGRAM, ((data[start + 2] & 0x80) << 1) | ((data[start + 9] & 0xC0) >> 6));
                HandlerTaskCanbus.update(U_DRIVERLASTDISTANCESELECTED, ((data[start + 2] & 0x40) << 2) | ((data[start + 9] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_DRIVERACC, ((data[start + 2] & 0x40) << 2) | ((data[start + 9] & 0x1f)));
                HandlerTaskCanbus.update(U_DRIVERACTIVE, ((data[start + 3] & 0x80) << 1) | ((data[start + 10] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_DRIVERDISPLAYDISTANCEWARNNING, ((data[start + 3] & 0x20) << 3) | ((data[start + 10] >> 5) & 0x01)); // add

                if (carId == 3) // 途观这里是4个选项
                    HandlerTaskCanbus.update(U_DRIVERADVANCEWARNNINGSETTING, ((data[start + 3] & 0x40) << 2) | ((data[start + 10] >> 3) & 0x03));
                else
                    HandlerTaskCanbus.update(U_DRIVERADVANCEWARNNINGSETTING, ((data[start + 3] & 0x40) << 2) | ((data[start + 10] & 0x40) >> 6));

                HandlerTaskCanbus.update(U_DRIVERLANEASSIST, ((data[start + 4] & 0x80) << 1) | ((data[start + 11] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_DRIVERTRAFFICESIGNRECOGNITION, ((data[start + 5] & 0x80) << 1) | ((data[start + 12] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_DRIVERALERTSYSTEM, ((data[start + 6] & 0x80) << 1) | ((data[start + 13] & 0x80) >> 7));

                HandlerTaskCanbus.update(U_BLIND_DETECT, ((data[start + 6] & 0x08) << 5) | ((data[start + 13] >> 3 & 0x01))); // 盲区检测 add
                HandlerTaskCanbus.update(U_DRIVERPROACTIVEOCCUPATION, ((data[start + 7] & 0x80) << 1) | ((data[start + 14] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_CARSET_47D137, ((data[start + 8] & 0x80) << 1) | ((data[start + 15] >> 7 & 0x01)));

                break;
            }
            case 0x48: { // tuang  直接式胎压检测信息
                int b0 = data[start + 2];
                int b1 = data[start + 3];
                int b2 = data[start + 4];
                int b3 = data[start + 5];
                int b4 = data[start + 6];
                int b5 = data[start + 7];
                int b6 = data[start + 8];
                int b7 = data[start + 9];
                int b8 = data[start + 10];
                int b9 = data[start + 11];
                int b10 = data[start + 12];
                int b11 = data[start + 13];
                int b12 = data[start + 14];
                int b13 = data[start + 15];
                int b14 = data[start + 16];
                int b15 = data[start + 17];
                int b16 = data[start + 18];
                int b17 = data[start + 19];

                HandlerTaskCanbus.update(U_TIRE_UNIT, b0 >> 6 & 0x3);        // 胎压单位
                HandlerTaskCanbus.update(U_TIRE_DISPLAY, b1); // 车轮胎压报警

                HandlerTaskCanbus.update(U_TIRE_PRESSURE_FL, ((b10 << 8 & 0x0FF00 | b11 & 0x0FF) << 16) | (b2 << 8 & 0x0FF00 | b3 & 0x0FF));
                HandlerTaskCanbus.update(U_TIRE_PRESSURE_FR, ((b12 << 8 & 0x0FF00 | b13 & 0x0FF) << 16) | (b4 << 8 & 0x0FF00 | b5 & 0x0FF));
                HandlerTaskCanbus.update(U_TIRE_PRESSURE_RL, ((b14 << 8 & 0x0FF00 | b15 & 0x0FF) << 16) | (b6 << 8 & 0x0FF00 | b7 & 0x0FF));
                HandlerTaskCanbus.update(U_TIRE_PRESSURE_RR, ((b16 << 8 & 0x0FF00 | b17 & 0x0FF) << 16) | (b8 << 8 & 0x0FF00 | b9 & 0x0FF));
                break;
            }
            case 0x64: {    // Opening And Closing设定信息
                HandlerTaskCanbus.update(U_OPENCLOSECONVENIENCE, ((data[start + 2] & 0x80) << 1) | ((data[start + 4] & 0xC0) >> 6));
                HandlerTaskCanbus.update(U_OPENCLOSEDOORUNLOCK, ((data[start + 3] & 0x80) << 1) | ((data[start + 5] & 0xC0) >> 6));
                HandlerTaskCanbus.update(U_OPENCLOSEAUTOLOCK, ((data[start + 3] & 0x40) << 2) | ((data[start + 5] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_DOOR_MIND_PAIR, ((data[start + 3] & 0x20) << 3) | ((data[start + 5] & 0x10) >> 4));
                HandlerTaskCanbus.update(U_DOOR_SENSE_LAN, ((data[start + 3] & 0x10) << 4) | ((data[start + 5] & 0x08) >> 3));

                break;
            }
            case 0x67: {    // Light设定信息2
                HandlerTaskCanbus.update(U_LIGHT_COLOR, ((data[start + 2] & 0x80) << 1) | data[start + 4]);
                HandlerTaskCanbus.update(U_LIGHT_FAN, ((data[start + 2] & 0x40) << 2) | data[start + 5]);
                HandlerTaskCanbus.update(U_LIGHT_RIGHT, ((data[start + 2] & 0x20) << 3) | data[start + 6]);


                HandlerTaskCanbus.update(U_CHANGE_BRIGHT_OD, ((data[start + 2] & 0x20) << 6) | data[start + 7]); //驾驶辅助系统亮度

                HandlerTaskCanbus.update(U_SET_LIGHT_DISTANCE, ((data[start + 2] & 0x08) << 5) | data[start + 3]);

                break;
            }
            case 0x68: {    // Light设定信息
                HandlerTaskCanbus.update(U_LIGHT_ASSIST, ((data[start + 2] & 0x80) << 1) | ((data[start + 5] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_LIGHT_BEND, ((data[start + 2] & 0x40) << 2) | ((data[start + 5] & 0x40) >> 6));
                HandlerTaskCanbus.update(U_LIGHTSWITCHONTIME, ((data[start + 2] & 0x20) << 3) | ((data[start + 5] & 0x30) >> 4));
                HandlerTaskCanbus.update(U_LIGHTAUTOMATICHEADLIGHTRAIN, ((data[start + 2] & 0x10) << 4) | ((data[start + 5] & 0x08) >> 3));
                HandlerTaskCanbus.update(U_LIGHTLANECHANGEFLASH, ((data[start + 2] & 0x08) << 5) | ((data[start + 5] & 0x04) >> 2));
                HandlerTaskCanbus.update(U_LIGHTTRAVELMODE, ((data[start + 2] & 0x04) << 6) | ((data[start + 5] & 0x02) >> 1));

                HandlerTaskCanbus.update(U_DAYTIME_RUNNING_LIGHTS, ((data[start + 2] & 0x02) << 7) | ((data[start + 5] & 0x01))); // add

                HandlerTaskCanbus.update(U_LIGHTINSTRUMENT, ((data[start + 3] & 0x80) << 1) | (data[start + 6] & 0xff));
                HandlerTaskCanbus.update(U_LIGHTDOORBACKGROUND, ((data[start + 3] & 0x40) << 2) | (data[start + 7] & 0xff));
                HandlerTaskCanbus.update(U_LIGHTFOOTWELL, ((data[start + 3] & 0x20) << 3) | (data[start + 8] & 0xff));
                HandlerTaskCanbus.update(U_LIGHTCOMINGHOME, ((data[start + 4] & 0x80) << 1) | (data[start + 9] & 0xff));
                HandlerTaskCanbus.update(U_LIGHTLEAVINGHOME, ((data[start + 4] & 0x40) << 2) | (data[start + 10] & 0xff));

                break;
            }
            case 0x69: {    // Mirrors&Wipers设定信息
                HandlerTaskCanbus.update(U_MIRRORWIPERSSYNADJUST, ((data[start + 2] & 0x80) << 1) | ((data[start + 4] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_MIRRORWIPERSLOWWHILEREVESING, ((data[start + 2] & 0x40) << 2) | ((data[start + 4] & 0x40) >> 6));
                HandlerTaskCanbus.update(U_MORRORWIPERSFOLDINWHENPARKED, ((data[start + 2] & 0x20) << 3) | ((data[start + 4] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_MORRORWIPERSAUTOWIPINGINRAIN, ((data[start + 3] & 0x80) << 1) | ((data[start + 5] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_MIRRORWIPERSREARWINWIPINGINREV, ((data[start + 3] & 0x40) << 2) | ((data[start + 5] & 0x40) >> 6));

                break;
            }
            case 0x74: {    // Vehicle Warning
                int cnt = data[start + 2] & 0xff;
                if (cnt > VEHICLE_WARNING_MAX) {
                    cnt = VEHICLE_WARNING_MAX;
                }

                if (cnt > 0) {
                    for (int i = 0; i < cnt; i++) {
                        int[] value = new int[]{i, data[start + 3 + i] & 0xff};
                        if (HandlerTaskCanbus.update(U_WARNNING_VEHICLE, value, mVehicleWarning[i])) {
                            mVehicleWarning[i] = value;
                        }
                    }
                    for (int i = cnt; i < VEHICLE_WARNING_MAX; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_VEHICLE, value, mVehicleWarning[i])) {
                            mVehicleWarning[i] = value;
                        }
                    }
                } else {
                    for (int i = 0; i < VEHICLE_WARNING_MAX; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_VEHICLE, value, mVehicleWarning[i])) {
                            mVehicleWarning[i] = value;
                        }
                    }
                }
                break;
            }
            case 0x75: {    // Start-Stop Warning
                int cnt = data[start + 2] & 0xff;
                if (cnt > START_STOP_MAX) {
                    cnt = START_STOP_MAX;
                }
                if (cnt > 0) {
                    for (int i = 0; i < cnt; i++) {
                        int[] value = new int[]{i, data[start + 3 + i] & 0xff};
                        if (HandlerTaskCanbus.update(U_WARNNING_START_STOP, value, mSartStop[i])) {
                            mSartStop[i] = value;
                        }
                    }
                    for (int i = cnt; i < 7; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_START_STOP, value, mSartStop[i])) {
                            mSartStop[i] = value;
                        }
                    }
                } else {
                    for (int i = 0; i < 7; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_START_STOP, value, mSartStop[i])) {
                            mSartStop[i] = value;
                        }
                    }
                }
                break;
            }
            case 0x76: {    // 多功能显示设定信息
                HandlerTaskCanbus.update(U_MUTILCURRENTCONSUMPTION, ((data[start + 2] & 0x80) << 1) | ((data[start + 4] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_MUTILAVERAGECONSUMPTION, ((data[start + 2] & 0x40) << 2) | ((data[start + 4] & 0x40) >> 6));
                HandlerTaskCanbus.update(U_MUTILCONVENIENCECONSUMER, ((data[start + 2] & 0x20) << 3) | ((data[start + 4] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_MUTILECOTIPS, ((data[start + 2] & 0x10) << 4) | ((data[start + 4] & 0x10) >> 4));
                HandlerTaskCanbus.update(U_MUTILTRAVERLLINGTIME, ((data[start + 2] & 0x08) << 5) | ((data[start + 4] & 0x08) >> 3));
                HandlerTaskCanbus.update(U_MUTILDISTANCETRAVELLED, ((data[start + 2] & 0x04) << 6) | ((data[start + 4] & 0x04) >> 2));
                HandlerTaskCanbus.update(U_MUTILAVERAGESPEED, ((data[start + 2] & 0x02) << 7) | ((data[start + 4] & 0x02) >> 1));
                HandlerTaskCanbus.update(U_MUTIILDIGITSPEEDDISPLAY, ((data[start + 2] & 0x01) << 8) | (data[start + 4] & 0x01));
                HandlerTaskCanbus.update(U_MUTILSPEEDWARNNING, ((data[start + 3] & 0x80) << 1) | ((data[start + 5] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_SYSTEM_KEY_WITH_EKEY_ENABLED, ((data[start + 3] & 0x40) << 2) | ((data[start + 5] & 0x40) >> 6));

                break;
            }
            case 0x77: {    // Conv. Consumer Warning
                int cnt = data[start + 2] & 0xff;
                if (cnt > CONV_CONSUMER_MAX) {
                    cnt = CONV_CONSUMER_MAX;
                }
                if (cnt > 0) {
                    for (int i = 0; i < cnt; i++) {
                        int[] value = new int[]{i, data[start + 3 + i] & 0xff};
                        if (HandlerTaskCanbus.update(U_WARNNING_CONV_CONSUMER, value, mConvConsumer[i])) {
                            mConvConsumer[i] = value;
                        }
                    }
                    for (int i = cnt; i < CONV_CONSUMER_MAX; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_CONV_CONSUMER, value, mConvConsumer[i])) {
                            mConvConsumer[i] = value;
                        }
                    }
                } else {
                    for (int i = 0; i < CONV_CONSUMER_MAX; i++) {
                        int[] value = new int[]{i, 0};
                        if (HandlerTaskCanbus.update(U_WARNNING_CONV_CONSUMER, value, mConvConsumer[i])) {
                            mConvConsumer[i] = value;
                        }
                    }
                }

                break;
            }
            case 0x82: {    // HAVC Infos
                break;
            }
            case 0x85: {    // 运动模式设置信息
                HandlerTaskCanbus.update(U_ESC_SYSTEM, ((data[start + 2] & 0x20) << 3) | ((data[start + 3] & 0x60) >> 5));
                break;
            }
            case 0x86: {    // Driving Mode Selection设定信息
                HandlerTaskCanbus.update(U_SETTING_86D17, ((data[start + 2] & 0x80) << 1) | ((data[start + 3] & 0x80) >> 7));
                HandlerTaskCanbus.update(U_SETTING_86D16, ((data[start + 2] & 0x40) << 2) | ((data[start + 3] & 0x40) >> 6));
                HandlerTaskCanbus.update(U_SETTING_86D15, ((data[start + 2] & 0x20) << 3) | ((data[start + 3] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_SETTING_86D14, ((data[start + 2] & 0x10) << 4) | ((data[start + 3] & 0x10) >> 4));
                HandlerTaskCanbus.update(U_SETTING_86D13, ((data[start + 2] & 0x08) << 5) | ((data[start + 3] & 0x08) >> 3));
                HandlerTaskCanbus.update(U_SETTING_86D12, ((data[start + 2] & 0x04) << 6) | (data[start + 3] >> 2 & 0x01)); // add 雪地
                HandlerTaskCanbus.update(U_SETTING_86D11, ((data[start + 2] & 0x02) << 7) | (data[start + 3] >> 1 & 0x01)); // add 越野
                HandlerTaskCanbus.update(U_SETTING_86D10, ((data[start + 2] & 0x01) << 8) | (data[start + 3] & 0x01)); // add 越野个性化

                if ((data[start + 2] >> 2 & 0x01) == 0x01)
                    Yyflag = true;

                if (((data[start + 3] & 0x80) >> 7) == 1)
                    mode = 4;
                else if (((data[start + 3] & 0x40) >> 6) == 1)
                    mode = 0;
                else if (((data[start + 3] & 0x20) >> 5) == 1)
                    mode = 1;
                else if (((data[start + 3] & 0x10) >> 4) == 1)
                    mode = 2;
                else if (((data[start + 3] & 0x08) >> 3) == 1)
                    mode = 3;
                else if (((data[start + 3] & 0x04) >> 2) == 1)
                    mode = 5;
                else if (((data[start + 3] & 0x02) >> 1) == 1)
                    mode = 6;
                else if ((data[start + 3] & 0x01) == 1)
                    mode = 7;
//                Intent intent = new Intent();
//                intent.putExtra("driving_mode", mode);
//                intent.setAction("syu.intent.action.gaoerfu");
//                MyApp.getInstance().sendBroadcast(intent);

                HandlerTaskCanbus.update(U_SETTING_86D26, (data[start + 4] >> 6 & 0x03)); // DCC
                HandlerTaskCanbus.update(U_SETTING_86D24, (data[start + 4] >> 4 & 0x03)); // 动态大灯随动
                HandlerTaskCanbus.update(U_SETTING_86D22, (data[start + 4] >> 2 & 0x03)); // 驱动装置
                HandlerTaskCanbus.update(U_SETTING_86D20, (data[start + 4] >> 0 & 0x03)); // 自适应巡航
                HandlerTaskCanbus.update(U_SETTING_86D37, (data[start + 5] >> 7 & 0x01)); // 空调
                HandlerTaskCanbus.update(U_SETTING_86D36, (data[start + 5] >> 6 & 0x01)); // 方向盘
                break;
            }
            case 0x87: { // 越野个性化
                int B0 = data[start + 2];
                int B1 = data[start + 3];
                int B2 = data[start + 4];
                int B3 = data[start + 5];
                int B4 = data[start + 6];
                int B5 = data[start + 7];
                int B6 = data[start + 8];

                HandlerTaskCanbus.update(U_SETTING_86D13_ENABLE, B4); // 动态大灯随动

                HandlerTaskCanbus.update(U_SET_OFFROAD_BEND, ((B5 >> 7 & 0x01) << 8) | (B2 >> 4 & 0x03)); // 动态大灯随动
                HandlerTaskCanbus.update(U_SET_OFFROAD_ENGINE, ((B5 >> 6 & 0x01) << 8) | (B2 >> 2 & 0x03)); // 驱动装置
                HandlerTaskCanbus.update(U_SET_OFFROAD_ACC, ((B5 >> 5 & 0x01) << 8) | (B2 & 0x03)); // ACC

                HandlerTaskCanbus.update(U_SET_OFFROAD_AIRCONDITION, ((B5 >> 4 & 0x01) << 8) | (B3 >> 7 & 0x01)); // 空调
                HandlerTaskCanbus.update(U_SET_OFFROAD_STEER, ((B5 >> 3 & 0x01) << 8) | (B3 >> 6 & 0x01)); // 转向
                HandlerTaskCanbus.update(U_SET_OFFROAD_4ENGINE, ((B5 >> 2 & 0x01) << 8) | (B3 >> 5 & 0x01)); // 四轮驱动
                HandlerTaskCanbus.update(U_SET_OFFROAD_DOWNHILL_ASSIST, ((B5 >> 1 & 0x01) << 8) | (B3 >> 4 & 0x01)); // 下坡辅助
                HandlerTaskCanbus.update(U_SET_OFFROAD_RAMP_START, ((B5 >> 0 & 0x01) << 8) | (B3 >> 3 & 0x01)); // 坡道起步
                HandlerTaskCanbus.update(U_SET_OFFROAD_PARKING_ASSIST, ((B6 >> 7 & 0x01) << 8) | (B3 >> 2 & 0x01)); // 驻车辅助

            }
            break;
            case 0xC1: {    // Unit设定信息

                HandlerTaskCanbus.update(U_FEEDBACK_LAMP_ENABLED, ((data[start + 3] & 0x80) >> 7));
//				U_SET_UNIT_DISTANCE
                HandlerTaskCanbus.update(U_SET_UNIT_DISTANCE, ((data[start + 2] & 0x80) << 1) | (data[start + 3] >> 7 & 0x01));
                HandlerTaskCanbus.update(U_UNITSPEED, ((data[start + 2] & 0x40) << 2) | ((data[start + 3] & 0x40) >> 6));
                HandlerTaskCanbus.update(U_AUTO_AC_ENABLED, ((data[start + 2] & 0x40) << 2) | ((data[start + 3] & 0x40) >> 6));

                HandlerTaskCanbus.update(U_UNITTEMPERATURE, ((data[start + 2] & 0x20) << 3) | ((data[start + 3] & 0x20) >> 5));
                HandlerTaskCanbus.update(U_UNITVOLUME, ((data[start + 2] & 0x10) << 4) | ((data[start + 3] & 0x18) >> 3));
                HandlerTaskCanbus.update(U_UNITCONSUMPTION, ((data[start + 2] & 0x08) << 5) | ((data[start + 3] & 0x06) >> 1));
                HandlerTaskCanbus.update(U_UNITPRESSURE, ((data[start + 2] & 0x04) << 6) | ((data[start + 4] & 0xC0) >> 6));
                HandlerTaskCanbus.update(U_TEMPERATUREUNIT, (data[start + 3] & 0x20) == 0 ? TEMPERATURE_UNIT_FAHRENHEIT : TEMPERATURE_UNIT_CELSIUS);

                break;
            }
            case 0xE8: {
                // 原车屏状态信息 摄像头模式 右摄像头状态
                // data1 摄像头模式 原车(2标准 3 俯角) 转成 UI定义{ 0广角, 1标准, 2,俯角 }
                int camera_state = data[start + 3] & 0xff;
                if (camera_state == 0x00)
                    camera_state = 0xff;

                if ((data[start + 3] & 0xff) == 0x09)
                    camera_state = 0x04;

                camera_state = camera_state - 1;

//                HandlerTaskCanbus.update(FinalCanbus.U_CAMERA_MODE, camera_state);

                int B2 = data[start + 4];
                int B3 = data[start + 5];
                int B4 = data[start + 6];

                HandlerTaskCanbus.update(U_SET_BACKCAR_BRIGTHNESS, B2);
                HandlerTaskCanbus.update(U_SET_BACKCAR_CONTRAST, B3);
                HandlerTaskCanbus.update(U_SET_BACKCAR_COLOR, B4);

                break;
            }
            case 0xC2: {    // Time and date setup设定信息
                break;
            }
            case 0xF0: {        // 软件版本号
                HandlerTaskCanbus.canbusVer(new String(data, start + 2, data.length - 2));
                break;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    private String mCarId = "";
    private String mCarId_temp="";

    //-------------------------------------------------------------------------------------------------
//    private void carId(String value) {
//        if (value == null) {
//            if (mCarId != value) {
//                mCarId = value;
//            }
//        } else if (!value.equals(mCarId)) {
//            mCarId = value;
//        }
//    }

    private int fix(int value) {
        if (value < 0) return 0;
        if (value > 8) return 8;
        return value;
    }

    @Override
    public void in() {
        LogsUtils.i("TaskCar_Golf in");
        EventNotify.NE_ID3_ALBUM.addNotify(N_ID3_ALBUM);
        EventNotify.NE_ID3_TITLE.addNotify(N_ID3_TITLE);
        EventNotify.NE_ID3_ARTIST.addNotify(N_ID3_ARTIST);
        EventNotify.NE_RADIO_BAND.addNotify(N_RADIO);
        EventNotify.NE_RADIO_FREQS.addNotify(N_RADIO);
        EventNotify.NE_APPID.addNotify(N_APPID);
        EventNotify.NE_BT_PHONENUM.addNotify(N_BT_PHONENUM);
        EventNotify.NE_BT_STATE.addNotify(N_BT_PHONESTATE);
        EventNotify.NE_GPS_ANGLE.addNotify(N_NAVI_INFO);
        EventNotify.NE_LANG.addNotify(N_LANG);

        Ticks.addTicks1s(TIMESET);
    }

    @Override
    public void out() {
        LogsUtils.i("TaskCar_Golf out");
        EventNotify.NE_ID3_ALBUM.removeNotify(N_ID3_ALBUM);
        EventNotify.NE_ID3_TITLE.removeNotify(N_ID3_TITLE);
        EventNotify.NE_ID3_ARTIST.removeNotify(N_ID3_ARTIST);
        EventNotify.NE_RADIO_BAND.removeNotify(N_RADIO);
        EventNotify.NE_RADIO_FREQS.removeNotify(N_RADIO);
        EventNotify.NE_APPID.removeNotify(N_APPID);
        EventNotify.NE_BT_PHONENUM.removeNotify(N_BT_PHONENUM);
        EventNotify.NE_BT_STATE.removeNotify(N_BT_PHONESTATE);
        EventNotify.NE_GPS_ANGLE.removeNotify(N_NAVI_INFO);
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

    private void sendHostInfo0x91(int cmd, String infos) {
        if (infos == null) return;
        byte[] infoBs;
        int[] data = new int[26];
        int len;
        infoBs = infos.getBytes();
        len = infoBs.length > 24 ? 24 : infoBs.length;
        for (int i = 0; i < len; i++) {
            data[2 + i] = infoBs[i];
        }
        SendFunc.send2Canbus(cmd, data);
    }

    private void sendHostInfo0x92(int cmd, String infos) {
        if (infos == null) return;
        byte[] infoBs;
        int[] data = new int[25];
        int len;
        infoBs = infos.getBytes();
        len = infoBs.length > 24 ? 24 : infoBs.length;
        for (int i = 0; i < len; i++) {
            data[i + 1] = infoBs[i];
        }
        SendFunc.send2Canbus(cmd, data);
    }

    IUiNotify N_BT_PHONENUM = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            sendHostInfo0x92(0x95, DataHost.sPhoneNum);
        }
    };

    IUiNotify N_BT_PHONESTATE = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            String phoneStateDes = HandlerBtPhone.getPhoneStateDes(DataHost.sPhoneSate);
            sendHostInfo0x92(0x96, phoneStateDes);
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
//            ToolTime ToolTime = new ToolTime();
//            ToolTime.year = year;
//            ToolTime.month = month;
//            ToolTime.day = day;
//            ToolTime.hour = hour;
//            ToolTime.min = min;
//            ToolTime.sec = sec;
//            ToolTime.format = format;
//            LogsUtils.i("time : " + ToolTime.toString());
                SendFunc.sendTime(year, month, day, hour, min, sec, format);
            }
        }
    };

    private IUiNotify N_RADIO = new IUiNotify() {
        private String Unit = "MHz";
        private String freq = "87.5 MHz";
        private String band = "FM";
        private String index = "";

        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (DataHost.isAM) {
                Unit = " KHz";
                freq = DataHost.iRadioFreq + Unit;
                band = "AM";
            } else {
                Unit = " MHz";
                freq = DataHost.iRadioFreq / 100.0f + Unit;
                band = "FM";
            }
            index = ((DataHost.iRadioBand & 0xFF) + 1) + "-";
            int channel = (DataHost.iRadioChannel & 0xFF) % 6 + 1;
            band = band + index + channel;
//            Arrays.fill(data, 0);
//            Arrays.fill(data2, 0);
//            byte[] bytes = freq.getBytes();
//            len = bytes.length > data.length - 2 ? data.length - 2: bytes.length;
//            for (int i = 0; i < len; i++){
//                data[2 + i] = bytes[i];
//            }
//            bytes = band.getBytes();
//            len = bytes.length > data2.length - 1 ? data2.length - 1: bytes.length;
//            for (int i = 0; i < len; i++){
//                data2[1 + i] = bytes[i];
//            }

            sendHostInfo0x91(0x91, freq); 
            sendHostInfo0x92(0x92, band);
            LogsUtils.i(" band: " + band + ", freq :" + freq);
        }
    };

    private IUiNotify N_APPID = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            LogsUtils.i("N_APPID: " + DataHost.sAppid); 
        }
    };

    private IUiNotify N_ID3_TITLE = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            sendHostInfo0x91(0x91, DataHost.sId3Title);
        }
    };
    private IUiNotify N_ID3_ARTIST = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            sendHostInfo0x92(0x92, DataHost.sId3Artist);
        }
    };

    private IUiNotify N_ID3_ALBUM = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            sendHostInfo0x92(0x93, DataHost.sId3Album);
        }
    };

    private float bear = 0;
    private int Old_bear = 0xff;
    private int New_bear = 0xff;
    private IUiNotify N_NAVI_INFO = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (true) {
                bear = DataHost.sGpsAngle;
                if ((bear <= 22.5 && bear >= 0) || (bear >= 360.0 - 22.5 && bear <= 360)) {//north
                    New_bear = 0x07;
                } else if (bear > 22.5 && bear < 22.5 + 45) {//northeast
                    New_bear = 0x08;
                } else if (bear >= 67.5 && bear <= 90 + 22.5) {//east
                    New_bear = 0x01;
                } else if (bear > 90 + 22.5 && bear < 135 + 22.5) {//southeast
                    New_bear = 0x02;
                } else if (bear >= 135 + 22.5 && bear <= 180 + 22.5) {//south
                    New_bear = 0x03;
                } else if (bear > 180 + 22.5 && bear < 225 + 22.5) {//southwest
                    New_bear = 0x04;
                } else if (bear >= 225 + 22.5 && bear <= 270 + 22.5) {//west
                    New_bear = 0x05;
                } else if (bear > 270 + 22.5 && bear < 315 + 22.5) {//northwest
                    New_bear = 0x06;
                } else
                    New_bear = 0x03;
                if (New_bear != Old_bear) {
                    SendFunc.send2Canbus(0xe4, 0, 0,
                            0, 0, 0, 0,
                            0,
                            0, 0, 0, 0,
                            New_bear,
                            0x00, 0x00,
                            0, 0,
                            0,
                            0x00, 0x00);
                    Old_bear = New_bear;
                }
            }
        }
    };

    @Override
    public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws RemoteException {
        LogsUtils.i("golf ~~~ cmd " + cmd);
        switch (cmd) {
            case C_AIR_CONTROL:
                if (ints != null && ints.length > 1)
                    SendFunc.send2Canbus(0x3A, ints);
                break;
            case C_CARSET:
                if (ints != null && ints.length > 1) {
                    int d[] = new int[ints.length - 1];
                    System.arraycopy(ints, 1, d, 0, ints.length - 1);
                    SendFunc.send2Canbus(ints[0], d);
                }
                break;
        }
    }

}
