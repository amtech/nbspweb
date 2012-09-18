package com.cabletech.business.workflow.workorder.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单工作流业务处理
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-31 修改工作流的继承方法
 * @author 杨隽 2012-02-15 提取initMap()方法中的文字为常量数据
 * 
 */
@Service
public class WorkOrderWorkflowService extends AbstractWorkflowService {
	// 工作流文件中“签收”任务名称
	private static final String SIGN_FOR_TASK_NAME = "签收";
	// 工作流文件中“退单审核”任务名称
	private static final String REFUSE_AUDIT_TASK_NAME = "退单审核";
	// 工作流文件中“验证回单”任务名称
	private static final String CHECK_REPLY_TASK_NAME = "验证回单";
	// 工作流文件中“签收”事件流名称
	private static final String SIGN_FOR_TRANSTION_NAME = "签收";
	// 工作流文件中“拒签”事件流名称
	private static final String REFUSE_TRANSTION_NAME = "拒签";
	// 工作流文件中“退单审核通过”事件流名称
	private static final String REFUSE_AUDIT_PASS_TRANSTION_NAME = "退单审核通过";
	// 工作流文件中“退单审核不通过”事件流名称
	private static final String REFUSE_AUDIT_REJECT_TRANSTION_NAME = "退单审核不通过";
	// 工作流文件中“验证通过”事件流名称
	private static final String CHECK_REPLY_PASS_TRANSTION_NAME = "验证通过";
	// 工作流文件中“验证不通过”事件流名称
	private static final String CHECK_REPLY_REJECT_TRANSTION_NAME = "验证不通过";
	// 通用工单工作流对应的业务操作服务KEY
	public static final String WORKORDER_SERVICE_INST_NAME = "workOrderWorkflowEntityService";
	// 通用工单工作流流程定义文件的id
	public static final String WORKORDER_WORKFLOW_NAME = "workorder";
	// 通用工单工作流流程定义文件的中文流程说明
	public static final String WORKORDER_WORKFLOW_COMMENT = "工单管理";

	/**
	 * 获取通用工单工作流流程定义文件的id
	 * 
	 * @return String 通用工单工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return WORKORDER_WORKFLOW_NAME;
	}

	/**
	 * 获取通用工单工作流对应的业务操作服务KEY
	 * 
	 * @return String 通用工单工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return WORKORDER_SERVICE_INST_NAME;
	}

	/**
	 * 初始化通用工单流程历史说明记录
	 * 
	 */
	@Override
	public void initMap() {
		// TODO Auto-generated method stub
		processResultMap.put(SIGN_FOR_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION, SIGN_FOR_TRANSTION_NAME);
		processResultMap
				.put(SIGN_FOR_TASK_NAME
						+ SysConstant.REFUSE_WORKFLOW_TRANSITION,
						REFUSE_TRANSTION_NAME);
		processResultMap.put(REFUSE_AUDIT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				REFUSE_AUDIT_PASS_TRANSTION_NAME);
		processResultMap.put(REFUSE_AUDIT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				REFUSE_AUDIT_REJECT_TRANSTION_NAME);
		processResultMap.put(CHECK_REPLY_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				CHECK_REPLY_PASS_TRANSTION_NAME);
		processResultMap.put(CHECK_REPLY_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				CHECK_REPLY_REJECT_TRANSTION_NAME);
	}
}
