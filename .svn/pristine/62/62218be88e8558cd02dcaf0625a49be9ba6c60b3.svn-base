package com.cabletech.business.workflow.workorder.dao;

import org.springframework.stereotype.Repository;
import com.cabletech.business.workflow.workorder.model.WorkOrder;

/**
 * 通用工单信息Dao
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 修改继承的基类并实现基类中的getSql方法
 * @author 杨隽 2012-02-06 将getSql()方法改为getBusinessTableSql()方法
 * 
 */
@Repository
public class WorkOrderDao extends WorkOrderBaseDao<WorkOrder, String> {
	/**
	 * 获取业务表单数据信息的sql语句
	 * 
	 * @return String 业务表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT DISTINCT wo.ID,wo.TASK_CODE,wo.TASK_NAME, ");
		sqlBuf.append(" wo.TASK_TYPE,wo.CREATER,wo.CREATE_DATE,vu.USERNAME AS CREATER_NAME, ");
		sqlBuf.append(" FN_GETNAMEBYCODE(wo.TASK_TYPE,'TASK_CODE') AS TASK_TYPE_DIS, ");
		sqlBuf.append(" to_char(wo.OVERTIME_SET,'yyyy-mm-dd hh24:mi:ss') AS OVERTIME_SET_DIS, ");
		sqlBuf.append(" to_char(wo.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS ");
		sqlBuf.append(" FROM WTASK_ORDER wo ");
		sqlBuf.append(" JOIN WTASK_TRANSFER wt ON wo.ID=wt.TASK_ID ");
		sqlBuf.append(" LEFT JOIN VIEW_USERINFO vu ON vu.SID=wo.CREATER ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}

	/**
	 * 根据工单编号删除不存在工单分发信息的工单
	 * 
	 * @param workOrderId
	 *            String 工单编号
	 */
	public void deleteWorkOrder(String workOrderId) {
		StringBuffer hql = new StringBuffer("");
		hql.append(" DELETE WorkOrder t ");
		hql.append(" WHERE NOT EXISTS( ");
		hql.append(" SELECT 1 FROM WorkOrderTransfer wt ");
		hql.append(" WHERE wt.taskId=t.id AND t.id='");
		hql.append(workOrderId);
		hql.append("') ");
		hql.append(" AND t.id='");
		hql.append(workOrderId);
		hql.append("'");
		super.batchHQLExecute(hql.toString());
	}
}