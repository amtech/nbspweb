package com.cabletech.business.workflow.fault.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.fault.model.FaultAudit;

/**
 * 故障回单审核DAO
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2012-02-07 将getSql()方法改为getBusinessTableSql()方法并修改传入的参数
 * 
 */
@Repository
public class FaultAuditDao extends FaultBaseDao<FaultAudit, String> {
	/**
	 * 获取故障回单审核的sql
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
