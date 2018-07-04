package com.hiworld.canbus.parsedata;

import android.os.Handler;

public interface BaseCmdidCallBack {
	public void setBaseVmHandler(Handler handler);// 有关车门、灯光等

	public int parseCmdId(byte[] buffer, int size);// 解析相关车门、灯光等信息
}
