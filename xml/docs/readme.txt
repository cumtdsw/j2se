dom4j
hibernate采用的xml解析工具
概念：Document Element

2014年8月6日 18:53:55
xml有个很重要的概念：schema，它用来验证xml是否符合xsd文件要求的语义。
这个在spring的xml配置中广泛使用。

命名空间的语法如下：
   xmlns:[prefix]="[url of name]"
其中prefix的名称不能是xml，后面的url只要可以达到全球唯一，就像java的package一样。

xsd文件的编写可以使用工具：XMLSpy

这里有两个工具：
1) eclipse对于带有xsd的xml进行代码提示功能。
  Window->Preferences->XML->XML Catalog->User Specified Entries窗口中,选择Add 按纽 
  然后选择Catalog Entry这个
  然后设置xsd文件位置，Namespace一般自动填好了，就是http://www.sunxin.org/book这样的
  验证一下：打开eclipse的编辑器，就可以试试有没有提示了。
  
2) xml解析工具对于带xsd的xml进行编译器的验证。

【2017年10月24日 18:09:18】
