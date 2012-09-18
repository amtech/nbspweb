package com.cabletech.business.analysis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 计划资源覆盖率 --DAO
 * 
 * @author wangjie 2012-03-19
 * 
 */
@Repository
public class WplanResCoverageRateDao extends BaseDao {

	/**
	 * 计划资源覆盖率 --按组织分组
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public List<Map<String, Object>> getWplanResCoverageRateByOrgList(
			Map<String, String> parameters) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select id,parentid,name, resnum,plannum,(resnum-plannum) as unplannum, ");
		sqlBuffer
				.append(" decode(resnum,0,0,round((plannum/resnum)*100,2)) as covrate ");
		sqlBuffer.append(" from (select o.ID,o.PARENTID,o.NAME,( ");
		sqlBuffer.append(" select count(sb.sublineid) from pointinfo t ");
		sqlBuffer.append(" join subline2point s on t.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		sqlBuffer
				.append(" where sb.contractorid = o.ID and sb.patrolid is not null ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and t.pointtype  = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		sqlBuffer
				.append(" )+(select count(rm.rs_id) from res_maintenance rm,rs_resourcerecord_v v where rm.maintenance_id = o.ID  and v.ID=rm.rs_id and v.TYPE=rm.rs_type  ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and rm.rs_type  = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" ) as resnum,( ");
		sqlBuffer
				.append(" select count(p.id) from res_maintenance p where exists (select 1 from wplan_patrolresource wpr ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer
				.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"
				+ SysConstant.WPLAN_PATROLINFO_STATE_END + "' ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and wpr.resource_type = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			sqlBuffer.append(" and wp.end_time between to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-MM-dd') and to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-MM-dd') ");
		}
		sqlBuffer.append(" )and p.maintenance_id = o.ID ");
		sqlBuffer
				.append(" )+(select count(distinct lst.objectid) from subtaskinfo lst ");
		sqlBuffer
				.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid  ");
		sqlBuffer
				.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where vwp.ORGID = o.ID ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and pi.pointtype = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			sqlBuffer.append(" and lp.enddate between to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-MM-dd') and to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-MM-dd') ");
		}
		sqlBuffer
				.append(" and pi.pointid in (select t.pointid from  sublineinfo sb ");
		sqlBuffer
				.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer
				.append(" where sb.contractorid = o.id and sb.patrolid is not null) ");

		sqlBuffer.append(" ) as plannum from view_org o ");
		sqlBuffer
				.append(" where o.ORGTYPE = '" + SysConstant.DEPTTYPE_C + "' ");
		sqlBuffer.append(" and o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and o.ID = '");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" order by o.id) ");
		ret = getJdbcTemplate().queryForList(sqlBuffer.toString());
		return ret;
	}

	/**
	 * 计划资源覆盖率 --按巡检组分组
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public List<Map<String, Object>> getWplanResCoverageRateByPatrolList(
			Map<String, String> parameters) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select id,parentid,name, resnum,plannum,(resnum-plannum) as unplannum, ");
		sqlBuffer
				.append(" decode(resnum,0,0,round((plannum/resnum)*100,2)) as covrate from ( ");
		sqlBuffer.append(" select o.ID,o.PARENTID,o.NAME,( ");
		sqlBuffer
				.append(" select count(rm.rs_id) from res_maintenance rm,rs_resourcerecord_v v where rm.patrol_group_id = o.id and v.ID=rm.rs_id and v.TYPE=rm.rs_type ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and rm.rs_type  = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" )+(select count(t.pointid) ");
		sqlBuffer.append(" from pointinfo t ");
		sqlBuffer.append(" join subline2point s on t.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and t.pointtype  = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" where sb.patrolid = o.id) as resnum, (");
		sqlBuffer
				.append(" select count(p.id) from res_maintenance p where exists  (select 1 from wplan_patrolresource wpr ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer
				.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"
				+ SysConstant.WPLAN_PATROLINFO_STATE_END + "' ");
		sqlBuffer.append(" and wp.patrol_group_id = o.id ");
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and wpr.resource_type = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			sqlBuffer.append(" and wp.end_time between to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-MM-dd') and to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-MM-dd') ");
		}
		sqlBuffer.append(" )and p.patrol_group_id = o.id ");
		sqlBuffer
				.append(" )+(select count(distinct lst.objectid) from subtaskinfo lst ");
		sqlBuffer
				.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer
				.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer.append(" where lp.executorid = o.id ");

		sqlBuffer.append(" and pi.pointid in (select t.pointid ");
		sqlBuffer.append(" from  sublineinfo sb ");
		sqlBuffer
				.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer.append(" where sb.patrolid = o.id) ");

		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			sqlBuffer.append(" and pi.pointtype = '");
			sqlBuffer.append(parameters.get("resourceType"));
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			sqlBuffer.append(" and lp.enddate between to_date('");
			sqlBuffer.append(parameters.get("startTime"));
			sqlBuffer.append("','yyyy-MM-dd') and to_date('");
			sqlBuffer.append(parameters.get("endTime"));
			sqlBuffer.append("','yyyy-MM-dd') ");
		}
		sqlBuffer.append(" ) as plannum from view_patrolgroup o ");
		sqlBuffer.append(" where o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sqlBuffer.append(" and o.PARENTID = '");
			sqlBuffer.append(parameters.get("orgId"));
			sqlBuffer.append("'");
		}
		sqlBuffer.append(" order by o.id) ");
		ret = getJdbcTemplate().queryForList(sqlBuffer.toString());
		return ret;
	}

	/**
	 * 计划资源覆盖率 --计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return Page
	 */
	public Page getPlannedResDetailList(Page page,
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		String wres_condition = "";
		String line_condition = "";
		String sb_condition = "";

		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			wres_condition += " and wpr.resource_type = '"
					+ parameters.get("resourceType") + "' ";
			line_condition += " and pi.pointtype = '"
					+ parameters.get("resourceType") + "' ";
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			String dateCondition = " between to_date('"
					+ parameters.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameters.get("endTime") + "','yyyy-MM-dd') ";
			wres_condition += " and wp.end_time" + dateCondition;
			line_condition += " and lp.enddate" + dateCondition;
		}
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {// 范围 -- 组织
			String orgCondition = "and vwp.orgid = '" + parameters.get("orgId")
					+ "'";
			wres_condition += ")" + orgCondition;
			line_condition += orgCondition;
			sb_condition += "sb.contractorid = '" + parameters.get("orgId")
					+ "'";
		}
		if (StringUtils.isNotBlank(parameters.get("patrolId"))) {// 范围 -- 巡检组
			String orgCondition = "and vwp.id = '" + parameters.get("patrolId")
					+ "'";
			wres_condition += ")" + orgCondition;
			line_condition += orgCondition;
			sb_condition += "sb.patrolid = '" + parameters.get("patrolId")
					+ "'";
		}
		sqlBuffer.append(" select distinct ");
		sqlBuffer.append(" to_char(p.rs_id) as rsid, ");
		sqlBuffer.append(" to_char(rsv.STATIONCODE) as STATIONCODE, ");
		sqlBuffer.append(" to_char(rsv.name) as rsname, ");
		sqlBuffer
				.append("  fn_getnamebycode(p.rs_type,'pointtype') as rstype, ");
		sqlBuffer.append(" to_char(vwp.PARENTNAME) as orgname, ");
		sqlBuffer.append(" to_char(vwp.NAME) as patrolname ");
		sqlBuffer.append(" from res_maintenance p ");
		sqlBuffer
				.append(" join rs_resourcerecord_v rsv on rsv.ID = p.rs_id and p.rs_type = rsv.TYPE ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on vwp.id = p.patrol_group_id ");
		sqlBuffer
				.append(" where exists  ( select 1 from wplan_patrolresource wpr ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer
				.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");

		sqlBuffer.append(" and wp.plan_state = '"
				+ SysConstant.WPLAN_PATROLINFO_STATE_END + "' ");
		sqlBuffer.append(wres_condition + " union all ");
		sqlBuffer.append(" select distinct ");
		sqlBuffer.append(" to_char(lst.objectid) as rsid, ");
		sqlBuffer.append(" to_char('') as STATIONCODE, ");
		sqlBuffer.append(" to_char(pi.pointname) as rsname, ");
		sqlBuffer
				.append(" fn_getnamebycode(pi.pointtype,'pointtype') as rstype, ");
		sqlBuffer.append(" to_char(vwp.PARENTNAME) as orgname, ");
		sqlBuffer.append(" to_char(vwp.NAME) as patrolname ");
		sqlBuffer.append(" from subtaskinfo lst ");
		sqlBuffer
				.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer
				.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID where 1=1 ");
		sqlBuffer
				.append(" and pi.pointid in (select t.pointid from  sublineinfo sb ");
		sqlBuffer
				.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer.append(" where " + sb_condition
				+ " and sb.patrolid is not null) ");
		sqlBuffer.append(line_condition);
		return super.getSQLPageAll(page, sqlBuffer.toString());
	}

	/**
	 * 计划资源覆盖率 --未计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return Page
	 */
	public Page getUnPlannedResDetailList(Page page,
			Map<String, String> parameters) {
		StringBuffer sqlBuffer = new StringBuffer("");
		String org_condition = "";
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {// 范围 -- 组织
			org_condition = " and vwp.orgid = '" + parameters.get("orgId")
					+ "' ";
		}
		if (StringUtils.isNotBlank(parameters.get("patrolId"))) {// 范围 -- 巡检组
			org_condition = "and vwp.id = '" + parameters.get("patrolId") + "'";
		}
		String wres_time_condition = "";
		String line_time_condition = "";
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {// 日期
			String time_condition = " between to_date('"
					+ parameters.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameters.get("endTime") + "','yyyy-MM-dd')) ";
			wres_time_condition = " and wp.end_time" + time_condition;
			line_time_condition = " and lp.enddate" + time_condition;
		} else {
			wres_time_condition = ")";
			line_time_condition = ")";
		}
		String type_condition = "";
		String rm_type_condition = "";
		String wpr_type_condition = "";
		if (StringUtils.isNotBlank(parameters.get("resourceType"))) {// 资源类型
			type_condition += " and pi.pointtype = '"
					+ parameters.get("resourceType") + "' ";
			rm_type_condition += " and p.rs_type = '"
					+ parameters.get("resourceType") + "' ";
			wpr_type_condition += " and wpr.resource_type = '"
					+ parameters.get("resourceType") + "' ";
		}
		sqlBuffer.append(" select distinct ");
		sqlBuffer.append(" to_char(p.rs_id) as rsid, ");
		sqlBuffer.append(" to_char(rsv.STATIONCODE) as STATIONCODE, ");
		sqlBuffer.append(" to_char(rsv.name) as rsname, ");
		sqlBuffer
				.append(" fn_getnamebycode(p.rs_type,'pointtype') as rstype, ");
		sqlBuffer.append(" to_char(vwp.PARENTNAME) as orgname, ");
		sqlBuffer.append(" to_char(vwp.NAME) as patrolname ");
		sqlBuffer.append(" from res_maintenance p ");
		sqlBuffer
				.append(" join rs_resourcerecord_v rsv on rsv.ID = p.rs_id and p.rs_type = rsv.TYPE ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on vwp.id = p.patrol_group_id ");
		sqlBuffer
				.append(" where not exists  ( select 1 from wplan_patrolresource wpr ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer
				.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"
				+ SysConstant.WPLAN_PATROLINFO_STATE_END + "' ");
		sqlBuffer.append(wpr_type_condition);
		sqlBuffer.append(wres_time_condition);
		sqlBuffer.append(org_condition);
		sqlBuffer.append(rm_type_condition);
		sqlBuffer.append(" union all ");
		sqlBuffer.append(" select to_char(pi.pointid) as rsid, ");
		sqlBuffer.append(" to_char('') as STATIONCODE, ");
		sqlBuffer.append(" to_char(pi.pointname) as rsname, ");
		sqlBuffer
				.append(" fn_getnamebycode(pi.pointtype,'pointtype') as rstype, ");
		sqlBuffer.append(" to_char(vwp.PARENTNAME) as orgname, ");
		sqlBuffer.append(" to_char(vwp.NAME) as patrolname ");
		sqlBuffer.append(" from pointinfo pi ");
		sqlBuffer.append(" join subline2point s on pi.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on sb.patrolid = vwp.ID ");
		sqlBuffer
				.append(" where not exists(select lst.id from subtaskinfo lst ");
		sqlBuffer
				.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer
				.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where lst.objectid = pi.pointid ");
		sqlBuffer.append(org_condition);
		sqlBuffer.append(type_condition);
		sqlBuffer.append(line_time_condition);
		sqlBuffer.append(type_condition);
		sqlBuffer.append(org_condition);
		return super.getSQLPageAll(page, sqlBuffer.toString());
	}
}