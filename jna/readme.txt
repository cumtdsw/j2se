https://github.com/twall/jna

开始教程：https://github.com/twall/jna/blob/master/www/GettingStarted.md

直接调用dll或so

*****对于Linux******
例子一： Hello World
cd /some/path/bin
java -cp .:../lib/* com/sun/jna/examples/HelloWorld

例子二：CustomSoTest
先进入linux_so后make生成libhelloworld.so
export LD_LIBRARY_PATH=/home/path/to/so:$LD_LIBRARY_PATH
cd /some/path/bin
java -cp .:../lib/* com/sun/jna/examples/CustomSoTest