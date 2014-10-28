package com.pugwoo.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 2012年2月21日
 * 
 * ConcurrentHashMap是线程安全的
 */
public class TestConcurrentHashMap {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Integer> map = 
				new ConcurrentHashMap<Integer, Integer>();
		
		// TODO 这里并没有用多线程来演示
		
		map.put(1, 1);
		System.out.println(map.get(1));

	}
}
