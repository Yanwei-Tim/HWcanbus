package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.SaicCarInfo;
import com.hiworld.adapter.VMCarInfo;
import com.hiworld.constant.Constant;
import com.hiworld.constant.SaicConstant;

import android.os.Handler;

public class SaicParseData implements SaicFragmentCallback {

	private Handler m_VmuiHandler = null;
	private Handler m_FuelHandler = null;

	private static final SaicParseData INSTANCE = new SaicParseData();

	public static SaicParseData getInstance() {
		return INSTANCE;
	}

	public int parseCmd(byte[] buffer, int size) {
		if (buffer != null && size > 0) {
			switch (buffer[0] & 0xff) {
			case SaicConstant.SAIC_0X12:// 车身详细信息
				if (size == 11) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x12(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X31:// 空调信息
				if (size == 13) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x31(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X32:// 车身信息
				if (size == 15) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x32(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X33:// 车身安全信息
				if (size == 7) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x33(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X34:// 里程信息
				if (size == 26) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x34(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X36:// 灯光信息
				if (size == 5) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x36(data, data.length);
				}
				break;
			case SaicConstant.SAIC_0X77:// 报警信息
				if (size == 9) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x77(data, data.length);
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}

	private void parseCmd0x77(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			int i = 0;

			VMCarInfo.getInstance().setM_lifebeltAlarm((byte) 0);
			VMCarInfo.getInstance().setM_leanerAlarm((byte) 0);

			int temp = buffer[i] & 0xff;
			if (temp > 0) {// 有报警
				for (int j = 0; j < temp; j++) {
					switch (buffer[j + 1] & 0xff) {
					case 0x04:
						VMCarInfo.getInstance().setM_lifebeltAlarm((byte) 1);
						break;
					case 0x05:
					case 0x06:
						VMCarInfo.getInstance().setM_leanerAlarm((byte) 1);
						break;
					default:
						break;
					}
				}
			}

			if (m_VmuiHandler != null) {
				m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
			}

		}
	}

	private void parseCmd0x34(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int temp = GetByteInt(buffer[4], buffer[5], buffer[6]);
			if (SaicCarInfo.getInstance().getM_iMileage() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iMileage(temp);
			}
			if (VMCarInfo.getInstance().getM_totalDistance() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_totalDistance(temp);
			}

			temp = GetByteInt((byte) 0, buffer[7], buffer[8]);
			if (SaicCarInfo.getInstance().getM_iAvgFuel1() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iAvgFuel1(temp);
			}

			temp = GetByteInt((byte) 0, buffer[12], buffer[13]);
			if (SaicCarInfo.getInstance().getM_iAvgFuel2() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iAvgFuel2(temp);
			}

			temp = GetByteInt((byte) 0, buffer[17], buffer[18]);
			if (SaicCarInfo.getInstance().getM_iAvgFuel3() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iAvgFuel3(temp);
			}

			temp = GetByteBit(buffer[22], 2);
			if (SaicCarInfo.getInstance().getM_iUnitMileage() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iUnitMileage(temp);
			}

			temp = GetByteData(buffer[22], 0, 0x03);
			if (SaicCarInfo.getInstance().getM_iUnitFuel() != temp) {
				bChange = true;
				SaicCarInfo.getInstance().setM_iUnitFuel(temp);
			}

			if (bChange) {
				if (m_FuelHandler != null) {
					m_FuelHandler.obtainMessage(Constant.HANDLER_MESSAGE_FUEL, SaicCarInfo.getInstance())
							.sendToTarget();
				}
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
				}
			}

		}
	}

	private int GetByteInt(byte b, byte c, byte d) {

		int temp = (b & 0xff) * 256 * 256 + (c & 0xff) * 256 + (d & 0xff);
		return temp;
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

			i++;
			temp = GetByteBit(buffer[i], 6);
			if (VMCarInfo.getInstance().getM_leftLight() != temp) {
				VMCarInfo.getInstance().setM_leftLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 7);
			if (VMCarInfo.getInstance().getM_rightLight() != temp) {
				VMCarInfo.getInstance().setM_rightLight(temp);
				bChange = true;
			}
			temp = GetByteBit(buffer[i], 3);
			if (VMCarInfo.getInstance().getM_doubleLight() != temp) {
				VMCarInfo.getInstance().setM_doubleLight(temp);
				bChange = true;
			}

			if (bChange) {
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x33(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int temp = GetByteBit(buffer[3], 7);
			if (VMCarInfo.getInstance().getM_iLFdoorlock() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iLFdoorlock(temp);
			}
			temp = GetByteBit(buffer[3], 6);
			if (VMCarInfo.getInstance().getM_iRFdoorlock() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iRFdoorlock(temp);
			}
			temp = GetByteBit(buffer[3], 5);
			if (VMCarInfo.getInstance().getM_iLRdoorlock() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iLRdoorlock(temp);
			}
			temp = GetByteBit(buffer[3], 4);
			if (VMCarInfo.getInstance().getM_iRRdoorlock() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iRRdoorlock(temp);
			}

			if (bChange) {
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
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
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
				}
			}
		}
	}

	private void parseCmd0x32(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int temp = 0;
			float ff = 0;
			temp = (buffer[2] & 0xff) * 256 + (buffer[3] & 0xff);
			if (VMCarInfo.getInstance().getM_iEngineSpeed() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iEngineSpeed(temp);
			}

			temp = (buffer[4] & 0xff) * 256 + (buffer[5] & 0xff);
			if (VMCarInfo.getInstance().getM_speed() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_speed(temp);
			}

			ff = (float) ((buffer[6] & 0xff) * 0.1);
			if (VMCarInfo.getInstance().getM_fBatteryVol() != ff) {
				bChange = true;
				VMCarInfo.getInstance().setM_fBatteryVol(ff);
			}

			temp = buffer[8] & 0xff;
			if (VMCarInfo.getInstance().getM_restOil() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_restOil(temp);
			}

			if (bChange) {
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
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
			temp = GetByteBit(buffer[i], 1);
			if (VMCarInfo.getInstance().getM_iLeftBelt() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iLeftBelt(temp);
			}
			temp = GetByteBit(buffer[i], 0);
			if (VMCarInfo.getInstance().getM_iRightBelt() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iRightBelt(temp);
			}

			if (bChange) {
				if (m_VmuiHandler != null) {
					m_VmuiHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance())
							.sendToTarget();
				}
			}
		}
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
	public void setVmUIHadler(Handler mHandler) {

		m_VmuiHandler = mHandler;
	}

	@Override
	public void setFuelHadler(Handler mHandler) {

		m_FuelHandler = mHandler;
	}

}
