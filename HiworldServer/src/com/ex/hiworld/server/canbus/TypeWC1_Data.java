package com.ex.hiworld.server.canbus;

import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.SendFunc;

import android.util.Log;

public class TypeWC1_Data {

	static int Vol_dis_cnt = 0;

	static byte CarDisSourceIdGet() {
		byte sourceid;

		sourceid = 0x00;
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			sourceid = 0x08;
			break;

		case FinalMain.APP_ID_DVD:// 2
			// if(DataDvd.sDeviceType == FinalDvd.DEVICE_TYPE_DISC)
			// {
			// sourceid=0x07;
			// }
			// else if(DataDvd.sDeviceType == FinalDvd.DEVICE_TYPE_SD)
			// {
			// sourceid=0x0e;
			// }
			// else if(DataDvd.sDeviceType == FinalDvd.DEVICE_TYPE_USB)
			// {
			// sourceid=0x0d;
			// }
			break;

		// case SYS_ID_CDC:// 3
		// sourceid=0x06;
		// break;

		case FinalMain.APP_ID_IPOD:// 4//IPOD
			sourceid = 0x0b;
			break;

		case FinalMain.APP_ID_AUX:// 5//AUX
			sourceid = 0x0c;
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

		// case SYS_ID_RADAR:// 7
		// sourceid=0x10;
		// break;

		case FinalMain.APP_ID_BTPHONE:// 8
			sourceid = 0x0a;
			break;

		case FinalMain.APP_ID_BTAV:// 11//蓝牙音乐
			sourceid = 0x0a;
			break;

		// case SYS_ID_GPS:// 12
		// sourceid=0x09;
		// break;

		// case SYS_ID_AIR:// 13
		// //sourceid=0x08;
		// break;

		case FinalMain.APP_ID_NULL:// 14
			sourceid = 0x09;

			break;
		case FinalMain.APP_ID_THIRD_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:// 15
			sourceid = 0x0d;
			break;

		// case SYS_ID_MP5:// 16
		// //sourceid=0x08;
		// break;

		case FinalMain.APP_ID_DVR:// 17
			// sourceid=0x08;
			break;

		case FinalMain.APP_ID_CAR_RADIO:// 18// 原车收音
			// sourceid=0x08;
			break;

		case FinalMain.APP_ID_CAR_BTPHONE:// 19// 原车蓝牙
			// sourceid=0x08;
			break;

		case FinalMain.APP_ID_CAR_USB:// 20// 原车USB, 2013.10.19 Add
			// sourceid=0x08;
			break;

		default:
			break;
		}
		/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
		/* <2016.3.2.01 tlm> */
		// if(CanBackDetFlag || CanRadarDetCurFlag)
		if (DataHost.sBackCar == 1) {// 不管任何情况下倒车，都要发送显示"CAREMA"否则协议盒会关闭视频
			sourceid = 0x10;// Carmera
		}
		// else if(bPower_flag==0 || Enter_SystemFlag==0)
		// {// 开机并且进系统才显示基本信息
		// sourceid=0x00;// off
		// }

		return sourceid;
	}

	static void CarDisNormal() {
		int[] cmds = new int[15];
		byte i;
		int charlong;
		int temp16;

		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x0d;// leng
		cmds[1] = 0xd2;// fid
		cmds[2] = CarDisSourceIdGet();// Source Id
		/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
		/* <2016.3.2.02 tlm> */
		// if(bPower_flag && Enter_SystemFlag && !CanBackDetFlag && !CanRadarDetCurFlag)
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			// if(DataTv.sTvType==FinalTv.TV_TYPE_ANALOG)
			// {
			// // tv channel
			// if((DataTv.sChannel/10)>0)
			// {
			// cmds[3]= DataTv.sChannel/10+'0';
			// }
			// cmds[4]= DataTv.sChannel%10+'0';
			//
			// cmds[5]= '-';
			//
			// // tv freq
			// charlong=DataTv.sFreq/100;
			// if((charlong/10000)>0)
			// {
			// cmds[6]= charlong/10000+'0';
			// }
			// cmds[7]= (charlong%10000)/1000+'0';
			// cmds[8]= (charlong%1000)/100+'0';
			// cmds[9]='.';
			// cmds[10]= (charlong%100)/10+'0';
			// cmds[11]= (charlong%10)/1+'0';
			//
			// cmds[12]= 'M';
			// cmds[13]= 'h';
			// cmds[14]= 'z';
			// }
			break;

		case FinalMain.APP_ID_DVD:// 2
			// if(DataDvd.sTotalTrack==0)break;// 没曲目信息时，就不要显示曲目了
			// /**********************************************************
			// 在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式
			// Txx mm:ss
			//
			// Date:2014.5.30
			// author:tang limin
			// ***********************************************************/
			// cmds[3]= 'T';//在当前曲目数之前加个大写的"T"
			//
			//
			// /*********************************************************
			// 曲目显示 只显示当前曲目4位 由于空间问题总曲目不显示
			//
			// Date:2014.5.30
			// author:tang limin
			// **********************************************************/
			// temp16=DataDvd.sPlayTrack%10000;
			//
			// cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
			// cmds[5] = ((temp16 % 100)/10 == 0 ? (temp16 % 1000)+ '0' : (temp16 % 1000)/10
			// + '0');
			// cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 %
			// 100) / 10 + '0') ;
			// cmds[7] = temp16 % 10 + '0';
			//
			// temp16=DataDvd.sPlayTime/60;
			// temp16%=60;
			// cmds[8] = ' ';
			// cmds[9] =temp16/10 + '0';//当前曲目
			// cmds[10] =temp16%10 + '0';//当前曲目
			// cmds[11] = ' ';
			// temp16=DataDvd.sPlayTime;
			// temp16%=60;
			// cmds[12] =temp16/10 + '0';//当前曲目
			// cmds[13] =temp16%10 + '0';//当前曲目

			break;

		// case SYS_ID_CDC:// 3
		// break;

		case FinalMain.APP_ID_IPOD:// 4//IPOD
			break;

		case FinalMain.APP_ID_AUX:// 5//AUX
			break;

		case FinalMain.APP_ID_RADIO:
			// radio freq
			int freq = DataHost.sRadioFreq;
			// Log.d("LG", "sBand = "+(DataHost.iRadioBand
			// -FinalRadio.BAND_FM_INDEX_BEGIN)+" sFreq="+freq);
			if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
				cmds[3] = (DataHost.sRadioFreq / 10000 == 0 ? DataHost.sRadioFreq / 10000 + ' '
						: DataHost.sRadioFreq / 10000 + '0');
				cmds[4] = (DataHost.sRadioFreq % 10000) / 1000 + '0';
				cmds[5] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[6] = '.';
				cmds[7] = (DataHost.sRadioFreq % 100) / 10 + '0';
				cmds[8] = (DataHost.sRadioFreq % 10) + '0';
				cmds[9] = 'M';
				cmds[10] = 'h';
				cmds[11] = 'z';
			} else {
				cmds[3] = (DataHost.sRadioFreq / 10000 == 0 ? DataHost.sRadioFreq / 10000 + ' '
						: DataHost.sRadioFreq / 10000 + '0');
				cmds[4] = ((DataHost.sRadioFreq % 10000) / 1000 == 0 ? (DataHost.sRadioFreq % 10000) / 1000 + ' '
						: (DataHost.sRadioFreq % 10000) / 1000 + '0');
				cmds[5] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[6] = (DataHost.sRadioFreq % 100) / 10 + '0';
				cmds[7] = (DataHost.sRadioFreq % 10) + '0';
				cmds[8] = 'K';
				cmds[9] = 'h';
				cmds[10] = 'z';
			}
			break;

		// case SYS_ID_RADAR:// 7
		// break;

		case FinalMain.APP_ID_BTPHONE:// 8
			break;

		case FinalMain.APP_ID_BTAV:// 11//��������
			break;

		// case SYS_ID_GPS:// 12
		// break;

		// case SYS_ID_AIR:// 13
		// break;

		case FinalMain.APP_ID_NULL:// 14
			break;
		// case SYS_ID_Res_1:
		case FinalMain.APP_ID_VIDEO_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:// 15
			if (DataHost.sPlayTotal == 0)
				break;// 没曲目信息时，就不要显示曲目了
			/**********************************************************
			 * 在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式 Txx mm:ss
			 * 
			 * Date:2014.5.30 author:tang limin
			 ***********************************************************/
			cmds[3] = 'T';// 在当前曲目数之前加个大写的"T"

			/*********************************************************
			 * 曲目显示 只显示当前曲目4位 由于空间问题总曲目不显示
			 * 
			 * Date:2014.5.30 author:tang limin
			 **********************************************************/

			temp16 = DataHost.sCurPlayIndex % 10000;

			cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
			cmds[5] = ((temp16 % 100) / 10 == 0 ? (temp16 % 1000) + '0' : (temp16 % 1000) / 10 + '0');
			cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 % 100) / 10 + '0');
			cmds[7] = temp16 % 10 + '0';

			temp16 = DataHost.sCurPlayTime / 60;
			temp16 %= 60;
			cmds[8] = ' ';
			cmds[9] = temp16 / 10 + '0';// 当前曲目
			cmds[10] = temp16 % 10 + '0';// 当前曲目
			cmds[11] = ' ';
			temp16 = DataHost.sCurPlayTime;
			temp16 %= 60;
			cmds[12] = temp16 / 10 + '0';// 当前曲目
			cmds[13] = temp16 % 10 + '0';// 当前曲目

			break;

		// case SYS_ID_MP5:// 16
		// break;

		case FinalMain.APP_ID_DVR:// 17
		case FinalMain.APP_ID_CAR_RADIO://
		case FinalMain.APP_ID_CAR_BTPHONE://
		case FinalMain.APP_ID_CAR_USB://
		default:
			break;
		}

		// WeiChi_1_ArmDataToCan(15,cmds);
		// int [] data = new int[16];
		// data[0] = 0xE3;
		//
		// int len = data.length > 0x0f ? 0x0f : data.length;
		//
		// for (int t = 0; t < len; t++) {
		// data [1 + t] = (byte)cmds[t];
		// }

		int[] candata = new int[cmds.length - 2];

		System.arraycopy(cmds, 2, candata, 0, candata.length);
		SendFunc.send2Canbus(0xD2, candata);
	}

	static void CarDis91Normal() {
		int[] cmds = new int[15];
		byte i;
		int charlong;
		int temp16;

		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x0d;// leng
		cmds[1] = 0x91;// fid
		cmds[2] = CarDisSourceIdGet();// Source Id
		/* 这里只判断倒车不判断雷达，因为在有雷达开关的车上倒车之后雷达标志一直为1，如果判断这个标志小屏显示不对了 */
		/* <2016.3.2.02 tlm> */
		// if(bPower_flag && Enter_SystemFlag && !CanBackDetFlag && !CanRadarDetCurFlag)
		switch (DataHost.sAppid) {
		case FinalMain.APP_ID_TV:// 1
			// if(DataTv.sTvType==FinalTv.TV_TYPE_ANALOG)
			// {
			// // tv channel
			// if((DataTv.sChannel/10)>0)
			// {
			// cmds[3]= DataTv.sChannel/10+'0';
			// }
			// cmds[4]= DataTv.sChannel%10+'0';
			//
			// cmds[5]= '-';
			//
			// // tv freq
			// charlong=DataTv.sFreq/100;
			// if((charlong/10000)>0)
			// {
			// cmds[6]= charlong/10000+'0';
			// }
			// cmds[7]= (charlong%10000)/1000+'0';
			// cmds[8]= (charlong%1000)/100+'0';
			// cmds[9]='.';
			// cmds[10]= (charlong%100)/10+'0';
			// cmds[11]= (charlong%10)/1+'0';
			//
			// cmds[12]= 'M';
			// cmds[13]= 'h';
			// cmds[14]= 'z';
			// }
			break;

		case FinalMain.APP_ID_DVD:// 2
			// if(DataDvd.sTotalTrack==0)break;// 没曲目信息时，就不要显示曲目了
			// /**********************************************************
			// 在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式
			// Txx mm:ss
			//
			// Date:2014.5.30
			// author:tang limin
			// ***********************************************************/
			// cmds[3]= 'T';//在当前曲目数之前加个大写的"T"
			//
			//
			// /*********************************************************
			// 曲目显示 只显示当前曲目4位 由于空间问题总曲目不显示
			//
			// Date:2014.5.30
			// author:tang limin
			// **********************************************************/
			// temp16=DataDvd.sPlayTrack%10000;
			//
			// cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
			// cmds[5] = ((temp16 % 100)/10 == 0 ? (temp16 % 1000)+ '0' : (temp16 % 1000)/10
			// + '0');
			// cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 %
			// 100) / 10 + '0') ;
			// cmds[7] = temp16 % 10 + '0';
			//
			// temp16=DataDvd.sPlayTime/60;
			// temp16%=60;
			// cmds[8] = ' ';
			// cmds[9] =temp16/10 + '0';//当前曲目
			// cmds[10] =temp16%10 + '0';//当前曲目
			// cmds[11] = ' ';
			// temp16=DataDvd.sPlayTime;
			// temp16%=60;
			// cmds[12] =temp16/10 + '0';//当前曲目
			// cmds[13] =temp16%10 + '0';//当前曲目

			break;

		// case SYS_ID_CDC:// 3
		// break;

		case FinalMain.APP_ID_IPOD:// 4//IPOD
			break;

		case FinalMain.APP_ID_AUX:// 5//AUX
			break;

		case FinalMain.APP_ID_RADIO:
			// radio freq
			int freq = DataHost.sRadioFreq;
			// Log.d("LG", "sBand = "+(DataHost.iRadioBand
			// -FinalRadio.BAND_FM_INDEX_BEGIN)+" sFreq="+freq);
			if (0 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 1 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)
					|| 2 == (DataHost.sRadioBand - FinalRadio.BAND_FM_INDEX_BEGIN)) {
				cmds[3] = (DataHost.sRadioFreq / 10000 == 0 ? DataHost.sRadioFreq / 10000 + ' '
						: DataHost.sRadioFreq / 10000 + '0');
				cmds[4] = (DataHost.sRadioFreq % 10000) / 1000 + '0';
				cmds[5] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[6] = '.';
				cmds[7] = (DataHost.sRadioFreq % 100) / 10 + '0';
				cmds[8] = (DataHost.sRadioFreq % 10) + '0';
				cmds[9] = 'M';
				cmds[10] = 'h';
				cmds[11] = 'z';
			} else {
				cmds[3] = (DataHost.sRadioFreq / 10000 == 0 ? DataHost.sRadioFreq / 10000 + ' '
						: DataHost.sRadioFreq / 10000 + '0');
				cmds[4] = ((DataHost.sRadioFreq % 10000) / 1000 == 0 ? (DataHost.sRadioFreq % 10000) / 1000 + ' '
						: (DataHost.sRadioFreq % 10000) / 1000 + '0');
				cmds[5] = (DataHost.sRadioFreq % 1000) / 100 + '0';
				cmds[6] = (DataHost.sRadioFreq % 100) / 10 + '0';
				cmds[7] = (DataHost.sRadioFreq % 10) + '0';
				cmds[8] = 'K';
				cmds[9] = 'h';
				cmds[10] = 'z';
			}
			break;

		// case SYS_ID_RADAR:// 7
		// break;

		case FinalMain.APP_ID_BTPHONE:// 8
			break;

		case FinalMain.APP_ID_BTAV:// 11//��������
			break;

		// case SYS_ID_GPS:// 12
		// break;

		// case SYS_ID_AIR:// 13
		// break;

		case FinalMain.APP_ID_NULL:// 14
			break;
		// case SYS_ID_Res_1:
		case FinalMain.APP_ID_VIDEO_PLAYER:
		case FinalMain.APP_ID_AUDIO_PLAYER:// 15
			if (DataHost.sPlayTotal == 0)
				break;// 没曲目信息时，就不要显示曲目了
			/**********************************************************
			 * 在威驰调试大众协议，因为原车小屏显示只有一行，所以建议将曲目的小屏显示改为以下格式 Txx mm:ss
			 * 
			 * Date:2014.5.30 author:tang limin
			 ***********************************************************/
			cmds[3] = 'T';// 在当前曲目数之前加个大写的"T"

			/*********************************************************
			 * 曲目显示 只显示当前曲目4位 由于空间问题总曲目不显示
			 * 
			 * Date:2014.5.30 author:tang limin
			 **********************************************************/

			temp16 = DataHost.sCurPlayIndex % 10000;

			cmds[4] = (temp16 / 1000 == 0 ? temp16 / 1000 + '0' : temp16 / 1000 + '0');
			cmds[5] = ((temp16 % 100) / 10 == 0 ? (temp16 % 1000) + '0' : (temp16 % 1000) / 10 + '0');
			cmds[6] = ((temp16 % 100) / 10 == 0 ? (temp16 % 100) / 10 + '0' : (temp16 % 100) / 10 + '0');
			cmds[7] = temp16 % 10 + '0';

			temp16 = DataHost.sCurPlayTime / 60;
			temp16 %= 60;
			cmds[8] = ' ';
			cmds[9] = temp16 / 10 + '0';
			cmds[10] = temp16 % 10 + '0';
			cmds[11] = ' ';
			temp16 = DataHost.sCurPlayTime;
			temp16 %= 60;
			cmds[12] = temp16 / 10 + '0';
			cmds[13] = temp16 % 10 + '0';

			break;

		// case SYS_ID_MP5:// 16
		// break;

		case FinalMain.APP_ID_DVR:// 17
		case FinalMain.APP_ID_CAR_RADIO://
		case FinalMain.APP_ID_CAR_BTPHONE://
		case FinalMain.APP_ID_CAR_USB://
		default:
			break;
		}

		// WeiChi_1_ArmDataToCan(15,cmds);
		// int [] data = new int[16];
		// data[0] = 0xE3;
		//
		// int len = data.length > 0x0f ? 0x0f : data.length;
		//
		// for (int t = 0; t < len; t++) {
		// data [1 + t] = (byte)cmds[t];
		// }
		// ToolkitDev.writeMcu(data);
		//

		int[] candata = new int[cmds.length - 2];
		System.arraycopy(cmds, 2, candata, 0, candata.length);
		SendFunc.send2Canbus(0x91, candata);
	}

	public static int CarBackTrackHandle(int data0, int data1) {
		int t1;
		// if(DataCustomer.sCustomerId == FinalCustomer.CUSTOMER_ID_QLTC){
		// if(data0!=0x00)// left
		// {
		// if(data0>132)
		// {
		// t1 = 0;
		// }
		// else
		// {
		// t1=(byte) (35-data0/4);
		// }
		// }
		// else if(data1!=0x00)// right
		// {
		// if(data1 > 132)
		// {
		// t1 = 70;
		// }
		// else
		// {
		// t1=data1/4;
		// t1 = t1+35;
		// }
		// }
		// else
		// {
		// t1=35;
		// }
		//
		// return t1;
		// }
		// else{
		// �����ͻ�ֻ��Ҫת����0-40��
		if (data0 != 0x00)// left
		{
			if (data0 >= 134) {
				t1 = 0;
			} else {
				t1 = 20 - data0 / 7;
			}
		} else if (data1 != 0x00)// right
		{
			if (data1 >= 134) {
				t1 = 40;
			} else {
				t1 = data1 / 7;
				t1 = t1 + 20;
			}
		} else {
			t1 = 20;
		}

		return t1;

		// }
	}

	public static int CarBackTrackHandle2(int data0, int data1) {
		int t1;
		int temp = 0;
		int flag = 0;
		temp = (data0 << 8 & 0xff00) | data1;

		if ((temp & 0x8000) != 0) {// left
			temp = (0xffff - temp) & 0xffff;
			flag = 1;
		} else {
			flag = 0;
		}
		// if (DataCanbus.mTrackType == DataCanbus.TRACK_TYPE_70) {
		// t1 = temp / 14; // 540 35
		// if (t1 > 35)
		// t1 = 35;
		//
		// if (flag != 0) {
		// t1 = 35 - t1;
		// } else {
		// t1 = 35 + t1;
		// }
		// } else {
		t1 = temp / 25; // 540 20

		if (t1 > 25)
			t1 = 25;
		if (flag != 0) {
			t1 = 20 - t1;
		} else {
			t1 = 20 + t1;
		}
		// }
		return t1;
	}

	static void CarDisVolume() {
		int[] cmds = new int[15];

		Log.d("LG", "CarDisVolume");
		Vol_dis_cnt = 4;
		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x0d;// leng
		cmds[1] = 0xd2;// fid
		cmds[2] = CarDisSourceIdGet();// Source Id

		if (DataHost.sMuteSrc == 1 || DataHost.sVolDst == 0) {
			cmds[3] = 'M';
			cmds[4] = 'U';
			cmds[5] = 'T';
			cmds[6] = 'E';
		} else {
			cmds[3] = 'V';
			cmds[4] = 'O';
			cmds[5] = 'L';
			if (DataHost.sVolDst >= 10) {
				cmds[7] = DataHost.sVolDst / 10 + '0';
			}

			cmds[8] = DataHost.sVolDst % 10 + '0';
		}

		// int[] data = new int[16];
		// data[0] = 0xE3;
		// int len = data.length > 0x0f ? 0x0f : data.length;
		// for (int t = 0; t < len; t++) {
		// data[1 + t] = (byte) cmds[t];
		// }
		// ToolkitDev.writeMcu(data);

		int[] candata = new int[cmds.length - 2];
		System.arraycopy(cmds, 2, candata, 0, candata.length);
		SendFunc.send2Canbus(0xd2, candata);
	}

	static void CarDis91Volume() {
		int[] cmds = new int[15];

		Log.d("LG", "CarDisVolume");
		Vol_dis_cnt = 4;
		for (int a = 0; a < cmds.length; a++)
			cmds[a] = ' ';

		cmds[0] = 0x0d;// leng
		cmds[1] = 0x91;// fid
		cmds[2] = CarDisSourceIdGet();// Source Id

		if (DataHost.sMuteSrc == 1 || DataHost.sVolDst == 0) {
			cmds[3] = 'M';
			cmds[4] = 'U';
			cmds[5] = 'T';
			cmds[6] = 'E';
		} else {
			cmds[3] = 'V';
			cmds[4] = 'O';
			cmds[5] = 'L';
			if (DataHost.sVolDst >= 10) {
				cmds[7] = DataHost.sVolDst / 10 + '0';
			}

			cmds[8] = DataHost.sVolDst % 10 + '0';
		}

		// int [] data = new int[16];
		// data[0] = 0xE3;
		//
		// int len = data.length > 0x0f ? 0x0f : data.length;
		//
		// for (int t = 0; t < len; t++) {
		// data [1 + t] = (byte)cmds[t];
		// }
		// ToolkitDev.writeMcu(data);
		int[] candata = new int[cmds.length - 2];
		System.arraycopy(cmds, 2, candata, 0, candata.length);
		SendFunc.send2Canbus(0x91, candata);
	}

	public static Runnable mCarDis91Normal = new Runnable() {
		@Override
		public void run() {
			if (Vol_dis_cnt > 0)
				Vol_dis_cnt--;
			if (Vol_dis_cnt == 0)
				CarDis91Normal();
		}
	};

	public static Runnable mCarDisNormal = new Runnable() {
		@Override
		public void run() {
			if (Vol_dis_cnt > 0)
				Vol_dis_cnt--;
			if (Vol_dis_cnt == 0)
				CarDisNormal();
		}
	};

	public static Runnable mCarDis91Volume = new Runnable() {
		@Override
		public void run() {
			CarDis91Volume();
		}
	};

	public static Runnable mCarDisVolume = new Runnable() {
		@Override
		public void run() {
			CarDisVolume();
		}
	};
}
