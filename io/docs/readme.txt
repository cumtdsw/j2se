Java IO分两种：
1) stream字节流InputStream/OutputStream，面向的是二进制byte
2) 字符reader/writer，面向的字符char，字符集是unicode
后者适合于处理文字，前者适合处理二进制文件或二进制数据流。

主要有2种，一种面向字节(8字节)，一种面向字符(Unicode)
1) 凡是Stream结尾的是面向字节，如InputStream/OutputStream
2) 凡是Reader/Writer结尾的是面向字符

InputStreamReader将InputStream转换成Reader
OutputStreamWriter将OutputStream转换成Writer

Apache Commons IO
http://commons.apache.org/io/

参考文档：http://www.ibm.com/developerworks/cn/java/j-lo-javaio/