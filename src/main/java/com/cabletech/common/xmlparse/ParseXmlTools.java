package com.cabletech.common.xmlparse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML文件解析类
 * 
 * @author 杨隽 2012-02-13 创建
 * @author 杨隽 2012-02-16 添加“XML配置文件的<column>标签中data-method属性常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<constant>标签常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<constant>标签中<prop>标签常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<style>标签常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<style>标签中<font>标签常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<font>标签中font-color属性常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<root>标签和<column>标签中style-id属性常量”
 * @author 杨隽 2012-02-16 删除“XML配置文件的<column>标签中col-color属性常量”
 * @author 杨隽 2012-02-16 添加“XML配置文件的<column>标签中subject-style-id属性常量”
 * @author 杨隽 2012-07-31 添加getChildElementById()方法
 * @author 杨隽 2012-08-16 添加“XML配置文件的<cmd>标签常量”
 * @author 杨隽 2012-08-16 添加“XML配置文件的<cmd>标签中<prop>标签常量”
 * 
 */
public class ParseXmlTools {
	private Logger logger = Logger.getLogger("ParseXmlTools");
	// XML配置文件的<cmd>标签常量
	public static final String CMD_ELEMENT_KEY = "cmd";
	// XML配置文件的<cmd>标签中service属性常量
	public static final String SERVICE_ATTRIBUTE_KEY = "service";
	// XML配置文件的<constant>标签常量
	public static final String CONSTANT_ELEMENT_KEY = "constant";
	// XML配置文件的<constant>标签和<cmd>标签中<prop>标签常量
	public static final String PROP_ELEMENT_KEY = "prop";
	// XML配置文件的<style>标签常量
	public static final String STYLE_ELEMENT_KEY = "style";
	// XML配置文件的<style>标签中<font>标签常量
	public static final String FONT_ELEMENT_KEY = "font";
	// XML配置文件的<font>标签中font-color属性常量
	public static final String FONT_COLOR_ATTRIBUTE_KEY = "font-color";
	// XML配置文件的<column>标签常量
	public static final String COLUMN_ELEMENT_KEY = "column";
	// XML配置文件的<column>标签中subject属性常量
	public static final String SUBJECT_ATTRIBUTE_KEY = "subject";
	// XML配置文件的<column>标签中property-name属性常量
	public static final String PROPERTY_NAME_ATTRIBUTE_KEY = "property-name";
	// XML配置文件的<column>标签中col-index属性常量
	public static final String COL_INDEX_ATTRIBUTE_KEY = "col-index";
	// XML配置文件的<column>标签中data-method属性常量
	public static final String DATA_METHOD_NAME_ATTRIBUTE_KEY = "data-method";
	// XML配置文件的<column>标签中subject-style-id属性常量
	public static final String SUBJECT_STYLE_ID_ATTRIBUTE_KEY = "subject-style-id";
	// XML配置文件的<root>标签和<column>标签中style-id属性常量
	public static final String STYLE_ID_ATTRIBUTE_KEY = "style-id";
	// XML配置文件的<root>标签中entity属性常量
	public static final String ENTITY_ATTRIBUTE_KEY = "entity";
	// XML配置文件的<root>标签中excel-title属性常量
	public static final String EXCEL_TITLE_ATTRIBUTE_KEY = "excel-title";
	// XML配置文件的<prop>标签<rule>标签常量
	public static final String RULE_ATTRIBUTE_KEY = "rule";
	// XML文件的id属性名
	public static final String ID_ATTRBUTE_KEY = "id";
	// XML文件的后缀名
	private static final String XML_FILE_SUFFIX = ".xml";
	// 路径分隔符
	private static final String SEPERATOR = "/";
	// 配置文件路径
	private String configFilePath = "";
	// 配置文件名称列表
	private List<String> configFileNameList = new ArrayList<String>();
	// 配置文件路径列表
	private List<String> configFilePathList = new ArrayList<String>();

	public List<String> getConfigFileNameList() {
		return configFileNameList;
	}

	public void setConfigFileNameList(List<String> configFileNameList) {
		this.configFileNameList = configFileNameList;
	}

	public String getConfigFilePath() {
		return configFilePath;
	}

	/**
	 * 根据配置文件路径提取XML文件
	 * 
	 * @param configFilePath configFilePath
	 */
	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
		if (StringUtils.isBlank(configFilePath)) {
			return;
		}
		try {
			fetchConfigFileNameList(configFilePath);
		} catch (Exception ex) {
			logger.error("提取配置文件所在目录下的所有XML文件出错:", ex);
		}
	}

	public List<String> getConfigFilePathList() {
		return configFilePathList;
	}

	/**
	 * 根据配置文件路径列表提取XML文件
	 * 
	 * @param configFilePathList configFilePathList
	 */
	public void setConfigFilePathList(List<String> configFilePathList) {
		this.configFilePathList = configFilePathList;
		if (CollectionUtils.isEmpty(configFilePathList)) {
			return;
		}
		String configFilePath;
		for (int i = 0; i < configFilePathList.size(); i++) {
			configFilePath = configFilePathList.get(i);
			try {
				fetchConfigFileNameList(configFilePath);
			} catch (Exception ex) {
				logger.error("提取配置文件所在目录下的所有XML文件出错:", ex);
			}
		}
	}

	/**
	 * 获取XML元素模板的Map
	 * 
	 * @return Map<String, Element> XML元素模板的Map
	 * @throws DocumentException
	 * @throws URISyntaxException
	 */
	public Map<String, Element> getXmlElementsMap() throws DocumentException,
			URISyntaxException {
		Map<String, Element> map = new HashMap<String, Element>();
		if (CollectionUtils.isEmpty(configFileNameList)) {
			return map;
		}
		URL url;
		Document doc;
		Element root;
		for (int i = 0; i < configFileNameList.size(); i++) {
			String fileName = configFileNameList.get(i);
			if (StringUtils.isBlank(fileName)) {
				continue;
			}
			url = getClass().getResource(fileName);
			doc = read(url);
			root = getRootNode(doc);
			map.put(root.attributeValue(ID_ATTRBUTE_KEY), root);
		}
		return map;
	}

	/**
	 * 获取配置XML文件数据
	 * 
	 * @param key
	 *            String 配置XML文件的id
	 * @return Element 导入模板的配置XML文件数据的根节点
	 */
	public Element getImportXmlElement(String key) {
		Map<String, Element> map = new HashMap<String, Element>();
		try {
			map = getXmlElementsMap();
		} catch (Exception ex) {
			logger.error("读取配置XML文件出错:", ex);
		}
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		Element root = map.get(key);
		return root;
	}
	
	/**
	 * 根据id的值获取该节点下的子节点元素
	 * @param root Element
	 * @param childElementLabel String
	 * @param value String
	 * @return Element
	 */
	public Element getChildElementById(Element root, String childElementLabel,
			String value) {
		List<Element> elements = root.elements(childElementLabel);
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		for (int i = 0; i < elements.size(); i++) {
			String id = elements.get(i).attributeValue(
					ParseXmlTools.ID_ATTRBUTE_KEY);
			if (id.equals(value)) {
				return elements.get(i);
			}
		}
		return null;
	}

	/**
	 * 提取配置文件所在目录下的所有XML文件
	 * 
	 * @param configFilePath
	 *            String 配置文件所在目录
	 * @throws URISyntaxException
	 */
	private void fetchConfigFileNameList(String configFilePath)
			throws URISyntaxException {
		// TODO Auto-generated method stub
		URL url = getClass().getResource(configFilePath);
		File file = new File(url.toURI());
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		File[] files = file.listFiles();
		for (int i = 0; files != null && i < files.length; i++) {
			if (files[i].getName().endsWith(XML_FILE_SUFFIX)) {
				configFileNameList.add(configFilePath + SEPERATOR
						+ files[i].getName());
			}
		}
	}

	/**
	 * 初始化XML文件解析设置（忽略DTD文件）
	 * 
	 * @param reader
	 *            SAXReader XML阅读器
	 */
	private void initialize(SAXReader reader) {
		reader.setValidation(false);
		reader.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				// TODO Auto-generated method stub
				return new InputSource(new ByteArrayInputStream(
						"<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
			}
		});
	}

	/**
	 * 读取信息结构配置XML文件
	 * 
	 * @param url
	 *            URL XML文件的URL路径
	 * @return Document 配置XML文件
	 * @throws DocumentException
	 * @throws URISyntaxException
	 */
	private Document read(URL url) throws DocumentException, URISyntaxException {
		SAXReader reader = new SAXReader();
		initialize(reader);
		Document document = reader.read(new File(url.toURI()));
		return document;
	}

	/**
	 * 获取读取信息结构XML的根节点
	 * 
	 * @param doc
	 *            Document 读取信息结构XML
	 * @return Element 读取信息结构XML的根节点
	 */
	private Element getRootNode(Document doc) {
		return doc.getRootElement();
	}
}
