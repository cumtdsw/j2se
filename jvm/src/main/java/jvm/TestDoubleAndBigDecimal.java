package jvm;

import java.math.BigDecimal;

/**
 * 2015年2月28日 11:32:48
 */
public class TestDoubleAndBigDecimal {

	public static void main(String[] args) {
		// double并不适合表达货币，建议用long以分为单位，或者BigDecimal
		
		// 而BigDecimal是要用BigDecimal(String)方式构造而非BigDecimal(double)
		
		System.out.println(new BigDecimal(0.1));
		System.out.println(new BigDecimal("0.1"));
	}
	
}
