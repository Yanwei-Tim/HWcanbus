package com.hiworld.canbus.parsedata;

import com.hiworld.adapter.CarInfo;
import com.hiworld.canbus.carpc.ActivityCarPC;
import com.hiworld.constant.Constant;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class PsaToyotaParseData implements PsaToyotaFragmentCallBack {
	private Handler mComputerHandler1;
	private Handler mComputerHandler2;
	private Handler mComputerHandler3;
	private Handler mPerMinuteHandler;
	private Handler mHistroyHandler;
	private Context mContext;
	private Handler mMainHandler;

	private static final PsaToyotaParseData INSTANCE = new PsaToyotaParseData();

	public static PsaToyotaParseData getInstance() {
		return INSTANCE;
	}

	public void setContext(Context context) {
		mContext = context;
	}

	public void parseToyotaCmd(byte[] buffer, int size) {

		if (buffer != null && size > 0) {
			// Log.d(TAG, "parseToyotaCmd "+(buffer[1] & 0xff));
			switch (buffer[0] & 0xff) {
			case 0x13:// 车身详细信息-页面0
				if (size == 13) {
					if (CarInfo.getInstance() != null) {
						// 平均油耗
						int values = ((buffer[1] & 0xff) * 256) + (buffer[2] & 0xff);
						if (values != 0xffff) {
							CarInfo.getInstance().setTrip1AverageFuel((float) (values * 0.1));
						} else {
							CarInfo.getInstance().setTrip1AverageFuel(values);
						}
						// 续航里程
						values = ((buffer[3] & 0xff) * 256) + (buffer[4] & 0xff);
						CarInfo.getInstance().setMileage(values);
						// 最佳油耗
						values = ((buffer[5] & 0xff) * 256) + (buffer[6] & 0xff);
						if (values != 0xffff) {
							CarInfo.getInstance().setBestFuel((float) (values * 0.1));
						} else {
							CarInfo.getInstance().setBestFuel(values);
						}
						// 行车时间(分钟)
						values = ((buffer[7] & 0xff) * 256) + (buffer[8] & 0xff);
						// Log.d(TAG, "time =="+values);
						CarInfo.getInstance().setDrivingTime(values);
						// 平均车速
						values = ((buffer[9] & 0xff) * 256) + (buffer[10] & 0xff);
						CarInfo.getInstance().setTrip1AverageSpeed(values);
						// 油耗单位
						values = buffer[11] & 0xff;
						CarInfo.getInstance().setFuleUnit(values);
						// 里程单位
						values = buffer[12] & 0xff;
						CarInfo.getInstance().setMileageUnit(values);

						if (mPerMinuteHandler != null) {
							mPerMinuteHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
									.sendToTarget();
						}
						if (mHistroyHandler != null) {
							mHistroyHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			case 0x16:// 车身详细信息-页面1
				if (size == 14) {
					if (CarInfo.getInstance() != null) {
						// 当前行程油耗
						int values = ((buffer[1] & 0xff) * 256) + (buffer[2] & 0xff);
						CarInfo.getInstance().setCurrentTripFuel((float) (values * 0.1));
						// Trip1油耗
						values = ((buffer[3] & 0xff) * 256) + (buffer[4] & 0xff);
						CarInfo.getInstance().setTrip1Fuel((float) (values * 0.1));
						// Trip2油耗
						values = ((buffer[5] & 0xff) * 256) + (buffer[6] & 0xff);
						CarInfo.getInstance().setTrip2Fuel((float) (values * 0.1));
						// Trip3油耗
						values = ((buffer[7] & 0xff) * 256) + (buffer[8] & 0xff);
						CarInfo.getInstance().setTrip3Fuel((float) (values * 0.1));
						// Trip4油耗
						values = ((buffer[9] & 0xff) * 256) + (buffer[10] & 0xff);
						CarInfo.getInstance().setTrip4Fuel((float) (values * 0.1));
						// Trip5油耗
						values = ((buffer[11] & 0xff) * 256) + (buffer[12] & 0xff);
						CarInfo.getInstance().setTrip5Fuel((float) (values * 0.1));
						// 油耗单位
						values = buffer[13] & 0xff;
						CarInfo.getInstance().setTrip1FuleUnit(values);

						if (mPerMinuteHandler != null) {
							mPerMinuteHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
									.sendToTarget();
						}
						if (mHistroyHandler != null) {
							mHistroyHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
									.sendToTarget();
						}
					}
				}
				break;
			case 0x17:// 车身详细信息-页面2
				if (size == 62) {
					if (CarInfo.getInstance() != null) {
						// 上一分钟油耗
						int values = ((buffer[1] & 0xff) * 256) + (buffer[2] & 0xff);
						CarInfo.getInstance().setTrip2Minute1Fuel((float) (values * 0.1));
						// 上2分钟油耗
						values = ((buffer[3] & 0xff) * 256) + (buffer[4] & 0xff);
						CarInfo.getInstance().setTrip2Minute2Fuel((float) (values * 0.1));
						// 上3分钟油耗
						values = ((buffer[5] & 0xff) * 256) + (buffer[6] & 0xff);
						CarInfo.getInstance().setTrip2Minute3Fuel((float) (values * 0.1));
						// 上4分钟油耗
						values = ((buffer[7] & 0xff) * 256) + (buffer[8] & 0xff);
						CarInfo.getInstance().setTrip2Minute4Fuel((float) (values * 0.1));
						// 上5分钟油耗
						values = ((buffer[9] & 0xff) * 256) + (buffer[10] & 0xff);
						CarInfo.getInstance().setTrip2Minute5Fuel((float) (values * 0.1));
						// 上6分钟油耗
						values = ((buffer[11] & 0xff) * 256) + (buffer[12] & 0xff);
						CarInfo.getInstance().setTrip2Minute6Fuel((float) (values * 0.1));
						// 上7分钟油耗
						values = ((buffer[13] & 0xff) * 256) + (buffer[14] & 0xff);
						CarInfo.getInstance().setTrip2Minute7Fuel((float) (values * 0.1));
						// 上8分钟油耗
						values = ((buffer[15] & 0xff) * 256) + (buffer[16] & 0xff);
						CarInfo.getInstance().setTrip2Minute8Fuel((float) (values * 0.1));
						// 上9分钟油耗
						values = ((buffer[17] & 0xff) * 256) + (buffer[18] & 0xff);
						CarInfo.getInstance().setTrip2Minute9Fuel((float) (values * 0.1));
						// 上10分钟油耗
						values = ((buffer[19] & 0xff) * 256) + (buffer[20] & 0xff);
						CarInfo.getInstance().setTrip2Minute10Fuel((float) (values * 0.1));
						// 上11分钟油耗
						values = ((buffer[21] & 0xff) * 256) + (buffer[22] & 0xff);
						CarInfo.getInstance().setTrip2Minute11Fuel((float) (values * 0.1));
						// 上12分钟油耗
						values = ((buffer[23] & 0xff) * 256) + (buffer[24] & 0xff);
						CarInfo.getInstance().setTrip2Minute12Fuel((float) (values * 0.1));
						// 上13分钟油耗
						values = ((buffer[25] & 0xff) * 256) + (buffer[26] & 0xff);
						CarInfo.getInstance().setTrip2Minute13Fuel((float) (values * 0.1));
						// 上14分钟油耗
						values = ((buffer[27] & 0xff) * 256) + (buffer[28] & 0xff);
						CarInfo.getInstance().setTrip2Minute14Fuel((float) (values * 0.1));
						// 上15分钟油耗
						values = ((buffer[29] & 0xff) * 256) + (buffer[30] & 0xff);
						CarInfo.getInstance().setTrip2Minute15Fuel((float) (values * 0.1));
						// 上16分钟油耗
						values = ((buffer[31] & 0xff) * 256) + (buffer[32] & 0xff);
						CarInfo.getInstance().setTrip2Minute16Fuel((float) (values * 0.1));
						// 上17分钟油耗
						values = ((buffer[33] & 0xff) * 256) + (buffer[34] & 0xff);
						CarInfo.getInstance().setTrip2Minute17Fuel((float) (values * 0.1));
						// 上18分钟油耗
						values = ((buffer[35] & 0xff) * 256) + (buffer[36] & 0xff);
						CarInfo.getInstance().setTrip2Minute18Fuel((float) (values * 0.1));
						// 上19分钟油耗
						values = ((buffer[37] & 0xff) * 256) + (buffer[38] & 0xff);
						CarInfo.getInstance().setTrip2Minute19Fuel((float) (values * 0.1));
						// 上20分钟油耗
						values = ((buffer[39] & 0xff) * 256) + (buffer[40] & 0xff);
						CarInfo.getInstance().setTrip2Minute20Fuel((float) (values * 0.1));
						// 上21分钟油耗
						values = ((buffer[41] & 0xff) * 256) + (buffer[42] & 0xff);
						CarInfo.getInstance().setTrip2Minute21Fuel((float) (values * 0.1));
						// 上22分钟油耗
						values = ((buffer[43] & 0xff) * 256) + (buffer[44] & 0xff);
						CarInfo.getInstance().setTrip2Minute22Fuel((float) (values * 0.1));
						// 上23分钟油耗
						values = ((buffer[45] & 0xff) * 256) + (buffer[46] & 0xff);
						CarInfo.getInstance().setTrip2Minute23Fuel((float) (values * 0.1));
						// 上24分钟油耗
						values = ((buffer[47] & 0xff) * 256) + (buffer[48] & 0xff);
						CarInfo.getInstance().setTrip2Minute24Fuel((float) (values * 0.1));
						// 上25分钟油耗
						values = ((buffer[49] & 0xff) * 256) + (buffer[50] & 0xff);
						CarInfo.getInstance().setTrip2Minute25Fuel((float) (values * 0.1));
						// 上26分钟油耗
						values = ((buffer[51] & 0xff) * 256) + (buffer[52] & 0xff);
						CarInfo.getInstance().setTrip2Minute26Fuel((float) (values * 0.1));
						// 上27分钟油耗
						values = ((buffer[53] & 0xff) * 256) + (buffer[54] & 0xff);
						CarInfo.getInstance().setTrip2Minute27Fuel((float) (values * 0.1));
						// 上28分钟油耗
						values = ((buffer[55] & 0xff) * 256) + (buffer[56] & 0xff);
						CarInfo.getInstance().setTrip2Minute28Fuel((float) (values * 0.1));
						// 上29分钟油耗
						values = ((buffer[57] & 0xff) * 256) + (buffer[58] & 0xff);
						CarInfo.getInstance().setTrip2Minute29Fuel((float) (values * 0.1));
						// 上30分钟油耗
						values = ((buffer[59] & 0xff) * 256) + (buffer[60] & 0xff);
						CarInfo.getInstance().setTrip2Minute30Fuel((float) (values * 0.1));
						// 油耗单位
						values = buffer[61] & 0xff;
						CarInfo.getInstance().setTrip2FuleUnit(values);

						if (mPerMinuteHandler != null) {
							mPerMinuteHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
									.sendToTarget();
						}
						if (mHistroyHandler != null) {
							mHistroyHandler.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
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

	public void parsePsaCmd(byte[] buffer, int size) {

		if (buffer != null && size > 0) {
			System.out.println("xxxx ==" + (buffer[0] & 0xff) + ", size ==" + size);
			switch (buffer[0] & 0xff) {
			case 0x13:// 行车电脑信息page0
				if (size == 11) {
					// 瞬时油耗
					int values = (buffer[1] & 0xff) * 256 + (buffer[2] & 0xff);
					if (values != 0xffff) {
						CarInfo.getInstance().setInstantFuel((float) (values * 0.1));
					} else {
						CarInfo.getInstance().setInstantFuel(values);
					}

					// 续航里程
					values = (buffer[3] & 0xff) * 256 + (buffer[4] & 0xff);
					CarInfo.getInstance().setMileage(values);

					if (mComputerHandler1 != null) {
						mComputerHandler1.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
								.sendToTarget();
					}
				}
				break;
			case 0x14:
				if (size == 11) {
					int values = (buffer[1] & 0xff) * 256 + (buffer[2] & 0xff);
					CarInfo.getInstance().setTrip1AverageFuel((float) (values * 0.1));
					values = buffer[4] & 0xff;
					CarInfo.getInstance().setTrip1AverageSpeed(values);
					values = (buffer[5] & 0xff) * 256 + (buffer[6] & 0xff);
					CarInfo.getInstance().setTrip1AccumMileage(values);

					if (mComputerHandler2 != null) {
						mComputerHandler2.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
								.sendToTarget();
					}
				}
				break;
			case 0x15:
				if (size == 11) {
					int values = (buffer[1] & 0xff) * 256 + (buffer[2] & 0xff);
					if (values != 0xffff) {
						CarInfo.getInstance().setTrip2AverageFuel((float) (values * 0.1));
					} else {
						CarInfo.getInstance().setTrip2AverageFuel(values);
					}

					values = buffer[4] & 0xff;
					CarInfo.getInstance().setTrip2AverageSpeed(values);
					values = (buffer[5] & 0xff) * 256 + (buffer[6] & 0xff);
					CarInfo.getInstance().setTrip2AccumMileage(values);

					if (mComputerHandler3 != null) {
						mComputerHandler3.obtainMessage(Constant.Message_Instant_Handler, CarInfo.getInstance())
								.sendToTarget();
					}
				}
				break;
			case 0x11:// 面板处理CarPC
				if (size == 11) {
					if ((buffer[4] & 0xff) == 0x01) {
						if ((buffer[3] & 0xff) == 0x16) {
							// Log.d(TAG, "psa activity = "+MainActivity.isActive);
							System.out.println("MainActivity.isActive ==" + ActivityCarPC.isActive);
							if (ActivityCarPC.isActive) {
								if (mMainHandler != null) {
									mMainHandler.sendEmptyMessage(Constant.MESSAGE_CHANGEPAGE_CARPC);
								}
							} else {
								startApp("com.hiworld.carcomputer");
							}
						}
					}
				}
				break;
			default:
				break;
			}
		}
	}

	private void startApp(final String apppackname) {
		try {
			Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(apppackname);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
		} catch (Exception e) {
		}
	}

	@Override
	public void setPsaHandler(Handler mHandler) {

		mComputerHandler1 = mHandler;
	}

	@Override
	public void setTrip1Handler(Handler mHandler) {

		mComputerHandler2 = mHandler;
	}

	@Override
	public void setTrip2Handler(Handler mHandler) {

		mComputerHandler3 = mHandler;
	}

	@Override
	public void setPerMinuteHandler(Handler mHandler) {

		mPerMinuteHandler = mHandler;
	}

	@Override
	public void setHistroyHandler(Handler mHandler) {

		mHistroyHandler = mHandler;
	}

	@Override
	public void setMainHandler(Handler mHandler) {

		mMainHandler = mHandler;
	}

}
