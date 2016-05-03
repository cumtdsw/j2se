package jvm;

/**
 * 2014年7月25日 20:22:16
 * 测试内部类.
 * 我这样觉得：内部类可以看作是外部类的一个成员对象，内部类可以访问外部类的成员变量。这就类似于闭包的概念。
 */
public class TestStaticInternalClass {
	
	private static int s = 5;
	
	private int i = 1;
	
	static class InternalClass {
		private static int a = 4; 
		private int j = 3; 
		public int getI() {
			// return TestInternalClass.this.i; // this的使用
			return s; // 可以访问外部类的i，类似于闭包的概念
		}
		// 可以有静态的方法
		public static void test() {}
	}
	
	public InternalClass getInternalClass() {
		return new InternalClass();
	}
	
	public static void main(String[] args) {
		TestStaticInternalClass testInternalClass = new TestStaticInternalClass();
		InternalClass internalClass = testInternalClass.getInternalClass();
		System.out.println("i=" + internalClass.getI()); // i=1

		InternalClass internalClass2 = new TestStaticInternalClass.InternalClass();
		
	}
	
}
