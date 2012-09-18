package com.cabletech.business.monthcost.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 
 * @author Administrator
 *
 */
public class MonthCheckCost extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String regionId;
	private String months;
	private String specialty;
	private String contractorId;
	private String unitPrice;
	private String numbers;
	private String shouldMoney;
	private String checkFraction;
	private String subtractMoney;
	private String factMoney;
	private String writePersonId;
	private String writeDate;
	
	 
	
	private String regionName;
	private String contractorName;

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

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getShouldMoney() {
		return shouldMoney;
	}

	public void setShouldMoney(String shouldMoney) {
		this.shouldMoney = shouldMoney;
	}

	public String getCheckFraction() {
		return checkFraction;
	}

	public void setCheckFraction(String checkFraction) {
		this.checkFraction = checkFraction;
	}

	public String getSubtractMoney() {
		return subtractMoney;
	}

	public void setSubtractMoney(String subtractMoney) {
		this.subtractMoney = subtractMoney;
	}

	public String getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(String factMoney) {
		this.factMoney = factMoney;
	}

	public String getWritePersonId() {
		return writePersonId;
	}

	public void setWritePersonId(String writePersonId) {
		this.writePersonId = writePersonId;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writedate) {
		this.writeDate = writedate;
	}

}
