https://github.com/ahmetalpbalkan/orman

JFinal的DAO就是参考这个orman的方式，让model继承Model<T>以实现save/update等方法

支持MySQL, SQLite and Android

一对一、一对多、多对多
https://github.com/ahmetalpbalkan/orman/wiki/Managing-relationships-%26-using-other-entities-as-fields

2014年12月2日 17:38:31
让DO去继承一个对象以实现insert update等数据库能力，
这种方式非常不好，破坏了DO作为java bean的简单，属于强入侵式。