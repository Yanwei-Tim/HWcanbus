package ex.hiworld.car;

import android.os.RemoteException;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;

import com.ex.hiworld.aidl.ITaskCallback; 

/**
 * Created by APP03 on 2018/6/23.
 */

public class Task_CallBackProxy extends ITaskCallback.Stub {

    public static final Task_CallBackProxy OBJ = new Task_CallBackProxy();

    public static Task_CallBackProxy getObj() {
        return OBJ;
    }

    private Task_CallbackCanBusBase Canbus;

    public void setCanbus(Task_CallbackCanBusBase bus) {
        if (bus == null) return;
        if (Canbus != bus) {
            if (Canbus != null)
                Canbus.out();
            Canbus = bus;
            bus.in();
        }
    }

    @Override
    public void getByte(int code, byte[] bytes, int size, String strs) throws RemoteException {
        if (Canbus != null) {
            Canbus.getByte(code, bytes, size, strs);
        }
    }

    @Override
    public void getCmd(int code, int[] ints, int size, String strs) throws RemoteException {
        if (Canbus != null)
            Canbus.getCmd(code, ints, size, strs);
    }

    @Override
	public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
		if (updateCode >= 0 && updateCode < FinalCanbus.U_MISC_BEGIN) {
			if (Canbus != null)
				Canbus.update(updateCode, ints, flts, strs);
		} else {
			switch (updateCode) {
			case FinalCanbus.U_CANBUS_ID:
				if(ints == null) return;
				HandlerCanbus.update(updateCode, ints[0]);
				break;
			case FinalCanbus.U_HW_CMD_UPDATE_MODE:
				if(ints == null) return;
				DataCanbus.isEnterUpdateMode = ints[0] == 1;
				HandlerCanbus.update(updateCode, ints[0]);
				break;
			default:
				if(ints == null) return;
				HandlerCanbus.update(updateCode, ints, flts, strs);
				break;
			}
		}
	}
}
