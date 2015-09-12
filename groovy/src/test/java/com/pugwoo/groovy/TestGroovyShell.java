package com.pugwoo.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * 2015年9月12日 11:28:19
 * 
 * http://www.blogways.net/blog/2014/09/15/groovy-mvel-javascript.html
 * 根据这篇博客的测试，GroovyShell的性能是Eval的6倍！
 */
public class TestGroovyShell {

	public static void main(String[] args) {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		Script script = shell
				.parse("def mul(x, y) { return x * y }\n mul(x1, y1)");
		binding.setProperty("x1", 88);
		binding.setProperty("y1", 99);
		Object result = script.run();
		
		System.out.println(result);
	}

}
