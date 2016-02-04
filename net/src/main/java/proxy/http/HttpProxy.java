package proxy.http;

/*************************************
 * 一个基础的代理服务器类
 * http://www.sadtojoy.com/aspx/Detail.aspx?id=4069
 *************************************
 * 
 * 注：这份程序原始是有问题的，可能出现卡死。（read阻塞，只能靠TIMEOUT来解）
 * TIMEOUT是个两难的设置，一方面它防止了阻塞，同时它又要求客户端输入速度要够快。
 * 
 * 为了解决这个问题，去掉TIMEOUT，引入了线程。如果要求性能高，这里可以改成NIO。TODO
 *
 * 测试http代理是否生效：
 * telnet ip port
 * 然后输入GET http://www.baidu.com/ HTTP/1.1
 * 
 * 2014年6月17日 12:23:44
 * 发现问题：由于这个程序的逻辑是从链接的第一个GET命令中获得remote的sokect ip和端口
 * 此后的链接都是用这个socket，但是一个链接可能有多个GET命令，当断开不同时，socket就无法公用。
 * 为此，【临时解决方法】：客户端不要用“Reuse Server Connection”。
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class HttpProxy extends Thread {
	
	static public int CONNECT_RETRIES = 5;
	static public int CONNECT_PAUSE = 5;
	static public int TIMEOUT = 50; // ms
	static public int BUFSIZ = 1024;
	static public boolean logging = false;
	static public OutputStream log = null;
	
	// 传入数据用的Socket
	protected Socket socket;
	
	// 上级代理服务器，可选
	static private String parent = null;
	static private int parentPort = -1;
	
	// 指定出口网卡的ip地址，可选
	static public String specificIp = null;

	static public void setParentProxy(String name, int pport) {
		parent = name;
		parentPort = pport;
	}

	// 在给定Socket上创建一个代理线程。
	public HttpProxy(Socket s) {
		socket = s;
		start();
	}

	public void writeLog(int c, boolean browser) throws IOException {
		log.write(c);
	}

	public void writeLog(byte[] bytes, int offset, int len, boolean browser)
			throws IOException {
		for (int i = 0; i < len; i++)
			writeLog((int) bytes[offset + i], browser);
	}

	// 默认情况下，日志信息输出到
	// 标准输出设备
	// 派生类可以覆盖它
	public String processHostName(String url, String host, int port, Socket sock) {
		java.text.DateFormat cal = java.text.DateFormat.getDateTimeInstance();
		System.out.println(cal.format(new java.util.Date()) + " - " + url + " "
				+ sock.getInetAddress() + "\n");
		return host;
	}

	// 执行操作的线程
	public void run() {
		
		System.out.println("start thread: socket " + socket.getInetAddress()
				+ ":" + socket.getPort());
		
		String line;
		String host;
		int port = 80;
		Socket outbound = null;
		try {
//			socket.setSoTimeout(TIMEOUT);
			InputStream is = socket.getInputStream();
			OutputStream os = null;
			try {
				// 获取请求行的内容
				line = "";
				host = "";
				int state = 0;
				boolean space;
				
				boolean isEnd = false;
				// 2014年6月16日 16:49:24 fix bug：pipe結束應該等於該socket處理完成。否則is.read()阻塞
				
				while (true) {
					int c = is.read();
					if (c == -1)
						break;
					if (logging)
						writeLog(c, true);
					space = Character.isWhitespace((char) c);
					switch (state) {
					case 0:
						if (space)
							continue;
						state = 1;
					case 1:
						if (space) {
							state = 2;
							continue;
						}
						line = line + (char) c;
						break;
					case 2:
						if (space)
							continue; // 跳过多个空白字符
						state = 3;
					case 3:
						if (space) {
							state = 4;
							// 只取出主机名称部分
							String host0 = host;
							int n;
							n = host.indexOf("//");
							if (n != -1)
								host = host.substring(n + 2);
							n = host.indexOf('/');
							if (n != -1)
								host = host.substring(0, n);
							// 分析可能存在的端口号
							n = host.indexOf(":");
							if (n != -1) {
								port = Integer.parseInt(host.substring(n + 1));
								host = host.substring(0, n);
							}
							host = processHostName(host0, host, port, socket);
							if (parent != null) {
								host = parent;
								port = parentPort;
							}
							
							System.out.println("remote " + host + ":" + port);
							
							int retry = CONNECT_RETRIES;
							while (retry-- != 0) {
								try {
									outbound = new Socket();
									if(specificIp != null && !specificIp.isEmpty()) {
										outbound.bind(new InetSocketAddress(specificIp, 0));
									}
									outbound.connect(new InetSocketAddress(host, port));
									System.out.println("connect ok:" + host + ":" + port);
									break;
								} catch (Exception e) {
									e.printStackTrace();
								}
								// 等待
								Thread.sleep(CONNECT_PAUSE);
							}
							if (outbound == null)
								break;
//							outbound.setSoTimeout(TIMEOUT);
							os = outbound.getOutputStream();
							os.write(line.getBytes());
							os.write(' ');
							os.write(host0.getBytes());
							os.write(' ');
							pipe(is, outbound.getInputStream(), os, socket
									.getOutputStream());
							
							// 這裏有bug，break是switch的break，如果pipe結束了，應該斷開客戶端。
							isEnd = true;
							break;
						}
						host = host + (char) c;
						break;
					}
					
					if(isEnd) {
						break;
					}
				}
			} catch (IOException e) {
			}

		} catch (Exception e) {
		} finally {
			try {
				socket.close();
			} catch (Exception e1) {
			}
			try {
				outbound.close();
			} catch (Exception e2) {
			}
		}
		
		System.out.println("client socket " + socket.getInetAddress()
				+ ":" + socket.getPort() + " done!");
	}

	/**
	 * 将is0的输入对接到os0；将is1的输入对接到os1
	 * @param is0
	 * @param is1
	 * @param os0
	 * @param os1
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	void pipe(final InputStream is0, final InputStream is1, final OutputStream os0,
			final OutputStream os1) throws InterruptedException {
		/**
		 * 这里改成线程的方式 2014年6月16日 16:32:20
		 */
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int ir;
				byte bytes[] = new byte[BUFSIZ];
				while (true) {
					try {
						if ((ir = is0.read(bytes)) > 0) {
							os0.write(bytes, 0, ir);
							if (logging)
								writeLog(bytes, 0, ir, true);
						} else if (ir < 0) {
							break;
						}
					} catch (SocketException e) {
						if(logging) {
						    e.printStackTrace(); // Software caused connection abort: socket write error
						}
						break;
					} catch (IOException e) {
						if(logging) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int ir;
				byte bytes[] = new byte[BUFSIZ];
				while (true) {
					try {
						if ((ir = is1.read(bytes)) > 0) {
							os1.write(bytes, 0, ir);
							if (logging)
								writeLog(bytes, 0, ir, false);
						} else if (ir < 0) {
							break;
						}
					} catch (SocketException e) {
						if(logging) {
							e.printStackTrace(); // Software caused connection abort: socket write error
						}
						break;
					} catch (IOException e) {
						if(logging) {
							e.printStackTrace();
						}
					}
				}
			}
		});
			
		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public void startProxy(int port, Class clobj) {
		ServerSocket ssock = null;
		try {
			ssock = new ServerSocket(port);
			while (true) {
				Class[] sarg = new Class[1];
				Object[] arg = new Object[1];
				sarg[0] = Socket.class;
				try {
					java.lang.reflect.Constructor cons = clobj
							.getDeclaredConstructor(sarg);
					arg[0] = ssock.accept();
					cons.newInstance(arg); // 创建HttpProxy或其派生类的实例
				} catch (Exception e) {
					Socket esock = (Socket) arg[0];
					try {
						esock.close();
					} catch (Exception ec) {
					}
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				if(ssock != null)
					ssock.close();
			} catch (IOException e) {
			}
		}
	}

	// 测试用的简单main方法
	static public void main(String args[]) {
		System.out.println("在端口8080启动代理服务器\n");
		HttpProxy.log = System.out;
		HttpProxy.logging = false;
		
		// 走代理
//		HttpProxy.parent = "proxy.tencent.com";
//		HttpProxy.parentPort = 8080;
		
		// 走指定网卡
//		HttpProxy.specificIp = "10.66.124.54";
		
		HttpProxy.startProxy(8080, HttpProxy.class);
	}
}
