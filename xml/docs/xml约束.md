约束技术有两种：DTD和scheme

## 1. DTD (较老，现在少用)

DTD约束的引入方式：
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

对【名称空间】namespace支持好

