package com.pugwoo.remote.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.pugwoo.remote.IStudentService;
import com.pugwoo.remote.Student;

public class StudentServiceImpl extends UnicastRemoteObject implements IStudentService {
	
	public StudentServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static Student student;

	@Override
	public Student getStudent() throws RemoteException {
		return student;
	}

	@Override
	public void setStudent(Student student) throws RemoteException {
		StudentServiceImpl.student = student;
	}

}
