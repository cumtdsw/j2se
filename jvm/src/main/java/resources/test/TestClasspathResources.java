package resources.test;

/**
 * 2014年8月6日 21:48:44
 * 测试读取classpath下文件。
 * http://ygydaiaq-gmail-com.iteye.com/blog/1985285
 * 
 * 凡是处在classpath中的文件，我们称之为资源，都可以用classloader来读取
 */
public class TestClasspathResources {

	public static void main(String[] args) {
		
		/**
		 * 首先是getResource的参数，只要是/开始的就是绝对路径，指classpath根目录，否则是相对路径。
		 * 
		 * 如果从指定类.class.getResource("") 则相对路径是和该类同级目录。
		 * 如果从指定类.class.getClassLoader().getResource("") 则相对路径是根目录
		 */
		
		// 子目录
		System.out.println(TestClasspathResources.class.getResource(""));
		
		// 根目录
		System.out.println(TestClasspathResources.class.getResource("/"));
		
		// 根目录
		System.out.println(TestClasspathResources.class.getClassLoader().getResource(""));
		
		// 这是一种错误的读取方式，使用classloader的时候，不可以指定/来读取。
		System.out.println(TestClasspathResources.class.getClassLoader().getResource("/"));
		
		System.out.println(TestClasspathResources.class.getResource("test.txt"));
		System.out.println(TestClasspathResources.class.getResource("/root.txt"));
		
		System.out.println(TestClasspathResources.class.getClassLoader().getResource("root.txt"));

		
	}
	
}
