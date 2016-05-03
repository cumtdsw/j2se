package basic;

public class TestStopThread {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					int a = 3;
					System.out.println("a=" + a);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		});
		
		thread.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main call thread stop");
		thread.stop(); // 强制结束线程，不建议
	}
	
}
