package com.pugwoo.test;

import java.io.UnsupportedEncodingException;

/**
 * 2014年5月8日 下午10:47:45
 */
public class TestStringBinary {

	public static void main(String[] args) throws UnsupportedEncodingException {

		byte[] bytes = new byte[8];
		bytes[0] = (byte)0xfd;
		bytes[1] = (byte)0x00;
		bytes[2] = (byte)0x0f;
		bytes[3] = (byte)0x0f;
		bytes[4] = (byte)0xee;
		bytes[5] = (byte)0xdd;
		
		for(byte b : bytes) {
			System.out.printf("%02x ", b);
		}
		System.out.println();
		
		/**
		 * note: new String(byte[])
		 * is short for new String(byte[], file_encode_charset)
		 */
		String str = new String(bytes, "us-ascii");
		
		System.out.println(str);
		System.out.println(str.length());
		
		/**
		 * note: str.getBytes()
		 * is short for str.getBytes(file_encode_charset); 
		 */
		byte[] bytesInStr = str.getBytes("us-ascii");
		for(byte b : bytesInStr) {
			System.out.printf("%02x ", b);
		}
		System.out.println();
		
		/**
		 * when file is encode in UTF-8, output is:
		 * ef bf bd 00 0f 0f ef bf bd ef bf bd 00 00 
		 * when file is encode in GBK, output is:
		 * 3f 0f 0f ee dd 00 00 
		 */
	}

}
