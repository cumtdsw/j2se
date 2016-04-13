package mem;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试下内存
 * 加上jvm参数：-Xms16m -Xmx16m -verbose:gc  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 */
public class TestMemory {

	public static void main(String[] args) throws InterruptedException {
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 0; i < 1000000; i++) { // XXX 改大这个值，让gc爆内存
			map.put(new Integer(i).toString(), new byte[100]);
		}
		System.out.println("sleeping");
		System.gc();
		System.out.println(Thread.activeCount());
		Thread.sleep(10000000);
	}
	
}
