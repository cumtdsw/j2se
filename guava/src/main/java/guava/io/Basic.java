package guava.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

public class Basic {
	
	/**
	 * 实用：把文件中的文本并解析为行
	 */
	@Test
	public void testReadLines() throws Exception {
		// 如果文件不存在，会抛出FileNotFoundException
		// 读出来的数据不包括回车符
		/**
		 * 注意，对于大文件，该方法会占用过多内存，小心
		 */
		List<String> lines = Files.readLines(new File("C:/a.txt"), Charsets.UTF_8);
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
	/**
	 * 将文件所有内容读出来，放到byte[]中
	 */
	@Test
	public void testReadBinary() throws Exception {
		// 对于一个文件
		byte[] bytes = Files.toByteArray(new File("C:/a.txt"));
		System.out.println("read bytes:" + bytes.length);
		// 对于输入流
//		byte[] bytes = ByteStreams.toByteArray(in);
	}
	
	/**
	 * 写文件
	 * 【强烈注意】如果要写的文件不存在，会抛异常，此时要先调用Files.createParentDirs(file);
	 * @throws Exception
	 */
	@Test
	public void testWriteFile() throws Exception {
		File file = new File("C:/p/a.txt");
		Files.createParentDirs(file);
		Files.write("test", file, Charsets.UTF_8);
		// 注意，write会全量更新这个文件，如果要追加，则用append那个
	}

	@Test
	public void testClose() throws Exception {
		String str = "hello";
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		Closeables.closeQuietly(in); // 安静地关掉
	}
	
}
