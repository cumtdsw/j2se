package com.pugwoo.fastjson_from2016;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 注解@JSONField可以指定json序列化后的名称，特别方便
 * 
 * @author nick
 */
public class TestJSONFieldAnnotation {
	
	public static void main(String[] args) {
		StudentVO studentVO = new StudentVO();
		studentVO.setName("nick");
		studentVO.set_package("studentmath");
		studentVO.setBirth(new Date());
		
		System.out.println(JSON.toJSONString(studentVO));
	}

	public static class StudentVO {
		
		private String name;
		
		@JSONField(name = "package")
		private String _package; // package是java关键字，但是序列化要想用这个名字，就只能注解
		
		@JSONField(format = "yyyy-MM-dd")
		private Date birth; // 可以设定日期格式的输出方式，例如不要时间

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String get_package() {
			return _package;
		}

		public void set_package(String _package) {
			this._package = _package;
		}

		public Date getBirth() {
			return birth;
		}

		public void setBirth(Date birth) {
			this.birth = birth;
		}
		
	}
	
	
}
