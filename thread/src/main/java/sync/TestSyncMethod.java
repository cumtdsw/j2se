package sync;

/**
 * 2014年5月21日 14:24:40
 * 输出是两个method1再加两个method2
 * 这说明：【method的同步是对象级别的，也就是不存在同一个对象的两个synchronized方法并行执行】
 */
public class TestSyncMethod {
	
	public static synchronized void method1() {
		System.out.println("method1");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1");
	}
	
	public static synchronized void method2() {
		System.out.println("method2");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method2");
	}

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				TestSyncMethod.method1();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				TestSyncMethod.method2();
			}
		}).start();
		
	}

}
