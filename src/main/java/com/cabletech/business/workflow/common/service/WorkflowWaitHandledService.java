package com.cabletech.business.workflow.common.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 工作流待办列表数量业务处理类接口
 * 
 * @author 杨隽 2012-03-13 创建
 * 
 */
public interface WorkflowWaitHandledService {
	/**
	 * 根据当前登录用户获取待办工作数量
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return int 待办工作数量
	 */
	int getWorkflowWaitHandledNumber(UserInfo userInfo);

	/**
	 * 根据当前登录用户获取待办工作map
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return Map<String,Object> 待办工作map
	 */
	Map<String, Object> getWorkflowWaitHandledNumberMap(UserInfo userInfo);
}
