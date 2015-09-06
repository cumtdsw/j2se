package com.pugwoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015年8月9日 20:12:56
 */
public class TestSLF {
	
	private transient static final Logger LOGGER = LoggerFactory.getLogger(TestSLF.class);

	public static void main(String[] args) {
		// 推荐这样传递参数，减少了log时拼凑字符串的损耗
		LOGGER.info("hello {}", "nick");
		// 高版本的slf支持多个参数
		LOGGER.warn("{} + {} = {}", 1, 2, 3);
		
		try {
			throw new Exception("test ex2");
		} catch (Exception e) {
			// 【重要】logger约定了最后一个参数如果是exception，则打印堆栈
			LOGGER.error("one:{},two:{},three:{}",1,2,3,e); 
		}
	}
	
}
