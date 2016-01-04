线程池：非常使用，可以很方便的做成多线程程序来跑任务。

Runnable和Callable的区别：
Runnable实现void run()方法，没有返回值也不能抛出checked exception;
Callable实现V call()方法，可以抛出Exception。

线程池的实现思路：
1) 使用一个BlockingQueue存放Runnable对象，N个进程共享这个queue
   继承Thread类写一个ThreadPool类，它的任务是拿queue的Runnable对象并执行run()方法。
   然后这个线程池先生成若干个Thread(ThreadPool)，让它们start()起来就ok了。
实现参考：http://tutorials.jenkov.com/java-concurrency/thread-pools.html

这里有一个体会：
Thread(new Runnable(){})对象的start()执行的东西，就等价于
一个线程中执行那个Runnable对象的run()方法。