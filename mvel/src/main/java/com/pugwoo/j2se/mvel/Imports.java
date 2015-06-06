package com.pugwoo.j2se.mvel;

import java.io.Serializable;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

public class Imports {

	public static void main(String[] args) {
        ParserContext ctx = new ParserContext();
        try {
            ctx.addImport("time", System.class.getMethod("currentTimeMillis"));
        }
        catch (NoSuchMethodException e) {
            // handle exception here.
        }

        Serializable s = MVEL.compileExpression("time();", ctx);
        Object ans = MVEL.executeExpression(s);
        System.out.println(ans.toString());
        
        // System.out.println(MVEL.eval("time()", ctx)); // 不支持这种
	}
}
