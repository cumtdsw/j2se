package com.pugwoo.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.codehaus.groovy.control.CompilationFailedException;

/**
 * 
 */
public class TestGroovyClassLoader {

	public static void main(String[] args) throws CompilationFailedException, IOException, InstantiationException, IllegalAccessException {
		GroovyClassLoader loader = new GroovyClassLoader();
		
		InputStream in = TestGroovyClassLoader.class.getResourceAsStream("/groovy/hello.groovy");
		
		/**
		 * 加载groovy类可以从text或inputstream加载
		 * 但是inputstream可能有编码问题 XXX
		 */
		Class groovyClass = loader.parseClass(in, "hello.groovy");

		GroovyObject object = (GroovyObject) groovyClass.newInstance();
		Object result = object.invokeMethod("num", new int[]{88, 99});
        System.out.println(result);
	}
	
}
