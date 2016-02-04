package httpclient.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 2014年6月17日 09:43:46
 * httpClient非常強大，如果電腦上有多個網卡，有些連接外網有些連接內網
 * 如果想指定某個http請求通過某個網卡出去的話，一般得改系統route表，生效慢也很麻煩（全局的）。
 * 
 * httpclient可以指定這個請求走哪個網卡，太方便了！
 * 
 */
public class TestSpecificOutIp {
	
	/**
	 * 從url獲取數據
	 * 1.如果encode不為null，則使用編碼，否則不使用編碼
	 * 2.如果代理proxy不為null，則使用proxy
	 * 3.如果out不為null，則寫入到文件中，此時返回null；如果out為null，則返回string
	 * 
	 * @param specificIp 指定出口網卡ip
	 */
	public static String get(String url, String encode, 
			HttpHost proxy, String specificIp,
			FileOutputStream out) throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		if(specificIp != null) {
			String strs[] = specificIp.split("\\.");
			if(strs.length == 4) {
				byte ip[] = new byte[] {
					(byte) Integer.parseInt(strs[0]),
					(byte) Integer.parseInt(strs[1]),
					(byte) Integer.parseInt(strs[2]),
					(byte) Integer.parseInt(strs[3])
				};
				httpclient.getParams().setParameter(ConnRouteParams.LOCAL_ADDRESS,
					    InetAddress.getByAddress(ip));
			}
		}
		
		String content = null;

		try {
			if (proxy != null) {
				httpclient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
			}

			HttpGet req = new HttpGet(url);
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

	public static void main(String[] args) throws ClientProtocolException, IOException {

		// 可以上外網的網卡
		String specificIp = "10.66.124.54";

		String url = "http://www.baidu.com/";
		String content = get(url, null, null, specificIp, null);
		System.out.println(content);
		System.out.println(content.getBytes().length + "字節");
		
	}

}
