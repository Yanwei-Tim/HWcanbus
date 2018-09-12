package tools;

import android.content.Context;
import android.content.pm.PackageManager;
import ex.hiworld.MyApp;

/**
 * Created by APP03 on 2018/6/23.
 */

public class Utils {
	public static final String HW_SRV_PKG = "com.ex.hiworld.server";
	public static final String HW_SRV_ACTION = "com.ex.hiworld.taskserver";

	public static boolean checkAppExist(String packageName) {
		if (packageName == null || "".equals(packageName)) {
			return false;
		}
		PackageManager packageManager = MyApp.getInstance().getPackageManager();
		try {
			packageManager.getPackageInfo(packageName, PackageManager.GET_SERVICES);
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
			} else {
				break;
			}
		}
		return mm;

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

	// 123 -> 02:03 , 3623->01:00:23
	public static String getTimeFromRealVal(int val) {
		String tString = "00:00";
		int h = 0, m, s;
		if (val > 60 * 60) {
			h = val / 3600;
		}
		int ms = val % 3600;
		m = ms / 60;
		s = ms % 60;
		if (h > 0) {
			tString = String.format("%02d:%02d:%02d", h, m, s);
		} else {
			tString = String.format("%02d:%02d", m, s);
		}
		return tString;
	}
 
}
