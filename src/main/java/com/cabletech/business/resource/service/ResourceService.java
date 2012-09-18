package com.cabletech.business.resource.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 资源业务操作接口
 * 
 * @author 杨隽 2012-02-07 创建
 * 
 */
public interface ResourceService {
	/**
	 * 根据资源类型和用户信息获取资源map
	 * 
	 * @param businessType
	 *            专业类型
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public Map<String, Object> getResourcesMap(String businessType,
			UserInfo userInfo);

	/**
	 * 根据业务类型和资源编号获取资源名称
	 * 
	 * @param businessType
	 *            专业类型
	 * @param stationId
	 *            站点ID
	 * @param stationType
	 *            站点类型
	 * @return
	 */
	public String getResourceName(String businessType, String stationId,
			String stationType);

	/**
	 * 根据业务类型和用户信息获取资源列表字符串
	 * 
	 * @param parameter
	 *            告警参数
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public String getResources(FaultQueryParameter parameter, UserInfo userInfo);

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
			String businessType, String condition);

	/**
	 * 分页获取资源信息
	 * 
	 * @param resourceInfo
	 *            资源信息
	 * @param page
	 *            分页
	 * @return
	 */
	public Page getResourceInfo(ResourceInfo resourceInfo, Page page);

	/**
	 * 根据资源编号获取资源信息
	 * 
	 * @param resourceId
	 *            String
	 * @return ResourceInfo
	 */
	public ResourceInfo viewResourceInfo(String resourceId);
}
