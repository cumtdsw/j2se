package com.pugwoo.test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 2015年7月6日 17:56:26 
 * 在linux中，管道非常常用，在命令行用|把一个程序的outputstream转成另一个程序的inputstream
 */
public class TestPipeOutputstreamInputstream {

	public static void main(String[] args) throws IOException {
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream(in); // 所有写入到out的数据可以从in读取出来
		
		out.write("hello".getBytes());
		
		byte[] bytes = new byte[1024];
		in.read(bytes);
		System.out.println(new String(bytes));
		
		out.close();
	}
}
