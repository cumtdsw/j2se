package list;

import java.util.Arrays;

/**
 * Arrays是一个工具箱，本身没有构造方法。
 */
public class TestArrays {

	public static void main(String[] args) {
		int a[] = new int[100];
		for(int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		
		// 二分查找
		int index = Arrays.binarySearch(a, 20);
		System.out.println("index:" + index);
		
		// 排序
		Arrays.sort(a);
		
		// 还有复制、拷贝等其它对数组的操作。
	}
	
}
