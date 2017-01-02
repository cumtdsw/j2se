package com.pugwoo.fastjson;

import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 2011
 */
public class GetStart {

	/**
	 * 这是一个利用java来解析数字的方法，支持整数（不支持整数，只支持到Long）、小数。
	 */
	public static Object getNumber(String str) {
		if (str == null)
			return null;
		Object object = null;
		try {
			object = Long.parseLong(str);
		} catch (NumberFormatException e) {
			try {
				object = Double.parseDouble(str);
			} catch (NumberFormatException ex) {
			}
		}
		return object == null ? str : object;
	}
	
	/**
	 * 测试解析数字的速度，数字，每个解析时间：
	 * -1  48ns
	 * 1   50ns
	 * 12  60ns
	 * 123 75ns
	 * 1234 100ns
	 * 12345 110ns
	 * 123456789123456789 303ns
	 * 123456789123456789123456789 3825ns double
	 * 1.23 1295ns
	 * 1.23456789 1500ns
	 * 1.23456789123456789 3509ns
	 */
	public static void benchGetNumberValue() {
		long start = System.currentTimeMillis();
		int times = 10000000;
		for(int i = 0; i < times; i++) {
			getNumber("123");
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) * 1000000.0
				 / times + "ns");
	}
	
	/**
	 * 测试fastjson解析数字的性能
	 * -1   315ns
	 * 1    334ns
	 * 12   343ns
	 * 123  351ns
	 * 123456789123456789 629ns
	 * 123456789123456789123456789 1312ns
	 * 1.23456789123456789  1392ns
	 */
	public static void benchJsonNumber() {
		long start = System.currentTimeMillis();
		int times = 10000000;
		for(int i = 0; i < times; i++) {
			JSON.parseObject("1.23456789123456789", Object.class);
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) * 1000000.0
				 / times + "ns");
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		/**
		 * parse
		 */
		String json = "{id:3,name:'pugwoo',age:''}";
		Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
		System.out.println(map);

		/**
		 * 日期
		 */
		SerializeWriter out = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(out);
		serializer.config(SerializerFeature.UseISO8601DateFormat, true);
		serializer.write(new Date());
		System.out.println(out.toString());

		/**
		 * 反序列化成对象，非入侵式，通过getter/setter
		 */
		String userJson = "{id:3,name:'pugwoo',age:25,birth:'2012-12-22 12:23:34'}";
		User user = JSON.parseObject(userJson, User.class);
		System.out.println(user);
		
		/**
		 * list
		 */
		Object i = JSON.parseObject("[53564,79798,798]", Object.class);
		System.out.println(i.getClass());
		System.out.println(i);
	}

}
