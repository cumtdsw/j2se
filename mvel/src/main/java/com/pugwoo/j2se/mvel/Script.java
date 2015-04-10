package com.pugwoo.j2se.mvel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

public class Script {

	public static void main(String[] args) throws IOException {
		VariableResolverFactory vars = new MapVariableResolverFactory();
		
		String resourceFile = "/scripts/fquicksort.mvel";
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
