package com.pugwoo.sync;

/**
 * 2014年6月10日 16:17:33 一个简单的Lock： 只有锁住的线程可以解锁
 * http://tutorials.jenkov.com/java-concurrency/starvation-and-fairness.html
 * 
 * 这个锁【不是】可重入锁，改写成可重入锁需要注意一点：
 * 同一个线程允许lock()多次，lock()N次就必须unlock()N次才能解锁。
 */
public class Lock {

	private boolean isLocked = false;
	private Thread lockingThread = null;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			/**
			 * 【注意这里】 
			 * wait()方法使得当前线程可以释放当前对象锁，因此其它线程也可以进入synchronized代码块，
			 * 从而都可以阻塞在这里wait()。
			 */
			wait();
		}
		isLocked = true;
		lockingThread = Thread.currentThread();
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		notify(); // 这里只需要叫醒一个阻塞的线程就可以了
	}

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new Lock();
		lock.lock();
		// lock.lock(); // 不可重入锁，死在这里
		lock.unlock();
		
		System.out.println("done");
	}

}
