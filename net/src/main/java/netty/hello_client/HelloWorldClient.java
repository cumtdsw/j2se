package netty.hello_client;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class HelloWorldClient {

	public static void main(String[] args) {
		
		// 服务类
        ClientBootstrap bootstrap = new ClientBootstrap();
        
        // 线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        
        // socket工程
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
        
        // 管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast("decoder", new StringDecoder()); 
				pipeline.addLast("encoder", new StringEncoder());
				
				pipeline.addLast("myHandler", new HelloWorldClientHandler());
		
				return pipeline;
			}
		});
        
        // 连接服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8787));
		Channel channel = connect.getChannel();
		
		// 往服务端发送点东西
		channel.write("==hello==");
	}
}
