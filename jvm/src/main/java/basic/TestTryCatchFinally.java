package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016年1月8日 17:57:50
 * tryCatchFinally
 * 可以确定的事情：
 * # finnaly可以不要catch，无论try里面抛出什么异常有没有被catch到，甚至return值，finally都会被执行到
 * # try里面return的引用值，【不会】在finally中被改变，但是finally里面是可以改变引用的对象的内容的
 */
public class TestTryCatchFinally {
	
	public static List<String> test() {
		List<String> list = new ArrayList<String>();
		try {
			list.add("in try");
			return list;
		} finally {
			list.add("in finally");
		}
	}

	public static void main(String[] args) {
		List<String> result = test();
		for(String str : result) {
			System.out.println(str);
		}
	}
	
}
