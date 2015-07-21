package com.pugwoo.runtask.test;

import java.util.ArrayList;
import java.util.List;

import com.pugwoo.runtask.ITask;
import com.pugwoo.runtask.TaskResult;

public class MyTask implements ITask {
	
	private List<Long> tasks;
	
	private int size;
	
	public MyTask(int size) {
		this.size = size;
	}
	
	@Override
	public void reset() {
		tasks = new ArrayList<Long>(size);
		for(int i = 0; i < size; i++) {
			tasks.add((long)i);
		}
	}

	@Override
	public int getRestCount() {
		return tasks.size();
	}

	@Override
	public TaskResult runStep() {
		Long id = tasks.remove(0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TaskResult(true, "finish " + id);
	}

}
