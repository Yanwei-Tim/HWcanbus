package com.hiworld.canbus.util;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

public class ImeRecode {
	//电子狗开关通过修改和获取数据库值去判断
	public static final Uri CONTENT_URI = Uri.parse("content://com.ime.system.setttings.util/system_settings_tab");
		
	public static int getRecord(Context context, String key, int default_value){
		ContentResolver contentResolver;
		contentResolver = context.getContentResolver();
		
		Cursor cursor = contentResolver.query(CONTENT_URI, null, "setting_type=?", new String[]{key}, null);
		if (cursor != null)
		{
			
			if (cursor.moveToFirst()) {					
				default_value = cursor.getInt(cursor.getColumnIndex("setting_value"));	
			}		 
			
			cursor.close();
		}	
		
		return default_value;
	}

    public static void updateRecord(Context context, String key, int value)
	{
		ContentResolver contentResolver;
		contentResolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put("setting_type", key);
		values.put("setting_value", value);
		contentResolver.update(CONTENT_URI, values, null, null);
	}
    
	public static void speakWarnTip(Context context, String speak) {
		
		if (!TextUtils.isEmpty(speak) && context != null) {
			String AMparam[] = new String[2];
			AMparam[0] = "edog";
			AMparam[1] = speak;
			Intent intent = new Intent();  
			intent.setAction("ime.service.intent.action.TTS_SPEACK");
			intent.putExtra("param", AMparam);
			context.sendBroadcast(intent);
		}
	}

}
