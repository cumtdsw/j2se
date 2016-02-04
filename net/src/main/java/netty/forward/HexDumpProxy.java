/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.forward;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 这个程序的作用： 
 * 将远程的remoteHost:remotePort端口映射到本机的localPort断开
 * 
 * 也就是：连接本机的localPort完全等价于在本机连接remoteHost:remotePort
 * 
 * 实现原理：
 * 服务器监听在本机localPort端口，
 * 当channelOpen打开时，先把e.getChannel()【记为inboundChannel】设置为setReadable(false)
 * 然后创建ClientBootstrap去连接remoteHost:remotePort
 * 这个ClientBootstrap的Handler处理内容：当收到消息时，将内容写入inboundChannel（这里需全局sync）
 * 
 * 注：这里的setReadable的控制是细节。
 * 
 * 注：同一个ClientSocketChannelFactory是可以被多个ClientBootstrap共用的
 */
public class HexDumpProxy {

    private final int localPort;
    private final String remoteHost;
    private final int remotePort;

    public HexDumpProxy(int localPort, String remoteHost, int remotePort) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public void run() {
        System.err.println(
                "Proxying *:" + localPort + " to " +
                remoteHost + ':' + remotePort + " ...");

        // Configure the bootstrap.
        Executor executor = Executors.newCachedThreadPool();
        ServerBootstrap sb = new ServerBootstrap(
                new NioServerSocketChannelFactory(executor, executor));

        // Set up the event pipeline factory.
        ClientSocketChannelFactory cf =
                new NioClientSocketChannelFactory(executor, executor);

        sb.setPipelineFactory(
                new HexDumpProxyPipelineFactory(cf, remoteHost, remotePort));

        // Start up the server.
        sb.bind(new InetSocketAddress(localPort));
    }

    public static void main(String[] args) throws Exception {
        // Validate command line options.
        if (args.length != 3) {
            System.err.println(
                    "Usage: " + HexDumpProxy.class.getSimpleName() +
                    " <local port> <remote host> <remote port>");
            return;
        }

        // Parse command line options.
        int localPort = Integer.parseInt(args[0]);
        String remoteHost = args[1];
        int remotePort = Integer.parseInt(args[2]);

        new HexDumpProxy(localPort, remoteHost, remotePort).run();
    }
}
