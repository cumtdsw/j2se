package test;

import static interface_enum.ErrorCode.*;

/**
 * 2015年2月13日 14:46:05
 */
public class ErrorCodeTest {

	public static void main(String[] args) {
		System.out.println(NOT_FOUND);
		System.out.println(INTERNAL_ERROR); // 像C/C++一样使用
		
		// NOT_FOUND = "123"; // 这样是修改不了的
		
		LIST.add("hello"); // 这个是可以修改的，所以不建议用mutable的变量做枚举
	}
}
