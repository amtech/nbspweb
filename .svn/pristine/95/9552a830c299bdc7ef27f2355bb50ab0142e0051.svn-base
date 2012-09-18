package com.cabletech.common.excel.exports;

import com.cabletech.common.util.ReflectionUtils;

/**
 * 实体属性值生成器
 * 
 * @author 杨隽 2012-02-15 创建
 * 
 */
public class EntityValueGenerator {
	/**
	 * 获取数据对象中某个数据属性值
	 * 
	 * @param object
	 *            Object 传入数据对象
	 * @param propertyName
	 *            String 数据对应的属性
	 * @return Object 数据属性值
	 */
	public static Object getValue(Object object, String propertyName) {
		Object value = ReflectionUtils.invokeGetterMethod(object, propertyName);
		if (value == null) {
			return "";
		}
		return value;
	}
}
