package guava.container;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class TestList {

	public static class Student {
		private String name;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	}
	
	@Test
	public void testTransform() {
		List<String> str = Lists.newArrayList("nick", "karen");
		List<Student> students = Lists.transform(str, 
				new Function<String, Student>() {
					@Override
					public Student apply(String input) {
						Student student = new Student();
						student.setName(input);
						student.setAge(20);
						return student;
					}
				});
		
		students.get(0).setAge(21); // 【重要问题】guava转换的List中的元素，无法改变！！
		for(Student student : students) {
			System.out.println(student.getName() + ":" + student.getAge());
		}
	}
	
	@Test
	public void testJava8Transform() {
		List<String> str = Lists.newArrayList("nick", "karen");
		List<Student> students = str.stream().map(s -> {
			Student student = new Student();
			student.setName(s);
			student.setAge(20);
			return student;
		}).collect(Collectors.toList());
		
		students.get(0).setAge(21); // java 8 可以!! 因此，并不推荐使用Lists.transform功能
		// 更广了说，能使用java 8的就不需要再使用guava了
		for(Student student : students) {
			System.out.println(student.getName() + ":" + student.getAge());
		}
	}
	
}
