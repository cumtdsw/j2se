语法指南：http://blog.csdn.net/fhm727/article/details/6543152

MVEL还可以在shell命令行执行：jar -jar mvel2-**.jar  执行的是org.mvel2.sh.Main
(也可以在eclipse中，右键点击jar包，选择对应的Main方法执行)

官方语法文档:http://mvel.codehaus.org/Language+Guide+for+2.0

对于compile和直接eval，是否可以理解为他们是等价的，只是性能有差异？

====
大家选择mvel的原因是它只有900k，不依赖于其它任何jar包
所以mvel可以用来做非常动态的事情

mvel操作符：http://mvel.codehaus.org/MVEL+2.0+Operators
中文版：http://blog.csdn.net/chs_jdmdr/article/details/7523537
判断一个变量是否存在：isdef a
字符串正则表达式比较：'hello' ~= '[a-z]+'   左边是字符串，右边是正则表达式
链式或：a or b  返回第一个非null的值，这个挺实用
+ : 可以拼接字符串
in : 判断对象是否在集合中，例如 a in list

【注意】在mvel中，如果
import XXX.AAA; 
引入了AAA这个类之后，
AAA本身就代表这个类的class了。