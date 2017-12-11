2017年12月11日 19:22:05

源代码目录说明：

1. basic 中关于net的基本操作已经封装到https://github.com/pugwoo/woo-utils中。

2. httpClient 废弃，已经使用jdk httpConnection来代替，封装在woo-utils的Browser中。
              httpClient中可以指定网络从哪个网卡走出去，这个可以参考。

3. mina 废弃，现在已经用netty代替。

4. proxy.http 废弃，这个是网上找到的一个简单的http proxy代理服务器代码，有点问题。

5. socket 关于socket编程的基本demo。主要有：单线程阻塞、多线程、单线程io复用

6. 