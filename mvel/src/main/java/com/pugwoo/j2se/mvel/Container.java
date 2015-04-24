package com.pugwoo.j2se.mvel;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

/**
 * 2015年4月24日 14:48:10
 * 
 * mvel的容器实际上非常强大
 */
public class Container {

	public static void main(String[] args) {
		
		// 数组
		System.out.println(MVEL.eval("[1,2,3]"));	
		// map
		System.out.println(MVEL.eval("[1:2,3:4]"));
		
		VariableResolverFactory vars = new MapVariableResolverFactory();
		// 定义一个list，里面有两个map
		MVEL.eval("objs=[['name':'nick','age':20],['name':'karen','age':18]]", vars);
		System.out.println(MVEL.eval("objs", vars));
		System.out.println(MVEL.eval("objs[0]", vars)); // 获得数组元素
		System.out.println(MVEL.eval("(name in objs)")); // 获得某个属性并组成数组
		System.out.println(MVEL.eval("($ in objs if true)")); // if条件遍历取出符合条件的元素
		System.out.println(MVEL.eval("($ in objs if $.name=='nick')"));
		
		// 注：这些操作是可以嵌套的
		
		// 创建一个map，可以看出，$表示的就是后面in里面的元素
		System.out.println(MVEL.eval("(['age':$] in (age in objs))"));
		
		// 数组转List可以用：java.util.Arrays.asList
		
		
	}
	
}
