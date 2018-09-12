package com.ex.hiworld.update;

import java.io.FileInputStream;
import java.io.InputStream;

import com.ex.hiworld.aidl.ITaskCallback;
import com.ex.hiworld.aidl.car.BaseCar;
import com.ex.hiworld.aidl.car.HandlerTaskCanbus;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.Ticks;

import android.content.Context;
import android.os.RemoteException;

public class TaskCanBox_Update extends BaseCar{
	
	public static final int U_UPDATE_TYPE = 1;
	
	public static final int FLAG_UPDATE_START = 1; 
	public static final int FLAG_UPDATE_ERROR = 3; 
	public static final int FLAG_UPDATE_PROGRESS = 4;
	public static final int FLAG_UPDATE_FINISH = 5; 
	public static final int FLAG_UPDATE_FILE_NOT_EXIST = 6;
	public static final int FLAG_UPDATE_SIZE_NOT_MATCH = 7;
	public static final int FLAG_UPDATA_ERROR_LARGE = 8;
	public static final int FLAG_UPDATA_FILE_READ_FAILED = 9;
	
	private static final int C_FILE_PATH = 1;

	private boolean bFileEixt = false;
	public boolean bClickUpdate = false;
	private final int MAX_LEN = 136;
	private boolean bUpdating = false;
	private int index = -1;// 第几帧的索引

	private int mlengthData = 0;
	private byte[] mIapData;

	private boolean bErrorStart = false;
	private final static TaskCanBox_Update mInstance = new TaskCanBox_Update();


	public static TaskCanBox_Update getInstance() {
		return mInstance;
	}

	public void init(Context context) {
	} 

	public void setFileEixt(boolean FileEixt) {
		this.bFileEixt = FileEixt;
	}
	
	
	@Override
	public void onHandler(int[] data) {
		parseUpdateUartData(data, data.length);
	}
	

	protected boolean parseUpdateUartData(int[] buffer, int size) {
		// 传递符合IAP升级数据
		if (size > 0 && size < 3) {
			return parseIapBuf(buffer, size);
		}
		return false;
	}

	public boolean parseIapBuf(int[] buffer, int length) {
		boolean isUpdateDataFormat = true;
		if (buffer != null && length > 0) {
			switch (length) {
			case 1:
				switch (buffer[0]) {
				case 0x55:// U 升级准备 85
					LogsUtils.i("UP_WC  parseIapBuf   U");

					if (bClickUpdate || bErrorStart ) {
						bClickUpdate = false;
						bErrorStart = false;
						bUpdating = true;
						index = 0;

						sendPassword();

						// if (mListener != null) {
						// mListener.getCurrentState("开始升级！");
						// }

						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_START);
					}
					break;
				case 0x52:// R 升级出错
					LogsUtils.i("UP_WC  parseIapBuf  R Err ");
					// if (mListener != null) {
					// mListener.getCurrentState("升级错误，请点击开始按钮复位！");
					// }

					HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_ERROR);
					bUpdating = false;
					break;
				case 0x42:
					// 忽略单独发B的情况
					break;
				case 0x53:// S 下一帧 83
					LogsUtils.i("UP_WC  parseIapBuf   S");
//					if (!bFileEixt) { 
//						// 文件不存在
//						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_FILE_NOT_EXIST);
//					} else 
					{
						if (bUpdating) {
							sendIapData(index);
							LogsUtils.i(String.format(" 进度: %02d/%02d", index, mlengthData / MAX_LEN));
							// if (mListener != null) {
							// mListener.getCurrentState("进度 ：" + resString);
							// }
							HandlerTaskCanbus.update(U_UPDATE_TYPE, new int[] { FLAG_UPDATE_PROGRESS, index, mlengthData / MAX_LEN },
									null, null);
							index++;
						}
					}
					break;
				case 0x45:// E 升级结束 69
					LogsUtils.i("UP_WC  parseIapBuf   End");
					if (bUpdating) {
						index = 0;
						bUpdating = false;
						LogsUtils.i("升级结束");
						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_FINISH);
					}
					break;
				default:
					isUpdateDataFormat = false;
					break;
				}
				break;
			case 2:
				if ((buffer[0] == 0x42 && buffer[1] == 0x53)) { // BS 66 83
					if (mIapData != null && bFileEixt) {
						sendIapData(0);
						index = 1;
						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_START);
					}
				} else if (buffer[0] == 0x52 && (buffer[1] == 0x45|| buffer[1] == 0x53)) {// RE 82 69
					LogsUtils.i("UP_WC  parseIapBuf   RE  error");
					HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_ERROR);
					bUpdating = false;
					bErrorStart = true;
				}
				break;
			default:
				isUpdateDataFormat = false;
				break;
			}
		}
		return isUpdateDataFormat;
	}

	private void reset() {
		mSendCnt = 0;
		requestCanCnt = 0;
		Ticks.addTicks1s(mRequestUpgradeMode);
	}

	private int requestCanCnt = 0;
	private int mSendCnt = 0;
	private final Runnable mRequestUpgradeMode = new Runnable() {
		@Override
		public void run() {
			if (!bUpdating) {
				mSendCnt++;
				if (mSendCnt == 4) { // 若干次都没有进入CAN盒自身的升级模式,重新请求进入MCU的CAN盒升级模式
					mSendCnt = 0;
				} else { // 请求进入CAN盒自身的升级模式
					LogsUtils.i("请求进入CAN盒自身的升级模式");
					if (requestCanCnt < 3) {
						LogsUtils.i("请求进入CAN盒自身的升级模式111 5A A5");
						if (requestCanCnt == 0) {
							reset5A();
						}
						requestCanCnt++;
					} else if (requestCanCnt >= 3) {
						LogsUtils.i("请求进入CAN盒自身的升级模式222 AA 55");
						if (requestCanCnt == 3) {
							resetAA();
						}
						requestCanCnt++;
						if (requestCanCnt == 6) {
							requestCanCnt = 0;
						}
					}
				}
			} else {
				Ticks.removeTicks1s(this);
			}
		}

	};

	@Override
	public void in() {
		super.in();
	}
	
	@Override
	public void out() {
		super.out();
		
		Ticks.removeTicks1s(mRequestUpgradeMode);
		mIapData = null;
		index = 0;
		bFileEixt = false;
	}
	
	@Override
	public void cmd(int cmd, int[] ints, float[] floats, String[] strs) throws RemoteException {
		switch (cmd) {
		case C_FILE_PATH: {
			LogsUtils.i("选择升级文件0 " + strs);
			if (strs != null && strs.length > 0) {
				InputStream in = null;
				try {
					in = new FileInputStream(strs[0]);
					LogsUtils.i("选择升级文件" + strs[0] );
					int size = in.available();
					if (size == 0 || size % 136 != 0) {
						LogsUtils.i("size == 0 or not 136*n");
						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATE_SIZE_NOT_MATCH);
						return;
					}
					if (size > 2 * 1024 * 1024) {
						LogsUtils.i("size to large");
						HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATA_ERROR_LARGE);
						return;
					}
					LogsUtils.i("size = " + size);
					mIapData = new byte[mlengthData = size];
					in.read(mIapData);
				} catch (Exception e) {
					e.printStackTrace();
					LogsUtils.i("读取文件失败");
					HandlerTaskCanbus.update(U_UPDATE_TYPE, FLAG_UPDATA_FILE_READ_FAILED);
					return;
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				LogsUtils.i("读取文件成功");
				setClickUpdate();
				break;
			}
		}
		}
	}

	// 发复位,启动协议升级
	public void setClickUpdate() {
		bClickUpdate = true;
		bFileEixt = true;
		// 新老盒子协议头不同，一起发送
		// sendVissUart(new byte[] { 0x5A, (byte) 0xA5, 0x02, (byte) 0xE1, 0x00, 0x00,
		// (byte) 0xE2 }); // this is just for viss
		// sendVissUart(new byte[] { (byte) 0xAA, (byte) 0x55, 0x02, (byte) 0xE1, 0x00,
		// 0x00, (byte) 0xE2 });

//		reset5A();
//		resetAA();
		
		reset();
	}

	private void resetAA() {
		sendVissUart(new int[] { (byte) 0xAA, (byte) 0x55, 0x02, (byte) 0xE0, 0x00, 0x00, (byte) 0xE1 });
	}

	protected void reset5A() {
		sendVissUart(new int[] { 0x5A, (byte) 0xA5, 0x02, (byte) 0xE0, 0x00, 0x00, (byte) 0xE1 });
	}

	private void sendPassword() {
		sendVissUart(new int[] { 0x19, (byte) 0x78, 0x02, 0x17 });
	}

	private void sendIapData(int N) {
		if (mIapData != null && N < mlengthData / MAX_LEN) {
			LogsUtils.i("UP_WC  sendIapData   N   = " + N + " / " + mlengthData / MAX_LEN);
			int len = MAX_LEN;
			int[] iap = new int[MAX_LEN];
			index = N; 
//			System.arraycopy(mIapData, N * MAX_LEN, iap, 0, len); // 卡死。。
			for (int i = 0; i < iap.length; i++) {
				iap[i]  = mIapData[i + N *MAX_LEN]; 
			} 
			sendVissUart(iap);
		}
	}

	private void sendVissUart(int[] bs) {
		SendFunc.send2CanbusRaw(bs);
	}
	@Override
	public void registerCallback(ITaskCallback cb, int code) throws RemoteException {
		if(code >= 0 && code < 10) {
			HandlerTaskCanbus.update(code);
		}
	}
}
