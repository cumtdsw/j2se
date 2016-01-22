package list;

import java.util.Arrays;

/**
 * Arrays是一个数组[]的工具类，本身没有构造方法。
 * Arrays只操作数组，所以它是很基本的类，一般用Collections这个更抽象的工具类
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
		
		// 还有复制、拷贝等其它对数组的操作。适合于底层优化
	}
	
}
