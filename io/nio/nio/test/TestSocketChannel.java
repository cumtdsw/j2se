package nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * . // TODO 需要完善
 */
public class TestSocketChannel {

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("www.qq.com", 80));
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = socketChannel.read(buf);
		
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			bytesRead = socketChannel.read(buf);
		}

		socketChannel.close();
	}
}
