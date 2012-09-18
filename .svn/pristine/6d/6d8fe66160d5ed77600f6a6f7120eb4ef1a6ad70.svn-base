package com.cabletech.business.wplan.patrolitem.exports;

import org.springframework.stereotype.Service;

import com.cabletech.common.excel.exports.ExcelExport;

/**
 * 不合法巡检项Excel导出功能类
 * 
 * @author 杨隽 2012-02-15 创建
 * @author 杨隽 2012-02-16 添加processDataValue抽象方法实现
 * 
 */
@Service
public class InvalidPatrolItemExcelExport extends ExcelExport {
	// 巡检项导出模板的Xml配置文件的根节点id
	private static final String INVALID_PATROL_ITEM_EXPORT_ID = "invalidPatrolItemExport";

	/**
	 * 获取导出Excel配置XML文件的id
	 * 
	 * @return String 导出Excel配置XML文件的id
	 */
	@Override
	public String getExportXmlId() {
		// TODO Auto-generated method stub
		return INVALID_PATROL_ITEM_EXPORT_ID;
	}

	/**
	 * 进行获取后的数据后期处理（使用XML文件中配置的数据转换方法将数据库中存放的属性KEY值转换成输出Excel文件中的VALUE值）
	 * 
	 * @param value
	 *            String 获取后的数据
	 * @param methodName
	 *            String 获取数据处理的调用方法名称
	 * @return String 后期处理后的数据
	 */
	@Override
	public String processDataValue(String value, String methodName) {
		// TODO Auto-generated method stub
		return value;
	}
}
