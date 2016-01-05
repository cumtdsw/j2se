package classloader;

public class TestClassLoader {

	public static void main(String[] args) {
		
		// 获得任意一个类的ClassLoader
		ClassLoader cl1 = String.class.getClassLoader();
		System.out.println(cl1); // null，如果是Bootstrap
		
		// 
		ClassLoader cl2 = TestClassLoader.class.getClassLoader();
		System.out.println(cl2); // AppClassLoader
	}
}
