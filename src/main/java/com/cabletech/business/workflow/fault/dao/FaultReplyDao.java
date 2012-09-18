package com.cabletech.business.workflow.fault.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.fault.model.FaultReply;

/**
 * 故障回单DAO
 * 
 * @author 杨隽 2011-10-27 创建
 * 
 */
@Repository
public class FaultReplyDao extends FaultBaseDao<FaultReply, String> {
	/**
	 * 获取故障回单的sql
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		return sqlBuf.toString();
	}
}
