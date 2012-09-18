package com.cabletech.business.sysmanager.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 
 * @author Administrator
 * 
 */
public class WwWorkorderCCInfo extends BaseEntity {
	/**
	 * 工单抄送信息表
	 */
	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public String getWorkorderType() {
		return workorderType;
	}

	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return
	 */
	public String getccPersonId() {
		return ccPersonId;
	}

	/**
	 * 
	 * @param ccPersonIds
	 *            String
	 */
	public void setccPersonId(String ccPersonIds) {
		ccPersonId = ccPersonIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConfirmRemark() {
		return confirmRemark;
	}

	public void setConfirmRemark(String confirmRemark) {
		this.confirmRemark = confirmRemark;
	}

	private String id;
	private String workorderId;
	private String workorderType;
	private String creater;
	private Date createTime;
	private String ccPersonId;
	private String status;
	private Date confirmTime;
	private String confirmRemark;

}
