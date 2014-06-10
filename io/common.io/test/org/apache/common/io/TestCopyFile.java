package test.org.apache.common.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * 4种方式复制文件：
 * http://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file
 * -in-java/
 * 
 * 根据这篇博客的测试，Channel复制文件是最快的。
 * 
 * 2014年6月9日 10:24:34
 */
public class TestCopyFile {

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("fromFile.txt");
		FileOutputStream outputStream = new FileOutputStream("toFile.txt");
		
		// 大文件则使用copyLarge
		int bytes = IOUtils.copy(inputStream, outputStream);
		System.out.println("copy bytes:" + bytes);
		
		IOUtils.closeQuietly(outputStream); // 必须
	}

}
