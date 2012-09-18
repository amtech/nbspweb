package com.cabletech.common.excel.exports;

import java.util.Map;

/**
 * Map属性值生成器
 * 
 * @author 杨隽 2012-02-15 创建
 * 
 */
public class MapValueGenerator {
	/**
	 * 获取Map中某个数据属性值
	 * 
	 * @param object
	 *            Object 传入数据Map
	 * @param propertyName
	 *            String 属性值对应的Key
	 * @return Object 数据属性值
	 */
	@SuppressWarnings("rawtypes")
	public static Object getValue(Object object, String propertyName) {
		Map map = (Map) object;
		Object value = map.get(propertyName);
		if (value == null) {
			return "";
		}
		return value;
	}
}
