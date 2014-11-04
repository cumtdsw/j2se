package guava.container;

import java.util.Map;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * 2013年2月14日 15:22:18
 * 如果一个container的数据是不可变的，那么将获得更高的速度和安全性
 */
public class Immutable extends TestCase {

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
	}
	
	
}
