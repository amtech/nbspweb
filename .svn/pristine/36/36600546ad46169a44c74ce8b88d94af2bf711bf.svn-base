package com.cabletech.business.analysis.dao;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;

/**
 * 故障分析统计Dao基类
 * 
 * @author 杨隽 2012-03-30 创建
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class TroubleAnalyseBaseDao extends BaseDao {
	public static final String WTROUBLE_END_STATE = "4";
	public static final String LTROUBLE_END_STATE = "50";
	public static final String ARRIVE_PROCESS_STATE = "02";

	/**
	 * 获取工作流任务执行次数sql
	 * 
	 * @param sqlBuffer sql字符串
	 * @param transitionSql 
	 */
	protected void getJbpmTaskTimesSql(StringBuffer sqlBuffer,
			String transitionSql) {
		sqlBuffer.append(" select task_times from ( ");
		sqlBuffer.append(" select jd.deploymentkey as key,jhp.id_ as did, ");
		sqlBuffer
				.append(" decode(jhpe.bzid,null,jhp.key_,jhpe.bzid) as bzid, ");
		sqlBuffer
				.append(" det.activity_name_ as task_name,det.times as task_times ");
		sqlBuffer.append(" from JBPM4_HIST_PROCINST jhp ");
		sqlBuffer
				.append(" left join jbpm4_hist_pi_ext jhpe on jhp.id_=jhpe.piid ");
		sqlBuffer.append(" left join ( ");
		sqlBuffer
				.append(" select jha.execution_,jha.activity_name_,count(*) times ");
		sqlBuffer.append(" from jbpm4_hist_actinst jha ");
		sqlBuffer.append(" where  jha.type_='task' ");
		sqlBuffer.append(transitionSql);
		sqlBuffer.append(" group by jha.activity_name_,jha.execution_ ");
		sqlBuffer.append(" ) det on det.execution_=jhp.id_ ");
		sqlBuffer
				.append(" left join jbpm4_defineinfo jd on jhp.procdefid_=jd.processdefinitionid ");
		sqlBuffer.append(" where jhp.state_='ended' ");
		sqlBuffer.append(" order by jhp.id_ ");
		sqlBuffer.append(" ) jbpm_times_ ");
	}

	/**
	 * 获取工作流的任务时长数据信息
	 * 
	 * @param sqlBuffer sql字符串
	 */
	protected void getJbpmTaskDataSql(StringBuffer sqlBuffer) {
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

	/**
	 * 获取线路故障单列表查询条件的sql
	 * 
	 * @param parameters 条件
	 * @param sqlBuffer sql字符串
	 */
	protected void getLTroubleCondition(Map<String, String> parameters,
			StringBuffer sqlBuffer) {
		if (StringUtils.isNotBlank(parameters.get("startTime"))) {
			sqlBuffer.append(" and lti.trouble_start_time>=to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameters.get("endTime"))) {
			sqlBuffer.append(" and lti.trouble_start_time<to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and ltpu.process_unit_id='");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("' ");
		}
		if (StringUtils.isNotBlank(parameters.get("businessType"))) {
			if (!SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30
					.equals(parameters.get("businessType"))) {
				sqlBuffer.append(" and 1<>1 ");
			}
		}
		if (StringUtils.isNotBlank(parameters.get("isInstancy"))) {
			sqlBuffer.append(" and decode(lti.is_great_trouble,'1','重大故障','一般故障')='");
			sqlBuffer.append(parameters.get("isInstancy"));
			sqlBuffer.append("' ");
		}
	}

	/**
	 * 获取无线故障单列表查询条件的sql
	 * @param parameters 条件
	 * @param sqlBuffer sql字符串
	 */
	protected void getWTroubleCondition(Map<String, String> parameters,
			StringBuffer sqlBuffer) {
		if (StringUtils.isNotBlank(parameters.get("startTime"))) {
			sqlBuffer.append(" and wa.trouble_time>=to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameters.get("endTime"))) {
			sqlBuffer.append(" and wa.trouble_time<to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and ws.maintenance_id='");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("' ");
		}
		if (StringUtils.isNotBlank(parameters.get("patrolGroupId"))) {
			sqlBuffer.append(" and ws.patrol_group='");
			sqlBuffer.append(parameters.get("patrolGroupId"));
			sqlBuffer.append("' ");
		}
		if (StringUtils.isNotBlank(parameters.get("businessType"))) {
			sqlBuffer.append(" and wa.business_type = '");
			sqlBuffer.append(parameters.get("businessType"));
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(parameters.get("isInstancy"))) {
			sqlBuffer
					.append(" and FN_GETNAMEBYCODE(wa.trouble_level,'TROUBLE_LEVEL')='");
			sqlBuffer.append(parameters.get("isInstancy"));
			sqlBuffer.append("' ");
		}
	}
}
