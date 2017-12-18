package netty.hello;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class HelloWorldServerHandler extends SimpleChannelHandler {

	/**
	 * 新客户端连接进来
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// 一般通过e.getChannel()的write方法发送数据
		e.getChannel().write("Welcome!\n");
		
		// 这里可以做一个安全：当同一个ip单位时间连接次数过多时，拒绝连接
		// e.getChannel().close()
		
		// 构造ChannelBuffer的方式
		// ChannelBuffer mybuffer = ChannelBuffers.copiedBuffer("hello".getBytes());
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
			// System.out.println(ctx.getChannel() == e.getChannel()); // true，两种方式拿到都是同一个channel
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// 说明：当messageReceived方法中实现抛出异常时，来到这里
		e.getCause().printStackTrace();
		// e.getChannel().close(); // 出错时关闭channel
	}
	
	/**
	 * 只有connected有了，关闭时才有disconnect。
	 * disconnected之后才close channel
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// 可以用于清理资源，例如缓存或上下文等
	}
	
	/**
	 * channel关闭的时候，一定会close
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		
	}
}
