package com.cabletech.business.workflow.electricity.security.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Page;

/**
 * 断电告警信息实体
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 添加createDateMin、createDateMax属性
 * 
 */
public class OeOutageAlarm extends BaseEntity {
	// 断电告警未派单状态
	public static final String UNDISPATCHED_STATE = "1";
	// 断电告警已派单状态
	public static final String DISPATCHED_STATE = "2";
	// 断电告警被忽略状态
	public static final String IGNORED_STATE = "3";
	// 断电告警结束状态
	public static final String END_STATE = "4";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 断电站点编号
	private String stationId;
	// 站点类型
	private String stationType;
	// 断电时间
	private String blackoutReason;
	// 断电原因
	private Date blackoutTime;
	// 处理状态
	private String state;
	// 接收时间
	private Date createTime;
	// 忽略人编号
	private String handler;
	// 忽略时间
	private Date ignoreTime;
	// 断电站点名称（用于页面显示）
	private String stationName;
	// 忽略人名称（用于页面显示）
	private String handlerName;
	// 分页数据信息（用于页面查询）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 创建时间开始比较日期（用于页面查询）
	private String createDateMin;
	// 创建时间结束比较日期（用于页面查询）
	private String createDateMax;

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getBlackoutReason() {
		return blackoutReason;
	}

	public void setBlackoutReason(String blackoutReason) {
		this.blackoutReason = blackoutReason;
	}

	public Date getBlackoutTime() {
		return blackoutTime;
	}

	public void setBlackoutTime(Date blackoutTime) {
		this.blackoutTime = blackoutTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Date getIgnoreTime() {
		return ignoreTime;
	}

	public void setIgnoreTime(Date ignoreTime) {
		this.ignoreTime = ignoreTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public String getCreateDateMin() {
		return createDateMin;
	}

	public void setCreateDateMin(String createDateMin) {
		this.createDateMin = createDateMin;
	}

	public String getCreateDateMax() {
		return createDateMax;
	}

	public void setCreateDateMax(String createDateMax) {
		this.createDateMax = createDateMax;
	}
}
