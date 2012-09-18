package com.cabletech.business.workflow.workorder.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.workorder.model.WorkOrderReply;

/**
 * 通用工单回复信息Dao
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-01-09 实现getSql方法
 * @author 杨隽 2012-02-06 将getSql()方法改为getBusinessTableSql()方法
 * 
 */
@Repository
public class WorkOrderReplyDao extends WorkOrderBaseDao<WorkOrderReply, String> {
	/**
	 * 获取业务表单数据信息的sql语句
	 * 
	 * @return String 业务表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT wr.ID,wr.REMARK,wr.CREATE_DATE,wr.CREATER,vu.USERNAME AS CREATER_NAME, ");
		sqlBuf.append(" to_char(wr.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS ");
		sqlBuf.append(" FROM WTASK_RECEIPT wr ");
		sqlBuf.append(" JOIN WTASK_TRANSFER wt ON wr.TRANSFER_ID=wt.ID ");
		sqlBuf.append(" JOIN WTASK_ORDER wo ON wr.TASK_ID=wo.ID ");
		sqlBuf.append(" LEFT JOIN VIEW_USERINFO vu ON vu.SID=wo.CREATER ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}
