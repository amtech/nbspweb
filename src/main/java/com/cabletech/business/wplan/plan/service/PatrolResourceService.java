package com.cabletech.business.wplan.plan.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.wplan.plan.model.PatrolResource;
import com.cabletech.common.base.BaseService;
import com.cabletech.common.util.Page;

/**
 * 巡检资源服务
 * 
 * @author Administrator
 * 
 */
public interface PatrolResourceService extends
		BaseService<PatrolResource, String> {

	/**
	 * 获取巡检资源
	 * 
	 * @param resourcetype
	 *            资源类型
	 * @param patrolgroupid
	 *            巡检组ID
	 * @return
	 */
	public List<Map<String, Object>> getPatrolResource(String resourcetype,
			String patrolgroupid);

	/**
	 * 根据计划ID，删除计划资源
	 * 
	 * @param planid
	 *            String
	 */
	public void deleteResource(String planid);

	/**
	 * 获取巡检资源根据计划ID
	 * 
	 * @param planid
	 *            String
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getPlanResource(String planid, Page page);

	/**
	 * 获取巡检资源列表
	 * 
	 * @param planid
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getPatrolResourceByPlanid(String planid);

	/**
	 * 获取巡检资源信息根据巡检结果
	 * 
	 * @param rid
	 *            巡检结果ID
	 * @return
	 */
	public Map<String, Object> getPatrolResourceInfo(String rid);

}
