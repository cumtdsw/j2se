package com.pugwoo.JAXP;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * java自带的
 */
public class DomParse {

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(DomParse.class.getResourceAsStream("/myxml.xml"));
		
		// 通过document元素找到集合
		NodeList list = document.getElementsByTagName("book");
	    for(int i = 0; i < list.getLength(); i++) {
	    	System.out.println(list.item(i).getTextContent());
	    }
	}
}
