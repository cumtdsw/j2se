package com.pugwoo.test;

/**
 * 2015年2月28日 14:32:06
 */
public class ValueOf {

	public static void main(String[] args) {
		char[] chars = new char[] {'h', 'e', 'l', 'l', 'o'};
		System.out.println(String.valueOf(chars)); // 这个valueOf对char[]很好用
		
		char c = 0x000A;
		System.out.println(c);
	}
}
