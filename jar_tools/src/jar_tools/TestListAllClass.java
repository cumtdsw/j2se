package jar_tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 2014年8月24日 09:42:29 将jar包中的所有文件列表打印出来。
 */
public class TestListAllClass {

	public static JarFile getJarFile(String filePath) throws IOException {
		return new JarFile(filePath);
	}

	/**
	 * 获得一个jar包的所有entry，也就是jar包中所有文件的列表。
	 * 
	 * 当然后缀class结尾的就是编译java后的文件。
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<JarEntry> getJarEntries(String filePath)
			throws IOException {
		JarFile jarfile = getJarFile(filePath);
		Enumeration<JarEntry> entryList = jarfile.entries();
		List<JarEntry> list = new ArrayList<JarEntry>();
		while (entryList.hasMoreElements()) {
			list.add(entryList.nextElement());
		}
		return list;
	}

	public static void main(String[] args) throws IOException{

		List<String> classes = new ArrayList<String>();
		
		String jarFilePath = "C:\\fastjson.jar";
		List<JarEntry> jarEntries = getJarEntries(jarFilePath);
		for(JarEntry entry : jarEntries) {
			System.out.println(entry);
			/**
			 * 根据文件后缀，.class结尾的是包
			 * 这里不没有处理[内部类]的情况，内部类排除掉
			 */
			if(entry.getName().endsWith(".class")) {
				if(!entry.getName().contains("$")) {
				    classes.add(entry.getName().replace("/", ".")
				    		.substring(0, entry.getName().length() - 6));
				}
			}
		}
		
		System.out.println("=====================================");
		for(String className : classes) {
			System.out.println(className);
		}
		
		// 注意这里你无法class.forName，因为这些类没有加载到jvm
	}
}
