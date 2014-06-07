package nio.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2014年6月5日
 * 
 * FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下。
 */
public class TestFileChannelRead {

	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("readme.txt", "rw");
		
		// 使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例
		FileChannel inChannel = aFile.getChannel();

		// Buffer大小，每次read最多读取buffer大小的字节
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			
			/**
			 * flip看下一些博客的英文的解释吧：
The flip() method switches a Buffer from writing mode to reading mode. Calling flip() sets the position back to 0, and sets the limit to where position just was.
In other words, position now marks the reading position, and limit marks how many bytes, chars etc. were written into the buffer - the limit of how many bytes, chars etc. that can be read.
             * 意思是：buf的limit设置为当前位置，buf的position设置为0，这样去读取buf的时候，就从头开始读取到当前位置为止。
			 */
			buf.flip();

			while (buf.hasRemaining()) {
				/**
				 * buf.get()读取一个字节，并移动position到下一个字节
				 */
				System.out.print((char) buf.get()); // 这里对中文肯定是乱码的
			}

			/**
			 * If you call clear() the position is set back to 0 and the limit to capacity.
			 * In other words, the Buffer is cleared. The data in the Buffer is not cleared.
			 * Only the markers telling where you can write data into the Buffer are.
			 * 相当于逻辑上清空buf
			 */
			buf.clear();
			
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

}
