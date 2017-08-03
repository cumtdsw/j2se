package com.pugwoo.jackson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	private Integer id;
	private String name;
	private Date birth;
	
	// 对于jackson，transient属性没有将age排除掉。
	// 因为jackson是直接看getter和setter的，可以加注解：@JsonIgnore来去掉
	@JsonIgnore
	private transient int age;
	
	// 使用@JsonProperty("label")可以改变序列化出来的名称
	
	private String _address;
	
	public static void main(String[] args) throws JsonProcessingException {
		User user = new User();
		user.setId(33);
		user.setName("test");
		user.setBirth(new Date());
		user.set_address("test address");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println(json);
	}

	public String toString() {
		return "id:" + id + ",name:" + name + ",birth:" + birth + ",age:" + age;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
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

}
