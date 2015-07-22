package com.pugwoo.benchmark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pugwoo.CountTime;

/**
 * 用于测试固定线程池的性能
 * 
 * 在主流笔记本（2015年）上测试，这个空跑的任务完全用于测试线程池调度的性能
 * poolSize   线程/秒
 * 1000       196万
 * 100        167万
 * 
 * 所以，对于普通的应用，百万级/s的速度没有问题。
 */
public class BenchNewFixedThreadPool {

	public static void main(String[] args) {
		
		CountTime countTime = new CountTime();
		countTime.start();
		
		// 测试结果：当线程数达到千万级别时，由于一次全部加入到pool
		// 会出现各种错误，包括java.lang.OutOfMemoryError
		// 所以要控制加入的速度
		// 参考【BenchThreadPool】
		int fixedThreadPoolSize = 100;
		int threadNum = 10000000;
		
		/**
		 * newFixedThreadPool本质是用了ThreadPoolExecutor：
		 * 
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
		 */
		ExecutorService service = Executors
				.newFixedThreadPool(fixedThreadPoolSize);

		for (int i = 0; i < threadNum; i++) {
//			final int fi = i;
			service.execute(new Runnable() {
				@Override
				public void run() {
//					System.out.println(fi);
				}
			});
		}
		
		service.shutdown();
		countTime.printInMs(); // 一般是5s这样
	}

}
