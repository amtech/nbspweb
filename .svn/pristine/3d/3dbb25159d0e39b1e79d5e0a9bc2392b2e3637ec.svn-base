package com.cabletech.business.workflow.wmaintain.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.wmaintain.model.WMaintainResult;

/**
 * 站点异常项及处理结果Dao
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deleteResult()方法
 * @author 杨隽 2012-05-14 修改getBusinessTableSql()方法（级联巡检组视图并添加维护日期显示列和巡检组名称列）
 * 
 */
@Repository
public class WMaintainResultDao extends
		WMaintainBaseDao<WMaintainResult, String> {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT wr.*, ");
		sqlBuffer.append(" to_char(wr.MAINTAIN_DATE,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS MAINTAIN_DATE_DIS, ");
		sqlBuffer.append(" ws.RS_ID,ws.RS_TYPE,rm.NAME AS RS_NAME, ");
		sqlBuffer.append(" wps.SUBITEM_NAME,rm.ADDRESS, ");
		sqlBuffer.append(" wp.PATROL_GROUP,wp.BUSINESS_TYPE, ");
		sqlBuffer.append(" wpr.SUBITEM_PATROL, ");
		sqlBuffer.append(" vpg.NAME AS PATROLGROUP_NAME ");
		sqlBuffer.append(" FROM WMAINTAIN_RESULT wr ");
		sqlBuffer.append(" JOIN WMAINTAIN_SITE ws ON wr.MAINTAIN_ID=ws.ID ");
		sqlBuffer.append(" JOIN WMAINTAIN_PLAN wp ON ws.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLSUBITEM wps ON wps.ID=wr.ITEM_ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLRECORD wpr ");
		sqlBuffer.append(" ON wr.PATROLRECORD_ID=wpr.ID ");
		sqlBuffer.append(" LEFT JOIN VIEW_PATROLGROUP vpg ");
		sqlBuffer.append(" ON wr.PATROLMAN_ID=vpg.ID ");
		sqlBuffer.append(" LEFT JOIN RS_RESOURCERECORD_V rm ");
		sqlBuffer.append(" ON rm.ID=ws.RS_ID ");
		sqlBuffer.append(" AND rm.TYPE=wp.BUSINESS_TYPE ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}

	/**
	 * 根据计划编号删除站点异常项及处理结果信息
	 * 
	 * @param planId
	 *            String 计划编号
	 */
	public void deleteResult(String planId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" DELETE FROM WMAINTAIN_RESULT wr ");
		sqlBuffer.append(" WHERE EXISTS( ");
		sqlBuffer.append(" SELECT ws.ID FROM WMAINTAIN_SITE ws ");
		sqlBuffer.append(" WHERE wr.MAINTAIN_ID=ws.ID ");
		sqlBuffer.append(" AND ws.PLAN_ID='");
		sqlBuffer.append(planId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" ) ");
		super.getJdbcTemplate().execute(sqlBuffer.toString());
	}
}
