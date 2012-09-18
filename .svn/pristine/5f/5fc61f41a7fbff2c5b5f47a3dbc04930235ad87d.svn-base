package com.cabletech.business.wplan.nopatrolstation.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.nopatrolstation.model.NoPatrolStation;
import com.cabletech.common.util.Page;

/**
 * 未巡检站点原因登记业务接口
 * 
 * @author 杨隽 2012-07-23 创建
 * 
 */
public interface NoPatrolStationService {
	/**
	 * 根据查询条件获取未巡检站点原因登记列表数据
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("rawtypes")
	public void queryNoPatrolStationPage(NoPatrolStation noPatrolStation,
			Page page);

	/**
	 * 根据查询条件获取当前处于巡检状态的站点列表数据
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("rawtypes")
	public void queryStationPage(NoPatrolStation noPatrolStation, Page page);

	/**
	 * 保存未巡检站点原因登记信息
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @param user
	 *            UserInfo
	 */
	public void saveNoPatrolStation(NoPatrolStation noPatrolStation,
			UserInfo user);

	/**
	 * 查看未巡检站点原因登记信息
	 * 
	 * @param id
	 *            String
	 * @return NoPatrolStation
	 */
	public NoPatrolStation viewNoPatrolStation(String id);

	/**
	 * 确认未巡检站点原因登记信息
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @param user
	 *            UserInfo
	 */
	public void confirmNoPatrolStation(NoPatrolStation noPatrolStation,
			UserInfo user);
}
