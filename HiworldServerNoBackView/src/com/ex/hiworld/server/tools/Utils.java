package com.ex.hiworld.server.tools;

import java.io.UnsupportedEncodingException;

import com.ex.hiworld.server.MyApp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by APP03 on 2018/6/9.
 */

public class Utils {
	public static boolean checkAppExist(String packageName) {
		if (packageName == null || "".equals(packageName)) {
			return false;
		}
		PackageManager packageManager = MyApp.getInstance().getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SERVICES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			// e.printStackTrace();
		}

		return false;
	}
	
	public static String getStringFromInts(int[] arrs, int start, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char c = (char) arrs[start + i];
			if (c == 0)
				break;
			sb.append(c);
		}
		return sb.toString();
	}

	public static int combine(int h, int l) {
		return (h & 0xFF) << 8 | l & 0xFF;
	}

	public static int combine(int h, int m, int l) {
		return (h & 0xFF) << 16 | (m & 0xFF) << 8 | l & 0xFF;
	}

	public static int[] convertString2Unicode(String s) {
		if (s == null)
			s = "";
		int[] data = new int[(s.length() + 1) * 2];
		for (int i = 0; i < s.length(); i++) {
			int unicode = s.codePointAt(i);
			data[(i << 1)] = unicode >> 8 & 0xFF;
			data[(i << 1) + 1] = unicode & 0xFF;
		}
		return data;

	}

	public static int[] convertString2Unicode(String s, int needLen) {
		if (s == null)
			s = "";
		System.out.println(s + " . " + s.length());
		int mm[] = new int[needLen];
		int strlen = s.length();
		for (int i = 0, j = 0; i < needLen - 2; j++) {
			if (j < strlen) {
				int c = s.codePointAt(j);
				mm[i++] = c >> 8 & 0xFF;
				mm[i++] = c & 0xFF;
			}else {
				break;
			}
		}
		return mm;

	}
	
	public static String U2Str(int[] ints) {
		byte[] bs = I2B(ints);
		String string = "";
		try {
			string = new String(bs, "unicode");
		} catch (UnsupportedEncodingException e) {
		}
		return string;
	}


//    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块      */
//    public static final String US_ASCII = "US-ASCII";
//    /** ISO拉丁字母表 No.1，也叫做ISO-LATIN-1     */
//    public static final String ISO_8859_1 = "ISO-8859-1";
//    /** 8 位 UCS 转换格式     */
//    public static final String UTF_8 = "UTF-8";
//    /** 16 位 UCS 转换格式，Big Endian(最低地址存放高位字节）字节顺序     */
//    public static final String UTF_16BE = "UTF-16BE";
//    /** 16 位 UCS 转换格式，Litter Endian（最高地址存放地位字节）字节顺序     */
//    public static final String UTF_16LE = "UTF-16LE";
//    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识     */
//    public static final String UTF_16 = "UTF-16";
//    /** 中文超大字符集     **/
//    public static final String GBK = "GBK";
//    
//    public static final String GB2312 = "GB2312";
	// String sb = "123456";
	// 
	// byte[] bs = sb.getBytes("UTF-16BE");
	// [fe ff 00 31 00 32 00 33 00 34 00 35 00 36 ] --- > UNICODE
	// [00 31 00 32 00 33 00 34 00 35 00 36 ]  -- >> "UTF-16BE"
	
	public static int[] Str2U(String str) {
		int[] its = new int[0];
		try {
			byte[] bs = str.getBytes("UTF-16BE");
			return B2I(bs);
		} catch (UnsupportedEncodingException e) {
		}
		return its;
	}

	
	public static byte[] I2B(int[] i) {
		byte[] b = new byte[i.length];
		for (int j = 0; j < b.length; j++) {
			b[j] = (byte) (i[j] & 0xFF);
		}
		return b;
	}
	
	public static int[] B2I(byte[] b) {
		int[] i = new int[b.length];
		for (int j = 0; j < b.length; j++) {
			i[j] = b[j];
		}
		return i;
	}
	

	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static float convertC2F(float c) {
		return (c * 9) / 5.0f + 32;
	}

	public static float convertF2C(float f) {
		return (f - 32) * 5 / 9.0f;
	}
}
