package com.cabletech.business.wplan.plan.service;

import java.util.Map;

import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.util.Page;

/**
 * 巡检分析服务 zhaobi
 * 
 * @author Administrator
 * 
 */
public interface PatrolanalysisService {

	/**
	 * 获得所有统计需要的巡检计划数
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public Map<String, Object> getAllPatrolCount(Patrolinfo patrolinfo);

	/**
	 * 获取巡检组巡检信息
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getPatrolGroupPatrolInfo(Patrolinfo patrolinfo, Page page);

	/**
	 * 获取巡检组图表数据
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public Map<String, Object> getPatrolGroupChart(Patrolinfo patrolinfo);

	/**
	 * 获取组织图表图表数据
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public Map<String, Object> getPatrolOrgChart(Patrolinfo patrolinfo);

	/**
	 * 获取巡检计划信息列表
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            page
	 * @return
	 */
	public Page getPatrolInfo(Patrolinfo patrolinfo, Page page);

	/**
	 * 获取区域巡检信息
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            page
	 * @return
	 */
	public Page getRegionPatrolInfo(Patrolinfo patrolinfo, Page page);
}
