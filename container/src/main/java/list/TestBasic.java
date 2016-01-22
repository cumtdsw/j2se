package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 常用有两种列表：两者性能一般情况下差别不大，一般都用ArrayList就行
 * 数组列表：ArrayList 
 * 指针链式列表：LinkedList，对于大量中间位置增删元素很合适
 */
public class TestBasic {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		// new LinkedList<Integer>(); 另一种链表
		for(int i = 0; i < 10; i++) {
			list.add(i * i);
		}
		
		Iterator<Integer> it = list.iterator(); // 通过游标遍历，如果要删除list中的某个元素，游标是推荐的安全方式
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
			it.remove(); // 删除当前元素
		}
		
		System.out.println("=====================================================");
		
		// 2015年3月6日 11:40:07 一种类似guava Lists创建list的方法：
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		for(Integer i : list2) {
			System.out.println(i);
		}
	}
}
