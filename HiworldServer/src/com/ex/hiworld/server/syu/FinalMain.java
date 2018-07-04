/**
 * 版权：深圳深青联科技有限公司
 * 设计:	 柯华栋
 * 代码：深青联研发部/Android组
 * 日期：2015年1月1日
 */

package com.ex.hiworld.server.syu;


public class FinalMain {
	public static final int MODULE_NULL					= 0;
	public static final int MODULE_MAIN					= 1;

	public static final int C_APP_ID					= 0;	// APP_ID,指示当前应用类型
	public static final int C_LAMPLET_BY_TIME			= 1;	// 时间控制小灯
	public static final int C_ANY_KEY_BOOT				= 2;	// 任意键开机
	public static final int C_NAVI_ON_BOOT				= 3;	// 开机启动导航应用
	public static final int C_HANDBRAKE_ENABLE			= 4;	// 允许检测手刹状态
	public static final int C_BACKCAR_RADAR_ENABLE		= 5;	// 允许倒车显示雷达
	public static final int C_BACKCAR_TRACK_ENABLE		= 6;	// 允许倒车显示轨迹
	public static final int C_BACKCAR_MIRROR			= 7;	// 倒车镜象
	public static final int C_OSD_TIME					= 8;	// 视频叠加时间
	public static final int C_NAVI_PACKAGE				= 9;	// 导航应用包名
	// PARAM:new int[]{BRIGHT_LEVEL_XXX or 0~100}
	public static final int C_BRIGHT_LEVEL				= 10;	// 当前背光等级
	// PARAM:new int[]{0~100}
	public static final int C_BRIGHT_LEVEL_DAY			= 11;	// 白天背关等级
	// PARAM:new int[]{0~100}
	public static final int C_BRIGHT_LEVEL_NIGHT		= 12;	// 晚上背光等级
	// PARAM:new int[]{time} <=0:永不黑屏  >=30 自动黑屏时间(单位秒)
	public static final int C_AUTO_BLACK_SCREEN			= 13;	// 自动黑屏
	// PARAM:new String[]{value}
	public static final int C_MCU_SERIAL				= 14;	// MCU序列号
	public static final int C_MCU_POWER_OPTION			= 15;	// MCU电源选项(休眠/关机)
	// PARAM: new int[]{0/1/2}
	public static final int C_BLACKSCREEN				= 16;	// 黑屏
	public static final int C_MCU_ON					= 17;	// MCU开关
	// 默认0:或者null，MCU待机 1：ARM待机，显示待机LOGO.(时钟或者logo等，UI自定义)
	public static final int C_STANDBY					= 18;	// 待机状态
	public static final int C_RESET_ARM_LATER			= 19;	// n秒后复位ARM
	public static final int C_VA_CMD					= 20;	// 语音助手发送的命令
	// PARAM: new int[]{0:清除错误码  1:查询错误码}
	public static final int C_MCU_ERROR_CODE			= 21;	// MCU错误码
	// PARAM: new int[]{value1, value2}
	// value1谁发送的请求:VIDEO_ID_XXX (参见下面的常量表)
	// value2请求切到哪路视频: VIDE_ID_NULL/VIDEO_ID_XXX
	public static final int C_VIDEO_ID					= 22;
	// PARAM: new int[]{APP_ID, TYPE} new String[]{value}
	// type:0:ID3_TITLE, 1:ID3_ARTIST 2:ID3_ALBUM
	// 3:PLAY_STATUS(ints[2]:PLAYER_CMD_XXX)
	// 4:曲目信息(ints[2]:curr;ints[3]:total 第一首为0)
	// 5:曲目播放时间信息(ints[2]:curr;ints[3]:total 单位秒)
	// 6:循环模式：1：不循环 2：单曲循环 3：全部循环
	// 7:随机模式：0：不随机 1：随机
	// 8:文件夹名称new int[]{APP_ID, TYPE} new String[]{value}
	// 9:播放设备类型0x01:USB 0x02:CARD 0x03:Other
	// 10:播放媒体类型0x01:音乐,0x02:视频,0x03:图片
	public static final int C_PLAY_INFO					= 23;	// 播放信息
	// PARAM: new int[]{PAGE_XXX}
	public static final int C_JUMP_PAGE					= 24;	// 进入某界面
	// PARAM: new int[]{KeyEvent.KEYCODE_XXX}
	public static final int C_KEY						= 25;
	// PARAM: new int[]{Language_xxx}
	public static final int C_LANGUAGE					= 26;
	// PARAM new int[]{TYPE} new String[]{value}
	// type:0 filePath, 1:reboot 2:带MCU文件开始位置和结束位置的升级方式int[]{2,startAddr,endAddr}
	public static final int C_MCU_UPGRADE				= 27;
	public static final int C_BACKCAR_TYPE				= 28;	// 倒车类型 0:主机倒车 1:原车倒车
	// PARAM new int[]{直接设置面板类型的值} // 直接设定类型 15/12/18
	public static final int C_PANEL_KEY_TYPE			= 29;	// 面板按键类型
	public static final int C_LAMPLET_ON_BOOT			= 30;	// 开机时,面板灯亮
	public static final int C_LAMPLET_ON_ALWAYS			= 31;	// 面板灯一直亮
	public static final int C_LAMPLET_COLOR_CTRL		= 32;	// 面板灯颜色控制开关
	public static final int C_PANORAMA_ON				= 33;	// 全景开关
	public static final int C_FAN_CYCLE					= 34;	// 风扇转动周期 0x00:不转 0xFF:常转 其他:转动周期(单位:分钟)
	// PARAM: new int[]{index, value0/1}
	public static final int C_MIRROR_UP_DOWN			= 35;	// 上下镜像开关，0:AV1镜像；1:AV2镜像；2:AV3镜像；
	// PARAM: new int[]{appId, brightness, color, contrast}
	public static final int C_VIDEO_IMAGE				= 36;	// 设置视频亮度/色度/对比度
	public static final int C_OUT_BACKCAR				= 37;	// UI请求退出倒车    0:进入倒车 1:退出倒车
	public static final int C_FACTORY_RESET				= 38;	// 恢复出厂设置
	public static final int C_GUESTURE					= 39;	// 手势学习及开关
	public static final int C_HOST_BACKCAR_ENABLE		= 40;	// 本地倒车开关1：本机倒车 0：原车视频倒车/原车倒车
	// PARAM new String[]{key, value},注意,只允许设置sys.xxx属性(即临时系统属性)
	public static final int C_SYSTEM_PROPERTIES			= 41;	// 系统属性
	public static final int C_CUTACC_TURNOFF_LCDC		= 42;	// 断ACC关屏开关，默认关
	public static final int C_ECARLINK_ON				= 43;	// 亿连开关
	// PARAM new int[]{视频ID,水平方向起始位置,水平方向大小,垂直方向起始位置,垂直方向大小};
	public static final int C_VIDEO_POSITION			= 44;	// PX3竖屏设置图像显示的位置
	public static final int C_RADAR_POWER				= 45;	// 雷达电源
	// PARAM: new int[]{0/1}
	public static final int C_ENTER_SLEEP_WAKEUP		= 46;	// UI进入/退出休眠唤醒，服务需跟MCU握手通讯
	public static final int C_AUX_ENABLE				= 47;	// AUX使能开关
	public static final int C_SLEEP_AIRPLANE			= 48;	// 休眠前是否进入飞行模式
	public static final int C_LAMPLET_CLEAN_ON			= 49;	// 大灯清洗开关
	// PARAM: new int[]{ints[0],ints[1]}
	// ints[0] = 0:氛围灯开关; ints[1]:0关，1开，2互切
	// ints[0] = 1:氛围灯亮度调节; ints[1]:0亮度减，1亮度加，长按1s发一次此命令
	// ints[0] = 2:灯光颜色选择 ints[1]:具体颜色
	// 0奔放红 //1清新绿//2神秘蓝3//单纯白//4阳光橙//5优雅绿//6梦幻紫//7欢快黄//8单色变幻//9多彩变幻//a单色呼吸//b多彩呼吸//c流行音乐//d柔和音乐//e动感音乐//f DJ音效
	public static final int C_AMBIENT_LIGHT				= 50;	// 氛围灯相关控制
	// 索航PX3一条特殊的命令，改应用需要视频输出发1，退出该应用时发0，用于通知MCU控制是否有图像输出
	public static final int C_VIDEO_OUT_ON				= 51;	// 视频输出通知MCU
	// new int[]{0/1/2}
	public static final int C_SHOW_FLASH_WRITE_ON		= 52;	// 显示各个应用读写Flash次数开关
	public static final int C_ARM_RESET_SELF			= 53;	// 升级黑屏命令,黑屏时间时间3s
	public static final int C_BACKCAR_360_CAMERA		= 54;	// 名途倒车类型 0:主机倒车 1:360全景倒车
	public static final int C_MCU_PANEL_KEY_ENABLE		= 55;	// MCU面板按键开关
	// PARAM: new int[]{MotionEvent.xxx, x, y}
	public static final int C_TOUCH						= 56;	// 触摸坐标
	public static final int C_360CAMERA_BACKCAR_ENABLE	= 57;	// 名途360全景倒车使能开关
	public static final int C_TRUNK_CONTROL_CMD			= 58;	// 后备箱控制命令，无参数
	public static final int C_RADAR_PARK_ENABLE			= 59;	// 泊车使能开关
	public static final int C_RIGHT_CAMERA_STATE		= 60;	// 右视摄像头供电开关，进入右视状态打开，离开关掉
	public static final int C_START_STOP_ENABLE			= 61;	// 启停开关
	// PARAM: new int[]{0：下 1：上}
	public static final int C_MOTOR_DOWN_UP_BYMCU		= 62;	// 屏收缩
	// PARAM: new int[]{0 旋钮 01:三色灯 02:预留，不支持2switch}
	public static final int C_ROLL_KEY_TYPE				= 63;	// 面板三色灯/旋钮选项
	// PARAM: new int[]{LED_COLOR_XXX,见下面的颜色表}
	public static final int C_LED_COLOR					= 64;	// 按键灯颜色
	// USB异常检测开关
	public static final int C_USB_ERROR_ENABLE			= 65;	// USB异常检测开关
	// PARAM: new int[]{value1, value2}
	// value1谁发送的请求:VIDEO_ID_XXX (参见下面的常量表)
	// value2请求切到哪路视频: VIDE_ID_NULL
	public static final int C_VIDEO_STOP_CAMERA			= 66;	// UI停止使用cmaera
	// PARAM: new int[]{见MCU参数表}
	// 0x00 POWER;0X01 返回；0x02 up；0x03 down；0x04 left；0x05 right；0x06 确定；0x07 AV1;0x08 AV3;0x09 AV3;0x0a AV4;
	public static final int C_360_CONTROL				= 67;	// 360控制命令
	public static final int C_TRACK_REVERES_ENABLE		= 68;	// 轨迹反向开关
	// PARAM: new int[]{VIDEO_OUTPUT_xxx, value}
	public static final int C_VIDEO_OUTPUT_PARAMETERS	= 69;	// 视频输出设置
	// STEP1:获取MCU记忆的数据总数 new int[]{MCU_MEMORY_CONTROL_GETTATAL}
	// STEP3:获取MCU记忆的数据 new int[]{MCU_MEMORY_CONTROL_READ，readCnt(读多少个数据)，readStart(读的起始位置)}，注意MCU最大数据量为100，建议一次读50个
	// STEP6:写记忆数据，new int[]{MCU_MEMORY_CONTROL_WRITE, writeCnt(写多少个数据)，writeStart(写的起始位置)， 写的数据}，注意MCU最大数据量为100，建议一次读50个
	public static final int C_MCU_MEMORY_CONTROL		= 70;	// MCU记忆内存读写操作
	public static final int C_MIC_TYPE					= 71;	// MIC类型：0 前麦克 1：后置麦克
	public static final int C_VIDEO_AUX_TV				= 72;	// AUX和TV视频通道换

	public static final int AUTO_BLACK_SCREEN_MIN_TIME	= 30;

	public static final int U_APP_ID					= 0;	// APP_ID
	public static final int U_MCU_ON					= 1;	// MCU开关
	public static final int U_STANDBY					= 2;	// 待机状态
	public static final int U_BLACK_SCREEN				= 3;	// 黑屏状态
	public static final int U_LAMPLET					= 4;	// 小灯状态
	public static final int U_ANY_KEY_BOOT				= 5;	// 任意键开机
	public static final int U_NAVI_ON_BOOT				= 6;	// 开机启动导航
	public static final int U_HANDBRAKE					= 7;	// 手刹状态
	public static final int U_HANDBRAKE_ENABLE			= 8;	// 手刹使能
	public static final int U_EXIST_SD1_ON_ARM			= 9;	// SD1挂载在ARM上
	public static final int U_EXIST_SD2_ON_ARM			= 10;	// SD2挂载在ARM上
	public static final int U_EXIST_USB_ON_ARM			= 11;	// USB挂载在ARM上
	public static final int U_BACKCAR					= 12;	// 倒车中
	public static final int U_RADAR						= 13;	// 显示雷达UI
	public static final int U_RADAR_FL					= 14;	// 雷达信号 前左
	public static final int U_RADAR_FML					= 15;	// 雷达信号 前中左
	public static final int U_RADAR_FMR					= 16;	// 雷达信号 前中右
	public static final int U_RADAR_FR					= 17;	// 雷达信号 前右
	public static final int U_RADAR_RL					= 18;	// 雷达信号 后左
	public static final int U_RADAR_RML					= 19;	// 雷达信号 后中左
	public static final int U_RADAR_RMR					= 20;	// 雷达信号 后中右
	public static final int U_RADAR_RR					= 21;	// 雷达信号 后右
	public static final int U_BACKCAR_RADAR				= 22;	// 倒车显示雷达
	public static final int U_BACKCAR_TRACK_ENABLE		= 23;	// 倒车轨迹
	public static final int U_BACKCAR_MIRROR			= 24;	// 倒车镜像
	// 2015/9/16 TO DO ...
	public static final int U_CUT_ACC_POWER				= 25;	// 断ACC电源方式
	public static final int U_OSD_TIME					= 26;	// 视频显示时间
	public static final int U_BACKCAR_RADAR_ENABLE		= 27;	// 允许倒车显示雷达
	public static final int U_NAVI_PACKAGE				= 28;	// 导航应用包名
	public static final int U_LAMPLET_BY_TIME			= 29;	// 时间控制小灯
	// 2015/9/16 保留
	public static final int U_RESERVE					= 30;
	public static final int U_BRIGHT_LEVEL				= 31;	// 当前背关等级
	public static final int U_BRIGHT_LEVEL_DAY			= 32;	// 白天背光等级
	public static final int U_BRIGHT_LEVEL_NIGHT		= 33;	// 黑夜背关等级
	public static final int U_MCU_VER					= 34;	// MCU版本
	public static final int U_MCU_SERIAL				= 35;	// MCU序列号
	public static final int U_AUTO_BLACK_SCREEN			= 36;	// 自动黑屏 <=0:永不黑屏  >=30 自动黑屏时间(单位秒)
	// PARAM:new int[]{APP_ID, PLAYER_CMD}
	public static final int U_PLAYER_CMD				= 37;	// 播放器命令
	public static final int U_MCU_POWER_OPTION			= 38;	// MCU电源选项
	// PARAM new int[]{0不可见/1可见} new String[]{packageName}
	public static final int U_APP_VISIBILITY			= 39;	// 应用可见性(主要用于Launcher图标显示)
	// bit31~30 显示类型(0:value 1:none 2:low 3:high)
	// bit29 单位(类型为value时有效  0:度C 1:F)
	// bit28~0 value (类型为value时有效 UI需要减1000)
	public static final int U_TEMP_OUT					= 40;	// 外部温度
	public static final int U_STEER_ANGLE				= 41;	// 方向盘角度
	public static final int U_VA_CMD					= 42;	// 转发语音助手命令
	public static final int U_ARM_SLEEP_WAKEUP			= 43;	// 0:ARM休眠 1:ARM起来
	// 2015/9/16 保留
	public static final int U_RESERVE2					= 44;
	public static final int U_TIP						= 45;	// 提示
	public static final int U_MCU_ERROR_CODE			= 46;	// MCU错误码
	public static final int U_BACKCAR_TYPE				= 47;	// 倒车类型 0:主机 1:原车
	public static final int U_TIP_MCU_UPGRADE			= 48;	// MCU升级提示
	public static final int U_ID3_TITLE					= 49;	// ID3_TITLE
	public static final int U_ACC_ON					= 50;	// ACC状态
	public static final int U_PANEL_KEY_TYPE			= 51;	// 面板按键类型
	public static final int U_LAMPLET_ON_BOOT			= 52;	// 开机时,面板灯亮
	public static final int U_LAMPLET_ON_ALWAYS			= 53;	// 面板灯一直亮
	public static final int U_LAMPLET_COLOR_CTRL		= 54;	// 面板灯颜色控制开关
	public static final int U_PANORAMA_ON				= 55;	// 全景开关
	public static final int U_FAN_CYCLE					= 56;	// 风扇转动周期 0x00:不转 0xFF:常转 其他:转动周期(单位:分钟)
	public static final int U_VA_AUDIO_OCCUPIED			= 57;	// 语音助手占用声音 new int[]{0/1}
	public static final int U_MIRROR_UP_DOWN			= 58;	// 上下镜像开关，返回的是int数组int[]{av1,av2.av2}
	public static final int U_MCU_REQUEST_VIDEO			= 59;	// 0:切倒车视频（1） 1：AUX视频（2） 2：切数字电视视频（7）；博悦众恒添加，其他客户可沿用
	// new int[]{MCU_DIEECTION_xxx,见下面的常亮}
	public static final int U_MCU_DIEECTION_KEY			= 60;	// MCU回传的方向按键，UI自行处理
	public static final int U_RESET_ARM_LATER			= 61;	// n秒后复位ARM 注册的时候服务返回-1，无效值
	public static final int U_MCU_BOOT_ON				= 62;	// MCU第一次上电
	public static final int U_PANEL_KEY_TYPE_CNT		= 63;	// 面板按键类型总数
	public static final int U_GUESTURE					= 64;	// 手势学习及开关
	public static final int U_HOST_BACKCAR_ENABLE		= 65;	// 本地倒车开关1：本机倒车 0：原车视频倒车/原车倒车
	public static final int U_CUTACC_TURNOFF_LCDC		= 66;	// 断ACC关屏开关，默认关
	public static final int U_ECARLINK_ON				= 67;	// 亿连开关
	// int[0]:视频ID ints[1]:执行开关Camera操作,如果CAMERA已经是开或者关的状态，则不用操作
	// ints[1]:0关闭CAMERA 1:打开CAMERA 2：其他应用用完CAMERA
	public static final int U_REQUEST_CAMERA			= 68;	// 通知具体的UI打开/关闭CAMERA
	// 视频打开以后开始检测信号，无信号的时候提示，有信号的时候移除
	public static final int U_SIGNAL_ON					= 69;	// 视频信号  0：无信号 1：有信号
	public static final int U_SIGNAL_NTSC_PAL			= 70;	// 视频信号  0：N制  1：P制
	public static final int U_CUTACC_DELAY_CLOSE_SCREEN	= 71;	// 关ACC延迟关闭背光
	// new int[]{-1}全部不支持
	public static final int U_AIR_CONTROL_SUPPORT_CMD	= 72;	// 空调控制支持的命令
	// 0:无AUX 1：有AUX
	public static final int U_AUX_ENABLE				= 73;	// AUX使能开关
	// PARAM: new int[]{APP_ID, TYPE} new String[]{value}
	// type:
	// 0:ID3_TITLE,
	// 1:ID3_ARTIST
	// 2:ID3_ALBUM
	// 3:PLAY_STATUS(ints[2]:PLAYER_CMD_XXX)
	// 4:曲目信息(ints[2]:curr;ints[3]:total 第一首为0)
	// 5:曲目播放时间信息(ints[2]:curr;ints[3]:total 单位秒)
	// 6:循环模式：0：不循环 1：单曲循环 2：全部循环
	// 7:随机模式：0：不随机 1：随机
	// 8:文件夹名称new int[]{APP_ID, TYPE} new String[]{value}
	// 9:播放设备类型0x01:USB 0x02:CARD 0x03:Other
	// 10:播放媒体类型0x01:音乐,0x02:视频,0x03:图片
	public static final int U_PLAY_STATUS				= 74;	// 播放器传过来(通知其他APP)
	public static final int U_RADAR_POWER				= 75;	// 雷达电源
	public static final int U_SLEEP_AIRPLANE			= 76;	// 休眠前是否进入飞行模式
	//new int[]{action, x, y}
	public static final int U_SCREEN_TOUCH_EVENT		= 77;	// 触屏事键
	public static final int U_LAMPLET_CLEAN_ON			= 78;	// 大灯清洗开关
	// PARAM: new int[]{ints[0],ints[1]}
	// ints[0] = 0:氛围灯开关; ints[1]:0关，1开
	// ints[0] = 1:无效
	// ints[0] = 2:灯光颜色 ints[1]:具体颜色
	// 0奔放红 //1清新绿//2神秘蓝3//单纯白//4阳光橙//5优雅绿//6梦幻紫//7欢快黄//8单色变幻//9多彩变幻//a单色呼吸//b多彩呼吸//c流行音乐//d柔和音乐//e动感音乐//f DJ音效
	public static final int U_AMBIENT_LIGHT				= 79;	// 氛围灯相关控制更新
	// new int[]{0/1}
	public static final int U_SHOW_FLASH_WRITE_ON		= 80;	// 显示各个应用读写Flash次数开关
	public static final int U_BACKCAR_360_CAMERA		= 81;	// 名途倒车类型 0:主机倒车 1:360全景倒车
	public static final int U_MCU_PANEL_KEY_ENABLE		= 82;	// MCU面板按键开关
	public static final int U_360CAMERA_BACKCAR_ENABLE	= 83;	// 名途360全景倒车使能开关
	public static final int U_SPI_OSD_VER				= 84;	// SPI-OSD版本
	public static final int U_SPI_MCU_VER				= 85;	// SPI-MCU版本
	public static final int U_RADAR_PARK_ENABLE			= 86;	// 泊车使能开关
	public static final int U_TRUNK_CONTROL_STATE		= 87;	// 后备箱状态
	public static final int U_START_STOP_ENABLE			= 88;	// 启停开关
	// PARAM: new int[]{ints[0]:是否支持 ints[1]0 旋钮 01:三色灯}
	public static final int U_ROLL_KEY_TYPE				= 89;	// 面板三色灯/旋钮选项
	public static final int U_RADAR_RSF 				= 90; 	// 雷达信息: 右侧前
	public static final int U_RADAR_RSMF 				= 91;	// 雷达信息: 右侧中前
	public static final int U_RADAR_RSMB 				= 92;	// 雷达信息: 右侧中后
	public static final int U_RADAR_RSB 				= 93;	// 雷达信息: 右侧后
	public static final int U_RADAR_LSF 				= 94;	// 雷达信息: 左侧前
	public static final int U_RADAR_LSMF 				= 95;	// 雷达信息: 左侧中前
	public static final int U_RADAR_LSMB 				= 96;	// 雷达信息: 左侧中后
	public static final int U_RADAR_LSB 				= 97;	// 雷达信息: 左侧后
	// PARAM: new int[]{ints[0]:AUX1状态 ints[1]AUX2状态}
	public static final int U_CNC_AUX_ENABLE			= 98;	// 韩国CNC-aux状态
	// PARAM: new int[]{LED_COLOR_XXX,见下面的颜色表}
	public static final int U_LED_COLOR					= 99;	// 按键灯颜色
	// USB异常检测开关
	public static final int U_USB_ERROR_ENABLE			= 100;	// USB异常检测开关
	// 从GPS获取的当前车速,单位km/h
	public static final int U_GPS_SPEED					= 101;	// 当前车速
	public static final int U_TRACK_REVERES_ENABLE		= 102;	// 轨迹反向开关
	public static final int U_SYSTEM_SLEEP_STATE		= 103;	// 0:休眠完成 1:正在休眠
	public static final int U_VA_AUDIO_OCCUPIED_TO_APP	= 104;	// 语音助手占用声音 new int[]{0/1}
	// PARAM: new int[]{VIDEO_OUTPUT_xxx, value}
	public static final int U_VIDEO_OUTPUT_PARAMETERS	= 105;	// 视频输出设置
	// STEP2:获取MCU记忆的数据总数结果 new int[]{U_MCU_MEMORY_CONTROL,MCU_MEMORY_CONTROL_GETTATAL, tatalCnt}
	// STEP4:请求读取数据结果 new int[]{MCU_MEMORY_CONTROL_READ，MCU_MEMORY_CONTROL_RESULT_xxx}
	// STEP5:读取数据成功的话，new int[]{MCU_MEMORY_CONTROL_DATA， 回传总数，回传起始位置，回传数据}
	// STEP7:写数据结果 new int[]{MCU_MEMORY_CONTROL_WRITE，MCU_MEMORY_CONTROL_RESULT_xxx}
	public static final int U_MCU_MEMORY_CONTROL		= 106;	// MCU记忆内存读写操作
	public static final int U_MIC_TYPE					= 107;	// MIC类型：0 前麦克 1：后置麦克
	public static final int U_VIDEO_AUX_TV				= 108;	// AUX和TV视频通道换
	public static final int U_CNT_MAX					= 120;

	// APP类型
	public static final int G_APP_ID					= 0;	// APP_ID

	// APP类型
//	public static final int MCU_STATE_LAST				= -2; 	// ?
	public static final int APP_ID_LAST					= -1;	// 上一个应用
	public static final int APP_ID_NULL					= 0;	// 无
	public static final int APP_ID_RADIO				= 1;	// 收音机
	public static final int APP_ID_BTPHONE				= 2;	// 蓝牙电话
	public static final int APP_ID_BTAV					= 3;	// 蓝牙音频
	public static final int APP_ID_DVD					= 4;	// DVD
	public static final int APP_ID_AUX					= 5;	// AUX
	public static final int APP_ID_TV					= 6;	// 电视
	public static final int APP_ID_IPOD					= 7;	// 苹果设备
	public static final int APP_ID_AUDIO_PLAYER			= 8;	// 音频播放器
	public static final int APP_ID_VIDEO_PLAYER			= 9;	// 视频播放器
	public static final int APP_ID_THIRD_PLAYER			= 10;	// 第三方播放器
	public static final int APP_ID_CAR_RADIO			= 11;	// 原车收音机
	public static final int APP_ID_CAR_BTPHONE			= 12;	// 原车蓝牙
	public static final int APP_ID_CAR_USB				= 13;	// 原车USB
	public static final int APP_ID_DVR					= 14;	// DVR
	public static final int APP_ID_3GPHONE				= 15;	// 3G通话
	public static final int APP_ID_SPECIAL				= 16;	// 特殊声音通道，不静音且几个流不能设置为0
	public static final int APP_ID_CNT_MAX				= 20;

	public static final int BRIGHT_LEVEL_STEP_UP		= -1;	// 按步数+
	public static final int BRIGHT_LEVEL_STEP_DOWN		= -2;	// 按步数-
	public static final int BRIGHT_LEVEL_STEP_BY_SERVER	= -3;	// 服务自定义
	public static final int BRIGHT_LEVEL_UP				= -4;	// +1
	public static final int BRIGHT_LEVEL_DOWN			= -5;	// -1

	public static final int PLAYER_CMD_PLAY				= 0;
	public static final int PLAYER_CMD_PLAYPAUSE		= 1;
	public static final int PLAYER_CMD_PAUSE			= 2;
	public static final int PLAYER_CMD_STOP				= 3;
	public static final int PLAYER_CMD_PREV				= 4;
	public static final int PLAYER_CMD_NEXT				= 5;
	public static final int PLAYER_CMD_FF				= 6;
	public static final int PLAYER_CMD_FB				= 7;
	public static final int PLAYER_CMD_NUM				= 8;//0~9

	public static final int MCU_POWER_OPTION_SHUTDOWN	= 0;
	public static final int MCU_POWER_OPTION_SLEEP		= 1;

	public static final int TIP_NO_NAVI_SET				= 0;	// 没有设置导航应用
	public static final int TIP_KEY_POWER				= 1;	// POWER按键被按下，UI提示
	public static final int TIP_BT_ERROR				= 2;	// 系统蓝牙故障提示
	//getSystemProperty("ro.fyt.storage_warnning", 300)获取警告的内存大小，单位M
	//内部存储空间不足xxxM，请删除部分应用或者数据（注意要加不再提示的选项，不然5分钟后会重新弹出界面）
	public static final int TIP_STORAGE_NOT_ENOUGH		= 3;	// 内部存储空间不足，默认300M
	public static final int TIP_DEBUG_MSG				= 4;	// {String[0] = msg}
	// ints[0]:5 ints[1] 0/1
	public static final int TIP_MCU_NEED_UPDATE			= 5;	// MCU需要升级标记（1：要升级 0 ：不用升级）
	public static final int TIP_GPS_ERROR				= 6;	// GPS异常，提示重启机器
	public static final int TIP_MCU_MATCH_ERROR			= 7;	// MCU硬件平台不匹配
	//未检测到USB设备，请确认USB设备是否接好，如线材已确认接好还无法使用USB设备，是否立即重启机器？
	public static final int TIP_USB_ERROR				= 8;	// USB异常，提示重启机器
	public static final int TIP_USB_NOEMAL				= 9;	// USB恢复正常，移除对话框

	public static final int BACKCAR_TYPE_HOST			= 0;	// 主机倒车
	public static final int BACKCAR_TYPE_CAR			= 1;	// 原车倒车

	public static final int PAGE_NAVI					= 0;	// 导航
	public static final int PAGE_RECENT_TASK			= 1;	// 最近任务列表

	public static final int VIDEO_OUTPUT_TYPE				= 0;	//0：普清 800x480 1：高清1024x600
	public static final int VIDEO_OUTPUT_HORIZONTAL_SYNC	= 1;	//水平同步调节
	public static final int VIDEO_OUTPUT_VERTICAL_SYNC		= 2;	//竖直同步调节
	public static final int VIDEO_OUTPUT_HORIZONTAL_POSITION= 3;	//水平位置调节
	public static final int VIDEO_OUTPUT_VERTICAL_POSITION	= 4;	//竖直位置调节
	public static final int VIDEO_OUTPUT_RESET				= 5;	//恢复默认值，发两个参数{5,1}

	public static final int LANG_EN						= 0;	//英文
	public static final int LANG_ZH						= 1;	//简体中文
	public static final int LANG_TW						= 2;	//繁体中文
	public static final int LANG_TR						= 3;	//土耳其
	public static final int LANG_IT						= 4;	//意大利
	public static final int LANG_FR						= 5;	//法文
	public static final int LANG_DE						= 6;	//德文
	public static final int LANG_ES						= 7;	//西班牙
	public static final int LANG_JP						= 8;	//日文
	public static final int LANG_KO = 9;	//韩语
	public static final int LANG_PL						= 10;	//波兰语
	public static final int LANG_RU						= 11;	//俄语
	public static final int LANG_SV 					= 12;	//瑞典
	public static final int LANG_PT						= 13;	//葡萄牙
	public static final int LANG_NO						= 14;	//挪威
	public static final int LANG_FI						= 15;	//芬兰
	public static final int LANG_DA 					= 16;	//丹麦
	public static final int LANG_AR						= 17;	//阿拉伯
	public static final int LANG_NL						= 18;	//荷兰

	public static final int VIDEO_ID_NULL				= 0;	// 关闭视频
	public static final int VIDEO_ID_BACKCAR			= 1;	// 服务使用,UI不要使用
	public static final int VIDEO_ID_AUX				= 2;
	public static final int VIDEO_ID_DVD				= 3;
	public static final int VIDEO_ID_TV_ANALOG			= 4;	// 模拟电视
	public static final int VIDEO_ID_DVR				= 5;
	public static final int VIDEO_ID_RIGHTCAMERA		= 6;
	public static final int VIDEO_ID_TV_DIGIT			= 7;	// 数字电视
	public static final int VIDEO_ID_CAR_VIDEO			= 8;	// 原车视频
	public static final int VIDEO_ID_MAX_CNT			= 10;

	public static final int MCU_DIEECTION_KEY_ENTER		= 0;	// 确认键
	public static final int MCU_DIEECTION_KEY_UP		= 1;	// 上
	public static final int MCU_DIEECTION_KEY_DOWN		= 2;	// 下
	public static final int MCU_DIEECTION_KEY_LEFT		= 3;	// 左
	public static final int MCU_DIEECTION_KEY_RIGHT		= 4;	// 右
	public static final int MCU_DIEECTION_KEY_ROLL_LEFT	= 5;	// 左旋
	public static final int MCU_DIEECTION_KEY_ROLL_RIGHT= 6;	// 右旋

	public static final int MCU_UPGRADE_CMD_TYPE_0_ONEMCU		= 0;	// 单个MCU升级
	public static final int MCU_UPGRADE_CMD_TYPE_1_COMPLTE		= 1;	// MCU升级完成重启
	public static final int MCU_UPGRADE_CMD_TYPE_2_ALLINONE		= 2;	// 多合一MCU升级
	public static final int MCU_UPGRADE_CMD_TYPE_3_STM32		= 3;	// STM32-MCU升级
	public static final int MCU_UPGRADE_CMD_TYPE_4_SPIOSD		= 4;	// SpiOsd-MCU升级TwSpiOsd.bin,path
	public static final int MCU_UPGRADE_CMD_TYPE_5_SPIMCU		= 5;	// SpiMcu-MCU升级TwSpiMcu.bin,path
	public static final int MCU_UPGRADE_CMD_TYPE_6_COMPLTE		= 6;	// SPI-MCU升级完成重启

	public static final int LED_COLOR_BLUE			= 0;	// 蓝色
	public static final int LED_COLOR_RED			= 1;	// 红色
	public static final int LED_COLOR_GREEN			= 2;	// 绿色
	public static final int LED_COLOR_YELLOW		= 3;	// 黄色
	public static final int LED_COLOR_CYAN			= 4;	// 青色
	public static final int LED_COLOR_PURPLE		= 5;	// 紫色
	public static final int LED_COLOR_WHITE			= 6;	// 白色

	public static final int MCU_MEMORY_CONTROL_GETTATAL		= 0;	// MCU记忆总数
	public static final int MCU_MEMORY_CONTROL_READ			= 1;	// 读数据
	public static final int MCU_MEMORY_CONTROL_WRITE		= 2;	// 写数据
	public static final int MCU_MEMORY_CONTROL_DATA			= 3;	// 返回MCU记忆的数据，回传总数，回传起始位置，回传数据

	public static final int MCU_MEMORY_CONTROL_RESULT_SUCCESS		= 0;	// 读写成功
	public static final int MCU_MEMORY_CONTROL_RESULT_ERROR1		= 1;	// 数据不对或多了
	public static final int MCU_MEMORY_CONTROL_RESULT_ERROR2		= 1;	// 当前不支持EEP
}
