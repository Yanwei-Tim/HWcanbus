package com.ex.hiworld.server.tools;

import java.util.Arrays;

import android.R.integer;
import android.os.SystemClock;

public class FormatSerialData {
	private static FormatSerialData Obj = new FormatSerialData();

	public static FormatSerialData getObj() {
		return Obj;
	}

	public interface OnHandlerListener {
		void onHandler(int[] data, int start, int length);
	}

	OnHandlerListener mListener;

	public void setOnHandlerListener(OnHandlerListener li) {
		this.mListener = li;
	}

	private int mSize, mFrameStartIndex, mCheckSumIndex;
	private int[] DATA = new int[1024];

	public void onReceiver(int[] data) {

		if (data == null) {
			SystemClock.sleep(100);
			return;
		}

//		System.out.println("origin : " + toHexString(data));
		if (mSize + data.length > 1024) {
			mSize = mFrameStartIndex = mCheckSumIndex = 0;
		}
		System.arraycopy(data, 0, DATA, mSize, data.length);
		mSize += data.length;

		// 上次处理已经定位到校验码位置， 但是有效数据不足
		if (mCheckSumIndex != 0) {
			if (mCheckSumIndex >= mSize)
				return;

			if (checkOk(DATA, 0)) {
				onHandler(DATA, 0, DATA[2] + 5);
				mFrameStartIndex = DATA[2] + 5 /* + 1 */;
			} else {
				mFrameStartIndex++;
			}
		}

		mCheckSumIndex = 0;
//		LogsUtils.w(" . " + mFrameStartIndex + ", " + mCheckSumIndex + ", " + mSize);
//		LogsUtils.w("origin data: " + toHexString(DATA, mFrameStartIndex, mSize));
		
		for (int end = mSize; mFrameStartIndex < end; ++mFrameStartIndex) {
			if (isHead(DATA, mFrameStartIndex)) {
				mCheckSumIndex = DATA[mFrameStartIndex + 2];
				if (mCheckSumIndex > 512) {
					mCheckSumIndex = 0;
					continue;
				}

				mCheckSumIndex += mFrameStartIndex + 4;
				if (mCheckSumIndex > mSize) {
					mCheckSumIndex -= mFrameStartIndex;
					break;
				}

				if (checkOk(DATA, mFrameStartIndex)) {
					onHandler(DATA, mFrameStartIndex + 0, DATA[mFrameStartIndex + 2] + 5);
					mFrameStartIndex = mCheckSumIndex;
				} else {
					mFrameStartIndex++;
				}
				mCheckSumIndex = 0;
			}

		}

		if (mFrameStartIndex != 0) {
			mSize -= mFrameStartIndex;
			if (mSize > 0) {
				System.arraycopy(DATA, mFrameStartIndex, DATA, 0, mSize);
				Arrays.fill(DATA, mSize, DATA.length, 0);
			}

//			if (mSize < 0) {
//
//				System.out.println(".............. " + mFrameStartIndex);
//			}
			mFrameStartIndex = 0;
		}
	}

	private void onHandler(int[] ints, int start, int len) {
//		LogsUtils.w(" +++YYY+++++ onHandler " + toHexString(ints, start, len));

		if (mListener != null) {
			mListener.onHandler(ints, start, len);
		}
	}

	private boolean isHead(int[] ints, int index) {
		return ints[index] == 0x5A && ints[index + 1] == 0xA5;
	}

	private boolean checkOk(int[] dd, int start) {
		byte chck = 0;
		int len = dd[start + 2];
		for (int i = 0; i < len + 2; i++) {
			chck += (byte) dd[start + 2 + i];
		}
		chck = (byte) ((chck - 1) & 0xFF);
		byte chkSum = (byte) dd[start + len + 4];
		// System.out.println(" chk start " + start +" : "+ chck + "/" + chkSum);
		return chck == chkSum;
	}

	public String toHexString(int[] bytes, int start, int len) {
		int[] copyOfRange = Arrays.copyOfRange(bytes, start, start + len);
		return toHexString(copyOfRange);
	}

	public String toHexString(int[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i : bytes) {
			String s = Integer.toHexString(i & 0xFF).toUpperCase();
			if (s.length() == 1)
				s = "0" + s;
			buffer.append(s + " ");
		}
		return buffer.toString();
	}

}
