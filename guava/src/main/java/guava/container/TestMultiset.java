package guava.container;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * 2015年6月7日 16:30:13
 * 集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，
 * Multiset元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的
 * 
 * 可以用两种方式看待Multiset：
1. 没有元素顺序限制的ArrayList<E>
2. Map<E, Integer>，键为元素，值为计数
 */
public class TestMultiset {

	public static void main(String[] args) {
		// 单词计数的例子
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("hello");
		multiset.add("world");
		multiset.add("hello");
		multiset.add("you");
		
		System.out.println(multiset.size());
		System.out.println(multiset.count("hello")); // 计数
		
		// 此外还有SortedMultiset
		
	}
	
}
