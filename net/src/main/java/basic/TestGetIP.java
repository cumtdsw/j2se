package basic;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * 测试获得本机ip地址，一般本机都有多个ip，有多个网卡的时候，服务器也是一样，所以需要把所有的ip都获取到
 * 
 * 获取ip地址的速度很慢，实际项目中要注意
 * 
 * @author NICK
 */
public class TestGetIP {
	
	/**
	 * 获得本机的ipv4的所有ip列表，返回的是网卡别称 -> ip 的map <br>
	 * 排除本机ip 127.开头的
	 * @return
	 */
	public static Map<String, String> getIpv4IPs() throws Exception {
		Map<String, String> ips = new HashMap<String, String>();
		String regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
		Pattern pattern = Pattern.compile(regex);
		for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
				ifaces.hasMoreElements();) {
			NetworkInterface iface = ifaces.nextElement();
			for (Enumeration<InetAddress> addresses = iface.getInetAddresses(); addresses.hasMoreElements();) {
				InetAddress address = addresses.nextElement();
				if(pattern.matcher(address.getHostAddress()).find() && !address.getHostAddress().startsWith("127.")) {
					ips.put(iface.getName(), address.getHostAddress());
				}
			}
		}
		return ips;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> ips = getIpv4IPs();
		for(Entry<String, String> entry : ips.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
