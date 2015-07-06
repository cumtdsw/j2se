英文版：[http://tutorials.jenkov.com/java-nio/index.html]
中文翻译：[http://ifeve.com/java-nio-all/]

【最重要的3个基础概念】Channel Buffer Selector
Channel对应于linux的fd文件描述符概念，可读写。
Buffer用于存放数据
Selector用于监听发生事件的Channel。

【Channel的实现】
FileChannel  对应于文件
DatagramChannel  对应于UDP
SocketChannel  对应于TCP
ServerSocketChannel  对应于TCP监听

【Buffer读写数据的四个步骤】
Buffer和类型对应，有ByteBuffer/CharBuffer/IntBuffer/MappedByteBuffer等
1) 写入数据到Buffer
2) 调用flip()方法
3) 从Buffer中读取数据
4) 调用clear()或者compact()清除buf，clear清空全部，compact只清空已读取的
Buffer的3个属性：capacity position limit

【Scatter分散/Gather聚集】
Scatter: 一个Channel读取到多个顺序的Buffer中，对应于方法buf.read(Buffer[])
Gather: 多个Buffer顺序写入到一个Channel中，对应于方法buf.write(Buffer[])

【Channel对接传输数据】
toChannel.transferFrom(fromChannel, position, count);
        从fromChannel的position位置读取count个字节到toChannel中
fromChannel.transferTo(toChannel, position, count);
        从fromChannel的position位置读取count个字节到toChannel中

【Selector】检测若干个Channel中发生事件的Channel
事件包括：Connect Accept Read Write
这一部分参考net中的nio代码。

与Selector一起使用时，Channel必须处于非阻塞模式下。
这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。
而套接字通道都可以。 // TODO linux中select模型对于文件描述符fd应该不关系是否本地或网络吧?

channel.register(selector, 关系的事件集合)

