package com.cabletech.business.assess.service;

import java.util.Map;

/**
 * 年考核汇总业务接口
 * 
 * @author 杨隽 2012-08-09 创建
 * 
 */
public interface AssessYearSummaryService {
	/**
	 * 获取年考核汇总数据信息
	 * 
	 * @param yearMonth
	 *            String
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getSummaryScoreDataMap(String yearMonth);

	/**
	 * 获取年考核汇总导出数据Map
	 * 
	 * @param yearMonth
	 *            String
	 * @param type
	 *            String
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getSummaryScoreExcelMap(String yearMonth,
			String type);
}
