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
		// Array数组[]
		System.out.println(MVEL.eval("{1,2,3}"));
		
		// List
		System.out.println(MVEL.eval("[1,2,3]"));	
		// map
		System.out.println(MVEL.eval("[1:2,3:4]"));
		
		VariableResolverFactory vars = new MapVariableResolverFactory();
		// 定义一个list，里面有两个map
		MVEL.eval("objs=[['name':'nick','age':20],['name':'karen','age':18]]", vars);
		System.out.println(MVEL.eval("objs", vars));
		System.out.println(MVEL.eval("objs[0]", vars)); // 获得数组元素
		System.out.println(MVEL.eval("objs[0]['name']", vars)); // 获得map元素
		System.out.println(MVEL.eval("(name in objs)", vars)); // 获得某个属性并组成数组
		System.out.println(MVEL.eval("($ in objs if true)", vars)); // if条件遍历取出符合条件的元素
		System.out.println(MVEL.eval("($ in objs if $.name=='nick')", vars));
		
		// 注：这些操作是可以嵌套的
		
		// 很牛的用法：(doSomeMethod() in listOfThings if $.shouldBeRun())
		System.out.println(MVEL.eval("(toUpperCase() in ['foo', 'bar'])"));
		// (($ < 10) in [2,4,8,16])
		// ($ in [2,3,8,16] if $ < 10)
		
		// 创建一个map，可以看出，$表示的就是后面in里面的元素
		System.out.println(MVEL.eval("(['age':$] in (age in objs))", vars));
		
		// 数组转List可以用：java.util.Arrays.asList
		
		// 2015年6月5日 11:32:02
		// 字符串也可以当作数组来用，a="Hello"，那么a[0]就是H
		
		// 数组切片
		
	}
	
}
