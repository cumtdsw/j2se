package com.pugwoo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 2014年6月17日 11:32:22
 * 
 * 指定从哪个网卡出去：
 * http://docs.oracle.com/javase/tutorial/networking/nifs/definition.html
 */
public class TestConnect {
	
	private static byte[] getIp(String strIp) {
		if (strIp != null) {
			String strs[] = strIp.split("\\.");
			if (strs.length == 4) {
				byte ip[] = new byte[] { (byte) Integer.parseInt(strs[0]),
						(byte) Integer.parseInt(strs[1]),
						(byte) Integer.parseInt(strs[2]),
						(byte) Integer.parseInt(strs[3]) };
				return ip;
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		Socket soc = new java.net.Socket();
		
		soc.bind(new InetSocketAddress("10.66.124.54", 0));
		
		System.out.println("connecting");
		soc.connect(new InetSocketAddress("220.181.159.91", 80));
		System.out.println("connect ok");
		
	}
}
