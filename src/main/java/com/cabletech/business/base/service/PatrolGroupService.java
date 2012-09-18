package com.cabletech.business.base.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.PatrolGroup;
import com.cabletech.common.util.Page;

/**
 * 巡检组基础数据
 * @author zhaobi
 *
 */
public interface PatrolGroupService {
	/**
	 * 根据巡检组名称搜索巡检组列表
	 * 
	 * @param patrolGroupName
	 *            String
	 * @param user
	 *            UserInfo
	 * @param page
	 *            Page
	 */
	public void getPatrolGroupList(String patrolGroupName, UserInfo user,
			Page page);
	
	/**
	 * 根据组织ID获取巡检组
	 * @param orgid orgid
	 * @return
	 */
	@Deprecated
	public List<PatrolGroup>  getPatrolGroupByOrgid(String orgid);
	
	
	/**
	 * 根据用户ID获取巡检组
	 * @param staffid staffid
	 * @return
	 */
	public List<Map<String,Object>>  getPatrolGroupByStaffid(String staffid);
}
