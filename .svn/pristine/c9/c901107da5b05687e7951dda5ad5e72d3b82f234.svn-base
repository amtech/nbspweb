package com.cabletech.business.base.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.LocaleProcess;

/**
 * 现场处理过程DAO
 * 
 * @author 杨隽 2012-01-09 创建
 * @author 杨隽 2012-05-08 修改现场处理状态的值取自数据字典表
 * 
 */
@Repository
public class LocaleProcessDao extends
		CommonBaseDao<LocaleProcess, String> {
	/**
	 * 获取现场处理过程的sql
	 */
	public String getSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT p.*,bp.NAME AS PERSON_NAME, ");
		sqlBuf.append(" dic.LABLE AS PROCESS_STATE_DIS, ");
		sqlBuf.append(" to_char(p.PROCESS_TIME,'yyyy-mm-dd hh24:mi:ss') AS PROCESS_TIME_DIS ");
		sqlBuf.append(" FROM WLOCALE_PROCESS p ");
		sqlBuf.append(" JOIN BASE_PERSON bp ON p.PROCESSER=bp.ID ");
		sqlBuf.append(" JOIN BASE_SYSDICTIONARY dic ");
		sqlBuf.append(" ON dic.CODEVALUE=p.PROCESS_STATE ");
		sqlBuf.append(" AND dic.COLUMNTYPE='SCENE_STATUS' ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
