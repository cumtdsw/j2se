package com.pugwoo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 2012年2月23日 21:28:16
 * 
 * @author Pugwoo
 * 
 *         用户输入一个字符串，返回该字符串的大写形式。
 *         !! 接受一个字符串返回后自动关闭连接（为了jmeter测试）
 *         
 * 测试点：
 * 1）业务是否正常，telnet上之后，输入字符，服务器要返回对应字符
 * 2）多人telnet上是否正常？客户异常退出telnet后之后服务器是否正常，是否影响其它客户？
 */
public class NIOEchoServer {

	private Selector selector;

	public NIOEchoServer(int port) throws IOException {
		// 获得server的socketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));

		// 通过open()方法找到Selector，默认在JDK6+Linux2.6+上是用Epoll模型
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		System.out.println("Server started successfully at port: " + port);
	}

	// 监听，不停地接受客户端连接和处理读写事件
	public void listen() throws IOException {
		while (true) {
			// 阻塞，直到有事件发生
			// 【还有另一种方式让selector醒来：另外一个线程调用该selector.wakeUp()方法】
			selector.select();
			
			// 返回此选择器的已选择键集。
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while (keyIter.hasNext()) {
				SelectionKey selectionKey = keyIter.next();

				// 一定要删除，否则selector会陷入无尽的循环中，或者其它错误
				keyIter.remove();
				
				// 连接新Client并加入监听
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) selectionKey
							.channel();
					SocketChannel client = server.accept();
					// client可能为null(如果忘记设置server为非阻塞时)
					if (client != null) {
						System.out.println("new client connected: "
								+ client.socket().getRemoteSocketAddress());
						client.configureBlocking(false);
						// 注册到selector，等待数据
						// 【特别注意】：这里不能关注OP_WRITE，因为我们确实还没有数据要写，除非是要向用户输出一些欢迎信息
						// 【只有数据可以写时才注册OP_WRITE操作】
						// 避免死锁的一个简单方法就是不要在同一个socket同时注册多个操作。不要同时注册OP_READ和OP_WRITE，要么只注册OP_READ，要么只注册OP_WRITE。
						SelectionKey key = client.register(selector, SelectionKey.OP_READ);
						key.attach(ByteBuffer.allocate(128));
					}
				} else {
					handleClient(selectionKey);
				}
			}
		}
	}

	// 处理client的读写操作
	protected void handleClient(SelectionKey selectionKey) {
		SocketChannel client = (SocketChannel) selectionKey.channel();
		
		try {
			// 读
			if (selectionKey.isReadable()) {
				System.out.println("is readable");
				ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
				int count = client.read(buffer);
				if (count > 0) {
					System.out.println("recv-->"
							+ new String(buffer.array(), 0, count, "UTF-8"));

					buffer.flip();
					// 等待写入
					selectionKey.interestOps(SelectionKey.OP_WRITE);
				}
			}
			// 写
			if (selectionKey.isWritable()) {
				System.out.println("is writable");

				ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
				int count = client.write(buffer);
				System.out.println("write count:" + count);

				// 写一个之后就关闭链接，【用于jmeter测试】
//				if (count > 0) {
//					selectionKey.cancel();
//					client.close();
//				    return;
//				}

				if (buffer.remaining() == 0) { // write finished, switch to
												// OP_READ
					buffer.clear();
					selectionKey.interestOps(SelectionKey.OP_READ); // 等待读取
				}
			}
		} catch (IOException e) { // CancelledKeyException
			/**
			 * selectionKey.isReadable()和selectionKey.isWritable()
			 * 在客户端强制退出情况下，抛出CancelledKeyException异常
			 */
			e.printStackTrace();
			try { // 远程主机强迫关闭了一个现有的连接。
				selectionKey.cancel();
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		NIOEchoServer server = new NIOEchoServer(8787);
		server.listen();
	}

}
