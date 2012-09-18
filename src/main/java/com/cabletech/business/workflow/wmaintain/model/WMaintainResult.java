package com.cabletech.business.workflow.wmaintain.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 站点异常项及处理结果实体
 * 
 * @author 杨隽 2012-04-11 创建
 * 
 */
public class WMaintainResult extends BaseEntity {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 系统编号
	private String id;
	// 巡检记录编号
	private String patrolRecordId;
	// 巡检异常项（巡检子项）编号
	private String itemId;
	// 计划维护站点编号
	private String maintainId;
	// 维修记录结果
	private String maintainResult;
	// 维修记录说明
	private String maintainRecord;
	// 维修时间
	private Date maintainDate;
	// 巡检人员编号
	private String patrolmanId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatrolRecordId() {
		return patrolRecordId;
	}

	public void setPatrolRecordId(String patrolRecordId) {
		this.patrolRecordId = patrolRecordId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}

	public String getMaintainResult() {
		return maintainResult;
	}

	public void setMaintainResult(String maintainResult) {
		this.maintainResult = maintainResult;
	}

	public String getMaintainRecord() {
		return maintainRecord;
	}

	public void setMaintainRecord(String maintainRecord) {
		this.maintainRecord = maintainRecord;
	}

	public Date getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(Date maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getPatrolmanId() {
		return patrolmanId;
	}

	public void setPatrolmanId(String patrolmanId) {
		this.patrolmanId = patrolmanId;
	}
}
