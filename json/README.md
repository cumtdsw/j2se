# 关于JSON工具的选择

### 推荐jackson(from 2017)

知乎关于jackson和fastjson的比较：https://www.zhihu.com/question/44199956

Spring也默认支持jackson，国际上也是jackson主流。

jackson，能对空字符串的json转成null的object

### gson

不再推荐gson，因为它违背java的基本规则，不是通过public的getter获取数据；
需要new一个Gson，此外gson的性能并不高。

### fastjson(不再推荐)

fastjson（from 2012）:

- 支持非严格json：map{}的属性名称不需要双引号，字符串可以用单引号
- 能对空字符串的json转成null的object
- 可以统一配置输出格式
- 使用`@JSONField`注解配置序列化输入和输出的名称和格式
