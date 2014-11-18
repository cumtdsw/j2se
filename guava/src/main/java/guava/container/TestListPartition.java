package guava.container;

import java.util.List;

import com.google.common.collect.Lists;

public class TestListPartition {

	public static void main(String[] args) {
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5);
		
		// 分成多个子数组，每个子数组包含2个元素
		List<List<Integer>> subInts = Lists.partition(ints, 2);
		
		System.out.println(subInts);
	}
	
}
