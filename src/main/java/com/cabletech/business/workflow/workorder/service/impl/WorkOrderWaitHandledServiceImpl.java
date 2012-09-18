package com.cabletech.business.workflow.workorder.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.baseinfo.excel.ExportSupport;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitHandledService;
import com.cabletech.common.util.Page;

/**
 * 待办工作业务处理类
 * 
 * @author 汪杰 2011-12-30 创建
 * @author 杨隽 2012-01-05 修改getWaitHandledList方法的参数和返回值
 * @author 杨隽 2012-02-06 修改getWaitHandledList方法
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * @author 杨隽 2012-02-07 提取getWaitHandledList()方法的公共部分
 * 
 */
@Service
public class WorkOrderWaitHandledServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderTransfer, String> implements
		WorkOrderWaitHandledService,ExportSupport {
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	protected WorkOrderBaseDao<WorkOrderTransfer, String> getWorkOrderBaseDao() {
		return workOrderTransferDao;
	}

	/**
	 * 获取待办任务列表
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单查询实体
	 * @return Page 通用工单的待办任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitHandledList(WorkOrder workOrder) {
		// 使用WaitHandledTaskConditionGenerateImpl的条件生成器实例进行sql组装查询
		Page page = super.getWorkOrderTransferList(workOrder,
				WAIT_HANDLED_TASK_CONDITION_GENERATE_KEY);
		return page;
	}

}