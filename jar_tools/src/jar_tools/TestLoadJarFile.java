package jar_tools;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Vector;

/**
 * 2014年8月24日 09:59:04 测试加载jar包到当前jvm，有两种场景 
 * 1) jar包还是用来解析，所以要独立classloader来加
 * 2) jar包当前应用依赖，所以加载到目前已有的classloader中
 */
public class TestLoadJarFile {
	
	public static void listLoadedClasses(ClassLoader byClassLoader) {
	    Class<?> clKlass = byClassLoader.getClass();
	    System.out.println("Classloader: " + clKlass.getCanonicalName());
	    while (clKlass != java.lang.ClassLoader.class) {
	        clKlass = clKlass.getSuperclass();
	    }
	    try {
	        java.lang.reflect.Field fldClasses = clKlass
	                .getDeclaredField("classes");
	        fldClasses.setAccessible(true);
	        Vector<?> classes = (Vector<?>) fldClasses.get(byClassLoader);
	        for (Iterator<?> iter = classes.iterator(); iter.hasNext();) {
	            System.out.println("   Loaded " + iter.next());
	        }
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) throws MalformedURLException,
			ClassNotFoundException {
		File file = new File("C:\\fastjson.jar");
		URL url = file.toURI().toURL();

		URL[] urls = new URL[] { url };
		
		/**
		 * 注意这里：如果需要独立的classloader，则用null，
		 * 否则用：TestListAllClass.class.getClassLoader()
		 */
		ClassLoader cl = new URLClassLoader(urls, null);

		// 只有loadClass才会load到jvm，属于懒加载的设计
		Class<?> cls = cl.loadClass("com.alibaba.fastjson.util.UTF8Decoder");
		System.out.println("load class:" + cls.getName());
		
		Method[] methods = cls.getDeclaredMethods(); // 不包括父类的
		for(Method method : methods) {
			System.out.println(method.getName()); //此对象还可以获得该方法的参数
		}
		 
		
		// 这一行会出错，因为当前是另外一个classLoader
		// Class.forName("com.alibaba.fastjson.util.UTF8Decoder");
		
		// 获取load到classLoader中所有的类
		listLoadedClasses(cl);
		
	}
	
}
