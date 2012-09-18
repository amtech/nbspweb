package com.cabletech.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 获取Spring配置内容
 * @author wangt
 *
 */
public class SpringContext {
	private static ApplicationContext ctx = null;

	/**
	 * 获取Spring配置内容
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		}
		return ctx;
	}

}
