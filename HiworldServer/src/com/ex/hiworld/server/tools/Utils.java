package com.ex.hiworld.server.tools;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ex.hiworld.server.MyApp;

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
//            e.printStackTrace();
        }

        return false;
    }

    public static String getStringFromInts(int[] arrs, int start, int len){
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < len ; i++){
            char c = (char) arrs[start + i];
            if(c == 0) break;
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static int combine(int carLevel, int cartype) {
		return (carLevel&0xFF)<<8|cartype&0xFF;
	}
    
    public static int combine(int h, int m, int l) {
		return (h&0xFF)<<16|(m&0xFF)<<8|l&0xFF;
	}
}
