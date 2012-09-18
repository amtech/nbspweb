package com.cabletech.business.workflow.workorder.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 通用工单信息内容实体
 * 
 * @author 杨隽 2011-12-30 创建
 * 
 */
public class WorkCommonOrder extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 任务名称（同工单实体中的“工单标题”）
	private String taskName;
	// 任务要求
	private String taskRequest;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskRequest() {
		return taskRequest;
	}

	public void setTaskRequest(String taskRequest) {
		this.taskRequest = taskRequest;
	}
}
