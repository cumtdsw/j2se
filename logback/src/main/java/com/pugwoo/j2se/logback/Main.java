package com.pugwoo.j2se.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2016年1月5日 15:28:35
 */
public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOGGER.info("hello logback and slf");
		LOGGER.info("1 + 2 = {}", 1 + 2);
		LOGGER.info("main end");
	}
}
