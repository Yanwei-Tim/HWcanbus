package com.hiworld.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class VMCarInfo implements Parcelable{

	/*****************大众全兼容 begin******************/
	private byte m_lowOilAlarm;//油量过低报警
	private byte m_lowBatteryAlarm;//电池电压过低报警
	private byte m_lifebeltAlarm;//安全带报警
	private byte m_leanerAlarm;//清洗液报警
	private byte m_engineOilAlarm;//机油报警
	private byte m_nearlyLight;//近光灯
	private byte m_farLight;//远光灯
	private byte m_showWidthLight;//示宽灯
	private byte m_frontfogLight;//前雾灯
	private byte m_realfogLight;//后雾灯
	private byte m_stopLight;//刹车灯
	private byte m_backLight;//倒车灯
	private byte m_daytimeLight;//日间行车灯
	private byte m_leftLight;//左转向灯
	private byte m_rightLight;//右转向灯
	private byte m_leftFillLight;//左转向补光灯
	private byte m_rightFillLight;//右转向补光灯
	private byte m_doubleLight;//双闪灯
	private byte m_waterTemp;//水温值
	private byte m_currentOilInt;//瞬时油耗整数位
	private byte m_currentOilFloat;//瞬时油耗小数位
	private byte m_carGear;//档位提醒
	private int m_restOil;//剩余油量
	private int m_baterryVolInt;//电池电压整数位
	private int m_batteryVolFloat;//电池电压小数位
	private int m_totalDistanceHigh;//行驶里程最高位
	private int m_totalDistance;//行驶里程
	private int m_totalDistanceLow;//行驶里程最低位
	private int m_exactlySpeedHigh;//精准车速最高位
	private int m_exactlySpeedLow;//精准车速最低位
	private int m_engineSpeedHigh;//发动机转速最高位
	private int m_engineSpeedLow;//发动机转速最低位
	
	private byte m_byRoadsideparking;//路边驻车
	private byte m_byStorageparking;//入库驻车
	private byte m_byRadarsilence;//雷达静音
	
	private byte m_airShow;//空调显示（开/关）
	private byte m_airSwitch;//空调开关(开/关)
	private byte m_cycleMode;//循环模式(三种)
	private byte m_Auto;//Auto(开/关)
	private byte m_Dual;//Dual(开/关)
	private byte m_AC_MAX;//A/C_MAX(开/关)
	private byte m_Auto2;//Auto2(开/关)
	private byte m_aircondBlack;//空调后区使能(开/关)
	private byte m_AC;//(开/关)
	private byte m_realWindFog;//后窗除雾
	private byte m_frontWindFog;//前窗除雾
	private byte m_heatRightSeat;//右座椅加热
	private byte m_heatLeftSeat;//左座椅加热
	private byte m_leftFrontTempSet;//前排左设定温度
	private byte m_rightFrontTempSet;//前排右设定温度
	private byte m_left_blowFoot;//左吹脚
	private byte m_left_blowBody;//左吹身
	private byte m_left_blowWindow;//左吹窗
	private byte m_leftFlowLevel;//左风速
	private byte m_right_blowFoot;//右吹脚
	private byte m_right_blowBody;//右吹身
	private byte m_right_blowWindow;//右吹窗
	private byte m_rightFlowLevel;//右风速
	private int m_outTemp;//车外温度
	private byte m_leftFrontDoor;//左前门
	private byte m_rightFrontDoor;//右前门
	private byte m_leftRealDoor;//左后门
	private byte m_rightRealDoor;//右后门
	private byte m_bonnet;//引擎盖
	private byte m_trunk;//行李箱
	private byte m_doorFlag = 0x01;//门状态使能位
	private byte m_park_handStop;//泊车手刹状态
	private int m_speed;//车速
	private int m_iLeftBelt;//左安全带
	private int m_iRightBelt;//右安全带
	private int m_iHood;//引擎盖
	private int m_iTaixBox;//尾箱
	private int m_iEngineSpeed;//发动机转速
	private float m_fBatteryVol;//电池电压
	private int m_iLFdoorlock;//左前门锁
	private int m_iRFdoorlock;//右前门锁
	private int m_iLRdoorlock;//左后门锁
	private int m_iRRdoorlock;//右后门锁
	private int m_iCoolantTemp;//冷却液温度
	private int m_iFuelPa;//机油压力
	
	public int getM_iFuelPa() {
		return m_iFuelPa;
	}

	public void setM_iFuelPa(int m_iFuelPa) {
		this.m_iFuelPa = m_iFuelPa;
	}

	public int getM_iCoolantTemp() {
		return m_iCoolantTemp;
	}

	public void setM_iCoolantTemp(int m_iCoolantTemp) {
		this.m_iCoolantTemp = m_iCoolantTemp;
	}

	public int getM_iLFdoorlock() {
		return m_iLFdoorlock;
	}

	public void setM_iLFdoorlock(int m_iLFdoorlock) {
		this.m_iLFdoorlock = m_iLFdoorlock;
	}

	public int getM_iRFdoorlock() {
		return m_iRFdoorlock;
	}

	public void setM_iRFdoorlock(int m_iRFdoorlock) {
		this.m_iRFdoorlock = m_iRFdoorlock;
	}

	public int getM_iLRdoorlock() {
		return m_iLRdoorlock;
	}

	public void setM_iLRdoorlock(int m_iLRdoorlock) {
		this.m_iLRdoorlock = m_iLRdoorlock;
	}

	public int getM_iRRdoorlock() {
		return m_iRRdoorlock;
	}

	public void setM_iRRdoorlock(int m_iRRdoorlock) {
		this.m_iRRdoorlock = m_iRRdoorlock;
	}

	public float getM_fBatteryVol() {
		return m_fBatteryVol;
	}

	public void setM_fBatteryVol(float m_fBatteryVol) {
		this.m_fBatteryVol = m_fBatteryVol;
	}

	public int getM_iEngineSpeed() {
		return m_iEngineSpeed;
	}

	public void setM_iEngineSpeed(int m_iEngineSpeed) {
		this.m_iEngineSpeed = m_iEngineSpeed;
	}

	public int getM_iHood() {
		return m_iHood;
	}

	public void setM_iHood(int m_iHood) {
		this.m_iHood = m_iHood;
	}

	public int getM_iTaixBox() {
		return m_iTaixBox;
	}

	public void setM_iTaixBox(int m_iTaixBox) {
		this.m_iTaixBox = m_iTaixBox;
	}

	public int getM_iLeftBelt() {
		return m_iLeftBelt;
	}

	public void setM_iLeftBelt(int m_iLeftBelt) {
		this.m_iLeftBelt = m_iLeftBelt;
	}

	public int getM_iRightBelt() {
		return m_iRightBelt;
	}

	public void setM_iRightBelt(int m_iRightBelt) {
		this.m_iRightBelt = m_iRightBelt;
	}

	public byte getM_park_handStop() {
		return m_park_handStop;
	}

	public void setM_park_handStop(byte m_park_handStop) {
		this.m_park_handStop = m_park_handStop;
	}

	public byte getM_airShow() {
		return m_airShow;
	}

	public void setM_airShow(byte m_airShow) {
		this.m_airShow = m_airShow;
	}

	public byte getM_airSwitch() {
		return m_airSwitch;
	}

	public void setM_airSwitch(byte m_airSwitch) {
		this.m_airSwitch = m_airSwitch;
	}

	public byte getM_cycleMode() {
		return m_cycleMode;
	}

	public void setM_cycleMode(byte m_cycleMode) {
		this.m_cycleMode = m_cycleMode;
	}

	public byte getM_Auto() {
		return m_Auto;
	}

	public void setM_Auto(byte m_Auto) {
		this.m_Auto = m_Auto;
	}

	public byte getM_Dual() {
		return m_Dual;
	}

	public void setM_Dual(byte m_Dual) {
		this.m_Dual = m_Dual;
	}

	public byte getM_AC_MAX() {
		return m_AC_MAX;
	}

	public void setM_AC_MAX(byte m_AC_MAX) {
		this.m_AC_MAX = m_AC_MAX;
	}

	public byte getM_Auto2() {
		return m_Auto2;
	}

	public void setM_Auto2(byte m_Auto2) {
		this.m_Auto2 = m_Auto2;
	}

	public byte getM_aircondBlack() {
		return m_aircondBlack;
	}

	public void setM_aircondBlack(byte m_aircondBlack) {
		this.m_aircondBlack = m_aircondBlack;
	}

	public byte getM_AC() {
		return m_AC;
	}

	public void setM_AC(byte m_AC) {
		this.m_AC = m_AC;
	}

	public byte getM_realWindFog() {
		return m_realWindFog;
	}

	public void setM_realWindFog(byte m_realWindFog) {
		this.m_realWindFog = m_realWindFog;
	}

	public byte getM_frontWindFog() {
		return m_frontWindFog;
	}

	public void setM_frontWindFog(byte m_frontWindFog) {
		this.m_frontWindFog = m_frontWindFog;
	}

	public byte getM_heatRightSeat() {
		return m_heatRightSeat;
	}

	public void setM_heatRightSeat(byte m_heatRightSeat) {
		this.m_heatRightSeat = m_heatRightSeat;
	}

	public byte getM_heatLeftSeat() {
		return m_heatLeftSeat;
	}

	public void setM_heatLeftSeat(byte m_heatLeftSeat) {
		this.m_heatLeftSeat = m_heatLeftSeat;
	}

	public byte getM_leftFrontTempSet() {
		return m_leftFrontTempSet;
	}

	public void setM_leftFrontTempSet(byte m_leftFrontTempSet) {
		this.m_leftFrontTempSet = m_leftFrontTempSet;
	}

	public byte getM_rightFrontTempSet() {
		return m_rightFrontTempSet;
	}

	public void setM_rightFrontTempSet(byte m_rightFrontTempSet) {
		this.m_rightFrontTempSet = m_rightFrontTempSet;
	}

	public byte getM_left_blowFoot() {
		return m_left_blowFoot;
	}

	public void setM_left_blowFoot(byte m_left_blowFoot) {
		this.m_left_blowFoot = m_left_blowFoot;
	}

	public byte getM_left_blowBody() {
		return m_left_blowBody;
	}

	public void setM_left_blowBody(byte m_left_blowBody) {
		this.m_left_blowBody = m_left_blowBody;
	}

	public byte getM_left_blowWindow() {
		return m_left_blowWindow;
	}

	public void setM_left_blowWindow(byte m_left_blowWindow) {
		this.m_left_blowWindow = m_left_blowWindow;
	}

	public byte getM_leftFlowLevel() {
		return m_leftFlowLevel;
	}

	public void setM_leftFlowLevel(byte m_leftFlowLevel) {
		this.m_leftFlowLevel = m_leftFlowLevel;
	}

	public byte getM_right_blowFoot() {
		return m_right_blowFoot;
	}

	public void setM_right_blowFoot(byte m_right_blowFoot) {
		this.m_right_blowFoot = m_right_blowFoot;
	}

	public byte getM_right_blowBody() {
		return m_right_blowBody;
	}

	public void setM_right_blowBody(byte m_right_blowBody) {
		this.m_right_blowBody = m_right_blowBody;
	}

	public byte getM_right_blowWindow() {
		return m_right_blowWindow;
	}

	public void setM_right_blowWindow(byte m_right_blowWindow) {
		this.m_right_blowWindow = m_right_blowWindow;
	}

	public byte getM_rightFlowLevel() {
		return m_rightFlowLevel;
	}

	public void setM_rightFlowLevel(byte m_rightFlowLevel) {
		this.m_rightFlowLevel = m_rightFlowLevel;
	}

	public int getM_outTemp() {
		return m_outTemp;
	}

	public void setM_outTemp(int m_outTemp) {
		this.m_outTemp = m_outTemp;
	}

	public byte getM_leftFrontDoor() {
		return m_leftFrontDoor;
	}

	public void setM_leftFrontDoor(byte m_leftFrontDoor) {
		this.m_leftFrontDoor = m_leftFrontDoor;
	}

	public byte getM_rightFrontDoor() {
		return m_rightFrontDoor;
	}

	public void setM_rightFrontDoor(byte m_rightFrontDoor) {
		this.m_rightFrontDoor = m_rightFrontDoor;
	}

	public byte getM_leftRealDoor() {
		return m_leftRealDoor;
	}

	public void setM_leftRealDoor(byte m_leftRealDoor) {
		this.m_leftRealDoor = m_leftRealDoor;
	}

	public byte getM_rightRealDoor() {
		return m_rightRealDoor;
	}

	public void setM_rightRealDoor(byte m_rightRealDoor) {
		this.m_rightRealDoor = m_rightRealDoor;
	}

	public byte getM_bonnet() {
		return m_bonnet;
	}

	public void setM_bonnet(byte m_bonnet) {
		this.m_bonnet = m_bonnet;
	}

	public byte getM_trunk() {
		return m_trunk;
	}

	public void setM_trunk(byte m_trunk) {
		this.m_trunk = m_trunk;
	}

	public byte getM_doorFlag() {
		return m_doorFlag;
	}

	public void setM_doorFlag(byte m_doorFlag) {
		this.m_doorFlag = m_doorFlag;
	}


	/*****************大众全兼容 end******************/
	
	private static VMCarInfo vmCarInfo = new VMCarInfo();
	
	public static VMCarInfo getInstance(){
		return vmCarInfo;
	}
	
	public byte getM_byRoadsideparking() {
		return m_byRoadsideparking;
	}

	public void setM_byRoadsideparking(byte m_byRoadsideparking) {
		this.m_byRoadsideparking = m_byRoadsideparking;
	}

	public byte getM_byStorageparking() {
		return m_byStorageparking;
	}

	public void setM_byStorageparking(byte m_byStorageparking) {
		this.m_byStorageparking = m_byStorageparking;
	}

	public byte getM_byRadarsilence() {
		return m_byRadarsilence;
	}

	public void setM_byRadarsilence(byte m_byRadarsilence) {
		this.m_byRadarsilence = m_byRadarsilence;
	}

	
	
	
	public byte getM_lowOilAlarm() {
		return m_lowOilAlarm;
	}

	public void setM_lowOilAlarm(byte m_lowOilAlarm) {
		this.m_lowOilAlarm = m_lowOilAlarm;
	}

	public byte getM_lowBatteryAlarm() {
		return m_lowBatteryAlarm;
	}

	public void setM_lowBatteryAlarm(byte m_lowBatteryAlarm) {
		this.m_lowBatteryAlarm = m_lowBatteryAlarm;
	}

	public byte getM_lifebeltAlarm() {
		return m_lifebeltAlarm;
	}

	public void setM_lifebeltAlarm(byte m_lifebeltAlarm) {
		this.m_lifebeltAlarm = m_lifebeltAlarm;
	}

	public byte getM_leanerAlarm() {
		return m_leanerAlarm;
	}

	public void setM_leanerAlarm(byte m_leanerAlarm) {
		this.m_leanerAlarm = m_leanerAlarm;
	}

	public byte getM_engineOilAlarm() {
		return m_engineOilAlarm;
	}

	public void setM_engineOilAlarm(byte m_engineOilAlarm) {
		this.m_engineOilAlarm = m_engineOilAlarm;
	}

	public byte getM_nearlyLight() {
		return m_nearlyLight;
	}

	public void setM_nearlyLight(byte m_nearlyLight) {
		this.m_nearlyLight = m_nearlyLight;
	}

	public byte getM_farLight() {
		return m_farLight;
	}

	public void setM_farLight(byte m_farLight) {
		this.m_farLight = m_farLight;
	}

	public byte getM_showWidthLight() {
		return m_showWidthLight;
	}

	public void setM_showWidthLight(byte m_showWidthLight) {
		this.m_showWidthLight = m_showWidthLight;
	}

	public byte getM_frontfogLight() {
		return m_frontfogLight;
	}

	public void setM_frontfogLight(byte m_frontfogLight) {
		this.m_frontfogLight = m_frontfogLight;
	}

	public byte getM_realfogLight() {
		return m_realfogLight;
	}

	public void setM_realfogLight(byte m_realfogLight) {
		this.m_realfogLight = m_realfogLight;
	}

	public byte getM_stopLight() {
		return m_stopLight;
	}

	public void setM_stopLight(byte m_stopLight) {
		this.m_stopLight = m_stopLight;
	}

	public byte getM_backLight() {
		return m_backLight;
	}

	public void setM_backLight(byte m_backLight) {
		this.m_backLight = m_backLight;
	}

	public byte getM_daytimeLight() {
		return m_daytimeLight;
	}

	public void setM_daytimeLight(byte m_daytimeLight) {
		this.m_daytimeLight = m_daytimeLight;
	}

	public byte getM_leftLight() {
		return m_leftLight;
	}

	public void setM_leftLight(byte m_leftLight) {
		this.m_leftLight = m_leftLight;
	}

	public byte getM_rightLight() {
		return m_rightLight;
	}

	public void setM_rightLight(byte m_rightLight) {
		this.m_rightLight = m_rightLight;
	}

	public byte getM_leftFillLight() {
		return m_leftFillLight;
	}

	public void setM_leftFillLight(byte m_leftFillLight) {
		this.m_leftFillLight = m_leftFillLight;
	}

	public byte getM_rightFillLight() {
		return m_rightFillLight;
	}

	public void setM_rightFillLight(byte m_rightFillLight) {
		this.m_rightFillLight = m_rightFillLight;
	}

	public byte getM_doubleLight() {
		return m_doubleLight;
	}

	public void setM_doubleLight(byte m_doubleLight) {
		this.m_doubleLight = m_doubleLight;
	}

	public byte getM_waterTemp() {
		return m_waterTemp;
	}

	public void setM_waterTemp(byte m_waterTemp) {
		this.m_waterTemp = m_waterTemp;
	}

	public byte getM_currentOilInt() {
		return m_currentOilInt;
	}

	public void setM_currentOilInt(byte m_currentOilInt) {
		this.m_currentOilInt = m_currentOilInt;
	}

	public byte getM_currentOilFloat() {
		return m_currentOilFloat;
	}

	public void setM_currentOilFloat(byte m_currentOilFloat) {
		this.m_currentOilFloat = m_currentOilFloat;
	}

	public byte getM_carGear() {
		return m_carGear;
	}

	public void setM_carGear(byte m_carGear) {
		this.m_carGear = m_carGear;
	}

	public int getM_restOil() {
		return m_restOil;
	}

	public void setM_restOil(int m_restOil) {
		this.m_restOil = m_restOil;
	}

	public int getM_baterryVolInt() {
		return m_baterryVolInt;
	}

	public void setM_baterryVolInt(int m_baterryVolInt) {
		this.m_baterryVolInt = m_baterryVolInt;
	}

	public int getM_batteryVolFloat() {
		return m_batteryVolFloat;
	}

	public void setM_batteryVolFloat(int m_batteryVolFloat) {
		this.m_batteryVolFloat = m_batteryVolFloat;
	}

	public int getM_totalDistanceHigh() {
		return m_totalDistanceHigh;
	}

	public void setM_totalDistanceHigh(int m_totalDistanceHigh) {
		this.m_totalDistanceHigh = m_totalDistanceHigh;
	}

	public int getM_totalDistance() {
		return m_totalDistance;
	}

	public void setM_totalDistance(int m_totalDistance) {
		this.m_totalDistance = m_totalDistance;
	}

	public int getM_totalDistanceLow() {
		return m_totalDistanceLow;
	}

	public void setM_totalDistanceLow(int m_totalDistanceLow) {
		this.m_totalDistanceLow = m_totalDistanceLow;
	}

	public int getM_exactlySpeedHigh() {
		return m_exactlySpeedHigh;
	}

	public void setM_exactlySpeedHigh(int m_exactlySpeedHigh) {
		this.m_exactlySpeedHigh = m_exactlySpeedHigh;
	}

	public int getM_exactlySpeedLow() {
		return m_exactlySpeedLow;
	}

	public void setM_exactlySpeedLow(int m_exactlySpeedLow) {
		this.m_exactlySpeedLow = m_exactlySpeedLow;
	}

	public int getM_engineSpeedHigh() {
		return m_engineSpeedHigh;
	}

	public void setM_engineSpeedHigh(int m_engineSpeedHigh) {
		this.m_engineSpeedHigh = m_engineSpeedHigh;
	}

	public int getM_engineSpeedLow() {
		return m_engineSpeedLow;
	}

	public void setM_engineSpeedLow(int m_engineSpeedLow) {
		this.m_engineSpeedLow = m_engineSpeedLow;
	}



	private static final Parcelable.Creator<VMCarInfo> CREATOR = new Creator<VMCarInfo>() {

		@Override
		public VMCarInfo createFromParcel(Parcel source) {
			
			VMCarInfo vm_carInfo = new VMCarInfo();
			vm_carInfo.setM_lowOilAlarm(source.readByte());
			vm_carInfo.setM_lowBatteryAlarm(source.readByte());
			vm_carInfo.setM_lifebeltAlarm(source.readByte());
			vm_carInfo.setM_leanerAlarm(source.readByte());
			vm_carInfo.setM_engineOilAlarm(source.readByte());
			vm_carInfo.setM_nearlyLight(source.readByte());
			vm_carInfo.setM_farLight(source.readByte());
			vm_carInfo.setM_showWidthLight(source.readByte());
			vm_carInfo.setM_frontfogLight(source.readByte());
			vm_carInfo.setM_realfogLight(source.readByte());
			vm_carInfo.setM_stopLight(source.readByte());
			vm_carInfo.setM_backLight(source.readByte());
			vm_carInfo.setM_daytimeLight(source.readByte());
			vm_carInfo.setM_leftLight(source.readByte());
			vm_carInfo.setM_rightLight(source.readByte());
			vm_carInfo.setM_leftFillLight(source.readByte());
			vm_carInfo.setM_rightFillLight(source.readByte());
			vm_carInfo.setM_doubleLight(source.readByte());
			vm_carInfo.setM_waterTemp(source.readByte());
				
			vm_carInfo.setM_currentOilInt(source.readByte());
			vm_carInfo.setM_currentOilFloat(source.readByte());
			vm_carInfo.setM_carGear(source.readByte());
			vm_carInfo.setM_restOil(source.readInt());
			vm_carInfo.setM_baterryVolInt(source.readInt());
			vm_carInfo.setM_batteryVolFloat(source.readInt());
			vm_carInfo.setM_totalDistanceHigh(source.readInt());
			vm_carInfo.setM_totalDistance(source.readInt());
			vm_carInfo.setM_totalDistanceLow(source.readInt());
			vm_carInfo.setM_exactlySpeedHigh(source.readInt());
			vm_carInfo.setM_exactlySpeedLow(source.readInt());
			vm_carInfo.setM_engineSpeedHigh(source.readInt());
			vm_carInfo.setM_engineSpeedLow(source.readInt());
			
			vm_carInfo.setM_byRoadsideparking(source.readByte());
			vm_carInfo.setM_byStorageparking(source.readByte());
			vm_carInfo.setM_byRadarsilence(source.readByte());
			vm_carInfo.setM_airShow(source.readByte());
			vm_carInfo.setM_airSwitch(source.readByte());
			vm_carInfo.setM_cycleMode(source.readByte());
			vm_carInfo.setM_Auto(source.readByte());
			vm_carInfo.setM_Dual(source.readByte());
			vm_carInfo.setM_AC_MAX(source.readByte());
			vm_carInfo.setM_Auto2(source.readByte());
			vm_carInfo.setM_aircondBlack(source.readByte());
			vm_carInfo.setM_AC(source.readByte());
			vm_carInfo.setM_realWindFog(source.readByte());
			vm_carInfo.setM_frontWindFog(source.readByte());
			vm_carInfo.setM_heatRightSeat(source.readByte());
			vm_carInfo.setM_heatLeftSeat(source.readByte());
			vm_carInfo.setM_leftFrontTempSet(source.readByte());
			vm_carInfo.setM_rightFrontTempSet(source.readByte());
			vm_carInfo.setM_left_blowFoot(source.readByte());
			vm_carInfo.setM_left_blowBody(source.readByte());
			vm_carInfo.setM_left_blowWindow(source.readByte());
			vm_carInfo.setM_leftFlowLevel(source.readByte());
			vm_carInfo.setM_right_blowFoot(source.readByte());
			vm_carInfo.setM_right_blowBody(source.readByte());
			vm_carInfo.setM_right_blowWindow(source.readByte());
			vm_carInfo.setM_rightFlowLevel(source.readByte());
			vm_carInfo.setM_outTemp(source.readInt());
			vm_carInfo.setM_leftFrontDoor(source.readByte());
			vm_carInfo.setM_rightFrontDoor(source.readByte());
			vm_carInfo.setM_leftRealDoor(source.readByte());
			vm_carInfo.setM_rightRealDoor(source.readByte());
			vm_carInfo.setM_bonnet(source.readByte());
			vm_carInfo.setM_trunk(source.readByte());
			vm_carInfo.setM_doorFlag(source.readByte());
			vm_carInfo.setM_park_handStop(source.readByte());
			vm_carInfo.setM_speed(source.readInt());
			vm_carInfo.setM_iLeftBelt(source.readInt());
			vm_carInfo.setM_iRightBelt(source.readInt());
			vm_carInfo.setM_iHood(source.readInt());
			vm_carInfo.setM_iTaixBox(source.readInt());
			vm_carInfo.setM_iEngineSpeed(source.readInt());
			vm_carInfo.setM_fBatteryVol(source.readFloat());
			vm_carInfo.setM_iLFdoorlock(source.readInt());
			vm_carInfo.setM_iRFdoorlock(source.readInt());
			vm_carInfo.setM_iLRdoorlock(source.readInt());
			vm_carInfo.setM_iRRdoorlock(source.readInt());
			vm_carInfo.setM_iCoolantTemp(source.readInt());
			vm_carInfo.setM_iFuelPa(source.readInt());
			return vm_carInfo;
		}

		@Override
		public VMCarInfo[] newArray(int size) {
			
			return new VMCarInfo[size];
		}
	};
	
	@Override
	public int describeContents() {
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		
		out.writeByte(m_lowOilAlarm);
		out.writeByte(m_lowBatteryAlarm);
		out.writeByte(m_lifebeltAlarm);
		out.writeByte(m_leanerAlarm);
		out.writeByte(m_engineOilAlarm);
		out.writeByte(m_nearlyLight);
		out.writeByte(m_farLight);
		out.writeByte(m_showWidthLight);
		out.writeByte(m_frontfogLight);
		out.writeByte(m_realfogLight);
		out.writeByte(m_stopLight);
		out.writeByte(m_backLight);
		out.writeByte(m_daytimeLight);
		out.writeByte(m_leftLight);
		out.writeByte(m_rightLight);
		out.writeByte(m_leftFillLight);
		out.writeByte(m_rightFillLight);
		out.writeByte(m_doubleLight);
		
		out.writeByte(m_waterTemp);
		out.writeByte(m_currentOilInt);
		out.writeByte(m_currentOilFloat);
		out.writeByte(m_carGear);
		out.writeInt(m_restOil);
		out.writeInt(m_baterryVolInt);
		out.writeInt(m_batteryVolFloat);
		out.writeInt(m_totalDistanceHigh);
		out.writeInt(m_totalDistance);
		out.writeInt(m_totalDistanceLow);
		out.writeInt(m_exactlySpeedHigh);
		out.writeInt(m_exactlySpeedLow);
		out.writeInt(m_engineSpeedHigh);
		out.writeInt(m_engineSpeedLow);
		
		out.writeByte(m_byRoadsideparking);
		out.writeByte(m_byStorageparking);
		out.writeByte(m_byRadarsilence);
		out.writeByte(m_airShow);
		out.writeByte(m_airSwitch);
		out.writeByte(m_cycleMode);
		out.writeByte(m_Auto);
		out.writeByte(m_Dual);
		out.writeByte(m_AC_MAX);
		out.writeByte(m_Auto2);
		out.writeByte(m_aircondBlack);
		out.writeByte(m_AC);
		out.writeByte(m_realWindFog);
		out.writeByte(m_frontWindFog);
		out.writeByte(m_heatRightSeat);
		out.writeByte(m_heatLeftSeat);
		out.writeByte(m_leftFrontTempSet);
		out.writeByte(m_rightFrontTempSet);
		out.writeByte(m_left_blowFoot);
		out.writeByte(m_left_blowBody);
		out.writeByte(m_left_blowWindow);
		out.writeByte(m_leftFlowLevel);
		out.writeByte(m_right_blowFoot);
		out.writeByte(m_right_blowBody);
		out.writeByte(m_right_blowWindow);
		out.writeByte(m_rightFlowLevel);
		out.writeInt(m_outTemp);
		out.writeByte(m_leftFrontDoor);
		out.writeByte(m_rightFrontDoor);
		out.writeByte(m_leftRealDoor);
		out.writeByte(m_rightRealDoor);
		out.writeByte(m_bonnet);
		out.writeByte(m_trunk);
		out.writeByte(m_doorFlag);
		out.writeByte(m_park_handStop);
		out.writeInt(m_speed);
		out.writeInt(m_iLeftBelt);
		out.writeInt(m_iRightBelt);
		out.writeInt(m_iHood);
		out.writeInt(m_iTaixBox);
		out.writeInt(m_iEngineSpeed);
		out.writeFloat(m_fBatteryVol);
		out.writeInt(m_iLFdoorlock);
		out.writeInt(m_iRFdoorlock);
		out.writeInt(m_iLRdoorlock);
		out.writeInt(m_iRRdoorlock);
		out.writeInt(m_iCoolantTemp);
		out.writeInt(m_iFuelPa);
	}

	public int getM_speed() {
		return m_speed;
	}

	public void setM_speed(int m_speed) {
		this.m_speed = m_speed;
	}

}
