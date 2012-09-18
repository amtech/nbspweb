package com.cabletech.business.workflow.wmaintain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.model.WMaintainSite;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResourceService;

/**
 * 查询待处理的资源站点服务接口实现
 * 
 * @author 杨隽 2012-04-23 创建
 * @author 杨隽 2012-06-04 修改基站重复的bug问题
 * @author 杨隽 2012-06-06 修改问题站点选择的bug问题
 * 
 */
@Service
@Transactional(readOnly = true)
public class WMaintainResourceServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainSite, String> implements
		WMaintainResourceService {
	// 维修作业计划中资源查询Dao
	@SuppressWarnings("rawtypes")
	@Resource(name = "WMaintainResourceDao")
	private WMaintainBaseDao wMaintainResourceDao;

	/**
	 * 根据查询条件获取无线资源计划列表字符串
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return String 无线资源计划列表字符串
	 */
	public String getWplanList(WMaintainPlan plan) {
		List<Map<String, Object>> list = getWMaintainResourceProblemList(plan);
		String planListStr = "";
		if (CollectionUtils.isEmpty(list)) {
			return planListStr;
		}
		List<String> planIdList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String planId = (String) map.get("PLAN_ID");
			if (planIdList.contains(planId)) {
				continue;
			}
			planIdList.add(planId);
			planListStr += planId;
			planListStr += "=";
			planListStr += (String) map.get("PLAN_NAME");
			planListStr += ";";
		}
		planListStr = planListStr.substring(0, planListStr.length() - 1);
		return planListStr;
	}

	/**
	 * 根据查询条件获取查询待处理的资源站点列表信息
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return List<Map<String, Object>> 待处理的资源站点列表信息
	 */
	@Override
	public List<Map<String, Object>> getWMaintainResourceList(WMaintainPlan plan) {
		List<Map<String, Object>> list = getWMaintainResourceProblemList(plan);
		List<Map<String, Object>> resourceList = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isEmpty(list)) {
			return resourceList;
		}
		List<String> resourceIdList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String resourceId = (String) map.get("RESOURCE_ID");
			String resourceType = (String) map.get("RESOURCE_TYPE");
			String resourceName = (String) map.get("RS_NAME");
			if (resourceIdList.contains(resourceId + "_" + resourceType)) {
				continue;
			}
			map.put("ID", resourceType + "_" + resourceId);
			map.put("SUBITEM_ID", resourceId);
			map.put("RES_", "root");
			map.put("SUBITEM_NAME", resourceName);
			map.put("SUBITEM_PATROL", "");
			resourceList.add(map);
			resourceIdList.add(resourceId + "_" + resourceType);
		}
		return resourceList;
	}

	/**
	 * 根据查询条件获取查询待处理的资源站点中存在问题列表信息
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件
	 * @return List<Map<String, Object>> 待处理的资源站点中存在问题列表信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getWMaintainResourceProblemList(
			WMaintainPlan plan) {
		String key = WPLAN_RESOURCE_CONDITION_GENERATE_KEY;
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(plan);
		ConditionGenerate conditionGenerate = super.getConditionGenerate(key,
				parameter);
		List<Map<String, Object>> list = wMaintainResourceDao
				.queryListForSql(conditionGenerate);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected WMaintainBaseDao getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wMaintainResourceDao;
	}
}
