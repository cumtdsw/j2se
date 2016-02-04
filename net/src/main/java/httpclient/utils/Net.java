package httpclient.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 2012-12-3 下午03:23:35 http get數據
 * 2013年11月30日 22:41:21 由于网络问题，增加重试机制，增加成功率
 * 2014年10月21日 13:48:21 去掉重试机制，交给上一层去完成
 * 2014年10月21日 14:52:51 使用新版4.3.5的httpclient，并修改GET和POST方法
 * 2014年10月22日 09:25:38 增加发送自定义cookie，但不会管理cookie上下文
 */
public class Net {

	// 2014年10月21日 Chrome抓取
	private static String USER_AGENT_CHROME = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36";

	public static class HttpRespContent {
		public StatusLine statusLine; // html状态
		public Header[] headers; // html头部
		public String content; // html正文
	}

	/**
	 * 同get方法
	 */
	public static HttpRespContent get(String url, String encode,
			HttpHost proxy, FileOutputStream out)
			throws ClientProtocolException, IOException {
		return get(url, encode, proxy, out, null);
	}

	/**
	 * 從url獲取數據<br>
	 * 1.如果encode不為null，則使用編碼，否則不使用編碼<br>
	 * 2.如果代理proxy不為null，則使用proxy<br>
	 * 3.如果out不為null，則http content寫入到文件中，仅返回头数据；<br>
	 * 如果out為null，則返回完整的HttpRespContent<br>
	 * 4.如果cookie不为null，则使用该cookie发送
	 */
	public static HttpRespContent get(String url, String encode,
			HttpHost proxy, FileOutputStream out, List<Cookie> cookies)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient;
		if(cookies == null) {
			httpclient = HttpClients.createDefault();
		} else {
			BasicCookieStore cookieStore = new BasicCookieStore();
			for(Cookie cookie : cookies) {
				cookieStore.addCookie(cookie);
			}
			httpclient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		}
		
		// 配置超时时间和代理
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000).setProxy(proxy).build();

		CloseableHttpResponse response = null;
		HttpRespContent httpRespContent = new HttpRespContent();

		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("User-Agent", USER_AGENT_CHROME);

			response = httpclient.execute(httpGet);

			httpRespContent.statusLine = response.getStatusLine();
			httpRespContent.headers = response.getAllHeaders();

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (out != null) {
					out.write(EntityUtils.toByteArray(entity));
				} else {
					if (encode == null) {
						httpRespContent.content = EntityUtils.toString(entity);
					} else {
						httpRespContent.content = EntityUtils.toString(entity,
								encode);
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

		return httpRespContent;
	}

	/**
	 * 同post
	 */
	public static HttpRespContent post(String url, Map<String, String> params,
			String encode, HttpHost proxy, FileOutputStream out)
			throws ClientProtocolException, IOException {
		return post(url, params, encode, proxy, out, null);
	}

	/**
	 * 支持post数据，其它说明同GET
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 * @param proxy
	 * @param out
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpRespContent post(String url, Map<String, String> params,
			String encode, HttpHost proxy, FileOutputStream out,
			List<Cookie> cookies)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient;
		if(cookies == null) {
			httpclient = HttpClients.createDefault();
		} else {
			BasicCookieStore cookieStore = new BasicCookieStore();
			for(Cookie cookie : cookies) {
				cookieStore.addCookie(cookie);
			}
			httpclient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		}
		
		// 配置超时时间和代理
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000).setProxy(proxy).build();

		// 拼接参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
		}

		CloseableHttpResponse response = null;
		HttpRespContent httpRespContent = new HttpRespContent();

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("User-Agent", USER_AGENT_CHROME);
			if (!nvps.isEmpty()) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}

			response = httpclient.execute(httpPost);

			httpRespContent.statusLine = response.getStatusLine();
			httpRespContent.headers = response.getAllHeaders();

			HttpEntity entity = httpPost.getEntity();
			if (entity != null) {
				if (out != null) {
					out.write(EntityUtils.toByteArray(entity));
				} else {
					if (encode == null) {
						httpRespContent.content = EntityUtils.toString(entity);
					} else {
						httpRespContent.content = EntityUtils.toString(entity,
								encode);
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

		return httpRespContent;
	}
}
