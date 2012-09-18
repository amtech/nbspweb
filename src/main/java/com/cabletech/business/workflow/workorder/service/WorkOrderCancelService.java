package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理接口
 * 
 * @author 汪杰2011-12-31 创建
 * @author 杨隽 2012-02-06 修改getWaitCanceledList()方法和getCanceledList()方法的参数
 * @author 杨隽 2012-02-07
 *         修改getWaitCanceledList()方法和getCanceledList()方法的参数（去除userInfo参数）
 * 
 */
public interface WorkOrderCancelService {
	/**
	 * 获取待取消任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的待取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getWaitCanceledList(WorkOrder workOrder);

	/**
	 * 获取已取消任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的已取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getCanceledList(WorkOrder workOrder);

	/**
	 * 取消任务
	 * 
	 * @param workTransferId
	 *            String 任务分发派单编号
	 */
	void cancelTask(String workTransferId);
}
