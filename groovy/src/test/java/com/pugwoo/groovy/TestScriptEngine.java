package com.pugwoo.groovy;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 2015年9月12日 11:53:42
 */
public class TestScriptEngine {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine1 = factory.getEngineByName("groovy");
		String testScript = "def num(int[] a) {return  a[0] * a[1]}";  
		engine1.eval(testScript);  
		Invocable inv = (Invocable) engine1; 
		Object result = inv.invokeFunction("num",  new int[]{88, 99});  
		System.out.println(result);
	}
}
