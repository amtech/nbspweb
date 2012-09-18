package com.cabletech.business.wplan.plan.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.util.Page;

/**
 * 巡检计划服务
 * 
 * @author zhaobi
 * 
 */
public interface PatrolinfoService {
	/**
	 * 保存巡检信息
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 */
	public void save(Patrolinfo patrolinfo);

	/**
	 * 删除巡检信息
	 * 
	 * @param planid
	 *            String
	 */
	public void delete(String planid);

	/**
	 * 查看巡检信息
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public Map<String, Object> view(String id);

	/**
	 * 保存巡检资源
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 */
	public void saveresource(Patrolinfo patrolinfo);

	/**
	 * 保存巡检计划模板
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 */
	public void savetemplate(Patrolinfo patrolinfo);

	/**
	 * 保存巡检审批
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 */
	public void saveapprove(Patrolinfo patrolinfo);

	/**
	 * 分页获取巡检计划信息列表
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page listByPage(Patrolinfo patrolinfo, Page page);

	/**
	 * 查询待办工作分页列表
	 * 
	 * @param patrolinfo
	 *            计划信息
	 * @param page
	 *            分页
	 * @param user
	 *            用户
	 * @return
	 */
	public Page queryWaithHandledList(Patrolinfo patrolinfo, Page page,
			UserInfo user);
}
