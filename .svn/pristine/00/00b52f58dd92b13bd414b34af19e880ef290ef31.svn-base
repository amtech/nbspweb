package com.cabletech.business.workflow.electricity.security.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警派单信息DAO
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 进行后台sql的细化
 * 
 */
@Repository
public class OeDispatchTaskDao extends
		ElectricitySecurityBaseDao<OeDispatchTask, String> {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT odt.*,odt.STATE AS TASK_STATE, ");
		sqlBuffer.append(" decode(sign(odt.HANDLE_LIMIT-SYSDATE),-1,'Y','N') AS IS_OVERTIME, ");
		sqlBuffer.append(" decode(sign(odt.HANDLE_LIMIT-SYSDATE-2/24),-1,'Y','N') AS IS_DEADLINE, ");
		sqlBuffer.append(" to_char(odt.HANDLE_LIMIT, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS PROCESS_TIME_DIS, ");
		sqlBuffer.append(" to_char(odt.BLACKOUT_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS BLACKOUT_TIME_DIS, ");
		sqlBuffer.append(" to_char(odt.DISPATCH_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS DISPATCH_TIME_DIS, ");
		sqlBuffer.append(" to_char(odt.CREATE_DATE, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS CREATE_DATE_DIS, ");
		sqlBuffer.append(" res.NAME AS RS_NAME,FN_GETNAMEBYCODE(res.STATION_TYPE,'ZDLX') AS RS_TYPE, ");
		sqlBuffer.append(" vo.ORGNAME AS ORG_NAME,vu.USERNAME AS CREATER_NAME ");
		sqlBuffer.append(" FROM OE_DISPATCHTASK odt ");
		sqlBuffer.append(" JOIN RS_RESOURCERECORD_V res ");
		sqlBuffer.append(" ON res.TYPE='");
		sqlBuffer.append(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		sqlBuffer.append("' ");
		sqlBuffer.append(" AND res.ID=odt.STATION_ID ");
		sqlBuffer.append(" JOIN VIEW_ORG vo ");
		sqlBuffer.append(" ON vo.ID=odt.MAINTENANCE_ID ");
		sqlBuffer.append(" JOIN VIEW_USERINFO vu ");
		sqlBuffer.append(" ON vu.SID=odt.CREATOR ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}
}
