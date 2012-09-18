package com.cabletech.business.workflow.workorder.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 超时工单查询Dao
 * 
 * @author XXX 2012-03-15 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class WorkOrderHandledOverTimeDao extends BaseDao {
	/**
	 * 获取超时工单信息列表
	 * 
	 * @param page
	 *            Page 分页数据信息
	 * @param condition
	 *            String 查询条件
	 * @return Page 分页数据信息
	 */
	@SuppressWarnings("unchecked")
	public Page getList(Page page, String condition) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select ui.orgid as contractorid,ui.orgname as contractorname, ");
		sql.append(" wo.task_name,wo.create_date,ui_s.username,wo.overtime_set as deadline, ");
		sql.append(" ( ");
		sql.append(" select max(wr.create_date) from wtask_receipt wr ");
		sql.append(" where wr.transfer_id=wt.id ");
		sql.append(" group by wr.transfer_id ");
		sql.append(" ) as reply_time, ");
		sql.append(" FN_GETNAMEBYCODE(wo.task_type,'TASK_CODE') as task_type, ");
		sql.append(" to_char((( ");
		sql.append(" select max(wr.create_date) from wtask_receipt wr ");
		sql.append(" where wr.transfer_id=wt.id ");
		sql.append(" group by wr.transfer_id ");
		sql.append(" )-wo.overtime_set)*24*60,'FM9999999999') as overtime_length ");
		sql.append(" from wtask_order wo ");
		sql.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sql.append(" join view_userinfo ui on ui.sid=wt.target_principal ");
		sql.append(" join view_userinfo ui_s on ui_s.sid=wt.source_principal ");
		sql.append(" where sign(wo.overtime_set- ");
		sql.append(" ( ");
		sql.append(" select max(wr.create_date) from wtask_receipt wr ");
		sql.append(" where wr.transfer_id=wt.id ");
		sql.append(" group by wr.transfer_id ");
		sql.append(" ) ");
		sql.append(" )=-1 ");
		sql.append(" and wt.task_state='");
		sql.append(WorkOrder.WORKORDER_END_STATE);
		sql.append("' ");
		sql.append(condition);
		return super.findSQLPage(page, sql.toString());
	}
}
