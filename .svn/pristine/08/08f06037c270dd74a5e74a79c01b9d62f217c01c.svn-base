package com.cabletech.business.webservice.interfaces.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cabletech.business.webservice.interfaces.ExternalWebService;
import com.cabletech.business.webservice.service.BusinessService;
import com.cabletech.business.webservice.utils.XmlReaderUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 外部WEBSERVICE接口实现
 * 
 * @author 杨隽 2012-08-16 创建
 * 
 */
@Component
public class ExternalWebServiceImpl implements ExternalWebService {
	public static final String CMD_KEY = "cmd";
	/**
	 * 日志输出
	 */
	private Logger logger = Logger.getLogger("WebServiceImpl");
	/**
	 * 解析工具
	 */
	@Resource(name = "parseXmlTools")
	private ParseXmlTools parseXmlTools;
	/**
	 * 业务处理方法
	 */
	@Autowired
	private Map<String, BusinessService> businessServiceMap;

	@SuppressWarnings("unchecked")
	@Override
	public String invokeService(String xmlText) {
		logger.info("input xml::" + xmlText);
		Document document = null;
		try {
			document = XmlReaderUtils.read(xmlText);
		} catch (Exception ex) {
			logger.error("解析XML字串出错", ex);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", ExternalWebService.PARSE_ERROR_CODE);
			String datafomat = "yyyy-MM-dd HH:mm:ss";
			Gson gson = new GsonBuilder().setDateFormat(datafomat).create();
			return gson.toJson(map);
		}
		Element root = document.getRootElement();
		if (root == null) {
			return "";
		}
		List<Element> list = root.elements();
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			map.put(element.getName(), element.getTextTrim());
		}
		return doInvokeMethod(map);
	}

	/**
	 * 调用访问服务对应的方法
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String doInvokeMethod(Map<String, Object> map) {
		String cmd = (String) map.get(CMD_KEY);
		Element webserviceElement = parseXmlTools
				.getImportXmlElement("webserviceConfig");
		Element cmdElement = getElement(webserviceElement, cmd);
		if (cmdElement == null) {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("code", ExternalWebService.NO_CMD_ERROR_CODE);
			valueMap.put("cmd", cmd);
			String datafomat = "yyyy-MM-dd HH:mm:ss";
			Gson gson = new GsonBuilder().setDateFormat(datafomat).create();
			return gson.toJson(valueMap);
		}
		List<Element> propElementList = cmdElement
				.elements(ParseXmlTools.PROP_ELEMENT_KEY);
		BusinessService businessService = businessServiceMap.get(cmdElement
				.attributeValue(ParseXmlTools.SERVICE_ATTRIBUTE_KEY));
		if (businessService == null) {
			return "";
		}
		return businessService.doBusiness(cmd, map, propElementList);
	}

	/**
	 * 获取命令的元素
	 * 
	 * @param webserviceElement
	 *            Element
	 * @param cmd
	 *            String
	 * @return Element
	 */
	@SuppressWarnings("unchecked")
	private Element getElement(Element webserviceElement, String cmd) {
		List<Element> cmdElementList = webserviceElement
				.elements(ParseXmlTools.CMD_ELEMENT_KEY);
		if (CollectionUtils.isEmpty(cmdElementList)) {
			return null;
		}
		for (int i = 0; i < cmdElementList.size(); i++) {
			Element cmdElement = cmdElementList.get(i);
			if (cmd.equals(cmdElement
					.attributeValue(ParseXmlTools.ID_ATTRBUTE_KEY))) {
				return cmdElement;
			}
		}
		return null;
	}
}
