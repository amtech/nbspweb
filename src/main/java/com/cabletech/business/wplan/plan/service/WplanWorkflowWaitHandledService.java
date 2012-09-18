package com.cabletech.business.wplan.plan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;

/**
 * 无线计划工作流待办列表数量业务处理
 * 
 * @author 杨隽 2012-04-18 创建
 * 
 */
@Service
public class WplanWorkflowWaitHandledService extends
		AbstractWorkflowWaitHandledService {
	// 无线计划工作流业务处理
	@Resource(name = "patrolWorkflowService")
	private PatrolWorkflowService patrolWorkflowService;

	/**
	 * 获取工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return PatrolWorkflowService.PATROL_WORKFLOW_NAME;
	}

	/**
	 * 获取工作流流程定义文件的中文流程说明
	 */
	@Override
	public String getProcessDefineName() {
		// TODO Auto-generated method stub
		return PatrolWorkflowService.PATROL_WORKFLOW_COMMENT;
	}

	/**
	 * 获取工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return PatrolWorkflowService.PATROL_PLAN_SERVICE_INST_NAME;
	}

	@Override
	public void setTaskPi(UserInfo userInfo, ProMockPo taskPi) {
		// TODO Auto-generated method stub
		String userId = userInfo.getPersonId();
		taskPi.setDealUsers(userId);
	}

	@Override
	public AbstractWorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return patrolWorkflowService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.workflow.common.service.
	 * AbstractWorkflowWaitHandledService#setBusinessTypeList(java.util.List)
	 */
	@Override
	public void setBusinessTypeList(List<Map<String, Object>> businessTypeList) {
		// TODO Auto-generated method stub
		super.businessTypeList = businessTypeList;
	}
}
