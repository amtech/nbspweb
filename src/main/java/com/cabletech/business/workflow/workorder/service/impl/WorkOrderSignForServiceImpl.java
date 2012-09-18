package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderSignFor;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderSignForService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单签收业务接口实现
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao()方法实现
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改save方法和doWorkflow方法）
 * 
 */
@Service
@Transactional
public class WorkOrderSignForServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderSignFor, String> implements
		WorkOrderSignForService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	@Override
	protected WorkOrderBaseDao<WorkOrderSignFor, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存通用工单签收信息
	 * 
	 * @param workOrderSignFor
	 *            WorkOrderSignFor 通用工单签收实体
	 */
	@Override
	public void save(WorkOrderSignFor workOrderSignFor) {
		// TODO Auto-generated method stub
		SmParameter parameter = getSmParameter(workOrderSignFor);
		String pId = workOrderSignFor.getWorkflowBzId();
		WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
		ProMockPo proMockPo = doWorkflow(workOrderSignFor,
				workOrderTransfer.getSourcePrincipal(), parameter);
		String taskName = proMockPo.getTaskName();
		String taskState = WorkOrder.getTaskState(taskName);
		String patrolGroupId = workOrderSignFor.getPatrolGroupId();
		workOrderTransfer.setTaskState(taskState);
		workOrderTransfer.setGroupId(patrolGroupId);
		workOrderTransferDao.save(workOrderTransfer);
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param workOrderSignFor
	 *            WorkOrderSignFor 通用工单签收实体
	 * @return SmParameter 发送短信的传递参数
	 */
	private SmParameter getSmParameter(WorkOrderSignFor workOrderSignFor) {
		// TODO Auto-generated method stub
		String taskId = workOrderSignFor.getTaskId();
		WorkOrder workOrder = workOrderDao.get(taskId);
		String simId = super.getUserPhone(workOrder.getCreater());
		String signForUserId = workOrderSignFor.getSignForUserId();
		String signForUserName = super.getUserName(signForUserId);
		String[] contentParameters = new String[] { workOrder.getTaskName(),
				signForUserName };
		SmParameter parameter = SmParameter.getInstance(WORKORDER_XML_FILE_ID,
				REFUSE_TASK_MSG_ID, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(workOrder.getBusinessType());
		parameter.setHandleLimit(workOrder.getOvertimeSet());
		parameter.setHandlePersonId(workOrder.getCreater());
		parameter.setWorkorderId(workOrder.getId());
		parameter.setWorkorderTitle(workOrder.getTaskName());
		parameter.setWorkorderType(SysConstant.COMMON_ORDER_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 完成签收工作流的业务处理
	 * 
	 * @param workOrderSignFor
	 *            WorkOrderSignFor 通用工单签收实体
	 * @param refuseConfirmUserId
	 *            String 拒签确认人编号
	 * @param parameter
	 *            SmParameter 发送短信的传递参数
	 * @return ProMockPo 工作流实体
	 */
	private ProMockPo doWorkflow(WorkOrderSignFor workOrderSignFor,
			String refuseConfirmUserId, SmParameter parameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setUserId(workOrderSignFor.getSignForUserId());
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(workOrderSignFor
				.getSignForResult())) {
			taskPi.setDealUsers(workOrderSignFor.getSignForUserId());
		}
		if (SysConstant.REFUSE_WORKFLOW_TRANSITION.equals(workOrderSignFor
				.getSignForResult())) {
			taskPi.setDealUsers(refuseConfirmUserId);
		}
		taskPi.setTaskId(workOrderSignFor.getWorkflowTaskId());
		taskPi.setTransition(workOrderSignFor.getSignForResult());
		taskPi.setComment(workOrderSignFor.getSignForRemark());
		String userName = super
				.getUserName(workOrderSignFor.getSignForUserId());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setUserName(userName);
		}
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(workOrderSignFor
				.getSignForResult())) {
			if (StringUtils.isNotBlank(userName)) {
				taskPi.setDealUsersName(userName);
			}
			parameter = null;
		}
		if (SysConstant.REFUSE_WORKFLOW_TRANSITION.equals(workOrderSignFor
				.getSignForResult())) {
			userName = super.getUserName(refuseConfirmUserId);
			if (StringUtils.isNotBlank(userName)) {
				taskPi.setDealUsersName(userName);
			}
		}
		return super.workOrderWorkflowService.doTask(taskPi, parameter);
	}
}
