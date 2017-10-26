约束技术有两种：DTD和scheme

## 1. DTD (较老，现在少用)

**DTD约束的引入方式：**

本地：
<!DOCTYPE 根元素 SYSTEM "xxx.dtd">
公共网络上：
```
<!DOCTYPE 根元素 PUBLIC "DTD名称" "DTD文档URL">
```

例如：
```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >
```

## 2. scheme (主流)

对【名称空间】namespace支持好，命名空间一般命名是http://域名/，看起来像个url，但它含义不是url

**xsd的引入方式：**

如果使用eclipse的，可以创建一个xml文件，然后选择对应的xsd就可以创建。

xsi:schemaLocation指定了 namespace到schema的对应位置。
schema文件如果是网络上的，那就是完整的url。如果是本地，就是相对路径。
