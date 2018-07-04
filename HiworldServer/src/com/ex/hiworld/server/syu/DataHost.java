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
    public static int sPlayState, iCurPlayIndex, iPlayTotal, iCurPlayTime, iTotalPlayTime,
            iPlayCycleMode, iPlayRandomMode, iPlayDeviceType, iPlayMediaType;
    public static int sGpsAngle;
    public static int iRadioBand, iRadioFreq, iRadioSearchState, iRadioScan, iRadioChannel;
    public static boolean isAM;
    public static int sLang;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getCanonicalName() + " [");
        buffer.append("playstate: " + sPlayState);
        buffer.append(",sId3Title: " + sId3Title);
        buffer.append(",sId3Artist: " + sId3Artist);
        buffer.append(",sId3Album: " + sId3Album);
        buffer.append(",iCurPlayIndex: " + iCurPlayIndex);
        buffer.append(",iPlayTotal: " + iPlayTotal);
        buffer.append(",iCurPlayTime: " + iCurPlayTime);
        buffer.append(",iTotalPlayTime: " + iTotalPlayTime);
        buffer.append(",iPlayCycleMode: " + iPlayCycleMode);
        buffer.append(",iPlayRandomMode: " + iPlayRandomMode);
        buffer.append(",iPlayDeviceType: " + iPlayDeviceType);
        buffer.append(",iPlayMediaType: " + iPlayMediaType);
        buffer.append("]");
        return buffer.toString();
    }
}
