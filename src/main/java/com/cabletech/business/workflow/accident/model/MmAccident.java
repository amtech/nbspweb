package com.cabletech.business.workflow.accident.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 隐患实体
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
public class MmAccident extends BaseEntity {
	/**
	 * 隐患待处理状态
	 */
	public static final String WAIT_PROCESSED_STATE = "1";
	/**
	 * 隐患已忽略状态
	 */
	public static final String IGNORED_STATE = "2";
	/**
	 * 隐患处理中状态
	 */
	public static final String PROCESSING_STATE = "3";
	/**
	 * 隐患已消除状态
	 */
	public static final String CLEARED_STATE = "4";
	/**
	 * 隐患未消除状态
	 */
	public static final String UNCLEARED_STATE = "5";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 专业类型（数据库字段）
	 */
	private String businessType;
	/**
	 * 隐患资源（数据库字段）
	 */
	private String resourceId;
	/**
	 * 隐患资源名称
	 */
	private String resourceName;
	/**
	 * 段类型（数据库字段）
	 */
	private String resourceType;
	/**
	 * 隐患名称（数据库字段）
	 */
	private String name;
	/**
	 * 隐患分类（数据库字段）
	 */
	private String accidentType;
	/**
	 * 隐患分类名称
	 */
	private String accidentTypeName;
	/**
	 * 隐患描述（数据库字段）
	 */
	private String remark;
	/**
	 * 上报时间（数据库字段）
	 */
	private Date createTime;
	/**
	 * 上报人（数据库字段）
	 */
	private String creater;
	/**
	 * 上报人名称
	 */
	private String createrName;
	/**
	 * 上报巡检组（数据库字段）
	 */
	private String patrolGroupId;
	/**
	 * 上报巡检组名称
	 */
	private String patrolGroupName;
	/**
	 * SIM卡（数据库字段）
	 */
	private String simId;
	/**
	 * 状态（数据库字段）
	 */
	private String status;
	/**
	 * 级别（数据库字段）
	 */
	private String accidentLevel;
	/**
	 * 忽略说明（数据库字段）
	 */
	private String ignoreReason;
	/**
	 * 忽略人（数据库字段）
	 */
	private String ignorePersonId;
	/**
	 * 忽略人名称
	 */
	private String ignorePersonName;
	/**
	 * 忽略时间（数据库字段）
	 */
	private Date ignoreTime;
	/**
	 * 经度（数据库字段）
	 */
	private String lon;
	/**
	 * 纬度（数据库字段）
	 */
	private String lat;
	/**
	 * 位置描述（数据库字段）
	 */
	private String positionRemark;
	/**
	 * 确认人（数据库字段）
	 */
	private String confirmPersonId;
	/**
	 * 确认人名称
	 */
	private String confirmPersonName;
	/**
	 * 确认时间（数据库字段）
	 */
	private Date confirmTime;
	/**
	 * 处理时间（数据库字段）
	 */
	private Date handleTime;
	/**
	 * 处理结果描述（数据库字段）
	 */
	private String handleRemark;
	/**
	 * 处理人（数据库字段）
	 */
	private String handlePersons;
	/**
	 * 处理措施（数据库字段）
	 */
	private String handleMeasure;
	/**
	 * 上报时间开始日期
	 */
	private String createTimeStart;
	/**
	 * 上报时间结束日期
	 */
	private String createTimeEnd;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String siteId) {
		this.resourceId = siteId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String siteName) {
		this.resourceName = siteName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String lineType) {
		this.resourceType = lineType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}

	public String getAccidentTypeName() {
		return accidentTypeName;
	}

	public void setAccidentTypeName(String accidentTypeName) {
		this.accidentTypeName = accidentTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getPatrolGroupId() {
		return patrolGroupId;
	}

	public void setPatrolGroupId(String patrolGroupId) {
		this.patrolGroupId = patrolGroupId;
	}

	public String getPatrolGroupName() {
		return patrolGroupName;
	}

	public void setPatrolGroupName(String patrolGroupName) {
		this.patrolGroupName = patrolGroupName;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccidentLevel() {
		return accidentLevel;
	}

	public void setAccidentLevel(String accidentLevel) {
		this.accidentLevel = accidentLevel;
	}

	public String getIgnoreReason() {
		return ignoreReason;
	}

	public void setIgnoreReason(String ignoreReason) {
		this.ignoreReason = ignoreReason;
	}

	public String getIgnorePersonId() {
		return ignorePersonId;
	}

	public void setIgnorePersonId(String ignorePersonId) {
		this.ignorePersonId = ignorePersonId;
	}

	public String getIgnorePersonName() {
		return ignorePersonName;
	}

	public void setIgnorePersonName(String ignorePersonName) {
		this.ignorePersonName = ignorePersonName;
	}

	public Date getIgnoreTime() {
		return ignoreTime;
	}

	public void setIgnoreTime(Date ignoreTime) {
		this.ignoreTime = ignoreTime;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPositionRemark() {
		return positionRemark;
	}

	public void setPositionRemark(String positionRemark) {
		this.positionRemark = positionRemark;
	}

	public String getConfirmPersonId() {
		return confirmPersonId;
	}

	public void setConfirmPersonId(String confirmPersonId) {
		this.confirmPersonId = confirmPersonId;
	}

	public String getConfirmPersonName() {
		return confirmPersonName;
	}

	public void setConfirmPersonName(String confirmPersonName) {
		this.confirmPersonName = confirmPersonName;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleRemark() {
		return handleRemark;
	}

	public void setHandleRemark(String handleRemark) {
		this.handleRemark = handleRemark;
	}

	public String getHandlePersons() {
		return handlePersons;
	}

	public void setHandlePersons(String handlePersons) {
		this.handlePersons = handlePersons;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getHandleMeasure() {
		return handleMeasure;
	}

	public void setHandleMeasure(String handleMeasure) {
		this.handleMeasure = handleMeasure;
	}
}
