package com.pugwoo.j2se.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2014年11月27日 15:39:04 <br>
 * 注解的声明关键字是: @interface <br>
 * @Target 指定该注解使用的地方，可以是类、包、方法、成员变量、参数等等
 * @Retention 指定该注解的应用方法，一般都是RUNTIME，这样编译时会编进class中
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	// 下面是注解的参数
	// 包括：所有基本类型、String、Class、enum、Annotation、以上类型的数组形式。
	
	public String name() default "";
	
	public int age() default 0;
	
	public String[] interest() default {"math", "music"};
	
}
