package com.cabletech.business.workflow.wmaintain.dao;

import org.springframework.stereotype.Repository;

/**
 * 维修作业计划中资源查询Dao
 * 
 * @author 杨隽 2012-04-23 创建
 * @author 杨隽 2012-06-04 修改问题站点重复的bug问题
 * @author 杨隽 2012-06-06 修改问题站点选择的bug问题
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class WMaintainResourceDao extends WMaintainBaseDao {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT * FROM ( ");
		sqlBuffer.append(" SELECT DISTINCT wpr.ID,wpr.SUBITEM_ID, ");
		sqlBuffer.append(" res.ADDRESS,wer.RESOURCE_TYPE, ");
		sqlBuffer.append(" wer.RESOURCE_ID, ");
		sqlBuffer.append(" wer.RESOURCE_TYPE||'_'||wer.RESOURCE_ID AS RES_, ");
		sqlBuffer.append(" wps.SUBITEM_NAME,wpr.SUBITEM_PATROL, ");
		sqlBuffer.append(" wp.ID AS PLAN_ID,wp.PLAN_NAME AS PLAN_NAME, ");
		sqlBuffer.append(" nvl(res.NAME,'未命名') AS RS_NAME, ");
		sqlBuffer.append(" wp.START_TIME,wp.PATROL_GROUP_ID, ");
		sqlBuffer.append(" wp.BUSINESS_TYPE ");
		putTableToSql(sqlBuffer);
		sqlBuffer.append(" ) t_ WHERE 1=1 ");
		return sqlBuffer.toString();
	}

	/**
	 * 将业务数据表的from语句放到sql缓冲区中
	 * 
	 * @param sqlBuffer
	 *            StringBuffer sql缓冲区
	 */
	private void putTableToSql(StringBuffer sqlBuffer) {
		sqlBuffer.append(" FROM WPLAN_PATROLRECORD wpr ");
		sqlBuffer.append(" JOIN WPLAN_EXECUTERESULT wer ");
		sqlBuffer.append(" ON wpr.EXECUTERESULT_ID=wer.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLINFO wp ");
		sqlBuffer.append(" ON wer.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLSUBITEM wps ");
		sqlBuffer.append(" ON wpr.SUBITEM_ID=wps.ID ");
		sqlBuffer.append(" JOIN RS_RESOURCERECORD_V res ");
		sqlBuffer.append(" ON res.ID=wer.RESOURCE_ID  ");
		sqlBuffer.append(" AND res.TYPE=wp.BUSINESS_TYPE ");
		sqlBuffer.append(" WHERE wps.EXCEPTION_VALUE=wpr.SUBITEM_PATROL ");
		sqlBuffer.append(super.getInnerCondition());
	}
}
