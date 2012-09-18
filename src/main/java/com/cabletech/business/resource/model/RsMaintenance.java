package com.cabletech.business.resource.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 基站维护关系
 * 
 * @author 张会军
 * 
 */
public class RsMaintenance extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 维护单位ID
	 */
	private String maintenanceId;
	/**
	 * 巡检组ID
	 */
	private String patrolGroupId;
	/**
	 * 资源ID
	 */
	private String rsId;
	/**
	 * 专业类型
	 */
	private String rsType;
	/**
	 * 维护单位名称
	 */
	private String maintenanceName;
	/**
	 * 巡检组名称
	 */
	private String patrolGroupName;
	/**
	 * 资源名称
	 */
	private String rsName;
	/**
	 * 资源类型名称
	 */
	private String rsTypeName;
	/**
	 * 区域ID
	 */
	private String regionid;

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getPatrolGroupId() {
		return patrolGroupId;
	}

	public void setPatrolGroupId(String patrolGroupId) {
		this.patrolGroupId = patrolGroupId;
	}

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getRsType() {
		return rsType;
	}

	public void setRsType(String rsType) {
		this.rsType = rsType;
	}

	public String getMaintenanceName() {
		return maintenanceName;
	}

	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}

	public String getPatrolGroupName() {
		return patrolGroupName;
	}

	public void setPatrolGroupName(String patrolGroupName) {
		this.patrolGroupName = patrolGroupName;
	}

	public String getRsName() {
		return rsName;
	}

	public void setRsName(String rsName) {
		this.rsName = rsName;
	}

	public String getRsTypeName() {
		return rsTypeName;
	}

	public void setRsTypeName(String rsTypeName) {
		this.rsTypeName = rsTypeName;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

}
