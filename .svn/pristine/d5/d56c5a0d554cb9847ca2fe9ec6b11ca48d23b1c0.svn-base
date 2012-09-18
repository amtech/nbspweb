package com.cabletech.business.workflow.electricity.security.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警信息DAO
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 进行后台sql的细化
 * 
 */
@Repository
public class OeOutageAlarmDao extends
		ElectricitySecurityBaseDao<OeOutageAlarm, String> {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT ooa.*, ");
		sqlBuffer.append(" to_char(ooa.BLACKOUT_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS BLACKOUT_TIME_DIS, ");
		sqlBuffer.append(" to_char(ooa.CREATE_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS CREATE_TIME_DIS, ");
		sqlBuffer.append(" res.NAME AS RS_NAME,FN_GETNAMEBYCODE(res.STATION_TYPE,'ZDLX') AS RS_TYPE ");
		sqlBuffer.append(" FROM OE_OUTAGEALARM ooa ");
		sqlBuffer.append(" JOIN RS_RESOURCERECORD_V res ");
		sqlBuffer.append(" ON res.TYPE='");
		sqlBuffer.append(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		sqlBuffer.append("' ");
		sqlBuffer.append(" AND res.ID=ooa.STATION_NAME ");
		sqlBuffer.append(" JOIN ReS_MAINTENANCE rm ");
		sqlBuffer.append(" ON  rm.RS_ID=ooa.STATION_NAME  ");
		//sqlBuffer.append(" AND rm.RS_TYPE=ooa.STATION_TYPE");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}
}
