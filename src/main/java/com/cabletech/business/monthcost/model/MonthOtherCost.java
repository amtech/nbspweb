package com.cabletech.business.monthcost.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 月度考核
 * 
 * @author Administrator
 */
public class MonthOtherCost extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;
	private String regionId;
	private String months;
	private String contractorId;
	private String typet;
	private String shouldMoney;
	private String factMoney;
	private String remark;
	private String writePersonId;
	private String writeDate;

	private String regionName;
	private String contractorName;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getTypet() {
		return typet;
	}

	public void setTypet(String typet) {
		this.typet = typet;
	}

	public String getShouldMoney() {
		return shouldMoney;
	}

	public void setShouldMoney(String shouldMoney) {
		this.shouldMoney = shouldMoney;
	}

	public String getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(String factMoney) {
		this.factMoney = factMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWritePersonId() {
		return writePersonId;
	}

	public void setWritePersonId(String writePersonId) {
		this.writePersonId = writePersonId;
	}

}
