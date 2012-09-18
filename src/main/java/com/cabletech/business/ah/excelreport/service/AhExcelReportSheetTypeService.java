package com.cabletech.business.ah.excelreport.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;

/**
 * 
 * 地市每个月上传的excel报表中sheet类型service
 * 
 * @author 杨隽 2012-06-27 创建
 * @author 杨隽 2012-07-09 添加getSheetExportName()方法
 * 
 */
public interface AhExcelReportSheetTypeService {
	/**
	 * 获取所有地市每个月上传的excel报表中sheet类型列表
	 * 
	 * @return List<Map<String, Object>> 所有地市每个月上传的excel报表中sheet类型列表
	 */
	public List<Map<String, Object>> queryAllList();

	/**
	 * 获取所有地市每个月上传的excel报表中需要汇总的sheet类型列表
	 * 
	 * @return List<Map<String, Object>> 所有地市每个月上传的excel报表中sheet类型列表
	 */
	public List<Map<String, Object>> querySumSheetTypeList();

	/**
	 * 获取地市每个月上传的excel报表中sheet类型信息
	 * 
	 * @param id
	 *            String 地市每个月上传的excel报表中sheet类型编号
	 * @return AhExcelReportSheet 地市每个月上传的excel报表中sheet类型
	 */
	public AhExcelReportSheet viewAhExcelReportSheet(String id);

	/**
	 * 根据报表查询数据生成报表导出的名称
	 * 
	 * @param report
	 *            AhExcelReportRecode 报表查询数据
	 * @return String 报表导出的名称
	 */
	public String getSheetExportName(AhExcelReportRecode report);
}
