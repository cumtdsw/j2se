package com.pugwoo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 2015年9月24日 17:46:47 基于http client的浏览器工具
 * 2015年9月25日 14:20:34 增加ssl忽略设置
 * 
 * 历史log:
 * 2012-12-3 下午03:23:35 http get数据
 * 2013年11月30日 22:41:21 由于网络问题，增加重试机制，增加成功率
 * 2014年10月21日 13:48:21 去掉重试机制，交给上一层去完成
 * 2014年10月21日 14:52:51 使用新版4.3.5的httpclient，并修改GET和POST方法
 * 2014年10月22日 09:25:38 增加发送自定义cookie，但不会管理cookie上下文
 * 
 * @author pugwoo
 * 
 * 计划实现：
 * 1. TODO post上传文件
 */
public class Browser {
	
	/**
	 * http返回报文
	 */
	public static class HttpResponse {
		/**html状态*/
		public StatusLine statusLine;
		/**html头部*/
		public Header[] headers;
		/**html正文*/
		public String content;
	}

	/**默认浏览器userAgent*/
	private String USER_AGENT_CHROME_WIN7 = 
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36";
	
	/**浏览器userAgent*/
	private String userAgent = USER_AGENT_CHROME_WIN7;
	/**http代理*/
	private HttpHost proxy;
	/**输出数据编码*/
	private String encode;
	/**忽略https证书验证，方便fiddler抓包测试*/
	private boolean ignoreHttpsCertificates = false;
	
	private BasicCookieStore cookieStore = new BasicCookieStore();
	
	/**设置http代理*/
	public Browser setProxyHttp(String ip, int port) {
		this.proxy = new HttpHost(ip, port, "http");
	    return this;
	}
	/**清楚所有代理*/
	public Browser clearProxy() {
		this.proxy = null;
		return this;
	}
	/**设置解析返回报文编码*/
	public Browser setEncode(String encode) {
		this.encode = encode;
		return this;
	}
	/**设置不要校验ssl证书*/
	public Browser setIgnoreHttpsCertificates(boolean ignoreHttpsCertificates) {
		this.ignoreHttpsCertificates = ignoreHttpsCertificates;
		return this;
	}
	/**拿到浏览器cookies，操作这个列表【不会】操作browser的cookie*/
	public List<Cookie> getCookies() {
		return cookieStore.getCookies();
	}
	/**添加cookie，如果name相同，会覆盖掉，不关心原来cookie的domain*/
	public void addCookie(String name, String value, String domain) {
		BasicClientCookie cookie = new BasicClientCookie(name, value);
		if(domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		cookieStore.addCookie(cookie);
	}

	/**
	 * GET方式请求
	 * @param url
	 * @param out 当out不为null时，
	 */
	public HttpResponse get(String url) throws IOException {
		return getToOutputStream(url, null);
	}
	
	/**
	 * 请求并输出到输出流
	 * @param out 当out为null时，将报文内容保存到HttpResponse的content中，使用的是默认编码或指定的编码
	 */
	public HttpResponse getToOutputStream(String url, OutputStream out) throws IOException {
		CloseableHttpResponse response = null;
		HttpResponse httpResponse = new HttpResponse();
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(getRequestConfig());
			httpGet.setHeader("User-Agent", userAgent);

			response = getHttpClient().execute(httpGet);
			processResp(response, httpResponse, out);
		} finally {
			// 建立的http连接，仍旧被response保持着，允许我们从网络socket中获取返回的数据
			// 为了释放资源，我们必须手动消耗掉response或者取消连接（使用CloseableHttpResponse类的close方法）
			if (response != null) {
				response.close();
			}
		}
		return httpResponse;
	}
	
	/**
	 * POST方式请求
	 */
	public HttpResponse post(String url, Map<String, String> params) throws IOException {
		return postToOutputStream(url, params, null);
	}
	
	/**
	 * POST方式请求
	 * @param out 当out为null时，将报文内容保存到HttpResponse的content中，使用的是默认编码或指定的编码
	 * @return
	 */
	public HttpResponse postToOutputStream(String url, Map<String, String> params,
			OutputStream out) throws IOException {		
		CloseableHttpResponse response = null;
		HttpResponse httpResponse = new HttpResponse();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(getRequestConfig());
			httpPost.setHeader("User-Agent", USER_AGENT_CHROME_WIN7);
			
			if(params != null && !params.isEmpty()) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(nvps));
				} catch (UnsupportedEncodingException e) {
					// TODO log
				}
			}
			
			response = getHttpClient().execute(httpPost);
			processResp(response, httpResponse, out);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		
		return httpResponse;
	}
	
	/**
	 * 配置超时时间和代理
	 */
	private RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).setProxy(proxy).build();
		return requestConfig;
	}
	
	/**
	 * 获得CloseableHttpClient
	 */
	private CloseableHttpClient getHttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().
				setDefaultCookieStore(cookieStore);
		if(ignoreHttpsCertificates) {
			try {
				SSLContextBuilder builder = new SSLContextBuilder();
			    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			            builder.build());
			    httpClientBuilder.setSSLSocketFactory(sslsf);
			} catch (Exception e) {
				// TODO log exception
			}
		}
		
		CloseableHttpClient httpClient = httpClientBuilder.build();
		return httpClient;
	}
	
	/**
	 * 将httpEntity的值查询后输出，注意，httpEntity本身是维系一个输出流
	 * 在这里把所有数据都读出来，所以这里对大数据量会停留很久,XXX 目前还未考虑这种情况的处理，可以从httpEntity拿到输入流
	 * 数据读完之后就关闭httpEntity
	 */
	private void processResp(CloseableHttpResponse response, HttpResponse httpResponse,
			OutputStream out) throws IOException {
		if(response == null) {
			throw new IOException("response is null");
		}
		
		httpResponse.statusLine = response.getStatusLine();
		httpResponse.headers = response.getAllHeaders();
		
		HttpEntity httpEntity = response.getEntity();
		if(httpEntity == null) {
			throw new IOException("response HttpEntity is null");
		}
		
		if (out != null) {
			httpEntity.writeTo(out);
		} else {
			httpResponse.content = EntityUtils.toString(httpEntity, encode);
		}
		
		// 当entity已经处理完了，关闭掉
		EntityUtils.consume(httpEntity);
	}
	
	public static void main(String[] args) throws IOException {
		Browser browser = new Browser().setProxyHttp("127.0.0.1", 8888);
		
		browser.setIgnoreHttpsCertificates(true);
		
		String url = "http://www.baidu.com/";
		// 发现一个很强大的功能，如果网站302跳转，照样可以处理,但这个只对get有效，对post无效
		HttpResponse content = browser.get(url);

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
		
	    content = browser.get(url); // 第二次请求会带上cookie

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
		
		Map<String, String> postData = new HashMap<String, String>();
		postData.put("one", "1");
	    content = browser.post(url, postData); // 第二次请求会带上cookie

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
		
		// 下载文件
		String img_url = "http://www.baidu.com/img/bdlogo.png";
		FileOutputStream out = new FileOutputStream("C:/baidu_logo.png");
		browser.getToOutputStream(img_url, out);
		
		// 添加cookie
		// 注意：要让www.baidu.com带上cookie，domain得写www.baidu.com，写baidu.com没用，这应该是httpclient的bug
		browser.addCookie("name", "nick", "www.baidu.com");
		// browser.addCookie("name", "nick", "baidu.com"); // 写多一个也无妨
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    content = browser.get(url); // 会带上cookie
		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
		
		for(Cookie cookie : browser.getCookies()) {
			System.out.println(cookie);
		}
	}
	
}
