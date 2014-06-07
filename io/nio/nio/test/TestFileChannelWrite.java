package nio.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2014年6月6日 12:22:54
 */
public class TestFileChannelWrite {

	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("test_channel_write.txt",
				"rw");

		FileChannel channel = aFile.getChannel();

		String newData = "New String to write to file..."
				+ System.currentTimeMillis();

		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());

		buf.flip();

		while (buf.hasRemaining()) {
			channel.write(buf);
		}
		
		// channel.force(); // 还有个truncate()方法可以截断文件

		channel.close(); // 必须
		
		aFile.close();

	}
}
