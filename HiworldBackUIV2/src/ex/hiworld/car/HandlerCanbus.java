package ex.hiworld.car;

import com.ex.hiworld.aidl.RemoteTaskProxy;

import ex.hiworld.backcar.BackCarUtils;
import ex.hiworld.backcar.Full17Crv;
import ex.hiworld.backcar.FullToyota;
import ex.hiworld.backcar.FullSiyu;
import ex.hiworld.backcar.HandlerViewUtils;
import ex.hiworld.backcar.LayoutBase;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;

/**
 * Created by APP03 on 2018/6/23.
 */

public class HandlerCanbus {

	public static Task_CallbackCanBusBase getCallbackCanBusById(int id) {
		DataCanbus.sCanbusId = id;
		DataCanbus.sCanbusIdBase = id & 0xFFFF;
		DataCanbus.sCanbusIdOffset = id >> 16 & 0xFFFF;

		HandlerViewUtils.buildContent();

		return null;
	}
	
	


	public static final RemoteTaskProxy PROXY = RemoteTaskProxy.getInstance();

	public static void sendCmd(int cmd) {
		PROXY.cmd(cmd);
	}

	public static void sendCmd(int cmd, int val) {
		PROXY.cmd(cmd, val);
	}

	public static void sendCmd(int cmd, int val1, int val2) {
		PROXY.cmd(cmd, val1, val2);
	}

	public static void sendCmd(int cmd, int[] vals) {
		PROXY.cmd(cmd, vals, null, null);
	}

	public static void sendCmd(int cmd, int[] ints, float[] floats, String[] strings) {
		PROXY.cmd(cmd, ints, floats, strings);
	}

	public static void update(int updateCode, int[] ints) {
		if (ints == null || ints.length == 0)
			return;
		if (DataCanbus.DATA[updateCode] != ints[0]) {
			DataCanbus.DATA[updateCode] = ints[0];
			DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
		}
	}

	public static void update(int updateCode, int value) {
		if (DataCanbus.DATA[updateCode] != value) {
			DataCanbus.DATA[updateCode] = value;
			DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
		}
	}

	public static void update(int updateCode, int[] ints, float[] flts, String[] strs) {
		if ((ints == null || ints.length == 0) && (strs == null || strs.length == 0))
			return;
		if (ints != null) {
			if (DataCanbus.DATA[updateCode] != ints[0]) {
				DataCanbus.DATA[updateCode] = ints[0];
			}
		}
		DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, flts, strs);
	}

	public static void sendCmd(int cmd, String str) {
		PROXY.cmd(cmd, new int[]{0}, null, new String[] { str });
	}

}
