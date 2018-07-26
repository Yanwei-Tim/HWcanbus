package com.hiworld.customer.network;

public class NetworkPathUtil {
	//w11查询SN接口
	public static final String PATH_W11_QUERY_SN = "http://112.74.111.218/Youzi_phonebluebound.php?query_sn=";
	//w11-2查询SN接口
	//public static final String PATH_W11_2_QUERY_SN = "http://112.74.111.218/youzi_driving/YouziW11_2/YouziW11_2_phonebluebound.php?query_sn=";
	//w11返回MCU号接口
	public static final String PATH_W11_QUERY_MCU = "http://112.74.111.218/Youzi_phonebluebound.php?Youzi_sn=";
	//w11-2返回MCU号接口
	//public static final String PATH_W11_2_QUERY_MCU = "http://112.74.111.218/youzi_driving/YouziW11_2/YouziW11_2_phonebluebound.php?Youzi_sn=";
	//w11解绑接口
	public static final String PATH_W11_UNBIND = "http://112.74.111.218/Youzi_phoneblueunwrap.php?Youzi_mcu=";
	//W11-2解绑接口
	//public static final String PATH_W11_2_UNBIND = "http://112.74.111.218/youzi_driving/YouziW11_2/YouziW11_2_phoneblueunwrap.php?Youzi_mcu=";
	//w11
	public static final String PATH_TARGET_VERSION = "http://112.74.111.218/Viss_VersionManagement.php?Youzi_sn=";
	public static final String PATH_POST_VERSION = "http://112.74.111.218/Viss_VersionManagement.php?Youzi_CurrentVersion=";
	
	//W11提交地理位置、版本的接口
	public static final String PATH_W11_SUBMITINFO = "http://112.74.111.218/YouziDrivingConnect/Youzi_Bindinguploadinfo.php?Bindupload=1&Youzi_sn=";
	//W11-2提交地理位置、版本的接口
	public static final String PATH_W11_2_SUBMITINFO = "";
}
