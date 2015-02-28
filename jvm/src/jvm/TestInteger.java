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
		
		// 注意负数的取模是负数，如果a和b都是int，那么(a/b)*b+(a%b)==a
		System.out.println("-229 % 5 = " + (-9 % 5));
		System.out.println("-9 is odd:" + isOdd(-9));
	}
	
	private static boolean isOdd(int a) {
		return (a & 0x1) != 0; // 最快的方式，来自Java解惑一书
	}
}
