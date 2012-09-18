package com.cabletech.business.wplan.plan.service;

import java.util.Map;
import com.cabletech.common.util.Page;

/**
 * 巡检进度查询
 * 
 * @author wj
 * 
 */
public interface PatrolinfoScheduleService {
	/**
	 * 按区域统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
	 * @return LIST
	 */
	public Page statisticsByRegion(Map<String, Object> parameter, Page page);

	/**
	 * 按组织统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
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
	 * 按巡检员统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByPatrolMan(Map<String, Object> parameter, Page page);

	/**
	 * 查询资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page searchDetailed(Map<String, Object> parameter, Page page);
}
