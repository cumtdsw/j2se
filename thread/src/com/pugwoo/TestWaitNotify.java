package com.pugwoo;

/**
 * 注意：调用某个对象obj的wait()和notify()时，必须synchronized
 * 
 * synchronized有两种方式：
 * 1) synchronized(obj){obj.wait/notify();}
 * 2) 在调用了wait/notify的整个方法声明上加synchronized关键字
 * 
 * 2013年10月16日 23:34:09
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
