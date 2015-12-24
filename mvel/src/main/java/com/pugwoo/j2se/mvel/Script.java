package com.pugwoo.j2se.mvel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;

public class Script {

	public static void main(String[] args) throws IOException {
		Map<String, Object> vars = new HashMap<String, Object>();
		
		String resourceFile = "/scripts/hello.mvel";
		InputStream in = Script.class.getResourceAsStream(resourceFile);
		
		String fileContent = readAsReader(in);
		
		MVEL.eval(fileContent, vars);
	}
	
	private static String readAsReader(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
	
}
