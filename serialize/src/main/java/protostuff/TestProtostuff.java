package protostuff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 2016年2月1日 16:38:41
 * 测试protostuff序列化和反序列化
 */
public class TestProtostuff {
	
	private static <T> byte[] serialize(T t, Class<T> clazz) {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		return ProtostuffIOUtil.toByteArray(t, schema, buffer);
	}
	
	private static <T> void deserialize(byte[] bytes, Class<T> clazz, T t) {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, t, schema);
	}

	public static void main(String[] args) {
		Student student = new Student();
		student.setNumber(3);
		student.setName("nick");
		student.setBirth(new Date());
		student.setScore(new BigDecimal(99));
		List<String> interests = new ArrayList<String>();
		interests.add("music");
		interests.add("basketball");
		student.setInterests(interests);
		
		byte[] bytes = serialize(student, Student.class);
		System.out.println("serialized bytes length:" + bytes.length); // 40字节
		
		Student student2 = new Student();
		deserialize(bytes, Student.class, student2);
		System.out.println(student2);
		
		// 对于json数据，序列化后大小：
		System.out.println("json serialized bytes:" + JSON.toJSONString(student).getBytes().length);
	}
}
