介绍PPT:
http://www.searchtb.com/2011/03/dive_into_netty.html

完整的入门教材：
http://blog.csdn.net/vba_2001/article/details/7307125
服务端程序通常的处理过程是：解码请求数据、业务逻辑处理、编码响应。

概念:
Bootstrap “引导程序”类，初始化channel的辅助类，子类有：
1) ServerBootstrap  服务器端，接收connection请求
2) ClientBootstrap  客户端，发送connectoin请求
3) ConnectionlessBootstrap 对应于UDP

感觉上，netty是一个封装得很好的基于NIO的【事件驱动】网络通信框架。(事件驱动状态机)
我们只需要定义哪个事件发生时应该做什么事情即可。
Channels.pipeline定义了若干个handler，用于处理发生的事件。

【Uptream接收请求】对应于ChannelUpstreamHandler，这一过程相当于接收处理外来请求的过程。
== upstream event有：
messageReceived: 收到消息时触发
exceptionCaught: 异常发生时触发
channelOpen: channel打开时触发，此时还没有bound或connect，这里不应该执行太慢的任务
channelBound: channel Bound到本地地址时触发，还没有connect，这里不应该执行太慢的任务
channelConnected: channel已经连接到远程ip时触发，这里不应该执行太慢的任务
channelInterestChanged: 当channel感兴趣的事件(读写)被修改时触发
channelDisconnected: 当channel和远程机器断开链接时触发
channelUnbound: 和bound对应，unbound时触发
channelClosed: channel关闭时触发
writeComplete: 当写入channel的事件完成时触发
childChannelOpen:
childChannelClosed:

【Downstream发送请求】对应于ChannelDownstreamHandler，这一过程相当于向外发送数据的过程。
 downstream event有：”write”、”bind”、”unbind”、 “connect”、 “disconnect”、”close”

【ChannelPipeline】
UpStream顺序处理
DownStream逆序处理

decoder将字节流byte[]转换成Object，自带的StringDecoder将byte[]转换成String对象
encoder和decoder相反
约定前4个字节为byte[]里的数据长度（不包括这4个字节）

===========================================2014年6月24日 13:02:54========================
一些编程处理技巧：
1) 等待一个channel写完内容之后关闭它：
   channel.write(ChannelBuffers.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
2) channel是双工的，控制channel的是否处于可读状态channel.setReadable(false/true)
