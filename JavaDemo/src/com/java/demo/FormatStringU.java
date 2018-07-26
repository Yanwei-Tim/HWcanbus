package com.java.demo;

public class FormatStringU {
	public byte[] codeString2Unicode(String str) {
		if (str == null) {
			str = "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			sb.append("\\u" + Integer.toString(c));
		}
		
		System.out.println("m:" + sb.toString());
		return sb.toString().getBytes();
	}
	
}
