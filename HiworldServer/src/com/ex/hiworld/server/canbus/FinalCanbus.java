package com.ex.hiworld.server.canbus;

/**
 * Created by APP03 on 2018/6/9.
 */

public class FinalCanbus {

    // 注意所有CMD_CODE都在同一个区域内，不要越界
    public static final int C_MISC_BEGIN					= 1000;
    public static final int C_CANBUS_ID						= C_MISC_BEGIN+0;	// (car_id<<16|canbus_id)
    public static final int C_DRIVER_ON_RIGHT				= C_MISC_BEGIN+1;	// 司机在右边
    // 显示空调弹窗(主要用于屏幕按钮) PARAM new int[]{0:临时显示,n秒后自动消失}
    public static final int C_SHOW_AIR_WINDOW				= C_MISC_BEGIN+2;
    // PARAM new int[]{0}
    public static final int C_CHANGE_PANORAMA				= C_MISC_BEGIN+3;	// 切换全景摄像头
    // PARAM new int[]{0:临时显示,n秒后自动消失}
    public static final int C_SHOW_DOOR_WINDOW				= C_MISC_BEGIN+4;	// 显示车门弹窗
    public static final int C_CAMERA_MODE					= C_MISC_BEGIN+5;	// 摄像头模式
    //= 00：广角 	= 01：标准	= 02：俯角 	= 03： 全广角
//	public static final int C_SET_PARKTRACK					= C_MISC_BEGIN+6;	// 摄像头模
//	public static final int C_SET_REMINDER					= C_MISC_BEGIN+7;	// 泊车轨迹模式
    public static final int C_CANBUS_FRAME_TO_MCU			= C_MISC_BEGIN+8;	//
    public static final int C_AIR_WINDOW_ENABLE				= C_MISC_BEGIN+9;	// 空调显示开关
    public static final int C_DOOR_WINDOW_ENABLE			= C_MISC_BEGIN+10;	// 车门显示开关
    //[注释：]索航提某些含SYNC的协议，装在无SYNC的车上，导致无声，UI根据这个开关，来确定是否切原车SYNC和USB通道，默认开
    public static final int C_ONSTAR_SYNC_ON				= C_MISC_BEGIN+11;	// OnStar/SYNC开关
    public static final int C_CANBUS_FRAME_TO_MTU			= C_MISC_BEGIN+12;	// 名途原车屏协议


    //-------------------------------------------------------------------------------------------------
    // 注意所有GET_CODE都在同一个区域内，不要越界
    public static final int G_MISC_BEGIN					= 1000;
    // PARAM new int[]{index}
    public static final int G_MCU_CANBUS_SUPPORT			= G_MISC_BEGIN+0;	// 获取MCU支持的CANBUS

    //-------------------------------------------------------------------------------------------------
    // 注意所有UPDATE_CODE都在同一个区域内，不要越界
    public static final int U_MISC_BEGIN					= 1000;
    public static final int U_CANBUS_ID						= U_MISC_BEGIN+0;
    // 空调窗口使能 (有些人不想被动弹出空调窗口,此处为一选项)
    public static final int U_AIR_WINDOW_ENABLE				= U_MISC_BEGIN+1;	// AIR弹窗使能
    // 车门窗口使能(有些人不想被动探出车门窗口,此处为一选项)
    public static final int U_DOOR_WINDOW_ENABLE			= U_MISC_BEGIN+2;	// DOOR弹窗使能
    public static final int U_DRIVER_ON_RIGHT				= U_MISC_BEGIN+3;	// 司机是否在右边
    public static final int U_EXIST_DOOR					= U_MISC_BEGIN+4;	// 是否有车门
    // CANBUS版本号 PARAM:new String[]{VER}
    public static final int U_CANBUS_VER					= U_MISC_BEGIN+5;	// CANBUS版本信息
    public static final int U_TIP_ID						= U_MISC_BEGIN+6;	// 提示(主要是提示当前协议不支持等信息)
    public static final int U_EXIST_AIR						= U_MISC_BEGIN+7;	// 是否有空调
    public static final int U_SHOW_AIR_WINDOW				= U_MISC_BEGIN+8;	// 显示空调(来自屏幕按钮的调用)
    public static final int U_EXIST_PANORAMA				= U_MISC_BEGIN+9;	// 是否有全景摄像头
    public static final int U_MCU_CANBUS_SUPPORT_CNT		= U_MISC_BEGIN+10;	// MCU支持的CANBUS总数
    public static final int U_SHOW_DOOR_WINDOW				= U_MISC_BEGIN+11;	// 显示车门(来自屏幕按钮的调用)
    public static final int U_EXIST_TEMP_OUT				= U_MISC_BEGIN+12;	// 是否存在外温
    public static final int U_CAMERA_MODE					= U_MISC_BEGIN+13;	// 摄像头模式
    public static final int U_AIR_CONTROL_PAGE				= U_MISC_BEGIN+14;	// 空调控制界面
    public static final int U_CAR_BT_ON						= U_MISC_BEGIN+15;	// 原车蓝牙状态，MCU返回的
    public static final int U_EXIST_CAR_RADIO				= U_MISC_BEGIN+16;	// 是否存在原车收音机
    public static final int U_RIGHT_CAMERA_ON_OFF			= U_MISC_BEGIN+17;	// 原车右视频打开
    public static final int U_EXIST_AIR_CONTROL				= U_MISC_BEGIN+18;	// 是否有空调控制，浮动按钮、主界面根据这个显示小风扇
    public static final int U_CANBUS_FRAME_TO_UI			= U_MISC_BEGIN+19;
    public static final int U_RIGHT_CAMERA_STATE			= U_MISC_BEGIN+20;	// 原车右视频状态
    public static final int U_ORI_CARBACK					= U_MISC_BEGIN+21;
    public static final int U_ONSTAR_SYNC_ON				= U_MISC_BEGIN+22;	// OnStar/SYNC开关
    public static final int U_CANBUS_AIR_VER				= U_MISC_BEGIN+23;	// 空调盒版本 {int[]{value}, null, String[]{"version"}} value = 1时显示
    public static final int U_CANBUS_FRAME_TO_MTU			= U_MISC_BEGIN+24;	// 名途原车屏协议

    public static final int U_CANBUS_SLAVECAR_TOUCH_CALI 	= U_MISC_BEGIN + 25; // 使用原车触摸 // 1 : 使用原车触摸, 0 :使用主机触摸校准
    public static final int U_CANBUS_SLAVECAR_BACKLIGHT	= U_MISC_BEGIN + 26; // 使用原车显示, 背光选项隐藏 1:主机背光调节功能隐藏  0: 允许主机背光调节
    public static final int U_CANBUS_PM25_CAR_IN				= U_MISC_BEGIN+27;//车内PM2.5值
    public static final int U_CANBUS_PM25_CAR_OUT				= U_MISC_BEGIN+28;//车外PM2.5值
    public static final int U_CANBUS_PM25_ENABLE				= U_MISC_BEGIN+29;// PM2.5 //0隐藏 ，1显示

    public static final int U_CUR_SPEED						= U_MISC_BEGIN+31;// 车速
    public static final int U_ENGINE_SPEED					= U_MISC_BEGIN+32;// 转速
    public static final int U_SHOW_BACKCAR_HOST 		= U_MISC_BEGIN + 30; // 主机是否显示倒车视频, 0: 需要, 1: 不需要,只显示倒车视角按键

    //= 00： 广角
    //= 01： 标准
    //= 02： 俯角
    //= 03： 全广角
    public static final int U_CNT_MAX						= 1200;

    public static final int TEMP_LOW						= -1;
    public static final int TEMP_HIGH						= -2;
    public static final int TEMP_NONE						= -3;
    //-------------------------------------------------------------------------------------------------
    public static final int TIP_CANBUS_UNSUPPORT			= 0;

    public static final int MCU_CANBUS_SUPPORT_CNT_MAX		= 1000;

    //-------------------------------------------------------------------------------------------------
    // 无
    public static final int CAR_NULL_Null					= 0;
    public static final int CAR_GOLF = 1;


}
