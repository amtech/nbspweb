package com.cabletech.business.wplan.patrolitem.model;

import com.cabletech.common.base.*;

/**
 * 巡检项--子项
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 * @author 杨隽 2011-11-02 添加导入巡检项目的“异常值”字段
 */
public class PatrolSubItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String id;// ID VARCHAR2(12) N 系统编号
	private String itemId;// ITEM_ID VARCHAR2(100) Y 巡检项目编号
	private String subitemName;// SUBITEM_NAME VARCHAR2(20) Y 子项名称
	private String inputType;// INPUT_TYPE VARCHAR2(12) Y 输入类型
	private String cycle;// VARCHAR2(3) Y
							// year：年，quarter：季，month：月份，week：周，custom：按需
	private String qualityStandard;// QUALITY_STANDARD VARCHAR2(2000) Y 维护指标说明
	private String remark;// REMARK VARCHAR2(1000) Y 备注
	private String state;// STATE VARCHAR2(3) Y 标记当前在用状态，1为在用，2为删除。
	private String valueScope;// VALUE_SCOPE VARCHAR2(1024) 值域范围
	private String defaultValue;// DEFAULT_VALUE VARCHAR2(20) 默认值
	private String exceptionValue;// EXCEPTION_VALUE VARCHAR2(20) 异常值

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSubitemName() {
		return subitemName;
	}

	public void setSubitemName(String subitemName) {
		this.subitemName = subitemName;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getQualityStandard() {
		return qualityStandard;
	}

	public void setQualityStandard(String qualityStandard) {
		this.qualityStandard = qualityStandard;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getValueScope() {
		return valueScope;
	}

	public void setValueScope(String valueScope) {
		this.valueScope = valueScope;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getExceptionValue() {
		return exceptionValue;
	}

	public void setExceptionValue(String exceptionValue) {
		this.exceptionValue = exceptionValue;
	}
}