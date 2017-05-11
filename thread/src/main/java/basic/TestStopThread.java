package basic;

public class TestStopThread {

	public static void main(String[] args) {

		// 这里演示一个一直跑满CPU的线程
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					int a = 3;
					System.out.println("a=" + a);
				}
			}
		});
		thread.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 等待5秒钟后，直接调用另外一个线程的stop方法，强制结束另外一个线程，这种情况可以成功结束掉。
		// 但不保证这种方式可以成功结束线程，也不建议用这种方式，建议的设计是线程可以管控自己的状态，正常结束
		System.out.println("main call thread stop");
		thread.stop(); // 强制结束线程，不建议
	}
	
}
