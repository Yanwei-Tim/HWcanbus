package com.hiworld.canbus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

public class TimeUtils {
	
	public static TimeUtils timeUtils;
	
	public static TimeUtils getInstance() {
		if(timeUtils == null) {
			timeUtils = new TimeUtils();
		}
		return timeUtils;
	}

	public static String date(String time){
		SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"); 
//		long lcc = Long.valueOf(time);  
		long lcc = Long.parseLong(time);
        String times = sdr.format(new Date(lcc));  
        return times;  
	}
	
	
	//APP肤色种类集合
	public static int[] skinSelect;
	
	@SuppressLint("Recycle")
	public int[] getAllSkin(Context context) {
		int skinNumber = UtilityClass.getPrefrenceInts(context, "skinNumber");
		if(skinNumber == -1)
			skinNumber = 0;
		TypedArray typedArray;
		if(skinSelect == null || skinSelect.length <= 0) {
			//获取全部皮肤个数集合
			typedArray = context.getResources().obtainTypedArray(R.array.skin_colour_select);
			skinSelect = new int[typedArray.length()];  
			for (int i = 0; i < typedArray.length(); i++) {
				skinSelect[i] = typedArray.getResourceId(i, 0);
			}
		}
		//获取需要加载的皮肤
		typedArray = context.getResources().obtainTypedArray(skinSelect[skinNumber]);
		int[] skinColour = new int[typedArray.length()];  
		for (int i = 0; i < typedArray.length(); i++) {
			skinColour[i] = typedArray.getResourceId(i, 0);
		}
		if(typedArray != null) {
			typedArray.recycle();  
		}
		return skinColour;
	}
}
