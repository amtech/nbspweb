package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 填写维修作业计划维护报告服务接口
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * 
 */
public interface WMaintainCreateReportService {
	/**
	 * 填写维修作业计划维护报告
	 * 
	 * @param planReport
	 *            WMaintainPlan 维修作业计划维护报告表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void save(WMaintainPlan planReport, UserInfo userInfo);
}
