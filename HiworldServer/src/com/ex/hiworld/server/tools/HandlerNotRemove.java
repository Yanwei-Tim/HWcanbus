package com.ex.hiworld.server.tools;

import android.os.Handler;
import android.os.HandlerThread;

public class HandlerNotRemove extends Handler{
	private static HandlerNotRemove OBJ = new HandlerNotRemove();
	private Handler mHandler;
	private HandlerThread mHandlerThread;

	HandlerNotRemove(){
		mHandlerThread = new HandlerThread("Not Removable");
		mHandlerThread.start();
		mHandler = new Handler(mHandlerThread.getLooper());
	}

	public static HandlerNotRemove getInstance() {
		return OBJ;
	}

}
