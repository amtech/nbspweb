package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderReplyCheck;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyCheckService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单回单验证业务接口实现
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao()方法实现
 * @author 杨隽 2012-02-24 修改doWorkflow()方法以支持转审
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改save方法和doWorkflow方法）
 * 
 */
@Service
@Transactional
public class WorkOrderReplyCheckServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderReplyCheck, String> implements
		WorkOrderReplyCheckService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	@Override
	protected WorkOrderBaseDao<WorkOrderReplyCheck, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存通用工单回单验证信息
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 */
	@Override
	public void save(WorkOrderReplyCheck workOrderReplyCheck) {
		// TODO Auto-generated method stub
		SmParameter smParameter = getSmParameter(workOrderReplyCheck);
		ProMockPo proMockPo = doWorkflow(workOrderReplyCheck, smParameter);
		String taskName = proMockPo.getTaskName();
		String pId = workOrderReplyCheck.getWorkflowBzId();
		String taskState = "";
		if (proMockPo.isFlowOver()) {
			taskState = WorkOrder.WORKORDER_END_STATE;
		} else {
			taskState = WorkOrder.getTaskState(taskName);
		}
		WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
		workOrderTransfer.setTaskState(taskState);
		workOrderTransferDao.save(workOrderTransfer);
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 * @return SmParameter 发送短信的传递参数
	 */
	private SmParameter getSmParameter(WorkOrderReplyCheck workOrderReplyCheck) {
		// TODO Auto-generated method stub
		String taskId = workOrderReplyCheck.getTaskId();
		WorkOrder workOrder = workOrderDao.get(taskId);
		String simId = getSimId(workOrderReplyCheck);
		String[] contentParameters = new String[] { workOrder.getTaskName() };
		String msgId = getMessageId(workOrderReplyCheck);
		SmParameter parameter = SmParameter.getInstance(WORKORDER_XML_FILE_ID,
				msgId, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(workOrder.getBusinessType());
		parameter.setHandleLimit(workOrder.getOvertimeSet());
		parameter.setHandlePersonId(workOrderReplyCheck
				.getAcceptMessageUserId());
		parameter.setWorkorderId(workOrder.getId());
		parameter.setWorkorderTitle(workOrder.getTaskName());
		parameter.setWorkorderType(SysConstant.COMMON_ORDER_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 根据通用工单回单验证实体获取发送短信的编号
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 * @return String 发送短信的编号
	 */
	private String getMessageId(WorkOrderReplyCheck workOrderReplyCheck) {
		String msgId = "";
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(workOrderReplyCheck
				.getCheckResult())) {
			msgId = APPROVE_PASSED_TASK_MSG_ID;
		} else if (SysConstant.TRANSFER_WORKFLOW_TRANSTION
				.equals(workOrderReplyCheck.getCheckResult())) {
			msgId = APPROVE_TRANSFER_TASK_MSG_ID;
		} else {
			msgId = APPROVE_NOTPASSED_TASK_MSG_ID;
		}
		return msgId;
	}

	/**
	 * 根据通用工单回单验证实体获取发送短信的sim卡号
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 * @return String 发送短信的sim卡号
	 */
	private String getSimId(WorkOrderReplyCheck workOrderReplyCheck) {
		String simId = "";
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(workOrderReplyCheck
				.getCheckResult())) {
			simId = super.getUserPhone(workOrderReplyCheck
					.getTransferApprover());
			workOrderReplyCheck.setAcceptMessageUserId(workOrderReplyCheck
					.getTransferApprover());
		} else {
			String pId = workOrderReplyCheck.getWorkflowBzId();
			WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
			simId = super.getUserPhone(workOrderTransfer.getTargetPrincipal());
			workOrderReplyCheck.setAcceptMessageUserId(workOrderTransfer
					.getTargetPrincipal());
		}
		return simId;
	}

	/**
	 * 完成回单验证工作流的业务处理
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 * @param smParameter
	 *            SmParameter 发送短信的传递参数
	 * @return ProMockPo 工作流实体
	 */
	private ProMockPo doWorkflow(WorkOrderReplyCheck workOrderReplyCheck,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setUserId(workOrderReplyCheck.getCheckUserId());
		taskPi.setTaskId(workOrderReplyCheck.getWorkflowTaskId());
		taskPi.setTransition(workOrderReplyCheck.getCheckResult());
		taskPi.setComment(workOrderReplyCheck.getCheckRemark());
		String userName = super.getUserName(workOrderReplyCheck
				.getCheckUserId());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setUserName(userName);
		}
		taskPi.setDealUsers(workOrderReplyCheck.getTransferApprover());
		userName = super.getUserName(workOrderReplyCheck.getTransferApprover());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setDealUsersName(userName);
		}
		return super.workOrderWorkflowService.doTask(taskPi, smParameter);
	}
}
