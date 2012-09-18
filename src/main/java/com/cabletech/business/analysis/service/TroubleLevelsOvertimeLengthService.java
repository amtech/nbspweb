package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * 故障超时时长统计接口
 * 
 * @author 杨隽 2012-03-23 创建
 * 
 */
public interface TroubleLevelsOvertimeLengthService {
	/**
	 * 故障超时时长统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsMainOvertimeLengthList(
			Map<String, String> parameters);

	/**
	 * 故障超时时长统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsSubOvertimeLengthList(
			Map<String, String> parameters);
}
