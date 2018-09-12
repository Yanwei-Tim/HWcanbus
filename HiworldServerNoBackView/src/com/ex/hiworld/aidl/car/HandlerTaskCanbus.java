package com.ex.hiworld.aidl.car;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.tools.DBUtils;
import com.ex.hiworld.server.tools.DBUtils.CarVersion;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.PrintScreenView;
import com.ex.hiworld.update.TaskCanBox_Update;

import android.R.integer;
import android.annotation.SuppressLint;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

/**
 * Created by APP03 on 2018/6/9.
 */

public class HandlerTaskCanbus {
	private static BaseCar mBaseCar = null;
	
	@SuppressLint("DefaultLocale")
	public static int getCarTypeByVersion(String version) {
		int cartype = 0;
		int carLevel = 0;
		
		{ // for test
 
			int canbusID = 0;
			LogsUtils.i(" version :[" + version + "]");
			if (version != null && !version.trim().isEmpty()) { 

				String strVer = version;
				if (version.length() > 7) {
					strVer = version.substring(2, 7);
				}
				LogsUtils.i(" -- >>>>>> 查询版本 [" + strVer + "]");
				List<CarVersion> query = DBUtils.getInstance().query(strVer);
				if (query != null && query.size() > 0) {
					CarVersion carVersionItem = query.get(0);
					LogsUtils.i(" -- >>>>>> 找到 版本 [" + strVer + "] ");
					LogsUtils.i("-- >>>>>> " + carVersionItem.toString());

					PrintScreenView.getMsgView().msg(" 从数据库中找到 版本  " + carVersionItem.toString());
					carLevel = carVersionItem.getCaridoffset();
					cartype = carVersionItem.getCarid();
				} else {
					LogsUtils.i(" -- >>>>>> 无法从 数据库中找到 版本[" + strVer + "]");
					PrintScreenView.getMsgView().msg(" -- >>>>>> 无法从 数据库中找到 版本[" + strVer + "]");
				}
			
			}
		}

		if(carLevel == 0 && cartype == FinalCanbus.CAR_VWF0 && DataCanbus.isHead5A) {
			carLevel = 1;
		}
		
		int idCan = carLevel << 16 | cartype ;
		BaseCar carById = getCarById(idCan);
		
		if(carById.mCarOffset != 0)
			carLevel = carById.mCarOffset;
		
		LogsUtils.i("getcar by version " + carLevel);
		return carLevel << 16 | cartype;
	}
	
	
	public static BaseCar getCarById(int id) {
		LogsUtils.i("getCarById " + Integer.toHexString(id));
		BaseCar base = null;
		int carOffset = id >> 16 & 0xFFFF;
		int cartype = id & 0xFFFF;
		switch (cartype) {
		case FinalCanbus.CAR_VWF0:
			if (carOffset == 1) {
				base = new TaskCar_Golf();
			} else {
				base = new TaskCar_VW();
			}
			break;
		case FinalCanbus.CAR_GM_ALL: {
			base = new TaskCar_GM_All();
			break;
		}
		case FinalCanbus.CAR_PSA_ALL:
			base = new TaskCar_PSA();
			break;
		case FinalCanbus.CAR_TOYOTA_ALL:
			base = new TaskCar_Toyota();
			break;
		case FinalCanbus.CAR_HONDA_ALL:
			if (12 == carOffset)
				base = new TaskCar_Honda_12Crv();
			else
				base = new TaskCar_Honda();
			break;
		case FinalCanbus.CAR_NISSAN_ALL:
			base = new TaskCar_Nissan();
			break;
		case FinalCanbus.CAR_HYUNDAI_ALL:
			base = new TaskCar_Hyunda();
			break;
		case FinalCanbus.CAR_FORD_ALL:
			if (carOffset == 19) {
				base = new TaskCar_Ford_MengDiOu();
			} else
				base = new TaskCar_Ford_All();
			break;
		case FinalCanbus.CAR_MAZIDA_ALL: {
			base = new TaskCar_Mazida_All();
			break;
		}
		default:
			base = new TaskCar_Null();
			break;
		}

		// 进入升级模式
		if(DataCanbus.isEnterUpdateMode){
			LogsUtils.i("进入升级模式 " + DataCanbus.isEnterUpdateMode);
			base = TaskCanBox_Update.getInstance();
		}

			if (mBaseCar != null)
				mBaseCar.out();

			LogsUtils.i(" getCarById: " + base.getClass().getSimpleName());
			mBaseCar = base;
			TaskCarRemote.getOBJ().setCanbus(mBaseCar);

			mBaseCar.in();

		return base;
	}
	

	public static void parseCanbusData(int[] ints) {
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

//	public static void canbusVer(String s) { }

	public static void update(int updateCode) {
		update(updateCode, new int[] { DataCanbus.DATA[updateCode] }, null,
				new String[] { DataCanbus.DATA_String[updateCode] });
	}

	public static void update(int updateCode, int i) {
		if (DataCanbus.DATA[updateCode] != i) {
			LogsUtils.d("update " + updateCode + " " + i);
			DataCanbus.DATA[updateCode] = i;
			update(updateCode, new int[] { i }, null, null);
		}
	}
	
	// 更新全景图
	public static void updateFullViewState(int updateCode, int i) {
		if (DataCanbus.DATA[updateCode] != i) {
			DataCanbus.DATA[updateCode] = i;
			update(updateCode, new int[] { i }, null, null);

			EventNotify.NE_FULLVIEW.onNotify();
		}
	}

	public static void update(int updateCode, String str, String strTemp) {
		if (str == null)
			return;
		if (!str.equalsIgnoreCase(strTemp)) {
//			DataCanbus.DATA_String[updateCode] = str;
			update(updateCode, new int[] { updateCode }, null, new String[] { str });
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

	public static void reg(ITaskCallback callback) {
		TASK_CALLBACK_REMOTE_CALLBACK_LIST.register(callback);
		
//		DataCanbus.MCL.register(callback);
	}

	public static void unreg(ITaskCallback callback) {
		TASK_CALLBACK_REMOTE_CALLBACK_LIST.unregister(callback);
		
		
//		DataCanbus.MCL.unregister(callback);
	}

	public static void update(int update, int[] ints) {
		update(update, ints, null, null);
	}

	public static void update(int update, int[] ints, String[] strs) {
		update(update, ints, null, strs);
	}
}
