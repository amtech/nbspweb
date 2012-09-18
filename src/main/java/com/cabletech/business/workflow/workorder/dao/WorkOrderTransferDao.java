package com.cabletech.business.workflow.workorder.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;

/**
 * 通用工单分发信息Dao
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 修改继承的基类并实现基类中的getSql方法
 * @author 杨隽 2012-02-06 将getSql()方法改为getBusinessTableSql()方法
 * @author 杨隽 2012-02-06 修改getBusinessTableSql()方法（去除与工作流进行级联的sql部分语句）
 * @author 杨隽 2012-02-07 删除deletedTransferAndWorkOrder()和setTaskState()方法
 * 
 */
@Repository
public class WorkOrderTransferDao extends
		WorkOrderBaseDao<WorkOrderTransfer, String> {
	/**
	 * 获取业务表单数据信息的sql语句
	 * 
	 * @return String 业务表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT wo.ID,wo.TASK_CODE,wo.TASK_NAME, ");
		sqlBuf.append(" wo.TASK_TYPE,wo.CREATER,wo.CREATE_DATE,vu.USERNAME AS CREATER_NAME, ");
		sqlBuf.append(" FN_GETNAMEBYCODE(wo.TASK_TYPE,'TASK_CODE') AS TASK_TYPE_DIS, ");
		sqlBuf.append(" to_char(wo.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS, ");
		sqlBuf.append(" to_char(wo.OVERTIME_SET,'yyyy-mm-dd hh24:mi:ss') AS OVERTIME_SET_DIS, ");
		sqlBuf.append(" to_char(wo.OVERTIME_SET,'yyyy-mm-dd hh24:mi:ss') AS PROCESS_TIME_DIS, ");
		sqlBuf.append(" decode(sign(wo.OVERTIME_SET-SYSDATE),-1,'Y','N') AS IS_OVERTIME, ");
		sqlBuf.append(" decode(sign(wo.OVERTIME_SET-SYSDATE-2/24),-1,'Y','N') AS IS_DEADLINE, ");
		sqlBuf.append(" wt.TARGET_PRINCIPAL,wt.ID AS PID,wt.TASK_STATE,vua.USERNAME AS TARGET_PRINCIPAL_NAME, ");
		sqlBuf.append(" decode(wt.TASK_STATE, ");
		sqlBuf.append(" '2','签收','3','回单','4','验证审核', ");
		sqlBuf.append(" '5','结束','6','已取消','a','退单', ");
		sqlBuf.append(" 'b','退单审核','草稿') AS PROINST_STATE ");
		sqlBuf.append(" FROM WTASK_ORDER wo ");
		sqlBuf.append(" JOIN WTASK_TRANSFER wt ON wo.ID=wt.TASK_ID ");
		sqlBuf.append(" LEFT JOIN VIEW_USERINFO vu ON vu.SID=wo.CREATER ");
		sqlBuf.append(" LEFT JOIN VIEW_USERINFO vua ON vua.SID=wt.TARGET_PRINCIPAL ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}