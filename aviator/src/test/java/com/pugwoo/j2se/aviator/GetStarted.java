package com.pugwoo.j2se.aviator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * 2014年12月4日 17:18:39
 */
public class GetStarted {
	
	/**
	 * aviator算术只返回Long或Double
	 */
	@Test
	public void testMathEx() {
		Long result = (Long) AviatorEvaluator.execute("1+2*3");
		System.out.println(result);
	}

	public static void main(String[] args) {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("email", "killme2008@gmail.com");
		String username = (String) AviatorEvaluator.execute(
				"email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1:'unknow'", env);
		System.out.println(username);
	}

}
