package com.hiworld.canbus.util;

import com.hiworld.mycar.t11.R;


/**
* 常量
* Copyright: Hiworld
* Author: Hardy.lai
* Date: 10/22/2015
*/
public class ConstData {
	//共享文件名
	public static final String SHAREDPREFERENCES_NAME = "htpms_pref";
	//勾选服务条款名
	public static final String ISAGREESERVER = "isAgreeServer";
	//勾选功能简介名
	public static final String ISGUIDE = "isGuide";
	//是否支持该胎压
	public static final String ISAGREETPMS = "isAgreeTpms";
	//灵敏度
	public static final String INSENITIVITY = "inSenitivity";
	//路况
	public static final String INTRAFFIC = "inTraffic";
	//数据帧头
	public static final byte PROTOCAL_HEAD1 = (byte) 0x5a;
	public static final byte PROTOCAL_HEAD2 = (byte) 0xa5;
	
	public static final String ACTION_TO_HIDE_HTPMS  = "com.youzi.intent.action.ACTION_TO_HIDE_HTPMS";

	//传递序列化数据
	public static final int MESSAGE_TIRE_FAILURE = 1;
	public static final int MESSAGE_TPMS_SERIAL_HIGHSPEED = 2;
	public static final int MESSAGE_TPMS_SWHEEL = 3;
	public static final int MESSAGE_TPMS_CONNECT = 4;
	public static final int MESSAGE_WARNTIPS = 4;
	
	//轮胎转速分段等级
	public static final int WHEEL_SPEED_LEVEL1 = 10;
	public static final int WHEEL_SPEED_LEVEL2 = 30;
	public static final int WHEEL_SPEED_LEVEL3 = 50;
	public static final int WHEEL_SPEED_LEVEL4 = 80;
	
	//轮胎柱状图等级偏差值
	public static final int WHEEL_SCHEDULE_LEVEL1 = 1;
	public static final int WHEEL_SCHEDULE_LEVEL2 = 2;
	public static final int WHEEL_SCHEDULE_LEVEL3 = 3;
	
    public static final int HANDLER_MESSAGE_CARPC = 100;//handler传递数据
	//灯光
	public static final int HANDLER_LEFTLIGHT = 200;//左转向灯
	public static final int HANDLER_RIGHTLIGHT = 201;//右转向灯
	public static final int HANDLER_DOUBLELIGHT = 202;//双闪灯
	
	
	//CMDID
	public static final int CMDID_0XD1 = 0xD1;//车辆基本信息
	public static final int CMDID_0XD2 = 0xD2;//车辆状态信息
	public static final int CMDID_0XD3 = 0xD3;//车辆行驶信息
	public static final int CMDID_0XD4 = 0xD4;//车辆倒车信息
	public static final int CMDID_0XD5 = 0xD5;//娱乐
	public static final int CMDID_0XD6 = 0xD6;//报警
	public static final int CMDID_0XD7 = 0xD7;//雷达信息
	public static final int CMDID_0XD8 = 0xD8;//里程信息
	public static final int CMDID_0XD9 = 0xD9;//功能使能
	
	public static final int CMDID_0XC1 = 0xC1;//车辆基本信息
	public static final int CMDID_0XC2 = 0xC2;//车辆状态信息
	public static final int CMDID_0XC3 = 0xC3;//车辆行驶信息
	public static final int CMDID_0XC4 = 0xC4;//车辆倒车信息
	public static final int CMDID_0XC7 = 0xC7;//雷达信息
	public static final int CMDID_0XC8 = 0xC8;//里程信息
	public static final int CMDID_0XC9 = 0xC9;//功能使能
	public static final int CMDID_0X48 = 0x48;//胎压
	public static final int CMDID_0X49 = 0x49;//胎压
	public static final int CMDID_0X50 = 0x50;
	public static final int CMDID_0XE0 = 0xE0;//设备
	public static final int CMDID_0XF3 = 0xF3;//版本号
	public static final int CMDID_0XFF = 0xFF;//胎压假ID

  //摄像头预览位置
  	public static final int LEFT = 0;//全屏
  	public static final int CENTER = 1;//480*480
  	public static final int RIGHT = 2;//隐藏
  	
  	public static final int MSG_GET_FUEL = 0X2EB;//油量不足
	public static final int MSG_SLIDE_MODE = 0X2EC;//摄像头预览位置
	public static final int MSG_UART_NODATA = 0X2ED;//串口卡顿
	public static final int MSG_GAODE_NAVI = 0X2EE;//高德地图
	public static final int MSG_GPS_FX = 0X2EF;//GPS方向
	public static final int MSG_GPS_SPEED = 0X2F0;//GPS速度
	public static final int MSG_GET_TRIED = 0X2FA;//显示5S
	public static final int MSG_GET_COFFIE = 0X2FB;//疲劳驾驶
	public static final int MSG_GET_WARNINFO = 0X2FC;//报警信息
	public static final int MSG_GET_DOG = 0X2FD;//电子狗数据
	public static final int MSG_GET_SAFEBELT = 0X2FE;//安全带效果
	public static final int MSG_GET_DOUBLELIGHT = 0X2FF;//双闪效果
	public static final int MSG_GET_SYSTEMTIME = 0X300;//获取系统时间
	public static final int MSG_GET_CARPC = 0X301;//行车电脑数据
	public static final int MSG_GET_TPMS = 0X302;//胎压数据
	public static final int MSG_SHOW_NAVI = 0X303;//导航显示
	
	//1、电子狗告警信息
//  	public static final String ACTION_EDOG_INFO = "ime.service.intent.action.ALARMINFO";
	public static final String ACTION_EDOG_INFO = "ACTION_OVER_SPEED";
	//2、服务上传报警信息
	public static final String WARN_TO＿APP = "com.hiworld.broadcast.warn.toapp";
	//3：高德地图广播
	public static final String ACTION_GAODE_INFO ="AUTONAVI_STANDARD_BROADCAST_SEND";
	public static final String MACHINE_SETTING  ="ime.intent.action.nlu.MACHINE_SETTING";
	//退出倒车模式
	public static final String ACTION_EXIT_REVERSE = "youzi.service.intent.action.ACTION_EXIT_REVERSE";
	//摄像头预览
	public static final String RECORDER_PREVIEW_SLIDE_MODE = "ime.smart.driving.recorder.preview.slide.mode";
	//hud版本
	public static final String HUD_VERSION_NAME = "com.hiworld.htpms.hud.versioninfo";
	//电子狗开关状态
	public static final String ACTION_EDOG_STATUS = "com.spreadwin.edog.status";
	//电子狗开关状态查询
	public static final String ACTION_EDOG_STATUS_REQUEST = "com.spreadwin.edog.status.request";
	
	public static final String ACTION_ILL = "com.hiworld.auto.ill";
	
	public static final String ACTION_VOICE_WARN = "com.hiworld.broadcast.warn.toapp";
	public static final String ACTION_GAODE_SEND = "AUTONAVI_STANDARD_BROADCAST_RECV";
	
	public static final int[] VOICE_WARN = {R.raw.tpms_lf,//0
		R.raw.tpms_rf,//1
		R.raw.tpms_lr,//2
		R.raw.tpms_rr,//3
		R.raw.safebelt,//4
		R.raw.door_lf,//5
		R.raw.door_rf,//6
		R.raw.door_lr,//7
		R.raw.door_rr,//8
		R.raw.handbrake,//9
		R.raw.oil_low,//10
		R.raw.coolwater,//11
		R.raw.drive_tired,//12
		R.raw.trunk,//13
		R.raw.bonnet,//14
		R.raw.left,R.raw.right};
}
