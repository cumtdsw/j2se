package com.pugwoo.groovy;

import groovy.util.Eval;

/**
 * 在Java中直接运行groovy脚本，方式一：Eval
 */
public class TestEvalGroovyScript {

	public static void main(String[] args) {
		System.out.println(Eval.me("1+2"));
		System.out.println(Eval.me("'hello'.length()")); // 可以执行复杂语句
		System.out.println(Eval.me("'hello'"));
		
		// eval 不支持 像mvel那样搞个map的context，自定义很多变量
		// eval只支持自定义命名一个变量，或者用xyz最多3个变量
		System.out.println(Eval.me("var", 5, "var * 2 * var"));
		System.out.println(Eval.x(100, "x - 99"));
		
		// 所以，groovy的Eval在实际项目中没什么用
		
	}
}
