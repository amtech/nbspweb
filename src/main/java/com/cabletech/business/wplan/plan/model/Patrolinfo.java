/**
 * 名称：巡检计划Bean
 * 功能：巡检计划Bean
 * 作者：zhaobi
 * 版权：Copyright (c) 2004-2011 北京鑫干线
 * 日期：2011-7-21
 *
 * 变更历史：
 * 日期		作者		变更
 *  ------------------------------------------------------------------------------------------------
 * 2012-07-25 杨隽   添加maintainResourcesNum属性
 * 
 * 
 * 
 */
package com.cabletech.business.wplan.plan.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.DateUtil;

/**
 * 巡检计划信息实体
 * 
 * @author zhaobi
 * 
 */
public class Patrolinfo extends BaseEntity {
	/**
	 * 计划名称
	 */
	private String planname;
	/**
	 * 计划年份
	 */
	private String year = DateUtil.getNowYearStr();
	/**
	 * 专业类型
	 */
	private String businesstype;
	/**
	 * 年类型
	 */
	private String yeartype;
	/**
	 * 季度类型
	 */
	private String seasontype;
	/**
	 * 计划类型
	 */
	private String plantype;
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * 开始日期
	 */
	private Date startdate;
	/**
	 * 结束日期
	 */
	private Date enddate;
	/**
	 * 巡检组ID
	 */
	private String patrolgroupid;
	/**
	 * 巡检组名称
	 */
	private String patrolgroupname;
	/**
	 * 代维公司ID
	 */
	private String contractorid;
	/**
	 * 计划状态
	 */
	private String planstate;
	/**
	 * 区域ID
	 */
	private String regionid;
	/**
	 * 区域名称
	 */
	private String regionname;
	/**
	 * 是否提交
	 */
	private String issubmited;
	/**
	 * 计划模板ID
	 */
	private String plantemplate;
	/**
	 * 申请人ID
	 */
	private String creater;
	/**
	 * 申请人姓名
	 */
	private String creatername;
	/**
	 * 申请时间
	 */
	private Date createtime;
	/**
	 * 巡检计划审批人
	 */
	private String approver;
	/**
	 * 巡检计划审批人姓名
	 */
	private String approvername;
	/**
	 * 选举资源ids
	 */
	private String resourceids;
	/**
	 * 选举资源类型ids
	 */
	private String resourcetypes;
	/**
	 * 自定义条件
	 */
	private String condition;
	/**
	 * 维护资源总数
	 */
	private int maintainResourcesNum;

	/**
	 * @return the planname
	 */
	public String getPlanname() {
		return planname;
	}

	/**
	 * @param planname
	 *            the planname to set
	 */
	public void setPlanname(String planname) {
		this.planname = planname;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the businesstype
	 */
	public String getBusinesstype() {
		return businesstype;
	}

	/**
	 * @param businesstype
	 *            the businesstype to set
	 */
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	/**
	 * @return the plantype
	 */
	public String getPlantype() {
		return plantype;
	}

	/**
	 * @param plantype
	 *            the plantype to set
	 */
	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the patrolgroupid
	 */
	public String getPatrolgroupid() {
		return patrolgroupid;
	}

	/**
	 * @param patrolgroupid
	 *            the patrolgroupid to set
	 */
	public void setPatrolgroupid(String patrolgroupid) {
		this.patrolgroupid = patrolgroupid;
	}

	/**
	 * @return the patrolgroupname
	 */
	public String getPatrolgroupname() {
		return patrolgroupname;
	}

	/**
	 * @param patrolgroupname
	 *            the patrolgroupname to set
	 */
	public void setPatrolgroupname(String patrolgroupname) {
		this.patrolgroupname = patrolgroupname;
	}

	/**
	 * @return the planstate
	 */
	public String getPlanstate() {
		return planstate;
	}

	/**
	 * @param planstate
	 *            the planstate to set
	 */
	public void setPlanstate(String planstate) {
		this.planstate = planstate;
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
	 * @return the issubmited
	 */
	public String getIssubmited() {
		return issubmited;
	}

	/**
	 * @param issubmited
	 *            the issubmited to set
	 */
	public void setIssubmited(String issubmited) {
		this.issubmited = issubmited;
	}

	/**
	 * @return the plantemplate
	 */
	public String getPlantemplate() {
		return plantemplate;
	}

	/**
	 * @param plantemplate
	 *            the plantemplate to set
	 */
	public void setPlantemplate(String plantemplate) {
		this.plantemplate = plantemplate;
	}

	/**
	 * @return the yeartype
	 */
	public String getYeartype() {
		return yeartype;
	}

	/**
	 * @param yeartype
	 *            the yeartype to set
	 */
	public void setYeartype(String yeartype) {
		this.yeartype = yeartype;
	}

	/**
	 * @return the seasontype
	 */
	public String getSeasontype() {
		return seasontype;
	}

	/**
	 * @param seasontype
	 *            the seasontype to set
	 */
	public void setSeasontype(String seasontype) {
		this.seasontype = seasontype;
	}

	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * @param creater
	 *            the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the resourceids
	 */
	public String getResourceids() {
		return resourceids;
	}

	/**
	 * @param resourceids
	 *            the resourceids to set
	 */
	public void setResourceids(String resourceids) {
		this.resourceids = resourceids;
	}

	/**
	 * @return the resourcetypes
	 */
	public String getResourcetypes() {
		return resourcetypes;
	}

	/**
	 * @param resourcetypes
	 *            the resourcetypes to set
	 */
	public void setResourcetypes(String resourcetypes) {
		this.resourcetypes = resourcetypes;
	}

	/**
	 * @return the approver
	 */
	public String getApprover() {
		return approver;
	}

	/**
	 * @param approver
	 *            the approver to set
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * @return the creatername
	 */
	public String getCreatername() {
		return creatername;
	}

	/**
	 * @param creatername
	 *            the creatername to set
	 */
	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}

	/**
	 * @return the approvername
	 */
	public String getApprovername() {
		return approvername;
	}

	/**
	 * @param approvername
	 *            the approvername to set
	 */
	public void setApprovername(String approvername) {
		this.approvername = approvername;
	}

	/**
	 * @return the contractorid
	 */
	public String getContractorid() {
		return contractorid;
	}

	/**
	 * @param contractorid
	 *            the contractorid to set
	 */
	public void setContractorid(String contractorid) {
		this.contractorid = contractorid;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the regionname
	 */
	public String getRegionname() {
		return regionname;
	}

	/**
	 * @param regionname
	 *            the regionname to set
	 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate
	 *            the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate
	 *            the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getMaintainResourcesNum() {
		return maintainResourcesNum;
	}

	public void setMaintainResourcesNum(int maintainResourcesNum) {
		this.maintainResourcesNum = maintainResourcesNum;
	}
}