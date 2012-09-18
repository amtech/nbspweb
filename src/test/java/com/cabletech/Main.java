package com.cabletech;

import java.lang.reflect.Method;
import java.util.Map;

//import com.cabletech.wplanpatrol.model.PatrolItemInput;
//import com.cabletech.wplanpatrol.xmlparse.ParseXmlTools;

/**
 * 测试类
 * @author wangt
 *
 */
public class Main {
	/**
	 * 主方法
	 * @param args 
	 */
	public static void main(String[] args) {
		StringBuffer sb1 = new StringBuffer("Hello World");
		sb1.delete(0, 6);
//		logger.info(sb1);
		sb1.delete(0, sb1.length());
//		logger.info(sb1);
		sb1 = new StringBuffer("Hello World ");
		sb1.deleteCharAt(sb1.length() - 1);
//		logger.info(sb1);
		try {
			Class<?> clazz = Class
			.forName("com.cabletech.wplanpatrol.service.generate.GetInputElementUtils");
			String methodName = "getNumInputElement";
//			Method method = clazz.getMethod(methodName, PatrolItemInput.class);
		} catch (Exception ex) {
//			logger.error(ex);
		}
		try {
//			ParseXmlTools xmlTools = new ParseXmlTools();
//			xmlTools.setConfigFilePath("/xml");
//			Map<String, Object> map = xmlTools.getInputElementMap();
		} catch (Exception ex) {
//			logger.error(ex);
		}
	}
}