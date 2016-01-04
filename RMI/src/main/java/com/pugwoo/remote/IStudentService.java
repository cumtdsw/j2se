package com.pugwoo.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 2014年5月21日 10:18:52
 * 主要演示自定义序列化结构体
 */
public interface IStudentService extends Remote{

	public Student getStudent() throws RemoteException;

	public void setStudent(Student student) throws RemoteException;
	
}
