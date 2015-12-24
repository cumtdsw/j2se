package com.pugwoo.j2se.mvel;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;

/**
 * 2015年4月9日 19:02:14
 * mvel支持执行类似java语言的script
 */
public class Variables {

	public static void main(String[] args) {
		
		// 对于有赋值（需要保存变量）的，就需要定义一个变量resolverFactory
		// 这个东西就相当于【上下文】
		Map<String, Object> vars = new HashMap<String, Object>();
		
		MVEL.eval("a=3", vars);
		MVEL.eval("System.out.println(a)", vars);
		
		// 关于类型定义变量的有个特点，可以不指定类型
		// 但是如果指定了类型，那么一次就定义一次，否则就报错了，例如String str = "hi"; 执行两遍就出错
		// 所以这个可以看项目对类型的要求高不高来定，也非常合理
		
		// 还可以直接把数字通过(String)的方式转换
		MVEL.eval("a=(String) 3", vars);
		System.out.println(MVEL.eval("a.class", vars));
	}
	
}
