package netty.hello;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class HelloWorldServerHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// 一般通过e.getChannel()的write方法发送数据
		e.getChannel().write("Welcome!\n");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		
		// 通过e.getMessage()获得数据,如果有decoder，这个是由decoder解码后返回的Object

		String msg = (String) e.getMessage();
		System.out.println("read msg:" + msg);
		
		/**
		 * netty默认会创建2倍cpu核数的worker线程
		 * 如果这里sleep2秒，开8个worker线程的话，tps就只有4
		 * 
		 * 换句话说，如果messageReceived不是异步IO也不是CPU密集型的处理的话，要小心。
		 */
		//System.out.println(Thread.currentThread().getName());
		//Thread.sleep(2000);

		if(msg != null && msg.startsWith("bye")) {
			e.getChannel().close();
		} else {
			e.getChannel().write(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close(); // 出错时关闭channel
	}
}
