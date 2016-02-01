package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import advance_serialize.Student;

/**
 * 2014年5月21日 11:50:27
 */
public class TestAdvanceSerialize {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// ObjectOutputStream序列化
		FileOutputStream f = new FileOutputStream("tmp_TestAdvanceSerialize");
		ObjectOutputStream s = new ObjectOutputStream(f);
		
		Student student = new Student();
		student.setName("nick");
		student.setAge(27);
		s.writeObject(student);
		
		s.flush();
		s.close();
		
		// ObjectInputStream反序列化
		FileInputStream in = new FileInputStream("tmp_TestAdvanceSerialize");
		ObjectInputStream ins = new ObjectInputStream(in);
		Student stu = (Student) ins.readObject();
		
		System.out.println(stu);
		ins.close();
	}

}
