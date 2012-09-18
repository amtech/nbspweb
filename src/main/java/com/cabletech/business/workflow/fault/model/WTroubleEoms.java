package com.cabletech.business.workflow.fault.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 故障EOMS工单
 * 
 * @author zhaobi
 * @date 2012-6-15
 * @author 杨隽 2012-07-17 添加“最终网络分类”、“最终设备厂商”和“是否重大故障”属性
 */
public class WTroubleEoms extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * EOMS单号
	 */
	private String eomsid;
	/**
	 * EOMS 标题
	 */
	private String title;
	/**
	 * 网络类型
	 */
	private String networktype;
	/**
	 * 是否影响业务
	 */
	private String isaffectbusiness;
	/**
	 * 影响业务类型
	 */
	private String affectbusinesstype;
	/**
	 * 发生时间
	 */
	private Date happentime;
	/**
	 * 响应级别
	 */
	private String responselevel;
	/**
	 * 受理时限
	 */
	private Date acceptedtime;
	/**
	 * 处理时限
	 */
	private Date processtime;
	/**
	 * 告警级别
	 */
	private String alarmlevel;
	/**
	 * T1处理时限
	 */
	private Date processtimet1;
	/**
	 * T2处理时限
	 */
	private Date processtimet2;
	/**
	 * T3处理时限
	 */
	private Date processtimet3;

	/**
	 * 故障所属区域ID
	 */
	private String regionid;
	/**
	 * 发现方式
	 */
	private String findtype;
	/**
	 * 网元名称
	 */
	private String networkelementname;
	/**
	 * 故障设备厂商
	 */
	private String equipmentcompany;
	/**
	 * 故障设备类型
	 */
	private String equipmenttype;
	/**
	 * 故障设备型号
	 */
	private String equipmentmodel;
	/**
	 * 告警流水号
	 */
	private String alarmserial;
	/**
	 * 网管告警ID
	 */
	private String nmalarmid;
	/**
	 * 告警状态
	 */
	private String alarmstatus;
	/**
	 * 告警清除时间
	 */
	private Date alarmcleartime;
	/**
	 * 告警逻辑分类
	 */
	private String alarmlogictype;
	/**
	 * 告警逻辑子类
	 */
	private String alarmlogicsubtype;
	/**
	 * 告警来源
	 */
	private String alarmsource;
	/**
	 * 相关投诉工单号
	 */
	private String complaintsorder;
	/**
	 * 原始告警号
	 */
	private String alarmoldcode;
	/**
	 * 派单联系人
	 */
	private String sendcontact;
	/**
	 * 告警描述
	 */
	private String alarmdesc;
	/**
	 * 最终网络分类
	 */
	private String incendnettype;
	/**
	 * 最终设备厂商
	 */
	private String incendequipment;
	/**
	 * 是否重大故障
	 */
	private String incisimportantincident;

	/**
	 * @return the eomsid
	 */
	public String getEomsid() {
		return eomsid;
	}

	/**
	 * @param eomsid
	 *            the eomsid to set
	 */
	public void setEomsid(String eomsid) {
		this.eomsid = eomsid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the networktype
	 */
	public String getNetworktype() {
		return networktype;
	}

	/**
	 * @param networktype
	 *            the networktype to set
	 */
	public void setNetworktype(String networktype) {
		this.networktype = networktype;
	}

	/**
	 * @return the isaffectbusiness
	 */
	public String getIsaffectbusiness() {
		return isaffectbusiness;
	}

	/**
	 * @param isaffectbusiness
	 *            the isaffectbusiness to set
	 */
	public void setIsaffectbusiness(String isaffectbusiness) {
		this.isaffectbusiness = isaffectbusiness;
	}

	/**
	 * @return the affectbusinesstype
	 */
	public String getAffectbusinesstype() {
		return affectbusinesstype;
	}

	/**
	 * @param affectbusinesstype
	 *            the affectbusinesstype to set
	 */
	public void setAffectbusinesstype(String affectbusinesstype) {
		this.affectbusinesstype = affectbusinesstype;
	}

	/**
	 * @return the happentime
	 */
	public Date getHappentime() {
		return happentime;
	}

	/**
	 * @param happentime
	 *            the happentime to set
	 */
	public void setHappentime(Date happentime) {
		this.happentime = happentime;
	}

	/**
	 * @return the responselevel
	 */
	public String getResponselevel() {
		return responselevel;
	}

	/**
	 * @param responselevel
	 *            the responselevel to set
	 */
	public void setResponselevel(String responselevel) {
		this.responselevel = responselevel;
	}

	/**
	 * @return the acceptedtime
	 */
	public Date getAcceptedtime() {
		return acceptedtime;
	}

	/**
	 * @param acceptedtime
	 *            the acceptedtime to set
	 */
	public void setAcceptedtime(Date acceptedtime) {
		this.acceptedtime = acceptedtime;
	}

	/**
	 * @return the processtime
	 */
	public Date getProcesstime() {
		return processtime;
	}

	/**
	 * @param processtime
	 *            the processtime to set
	 */
	public void setProcesstime(Date processtime) {
		this.processtime = processtime;
	}

	/**
	 * @return the alarmlevel
	 */
	public String getAlarmlevel() {
		return alarmlevel;
	}

	/**
	 * @param alarmlevel
	 *            the alarmlevel to set
	 */
	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

	/**
	 * @return the processtimet1
	 */
	public Date getProcesstimet1() {
		return processtimet1;
	}

	/**
	 * @param processtimet1
	 *            the processtimet1 to set
	 */
	public void setProcesstimet1(Date processtimet1) {
		this.processtimet1 = processtimet1;
	}

	/**
	 * @return the processtimet2
	 */
	public Date getProcesstimet2() {
		return processtimet2;
	}

	/**
	 * @param processtimet2
	 *            the processtimet2 to set
	 */
	public void setProcesstimet2(Date processtimet2) {
		this.processtimet2 = processtimet2;
	}

	/**
	 * @return the processtimet3
	 */
	public Date getProcesstimet3() {
		return processtimet3;
	}

	/**
	 * @param processtimet3
	 *            the processtimet3 to set
	 */
	public void setProcesstimet3(Date processtimet3) {
		this.processtimet3 = processtimet3;
	}

	/**
	 * @return the regionid
	 */
	public String getRegionid() {
		return regionid;
	}

	/**
	 * @param regionid
	 *            the regionid to set
	 */
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	/**
	 * @return the findtype
	 */
	public String getFindtype() {
		return findtype;
	}

	/**
	 * @param findtype
	 *            the findtype to set
	 */
	public void setFindtype(String findtype) {
		this.findtype = findtype;
	}

	/**
	 * @return the networkelementname
	 */
	public String getNetworkelementname() {
		return networkelementname;
	}

	/**
	 * @param networkelementname
	 *            the networkelementname to set
	 */
	public void setNetworkelementname(String networkelementname) {
		this.networkelementname = networkelementname;
	}

	/**
	 * @return the equipmentcompany
	 */
	public String getEquipmentcompany() {
		return equipmentcompany;
	}

	/**
	 * @param equipmentcompany
	 *            the equipmentcompany to set
	 */
	public void setEquipmentcompany(String equipmentcompany) {
		this.equipmentcompany = equipmentcompany;
	}

	/**
	 * @return the equipmenttype
	 */
	public String getEquipmenttype() {
		return equipmenttype;
	}

	/**
	 * @param equipmenttype
	 *            the equipmenttype to set
	 */
	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}

	/**
	 * @return the equipmentmodel
	 */
	public String getEquipmentmodel() {
		return equipmentmodel;
	}

	/**
	 * @param equipmentmodel
	 *            the equipmentmodel to set
	 */
	public void setEquipmentmodel(String equipmentmodel) {
		this.equipmentmodel = equipmentmodel;
	}

	/**
	 * @return the alarmserial
	 */
	public String getAlarmserial() {
		return alarmserial;
	}

	/**
	 * @param alarmserial
	 *            the alarmserial to set
	 */
	public void setAlarmserial(String alarmserial) {
		this.alarmserial = alarmserial;
	}

	/**
	 * @return the nmalarmid
	 */
	public String getNmalarmid() {
		return nmalarmid;
	}

	/**
	 * @param nmalarmid
	 *            the nmalarmid to set
	 */
	public void setNmalarmid(String nmalarmid) {
		this.nmalarmid = nmalarmid;
	}

	/**
	 * @return the alarmstatus
	 */
	public String getAlarmstatus() {
		return alarmstatus;
	}

	/**
	 * @param alarmstatus
	 *            the alarmstatus to set
	 */
	public void setAlarmstatus(String alarmstatus) {
		this.alarmstatus = alarmstatus;
	}

	/**
	 * @return the alarmcleartime
	 */
	public Date getAlarmcleartime() {
		return alarmcleartime;
	}

	/**
	 * @param alarmcleartime
	 *            the alarmcleartime to set
	 */
	public void setAlarmcleartime(Date alarmcleartime) {
		this.alarmcleartime = alarmcleartime;
	}

	/**
	 * @return the alarmlogictype
	 */
	public String getAlarmlogictype() {
		return alarmlogictype;
	}

	/**
	 * @param alarmlogictype
	 *            the alarmlogictype to set
	 */
	public void setAlarmlogictype(String alarmlogictype) {
		this.alarmlogictype = alarmlogictype;
	}

	/**
	 * @return the alarmlogicsubtype
	 */
	public String getAlarmlogicsubtype() {
		return alarmlogicsubtype;
	}

	/**
	 * @param alarmlogicsubtype
	 *            the alarmlogicsubtype to set
	 */
	public void setAlarmlogicsubtype(String alarmlogicsubtype) {
		this.alarmlogicsubtype = alarmlogicsubtype;
	}

	/**
	 * @return the alarmsource
	 */
	public String getAlarmsource() {
		return alarmsource;
	}

	/**
	 * @param alarmsource
	 *            the alarmsource to set
	 */
	public void setAlarmsource(String alarmsource) {
		this.alarmsource = alarmsource;
	}

	/**
	 * @return the complaintsorder
	 */
	public String getComplaintsorder() {
		return complaintsorder;
	}

	/**
	 * @param complaintsorder
	 *            the complaintsorder to set
	 */
	public void setComplaintsorder(String complaintsorder) {
		this.complaintsorder = complaintsorder;
	}

	/**
	 * @return the alarmoldcode
	 */
	public String getAlarmoldcode() {
		return alarmoldcode;
	}

	/**
	 * @param alarmoldcode
	 *            the alarmoldcode to set
	 */
	public void setAlarmoldcode(String alarmoldcode) {
		this.alarmoldcode = alarmoldcode;
	}

	/**
	 * @return the sendcontact
	 */
	public String getSendcontact() {
		return sendcontact;
	}

	/**
	 * @param sendcontact
	 *            the sendcontact to set
	 */
	public void setSendcontact(String sendcontact) {
		this.sendcontact = sendcontact;
	}

	/**
	 * @return the alarmdesc
	 */
	public String getAlarmdesc() {
		return alarmdesc;
	}

	/**
	 * @param alarmdesc
	 *            the alarmdesc to set
	 */
	public void setAlarmdesc(String alarmdesc) {
		this.alarmdesc = alarmdesc;
	}

	public String getIncendnettype() {
		return incendnettype;
	}

	public void setIncendnettype(String incendnettype) {
		this.incendnettype = incendnettype;
	}

	public String getIncendequipment() {
		return incendequipment;
	}

	public void setIncendequipment(String incendequipment) {
		this.incendequipment = incendequipment;
	}

	public String getIncisimportantincident() {
		return incisimportantincident;
	}

	public void setIncisimportantincident(String incisimportantincident) {
		this.incisimportantincident = incisimportantincident;
	}

}
