package com.cabletech.business.workflow.common.condition;

import com.cabletech.business.base.condition.QueryParameter;

/**
 * 工单和故障及时率、超时单统计查询条件参数
 * 
 * @author 杨隽 2012-04-28 创建
 * 
 */
public class StatisticQueryParameter extends QueryParameter {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开始日期
	 */
	private String startTime;
	/**
	 * 结束日期
	 */
	private String endTime;
	/**
	 * 代维公司编号
	 */
	private String contractorId;
	/**
	 * 专业类型
	 */
	private String businessType;
	/**
	 * 工单类型
	 */
	private String oderType;

	public String getOderType() {
		return oderType;
	}

	public void setOderType(String oderType) {
		this.oderType = oderType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
