package guava.concurrency;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.Monitor;

/**
 * 这里演示的是消费者模型
 * 2014年11月19日 16:02:30
 */
public class TestMonitor {

	private List<String> list = new ArrayList<String>();
	private static final int MAX_SIZE = 10;

	private Monitor monitor = new Monitor();
	private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
		@Override
		public boolean isSatisfied() {
			return list.size() < MAX_SIZE;
		}
	};

	public void addToList(String item) throws InterruptedException {
		/**
		 * 还有一种进入方式是monitor.enterIf(guardCondition) XXX
		 */
		monitor.enterWhen(listBelowCapacity);
		try {
			list.add(item);
		} finally {
			monitor.leave();
		}
	}

	public static void main(String[] args) {
		// 起3个线程，模拟往里面放东西，看是否到10个就停了
		final TestMonitor testMonitor = new TestMonitor();

		for (int i = 0; i < 3; i++) {
			final int a = i;
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 10; i++) {
						System.out.println("thread" + a + " before add");
						try {
							testMonitor.addToList("one");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("thread" + a + " after add");
					}
				}
			}.start();
		}

	}
}
