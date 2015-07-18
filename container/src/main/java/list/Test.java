package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		// new LinkedList<Integer>(); 另一种链表
		for(int i = 0; i < 10; i++) {
			list.add(i * i);
		}
		
		Iterator<Integer> it = list.iterator();
		
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		// 2015年3月6日 11:40:07 一种类似guava Lists创建list的方法：
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		for(Integer i : list2) {
			System.out.println(i);
		}
	}
}
