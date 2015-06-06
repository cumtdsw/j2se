语法指南：http://blog.csdn.net/fhm727/article/details/6543152
中文手册：http://www.doc88.com/p-1774304183043.html

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
# : 可以按字符串的方式拼接数字，例如1 # 2就等于12
in : 判断对象是否在集合中，例如 a in list

【注意】在mvel中，如果
import XXX.AAA; 
引入了AAA这个类之后，
AAA本身就代表这个类的class了。
如果要获得变量的类名，这个有点特殊：someVar.class.getName() 或 someVar.getClass().getName()

===
mvel的if和for while语句的语法都和java一致，太牛了!
也支持条件语句, ? : 
mvel支持foreach(变量:集合)，这个和for有点相似，还支持字符串、数字的遍历，例如：
foreach(x:9) {System.out.println(x);} 输出1到9

===
mvel支持定义函数，def,这个功能非常实用也非常重要

mvel可以在context那里import，这里的好处可以重命名import的类名。
当然直接script中import XXX;也是可以的。
但是script不支持static import，只能在代码里import静态方法或变量，很实用：
http://stackoverflow.com/questions/4349519/cant-import-static-method-using-mvel

