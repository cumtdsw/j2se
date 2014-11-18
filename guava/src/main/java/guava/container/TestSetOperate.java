package guava.container;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 2014年11月18日 16:19:20
 * 非常使用的：集合差集
 */
public class TestSetOperate {

	public static void main(String[] args) {
		Set<Integer> set1 = Sets.newHashSet(1,2,3);
		Set<Integer> set2 = Sets.newHashSet(2,3,4);
		
		// 集合1 减去 集合2中的元素 = 集合1有，集合2没有的元素
		System.out.println(Sets.difference(set1, set2));
		
		// 并集
		System.out.println(Sets.intersection(set1, set2));
		
		// 交集
		System.out.println(Sets.union(set1, set2));
		
		// 对称差，返回两个集合相同之外的其它元素
		System.out.println(Sets.symmetricDifference(set1, set2)); // 1、4
	}
	
}
