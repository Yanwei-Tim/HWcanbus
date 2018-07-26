package com.hiworld.canbus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UtilityClass {
	
	//添加校验位
	public static byte addCheckSum(byte[] buffer, int iLength) {
		byte addSum = (byte)0x00;
		if(buffer != null && iLength > 0){
			for (int i=0; i<iLength; i++){
				addSum += buffer[i];
			}
			addSum = (byte) ((addSum & 0xff) - 1);
		}
		return addSum;
	}
	
	//检查校验位是否正确
	public static boolean checkSum(byte[] buffer, int iLength) {
        boolean m_bResult = false;
        if (buffer != null && iLength > 0) {
            byte AddSum = 0;
            AddSum = addCheckSum(buffer, iLength - 1);
            
            //Log.i("aaa", "AddSum =="+AddSum+",buffer[iLength - 1] =="+buffer[iLength - 1]);
            if (AddSum == buffer[iLength - 1]) {
                m_bResult = true;
            }
        }
        return m_bResult;
    }
	
	public static String bytesToHexString(byte[] src, int iLength) {
		StringBuilder stringBuilder = new StringBuilder("");
	    if (src == null || iLength <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < iLength; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}
	
	//标记是否已点击更新
	public static void setPrefrenceBoolean(Context context, String name, boolean value){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(name, value);
		editor.commit();
	}
	
	public static boolean getPrefrenceBoolean(Context context, String name){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		return preferences.getBoolean(name, false);
	}
	
	public static void setPrefrenceInts(Context context, String name, int value){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(name, value);
		editor.commit();
	}
	
	public static int getPrefrenceInts(Context context, String name){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		return preferences.getInt(name, -1);
	}
	
	public static void setPrefrenceString(Context context, String name, String value){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(name, value);
		editor.commit();
	}
	
	public static String getPrefrenceString(Context context, String name){
		SharedPreferences preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
		return preferences.getString(name, "");
	}
}
