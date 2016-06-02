package auto_serialize;

import utils.SerializeTools;

public class TestSerializeAnotherClass {

	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setName("nick");
		student.setScore(99f);
		
		byte[] bytes = SerializeTools.serialize(student);
		System.out.println("bytes size:" + bytes.length);

		// ERROR 这样是转换不过的
		AnotherStudentClass stu = (AnotherStudentClass) SerializeTools.deserialize(bytes);
		System.out.println(stu);
	}
	
}
