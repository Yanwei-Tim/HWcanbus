package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.HondaCarInfo;
import com.hiworld.constant.Constant;

import android.os.Handler;

public class HondaParseData implements HondaFragmentCallBack {

	private Handler m_setThisDrivehandler;
	private Handler m_settripAHandler;

	@Override
	public void setThisDrive(Handler handler) {

		this.m_setThisDrivehandler = handler;
	}

	@Override
	public void setTripAHandler(Handler handler) {

		this.m_settripAHandler = handler;
	}

	private static final HondaParseData INSTANCE = new HondaParseData();

	public static HondaParseData getInstance() {
		return INSTANCE;
	}

	public void ParseHondaCMD(byte[] buffer, int size) {
		if (buffer != null && size > 1) {
			switch (buffer[0] & 0xff) {
			case 0x16:// 当前油耗、里程信息
				if (size == 18) {
					// Log.e("info", "=========进入到解析方法中去");
					boolean m_breast = false;
					if (HondaCarInfo.getInstance().getM_nowInstantFuel() != (int) (buffer[1] & 0xff)) {
						HondaCarInfo.getInstance().setM_nowInstantFuel((int) (buffer[1] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_nowAverageFuelHigh() != (int) (buffer[2] & 0xff)) {
						HondaCarInfo.getInstance().setM_nowAverageFuelHigh((int) (buffer[2] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_nowAverageFuelLow() != (int) (buffer[3] & 0xff)) {
						HondaCarInfo.getInstance().setM_nowAverageFuelLow((int) (buffer[3] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_hisAverageFuelHigh() != (int) (buffer[4] & 0xff)) {
						HondaCarInfo.getInstance().setM_hisAverageFuelHigh((int) (buffer[4] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_hisAverageFuelLow() != (int) (buffer[5] & 0xff)) {
						HondaCarInfo.getInstance().setM_hisAverageFuelLow((int) (buffer[5] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAaveFuelHigh() != (int) (buffer[6] & 0xff)) {
						HondaCarInfo.getInstance().setM_tripAaveFuelHigh((int) (buffer[6] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAaveFuelLow() != (int) (buffer[7] & 0xff)) {
						HondaCarInfo.getInstance().setM_tripAaveFuelLow((int) (buffer[7] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAdistanceHigh() != (int) (buffer[8] & 0xff)) {
						HondaCarInfo.getInstance().setM_tripAdistanceHigh((int) (buffer[8] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAdistanceMedium() != (int) (buffer[9] & 0xff)) {
						HondaCarInfo.getInstance().setM_tripAdistanceMedium((int) (buffer[9] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAdistanceLow() != (int) (buffer[10] & 0xff)) {
						HondaCarInfo.getInstance().setM_tripAdistanceLow((int) (buffer[10] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_drivingMileageHigh() != (int) (buffer[11] & 0xff)) {
						HondaCarInfo.getInstance().setM_drivingMileageHigh((int) (buffer[11] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_drivingMileageLow() != (int) (buffer[12] & 0xff)) {
						HondaCarInfo.getInstance().setM_drivingMileageLow((int) (buffer[12] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_drivingMileageUnit() != (int) (buffer[13] >> 7 & 0x01)) {
						HondaCarInfo.getInstance().setM_drivingMileageUnit((int) (buffer[13] >> 7 & 0x01));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_InstantFuelUnit() != (int) (buffer[13] & 0x03)) {
						HondaCarInfo.getInstance().setM_InstantFuelUnit((int) (buffer[13] & 0x03));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripDistanceUnit() != (int) (buffer[13] >> 6 & 0x01)) {
						HondaCarInfo.getInstance().setM_tripDistanceUnit((int) (buffer[13] >> 6 & 0x01));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_tripAverageFuelUnit() != (int) (buffer[13] >> 4 & 0x03)) {
						HondaCarInfo.getInstance().setM_tripAverageFuelUnit((int) (buffer[13] >> 4 & 0x03));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_averageFuelUnit() != (int) (buffer[13] >> 2 & 0x03)) {
						HondaCarInfo.getInstance().setM_averageFuelUnit((int) (buffer[13] >> 2 & 0x03));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_fuelRange() != (int) (buffer[14] & 0xff)) {
						HondaCarInfo.getInstance().setM_fuelRange((int) (buffer[14] & 0xff));
						m_breast = true;
					}
					if (m_breast) {

						if (m_setThisDrivehandler != null) {
							m_setThisDrivehandler
									.obtainMessage(Constant.Honda_MESSAGE_SEND_INFO, HondaCarInfo.getInstance())
									.sendToTarget();
							// Log.e("info", "=========通过Handler传递消息至Fragment中");
						}
						if (m_settripAHandler != null) {
							m_settripAHandler
									.obtainMessage(Constant.Honda_MESSAGE_SEND_INFO, HondaCarInfo.getInstance())
									.sendToTarget();
						}
					}

				}
				break;
			case 0x17:// 历史油耗、里程信息
				if (size == 18) {
					boolean m_breast = false;
					if (HondaCarInfo.getInstance().getM_trip1distanceHigh() != (int) (buffer[1] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip1distanceHigh((int) (buffer[1] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip1distanceMedium() != (int) (buffer[2] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip1distanceMedium((int) (buffer[2] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip1distanceLow() != (int) (buffer[3] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip1distanceLow((int) (buffer[3] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip1aveFuelHigh() != (int) (buffer[4] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip1aveFuelHigh((int) (buffer[4] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip1aveFuelLow() != (int) (buffer[5] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip1aveFuelLow((int) (buffer[5] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip2distanceHigh() != (int) (buffer[6] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip2distanceHigh((int) (buffer[6] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip2distanceMedium() != (int) (buffer[7] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip2distanceMedium((int) (buffer[7] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip2distanceLow() != (int) (buffer[8] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip2distanceLow((int) (buffer[8] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip2aveFuelHigh() != (int) (buffer[9] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip2aveFuelHigh((int) (buffer[9] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip2aveFuelLow() != (int) (buffer[10] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip2aveFuelLow((int) (buffer[10] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip3distanceHigh() != (int) (buffer[11] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip3distanceHigh((int) (buffer[11] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip3distanceMedium() != (int) (buffer[12] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip3distanceMedium((int) (buffer[12] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip3distanceLow() != (int) (buffer[13] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip3distanceLow((int) (buffer[13] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip3aveFuelHigh() != (int) (buffer[14] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip3aveFuelHigh((int) (buffer[14] & 0xff));
						m_breast = true;
					}
					if (HondaCarInfo.getInstance().getM_trip3aveFuelLow() != (int) (buffer[15] & 0xff)) {
						HondaCarInfo.getInstance().setM_trip3aveFuelLow((int) (buffer[15] & 0xff));
						m_breast = true;
					}
					if (m_breast) {
						if (m_settripAHandler != null) {
							m_settripAHandler
									.obtainMessage(Constant.Honda_MESSAGE_SEND_INFO, HondaCarInfo.getInstance())
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
}
