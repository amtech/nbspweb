package com.cabletech.business.webservice.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML解析工具类
 * 
 * @author 杨隽 2012-06-07 创建
 * 
 */
public class XmlReaderUtils {
	/**
	 * 读取信息结构配置XML文件
	 * 
	 * @param url
	 *            URL XML文件的URL路径
	 * @return Document 配置XML文件
	 * @throws DocumentException
	 * @throws URISyntaxException
	 */
	public static Document read(URL url) throws DocumentException,
			URISyntaxException {
		SAXReader reader = new SAXReader();
		initialize(reader);
		Document document = reader.read(new File(url.toURI()));
		return document;
	}

	/**
	 * 读取信息结构配置XML文件内容
	 * 
	 * @param xmlText
	 *            String XML文件内容
	 * @return Document 配置XML文件
	 * @throws DocumentException
	 */
	public static Document read(String xmlText) throws DocumentException {
		SAXReader reader = new SAXReader();
		initialize(reader);
		Document document = reader.read(new StringReader(xmlText));
		return document;
	}

	/**
	 * 初始化XML文件解析设置（忽略DTD文件）
	 * 
	 * @param reader
	 *            SAXReader XML阅读器
	 */
	private static void initialize(SAXReader reader) {
		reader.setValidation(false);
		reader.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new ByteArrayInputStream(
						"<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
			}
		});
	}
}
