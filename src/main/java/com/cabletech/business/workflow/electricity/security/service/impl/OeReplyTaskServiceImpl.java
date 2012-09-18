package com.cabletech.business.workflow.electricity.security.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeReplyTask;
import com.cabletech.business.workflow.electricity.security.service.OeReplyTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警回单业务操作接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
@Transactional
public class OeReplyTaskServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeDispatchTask, String> implements
		OeReplyTaskService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;

	/**
	 * 断电告警回单
	 * 
	 * @param oeReplyTask
	 *            OeReplyTask 输入的断电告警派单回复信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(OeReplyTask oeReplyTask, UserInfo userInfo) {
		String dispatchId = oeReplyTask.getDispatchId();
		OeDispatchTask oeDispatchTask = oeDispatchTaskDao.get(dispatchId);
		oeDispatchTask.setReplyContent(oeReplyTask.getReplyContent());
		oeDispatchTask.setReplyDate(new Date());
		oeDispatchTask.setReplyPerson(userInfo.getPersonId());
		if (SysConstant.FORM_IS_SUBMITED.equals(oeReplyTask.getIsSubmited())) {
			oeDispatchTask.setState(OeDispatchTask.WAIT_APPROVED_STATE);
		}
		oeDispatchTask.setCurrentProcessUserId(userInfo.getPersonId());
		oeDispatchTask.setNextProcessUserId(oeReplyTask.getApproverId());
		oeDispatchTask.setWorkflowTaskId(oeReplyTask.getWorkflowTaskId());
		oeDispatchTask
				.setWorkflowTransition(SysConstant.PASS_WORKFLOW_TRANSTION);
		oeDispatchTask.setWorkflowComment(oeReplyTask.getReplyContent());
		oeDispatchTaskDao.save(oeDispatchTask);
		if (SysConstant.FORM_IS_SUBMITED.equals(oeReplyTask.getIsSubmited())) {
			SmParameter smParameter = getSmParameter(oeDispatchTask);
			super.doWorkflow(oeDispatchTask, smParameter);
		}
	}

	/**
	 * 获取回单短信发送参数
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单回复信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(OeDispatchTask oeDispatchTask) {
		String simId = super
				.getUserPhone(oeDispatchTask.getNextProcessUserId());
		String[] contentParameters = new String[] { oeDispatchTask.getTitle() };
		SmParameter parameter = SmParameter.getInstance(
				ELECTRICTITY_XML_FILE_ID, REPLY_MSG_ID, simId,
				contentParameters);
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
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected ElectricitySecurityBaseDao<OeDispatchTask, String> getElectricitySecurityBaseDao() {
		return oeDispatchTaskDao;
	}
}
