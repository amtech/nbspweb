package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * 故障平均历时统计接口
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
public interface TroubleLevelsTimeLengthService {
	/**
	 * 故障平均历时统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsMainTimeLengthList(
			Map<String, String> parameters);

	/**
	 * 故障平均历时统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getTroubleLevelsSubTimeLengthList(
			Map<String, String> parameters);
}
