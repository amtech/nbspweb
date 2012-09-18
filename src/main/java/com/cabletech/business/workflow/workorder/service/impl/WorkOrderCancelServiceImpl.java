package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.dao.WorkOrderTransferDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderCancelService;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理类
 * 
 * @author 汪杰2011-12-31 创建
 * @author 杨隽 2012-02-06 修改getWaitCanceledList()方法和getCanceledList()方法的参数、业务实现
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * @author 杨隽 2012-02-07 提取getWaitCanceledList()方法和getCanceledList()方法的公共部分
 * 
 */
@Service
public class WorkOrderCancelServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderTransfer, String> implements
		WorkOrderCancelService {
	// 工作流处理服务
	@Resource(name = "workFlowServiceClient")
	private WorkFlowServiceClient workflowClient;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderTransferDao workOrderTransferDao;

	@Override
	protected WorkOrderBaseDao<WorkOrderTransfer, String> getWorkOrderBaseDao() {
		return workOrderTransferDao;
	}

	/**
	 * 获取待取消任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的待取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitCanceledList(WorkOrder workOrder) {
		// 使用WaitCanceledTaskConditionGenerateImpl的条件生成器实例进行sql组装查询
		Page page = super.getWorkOrderTransferList(workOrder,
				WAIT_CANCELED_TASK_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 获取已取消任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的已取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getCanceledList(WorkOrder workOrder) {
		// 使用CanceledTaskConditionGenerateImpl的条件生成器实例进行sql组装查询
		Page page = super.getWorkOrderTransferList(workOrder,
				CANCELED_TASK_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 取消任务
	 * 
	 * @param workTransferId
	 *            String 任务分发派单编号
	 */
	@Transactional
	public void cancelTask(String workTransferId) {
		workflowClient.deletePins(workTransferId);
		WorkOrderTransfer workOrderTransfer = workOrderTransferDao
				.get(workTransferId);
		workOrderTransfer.setTaskState(WorkOrder.WORKORDER_CANCELED_STATE);
		workOrderTransferDao.save(workOrderTransfer);
	}
}