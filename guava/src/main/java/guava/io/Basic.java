package guava.io;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import com.google.common.io.Closeables;

public class Basic {

	@Test
	public void testClose() throws Exception {
		String str = "hello";
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		Closeables.closeQuietly(in); // 安静地关掉
	}
	
}
