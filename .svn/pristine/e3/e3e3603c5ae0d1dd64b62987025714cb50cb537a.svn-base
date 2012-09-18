package com.cabletech.business.analysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.common.util.Page;

/**
 * 工单审核通过率统计Dao
 * 
 * @author 杨隽 2012-03-22 创建
 * @author 杨隽 2012-03-30 提取公共方法
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class WorkOrderApprovePassedRateDao extends WorkOrderAnalyseBaseDao {
	/**
	 * 进行工单审核通过率统计（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	public List getWorkOrderApprovedPassedRateListByOrg(
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
		sqlBuffer.append(getWorkOrderProcessInTimeRateSqlByOrg(parameters));
		sqlBuffer.append(" ) result_ on vo.id=result_.orgid ");
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取n次审核通过工单的列表
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            parameters Map<String, String>
	 * @param approveTimes
	 *            approveTimes int
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page getNTimesApprovePassedWorkOrderList(Page page,
			Map<String, String> parameters, int approveTimes) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select distinct id,task_code,task_name,  task_type,creater,create_date,overtime_set,target_principal,task_type_dis,create_date_dis,pid,task_state,orgid,len from ( ");
		sqlBuffer.append(getAllWorkOrderSql(parameters));
		sqlBuffer.append(" ) tb ");
		sqlBuffer.append(" where nvl(tb.task_times,'0')+1=");
		sqlBuffer.append(approveTimes);
		sqlBuffer.append(" ");
		return super.getSQLPageAll(page, sqlBuffer.toString());
	}

	/**
	 * 获取工单审核通过率统计的sql（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private StringBuffer getWorkOrderProcessInTimeRateSqlByOrg(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select all_t.orgid, ");
		getStatisticNumberColumnSql(sqlBuffer);
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select tb.*, ");
		sqlBuffer.append(" nvl(tb.task_times,'0')+1 as approve_times from ( ");
		sqlBuffer.append(getAllWorkOrderSql(parameters));
		sqlBuffer.append(" ) tb ");
		sqlBuffer.append(" ) all_t ");
		sqlBuffer.append(" group by all_t.orgid ");
		return sqlBuffer;
	}

	/**
	 * 获取统计使用的数据列
	 * 
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getStatisticNumberColumnSql(StringBuffer sqlBuffer) {
		sqlBuffer.append(" count(all_t.pid) as all_num, ");
		sqlBuffer
				.append(" decode(count(all_t.pid),0,0,sum(decode(approve_times,1,1,0))) as onepassed_num, ");
		sqlBuffer
				.append(" to_char(decode(count(all_t.pid),0,0,100*sum(decode(approve_times,1,1,0))/count(all_t.pid)),'FM990.09') as onepassed_rate, ");
		sqlBuffer
				.append(" decode(count(all_t.pid),0,0,sum(decode(approve_times,2,1,0))) as twopassed_num, ");
		sqlBuffer
				.append(" to_char(decode(count(all_t.pid),0,0,100*sum(decode(approve_times,2,1,0))/count(all_t.pid)),'FM990.09') as twopassed_rate ");
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
		sqlBuffer.append(" FN_GETNAMEBYCODE(wo.task_type,'TASK_CODE') as task_type_dis, ");
		sqlBuffer.append(" to_char(wo.create_date,'yyyy-mm-dd hh:mi:ss') as create_date_dis, ");
		sqlBuffer.append(" wt.id as pid,wt.task_state,vu.orgid, ");
		sqlBuffer.append(" to_char((( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" )-wo.CREATE_DATE)*24,'FM99999990.099') as len,  ");
		sqlBuffer.append(" ( ");
		String transitionSql = " and jha.transition_='reject' ";
		getJbpmTaskTimesSql(sqlBuffer, transitionSql);
		sqlBuffer.append(" where jbpm_times_.key='workorder'  ");
		sqlBuffer.append(" and jbpm_times_.bzid=wt.id  ");
		sqlBuffer.append(" and jbpm_times_.task_name='验证回单' ");
		sqlBuffer.append(" ) as task_times ");
		sqlBuffer.append(" from wtask_order wo ");
		sqlBuffer.append(" join wtask_transfer wt on wo.id=wt.task_id ");
		sqlBuffer.append(" left join view_userinfo vu on vu.sid=wt.target_principal ");
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
}
