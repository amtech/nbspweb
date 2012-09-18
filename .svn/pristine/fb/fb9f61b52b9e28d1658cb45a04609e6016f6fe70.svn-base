package com.cabletech.business.workflow.workorder.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 首页工单统计业务服务接口
 * 
 * @author 杨隽 2012-03-09 创建
 * 
 */
public interface WorkOrderStatisticService {
	/**
	 * 根据当前用户信息进行首页工单统计
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Map<String, List<String>> 首页工单统计结果列表
	 */
	Map<String, Object> getWorkOrderStatisticResultMap(UserInfo userInfo);

	/**
	 * 根据当前用户信息获取首页超时工单数量信息列表
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return List<Map<String, Object>> 首页超时工单数量信息列表
	 */
	List<Map<String, Object>> getOvertimeWorkOrderStatisticResultList(
			UserInfo userInfo);
}
