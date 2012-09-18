package com.cabletech.business.base.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 用户活动日志
 * 
 * @author zhaobi
 * 
 */
public class UserActionLog extends BaseEntity {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 人员ID
	 */
	private String personid;
	/**
	 * 菜单ID
	 */
	private String menuid;
	/**
	 * 操作时间
	 */
	private Date operate_time;
	/**
	 * 方法名称
	 */
	private String class_method;
	/**
	 * 方法描述
	 */
	private String method_desc;
	/**
	 * 登陆IP
	 */
	private String loginip;
	/**
	 * 操作记录ID
	 */
	private String recordid;
	/**
	 * 操作记录
	 */
	private String record;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the personid
	 */
	public String getPersonid() {
		return personid;
	}

	/**
	 * @param personid
	 *            the personid to set
	 */
	public void setPersonid(String personid) {
		this.personid = personid;
	}

	/**
	 * @return the menuid
	 */
	public String getMenuid() {
		return menuid;
	}

	/**
	 * @param menuid
	 *            the menuid to set
	 */
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	/**
	 * @return the operate_time
	 */
	public Date getOperate_time() {
		return operate_time;
	}

	/**
	 * @param operate_time
	 *            the operate_time to set
	 */
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}

	/**
	 * @return the class_method
	 */
	public String getClass_method() {
		return class_method;
	}

	/**
	 * @param class_method
	 *            the class_method to set
	 */
	public void setClass_method(String class_method) {
		this.class_method = class_method;
	}

	/**
	 * @return the method_desc
	 */
	public String getMethod_desc() {
		return method_desc;
	}

	/**
	 * @param method_desc
	 *            the method_desc to set
	 */
	public void setMethod_desc(String method_desc) {
		this.method_desc = method_desc;
	}

	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * @param loginip
	 *            the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	/**
	 * @return the recordid
	 */
	public String getRecordid() {
		return recordid;
	}

	/**
	 * @param recordid
	 *            the recordid to set
	 */
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	/**
	 * @return the record
	 */
	public String getRecord() {
		return record;
	}

	/**
	 * @param record
	 *            the record to set
	 */
	public void setRecord(String record) {
		this.record = record;
	}
}
