package classloader;

public class TestMyClassLoader {
	
	public static void main(String[] args) {
		try {
			// XXX 这个位置根据实际情况修改，它是编译出来的class文件的根目录，例如maven编译出来的target/classes目录
			MyClassLoader tc = new MyClassLoader("D:/code/j2se/jvm/target/classes");
			System.out.println("tc's parent classloader is:" + tc.getParent());
			
			Class<?> c = tc.findClass("classloader.Test");
			Object obj = c.newInstance();
			
			System.out.println(obj.getClass());
			System.out.println(obj.getClass().getClassLoader());
			
			/**
			 * 重点注意! 虽然都是jvm.classloader.Test
			 * [但是]它们的classLoader是不同的，因此他们不能相互转化
			 */
			System.out.println(Test.class);
			System.out.println(Test.class.getClassLoader());
			
			// java.lang.ClassCastException: jvm.classloader.Test cannot be cast to jvm.classloader.Test
			Test test = (Test) obj; // 这样是不行的
			System.out.println("test:" + test);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
