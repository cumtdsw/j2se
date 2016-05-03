package thread_info;

import java.util.Set;

/**
 * 2016年4月13日 21:27:53
 * 在代码中获取线程的信息
 * 
 * 可以拿到jvm的所有线程的状态、堆栈
 */
public class GetThreadsInfo {
	
	public static void main(String[] args) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for(Thread thread : threadSet) {
			System.out.println(thread.getName() + ":" + thread.getState() + ">" +
		             (thread.getStackTrace().length > 0 ? thread.getStackTrace()[0] : ""));
		}
	}
	
}
