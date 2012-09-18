package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 巡检完成率
 * 
 * @author wangjie 2012-03-19
 * 
 */
public interface PollingAccomplishRateService {
	/**
	 * 计划巡检完成率主数据
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	List<Map<String, Object>> getMainList(Map<String, String> parameters);

	/**
	 * 计划巡检完成率从数据
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	List<Map<String, Object>> getSubList(Map<String, String> parameters);

	/**
	 * 巡检完成率 --未覆盖资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getUnPlannedResDetailList(Page page,
			Map<String, String> parameters);

	/**
	 * 巡检完成率 --已巡资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getPollingAccomplishResDetailList(Page page,
			Map<String, String> parameters);
}
