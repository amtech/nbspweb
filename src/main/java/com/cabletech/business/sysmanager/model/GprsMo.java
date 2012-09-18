package com.cabletech.business.sysmanager.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * GPRS日志
 * @author wangt
 *
 */
public class GprsMo extends BaseEntity {
	private String keyid;
	private String deviceid;
	private Date receivetime;
	private String content;
	private String state;
	private String sessionkey;
	private String commandid;
	private String imei;
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public Date getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public String getCommandid() {
		return commandid;
	}
	public void setCommandid(String commandid) {
		this.commandid = commandid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}

}
