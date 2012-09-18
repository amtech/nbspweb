package com.cabletech.business.workflow.workorder.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 及时工单查询Dao
 * 
 * @author XXX 2012-03-15 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class WorkOrderHandledOnTimeDao extends BaseDao {
	/**
	 * 获取及时工单信息列表
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
		sql.append(" count(distinct wt.id) as workorder_sum,sum(decode(sign(wo.overtime_set- ");
		sql.append(" ( ");
		sql.append(" select max(wr.create_date) from wtask_receipt wr ");
		sql.append(" where wr.transfer_id=wt.id ");
		sql.append(" group by wr.transfer_id ");
		sql.append(" ) ");
		sql.append(" ),-1,1,0)) as overtime_workorder_sum ");
		sql.append(" from wtask_order wo ");
		sql.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sql.append(" join view_userinfo ui on ui.sid=wt.target_principal ");
		sql.append(" where wt.task_state='");
		sql.append(WorkOrder.WORKORDER_END_STATE);
		sql.append("' ");
		sql.append(condition);
		sql.append(" group by ui.orgid,ui.orgname ");
		return super.findSQLPage(page, sql.toString());
	}
}
