package resources.test_read_jar;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 2014年8月17日 20:27:51
 * 
 * 测试从其它jar包中读取文件，这里构造了两个都包含
 * readme.txt和a包下b.txt的jar包。
 * 此外test_jar_2.jar多了一个文件
 */
public class TestReadJarResources {

	public static void main(String[] args) throws IOException {
		URL resource = TestReadJarResources.class.getClassLoader()
				.getResource("readme.txt");
		// jar:file:/I:/github/j2se/jvm/lib/test_jar_1.jar!/readme.txt
		System.out.println(resource);
		
		resource = TestReadJarResources.class.getClassLoader()
				.getResource("a/b.txt");
		// jar:file:/I:/github/j2se/jvm/lib/test_jar_1.jar!/a/b.txt
		System.out.println(resource);
		
		resource = TestReadJarResources.class.getClassLoader()
				.getResource("ext.txt");
		// jar:file:/I:/github/j2se/jvm/lib/test_jar_2.jar!/ext.txt
		System.out.println(resource);
		
		Enumeration<URL> resources = TestReadJarResources.class.getClassLoader()
				.getResources("readme.txt");
		while(resources.hasMoreElements()) {
			URL r = resources.nextElement();
			/**
jar:file:/I:/github/j2se/jvm/lib/test_jar_1.jar!/readme.txt
jar:file:/I:/github/j2se/jvm/lib/test_jar_2.jar!/readme.txt
			 */
			System.out.println(r);
		}
		
		/**
		 * 结论：也就是说，通过ClassLoader加载的是classpath下的资源，无论是源码还是jar包同等对待。
		 * 
		 * 关于冲突：java不会报错，它会按它的顺序选择一个。
		 * 如果想拿到全部的资源，则用getResources("")
		 */
	}
	
}
