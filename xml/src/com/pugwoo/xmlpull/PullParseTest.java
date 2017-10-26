package com.pugwoo.xmlpull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class PullParseTest {

	public static void main(String[] args) throws Exception {
		
		XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = parserFactory.newPullParser();
		
		parser.setInput(PullParseTest.class.getResourceAsStream("/myxml.xml"), "utf-8");
		
		// 获取当前事件类型
		int eventType = parser.getEventType();;
		while(eventType != XmlPullParser.END_DOCUMENT) {
			
			switch (eventType) {
			case XmlPullParser.START_TAG: // 开始标签
				if("title".equals(parser.getName())) { // 解析到<book>标签
					System.out.println(parser.nextText()); // nextText很常用，获得这个节点下一个位置的文本内容
					// 【注意】parser.nextText()不能获取一整段标签<>
				}
				break;
			case XmlPullParser.END_TAG: // 结束标签
				
				break;
			default:
				break;
			}
			
			eventType = parser.next();
		}
	}
	
	
}
