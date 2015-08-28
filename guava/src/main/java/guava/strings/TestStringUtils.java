package guava.strings;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

/**
 * 2015年8月28日 16:53:50
 * 1. Strings 功能比较简单
 * 2. CharMatcher 这个更加实用，例如空白字符，这个一般情况我们自己是写不全的(世界语言太多)，靠这些工具类非常好用
 *    常用的CharMatcher常量:
 *    1) WHITESPACE 空白字符
 *    2) BREAKING_WHITESPACE
 *    3) DIGIT 数字
 *    4) INVISIBLE 看不到的字符
 *    【构造自己的CharMatcher】 CharMatcher.anyOf("abc") 就等于abc其一都符合
 *   CharMatcher的常用方法:
 *    1) matchesAllOf 和 matchesAnyOf 和 matchesNoneOf 判定一个字符串是否含有或部分含有
 *    2) collapseFrom 和 trimAndCollapseFrom 将连续匹配到的字符用指定的字符替换【实用】
 *    3) countIn 计算匹配到的字符的个数
 *    4) indexIn lastIndexIn 这些是查找，但只能找第一个或最后一个
 *    5) retainFrom removeFrom replaceFrom trimFrom 抽取或移除匹配到的字符【实用】
 */
public class TestStringUtils {

	public static void main(String[] args) {

		String str = Strings.padEnd("4.", 4, '0');
		System.out.println(str); // 4.00
		str = Strings.padStart("4", 4, '0');
		System.out.println(str); // 0004

		System.out.println(Strings.isNullOrEmpty(" ")); // isNullOrEmpty这个判断不了blank
		System.out.println(Strings.isNullOrEmpty(null)); // 判断字符串是否为空
		
		System.out.println(Strings.commonPrefix("abc", "abd")); // 相同的前缀:ab
		System.out.println(Strings.repeat("=", 30)); // 实现是用arraycopy，性能好些

		// ===========================================================================
		
		System.out.println(CharMatcher.WHITESPACE.matchesAllOf("  　　			　　  ")); // true 里面有tab 中文空格等
		
		// 过滤掉多余的字符串，特别适合split之前 【实用】
		String tabsAndSpace = "    a    b			c";
		System.out.println(CharMatcher.WHITESPACE.collapseFrom(tabsAndSpace, ' '));
		System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpace, ' ')); // trim先
		
		// 抽取出字符串中的数字
		String numberAndLetter = "aa99ff88dd0";
		System.out.println(CharMatcher.DIGIT.retainFrom(numberAndLetter));
		System.out.println("数字多少个:" + CharMatcher.DIGIT.countIn(numberAndLetter));
		
        // CharMatcher最牛的一点就是，它可以组合构建出一个新的CharMatcher
		CharMatcher cm = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);
		System.out.println(cm.retainFrom("9 8aaa 7  6")); // 只拿空格和数字
		
		// 构造自己的CharMatcher
		System.out.println(CharMatcher.anyOf("eko").collapseFrom("bookkeeper", '-')); // 相反的还有个noneOf构造器
	
	    // =============================================================================
		
		String lowerUnderScore = "hello_world";
		System.out.println(CaseFormat.LOWER_UNDERSCORE.to( // 下划线转驼峰形式【实用】
				CaseFormat.LOWER_CAMEL, lowerUnderScore));
	}

}
