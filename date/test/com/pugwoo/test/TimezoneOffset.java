package com.pugwoo.test;

import java.util.Calendar;

/**
 * 2015年2月28日 17:24:22
 */
public class TimezoneOffset {

	public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.set(1970, 0, 1, 0, 0, 0);
        
        // 当前时区下的计算机起始时间,即与格林威治时区的时间差
        long timezone = calendar.getTimeInMillis();
        
        System.out.println(timezone);
	}
}
