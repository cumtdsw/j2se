package com.pugwoo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * 2013年2月20日 23:00:57
 */
public class GetStarted {

	// 比较常用的用法，根据类名实例化一个静态的全局日志记录器
	private static final Logger logger = LogManager.getLogger(GetStarted.class);

	public static void main(String[] args) throws InterruptedException {
		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		Thread.sleep(1);
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");
		
		logger.error("hello:{}", "world");
	}

}
