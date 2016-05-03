package basic;

import java.util.HashMap;

public class TestInitialize {
	
	@SuppressWarnings("serial")
	private static HashMap<String, String> map = new HashMap<String, String>() {
		// 这里开始实际上是HashMap的匿名类
		
		@Override
		public String toString() {
			return "toString被重写了";
		}
		
		// 这个是实例初始化代码块
		{
			put("Name", "June");
			put("QQ", "4889983");
			System.out.println("put name and qq");
		}
	};
	 

	public TestInitialize() {
	        System.out.println("Constructor called：构造器被调用");
	    }

	/**
	 * 静态初始化，每个类被加载后执行一次
	 */
	static {
		System.out.println("Static block called：静态块被调用");
	}

	/**
	 * 实例初始化，每个实例new时会被调用一次
	 */
	{
		System.out.println("Instance initializer called：实例初始化块被调用");
	}

	public static void main(String[] args) {
		new TestInitialize();
		System.out.println("=======================");
		new TestInitialize();
		
		System.out.println(map.size() + ":toString:" + map.toString());
	}
}
