package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 审核维修作业计划服务接口
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * 
 */
public interface WMaintainApprovePlanService {
	/**
	 * 审核维修作业计划
	 * 
	 * @param planApprove
	 *            WMaintainPlan 审核维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void approvePlan(WMaintainPlan planApprove, UserInfo userInfo);
}
