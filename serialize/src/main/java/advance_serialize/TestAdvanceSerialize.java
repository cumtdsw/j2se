package advance_serialize;

import java.io.IOException;

import utils.SerializeTools;

/**
 * 2014年5月21日 11:50:27
 */
public class TestAdvanceSerialize {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Student student = new Student();
		student.setName("nick");
		student.setAge(27);
		
		byte[] bytes = SerializeTools.serialize(student);
		System.out.println("bytes size:" + bytes.length);
		
		Student stu = (Student) SerializeTools.deserialize(bytes);
		System.out.println(stu);
	}

}
