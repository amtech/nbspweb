package com.cabletech.business.resource.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 资源分配 值对象 用于封装 页面传递的值
 * 
 * @author 杨隽 2012-07-20
 *         添加“是否多家维护”属性值、“分配回收动作类型”属性值、“是多家维护”常量、isNoMaintenancedContractor()
 *         、isNoMaintenancedPatrolgroup ()和isManyMaintenanced()方法
 * 
 */
public class ResourceAllotForm {
	/**
	 * “未选择维护”常量
	 */
	public static final String NO_MAINTENANCE_VALUE = "-1";
	/**
	 * “是多家维护”常量
	 */
	public static final String IS_MANY_MAINTENANCED = "0";
	/**
	 * 原代维公司编号
	 */
	private String oldMaintenceId;
	/**
	 * 原维护组编号
	 */
	private String oldPatrolmanId;
	/**
	 * 新代维公司编号
	 */
	private String newMaintenceId;
	/**
	 * 新维护组编号
	 */
	private String newPatrolmanId;
	/**
	 * 资源名称
	 */
	private String resourceName;
	/**
	 * 专业类型
	 */
	private String resourceType;
	/**
	 * 区域编号
	 */
	private String regionId;
	/**
	 * 组织类型
	 */
	private String orgType;
	/**
	 * 组织编号
	 */
	private String orgId;
	/**
	 * 是否多家维护
	 */
	private String isManyMaintenanced;
	/**
	 * 分配回收动作类型
	 */
	private String actionType;
	/**
	 * 分配或者回收资源的编号数组
	 */
	private String[] newResources;
	/**
	 * 用户区域编号
	 */
	private String userRegionId;

	/**
	 * 是否为未分配代维公司
	 * 
	 * @return boolean
	 */
	public boolean isNoMaintenancedContractor() {
		return ResourceAllotForm.NO_MAINTENANCE_VALUE
				.equals(this.oldMaintenceId);
	}

	/**
	 * 是否为未分配维护组
	 * 
	 * @return boolean
	 */
	public boolean isNoMaintenancedPatrolgroup() {
		return ResourceAllotForm.NO_MAINTENANCE_VALUE
				.equals(this.oldPatrolmanId);
	}

	/**
	 * 判断是否为多家维护
	 * 
	 * @return boolean
	 */
	public boolean isManyMaintenanced() {
		return ResourceAllotForm.IS_MANY_MAINTENANCED
				.equals(this.isManyMaintenanced);
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * 设置regionid的具体值
	 * 
	 * @return
	 */
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	/**
	 * 原来的维护组
	 * 
	 * @return
	 */
	public String getOldMaintenceId() {
		if ("2".equals(this.getOrgType())
				&& StringUtils.isBlank(oldMaintenceId)) {
			return this.getOrgId();
		}
		return oldMaintenceId;
	}

	public void setOldMaintenceId(String oldMaintenceId) {
		this.oldMaintenceId = oldMaintenceId;
	}

	public String getOldPatrolmanId() {
		return oldPatrolmanId;
	}

	public void setOldPatrolmanId(String oldPatrolmanId) {
		this.oldPatrolmanId = oldPatrolmanId;
	}

	public String getNewMaintenceId() {
		return newMaintenceId;
	}

	public void setNewMaintenceId(String maintenceId) {
		this.newMaintenceId = maintenceId;
	}

	public String getNewPatrolmanId() {
		return newPatrolmanId;
	}

	public void setNewPatrolmanId(String patrolmanId) {
		this.newPatrolmanId = patrolmanId;
	}

	/**
	 * 资源名称
	 * 
	 * @return
	 */
	public String getResourceName() {
		try {
			return URLDecoder.decode(resourceName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return resourceName;
		}
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String[] getNewResources() {
		return newResources;
	}

	public void setNewResources(String[] newResources) {
		this.newResources = newResources;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getIsManyMaintenanced() {
		return isManyMaintenanced;
	}

	public void setIsManyMaintenanced(String isManyMaintenanced) {
		this.isManyMaintenanced = isManyMaintenanced;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getUserRegionId() {
		return userRegionId;
	}

	public void setUserRegionId(String userRegionId) {
		this.userRegionId = userRegionId;
	}
}