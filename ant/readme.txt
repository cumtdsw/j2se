命令行下到ant根目录，然后执行ant就可以编译出：build/classes和build/jar目录和文件

使用ant来跑这个jar包：ant run

直接执行jar包：
windows下
java -cp lib\*;build\jar\HelloWorld.jar oata.HelloWorld

linux下：
java -cp lib/*:build/jar/HelloWorld.jar oata.HelloWorld

输出的log文件位置：/tmp/ant_hello.log

===================================================================
2016年1月4日 14:33:32
现在大部分项目都是Maven项目，ant在新项目已经不用了。