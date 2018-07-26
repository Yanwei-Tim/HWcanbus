package com.ex.hiworld.aidl.car;

import com.ex.hiworld.server.MyApp;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.TypeWC1_Data;
import com.ex.hiworld.server.canbus.TypeWC2_Data;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.HandlerNotRemove;
import com.ex.hiworld.server.tools.Ticks;
import com.ex.hiworld.server.tools.Utils;
import com.ex.hiworld.server.tools.spUtils;

public class TaskCar_Hyunda extends BaseCar {

	public static final int U_DOOR_BEGIN					= 0;
	public static final int U_DOOR_ENGINE					= U_DOOR_BEGIN+0;
	public static final int U_DOOR_FL						= U_DOOR_BEGIN+1;
	public static final int U_DOOR_FR						= U_DOOR_BEGIN+2;
	public static final int U_DOOR_RL						= U_DOOR_BEGIN+3;
	public static final int U_DOOR_RR						= U_DOOR_BEGIN+4;
	public static final int U_DOOR_BACK						= U_DOOR_BEGIN+5;
	public static final int U_DOOR_END						= U_DOOR_BEGIN+6;

	public static final int U_AIR_BEGIN 						= U_DOOR_END + 1;
	public static final int U_SHOW_AIRKEY 						= U_AIR_BEGIN + 2;
	public static final int U_AIR_POWER 						= U_AIR_BEGIN + 3;
	public static final int U_AIR_AUTO 							= U_AIR_BEGIN + 4;
	public static final int U_AIR_SYNC 							= U_AIR_BEGIN + 5;
	public static final int U_AIR_AC 							= U_AIR_BEGIN + 6;
	public static final int U_AIR_AIR_QUALITY 					= U_AIR_BEGIN + 7;
	public static final int U_AIR_CYCLE 						= U_AIR_BEGIN + 8;
	public static final int U_AIR_FRONT_DEFROG 					= U_AIR_BEGIN + 9;
	public static final int U_AIR_BLOW_MODE 					= U_AIR_BEGIN + 10;
	public static final int U_AIR_BLOW_WIN 						= U_AIR_BEGIN + 11;
	public static final int U_AIR_BLOW_BODY 					= U_AIR_BEGIN + 12;
	public static final int U_AIR_BLOW_FOOT 					= U_AIR_BEGIN + 13;
	public static final int U_AIR_WIND_LEVEL 					= U_AIR_BEGIN + 14;
	public static final int U_AIR_TEMP_LEFT 					= U_AIR_BEGIN + 15;
	public static final int U_AIR_TEMP_RIGHT 					= U_AIR_BEGIN + 16;
	public static final int U_AIR_END 							= U_AIR_BEGIN + 17;

	public static final int U_ENGINE_SPEED 						= U_AIR_END + 1;
	public static final int U_CUR_SPEED 						= U_AIR_END + 2;

	public static final int U_AMP_VOLUME 					= U_CUR_SPEED + 1;
	public static final int U_AMP_BALANCE_LeftRight 		= U_AMP_VOLUME + 1;
	public static final int U_AMP_BALANCE_FrontRear 		= U_AMP_VOLUME + 2;
	public static final int U_AMP_BALANCE_BASS 				= U_AMP_VOLUME + 3;
	public static final int U_AMP_BALANCE_MID 				= U_AMP_VOLUME + 4;
	public static final int U_AMP_BALANCE_TREMBLE 			= U_AMP_VOLUME + 5;
	public static final int U_AMP_MUTE 						= U_AMP_VOLUME + 6;
	
	public static final int U_SET_REAR_INIT_VIEW_MODE 		= U_AMP_MUTE + 1;
	public static final int U_SET_FRONT_INIT_VIEW_MODE 		= U_AMP_MUTE + 2;
	public static final int U_SET_FRONT_REAR_NEAR_WARN_SHOW = U_AMP_MUTE + 3;
	public static final int U_SET_GUIDE_LINE_SHOW 			= U_AMP_MUTE + 4;
	public static final int U_SET_FULLVIEW_MODE 			= U_AMP_MUTE + 5;
	public static final int U_SET_CARTYPE 					= U_AMP_MUTE + 6;
	
	public static final int U_CNT_MAX 						= U_SET_FULLVIEW_MODE + 10;
	
	public static final int TEMPERATURE_NONE	= -1;
	public static final int TEMPERATURE_LOW		= -2;
	public static final int TEMPERATURE_HIGH	= -3;
	
	public static final int C_AMP_SET 				= 0;
	public static final int C_CARTYPE_SET 			= 1;
	public static final int C_FULLVIEW_SET 		= 2;
	public static final int C_AIR_SET 				= 3;
	
	private static final int SP_CARTYPE_MODE = 0;
	
	int Vol_dis_cnt = 0;
	int TemCanKey, CanKey, CanKey2, TemCanKey2 = 0xff;
	 int KeyCanKeyTable[][]=
		 {
	 			{1,			FinalKeyCode.KEY_CODE_VOL_UP},
	 			{2, 		FinalKeyCode.KEY_CODE_VOL_DOWN},		 	
	 			{3, 		FinalKeyCode.KEY_CODE_MUTE},
	 			{4,			FinalKeyCode.KEY_CODE_VA},
	 			{5,			FinalKeyCode.KEY_CODE_PHONE},
	 			{6, 		FinalKeyCode.KEY_CODE_HANG}, 
	 			{7,			FinalKeyCode.KEY_CODE_NULL},
	 			{0x08,		FinalKeyCode.KEY_CODE_PREV},	
	 			{0x09,		FinalKeyCode.KEY_CODE_NEXT},
	 			{0x0a,		FinalKeyCode.KEY_CODE_MODE},
	 			{0x0B,		FinalKeyCode.KEY_CODE_MODE},
	 			{0x20,		FinalKeyCode.KEY_CODE_SEEK_UP},
	 			{0x21,		FinalKeyCode.KEY_CODE_SEEK_DOWN},
	 			{0x45,		FinalKeyCode.KEY_CODE_PHONE},
	 			{0x46,		FinalKeyCode.KEY_CODE_NAVI}, 
	 			{0x47,		FinalKeyCode.KEY_CODE_VA}, 
	 			

	 			{0x01|0x80,		FinalKeyCode.KEY_CODE_POWER}, 
	 			{0x02|0x80,		FinalKeyCode.KEY_CODE_PREV}, 
	 			{0x03|0x80,		FinalKeyCode.KEY_CODE_NEXT}, 
	 			{0x06|0x80,		FinalKeyCode.KEY_CODE_BACK}, 
	 			{0x16|0x80,		FinalKeyCode.KEY_CODE_NULL},  // select
	 			{0x21|0x80,		FinalKeyCode.KEY_CODE_NAVI}, 
	 			{0x24|0x80,		FinalKeyCode.KEY_CODE_PLAYER}, 
	 			{0x28|0x80,		FinalKeyCode.KEY_CODE_PHONE}, 
	 			{0x2B|0x80,		FinalKeyCode.KEY_CODE_HOME}, 
	 			{0x2F|0x80,		FinalKeyCode.KEY_CODE_MENU}, 
	 			{0x35|0x80,		FinalKeyCode.KEY_CODE_TIMESETTINT}, 
	 			{0x36|0x80,		FinalKeyCode.KEY_CODE_CARSETTTING}, 
	 			{0x37|0x80,		FinalKeyCode.KEY_CODE_SEARCH}, 
	 			{0x38|0x80,		FinalKeyCode.KEY_CODE_DVD}, 
	 			{0x39|0x80,		FinalKeyCode.KEY_CODE_NAVI}, 
	 			{0x47|0x80,		FinalKeyCode.KEY_CODE_FM}, 
	 			{0x48|0x80,		FinalKeyCode.KEY_CODE_AM}, 
	 			{0x49|0x80,		FinalKeyCode.KEY_CODE_NULL}, // FILE 
	 			{0x4a|0x80,		FinalKeyCode.KEY_CODE_NULL}, // UVO
	 			{0x4B|0x80,		FinalKeyCode.KEY_CODE_BAND}, 
	 			{0x5B|0x80,		FinalKeyCode.KEY_CODE_PREV}, 
	 			{0x5C|0x80,		FinalKeyCode.KEY_CODE_NEXT}, 
	 			{0x5F|0x80,		FinalKeyCode.KEY_CODE_VA},  
		 };
		
	
	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start + 1]) {
		case 0x11:
			CanInfos.onKeyEvent(KeyCanKeyTable, data[start + 4], data[start + 5]);
			break;
		case 0x12: {
			int B2 = data[start + 4];
			HandlerTaskCanbus.update(U_DOOR_FL, B2 >> 7 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_FR, B2 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RL, B2 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_RR, B2 >> 4 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_BACK, B2 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_DOOR_ENGINE, B2 >> 2 & 0x01);
			break;
		}

		case 0x21: {
			int Cank = data[start + 2] | 0x80;
			CanInfos.onKeyEvent(KeyCanKeyTable, Cank, data[start + 3]);
			break;
		}
		case 0x22:{ //  旋钮
			int event = data[start + 2]&0xFF;
			int action = data[start + 3]&0xFF;
			switch (event) {
			case 1:{
				if(action < 0x80) {
					CarKeyVolPlusCnt=CarKeyVolPlusCnt+(action&0xff);
					HandlerNotRemove.getInstance().post(CarDisNormal);
				}else {
					CarKeyVolMinusCnt=CarKeyVolMinusCnt+(0x100-(action&0xff));
					HandlerNotRemove.getInstance().post(CarDisNormal);
				}
				break;
			}
				
			case 2:{
				if((data[start+3]&0xff) < 0x80){
					CanInfos.canbusKeyNext();
				}
				else{
					CanInfos.canbusKeyPrev();
				}
				break;
			}
			case 3:{
				if((data[start+3]&0xff) < 0x80){
					CanInfos.canbusKeyRight();
				}
				else{
					CanInfos.canbusKeyLeft();
				}
				break;
			}
			default:
				break;
			}
			break;
		}
		case 0x31: {
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];
			int B7 = data[start + 9];
			int B11 = data[start + 13];

			HandlerTaskCanbus.update(U_SHOW_AIRKEY, B0 >> 7 & 0x01);
			HandlerTaskCanbus.update(U_AIR_POWER, B0 >> 6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AUTO, B0 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_SYNC, B0 >> 2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC, B0 >> 0 & 0x03);

			HandlerTaskCanbus.update(U_AIR_AIR_QUALITY, B1 >> 5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_CYCLE, B1 >> 4 & 0x01);

			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROG, B2 >> 4 & 0x01);
			
			HandlerTaskCanbus.update(U_AIR_BLOW_MODE, B4 & 0x01);
			int win =0, body=0, foot = 0, auto=0, front= 0;
			
			switch (B4) {
			case 1: auto = 1; break;
			case 2:	front = 1; break;
			case 3: foot = 1; break;
			case 5: body = foot = 1; break;
			case 6: body = 1; break;
			case 0x0B: win = 1; break;
			case 0x0C: win = foot = 1; break;
			case 0x0D: win = body = 1; break;
			case 0x0E: win = body = foot = 1; break; 
			}
			
			HandlerTaskCanbus.update(U_AIR_AUTO, auto);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROG, front);
			HandlerTaskCanbus.update(U_AIR_BLOW_WIN, win);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY, body);
			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT, foot);
			

			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL, B5);
			
			switch (B6) {
			case 0xFE: {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, TEMPERATURE_LOW);
				break;
			}
			case 0xFF: {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, TEMPERATURE_HIGH);
				break;
			}
			default: {
				HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, (data[start + 8] & 0xff) * 5);
				break;
			}
			}
			
			switch (data[start + 9]) {
			case 0xFE: {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_LOW);
				break;
			}
			case 0xFF: {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, TEMPERATURE_HIGH);
				break;
			}
			default: {
				HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, (data[start + 9] & 0xff) * 5);
				break;
			}
			}
			 
            int tempature = 0;
            tempature = 1000 + (B11 * 5) - 400;
            SendFunc.sendOutTemp(1, tempature);  
			break;
		}
		case 0x32:{ 
			HandlerTaskCanbus.update(U_ENGINE_SPEED, Utils.combine(data[start+2], data[start+3]));
			HandlerTaskCanbus.update(U_CUR_SPEED, Utils.combine(data[start+2], data[start+3]));
			break;
		}
		case 0x41:{
			CanInfos.radarRl(TypeWC2_Data.CarGetRadarDistancef5(data[start + 2] & 0xff));
			CanInfos.radarRml(TypeWC2_Data.CarGetRadarDistancef5(data[start + 3] & 0xff));
			CanInfos.radarRmr(TypeWC2_Data.CarGetRadarDistancef5(data[start + 3] & 0xff));
			CanInfos.radarRr(TypeWC2_Data.CarGetRadarDistancef5(data[start + 5] & 0xff));
			CanInfos.radarFl(TypeWC2_Data.CarGetRadarDistancef5(data[start + 6] & 0xff));
			CanInfos.radarFml(TypeWC2_Data.CarGetRadarDistancef5(data[start + 7] & 0xff));
			CanInfos.radarFmr(TypeWC2_Data.CarGetRadarDistancef5(data[start + 7] & 0xff));
			CanInfos.radarFr(TypeWC2_Data.CarGetRadarDistancef5(data[start + 9] & 0xff)); 
			break;
		}
		case 0xA6: { // AMP
			int B0 = data[start + 2];
			int B1 = data[start + 3];
			int B2 = data[start + 4];
			int B3 = data[start + 5];
			int B4 = data[start + 6];
			int B5 = data[start + 7];
			int B6 = data[start + 8];

			// IX35 ， 索纳塔 最大音量 0x1E
			// 索纳塔9， 名图 最大音量 0x2D
			// IX45  最大音量 0x23 无静音控制
			// KX9 高配  最大音量 0x2D 无静音控制
			
			HandlerTaskCanbus.update(U_AMP_VOLUME, B0);
			HandlerTaskCanbus.update(U_AMP_BALANCE_LeftRight, B1);
			HandlerTaskCanbus.update(U_AMP_BALANCE_FrontRear, B2);
			HandlerTaskCanbus.update(U_AMP_BALANCE_BASS, B3);
			HandlerTaskCanbus.update(U_AMP_BALANCE_MID, B4);
			HandlerTaskCanbus.update(U_AMP_BALANCE_TREMBLE, B5);
			HandlerTaskCanbus.update(U_AMP_MUTE, B6 & 0x01);
			break;
		}
		case 0xE9:{
			int B0 = data[start + 2];
			HandlerTaskCanbus.update(U_SET_REAR_INIT_VIEW_MODE, B0 >> 6 & 0x03);
			HandlerTaskCanbus.update(U_SET_FRONT_INIT_VIEW_MODE, B0 >> 4 & 0x03);
			HandlerTaskCanbus.update(U_SET_FRONT_REAR_NEAR_WARN_SHOW, B0 >> 3 & 0x01);
			HandlerTaskCanbus.update(U_SET_GUIDE_LINE_SHOW, B0 >> 2 & 0x01);
			HandlerTaskCanbus.update(U_SET_FULLVIEW_MODE, B0  & 0x03);
			break;
		}
		default:
			break;
		}
	}	

	private static int CarKeyVolPlusCnt;
	private static int CarKeyVolMinusCnt;
	public static Runnable CarDisNormal = new Runnable() {
		@Override
		public void run() {
			if (CarKeyVolPlusCnt != 0) {
				CarKeyVolPlusCnt--;
				CanInfos.mcuKeyVolUp();
			} else if (CarKeyVolMinusCnt != 0) {
				CarKeyVolMinusCnt--;
				CanInfos.mcuKeyVolDown();
			}
			if (CarKeyVolPlusCnt != 0 || CarKeyVolMinusCnt != 0)
				HandlerNotRemove.getInstance().postDelayed(this, 10);
		}
	};
	@Override
	public void in() {
		spUtils.init(""+getClass().getName(), MyApp.getInstance()); 
		Ticks.addTicks1s(TypeWC1_Data.mCarDisNormal);
		Ticks.addTicks1s(TypeWC1_Data.mCarDisTime1);
		EventNotify.NE_RADIO_FREQS.addNotify(TypeWC1_Data.mCarDisNormal, 1);
		EventNotify.NE_RADIO_BAND.addNotify(TypeWC1_Data.mCarDisNormal, 1);
		
		setCarType(spUtils.get(SP_CARTYPE_MODE, 0));
	}

	private void setCarType(int i) {
		HandlerTaskCanbus.update(U_SET_CARTYPE, i);
		SendFunc.send2Canbus(0x24, i, 0x09);
		if (spUtils.get(SP_CARTYPE_MODE, 0) != i) {
			spUtils.set(SP_CARTYPE_MODE, i);
		}
	}

	@Override
	public void out() {
		Ticks.removeTicks1s(TypeWC1_Data.mCarDisNormal);
		Ticks.removeTicks1s(TypeWC1_Data.mCarDisTime1);
		EventNotify.NE_RADIO_FREQS.removeNotify(TypeWC1_Data.mCarDisNormal);
		EventNotify.NE_RADIO_BAND.removeNotify(TypeWC1_Data.mCarDisNormal);

	}

	
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		switch (cmd) {
		case C_AMP_SET:{
			if(ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0xAD, ints);
			}
			break;
		} 
		case C_CARTYPE_SET:{
			if(ints != null && ints.length > 0) { 
				setCarType(ints[0]);
			}
			break;
		} 
		case C_FULLVIEW_SET:{
			if(ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0xF9, ints);
			}
			break;
		} 
		case C_AIR_SET:{
			if(ints != null && ints.length > 0) {
				SendFunc.send2Canbus(0x3D, ints);
			}
			break;
		} 
		default:
			break;
		}
	};
}
