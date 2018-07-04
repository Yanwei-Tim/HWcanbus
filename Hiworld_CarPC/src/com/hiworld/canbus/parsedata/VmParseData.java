package com.hiworld.canbus.parsedata;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.hiworld.adapter.VMCarInfo;
import com.hiworld.constant.Constant;

public class VmParseData implements VMFragmentCallBack {

	private Handler m_SettHandlerRadar;// 雷达信息
	private Handler m_SettHandlerVmUIHandler;// UI界面的Handler
	private static final VmParseData INSTANCE = new VmParseData();

	public static VmParseData getInstance() {
		return INSTANCE;
	}

	public static void setContext(Context context) {

	}

	public void parseVwfoCmd(byte[] buffer, int size) {

		if (buffer != null && size > 1) {
			switch (buffer[0] & 0xff) {
			case 0x47:// 驾驶员辅助系统设定信息
				if (size == 15) {
					boolean m_breset = false;
					if (VMCarInfo.getInstance().getM_byRoadsideparking() != (byte) (buffer[14] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_byRoadsideparking((byte) (buffer[14] >> 7 & 0x01));
						m_breset = true;
					}

					if (VMCarInfo.getInstance().getM_byStorageparking() != (byte) (buffer[14] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_byStorageparking((byte) (buffer[14] >> 6 & 0x01));
						m_breset = true;
					}

					if (VMCarInfo.getInstance().getM_byRadarsilence() != (byte) (buffer[14] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_byRadarsilence((byte) (buffer[14] >> 5 & 0x01));
						m_breset = true;
					}

					if (m_breset) {
						if (m_SettHandlerRadar != null) {
							m_SettHandlerRadar.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;

			/////////////////////////////////////////////////////////////////
			case 0x12:// 车辆具体信息-1
				if (size == 11) {
					boolean m_breset = false;
					if (VMCarInfo.getInstance().getM_waterTemp() != buffer[3]) {
						VMCarInfo.getInstance().setM_waterTemp(buffer[3]);
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_currentOilInt() != buffer[4]) {
						VMCarInfo.getInstance().setM_currentOilInt(buffer[4]);
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_currentOilFloat() != buffer[5]) {
						VMCarInfo.getInstance().setM_currentOilFloat(buffer[5]);
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_carGear() != buffer[6]) {
						VMCarInfo.getInstance().setM_carGear(buffer[6]);
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_lowOilAlarm() != (byte) (buffer[7] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_lowOilAlarm((byte) (buffer[7] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_lowBatteryAlarm() != (byte) (buffer[7] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_lowBatteryAlarm((byte) (buffer[7] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_lifebeltAlarm() != (byte) (buffer[7] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_lifebeltAlarm((byte) (buffer[7] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leanerAlarm() != (byte) (buffer[7] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_leanerAlarm((byte) (buffer[7] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_engineOilAlarm() != (byte) (buffer[7] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_engineOilAlarm((byte) (buffer[7] >> 3 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_restOil() != (int) (buffer[8] & 0xff)) {
						VMCarInfo.getInstance().setM_restOil((int) (buffer[8] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_baterryVolInt() != (buffer[9] & 0xff)) {
						VMCarInfo.getInstance().setM_baterryVolInt((buffer[9] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_batteryVolFloat() != (buffer[10] & 0xff)) {
						VMCarInfo.getInstance().setM_batteryVolFloat(buffer[10] & 0xff);
						m_breset = true;
					}
					if (m_breset) {
						if (m_SettHandlerVmUIHandler != null) {
							m_SettHandlerVmUIHandler
									.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			case 0x13:// 车辆具体信息-2
				if (size == 11) {
					boolean m_breset = false;
					if (VMCarInfo.getInstance().getM_totalDistanceHigh() != (buffer[1] & 0xff)) {
						VMCarInfo.getInstance().setM_totalDistanceHigh((buffer[1] & 0xff));
						// Log.i("info", "=====getM_totalDistanceHigh"+(buffer[1] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_totalDistance() != (buffer[2] & 0xff)) {
						VMCarInfo.getInstance().setM_totalDistance((buffer[2] & 0xff));
						// Log.i("info", "=====getM_totalDistance"+(buffer[2] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_totalDistanceLow() != (buffer[3] & 0xff)) {
						VMCarInfo.getInstance().setM_totalDistanceLow((buffer[3] & 0xff));
						// Log.i("info", "=====getM_totalDistanceLow"+(buffer[3] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_exactlySpeedHigh() != (buffer[4] & 0xff)) {
						VMCarInfo.getInstance().setM_exactlySpeedHigh((buffer[4] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_exactlySpeedLow() != (buffer[5] & 0xff)) {
						VMCarInfo.getInstance().setM_exactlySpeedLow((buffer[5] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_engineSpeedHigh() != (buffer[9] & 0xff)) {
						VMCarInfo.getInstance().setM_engineSpeedHigh(buffer[9] & 0xff);
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_engineSpeedLow() != (buffer[10] & 0xff)) {
						VMCarInfo.getInstance().setM_engineSpeedLow((buffer[10] & 0xff));
						m_breset = true;
					}
					if (m_breset) {
						if (m_SettHandlerVmUIHandler != null) {
							m_SettHandlerVmUIHandler
									.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}

				break;
			case 0x18:// 车辆具体信息-3
				if (size == 11) {

					Log.i("tag", "========解析灯光");
					boolean m_breset = false;
					if (VMCarInfo.getInstance().getM_nearlyLight() != (byte) (buffer[1] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_nearlyLight((byte) (buffer[1] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_farLight() != (byte) (buffer[1] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_farLight((byte) (buffer[1] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_showWidthLight() != (byte) (buffer[1] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_showWidthLight((byte) (buffer[1] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_frontfogLight() != (byte) (buffer[1] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_frontfogLight((byte) (buffer[1] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_realfogLight() != (byte) (buffer[1] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_realfogLight((byte) (buffer[1] >> 3 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_stopLight() != (byte) (buffer[1] >> 2 & 0x01)) {
						VMCarInfo.getInstance().setM_stopLight((byte) (buffer[1] >> 2 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_backLight() != (byte) (buffer[1] >> 1 & 0x01)) {
						VMCarInfo.getInstance().setM_backLight((byte) (buffer[1] >> 1 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_daytimeLight() != (byte) (buffer[1] >> 0 & 0x01)) {
						VMCarInfo.getInstance().setM_daytimeLight((byte) (buffer[1] >> 0 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftLight() != (byte) (buffer[2] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_leftLight((byte) (buffer[2] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightLight() != (byte) (buffer[2] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_rightLight((byte) (buffer[2] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftFillLight() != (byte) (buffer[2] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_leftFillLight((byte) (buffer[2] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightFillLight() != (byte) (buffer[2] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_rightFillLight((byte) (buffer[2] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_doubleLight() != (byte) (buffer[2] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_doubleLight((byte) (buffer[2] >> 3 & 0x01));
						m_breset = true;
					}
					if (m_breset) {
						if (m_SettHandlerVmUIHandler != null) {
							m_SettHandlerVmUIHandler
									.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			////////////////////////////////////////////////////////////////////////////////////////
			case 0x73:// 空调信息
				if (size == 9) {
					boolean m_breset = false;
					if (VMCarInfo.getInstance().getM_airShow() != (byte) (buffer[1] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_airShow((byte) (buffer[1] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_airSwitch() != (byte) (buffer[1] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_airShow((byte) (buffer[1] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_cycleMode() != (byte) (buffer[1] >> 4 & 0x03)) {
						VMCarInfo.getInstance().setM_cycleMode((byte) (buffer[1] >> 4 & 0x03));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_Auto() != (byte) (buffer[1] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_Auto((byte) (buffer[1] >> 3 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_Dual() != (byte) (buffer[1] >> 2 & 0x01)) {
						VMCarInfo.getInstance().setM_Dual((byte) (buffer[1] >> 2 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_AC_MAX() != (byte) (buffer[1] >> 1 & 0x01)) {
						VMCarInfo.getInstance().setM_AC_MAX((byte) (buffer[1] >> 1 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_Auto2() != (byte) (buffer[1] & 0x01)) {
						VMCarInfo.getInstance().setM_Auto2((byte) (buffer[1] & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_aircondBlack() != (byte) (buffer[2] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_aircondBlack((byte) (buffer[2] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_AC() != (byte) (buffer[2] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_AC((byte) (buffer[2] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_realWindFog() != (byte) (buffer[2] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_realWindFog((byte) (buffer[2] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_frontWindFog() != (byte) (buffer[2] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_frontWindFog((byte) (buffer[2] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_heatRightSeat() != (byte) (buffer[2] >> 2 & 0x03)) {
						VMCarInfo.getInstance().setM_heatRightSeat((byte) (buffer[2] >> 2 & 0x03));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_heatLeftSeat() != (byte) (buffer[2] & 0x03)) {
						VMCarInfo.getInstance().setM_heatLeftSeat((byte) (buffer[2] & 0x03));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftFrontTempSet() != (byte) (buffer[3])) {
						VMCarInfo.getInstance().setM_leftFrontTempSet((byte) (buffer[3]));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightFrontTempSet() != (byte) (buffer[4])) {
						VMCarInfo.getInstance().setM_rightFrontTempSet((byte) (buffer[4]));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_left_blowFoot() != (byte) (buffer[5] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_left_blowFoot((byte) (buffer[5] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_left_blowBody() != (byte) (buffer[5] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_left_blowBody((byte) (buffer[5] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_left_blowWindow() != (byte) (buffer[5] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_left_blowWindow((byte) (buffer[5] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftFlowLevel() != (byte) (buffer[5] & 0x07)) {
						VMCarInfo.getInstance().setM_leftFlowLevel((byte) (buffer[5] & 0x07));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_right_blowFoot() != (byte) (buffer[6] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_right_blowFoot((byte) (buffer[6] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_right_blowBody() != (byte) (buffer[6] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_right_blowBody((byte) (buffer[6] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_right_blowWindow() != (byte) (buffer[6] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_right_blowWindow((byte) (buffer[6] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightFlowLevel() != (byte) (buffer[6] & 0x07)) {
						VMCarInfo.getInstance().setM_rightFlowLevel((byte) (buffer[6] & 0x07));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_outTemp() != (int) (buffer[7] & 0xff)) {
						VMCarInfo.getInstance().setM_outTemp((int) (buffer[7] & 0xff));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftFrontDoor() != (byte) (buffer[8] >> 6 & 0x01)) {
						VMCarInfo.getInstance().setM_leftFrontDoor((byte) (buffer[8] >> 6 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightFrontDoor() != (byte) (buffer[8] >> 7 & 0x01)) {
						VMCarInfo.getInstance().setM_rightFrontDoor((byte) (buffer[8] >> 7 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_leftRealDoor() != (byte) (buffer[8] >> 5 & 0x01)) {
						VMCarInfo.getInstance().setM_leftRealDoor((byte) (buffer[8] >> 5 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_rightRealDoor() != (byte) (buffer[8] >> 4 & 0x01)) {
						VMCarInfo.getInstance().setM_rightRealDoor((byte) (buffer[8] >> 4 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_trunk() != (byte) (buffer[8] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_trunk((byte) (buffer[8] >> 3 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_bonnet() != (byte) (buffer[8] >> 2 & 0x01)) {
						VMCarInfo.getInstance().setM_bonnet((byte) (buffer[8] >> 2 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_doorFlag() != (byte) (buffer[8] & 0x01)) {
						VMCarInfo.getInstance().setM_doorFlag((byte) (buffer[8] & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_iHood() != (byte) (buffer[8] >> 2 & 0x01)) {
						VMCarInfo.getInstance().setM_iHood((byte) (buffer[8] >> 2 & 0x01));
						m_breset = true;
					}
					if (VMCarInfo.getInstance().getM_iTaixBox() != (byte) (buffer[8] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_iTaixBox((byte) (buffer[8] >> 3 & 0x01));
						m_breset = true;
					}

					if (m_breset) {
						if (m_SettHandlerVmUIHandler != null) {
							m_SettHandlerVmUIHandler
									.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			//////////
			case 0x72:// 车身信息
				if (size == 15) {
					Boolean m_breast = false;
					if (VMCarInfo.getInstance().getM_park_handStop() != (byte) (buffer[1] >> 3 & 0x01)) {
						VMCarInfo.getInstance().setM_park_handStop((byte) (buffer[1] >> 3 & 0x01));
						m_breast = true;
					}
					if (VMCarInfo.getInstance().getM_speed() != (buffer[2] & 0xff)) {
						VMCarInfo.getInstance().setM_speed((buffer[2] & 0xff));
						m_breast = true;
					}
					if (m_breast) {
						if (m_SettHandlerVmUIHandler != null) {
							m_SettHandlerVmUIHandler
									.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void setVmRadarHandler(Handler mHandler) {

		m_SettHandlerRadar = mHandler;
	}

	@Override
	public void setVmUIHadler(Handler mHandler) {

		this.m_SettHandlerVmUIHandler = mHandler;
	}
}
