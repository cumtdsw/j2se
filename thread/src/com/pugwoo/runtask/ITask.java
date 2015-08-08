package com.pugwoo.runtask;

/**
 * 2015年7月21日 11:17:23
 * 
 */
public interface ITask {

	/**
	 * 获得剩下的任务记录数，当获取的任务数小于等于0时，认为任务结束
	 */
	int getRestCount();

	/**
	 * 执行单个记录，单线程执行
	 * @return 成功返回true，失败返回false
	 */
	TaskResult runStep();
	
	/**
	 * 初始化Task，使得重跑时可以正常执行，这个方法会在每次start时调用
	 * 如果是resume调用则不掉这个接口
	 */
	void reset();
}
