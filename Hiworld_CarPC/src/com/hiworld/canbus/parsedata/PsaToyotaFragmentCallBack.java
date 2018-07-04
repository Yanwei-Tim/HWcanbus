package com.hiworld.canbus.parsedata;

import android.os.Handler;

public interface PsaToyotaFragmentCallBack {
	public void setPsaHandler(Handler mHandler);

	public void setTrip1Handler(Handler mHandler);

	public void setTrip2Handler(Handler mHandler);

	public void setPerMinuteHandler(Handler mHandler);

	public void setHistroyHandler(Handler mHandler);

	public void setMainHandler(Handler mHandler);
}
