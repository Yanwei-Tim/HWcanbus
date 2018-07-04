package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.Golf7CarInfo;
import com.hiworld.constant.Constant;

import android.os.Handler;

public class Golf7ParseData implements Golf7FragmentCallBack {

	private Handler m_fromStartHandler;
	private Handler m_longTimeHandler;
	private Handler m_fromAddOilHandler;

	@Override
	public void setFromStartHandler(Handler handler) {

		this.m_fromStartHandler = handler;
	}

	@Override
	public void setLongTimeHandler(Handler handler) {

		this.m_longTimeHandler = handler;
	}

	@Override
	public void setFromAddOilHandler(Handler handler) {

		this.m_fromAddOilHandler = handler;
	}

	private static final Golf7ParseData INSTANCE = new Golf7ParseData();

	public static Golf7ParseData getInstance() {
		return INSTANCE;
	}

	public void ParseGolf7CMD(byte[] buffer, int size) {// 去头和ID
		if (buffer != null && size > 1) {
			switch (buffer[0] & 0xff) {
			case 0x13:// 自起动以后
				if (size == 11) {
					boolean m_breast = false;

					if (Golf7CarInfo.getInstance().getM_trip1AverageFuelHigh() != (int) (buffer[1] & 0xff)) {

						Golf7CarInfo.getInstance().setM_trip1AverageFuelHigh((int) (buffer[1] & 0xff));

						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1AverageFuelLow() != (int) (buffer[2] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1AverageFuelLow((int) (buffer[2] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1cruisingDistanceHigh() != (int) (buffer[3] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1cruisingDistanceHigh((int) (buffer[3] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1cruisingDistanceLow() != (int) (buffer[4] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1cruisingDistanceLow((int) (buffer[4] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1travelDistanceHigh() != (int) (buffer[5] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1travelDistanceHigh((int) (buffer[5] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1travelDistanceLow() != (int) (buffer[6] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1travelDistanceLow((int) (buffer[6] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1TravelTimeHigh() != (int) (buffer[7] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1TravelTimeHigh((int) (buffer[7] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1TravelTimeLow() != (int) (buffer[8] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1TravelTimeLow((int) (buffer[8] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip1AverageSpeed() != (buffer[9] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip1AverageSpeed(buffer[9] & 0xff);

						m_breast = true;
					}
					if (m_breast) {

						if (m_fromStartHandler != null) {
							m_fromStartHandler.obtainMessage(0x32, Golf7CarInfo.getInstance()).sendToTarget();
						}
					}
				}

				break;
			case 0x14:// 长时间
				if (size == 11) {
					boolean m_breast = false;
					if (Golf7CarInfo.getInstance().getM_trip2AverageFuelHigh() != (int) (buffer[1] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2AverageFuelHigh((int) (buffer[1] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2AverageFuelLow() != (int) (buffer[2] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2AverageFuelLow((int) (buffer[2] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2cruisingDistanceHigh() != (int) (buffer[3] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2cruisingDistanceHigh((int) (buffer[3] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2cruisingDistanceLow() != (int) (buffer[4] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2cruisingDistanceLow((int) (buffer[4] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2travelDistanceHigh() != (int) (buffer[5] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2travelDistanceHigh((int) (buffer[5] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2travelDistanceLow() != (int) (buffer[6] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2travelDistanceLow((int) (buffer[6] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2TravelTimeHigh() != (int) (buffer[7] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2TravelTimeHigh((int) (buffer[7] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2TravelTimeLow() != (int) (buffer[8] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2TravelTimeLow((int) (buffer[8] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip2AverageSpeed() != (buffer[9] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip2AverageSpeed(buffer[9] & 0xff);

						m_breast = true;
					}
					if (m_breast) {
						if (m_longTimeHandler != null) {
							m_longTimeHandler
									.obtainMessage(Constant.GOLF7_MESSAGE＿SEND_INFO, Golf7CarInfo.getInstance())
									.sendToTarget();
						}
					}
				}

				break;
			case 0x15:// 自加油以后
				if (size == 11) {
					boolean m_breast = false;
					if (Golf7CarInfo.getInstance().getM_trip3AverageFuelHigh() != (int) (buffer[1] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3AverageFuelHigh((int) (buffer[1] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3AverageFuelLow() != (int) (buffer[2] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3AverageFuelLow((int) (buffer[2] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3cruisingDistanceHigh() != (int) (buffer[3] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3cruisingDistanceHigh((int) (buffer[3] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3cruisingDistanceLow() != (int) (buffer[4] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3cruisingDistanceLow((int) (buffer[4] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3travelDistanceHigh() != (int) (buffer[5] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3travelDistanceHigh((int) (buffer[5] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3travelDistanceLow() != (int) (buffer[6] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3travelDistanceLow((int) (buffer[6] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3TravelTimeHigh() != (int) (buffer[7] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3TravelTimeHigh((int) (buffer[7] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3TravelTimeLow() != (int) (buffer[8] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3TravelTimeLow((int) (buffer[8] & 0xff));
						m_breast = true;
					}
					if (Golf7CarInfo.getInstance().getM_trip3AverageSpeed() != (buffer[9] & 0xff)) {
						Golf7CarInfo.getInstance().setM_trip3AverageSpeed(buffer[9] & 0xff);

						m_breast = true;
					}
					if (m_breast) {
						if (m_fromAddOilHandler != null) {
							m_fromAddOilHandler
									.obtainMessage(Constant.GOLF7_MESSAGE＿SEND_INFO, Golf7CarInfo.getInstance())
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
