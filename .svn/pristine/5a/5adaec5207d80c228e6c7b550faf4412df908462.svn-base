package com.cabletech.business.workflow.workorder.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 通用工单分发信息实体
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加“工单状态”实体列
 * 
 */
public class WorkOrderTransfer extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 工单ID
	private String taskId;
	// 转派任务说明
	private String remark;
	// 源部门
	private String sourceDeptId;
	// 源责任人
	private String sourcePrincipal;
	// 目标部门
	private String targetDeptId;
	// 目标责任人
	private String targetPrincipal;
	// 转派分派时间
	private Date createDate;
	// 维护组
	private String groupId;
	// 工单状态（0：草稿，1：转派，2：签收，3：回单，4：验证审核，5：结束 a：退单，b：退单审核）
	private String taskState;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSourceDeptId() {
		return sourceDeptId;
	}

	public void setSourceDeptId(String sourceDeptId) {
		this.sourceDeptId = sourceDeptId;
	}

	public String getSourcePrincipal() {
		return sourcePrincipal;
	}

	public void setSourcePrincipal(String sourcePrincipal) {
		this.sourcePrincipal = sourcePrincipal;
	}

	public String getTargetDeptId() {
		return targetDeptId;
	}

	public void setTargetDeptId(String targetDeptId) {
		this.targetDeptId = targetDeptId;
	}

	public String getTargetPrincipal() {
		return targetPrincipal;
	}

	public void setTargetPrincipal(String targetPrincipal) {
		this.targetPrincipal = targetPrincipal;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
}
