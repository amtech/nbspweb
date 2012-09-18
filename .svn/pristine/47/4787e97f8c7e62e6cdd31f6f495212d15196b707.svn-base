package com.cabletech.business.wplan.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 巡检结果统计DAO
 * 
 * @author wj
 * 
 */
@Repository
public class PatrolinfoResultDao extends BaseDao {
	private static final String NOPATROLSTATION_PROBLEM_TYPE_CQ = "01";// 站点拆迁
	private static final String NOPATROLSTATION_PROBLEM_TYPE_JF = "02";// 业主纠纷
	private static final String NOPATROLSTATION_PROBLEM_TYPE_QT = "03";// 其他

	/**
	 * 按区域统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByRegion(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select "
				+ createDetailCondition(parameter)
				+ "t.REGIONID,t.REGIONNAME,nvl(res1.nub,0)as plannub,nvl(res2.nub,0)as exceptionnub, ");
		sb.append(" nvl(res3.nub,0)as cqnub,nvl(res4.nub,0)as jfnub,nvl(res5.nub,0)as qtnub from view_region t ");
		sb.append(" left join ( ");
		sb.append(getPlanNubSql("region", parameter));
		sb.append(" )res1 on t.REGIONID = res1.Region_Id ");
		sb.append(" left join ( ");
		sb.append(getExceptionNubSql("region", parameter));
		sb.append(" )res2 on t.REGIONID = res2.RegionId ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_CQ, "region",
				parameter));
		sb.append(" )res3 on res3.REGION_ID = t.REGIONID ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_JF, "region",
				parameter));
		sb.append(" )res4 on res4.REGION_ID = t.REGIONID ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_QT, "region",
				parameter));
		sb.append(" )res5 on res5.REGION_ID = t.REGIONID ");
		sb.append(" where 1=1 ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))
				&& ((String) parameter.get("regionId")).endsWith("0000")) {
			sb.append(" and t.parentid = '" + parameter.get("regionId") + "'");
		} else {
			sb.append(" and t.regionid = '" + parameter.get("regionId") + "'");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 按组织统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByOrg(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select "
				+ createDetailCondition(parameter)
				+ " t.id,t.orgname,nvl(res1.nub,0)as plannub,nvl(res2.nub,0)as exceptionnub, ");
		sb.append(" nvl(res3.nub,0)as cqnub,nvl(res4.nub,0)as jfnub,nvl(res5.nub,0)as qtnub from view_org t ");
		sb.append(" left join ( ");
		sb.append(getPlanNubSql("org", parameter));
		sb.append(" )res1 on t.id = res1.orgid ");
		sb.append(" left join ( ");
		sb.append(getExceptionNubSql("org", parameter));
		sb.append(" )res2 on t.id = res2.orgid ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_CQ, "org",
				parameter));
		sb.append(" )res3 on res3.orgid = t.id ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_JF, "org",
				parameter));
		sb.append(" )res4 on res4.orgid = t.id ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_QT, "org",
				parameter));
		sb.append(" )res5 on res5.orgid = t.id ");
		sb.append(" where t.orgtype = '2' ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and t.regionid = '" + parameter.get("regionId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and t.id = '" + parameter.get("orgId") + "'");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 按巡检组统计
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page statisticsByPatrolGroup(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select "
				+ createDetailCondition(parameter)
				+ " t.id,t.name,nvl(res1.nub,0)as plannub,nvl(res2.nub,0)as exceptionnub, ");
		sb.append(" nvl(res3.nub,0)as cqnub,nvl(res4.nub,0)as jfnub,nvl(res5.nub,0)as qtnub from view_patrolgroup t ");
		sb.append(" left join ( ");
		sb.append(getPlanNubSql("patrolgroup", parameter));
		sb.append(" )res1 on t.id = res1.patrol_group_id ");
		sb.append(" left join ( ");
		sb.append(getExceptionNubSql("patrolgroup", parameter));
		sb.append(" )res2 on t.id = res2.patrolgroupid ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_CQ,
				"patrolgroup", parameter));
		sb.append(" )res3 on res3.patrol_group_id = t.id ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_JF,
				"patrolgroup", parameter));
		sb.append(" )res4 on res4.patrol_group_id = t.id ");
		sb.append(" left join ( ");
		sb.append(getNoPatrolNubSql(NOPATROLSTATION_PROBLEM_TYPE_QT,
				"patrolgroup", parameter));
		sb.append(" )res5 on res5.patrol_group_id = t.id ");
		sb.append(" where 1=1 ");
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and t.orgid = '" + parameter.get("orgId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and t.id = '" + parameter.get("patrolGroupId") + "'");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 根据条件生成计划数量 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param parameter
	 *            查询条件
	 * @return
	 */
	public String getPlanNubSql(String groupBy, Map<String, Object> parameter) {
		String queryKey = "";
		String queryGorupBy = "";
		if ("all".equals(groupBy)) {
			queryKey = "";
			queryGorupBy = "";
		}
		if ("region".equals(groupBy)) {
			queryKey = " WP.REGION_ID, ";
			queryGorupBy = " GROUP BY WP.REGION_ID ";
		}
		if ("org".equals(groupBy)) {
			queryKey = " VPG.ORGID, ";
			queryGorupBy = " GROUP BY VPG.ORGID ";
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " WP.PATROL_GROUP_ID, ";
			queryGorupBy = " GROUP BY WP.PATROL_GROUP_ID ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(queryKey);
		sb.append(" COUNT(PRS.ID) AS NUB FROM WPLAN_PATROLRESOURCE PRS ");
		sb.append(" JOIN WPLAN_PATROLINFO WP ON WP.ID = PRS.PLAN_ID ");
		sb.append(" JOIN VIEW_PATROLGROUP VPG ON WP.PATROL_GROUP_ID = VPG.ID ");
		sb.append(" WHERE wp.PLAN_STATE='" + SysConstant.PASSED_STATE + "' ");
		getUserCondition(parameter, sb);
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and WP.START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" AND WP.BUSINESS_TYPE = '"
					+ parameter.get("businessType") + "' ");
		}
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 计划资源明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getPlanResDetail(Map<String, String> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 问题站点明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getExceptionResDetail(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT rz.ZYMC AS STATION_NAME,w.ERROR_NUM, ");
		sb.append(" dic.LABLE AS BUSINESS_TYPE,rz.XTBH AS STATION_ID, ");
		sb.append(" rz.DZ AS ADDRESS,vo.NAME AS ORG_NAME, ");
		sb.append(" vo.LINKMANINFO AS ORG_LINKMAN, ");
		sb.append(" vo.LINKMANTEL AS ORG_TEL,w.PLAN_NAME, ");
		sb.append(" vr.REGIONNAME,w.ID AS PLAN_ID ");
		sb.append(" FROM ( ");
		sb.append(" SELECT wp.ID,wp.REGION_ID,wp.PLAN_NAME,wer.RESOURCE_ID,wp.BUSINESS_TYPE, ");
		sb.append(" COUNT(DISTINCT wpr.ID) AS ERROR_NUM ");
		sb.append(" FROM WPLAN_PATROLRECORD wpr  ");
		sb.append(" JOIN WPLAN_PATROLSUBITEM wps  ");
		sb.append(" ON wps.ID=wpr.SUBITEM_ID  ");
		sb.append(" JOIN WPLAN_PATROLITEM wpi ON wpI.ID=wps.ITEM_ID ");
		sb.append(" JOIN WPLAN_EXECUTERESULT wer  ");
		sb.append(" ON wpr.EXECUTERESULT_ID=wer.ID  ");
		sb.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID ");
		sb.append(" JOIN VIEW_PATROLGROUP vp  ");
		sb.append(" ON wp.PATROL_GROUP_ID=vp.ID  ");
		sb.append(" WHERE wps.EXCEPTION_VALUE=wpr.SUBITEM_PATROL ");
		getUserCondition(parameter, sb);
		getConditionString(parameter, sb);
		sb.append(" GROUP BY wer.RESOURCE_ID,wp.BUSINESS_TYPE,wp.PLAN_NAME,wp.REGION_ID,wp.ID ");
		sb.append(" ) w ");
		sb.append(" JOIN RES_ZDXX rz ON rz.XTBH=w.RESOURCE_ID ");
		sb.append(" JOIN RES_MAINTENANCE rm ON rz.XTBH=rm.RS_ID  ");
		sb.append(" AND rm.RS_TYPE=w.BUSINESS_TYPE  ");
		sb.append(" JOIN VIEW_ORG vo ON rm.MAINTENANCE_ID=vo.ID  ");
		sb.append(" JOIN VIEW_REGION vr ON w.REGION_ID=vr.REGIONID ");
		sb.append(" JOIN BASE_SYSDICTIONARY dic  ");
		sb.append(" ON dic.CODEVALUE=w.BUSINESS_TYPE ");
		sb.append(" AND dic.COLUMNTYPE='BUSINESSTYPE'  ");
		QueryParameter parameters = new QueryParameter();
		parameters.setAlias("vo");
		parameters.setColumnName("REGIONID");
		parameters.setValue((String) parameter.get("regionId"));
		sb.append(BusinessConditionUtils.getRegionCondition(parameters));
		parameters.setColumnName("ID");
		parameters.setValue((String) parameter.get("orgId"));
		sb.append(BusinessConditionUtils.getOrgCondition(parameters));
		sb.append(" ORDER BY w.ERROR_NUM DESC,rz.XTBH ");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 异常项明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getExceptionItemsDetail(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT wpi.ID,wps.ID AS SUBITEM_ID,wpi.ITEM_NAME, ");
		sb.append(" wps.SUBITEM_NAME,rz.ZYMC AS STATION_NAME,wpr.SUBITEM_PATROL,wpr.EXCEPTION_DESC ");
		sb.append(" FROM WPLAN_PATROLRECORD wpr  ");
		sb.append(" JOIN WPLAN_PATROLSUBITEM wps  ");
		sb.append(" ON wpr.SUBITEM_ID=wps.ID  ");
		sb.append(" JOIN WPLAN_PATROLITEM wpi ON wps.ITEM_ID=wpi.ID  ");
		sb.append(" JOIN WPLAN_EXECUTERESULT wer  ");
		sb.append(" ON wpr.EXECUTERESULT_ID=wer.ID  ");
		sb.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID  ");
		sb.append(" JOIN VIEW_PATROLGROUP vp  ");
		sb.append(" ON wp.PATROL_GROUP_ID=vp.ID  ");
		sb.append(" JOIN RES_ZDXX rz ON rz.XTBH=wer.RESOURCE_ID  ");
		sb.append(" WHERE wpr.SUBITEM_PATROL=wps.EXCEPTION_VALUE  ");
		sb.append(" AND rz.XTBH='");
		sb.append(parameter.get("rsId"));
		sb.append("' ");
		sb.append(" AND wp.ID='");
		sb.append(parameter.get("planId"));
		sb.append("' ");
		getUserCondition(parameter, sb);
		getConditionString(parameter, sb);
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 拆迁站点明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getCqResDetail(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select rsv.zymc as NAME,bs.lable,vr.REGIONNAME,vp.ORGNAME,wp.plan_name,t.remark,t.CAUSE from wplan_nopatrolstation t ");
		sb.append(" join wplan_patrolinfo wp on t.plan_id = wp.id ");
		sb.append(" join RES_ZDXX rsv on t.resource_id = rsv.XTBH ");
		sb.append(" left join base_sysdictionary bs on bs.codevalue = wp.business_type and bs.columntype = 'BUSINESSTYPE' ");
		sb.append(" left join view_region vr on vr.regionid = wp.region_id ");
		sb.append(" left join view_patrolgroup vp on vp.ID = wp.patrol_group_id ");
		sb.append(" where t.problem_type = '" + NOPATROLSTATION_PROBLEM_TYPE_CQ
				+ "' ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and wp.region_id = '" + parameter.get("regionId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and vp.orgid = '" + parameter.get("orgId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and wp.patrol_group_id = '"
					+ parameter.get("patrolGroupId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" and wp.business_type = '"
					+ parameter.get("businessType") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and WP.START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("planId"))) {
			sb.append(" and wp.id = '" + parameter.get("planId") + "' ");
		}
		getPatrolCondition(parameter, sb, "vp");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 纠纷站点明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getJfResDetail(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select rsv.zymc as NAME,bs.lable,vr.REGIONNAME,vp.ORGNAME,wp.plan_name,t.remark,t.CAUSE from wplan_nopatrolstation t ");
		sb.append(" join wplan_patrolinfo wp on t.plan_id = wp.id ");
		sb.append(" join RES_ZDXX rsv on t.resource_id = rsv.XTBH ");
		sb.append(" left join base_sysdictionary bs on bs.codevalue = wp.business_type and bs.columntype = 'BUSINESSTYPE' ");
		sb.append(" left join view_region vr on vr.regionid = wp.region_id ");
		sb.append(" left join view_patrolgroup vp on vp.ID = wp.patrol_group_id ");
		sb.append(" where t.problem_type = '" + NOPATROLSTATION_PROBLEM_TYPE_JF
				+ "' ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and wp.region_id = '" + parameter.get("regionId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and vp.orgid = '" + parameter.get("orgId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and wp.patrol_group_id = '"
					+ parameter.get("patrolGroupId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" and wp.business_type = '"
					+ parameter.get("businessType") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and WP.START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("planId"))) {
			sb.append(" and wp.id = '" + parameter.get("planId") + "' ");
		}
		getPatrolCondition(parameter, sb, "vp");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 其它未巡检站点明细
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getQtResDetail(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select rsv.zymc as NAME,bs.lable,vr.REGIONNAME,vp.ORGNAME,wp.plan_name,t.remark,t.CAUSE from wplan_nopatrolstation t ");
		sb.append(" join wplan_patrolinfo wp on t.plan_id = wp.id ");
		sb.append(" join RES_ZDXX rsv on t.resource_id = rsv.XTBH ");
		sb.append(" left join base_sysdictionary bs on bs.codevalue = wp.business_type and bs.columntype = 'BUSINESSTYPE' ");
		sb.append(" left join view_region vr on vr.regionid = wp.region_id ");
		sb.append(" left join view_patrolgroup vp on vp.ID = wp.patrol_group_id ");
		sb.append(" where t.problem_type = '" + NOPATROLSTATION_PROBLEM_TYPE_QT
				+ "' ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and wp.region_id = '" + parameter.get("regionId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and vp.orgid = '" + parameter.get("orgId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and wp.patrol_group_id = '"
					+ parameter.get("patrolGroupId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" and wp.business_type = '"
					+ parameter.get("businessType") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and WP.START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("planId"))) {
			sb.append(" and wp.id = '" + parameter.get("planId") + "' ");
		}
		getPatrolCondition(parameter, sb, "vp");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 根据条件生成异常数量 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param parameter
	 *            查询条件
	 * @return
	 */
	public String getExceptionNubSql(String groupBy,
			Map<String, Object> parameter) {
		String queryKey = "";
		String queryGorupBy = "";
		if ("all".equals(groupBy)) {
			queryKey = "";
			queryGorupBy = "";
		}
		if ("region".equals(groupBy)) {
			queryKey = " vp.REGIONID, ";
			queryGorupBy = " GROUP BY vp.REGIONID ";
		}
		if ("org".equals(groupBy)) {
			queryKey = " vp.ORGID, ";
			queryGorupBy = " GROUP BY vp.ORGID ";
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " vp.ID as PATROLGROUPID, ";
			queryGorupBy = " GROUP BY vp.ID ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  ");
		sb.append(queryKey);
		sb.append(" COUNT(DISTINCT w.RESOURCE_ID) AS NUB ");
		sb.append(" FROM (");
		sb.append(" SELECT wp.ID,wp.PLAN_NAME,wer.RESOURCE_ID,wp.BUSINESS_TYPE, ");
		sb.append(" COUNT(DISTINCT wpr.ID) AS ERROR_NUM ");
		sb.append(" FROM WPLAN_PATROLRECORD wpr  ");
		sb.append(" JOIN WPLAN_PATROLSUBITEM wps  ");
		sb.append(" ON wps.ID=wpr.SUBITEM_ID  ");
		sb.append(" JOIN WPLAN_PATROLITEM wpi ON wpI.ID=wps.ITEM_ID ");
		sb.append(" JOIN WPLAN_EXECUTERESULT wer  ");
		sb.append(" ON wpr.EXECUTERESULT_ID=wer.ID  ");
		sb.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID ");
		sb.append(" JOIN VIEW_PATROLGROUP vp  ");
		sb.append(" ON wp.PATROL_GROUP_ID=vp.ID  ");
		sb.append(" WHERE wps.EXCEPTION_VALUE=wpr.SUBITEM_PATROL ");
		getUserCondition(parameter, sb);
		getConditionString(parameter, sb);
		sb.append(" GROUP BY wer.RESOURCE_ID,wp.BUSINESS_TYPE,wp.PLAN_NAME,wp.ID ");
		sb.append(" ) w ");
		sb.append(" JOIN RES_ZDXX rz ON rz.XTBH=w.RESOURCE_ID ");
		sb.append(" LEFT JOIN RES_MAINTENANCE rm ON rz.XTBH=rm.RS_ID ");
		sb.append(" LEFT JOIN VIEW_PATROLGROUP vp ON vp.ID=rm.PATROL_GROUP_ID ");
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 根据条件生成未巡检数量数量 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param parameter
	 *            查询条件
	 * @param problemType
	 *            String
	 * @return
	 */
	public String getNoPatrolNubSql(String problemType, String groupBy,
			Map<String, Object> parameter) {
		String queryKey = "";
		String queryGorupBy = "";
		if ("region".equals(groupBy)) {
			queryKey = " WP.REGION_ID, ";
			queryGorupBy = " GROUP BY WP.REGION_ID ";
		}
		if ("org".equals(groupBy)) {
			queryKey = " VPG.ORGID, ";
			queryGorupBy = " GROUP BY VPG.ORGID ";
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " WP.PATROL_GROUP_ID, ";
			queryGorupBy = " GROUP BY WP.PATROL_GROUP_ID ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  ");
		sb.append(queryKey);
		sb.append(" COUNT(WNS.ID)AS NUB  FROM WPLAN_NOPATROLSTATION WNS ");
		sb.append(" JOIN WPLAN_PATROLINFO WP ON WNS.PLAN_ID = WP.ID ");
		sb.append(" JOIN VIEW_PATROLGROUP VPG ON WP.PATROL_GROUP_ID = VPG.ID ");
		sb.append(" WHERE WNS.PROBLEM_TYPE = '" + problemType
				+ "' AND WNS.RESULT='0' ");
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and WP.START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" AND WP.BUSINESS_TYPE = '"
					+ parameter.get("businessType") + "' ");
		}
		getUserCondition(parameter, sb);
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 获取查询条件的字串
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getConditionString(Map<String, Object> parameters,
			StringBuffer sqlBuffer) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("wp");
		parameter.setColumnName("ID");
		parameter.setValue((String) parameters.get("planId"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("BUSINESS_TYPE");
		parameter.setValue((String) parameters.get("businessType"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("REGION_ID");
		parameter.setValue((String) parameters.get("regionId"));
		sqlBuffer.append(BusinessConditionUtils.getRegionCondition(parameter));
		parameter.setColumnName("PATROL_GROUP_ID");
		parameter.setValue((String) parameters.get("patrolId"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setAlias("vp");
		parameter.setColumnName("PARENTID");
		parameter.setValue((String) parameters.get("orgId"));
		sqlBuffer.append(BusinessConditionUtils.getOrgCondition(parameter));
		getTimeConditionString(parameters, sqlBuffer);
	}

	/**
	 * 获取巡检的查询条件
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 * @param alias
	 *            String
	 */
	private void getPatrolCondition(Map<String, Object> parameters,
			StringBuffer sqlBuffer, String alias) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias(alias);
		parameter.setColumnName("REGIONID");
		parameter.setValue((String) parameters.get("regionId"));
		sqlBuffer.append(BusinessConditionUtils.getRegionCondition(parameter));
		parameter.setColumnName("ID");
		parameter.setValue((String) parameters.get("patrolId"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("PARENTID");
		parameter.setValue((String) parameters.get("orgId"));
		sqlBuffer.append(BusinessConditionUtils.getOrgCondition(parameter));
	}

	/**
	 * 获取时间查询条件
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getTimeConditionString(Map<String, Object> parameters,
			StringBuffer sqlBuffer) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("wp");
		parameter.setColumnName("END_TIME");
		parameter.setValue((String) parameters.get("startTime"));
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionDateTimeByAndLogicOperator(parameter));
		parameter.setValue((String) parameters.get("endTime"));
		parameter.setOperator(ConditionGenerateUtils.LE_OPERATOR);
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionDateTimeByAndLogicOperator(parameter));
	}

	/**
	 * 获取用户专业查询条件
	 * 
	 * @param parameters
	 *            Map<String, Object>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getUserCondition(Map<String, Object> parameters,
			StringBuffer sqlBuffer) {
		UserInfo user = (UserInfo) parameters.get("user");
		if (user == null) {
			return;
		}
		List<Map<String, Object>> businessTypeList = user.getBusinessTypes();
		String businessTypeStr = "";
		if (CollectionUtils.isNotEmpty(businessTypeList)) {
			for (int i = 0; i < businessTypeList.size(); i++) {
				Map<String, Object> map = businessTypeList.get(i);
				businessTypeStr += "'";
				businessTypeStr += map.get("CODEVALUE");
				businessTypeStr += "'";
				if (i < businessTypeList.size() - 1) {
					businessTypeStr += ",";
				}
			}
			sqlBuffer.append(" AND BUSINESS_TYPE IN ( ");
			sqlBuffer.append(businessTypeStr);
			sqlBuffer.append(" )");
		}
	}

	/**
	 * 获取计划信息
	 * 
	 * @param parameter
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return
	 */
	public Page getPlanInfo(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select " + createDetailCondition(parameter) + " t.*, ");
		sb.append(" (t.patrolcount-t.endpatrolcount) nopatrolcount, ");
		sb.append(" case when t.patrolcount=0 then '--' else to_char(Round(TO_NUMBER((t.endpatrolcount/t.patrolcount)*100),2),'9999990.99')||'%' end PLANRATE, ");
		sb.append(" case when t.rescount=0 then '--' else to_char(Round(TO_NUMBER((t.patrolcount/t.rescount)*100),2),'9999990.99')||'%' end PLANOVERRATE, ");
		sb.append(" case when t.rescount=0 then '--' else to_char(Round(TO_NUMBER((t.endpatrolcount/t.rescount)*100),2),'9999990.99')||'%' end REALRATE, ");
		sb.append(" FN_GET_EXCEPTION_STATION_COUNT(t.id,to_char(t.start_time,'yyyy-mm-dd hh24:mi:ss'),to_char(t.end_time,'yyyy-mm-dd hh24:mi:ss')) EXCEPTIONCOUNT, ");
		sb.append(" (select count(resource_id) from wplan_nopatrolstation n where n.plan_id=t.id and problem_type='01' AND RESULT='0') CQNUM, ");
		sb.append(" (select count(resource_id) from wplan_nopatrolstation n where n.plan_id=t.id and problem_type='02' AND RESULT='0') JFNUM, ");
		sb.append(" (select count(resource_id) from wplan_nopatrolstation n where n.plan_id=t.id and problem_type='03' AND RESULT='0') QTNUM  ");
		sb.append(" from ((select tt.*, (select count(1) from WPLAN_PATROLRESOURCE pr where pr.plan_id=tt.id)  patrolcount,nvl(g.endpatrolcount,0) endpatrolcount ");
		sb.append(" from  (select distinct pi.id,to_char(pi.createtime,'yyyy-mm-dd hh24:mi:ss') as createtime,pi.maintain_resources_num as rescount,pi.region_id,pi.year,pi.plan_type,pi.PLAN_state,PI.BUSINESS_TYPE,pi.plan_name,pi.start_time,pi.end_time,p.orgid,p.orgname, ");
		sb.append(" pi.PATROL_GROUP_ID,p.name patrolGROUPname,vr.regionname from WPLAN_PATROLINFO pi join WPLAN_PATROLRESOURCE pr on pr.plan_id=pi.id   join  view_region vr on pi.region_id=vr.regionid   join view_patrolgroup p on pi.patrol_group_id=p.id)tt");
		sb.append(" left join (select count(distinct r.id) endpatrolcount,r.plan_id from wplan_executeresult r  join WPLAN_PATROLRESOURCE pr  on pr.plan_id=r.plan_id and pr.resource_id=r.resource_id and pr.resource_type=r.resource_type ");
		sb.append(" and r.end_time=(select max(end_time) from wplan_executeresult where plan_id=r.plan_id and resource_id=r.resource_id and resource_type=r.resource_type and patrol_group_id=r.patrol_group_id) group by r.plan_id) g on tt.id=g.plan_id))t");
		sb.append(" where PLAN_state='03'");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))
				&& !((String) parameter.get("regionId")).endsWith("0000")) {
			sb.append(" and REGION_ID = '" + parameter.get("regionId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and orgid = '" + parameter.get("orgId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" and business_type = '" + parameter.get("businessType")
					+ "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and START_TIME between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		getUserCondition(parameter, sb);
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @return
	 */
	public String createDetailCondition(Map<String, Object> parameter) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank((String) parameter.get("businessType"))) {
			sb.append(" '" + parameter.get("businessType")
					+ "' as businessType,");
		} else {
			sb.append(" '' as businessType,");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" '" + parameter.get("startTime") + "' AS startTime,'"
					+ parameter.get("endTime") + "' AS endTime , ");
		} else {
			sb.append(" '' AS startTime,'' AS endTime,");
		}
		return sb.toString();
	}

}