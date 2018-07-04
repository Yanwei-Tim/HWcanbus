package tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by APP03 on 2018/6/23.
 */

public class Utils {
    public static final String HW_SRV_PKG = "com.ex.hiworld.server";
    public static final String HW_SRV_ACTION = "com.ex.hiworld.taskserver";

    public static boolean checkAppExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SERVICES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
        }

        return false;
    }
    

    // 123 -> 02:03 , 3623->01:00:23
    public static String getTimeFromRealVal(int val) {
        String tString = "00:00";
        int h = 0, m, s;
        if (val > 60 * 60) {
            h = val / 3600;
        }
        int ms = val %3600;
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
