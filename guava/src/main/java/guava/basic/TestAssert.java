package guava.basic;

import com.google.common.base.Preconditions;

/**
 * 2014年11月18日 14:31:45
 * 断言方面的测试
 */
public class TestAssert {
	
	private static void sayHello(String name) {
		Preconditions.checkNotNull(name); // 抛出nullpoint
		System.out.println("hello " + name);
	}

	public static void main(String[] args) {
		sayHello("nick");
		sayHello(null);
		
		// 其它常用方法
		// checkElementIndex(int index, int size, Object msg);
		// checkArgument(Boolean expression, Object msg);
	}
	
}
