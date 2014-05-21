package com.unmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.pugwoo.remote.IStudentService;
import com.pugwoo.remote.Student;

/**
 * 2014年5月21日 10:23:26
 */
public class StudentServiceClient {

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {

		IStudentService studentService = (IStudentService) Naming
				.lookup("IStudentService");
		
		System.out.println(studentService.getStudent());
		
		Student student = new Student();
		student.setId(3);
		student.setName("nick");
		
		studentService.setStudent(student);
		
		System.out.println(studentService.getStudent());

	}

}
