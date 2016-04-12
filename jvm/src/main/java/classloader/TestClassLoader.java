package classloader;

/**
  Bootstrap ClassLoader  负责加载java基础类，主要是 %JRE_HOME/lib/ 目录下的rt.jar、resources.jar、charsets.jar和class等
Extension ClassLoader  负责加载java扩展类，主要是 %JRE_HOME/lib/ext 目录下的jar和class
App ClassLoader        负责加载当前java应用的classpath中的所有类。
 */
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
