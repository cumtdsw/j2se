package guava.basic;

import com.google.common.primitives.Longs;

/**
 * 2015年9月2日 15:48:26
 */
public class TestFormatParser {

	public static void main(String[] args) {
		/**
		 * 如果直接使用Long.valueOf(String)来解析long，可能会抛异常，然后还要catch，这个很麻烦
		 * 使用guava的Longs比较方便，如果解析失败，则返回null
		 */
		System.out.println(Longs.tryParse("123"));
		System.out.println(Longs.tryParse(" 1123  ".trim()));
		System.out.println(Longs.tryParse("")); // null
		System.out.println(Longs.tryParse(null)); // null pointer ex
	}
	
}
