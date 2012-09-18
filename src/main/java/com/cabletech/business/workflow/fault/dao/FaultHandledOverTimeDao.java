package com.cabletech.business.workflow.fault.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 故障处理超时Dao
 * 
 * @author 杨隽 2012-03-15 创建
 */
@SuppressWarnings("rawtypes")
@Repository
public class FaultHandledOverTimeDao extends BaseDao {
	/**
	 * 获取故障处理超时信息列表
	 * 
	 * @param page
	 *            Page 分页信息数据
	 * @param condition
	 *            String 查询条件
	 * @return Page 分页信息数据
	 */
	@SuppressWarnings("unchecked")
	public Page getList(Page page, String condition) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select ui.id as contractorid,ui.name as contractorname, ");
		sql.append(" wa.trouble_title,wa.trouble_time,wr.reply_time,wt.deadline as deadline, ");
		sql.append(" to_char((wr.reply_time-wt.deadline)*24*60,'FM9999999999') as overtime_length ");
		sql.append(" from wtrouble_alarm wa ");
		sql.append(" join wtrouble_sendtask wt on wa.id=wt.alarm_id ");
		sql.append(" join wtrouble_reply wr on wt.id=wr.task_id ");
		sql.append(" join view_org ui on wt.maintenance_id=ui.id ");
		sql.append(" where sign(wt.deadline-wr.reply_time)=-1 ");
		sql.append(" and wa.ignore_state='");
		sql.append(FaultAlert.FINISHED_STATE);
		sql.append("' ");
		sql.append(condition);
		return super.findSQLPage(page, sql.toString());
	}
}
