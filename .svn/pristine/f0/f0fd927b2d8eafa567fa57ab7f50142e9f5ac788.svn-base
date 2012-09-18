package com.cabletech.business.workflow.electricity.security.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警派单工作流业务处理
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
public class ElectricitySecurityWorkflowService extends AbstractWorkflowService {
	// 工作流文件中“审核回单”任务名称
	public static final String AUDIT_TASK_NAME = "审核";
	// 工作流文件中“审核回单通过”事件流名称
	public static final String PASS_WORKFLOW_TRANSTIONNAME = "审核通过";
	// 工作流文件中“审核回单不通过”事件流名称
	public static final String REJECT_WORKFLOW_TRANSITIONNAME = "审核不通过";
	// 断电告警派单实体业务服务
	public static final String OE_DISPATCH_SERVICE_INST_NAME = "electricitySecurityWorkflowEntityService";
	// 断电告警派单工作流定义
	public static final String OE_WORKFLOW_NAME = "powersupply";
	// 断电告警派单工作流定义名称
	public static final String OE_WORKFLOW_COMMENT = "供电保障";

	/**
	 * 获取断电告警工作流流程定义文件的id
	 * 
	 * @return String 断电告警工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return OE_WORKFLOW_NAME;
	}

	/**
	 * 获取断电告警工作流对应的业务操作服务KEY
	 * 
	 * @return String 断电告警工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return OE_DISPATCH_SERVICE_INST_NAME;
	}

	/**
	 * 初始化断电告警流程历史说明记录
	 * 
	 */
	@Override
	public void initMap() {
		// TODO Auto-generated method stub
		processResultMap.put(AUDIT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				PASS_WORKFLOW_TRANSTIONNAME);
		processResultMap.put(AUDIT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				REJECT_WORKFLOW_TRANSITIONNAME);
	}
}
