package com.pugwoo.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2015年2月28日 15:09:50
 * replace和replaceAll的区别不是一个只替换一个另一个替换全部的意思，
 * 当然C++中确实有这么恶心的可能。
 * 
 * replace和replaceAll都替换全部
 * 
 * replace没有正则表达式，是什么替换什么
 * replaceAll用正则表达式，所以也容易出问题。。
 */
public class ReplaceAndReplaceAll {

	public static void main(String[] args) {
		String className = ReplaceAndReplaceAll.class.getName();
		System.out.println(className.replace(".", "/"));
		
		System.out.println(className.replaceAll(".", "/")); // 这个是有问题的
		System.out.println(className.replaceAll("\\.", "/")); // regex的.要转义
		System.out.println(className.replaceAll(Pattern.quote("."), "/")); // ok

		// 在windows下的文件分隔符是/，这个刚好是转义字符，所以windows面这一行有问题
		// System.out.println(className.replaceAll("\\.", File.separator));
		System.out.println(className.replace(".", File.separator)); // 【推荐】语义清晰
	    System.out.println(className.replaceAll("\\.", Matcher.quoteReplacement(File.separator))); // ok
	
	}
	
}
