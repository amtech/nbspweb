package com.cabletech.business.wplan.template.model;

import com.cabletech.common.base.*;

/**
 * 计划模板
 * 
 * @author wangjie
 */
public class WplanTemplate extends BaseEntity {
	private String id;// ID VARCHAR2(12) N 系统编号
	private String businessType;// BUSINESS_TYPE VARCHAR2(20) Y 专业类型
	private String templateName;// TEMPLATE_NAME VARCHAR2(30) Y 模板名称
	private String remark;// REMARK VARCHAR2(1000) Y 备注
	private String state;
    /**
     * 区域ID
     */
    private String regionid;
	// 非持久化数据
	private String items;
	private String businessTypeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	/**
	 * @return the regionid
	 */
	public String getRegionid() {
		return regionid;
	}

	/**
	 * @param regionid the regionid to set
	 */
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
}
