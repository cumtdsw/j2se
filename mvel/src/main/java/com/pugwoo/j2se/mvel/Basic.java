package com.pugwoo.j2se.mvel;

import java.util.Date;

import org.mvel2.MVEL;

import com.pugwoo.j2se.entity.Student;

/**
 * 2015年3月6日 15:03:47
 */
public class Basic {

	public static void main(String[] args) {
		
		/**
		 * 最简单的eval可以没有context，直接执行一些表达式
		 */
		System.out.println(MVEL.eval("1 + 2"));
		
		/**
		 * eval方法: 通常根据一个表达式，从一个Object获得eval后的结果
		 */
		Student student = new Student();
		student.setId(3);
		student.setAge(23);
		student.setName("nick");
		student.setSchool("sysu");
		student.setRegisterDate(new Date());
		
		System.out.println(MVEL.eval("name", student)); // 直接拿值
		System.out.println(MVEL.eval("name == 'nick'", student)); // 判断表达式
		System.out.println(MVEL.eval("id > 2", student));
		
		System.out.println(MVEL.eval("id * age", student)); // 计算
		
	}
	
}
