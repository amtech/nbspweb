package com.cabletech.business.analysis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;

/**
 * 综合巡检报表Dao
 * 
 * @author 汪杰 2012-03-27
 * 
 */
@SuppressWarnings("rawtypes")
public class PollingColligateReportDao extends BaseDao{
	
	/**
	 * 综合巡检报表 --按区域分组 
	 * @param parameters 参数
	 * @return list
	 */
	public List<Map<String, Object>> getPollingColligateReportByRegionList(Map<String, String> parameters){
		List<Map<String, Object>> ret  = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer("");
		
		sqlBuffer.append(" select ");
		sqlBuffer.append(" JZ_ID as id ");
		sqlBuffer.append(" ,JZ_PARENTID as PARENTID ");
		sqlBuffer.append(" ,JZ_NAME as NAME ");
		
		//基站
		sqlBuffer.append(" ,JZ_PLANNUM ");
		sqlBuffer.append(" ,JZ_DEALNUM ");
		sqlBuffer.append(" ,JZ_UNPLANNUM ");
		sqlBuffer.append(" ,JZ_DEARATE ");
		//铁塔
		sqlBuffer.append(" ,TT_PLANNUM ");
		sqlBuffer.append(" ,TT_DEALNUM ");
		sqlBuffer.append(" ,TT_UNPLANNUM ");
		sqlBuffer.append(" ,TT_DEARATE ");
		//综合覆盖
		sqlBuffer.append(" ,ZH_PLANNUM ");
		sqlBuffer.append(" ,ZH_DEALNUM ");
		sqlBuffer.append(" ,ZH_UNPLANNUM ");
		sqlBuffer.append(" ,ZH_DEARATE ");
		//线路
		sqlBuffer.append(" ,XL_PLANNUM ");
		sqlBuffer.append(" ,XL_DEALNUM ");
		sqlBuffer.append(" ,XL_UNPLANNUM ");
		sqlBuffer.append(" ,XL_DEARATE ");
		//集客家客
		sqlBuffer.append(" ,JK_PLANNUM ");
		sqlBuffer.append(" ,JK_DEALNUM ");
		sqlBuffer.append(" ,JK_UNPLANNUM ");
		sqlBuffer.append(" ,JK_DEARATE ");
		
		sqlBuffer.append(" from  ");
		parameters.put("businessType", "jz");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_BASESTATION+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByRegionSql(parameters));
		sqlBuffer.append(" ) jz,");
		parameters.put("businessType", "tt");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByRegionSql(parameters));
		sqlBuffer.append(" ) tt,");
		parameters.put("businessType", "zh");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_REPEATER+"','"+SysConstant.DB_TABLENAME_RS_OVERRIDEINFO+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByRegionSql(parameters));
		sqlBuffer.append(" ) zh ,");
		parameters.put("businessType", "xl");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportLineByRegionSql(parameters));
		sqlBuffer.append(" ) xl, ");
		parameters.put("businessType", "jk");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER+"','"+SysConstant.DB_TABLENAME_RS_CUSTOMER+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByRegionSql(parameters));
		sqlBuffer.append(" ) jk ");
		sqlBuffer.append(" where jz.jz_id = tt.tt_id and jz.jz_id = zh.zh_id and jz.jz_id = xl.xl_id and jz.jz_id = jk.jk_id ");
		ret = getJdbcTemplate().queryForList(sqlBuffer.toString());
		return ret;
	}
	
	/**
	 * 综合巡检报表 --按组织分组 --无线
	 * @param parameters 参数
	 * @return list
	 */
	public List<Map<String, Object>> getPollingColligateReportByOrgList(Map<String, String> parameters){
		List<Map<String, Object>> ret  = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer("");
		
		sqlBuffer.append(" select ");
		sqlBuffer.append(" JZ_ID as id ");
		sqlBuffer.append(" ,JZ_PARENTID as PARENTID ");
		sqlBuffer.append(" ,JZ_NAME as NAME ");
		
		//基站
		sqlBuffer.append(" ,JZ_PLANNUM ");
		sqlBuffer.append(" ,JZ_DEALNUM ");
		sqlBuffer.append(" ,JZ_UNPLANNUM ");
		sqlBuffer.append(" ,JZ_DEARATE ");
		//铁塔
		sqlBuffer.append(" ,TT_PLANNUM ");
		sqlBuffer.append(" ,TT_DEALNUM ");
		sqlBuffer.append(" ,TT_UNPLANNUM ");
		sqlBuffer.append(" ,TT_DEARATE ");
		//综合覆盖
		sqlBuffer.append(" ,ZH_PLANNUM ");
		sqlBuffer.append(" ,ZH_DEALNUM ");
		sqlBuffer.append(" ,ZH_UNPLANNUM ");
		sqlBuffer.append(" ,ZH_DEARATE ");
		//线路
		sqlBuffer.append(" ,XL_PLANNUM ");
		sqlBuffer.append(" ,XL_DEALNUM ");
		sqlBuffer.append(" ,XL_UNPLANNUM ");
		sqlBuffer.append(" ,XL_DEARATE ");
		//集客家客
		sqlBuffer.append(" ,JK_PLANNUM ");
		sqlBuffer.append(" ,JK_DEALNUM ");
		sqlBuffer.append(" ,JK_UNPLANNUM ");
		sqlBuffer.append(" ,JK_DEARATE ");
		
		sqlBuffer.append(" from  ");
		parameters.put("businessType", "jz");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_BASESTATION+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByOrgSql(parameters));
		sqlBuffer.append(" ) jz,");
		parameters.put("businessType", "tt");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByOrgSql(parameters));
		sqlBuffer.append(" ) tt,");
		parameters.put("businessType", "zh");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_REPEATER+"','"+SysConstant.DB_TABLENAME_RS_OVERRIDEINFO+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByOrgSql(parameters));
		sqlBuffer.append(" ) zh ,");
		parameters.put("businessType", "xl");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportLineByOrgSql(parameters));
		sqlBuffer.append(" ) xl, ");
		parameters.put("businessType", "jk");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER+"','"+SysConstant.DB_TABLENAME_RS_CUSTOMER+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByOrgSql(parameters));
		sqlBuffer.append(" ) jk ");
		sqlBuffer.append(" where jz.jz_id = tt.tt_id and jz.jz_id = zh.zh_id and jz.jz_id = xl.xl_id and jz.jz_id = jk.jk_id ");
		ret = getJdbcTemplate().queryForList(sqlBuffer.toString());
		return ret;
	}
	
	/**
	 * 综合巡检报表 --按巡检组分组 
	 * @param parameters 参数
	 * @return list
	 */
	public List<Map<String, Object>> getPollingColligateReportByPatrolList(Map<String, String> parameters){
		List<Map<String, Object>> ret  = new ArrayList<Map<String, Object>>();
		StringBuffer sqlBuffer = new StringBuffer("");
		
		sqlBuffer.append(" select ");
		sqlBuffer.append(" JZ_ID as id ");
		sqlBuffer.append(" ,JZ_PARENTID as PARENTID ");
		sqlBuffer.append(" ,JZ_NAME as NAME ");
		
		//基站
		sqlBuffer.append(" ,JZ_PLANNUM ");
		sqlBuffer.append(" ,JZ_DEALNUM ");
		sqlBuffer.append(" ,JZ_UNPLANNUM ");
		sqlBuffer.append(" ,JZ_DEARATE ");
		//铁塔
		sqlBuffer.append(" ,TT_PLANNUM ");
		sqlBuffer.append(" ,TT_DEALNUM ");
		sqlBuffer.append(" ,TT_UNPLANNUM ");
		sqlBuffer.append(" ,TT_DEARATE ");
		//综合覆盖
		sqlBuffer.append(" ,ZH_PLANNUM ");
		sqlBuffer.append(" ,ZH_DEALNUM ");
		sqlBuffer.append(" ,ZH_UNPLANNUM ");
		sqlBuffer.append(" ,ZH_DEARATE ");
		//线路
		sqlBuffer.append(" ,XL_PLANNUM ");
		sqlBuffer.append(" ,XL_DEALNUM ");
		sqlBuffer.append(" ,XL_UNPLANNUM ");
		sqlBuffer.append(" ,XL_DEARATE ");
		//集客家客
		sqlBuffer.append(" ,JK_PLANNUM ");
		sqlBuffer.append(" ,JK_DEALNUM ");
		sqlBuffer.append(" ,JK_UNPLANNUM ");
		sqlBuffer.append(" ,JK_DEARATE ");
		
		sqlBuffer.append(" from  ");
		parameters.put("businessType", "jz");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_BASESTATION+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByPatrolSql(parameters));
		sqlBuffer.append(" ) jz,");
		parameters.put("businessType", "tt");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByPatrolSql(parameters));
		sqlBuffer.append(" ) tt,");
		parameters.put("businessType", "zh");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_REPEATER+"','"+SysConstant.DB_TABLENAME_RS_OVERRIDEINFO+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByPatrolSql(parameters));
		sqlBuffer.append(" ) zh ,");
		parameters.put("businessType", "xl");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportLineByPatrolSql(parameters));
		sqlBuffer.append(" ) xl, ");
		parameters.put("businessType", "jk");
		parameters.put("resourceType", "'"+SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER+"','"+SysConstant.DB_TABLENAME_RS_CUSTOMER+"'");
		sqlBuffer.append(" ( ");
		sqlBuffer.append(getPollingColligateReportByPatrolSql(parameters));
		sqlBuffer.append(" ) jk ");
		sqlBuffer.append(" where jz.jz_id = tt.tt_id and jz.jz_id = zh.zh_id and jz.jz_id = xl.xl_id and jz.jz_id = jk.jk_id ");
		ret = getJdbcTemplate().queryForList(sqlBuffer.toString());
		return ret;
	}
	
	

	/**
	 * 综合巡检报表 --按组织分组 --无线
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportByOrgSql(Map<String, String> parameters){
		String time_condition= "",rs_type_condition= "",wpr_type_condition = "",wes_type_condition = "";
		String businessType = parameters.get("businessType");
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			rs_type_condition   = " and rm.rs_type in ("+parameters.get("resourceType")+") ";
			wpr_type_condition  = " and wpr.resource_type in ("+parameters.get("resourceType")+") ";
			wes_type_condition  = " and wes.resource_type in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and wp.end_time between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append("  select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from (select o.ID,o.PARENTID,o.NAME, ");
		sqlBuffer.append(" (select count(rm.rs_id) from res_maintenance rm where rm.maintenance_id = o.ID ");
		sqlBuffer.append(rs_type_condition);//类型
		sqlBuffer.append(" ) as resnum, ");
		sqlBuffer.append(" (select count(p.id) from res_maintenance p where exists (select 1 from wplan_patrolresource wpr  ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"+SysConstant.WPLAN_PATROLINFO_STATE_END+"' ");
		sqlBuffer.append(wpr_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) and p.maintenance_id = o.ID ) as plannum, ");
		sqlBuffer.append(" (select count(distinct (wes.resource_id||wes.resource_type||wes.patrol_group_id)) from wplan_executeresult wes ");
		sqlBuffer.append(" left join wplan_patrolinfo wp on wp.id = wes.plan_id ");
		sqlBuffer.append(" left join view_patrolgroup vwp on wes.patrol_group_id = vwp.ID ");
		sqlBuffer.append(" where wes.end_time is not null ");
		sqlBuffer.append(" and vwp.ORGID = o.ID ");
		sqlBuffer.append(wes_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) as dealnum ");
		sqlBuffer.append(" from view_org o ");
		sqlBuffer.append(" where o.ORGTYPE = '"+SysConstant.DEPTTYPE_C+"' ");
		sqlBuffer.append(" and o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" order by o.id) ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 综合巡检报表 --按组织分组 --线路
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportLineByOrgSql(Map<String, String> parameters){
		String businessType = parameters.get("businessType");
		String time_condition= "",type_condition= "",org_condition = "";
		if(StringUtils.isNotBlank(parameters.get("orgId"))){//组织
			org_condition   = " and o.id = '"+parameters.get("orgId")+"' ";
		}
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			type_condition   = " and pi.pointtype in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and lp.begindate between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from (select o.ID,o.PARENTID,o.NAME, ");
		sqlBuffer.append(" (select count(sb.sublineid) from pointinfo pi ");
		sqlBuffer.append(" join subline2point s on pi.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" where sb.contractorid = o.ID and sb.patrolid is not null");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(" )as resnum, ");
		sqlBuffer.append(" (select count(distinct lst.objectid) from subtaskinfo lst ");
		sqlBuffer.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where vwp.ORGID = o.ID ");
		sqlBuffer.append(" and pi.pointid in (select t.pointid from  sublineinfo sb ");
		sqlBuffer.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer.append(" where sb.contractorid = o.id ");
		sqlBuffer.append(" and sb.patrolid is not null) ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as plannum, ");
		sqlBuffer.append(" (select count(lps.pointid) from PLAN_STATPATROLDAD lps ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lps.pointid ");
		sqlBuffer.append(" left join plan lp on lp.id = lps.planid ");
		sqlBuffer.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where vwp.ORGID = o.ID ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as dealnum ");
		sqlBuffer.append(" from view_org o ");
		sqlBuffer.append(" where o.ORGTYPE = '"+SysConstant.DEPTTYPE_C+"' ");
		sqlBuffer.append(" and o.regionid=any (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(org_condition);
		sqlBuffer.append(" order by o.id) ");
		return sqlBuffer.toString();
	}

	/**
	 * 综合巡检报表 --按巡检组分组 --无线
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportByPatrolSql(Map<String, String> parameters){
		String businessType = parameters.get("businessType");
		String time_condition= "",rs_type_condition= "",wpr_type_condition = "",wes_type_condition = "",org_condition = "";
		if(StringUtils.isNotBlank(parameters.get("orgId"))){//组织
			org_condition   = " and o.PARENTID = '"+parameters.get("orgId")+"' ";
		}
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			rs_type_condition   = " and rm.rs_type in ("+parameters.get("resourceType")+") ";
			wpr_type_condition  = " and wpr.resource_type in ("+parameters.get("resourceType")+") ";
			wes_type_condition  = " and wes.resource_type in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and wp.end_time between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from(select o.ID,o.PARENTID,o.NAME, ");
		sqlBuffer.append(" (select count(rm.rs_id) from res_maintenance rm where rm.patrol_group_id = o.id ");
		sqlBuffer.append(rs_type_condition); //类型
		sqlBuffer.append(" ) as resnum, ");
		sqlBuffer.append(" (select count(p.id) from res_maintenance p where exists (select 1 from wplan_patrolresource wpr  ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"+SysConstant.WPLAN_PATROLINFO_STATE_END+"' ");
		sqlBuffer.append(wpr_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) and p.patrol_group_id = o.ID ) as plannum, ");
		sqlBuffer.append(" (select count(distinct (wes.resource_id||wes.resource_type||wes.patrol_group_id)) from wplan_executeresult wes ");
		sqlBuffer.append(" left join wplan_patrolinfo wp on wp.id = wes.plan_id ");
		sqlBuffer.append(" left join view_patrolgroup vwp on wes.patrol_group_id = vwp.ID ");
		sqlBuffer.append(" where wes.end_time is not null ");
		sqlBuffer.append(" and wes.patrol_group_id = o.id ");
		sqlBuffer.append(wes_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) as dealnum ");
		sqlBuffer.append(" from view_patrolgroup o ");
		sqlBuffer.append(" where o.regionid=any  ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(org_condition);//单位
		sqlBuffer.append(" order by o.id) ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 综合巡检报表 --按巡检组分组 --线路
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportLineByPatrolSql(Map<String, String> parameters){
		String businessType = parameters.get("businessType");
		String time_condition= "",type_condition= "",org_condition = "";
		if(StringUtils.isNotBlank(parameters.get("orgId"))){//组织
			org_condition   = " and o.PARENTID = '"+parameters.get("orgId")+"' ";
		}
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			type_condition   = " and pi.pointtype in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and lp.begindate between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from (select o.ID,o.PARENTID,o.NAME, ");
		sqlBuffer.append(" (select count(sb.sublineid) from pointinfo pi ");
		sqlBuffer.append(" join subline2point s on pi.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" where sb.patrolid = o.id) ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(" )as resnum, ");
		sqlBuffer.append(" (select count(distinct lst.objectid) from subtaskinfo lst ");
		sqlBuffer.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer.append(" where lp.executorid = o.id ");
		sqlBuffer.append(" and pi.pointid in (select t.pointid from  sublineinfo sb ");
		sqlBuffer.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer.append(" where sb.patrolid = o.id ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as plannum, ");
		sqlBuffer.append(" (select count(lps.pointid) from PLAN_STATPATROLDAD lps ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lps.pointid ");
		sqlBuffer.append(" left join plan lp on lp.id = lps.planid ");
		sqlBuffer.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where lp.executorid = o.id ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as dealnum ");
		sqlBuffer.append(" from view_patrolgroup o ");
		sqlBuffer.append(" where o.regionid=any (select regionid from region start with regionid='");
		sqlBuffer.append(parameters.get("regionId"));
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(org_condition);
		sqlBuffer.append(" order by o.id) ");
		return sqlBuffer.toString();
	}

	/**
	 * 综合巡检报表 --按区域分组 --无线
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportByRegionSql(Map<String, String> parameters){
		String time_condition= "",rs_type_condition= "",wpr_type_condition = "",wes_type_condition = "",region_condition="";
		String businessType = parameters.get("businessType");
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			rs_type_condition   = " and rm.rs_type in ("+parameters.get("resourceType")+") ";
			wpr_type_condition  = " and wpr.resource_type in ("+parameters.get("resourceType")+") ";
			wes_type_condition  = " and wes.resource_type in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and wp.end_time between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		if(StringUtils.isNotBlank(parameters.get("regionId"))&&parameters.get("regionId").endsWith("0000")){
			region_condition = " where o.PARENTREGIONID = '"+parameters.get("regionId")+"'"; 
		}else{
			region_condition = " where o.REGIONID = '"+parameters.get("regionId")+"'"; 
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append("  select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from (select o.regionid as id,o.PARENTREGIONID as PARENTID,o.REGIONNAME as NAME, ");
		sqlBuffer.append(" (select count(rm.rs_id) from res_maintenance rm left join view_org org on rm.maintenance_id = org.ID where org.REGIONID = o.regionid ");
		sqlBuffer.append(rs_type_condition);//类型
		sqlBuffer.append(" ) as resnum, ");
		sqlBuffer.append(" (select count(p.id) from res_maintenance p where exists (select 1 from wplan_patrolresource wpr  ");
		sqlBuffer.append(" join wplan_patrolinfo wp on wp.id = wpr.plan_id ");
		sqlBuffer.append(" where p.rs_id=wpr.resource_id and p.rs_type=wpr.resource_type and p.patrol_group_id=wp.patrol_group_id ");
		sqlBuffer.append(" and wp.plan_state = '"+SysConstant.WPLAN_PATROLINFO_STATE_END+"' ");
		sqlBuffer.append(" and wp.region_id = o.regionid ");
		sqlBuffer.append(wpr_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) ) as plannum, ");
		sqlBuffer.append(" (select count(distinct (wes.resource_id||wes.resource_type||wes.patrol_group_id)) from wplan_executeresult wes ");
		sqlBuffer.append(" left join wplan_patrolinfo wp on wp.id = wes.plan_id ");
		sqlBuffer.append(" left join view_patrolgroup vwp on wes.patrol_group_id = vwp.ID ");
		sqlBuffer.append(" where wes.end_time is not null ");
		sqlBuffer.append(" and vwp.regionid = o.regionid  ");
		sqlBuffer.append(wes_type_condition);//类型
		sqlBuffer.append(time_condition);//日期
		sqlBuffer.append(" ) as dealnum ");
		sqlBuffer.append(" from region o ");
		sqlBuffer.append(region_condition);
		sqlBuffer.append(" order by o.regionid)  ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 综合巡检报表 --按区域分组 --线路
	 * @param parameters 参数
	 * @return list
	 */
	@SuppressWarnings("unused")
	private String getPollingColligateReportLineByRegionSql(Map<String, String> parameters){
		String businessType = parameters.get("businessType");
		String time_condition= "",type_condition= "",region_condition="";
		if(StringUtils.isNotBlank(parameters.get("resourceType"))){//资源类型
			type_condition   = " and pi.pointtype in ("+parameters.get("resourceType")+") ";
		}
		if(StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))){//日期
			time_condition = " and lp.begindate between to_date('"+parameters.get("startTime")+"','yyyy-MM-dd') and to_date('"+parameters.get("endTime")+"','yyyy-MM-dd') ";
		}
		if(StringUtils.isNotBlank(parameters.get("regionId"))&&parameters.get("regionId").endsWith("0000")){
			region_condition = " where o.PARENTREGIONID = '"+parameters.get("regionId")+"'"; 
		}else{
			region_condition = " where o.REGIONID = '"+parameters.get("regionId")+"'"; 
		}
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select ");
		sqlBuffer.append("  id as "+businessType+"_id");
		sqlBuffer.append(" ,parentid as "+businessType+"_parentid");
		sqlBuffer.append(" ,name as "+businessType+"_name");
		sqlBuffer.append(" ,plannum as "+businessType+"_plannum");
		sqlBuffer.append(" ,dealnum as "+businessType+"_dealnum");
		sqlBuffer.append(" ,(resnum-plannum) as "+businessType+"_unplannum");
		sqlBuffer.append(" ,decode(plannum,0,0,round((dealnum/plannum)*100,2)) as "+businessType+"_dearate");
		sqlBuffer.append(" from (select o.regionid as id,o.PARENTREGIONID as PARENTID,o.REGIONNAME as NAME, ");
		sqlBuffer.append(" (select count(sb.sublineid) from pointinfo pi ");
		sqlBuffer.append(" join subline2point s on pi.pointid = s.pointid ");
		sqlBuffer.append(" join sublineinfo sb on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" where sb.regionid = o.regionid and sb.patrolid is not null ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(" )as resnum, ");
		sqlBuffer.append(" (select count(distinct lst.objectid) from subtaskinfo lst ");
		sqlBuffer.append(" left join plantasklist lptl on lptl.taskid = lst.taskid ");
		sqlBuffer.append(" left join PLAN lp on lp.id = lptl.planid ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lst.objectid ");
		sqlBuffer.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where vwp.REGIONID = o.regionid ");
		sqlBuffer.append(" and pi.pointid in (select t.pointid from  sublineinfo sb ");
		sqlBuffer.append(" join subline2point s  on sb.sublineid = s.sublineid ");
		sqlBuffer.append(" join pointinfo t  on t.pointid = s.pointid ");
		sqlBuffer.append(" where sb.regionid = o.regionid ");
		sqlBuffer.append(" and sb.patrolid is not null) ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as plannum, ");	
		sqlBuffer.append(" (select count(lps.pointid) from PLAN_STATPATROLDAD lps ");
		sqlBuffer.append(" left join pointinfo pi on pi.pointid = lps.pointid ");
		sqlBuffer.append(" left join plan lp on lp.id = lps.planid ");
		sqlBuffer.append(" left join view_patrolgroup vwp on lp.executorid = vwp.ID ");
		sqlBuffer.append(" where vwp.REGIONID = o.regionid ");
		sqlBuffer.append(type_condition);
		sqlBuffer.append(time_condition);
		sqlBuffer.append(" )as dealnum ");
		sqlBuffer.append(" from region o ");
		sqlBuffer.append(region_condition);
		sqlBuffer.append(" order by o.REGIONID) ");
		return sqlBuffer.toString();
	}
	
	
	

}