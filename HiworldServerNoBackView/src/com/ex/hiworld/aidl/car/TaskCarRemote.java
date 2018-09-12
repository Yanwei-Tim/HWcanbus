package com.ex.hiworld.aidl.car;


import com.ex.hiworld.aidl.ITaskBinder;
import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.server.canbus.CanInfos;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.tools.IUiNotify;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.UiNotifyEvent;

import android.os.RemoteException;

/**
 * Created by APP03 on 2018/6/23.
 */

public class TaskCarRemote extends ITaskBinder.Stub {
    public static final TaskCarRemote OBJ = new TaskCarRemote();
    private BaseCar mCanbus;

    public void setCanbus(BaseCar car) {
        this.mCanbus = car;
    }

    public BaseCar getCanbus() {
        return mCanbus;
    }

    public static TaskCarRemote getOBJ() {
        return OBJ;
    }

    @Override
    public void sendByte(int code, byte[] bytes) throws RemoteException { }
    
    
	public void cmd(int cmdCode, int val) {
			try {
			cmd(cmdCode, new int[] { val }, null, null);
			} catch (RemoteException e) {
		}
	}

	public void cmd(int cmdCode, int val1, int val2) {
			try {
			cmd(cmdCode, new int[] { val1, val2 }, null, null);
			} catch (RemoteException e) {
			}
		}
 


    @Override
    public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        LogsUtils.i("cmd ... " + cmdCode + " >> " + LogsUtils.toHexString(ints) + " @ "+ mCanbus.getClass().getSimpleName() );
		switch (cmdCode) {
		case FinalCanbus.C_HW_CMD_SOUND_CHANNEL:
			switch (ints[0]) {
			case 0x22:
				CanInfos.switchAudio2CarBt();
				break;
			case 0x21:
				CanInfos.switchAudio2CarUSB();
				break;
			case 0x20:
				CanInfos.switchAudio2CarRadio();
				break;
			case 0:
				CanInfos.switchAudio2Null();
				break;
			case -1:
				CanInfos.switchAudio2LastAppId();
				break;
			}
			break;
		case FinalCanbus.C_HW_CMD_UPDATE_MODE: {
			if (ints != null && ints.length > 0) {
				DataCanbus.isEnterUpdateMode = ints[0] == 1;
				if (DataCanbus.isEnterUpdateMode)
					HandlerTaskCanbus.getCarTypeByVersion("update mode");
				else {
					HandlerTaskCanbus.getCarTypeByVersion(DataCanbus.sVersionCanbox);
				}
				HandlerTaskCanbus.update(FinalCanbus.U_HW_CMD_UPDATE_MODE, ints[0]);
			}
			break;
		}
		default:
			if (mCanbus != null) {
				mCanbus.cmd(cmdCode, ints, flts, strs);
				break;
			}
		} 
    }

    @Override
    public void sendCmd(int cmdCode, int[] ints) throws RemoteException {
    	 if (mCanbus != null) {
             mCanbus.cmd(cmdCode, ints, null, null);
         } 
    }

    @Override
    public void sendUpdate(int code, int[] ints) throws RemoteException { }

    @Override
    public void registerCallback(ITaskCallback callback, int updateCode) throws RemoteException {
        if (callback == null) return;
        HandlerTaskCanbus.reg(callback);

        if (updateCode < FinalCanbus.U_MISC_BEGIN) {
            BaseCar canbus = mCanbus;
            if (canbus != null) {
                canbus.registerCallback(callback, updateCode);
            }
 
        } else {
			switch (updateCode) {
			case FinalCanbus.U_CANBUS_ID:
				LogsUtils.i("reg U_CANBUS_ID " + DataCanbus.DATA[updateCode]);
				HandlerTaskCanbus.update(updateCode);
				break;
			case FinalCanbus.U_HW_CMD_UPDATE_MODE:
				LogsUtils.i("reg U_HW_CMD_UPDATE_MODE " + DataCanbus.DATA[updateCode]);
				HandlerTaskCanbus.update(updateCode);
				break;
			case FinalCanbus.U_CANBUS_VER:
				LogsUtils.i("reg U_CANBUS_VER");
				HandlerTaskCanbus.update(updateCode, DataCanbus.sVersionCanbox, null);
				break;
			case FinalCanbus.U_HW_EXIST_FULLVIEW: {
				LogsUtils.i("reg U_HW_EXIST_FULLVIEW " + DataCanbus.sExistFullView + ", "
						+ DataCanbus.sExistFullViewFloatBtn);
				HandlerTaskCanbus.update(updateCode,
						new int[] { DataCanbus.sExistFullViewFloatBtn ? 1 : 0, DataCanbus.sExistFullView ? 1 : 0 });
				break;
			}
			case FinalCanbus.U_HW_HOST_STATE: {
				HandlerTaskCanbus.update(FinalCanbus.U_HW_HOST_STATE, new int[]{0, DataHost.sBackCar});
				HandlerTaskCanbus.update(FinalCanbus.U_HW_HOST_STATE, new int[]{1, DataHost.sAppid});
				break;
			}
			case FinalCanbus.U_HW_CHANGE_PANORAMA: {
				HandlerTaskCanbus.update(updateCode);
				break;
			}
			}
        }
    } 

	private static final Runnable CAN_ID = new Runnable() {

		@Override
		public void run() {
			LogsUtils.i("Notify CAN_ID " + Integer.toHexString(DataCanbus.canbusId & 0xFFFFF));
//			HandlerTaskCanbus.getCarById(DataCanbus.canbusId);
			HandlerTaskCanbus.update(FinalCanbus.U_HW_EXIST_FULLVIEW,
					new int[] { DataCanbus.sExistFullViewFloatBtn ? 1 : 0, DataCanbus.sExistFullView ? 1 : 0 });
		}
	};
	
    static {
		EventNotify.NE_CANBUS_ID.addNotify(CAN_ID, 1);
    }

    @Override
    public void unregisterCallback(ITaskCallback cb, int code) throws RemoteException {
        HandlerTaskCanbus.unreg(cb);
    }
}
