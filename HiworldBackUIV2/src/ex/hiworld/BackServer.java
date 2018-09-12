package ex.hiworld;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ex.hiworld.backcar.BackCarUtils;
import ex.hiworld.backcar.Full17Crv;
import ex.hiworld.backcar.FullSiyu;
import ex.hiworld.backcar.LayoutBase;
import ex.hiworld.car.HandlerCanbus;
import ex.hiworld.car.Task_CallBackProxy;
import ex.hiworld.car.Task_CallbackCanBusBase;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.IUiNotify;
import tools.LogsUtils;
import tools.spUtils;

public class BackServer extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private int[] ids = new int[] {
			// FinalCanbus.U_HW_CAMERA_MODE,
			FinalCanbus.U_HW_CHANGE_PANORAMA, FinalCanbus.U_HW_EXIST_FULLVIEW, };

	
	@Override
	public void onCreate() {
		super.onCreate();

		for (int i : ids) {
			DataCanbus.NOTIFY_EVENTS[i].addNotify(notify, 1);
		}
	}

	private static final IUiNotify _CANBUS_ID = new IUiNotify() {
		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			LogsUtils.i("Task_CallBackProxy 0000 . _CANBUS_ID " + DataCanbus.DATA[updateCode]);
			if (spUtils.get(FinalCanbus.U_CANBUS_ID, 0) != DataCanbus.DATA[updateCode]) {
				spUtils.set(FinalCanbus.U_CANBUS_ID, DataCanbus.DATA[updateCode]);
			}
			Task_CallbackCanBusBase callbackCanBus = HandlerCanbus.getCallbackCanBusById(DataCanbus.DATA[updateCode]);
			Task_CallBackProxy.getObj().setCanbus(callbackCanBus);
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();

		for (int i : ids) {
			DataCanbus.NOTIFY_EVENTS[i].removeNotify(notify);
		}
	}

	private IUiNotify notify = new IUiNotify() {

		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			// LogsUtils.i("updateCode ... " + updateCode);
			switch (updateCode) {
			case FinalCanbus.U_HW_CHANGE_PANORAMA: {
				if (ints == null)
					return;
				LogsUtils.i("updateCode U_HW_CHANGE_PANORAMA ... sExistFullView " + DataCanbus.isExistFullView + ": " + ints[0]);
				if (DataCanbus.isExistFullView) {
					if (ints[0] == 1) {
						BackCarUtils.getInstance().showFullView();
					} else if (ints[0] == 0) {
						BackCarUtils.getInstance().hideFullView();
					}
				}
				break;
			}
			case FinalCanbus.U_HW_EXIST_FULLVIEW: {
				if (ints == null)
					return;

				LogsUtils.i("updateCode U_HW_EXIST_FULLVIEW ... " + ints[0] + ":" + ints[1]);
				{
					DataCanbus.isExistFloatBtn = ints[0] == 1;
					// 浮动按钮
					BackCarUtils.getInstance().setFloatShowEnable(ints[0] == 1);
				}
				// 全景视图
				int exist = ints[1];
				DataCanbus.isExistFullView = exist == 1;
			} 
			default:
				break;
			}
		}
	};

	static {
		DataCanbus.NOTIFY_EVENTS[FinalCanbus.U_CANBUS_ID].addNotify(_CANBUS_ID, 1);
	}
}
