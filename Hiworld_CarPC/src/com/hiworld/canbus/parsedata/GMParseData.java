package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.SaicCarInfo;
import com.hiworld.adapter.VMCarInfo;
import com.hiworld.constant.Constant;
import com.hiworld.constant.GMConstant;

import android.os.Handler;

public class GMParseData implements GMFragmentCallBack {

	private Handler mVmHandler;
	private Handler mFuelHandler;

	private static final GMParseData INSTANCE = new GMParseData();

	public static GMParseData getInstance() {
		return INSTANCE;
	}

	@Override
	public void setVmUIHadler(Handler mHandler) {

		mVmHandler = mHandler;
	}

	@Override
	public int parseGmCmd(byte[] buffer, int size) {

		if (buffer != null && size > 0) {
			switch (buffer[0] & 0xff) {
			case GMConstant.GM_CMD_0X12:// 车身详细信息
				if (size == 11) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x12(data, data.length);
				}
				break;
			case GMConstant.GM_CMD_0X31:// 空调信息
				if (size == 13) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x31(data, data.length);
				}
				break;
			case GMConstant.GM_CMD_0X32:// 车身信息
				if (size == 15) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x32(data, data.length);
				}
				break;
			case GMConstant.GM_CMD_0X34:// 油耗里程信息
				if (size == 26) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x34(data, data.length);
				}
				break;
			case GMConstant.GM_CMD_0X67:
				if (size == 3) {
					byte[] data = new byte[size - 1];
					System.arraycopy(buffer, 1, data, 0, size - 1);
					parseCmd0x67(data, data.length);
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}

	private void parseCmd0x67(byte[] buffer, int length) {

		boolean bChange = false;
		byte temp = GetByteBit(buffer[1], 3);
		if (VMCarInfo.getInstance().getM_leftLight() != temp) {
			VMCarInfo.getInstance().setM_leftLight(temp);
			bChange = true;
		}
		// System.out.println("golf7 temp =="+temp+", i=="+i);
		temp = GetByteBit(buffer[1], 4);
		if (VMCarInfo.getInstance().getM_rightLight() != temp) {
			VMCarInfo.getInstance().setM_rightLight(temp);
			bChange = true;
		}

		if (bChange && GetByteBit(buffer[0], 5) == 1) {
			if (mVmHandler != null) {
				mVmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
			}
		}

	}

	private void parseCmd0x34(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			// 瞬时油耗
			int temp = GetByteInt(buffer[1], buffer[0], 0);
			if (SaicCarInfo.getInstance().getM_iInstantFuel() != temp) {
				SaicCarInfo.getInstance().setM_iInstantFuel(temp);
				bChange = true;
			}
			// 续航里程
			temp = GetByteInt(buffer[3], buffer[2], 0);
			if (SaicCarInfo.getInstance().getM_iDitanseMileage() != temp) {
				SaicCarInfo.getInstance().setM_iDitanseMileage(temp);
				bChange = true;
			}
			// 总里程
			temp = GetByteInt(buffer[6], buffer[5], buffer[4] & 0xff);
			if (SaicCarInfo.getInstance().getM_iMileage() != temp) {
				SaicCarInfo.getInstance().setM_iMileage(temp);
				bChange = true;
			}
			// 平均油耗1
			temp = GetByteInt(buffer[8], buffer[7], 0);
			if (SaicCarInfo.getInstance().getM_iAvgFuel1() != temp) {
				SaicCarInfo.getInstance().setM_iAvgFuel1(temp);
				bChange = true;
			}
			// 小计里程1
			temp = GetByteInt(buffer[11], buffer[10], buffer[9] & 0xff);
			if (SaicCarInfo.getInstance().getM_iLittleMileage1() != temp) {
				SaicCarInfo.getInstance().setM_iLittleMileage1(temp);
				bChange = true;
			}
			// 平均油耗2
			temp = GetByteInt(buffer[13], buffer[12], 0);
			if (SaicCarInfo.getInstance().getM_iAvgFuel2() != temp) {
				SaicCarInfo.getInstance().setM_iAvgFuel2(temp);
				bChange = true;
			}
			// 小计里程2
			temp = GetByteInt(buffer[16], buffer[15], buffer[14] & 0xff);
			if (SaicCarInfo.getInstance().getM_iLittleMileage2() != temp) {
				SaicCarInfo.getInstance().setM_iLittleMileage2(temp);
				bChange = true;
			}
			// 平均油耗3
			temp = GetByteInt(buffer[18], buffer[17], 0);
			if (SaicCarInfo.getInstance().getM_iAvgFuel3() != temp) {
				SaicCarInfo.getInstance().setM_iAvgFuel3(temp);
				bChange = true;
			}
			// 小计里程3
			temp = GetByteInt(buffer[21], buffer[20], buffer[19] & 0xff);
			if (SaicCarInfo.getInstance().getM_iLittleMileage3() != temp) {
				SaicCarInfo.getInstance().setM_iLittleMileage3(temp);
				bChange = true;
			}
			// 油耗单位
			temp = GetByteData(buffer[22], 0, 3);
			if (SaicCarInfo.getInstance().getM_iUnitFuel() != temp) {
				SaicCarInfo.getInstance().setM_iUnitFuel(temp);
				bChange = true;
			}
			// 里程单位
			temp = GetByteBit(buffer[22], 2);
			if (SaicCarInfo.getInstance().getM_iUnitMileage() != temp) {
				SaicCarInfo.getInstance().setM_iUnitMileage(temp);
				bChange = true;
			}

			if (bChange) {
				if (mFuelHandler != null) {
					mFuelHandler.obtainMessage(Constant.GM_MESSAGE_HANDLER, SaicCarInfo.getInstance()).sendToTarget();

				}
			}
		}
	}

	private void parseCmd0x32(byte[] buffer, int length) {

		if (buffer != null && length > 0) {
			boolean bChange = false;
			int temp = 0;
			float ff = 0;

			if (VMCarInfo.getInstance().getM_park_handStop() != GetByteBit(buffer[0], 0)) {
				bChange = true;
				VMCarInfo.getInstance().setM_park_handStop(GetByteBit(buffer[0], 0));
			}

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
			temp = buffer[9] & 0xff - 40;
			if (VMCarInfo.getInstance().getM_iCoolantTemp() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iCoolantTemp(temp);
			}

			temp = GetByteInt(buffer[11], buffer[10], 0);
			if (VMCarInfo.getInstance().getM_iFuelPa() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_iFuelPa(temp);
			}

			if (bChange) {
				if (mVmHandler != null) {
					mVmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
				}
			}
		}
	}

	private int GetByteInt(byte b, byte c, int d) {

		int temp = (b & 0xff) + (c & 0xff) * 256 + d * 256 * 256;
		return temp;
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

			i++;
			temp = buffer[i];
			if (VMCarInfo.getInstance().getM_currentOilInt() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_currentOilInt(temp);
			}
			i++;
			temp = buffer[i];
			if (VMCarInfo.getInstance().getM_currentOilFloat() != temp) {
				bChange = true;
				VMCarInfo.getInstance().setM_currentOilFloat(temp);
			}
			System.out.println("GM bChange  ==" + bChange);

			if (bChange) {
				if (mVmHandler != null) {
					mVmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
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
				if (mVmHandler != null) {
					mVmHandler.obtainMessage(Constant.VWF0_MESSAGE_SEND_INFO, VMCarInfo.getInstance()).sendToTarget();
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
	public void setFuleMileageHandler(Handler mHandler) {

		mFuelHandler = mHandler;
	}

}
