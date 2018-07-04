/**
 * 版权：深圳深青联科技有限公司
 * 设计:	 柯华栋
 * 代码：深青联研发部/Android组
 * 日期：2015年1月1日
 */

package com.ex.hiworld.server.syu;

public class FinalDvd {
	public static final int MODULE_NULL					= 0;
	public static final int MODULE_8288					= 1;
	public static final int MODULE_8202					= 2;
	public static final int MODULE_8700					= 3;
	public static final int MODULE_CORRDINATE_TO_MCU	= 4;//UI仅发触摸坐标即可，凯振在用
	public static final int MODULE_KEY_TO_MCU			= 5;//UI仅发按钮事件转发MCU处理

	public static final int C_EJECT						= 0;
	public static final int C_TRACK						= 1;
	public static final int C_MEDIA_TYPE				= 2;
	public static final int C_TOUCH						= 3;
	public static final int C_PLAY_TIME					= 4;
	public static final int C_PLAYPAUSE					= 5;
	public static final int C_PREV						= 6;
	public static final int C_NEXT						= 7;
	public static final int C_FB						= 8;
	public static final int C_FF						= 9;
	public static final int C_PLAY						= 10;
	public static final int C_PAUSE						= 11;
	public static final int C_ZOOM						= 12;
	public static final int C_SOUND						= 13;	// 声道
	public static final int C_MENU						= 14;	// 顶层菜单?
	public static final int C_TITLE						= 15;	// 菜单?
	public static final int C_SUBT						= 16;
	public static final int C_STOP						= 17;
	public static final int C_AREA_AB					= 18; // AB区域重复
	public static final int C_REPEAT					= 19;
	// PARAM: new int[]{OFF/ON/SWITCH:0/1/2}
	public static final int C_RANDOM					= 20;
	public static final int C_DVD_ENABLE				= 21;
	// PARAM new String[]{filePath}
	public static final int C_UPGRADE					= 22;
	public static final int C_ANGLE						= 23;//不带参数
	// PARAN new String[]{DEVICE_TYPE_DISC/DEVICE_TYPE_SD/DEVICE_TYPE_USB}
	public static final int C_DEVICE_TYPE				= 24;
	public static final int C_SETUP						= 25;

	public static final int G_AUDIO_FILE				= 0;
	public static final int G_PHOTO_FILE				= 1;
	public static final int G_VIDEO_FILE				= 2;
	public static final int G_AUDIO_FOLDER				= 3;
	public static final int G_PHOTO_FOLDER				= 4;
	public static final int G_VIDEO_FOLDER				= 5;
	public static final int G_EXIST_DEVICE_ON_DVD		= 6;
	public static final int G_PAGE						= 7;
	public static final int G_EXIST_DISC_ON_DVD			= 8;
	public static final int G_EXIST_SD_ON_DVD			= 9;
	public static final int G_EXIST_USB_ON_DVD			= 10;

	public static final int U_ID3_TITLE					= 0;
	public static final int U_ID3_ARTIST				= 1;
	public static final int U_ID3_ALBUM					= 2;
	public static final int U_AUDIO_FILE_CNT			= 3;
	public static final int U_PHOTO_FILE_CNT			= 4;
	public static final int U_VIDEO_FILE_CNT			= 5;
	public static final int U_AUDIO_FOLDER_CNT			= 6;
	public static final int U_PHOTO_FOLDER_CNT			= 7;
	public static final int U_VIDEO_FOLDER_CNT			= 8;
	public static final int U_AUDIO_FILE				= 9;
	public static final int U_PHOTO_FILE				= 10;
	public static final int U_VIDEO_FILE				= 11;
	public static final int U_PLAY_TIME					= 12;
	public static final int U_TOTAL_TIME				= 13;
	public static final int U_PLAY_TRACK				= 14;
	public static final int U_TOTAL_TRACK				= 15;
	public static final int U_TRACK_TITLE				= 16;
	public static final int U_PLAY_MEDIA_TYPE			= 17;
	public static final int U_LIST_MEDIA_TYPE			= 18;
	public static final int U_PLAY_FOLDER				= 19;
	public static final int U_PAGE						= 20;
	public static final int U_TRACK_UNSUPPORT			= 21;
	public static final int U_LOADING_DEVICE			= 22;
	public static final int U_EJECTING_DEVICE			= 23;
	public static final int U_MODULE_ID					= 24;	// DVD类型
	public static final int U_DVD_ENABLE				= 25;	// DVD使能
	public static final int U_EXIST_DISC_ON_DVD			= 26;	// DVD盒子存在存在碟片
	public static final int U_EXIST_SD_ON_DVD			= 27;	// DVD盒子存在SD卡
	public static final int U_EXIST_USB_ON_DVD			= 28;	// DVD盒子存在USB
	public static final int U_EXIST_DEVICE_ON_DVD		= 29;	// DVD盒子存在设备
	public static final int U_DVD_VER					= 30;	// DVD版本信息
	public static final int U_PLAY_STATE				= 31;	// 播放状态
	public static final int U_REPEAT_STATE				= 32;	// 重复状态
	// new int[]{type, subType, info, showTime}
	public static final int U_OSD_INFO					= 33;	// OSD信息
	public static final int U_RANDOM					= 34;	// 随机
	public static final int U_TOUCH_STATE				= 35;	// 触摸状态	// 1
	public static final int U_SPEED_MODE				= 36;
	public static final int U_UPGRADE_STEP				= 37;
	public static final int U_DISC_TYPE					= 38;	// 碟片类型
	public static final int U_DEVICE_TYPE				= 39;	// 设备类型
	public static final int U_CNT_MAX					= 50;

	public static final int FILE_CNT_MAX				= 10240;
	public static final int FOLDER_CNT_MAX				= 2048;

	public static final int MEDIA_TYPE_INVALID			= -1;	// 暂时未定义用途
	public static final int MEDIA_TYPE_EMPTY			= 0;
	public static final int MEDIA_TYPE_AUDIO			= 1;
	public static final int MEDIA_TYPE_PHOTO			= 2;
	public static final int MEDIA_TYPE_VIDEO			= 3;

	public static final int DEVICE_TYPE_INVALID			= -1;	// 暂时未定义用途
	public static final int DEVICE_TYPE_EMPTY			= 0;
	public static final int DEVICE_TYPE_DISC			= 1;
	public static final int DEVICE_TYPE_SD				= 2;
	public static final int DEVICE_TYPE_USB				= 3;
	public static final int DEVICE_TYPE_USB2			= 4;
	public static final int DEVICE_TYPE_VMCD			= 5;

	public static final String CHAPTER_TOKEN			= "/^_^ChApTeR^_^/";
	public static final String LIST_TOKEN				= "/^-^LiSt^_^/";

	public static final int PAGE_EMPTY					= 0;	// PLAY界面(关连U_PLAY_MEDIA_TYPE)/LIST界面(关联U_LIST_MEDIA_TYPE)
	public static final int PAGE_LOADING				= 1;	// LOADING界面,关联U_LOADING_DEVICE
	public static final int PAGE_EJECTING				= 2;	// EJECTING界面,关联U_EJECTING_DEVICE
	public static final int PAGE_ERROR					= 3;	// ERROR界面
	public static final int PAGE_LIST					= 4;	// LIST界面(关联U_LIST_MEDIA_TYPE)
	public static final int PAGE_UPGRADE				= 5;	// UPGRADE界面
	public static final int PAGE_CDROM					= 6;	// CDROM界面

	public static final int PLAY_STATE_INVALID			= 0;
	public static final int PLAY_STATE_PLAY				= 1;
	public static final int PLAY_STATE_PAUSE			= 2;
	public static final int PLAY_STATE_STOP				= 3;
	public static final int PLAY_STATE_FB				= 4;
	public static final int PLAY_STATE_FF				= 5;

	public static final int REPEAT_STATE_NULL			= 0;
	public static final int REPEAT_STATE_OFF			= 1;
	public static final int REPEAT_STATE_ALL			= 2;
	public static final int REPEAT_STATE_ONE			= 3;
	public static final int REPEAT_STATE_FOLDER			= 4;
	public static final int REPEAT_STATE_TITLE			= 5;

	public static final int SPEED_MODE_NONE				= 0;
	public static final int SPEED_MODE_FF2				= 1;
	public static final int SPEED_MODE_FF4				= 2;
	public static final int SPEED_MODE_FF8				= 3;
	public static final int SPEED_MODE_FF16				= 4;
	public static final int SPEED_MODE_FF20				= 5;
	public static final int SPEED_MODE_FB2				= 6;
	public static final int SPEED_MODE_FB4				= 7;
	public static final int SPEED_MODE_FB8				= 8;
	public static final int SPEED_MODE_FB16				= 9;
	public static final int SPEED_MODE_FB20				= 10;
	public static final int SPEED_MODE_FF32				= 11;
	public static final int SPEED_MODE_FB32				= 12;

	public static final int UPGRADE_STEP_IDLE			= 0;	// 空闲状态
	public static final int UPGRADE_STEP_READ_FILE		= 1;	// 读取文件中
	public static final int UPGRADE_STEP_READ_FILE_FAIL	= 2;	// 读取文件失败
	public static final int UPGRADE_STEP_REQUEST_UPGRADE= 3;	// 请求进入升级
	// new int[]{UPGRADE_STEP_SEND_DATA, blockIndex, blockCnt}
	public static final int UPGRADE_STEP_SEND_DATA		= 4;	// 传输数据中
	public static final int UPGRADE_STEP_ON_UPGRADE		= 5;	// 升级中
	public static final int UPGRADE_STEP_RESULT_OK		= 6;	// 升级完成
	public static final int UPGRADE_STEP_RESULT_FAIL	= 7;	// 升级失败

	public static final int DISC_TYPE_NULL				= 0;
	public static final int DISC_TYPE_CD				= 1;
	public static final int DISC_TYPE_VCD				= 2;
	public static final int DISC_TYPE_DVD				= 3;
	public static final int DISC_TYPE_DATA				= 4;
}
