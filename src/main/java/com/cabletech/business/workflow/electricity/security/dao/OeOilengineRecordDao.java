package com.cabletech.business.workflow.electricity.security.dao;

import org.springframework.stereotype.Repository;

/**
 * 油机发电记录信息DAO
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-07 进行后台sql的细化
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class OeOilengineRecordDao extends ElectricitySecurityBaseDao {
	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT oor.*, ");
		sqlBuffer.append(" oor.END_DEGREE-oor.START_DEGREE AS GEN_POWER, ");
		sqlBuffer.append(" ooe.OILENGINE_CODE,ooe.OILENGINE_MODEL, ");
		sqlBuffer.append(" ooe.RATION_POWER,ooe.STANDARD_OILWEAR, ");
		sqlBuffer.append(" ooe.PHONE,vu.USERNAME AS PROCESSOR_NAME, ");
		sqlBuffer.append(" to_char(SETOUT_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS SETOUT_TIME_DIS, ");
		sqlBuffer.append(" to_char(GEN_ELE_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS GEN_ELE_TIME_DIS, ");
		sqlBuffer.append(" to_char(END_TIME, ");
		sqlBuffer.append(" 'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS END_TIME_DIS ");
		sqlBuffer.append(" FROM OE_OILENGINERECORD oor ");
		sqlBuffer.append(" JOIN OE_OILENGINE ooe ");
		sqlBuffer.append(" ON oor.OILENGINE_ID=ooe.ID ");
		sqlBuffer.append(" JOIN VIEW_USERINFO vu ");
		sqlBuffer.append(" ON vu.SID=oor.PROCESSER ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}
}
