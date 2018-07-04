package com.hiworld.canbus.parsedata;

import android.os.Handler;

public interface GMFragmentCallBack {
	public void setVmUIHadler(Handler mHandler);

	public void setFuleMileageHandler(Handler mHandler);

	public int parseGmCmd(byte[] buffer, int size);
}
