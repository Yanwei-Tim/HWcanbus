package ex.hiworld.car;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;
import tools.Utils;
import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.RemoteTaskProxy;

import java.util.Random;

/**
 * Created by APP03 on 2018/6/23.
 */

public class TaskServerConnect implements ServiceConnection {

    private static Context mContext;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable C = new Runnable() {
        @Override
        public void run() {
            connect(mContext);
            mHandler.postDelayed(C, 1000 + new Random().nextInt(3000));
        }
    };

    private static final TaskServerConnect OBJ = new TaskServerConnect();

    private static boolean isConnect = false;

	public static void connect(Context c) {
		mContext = c;
		if (!Utils.checkAppExist(Utils.HW_SRV_PKG))
			return;

		if (!isConnect) {
			Intent ii = new Intent(Utils.HW_SRV_ACTION);
			ii.setPackage(Utils.HW_SRV_PKG);
			try {
				c.bindService(ii, OBJ, Context.BIND_AUTO_CREATE);
			} catch (Exception e) {
				LogsUtils.e("bindHWServer error " + e.getLocalizedMessage());
			}

			LogsUtils.i("connect to TaskServerConnect .... ");
		}
	}

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isConnect = true;
        ITaskBinder iTaskBinder = ITaskBinder.Stub.asInterface(service);
        LogsUtils.i("onServiceConnected " + iTaskBinder);
        RemoteTaskProxy.getInstance().setTaskBinder(iTaskBinder);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_CANBUS_ID);
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_HW_CMD_UPDATE_MODE);
//                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_CANBUS_VER);
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_HW_CAMERA_MODE);
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_HW_EXIST_FULLVIEW);
                RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), FinalCanbus.U_HW_CHANGE_PANORAMA);
            }
        }, 500);
         
    }

	@Override
    public void onServiceDisconnected(ComponentName name) {
        LogsUtils.i("onServiceDisconnected TaskServerConnect");
        isConnect = false;
        mHandler.postDelayed(C, 1000 + new Random().nextInt(3000));
        RemoteTaskProxy.getInstance().setTaskBinder(null);
    }

}
