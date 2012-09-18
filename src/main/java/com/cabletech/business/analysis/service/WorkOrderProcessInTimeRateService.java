package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 工单处理及时率统计接口
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
public interface WorkOrderProcessInTimeRateService {
	/**
	 * 工单处理及时率统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getWorkOrderProcessInTimeMainRateList(
			Map<String, String> parameters);

	/**
	 * 处理及时工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getInTimeWorkOrderList(Map<String, String> parameters, Page page);

	/**
	 * 处理超时工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getOverTimeWorkOrderList(Map<String, String> parameters, Page page);
}
