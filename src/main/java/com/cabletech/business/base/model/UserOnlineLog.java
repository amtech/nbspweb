package com.cabletech.business.base.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 用户在线日志
 * @author Administrator
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 *
 */
public class UserOnlineLog extends BaseEntity {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 人员ID
	 */
	private String personid;
	/**
	 * IP
	 */
	private String ip;
	/**
	 * 登陆时间
	 */
	private Date login_time;
	/**
	 * 登出时间
	 */
	private Date logout_time;
	
	/**
	 * 在线时长
	 * 用于显示
	 * 非持久化数据
	 */
	private String online_time;
	/**
	 * 在线时长
	 */
	public String getOnline_time() {
		String ret = "";
        if(null!=this.getLogin_time()&&null!=this.getLogout_time()){
    		java.util.Date now = this.getLogout_time();
    		java.util.Date date = this.getLogin_time();
    		long l = now.getTime() - date.getTime();
    		long day = l / (24 * 60 * 60 * 1000);
    		long hour = (l / (60 * 60 * 1000) - day * 24);
    		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
    		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60
    				* 60 - min * 60);
    		ret="" + day + "天" + hour + "小时" + min + "分" + s+ "秒";
        }
        online_time=ret;
		return online_time;
	}
	
	public void setOnline_time(String onlineTime) {
		this.online_time = onlineTime;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param personid the personid to set
	 */
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the login_time
	 */
	public Date getLogin_time() {
		return login_time;
	}
	/**
	 * @param login_time the login_time to set
	 */
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	/**
	 * @return the logout_time
	 */
	public Date getLogout_time() {
		return logout_time;
	}
	/**
	 * @param logout_time the logout_time to set
	 */
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}

}
