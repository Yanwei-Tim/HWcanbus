package ex.hiworld.obd;

import com.youzi.customer.aidl.CallbackTask;
import com.youzi.customer.aidl.CallbackTask.OnReciverIntsAble;
import com.youzi.customer.aidl.RemoteProxy;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import tools.GaoDeReceiver;
import tools.HandlerUI;
import tools.LogsUtils;

public class OBDServer extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
 
	@Override
	public void onCreate() {
		super.onCreate();
		ConnConnect.connect();
		HandlerUI.getInstance().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				RemoteProxy remoteProxy = ConnConnect.getInstance().getRemoteProxy();
				if(remoteProxy == null) {
					HandlerUI.getInstance().postDelayed(this, 500);
				}else {
					LogsUtils.i(".................. remote " + remoteProxy) ;
					HandlerOBDCanbus.setPROXY(remoteProxy);
					CallbackTask.getInstance().registerIntsCallBack(ConstUtil.APP2SERVER_OTHER, mVissCallBack);
				}
			}
		}, 500);
		
		GaoDeReceiver.getOBJ().reg(this);
		initObdWindow();
		LogsUtils.i(getClass().getSimpleName() + " create ");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		if(extras != null) {
			int iShowFlag = extras.getInt("SHOW");
			if(iShowFlag == 1) {
				OBDUtils.getInstance().showOBDView();
			}else {
				OBDUtils.getInstance().hideOBDView();
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void initObdWindow() {
		OBDUtils.getInstance().init(this);
		OBDUtils.getInstance().buildContent(new MainOBD());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		GaoDeReceiver.getOBJ().unReg(this); 
	}
	
	private OnReciverIntsAble mVissCallBack = new OnReciverIntsAble() {
		
		@Override
		public int update(int[] arg0, int arg1) {  return 0; }
		
		@Override
		public int getIntsCmd(int[] ints, int size, String text) {
			HandlerParseData.onHandle(ints);
			return 0;
		}
	};

//	private static final IUiNotify _CANBUS_ID = new IUiNotify() {
//		@Override
//		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
//			LogsUtils.i("Task_CallBackProxy 0000 . _CANBUS_ID " + DataCanbus.DATA[updateCode]);
//			if (spUtils.get(FinalCanbus.U_CANBUS_ID, 0) != DataCanbus.DATA[updateCode]) {
//				spUtils.set(FinalCanbus.U_CANBUS_ID, DataCanbus.DATA[updateCode]);
//			}
//			Task_CallbackCanBusBase callbackCanBus = HandlerCanbus.getCallbackCanBusById(DataCanbus.DATA[updateCode]);
//			Task_CallBackProxy.getObj().setCanbus(callbackCanBus);
//		}
//	};

//	private IUiNotify notify = new IUiNotify() {
//
//		@Override
//		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
//			// LogsUtils.i("updateCode ... " + updateCode);
//			switch (updateCode) {
//			case FinalCanbus.U_HW_CHANGE_PANORAMA: {
//				if (ints == null)
//					return;
//				LogsUtils.i("updateCode U_HW_CHANGE_PANORAMA ... sExistFullView " + DataCanbus.isExistFullView + ": " + ints[0]);
//				if (DataCanbus.isExistFullView) {
//					if (ints[0] == 1) {
//						BackCarUtils.getInstance().showFullView();
//					} else if (ints[0] == 0) {
//						BackCarUtils.getInstance().hideFullView();
//					}
//				}
//				break;
//			}
//			case FinalCanbus.U_HW_EXIST_FULLVIEW: {
//				if (ints == null)
//					return;
//
//				LogsUtils.i("updateCode U_HW_EXIST_FULLVIEW ... " + ints[0] + ":" + ints[1]);
//				{
//					DataCanbus.isExistFloatBtn = ints[0] == 1;
//					// 浮动按钮
//					BackCarUtils.getInstance().setFloatShowEnable(ints[0] == 1);
//				}
//				// 全景视图
//				int exist = ints[1];
//				DataCanbus.isExistFullView = exist == 1;
//			} 
//			default:
//				break;
//			}
//		}
//	};

//	static {
//		DataCanbus.NOTIFY_EVENTS[FinalCanbus.U_CANBUS_ID].addNotify(_CANBUS_ID, 1);
//	}
}
