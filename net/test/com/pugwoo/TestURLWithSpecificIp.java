package com.pugwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 指定http請求從哪個網卡出去
 * 
 * http://stackoverflow.com/questions/91678/how-can-i-specify-the-local-address-
 * on-a-java-net-urlconnection
 * 
 * 【实验没有成功】
 */
public class TestURLWithSpecificIp {

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

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.baidu.com");

		InetAddress addr = InetAddress.getByAddress(getIp("10.66.124.54"));

		/**
		 * Exception in thread "main" java.lang.IllegalArgumentException: type DIRECT is not compatible with address /10.66.124.54:0
	at java.net.Proxy.<init>(Unknown Source)
	at com.pugwoo.TestURLWithSpecificIp.main(TestURLWithSpecificIp.java:42)
**/
		Proxy proxy = new Proxy(Proxy.Type.DIRECT, new InetSocketAddress(addr,
				0));
		URLConnection conn = url.openConnection(proxy);

		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// 建立实际的连接
		conn.connect();
		// 获取所有响应头字段
		Map<String, List<String>> map = conn.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : map.keySet()) {
			System.out.println(key + "--->" + map.get(key));
		}
		// 定义BufferedReader输入流来读取URL的响应
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String result = "";
		String line;
		while ((line = in.readLine()) != null) {
			result += "/n" + line;
		}

		System.out.println(result);
	}
}
