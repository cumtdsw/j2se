package jsch_client;

import java.util.regex.Pattern;

/**
 * http://www.unixwerk.eu/unix/ansicodes.html 
 * 2014-2-2 21:17:05 移除掉shell中颜色等特殊字符
 * 
 * 还不支持top中太多的特殊字符
 */
public class RemoveSpecialChar {

	private static String reg = "\\e\\[([0-9]{1,2};)*[0-9]{1,2}m";
	private static Pattern p = Pattern.compile(reg);

	public static String process(String src) {
		java.util.regex.Matcher matcher = p.matcher(src);

		StringBuilder sb = new StringBuilder();
		int index = 0;
		while (matcher.find()) {
			sb.append(src.substring(index, matcher.start()));
			index = matcher.end();
		}
		sb.append(src.substring(index, src.length()));

		return sb.toString();
	}
}
