package ex.hiworld.obd;


import android.content.Intent;
import android.util.Log;
import ex.hiworld.MyApp;
import ex.hiworld.obd.entity.CarPcInfo;
import ex.hiworld.obd.entity.DataCanbus;
import tools.LogsUtils;

public class HandlerParseData {



	public static void onHandle(int[] ints) {
		int type = ints[0];
		int len = ints.length;
		switch (type) {
		case 0xD2: // door light
			if (len == 0x0B) { 
				LogsUtils.i("getIntsCmd: - >[" + LogsUtils.toHexString(ints));
				parseCmd0xD2(ints);
			}
			break;
		case 0xD3: // speed
			if (len == 0x0D) {
				LogsUtils.i("getIntsCmd: - >[" + LogsUtils.toHexString(ints));
				parseCmd0xD3(ints);
			}
			
			break; 
		case 0xD8: // fuel cost
			if (len == 0x17) {
				parseCmd0xD8(ints);
			}
			break;
		default:
			break;
		}
	}

	private static void parseCmd0xD2(int[] data) {
		int start = 1;
		boolean bChange = false;
		int temp = GetByteBit(data[start+0], 5);
		if (CarPcInfo.getInstance().getM_iHood() != temp) {
			CarPcInfo.getInstance().setM_iHood(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+0], 4);
		if (CarPcInfo.getInstance().getM_iTailBox() != temp) {
			CarPcInfo.getInstance().setM_iTailBox(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+0], 3);
		if (CarPcInfo.getInstance().getM_iRRDoor() != temp) {
			CarPcInfo.getInstance().setM_iRRDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+0], 2);
		if (CarPcInfo.getInstance().getM_iLRDoor() != temp) {
			CarPcInfo.getInstance().setM_iLRDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+0], 1);
		if (CarPcInfo.getInstance().getM_iRFDoor() != temp) {
			CarPcInfo.getInstance().setM_iRFDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+0], 0);
		if (CarPcInfo.getInstance().getM_iLFDoor() != temp) {
			CarPcInfo.getInstance().setM_iLFDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+1], 4);
		if (CarPcInfo.getInstance().getM_iDormer() != temp) {
			CarPcInfo.getInstance().setM_iDormer(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+1], 3);
		if (CarPcInfo.getInstance().getM_iRRWindow() != temp) {
			CarPcInfo.getInstance().setM_iRRWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+1], 2);
		if (CarPcInfo.getInstance().getM_iLRWindow() != temp) {
			CarPcInfo.getInstance().setM_iLRWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+1], 1);
		if (CarPcInfo.getInstance().getM_iRFWindow() != temp) {
			CarPcInfo.getInstance().setM_iRFWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+1], 0);
		if (CarPcInfo.getInstance().getM_iLFWindow() != temp) {
			CarPcInfo.getInstance().setM_iLFWindow(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[start+2], 5);
		if (CarPcInfo.getInstance().getM_iFootBrake() != temp) {
			CarPcInfo.getInstance().setM_iFootBrake(temp);
		}
		temp = GetByteBit(data[start+2], 4);
		if (CarPcInfo.getInstance().getM_iILL() != temp) {
			CarPcInfo.getInstance().setM_iILL(temp);
			bChange = true;
//			if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
//				 Intent intent = new Intent(FinalOBD.ACTION_ILL);
//				 intent.putExtra("ill",temp);
//				 MyApp.getInstance().sendBroadcast(intent);
//			}
		}
		temp = GetByteBit(data[start+2], 3);
		if (CarPcInfo.getInstance().getM_iACC() != temp) {
			CarPcInfo.getInstance().setM_iACC(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+2], 2);
		if (CarPcInfo.getInstance().getM_iFire() != temp) {
			CarPcInfo.getInstance().setM_iFire(temp);
		}
		temp = GetByteBit(data[start+2], 1);
		if (CarPcInfo.getInstance().getM_iHanderBrake() != temp) {
			CarPcInfo.getInstance().setM_iHanderBrake(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+2], 0);
		if (CarPcInfo.getInstance().getM_iLock() != temp) {
			CarPcInfo.getInstance().setM_iLock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 7);
		if (CarPcInfo.getInstance().getM_iDayLight() != temp) {
			CarPcInfo.getInstance().setM_iDayLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 6);
		if (CarPcInfo.getInstance().getM_iRevLight() != temp) {
			CarPcInfo.getInstance().setM_iRevLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 5);
		if (CarPcInfo.getInstance().getM_iStopLight() != temp) {
			CarPcInfo.getInstance().setM_iStopLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 4);
		if (CarPcInfo.getInstance().getM_iRearflogLight() != temp) {
			CarPcInfo.getInstance().setM_iRearflogLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 3);
		if (CarPcInfo.getInstance().getM_iFrontflogLight() != temp) {
			CarPcInfo.getInstance().setM_iFrontflogLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 2);
		if (CarPcInfo.getInstance().getM_iWideLight() != temp) {
			CarPcInfo.getInstance().setM_iWideLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 1);
		if (CarPcInfo.getInstance().getM_iFarLight() != temp) {
			CarPcInfo.getInstance().setM_iFarLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+3], 0);
		if (CarPcInfo.getInstance().getM_iNearLight() != temp) {
			CarPcInfo.getInstance().setM_iNearLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+4], 4);
		if (CarPcInfo.getInstance().getM_iDoubleLight() != temp) {
			CarPcInfo.getInstance().setM_iDoubleLight(temp);
			bChange = true;
		}
//		temp = GetByteBit(data[start+4], 3);
//		if (CarPcInfo.getInstance().getM_iLeftTrunFlogLight() != temp) {
//			CarPcInfo.getInstance().setM_iLeftTrunFlogLight(temp);
//		}
//		temp = GetByteBit(data[start+4], 2);
//		if (CarPcInfo.getInstance().getM_iRightTrunFlogLight() != temp) {
//			CarPcInfo.getInstance().setM_iRightTrunFlogLight(temp);
//		}
		temp = GetByteBit(data[start+4], 1);
		if (CarPcInfo.getInstance().getM_iLeftTrunLight() != temp) {
			CarPcInfo.getInstance().setM_iLeftTrunLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+4], 0);
		if (CarPcInfo.getInstance().getM_iRightTrunLight() != temp) {
			CarPcInfo.getInstance().setM_iRightTrunLight(temp);
			bChange = true;
		}
		temp = data[start+6];
		if (CarPcInfo.getInstance().getM_iLightData() != temp) {
			CarPcInfo.getInstance().setM_iLightData(temp);
			bChange = true;
		}
		temp = data[start+7];
		if (CarPcInfo.getInstance().getM_iGear() != temp) {
			CarPcInfo.getInstance().setM_iGear(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[start+8], 7);
		if (CarPcInfo.getInstance().getM_iLFSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iLFSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+8], 6);
		if (CarPcInfo.getInstance().getM_iRFSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRFSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+8], 5);
		if (CarPcInfo.getInstance().getM_iLRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iLRSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+8], 4);
		if (CarPcInfo.getInstance().getM_iRRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRRSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+8], 3);
		if (CarPcInfo.getInstance().getM_iRMRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRMRSafebelt(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[start+9], 7);
		if (CarPcInfo.getInstance().getM_iLFDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iLFDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+9], 6);
		if (CarPcInfo.getInstance().getM_iRFDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iRFDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+9], 5);
		if (CarPcInfo.getInstance().getM_iLRDoorLock() != temp) {
			CarPcInfo.getInstance().setM_iLRDoorLock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+9], 4);
		if (CarPcInfo.getInstance().getM_iRRDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iRRDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+9], 3);
		if (CarPcInfo.getInstance().getM_iRemotelock() != temp) {
			CarPcInfo.getInstance().setM_iRemotelock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[start+9], 2);
		if (CarPcInfo.getInstance().getM_iInnerlock() != temp) {
			CarPcInfo.getInstance().setM_iInnerlock(temp);
			bChange = true;
		}
		
		if (bChange) { 
			DataCanbus.NOTIFY_EVENTS[FinalOBD.U_DOOR_LIGHT].onNotify();
		}
	}

	private static void parseCmd0xD3(int[] data) {
		int start = 1;
		boolean bChange = false;
		float temp = (data[start+0] * 256 + data[start+1]) / 10;

		if (temp > 280) {
			temp = 0;
		}
		if (CarPcInfo.getInstance().getM_iACC() == 0) {
			temp = 0;
		}
		int frontSpeed = (int) CarPcInfo.getInstance().getM_fInstantSpeed();
		if (Math.abs(temp - frontSpeed) >= 240) {
			temp = frontSpeed;
			bChange = true;
		}
		if (CarPcInfo.getInstance().getM_fInstantSpeed() != temp) {
			CarPcInfo.getInstance().setM_fInstantSpeed(temp);
			bChange = true; 
		}
		int temp2 = data[start+2] * 256 + data[start+3];
		if (CarPcInfo.getInstance().getM_iEngineSpeed() != temp2) {
			CarPcInfo.getInstance().setM_iEngineSpeed(temp2); 
			if (temp2 != 0) {
				bChange = true;
			}
		}
		temp = (float) (data[start+4] * 0.1);
		if (CarPcInfo.getInstance().getM_fBatteryVol() != temp) {
			CarPcInfo.getInstance().setM_fBatteryVol(temp);
			bChange = true;
		}
		temp2 = GetByteData(data[start+5], 0, 3);
		if (CarPcInfo.getInstance().getM_iFuelUnit() != temp2) {
			CarPcInfo.getInstance().setM_iFuelUnit(temp2);
			bChange = true;
		}
		temp2 = data[start+6] - 40;
		if (CarPcInfo.getInstance().getM_iCooltemp() != temp2) {
			CarPcInfo.getInstance().setM_iCooltemp(temp2);
			bChange = true;
		}
		temp = (data[start+7] * 256 + data[start+8]) / 10;
		if (CarPcInfo.getInstance().getM_fInstantFuel() != temp) {
			CarPcInfo.getInstance().setM_fInstantFuel(temp);
			bChange = true;
		}
		// temp2 = data[start+9];
		// if (CarPcInfo.getInstance().getM_iBaroPressure() != temp2) {
		// CarPcInfo.getInstance().setM_iBaroPressure(temp2);
		// }
		// temp2 = data[start+10];
		// if (CarPcInfo.getInstance().getM_iDamper() != temp2) {
		// CarPcInfo.getInstance().setM_iDamper(temp2);
		// }

		if (bChange) {
			DataCanbus.NOTIFY_EVENTS[FinalOBD.U_SPEED].onNotify();
		}
	}
	private static void parseCmd0xD8(int[] data) {
		int start = 1;
		boolean bChange = false;
		int temp = data[start+0]*256*256+data[start+1]*256+data[start+2];
		if (CarPcInfo.getInstance().getM_iTotalMileage() != temp) {
			CarPcInfo.getInstance().setM_iTotalMileage(temp);
			bChange = true;
		}
		temp = data[start+3];
		if (CarPcInfo.getInstance().getM_iResidualOil() != temp) {
			CarPcInfo.getInstance().setM_iResidualOil(temp);
			bChange = true;
		}
		temp = data[start+4]*256+data[start+5];
		if (CarPcInfo.getInstance().getM_iMileage() != temp) {
			CarPcInfo.getInstance().setM_iMileage(temp);
			bChange = true;
		}
		temp = data[start+6]*256+data[start+7];
		if (CarPcInfo.getInstance().getM_iTripmeter_mileage() != temp) {
			CarPcInfo.getInstance().setM_iTripmeter_mileage(temp);
			bChange = true;
		}
		float temp2 = (data[start+8]*256+data[start+9])/10;
		if (CarPcInfo.getInstance().getM_fTripmeter_avgspeed() != (int)temp2) {
			CarPcInfo.getInstance().setM_fTripmeter_avgspeed((int)temp2);
			bChange = true;
		}
		temp2 = (data[start+10]*256+data[start+11])/10;
		if (CarPcInfo.getInstance().getM_fTripmeter_avgfuel() != temp2) {
			CarPcInfo.getInstance().setM_fTripmeter_avgfuel(temp2);
			bChange = true;
		}
		temp = data[start+12]*256+data[start+13];
		if (CarPcInfo.getInstance().getM_iTripmeter_drivertime() != temp) {
			CarPcInfo.getInstance().setM_iTripmeter_drivertime(temp);
			bChange = true;
		}
		temp = data[start+14]*256+data[start+15];
		if (CarPcInfo.getInstance().getM_iSelfstart_mileage() != temp) {
			CarPcInfo.getInstance().setM_iSelfstart_mileage(temp);
			bChange = true;
		}
		temp = (data[start+16]*256+data[start+17]);
		if (CarPcInfo.getInstance().getM_fSelfstart_avgspeed() != temp) {
			CarPcInfo.getInstance().setM_fSelfstart_avgspeed(temp);
			bChange = true;
		}
		temp2 = (data[start+18]*256+data[start+19])/10;
		if (CarPcInfo.getInstance().getM_fSelfstart_avgfuel() != temp2) {
			CarPcInfo.getInstance().setM_fSelfstart_avgfuel(temp2);
			bChange = true;
		}
		temp = data[start+20]*256+data[start+21];
		if (CarPcInfo.getInstance().getM_iSelfstart_drivertime() != temp) {
			CarPcInfo.getInstance().setM_iSelfstart_drivertime(temp);
			bChange = true;
		}
		
		if (bChange) {
			DataCanbus.NOTIFY_EVENTS[FinalOBD.U_FUEL_COST].onNotify();
		}
	}


	private static int GetByteBit(int data, int bit) {
		int temp = (int)(data>>bit & 0x01);
		return temp;
	}
	private static int GetByteData(int data, int bit, int num) {
		int temp = (int)(data>>bit & num);
		return temp;
	}

}
