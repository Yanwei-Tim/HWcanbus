package com.ex.hiworld.server.canbus;


/**
 * Created by APP03 on 2018/6/9.
 */

public class DataCanbus {
	public static int DATA[] = new int[FinalCanbus.U_CNT_MAX];
	public static String DATA_String[] = new String[FinalCanbus.U_CNT_MAX];


	public static String sVersionCanbox = "";
	public static int canbusId, canbusLevel, canbusType;
	public static boolean isHead5A = true; // 5AA5 or AA 55
	public static boolean isEnterUpdateMode;
	public static boolean sExistFullView = false;
	public static boolean sExistFullViewFloatBtn = false; 

	public static final int TEMP_NONE = -1;
	public static final int TEMP_LOW = -2;
	public static final int TEMP_HIGH = -3;
}
