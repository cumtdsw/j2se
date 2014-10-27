package com.pugwoo.j2se.apache_commons.lang;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 演示通用的通过反射来输出java bean的所有属性 2014年10月10日 19:06:41
 */
public class BeanReflectToString {

	private String name;
	private int age;
	private Date registerTime;

	@Override
	public String toString() {
		// 使用apache commons lang，可以设置不同的输出格式
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static void main(String[] args) {
		BeanReflectToString bean = new BeanReflectToString();
		bean.setName("nick");
		bean.setAge(27);
		bean.setRegisterTime(new Date());

		System.out.println(bean);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

}
