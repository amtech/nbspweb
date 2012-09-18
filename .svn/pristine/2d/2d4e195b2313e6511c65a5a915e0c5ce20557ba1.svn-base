package com.cabletech.business.workflow.wmaintain.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.wmaintain.model.WMaintainSite;

/**
 * 计划维护站点Dao
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deleteSite()方法
 * 
 */
@Repository
public class WMaintainSiteDao extends WMaintainBaseDao<WMaintainSite, String> {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT ws.*,rm.NAME AS RS_NAME, ");
		sqlBuffer.append(" rm.ADDRESS,DECODE(SIGN(( ");
		sqlBuffer.append(" SELECT SUM(DECODE( ");
		sqlBuffer.append(" wr.MAINTAIN_RESULT,null,1,'',1,0) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" FROM WMAINTAIN_RESULT wr ");
		sqlBuffer.append(" WHERE wr.MAINTAIN_ID=ws.ID ");
		sqlBuffer.append(" )-0),1,'RED', ");
		sqlBuffer.append(" DECODE(SIGN(( ");
		sqlBuffer.append(" SELECT SUM(DECODE( ");
		sqlBuffer.append(" wr.MAINTAIN_RESULT,'");
		sqlBuffer.append(WMaintainSite.CANNOT_MAINTAIN_STATE);
		sqlBuffer.append("',1,0)) ");
		sqlBuffer.append(" FROM WMAINTAIN_RESULT wr ");
		sqlBuffer.append(" WHERE wr.MAINTAIN_ID=ws.ID ");
		sqlBuffer.append(" )-0),1,'YELLOW','BLACK')) AS SITE_MAINTAIN_STATE, ");
		sqlBuffer.append(" wp.PATROL_GROUP,wp.BUSINESS_TYPE ");
		sqlBuffer.append(" FROM WMAINTAIN_SITE ws ");
		sqlBuffer.append(" JOIN WMAINTAIN_PLAN wp ON wp.ID=ws.PLAN_ID ");
		sqlBuffer.append(" LEFT JOIN RS_RESOURCERECORD_V rm ");
		sqlBuffer.append(" ON rm.ID=ws.RS_ID ");
		sqlBuffer.append(" AND rm.TYPE=wp.BUSINESS_TYPE ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}

	/**
	 * 根据计划编号删除计划维护站点信息
	 * 
	 * @param planId
	 *            String 计划编号
	 */
	public void deleteSite(String planId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" DELETE FROM WMAINTAIN_SITE ws ");
		sqlBuffer.append(" WHERE ws.PLAN_ID='");
		sqlBuffer.append(planId);
		sqlBuffer.append("' ");
		super.getJdbcTemplate().execute(sqlBuffer.toString());
	}
}
