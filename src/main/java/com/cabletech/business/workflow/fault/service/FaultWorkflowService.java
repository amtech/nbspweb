package com.cabletech.business.workflow.fault.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 故障派单工作流业务处理
 * 
 * @author 杨隽 2011-10-31 创建
 * @author 杨隽 2011-11-07 修改 提取工作流业务的相似代码
 * @author 杨隽 2011-11-29 修改setTaskPi()方法（将获取待办任务的巡检组编号改为代维单位编号）
 * @author 杨隽 2011-12-02 实现getProcessDefineName()方法
 * @author 杨隽 2012-02-08 去除getWaitHandleList()方法
 * @author 杨隽 2012-02-15 实现initMap()方法
 * 
 */
@Service
public class FaultWorkflowService extends AbstractWorkflowService {
	// 工作流文件中“审核故障回单”任务名称
	public static final String AUDIT_TASK_NAME = "审核故障回单";
	// 工作流文件中“审核故障回单通过”事件流名称
	public static final String PASS_WORKFLOW_TRANSTIONNAME = "审核通过";
	// 工作流文件中“审核故障回单不通过”事件流名称
	public static final String REJECT_WORKFLOW_TRANSITIONNAME = "审核不通过";
	// 故障派单实体业务服务
	public static final String FAULT_DISPATCH_SERVICE_INST_NAME = "faultWorkflowEntityService";
	// 故障派单工作流定义
	public static final String FAULT_WORKFLOW_NAME = "fault";
	// 故障派单工作流定义名称
	public static final String FAULT_WORKFLOW_COMMENT = "故障管理";

	/**
	 * 获取故障工作流流程定义文件的id
	 * 
	 * @return String 故障工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return FAULT_WORKFLOW_NAME;
	}

	/**
	 * 获取故障工作流对应的业务操作服务KEY
	 * 
	 * @return String 故障工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return FAULT_DISPATCH_SERVICE_INST_NAME;
	}

	/**
	 * 初始化故障流程历史说明记录
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
