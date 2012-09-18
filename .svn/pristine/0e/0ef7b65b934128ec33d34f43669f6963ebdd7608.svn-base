package com.cabletech.business.contactletter.service;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class ContactLetterAuditService extends AbstractWorkflowService {

	// 工作流文件中“审核联系函”任务名称
	public static final String AUDIT_TASK_NAME = "审核";
	// 工作流文件中“审核通过”事件流名称
	public static final String PASS_WORKFLOW_TRANSTIONNAME = "通过";
	// 工作流文件中“审核不通过”事件流名称
	public static final String REJECT_WORKFLOW_TRANSITIONNAME = "驳回";
	// 工作流定义
	public static final String WORKFLOW_NAME = "contactletter";
	// 联系函实体业务服务
	public static final String SERVICE_INST_NAME = "contactLetterWorkflowEntityService";
	public static final String WORKFLOW_COMMENT = "业务联系函";

	@Override
	public void initMap() {
		processResultMap.put(AUDIT_TASK_NAME
				+ SysConstant.PASS_WORKFLOW_TRANSTION,
				PASS_WORKFLOW_TRANSTIONNAME);
		processResultMap.put(AUDIT_TASK_NAME
				+ SysConstant.REJECT_WORKFLOW_TRANSITION,
				REJECT_WORKFLOW_TRANSITIONNAME);
	}

	@Override
	public String getProcessDefName() {
		return WORKFLOW_NAME;
	}

	@Override
	public String getBusinessManagerKey() {
		return SERVICE_INST_NAME;
	}

}
