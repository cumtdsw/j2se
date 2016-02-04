package httpclient.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import httpclient.utils.Net;
import httpclient.utils.Net.HttpRespContent;

/**
 * 2014年10月21日 13:47:59 测试我的utils.Net，迁移自Get.java
 */
public class TestNet {

	/**
	 * 演示获取普通的网页
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		HttpHost proxy = null;
		// proxy = new HttpHost("127.0.0.1", 8888, "http");

		String url = "http://www.baidu.com/";
		HttpRespContent content = Net.get(url, null, proxy, null);

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
	}
	
	@Test
	public void testGetWithCookie() throws ClientProtocolException, IOException {
		HttpHost proxy = null;
		 proxy = new HttpHost("127.0.0.1", 8888, "http");

		List<Cookie> cookies = new ArrayList<Cookie>();
		BasicClientCookie cookie = new BasicClientCookie("test", "hello");
		cookie.setDomain("www.baidu.com"); // 必须设置，它必须是url的host的后缀，例如com都可以
//	    cookie.setPath("/");
		cookies.add(cookie);
		
		String url = "http://www.baidu.com/";
		HttpRespContent content = Net.get(url, null, proxy, null, cookies);
		
		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
	}

	/**
	 * 测试下载文件,二进制
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	public void testDownload() throws ClientProtocolException, IOException {
		HttpHost proxy = null;
		// proxy = new HttpHost("127.0.0.1", 8888, "http");

		String img_url = "http://www.baidu.com/img/bdlogo.png";
		FileOutputStream out = new FileOutputStream("C:/baidu_logo.png");
		Net.get(img_url, null, proxy, out);
	}

	/**
	 * 演示post登录，需要本地开一个登录应用例子
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	public void testPostLogin() throws ClientProtocolException, IOException {
		String post_url = "http://127.0.0.1:8080/userRegister/login";

		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "nick");
		params.put("password", "123456");

		HttpRespContent post_content = Net.post(post_url, params, null, null,
				null);
		System.out.println(post_content.content);
	}

}
