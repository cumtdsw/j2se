package guava.basic;

import com.google.common.base.Preconditions;

/**
 * 2014年11月18日 14:31:45
 * 断言方面的测试
 * http://ifeve.com/google-guava-preconditions/
 * 
 * 2015年6月7日 14:58:17
 * 实际上关于这种数据不对就抛异常，而且是Runtime异常的这种
 * 我觉得对调用方不够友好，如果调用方数据不正确，将导致它的程序停止
 * 而这种隐藏的Exception并没有足够的语义让调用者去捕获异常
 */
public class TestAssert {
	
	private static void sayHello(String name) {
		Preconditions.checkNotNull(name); // 抛出nullpoint
		System.out.println("hello " + name);
	}
	
	public static void checkArgument(int age) {
		Preconditions.checkArgument(age >= 0, "age must >=0");
	}

	public static void main(String[] args) {
		try {
			sayHello("nick");
			sayHello(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			checkArgument(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 其它常用方法
		// checkElementIndex(int index, int size, Object msg);
		// checkArgument(Boolean expression, Object msg);
	}
	
}
