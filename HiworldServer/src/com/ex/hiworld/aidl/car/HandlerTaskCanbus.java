package com.ex.hiworld.aidl.car;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.PrintScreenView;
import com.ex.hiworld.server.tools.Utils;

/**
 * Created by APP03 on 2018/6/9.
 */

public class HandlerTaskCanbus {
	private static BaseCar mBaseCar = null;

	public static int getCarTypeByVersion(String version) {
		int cartype = 0;
		int carLevel = 0;

		{ // for test
			LogsUtils.i("/////////////////////////////////////");
			LogsUtils.i("/////////////////////////////////////");
			LogsUtils.i("/////////////////////////////////////");
			LogsUtils.i("//////////////调试协议////////////////");
			LogsUtils.i("/////////////固定ID//////////////////");
			LogsUtils.i("/////////////////////////////////////");
			LogsUtils.i("/////////////////////////////////////");
			LogsUtils.i("/////////////////////////////////////");
			int canbusID = FinalCanbus.CAR_NISSAN_ALL;
			carLevel = canbusID >> 16 & 0xFFFF;
			cartype = canbusID & 0xFFFF;
		}

		if (mBaseCar != null)
			mBaseCar.out();

		DataCanbus.canbusType = cartype;
		DataCanbus.canbusLevel = carLevel;
		BaseCar base = null;
		switch (cartype) {
		case FinalCanbus.CAR_VWF0:
			if (cartype == 1) {
				base = new TaskCar_Golf();
			} else {
				base = new TaskCar_VW();
			}
			break;
		case FinalCanbus.CAR_GM_ALL: {
			base = new TaskCar_GM();
			break;
		}
		case FinalCanbus.CAR_PSA_ALL:
			base = new TaskCar_PSA();
			break;
		case FinalCanbus.CAR_TOYOTA_ALL:
			base = new TaskCar_Toyota();
			break;
		case FinalCanbus.CAR_HONDA_ALL:
			base = new TaskCar_Honda();
			break;
		case FinalCanbus.CAR_NISSAN_ALL:
			base = new TaskCar_Nissan();
			break;
		default:
			base = new TaskCar_Null();
			break;
		}

		mBaseCar = base;
		TaskCarRemote.getOBJ().setCanbus(mBaseCar);

		mBaseCar.in();
		return carLevel << 16 | cartype;
	}

	public static void parseCanbusData(int[] ints) {
//		LogsUtils.d("canbus_data_full :" + LogsUtils.toHexString(ints));
		PrintScreenView.getMsgView().msg(LogsUtils.toHexString(ints));
		if (mBaseCar != null)
			mBaseCar.onHandler(ints);
	}

	public static final RemoteCallbackList<ITaskCallback> TASK_CALLBACK_REMOTE_CALLBACK_LIST = new RemoteCallbackList<ITaskCallback>();

	public static synchronized void update(int update, int[] ints, float[] flts, String[] strs) {
		synchronized (TASK_CALLBACK_REMOTE_CALLBACK_LIST) {
			int M = TASK_CALLBACK_REMOTE_CALLBACK_LIST.beginBroadcast();
			for (int i = 0; i < M; i++) {
				try {
					ITaskCallback broadcastItem = TASK_CALLBACK_REMOTE_CALLBACK_LIST.getBroadcastItem(i);
					synchronized (broadcastItem) {
						broadcastItem.update(update, ints, flts, strs);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			TASK_CALLBACK_REMOTE_CALLBACK_LIST.finishBroadcast();
		}
	}

	public static void canbusVer(String s) {

	}

	public static void update(int updateCode) {
		update(updateCode, new int[] { DataCanbus.DATA[updateCode] }, null,
				new String[] { DataCanbus.DATA_String[updateCode] });
	}

	public static void update(int updateCode, int i) {
		if (DataCanbus.DATA[updateCode] != i) {
			LogsUtils.d("update: " + updateCode + " " + i);
			DataCanbus.DATA[updateCode] = i;
			update(updateCode, new int[] { i }, null, null);
		}
	}

	public static void update(int updateCode, String str, String strTemp) {
		if (str == null)
			return;
		if (!str.equalsIgnoreCase(strTemp)) {
			LogsUtils.i("update String: " + updateCode + " " + str);
			DataCanbus.DATA_String[updateCode] = str;
			update(updateCode, new int[] { 0 }, null, new String[] { str });
		}

	}

	public static boolean update(int updateCode, int[] value, int[] ints) {
		if (ints[0] == value[0]) {
			if (ints[1] != value[1]) {
				update(updateCode, new int[] { value[0], value[1] }, null, null);
				return true;
			}
		}

		return false;
	}

	public static void callUpdate(int[] data) {
		int M = TASK_CALLBACK_REMOTE_CALLBACK_LIST.beginBroadcast();
		for (int i = 0; i < M; i++) {
			try {
				ITaskCallback broadcastItem = TASK_CALLBACK_REMOTE_CALLBACK_LIST.getBroadcastItem(i);
				broadcastItem.getCmd(1, data, 0, null);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		TASK_CALLBACK_REMOTE_CALLBACK_LIST.finishBroadcast();
	}

	public static void reg(ITaskCallback callback) {
		TASK_CALLBACK_REMOTE_CALLBACK_LIST.register(callback);
	}

	public static void unreg(ITaskCallback callback) {
		TASK_CALLBACK_REMOTE_CALLBACK_LIST.unregister(callback);
	}
}
