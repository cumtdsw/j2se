package com.pugwoo;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 2015年9月8日 17:07:59
 * 原子化CAS操作，性能不错
 */
public class TestAtomic {

	public static void main(String[] args) {
		final AtomicBoolean ab = new AtomicBoolean(true);
		for(int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					if(ab.compareAndSet(true, false)) { // 将ab从true修改为false，修改成功返回true
						System.out.println("change succ, curr: " + ab.get());
					}
				}
			}).start();
		}
		
		// 上面的例子演示了，只有线程可以成功将值修改成功，并执行了if里面的代码段
	}
	
}
