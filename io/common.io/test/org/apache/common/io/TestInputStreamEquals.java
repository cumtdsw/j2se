package test.org.apache.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * 2014年6月10日 11:14:59 
 * 比较2个input stream是否相等
 */
public class TestInputStreamEquals {

	public static void main(String[] args) throws MalformedURLException, IOException {

		InputStream in = new URL("http://www.qq.com").openStream();
		InputStream in2 = new URL("http://www.qq.com").openStream();

		System.out.println(IOUtils.contentEquals(in, in2));
	}

}
