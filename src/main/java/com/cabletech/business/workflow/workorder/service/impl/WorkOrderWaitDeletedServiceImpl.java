package com.cabletech.business.workflow.workorder.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.dao.WorkOrderDao;
import com.cabletech.business.workflow.workorder.dao.WorkOrderTransferDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitDeletedService;
import com.cabletech.common.util.Page;

/**
 * 待删除工作业务处理类
 * 
 * @author 汪杰2012-01-04 创建
 * @author 杨隽 2012-02-06 修改getWaitDeletedList()方法的参数和业务实现
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * @author 杨隽 2012-02-07 提取getWaitDeletedList()方法的公共部分
 * @author 杨隽 2012-02-07 修改deletedWorkOrder()和deletedTransferAndWorkOrder()方法
 * 
 */
@Service
public class WorkOrderWaitDeletedServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderTransfer, String> implements
		WorkOrderWaitDeletedService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderDao workOrderDao;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderTransferDao workOrderTransferDao;

	protected WorkOrderBaseDao<WorkOrderTransfer, String> getWorkOrderBaseDao() {
		return workOrderTransferDao;
	}

	/**
	 * 获取待删除业务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 查询条件参数
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitDeletedList(WorkOrder workOrder) {
		// 使用CanceledTaskConditionGenerateImpl的条件生成器实例进行sql组装查询
		Page page = super.getWorkOrderTransferList(workOrder,
				WAIT_DELETED_TASK_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 删除任务(工单信息+分发信息) 注：该方法在删除工单时将不关心工单的分发信息是否存在
	 * 
	 * @param workOrderId
	 *            String 工单编号
	 */
	@Transactional
	public void deletedWorkOrder(String workOrderId) {
		workOrderDao.delete(workOrderId);
		List<WorkOrderTransfer> list = workOrderTransferDao.find(
				" FROM WorkOrderTransfer WHERE taskId=?", workOrderId);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (WorkOrderTransfer workOrderTransfer : list) {
			workOrderTransferDao.delete(workOrderTransfer);
		}
	}

	/**
	 * 删除任务(工单信息+分发信息)注：该方法在删除工单时将关心工单的分发信息是否存在
	 * 
	 * @param workOrderId
	 *            String 工单编号
	 * @param transferId
	 *            String 工单分发编号
	 */
	@Transactional
	public void deletedTransferAndWorkOrder(String workOrderId,
			String transferId) {
		workOrderTransferDao.delete(transferId);
		workOrderDao.deleteWorkOrder(workOrderId);
	}
}