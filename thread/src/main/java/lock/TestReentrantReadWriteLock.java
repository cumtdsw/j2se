package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2014年6月10日 22:00:42
 * 这里是一个读写锁的实现：http://ifeve.com/read-write-locks/
 */
public class TestReentrantReadWriteLock {

	public static void main(String[] args) {

		final ReadWriteLock lock = new ReentrantReadWriteLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread-1试图获得写锁");
				lock.writeLock().lock();
				System.out.println("thread-1获得写锁");
				lock.writeLock().lock(); // 写锁可重入
				System.out.println("thread-1第二次获得写锁");
				System.out.println("thread-1试图获得读锁");
				lock.readLock().lock(); // 获得写锁后同线程可获得读锁
				System.out.println("thread-1获得读锁");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				lock.writeLock().unlock();
				lock.writeLock().unlock(); // lock与unlock个数必须一致
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				lock.readLock().unlock();
				
			}
		}, "thread-1").start();

		try {
			Thread.sleep(100); // 让线程1有足够时间运行
		} catch (InterruptedException e) {
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread-2试图获得读锁");
				lock.readLock().lock(); // 在线程1解除【写锁】之前无法获得
				System.out.println("thread-2获得读锁");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				lock.readLock().unlock();
			}
		}, "thread-2").start();

		try {
			Thread.sleep(100); // 让线程2有足够时间运行
		} catch (InterruptedException e) {
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread-3试图获得写锁");
				lock.writeLock().lock(); // 在线程1解除【读写锁】和线程2解除【读锁】之前无法获得
				System.out.println("thread-3获得写锁");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				lock.writeLock().unlock();
			}
		}, "thread-3").start();

	}

}
