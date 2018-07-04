package com.hiworld.canbus.parsedata;

import android.os.Handler;

public interface HondaFragmentCallBack {
	// 本次驾驶的Handler
	public void setThisDrive(Handler handler);

	// 里程A记录的Handler
	public void setTripAHandler(Handler handler);
}
