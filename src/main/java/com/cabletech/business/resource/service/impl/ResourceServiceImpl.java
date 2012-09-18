package com.cabletech.business.resource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.dao.ResourceInfoDao;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 资源业务操作实现类
 * 
 * @author 杨隽 2012-02-07 创建
 * 
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {


	/**
	 * 资源信息Dao操作类
	 */
	@Resource(name = "resourceInfoDao")
	private ResourceInfoDao resourceInfoDao;

	/*
	 * 根据资源类型和用户信息获取资源map
	 * 
	 * @see
	 * com.cabletech.business.resource.service.ResourceService#getResourcesMap
	 * (java.lang.String, com.cabletech.baseinfo.business.entity.UserInfo)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getResourcesMap(String businessType,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		String condition = getUserCondition(userInfo);
		List<Map<String, Object>> list = getResourcesListByBusinessType(
				businessType, condition);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		if (list == null || list.isEmpty()) {
			return map;
		}
		for (int i = 0; i < list.size(); i++) {
			tempMap = list.get(i);
			if (tempMap == null) {
				continue;
			}
			String key = tempMap.get("id") + "_"
					+ tempMap.get("resource_type_code");
			map.put(key, tempMap.get("resource_name"));
		}
		return map;
	}

	/*
	 * 根据业务类型和资源编号获取资源名称 (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.resource.service.ResourceService#getResourceName
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public String getResourceName(String businessType, String id,
			String resourceType) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(businessType)) {
			return "";
		}
		String condition = " and mt.rs_id='" + id + "' ";
		condition += " and mt.rs_type='" + resourceType + "' ";
		List<Map<String, Object>> list = getResourcesListByBusinessType(
				businessType, condition);
		if (list == null || list.isEmpty()) {
			return "";
		}
		Map<String, Object> map = list.get(0);
		return (String) map.get("resource_name");
	}

	/*
	 * 根据业务类型和用户信息获取资源列表字符串
	 * 
	 * @see
	 * com.cabletech.business.resource.service.ResourceService#getResources(
	 * com.cabletech
	 * .business.workflow.fault.condition.parameter.FaultQueryParameter,
	 * com.cabletech.baseinfo.business.entity.UserInfo)
	 */
	@Override
	@Transactional(readOnly = true)
	public String getResources(FaultQueryParameter parameter, UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(parameter.getBusinessType())) {
			return "";
		}
		String condition = getCondition(parameter);
		condition += getUserCondition(userInfo);
		List<Map<String, Object>> list = getResourcesListByBusinessType(
				parameter.getBusinessType(), condition);
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuffer resources = generateResourcesString(list);
		return resources.toString();
	}

	/**
	 * 根据业务类型获取资源信息列表
	 * 
	 * @param businessType
	 *            businessType
	 * @param condition
	 *            condition
	 * @return
	 */
	public List<Map<String, Object>> getResourcesListByBusinessType(
			String businessType, String condition) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}

	/**
	 * 根据输入参数生成查询条件
	 * 
	 * @param parameter
	 *            FaultQueryParameter
	 * @return
	 */
	private String getCondition(FaultQueryParameter parameter) {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer("");
		if (StringUtils.isNotBlank(parameter.getMaintenceId())) {
			buf.append(" and mt.maintenance_id ='");
			buf.append(parameter.getMaintenceId());
			buf.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.getPatrolmanId())) {
			buf.append(" and mt.patrol_group_id ='");
			buf.append(parameter.getPatrolmanId());
			buf.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.getResourceName())) {
			buf.append(" and mt.rs_name like '%");
			buf.append(parameter.getResourceName());
			buf.append("%' ");
		}
		return buf.toString();
	}

	/**
	 * 根据列表生成资源信息字符串用于Ajax显示
	 * 
	 * @param list
	 *            list
	 * @return
	 */
	private StringBuffer generateResourcesString(List<Map<String, Object>> list) {
		Map<String, Object> map;
		StringBuffer resources = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			if (map == null) {
				continue;
			}
			resources.append(map.get("id"));
			resources.append("=");
			resources.append(map.get("resource_name"));
			resources.append("=");
			resources.append(map.get("resource_address"));
			resources.append("=");
			resources.append(map.get("patrol_group_id"));
			resources.append("=");
			resources.append(map.get("patrolname"));
			resources.append("=");
			resources.append(map.get("maintenance_id"));
			resources.append("=");
			resources.append(map.get("resource_type_code"));
			if (i < list.size() - 1) {
				resources.append(";");
			}
		}
		return resources;
	}

	/**
	 * 根据登录用户获取查询条件sql
	 * 
	 * @param userInfo
	 *            userInfo
	 * @return
	 */
	private String getUserCondition(UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer condition = new StringBuffer("");
		if (userInfo.isCityMobile()) {
			condition.append(" and exists( ");
			condition.append(" select regionid from region rg ");
			condition.append(" where rg.regionid=c.regionid ");
			condition.append(" start with rg.regionid='");
			condition.append(userInfo.getRegionId());
			condition
					.append("' connect by prior rg.regionid=rg.parentregionid ");
			condition.append(" ) ");
		}
		if (userInfo.isCityContractor()) {
			condition.append(" and c.contractorid='");
			condition.append(userInfo.getOrgId());
			condition.append("' ");
		}
		return condition.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.resource.service.ResourceService#getResourceInfo
	 * (com.cabletech.business.resource.model.ResourceInfo)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getResourceInfo(ResourceInfo resourceInfo, Page page) {
		return resourceInfoDao.getResourceInfo(resourceInfo, page);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResourceInfo viewResourceInfo(String resourceId) {
		return resourceInfoDao.view(resourceId);
	}
}
