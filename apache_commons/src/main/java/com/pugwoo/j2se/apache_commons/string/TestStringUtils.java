package com.pugwoo.j2se.apache_commons.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestStringUtils {

	@Test
	public void testBasic() {
		String a = "  　";
		// 判断字符串是否为空，支持Tab，全角等，自己写不到这么全
		System.out.println(StringUtils.isBlank(a)); 
		
		// 判断两个字符串是否相等
		String str1 = "Abc";
		String str2 = "abc";
		System.out.println(StringUtils.equalsIgnoreCase(str1, str2));
	}
	
}
