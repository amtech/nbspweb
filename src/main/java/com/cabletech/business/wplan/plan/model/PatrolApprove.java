package com.cabletech.business.wplan.plan.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 名称：巡检计划审批Bean
 * 功能：巡检计划审批Bean
 * 作者：zhaobi
 * 版权：Copyright (c) 2004-2011 北京鑫干线
 * 日期：2011-7-21
 *
 * 变更历史：
 * 日期		作者		变更
 *  ------------------------------------------------------------------------------------------------
 */
public class PatrolApprove extends BaseEntity    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//计划ID
	private String planid;
	//审批结果
	private String result;
	//审批意见
	private String attitude;
	//用户名称
	private String username;
	//审批时间
	private String approvetime;
	//审批人ID
	private String approver;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAttitude() {
		return attitude;
	}
	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}