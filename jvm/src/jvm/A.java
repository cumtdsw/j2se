package jvm;

public class A {
	
	private static int i = 0;

	public A() throws Exception {
		i++; // 构造方法是static方法
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class<Integer> clazz = int.class;
		System.out.println(clazz.getCanonicalName()); // 无法newInstance,因为它是基本类型
		
		System.out.println(clazz instanceof Object);
	}
}
