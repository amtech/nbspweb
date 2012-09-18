package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划取消任务列表业务处理接口
 * 
 * @author 王甜 2012-04-17 创建
 * @author 杨隽 2012-04-17 添加cancel()方法
 * @author 杨隽 2012-04-18 修改cancel()方法的参数
 * 
 */
public interface WMaintainCancelService {
	/**
	 * 根据查询条件获取查询待取消工作列表分页数据
	 * 
	 * @param plan
	 *            UserInfo 当前用户信息
	 * @param userInfo
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 */
	@SuppressWarnings("rawtypes")
	Page getWaitCancelList(WMaintainPlan plan, UserInfo userInfo);

	/**
	 * 根据查询条件获取查询已经取消工作列表分页数据
	 * 
	 * @param plan
	 *            UserInfo 当前用户信息
	 * @param userInfo
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 */
	@SuppressWarnings("rawtypes")
	Page getCancedlList(WMaintainPlan plan, UserInfo userInfo);

	/**
	 * 取消隐患维修作业计划流程
	 * 
	 * @param id
	 *            String[] 隐患维修作业计划编号
	 */
	void cancel(String[] id);
}
