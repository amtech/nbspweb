package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 故障分级统计接口
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
public interface TroubleLevelsNumberService {
	/**
	 * 故障分级统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsMainNumberList(
			Map<String, String> parameters);

	/**
	 * 故障分级统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsSubNumberList(
			Map<String, String> parameters);

	/**
	 * 按级别查询故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getTroubleListByLevel(Map<String, String> parameters, Page page);
}
