package com.ex.hiworld.aidl.task;

import com.ex.canbus.DataCanbus;
import com.ex.hiworld.aidl.RemoteTaskProxy;
import com.hiworld.fragment.golf.ConstGolf;

import android.os.RemoteException;
import tools.LogsUtils;

public class Task_CallBack_VW extends Task_CallbackCanBusBase {

	@Override
	public void in() {
		LogsUtils.i("Task_CallBack_Golf in");
		for (int i = 0; i < ConstGolf.U_CNT_MAX; i++)
			RemoteTaskProxy.getInstance().registerCallback(Task_CallBackProxy.getObj(), i);
		
		
	}
	
	@Override
	public void out() {
		for (int i = 0; i < ConstGolf.U_CNT_MAX; i++)
			RemoteTaskProxy.getInstance().unregisterCallback(Task_CallBackProxy.getObj(), i);
	}

	@Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
		if (updateCode >= 0) {
			switch (updateCode) {
			case ConstGolf.U_IDCARNUM: 
			case ConstGolf.U_WARNNING_CONV_CONSUMER:
			case ConstGolf.U_WARNNING_VEHICLE:
			case ConstGolf.U_WARNNING_START_STOP:
			case ConstGolf.U_JUMP_CARINFO:
			case ConstGolf.U_AIR_SHOW:
			case ConstGolf.U_DASHBOARD_SHOW:
				break;
			default:{
				if (updateCode >= 0 && updateCode < ConstGolf.U_CNT_MAX) {
			        LogsUtils.i("Task_CallBack_Golf update " + updateCode + " " + ints[0]);
					Task_HandlerCanbus.update(updateCode, ints);	
							
				}
				

					break;
				}
			}
		}

    } 
}
