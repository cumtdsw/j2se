package com.pugwoo.sync;

import java.util.concurrent.Semaphore;

/**
 * 2014年6月10日 23:04:15 信号量：适合于实现生产者消费者模型。
 * 比起wait/notify，信号量的实现屏蔽了易错的wait/notify机制。
 * 同时，信号量适合于生产者生产产品N的情况，wait/notify需要额外变量记录。
 * 
 * 看了教程发现，Semaphore的使用也不方便，需要2到3个信号量才能实现。
 * 
 * 【信号量最小为0】
 * 
 * 2014-6-11 10:43:37  这个需求直接被阻塞队列【秒杀】了！
 */
public class TestSemaphore {

	private int value = 0;

	// 构造函数的值就是该信号量的初始值，信号量没有最大值，最小值是0
	private final Semaphore mutex = new Semaphore(1);

	// 由信号量保证原子性
	public int getNextValue() throws InterruptedException {
		try {
			mutex.acquire(); // 获得信号量，获取不到时阻塞，-1
			Thread.sleep(1000);
			return value++;
		} finally {
			mutex.release(); // 释放信号量,+1
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final TestSemaphore testSemaphore = new TestSemaphore();

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(testSemaphore.getNextValue());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

	}

}
