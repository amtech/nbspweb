package com.cabletech.business.workflow.fault.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.DateUtil;

/**
 * 故障回单审核结果实体
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2012-02-24 添加“转审人”变量
 * 
 */
public class FaultAudit extends BaseEntity {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障回单审核结果编号
	private String id;
	// 故障任务单编号
	private String taskId;
	// 是否审核通过
	private String isAuditing;
	// 备注
	private String remark;
	// 审核时间
	private Date auditingTime;
	// 审核时间字符串
	private String auditingTimeString;
	// 审核人
	private String auditor;
	// 故障工作流任务编号
	private String workflowTaskId;
	// 转审人
	private String transferApprover;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getIsAuditing() {
		return isAuditing;
	}

	public void setIsAuditing(String isAuditing) {
		this.isAuditing = isAuditing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAuditingTime() {
		return auditingTime;
	}

	/**
	 * 
	 * @param auditingTime
	 *            Date
	 */
	public void setAuditingTime(Date auditingTime) {
		this.auditingTime = auditingTime;
		this.auditingTimeString = DateUtil.DateToString(auditingTime,
				"yyyy-MM-dd HH:mm:ss");
	}

	public String getAuditingTimeString() {
		return auditingTimeString;
	}

	/**
	 * 
	 * @param auditingTimeString
	 *            String
	 */
	public void setAuditingTimeString(String auditingTimeString) {
		this.auditingTimeString = auditingTimeString;
		this.auditingTime = DateUtil.parseDate(auditingTimeString,
				"yyyy-MM-dd HH:mm:ss");
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(String workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}

	public String getTransferApprover() {
		return transferApprover;
	}

	public void setTransferApprover(String transferApprover) {
		this.transferApprover = transferApprover;
	}
}
