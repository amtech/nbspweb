package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 制定维修作业计划服务接口
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * @author 杨隽 2012-04-17 添加deletePlan()方法
 * 
 */
public interface WMaintainCreatePlanService {
	/**
	 * 制定维修作业计划
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void save(WMaintainPlan plan, UserInfo userInfo);

	/**
	 * 查看维修作业计划详细信息
	 * 
	 * @param id
	 *            String 维修作业计划编号
	 * @return WMaintainPlan 维修作业计划详细信息数据
	 */
	WMaintainPlan view(String id);

	/**
	 * 删除维修作业计划信息
	 * 
	 * @param id
	 *            String[] 维修作业计划编号
	 */
	void deletePlan(String[] id);
}
