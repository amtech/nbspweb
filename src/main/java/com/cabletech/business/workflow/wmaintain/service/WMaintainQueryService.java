package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划任务列表业务处理接口
 * 
 * @author 王甜 2012-04-11 创建
 * @author 王甜 2012-04-17 补充实现的方法
 */
public interface WMaintainQueryService {
	/**
	 * 根据查询条件获取查询工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 待办工作列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	Page getQueryList(WMaintainPlan plan, UserInfo userInfo);
}
