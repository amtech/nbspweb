package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderRefuseConfirm;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderRefuseConfirmService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单拒签确认业务接口实现
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao()方法实现
 * @author 杨隽 2012-02-24 修改doWorkflow()方法以支持转审
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改save方法和doWorkflow方法）
 * 
 */
@Service
@Transactional
public class WorkOrderRefuseConfirmServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderRefuseConfirm, String> implements
		WorkOrderRefuseConfirmService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	@Override
	protected WorkOrderBaseDao<WorkOrderRefuseConfirm, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存通用工单拒签确认信息
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 */
	@Override
	public void save(WorkOrderRefuseConfirm workOrderRefuseConfirm) {
		// TODO Auto-generated method stub
		SmParameter parameter = getSmParameter(workOrderRefuseConfirm);
		ProMockPo proMockPo = doWorkflow(workOrderRefuseConfirm, parameter);
		String taskName = proMockPo.getTaskName();
		String pId = workOrderRefuseConfirm.getWorkflowBzId();
		String taskState = WorkOrder.getTaskState(taskName);
		WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
		workOrderTransfer.setTaskState(taskState);
		workOrderTransferDao.save(workOrderTransfer);
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 * @return SmParameter 发送短信的传递参数
	 */
	private SmParameter getSmParameter(
			WorkOrderRefuseConfirm workOrderRefuseConfirm) {
		// TODO Auto-generated method stub
		String taskId = workOrderRefuseConfirm.getTaskId();
		WorkOrder workOrder = workOrderDao.get(taskId);
		String simId = getSimId(workOrderRefuseConfirm);
		String[] contentParameters = new String[] { workOrder.getTaskName() };
		String msgId = getMessageId(workOrderRefuseConfirm);
		SmParameter parameter = SmParameter.getInstance(WORKORDER_XML_FILE_ID,
				msgId, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(workOrder.getBusinessType());
		parameter.setHandleLimit(workOrder.getOvertimeSet());
		parameter.setHandlePersonId(workOrderRefuseConfirm
				.getAcceptMessageUserId());
		parameter.setWorkorderId(workOrder.getId());
		parameter.setWorkorderTitle(workOrder.getTaskName());
		parameter.setWorkorderType(SysConstant.COMMON_ORDER_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 根据通用工单拒签确认实体获取发送的sim卡号
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 * @return 发送的sim卡号
	 */
	private String getSimId(WorkOrderRefuseConfirm workOrderRefuseConfirm) {
		String simId = "";
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION
				.equals(workOrderRefuseConfirm.getRefuseConfirmResult())) {
			simId = super.getUserPhone(workOrderRefuseConfirm
					.getTransferApprover());
			workOrderRefuseConfirm
					.setAcceptMessageUserId(workOrderRefuseConfirm
							.getTransferApprover());
		} else {
			String pId = workOrderRefuseConfirm.getWorkflowBzId();
			WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
			simId = super.getUserPhone(workOrderTransfer.getTargetPrincipal());
			workOrderRefuseConfirm.setAcceptMessageUserId(workOrderTransfer
					.getTargetPrincipal());
		}
		return simId;
	}

	/**
	 * 根据通用工单拒签确认实体获取工单发送短信的编号
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 * @return String 工单发送短信的编号
	 */
	private String getMessageId(WorkOrderRefuseConfirm workOrderRefuseConfirm) {
		String msgId = "";
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(workOrderRefuseConfirm
				.getRefuseConfirmResult())) {
			msgId = CONFIRM_PASSED_TASK_MSG_ID;
		} else if (SysConstant.TRANSFER_WORKFLOW_TRANSTION
				.equals(workOrderRefuseConfirm.getRefuseConfirmResult())) {
			msgId = CONFIRM_TRANSFER_TASK_MSG_ID;
		} else {
			msgId = CONFIRM_NOTPASSED_TASK_MSG_ID;
		}
		return msgId;
	}

	/**
	 * 完成拒签确认工作流的业务处理
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 * @param smParameter
	 *            SmParameter 发送短信的传递参数
	 * @return ProMockPo 工作流实体
	 */
	private ProMockPo doWorkflow(WorkOrderRefuseConfirm workOrderRefuseConfirm,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setUserId(workOrderRefuseConfirm.getRefuseConfirmUserId());
		taskPi.setTaskId(workOrderRefuseConfirm.getWorkflowTaskId());
		taskPi.setTransition(workOrderRefuseConfirm.getRefuseConfirmResult());
		taskPi.setComment(workOrderRefuseConfirm.getRefuseConfirmRemark());
		String userName = super.getUserName(workOrderRefuseConfirm
				.getRefuseConfirmUserId());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setUserName(userName);
		}
		taskPi.setDealUsers(workOrderRefuseConfirm.getTransferApprover());
		userName = super.getUserName(workOrderRefuseConfirm
				.getTransferApprover());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setDealUsersName(userName);
		}
		return super.workOrderWorkflowService.doTask(taskPi, smParameter);
	}
}
