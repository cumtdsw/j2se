package jvm;

/**
 * 2014年7月25日 20:22:16
 * 测试内部类.
 * 我这样觉得：内部类可以看作是外部类的一个成员对象，内部类可以访问外部类的成员变量。这就类似于闭包的概念。
 */
public class TestInternalClass {
	
	private int i = 1;
	
	class InternalClass {
		private int j = 3; // 不能有static变量或方法
		public int getI() {
			// return TestInternalClass.this.i; // this的使用
			return i; // 可以访问外部类的i，类似于闭包的概念
		}
	}
	
	public InternalClass getInternalClass() {
		return new InternalClass();
	}
	
	public static void main(String[] args) {
		TestInternalClass testInternalClass = new TestInternalClass();
		InternalClass internalClass = testInternalClass.getInternalClass();
		System.out.println("i=" + internalClass.getI()); // i=1
		
		// .new的使用，另外一种用法
		InternalClass internalClass2 = testInternalClass.new InternalClass();
		System.out.println("i=" + internalClass2.getI()); // i=1
		
	}
	
}
