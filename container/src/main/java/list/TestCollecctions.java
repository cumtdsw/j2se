package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Collections是一个工具箱
 */
public class TestCollecctions {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		List<Map> list = new ArrayList<Map>();

		Collections.addAll(list, new HashMap(), new ConcurrentHashMap());
		
		List<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < 100; i++) {
			a.add(100 - i);
		}
		Collections.sort(a); // 排序，正序
		
		for(Integer i : a) {
			System.out.println(i);
		}
		
		System.out.println("seach index: " + Collections.binarySearch(a, 5));
		
		// 排序、查找、翻转、填充、拷贝、最大值、最小值、查序
		
		System.out.println("max:" + Collections.max(a));
		
		// 还可以创建单例、不可变、同步的集合
		List<Integer> unmodifyList = Collections.unmodifiableList(a);
		for(Integer i : unmodifyList) {
			System.out.println(i);
		}
		// unmodifyList.add(33); // 无法修改
		
	}

}
