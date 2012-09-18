package com.cabletech.business.workflow.fault.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultAudit;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.service.FaultAuditService;
import com.cabletech.common.base.SysConstant;

/**
 * 故障回单审核业务操作
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2011-10-31 添加“获取故障回单审核历史”方法并修改继承的基类
 * @author 杨隽 2011-11-25 修改“审核”方法（审核通过时将故障告警单状态置为“完成”状态）
 * @author 杨隽 2012-02-08 修改getBaseDao()方法实现为getFaultBaseDao ()方法实现
 * @author 杨隽 2012-02-15 删除getAuditHistoryList()方法
 * @author 杨隽 2012-02-24 修改doWorkflow()方法以支持转审
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改audit方法和doWorkflow方法）
 * 
 */
@Service
public class FaultAuditServiceImpl extends
		FaultBaseServiceImpl<FaultAudit, String> implements FaultAuditService {
	// 故障告警单Dao操作
	@Resource(name = "faultAlertDao")
	private FaultBaseDao<FaultAlert, String> faultAlertDao;
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;
	// 故障回单审核Dao操作
	@Resource(name = "faultAuditDao")
	private FaultBaseDao<FaultAudit, String> faultAuditDao;

	@Override
	protected FaultBaseDao<FaultAudit, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultAuditDao;
	}

	/**
	 * 故障回单审核
	 * 
	 * @param faultAudit
	 *            FaultAudit 故障回单审核输入信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Transactional
	public void audit(FaultAudit faultAudit, UserInfo userInfo) {
		// TODO Auto-generated method stub
		FaultDispatch faultDispatch = faultDispatchDao.get(faultAudit
				.getTaskId());
		faultAudit.setAuditor(userInfo.getPersonId());
		faultAudit.setAuditingTime(new Date());
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(faultAudit
				.getIsAuditing())) {
			faultDispatch.setState(FaultDispatch.END_STATE);
		} else if (SysConstant.REJECT_WORKFLOW_TRANSITION.equals(faultAudit
				.getIsAuditing())) {
			faultDispatch.setState(FaultDispatch.PROCESSING_STATE);
		}
		faultDispatchDao.save(faultDispatch);
		SmParameter parameter = getSmParameter(faultAudit, faultDispatch);
		// 执行工作流业务
		ProMockPo pro = doWorkflow(faultAudit, parameter);
		if (pro.isFlowOver()) {
			setFaultAlertFinishedState(faultDispatch);
		}
	}

	/**
	 * 执行故障回单审核工作流
	 * 
	 * @param faultAudit
	 *            故障回单审核信息
	 * @param smParameter
	 *            SmParameter 短信发送传递参数
	 * @return ProMockPo 故障回单审核工作流执行后的结果
	 */
	private ProMockPo doWorkflow(FaultAudit faultAudit, SmParameter smParameter) {
		// TODO Auto-generated method stub
		ProMockPo pro = new ProMockPo();
		String sendUserId = faultAudit.getAuditor();
		String processUserId = faultAudit.getTransferApprover();
		pro.setUserId(sendUserId);
		pro.setUserName(super.getUserName(sendUserId));
		pro.setTaskId(faultAudit.getWorkflowTaskId());
		pro.setDealUsers(processUserId);
		pro.setDealUsersName(super.getUserName(processUserId));
		pro.setTransition(faultAudit.getIsAuditing());
		pro.setComment(faultAudit.getRemark());
		return super.getFaultWorkflowManager().doTask(pro, smParameter);
	}

	/**
	 * 设置故障告警单的“故障完成”状态
	 * 
	 * @param faultDispatch
	 *            FaultDispatch 故障派单信息
	 */
	private void setFaultAlertFinishedState(FaultDispatch faultDispatch) {
		FaultAlert faultAlert = faultAlertDao.get(faultDispatch.getAlarmId());
		faultAlert.setState(FaultAlert.FINISHED_STATE);
		faultAlertDao.save(faultAlert);
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @param faultAudit
	 *            FaultAudit 故障审核结果实体
	 * @param faultDispatch
	 *            FaultDispatch 故障派单实体
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(FaultAudit faultAudit,
			FaultDispatch faultDispatch) {
		// TODO Auto-generated method stub
		String msgId = getMessageId(faultAudit);
		String simId = getSimId(faultAudit, faultDispatch);
		String alertId = faultDispatch.getAlarmId();
		FaultAlert faultAlert = faultAlertDao.get(alertId);
		String[] contentParameters = new String[] { faultAlert
				.getTroubleTitle() };
		SmParameter parameter = SmParameter.getInstance(FAULT_XML_FILE_ID,
				msgId, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(faultAlert.getBusinessType());
		parameter.setHandleLimit(faultDispatch.getDeadline());
		parameter.setWorkorderId(faultDispatch.getId());
		parameter.setWorkorderTitle(faultAlert.getTroubleTitle());
		parameter.setWorkorderType(SysConstant.FAULT_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 根据故障审核结果获取发送短信的信息编号
	 * 
	 * @param faultAudit
	 *            FaultAudit 故障审核结果
	 * @return String 发送短信的信息编号
	 */
	private String getMessageId(FaultAudit faultAudit) {
		String msgId = "";
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(faultAudit
				.getIsAuditing())) {
			msgId = APPROVE_PASSED_TROUBLE_MSG_ID;
		} else if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(faultAudit
				.getIsAuditing())) {
			msgId = APPROVE_TRANSFER_TROUBLE_MSG_ID;
		} else {
			msgId = APPROVE_NOTPASSED_TROUBLE_MSG_ID;
		}
		return msgId;
	}

	/**
	 * 根据故障审核结果和故障派单实体获取sim卡号
	 * 
	 * @param faultAudit
	 *            FaultAudit 故障审核结果
	 * @param faultDispatch
	 *            FaultDispatch 故障派单实体
	 * @return String sim卡号
	 */
	private String getSimId(FaultAudit faultAudit, FaultDispatch faultDispatch) {
		String simId = "";
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(faultAudit
				.getIsAuditing())) {
			simId = super.getUserPhone(faultAudit.getTransferApprover());
		} else {
			simId = super.getOrgTel(faultDispatch.getMaintenanceId());
		}
		return simId;
	}
}
