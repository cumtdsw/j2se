package com.pugwoo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * 2013年2月20日 23:00:57
 */
public class GetStarted {

	// 比较常用的用法，根据类名实例化一个静态的全局日志记录器
	private static final Logger logger = LogManager.getLogger(GetStarted.class);

	public static void main(String[] args) {
		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");
		
		logger.error("hello:{}", "world");
		
		try {
			throw new Exception("test ex");
		} catch (Exception e) {
			logger.error("err happen", e); // 千万不要这么写：logger.error(e); 这样只是打印出e.toString()
		}
	}

}
