package com.hiworld.canbus.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hiworld.canbus.interfaces.FragmentCallBack;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ChangeDataUtil;
import com.hiworld.canbus.util.ConstData;


public class FragmentParseData implements FragmentCallBack{
	private static final String TAG = "FragmentParseData";
	private Handler mHandlerCarState;
	private Handler mHandlerCarTrip;
	private Handler mHandlerCarInfo;
	private Handler mHandlerHud;
	private Handler mHandlerDoorlock;
	private Handler mHandlerLight;
	private Handler mHandlerWindow;
	private Handler mHandlerControl;
	private Handler mHandlerHtpms;
	private Handler mHandlerInstrument;
	private Handler mHandlerMirror;
	private Handler mHandlerMainFregment;
	private Handler mHandlerHomeFregment;
	private Handler mHandlerCarStateFregment;
	private Handler mHandlerTpmsFragment;
	
	//50%           30%       20%
	private int mZhuan2, mZhuan1, mZhuan, mNewZhuan;

	
	//品牌和车型
	private ArrayList<String> strArrayLine = new ArrayList<String>();
	private String[] strArrayBrand = null; 
	private String[] strArrayModel = null; 
	
	private Context context;
	private static final FragmentParseData INSTANCE = new FragmentParseData();
	
	public static FragmentParseData getInstance(){
		return INSTANCE;
	}
	
	public void SetContext(Context context){
		this.context = context;
		
	}
	
	//读取车的配置文件
	public void ReadFileOnLine(String fileName) throws IOException{
		InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(fileName) ); 
        BufferedReader bufReader = new BufferedReader(inputReader);
        String line="";
        while((line = bufReader.readLine()) != null){
        	strArrayLine.add(line);
        }
	}
	
	public int parseCmd(int[] ints, int size){
		if (ints != null && size > 0) {
			switch (ints[0]) {
			case ConstData.CMDID_0XD1:
				if (size == 0x15) {
					int[] data = new int[size-1];
					System.arraycopy(ints, 1, data, 0, size-1);
					parseCmd0xD1(data,data.length);
				}
				break;
			case ConstData.CMDID_0XD2:
				if (size == 0x0B) {
					int[] data = new int[size-1];
					System.arraycopy(ints, 1, data, 0, size-1);
					parseCmd0xD2(data,data.length);
				}
				break;
			case ConstData.CMDID_0XD3:
				if (size == 0x0D) {
					int[] data = new int[size-1];
					System.arraycopy(ints, 1, data, 0, size-1);
					parseCmd0xD3(data,data.length);
				}
				break;
			case ConstData.CMDID_0XD8:
				if (size == 0x17) {
					int[] data = new int[size-1];
					System.arraycopy(ints, 1, data, 0, size-1);
					parseCmd0xD8(data,data.length);
				}
				break;
			case ConstData.CMDID_0XD9:
				if (size == 0x0D) {
					int[] data = new int[size-1];
					System.arraycopy(ints, 1, data, 0, size-1);
					parseCmd0xD9(data,data.length);
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}
	
	private void parseCmd0xD9(int[] data, int length) {
		
		boolean bChange = false;
		int temp = GetByteBit(data[0], 3);
		if (CarPcInfo.getInstance().getM_iVINAble() != temp) {
			CarPcInfo.getInstance().setM_iVINAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 2);
		if (CarPcInfo.getInstance().getM_iMadeTimeAble() != temp) {
			CarPcInfo.getInstance().setM_iMadeTimeAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 1);
		if (CarPcInfo.getInstance().getM_iModelAble() != temp) {
			CarPcInfo.getInstance().setM_iModelAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 0);
		if (CarPcInfo.getInstance().getM_iBrandAble() != temp) {
			CarPcInfo.getInstance().setM_iBrandAble(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[1], 7);
		if (CarPcInfo.getInstance().getM_iGearAble() != temp) {
			CarPcInfo.getInstance().setM_iGearAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 6);
		if (CarPcInfo.getInstance().getM_iLightBlueAble() != temp) {
			CarPcInfo.getInstance().setM_iLightBlueAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 5);
		if (CarPcInfo.getInstance().getM_iInnerLightAble() != temp) {
			CarPcInfo.getInstance().setM_iInnerLightAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 4);
		if (CarPcInfo.getInstance().getM_iOutLightAble() != temp) {
			CarPcInfo.getInstance().setM_iOutLightAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 3);
		if (CarPcInfo.getInstance().getM_iHandbrakeAble() != temp) {
			CarPcInfo.getInstance().setM_iHandbrakeAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 2);
		if (CarPcInfo.getInstance().getM_iBaseStateAble() != temp) {
			CarPcInfo.getInstance().setM_iBaseStateAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 1);
		if (CarPcInfo.getInstance().getM_iWindowAble() != temp) {
			CarPcInfo.getInstance().setM_iWindowAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 0);
		if (CarPcInfo.getInstance().getM_iDoorAble() != temp) {
			CarPcInfo.getInstance().setM_iDoorAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 7);
		if (CarPcInfo.getInstance().getM_iLRTrunAble() != temp) {
			CarPcInfo.getInstance().setM_iLRTrunAble(temp);
			bChange = true;
		}
//		temp = GetByteBit(data[2], 6);
//		if (CarPcInfo.getInstance().getM_iDamperAble() != temp) {
//			CarPcInfo.getInstance().setM_iDamperAble(temp);
//			bChange = true;
//		}
//		temp = GetByteBit(data[2], 5);
//		if (CarPcInfo.getInstance().getM_iBaroPressureAble() != temp) {
//			CarPcInfo.getInstance().setM_iBaroPressureAble(temp);
//			bChange = true;
//		}
		temp = GetByteBit(data[2], 4);
		if (CarPcInfo.getInstance().getM_iInstantFuelAble() != temp) {
			CarPcInfo.getInstance().setM_iInstantFuelAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 3);
		if (CarPcInfo.getInstance().getM_iCooltempAble() != temp) {
			CarPcInfo.getInstance().setM_iCooltempAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 2);
		if (CarPcInfo.getInstance().getM_iBatteryVolAble() != temp) {
			CarPcInfo.getInstance().setM_iBatteryVolAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 1);
		if (CarPcInfo.getInstance().getM_iEnigineSpeedAble() != temp) {
			CarPcInfo.getInstance().setM_iEnigineSpeedAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 0);
		if (CarPcInfo.getInstance().getM_iSpeedAble() != temp) {
			CarPcInfo.getInstance().setM_iSpeedAble(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[3], 1);
		if (CarPcInfo.getInstance().getM_iReverseGearAble() != temp) {
			CarPcInfo.getInstance().setM_iReverseGearAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 0);
		if (CarPcInfo.getInstance().getM_iSteerAngleAble() != temp) {
			CarPcInfo.getInstance().setM_iSteerAngleAble(temp);
			bChange = true;
		}
		
		Log.i(TAG, "temp ==="+temp);
		
//		temp = GetByteBit(data[5], 0);
//		if (CarPcInfo.getInstance().getM_iFaultWarnAble() != temp) {
//			CarPcInfo.getInstance().setM_iFaultWarnAble(temp);
//			bChange = true;
//		}
//		
//		temp = GetByteBit(data[6], 2);
//		if (CarPcInfo.getInstance().getM_iFrontRadarAble() != temp) {
//			CarPcInfo.getInstance().setM_iFrontRadarAble(temp);
//			bChange = true;
//		}
//		temp = GetByteBit(data[6], 1);
//		if (CarPcInfo.getInstance().getM_iRearRadarAble() != temp) {
//			CarPcInfo.getInstance().setM_iRearRadarAble(temp);
//			bChange = true;
//		}
//		temp = GetByteBit(data[6], 0);
//		if (CarPcInfo.getInstance().getM_iHaveRadarAble() != temp) {
//			CarPcInfo.getInstance().setM_iHaveRadarAble(temp);
//			bChange = true;
//		}
		
		temp = GetByteBit(data[7], 4);
		if (CarPcInfo.getInstance().getM_iSelfStartMileageAble() != temp) {
			CarPcInfo.getInstance().setM_iSelfStartMileageAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[7], 3);
		if (CarPcInfo.getInstance().getM_iLittleTripAble() != temp) {
			CarPcInfo.getInstance().setM_iLittleTripAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[7], 2);
		if (CarPcInfo.getInstance().getM_iMileageAble() != temp) {
			CarPcInfo.getInstance().setM_iMileageAble(temp);
			bChange = true;
		}
//		temp = GetByteBit(data[7], 1);
//		if (CarPcInfo.getInstance().getM_iResidualOilAble() != temp) {
//			CarPcInfo.getInstance().setM_iResidualOilAble(temp);
//			bChange = true;
//		}
		temp = GetByteBit(data[7], 0);
		if (CarPcInfo.getInstance().getM_iTotalMileageAble() != temp) {
			CarPcInfo.getInstance().setM_iTotalMileageAble(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[8], 7);
		if (CarPcInfo.getInstance().getM_iLFGatelockAble() != temp) {
			CarPcInfo.getInstance().setM_iLFGatelockAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 6);
		if (CarPcInfo.getInstance().getM_iRFGatelockAble() != temp) {
			CarPcInfo.getInstance().setM_iRFGatelockAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 5);
		if (CarPcInfo.getInstance().getM_iLRGatelockAble() != temp) {
			CarPcInfo.getInstance().setM_iLRGatelockAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 4);
		if (CarPcInfo.getInstance().getM_iRRGatelockAble() != temp) {
			CarPcInfo.getInstance().setM_iRRGatelockAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 3);
		if (CarPcInfo.getInstance().getM_iRemoteoutlockAble() != temp) {
			CarPcInfo.getInstance().setM_iRemoteoutlockAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 2);
		if (CarPcInfo.getInstance().getM_iInnerlockAble() != temp) {
			CarPcInfo.getInstance().setM_iInnerlockAble(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[9], 7);
		if (CarPcInfo.getInstance().getM_iLFSafebeltAble() != temp) {
			CarPcInfo.getInstance().setM_iLFSafebeltAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 6);
		if (CarPcInfo.getInstance().getM_iRFSafebeltAble() != temp) {
			CarPcInfo.getInstance().setM_iRFSafebeltAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 5);
		if (CarPcInfo.getInstance().getM_iLRSafebeltAble() != temp) {
			CarPcInfo.getInstance().setM_iLRSafebeltAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 4);
		if (CarPcInfo.getInstance().getM_iRRSafebeltAble() != temp) {
			CarPcInfo.getInstance().setM_iRRSafebeltAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 3);
		if (CarPcInfo.getInstance().getM_iRMRSafebeltAble() != temp) {
			CarPcInfo.getInstance().setM_iRMRSafebeltAble(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[10], 5);
		if (CarPcInfo.getInstance().getM_iFootbrakeAble() != temp) {
			CarPcInfo.getInstance().setM_iFootbrakeAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[10], 4);
		if (CarPcInfo.getInstance().getM_iILLAble() != temp) {
			CarPcInfo.getInstance().setM_iILLAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[10], 3);
		if (CarPcInfo.getInstance().getM_iACCAble() != temp) {
			CarPcInfo.getInstance().setM_iACCAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[10], 2);
		if (CarPcInfo.getInstance().getM_iFireAble() != temp) {
			CarPcInfo.getInstance().setM_iFireAble(temp);
			bChange = true;
		}
		temp = GetByteBit(data[10], 0);
		if (CarPcInfo.getInstance().getM_iLockAble() != temp) {
			CarPcInfo.getInstance().setM_iLockAble(temp);
			bChange = true;
		}
		
		if (bChange) {
			SendHandlerMessage(7);
		}
	}

	private void parseCmd0xD8(int[] data, int length) {
		
		boolean bChange = false;
		int temp = data[0]*256*256+data[1]*256+data[2];
		if (CarPcInfo.getInstance().getM_iTotalMileage() != temp) {
			CarPcInfo.getInstance().setM_iTotalMileage(temp);
			bChange = true;
		}
		temp = data[3];
		if (CarPcInfo.getInstance().getM_iResidualOil() != temp) {
			CarPcInfo.getInstance().setM_iResidualOil(temp);
			bChange = true;
		}
		temp = data[4]*256+data[5];
		if (CarPcInfo.getInstance().getM_iMileage() != temp) {
			CarPcInfo.getInstance().setM_iMileage(temp);
			bChange = true;
		}
		temp = data[6]*256+data[7];
		if (CarPcInfo.getInstance().getM_iTripmeter_mileage() != temp) {
			CarPcInfo.getInstance().setM_iTripmeter_mileage(temp);
			bChange = true;
		}
		float temp2 = (data[8]*256+data[9])/10;
		if (CarPcInfo.getInstance().getM_fTripmeter_avgspeed() != (int)temp2) {
			CarPcInfo.getInstance().setM_fTripmeter_avgspeed((int)temp2);
			bChange = true;
		}
		temp2 = (data[10]*256+data[11])/10;
		if (CarPcInfo.getInstance().getM_fTripmeter_avgfuel() != temp2) {
			CarPcInfo.getInstance().setM_fTripmeter_avgfuel(temp2);
			bChange = true;
		}
		temp = data[12]*256+data[13];
		if (CarPcInfo.getInstance().getM_iTripmeter_drivertime() != temp) {
			CarPcInfo.getInstance().setM_iTripmeter_drivertime(temp);
			bChange = true;
		}
		temp = data[14]*256+data[15];
		if (CarPcInfo.getInstance().getM_iSelfstart_mileage() != temp) {
			CarPcInfo.getInstance().setM_iSelfstart_mileage(temp);
			bChange = true;
		}
		temp = (data[16]*256+data[17]);
		if (CarPcInfo.getInstance().getM_fSelfstart_avgspeed() != temp) {
			CarPcInfo.getInstance().setM_fSelfstart_avgspeed(temp);
			bChange = true;
		}
		temp2 = (data[18]*256+data[19])/10;
		if (CarPcInfo.getInstance().getM_fSelfstart_avgfuel() != temp2) {
			CarPcInfo.getInstance().setM_fSelfstart_avgfuel(temp2);
			bChange = true;
		}
		temp = data[20]*256+data[21];
		if (CarPcInfo.getInstance().getM_iSelfstart_drivertime() != temp) {
			CarPcInfo.getInstance().setM_iSelfstart_drivertime(temp);
			bChange = true;
		}
		
		if (bChange) {
			SendHandlerMessage(7);
		}
	}

	private void parseCmd0xD3(int[] data, int length) {
		
		boolean bChange = false;
		float temp = (data[0]*256+data[1])/10;
		
		if (temp > 280) {
			temp = 0;
		}
		if (CarPcInfo.getInstance().getM_iACC() == 0) {
			temp = 0;
		}
		int frontSpeed = (int) CarPcInfo.getInstance().getM_fInstantSpeed();
		if (Math.abs(temp-frontSpeed) >= 240) {
			temp = frontSpeed;
			bChange = true;
		}
		if (CarPcInfo.getInstance().getM_fInstantSpeed() != temp) {
			CarPcInfo.getInstance().setM_fInstantSpeed(temp);
			bChange = true;
		}
	    int temp2 = data[2]*256+data[3];
		if (CarPcInfo.getInstance().getM_iEngineSpeed() != temp2) {
			CarPcInfo.getInstance().setM_iEngineSpeed(temp2);
			if (temp2 == 0) {
				bChange = true;
			}
		}
		temp = (float) (data[4]*0.1);
		Log.i(TAG, "电池电压 v=== data[4]="+data[4]+", temp="+temp);
		if (CarPcInfo.getInstance().getM_fBatteryVol() != temp) {
			CarPcInfo.getInstance().setM_fBatteryVol(temp);
			bChange = true;
		}
		temp2 = GetByteData(data[5], 0, 3);
		if (CarPcInfo.getInstance().getM_iFuelUnit() != temp2) {
			CarPcInfo.getInstance().setM_iFuelUnit(temp2);
			bChange = true;
		}
		temp2 = data[6]-40;
		if (CarPcInfo.getInstance().getM_iCooltemp() != temp2) {
			CarPcInfo.getInstance().setM_iCooltemp(temp2);
			bChange = true;
		}
		temp = (data[7]*256+data[8])/10;
		if (CarPcInfo.getInstance().getM_fInstantFuel() != temp) {
			CarPcInfo.getInstance().setM_fInstantFuel(temp);
			bChange = true;
		}
//		temp2 = data[9];
//		if (CarPcInfo.getInstance().getM_iBaroPressure() != temp2) {
//			CarPcInfo.getInstance().setM_iBaroPressure(temp2);
//		}
//		temp2 = data[10];
//		if (CarPcInfo.getInstance().getM_iDamper() != temp2) {
//			CarPcInfo.getInstance().setM_iDamper(temp2);
//		}
		
		if (bChange) {
			SendHandlerMessage(1);
		}
	}

	private void parseCmd0xD2(int[] data, int length) {
		
		boolean bChange = false;
		int temp = GetByteBit(data[0], 5);
		if (CarPcInfo.getInstance().getM_iHood() != temp) {
			CarPcInfo.getInstance().setM_iHood(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 4);
		if (CarPcInfo.getInstance().getM_iTailBox() != temp) {
			CarPcInfo.getInstance().setM_iTailBox(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 3);
		if (CarPcInfo.getInstance().getM_iRRDoor() != temp) {
			CarPcInfo.getInstance().setM_iRRDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 2);
		if (CarPcInfo.getInstance().getM_iLRDoor() != temp) {
			CarPcInfo.getInstance().setM_iLRDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 1);
		if (CarPcInfo.getInstance().getM_iRFDoor() != temp) {
			CarPcInfo.getInstance().setM_iRFDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[0], 0);
		if (CarPcInfo.getInstance().getM_iLFDoor() != temp) {
			CarPcInfo.getInstance().setM_iLFDoor(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 4);
		if (CarPcInfo.getInstance().getM_iDormer() != temp) {
			CarPcInfo.getInstance().setM_iDormer(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 3);
		if (CarPcInfo.getInstance().getM_iRRWindow() != temp) {
			CarPcInfo.getInstance().setM_iRRWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 2);
		if (CarPcInfo.getInstance().getM_iLRWindow() != temp) {
			CarPcInfo.getInstance().setM_iLRWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 1);
		if (CarPcInfo.getInstance().getM_iRFWindow() != temp) {
			CarPcInfo.getInstance().setM_iRFWindow(temp);
			bChange = true;
		}
		temp = GetByteBit(data[1], 0);
		if (CarPcInfo.getInstance().getM_iLFWindow() != temp) {
			CarPcInfo.getInstance().setM_iLFWindow(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[2], 5);
		if (CarPcInfo.getInstance().getM_iFootBrake() != temp) {
			CarPcInfo.getInstance().setM_iFootBrake(temp);
		}
		temp = GetByteBit(data[2], 4);
		if (CarPcInfo.getInstance().getM_iILL() != temp) {
			CarPcInfo.getInstance().setM_iILL(temp);
			bChange = true;
			if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
				 Intent intent = new Intent(ConstData.ACTION_ILL);
				 intent.putExtra("ill",temp);
				 context.sendBroadcast(intent);
			}
		}
		temp = GetByteBit(data[2], 3);
		if (CarPcInfo.getInstance().getM_iACC() != temp) {
			CarPcInfo.getInstance().setM_iACC(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 2);
		if (CarPcInfo.getInstance().getM_iFire() != temp) {
			CarPcInfo.getInstance().setM_iFire(temp);
		}
		temp = GetByteBit(data[2], 1);
		if (CarPcInfo.getInstance().getM_iHanderBrake() != temp) {
			CarPcInfo.getInstance().setM_iHanderBrake(temp);
			bChange = true;
		}
		temp = GetByteBit(data[2], 0);
		if (CarPcInfo.getInstance().getM_iLock() != temp) {
			CarPcInfo.getInstance().setM_iLock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 7);
		if (CarPcInfo.getInstance().getM_iDayLight() != temp) {
			CarPcInfo.getInstance().setM_iDayLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 6);
		if (CarPcInfo.getInstance().getM_iRevLight() != temp) {
			CarPcInfo.getInstance().setM_iRevLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 5);
		if (CarPcInfo.getInstance().getM_iStopLight() != temp) {
			CarPcInfo.getInstance().setM_iStopLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 4);
		if (CarPcInfo.getInstance().getM_iRearflogLight() != temp) {
			CarPcInfo.getInstance().setM_iRearflogLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 3);
		if (CarPcInfo.getInstance().getM_iFrontflogLight() != temp) {
			CarPcInfo.getInstance().setM_iFrontflogLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 2);
		if (CarPcInfo.getInstance().getM_iWideLight() != temp) {
			CarPcInfo.getInstance().setM_iWideLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 1);
		if (CarPcInfo.getInstance().getM_iFarLight() != temp) {
			CarPcInfo.getInstance().setM_iFarLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[3], 0);
		if (CarPcInfo.getInstance().getM_iNearLight() != temp) {
			CarPcInfo.getInstance().setM_iNearLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[4], 4);
		if (CarPcInfo.getInstance().getM_iDoubleLight() != temp) {
			CarPcInfo.getInstance().setM_iDoubleLight(temp);
			bChange = true;
		}
//		temp = GetByteBit(data[4], 3);
//		if (CarPcInfo.getInstance().getM_iLeftTrunFlogLight() != temp) {
//			CarPcInfo.getInstance().setM_iLeftTrunFlogLight(temp);
//		}
//		temp = GetByteBit(data[4], 2);
//		if (CarPcInfo.getInstance().getM_iRightTrunFlogLight() != temp) {
//			CarPcInfo.getInstance().setM_iRightTrunFlogLight(temp);
//		}
		temp = GetByteBit(data[4], 1);
		if (CarPcInfo.getInstance().getM_iLeftTrunLight() != temp) {
			CarPcInfo.getInstance().setM_iLeftTrunLight(temp);
			bChange = true;
		}
		temp = GetByteBit(data[4], 0);
		if (CarPcInfo.getInstance().getM_iRightTrunLight() != temp) {
			CarPcInfo.getInstance().setM_iRightTrunLight(temp);
			bChange = true;
		}
		temp = data[6];
		if (CarPcInfo.getInstance().getM_iLightData() != temp) {
			CarPcInfo.getInstance().setM_iLightData(temp);
			bChange = true;
		}
		temp = data[7];
		if (CarPcInfo.getInstance().getM_iGear() != temp) {
			CarPcInfo.getInstance().setM_iGear(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[8], 7);
		if (CarPcInfo.getInstance().getM_iLFSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iLFSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 6);
		if (CarPcInfo.getInstance().getM_iRFSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRFSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 5);
		if (CarPcInfo.getInstance().getM_iLRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iLRSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 4);
		if (CarPcInfo.getInstance().getM_iRRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRRSafebelt(temp);
			bChange = true;
		}
		temp = GetByteBit(data[8], 3);
		if (CarPcInfo.getInstance().getM_iRMRSafebelt() != temp) {
			CarPcInfo.getInstance().setM_iRMRSafebelt(temp);
			bChange = true;
		}
		
		temp = GetByteBit(data[9], 7);
		if (CarPcInfo.getInstance().getM_iLFDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iLFDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 6);
		if (CarPcInfo.getInstance().getM_iRFDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iRFDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 5);
		if (CarPcInfo.getInstance().getM_iLRDoorLock() != temp) {
			CarPcInfo.getInstance().setM_iLRDoorLock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 4);
		if (CarPcInfo.getInstance().getM_iRRDoorlock() != temp) {
			CarPcInfo.getInstance().setM_iRRDoorlock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 3);
		if (CarPcInfo.getInstance().getM_iRemotelock() != temp) {
			CarPcInfo.getInstance().setM_iRemotelock(temp);
			bChange = true;
		}
		temp = GetByteBit(data[9], 2);
		if (CarPcInfo.getInstance().getM_iInnerlock() != temp) {
			CarPcInfo.getInstance().setM_iInnerlock(temp);
			bChange = true;
		}
		
		if (bChange) {
			SendHandlerMessage(1);
		}
	
	}

	private void parseCmd0xD1(int[] data, int length) {
		
		//品牌、车型、制造年月
		SetBaseNum(data[0], data[1], data[2]);

		String s1 = new String(ChangeDataUtil.ints2Bytes(data, 3));

		CarPcInfo.getInstance().setM_strVIN(s1);
		
		SendHandlerMessage(3);
	}

	public int parseBuffer(byte[] buffer, int size){
		if (buffer != null && size > 0) {
			if (mHandlerHtpms != null) {
				mHandlerHtpms.sendEmptyMessage(ConstData.MESSAGE_TPMS_CONNECT);
			}
			if (mHandlerTpmsFragment != null) {
				mHandlerTpmsFragment.sendEmptyMessage(ConstData.MESSAGE_TPMS_CONNECT);
			}
			switch (buffer[0]&0xff) {
			case ConstData.CMDID_0XE0:
				if (size == 0x0b) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xE0(data,data.length);
				}
				break;
			case ConstData.CMDID_0XF3:
				if (size == 18){
					byte[] code = new byte[17];
					System.arraycopy(buffer, 1, code, 0, 17);
					if (code != null){
						String s1 = new String(code);
						CarInfo.getInstance().setVer(s1);
					}
				}
				break;
			case ConstData.CMDID_0XFF:
				if (size == 7){
					if (buffer[1] == (byte)0x3a && buffer[2] == (byte)0xa3 && buffer[3] == (byte)0x7f && buffer[4] == (byte)0xf7) {
						CarInfo.getInstance().setAccState((byte)1);
						CarInfo.getInstance().setAgreeTpms((byte)1);
						CarInfo.getInstance().setTpmsLink1((byte)1);
						CarInfo.getInstance().setTpmsLink2((byte)1);
						CarInfo.getInstance().setbLinkTx((byte)0);
						CarInfo.getInstance().setbCanUnlink((byte)0);

						if (CarInfo.getInstance().getWarnLevel() != (byte)(buffer[5]>>7 & 0x01)) {
							CarInfo.getInstance().setWarnLevel((byte)(buffer[5]>>7 & 0x01));
						}
						if (CarInfo.getInstance().getRightRear() != (byte)(buffer[5]>>3 & 0x01)) {
							CarInfo.getInstance().setRightRear((byte)(buffer[5]>>3 & 0x01));
						}
						if (CarInfo.getInstance().getLeftRear() != (byte)(buffer[5]>>2 & 0x01)) {
							CarInfo.getInstance().setLeftRear((byte)(buffer[5]>>2 & 0x01));
						}
						if (CarInfo.getInstance().getRightFront()  != (byte)(buffer[5]>>1 & 0x01)) {
							CarInfo.getInstance().setRightFront((byte)(buffer[5]>>1 & 0x01));
						}
						if (CarInfo.getInstance().getLeftFront()  != (byte)(buffer[5] & 0x01)) {
							CarInfo.getInstance().setLeftFront((byte)(buffer[5] & 0x01));
						}
						
						if (mHandlerHtpms != null) {
							mHandlerHtpms.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
						if (mHandlerTpmsFragment != null) {
							mHandlerTpmsFragment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
						if (mHandlerInstrument != null) {
							mHandlerInstrument.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
						if (mHandlerMainFregment != null) {
							mHandlerMainFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
						if (mHandlerHomeFregment != null) {
							mHandlerHomeFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
						if (mHandlerCarStateFregment != null) {
							mHandlerCarStateFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
						}
					}
				}
				break;
			case ConstData.CMDID_0X48:
				if (size == 9) {
					parseHtpms0x48(buffer, buffer.length);
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0x48(data,data.length);
				}
				break;
			case ConstData.CMDID_0X49:
				if (size == 9){
					CarInfo.getInstance().setiLFWheelSp((buffer[2]&0xff)*256+(buffer[1]&0xff)); //左前
					CarInfo.getInstance().setiRFWheelSp((buffer[4]&0xff)*256+(buffer[3]&0xff));//右前
					CarInfo.getInstance().setiLRWheelSp((buffer[6]&0xff)*256+(buffer[5]&0xff)) ;//左后
					CarInfo.getInstance().setiRRWheelSp((buffer[8]&0xff)*256+(buffer[7]&0xff)) ;//右后
					
					if (mHandlerHtpms != null) {
						mHandlerHtpms.obtainMessage(ConstData.MESSAGE_TPMS_SWHEEL, CarInfo.getInstance()).sendToTarget();
					}
					if (mHandlerTpmsFragment != null) {
						mHandlerTpmsFragment.obtainMessage(ConstData.MESSAGE_TPMS_SWHEEL, CarInfo.getInstance()).sendToTarget();
					}
				}
				break;
			case ConstData.CMDID_0X50:
				if (size == 9){
					if (CarInfo.getInstance().getAccState() != (byte)(buffer[1]>>7 & 0x01)) {
						CarInfo.getInstance().setAccState((byte)(buffer[1]>>7 & 0x01));
					}
					if(CarInfo.getInstance().getAccState() ==1){
						boolean m_breset = false;
						//车型支持
						if (CarInfo.getInstance().getAgreeTpms() != (byte)(buffer[1]>>4 & 0x01)) {
							CarInfo.getInstance().setAgreeTpms((byte)(buffer[1]>>4 & 0x01));
							m_breset = true;
						}
						//1无异常
						if (CarInfo.getInstance().getTpmsLink1() != (byte)(buffer[1] & 0x01)) {
							CarInfo.getInstance().setTpmsLink1((byte)(buffer[1] & 0x01));
							m_breset = true;
						}
						//1无异常
						if (CarInfo.getInstance().getTpmsLink2() != (byte)(buffer[1]>>5 & 0x01)) {
							CarInfo.getInstance().setTpmsLink2((byte)(buffer[1]>>5 & 0x01));
							m_breset = true;
						}
						//0无异常
						if (CarInfo.getInstance().getbLinkTx() != (byte)(buffer[1]>>1 & 0x01)) {
							CarInfo.getInstance().setbLinkTx((byte)(buffer[1]>>1 & 0x01));
							m_breset = true;
						}
						//0无异常
						if (CarInfo.getInstance().getbCanUnlink()  != (byte)(buffer[1]>>6 & 0x01)) {
							CarInfo.getInstance().setbCanUnlink((byte)(buffer[1]>>6 & 0x01));
							m_breset = true;
						}
						
						if (m_breset ) {
							if (mHandlerHtpms != null) {
								mHandlerHtpms.obtainMessage(ConstData.MESSAGE_TPMS_SERIAL_HIGHSPEED, CarInfo.getInstance()).sendToTarget();
							}
							if (mHandlerTpmsFragment != null) {
								mHandlerTpmsFragment.obtainMessage(ConstData.MESSAGE_TPMS_SERIAL_HIGHSPEED, CarInfo.getInstance()).sendToTarget();
							}
						}
					}
				}
				break;
			case ConstData.CMDID_0XC1://车辆基本信息
				if (size == 21) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xC1(data,data.length);
					
				}
				break;
			case ConstData.CMDID_0XC2://车辆状态使能
				if (size == 11) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xC2(data,data.length);
				}
				break;
			case ConstData.CMDID_0XC3://车辆行驶信息
				if (size == 13) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xC3(data,data.length);
				}
				break;
			case ConstData.CMDID_0XC4://车辆倒车信息
				if (size == 4) {
					
				}
				break;
			case ConstData.CMDID_0XC7://雷达信息
				if (size == 10) {
					
				}
				break;
			case ConstData.CMDID_0XC8://里程信息
				if (size == 23) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xC8(data,data.length);
				}
				break;
			case ConstData.CMDID_0XC9://功能使能
				if (size == 13) {
					byte[] data = new byte[size-1];
					System.arraycopy(buffer, 1, data, 0, size-1);
					parseCmd0xC9(data,data.length);
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}
	
	private void parseHtpms0x48(byte[] buffer, int length) {
		
		//data0
		if (CarInfo.getInstance().getState() != (byte)buffer[1]) {
			CarInfo.getInstance().setState((byte)buffer[1]);
		}
		//data1
		if (CarInfo.getInstance().getWarnLevel() != (byte)(buffer[2]>>7 & 0x01)) {
			CarInfo.getInstance().setWarnLevel((byte)(buffer[2]>>7 & 0x01));
		}
		if (CarInfo.getInstance().getRightRear() != (byte)(buffer[2]>>3 & 0x01)) {
			CarInfo.getInstance().setRightRear((byte)(buffer[2]>>3 & 0x01));
		}
		if (CarInfo.getInstance().getLeftRear() != (byte)(buffer[2]>>2 & 0x01)) {
			CarInfo.getInstance().setLeftRear((byte)(buffer[2]>>2 & 0x01));
		}
		if (CarInfo.getInstance().getRightFront()  != (byte)(buffer[2]>>1 & 0x01)) {
			CarInfo.getInstance().setRightFront((byte)(buffer[2]>>1 & 0x01));
		}
		if (CarInfo.getInstance().getLeftFront()  != (byte)(buffer[2] & 0x01)) {
			CarInfo.getInstance().setLeftFront((byte)(buffer[2] & 0x01));
		}
		
		//data2
		if (CarInfo.getInstance().getWarnSwitch() != (byte)(buffer[3] & 0x01)) {
			CarInfo.getInstance().setWarnSwitch((byte)(buffer[3] & 0x01));
		}
		//data3
		if (CarInfo.getInstance().getSenis() != buffer[4]) {
			CarInfo.getInstance().setSenis(buffer[4]);
		}
		
		if (mHandlerHtpms != null) {
			mHandlerHtpms.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		if (mHandlerTpmsFragment != null) {
			mHandlerTpmsFragment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		if (mHandlerInstrument != null) {
			mHandlerInstrument.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		if (mHandlerMainFregment != null) {
			mHandlerMainFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		if (mHandlerHomeFregment != null) {
			mHandlerHomeFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		if (mHandlerCarStateFregment != null) {
			mHandlerCarStateFregment.obtainMessage(ConstData.MESSAGE_TIRE_FAILURE, CarInfo.getInstance()).sendToTarget();
		}
		
	}

	private void parseCmd0xE0(byte[] data, int length) {
		

//		boolean bChange = false;
//		if (CarPcInfo.getInstance().getiLockAutoWindow() != GetByteBit(data[5], 7)) {
//			CarPcInfo.getInstance().setiLockAutoWindow(GetByteBit(data[5], 7));
//			bChange = true;
//		}
//		
//		if (CarPcInfo.getInstance().getiDoorAutoDouble() != GetByteBit(data[5], 6)) {
//			CarPcInfo.getInstance().setiDoorAutoDouble(GetByteBit(data[5], 6));
//			bChange = true;
//		}
//		
//		if (bChange) {
//			if (mHandlerDoorlock != null) {
//				mHandlerDoorlock.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
//			}
//		}
	}

	private void parseCmd0x48(byte[] buffer, int length) {
		
		if (buffer != null && length > 0){
			int lf = GetByteData(buffer[1], 0, 0x01);
			int rf = GetByteData(buffer[1], 1, 0x01);
			int lr = GetByteData(buffer[1], 2, 0x01);
			int rr = GetByteData(buffer[1], 3, 0x01);
			int warn = 0;
			if (lf == 1) {
				warn=1;
			}else if (rf == 1) {
				warn=1;
			}else if (lr == 1) {
				warn=1;
			}else if (rr == 1) {
				warn=1;
			}
			
			if (CarPcInfo.getInstance().getM_iHtpmsWarn() != warn) {
				CarPcInfo.getInstance().setM_iHtpmsWarn(warn);
				
				if (mHandlerHud != null) {
					mHandlerHud.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
				}
			}
			
		}
	}

	private void parseCmd0xC8(byte[] buffer, int length) {
		
		if (buffer != null && length > 0){
			boolean bChange = false;
			//小计里程-里程
			int temp = GetIntData(buffer[0],buffer[1],(byte) 0);
			if (CarPcInfo.getInstance().getM_iTripmeter_mileage() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iTripmeter_mileage(temp);
			}
			//小计里程-平均车速
			temp = GetIntData(buffer[2],buffer[3],(byte) 0);
			int ff = (int) (temp*0.1);
			if (CarPcInfo.getInstance().getM_fTripmeter_avgspeed() != ff) {
				bChange = true;
				CarPcInfo.getInstance().setM_fTripmeter_avgspeed(ff);
			}
			
			//小计里程-行驶时长
			temp = GetIntData(buffer[4],buffer[5],(byte) 0);
			if (CarPcInfo.getInstance().getM_iTripmeter_drivertime() != temp) {
//				//疲劳驾驶
//				if ((CarPcInfo.getInstance().getM_iTripmeter_drivertime() < 240)
//						&& (temp > 240)) {
//					sendWarnTire(CarPcInfo.getInstance());
//				} 

				bChange = true;
				CarPcInfo.getInstance().setM_iTripmeter_drivertime(temp);
				
				

			}
			//小计里程-平均油耗
			temp = GetIntData(buffer[6],buffer[7],(byte) 0);
			ff = (int) (temp*0.1);
			if (temp == 0xffff) {//无效
				if (CarPcInfo.getInstance().getM_fTripmeter_avgfuel() != temp) {
					bChange = true;
					CarPcInfo.getInstance().setM_fTripmeter_avgfuel(temp);
				}
			} else {
				if (CarPcInfo.getInstance().getM_fTripmeter_avgfuel() != ff) {
					bChange = true;
					CarPcInfo.getInstance().setM_fTripmeter_avgfuel(ff);
				}
			}
			//续航里程
			temp = GetIntData(buffer[8],buffer[9],(byte) 0);
			if (CarPcInfo.getInstance().getM_iMileage() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iMileage(temp);
			}
			//总里程
			temp = GetIntData(buffer[10],buffer[11],buffer[12]);
			if (CarPcInfo.getInstance().getM_iTotalMileage() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iTotalMileage(temp);
			}
			//自启动后里程-里程
			temp = GetIntData(buffer[13],buffer[14],(byte) 0);
			if (CarPcInfo.getInstance().getM_iSelfstart_mileage() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iSelfstart_mileage(temp);
			}
			//小计里程-平均车速
			temp = GetIntData(buffer[15],buffer[16],(byte) 0);
			ff = temp;
			if (CarPcInfo.getInstance().getM_fSelfstart_avgspeed() != ff) {
				bChange = true;
				CarPcInfo.getInstance().setM_fSelfstart_avgspeed(ff);
			}
			//小计里程-行驶时长
			temp = GetIntData(buffer[17],buffer[18],(byte) 0);
			if (CarPcInfo.getInstance().getM_iSelfstart_drivertime() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iSelfstart_drivertime(temp);
				
			}
			//小计里程-平均油耗
			temp = GetIntData(buffer[19],buffer[20],(byte) 0);
			ff = (int) (temp*0.1);
			if (temp == 0xffff) {//无效
				if (CarPcInfo.getInstance().getM_fSelfstart_avgfuel() != temp) {
					bChange = true;
					CarPcInfo.getInstance().setM_fSelfstart_avgfuel(temp);
				}
			} else {
				if (CarPcInfo.getInstance().getM_fSelfstart_avgfuel() != ff) {
					bChange = true;
					CarPcInfo.getInstance().setM_fSelfstart_avgfuel(ff);
				}
			}
			
			if (CarPcInfo.getInstance().getM_iResidualOil() != (buffer[21]&0xff)) {

				CarPcInfo.getInstance().setM_iResidualOil((buffer[21]&0xff));
				bChange = true;
			}
			
			if (bChange) {
				SendHandlerMessage(7);
			}
			
		}
	}

	/*
	 * 获取整数，地位在前，高位在后
	 */
	private int GetIntData(byte buf1, byte buf2, byte buf3) {
		
		int temp = (buf1&0xff)+(buf2&0xff)*256+(buf3&0xff)*256*256;
		return temp;
	}

	private void parseCmd0xC3(byte[] buffer, int length) {
		
		if (buffer != null && length > 0){
			boolean bChange = false;
			//data0,data1
			//瞬时油耗
			int temp = (buffer[0]&0xff)+(buffer[1]&0xff)*256;
			float ff = (float) (temp*0.1);
			if (temp == 0xffff) {//无效
				if (CarPcInfo.getInstance().getM_fInstantFuel() != temp) {
					bChange = true;
					CarPcInfo.getInstance().setM_fInstantFuel(temp);
				}
			} else {
	
				if (CarPcInfo.getInstance().getM_fInstantFuel() != ff) {
					bChange = true;
					CarPcInfo.getInstance().setM_fInstantFuel(ff);
				}
				
				
			}
			//电池电压
			temp = (buffer[2]&0xff);
			ff = (float) (temp*0.1);
			if (CarPcInfo.getInstance().getM_fBatteryVol() != ff) {
				bChange = true;
				CarPcInfo.getInstance().setM_fBatteryVol(ff);
			}
			//瞬时车速
			temp = (buffer[3]&0xff)+(buffer[4]&0xff)*256;
			ff = (float) (temp*0.1);
			if (temp == 0xffff) {//无效
				if (CarPcInfo.getInstance().getM_fInstantSpeed() != temp) {
					bChange = true;
					CarPcInfo.getInstance().setM_fInstantSpeed(temp);
				}
			} else {
				if (CarPcInfo.getInstance().getM_fInstantSpeed() != ff) {
					bChange = true;
					CarPcInfo.getInstance().setM_fInstantSpeed(ff);
					
				}
			}
			
			//冷却液温度
			temp = (buffer[5]&0xff)-40;
			if (CarPcInfo.getInstance().getM_iCooltemp() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iCooltemp(temp);
			}
			//发动机转速
			temp = (buffer[6]&0xff)+(buffer[7]&0xff)*256;
//			if (CarPcInfo.getInstance().getM_iEngineSpeed() != temp) {
//				bChange = true;
//				CarPcInfo.getInstance().setM_iEngineSpeed(temp);
//			}
			mNewZhuan = temp;
			setZhuansu();
			mHandler.removeMessages(0x205);
			mHandler.sendEmptyMessageDelayed(0x205, 300);
			bChange = true;
			
			//单位
			temp = GetByteData(buffer[11], 0, 0x03);
			if (CarPcInfo.getInstance().getM_iFuelUnit() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFuelUnit(temp);
			}
			
			if (bChange) {
				SendHandlerMessage(1);
			}
			
		}
	}

	private void setZhuansu() {
		
		mZhuan2 = mZhuan1;
		mZhuan1 = mZhuan;
		mZhuan = mNewZhuan;//
		
		int mTemp = (int) (mZhuan2*0.5+mZhuan1*0.3+mZhuan*0.2);
		Log.i("zhuansu1", "mZhuan2="+mZhuan2+",mZhuan1="+mZhuan1+",mZhuan="+mZhuan
				+",mTemp ="+mTemp);
		
		CarPcInfo.getInstance().setM_iEngineSpeed(mTemp);
	}

	private void parseCmd0xC2(byte[] buffer, int length) {
		
		if (buffer != null && length > 0) {
			boolean bChange = false;

			int i = 0;
			//data0
			//脚刹
			int temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iFootBrake() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFootBrake(temp);
			}
			//ILL
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iILL() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iILL(temp);
			}
			//ACC
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iACC() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iACC(temp);
				

			}
			//点火
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iFire() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFire(temp);
			}
			//手刹拉起
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iHanderBrake() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iHanderBrake(temp);
			}
			//锁车
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iLock()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLock(temp);
			}
			
			i++;
			//data1
			//前盖
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iHood() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iHood(temp);
			}
			//尾箱
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iTailBox() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iTailBox(temp);

			}

			//右后门
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRRDoor() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRDoor(temp);

			}
			//左后门
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iLRDoor() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRDoor(temp);
			}
			//右前门
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iRFDoor() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFDoor(temp);
			}
			//左前门
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iLFDoor() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFDoor(temp);
			}
			
			i++;
			//data2
			//天窗
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iDormer() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iDormer(temp);
			}
			//右后窗
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRRWindow()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRWindow(temp);
			}
			//左后窗
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iLRWindow()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRWindow(temp);
			}
			//右前窗
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iRFWindow()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFWindow(temp);
			}
			//左前窗
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iLFWindow()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFWindow(temp);
			}
			
			i++;
			//data3
			//档位
			temp = buffer[i]&0xff;
			if (CarPcInfo.getInstance().getM_iGear()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iGear(temp);
			}
			
			i++;
			//data4
			//日间行车灯
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iDayLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iDayLight(temp);
			}
			//倒车灯
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iRevLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRevLight(temp);
			}
			//刹车灯
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iStopLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iStopLight(temp);
			}
			//后雾灯
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iRearflogLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRearflogLight(temp);
			}
			//前雾灯
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iFrontflogLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFrontflogLight(temp);
			}
			//示宽灯
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iWideLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iWideLight(temp);
			}
			//近光灯
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iNearLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iNearLight(temp);
			}
			//远光灯
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iFarLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFarLight(temp);
			}
			
			i++;
			//data5
			//双闪灯
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iDoubleLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iDoubleLight(temp);
			}
			//左转向灯
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iLeftTrunLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLeftTrunLight(temp);
			}
			//右转向灯
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iRightTrunLight() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRightTrunLight(temp);
			}
			
			i++;
			//data6
			i++;
			//data7
			//灯光亮度
			temp = buffer[i]&0xff;
			if (CarPcInfo.getInstance().getM_iLightData() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLightData(temp);
			}
			
			i++;
			//data8
			//左前门锁
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iLFDoorlock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFDoorlock(temp);
			}
			//右前门锁
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iRFDoorlock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFDoorlock(temp);
			}
			//左后门锁
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iLRDoorLock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRDoorLock(temp);
			}
			//右后门锁
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iRRDoorlock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRDoorlock(temp);
			}
			//遥控车锁
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRemotelock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRemotelock(temp);
			}
			//车内锁锁
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iInnerlock() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iInnerlock(temp);
			}
			
			i++;
			//data9
			//左前安全带
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iLFSafebelt() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFSafebelt(temp);
			}
			//右前安全带
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iRFSafebelt() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFSafebelt(temp);
			}
			//左后安全带
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iLRSafebelt() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRSafebelt(temp);
			}
			//右后安全带
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iRRSafebelt() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRSafebelt(temp);
			}
			//右中后安全带
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRMRSafebelt() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRMRSafebelt(temp);
			}

		
			if (bChange) {
				SendHandlerMessage(1);
			}
		}
	}
	

	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x205:
				setZhuansu();
				if (mHandlerHud != null) {
					mHandlerHud.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
				}
				mHandler.sendEmptyMessageDelayed(0x205, 300);
				break;
			default:
				break;
			}
		}
		
	};





	/*
	 * 解析cmdID 0xc9
	 */
	private void parseCmd0xC9(byte[] buffer, int length) {
		
		if (buffer != null && length > 0) {
			boolean bChange = false;
			int i = 0;
			//data0
			//档位使能
			int temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iGearAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iGearAble(temp);
			}
			//灯光亮度
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iLightBlueAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLightBlueAble(temp);
			}
			//车内灯光使能
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iInnerLightAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iInnerLightAble(temp);
			}
			//车外灯光使能
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iOutLightAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iOutLightAble(temp);
			}
			//手刹使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iHandbrakeAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iHandbrakeAble(temp);
			}
			//基本信息使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iBaseStateAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iBaseStateAble(temp);
			}
			//车窗使能
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iWindowAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iWindowAble(temp);
			}
			//车门使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iDoorAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iDoorAble(temp);
			}
			
			i++;
			//data1
			//VIN使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iVINAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iVINAble(temp);
			}
			//制造日期使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iMadeTimeAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iMadeTimeAble(temp);
			}
			//品牌使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iBrandAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iBrandAble(temp);
			}
			
			i++;
			//data2
			
			i++;
			//data3
			//左右转向灯使能
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iLRTrunAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRTrunAble(temp);
			}
			//瞬时油耗使能
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iInstantFuelAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iInstantFuelAble(temp);
			}
			//冷却液温度使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iCooltempAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iCooltempAble(temp);
			}
			//电池电压使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iBatteryVolAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iBatteryVolAble(temp);
			}
			//发动机转速使能
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iEnigineSpeedAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iEnigineSpeedAble(temp);
			}
			//车速使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iSpeedAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iSpeedAble(temp);
			}
			
			i++;
			//data4
			//倒档信息使能
			temp = GetByteBit(buffer[i], 1);
			if (CarPcInfo.getInstance().getM_iReverseGearAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iReverseGearAble(temp);
			}
			//方向盘转角使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iSteerAngleAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iSteerAngleAble(temp);
			}
			
			i++;
			//data5
			i++;
			//data6
			//自启动后里程使能
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iSelfStartMileageAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iSelfStartMileageAble(temp);
			}
			//小计里程使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iLittleTripAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLittleTripAble(temp);
			}
			//续航里程使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iMileageAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iMileageAble(temp);
			}
			//总里程使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iTotalMileageAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iTotalMileageAble(temp);
			}
			
			i++;
			//data7
			i++;
			//data8
			//左前门锁使能
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iLFGatelockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFGatelockAble(temp);
			}
			//右前门锁使能
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iRFGatelockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFGatelockAble(temp);
			}
			//左后门锁使能
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iLRGatelockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRGatelockAble(temp);
			}
			//右后门锁使能
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iRRGatelockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRGatelockAble(temp);
			}
			//遥控车外锁车使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRemoteoutlockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRemoteoutlockAble(temp);
			}
			//车内锁车使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iInnerlockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iInnerlockAble(temp);
			}
			
			i++;
			//data9
			//左前安全带
			temp = GetByteBit(buffer[i], 7);
			if (CarPcInfo.getInstance().getM_iLFSafebeltAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLFSafebeltAble(temp);
			}
			//右前安全带
			temp = GetByteBit(buffer[i], 6);
			if (CarPcInfo.getInstance().getM_iRFSafebeltAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRFSafebeltAble(temp);
			}
			//左后安全带
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iLRSafebeltAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLRSafebeltAble(temp);
			}
			//右后安全带
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iRRSafebeltAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRRSafebeltAble(temp);
			}
			//右中后安全带
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iRMRSafebeltAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iRMRSafebeltAble(temp);
			}
			
			i++;
			//data10
			//脚刹使能
			temp = GetByteBit(buffer[i], 5);
			if (CarPcInfo.getInstance().getM_iFootbrakeAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFootbrakeAble(temp);
			}
			//ILL使能
			temp = GetByteBit(buffer[i], 4);
			if (CarPcInfo.getInstance().getM_iILLAble()!= temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iILLAble(temp);
			}
			//ACC使能
			temp = GetByteBit(buffer[i], 3);
			if (CarPcInfo.getInstance().getM_iACCAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iACCAble(temp);
			}
			//点火使能
			temp = GetByteBit(buffer[i], 2);
			if (CarPcInfo.getInstance().getM_iFireAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iFireAble(temp);
				
			}
			//锁车使能
			temp = GetByteBit(buffer[i], 0);
			if (CarPcInfo.getInstance().getM_iLockAble() != temp) {
				bChange = true;
				CarPcInfo.getInstance().setM_iLockAble(temp);
			}
			
			if (bChange) {
				SendHandlerMessage(7);
			}

		}
	}

	private void SendHandlerMessage(int iMsg) {
		
		switch (iMsg) {
		case 1://车辆状态
			if (mHandlerCarState != null) {
				mHandlerCarState.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHud != null) {
				mHandlerHud.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerDoorlock != null) {
				mHandlerDoorlock.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerLight != null) {
				mHandlerLight.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHtpms != null) {
				mHandlerHtpms.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerTpmsFragment != null) {
				mHandlerTpmsFragment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerInstrument != null) {
				mHandlerInstrument.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerMirror != null) {
				mHandlerMirror.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerMainFregment != null) {
				mHandlerMainFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHomeFregment != null) {
				mHandlerHomeFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarStateFregment != null) {
				mHandlerCarStateFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			
			break;
		case 2://车辆行程
			if (mHandlerCarTrip != null) {
				mHandlerCarTrip.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			break;
		case 3://车辆信息
			if (mHandlerCarInfo != null) {
				mHandlerCarInfo.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerWindow != null) {
				mHandlerWindow.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerControl != null) {
				mHandlerControl.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			break;
		case 4://车辆状态//车辆行程
			if (mHandlerCarState != null) {
				mHandlerCarState.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarTrip != null) {
				mHandlerCarTrip.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerMainFregment != null) {
				mHandlerMainFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHomeFregment != null) {
				mHandlerHomeFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarStateFregment != null) {
				mHandlerCarStateFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			
			break;
		case 5://车辆状态//车辆信息
			if (mHandlerCarState != null) {
				mHandlerCarState.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarInfo != null) {
				mHandlerCarInfo.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerMainFregment != null) {
				mHandlerMainFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHomeFregment != null) {
				mHandlerHomeFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarStateFregment != null) {
				mHandlerCarStateFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			
			break;
		case 6://车辆行程//车辆信息
			if (mHandlerCarTrip != null) {
				mHandlerCarTrip.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarInfo != null) {
				mHandlerCarInfo.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			break;
		case 7://车辆状态//车辆行程//车辆信息
			if (mHandlerCarState != null) {
				mHandlerCarState.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarTrip != null) {
				mHandlerCarTrip.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarInfo != null) {
				mHandlerCarInfo.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHud != null) {
				mHandlerHud.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerMainFregment != null) {
				mHandlerMainFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerHomeFregment != null) {
				mHandlerHomeFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			if (mHandlerCarStateFregment != null) {
				mHandlerCarStateFregment.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
			}
			break;
		default:
			break;
		}
	}

	private int GetByteBit(int data, int bit) {
		
		int temp = (int)(data>>bit & 0x01);
		return temp;
	}
	
	private int GetByteData(int data, int bit, int num) {
		
		int temp = (int)(data>>bit & num);
		return temp;
	}

	/*
	 * 解析cmdID 0xc1
	 */
	private void parseCmd0xC1(byte[] buffer, int length) {
		
		if (buffer != null && length > 0) {
			//识别码
			byte[] code = new byte[17];
			System.arraycopy(buffer, 0, code, 0, 17);
			String s1 = new String(code);
			CarPcInfo.getInstance().setM_strVIN(s1);

			//品牌，车型，制造日期
			SetBaseNum(buffer[17]&0xff,buffer[18]&0xff,buffer[19]&0xff);
			
			SendHandlerMessage(3);
		}
	}


	private void SetBaseNum(int Index, int iMod, int iMadeInChina) {
		
		//品牌
		strArrayBrand = convertStrToArray2("[-品牌-]");
		if (strArrayBrand != null && strArrayBrand.length > 0) {
			if (Index < strArrayBrand.length) {
				CarPcInfo.getInstance().setM_strBrand(strArrayBrand[Index]);
			}
		}
		//制造日期
		if (iMadeInChina != 0) {
			CarPcInfo.getInstance().setM_strMadeTime(Integer.toString(2000+iMadeInChina));
		}
		//车型
		switch (Index) {
		case 1://丰田
			if (iMod >= 101) {
				strArrayModel = convertStrToArray2("[-Lexus-]");
				if (strArrayModel.length > iMod-101) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
				}
			} else {
				strArrayModel = convertStrToArray2("[-丰田-]");
				if (strArrayModel.length > iMod) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
				}
			}
			break;
		case 2://本田
			if (iMod >= 101) {
				strArrayModel = convertStrToArray2("[-讴歌-]");
				if (strArrayModel.length > iMod-101) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
				}
			} else {
				strArrayModel = convertStrToArray2("[-本田-]");
				if (strArrayModel.length > iMod) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
				}
			}
			break;
		case 3://福特
			strArrayModel = convertStrToArray2("[-福特-]");
			if (strArrayModel.length > iMod) {
				CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
			}
			break;
		case 4://日产
			//英菲尼迪    
			if (iMod >= 151) {
				strArrayModel = convertStrToArray2("[-英菲尼迪-]");
				if (strArrayModel.length > iMod-151) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-151]);
				}
			}
			else
			{
				if (iMod >= 101) {
					strArrayModel = convertStrToArray2("[-启辰-]");
					if (strArrayModel.length > iMod-101) {
						CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
					}
				} else {
					strArrayModel = convertStrToArray2("[-日产-]");
					if (strArrayModel.length > iMod) {
						CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
					}
				}
			}
			break;
		case 6://大众
			if (iMod >= 101) {
				strArrayModel = convertStrToArray2("[-斯柯达-]");
				if (strArrayModel.length > iMod-101) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
				}
			} else {
				strArrayModel = convertStrToArray2("[-大众-]");
				if (strArrayModel.length > iMod) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
				}
			}
			break;
		case 7://通用
			if (iMod >= 101) {
				strArrayModel = convertStrToArray2("[-别克-]");
				if (strArrayModel.length > iMod-101) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
				}
			} else {
				strArrayModel = convertStrToArray2("[-通用-]");
				if (strArrayModel.length > iMod) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
				}
			}
			break;
		case 9://现代
			strArrayModel = convertStrToArray2("[-现代-]");
			if (strArrayModel.length > iMod) {
				CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
			}
			break;
		case 10://马自达
			if (iMod >= 101) {
				strArrayModel = convertStrToArray2("[-进口马自达-]");
				if (strArrayModel.length > iMod-101) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-101]);
				}
			}
			else
			{
				if (iMod >= 51) {
					strArrayModel = convertStrToArray2("[-一汽马自达-]");
					if (strArrayModel.length > iMod-51) {
						CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-51]);
					}
				} else {
					strArrayModel = convertStrToArray2("[-长安马自达-]");
					if (strArrayModel.length > iMod) {
						CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
					}
				}
			}
			break;
		case 23://起亚
			if (iMod >= 51) {
				strArrayModel = convertStrToArray2("[-进口起亚-]");
				if (strArrayModel.length > iMod-51) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod-51]);
				}
			} else {
				strArrayModel = convertStrToArray2("[-起亚-]");
				if (strArrayModel.length > iMod) {
					CarPcInfo.getInstance().setM_strModel(strArrayModel[iMod]);
				}
			}
			break;
		default:
			break;
		}
	}


	private String[] convertStrToArray2(String str) {
		
		String line = "";
		for (int k = 0; k < strArrayLine.size(); k++) {
			if (strArrayLine.get(k).contains(str)) {
				line = strArrayLine.get(k+1);
				break;
			}
		}
		
        StringTokenizer st = new StringTokenizer(line,",");//把","作为分割标志，然后把分割好的字符赋予StringTokenizer对象。
        String[] strArray = new String[st.countTokens()];//通过StringTokenizer 类的countTokens方法计算在生成异常之前可以调用此 tokenizer 的 nextToken 方法的次数。
        int i=0;
        while(st.hasMoreTokens()){//看看此 tokenizer 的字符串中是否还有更多的可用标记。
            strArray[i++] = st.nextToken();//返回此 string tokenizer 的下一个标记。
        }
        return strArray;
	}

	@Override
	public void setCarStateHandler(Handler handler) {
		
		this.mHandlerCarState = handler;
	}


	@Override
	public void setCarInfoHandler(Handler handler) {
		
		this.mHandlerCarInfo = handler;
	}

	@Override
	public void setCarTripHandler(Handler handler) {
		
		this.mHandlerCarTrip = handler;
	}

	@Override
	public void setHudHandler(Handler handler) {
		
		mHandlerHud = handler;
	}

	@Override
	public void setDoorlockHandler(Handler handler) {
		
		mHandlerDoorlock = handler;
	}

	@Override
	public void setLightHandler(Handler handler) {
		
		mHandlerLight = handler;
	}

	@Override
	public void setWindowHandler(Handler handler) {
		
		mHandlerWindow = handler;
	}

	@Override
	public void setControlHandler(Handler handler) {
		
		mHandlerControl = handler;
	}

	@Override
	public void setHtpmsHandler(Handler handler) {
		
		mHandlerHtpms = handler;
	}
	

	@Override
	public void setMirrorHandler(Handler handler) {
		
		mHandlerMirror = handler;
	}

	@Override
	public void setInstrumentHandler(Handler handler) {
		
		mHandlerInstrument = handler;
	}
	
	@Override
	public void setMainFregmentHandler(Handler handler) {
		
		mHandlerMainFregment = handler;
	}
	
	@Override
	public void setmHandlerHomeFregment(Handler handler) {
		this.mHandlerHomeFregment = handler;
	}
	
	@Override
	public void setmHandlerCarStateFregment(Handler handler) {
		this.mHandlerCarStateFregment = handler;
	}

	@Override
	public void setmHandlerTpmsFragment(Handler handler) {
		this.mHandlerTpmsFragment = handler;
	}
	
	
}
