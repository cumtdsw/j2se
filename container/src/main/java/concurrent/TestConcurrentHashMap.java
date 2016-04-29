package concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2012年2月21日
 * 
 * ConcurrentHashMap是线程安全的，当一个对象作为类的共享变量被多个线程修改时，就需要用到这个
 */
public class TestConcurrentHashMap {

	public static void main(String[] args) {
		Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		
		// TODO 这里并没有用多线程来演示
		
		map.put(1, 1);
		System.out.println(map.get(1));

	}
}
