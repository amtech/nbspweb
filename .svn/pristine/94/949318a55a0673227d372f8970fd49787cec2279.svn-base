package com.cabletech.business.workflow.fault.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 故障告警实体
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 添加实体属性
 * @author 杨隽 2011-11-02 添加生成查询条件的方法
 * @author 杨隽 2011-11-03 添加“故障专业”查询条件
 * @author 杨隽 2011-11-04 添加“资源名称”属性
 * @author 杨隽 2011-11-25 添加“故障告警单处理完成状态”常量
 * @author 杨隽 2011-10-31 添加通用表格的类型常量“WTROUBLE_ALARM”
 * @author 杨隽 2012-02-09 去除getConditionSqlByParameter()方法
 * @author 杨隽 2012-02-22 添加“报告人”、“报告时间”变量
 * @author 杨隽 2012-02-22 添加“故障派单已取消状态”常量
 * @author 杨隽 2012-07-17 添加“EOMS故障工单类型”常量
 * @author 杨隽 2012-07-18 添加isEomsFault()方法
 * 
 */
public class FaultAlert extends BaseEntity {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障告警单类型常量
	public static final String WTROUBLE_ALARM_TYPE = "WTROUBLE_ALARM";
	// 故障告警单派单未提交状态
	public static final String NOT_SUBMITED_STATE = "0";
	// 故障告警单未忽略状态
	public static final String WAIT_HANDLED_STATE = "1";
	// 故障告警单已忽略状态
	public static final String IGNORED_STATE = "2";
	// 故障告警单已派单状态
	public static final String DISPATCHED_STATE = "3";
	// 故障告警单处理完成状态
	public static final String FINISHED_STATE = "4";
	// 故障告警单派单已取消状态
	public static final String CANCELED_STATE = "9";
	// EOMS故障工单类型
	public static final String EOMS_FIND_TYPE = "B17";
	// 故障告警编号
	private String id;
	// 故障标题
	private String troubleTitle;
	// 发现方式
	private String findType;
	// EMOS单号
	private String eomsId;
	// 故障发生时间
	private Date troubleTime;
	// 是否紧急
	private String isInstancy;
	// 故障描述
	private String troubleDesc;
	// 资源编号
	private String stationId;
	// 资源名称
	private String stationName;
	// 资源类型
	private String stationType;
	// 故障地点
	private String address;
	// 故障级别
	private String troubleLevel;
	// 故障专业
	private String businessType;
	// 故障状态
	private String state;
	// 故障忽略人编号
	private String handler;
	// 故障忽略时间
	private Date ignoreTime;
	// 报告人
	private String reporter;
	// 报告时间
	private Date reportTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTroubleTitle() {
		return troubleTitle;
	}

	public void setTroubleTitle(String troubleTitle) {
		this.troubleTitle = troubleTitle;
	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public String getEomsId() {
		return eomsId;
	}

	public void setEomsId(String eomsId) {
		this.eomsId = eomsId;
	}

	public Date getTroubleTime() {
		return troubleTime;
	}

	public void setTroubleTime(Date troubleTime) {
		this.troubleTime = troubleTime;
	}

	public String getIsInstancy() {
		return isInstancy;
	}

	public void setIsInstancy(String isInstancy) {
		this.isInstancy = isInstancy;
	}

	public String getTroubleDesc() {
		return troubleDesc;
	}

	public void setTroubleDesc(String troubleDesc) {
		this.troubleDesc = troubleDesc;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTroubleLevel() {
		return troubleLevel;
	}

	public void setTroubleLevel(String troubleLevel) {
		this.troubleLevel = troubleLevel;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getState() {
		return state;
	}

	public void setState(String ignoreState) {
		this.state = ignoreState;
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

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * 判断是否为EOMS工单类型
	 * 
	 * @return boolan
	 */
	public boolean isEomsFault() {
		return FaultAlert.EOMS_FIND_TYPE.equals(this.findType);
	}
}
