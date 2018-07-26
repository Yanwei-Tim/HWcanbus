package com.hiworld.canbus.interfaces;

import android.os.Handler;

public interface FragmentCallBack {
	public void setCarStateHandler(Handler handler);//车辆状态
	public void setCarTripHandler(Handler handler);//车辆行程
	public void setCarInfoHandler(Handler handler);//车辆信息
	public void setHudHandler(Handler handler);
	public void setDoorlockHandler(Handler handler);
	public void setLightHandler(Handler handler);
	public void setWindowHandler(Handler handler);
	public void setControlHandler(Handler handler);
	public void setHtpmsHandler(Handler handler);
	public void setInstrumentHandler(Handler handler);
	public void setMirrorHandler(Handler handler);
	public void setMainFregmentHandler(Handler handler);
	public void setmHandlerHomeFregment(Handler handler);
	public void setmHandlerCarStateFregment(Handler handler);
	public void setmHandlerTpmsFragment(Handler handler);
}

