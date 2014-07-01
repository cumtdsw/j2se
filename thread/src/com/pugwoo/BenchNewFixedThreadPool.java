package com.pugwoo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于测试固定线程池的性能
 */
public class BenchNewFixedThreadPool {

	public static void main(String[] args) {
		
		CountTime countTime = new CountTime();
		countTime.start();
		
		// 测试结果：当线程数达到千万级别时，由于一次全部加入到pool
		// 会出现各种错误，包括java.lang.OutOfMemoryError
		// 所以要控制加入的速度
		// 参考【BenchThreadPool】
		int fixedThreadPoolSize = 1000;
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
			final int fi = i;
			service.execute(new Runnable() {
				@Override
				public void run() {
//					System.out.println(fi);
				}
			});
		}
		
		service.shutdown();
		countTime.printInMs();
	}

}
