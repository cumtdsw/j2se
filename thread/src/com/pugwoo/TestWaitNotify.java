package com.pugwoo;

/**
 * 2013年10月16日 23:34:09
 * 注意：调用某个对象obj的wait()和notify()时，必须synchronized
 * 
 * synchronized有两种方式：
 * 1) synchronized(obj){obj.wait/notify();}
 * 2) 在调用了wait/notify的整个方法声明上加synchronized关键字
 * 
 * 2014年6月10日 12:42:25
 * 关于wait和notify的synchronized代码库细节：
 * wait和notify都在同步块中，当wait()调用时，线程进入阻塞，此时sync的对象锁会释放掉。
 * 这样其他线程才能进入sync同步块代码。
 * 但是，当notify调用时，sync对象锁不会释放，这就等于，nofity()同步块代码要完全执行完，
 * 其它线程才可能重新获得对象锁并进入wait()代码。
 */
public class TestWaitNotify implements Runnable {

	public static void main(String[] args) throws InterruptedException {
		TestWaitNotify test = new TestWaitNotify();
		Thread thread = new Thread(test);
		thread.start();
		synchronized (thread) {
			thread.wait();
		}
		System.out.println("main");
	}

	/**
	 * run方法声明里面得有synchronized
	 * 或者this.notify();包上synchronized (this)
	 * ==> 不然抛java.lang.IllegalMonitorStateException
	 */
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()
					+ " is to sleep 2s");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread");
		synchronized (this) {
			this.notify();
		}
	}

}
