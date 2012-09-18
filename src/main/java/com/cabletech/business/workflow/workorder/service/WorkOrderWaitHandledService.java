package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.util.Page;

/**
 * 待办任务业务处理接口
 * 
 * @author 汪杰 2011-12-31 创建
 * @author 杨隽 2012-01-05 修改getWaitHandledList方法的参数和返回值
 * @author 杨隽 2012-01-07 修改getWaitHandledList方法的参数（去除userInfo参数）
 */
public interface WorkOrderWaitHandledService {
	/**
	 * 获取待办任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的待办任务列表
	 */
	@SuppressWarnings("rawtypes")
	public Page getWaitHandledList(WorkOrder workOrder);
}
