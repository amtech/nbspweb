package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainApprovePlanService;
import com.cabletech.common.base.SysConstant;

/**
 * 审核维修作业计划服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * 
 */
@Service
@Transactional
public class WMaintainApprovePlanServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainApprovePlanService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wMaintainPlanDao;

	/**
	 * 审核维修作业计划
	 * 
	 * @param planApprove
	 *            WMaintainPlan 审核维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void approvePlan(WMaintainPlan planApprove, UserInfo userInfo) {
		// TODO Auto-generated method stub
		WMaintainPlan plan = writePlanApproveInfo(planApprove, userInfo);
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
	 * 补充审核维修作业计划信息数据
	 * 
	 * @param planApprove
	 *            WMaintainPlan 审核维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return WMaintainPlan 审核维修作业计划信息数据
	 */
	private WMaintainPlan writePlanApproveInfo(WMaintainPlan planApprove,
			UserInfo userInfo) {
		WMaintainPlan plan = wMaintainPlanDao.get(planApprove.getId());
		plan.setApproveResult(planApprove.getApproveResult());
		plan.setApproveRemark(planApprove.getApproveRemark());
		plan.setTransferApproverId(planApprove.getTransferApproverId());
		plan.setWorkflowTaskId(planApprove.getWorkflowTaskId());
		plan.setWorkflowTransition(planApprove.getApproveResult());
		plan.setWorkflowComment(planApprove.getApproveRemark());
		plan.setCurrentProcessUserId(userInfo.getPersonId());
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(plan
				.getApproveResult())) {
			plan.setNextProcessUserId(planApprove.getTransferApproverId());
		} else {
			plan.setNextProcessUserId(plan.getCreater());
		}
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(plan.getApproveResult())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_PLAN_PASSED_STATE);
		} else if (SysConstant.REJECT_WORKFLOW_TRANSITION.equals(plan
				.getApproveResult())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_PLAN_NOTPASSED_STATE);
		}
		return plan;
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @param plan
	 *            WMaintainPlan 审核维修作业计划信息
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
			smParameter.setXmlMessageId(APPROVE_PASSED_PLAN_MSG_ID);
		} else if (SysConstant.REJECT_WORKFLOW_TRANSITION.equals(plan
				.getApproveResult())) {
			smParameter.setXmlMessageId(APPROVE_NOTPASSED_PLAN_MSG_ID);
		} else {
			smParameter.setXmlMessageId(APPROVE_TRANSFER_PLAN_MSG_ID);
		}
		return smParameter;
	}
}
