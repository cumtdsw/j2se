package com.pugwoo.j2se.annotation;

import java.lang.reflect.Method;

/**
 * 2014年11月27日 15:50:46
 * 
 * 测试怎样获取到注解的信息
 */
public class TestMyAnnotation {

    public static void main(String[] args) {
    	/**
    	 * class或method等都有getAnnotation()的方法
    	 */
		Method[] methods = WithAnnotation.class.getDeclaredMethods();
		for(Method m : methods) {
			MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
			if(myAnnotation != null) {
				System.out.println(myAnnotation.name());
				System.out.println(myAnnotation.age());
				for(String in : myAnnotation.interest()) {
					System.out.println("interest:" + in);
				}
			}
		}
	}
    
}
