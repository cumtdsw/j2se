package guava.strings;

import com.google.common.base.Splitter;

public class TestSplitter {

	public static void main(String[] args) {
		// 也支持正则表达式
		Iterable<String> strs = Splitter.on("|").split("foo|bar|baz|| |");
		for(String str : strs) {
			System.out.println(">>" + str);
		}
		
		// 和mapJoiner对应的，也有个mapSplitter，不过和json就很接近了
		// 不知道常不常用
	}
}
