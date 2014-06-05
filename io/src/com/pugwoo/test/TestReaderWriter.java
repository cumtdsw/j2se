package com.pugwoo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

/**
 * @author nickxie 2012-10-31 下午03:49:27
 */
public class TestReaderWriter extends TestCase {

	public void testFileReader() throws IOException {
		// 可以直接从文件获得FileReader对象
		FileReader fileReader = new FileReader("readme.txt");

		// 读取一个字符，16字节
		int ch = fileReader.read();
		System.out.println(ch);
		System.out.println((char) ch);

		fileReader.close();
	}

	/**
	 * 常用
	 */
	public void testBufferedReader() throws IOException {
		// BufferedReader可以从Reader的子类获得，如FileReader或InputStreamReader
		FileReader fileReader = new FileReader("readme.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String string = bufferedReader.readLine();
		System.out.println(string);

		bufferedReader.close();
	}

	/**
	 * 常用
	 */
	public void testBufferReaderWithEncode() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("readme.txt"), "UTF-8"));

		String string = br.readLine();
		System.out.println(string);

		br.close();
	}
	
	public void testFileWriter() throws IOException {
		FileWriter fileWriter = new FileWriter(new File("test_file_writer.txt"));
		
		fileWriter.write("你好");
		
		fileWriter.flush();
		
		fileWriter.close(); // 注意，这里要关闭才能保证数据完整写入
	}
	
	

}
