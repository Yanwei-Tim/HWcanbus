package com.ex.hiworld.server.syu;

/**
 * Created by APP03 on 2018/6/20.
 */

public class DataHost {
	public static int sAppid, sAccon;
	public static int sPhoneSate;
	public static String sPhoneNum = "", sPhoneContact = "";
	public static int sBackCar;
	public static String sId3Title = "", sId3Artist = "", sId3Album = "";
	public static int sPlayState, sCurPlayIndex, sPlayTotal, sCurPlayTime, sTotalPlayTime, sPlayCycleMode,
			sPlayRandomMode, sPlayDeviceType, sPlayMediaType;
	public static int sGpsAngle;
	public static int sRadioBand, sRadioFreq, sRadioSearchState, sRadioScan, sRadioChannel;
	public static boolean isAM;
	public static int sLang;
	public static int sMuteSrc, sVolDst;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getCanonicalName() + " [");
		buffer.append("playstate: " + sPlayState);
		buffer.append(",sId3Title: " + sId3Title);
		buffer.append(",sId3Artist: " + sId3Artist);
		buffer.append(",sId3Album: " + sId3Album);
		buffer.append(",iCurPlayIndex: " + sCurPlayIndex);
		buffer.append(",iPlayTotal: " + sPlayTotal);
		buffer.append(",iCurPlayTime: " + sCurPlayTime);
		buffer.append(",iTotalPlayTime: " + sTotalPlayTime);
		buffer.append(",iPlayCycleMode: " + sPlayCycleMode);
		buffer.append(",iPlayRandomMode: " + sPlayRandomMode);
		buffer.append(",iPlayDeviceType: " + sPlayDeviceType);
		buffer.append(",iPlayMediaType: " + sPlayMediaType);
		buffer.append("]");
		return buffer.toString();
	}
}
