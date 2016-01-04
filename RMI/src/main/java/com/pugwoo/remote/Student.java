package com.pugwoo.remote;

import java.io.Serializable;

/**
 * 2014年5月21日 10:09:57
 */
public class Student implements Serializable {

	/**
	 * 版本控制
	 */
	private static final long serialVersionUID = 1L;

	private int id; // 学生id

	private String name; // 学生姓名

	@Override
	public String toString() {
		return id + ":" + name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
