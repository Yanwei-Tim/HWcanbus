package com.hiworld.canbus.parse;

import java.util.ArrayList;

import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

public class RequestCmdUtil {
	private static final String TAG = RequestCmdUtil.class.getSimpleName();
	
	private static final int REQUEST_HANDLER = 0x500;
	//请求数组
	private ArrayList<Integer> mArrayRequestCmds = new ArrayList<Integer>();
	
	private int iCmdIndex = -1;
	
	private static final RequestCmdUtil INSTANCE = new RequestCmdUtil();
	
	public static RequestCmdUtil getInstance(){
		return INSTANCE;
	}
	
	public void initRequestArray(Integer[] arrayCmd){
		mArrayRequestCmds.clear();
		addArrayCmd(arrayCmd);
		//记录初始值
		iCmdIndex = -1;

		mHandler.removeMessages(REQUEST_HANDLER);
		mHandler.sendEmptyMessageDelayed(REQUEST_HANDLER, 1000);
	}
	
	public void clearRequestArray(){
		if (mArrayRequestCmds.size() > 0) {
			mArrayRequestCmds.clear();	
		}
		mHandler.removeMessages(REQUEST_HANDLER);
	}
	
	public void  addArrayCmd(Integer[] arrayCmd){
		for (int i = 0; i < arrayCmd.length; i++) {
			addArrayCmd(arrayCmd[i]);
		}
	}

	public void addArrayCmd(Integer cmd) {
		
		if (mArrayRequestCmds.indexOf(cmd) == -1) {
			mArrayRequestCmds.add(cmd);
		}
	}
	
	public void removeArrayCmd(Integer[] arrayCmd){
		for (int i = 0; i < arrayCmd.length; i++) {
			removeCmd(arrayCmd[i]);
		}
	}
	
	public void removeCmd(Integer cmd){
		if (mArrayRequestCmds.indexOf(cmd) != -1) {
			mArrayRequestCmds.remove(cmd);
		}
	}
	
	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case REQUEST_HANDLER:
				requestCmd();
				mHandler.sendEmptyMessageDelayed(REQUEST_HANDLER, 1000);
				break;
			default:
				break;
			}
		}
	};

	protected void requestCmd() {
		
		if (mArrayRequestCmds.size() > 0) {
			if (iCmdIndex >= (mArrayRequestCmds.size()-1)) {
				iCmdIndex = 0;
			} else {
				iCmdIndex++;
			}
			sendRequestCmd(mArrayRequestCmds.get(iCmdIndex));
		}
	}

	private void sendRequestCmd(Integer cmdId) {
		
//		int id = 0x7A;
//		switch (cmdId) {
//		case 0x48:
//		case 0x49:
//		case 0x50:
//		case 0xE0:
//			id = 0x6A;
//			break;
//		default:
//			break;
//		}
		
		sendVissCmd(new int[] {0x03,0x6A,0x05,0x01,cmdId.intValue()});
	}
	
	public void sendVissCmd(int[] ints){

		try {
			ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
