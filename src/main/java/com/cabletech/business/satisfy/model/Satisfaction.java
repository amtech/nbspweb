package com.cabletech.business.satisfy.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 客户满意度评价查询条件实体
 * 
 * @author 杨隽 2012-04-21 创建
 * 
 */
public class Satisfaction extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 工作类型
	private String taskType;
	// 评价级别
	private String satisfaction;
	// 回复开始时间
	private String replyTimeStart;
	// 回复结束时间
	private String replyTimeEnd;
	// 区域编号
	private String regionId;
	// 组织编号
	private String orgId;

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getReplyTimeStart() {
		return replyTimeStart;
	}

	public void setReplyTimeStart(String replyTimeStart) {
		this.replyTimeStart = replyTimeStart;
	}

	public String getReplyTimeEnd() {
		return replyTimeEnd;
	}

	public void setReplyTimeEnd(String replyTimeEnd) {
		this.replyTimeEnd = replyTimeEnd;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
