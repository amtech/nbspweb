package com.cabletech.business.workflow.wmaintain.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 查询待处理的资源站点服务接口
 * 
 * @author 杨隽 2012-04-23 创建
 * 
 */
public interface WMaintainResourceService {
	/**
	 * 根据查询条件获取无线资源计划列表字符串
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return String 无线资源计划列表字符串
	 */
	String getWplanList(WMaintainPlan plan);

	/**
	 * 根据查询条件获取查询待处理的资源站点列表信息
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return List<Map<String, Object>> 待处理的资源站点列表信息
	 */
	List<Map<String, Object>> getWMaintainResourceList(WMaintainPlan plan);

	/**
	 * 根据查询条件获取查询待处理的资源站点中存在问题列表信息
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return List<Map<String, Object>> 待处理的资源站点中存在问题列表信息
	 */
	List<Map<String, Object>> getWMaintainResourceProblemList(WMaintainPlan plan);
}
