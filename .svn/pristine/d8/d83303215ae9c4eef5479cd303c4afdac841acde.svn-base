package com.cabletech.business.workflow.accident.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.accident.model.MmAccident;

/**
 * 隐患实体DAO
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@Repository
public class MmAccidentDao extends MmAccidentBaseDao<MmAccident, String> {
	/**
	 * 获取隐患列表的sql
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT MAI.*, ");
		sqlBuf.append(" RZ.ZYMC AS RES_NAME,DIC.LABLE AS BUSINESS_TYPE_NAME, ");
		sqlBuf.append(" MAT.TYPE_NAME AS ACCIDENT_TYPE_DIS,VU.USERNAME AS CREATER_NAME, ");
		sqlBuf.append(" TO_CHAR(MAI.CREATE_TIME,'yyyy-mm-dd hh24:mi:ss') AS CREATE_TIME_DIS");
		sqlBuf.append(" FROM MM_ACCIDENT_INFO MAI ");
		sqlBuf.append(" LEFT JOIN RES_ZDXX RZ ON RZ.XTBH=MAI.RESOURCE_ID ");
		sqlBuf.append(" JOIN BASE_SYSDICTIONARY DIC ON DIC.CODEVALUE=MAI.BUSINESS_TYPE ");
		sqlBuf.append(" AND DIC.COLUMNTYPE='BUSINESSTYPE' ");
		sqlBuf.append(" LEFT JOIN MM_ACCIDENT_TYPE MAT ON MAT.ID=MAI.ACCIDENT_TYPE ");
		sqlBuf.append(" LEFT JOIN VIEW_USERINFO VU ON VU.SID=MAI.CREATER ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
