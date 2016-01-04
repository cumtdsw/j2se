package sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2014年7月4日 20:57:29
 * ReentrantLock解决了sync同步功能，但没有wait/notify功能
 * 因此，一个ReentrantLock对象可以关联若干个Condition对象
 * 一个Condition对象可以调用await()使当前线程进入等待，
 * 直到另外一个线程调用了该Condition对象的signal()方法。
 * 
 * await()和signal()
 * 
 * Condition的强大之处在于它可以为多个线程间建立不同的Condition
 * 
 * await()和signal()方法放在lock.lock()try{}finally{lock.unlock();}块中。
 * 否则报java.lang.IllegalMonitorStateException
 * 
 * 【和synchronized中的wait()一样，condition的await()方法调用时，会释放reentrantLock】
 */
public class TestCondition{
	
	public static void main(String[] args) throws InterruptedException {
		
		final ReentrantLock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					condition.await(); // 等待被另外一个线程叫醒
					// wait()支持超时机制
					
					System.out.println("second");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();;
		
		// 主线程先做完事情后再叫醒上面那个线程
		System.out.println("main thread will sleep 1s");
		Thread.sleep(1000);
		System.out.println("main thread wake up");
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
}
