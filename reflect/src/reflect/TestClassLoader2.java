package reflect;

public class TestClassLoader2 {

	public static void main(String[] args) {
		// AppClassLoader
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统类装载器:" + systemClassLoader);
		
		// ExtClassLoader
		ClassLoader extClassLoader = systemClassLoader.getParent();
		System.out.println("系统类装载器的父类加载器——扩展类加载器:" + extClassLoader);
		
		// null
		ClassLoader bootClassLoader = extClassLoader.getParent();
		System.out.println("扩展类加载器的父类加载器——引导类加载器:" + bootClassLoader);
	}
}
