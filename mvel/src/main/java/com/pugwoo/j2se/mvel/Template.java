package com.pugwoo.j2se.mvel;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.templates.TemplateRuntime;

public class Template {

	public static void main(String[] args) {
		String template = "hello, my name is @{name.toUpperCase()}";
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("name", "nick");
		
		String output = (String) TemplateRuntime.eval(template, vars);
		
		System.out.println(output);
	}
}
