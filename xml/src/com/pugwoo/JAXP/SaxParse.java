package com.pugwoo.JAXP;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParse {
	
	private static class MyHandler extends DefaultHandler {

		@Override
		public void startDocument() throws SAXException {
			System.out.println("文档开始");
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("文档结束");
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			System.out.println("开始标签<" + qName +">");
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			System.out.println("结束标签</" + qName +">");
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			System.out.println("文本内容:" + new String(ch, start, length));
		}
	}

	public static void main(String[] args) throws Exception {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = parserFactory.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		
		xmlReader.setContentHandler(new MyHandler());
		
		xmlReader.parse(new InputSource(SaxParse.class.getResourceAsStream("/myxml.xml")));
		
	}
	
}
