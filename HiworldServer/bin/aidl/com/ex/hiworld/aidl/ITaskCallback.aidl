package com.ex.hiworld.aidl;
interface ITaskCallback {   
   void getByte(int code,in byte[] bytes, int size, in String strs);  
   void getCmd(int code,in int[] ints, int size, in String strs);  
//   void update(int updateCode, in int[] ints, int size);
   void update(int updateCode, in int[] ints, in float[] flts, in String[] strs);
} 