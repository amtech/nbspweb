package com.cabletech.business.workflow.fault.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.fault.model.FaultDispatch;

/**
 * 故障派单DAO
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2012-02-07 将getSql()方法改为getBusinessTableSql()方法并修改传入的参数
 * 
 */
@Repository
public class FaultDispatchDao extends FaultBaseDao<FaultDispatch, String> {
	/**
	 * 获取故障派单的sql
	 * 
	 * @return String 生成后的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT t.ID AS DISPATCH_ID,wa.*,'' AS TASK_ID,'' AS URL, ");
		sqlBuf.append(" t.STATE AS TASK_STATE,t.TASK_CODE,vu.USERNAME AS CREATER_NAME,  ");
		sqlBuf.append(" to_char(t.DEADLINE,'yyyy-mm-dd hh24:mi:ss') AS PROCESS_TIME_DIS, ");
		sqlBuf.append(" decode(sign(t.DEADLINE-SYSDATE),-1,'Y','N') AS IS_OVERTIME, ");
		sqlBuf.append(" decode(sign(t.DEADLINE-SYSDATE-2/24),-1,'Y','N') AS IS_DEADLINE, ");
		sqlBuf.append(" to_char(wa.TROUBLE_TIME,'yyyy-mm-dd hh24:mi:ss') AS TROUBLE_TIME_DIS, ");
		sqlBuf.append(" to_char(t.SEND_TIME,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS, ");
		sqlBuf.append(" FN_GETNAMEBYCODE(wa.find_type,'FIND_TYPE') FIND_TYPENAME,rr.name RSNAME ");
		sqlBuf.append(" FROM WTROUBLE_ALARM wa JOIN WTROUBLE_SENDTASK t ");
		sqlBuf.append(" ON wa.ID=t.ALARM_ID ");
		sqlBuf.append(" join rs_resourcerecord_v rr on wa.station_id=rr.id and wa.business_type=rr.type ");
		sqlBuf.append(" JOIN VIEW_USERINFO vu ON vu.SID=t.CREATER ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
