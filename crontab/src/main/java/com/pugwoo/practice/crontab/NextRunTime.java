package com.pugwoo.practice.crontab;

import it.sauronsoftware.cron4j.Predictor;

/**
 * 这是工具的实用功能，计算一个cronexpr的下一次执行时间
 * @author pugwoo
 */
public class NextRunTime {

	public static void main(String[] args) {
		String pattern = "*/5 * * * *";
		Predictor p = new Predictor(pattern);
		for (int i = 0; i < 10; i++) {
			System.out.println(p.nextMatchingDate());
		}
	}
	
}
