package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * 综合巡检报表
 * 
 * @author 汪杰 2012-03-27
 * 
 */
public interface PollingColligateReportService {
	/**
	 * 综合巡检报表 
	 * @param parameters 参数
	 * @return list
	 */
	List<Map<String, Object>> getPollingColligateReportList(Map<String, String> parameters);
}
