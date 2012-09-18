package com.cabletech.business.assess.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 年度考核工作流业务服务
 * 
 * @author 杨隽 2012-08-06 创建
 * 
 */
@Service
public class AssessYearAppraiseWorkflowService extends AbstractWorkflowService {
	// 工作流文件中“省公司运维主任审核”任务名称
	private static final String ONE_AUDIT_TASK_NAME = "省运维主任审核";
	// 工作流文件中“省公司分管领导审核”任务名称
	private static final String TWO_AUDIT_TASK_NAME = "省公司分管领导审核";
	// 工作流文件中“代维确认考核结果”任务名称
	private static final String CONFIRM_REPLY_TASK_NAME = "年考核结果确认";
	// 工作流文件中“审核通过”事件流名称
	private static final String REFUSE_AUDIT_PASS_TRANSTION_NAME = "审核通过";
	// 工作流文件中“审核不通过”事件流名称
	private static final String REFUSE_AUDIT_REJECT_TRANSTION_NAME = "审核不通过";
	// 工作流文件中“代维确认考核结果通过”事件流名称
	private static final String CHECK_REPLY_PASS_TRANSTION_NAME = "确认通过";
	// 工作流文件中“代维确认考核结果问题反馈”事件流名称
	private static final String CHECK_REPLY_REJECT_TRANSTION_NAME = "问题反馈";
	// 年度考核实体业务服务
	public static final String ASSESS_YEAR_SERVICE_INST_NAME = "";
	// 年度考核工作流定义
	public static final String ASSESS_YEAR_WORKFLOW_NAME = "contractoryearappraise";
	// 年度考核工作流定义名称
	public static final String ASSESS_YEAR_WORKFLOW_COMMENT = "年度考核";

	@Override
	public void initMap() {
		processResultMap.put(ONE_AUDIT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				REFUSE_AUDIT_PASS_TRANSTION_NAME);
		processResultMap.put(ONE_AUDIT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				REFUSE_AUDIT_REJECT_TRANSTION_NAME);
		processResultMap.put(TWO_AUDIT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				REFUSE_AUDIT_PASS_TRANSTION_NAME);
		processResultMap.put(TWO_AUDIT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				REFUSE_AUDIT_REJECT_TRANSTION_NAME);
		processResultMap.put(CONFIRM_REPLY_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				CHECK_REPLY_PASS_TRANSTION_NAME);
		processResultMap.put(CONFIRM_REPLY_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				CHECK_REPLY_REJECT_TRANSTION_NAME);
	}

	@Override
	public String getProcessDefName() {
		return ASSESS_YEAR_WORKFLOW_NAME;
	}

	@Override
	public String getBusinessManagerKey() {
		return ASSESS_YEAR_SERVICE_INST_NAME;
	}
}
