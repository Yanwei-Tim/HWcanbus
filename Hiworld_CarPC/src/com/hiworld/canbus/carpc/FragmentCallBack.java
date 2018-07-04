package com.hiworld.canbus.carpc;



public interface FragmentCallBack {
	//发送指令
	public void callbackCMD(int[] buffer, int iLength);
	public void jumpFragment(int index);
}
