package com.pugwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * 2012年11月10日 下午04:24:20
 */
public class TestURL {
	
	// 旧的方式，一个字节一个字节读取
	@SuppressWarnings("unused")
	private static void readByteByByte(InputStream in) throws IOException {
		int c;
		while ((c = in.read()) != -1)
			System.out.print((char) c);
	}

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com");
		
		@SuppressWarnings("unused")
		Proxy proxy  = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",
				8888));
		
		URLConnection conn = url.openConnection(); // proxy
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		
		InputStream nin = conn.getInputStream();
		// 简单的可以写成 InputStream nin = url.openStream();
		
		InputStreamReader ireader = new InputStreamReader(nin, "gb2312");
		BufferedReader breader = new BufferedReader(ireader);
		String line = null;
		while ((line = breader.readLine()) != null) {
			System.out.println(line);
		}
		nin.close();
	}

}
