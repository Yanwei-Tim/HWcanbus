package com.ex.canbus;

/**
 * Created by APP03 on 2018/6/22.
 */

public class FinalCanbus {
    // 注意所有CMD_CODE都在同一个区域内，不要越界
    public static final int C_MISC_BEGIN					= 1000;




    // 注意所有UPDATE_CODE都在同一个区域内，不要越界
    public static final int U_MISC_BEGIN					= 1000;
    public static final int U_CANBUS_ID						= U_MISC_BEGIN+0;


    public static final int U_CNT_MAX = 2000;
}
