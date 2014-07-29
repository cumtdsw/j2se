package jvm;

public class TestInteger {

	public static void main(String[] args) {
		Integer i = 123;
		System.out.println(Integer.toBinaryString(i)); // 1111011
		
		int a = 10;
		a <<= 1;
		System.out.println("a=" + a);
		
		int b = -2;
		b <<= 1; // java的唯一本身就是有符号的
		System.out.println("b=" + b);
		
		int c = -30;
		c = c >>> 1; // 无符号移位，没有<<<这种
		System.out.println("c=" + c); // 此时不是15了，而是2147483633，一个大数
		
		
	}
	
}
