package com.cabletech.business.workflow.wmaintain.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 维修作业计划Dao
 * 
 * @author 杨隽 2012-04-11 创建
 * 
 */
@Repository
public class WMaintainPlanDao extends WMaintainBaseDao<WMaintainPlan, String> {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT wp.ID,wp.PLAN_NAME,wp.PATROL_GROUP, ");
		sqlBuffer.append(" wp.BUSINESS_TYPE, ");
		sqlBuffer.append(" to_char(wp.START_DATE,'yyyy-mm-dd') AS START_DATE, ");
		sqlBuffer.append(" to_char(wp.END_DATE,'yyyy-mm-dd') AS END_DATE, ");
		sqlBuffer.append(" to_char(wp.START_DATE,'yyyy-mm-dd')||'至'||to_char(wp.END_DATE,'yyyy-mm-dd') AS PLAN_DATE, ");
		sqlBuffer.append(" wp.CREATER,wp.CREATE_DATE,wp.PLAN_STATE, ");
		sqlBuffer.append(" to_char(wp.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS, ");
		sqlBuffer.append(" vu.USERNAME AS CREATER_NAME,vp.ORGNAME, ");
		sqlBuffer.append(" vp.NAME AS PATROLGROUP_NAME,r.REGIONNAME ");
		sqlBuffer.append(" FROM WMAINTAIN_PLAN wp ");
		sqlBuffer.append(" JOIN VIEW_USERINFO vu ");
		sqlBuffer.append(" ON vu.SID=wp.CREATER ");
		sqlBuffer.append(" LEFT JOIN VIEW_PATROLGROUP vp ");
		sqlBuffer.append(" ON vp.ID=wp.PATROL_GROUP ");
		sqlBuffer.append(" LEFT JOIN VIEW_REGION r ");
		sqlBuffer.append(" ON vp.REGIONID=r.REGIONID ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}
}
