package com.pugwoo.j2se.mvel;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

/**
 * 2015年4月9日 19:02:14
 * mvel支持执行类似java语言的script
 */
public class Variables {

	public static void main(String[] args) {
		
		// 对于有赋值（需要保存变量）的，就需要定义一个变量resolverFactory
		// 这个东西就相当于【上下文】
		VariableResolverFactory vars = new MapVariableResolverFactory();
		
		MVEL.eval("a=3", vars);
		MVEL.eval("System.out.println(a)", vars);
		
	}
	
}
