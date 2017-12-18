package netty.hello;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * 2012年11月10日 下午04:24:20
 */
public class HelloWorldServer {

	public static void main(String[] args) {

		ServerBootstrap bootstrap = new ServerBootstrap();
		
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				// 把输入byte[]转换成string，此时，MessageEvent e的e.getMessage()返回的是String对象
				pipeline.addLast("decoder", new StringDecoder()); 
				
				// 把输出string转换成byte[]，支持e.getChannel().write写入String
				pipeline.addLast("encoder", new StringEncoder());
				
				// 一般只需要在这里修改一下
				pipeline.addLast("handler", new HelloWorldServerHandler());
				
				return pipeline;
			}
		});

		bootstrap.bind(new InetSocketAddress(8787));
		System.out.println("server listen on 8787");
	}
}
