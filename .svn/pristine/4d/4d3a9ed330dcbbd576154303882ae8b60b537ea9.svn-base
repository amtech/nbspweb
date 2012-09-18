package com.cabletech.business.workflow.wmaintain.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 计划维护站点服务接口
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deletePlanSite()方法
 * @author 杨隽 2012-04-19 添加getPlanSiteMaintainNumberMap()方法
 * @author 杨隽 2012-04-26 添加getWMaintainSiteListInGrid()方法
 * 
 */
public interface WMaintainSiteService {
	/**
	 * 保存制定维修作业计划中的站点信息
	 * 
	 * @param plan
	 *            WMaintainPlan 制定的维修作业计划
	 */
	void save(WMaintainPlan plan);

	/**
	 * 根据计划编号删除维修作业计划中的站点信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 */
	void deletePlanSite(String planId);

	/**
	 * 根据计划编号获取维修作业计划中的站点不同维护状态的维护数量信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return Map<String,Object> 维修作业计划中的站点不同维护状态的维护数量信息
	 */
	Map<String, Object> getPlanSiteMaintainNumberMap(String planId);

	/**
	 * 根据计划编号获取维修作业计划中的站点列表信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点列表信息
	 */
	List<Map<String, Object>> getWMaintainSiteList(String planId);

	/**
	 * 根据计划编号获取维修作业计划中的站点列表信息（用于在编辑页面中显示站点列表）
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点列表信息
	 */
	List<Map<String, Object>> getWMaintainSiteListInGrid(String planId);
}
