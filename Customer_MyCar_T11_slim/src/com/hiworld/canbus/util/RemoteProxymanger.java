package com.hiworld.canbus.util;

import com.youzi.customer.aidl.RemoteProxy;
import com.youzi.customer.util.ConstUtil;

import android.os.RemoteException;

public class RemoteProxymanger {
	
	private static RemoteProxymanger Instance = new RemoteProxymanger();

	private RemoteProxy REMOTE_PROXY = null;

	public RemoteProxy getREMOTE_PROXY() {
		return REMOTE_PROXY;
	}

	public void setREMOTE_PROXY(RemoteProxy rEMOTE_PROXY) {
		REMOTE_PROXY = rEMOTE_PROXY;
	}

	public static RemoteProxymanger getInstance() {
		return Instance;
	} 
	
	public void writeBuf(int[] ints) {
		if (REMOTE_PROXY != null) {
			try {
				REMOTE_PROXY.sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
