package com.cabletech.business.workflow.workorder.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.cabletech.common.base.BaseEntity;

/**
 * 通用工单回复信息实体
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-10 添加“工单回单上传附件”数据变量
 * 
 */
public class WorkOrderReply extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 工单ID
	private String taskId;
	// 分发流转ID
	private String transferId;
	// 回单说明
	private String remark;
	// 回单部门
	private String deptId;
	// 回单人
	private String creater;
	// 回单时间
	private Date createDate;
	// 工作流当前任务编号
	private String workflowTaskId;
	// 上传附件列表
	private List<FileItem> fileList;
	// 回单验证人编号（表单数据，不存储数据库）
	private String replyCheckUserId;
	// 回单是否提交（表单数据，不存储数据库）
	private String isSubmited;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(String taskId) {
		this.workflowTaskId = taskId;
	}

	public List<FileItem> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileItem> fileList) {
		this.fileList = fileList;
	}

	public String getReplyCheckUserId() {
		return replyCheckUserId;
	}

	public void setReplyCheckUserId(String replyCheckUserId) {
		this.replyCheckUserId = replyCheckUserId;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}
}
