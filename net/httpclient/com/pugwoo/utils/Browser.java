package com.pugwoo.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 2015年9月24日 17:46:47
 * 基于http client的浏览器工具
 * 
 * 历史log:
 * 2012-12-3 下午03:23:35 http get数据
 * 2013年11月30日 22:41:21 由于网络问题，增加重试机制，增加成功率
 * 2014年10月21日 13:48:21 去掉重试机制，交给上一层去完成
 * 2014年10月21日 14:52:51 使用新版4.3.5的httpclient，并修改GET和POST方法
 * 2014年10月22日 09:25:38 增加发送自定义cookie，但不会管理cookie上下文
 * 
 * @author pugwoo
 */
public class Browser {
	
	/**
	 * http返回包
	 */
	public static class HttpResponse {
		/**html状态*/
		public StatusLine statusLine;
		/**html头部*/
		public Header[] headers;
		/**html正文*/
		public String content;
		/**html正文-二进制，当指定需要二进制才有，此时content无值*/
		public byte[] contentBytes;
	}

	/**默认浏览器userAgent*/
	private String USER_AGENT_CHROME_WIN7 = 
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36";
	
	/**http代理*/
	private HttpHost proxy;
	/**输出数据编码*/
	private String encode;
	/**保留二进制*/
	private boolean remainBytes;
	/**重定向输出*/
	private OutputStream outputStream;
	
	private BasicCookieStore cookieStore = new BasicCookieStore();
	private CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
	
	public Browser setProxyHttp(String ip, int port) {
		this.proxy = new HttpHost(ip, port, "http");
	    return this;
	}
	public Browser setEncode(String encode) {
		this.encode = encode;
		return this;
	}
	public Browser setRemainBytes(boolean remainBytes) {
		this.remainBytes = remainBytes;
		return this;
	}
	public Browser setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
		return this;
	}
	
	/**
	 * GET方式请求
	 * @param url
	 * @throws IOException 
	 */
	public HttpResponse get(String url) throws IOException {
		// 配置超时时间和代理
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).setProxy(proxy).build();
		
		CloseableHttpResponse response = null;
		HttpResponse httpResponse = new HttpResponse();

		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("User-Agent", USER_AGENT_CHROME_WIN7);

			response = httpClient.execute(httpGet);

			httpResponse.statusLine = response.getStatusLine();
			httpResponse.headers = response.getAllHeaders();

			HttpEntity entity = response.getEntity();
			if(entity == null) {
				throw new IOException("HttpEntity is null");
			}
			
			if (outputStream != null) {
				outputStream.write(EntityUtils.toByteArray(entity));
			} else {
				if(remainBytes) {
					httpResponse.contentBytes = EntityUtils.toByteArray(entity);
				} else {
					if (encode == null) {
						httpResponse.content = EntityUtils.toString(entity);
					} else {
						httpResponse.content = EntityUtils.toString(entity, encode);
					}
				}
			}

			// 当entity已经处理完了，关闭掉
			EntityUtils.consume(entity);
		} finally {
			// 建立的http连接，仍旧被response保持着，允许我们从网络socket中获取返回的数据
			// 为了释放资源，我们必须手动消耗掉response或者取消连接（使用CloseableHttpResponse类的close方法）
			if (response != null) {
				response.close();
			}
		}

		return httpResponse;
	}
	
	public static void main(String[] args) throws IOException {
		Browser browser = new Browser().setProxyHttp("127.0.0.1", 8888);
		
		String url = "http://www.baidu.com/";
		HttpResponse content = browser.get(url);

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
		
	    content = browser.get(url); // 第二次请求会带上cookie

		System.out.println(content.content);
		System.out.println(content.content.getBytes().length + "字节");
	}
	
}
