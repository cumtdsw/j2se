package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2014年6月10日 17:35:59 ReentrantLock的性能很不错
 * 
 * ReentrantLock主要方法：
lock()
lockInterruptibly()
tryLock()
tryLock(long timeout, TimeUnit timeUnit)
unlock()
 */
public class TestReentrantLock {

	private static Integer i = 0; // 这里不加valatile也测不出异常，这说明lock()代码块之间，变量是可见的

	private static ReentrantLock lock = new ReentrantLock();

	public static void addI(List<Integer> list) {
		lock.lock();
		i++;
		list.add(i);
		lock.unlock();
	}

	public static void main(String[] args) throws InterruptedException {

		final List<Integer> list = new ArrayList<Integer>();

		for (int thread = 0; thread < 1000; thread++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						addI(list);
						/**
						 * 注意： 这里不能设计为由addI()返回int
						 * 然后再list.add(addI())，addI()是原子性的，但是返回值之后，list.add()不是
						 * 真的是只要有“间隙”，线程不安全的情况就一定会出现。
						 */
					}
				}
			});
			t.start();
			t.join(); // main线程等待该线程结束
		}

		// 检查list是不是自增的
		System.out.println("list.size()=" + list.size());
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1) == null) {
				System.err.println("list.get(i - 1) null");
			}
			if (list.get(i) == null) {
				System.err.println("list.get(i) null");
			}
			if (list.get(i - 1) + 1 != list.get(i)) {
				System.err.println("i=" + i + ",list.get(i-1)="
						+ list.get(i - 1) + ",list.get(i)=" + list.get(i));
			}
		}
		System.out.println("check done");
	}
}
