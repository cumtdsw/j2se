package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * http://rainlife.iteye.com/blog/70072 2014年8月11日 19:12:30
 * 自定义ClassLoader例子，只能加载class文件，还不能加载jar包
 * 
 * 加载jar包思路：jar包就是类似zip压缩包，加载jar包就是解压并加载jar包中的class二进制文件
 */
public class MyClassLoader extends ClassLoader {

	private String fileName;

	/**
	 * @param fileName class文件的目录位置
	 */
	public MyClassLoader(String dirName) {
		this.fileName = dirName;
	}

	/**
	 * 定义查找Class的方式
	 */
	@Override
	protected Class<?> findClass(String className)
			throws ClassNotFoundException {

		// 查找父类已经加载的Class，如果父类没有加载，则这里来加载，否则用父类的
		Class<?> clazz = findLoadedClass(className);

		if (null == clazz) {
			try {
				String classFile = getClassFile(className);
				FileInputStream fis = new FileInputStream(classFile);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				int readBytes = 0;
				byte[] buf = new byte[4096];
				while((readBytes = fis.read(buf)) >= 0) {
					baos.write(buf, 0, readBytes);
				}
				fis.close();
				byte[] bytes = baos.toByteArray();

				// 调用父类的defineClass来加载class
				// 它只接受二进制
				clazz = defineClass(className, bytes, 0, bytes.length);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return clazz;
	}

	/**
	 * 把class全名转成文件名，
	 * 例如com.abc.Test会转成com/abc/Test.class
	 * @param name
	 * @return
	 */
	private String getClassFile(String name) {
		StringBuffer sb = new StringBuffer(fileName);
		name = name.replace('.', File.separatorChar) + ".class";
		sb.append(File.separator + name);
		return sb.toString();
	}
}
