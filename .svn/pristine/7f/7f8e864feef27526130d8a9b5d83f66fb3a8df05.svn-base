package com.cabletech.business.workflow.fault.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.common.base.BaseDao;

/**
 * 首页故障派单统计Dao
 * 
 * @author 杨隽 2012-03-09 创建
 * 
 */
@Repository
@SuppressWarnings("rawtypes")
public class FaultStatisticDao extends BaseDao {
	/**
	 * 日志输出
	 */
	protected final Logger logger = Logger.getLogger("FaultStatisticDao");

	/**
	 * 获取超时故障数量列表（按区域）
	 * 
	 * @param condition
	 *            String 查询条件
	 * @param regionId
	 *            String 区域编号
	 * @return List<Map<String, Object>> 超时故障数量列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOvertimeFaultNumberByRegion(
			String condition, String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" sum(decode(sign_,-1,1,0)) as overtime_f_num from ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select regionid,regionname  ");
		sqlBuffer.append(" from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid) r ");
		sqlBuffer.append(" join ");
		sqlBuffer.append(getOvertimeFaultNumberSql(condition));
		sqlBuffer.append(" on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		logger.info(":" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取超时故障数量列表（按组织）
	 * 
	 * @param condition
	 *            String 查询条件
	 * @param regionId
	 *            String 区域编号
	 * @param orgId
	 *            String 组织编号
	 * @return List<Map<String, Object>> 超时故障数量列表
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOvertimeFaultNumberByOrg(
			String condition, String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select vo.id as gid,vo.name as gname, ");
		sqlBuffer.append(" sum(decode(sign_,-1,1,0)) as overtime_f_num from ");
		sqlBuffer.append(" view_org vo ");
		sqlBuffer.append(" join ");
		sqlBuffer.append(getOvertimeFaultNumberSql(condition));
		sqlBuffer.append(" on result_.id=vo.id ");
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
	 * 获取超时故障数量列表的sql
	 * 
	 * @param condition
	 *            String 查询条件
	 * @return String 超时故障数量列表的sql
	 */
	private String getOvertimeFaultNumberSql(String condition) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select vo.id,vo.regionid,sign( ");
		sqlBuffer.append(" wt.deadline- ");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='fault'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写故障回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=wt.id ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ) as sign_ ");
		sqlBuffer.append(" from wtrouble_alarm wa ");
		sqlBuffer.append(" join wtrouble_sendtask wt on wa.id=wt.alarm_id ");
		sqlBuffer.append(" join wtrouble_reply wr on wt.id=wr.task_id ");
		sqlBuffer.append(" join view_org vo on wt.maintenance_id=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" ) result_ ");
		return sqlBuffer.toString();
	}

	/**
	 * 获取工作流的任务数据信息
	 * 
	 * @param sqlBuffer
	 *            StringBuffer sql存放缓冲区
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
