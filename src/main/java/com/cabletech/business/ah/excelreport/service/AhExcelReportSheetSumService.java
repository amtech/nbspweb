package com.cabletech.business.ah.excelreport.service;

import java.util.Map;

import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;

/**
 * 
 * 汇总地市每个月上传的excel报表中sheet工作表service
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
public interface AhExcelReportSheetSumService {
	/**
	 * 汇总并删除地市每个月上传的excel报表中sheet工作表的HTML表格字串
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件参数
	 * @return Map<String, Object> 地市每个月上传的excel报表中sheet工作表的数据map
	 */
	public Map<String, Object> sumAhExcelReportSheet(AhExcelReportRecode report);
}
