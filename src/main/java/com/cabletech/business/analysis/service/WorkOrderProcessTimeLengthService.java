package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * 工单时长统计接口
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
public interface WorkOrderProcessTimeLengthService {
	/**
	 * 工单时长统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getWorkOrderProcessMainTimeLengthList(
			Map<String, String> parameters);
}
