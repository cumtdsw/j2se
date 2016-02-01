package custom_serialize;
import java.io.IOException;

import utils.SerializeTools;

/**
 * 自定义序列化的方式极大化地减少序列化完的字节数和序列化效率
 */
public class TestCustomSerialize {

	/**
	 *  使用java自带的ObjectOutputStream ObjectInputStream
	 *  进行序列化和反序列化
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Student student = new Student();
		student.setName("nick");
		student.setScore(99f);
		
		byte[] bytes = SerializeTools.serialize(student);
		System.out.println("bytes size:" + bytes.length);

		Student stu = (Student) SerializeTools.deserialize(bytes);
		System.out.println(stu);
	}

}
