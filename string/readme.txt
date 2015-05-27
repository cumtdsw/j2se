2011-11-3

String是不可变的

字符串运算符“+”原理：使用StringBuilder

【这两个类初始化时最好指定容量，可获得45%的性能提升】
StringBuffer 线程安全
StringBuilder 线程不安全【更快】

正则表达式是强大的工具，有时可以很方便地提取一些pattern里的信息。

关于Java中的字符编码：写得很好
http://www.ibm.com/developerworks/cn/java/j-lo-chinesecoding/

========================================
关于字符串的操作，在实际项目中，经常用apache commons对字符串的工具类StringUtils
详见j2se/apache_commons项目