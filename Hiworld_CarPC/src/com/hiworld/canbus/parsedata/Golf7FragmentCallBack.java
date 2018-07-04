package com.hiworld.canbus.parsedata;

import android.os.Handler;

public interface Golf7FragmentCallBack {

	// 自起动之后的Handler
	public void setFromStartHandler(Handler handler);

	// 长时间的Handler
	public void setLongTimeHandler(Handler handler);

	// 自加油后的Handler
	public void setFromAddOilHandler(Handler handler);

}
