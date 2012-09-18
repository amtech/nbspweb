package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 计划资源覆盖率 --service
 * 
 * @author wangjie 2012-03-19
 * 
 */
public interface PlanResCoverageRateService {
	/**
	 * 计划资源覆盖率主数据
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	List<Map<String, Object>> getMainList(Map<String, String> parameters);

	/**
	 * 计划资源覆盖率从数据
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	List<Map<String, Object>> getSubList(Map<String, String> parameters);

	/**
	 * 计划资源覆盖率 --计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getPlannedResDetailList(Page page,
			Map<String, String> parameters);

	/**
	 * 计划资源覆盖率 --未计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getUnPlannedResDetailList(Page page,
			Map<String, String> parameters);
}
