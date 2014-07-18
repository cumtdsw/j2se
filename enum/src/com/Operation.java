package com;

/**
 * 例子来自Effective Java
 */
public enum Operation {
	PLUS("+") {
		@Override
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		@Override
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		@Override
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		@Override
		double apply(double x, double y) {
			return x / y;
		}
	};
	
	private final String symbol;
	Operation(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
	
	// 【这个用得太好了】强制枚举类型去实现
	abstract double apply(double x, double y);
	
	// unit test
	public static void main(String[] args) {
		double x = 1;
		double y = 2;
		
		for(Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
}
