package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 优先使用泛型列表 2014年7月17日
 */
public class TestList {

	interface Function<T> {
		T apply(T obj1, T obj2);
	}
	
	static <E> E reduce(List<E> list, Function<E> f, E initVal) {
		synchronized (list) { // 加锁的另一种解决方法就是复制一份局部list
			E result = initVal;
			for(E o : list) {
				result = f.apply(result, o);
			}
			return result;
		}
	}
	
	public static void main(String[] args) {
		
		List<Long> list = new ArrayList<Long>();
		for(long i = 0; i <= 100; i++) {
			list.add(i);
		}
		
		long sum = reduce(list, new Function<Long>() {
			@Override
			public Long apply(Long obj1, Long obj2) {
				return obj1 + obj2;
			}
		}, 0L);
		
		System.out.println("1..100 sum:" + sum);
	}
	
}
