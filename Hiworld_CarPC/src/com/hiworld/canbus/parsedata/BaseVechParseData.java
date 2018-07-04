package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.VMCarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.constant.Constant;
import com.hiworld.constant.ConstantCar;
import com.hiworld.constant.SaicConstant;

import android.os.Handler;

public class BaseVechParseData implements BaseCmdidCallBack {

	private Handler m_VmHandler = null;

	private static final BaseVechParseData INSTANCE = new BaseVechParseData();

	public static BaseVechParseData getInstance() {
		return INSTANCE;
	}

	@Override
	public void setBaseVmHandler(Handler handler) {

		m_VmHandler = handler;
	}

	private byte GetByteBit(byte data, int bit) {

		byte temp = (byte) (data >> bit & 0x01);
		return temp;
	}

	private byte GetByteData(byte b, int i, int j) {

		byte temp = (byte) (b >> i & j);
		return temp;
	}

	@Override
	public int parseCmdId(byte[] buffer, int size) {

		if (buffer != null && size > 0) {
			switch (buffer[0] & 0xff) {
			case SaicConstant.SAIC_0X11:// 车身基本信息
				if (size == 11) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x11(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X12:// 车身详细信息
				if (size == 11) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x12(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X19:// 车身详细信息2
				if (size == 11) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x19(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X31:// 空调信息
				if (size == 13) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x31(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X36:// 灯光信息
				if (size == 5) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x36(data, data.length);
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}

	private void parseCmd0x36(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int i = 0;
			byte temp = GetByteBit(buffer[i], 7);
			if (VMCarInfo.getInstance().getM_nearlyLight() != temp) {
				VMCarInfo.getInstance().setM_nearlyLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 6);
			if (VMCarInfo.getInstance().getM_farLight() != temp) {
				VMCarInfo.getInstance().setM_farLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 5);
			if (VMCarInfo.getInstance().getM_showWidthLight() != temp) {
				VMCarInfo.getInstance().setM_showWidthLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 4);
			if (VMCarInfo.getInstance().getM_frontfogLight() != temp) {
				VMCarInfo.getInstance().setM_frontfogLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 3);
			if (VMCarInfo.getInstance().getM_realfogLight() != temp) {
				VMCarInfo.getInstance().setM_realfogLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 2);
			if (VMCarInfo.getInstance().getM_stopLight() != temp) {
				VMCarInfo.getInstance().setM_stopLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 1);
			if (VMCarInfo.getInstance().getM_backLight() != temp) {
				VMCarInfo.getInstance().setM_backLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 0);
			if (VMCarInfo.getInstance().getM_daytimeLight() != temp) {
				VMCarInfo.getInstance().setM_daytimeLight(temp);
				bChange = true;
			}
			// System.out.println("golf7 buffer[0] =="+buffer[0]+", buffer[1]=="+buffer[1]);
			// System.out.println("golf7 buffer[2] =="+buffer[2]+", buffer[3]=="+buffer[3]);
			i++;
			if (ActivityCarPC.CAR_NUM == ConstantCar.VWFO_GOLF7) {
				temp = GetByteBit(buffer[i], 1);
				if (VMCarInfo.getInstance().getM_leftLight() != temp) {
					VMCarInfo.getInstance().setM_leftLight(temp);
					bChange = true;
				}
				// System.out.println("golf7 temp =="+temp+", i=="+i);
				temp = GetByteBit(buffer[i], 0);
				if (VMCarInfo.getInstance().getM_rightLight() != temp) {
					VMCarInfo.getInstance().setM_rightLight(temp);
					bChange = true;
				}
			} else {
				temp = GetByteBit(buffer[i], 6);
				if (VMCarInfo.getInstance().getM_leftLight() != temp) {
					VMCarInfo.getInstance().setM_leftLight(temp);
					bChange = true;
				}
				System.out.println("golf7  temp ==" + temp + ", i==" + i);
				temp = GetByteBit(buffer[i], 7);
				if (VMCarInfo.getInstance().getM_rightLight() != temp) {
					VMCarInfo.getInstance().setM_rightLight(temp);
					bChange = true;
				}
			}

			temp = GetByteBit(buffer[i], 3);
			if (VMCarInfo.getInstance().getM_doubleLight() != temp) {
				VMCarInfo.getInstance().setM_doubleLight(temp);
				bChange = true;
			}

			if (bChange) {
				if (m_VmHandler != null) {
					m_VmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x31(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int temp = buffer[11] & 0xff;
			if (VMCarInfo.getInstance().getM_outTemp() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_outTemp(temp);
			}
			if (bChange) {
				if (m_VmHandler != null) {
					m_VmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x11(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;

			if (VMCarInfo.getInstance().getM_park_handStop() != GetByteBit(buffer[0], 3)) {
				bChange = true;
				VMCarInfo.getInstance().setM_park_handStop(GetByteBit(buffer[0], 3));
			}

			if (VMCarInfo.getInstance().getM_speed() != (buffer[1] & 0xff)) {
				bChange = true;
				VMCarInfo.getInstance().setM_speed((buffer[1] & 0xff));
			}

			if (bChange) {
				if (m_VmHandler != null) {
					m_VmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x19(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;

			if (VMCarInfo.getInstance().getM_engineSpeedHigh() != buffer[8]) {
				bChange = true;
				VMCarInfo.getInstance().setM_engineSpeedHigh(buffer[8]);
			}

			if (VMCarInfo.getInstance().getM_engineSpeedLow() != buffer[9]) {
				bChange = true;
				VMCarInfo.getInstance().setM_engineSpeedLow(buffer[9]);
			}

			if (bChange) {
				if (m_VmHandler != null) {
					m_VmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x12(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int i = 2;
			byte temp = GetByteBit(buffer[i], 7);
			if (VMCarInfo.getInstance().getM_leftFrontDoor() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_leftFrontDoor(temp);
			}
			temp = GetByteBit(buffer[i], 6);
			if (VMCarInfo.getInstance().getM_rightFrontDoor() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_rightFrontDoor(temp);
			}
			temp = GetByteBit(buffer[i], 5);
			if (VMCarInfo.getInstance().getM_leftRealDoor() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_leftRealDoor(temp);
			}
			temp = GetByteBit(buffer[i], 4);
			if (VMCarInfo.getInstance().getM_rightRealDoor() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_rightRealDoor(temp);
			}
			temp = GetByteBit(buffer[i], 3);
			if (VMCarInfo.getInstance().getM_iTaixBox() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iTaixBox(temp);
			}
			temp = GetByteBit(buffer[i], 2);
			if (VMCarInfo.getInstance().getM_iHood() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iHood(temp);
			}

			if (VMCarInfo.getInstance().getM_currentOilInt() != buffer[3]) {
				bChange = true;
				VMCarInfo.getInstance().setM_currentOilInt(buffer[3]);
			}
			if (VMCarInfo.getInstance().getM_currentOilFloat() != buffer[4]) {
				bChange = true;
				VMCarInfo.getInstance().setM_currentOilFloat(buffer[4]);
			}
			// getM_leanerAlarm()
			if (VMCarInfo.getInstance().getM_leanerAlarm() != GetByteBit(buffer[6], 4)) {
				bChange = true;
				VMCarInfo.getInstance().setM_leanerAlarm(GetByteBit(buffer[6], 4));
			}
			if (VMCarInfo.getInstance().getM_lifebeltAlarm() != GetByteBit(buffer[6], 5)) {
				bChange = true;
				VMCarInfo.getInstance().setM_lifebeltAlarm(GetByteBit(buffer[6], 5));
			}

			if (VMCarInfo.getInstance().getM_restOil() != buffer[7]) {
				bChange = true;
				VMCarInfo.getInstance().setM_restOil(buffer[7]);
			}

			if (VMCarInfo.getInstance().getM_baterryVolInt() != buffer[8]) {
				bChange = true;
				VMCarInfo.getInstance().setM_baterryVolInt(buffer[8]);
			}

			if (VMCarInfo.getInstance().getM_batteryVolFloat() != buffer[9]) {
				bChange = true;
				VMCarInfo.getInstance().setM_batteryVolFloat(buffer[9]);
			}

			if (bChange) {
				if (m_VmHandler != null) {
					m_VmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

}
