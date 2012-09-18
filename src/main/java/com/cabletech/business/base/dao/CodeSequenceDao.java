package com.cabletech.business.base.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.CodeSequence;

/**
 * 序号生成记录信息DAO
 * 
 * @author 杨隽 2012-01-12 创建
 * 
 */
@Repository
public class CodeSequenceDao extends CommonBaseDao<CodeSequence, String> {
	/**
	 * 获取序号生成记录信息的sql
	 */
	public String getSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT ID,TABLE_NAME,DEPT_ID,DEPT_SHORT, ");
		sqlBuf.append(" YEAR_MONTH,MAX_ID,PATTERN,REMARK ");
		sqlBuf.append(" FROM CODE_SEQUENCE ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
