package com.hiworld.canbus.util;

public class ChangeDataUtil {
	
	/**
	 * YouZi_Service
	 * @title: bytes2Ints 
	 * @describe: 16进制转换成整形数组
	 * @param bytes
	 * @return
	 * @return: int[] 
	 * @Copyright: Hiworld®
	 * @Author: Hardy.lai
	 * @Date: Aug 12, 2016
	 */
	public static int[] bytes2Ints(byte[] bytes){
		int[] ints = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			ints[i] = (int) bytes[i];
		}
		return ints;
	}
	
	public static int[] hex2Ints(byte[] bytes){
		int[] ints = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			ints[i] =  (bytes[i] & 0xff);
		}
		return ints;
	}
	
	public static byte[] ints2Bytes(int[] ints){
		byte[] bytes = new byte[ints.length];
		for (int i = 0; i < ints.length; i++) {
			bytes[i] = (byte) ints[i];
		}
		return bytes;
	}
	
	public static byte[] ints2Bytes(int[] ints, int index){
		byte[] bytes = new byte[ints.length-index];
		for (int i = index; i < ints.length; i++) {
			bytes[i-index] = (byte) ints[i];
		}
		return bytes;
	}
}
