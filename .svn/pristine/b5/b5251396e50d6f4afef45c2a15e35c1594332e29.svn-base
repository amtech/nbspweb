package com.cabletech.business.wplan.plan.service;

import java.util.Map;
import com.cabletech.common.util.Page;

/**
 * 
 * @author Administrator
 * 
 */
public interface PatrolinfoResultService {
	/**
	 * 按区域统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByRegion(Map<String, Object> parameter, Page page);

	/**
	 * 按组织统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByOrg(Map<String, Object> parameter, Page page);

	/**
	 * 按巡检组统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByPatrolGroup(Map<String, Object> parameter, Page page);

	/**
	 * 查看未巡检站点明细
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page noPatrolDetails(Map<String, Object> parameter, Page page);

	/**
	 * 问题站点明细
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return Page
	 */
	public Page statisticProblemStation(Map<String, Object> parameter, Page page);

	/**
	 * 站点异常项明细
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return Page
	 */
	public Page statisticStationError(Map<String, Object> parameter, Page page);

	/**
	 * 获取计划信息
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getPlanInfo(Map<String, Object> parameter, Page page);
}