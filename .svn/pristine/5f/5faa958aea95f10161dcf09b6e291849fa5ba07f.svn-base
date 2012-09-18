package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 故障处理及时率统计接口
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
public interface TroubleProcessInTimeRateService {
	/**
	 * 故障处理及时率主统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleProcessInTimeMainRateList(
			Map<String, String> parameters);

	/**
	 * 故障处理及时率从统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleProcessInTimeSubRateList(
			Map<String, String> parameters);

	/**
	 * 处理及时故障列表
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
	 * 处理超时故障列表
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
