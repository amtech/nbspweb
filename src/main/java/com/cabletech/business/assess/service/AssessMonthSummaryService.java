package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

/**
 * 获取月考核汇总服务
 * @author zhaobi
 * @date 2012-8-9 
 */
public interface AssessMonthSummaryService {
	/**
	 * 获取月考核汇总
	 * @param map 条件
	 * @return
	 */
	public Map<String, Object> getMonthSummary(Map<String, Object> map);
	
	/**
	 * 获取月排名汇总
	 * @param map 条件
	 * @return
	 */
	public Map<String, Object> getMonthRank(Map<String, Object> map); 
}
