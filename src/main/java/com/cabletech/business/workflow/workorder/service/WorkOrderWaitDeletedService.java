package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.util.Page;

/**
 * 待删除工作业务接口
 * 
 * @author 汪杰2012-01-04 创建
 * @author 杨隽 2012-02-06 修改getWaitDeletedList()方法的参数
 * @author 杨隽 2012-02-07 修改getWaitDeletedList()方法的参数（去除userInfo参数）
 */
public interface WorkOrderWaitDeletedService {
	/**
	 * 获取待删除业务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的待删除任务列表
	 */
	@SuppressWarnings("rawtypes")
	public Page getWaitDeletedList(WorkOrder workOrder);

	/**
	 * 删除工单 注：该方法在删除工单时将不关心工单的分发信息是否存在
	 * 
	 * @param workOrderId
	 *            String 工单编号
	 */
	public void deletedWorkOrder(String workOrderId);

	/**
	 * 删除工单 注：该方法在删除工单时将关心工单的分发信息是否存在
	 * 
	 * @param workOrderId
	 *            String 工单编号
	 * @param transferId
	 *            String 任务分发派单编号
	 */
	public void deletedTransferAndWorkOrder(String workOrderId,
			String transferId);
}
