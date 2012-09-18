package com.cabletech.business.workflow.electricity.security.dao;

import org.springframework.stereotype.Repository;

/**
 * 搜索油机信息DAO
 * 
 * @author 杨隽 2012-05-09 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class OeOilengineSearchDao extends ElectricitySecurityBaseDao {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT t_.*, ");
		sqlBuffer.append(" substr( ");
		sqlBuffer.append(" t_.STATION_INFO, ");
		sqlBuffer.append(" instr(t_.STATION_INFO,'_')+1 ");
		sqlBuffer.append(" ) AS BASESTATION_ID, ");
		sqlBuffer.append(" to_char(t_.DISTANCE,'FM999990.0999') ");
		sqlBuffer.append(" AS DISTANCE_DIS ");
		sqlBuffer.append(" FROM ( ");
		sqlBuffer.append(" SELECT ooe.*, ");
		sqlBuffer.append(" dic01.LABLE AS OIL_TYPE_DIS, ");
		sqlBuffer.append(" dic02.LABLE AS STATE_DIS, ");
		sqlBuffer.append(" sqrt( ");
		sqlBuffer.append(" power((ooe.CT_X-res.CT_X),2) ");
		sqlBuffer.append(" + ");
		sqlBuffer.append(" power((ooe.CT_Y-res.CT_Y),2) ");
		sqlBuffer.append(" ) AS DISTANCE, ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" SELECT MAX(odt.ID||'_'||STATION_ID)  ");
		sqlBuffer.append(" FROM OE_DISPATCHTASK odt  ");
		sqlBuffer.append(" WHERE odt.OILENGINE_ID=ooe.ID ");
		sqlBuffer.append(" ) AS STATION_INFO, "); 
		sqlBuffer.append(" vr.REGIONNAME,vo.ORGNAME AS ORG_NAME ");
		sqlBuffer.append(" FROM OE_OILENGINE ooe ");
		sqlBuffer.append(" JOIN VIEW_REGION vr ");
		sqlBuffer.append(" ON vr.REGIONID=ooe.DISTRICT ");
		sqlBuffer.append(" JOIN BASE_SYSDICTIONARY dic01 ");
		sqlBuffer.append(" ON dic01.CODEVALUE=ooe.OIL_TYPE ");
		sqlBuffer.append(" AND dic01.COLUMNTYPE='OIL_TYPE' ");
		sqlBuffer.append(" JOIN BASE_SYSDICTIONARY dic02 ");
		sqlBuffer.append(" ON dic02.CODEVALUE=ooe.STATE ");
		sqlBuffer.append(" AND dic02.COLUMNTYPE='OLIENGINE_USE'  ");
		sqlBuffer.append(" JOIN VIEW_ORG vo ");
		sqlBuffer.append(" ON vo.ID=ooe.MAINTENANCE_ID ");
		putTableToSql(sqlBuffer);
		sqlBuffer.append(" ) t_ ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}

	/**
	 * 将业务数据表的from语句放到sql缓冲区中
	 * 
	 * @param sqlBuffer
	 *            StringBuffer sql缓冲区
	 */
	private void putTableToSql(StringBuffer sqlBuffer) {
		sqlBuffer.append(" JOIN RS_RESOURCERECORD_V res ");
		sqlBuffer.append(super.getInnerCondition());
	}
}
