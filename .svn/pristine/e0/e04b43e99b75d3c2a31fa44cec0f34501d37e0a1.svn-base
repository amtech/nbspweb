package com.cabletech.business.analysis.service;

import java.util.List;
import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 工单审核通过率统计接口
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
public interface WorkOrderApprovePassedRateService {
	/**
	 * 工单审核通过率统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	List<Map<String, Object>> getWorkOrderApprovePassedRateList(
			Map<String, String> parameters);

	/**
	 * 获取n次审核通过工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param approveTimes
	 *            int
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getNTimesApprovePassedWorkOrderList(Map<String, String> parameters,
			int approveTimes, Page page);
}
