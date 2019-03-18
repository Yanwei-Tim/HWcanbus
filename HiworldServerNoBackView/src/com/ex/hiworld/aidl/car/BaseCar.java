package com.ex.hiworld.aidl.car;

import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;

import android.os.RemoteException;

/**
 * Created by APP03 on 2018/6/23.
 */

public class BaseCar extends ITaskBinder.Stub {
	public int mCarOffset = 0;
	
    public void in(){}
    public void out(){}
    public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws RemoteException {    }
    public void onHandler(int[] data){};
    @Override
    public void sendByte(int code, byte[] bytes) throws RemoteException {

    }

    @Override
    public void sendCmd(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void sendUpdate(int code, int[] ints) throws RemoteException {

    }

    @Override
    public void registerCallback(ITaskCallback cb, int code) throws RemoteException {

    }

    @Override
    public void unregisterCallback(ITaskCallback cb, int code) throws RemoteException {

    }
    
	public void updateCanbusIdOffSet(int i) {
		DataCanbus.canbusLevel = i;
		DataCanbus.canbusId = i << 16 | DataCanbus.canbusType;
		HandlerTaskCanbus.update(FinalCanbus.U_CANBUS_ID, DataCanbus.canbusId);
	}
	
}