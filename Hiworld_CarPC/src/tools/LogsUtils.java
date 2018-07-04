package tools;

import android.util.Log;

/**
 * Created by APP03 on 2018/6/20.
 */

public class LogsUtils {
    public static final String TAG = "hipc";

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void w(String str) {
        Log.d(TAG, str);
    }

    public static void e(String str) {
        Log.e(TAG, str);
    }

    public static String toHexString(int[] bytes){
        StringBuffer buffer = new StringBuffer();
        for (int i: bytes){
            String s = Integer.toHexString(i&0xFF).toUpperCase();
            if (s.length() == 1)
                s = "0" + s;
            buffer.append(s + " ") ;
        }
        return buffer.toString();
    }
    public static String toHexString(byte[] bytes){
        StringBuffer buffer = new StringBuffer();
        for (int i: bytes){
            String s = Integer.toHexString(i&0xFF).toUpperCase();
            if (s.length() == 1)
                s = "0" + s;
            buffer.append(s + " ") ;
        }
        return buffer.toString();
    }

}
