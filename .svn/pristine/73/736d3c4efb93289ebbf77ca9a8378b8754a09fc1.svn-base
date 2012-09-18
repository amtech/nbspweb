package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainApproveReportService;
import com.cabletech.common.base.SysConstant;

/**
 * 审核维修作业计划维护报告服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * 
 */
@Service
@Transactional
public class WMaintainApproveReportServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainApproveReportService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wMaintainPlanDao;

	/**
	 * 审核维修作业计划维护报告
	 * 
	 * @param planRecordApprove
	 *            WMaintainPlan 审核维修作业计划维护报告表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void approveRecord(WMaintainPlan planRecordApprove, UserInfo userInfo) {
		// TODO Auto-generated method stub
		WMaintainPlan plan = writeRecordApproveInfo(planRecordApprove, userInfo);
		wMaintainPlanDao.save(plan);
		SmParameter smParameter = getSmParameter(plan);
		super.doWorkflow(plan, smParameter);
	}

	/**
	 * 获取维修作业计划处理操作Dao
	 * 
	 * @return WMaintainBaseDao<T, PK> 维修作业计划处理操作Dao
	 */
	@Override
	protected WMaintainBaseDao<WMaintainPlan, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wMaintainPlanDao;
	}

	/**
	 * 补充审核维修作业计划维护报告信息
	 * 
	 * @param planRecordApprove
	 *            WMaintainPlan 审核维修作业计划维护报告表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return WMaintainPlan 审核维修作业计划维护报告信息数据
	 */
	private WMaintainPlan writeRecordApproveInfo(
			WMaintainPlan planRecordApprove, UserInfo userInfo) {
		WMaintainPlan plan = wMaintainPlanDao.get(planRecordApprove.getId());
		plan.setApproveResult(planRecordApprove.getApproveResult());
		plan.setApproveRemark(planRecordApprove.getApproveRemark());
		plan.setTransferApproverId(planRecordApprove.getTransferApproverId());
		plan.setWorkflowTransition(planRecordApprove.getApproveResult());
		plan.setWorkflowComment(planRecordApprove.getApproveRemark());
		plan.setWorkflowTaskId(planRecordApprove.getWorkflowTaskId());
		plan.setCurrentProcessUserId(userInfo.getPersonId());
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(plan
				.getApproveResult())) {
			plan.setNextProcessUserId(planRecordApprove.getTransferApproverId());
		} else {
			plan.setNextProcessUserId(plan.getCreater());
		}
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(plan.getApproveResult())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_END_STATE);
		} else if (SysConstant.REJECT_WORKFLOW_TRANSITION.equals(plan
				.getApproveResult())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_RECORD_NOTPASSED_STATE);
		}
		return plan;
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @param plan
	 *            WMaintainPlan 审核维修作业计划维护报告信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(WMaintainPlan plan) {
		SmParameter smParameter = new SmParameter();
		String simId = super.getUserPhone(plan.getNextProcessUserId());
		String[] contentParameters = new String[] { plan.getPlanName() };
		smParameter.setSimId(simId);
		smParameter.setContentParameters(contentParameters);
		smParameter.setXmlFileId(WMAINTAIN_XML_FILE_ID);
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(plan.getApproveResult())) {
			smParameter.setXmlMessageId(APPROVE_PASSED_REPORT_MSG_ID);
		} else if (SysConstant.REJECT_WORKFLOW_TRANSITION.equals(plan
				.getApproveResult())) {
			smParameter.setXmlMessageId(APPROVE_NOTPASSED_REPORT_MSG_ID);
		} else {
			smParameter.setXmlMessageId(APPROVE_TRANSFER_REPORT_MSG_ID);
		}
		return smParameter;
	}
}
