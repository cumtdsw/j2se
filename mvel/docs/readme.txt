语法指南：http://blog.csdn.net/fhm727/article/details/6543152

MVEL还可以在shell命令行执行：jar -jar mvel2-**.jar  执行的是org.mvel2.sh.Main
(也可以在eclipse中，右键点击jar包，选择对应的Main方法执行)

官方语法文档:http://mvel.codehaus.org/Language+Guide+for+2.0

对于compile和直接eval，是否可以理解为他们是等价的，只是性能有差异？

====
大家选择mvel的原因是它只有900k，不依赖于其它任何jar包
所以mvel可以用来做非常动态的事情