package classloader;

public class TestMyClassLoader {
	
	public static void main(String[] args) {
		try {
			// TODO 这个位置自行修改 !!
			MyClassLoader tc = new MyClassLoader("C:\\Users\\boyu.xby\\git\\j2se\\jvm\\bin\\");
			
			Class<?> c = tc.findClass("jvm.classloader.Test");
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
