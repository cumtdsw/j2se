package com.pugwoo.benchmark;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.pugwoo.CountTime;

/**
 * 用于测试指定固定线程池且队列限长的threadPool的性能
 * 
 * 空跑以测试threadPool本身的性能
 * 进程池并非越大越大，例如1kw的任务，在进程池大小为100，队列长度为1000时，只需要14.4s(i5-2400四线程)就完成
 * 当线程池大小为10时，只需要1.7s完成
 * 而线程池大小为1000，队列长度为10000时，需要40s才能完成
 * 
 * 线程池大小实际上是和执行任务的CPU和IO等待占比相关的。
 * 
 * 在2015年主流笔记本上，1kw任务，进程池大小100，队列长度1000时，8s完成
 */
public class BenchThreadPool {

	public static void main(String[] args) {
		
		int fixedThreadPoolSize = 100;
		int queueSize = 1000;
		int threadNum = 10000000;
		
		CountTime countTime = new CountTime();
		countTime.start();
		
		/**
		 * 参数说明：
		 * 第一个 corePoolSize 初始线程池线程数
		 * 第二个 maximumPoolSize 最大的线程池数
		 * 第三个和第四个综合起来是 keepAlive的时间，也就是线程池里的线程过了这个时间仍处于idle状态，就关掉
		 * 第四个参数BlockingQueue 是一个阻塞队列
		 */
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				fixedThreadPoolSize, fixedThreadPoolSize, 0L,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(
						queueSize));
		
		// 这里设置当阻塞队列满时，ThreadPoolExecutor的管理线程也用于执行任务，这样使得
		// executor.execute被阻塞住了
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		

		for (int i = 0; i < threadNum; i++) {
//			final int fi = i;
			executor.execute(new Runnable() {
				@Override
				public void run() {
//					System.out.println(fi);
				}
			});
		}
		
		executor.shutdown();
		countTime.printInMs();
	}

}
