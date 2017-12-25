package basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 端口扫描工具
 */
public class TestScanPort {
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(1000);

	public static void main(String[] args) {
		final String ip = "127.0.0.1";
		for(int i = 1; i <= 65535; i++) {
			final int port = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					Socket socket = new Socket();
					try {
						socket.connect(new InetSocketAddress(ip, port), 3000);
						System.out.println(port);
					} catch (IOException e) {
					} finally {
						try {
							socket.close();
						} catch (IOException e) {
						}
					}
				}
			});
		}
	}
	
}
