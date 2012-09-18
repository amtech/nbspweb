package com.cabletech.business.monthcost.model;

/**
 * 導入報表時候使用 不持久
 * 
 * @author Administrator
 * 
 */
public class MonthTimesCostTemp {
	private String regionId;
	private String months;
	private String specialty;
	private String contractorId;
	private String unitPrice;
	private String numbers;
	private String shouldMoney;
	private String factMoney;
	private String writePersonId;
	private String writeDate;
	private String typet;
	private String regionName;
	private String contractorName;
	private boolean flag = true; 
	private String errorMsg = ""; 

	public String getRegionName() {
		return regionName;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public String getWritePersonId() {
		return writePersonId;
	}

	public void setWritePersonId(String writePersonId) {
		this.writePersonId = writePersonId;
	}

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

}
