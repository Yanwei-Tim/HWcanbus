package tools;

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
	 * 获取Int数据
	 */
	private static void setInt(String Name, int Data) {
		editor.putInt(Name, Data);
		editor.commit(); 
	}

	/*
	 * 得到Int数据
	 */
	private static int getInt(String Name, int Data) {
		return sha.getInt(Name, Data);
	}

	public static void set(int key, int value) { 
		setInt(key+"", value);
	}
	
	public static int get(int key, int def) {
		return getInt(key+"", def);
	}


}