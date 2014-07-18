package com;

/**
 * 水果枚举
 */
public enum Fuilt {

	APPLE, ORANGE, BANANA;
	
	// unit test
	public static void main(String[] args) {
		Fuilt[] fuilts = Fuilt.values();
		for(Fuilt f : fuilts) {
			System.out.println(f);
		}
	}
}
