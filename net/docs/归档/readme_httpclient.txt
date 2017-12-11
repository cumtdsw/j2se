Http client: http://hc.apache.org/httpclient-3.x/
用于模拟网络有登录有session/cookie的

在下载的压缩包里面有大量的例子。

httpClient 4.x支持保存cookie上下文：
可以参考：http://sb33060418.iteye.com/blog/2020007

httpclient本身就有维护cookie的，
在同一个http client下execute两次请求，cookie是有的，也会更新的。

// TODO 设计Net2，它等同于browser，同时支持修改cookie