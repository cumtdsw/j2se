package com.pugwoo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 2012-12-3 下午03:23:35
 * http get數據
 * 
 * 2013年11月30日 22:41:21
 * 由于网络问题，增加重试机制，增加成功率
 */
public class Net {
	
	public static int retryTimes = 100;
	
	public static String get(String url, String encode, HttpHost proxy,
			FileOutputStream out) throws Exception {
		Exception ex = null;
		for(int tries = 0; tries < retryTimes; tries++) {
			try {
				String content = __get(url, encode, proxy, out);
				return content;
			} catch (Exception e) {
				Thread.sleep(3000 * tries);
				ex = e;
			}
		}
		if(ex != null) {
			throw ex;
		}
		return null;
	}

	/**
	 * 從url獲取數據
	 * 1.如果encode不為null，則使用編碼，否則不使用編碼
	 * 2.如果代理proxy不為null，則使用proxy
	 * 3.如果out不為null，則寫入到文件中，此時返回null；如果out為null，則返回string
	 */
	private static String __get(String url, String encode, HttpHost proxy,
			FileOutputStream out) throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout",10000); //毫秒
		String content = null;

		try {
			if (proxy != null) {
				httpclient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
			}

			HttpGet req = new HttpGet(url);
			
			req.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_4) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5");
			
			HttpResponse rsp = httpclient.execute(req);

			// 獲得html頁面源碼
			HttpEntity entity = rsp.getEntity();
			if (entity != null) {
				if (out != null) {
					out.write(EntityUtils.toByteArray(entity));
				} else {
					content = EntityUtils.toString(entity);
				}
			}

			// 獲得請求返回狀態
			// rsp.getStatusLine()
			// 獲得http頭部
			// rsp.getAllHeaders()

		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return content;
	}

	@SuppressWarnings("unchecked")
	public static String post(String url, Map<String, String> params,
			String encode, HttpHost proxy, FileOutputStream out)
			throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		String content = null;

		try {
			if (proxy != null) {
				httpclient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
			}

			HttpPost req = new HttpPost(url);
			req.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_4) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			if (params != null) {
				Iterator iter = params.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
							.next();
					nvps.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue()));
				}
			}
			
			req.setEntity(new UrlEncodedFormEntity(nvps));

			HttpResponse rsp = httpclient.execute(req);

			// 獲得html頁面源碼
			HttpEntity entity = rsp.getEntity();
			if (entity != null) {
				if (out != null) {
					out.write(EntityUtils.toByteArray(entity));
				} else {
					content = EntityUtils.toString(entity);
				}
			}

			// 獲得請求返回狀態
			// rsp.getStatusLine()
			// 獲得http頭部
			// rsp.getAllHeaders()

		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return content;
	}
}
