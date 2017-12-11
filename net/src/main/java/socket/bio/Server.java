package socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2012年3月2日 上午10:27:42
 * http://tech.163.com/06/0410/09/2EBABUD20009159T.html
 * 
 * 单线程-阻塞方式
 */
public class Server {

	private ServerSocket ss;
	private Socket socket;

	public Server(int port) {
		try {
			ss = new ServerSocket(port);
			System.out.println("listening at " + port);

			byte[] buf = new byte[1024];
			// 不停接受客户端请求，单线程，同一时刻只能服务一个客户，另一种方案是采用线程
			// 此时，新来的客户端会阻塞在连接上，直到超时
			while (true) {
				socket = ss.accept(); // 当没有连接时，就阻塞在这里；
				System.out.println("new client connected: " + socket.getRemoteSocketAddress());

				// BufferedReader in = new BufferedReader(new
				// InputStreamReader(socket
				// .getInputStream()));
				// PrintWriter out = new PrintWriter(socket.getOutputStream(),
				// true);
				//
				// // 简单的回显服务器
				// String line = in.readLine();
				// // debug
				// //System.out.println("client say: " + line);
				// // 发送给客户端
				// out.println(line);
				//
				// out.close();
				// in.close();

				InputStream in = socket.getInputStream();
				// 这里read时会阻塞，等待客户端输入，如果客户端一直没有输入，这里就一直等待
				int n = in.read(buf); 
				System.out.println("recv:" + new String(buf, 0, n));
				
				OutputStream out = socket.getOutputStream();
				out.write(buf, 0, n);
				
				// 上面的回显服务器有个注意点：它本质上是读取到回车符，才往客户端写数据
				// 所以，真实的协议，就要设计怎样判断一次请求的数据传完了，再进行处理

				socket.close(); // 一次对话之后，主动结束掉连接
				System.out.println("close client");
			}
		} catch (IOException e) {
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server(8787);
	}
}