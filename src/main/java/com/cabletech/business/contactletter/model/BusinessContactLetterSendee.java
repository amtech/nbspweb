package com.cabletech.business.contactletter.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * @author zhougang 2012 07 20 业务联系函的实体bean
 */
public class BusinessContactLetterSendee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 业务联系函ID
	 */
	private String letterId;
	/**
	 * 通知人
	 */
	private String objectId;
	/**
	 * 是否已阅
	 */
	private String isread;
	/**
	 * 已阅时间
	 */
	private Date readtime;

	/**
	 * @return the letterId
	 */
	public String getLetterId() {
		return letterId;
	}

	/**
	 * @param letterId
	 *            the letterId to set
	 */
	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the isread
	 */
	public String getIsread() {
		return isread;
	}

	/**
	 * @param isread
	 *            the isread to set
	 */
	public void setIsread(String isread) {
		this.isread = isread;
	}

	/**
	 * @return the readtime
	 */
	public Date getReadtime() {
		return readtime;
	}

	/**
	 * @param readtime
	 *            the readtime to set
	 */
	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

}
