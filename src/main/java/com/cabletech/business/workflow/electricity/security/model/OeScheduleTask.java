package com.cabletech.business.workflow.electricity.security.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 供电派单调度实体
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-10 添加原来维护点编号属性
 * 
 */
public class OeScheduleTask extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电保障派单编号
	private String dispatchId;
	// 供电保障业务工作流编号
	private String workflowTaskId;
	// 调度油机编号
	private String oilEngineId;
	// 原来维护点编号
	private String preStationId;
	// 调度说明
	private String dispatchRemark;

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(String workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}

	public String getOilEngineId() {
		return oilEngineId;
	}

	public void setOilEngineId(String oilEngineId) {
		this.oilEngineId = oilEngineId;
	}

	public String getPreStationId() {
		return preStationId;
	}

	public void setPreStationId(String preStationId) {
		this.preStationId = preStationId;
	}

	public String getDispatchRemark() {
		return dispatchRemark;
	}

	public void setDispatchRemark(String dispatchRemark) {
		this.dispatchRemark = dispatchRemark;
	}
}
