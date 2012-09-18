package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.dao.WorkOrderTransferDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderHandledService;
import com.cabletech.common.util.Page;

/**
 * 已办工作业务处理类
 * 
 * @author 汪杰 2011-12-31 创建
 * @author 杨隽 2012-02-06 修改getHandledList()方法的参数、业务实现
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * @author 杨隽 2012-02-07 提取getHandledList()方法的公共部分
 * 
 */
@Service
public class WorkOrderHandledServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderTransfer, String> implements
		WorkOrderHandledService {
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderTransferDao workOrderTransferDao;

	protected WorkOrderBaseDao<WorkOrderTransfer, String> getWorkOrderBaseDao() {
		return workOrderTransferDao;
	}

	/**
	 * 获取已办任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的已办任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getHandledList(WorkOrder workOrder) {
		// 使用HandledTaskConditionGenerateImpl的条件生成器实例进行sql组装查询
		Page page = super.getWorkOrderTransferList(workOrder,
				HANDLED_TASK_CONDITION_GENERATE_KEY);
		return page;
	}
}
