package guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

class Student {
	public String name;
	public int age;

	@Override
	public String toString() {
		// 使用MoreObjects的toStringHelper
		return MoreObjects.toStringHelper(this)
				.add("name", name)
				.add("age", age).toString();
	};
	
	@Override
	public int hashCode() {
		// 使用Objects工具生成hscode
		return Objects.hashCode(name, age);
	}
	
	// 此外还有ComparisonChain工具，用于比较多个字段
}

public class TestObjectsHelper {

	public static void main(String[] args) {
		Student student = new Student();
		student.name = "nick";
		student.age = 27;
		
		System.out.println(student);
		System.out.println(student.hashCode());
	}
}
