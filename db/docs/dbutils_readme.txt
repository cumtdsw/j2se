2011-11-13
DBUtils是开源中国社区推荐的类ORM数据库操作工具
http://commons.apache.org/dbutils/

概念：
ResultSetHandler 负责将ResultSet转变成各种形式的对象
QueryRunner是每次都会调用到的实例，【线程安全】【根据oschina，可以只有一个实例】

apache DBCP和Pool
数据库连接池，DBCP依赖于Pool，Tomcat的数据源也是使用DBCP

注：如果使用Spring+DBUtils，则可以用Spring的DataSource

如果使用Oracle，则QueryRunner初始化用参数true：new QueryRunner(true);

关于【中文乱码】问题：
有2个地方需要注意一下：
1）数据库和表必须确认是哪种编码，一般选utf8编码
2）数据库链接url加上：?useUnicode=true&characterEncoding=utf8


2014年12月2日 17:07:08
dbutils是非常不错的设计，现在spring jdbcTemplate和dbutils的用法非常相似。
所以如果结合了spring的话就用spring jdbcTemplate就行。

2015年1月19日 11:38:51
dbutils对自定义列名的映射封装的没有spring jdbcTemplate直观。
就目前来看，数据库的字段名最好和java的字段名一样。但这样不够友好。