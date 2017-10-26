package com.pugwoo.JAXP;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	    
	    // 可以对document进行修改
	    
	    // document保存为xml文件
	    Transformer transformer = TransformerFactory.newInstance().newTransformer();
	    transformer.transform(new DOMSource(document), new StreamResult(new File("d:/gen.xml")));
	}
}
