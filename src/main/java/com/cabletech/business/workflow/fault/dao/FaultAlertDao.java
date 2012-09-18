package com.cabletech.business.workflow.fault.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.fault.model.FaultAlert;

/**
 * 故障告警单DAO
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2012-02-07 将getSql()方法改为getBusinessTableSql()方法并修改传入的参数
 * @author 杨隽 2012-07-18 修改getBusinessTableSql()中的sql
 * 
 */
@Repository
public class FaultAlertDao extends FaultBaseDao<FaultAlert, String> {
	/**
	 * 获取故障告警单的sql
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT t.*,rr.NAME RSNAME, ");
		sqlBuf.append(" to_char(t.TROUBLE_TIME,'yyyy-mm-dd hh24:mi:ss') AS TROUBLE_TIME_DIS, ");
		sqlBuf.append(" decode(t.IGNORE_STATE, ");
		sqlBuf.append(" '");
		sqlBuf.append(FaultAlert.IGNORED_STATE);
		sqlBuf.append("','已忽略', ");
		sqlBuf.append(" '");
		sqlBuf.append(FaultAlert.DISPATCHED_STATE);
		sqlBuf.append("','已派单', ");
		sqlBuf.append(" '");
		sqlBuf.append(FaultAlert.FINISHED_STATE);
		sqlBuf.append("','已完成', ");
		sqlBuf.append(" '");
		sqlBuf.append(FaultAlert.CANCELED_STATE);
		sqlBuf.append("','已取消', ");
		sqlBuf.append(" '未派单') AS IGNORE_STATE_DIS,");
		sqlBuf.append(" FN_GETNAMEBYCODE(t.find_type,'FIND_TYPE') FIND_TYPENAME ");
		sqlBuf.append(" FROM WTROUBLE_ALARM t ");
		sqlBuf.append(" LEFT JOIN RS_RESOURCERECORD_V rr ");
		sqlBuf.append(" ON t.STATION_ID=rr.ID AND t.BUSINESS_TYPE=rr.TYPE ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
