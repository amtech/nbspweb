package com.cabletech.business.wplan.plan.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.plan.model.PatrolApprove;

/**
 * 巡检计划流程审批
 * 
 * @author zhaobi
 * 
 */
public interface PatrolApproveService {

	/**
	 * 根据计划ID，删除计划流程审批
	 * 
	 * @param planid
	 *            String
	 */
	public void deleteApprove(String planid);

	/**
	 * 保存审批联系人
	 * 
	 * @param patrolapprove
	 *            PatrolApprove
	 */
	public void save(PatrolApprove patrolapprove);

	/**
	 * 工作流审批
	 * 
	 * @param patrolapprove
	 *            PatrolApprove
	 * @param userinfo
	 *            UserInfo
	 */
	public void audit(PatrolApprove patrolapprove, UserInfo userinfo);

}
