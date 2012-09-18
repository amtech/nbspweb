package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 已办任务业务处理接口
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
public interface FaultDispatchHandledService {
	/**
	 * 获取已办任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return Page 故障派单的已办任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getHandledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo);
}
