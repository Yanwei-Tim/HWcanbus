/**
 * 版权：深圳深青联科技有限公司
 * 设计:	 柯华栋
 * 代码：深青联研发部/Android组
 * 日期：2015年1月1日
 */


package com.ex.hiworld.server.syu;


public class FinalBt {
	public static final int MODULE_NULL						= 0;
	public static final int MODULE_RDA						= 1;
	public static final int MODULE_BT8700					= 2;
	public static final int MODULE_BT_SOFIA					= 3;
	public static final int MODULE_BT_SG9832				= 4;
	public static final int MODULE_IVT						= 5;

	public static final int C_BTAV_PREV						= 0;	// 蓝牙音乐上一曲
	public static final int C_BTAV_NEXT						= 1;	// 蓝牙音乐下一曲
	public static final int C_BTAV_PLAYPAUSE				= 2;	// 蓝牙音乐播放/暂停
	public static final int C_BTAV_PAUSE					= 3;	// 蓝牙音乐暂停
	public static final int C_BTAV_STOP						= 4;	// 蓝牙音乐停止
	public static final int C_QUERY_PAIR					= 5;	// 查询配对列表
	public static final int C_KEY							= 6;	// 模拟按键
	public static final int C_DIAL							= 7;	// 拨号(+号码)
	public static final int C_REDIAL						= 8;	// 重拨
	public static final int C_PICKUP						= 9;	// 接听
	public static final int C_HANG							= 10;	// 挂机
	public static final int C_REJECT_RING					= 11;	// 拒绝接听
	public static final int C_NUMBER						= 12;	// 设置当前号码
	// PARAM: new int[]{OFF:断开 ON:连接  SWITCH:OFF/ON切换}
	public static final int C_LINK_CUT						= 13;	// 连接/断开
	public static final int C_HFP							= 14;	// 免提/非免提
	// PARAM: new int[]{0一个字符/1全部}
	public static final int C_CLEAR							= 15;	// 清除
	public static final int C_HANG_CLEAR_NUM				= 16;	// 挂机清除号码 0:off/1:on/2:switch
	public static final int C_RESET							= 17;	// 复位蓝牙
	public static final int C_PIN_CODE						= 18;	// 配对密码
	public static final int C_LOCAL_NAME					= 19;	// 车载蓝牙名称
	// PARAM: new int[]{0停止DISCOVER, 1开始DISCOVER)
	public static final int C_DISCOVER						= 20;	//
	public static final int C_TEST							= 21;	// 测试?
	public static final int C_MIC_VOL						= 22;	// ？
	// SOFIA：蓝牙MIC灵敏度 ：1高 0低 2:切换 new String[]{"0"/"1"/"2"}
	public static final int C_MIC_LEVEL						= 23;	//
	public static final int C_CONNECT_DEVICE				= 24;	// 连接手机？
	public static final int C_CONNECT_OBD					= 25;	// 连接OBD?
	public static final int C_DOWNLOAD_BOOK					= 26;	// 下载电话本
	public static final int C_BTAV_PLAY						= 27;	// 蓝牙音乐播放
	public static final int C_AUTOPICK						= 28;	// 自动接听 (<0不自动接听 0立刻自动接听 >0自动接听倒计时)
	public static final int C_BT_POWER_ON					= 29;	// 蓝牙开关
	public static final int C_BTRING_PERCENT				= 30;	//(0~10)蓝牙铃声大小
	//new int[]{0/1}
	public static final int C_BTAV_LINK_CUT					= 31;	//蓝牙音乐链接断开

	public static final int U_BTAV_ID3_TITLE				= 0;	// 蓝牙音乐ID3标题
	public static final int U_BTAV_ID3_ARTIST				= 1;	// 蓝牙音乐ID3艺术家
	public static final int U_BTAV_TOTAL_TIME				= 2;	// 蓝牙音乐总时间
	public static final int U_BTAV_PLAY_TRACK				= 3;	// 蓝牙音乐当前曲目号
	public static final int U_BTAV_TOTAL_TRACK				= 4;	// 蓝牙音乐曲目数量
	public static final int U_PAIR_LIST						= 5;	// 配对列表
	public static final int U_PHONE_MAC_ADDR				= 6;	// 电话的物理地址
	public static final int U_PHONE_NAME					= 7;	// 电话的名称
	public static final int U_PHONE_NUMBER					= 8;
	public static final int U_PHONE_STATE					= 9;	// HFP协议
	public static final int U_HANG_CLEAR_NUM				= 10;
	// PARAM new int[]{Sysem.currentMillis : high, low}
	public static final int U_RING_TIME						= 11;	// 来电时间
	public static final int U_TALK_TIME						= 12;	// 通话时间
	public static final int U_BTAV_PLAY_STATE				= 13;	// 蓝牙音乐播放状态
	public static final int U_LOCAL_MAC_ADDR				= 14;	// 车载蓝牙的物理地址
	public static final int U_LOCAL_NAME					= 15;	// 车载蓝牙名称
	public static final int U_PIN_CODE						= 16;	// 配对密码
	public static final int U_BT_VER						= 17;	// BT版本信息
	public static final int U_HFP							= 18;	// 免提(通过蓝牙出声音)
	public static final int U_RESET							= 19;	// 通知已经RESET完成
	public static final int U_AUTOPICK						= 20;	// 自动接听(<0不自动接听 0立刻接听 n,n秒后接听)
	public static final int U_AUTOPICK_REMAIN				= 21;	// 自动接听倒计时(单位ms)
	// PARAM new String[]{name, number}
	public static final int U_BOOK							= 22;	// 电话本
	public static final int U_PHONE_TYPE					= 23;	// 电话类型 (eg.MI2)
	public static final int U_SEARCH_LIST					= 24;	// 搜索列表
	public static final int U_PAIR_RESULT					= 25;	// 配对结果
	public static final int U_BTAV_ID3_ALBUM				= 26;	// 蓝牙音乐ID3专辑
	public static final int U_BTAV_GENRE					= 27;	// 蓝牙音乐流派
	public static final int U_BTAV_PLAY_TIME				= 28;	// 蓝牙音乐播放时间
	public static final int U_PBAP_STATE					= 29;	// 电话本协议状态
	public static final int U_AVRCP14_SUPPORT				= 30;	// 是否有播放列表
	public static final int U_BT_POWER_ON					= 31;	// 蓝牙开关
	public static final int U_MIC_LEVEL						= 32;	// SOFIA：蓝牙MIC灵敏度 ：1高 0低
	public static final int U_BTRING_PERCENT				= 33;	//(0~10)蓝牙铃声大小
	public static final int U_A2DP_SINK_STATE				= 34;	//蓝牙音乐连接状态

	public static final int U_CNT_MAX						= 50;

	public static final int PHONE_STATE_DISCONNECTED		= 0;
	public static final int PHONE_STATE_LINK				= 1;
	public static final int PHONE_STATE_CONNECTED			= 2;
	public static final int PHONE_STATE_DIAL				= 3;
	public static final int PHONE_STATE_RING				= 4;
	public static final int PHONE_STATE_TALK				= 5;
	public static final int PHONE_STATE_PAIR				= 6;	// 配对中
	//public static final int PHONE_STATE_LOAD				= 7;	// PBAP协议

	public static final int PBAP_STATE_DISCONNECTED			= 0;
	public static final int PBAP_STATE_CONNECTED			= 1;
	public static final int PBAP_STATE_LOAD					= 2;

	public static final int A2DP_STATE_DISCONNECTED			= 0;//蓝牙音乐断开
	public static final int A2DP_STATE_CONNECTING			= 1;//连接中
	public static final int A2DP_STATE_CONNECTED			= 2;//已连接
	public static final int A2DP_STATE_DISCONNECTING		= 3;//断开中

	public static final int KEY_DIAL						= 0;	// 根据状态:拨号(当前号码)/接听/重拨
	public static final int KEY_HANG						= 1;
	public static final int KEY_N0							= 2;
	public static final int KEY_N1							= 3;
	public static final int KEY_N2							= 4;
	public static final int KEY_N3							= 5;
	public static final int KEY_N4							= 6;
	public static final int KEY_N5							= 7;
	public static final int KEY_N6							= 8;
	public static final int KEY_N7							= 9;
	public static final int KEY_N8							= 10;
	public static final int KEY_N9							= 11;
	public static final int KEY_NP							= 12;		// '+'
	public static final int KEY_NX							= 13;		// '*'
	public static final int KEY_NJ							= 14;		// '#'

	public static final int BTAV_PLAY_STATE_PAUSE			= 0;
	public static final int BTAV_PLAY_STATE_PLAY			= 1;

	public static final int PAGE_NULL						= 0;
	public static final int PAGE_DIAL						= 1;
	public static final int PAGE_BOOK						= 2;
	public static final int PAGE_SET						= 3;
	public static final int PAGE_HISTORY					= 4;
	public static final int PAGE_MUSIC						= 5;
	public static final int PAGE_PAIR						= 6;

	public static final String SERVICE_MS_ACTION			= "com.syu.ms.bt";
	public static final String BROADCAST_MS_ACTION			= "com.syu.ms.bt";
	public static final String BUNDLE_KEY_CMD				= "cmd";
	public static final int BROADCAST_CMD_NULL				= 0;
	public static final int BROADCAST_CMD_SHOW_PIP			= 1;	// 显示画中画
	public static final int BROADCAST_CMD_PAGE_DIAL_BY_KEY	= 2;	// 模式键/蓝牙键
	public static final int BROADCAST_CMD_PAGE_DIAL_BY_WORK	= 3;	// 来电/拨号而进入拨号界面
	public static final int BROADCAST_CMD_PAGE_BTAV			= 4;	// 在前台时进入BTAV
	public static final int BROADCAST_CMD_PAGE_BTAV_FORCE	= 5;	// 无条件进入BTAV
}
