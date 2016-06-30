package com.pugwoo.practice.crontab;

import java.util.Date;

import it.sauronsoftware.cron4j.Scheduler;

/**
 * 重要：这种方式可能导致tomcat没有办法正常结束
 **/
public class Basic {

	public static void main(String[] args) throws Exception {
		Scheduler scheduler = new Scheduler();
		// 这个配置每分钟执行一次
		String id = scheduler.schedule("* * * * *", new Runnable() {
			public void run() {
				System.out.println("hello " + new Date());
			}
		});
		scheduler.start();
		
		// scheduler.reschedule(id, "*/2 * * * *"); // 
		
		Thread.sleep(300000); // pause the main thread for 5 minutes
		scheduler.stop();
	}
	
}
