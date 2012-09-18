package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 故障响应 及时率统计接口
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
public interface TroubleResponseInTimeRateService {
	/**
	 * 故障响应及时率统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleResponseInTimeMainRateList(
			Map<String, String> parameters);

	/**
	 * 故障响应及时率统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleResponseInTimeSubRateList(
			Map<String, String> parameters);

	/**
	 * 响应及时故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getInTimeTroubleList(Map<String, String> parameters, Page page);

	/**
	 * 响应超时故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getOverTimeTroubleList(Map<String, String> parameters, Page page);
}
