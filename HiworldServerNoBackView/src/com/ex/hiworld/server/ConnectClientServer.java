package com.ex.hiworld.server;

import java.util.Random;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.Utils;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;



/**
 * Created by APP03 on 2018/6/22.
 */

public class ConnectClientServer implements ServiceConnection, Runnable {
	static Handler mH;

	static {
		HandlerThread handlerThread = new HandlerThread("c-client");
		handlerThread.start();
		mH = new Handler(handlerThread.getLooper());
	}

	@Override
	public void run() {
		startCanBusSrv();
		startCarPcSrv();
		startBackUiSrv();
		
		reQuestCanBoxVersion();
		mH.postDelayed(this, 1000 + new Random().nextInt(3000));
	}

	public void startCanBusSrv() {
		if (!Utils.checkAppExist(FinalData.HW_CLIENT_PKG))
			return;
		Intent ii = new Intent(FinalData.HW_CLIENT_ACTION);
		ii.setPackage(FinalData.HW_CLIENT_PKG);
		try {
			MyApp.getInstance().startService(ii);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startCarPcSrv() {
		if (!Utils.checkAppExist(FinalData.HW_CLIENT_CARPC_PKG))
			return;
		Intent ii = new Intent(FinalData.HW_CLIENT_CARPC_ACTION);
		ii.setPackage(FinalData.HW_CLIENT_CARPC_PKG);
		try {
			MyApp.getInstance().startService(ii);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startBackUiSrv() {
		if (!Utils.checkAppExist(FinalData.HW_CLIENT_BACKUI_PKG))
			return;
		Intent ii = new Intent(FinalData.HW_CLIENT_CARPC_ACTION);
		ii.setPackage(FinalData.HW_CLIENT_BACKUI_PKG);
		try {
			MyApp.getInstance().startService(ii);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void reQuestCanBoxVersion() { 
		if(DataCanbus.canbusId == 0 && !DataCanbus.isEnterUpdateMode)
		{
//			SendFunc.send2Canbus(0x6A, new int[] {0x01, 0xF0});
			SendFunc.send2Canbus(0x6A, new int[] {0x05, 0x01, 0xF0});
		}
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) { }

	@Override
	public void onServiceDisconnected(ComponentName name) { }
}
