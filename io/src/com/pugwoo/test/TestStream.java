package com.pugwoo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.TestCase;

/**
 * 2012年10月31日 15:38:31
 */
public class TestStream extends TestCase {

	public void testFileInputStream() throws IOException {
		// readme.txt是在项目的根目录下
		FileInputStream fileInputStream = new FileInputStream("readme.txt");

		byte[] b = new byte[1024];
		int readBytes = fileInputStream.read(b); // 如果读取完了返回-1

		System.out.println("读取的字节数:" + readBytes);

		System.out.println("第一个字节是:");
		System.out.println(b[0]); // 打印出ascii码的数字

		fileInputStream.close();
	}

	public void testFileOuputStream() throws IOException {
		File file = new File("file_output_stream.txt");
		FileOutputStream out = new FileOutputStream(file);
		
		String str = "你好";
		out.write(str.getBytes());
		
		out.close();
	}
	
	/**
	 * 网络读取数据
	 */
	public void testNetworkInputStream() throws IOException {
		InputStream nin = new URL("http://www.tencent.com/").openStream();
		
		// 注意，这里读取不到http头部，所以这里的编码是写死的
		InputStreamReader ireader = new InputStreamReader(nin, "gb2312");
		
		BufferedReader breader = new BufferedReader(ireader);
		String str;
		while ((str = breader.readLine()) != null) {
			System.out.println(str);
		}
		ireader.close();
		nin.close();
	}

}
