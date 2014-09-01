package jar_tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 2014年9月1日 22:29:35
 * 这个工具用于查找一个jar包或一个目录下所有jar包的文件列表
 */
public class ListAllFileInJar {
	
	public static JarFile getJarFile(String filePath) throws IOException {
		return new JarFile(filePath);
	}

	/**
	 * 获得一个jar包的所有entry，也就是jar包中所有文件的列表。
	 * 当然后缀class结尾的就是编译java后的文件。
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static List<JarEntry> getJarEntries(String filePath)
			throws IOException {
		JarFile jarfile = getJarFile(filePath);
		Enumeration<JarEntry> entryList = jarfile.entries();
		List<JarEntry> list = new ArrayList<JarEntry>();
		while (entryList.hasMoreElements()) {
			list.add(entryList.nextElement());
		}
		return list;
	}
	
	/**
	 * 当file是jar文件时，读取该jar文件；当file是目录时，读取整个目录下的所有jar文件。
	 * @param file
	 * @return String是文件的绝对路径
	 * @throws IOException 
	 */
	public static Map<String, List<JarEntry>> getJarEntries(File file) throws IOException {
		Map<String, List<JarEntry>> map = new HashMap<String, List<JarEntry>>();
		
		if(file.isFile() && file.getName().endsWith(".jar")) {
			map.put(file.getAbsolutePath(), getJarEntries(file.getAbsolutePath()));
		} else if(file.isDirectory()) {
			// 搜索整个文件夹
			File[] files = file.listFiles();
			for(File f : files) {
				Map<String, List<JarEntry>> tmp = getJarEntries(f);
				map.putAll(tmp);
			}
		}
		
		return map;
	}

	public static void main(String[] args) throws IOException {
		
		File file = new File("I:/java/lib");
		
		Map<String, List<JarEntry>> map = getJarEntries(file);
		
		System.out.println("total files:" + map.size());
		for(String filepath : map.keySet()) {
			System.out.println(filepath + "include files:" + map.get(filepath).size());
		}

	}

}
