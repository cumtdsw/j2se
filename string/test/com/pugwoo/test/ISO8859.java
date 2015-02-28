package com.pugwoo.test;

import java.io.UnsupportedEncodingException;

/**
 * 2015年2月28日 14:48:35
 * ISO8859就是latin-1编码，数字编码？
 */
public class ISO8859 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] bytes = new byte[256];
		for(int i = 0; i < 256; i++) {
			bytes[i] = (byte) i;
		}
		
		String str = new String(bytes, "ISO-8859-1");
//		System.out.println(str);
		System.out.println("length:" + str.length()); // 256
		
		// ISO-8859-1是唯一一种单个byte单个byte编码的方式
		// 所以下面打印出来的都是数字从0到255
		for(int i = 0; i < 256; i++) {
			System.out.println((int)str.charAt(i));
		}
		
//		String str2 = new String(bytes, "UTF-8");
//		System.out.println(str2.length()); // 253 【注意不是256】
//		System.out.println(str2);
	}
	
}
