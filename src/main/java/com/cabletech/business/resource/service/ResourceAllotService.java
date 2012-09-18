package com.cabletech.business.resource.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.model.ResourceAllotForm;

/**
 * 资源分配
 * 
 * @author wj
 * @author 杨隽 2012-07-20
 *         删除queryRelatingContractor()、queryRelatingPatrolman()和saveRsMaintenance
 *         ()方法
 * @author 杨隽 2012-07-20 改名queryResourcesString()和queryResourceList()方法
 * @author 杨隽 2012-07-20 添加recycleResources()和allotResources()方法
 */
public interface ResourceAllotService {
	/**
	 * 根据查询条件获取所有未分配的资源
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @return string
	 */
	public List<Map<String, Object>> queryResourcesString(ResourceAllotForm form);

	/**
	 * 根据查询条件获取所有待确认的资源
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @return list
	 */
	public List<Map<String, Object>> queryResourceList(ResourceAllotForm form);

	/**
	 * 执行资源分配
	 * 
	 * @param form
	 *            ResourceAllotForm
	 * @param user
	 *            UserInfo
	 */
	public void allotResources(ResourceAllotForm form, UserInfo user);

	/**
	 * 执行资源回收
	 * 
	 * @param form
	 *            ResourceAllotForm
	 * @param user
	 *            UserInfo
	 * 
	 */
	public void recycleResources(ResourceAllotForm form, UserInfo user);

	/**
	 * 判断资源是否被分配过
	 * 
	 * @param parameter
	 *            ResourceAllotForm
	 * @return
	 */
	public String isAllotedToSelf(ResourceAllotForm parameter);
}
