介绍PPT:
http://www.searchtb.com/2011/03/dive_into_netty.html


完整的入门教材：
http://blog.csdn.net/vba_2001/article/details/7307125
服务端程序通常的处理过程是：解码请求数据、业务逻辑处理、编码响应。

概念:
Bootstrap “引导程序”类，初始化channel的辅助类，子类有：
1) ServerBootstrap  接收connection请求
2) ClientBootstrap  发送connectoin请求
3) ConnectionlessBootstrap

Uptream接收请求，对应于ChannelUpstreamHandler，这一过程相当于接收处理外来请求的过程。
upstream event有：”messageReceived”、 “exceptionCaught”、”channelOpen”、”channelClosed”、 “channelBound”、”channelUnbound”、 “channelConnected”、”writeComplete”、”channelDisconnected”、”channelInterestChanged”。

Downstream发送请求，对应于ChannelDownstreamHandler，这一过程相当于向外发送数据的过程。
 downstream event有：”write”、”bind”、”unbind”、 “connect”、 “disconnect”、”close”

【ChannelPipeline】
UpStream顺序处理
DownStream逆序处理

decoder将字节流byte[]转换成Object，自带的StringDecoder将byte[]转换成String对象
encoder和decoder相反
约定前4个字节为byte[]里的数据长度（不包括这4个字节）
