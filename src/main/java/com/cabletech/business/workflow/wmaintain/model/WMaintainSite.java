package com.cabletech.business.workflow.wmaintain.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 计划维护站点实体
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-19 添加计划维修站点的维护状态常量
 * 
 */
public class WMaintainSite extends BaseEntity {
	// 计划维修站点“已维护”的维护状态
	public static final Object MAINTAINED_STATE = "已处理";
	// 计划维修站点“无法维护”的维护状态
	public static final Object CANNOT_MAINTAIN_STATE = "无法处理";
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 系统编号
	private String id;
	// 维修计划编号
	private String planId;
	// 站点编号
	private String rsId;
	// 站点类型
	private String rsType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getRsType() {
		return rsType;
	}

	public void setRsType(String rsType) {
		this.rsType = rsType;
	}
}
