package jvm;

/**
 * 2014年8月28日 14:06:07 测试使用addShutdownHook方法，让jvm在关闭之前做些事情
 */
public class TestAddShutdownHook {

	public static void main(String[] args) {
		
		Thread thread1 = new Thread() {
			public void run() {
				System.out.println("thread1 begin and sleep 1 second");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread1 ends");
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
				System.out.println("thread2 begin and sleep 1 second");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread2 ends");
			}
		};
		
		// 定义关闭线程
		Thread shutdownThread = new Thread() {
			public void run() {
				System.out.println("shutdownThread doing something, it takes some time");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("shutdownThread clean done");
			}
		};
		
		// 注册jvm关闭时执行的线程
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		
		thread1.start();
		thread2.start();
	}

}
