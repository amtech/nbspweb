package com.cabletech.business.workflow.workorder.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.common.base.BaseDao;

/**
 * 首页工单统计Dao
 * 
 * @author 杨隽 2012-03-09 创建
 * 
 */
@Repository
@SuppressWarnings("rawtypes")
public class WorkOrderStatisticDao extends BaseDao {
	/**
	 * 日志输出
	 */
	protected final Logger logger = Logger.getLogger("WorkOrderStatisticDao");

	/**
	 * 获取月份初始化结果列表
	 * 
	 * @return List<Map<String, Object>> 月份初始化结果列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMonthResultList() {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select lpad(level,2,0) as m, ");
		sqlBuffer.append(" '0' as total_num, ");
		sqlBuffer.append(" '0' as total_hour, ");
		sqlBuffer.append(" '0' as overtime_num ");
		sqlBuffer.append(" from dual ");
		sqlBuffer.append(" connect by level<13 ");
		sqlBuffer.append(" order by level ");
		logger.info("获取月份初始化结果列表：" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 根据查询条件获取工单总数统计结果列表
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return List<Map<String, Object>> 工单总数统计结果列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWorkOrderTotalNumberResultList(
			String condition) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select to_char(wo.create_date,'MM') as mo, ");
		sqlBuffer.append(" to_char(count(wt.id)) as total_num ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(condition);
		sqlBuffer.append(" group by to_char(wo.create_date,'MM') ");
		sqlBuffer.append(" order by to_char(wo.create_date,'MM') ");
		logger.info("根据查询条件获取工单总数统计结果列表:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 根据查询条件获取工单历时统计结果列表
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return List<Map<String, Object>> 工单历时统计结果列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWorkOrderTotalHourResultList(
			String condition) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select to_char(wo.create_date,'MM') as mo, ");
		sqlBuffer.append(" to_char(decode(count(wt.id),0,0,sum(( ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) ");
		sqlBuffer
				.append(" -wo.create_date)*24)/count(wt.id)),'FM999999999990.99') as total_hour ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) is not null ");
		sqlBuffer.append(condition);
		sqlBuffer.append(" group by to_char(wo.create_date,'MM') ");
		sqlBuffer.append(" order by to_char(wo.create_date,'MM') ");
		logger.info("根据查询条件获取工单历时统计结果列表:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 根据查询条件获取超时工单数量统计结果列表
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return List<Map<String, Object>> 超时工单数量统计结果列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWorkOrderOvertimeNumberResultList(
			String condition) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select to_char(wo.create_date,'MM') as mo,to_char(sum( ");
		sqlBuffer.append(" decode( ");
		sqlBuffer.append(" sign(wo.overtime_set- ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ),-1,1,0 ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" )) as overtime_num ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) is not null ");
		sqlBuffer.append(condition);
		sqlBuffer.append(" group by to_char(wo.create_date,'MM') ");
		sqlBuffer.append(" order by to_char(wo.create_date,'MM') ");
		logger.info("根据查询条件获取超时工单数量统计结果列表:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取超时工单数量（按区域）
	 * 
	 * @param condition
	 *            String 查询条件
	 * @param regionId
	 *            String 区域编号
	 * @return List<Map<String, Object>> 超时工单数量
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOvertimeWorkOrderNumberByRegion(
			String condition, String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" sum(decode(sign_,-1,1,0)) as overtime_w_num from ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select regionid,regionname  ");
		sqlBuffer.append(" from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid) r ");
		sqlBuffer.append(" join ");
		sqlBuffer.append(getOvertimeWorkOrderNumberSql(condition));
		sqlBuffer.append(" on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		logger.info(":" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取超时工单数量（按组织）
	 * 
	 * @param condition
	 *            String 查询条件
	 * @param regionId
	 *            String 区域编号
	 * @param orgId
	 *            String 组织编号
	 * @return List<Map<String, Object>> 超时工单数量
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOvertimeWorkOrderNumberByOrg(
			String condition, String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select vo.id as gid,vo.name as gname, ");
		sqlBuffer.append(" sum(decode(sign_,-1,1,0)) as overtime_w_num from ");
		sqlBuffer.append(" view_org vo ");
		sqlBuffer.append(" join ");
		sqlBuffer.append(getOvertimeWorkOrderNumberSql(condition));
		sqlBuffer.append(" on result_.orgid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		if (StringUtils.isNotBlank(regionId)) {
			sqlBuffer.append(" and vo.regionid='");
			sqlBuffer.append(regionId);
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(orgId)) {
			sqlBuffer.append(" and vo.id='");
			sqlBuffer.append(orgId);
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" group by vo.id,vo.name ");
		logger.info(":" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取超时工单数量的sql
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return String 超时工单数量的sql
	 */
	private String getOvertimeWorkOrderNumberSql(String condition) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select vu.orgid,vu.regionid,sign(wo.overtime_set- ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ) as sign_ ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(condition);
		sqlBuffer.append(" ) result_ ");
		return sqlBuffer.toString();
	}

	/**
	 * 获取工作流的任务数据信息
	 * 
	 * @param sqlBuffer
	 *            StringBuffer 工作流的任务数据信息
	 */
	private void getJbpmTaskDataSql(StringBuffer sqlBuffer) {
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select jd.deploymentkey as key, ");
		sqlBuffer
				.append(" decode(jhpe.bzid,null,jhp.key_,jhpe.bzid) as bzid, ");
		sqlBuffer.append(" jhp.id_ as did, ");
		sqlBuffer
				.append(" to_date(to_char(jha.end_,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss') as end_time, ");
		sqlBuffer.append(" jha.activity_name_ as task_name ");
		sqlBuffer.append(" from JBPM4_HIST_PROCINST jhp ");
		sqlBuffer.append(" left join jbpm4_defineinfo jd  ");
		sqlBuffer.append(" on jhp.procdefid_=jd.processdefinitionid ");
		sqlBuffer.append(" left join jbpm4_hist_pi_ext jhpe  ");
		sqlBuffer.append(" on jhp.id_=jhpe.piid ");
		sqlBuffer.append(" left join jbpm4_hist_actinst jha  ");
		sqlBuffer.append(" on jhp.id_=jha.execution_ and jha.type_ = 'task' ");
		sqlBuffer.append(" where jhp.state_ = 'ended' ");
		sqlBuffer.append(" order by jhp.id_,jha.end_ ");
		sqlBuffer.append(" ) jbpm_ ");
	}
}
