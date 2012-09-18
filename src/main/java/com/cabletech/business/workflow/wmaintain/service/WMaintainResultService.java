package com.cabletech.business.workflow.wmaintain.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.model.WMaintainSite;

/**
 * 站点异常项及处理结果服务接口
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deletePlanResult()方法
 * @author 杨隽 2012-04-26 添加getWMaintainResultListInGrid()方法
 * @author 杨隽 2012-07-10 添加saveWmaintainProcess()方法以支持隐患现场处理信息的保存
 * @author 杨隽 2012-07-10 修改getWMaintainResultList()方法的参数
 * 
 */
public interface WMaintainResultService {
	/**
	 * 保存制定维修作业计划中的站点异常项信息
	 * 
	 * @param plan
	 *            WMaintainPlan 制定的维修作业计划
	 * @param site
	 *            WMaintainSite 制定的维修作业计划中的站点信息
	 */
	void save(WMaintainPlan plan, WMaintainSite site);

	/**
	 * 保存隐患现场处理过程
	 * 
	 * @param plan
	 *            WMaintainPlan 隐患现场处理过程信息
	 */
	void saveWmaintainProcess(WMaintainPlan plan);

	/**
	 * 根据计划编号删除维修作业计划中的站点异常项信息
	 * 
	 * @param planId
	 *            String 计划编号
	 */
	void deletePlanResult(String planId);

	/**
	 * 根据计划编号获取维修作业计划中的站点异常项列表信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @param type
	 *            String 查询类型
	 * @return List<Map<String, Object>> 维修作业计划中的站点异常项列表信息
	 */
	List<Map<String, Object>> getWMaintainResultList(String planId, String type);

	/**
	 * 根据计划编号获取维修作业计划中的站点异常项列表信息（用于在编辑页面中显示站点列表）
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点异常项列表信息
	 */
	List<Map<String, Object>> getWMaintainResultListInGrid(String planId);
}
