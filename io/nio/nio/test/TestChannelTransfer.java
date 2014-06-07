package nio.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Channel对接传输数据 例如：复制文件
 */
public class TestChannelTransfer {

	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size(); // 文件大小

		toChannel.transferFrom(fromChannel, position, count);
		
		fromFile.close();
		toFile.close();
	}

}
