package ex.hiworld.constants;

/**
 * Created by APP03 on 2018/6/9.
 */

public class FinalCanbus {

    // 注意所有CMD_CODE都在同一个区域内，不要越界
    public static final int C_MISC_BEGIN					= 1000;
//    public static final int C_CANBUS_ID						= C_MISC_BEGIN+0;	// (car_id<<16|canbus_id)
//    public static final int C_DRIVER_ON_RIGHT				= C_MISC_BEGIN+1;	// 司机在右边
//    // 显示空调弹窗(主要用于屏幕按钮) PARAM new int[]{0:临时显示,n秒后自动消失}
//    public static final int C_SHOW_AIR_WINDOW				= C_MISC_BEGIN+2;
//    // PARAM new int[]{0}
    public static final int C_CHANGE_PANORAMA				= C_MISC_BEGIN+3;	// 切换全景摄像头
//    // PARAM new int[]{0:临时显示,n秒后自动消失}
//    public static final int C_SHOW_DOOR_WINDOW				= C_MISC_BEGIN+4;	// 显示车门弹窗
    public static final int C_CAMERA_MODE					= C_MISC_BEGIN+5;	// 摄像头模式
//    //= 00：广角 	= 01：标准	= 02：俯角 	= 03： 全广角
////	public static final int C_SET_PARKTRACK					= C_MISC_BEGIN+6;	// 摄像头模
////	public static final int C_SET_REMINDER					= C_MISC_BEGIN+7;	// 泊车轨迹模式
//    public static final int C_CANBUS_FRAME_TO_MCU			= C_MISC_BEGIN+8;	//
//    public static final int C_AIR_WINDOW_ENABLE				= C_MISC_BEGIN+9;	// 空调显示开关
//    public static final int C_DOOR_WINDOW_ENABLE			= C_MISC_BEGIN+10;	// 车门显示开关
//    //[注释：]索航提某些含SYNC的协议，装在无SYNC的车上，导致无声，UI根据这个开关，来确定是否切原车SYNC和USB通道，默认开
//    public static final int C_ONSTAR_SYNC_ON				= C_MISC_BEGIN+11;	// OnStar/SYNC开关
//    public static final int C_CANBUS_FRAME_TO_MTU			= C_MISC_BEGIN+12;	// 名途原车屏协议
    

    public static final int C_HW_BEGIN					= 2000;
    //0: null, 0x20: CARRADIO , 0x21:CARUSB, 0x22:CARBTPHONE
    public static final int C_HW_CMD_SOUND_CHANNEL		= C_HW_BEGIN + 1;
    public static final int C_HW_CHANGE_PANORAMA		= C_HW_BEGIN + 2;	// 切换全景摄像头
    public static final int C_HW_CAMERA_MODE			= C_HW_BEGIN + 3;	// 摄像头模式
    public static final int C_HW_CMD_UPDATE_MODE		= C_HW_BEGIN + 4;   // 进入升级模式 0 / 1
    public static final int C_HW_SCREEN_TOUCH			= C_HW_BEGIN + 5;	// 屏幕坐标


    public static final int U_HW_BEGIN					= 2000;
    // val1 val2
    // 1: 浮动按钮  , val2: 0/1
    // 2: 全景视图按键 , val2 : 0/1
    // 
    public static final int U_HW_EXIST_FULLVIEW			= U_HW_BEGIN + 1;  
    public static final int U_HW_CHANGE_PANORAMA		= U_HW_BEGIN + 2;	// 切换进全景摄像头
    public static final int U_HW_CAMERA_MODE			= U_HW_BEGIN + 3;	// 摄像头模式
    public static final int U_HW_CMD_UPDATE_MODE		= U_HW_BEGIN + 4;   // 进入升级模式 0 / 1

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
    public static final int U_CANBUS_SLAVECAR_BACKLIGHT		= U_MISC_BEGIN + 26; // 使用原车显示, 背光选项隐藏 1:主机背光调节功能隐藏  0: 允许主机背光调节
    public static final int U_CANBUS_PM25_CAR_IN				= U_MISC_BEGIN+27;//车内PM2.5值
    public static final int U_CANBUS_PM25_CAR_OUT				= U_MISC_BEGIN+28;//车外PM2.5值
    public static final int U_CANBUS_PM25_ENABLE				= U_MISC_BEGIN+29;// PM2.5 //0隐藏 ，1显示

    public static final int U_CUR_SPEED						= U_MISC_BEGIN+31;// 车速
    public static final int U_ENGINE_SPEED					= U_MISC_BEGIN+32;// 转速
    public static final int U_SHOW_BACKCAR_HOST 			= U_MISC_BEGIN + 30; // 主机是否显示倒车视频, 0: 需要, 1: 不需要,只显示倒车视角按键

    //= 00： 广角
    //= 01： 标准
    //= 02： 俯角
    //= 03： 全广角
    public static final int U_CNT_MAX						= 2200;

    public static final int TEMP_LOW						= -1;
    public static final int TEMP_HIGH						= -2;
    public static final int TEMP_NONE						= -3;
    //-------------------------------------------------------------------------------------------------
    public static final int TIP_CANBUS_UNSUPPORT			= 0;

    public static final int MCU_CANBUS_SUPPORT_CNT_MAX		= 1000;

    //-------------------------------------------------------------------------------------------------
    // 无

	public static final int CAR_NONE_CARTYPE 					= 0;//没有车系
  //车系 
  //25-49丰田车
	public static final int CAR_TOYOTA_ALL 				= 1;//丰田全兼容
	public static final int CAR_TOYOTA_PRADO 			= 1 << 16 | 1;//霸道
	public static final int CAR_TOYOTA_REIZ 			= 2 << 16 | 1;//锐志
	public static final int CAR_TOYOTA_RAV4 			= 3 << 16 | 1;//RAV4
	public static final int CAR_TOYOTA_CAMRY 			= 4 << 16 | 1;//凯美瑞
	public static final int CAR_TOYOTA_COROLLA 			= 5 << 16 | 1;//卡罗拉
  
   
	public static final int CAR_HONDA_ALL 				= 2;//本田全兼容
	public static final int CAR_HONDA_CRIDER 			= 1 << 16 | 2;//15款凌派
	public static final int CAR_HONDA_JADE 				= 2 << 16 | 2;//杰德
	public static final int CAR_HONDA_FIT 				= 3 << 16 | 2;//飞度
	public static final int CAR_HONDA_ODYSSEY 			= 4 << 16 | 2;//15款奥德赛
	public static final int CAR_HONDA_VEZEL 			= 5 << 16 | 2;//缤智
	public static final int CAR_HONDA_XRV 				= 6 << 16 | 2;//XR-V
	public static final int CAR_HONDA_GREIZ 			= 7 << 16 | 2;//哥瑞
	public static final int CAR_HONDA_CITY 				= 8 << 16 | 2;//锋范
	public static final int CAR_HONDA_CRIDER2016 		= 9 << 16 | 2;//16款凌派
	public static final int CAR_HONDA_CIVIC2012 		= 10 << 16 | 2;//2012款思域
	public static final int CAR_HONDA_CIVIC2014_L 		= 11 << 16 | 2;//2014款思域(低配)
	public static final int CAR_HONDA_CRV2012 			= 12 << 16 | 2;//2012款CRV
	public static final int CAR_HONDA_CRV2015_L 		= 13 << 16 | 2;//2015款CRV(低配)
	public static final int CAR_HONDA_CIVIC2014_H 		= 14 << 16 | 2;//2014款思域(高配)
	public static final int CAR_HONDA_CRV2015_M 		= 15 << 16 | 2;//2015款CRV(中高配)
	public static final int CAR_HONDA_ACCORD_M 			= 16 << 16 | 2;//九代雅阁中高配
	public static final int CAR_HONDA_ACCORD_L91 		= 17 << 16 | 2;//九代雅阁低配
	public static final int CAR_HONDA_ACCORD_L92 		= 18 << 16 | 2;//九代雅阁低配
	public static final int CAR_HONDA_ACCORD_L93 		= 19 << 16 | 2;//九代雅阁低配
	public static final int CAR_HONDA_ODYSSEY2008 		= 20 << 16 | 2;//08款奥德赛
	public static final int CAR_HONDA_17Crv 			= 21 << 16 | 2;// 17Crv
	  
	 //75-99福特车
	public static final int CAR_FORD_ALL				= 3;//福特全兼容
	public static final int CAR_FORD_FOCUS				= 1 << 16 | 3;//经典福克斯
	public static final int CAR_FORD_MONDEO_L			= 2 << 16 | 3;//老蒙迪欧(低配)
	public static final int CAR_FORD_MIKES				= 3 << 16 | 3;//麦柯斯
	public static final int CAR_FORD_MONDEO_H			= 5 << 16 | 3;//2013款蒙迪欧(高配)
	public static final int CAR_FORD_FIESTA				= 6 << 16 | 3;//嘉年华
	public static final int CAR_FORD_EXPEDITION			= 7 << 16 | 3;//远征
	public static final int CAR_FORD_F500				= 8 << 16 | 3;//F500
	public static final int CAR_FORD_FOCUS2012			= 9 << 16 | 3;//2012款福克斯
	public static final int CAR_FORD_FOCUS2015			= 10 << 16 | 3;//2015款福克斯
	public static final int CAR_FORD_EXPLOROR			= 11 << 16 | 3;//探险者
	public static final int CAR_FORD_EDGE				= 12 << 16 | 3;//锐界
	public static final int CAR_FORD_FIESTA2013			= 13 << 16 | 3;//2013款嘉年华
	public static final int CAR_FORD_ECOSPORT			= 14 << 16 | 3;//翼搏
	public static final int CAR_FORD_KUGA				= 15 << 16 | 3;//翼虎
	public static final int CAR_FORD_ESCAPE				= 16 << 16 | 3;//Escape
	public static final int CAR_FORD_CMAX				= 17 << 16 | 3;//C-MAX
	public static final int CAR_FORD_EROPEFOCUS			= 18 << 16 | 3;//欧版福克斯
	public static final int CAR_FORD_MONDEO_NEW			= 19 << 16 | 3;//新蒙迪欧
	public static final int CAR_FORD_EDGE2015			= 20 << 16 | 3;//15款锐界
	   
	//100-124日产车 
	public static final int CAR_NISSAN_ALL				= 4;//日产全兼容
	public static final int CAR_NISSAN_TEANA			= 1 << 16 | 4;//天籁
	public static final int CAR_NISSAN_ALTIMA			= 2 << 16 | 4;//Altima
	public static final int CAR_NISSAN_XTRAIL			= 3 << 16 | 4;//奇骏
	public static final int CAR_NISSAN_ROGUE			= 5 << 16 | 4;//ROGUE
	public static final int CAR_NISSAN_QASHQAI			= 6 << 16 | 4;//逍客
	public static final int CAR_NISSAN_MURANO			= 7 << 16 | 4;//楼兰
	public static final int CAR_NISSAN_BLUEBIRD			= 8 << 16 | 4;//蓝鸟
	public static final int CAR_NISSAN_PATROL			= 9 << 16 | 4;//途乐
	public static final int CAR_NISSAN_OLDTEANA			= 10 << 16 | 4;//老款天籁
	public static final int CAR_NISSAN_TEANA2008		= 11 << 16 | 4;//08款天籁 

	//125-149 PSA全兼容
	public static final int CAR_PSA_ALL					= 5;//PSA全兼容
	public static final int CAR_PSA_CQUATRE				= 1 << 16 | 5;//世嘉
	public static final int CAR_PSA_C4					= 2 << 16 | 5;//C4
	public static final int CAR_PSA_C4L					= 3 << 16 | 5;//C4L
	public static final int CAR_PSA_C5					= 5 << 16 | 5;//C5
	public static final int CAR_PSA_2015C5				= 6 << 16 | 5;//2013款C5
	public static final int CAR_PSA_307					= 7 << 16 | 5;//307
	public static final int CAR_PSA_308					= 8 << 16 | 5;//308
	public static final int CAR_PSA_408					= 9 << 16 | 5;//408
	public static final int CAR_PSA_508_L				= 10 << 16 | 5;//低配508
	public static final int CAR_PSA_508_H				= 11 << 16 | 5;//高配508
	public static final int CAR_PSA_3008				= 12 << 16 | 5;//3008全拆
	public static final int CAR_PSA_DS5					= 13 << 16 | 5;//DS5
	public static final int CAR_PSA_DS5LS				= 14 << 16 | 5;//DS5LS
	public static final int CAR_PSA_2008				= 15 << 16 | 5;//2008
	public static final int CAR_PSA_DS4					= 16 << 16 | 5;//DS4
	public static final int CAR_PSA_308S				= 17 << 16 | 5;//2014款408/308S
	public static final int CAR_PSA_3008Little			= 18 << 16 | 5;//3008留小屏
	public static final int CAR_PSA_301					= 19 << 16 | 5;//301、爱丽舍
	public static final int CAR_PSA_C3XR				= 20 << 16 | 5;//C3-XR
	public static final int CAR_PSA_4008				= 21 << 16 | 5;//17款4008
	public static final int CAR_PSA_508_AMP				= 22 << 16 | 5;//508带功放
	public static final int CAR_PSA_DS5_H				= 23 << 16 | 5;//DS5 High
	public static final int CAR_PSA_DS5LS_H				= 24 << 16 | 5;//DS5LS High
	public static final int CAR_PSA_DS4_H				= 25 << 16 | 5;//DS4 High
	
	  
	//150-174大众全兼容 
	public static final int CAR_VWF0 					= 6;//大众全兼容
	public static final int CAR_VWFO_GOLF7 				= 1 << 16 | 6;//高尔夫7
	public static final int CAR_VWFO_LAVIDA 			= 2 << 16 | 6;//大众全兼容(72、73)
	public static final int CAR_VWFO_TOUAREG 			= 3 << 16 | 6; //大众全兼容(81、82)
	public static final int CAR_VWFO_TIGUAN 			= 4 << 16 | 6;//途锐

	public static final int CAR_GM_ALL 					= 7;//通用全兼容
	public static final int CAR_GM_AVEO 				= 1 << 16 | 7;//爱唯欧、克鲁兹、英朗、威朗、迈锐宝、
	public static final int CAR_GM_LACROSSE 			= 2 << 16 | 7;//君越、GL8
	public static final int CAR_GM_ENCORE 				= 3 << 16 | 7;//昂科拉
	public static final int CAR_GM_MALIBU 				= 4 << 16 | 7;//ATS、14君越
	public static final int CAR_GM_GL6					= 5 << 16 | 7;//GL6
	
	public static final int CARTYPE_BMW = 8;//宝马
	
	public static final int CAR_HYUNDAI_ALL = 9;//现代
	
	public static final int CARTYPE_MAZDA = 0x0A;//马自达
	public static final int CARTYPE_BENZ = 0x0B;//奔驰
	public static final int CARTYPE_BAIC = 0x0C;//北汽
	public static final int CARTYPE_SAIC = 0x0D;//上汽
	public static final int CARTYPE_GAIG = 0x0E;//广汽
	public static final int CARTYPE_FAW = 0x0F;//一汽
	public static final int CARTYPE_GEELY = 0x10;//吉利
	public static final int CARTYPE_GWM = 0x11;//长城
	public static final int CARTYPE_LIFAN = 0x12;//力帆
	public static final int CARTYPE_CHANA = 0x13;//长安
	public static final int CARTYPE_CHERY  = 0x14;//奇瑞
	public static final int CARTYPE_AUDI = 0x15;//奥迪
	public static final int CARTYPE_BRILLIANCE = 0x16;//华晨宝马
	public static final int CARTYPE_BENZGEN = 0x17;//奔驰GEN
	public static final int CARTYPE_VOLVO = 0x18;//沃尔沃
	public static final int CARTYPE_BAOJUN = 0x19;//宝骏
	public static final int CARTYPE_MITSUBISHI = 0x1A;//三菱
	public static final int CARTYPE_FIAT = 0x1B;//菲亚特
	public static final int CARTYPE_HAIMA = 0x1E;//海马
	public static final int CARTYPE_JAC = 0x1F;//江淮
	public static final int CARTYPE_ZT = 0x20;//众泰
	public static final int CARTYPE_ZH = 0x21;//中华
	public static final int CARTYPE_LANDROVER = 0x22;//陆虎
	public static final int CARTYPE_PORSCHE = 0x23;//保时捷
	public static final int CARTYPE_JEEP = 0x24;//吉普
	public static final int CARTYPE_QCAR = 0x25;//猎豹
	public static final int CARTYPE_DF = 0x26;//东风
	public static final int CARTYPE_MUSTANG = 0x27;//川汽
	public static final int CARTYPE_BYD = 0x28;//比亚迪
	public static final int CARTYPE_BISU = 0x29;//比速

}
