package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用Collections排序的List元素要实现Comparable接口
 * 一般String、Integer等都实现了，但是对于null值，这些默认实现的比较，都会抛空指针，所以一般不用sort(List)，
 * 而自己写Comparator
 * 
 */
public class TestSortingList {
	
	static class Student {
		private Integer age;
		private String name;
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "name:" + name + ",age:" + age;
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		List<String> list = Arrays.asList("a", "b", null);
//		Collections.sort(list); // 因为有null值，所以这里有null pointer exception
		
		List<Student> students = new ArrayList<Student>();
		
		Student student0 = new Student();
		student0.setName(null);
		students.add(student0);
		
		Student student1 = new Student();
		student1.setName("nick");
		students.add(student1);
		
		students.add(null);
		
		Student student2 = new Student();
		student2.setName("karen");
		students.add(student2);
		
		
		// 按students的name排名，如果student为null或其name为null，则放到最后
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student left, Student right) { // 实际项目中，要处理null值
				boolean leftNull = left == null || left.getName() == null;
				boolean rightNull = right == null || right.getName() == null;
				if(leftNull && rightNull) {return 0;}
				if(leftNull) {return 1;} // null值排最后，注意sort是升序排列
				if(rightNull) {return -1;} // null值排最后
				return left.getName().compareTo(right.getName());
			}
		});
		
		for(Student student : students) {
			System.out.println(student);
		}
	}

}
