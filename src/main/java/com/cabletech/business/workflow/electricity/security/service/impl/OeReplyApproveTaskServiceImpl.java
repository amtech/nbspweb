package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeReplyApproveTask;
import com.cabletech.business.workflow.electricity.security.service.OeReplyApproveTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警回单审核业务操作接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
@Transactional
public class OeReplyApproveTaskServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeDispatchTask, String> implements
		OeReplyApproveTaskService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;

	/**
	 * 断电告警回单审核
	 * 
	 * @param oeReplyApproveTask
	 *            OeReplyApproveTask 输入的断电告警派单回复审核信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(OeReplyApproveTask oeReplyApproveTask, UserInfo userInfo) {
		String dispatchId = oeReplyApproveTask.getDispatchId();
		OeDispatchTask oeDispatchTask = oeDispatchTaskDao.get(dispatchId);
		String state = getDispatchTaskState(oeReplyApproveTask
				.getApproveResult());
		oeDispatchTask.setApproveResult(oeReplyApproveTask.getApproveResult());
		oeDispatchTask.setOffStation(oeReplyApproveTask.getOffStation());
		oeDispatchTask.setState(state);
		oeDispatchTask.setCurrentProcessUserId(userInfo.getPersonId());
		oeDispatchTask.setNextProcessUserId(oeReplyApproveTask
				.getTransferApprover());
		oeDispatchTask
				.setWorkflowTaskId(oeReplyApproveTask.getWorkflowTaskId());
		oeDispatchTask.setWorkflowTransition(oeReplyApproveTask
				.getApproveResult());
		oeDispatchTask
				.setWorkflowComment(oeReplyApproveTask.getApproveRemark());
		oeDispatchTaskDao.save(oeDispatchTask);
		SmParameter smParameter = getSmParameter(oeDispatchTask);
		super.doWorkflow(oeDispatchTask, smParameter);
	}

	/**
	 * 获取回单审核短信发送参数
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单回复审核信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(OeDispatchTask oeDispatchTask) {
		String simId = super
				.getUserPhone(oeDispatchTask.getNextProcessUserId());
		String[] contentParameters = new String[] { oeDispatchTask.getTitle() };
		String msgId = APPROVE_PASSED_MSG_ID;
		String state = getDispatchTaskState(oeDispatchTask.getApproveResult());
		if (OeDispatchTask.PROCESSING_STATE.equals(state)) {
			msgId = APPROVE_NOTPASSED_MSG_ID;
		}
		if (OeDispatchTask.WAIT_APPROVED_STATE.equals(state)) {
			msgId = APPROVE_TRANSFER_MSG_ID;
		}
		SmParameter parameter = SmParameter.getInstance(
				ELECTRICTITY_XML_FILE_ID, msgId, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter
				.setBusinessType(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		parameter.setHandleLimit(oeDispatchTask.getHandleLimit());
		parameter.setHandlePersonId(oeDispatchTask.getNextProcessUserId());
		parameter.setWorkorderId(oeDispatchTask.getId());
		parameter.setWorkorderTitle(oeDispatchTask.getTitle());
		parameter.setWorkorderType(SysConstant.OE_DISPATCHTASK_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 根据回单审核结果确定供电保障派单的状态
	 * 
	 * @param approveResult
	 *            String 回单审核结果
	 * @return String 供电保障派单的状态
	 */
	private String getDispatchTaskState(String approveResult) {
		// TODO Auto-generated method stub
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(approveResult)) {
			return OeDispatchTask.END_STATE;
		}
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(approveResult)) {
			return OeDispatchTask.WAIT_APPROVED_STATE;
		}
		return OeDispatchTask.PROCESSING_STATE;
	}

	/**
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected ElectricitySecurityBaseDao<OeDispatchTask, String> getElectricitySecurityBaseDao() {
		return oeDispatchTaskDao;
	}
}
