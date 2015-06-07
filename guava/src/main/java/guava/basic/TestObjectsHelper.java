package guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

class Student implements Comparable<Student>{
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
	// 这个设计也非常精巧
	@Override
	public int compareTo(Student o) {
		return ComparisonChain.start()
				.compare(name, name)
				.compare(age, age)
				.result();
	}
}

public class TestObjectsHelper {

	public static void main(String[] args) {
		Student student = new Student();
		student.name = "nick";
		student.age = 27;
		
		System.out.println(student);
		System.out.println(student.hashCode());
		
		// Objects的equal方法也可以很方便的比较两个对象，和某个对象是null的情况，这就少了很多这类的代码
		String a = "aa";
		String b = null;
		System.out.println(Objects.equal(a, b));
		
		// 2015年6月7日 15:07:03
		// 这个Objects还是非常好用的，什么都可以比，也避免了 Long == Long这种错误的写法
	}
}
