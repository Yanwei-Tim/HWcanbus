package com.ex.hiworld.server.tools;

import android.content.Context;
import android.content.SharedPreferences;
 
public class spUtils {

	private static SharedPreferences sha;
	private static SharedPreferences.Editor editor;

	public static void init(String name, Context c) {
		sha = c.getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sha.edit();
	}
	

	/*
	 * 获取Boolean数据
	 */
	private  void setBoolean(String Name, boolean Data) {
		editor.putBoolean(Name, Data);
		editor.commit();
	}

	/*
	 * 获取String数据
	 */
	private static void setString(String Name, String Data) {
		editor.putString(Name, Data);
		editor.commit();
	}

	/*
	 * 获取Int数据
	 */
	private static void setInt(String Name, int Data) {
		editor.putInt(Name, Data);
		editor.commit(); 
	}

	/*
	 * 获取Float数据
	 */
	private static void setFloat(String Name, float Data) {
		editor.putFloat(Name, Data);
		editor.commit();
	}

	/*
	 * 得到Boolean数据
	 */
	private static boolean getBoolean(String Name, boolean Data) {
		return sha.getBoolean(Name, Data);
	}

	/*
	 * 得到String数据
	 */
	private static String getString(String Name, String Data) {
		return sha.getString(Name, Data);
	}

	/*
	 * 得到Int数据
	 */
	private static int getInt(String Name, int Data) {
		return sha.getInt(Name, Data);
	}

	/*
	 * 得到Float数据
	 */
	private static float getFloat(String Name, float Data) {
		return sha.getFloat(Name, Data);
	}

	public static void set(int key, int value) { 
		setInt(key+"", value);
	}
	
	public static int get(int key, int def) {
		return getInt(key+"", def);
	}


}