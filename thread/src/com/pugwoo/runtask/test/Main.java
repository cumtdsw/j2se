package com.pugwoo.runtask.test;

import com.pugwoo.runtask.EasyRunTask;
import com.pugwoo.runtask.ITask;
import com.pugwoo.runtask.TaskResult;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ITask task = new MyTask(100);
		EasyRunTask easyRunTask = new EasyRunTask(task);

		TaskResult result = easyRunTask.start();
		System.out.println("result:" + result.isSuccess() + ",msg:" + result.getMessage());
		
		waitAndPrint(0, easyRunTask);
		
		result = easyRunTask.start();
		System.out.println("result:" + result.isSuccess() + ",msg:" + result.getMessage());
		
		waitAndPrint(1000, easyRunTask);
		
		easyRunTask.stop();
		waitAndPrint(1000, easyRunTask);
		
		easyRunTask.resume();
		waitAndPrint(1000, easyRunTask);
	}
	
	private static void waitAndPrint(long ms, EasyRunTask easyRunTask) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("total:" + easyRunTask.getTotal()
				+ ",processed:" + easyRunTask.getProcessed()
				+ ",success:" + easyRunTask.getSuccess()
				+ ",fail:" + easyRunTask.getFail());
		System.out.println("status:" + easyRunTask.getStatus());
	}
}
