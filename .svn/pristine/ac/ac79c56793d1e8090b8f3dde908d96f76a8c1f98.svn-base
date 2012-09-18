package com.cabletech.business.wplan.patrolitem.exports;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cabletech.common.excel.exports.ExcelExport;
import com.cabletech.common.util.ReflectionUtils;

/**
 * 巡检项Excel导出功能类
 * 
 * @author 杨隽 2012-02-15 创建
 * @author 杨隽 2012-02-16 添加processDataValue抽象方法实现
 * 
 */
@Service
public class PatrolItemExcelExport extends ExcelExport {
	// 巡检项导出模板的Xml配置文件的根节点id
	private static final String PATROL_ITEM_EXPORT_ID = "patrolItemExport";

	/**
	 * 获取导出Excel配置XML文件的id
	 * 
	 * @return String 导出Excel配置XML文件的id
	 */
	@Override
	public String getExportXmlId() {
		return PATROL_ITEM_EXPORT_ID;
	}

	/**
	 * 进行获取后的数据后期处理（使用XML文件中配置的数据转换方法将数据库中存放的属性KEY值转换成输出Excel文件中的VALUE值）
	 * 
	 * @param value
	 *            String 获取后的数据
	 * @param methodName
	 *            String 属性配置的XML元素
	 * @return String 后期处理后的数据
	 */
	@Override
	public String processDataValue(String value, String methodName) {
		// TODO Auto-generated method stub
		String lastValue = value;
		if (StringUtils.isNotBlank(methodName)) {
			lastValue = (String) ReflectionUtils.invokeMethod(this, methodName,
					new Class[] { String.class }, new Object[] { value });
		}
		return lastValue;
	}

	/**
	 * 根据输入的KEY值去查找数据值表获取VALUE值
	 * 
	 * @param value
	 *            String 输入的KEY值
	 * @return String VALUE值
	 */
	public String getConstantValue(String value) {
		String valueText = "";
		Map<String, String> map = super.initTableMap();
		if (map.containsKey(value)) {
			valueText = map.get(value);
		}
		return valueText;
	}
}
