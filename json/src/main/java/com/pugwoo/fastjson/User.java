package com.pugwoo.fastjson;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class User {

	private Integer id;
	private String name;
	private Date birth;
	private transient int age;
	
	// XXX fastjson有问题：下划线开头的属性，序列化之后下划线不见了，得用@JSONField
	private String _address;
	
	// 单元测试，
	public static void main(String[] args) {
		User user = new User();
		user.setId(33);
		user.setName("test");
		user.setBirth(new Date());
		user.set_address("test address");
		
		System.out.println(JSON.toJSONString(user, true));
	}

	public String toString() {
		return "id:" + id + ",name:" + name + ",birth:" + birth + ",age:" + age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

}
