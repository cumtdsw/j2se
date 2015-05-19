package thread;

/**
 * 2014年7月19日 10:34:36
 * 测试支持调度的线程池
 * http://wangxue.iteye.com/blog/1759836
 * 
 * 优先使用ScheduledThreadPoolExecutor，相对于Timer
 * 
 * 这个建议来自于Java Concurrency in Practice:
 * 这里也有人提及：http://stackoverflow.com/questions/409932/java-timer-vs-executorservice
 * 主要原因：
 * 1. Timer受系统时间变动的影响，而ScheduledThreadPoolExecutor不会（我反而觉得这是优势）
 * 2. Timer是单线程，跑得太久影响后面调度，如果抛RuntimeException则Timer就挂了
 * 
 * 当然，Timer更加轻量级。
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPoolExecutor {
	
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		
		// 两种初始化方法
		// ScheduledExecutorService exec=Executors.newScheduledThreadPool(1);
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		
		/**
		 * 周期性new线程并启动。第二个参数是初始延迟，第三个参数是执行周期，第4个参数是时间单位
		 */
		ScheduledFuture<?> future = exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() { // Runnable不能声明抛出异常
				System.out.println(format.format(new Date()));
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		// future.cancel(false); 通过scheduleAtFixedRate返回的future对象可以取消掉任务。

		// 开始执行后就触发异常,next周期将不会运行
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("RuntimeException no catch,next time can't run");
				throw new RuntimeException();
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);

		// 虽然抛出了运行异常,当被拦截了,next周期继续运行
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					throw new RuntimeException();
				} catch (Exception e) {
					System.out.println("RuntimeException catched,can run next");
				}
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);

		/**
		 * 创建并执行一个在给定初始延迟后首次启用的定期操作，<br/> 随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
		 */
		exec.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				System.out.println("scheduleWithFixedDelay:begin,"
						+ format.format(new Date()));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("scheduleWithFixedDelay:end,"
						+ format.format(new Date()));
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);

		/**
		 * 创建并执行在给定延迟后启用的一次性操作。
		 */
		exec.schedule(new Runnable() {
			public void run() {
				System.out.println("The thread can only run once!");
			}
		}, 5000, TimeUnit.MILLISECONDS);
	}
}

