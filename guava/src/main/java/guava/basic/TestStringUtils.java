package guava.basic;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

public class TestStringUtils {

	public static void main(String[] args) {

		String str = Strings.padEnd("4.", 4, '0');
		System.out.println(str); // 4.00
		str = Strings.padStart("4", 4, '0');
		System.out.println(str); // 0004

		// 如果string是null，则返回一个空字符串
		System.out.println(Strings.nullToEmpty(null));
		// 判断字符串是否为空
		System.out.println(Strings.isNullOrEmpty(null));

		// 过滤掉多余的字符串，特别适合split之前
		String tabsAndSpace = "    a    b			c";
		System.out.println(CharMatcher.WHITESPACE.collapseFrom(tabsAndSpace,
				' '));
		System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(
				tabsAndSpace, ' ')); // trim先
		
		// 抽取出字符串中的数字
		String numberAndLetter = "aa99ff88dd0";
		System.out.println(CharMatcher.DIGIT.retainFrom(numberAndLetter));
		
        // CharMatcher最牛的一点就是，它可以组合构建出一个新的CharMatcher
		CharMatcher cm = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);
		System.out.println(cm.retainFrom("9 8aaa 7  6")); // 只拿空格和数字
		
		
	}

}
