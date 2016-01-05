package nio.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 2014年6月6日 17:30:46 管道Pipe是单向的，用于线程之间的传输数据（当然同个线程读写也没问题）
 */
public class TestPipe {

	public static void main(String[] args) throws IOException {

		Pipe pipe = Pipe.open();

		// SinkChannel向pipe里面写入数据
		Pipe.SinkChannel sinkChannel = pipe.sink();
		String newData = "New String to write to file..."
				+ System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());

		buf.flip();

		while (buf.hasRemaining()) {
			sinkChannel.write(buf);
		}
		
		// SourceChannel从pipe中读取数据
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf2);
		
		System.out.println("read bytes:" + bytesRead);
		System.out.println("read -->"
				+ new String(buf2.array(), 0, bytesRead, "UTF-8"));
	}

}
