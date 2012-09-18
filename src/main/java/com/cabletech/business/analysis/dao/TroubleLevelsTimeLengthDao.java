package com.cabletech.business.analysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 故障平均历时统计Dao
 * 
 * @author 杨隽 2012-03-20 创建
 * @author 杨隽 2012-03-30 提取公共方法
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class TroubleLevelsTimeLengthDao extends TroubleAnalyseBaseDao {
	/**
	 * 进行故障平均历时统计（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	public List getTroubleLevelsTimeLengthListByOrg(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select vo.id,vo.name,vo.parentid, ");
		sqlBuffer.append(" '0' as level_,'false' as isLeaf, ");
		sqlBuffer.append(" 'false' as expanded,'true' as loaded, ");
		sqlBuffer.append(" result_.* from ( ");
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
		sqlBuffer.append(getTroubleLevelsTimeLengthSqlByOrg(parameters));
		sqlBuffer.append(" ) result_ on vo.id=result_.maintenance_id ");
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 进行故障平均历时统计（按巡检组分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	public List getTroubleLevelsTimeLengthListByPatrolGroup(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select vp.id,vp.name,vp.parentid, ");
		sqlBuffer.append(" '1' as level_,'true' as isLeaf, ");
		sqlBuffer.append(" 'false' as expanded,'true' as loaded, ");
		sqlBuffer.append(" result_.*  ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select v.* from view_patrolgroup v  ");
		sqlBuffer.append(" where v.regionid=any( ");
		sqlBuffer.append(" select r.regionid from view_region r  ");
		sqlBuffer.append(" start with r.regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior r.regionid=r.parentid ");
		sqlBuffer.append(" ) ");
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and v.orgid='");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" ) vp ");
		sqlBuffer.append(" join ( ");
		sqlBuffer
				.append(getTroubleLevelsTimeLengthSqlByPatrolGroup(parameters));
		sqlBuffer.append(" ) result_ on vp.id=result_.patrol_group ");
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取故障平均历时统计的sql（按组织分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private StringBuffer getTroubleLevelsTimeLengthSqlByOrg(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select all_t.maintenance_id, ");
		getStatisticNumberColumnSql(sqlBuffer);
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(getTroubleSqlByLevel(parameters));
		sqlBuffer.append(" ) all_t ");
		sqlBuffer.append(" group by all_t.maintenance_id ");
		return sqlBuffer;
	}

	/**
	 * 获取故障平均历时统计的sql（按巡检组分组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private StringBuffer getTroubleLevelsTimeLengthSqlByPatrolGroup(
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select all_t.patrol_group, ");
		getStatisticNumberColumnSql(sqlBuffer);
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(getTroubleSqlByLevel(parameters));
		sqlBuffer.append(" ) all_t ");
		sqlBuffer.append(" where all_t.patrol_group is not null ");
		sqlBuffer.append(" group by all_t.patrol_group ");
		return sqlBuffer;
	}

	/**
	 * 获取统计使用的数据列
	 * 
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getStatisticNumberColumnSql(StringBuffer sqlBuffer) {
		sqlBuffer.append(" count(all_t.did) as all_num, ");
		sqlBuffer
				.append(" to_char(sum(all_t.len),'FM99999990.099') as all_len, ");
		sqlBuffer
				.append(" to_char(decode(count(all_t.did),0,0,sum(all_t.len)/count(all_t.did)),'FM99999990.099') as all_avg_len, ");
		sqlBuffer
				.append(" sum(decode(all_t.is_instancy,'严重故障',1,0)) as level_num0, ");
		sqlBuffer
				.append(" to_char(sum(decode(all_t.is_instancy,'严重故障',len,0)),'FM99999990.099') as level_len0, ");
		sqlBuffer
				.append(" to_char(decode(sum(decode(all_t.is_instancy,'严重故障',1,0)),0,0,sum(decode(all_t.is_instancy,'严重故障',len,0))/sum(decode(all_t.is_instancy,'严重故障',1,0))),'FM99999990.099') as level_avg_len0, ");
		sqlBuffer
				.append(" sum(decode(all_t.is_instancy,'重大故障',1,0)) as level_num1, ");
		sqlBuffer
				.append(" to_char(sum(decode(all_t.is_instancy,'重大故障',len,0)),'FM99999990.099') as level_len1, ");
		sqlBuffer
				.append(" to_char(decode(sum(decode(all_t.is_instancy,'重大故障',1,0)),0,0,sum(decode(all_t.is_instancy,'重大故障',len,0))/sum(decode(all_t.is_instancy,'重大故障',1,0))),'FM99999990.099') as level_avg_len1, ");
		sqlBuffer
				.append(" sum(decode(all_t.is_instancy,'一般故障',1,0)) as level_num2, ");
		sqlBuffer
				.append(" to_char(sum(decode(all_t.is_instancy,'一般故障',len,0)),'FM99999990.099') as level_len2, ");
		sqlBuffer
				.append(" to_char(decode(sum(decode(all_t.is_instancy,'一般故障',1,0)),0,0,sum(decode(all_t.is_instancy,'一般故障',len,0))/sum(decode(all_t.is_instancy,'一般故障',1,0))),'FM99999990.099') as level_avg_len2 ");
	}

	/**
	 * 获取所有故障单列表的sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	private String getTroubleSqlByLevel(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select * from ( ");
		getWTroubleSelectSql(parameters, sqlBuffer);
		sqlBuffer.append(" union ");
		getLTroubleSelectSql(parameters, sqlBuffer);
		sqlBuffer.append(" ) order by len ");
		return sqlBuffer.toString();
	}

	/**
	 * 获取所有线路故障单列表的select sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getLTroubleSelectSql(Map<String, String> parameters,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select lti.id,ltpu.id as did, ");
		sqlBuffer.append(" ltpu.process_unit_id as maintenance_id, ");
		sqlBuffer.append(" '' as patrol_group, ");
		sqlBuffer.append(" lti.trouble_name,ltec.eoms_code as eoms_id, ");
		sqlBuffer.append(" lti.trouble_start_time as trouble_time, ");
		sqlBuffer.append(" '' as address,'' as station_id, ");
		sqlBuffer.append(" '' as station_type,'' as station_name, ");
		sqlBuffer
				.append(" decode(lti.is_great_trouble,'1','重大故障','一般故障') as is_instancy, ");
		sqlBuffer
				.append(" decode(lti.trouble_type,'0','巡回','告知') as find_type, ");
		sqlBuffer.append(" to_char((( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='trouble'  ");
		sqlBuffer.append(" and jbpm_.task_name='reply_task'  ");
		sqlBuffer.append(" and jbpm_.bzid=ltpu.id ");
		sqlBuffer
				.append(" )-lti.trouble_start_time)*24,'FM99999990.099') as len from lp_trouble_info lti ");
		sqlBuffer.append(" join lp_trouble_process_unit ltpu  ");
		sqlBuffer.append(" on lti.id=ltpu.trouble_id ");
		sqlBuffer.append(" left join lp_trouble_eoms_code ltec  ");
		sqlBuffer.append(" on lti.id=ltec.trouble_id ");
		sqlBuffer.append(" where lti.trouble_state='");
		sqlBuffer.append(LTROUBLE_END_STATE);
		sqlBuffer.append("' ");
		getLTroubleCondition(parameters, sqlBuffer);
	}

	/**
	 * 获取所有无线故障单列表的select sql
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getWTroubleSelectSql(Map<String, String> parameters,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select wa.id,ws.id as did, ");
		sqlBuffer.append(" ws.maintenance_id,ws.patrol_group, ");
		sqlBuffer.append(" wa.trouble_title as title ");
		sqlBuffer.append(" ,wa.eoms_id,wa.trouble_time,wa.address, ");
		sqlBuffer.append(" wa.station_id,wa.station_type, ");
		sqlBuffer.append(" rs.zymc as station_name,");
		sqlBuffer
				.append(" FN_GETNAMEBYCODE(wa.trouble_level,'TROUBLE_LEVEL') as is_instancy, ");
		sqlBuffer
				.append(" FN_GETNAMEBYCODE(wa.find_type,'FIND_TYPE') as find_type, ");
		sqlBuffer.append(" to_char((( ");
		sqlBuffer.append(" select min(jbpm_.end_time) from ");
		getJbpmTaskDataSql(sqlBuffer);
		sqlBuffer.append(" where jbpm_.key='fault'  ");
		sqlBuffer.append(" and jbpm_.task_name='填写故障回单'  ");
		sqlBuffer.append(" and jbpm_.bzid=ws.id ");
		sqlBuffer
				.append(" )-wa.trouble_time)*24,'FM99999990.099') as len from wtrouble_alarm wa ");
		sqlBuffer.append(" join wtrouble_sendtask ws on wa.id=ws.alarm_id ");
		sqlBuffer.append(" left join res_zdxx rs  ");
		sqlBuffer.append(" on wa.station_id=rs.xtbh  ");
		//sqlBuffer.append(" and wa.station_type=rs.rs_type ");
		sqlBuffer.append(" where wa.ignore_state='");
		sqlBuffer.append(WTROUBLE_END_STATE);
		sqlBuffer.append("' ");
		getWTroubleCondition(parameters, sqlBuffer);
	}
}
