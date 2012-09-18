package com.cabletech.business.analysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 超时工单统计Dao
 * 
 * @author 杨隽 2012-03-22 创建
 * @author 杨隽 2012-03-30 提取公共方法
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class WorkOrderProcessOverTimeRateDao extends WorkOrderAnalyseBaseDao {
	/**
	 * 进行超时工单统计（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	public List getWorkOrderProcessOverTimeRateListByOrg(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select vo.id,vo.name,vo.parentid, ");
		sqlBuffer.append(" '0' as level_,'false' as isLeaf, ");
		sqlBuffer.append(" 'false' as expanded,'true' as loaded, ");
		sqlBuffer.append(" result_.*  from ( ");
		sqlBuffer.append(" select v.* from view_org v  ");
		sqlBuffer.append(" where v.regionid=any( ");
		sqlBuffer.append(" select r.regionid from view_region r  ");
		sqlBuffer.append(" start with r.regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior r.regionid=r.parentid ");
		sqlBuffer.append(" ) ");
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and v.id='");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" ) vo ");
		sqlBuffer.append(" join ( ");
		sqlBuffer.append(getWorkOrderProcessOverTimeRateSqlByOrg(parameters));
		sqlBuffer.append(" ) result_ on vo.id=result_.orgid ");
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取超时工单统计的sql（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private StringBuffer getWorkOrderProcessOverTimeRateSqlByOrg(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select all_t.orgid, ");
		sqlBuffer.append(" count(all_t.pid) as all_num, ");
		sqlBuffer.append(" count(overtime_t.pid) as overtime_num, ");
		sqlBuffer
				.append(" to_char(sum(overtime_t.len),'FM99999990.099') as overtime_len, ");
		sqlBuffer
				.append(" to_char(decode(count(overtime_t.pid),0,0,sum(overtime_t.len)/count(overtime_t.pid)),'FM99999990.099') as overtime_avg_len, ");
		sqlBuffer
				.append(" to_char(decode(count(all_t.pid),0,0,100*count(overtime_t.pid)/count(all_t.pid)),'FM990.09') as overtime_rate ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(getAllWorkOrderSql(parameters));
		sqlBuffer.append(" ) all_t ");
		sqlBuffer.append(" left join ( ");
		sqlBuffer.append(getOverTimeWorkOrderSql(parameters));
		sqlBuffer.append(" ) overtime_t on overtime_t.pid=all_t.pid ");
		sqlBuffer.append(" group by all_t.orgid ");
		return sqlBuffer;
	}

	/**
	 * 获取所有工单列表的sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private String getAllWorkOrderSql(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select wo.id,wo.task_code,wo.task_name, ");
		sqlBuffer.append(" wo.task_type,wo.creater,wo.create_date, ");
		sqlBuffer.append(" wo.overtime_set,wt.target_principal, ");
		sqlBuffer.append(" wt.id as pid,wt.task_state,vu.orgid ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" left join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where wt.task_state='");
		sqlBuffer.append(END_STATE);
		sqlBuffer.append("' ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) is not null ");
		getWorkOrderCondition(parameters, sqlBuffer);
		return sqlBuffer.toString();
	}

	/**
	 * 获取所有处理超时工单列表的sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private String getOverTimeWorkOrderSql(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select distinct id,task_code,task_name,  task_type,creater,create_date,  overtime_set,target_principal,task_type_dis,create_date_dis,pid,task_state,orgid,len from ( ");
		getWorkOrderSelectSql(parameters, sqlBuffer);
		sqlBuffer.append(" and sign(wo.overtime_set-( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ))<0 ");
		sqlBuffer.append(" ) order by len desc ");
		return sqlBuffer.toString();
	}

	/**
	 * 获取所有通用工单列表的select sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getWorkOrderSelectSql(Map<String, String> parameters,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select wo.id,wo.task_code,wo.task_name, ");
		sqlBuffer.append(" wo.task_type,wo.creater,wo.create_date, ");
		sqlBuffer.append(" wo.overtime_set,wt.target_principal, ");
		sqlBuffer
				.append(" FN_GETNAMEBYCODE(wo.task_type,'TASK_CODE') as task_type_dis, ");
		sqlBuffer
				.append(" to_char(wo.create_date,'yyyy-mm-dd hh:mi:ss') as create_date_dis, ");
		sqlBuffer.append(" wt.id as pid,wt.task_state,vu.orgid, ");
		sqlBuffer.append(" to_char((( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" )-wo.CREATE_DATE)*24,'FM99999990.099') as len  ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer
				.append(" left join view_userinfo vu on vu.sid=wt.target_principal ");
		sqlBuffer.append(" where wt.TASK_STATE='");
		sqlBuffer.append(END_STATE);
		sqlBuffer.append("' ");
		getWorkOrderCondition(parameters, sqlBuffer);
	}
}
