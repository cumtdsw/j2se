package generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试方法泛型 2014年7月17日
 * 
 * 如果参数是一个泛型类，那基本上就得上泛型方法了。
 */
public class TestMethod {

	/**
	 * 最关键是加上<E>
	 * @param e
	 * @return
	 */
	public static <E> E get(E e) {
		return e;
	}
	
	/**
	 * 如果参数是一个泛型类，那基本上就得上泛型方法了。
	 * @param list
	 * @return
	 */
	public static <E> int getSize(List<E> list) {
		return list.size();
	}
	
	/**
	 * 一种漂亮的利用泛型方法创建泛型对象的用法
	 */
	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
	/**
	 * 使用泛型表达更好的语义：
	 * <T extends Comparable<T>> 表示本方法支持实现了Comparable接口的对象列表找到最大值
	 */
	public static <T extends Comparable<T>> T max(List<T> list) {
		T max = null;
		for(T t : list) {
			if(max == null || t.compareTo(max) > 0) {
				max = t;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println(get(new String("hi")));
		
		List<String> list = new ArrayList<String>();
		list.add("5");
		list.add("1");
		list.add("2");
		System.out.println(list.size());
		
		Map<String, Long> map = newHashMap();
		/**
		 * 等价于 Map<String, Long> map = new HashMap<String, Long>();
		 */
		map.put("123", 123L);
		
		System.out.println("最大值:" + max(list));
	}
}
