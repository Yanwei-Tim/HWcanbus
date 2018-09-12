package ex.hiworld.obd;

import com.youzi.customer.aidl.RemoteProxy;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.os.RemoteException;
import ex.hiworld.obd.entity.DataCanbus;

/**
 * Created by APP03 on 2018/6/23.
 */

public class HandlerOBDCanbus {

	public static RemoteProxy PROXY = null;
	
	public static void setPROXY(RemoteProxy pROXY) {
		PROXY = pROXY;
	}
	
	public static void sendCmd(int cmd) {
		sendCmd(cmd, 0);
	}

	public static void sendCmd(int cmd, int val) {
		sendCmd(cmd, new int[] {val});
	}

	public static void sendCmd(int cmd, int val1, int val2) {
		sendCmd(cmd, new int[] {val1 , val2});
	}

	public static void sendCmd(int cmd, int[] vals) {
		int[] ints = new int[vals.length + 1];
		ints[0] = cmd;
		for (int i = 0; i < vals.length; i++) {
			ints[1 + i] = vals[i];
		}
		sendCmd(ints);
	}
	
	public static void sendCmd(int... ints) {
		try {
			if(PROXY != null)
				PROXY.sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
		} catch (RemoteException e) {
		}
	}

	public static void sendCmd(int cmd, int[] ints, float[] floats, String[] strings) {
//		PROXY.cmd(cmd, ints, floats, strings);
	}

//	public static void update(int updateCode, int[] ints) {
//		if (ints == null || ints.length == 0)
//			return;
//		if (DataCanbus.DATA[updateCode] != ints[0]) {
//			DataCanbus.DATA[updateCode] = ints[0];
//			DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
//		}
//	}
//
//	public static void update(int updateCode, int value) {
//		if (DataCanbus.DATA[updateCode] != value) {
//			DataCanbus.DATA[updateCode] = value;
//			DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
//		}
//	}
//
//	public static void update(int updateCode, int[] ints, float[] flts, String[] strs) {
//		if ((ints == null || ints.length == 0) && (strs == null || strs.length == 0))
//			return;
//		if (ints != null) {
//			if (DataCanbus.DATA[updateCode] != ints[0]) {
//				DataCanbus.DATA[updateCode] = ints[0];
//			}
//		}
//		DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, flts, strs);
//	}

	public static void sendCmd(int cmd, String str) {
//		PROXY.cmd(cmd, new int[]{0}, null, new String[] { str });
	}

}
