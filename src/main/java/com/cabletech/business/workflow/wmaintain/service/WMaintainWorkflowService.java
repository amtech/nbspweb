package com.cabletech.business.workflow.wmaintain.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 隐患工作流业务处理
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加initMap()方法中的内容和工作流任务、事件流常量数据
 * 
 */
@Service
public class WMaintainWorkflowService extends AbstractWorkflowService {
	// 隐患工作流对应的业务操作服务KEY
	public static final String WMAINTAIN_SERVICE_INST_NAME = "WMaintainWorkflowEntityService";
	// 隐患工作流流程定义文件的id
	public static final String WMAINTAIN_WORKFLOW_NAME = "hidden";
	// 隐患工作流流程定义文件的中文流程说明
	public static final String WMAINTAIN_WORKFLOW_COMMENT = "隐患管理";
	// 工作流任务常量数据
	// 工作流文件中“审核作业计划”任务名称
	private static final String APPROVE_PLAN_TASK_NAME = "审核作业计划";
	// 工作流文件中“提交作业报告”任务名称
	private static final String CREATE_REPORT_TASK_NAME = "提交作业报告";
	// 工作流文件中“审核作业报告”任务名称
	private static final String APPROVE_REPORT_TASK_NAME = "审核归档";
	// 工作流事件流常量数据
	// 工作流文件中“计划审核通过”事件流名称
	private static final String APPROVE_PLAN_PASS_TRANSTION_NAME = "计划审核通过";
	// 工作流文件中“计划审核不通过”事件流名称
	private static final String APPROVE_PLAN_REFUSE_TRANSTION_NAME = "计划审核不通过";
	// 工作流文件中“提交作业报告”事件流名称
	private static final String CREATE_REPORT_PASS_TRANSTION_NAME = "提交作业报告";
	// 工作流文件中“报告审核通过”事件流名称
	private static final String APPROVE_REPORT_PASS_TRANSTION_NAME = "报告审核通过";
	// 工作流文件中“报告审核不通过”事件流名称
	private static final String APPROVE_REPORT_REJECT_TRANSTION_NAME = "报告审核不通过";

	/**
	 * 获取隐患工作流流程定义文件的id
	 * 
	 * @return String 隐患工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return WMAINTAIN_WORKFLOW_NAME;
	}

	/**
	 * 获取隐患工作流对应的业务操作服务KEY
	 * 
	 * @return String 隐患工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return WMAINTAIN_SERVICE_INST_NAME;
	}

	/**
	 * 初始化隐患流程历史说明记录
	 * 
	 */
	@Override
	public void initMap() {
		// TODO Auto-generated method stub
		processResultMap.put(APPROVE_PLAN_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				APPROVE_PLAN_PASS_TRANSTION_NAME);
		processResultMap.put(APPROVE_PLAN_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				APPROVE_PLAN_REFUSE_TRANSTION_NAME);
		processResultMap.put(CREATE_REPORT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				CREATE_REPORT_PASS_TRANSTION_NAME);
		processResultMap.put(APPROVE_REPORT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				APPROVE_REPORT_PASS_TRANSTION_NAME);
		processResultMap.put(APPROVE_REPORT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				APPROVE_REPORT_REJECT_TRANSTION_NAME);
	}
}
