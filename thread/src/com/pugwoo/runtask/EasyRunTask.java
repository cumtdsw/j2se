package com.pugwoo.runtask;

import java.util.ArrayList;
import java.util.List;

/**
 * 2015年7月21日 11:17:16
 * 按最简单的方式：每次只能有一个task
 */
public class EasyRunTask {

	// 要执行的任务
	private ITask task;
	// 状态
	private StatusEnum status = StatusEnum.NEW;
	// 执行任务的线程
	private Thread thread; // 可以考虑用线程池
	
	private List<Exception> exceptions = new ArrayList<Exception>();
	private int total;
	private int processed;
	private int success;
	private int fail;
	
	public static enum StatusEnum {
		NEW, RUNNING, STOPPING, STOPPED, FINISHED
	}
	
	public EasyRunTask() {
	}
	public EasyRunTask(ITask task) {
		this.task = task;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("task:").append(task == null ? "null" : task.getClass().getName()).append(",");
		sb.append("status:").append(status).append(",");
		sb.append("total:").append(total).append(",");
		sb.append("processed:").append(processed).append(",");
		sb.append("success:").append(success).append(",");
		sb.append("fail:").append(fail).append(",");
		sb.append("exceptions:").append(exceptions.size());
		return sb.toString();
	}
	
	/**
	 * 启动任务
	 * @return
	 */
	public synchronized TaskResult start() {
		return run(true);
	}
	
	/**
	 * 停止之后恢复任务
	 * @return
	 */
	public synchronized TaskResult resume() {
		return run(false);
	}

	private synchronized TaskResult run(boolean reset) {
		if(status == StatusEnum.RUNNING || status == StatusEnum.STOPPING) {
			return new TaskResult(false, "cannot start when running");
		}
		if(task == null) {
			return new TaskResult(false, "task is not assigned");
		}
		if(thread != null && thread.isAlive()) {
			return new TaskResult(false, "thread is running");
		}
		
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (status) {
						if(status == StatusEnum.STOPPING) {
							status = StatusEnum.STOPPED;
							return;
						}
					}
					int restCount = 0;
					try {
						restCount = task.getRestCount();
					} catch (Exception e) {
						exceptions.add(e);
					}

					if(restCount <= 0) {
						synchronized (status) {
							status = StatusEnum.FINISHED;
						}
						return;
					}
					total = processed + restCount;
					try {
						TaskResult result = task.runStep();
						if(result == null || !result.isSuccess()) {
							fail++;
						} else {
							success++;
						}
					} catch (Exception e) {
						exceptions.add(e);
						fail++;
					} finally {
						processed++;
					}
				}
			}
		});
		
		if(reset) {
			task.reset();
			total = 0;
			processed = 0;
			success = 0;
			fail = 0;
			exceptions.clear();
		}

		status = StatusEnum.RUNNING;
		thread.start();
		
		return new TaskResult(true);
	}

	/**
	 * 停止任务
	 * @return
	 */
	public synchronized TaskResult stop() {
		if(status == StatusEnum.RUNNING) {
			status = StatusEnum.STOPPING;
			return new TaskResult(true);
		}
		return new TaskResult(false, "stop must at running status");
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	public void setTask(ITask task) {
		this.task = task;
	}
	public ITask getTask() {
		return task;
	}
	public List<Exception> getExceptions() {
		return exceptions;
	}
	/**
	 * 获得所有的记录数
	 * @return
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 获得已处理的记录数
	 * @return
	 */
	public int getProcessed() {
		return processed;
	}
	/**
	 * 获得成功的记录数
	 * @return
	 */
	public int getSuccess() {
		return success;
	}
	/**
	 * 获得失败的记录数
	 * @return
	 */
	public int getFail() {
		return fail;
	}
	
}
