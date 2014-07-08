package reflect;

//import java.net.URLClassLoader;

public class TestClassLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		
		ClassLoader cl = new ClassLoader() {
		};
		Class<?> clazz = cl.loadClass("java.lang.String");
		System.out.println(clazz.getName());
		
		//URLClassLoader classLoader = new URLClassLoader("");
		
		/**
		 * Class对象的getClassLoader方法获得该方法的classLoader
		 * 1) 如果为null，则为bootstrap classloader
		 */
		System.out.println(System.out.getClass().getClassLoader());
		System.out.println("".getClass().getClassLoader());
		
		System.out.println(new TestClassLoader().getClass().getClassLoader());
	}

}
