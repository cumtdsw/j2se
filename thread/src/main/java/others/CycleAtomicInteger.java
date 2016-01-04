package others;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 2014年8月4日 14:14:01
 * http://www.atatech.org/articles/19728
 * 原子循环计数器
 * 
 * 实现目标：多线程获取一个自增的范围从0到N的循环的整数。
 * 例如，当N=5时，3个线程并发执行，那么这些线程拿到的数值应该是：
 * 0,1,2,3,4,5,0,1,2,3,4,5这样
 * 
 * 实现原理：CAS，失败则重试
 */
public class CycleAtomicInteger {

	private final static long PARK_TIME = 1000L * 1000;

	private AtomicInteger counter = new AtomicInteger(0);

	private int range;

	public CycleAtomicInteger(int range) {
	    if (range < 2)
	        throw new IllegalArgumentException();
	    this.range = range;
	}

	/**
	 * 获取下个原子值
	 * 
	 * @return
	 */
	public int next() {
	    for (;;) {
	        int c = counter.get();
	        /**
	         * 首先c的值每次都被限定在range范围内，而range一般不会达到溢出
	         * 这就避免了Math.abs(pollerRotater.incrementAndGet()) % range
	         * 这种方式调用Math.abs的耗时
	         */
	        int next = (c + 1) % range;
	        if (counter.compareAndSet(c, next)) {
	            return c;
	        } else {
	        	/**
	        	 * 这个是失败重试的等待时间。
	        	 * 等待时间过长和过短都会影响效率。
	        	 * 如果这一行没有，则相当于CPU自旋，非常耗CPU
	        	 */
	            LockSupport.parkNanos(PARK_TIME);
	        }
	    }
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[10];
		final CycleAtomicInteger cycleAtomicInteger = new CycleAtomicInteger(10);
		
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(String.valueOf(i)) {
				@Override
				public void run() {
					for(int j = 0; j < 50; j++) {
					    System.out.println("thread " + getName() + " get " + 
							cycleAtomicInteger.next());
					}
				}
			};
			threads[i].start();
		}
	}
	
}
