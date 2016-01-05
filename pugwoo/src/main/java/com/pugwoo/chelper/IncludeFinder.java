package com.pugwoo.chelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2014年2月11日 16:00:22
 * 先实现最简单的方式，扫描全部的输入
 * 然后获得所有的-I的绝对地址
 * 最后提供查询功能
 */
public class IncludeFinder {

	// include文件的绝对位置
	// 【重要】对于make输出中的相对路径，需要指定一下编译的基准路径。
	private static List<String> includePaths = new ArrayList<String>();

	private static Set<String> notFoundPaths = new HashSet<String>();

	/**
	 * 解析gcc/g++命令中的-I的文件
	 * @param line
	 * @return
	 */
	private static List<String> parseIncludes(String basePath, String line) {
		List<String> list = new ArrayList<String>();
		if (line.isEmpty()) {
			return list;
		}

		String strs[] = line.split("\\s+");
		if (!(strs[0].equals("gcc") || strs[0].equals("g++"))) {
			return list;
		}

		for (String str : strs) {
			if (str.isEmpty()) {
				continue;
			}

			boolean isStartWithI = false;
			while (str.startsWith("-I")) { // 支持-I-I-I../include这样的写法
				str = str.substring(2);
				isStartWithI = true;
			}

			if (isStartWithI) {
				String path = str;
				File file;

				if (path.isEmpty()) {
					continue;
				} else if (path.startsWith(".")) {
					file = new File(basePath + "/" + path); // 相对路径
				} else {
					file = new File(path);
				}

				if (file.exists()) {
					list.add(file.getAbsolutePath());
				} else {
					if (!notFoundPaths.contains(file.getAbsolutePath())) {
						System.err.println("File " + file.getAbsolutePath()
								+ " not exists.");
						notFoundPaths.add(file.getAbsolutePath());
					}
				}
			}
		}
		return list;
	}

	/**
	 * 输入参数：make输出的文件位置，这个文件是make>a.txt获得的
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.out.println("params: make_output_file_path");
			return;
		}

		File file = new File(args[0]);
		if (!file.isFile() || !file.exists()) {
			System.err.println("file not exists or is not regular file");
			return;
		}

		String basePath = file.getParent();
		System.out.println("makefile basePath:" + basePath);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			// 解析出include的绝对路径
			List<String> includes = parseIncludes(basePath, line);
			for (String str : includes) {
				if (!includePaths.contains(str)) {
					includePaths.add(str);
				}
			}
		}

		for (String path : includePaths) {
			System.out.println("Path: " + path);
		}

		System.out.println("parse ok, input include file(full name) to query:");

		while (true) {
			System.out.print(">>");
			BufferedReader inBr = new BufferedReader(new InputStreamReader(
					System.in));
			String l = inBr.readLine();
			if (l == null) {
				break;
			}
			if (l.isEmpty()) {
				continue;
			}
			// 查找
			boolean hasFound = false;
			for (String path : includePaths) {
				File f = new File(path + "/" + l);
				if (f.exists()) {
					System.out.println(f.getAbsolutePath());
					hasFound = true;
				}
			}
			if (!hasFound) {
				System.err.println("not found");
			}
		}
	}
}
