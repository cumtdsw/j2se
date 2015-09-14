package guava.basic;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;

/**
 * 2015年9月14日 15:44:07
 * 测试base64等的
 */
public class TestEncodeDecode {

	@Test
	public void testBase64() {
		byte[] bytes = "hello".getBytes(Charsets.UTF_8);
		
		String base64 = BaseEncoding.base64().encode(bytes);
		System.out.println("encode base64:" + base64);
		
		byte[] decoded = BaseEncoding.base64().decode(base64);
		System.out.println("decode to string:" + new String(decoded, Charsets.UTF_8));
	}
	
}
