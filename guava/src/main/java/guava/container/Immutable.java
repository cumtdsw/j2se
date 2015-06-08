package guava.container;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * 2013年2月14日 15:22:18
 * 如果一个container的数据是不可变的，那么将获得更高的速度和安全性
 * 
 * 非常【重要】的一点，如果我不确信某个接口会修改我传递的map，那么我就传递给它不可变map
 * 这样可以确定数据不会被乱修改
 * 
 * 注意：所有Guava不可变集合的实现都不接受null值
 * 
 * 【所有不可变类都是Immutable开头的】
 * 更多列表：http://ifeve.com/google-guava-immutablecollections/
 */
public class Immutable {

	public static final ImmutableSet<Integer> LUCKY_NUMBERS = ImmutableSet.of(
			4, 8, 15, 16, 23, 42);

	public static final ImmutableMap<String, Integer> map = ImmutableMap.of(
			"four", 4, "eight", 8, "fifteen", 15, "sixteen", 16,
			"twenty-three", 23);
	
	public static void main(String[] args) {
		System.out.println(LUCKY_NUMBERS);
		System.out.println(map);
		
		/**
		 * 如果需要创建一个只读map，那么也可以这样创建，速度极快
		 */
		Map<String, Object> extras = ImmutableMap.of();
		System.out.println(extras);
		
		Map<String, Long> immuMap = 
				ImmutableMap.of("one", 1L, "two", 2L);
		System.out.println(immuMap);
		
		ImmutableMap.copyOf(extras);// 将一个已有的map变成不可变map，可以当作参数传递或返回，以保证数据安全
	
	    // 如果有更复杂的构建的话，就用builder()吧
		
		// 所有immutableSet都有asList()返回不可变List的方法
		System.out.println(LUCKY_NUMBERS.asList());
	}
	
	
}
