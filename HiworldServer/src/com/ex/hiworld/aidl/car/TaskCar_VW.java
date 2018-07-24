package com.ex.hiworld.aidl.car;

import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalKeyCode;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.Utils;

import android.R.integer;
 

public class TaskCar_VW extends BaseCar {

    /**
     * 车门
     */
    public static final int U_DOOR_BEGIN                                = 0;
    public static final int U_DOOR_ENGINE                          		= U_DOOR_BEGIN + 0;
    public static final int U_DOOR_FL                                	= U_DOOR_BEGIN + 1;
    public static final int U_DOOR_FR                                	= U_DOOR_BEGIN + 2;
    public static final int U_DOOR_RL                                	= U_DOOR_BEGIN + 3;
    public static final int U_DOOR_RR                                	= U_DOOR_BEGIN + 4;
    public static final int U_DOOR_BACK                                	= U_DOOR_BEGIN + 5;
    public static final int U_DOOR_END                                	= U_DOOR_BEGIN + 6;
	
	public static final int U_AIR_BEGIN                                	= U_DOOR_END + 1;
    public static final int U_AIR_AC_MAX                             	= U_AIR_BEGIN + 1;
    public static final int U_AIR_AUTO                                	= U_AIR_BEGIN + 2;
    public static final int U_AIR_SYNC                                	= U_AIR_BEGIN + 3;
    public static final int U_AIR_AC                                	= U_AIR_BEGIN + 4;
    public static final int U_AIR_SEAT_HEAT_LEFT                       	= U_AIR_BEGIN + 5;
    public static final int U_AIR_SEAT_HEAT_RIGHT                      	= U_AIR_BEGIN + 6;
    public static final int U_AIR_BLOW_AUTO                          	= U_AIR_BEGIN + 7;
    public static final int U_AIR_BLOW_BODY                            	= U_AIR_BEGIN + 8;
    public static final int U_AIR_BLOW_FOOT                       	    = U_AIR_BEGIN + 9;
    public static final int U_AIR_WIND_LEVEL                       		= U_AIR_BEGIN + 10;
    public static final int U_AIR_TEMP_LEFT                           	= U_AIR_BEGIN + 11;
    public static final int U_AIR_TEMP_RIGHT                          	= U_AIR_BEGIN + 12;
    public static final int U_AIR_DUAL                                	= U_AIR_BEGIN + 13;
    public static final int U_AIR_AQS                                	= U_AIR_BEGIN + 14;
    public static final int U_SHOW_AIR 									= U_AIR_BEGIN + 15;
    public static final int U_AIR_POWER                            		= U_AIR_BEGIN + 16;
	public static final int U_AIR_AUTO2                         		= U_AIR_BEGIN + 17;
	public static final int U_AIR_REAR_ENBALE                         	= U_AIR_BEGIN + 18;
	public static final int U_AIR_REAR_DEFROST                     		= U_AIR_BEGIN + 19;
	public static final int U_AIR_FRONT_DEFROST                   		= U_AIR_BEGIN + 20;
	public static final int U_AIR_BLOW_WIN              				= U_AIR_BEGIN + 21;
	public static final int U_AIR_WIND_LEVEL_RIGHT                     	= U_AIR_BEGIN + 22;
	public static final int U_AIR_RIGHT_BLOW_FOOT                      	= U_AIR_BEGIN + 23;
	public static final int U_AIR_RIGHT_BLOW_BODY                      	= U_AIR_BEGIN + 24;
	public static final int U_AIR_RIGHT_BLOW_WIN                       	= U_AIR_BEGIN + 25;
    public static final int U_AIR_END                                	= U_AIR_BEGIN + 26;

	public static final int U_CARINFO_BEGIN								= U_AIR_END + 1;
	public static final int U_BT_ENABLE                        			= U_CARINFO_BEGIN + 1;
	public static final int U_RADAR_ENABLE                     			= U_CARINFO_BEGIN + 1;
	public static final int U_KEYIN_ENABLE                     			= U_CARINFO_BEGIN + 2;
	public static final int U_HANDBRAKE                     			= U_CARINFO_BEGIN + 3;
	public static final int U_BACKCAR                     				= U_CARINFO_BEGIN + 4;
	public static final int U_BIGLIGHT                     				= U_CARINFO_BEGIN + 5;
	public static final int U_ACC_ON                     				= U_CARINFO_BEGIN + 6;
	public static final int U_CAR_SPEED                     			= U_CARINFO_BEGIN + 7;
	public static final int U_LIGHT_BRIGHTNESS                    		= U_CARINFO_BEGIN + 8;
	public static final int U_DOOR_ENBALE                     			= U_CARINFO_BEGIN + 9;
	public static final int U_SET_47D137                     			= U_CARINFO_BEGIN + 10;		// 路边驻车
	public static final int U_SET_47D136                     			= U_CARINFO_BEGIN + 11; 	// 入库驻车
	public static final int U_SET_47D135                     			= U_CARINFO_BEGIN + 12; 	// 雷达静音
	public static final int U_CAR_WATER_TEMP                     		= U_CARINFO_BEGIN + 13;
	public static final int U_CAR_INSTANT_FUEL_CONSUME             		= U_CARINFO_BEGIN + 14;
	public static final int U_CAR_GEAR_STATE                     		= U_CARINFO_BEGIN + 15;
	public static final int U_CAR_LOW_FUEL_WARN                     	= U_CARINFO_BEGIN + 16;
	public static final int U_CAR_LOW_POWER_WARN                     	= U_CARINFO_BEGIN + 17;
	public static final int U_CAR_BELT_WARN                     		= U_CARINFO_BEGIN + 18;
	public static final int U_CAR_LIQUID_WARN                     		= U_CARINFO_BEGIN + 19;
	public static final int U_CAR_MOTOR_OIL_WARN                     	= U_CARINFO_BEGIN + 20;
	public static final int U_CAR_REMAIN_OIL                     		= U_CARINFO_BEGIN + 21;
	public static final int U_CAR_POWER_VALUE                     		= U_CARINFO_BEGIN + 22;
	public static final int U_CAR_DRIVE_MILES                     		= U_CARINFO_BEGIN + 23;
	public static final int U_CAR_SPEC_SPEED                     		= U_CARINFO_BEGIN + 24;
	public static final int U_CAR_ENGIN_SPEED                     		= U_CARINFO_BEGIN + 25;
	public static final int U_CAR_LIGHT_NEAR                     		= U_CARINFO_BEGIN + 26;
	public static final int U_CAR_LIGHT_STATE1                     		= U_CARINFO_BEGIN + 27;
	public static final int U_CAR_LIGHT_STATE2                     		= U_CARINFO_BEGIN + 28; 
	
    public static final int U_CNT_MAX 									= U_CAR_LIGHT_STATE2 + 5;
	
    

    public static final int C_SET		= 1;
    
    
    int TemCanKey, CanKey = 0xff;
    int KeyCanKeyTable[][] =
        {
            {1, FinalKeyCode.KEY_CODE_VOL_UP},
            {2, FinalKeyCode.KEY_CODE_VOL_DOWN},
            {3, FinalKeyCode.KEY_CODE_MUTE},
            {4, FinalKeyCode.KEY_CODE_NULL},
            {5, FinalKeyCode.KEY_CODE_PHONE},
            {6, FinalKeyCode.KEY_CODE_HANG},
            {7, FinalKeyCode.KEY_CODE_NULL},
            {8, FinalKeyCode.KEY_CODE_PREV},
            {9, FinalKeyCode.KEY_CODE_NEXT},
            {0x0a, FinalKeyCode.KEY_CODE_MODE},                   
        };


	@Override
	public void onHandler(int[] data) {
		int start = 0;
		switch (data[start + 1]) {
		case 0x12: {
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			byte B6 = (byte) data[start + 8];
			byte B7 = (byte) data[start + 9];
			byte B8 = (byte) data[start + 10];
			byte B9 = (byte) data[start + 11];
			HandlerTaskCanbus.update(U_CAR_WATER_TEMP, B2&0xFF);
			HandlerTaskCanbus.update(U_CAR_INSTANT_FUEL_CONSUME, Utils.combine(B3, B4));
			HandlerTaskCanbus.update(U_CAR_GEAR_STATE, B5&0xFF);
			
			HandlerTaskCanbus.update(U_CAR_LOW_FUEL_WARN, 		B6>>7 & 0x01);
			HandlerTaskCanbus.update(U_CAR_LOW_POWER_WARN, 		B6>>6 & 0x01);
			HandlerTaskCanbus.update(U_CAR_BELT_WARN, 			B6>>5 & 0x01);
			HandlerTaskCanbus.update(U_CAR_LIQUID_WARN, 		B6>>4 & 0x01);
			HandlerTaskCanbus.update(U_CAR_MOTOR_OIL_WARN, 		B6>>3 & 0x01);

			HandlerTaskCanbus.update(U_CAR_REMAIN_OIL, B7 & 0xFF);
			HandlerTaskCanbus.update(U_CAR_POWER_VALUE, Utils.combine(B8, B9));
			
			break;
		}
		case 0x13: {
			byte B0 = (byte) data[start + 2];
			byte B1 = (byte) data[start + 3];
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			byte B6 = (byte) data[start + 8];
			byte B7 = (byte) data[start + 9];
			byte B8 = (byte) data[start + 10];
			byte B9 = (byte) data[start + 11];

			HandlerTaskCanbus.update(U_CAR_DRIVE_MILES,		Utils.combine(B0, B1, B2));
			HandlerTaskCanbus.update(U_CAR_SPEC_SPEED,		Utils.combine(B3, B4));
			HandlerTaskCanbus.update(U_CAR_ENGIN_SPEED,		Utils.combine(B8, B9));
			break;
		}
		case 0x18: {
			byte B0 = (byte) data[start + 2];
			byte B1 = (byte) data[start + 3];

			HandlerTaskCanbus.update(U_CAR_LIGHT_STATE1,		B0);
			HandlerTaskCanbus.update(U_CAR_LIGHT_STATE2,		B1);
			
			break;
		}
		case 0x42: {
			byte B0 = (byte) data[start + 2];
			byte B1 = (byte) data[start + 3];
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			byte B6 = (byte) data[start + 8];
			byte B7 = (byte) data[start + 9];
            SendFunc.sendRadar(SendFunc.FL_RARA_RRF, ((B0 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RRMF, ((B1& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RRMR, ((B2& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RRR, ((B3& 0xff) * 10) / 0xff);

            SendFunc.sendRadar(SendFunc.FL_RARA_LF, ((B4& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_LMF, ((B5& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_LMR, ((B6& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_LR, ((B7& 0xff) * 10) / 0xff);
            
			break;
		}
		case 0x47: {
			byte B13 = (byte) data[start + 15];
			HandlerTaskCanbus.update(U_SET_47D137, B13>>7 & 0x01);
			HandlerTaskCanbus.update(U_SET_47D136, B13>>6 & 0x01);
			HandlerTaskCanbus.update(U_SET_47D135, B13>>5 & 0x01);
			break;
		}
		case 0x72: {
			byte B0 = (byte) data[start + 2];
			byte B1 = (byte) data[start + 3];
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			byte B6 = (byte) data[start + 8];
			byte B7 = (byte) data[start + 9];

			byte B8 = (byte) data[start + 10];
			byte B9 = (byte) data[start + 11];
			byte B10 = (byte) data[start + 12];
			byte B11 = (byte) data[start + 13];
			byte B12 = (byte) data[start + 14];
			byte B13 = (byte) data[start + 15];
			
			HandlerTaskCanbus.update(U_BT_ENABLE, B0>>6 &0x01);
			HandlerTaskCanbus.update(U_RADAR_ENABLE, B0>>5 &0x01);
			HandlerTaskCanbus.update(U_KEYIN_ENABLE, B0>>4 &0x01);
			HandlerTaskCanbus.update(U_HANDBRAKE, B0>>3 &0x01);
			HandlerTaskCanbus.update(U_BACKCAR, B0>>2 &0x01);
			HandlerTaskCanbus.update(U_BIGLIGHT, B0>>1 &0x01);
			HandlerTaskCanbus.update(U_ACC_ON, B0 &0x01);
			

			HandlerTaskCanbus.update(U_CAR_SPEED, B1 &0xFF);
			
			CanInfos.onKeyEvent2(KeyCanKeyTable, B2 & 0xFF);
			
			HandlerTaskCanbus.update(U_LIGHT_BRIGHTNESS, B3 &0xFF);
			
			if (DataHost.sBackCar == 1)
				CanInfos.CarBackTrackHandle_LeftAndRight(B4 & 0xFF, B5 & 0xFF);


            SendFunc.sendRadar(SendFunc.FL_RARA_RL, ((B6 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RML, ((B7& 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RMR, ((B8 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RR, ((B9 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_FL, ((B10 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_FML, ((B11 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_FMR, ((B12 & 0xff) * 10) / 0xff);
            SendFunc.sendRadar(SendFunc.FL_RARA_RR, ((B13 & 0xff) * 10) / 0xff);
			
			break; 
		}
		case 0x73: {
			byte B0 = (byte) data[start + 2];
			byte B1 = (byte) data[start + 3];
			byte B2 = (byte) data[start + 4];
			byte B3 = (byte) data[start + 5];
			byte B4 = (byte) data[start + 6];
			byte B5 = (byte) data[start + 7];
			byte B6 = (byte) data[start + 8];
			byte B7 = (byte) data[start + 9];
			
			HandlerTaskCanbus.update(U_SHOW_AIR, B0>>7 & 0x01);
			HandlerTaskCanbus.update(U_AIR_POWER, B0>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AQS, B0>>4 & 0x03);
			HandlerTaskCanbus.update(U_AIR_AUTO, B0>>3 & 0x01);
			HandlerTaskCanbus.update(U_AIR_DUAL, B0>>2 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC_MAX, B0>>1 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AUTO2, B0 & 0x01);

			HandlerTaskCanbus.update(U_AIR_REAR_ENBALE, B1>>7 & 0x01);
			HandlerTaskCanbus.update(U_AIR_AC, B1>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_REAR_DEFROST, B1>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_FRONT_DEFROST, B1>>4 & 0x01);

			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_RIGHT, B1>>2 & 0x03);
			HandlerTaskCanbus.update(U_AIR_SEAT_HEAT_LEFT, B1 & 0x03);
			
			int val = B2&0xFF;
			int upVal = DataCanbus.TEMP_NONE;
			switch (val) { 
			case 0x1:
				upVal = DataCanbus.TEMP_LOW;break;
			case 0xFF:
				upVal = DataCanbus.TEMP_HIGH;break;
			default:
				if(val > 17 && val < 27) {
					upVal = val * 10;
				}
				break;
			}

			HandlerTaskCanbus.update(U_AIR_TEMP_LEFT, upVal);
			
			val = B3&0xFF;
			upVal = DataCanbus.TEMP_NONE;
			switch (val) { 
			case 0x1:
				upVal = DataCanbus.TEMP_LOW;break;
			case 0xFF:
				upVal = DataCanbus.TEMP_HIGH;break;
			default:
				if(val > 17 && val < 27) {
					upVal = val * 10;
				}
				break;
			}
			HandlerTaskCanbus.update(U_AIR_TEMP_RIGHT, upVal);

			HandlerTaskCanbus.update(U_AIR_BLOW_FOOT, B4>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_BODY, B4>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_BLOW_WIN,  B4>>4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL,  B4 & 0x0F);


			HandlerTaskCanbus.update(U_AIR_RIGHT_BLOW_FOOT, B5>>6 & 0x01);
			HandlerTaskCanbus.update(U_AIR_RIGHT_BLOW_BODY, B5>>5 & 0x01);
			HandlerTaskCanbus.update(U_AIR_RIGHT_BLOW_WIN,  B5>>4 & 0x01);
			HandlerTaskCanbus.update(U_AIR_WIND_LEVEL_RIGHT,  B5 & 0x0F);
			
			
			 val = B6 & 0xFF;
             int tempature = 0;
             tempature = 1000 + (val * 5) - 400;
             SendFunc.sendOutTemp(1, tempature); 
			

 			HandlerTaskCanbus.update(U_DOOR_FL,  B7>>7 & 0x01);
 			HandlerTaskCanbus.update(U_DOOR_FR,  B7>>6 & 0x01);
 			HandlerTaskCanbus.update(U_DOOR_RL,  B7>>5 & 0x01);
 			HandlerTaskCanbus.update(U_DOOR_RR,  B7>>4 & 0x01);
 			HandlerTaskCanbus.update(U_DOOR_BACK,  B7>>3 & 0x01);
 			HandlerTaskCanbus.update(U_DOOR_ENGINE,  B7>>2 & 0x01);
             
 			HandlerTaskCanbus.update(U_DOOR_ENBALE,  B7 & 0x01);
        	break;
		}
//		case 0xF0: {
//			break;
//		}
		}
	}

	@Override
	public void in() {
//		0xD2;
//		0xE2;
//		0xE3;
//		0xE4;
//		0xE5;

        EventNotify.NE_APPID.addNotify(N_APPID, 1);
        EventNotify.NE_GPS_ANGLE.addNotify(N_NAVI_INFO, 1);
	}

	@Override
	public void out() {
        EventNotify.NE_APPID.removeNotify(N_APPID); 
        EventNotify.NE_GPS_ANGLE.removeNotify(N_NAVI_INFO);
	}

    private float bear = 0;
    private int Old_bear = 0xff;
    private int New_bear = 0xff;
    private IUiNotify N_NAVI_INFO = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (true) {
                bear = DataHost.sGpsAngle;
                if ((bear <= 22.5 && bear >= 0) || (bear >= 360.0 - 22.5 && bear <= 360)) {//north
                    New_bear = 0x07;
                } else if (bear > 22.5 && bear < 22.5 + 45) {//northeast
                    New_bear = 0x08;
                } else if (bear >= 67.5 && bear <= 90 + 22.5) {//east
                    New_bear = 0x01;
                } else if (bear > 90 + 22.5 && bear < 135 + 22.5) {//southeast
                    New_bear = 0x02;
                } else if (bear >= 135 + 22.5 && bear <= 180 + 22.5) {//south
                    New_bear = 0x03;
                } else if (bear > 180 + 22.5 && bear < 225 + 22.5) {//southwest
                    New_bear = 0x04;
                } else if (bear >= 225 + 22.5 && bear <= 270 + 22.5) {//west
                    New_bear = 0x05;
                } else if (bear > 270 + 22.5 && bear < 315 + 22.5) {//northwest
                    New_bear = 0x06;
                } else
                    New_bear = 0x03;
                if (New_bear != Old_bear) {
                    SendFunc.send2Canbus(0xe4, 0, 0,
                            0, 0, 0, 0,
                            0,
                            0, 0, 0, 0,
                            New_bear,
                            0x00, 0x00,
                            0, 0,
                            0,
                            0x00, 0x00);
                    Old_bear = New_bear;
                }
            }
        }
    };
	private static final IUiNotify N_APPID = new IUiNotify() {
		
		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			
		}
	};

	static int CarDisSourceIdGet() {
		int sourceid;

		sourceid=0x00;
		switch(DataHost.sAppid)
			{
			case FinalMain.APP_ID_TV://			1
				sourceid=0x08;
				break;
				
			case FinalMain.APP_ID_DVD://			2
//				if(DataDvd.sDeviceType == FinalDvd.DEVICE_TYPE_DISC){
					sourceid=0x06;
//					}
//				else {
//					sourceid=0x0d;
//					}
				break;
				
			    //case SYS_ID_CDC://			3
				//sourceid=0x06;
				//break;
			
			case FinalMain.APP_ID_IPOD://		4//IPOD
				sourceid=0x0b;
				break;
			
			case FinalMain.APP_ID_AUX://			5//AUX
				sourceid=0x0c;
				break;	

			case FinalMain.APP_ID_RADIO:
				// band
				if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x01;
				} else if (1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x02;
				} else if (2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
					sourceid = 0x03;
				} else if (0 == (DataHost.sRadioBand - FinalRadio.BAND_AM_INDEX_BEGIN)) {
					sourceid = 0x04;
				} else if (1 == (DataHost.sRadioBand - FinalRadio.BAND_AM_INDEX_BEGIN)) {
					sourceid = 0x05;
				}

				break;			
			
//			case SYS_ID_RADAR://		7
//				sourceid=0x10;
//				break;
			
			case FinalMain.APP_ID_BTPHONE://	8
				sourceid=0x0a;
				break;
			
			case FinalMain.APP_ID_BTAV://	11//蓝牙音乐
				sourceid=0x0a;
				break;
			
//			case SYS_ID_GPS://			12
//				sourceid=0x09;
//				break;
			
//			case SYS_ID_AIR://			13
//				//sourceid=0x08;
//				break;
			
			case FinalMain.APP_ID_NULL:// 		14	
				sourceid=0x09;
			
				break;
			case FinalMain.APP_ID_THIRD_PLAYER:
			case FinalMain.APP_ID_AUDIO_PLAYER://			15
				sourceid=0x0d;
				break;
			
//			case SYS_ID_MP5://			16
//				//sourceid=0x08;
//				break;		
			
			case FinalMain.APP_ID_DVR://			17
				//sourceid=0x08;
				break;		

			case FinalMain.APP_ID_CAR_RADIO://		18// 原车收音，2013.10.19 Add
				//sourceid=0x08;
				break;
			
			case FinalMain.APP_ID_CAR_BTPHONE://		19// 原车蓝牙，2013.10.19 Add
				sourceid=0xfe;
				break;
			
			case FinalMain.APP_ID_CAR_USB://		20// 原车USB,  2013.10.19 Add
				sourceid=0xff;
				break;

			default:
				break;
			}
	/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
	/* <2016.3.2.01  tlm> */
//		if(CanBackDetFlag || CanRadarDetCurFlag)
		if(DataHost.sBackCar == 1)
			{// 不管任何情况下倒车，都要发送显示"CAREMA"否则协议盒会关闭视频
			sourceid=0x10;// Carmera
			}
//		else if(bPower_flag==0 || Enter_SystemFlag==0)
//			{// 开机并且进系统才显示基本信息
//			sourceid=0x00;// off
//			}

		return sourceid;
	}
	

	
	static void CarDisNormal()
	{	
		int [] cmds = new int[15];
		byte i;
		int charlong;
		int temp16;
		
		for(int a = 0; a<cmds.length;a++)
			cmds[a] = ' ';
		
		cmds[0]=0x0d;// leng
		cmds[1]=0xd2;// fid
		cmds[2]=CarDisSourceIdGet();// Source Id
	/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
	/* <2016.3.2.02  tlm> */
//		if(bPower_flag && Enter_SystemFlag && !CanBackDetFlag && !CanRadarDetCurFlag)
			switch(DataHost.sAppid)
				{
				case FinalMain.APP_ID_TV://			1
//					if(DataTv.sTvType==FinalTv.TV_TYPE_ANALOG)
//						{
//						// tv channel
//						if((DataTv.sChannel/10)>0)
//							{
//								cmds[3]= DataTv.sChannel/10+'0';
//							}
//							cmds[4]= DataTv.sChannel%10+'0';
//
//							cmds[5]= '-';
//
//						// tv freq
//						charlong=DataTv.sFreq/100;
//						if((charlong/10000)>0)
//							{
//							cmds[6]= charlong/10000+'0';
//							}
//						cmds[7]= (charlong%10000)/1000+'0';
//						cmds[8]= (charlong%1000)/100+'0';
//						cmds[9]='.';
//						cmds[10]= (charlong%100)/10+'0';
//						cmds[11]= (charlong%10)/1+'0';
//
//						cmds[12]= 'M';
//						cmds[13]= 'h';
//						cmds[14]= 'z';
//						}
					break;
					
				case FinalMain.APP_ID_DVD://			2
//					if(DataDvd.sTotalTrack==0)break;// 没曲目信息时，就不要显示曲目了
//					/**********************************************************
//					在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式
//					Txx mm:ss
//					
//					Date:2014.5.30 
//					author:tang limin
//					***********************************************************/
//					cmds[3]= 'T';//在当前曲目数之前加个大写的"T"
//
//
//					/*********************************************************
//					曲目显示 只显示当前曲目4位  由于空间问题总曲目不显示
//					
//					Date:2014.5.30 
//					author:tang limin
//					**********************************************************/
//					temp16=DataDvd.sPlayTrack%10000;
//					
//					cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
//					cmds[5] = ((temp16 % 100)/10 == 0 ? (temp16 % 1000)+ '0' : (temp16 % 1000)/10 + '0');
//					cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 % 100) / 10 + '0') ;
//					cmds[7] = temp16 % 10 + '0';
//			
//					temp16=DataDvd.sPlayTime/60;
//					temp16%=60;
//					cmds[8] = ' ';
//					cmds[9] =temp16/10 + '0';//当前曲目 
//					cmds[10] =temp16%10 + '0';//当前曲目 
//					cmds[11] = ' ';
//					temp16=DataDvd.sPlayTime;
//					temp16%=60;
//					cmds[12] =temp16/10 + '0';//当前曲目 
//					cmds[13] =temp16%10 + '0';//当前曲目 
					break;
					
//				case SYS_ID_CDC://			3
//					break;
				
				case FinalMain.APP_ID_IPOD://		4//IPOD
					break;
				
				case FinalMain.APP_ID_AUX://			5//AUX
					break;	

				case FinalMain.APP_ID_RADIO:
					// radio freq
					int freq = DataHost.sRadioFreq;
				//	Log.d("LG", "sBand = "+(DataRadio.sBand -FinalRadio.BAND_FM_INDEX_BEGIN)+" sFreq="+freq);
					if(0 == (DataHost.sRadioBand -FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 1 == (DataHost.sRadioBand -FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 2 == (DataHost.sRadioBand -FinalRadio.BAND_FM_INDEX_BEGIN))
						{
						cmds[3]= (DataHost.sRadioFreq/10000==0?DataHost.sRadioFreq/10000+' ': DataHost.sRadioFreq/10000+'0');
						cmds[4]= (DataHost.sRadioFreq%10000)/1000+'0';
						cmds[5]= (DataHost.sRadioFreq%1000)/100+'0';
						cmds[6]= '.';
						cmds[7]= (DataHost.sRadioFreq%100)/10+'0';
						cmds[8]= (DataHost.sRadioFreq%10)+'0';
						cmds[9]= 'M';
						cmds[10]= 'h';
						cmds[11]= 'z';
						}
					else
						{
						cmds[3]= (DataHost.sRadioFreq/10000==0?DataHost.sRadioFreq/10000+' ': DataHost.sRadioFreq/10000+'0');
						cmds[4]= ((DataHost.sRadioFreq%10000)/1000==0?(DataHost.sRadioFreq%10000)/1000+' ': (DataHost.sRadioFreq%10000)/1000+'0');
						cmds[5]= (DataHost.sRadioFreq%1000)/100+'0';
						cmds[6]= (DataHost.sRadioFreq%100)/10+'0';
						cmds[7]= (DataHost.sRadioFreq%10)+'0';
						cmds[8]= 'K';
						cmds[9]= 'h';
						cmds[10]= 'z';
						}			
					break;			
				
//				case SYS_ID_RADAR://		7
//					break;
				
				case FinalMain.APP_ID_BTPHONE://	8
					break;
				
				case FinalMain.APP_ID_BTAV://	11//蓝牙音乐
					break;
				
//				case SYS_ID_GPS://			12
//					break;
				
//				case SYS_ID_AIR://			13
//					break;
				
				case FinalMain.APP_ID_NULL:// 		14	
					break;
//				case SYS_ID_Res_1:
				case FinalMain.APP_ID_VIDEO_PLAYER:
				case FinalMain.APP_ID_AUDIO_PLAYER://			15
					if(DataHost.sPlayTotal==0)break;// 没曲目信息时，就不要显示曲目了
					/**********************************************************
					在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式
					Txx mm:ss
					
					Date:2014.5.30 
					author:tang limin
					***********************************************************/
					cmds[3]= 'T';//在当前曲目数之前加个大写的"T"


					/*********************************************************
					曲目显示 只显示当前曲目4位  由于空间问题总曲目不显示
					
					Date:2014.5.30 
					author:tang limin
					**********************************************************/
					temp16=DataHost.sCurPlayIndex%10000;
					
					cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
					cmds[5] = ((temp16 % 100)/10 == 0 ? (temp16 % 1000)+ '0' : (temp16 % 1000)/10 + '0');
					cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 % 100) / 10 + '0') ;
					cmds[7] = temp16 % 10 + '0';
			
					temp16=DataHost.sCurPlayTime/60;
					temp16%=60;
					cmds[8] = ' ';
					cmds[9] =temp16/10 + '0';//当前曲目 
					cmds[10] =temp16%10 + '0';//当前曲目 
					cmds[11] = ' ';
					temp16=DataHost.sCurPlayTime;
					temp16%=60;
					cmds[12] =temp16/10 + '0';//当前曲目 
					cmds[13] =temp16%10 + '0';//当前曲目 

					break;
				
//				case SYS_ID_MP5://			16
//					break;		
				
				case FinalMain.APP_ID_DVR://			17
					break;		

				case FinalMain.APP_ID_CAR_RADIO://		18// 原车收音，2013.10.19 Add
					break;
				
				case FinalMain.APP_ID_CAR_BTPHONE://		19// 原车蓝牙，2013.10.19 Add
					break;
				
				case FinalMain.APP_ID_CAR_USB://		20// 原车USB,  2013.10.19 Add
					break;

				default:
					break;
				}

//		WeiChi_1_ArmDataToCan(15,cmds);
		int [] data = new int[16];
		data[0] = 0xE3;
		
		int len = data.length > 0x0f ? 0x0f : data.length;
		
		for (int t = 0; t < len; t++) {
			data [1 + t] = (byte)cmds[t];
		}

        int[] canData = new int[cmds.length - 2];
        System.arraycopy(cmds, 2, canData, 0, canData.length);
		SendFunc.send2Canbus(0xD2, canData ); 
	}
	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {
		 switch (cmd) {
         case C_SET:
             if (ints != null && ints.length > 1) {
                 int d[] = new int[ints.length - 1];
                 System.arraycopy(ints, 1, d, 0, ints.length - 1);
                 SendFunc.send2Canbus(ints[0], d);
             }
             break;
		 }
	};
}
