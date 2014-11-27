package com.pugwoo.j2se.annotation;

public class WithAnnotation {

	/**
	 * 注解一些信息
	 */
	@MyAnnotation(name = "nick", age = 27, interest = { "basketball",
			"internet" })
	public void test() {

	}

}
