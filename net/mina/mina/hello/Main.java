package mina.hello;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.DatagramAcceptor;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 来自http://jim19770812.blogspot.com/2012/02/minaecho-server.html
 * 
 * @author Administrator
 *
 */
public class Main {

	private static final int TCP_PORT = 50012;
	private static final int UDP_PORT = 50013;

	public static void main(String[] args) throws Exception {

		SocketAcceptor tcpAcceptor = new NioSocketAcceptor();
		tcpAcceptor.setReuseAddress(true);

		DefaultIoFilterChainBuilder tcpChain = tcpAcceptor.getFilterChain();
		tcpChain.addLast("codec", new ProtocolCodecFilter(
				new TextLineCodecFactory()));

		tcpAcceptor.setHandler(new EchoHandler());
		tcpAcceptor.bind(new InetSocketAddress(TCP_PORT));
		System.out.printf("在端口%d开始监听TCP通讯\n", TCP_PORT);

		DatagramAcceptor udpAcceptor = new NioDatagramAcceptor();

		DefaultIoFilterChainBuilder udpChain = udpAcceptor.getFilterChain();
		udpChain.addLast("codec", new ProtocolCodecFilter(
				new TextLineCodecFactory()));

		udpAcceptor.setHandler(new EchoHandler());
		udpAcceptor.bind(new InetSocketAddress(UDP_PORT));
		System.out.printf("在端口%d开始监听UDP通讯\n", UDP_PORT);

		while (true) {
			// System.out.println("R: " +
			// acceptor.getStatistics().getReadBytesThroughput() +", W: " +
			// acceptor.getStatistics().getWrittenBytesThroughput());
			Thread.sleep(3000);
		}
	}
}
