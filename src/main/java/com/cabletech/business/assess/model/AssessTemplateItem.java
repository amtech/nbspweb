package com.cabletech.business.assess.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 模版-项目实体
 * 
 * @author 杨隽 2012-07-31 创建
 * 
 */
public class AssessTemplateItem extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 隶属模版表编号
	 */
	private String tableId;
	/**
	 * 父项目编号
	 */
	private String parentItemId;
	/**
	 * 项目名称
	 */
	private String itemName;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
