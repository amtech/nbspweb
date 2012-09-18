/**
 * 名称：巡检计划资源实体
 * 功能：巡检计划资源实体
 * 作者：zhaobi
 * 版权：Copyright (c) 2004-2011 北京鑫干线
 * 日期：2012-2-8
 *
 * 变更历史：
 * 日期		作者		变更
 *  ------------------------------------------------------------------------------------------------
 * 
 * 
 * 
 * 
 */
package com.cabletech.business.wplan.plan.model;

import com.cabletech.common.base.BaseEntity;



/**
 * 巡检计划资源实体
 * @author zhaob
 *
 */
public class PatrolResource extends BaseEntity  {

	//巡检计划
	private String planid;
	//巡检资源
	private String resourceid;
	//巡检资源类型
	private String resourcetype;
	//巡检次数
	private int patrolnum=1;

	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getResourceid() {
		return resourceid;
	}
	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}
	public String getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}
	public int getPatrolnum() {
		return patrolnum;
	}
	public void setPatrolnum(int patrolnum) {
		this.patrolnum = patrolnum;
	}

}