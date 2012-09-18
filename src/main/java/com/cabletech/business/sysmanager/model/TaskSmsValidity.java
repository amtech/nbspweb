package com.cabletech.business.sysmanager.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 
 * @author Administrator
 *
 */
public class TaskSmsValidity extends BaseEntity {
	  /**
	 * 工单提醒短信有效性
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	  private String workorderType;
	  private String smsType;
	  private String validity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkorderType() {
		return workorderType;
	}
	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
}
