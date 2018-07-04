package com.ex.hiworld.server.tools;

/**
 * Created by APP03 on 2018/6/21.
 */

public class ToolTime {
    public int year ;
    public int month;
    public int day;
    public int hour ;
    public int min ;
    public int sec ;
    public int format;

    @Override
    public String toString() {
        return "ToolTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", min=" + min +
                ", sec=" + sec +
                ", format=" + format +
                '}';
    }
}
