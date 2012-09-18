package com.cabletech.business.wplan.patrolitem.model;

import com.cabletech.common.base.*;

/**
 * 巡检项
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 */
public class PatrolItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 巡检项目启用状态
	public static final String ITEM_START_USING_STATE = "1";
	// 巡检项目作废状态
	public static final String ITEM_SCRAP_STATE = "2";
	private String id;// ID VARCHAR2(12) N 系统编号
	private String itemName;// ITEM_NAME VARCHAR2(100) Y 项目名称
	private String businessType;// BUSINESS_TYPE VARCHAR2(20) Y 专业类型
	private String partenId;// PARTEN_ID VARCHAR2(12) Y 上级编号
	private String regionId;// REMARK VARCHAR2(1000) Y 区域
	private String itemRemark;// ITEM_REMARK VARCHAR2(1000) Y 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getPartenId() {
		return partenId;
	}

	public void setPartenId(String partenId) {
		this.partenId = partenId;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
}