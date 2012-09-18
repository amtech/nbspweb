package com.cabletech.business.wplan.plan.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 巡检计划工作流服务类
 * 
 * @author zhaobi
 * 
 */
@Service
public class PatrolWorkflowService extends AbstractWorkflowService {
	/**
	 * 巡检计划实体业务服务
	 */
	public static final String PATROL_PLAN_SERVICE_INST_NAME = "wplanWorkflowEntityService";

	/**
	 * 无线巡检计划工作流定义
	 */
	public static final String PATROL_WORKFLOW_NAME = "wplan";
	/**
	 * 无线巡检计划工作流名称
	 */
	public static final String PATROL_WORKFLOW_COMMENT = "巡检管理";

	/**
	 * 初始化流程历史说明记录
	 * 
	 * @throws Exception
	 */
	@Override
	public void initMap() {
		processResultMap.put("审核" + SysConstant.PASS_WORKFLOW_TRANSTION,
				SysConstant.PASS_WORKFLOW_TRANSTIONNAME);
		processResultMap.put("审核" + SysConstant.REJECT_WORKFLOW_TRANSITION,
				SysConstant.REJECT_WORKFLOW_TRANSITIONNAME);
	}

	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return PATROL_WORKFLOW_NAME;
	}

	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return PATROL_PLAN_SERVICE_INST_NAME;
	}

}
