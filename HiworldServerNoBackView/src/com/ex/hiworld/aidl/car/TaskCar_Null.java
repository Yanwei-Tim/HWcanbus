package com.ex.hiworld.aidl.car;

import com.ex.hiworld.aidl.ITaskCallback;
import android.os.RemoteException;
public class TaskCar_Null extends BaseCar {
	@Override
	public void onHandler(int[] data) {

	}

	@Override
	public void in() {

	}

	@Override
	public void out() {

	}

	public void cmd(int cmd, int[] ints, float[] floats, String[] strings) throws android.os.RemoteException {

	};
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
	}
}
