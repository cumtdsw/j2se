# 关于fastjson的常用使用技巧

- 使用`@JSONField`注解配置序列化输入和输出的名称和格式

# 关于JSON工具的选择

### fastjson

推荐fastjson（from 2012）:

- 支持非严格json：map{}的属性名称不需要双引号，字符串可以用单引号

- 能对空字符串的json转成null的object

- 可以统一配置输出格式

### 备选

jackson，能对空字符串的json转成null的object

### 不推荐

不再推荐gson，因为它违背java的基本规则，不是通过public的getter获取数据；
需要new一个Gson，此外gson的性能并不高。