事件级别：
TRACE 比DEBUG更细致的信息
DEBUG 调试
INFO 信息
WARN 警告
ERROR 错误
FATAL 致命错误
此外ALL打开所有级别，OFF关闭所有级别

Log4j的主要有三个组成部分：
loggers: 负责捕获日志信息。
appenders : 负责发布日志信息记录到不同的首选目的地。
layouts: 负责以不同的风格格式化日志信息。

2015年8月8日 20:04:01
当配置文件不存在时，ConsoleAppender会attached到root logger.
ConsoleAppender的pattern设置为"%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"

log4j 2.x 不再支持.properties的文件配置方式，推荐用xml
================
SLF是一个通用接口，不提供实现，它使得不同的库类可以兼容不同的log组件。

2016年1月5日 14:46:33
log4j和logback的作者相同，他推荐使用logback。

