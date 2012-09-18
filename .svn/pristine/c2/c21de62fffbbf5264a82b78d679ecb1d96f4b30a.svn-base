package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理接口
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
public interface FaultDispatchCancelService {
	/**
	 * 获取待取消任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 故障派单的待取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getWaitCanceledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo);

	/**
	 * 获取已取消任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 故障派单的已取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getCanceledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo);

	/**
	 * 取消任务
	 * 
	 * @param workTransferId
	 *            String 任务分发派单编号
	 */
	void cancelDispatch(String workTransferId);
}
