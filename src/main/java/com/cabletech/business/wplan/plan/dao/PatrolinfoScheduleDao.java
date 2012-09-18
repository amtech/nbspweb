package com.cabletech.business.wplan.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 
 * 巡检进度查询DAO
 * 
 * @author wj
 * 
 */
@Repository
public class PatrolinfoScheduleDao extends BaseDao {
	// 资源明细
	private String detailed_sql = " select vsd.lable as restype,rrv.NAME as resname,vpgp.ORGNAME,vpgp.PARENTNAME as patrolgroupname, "
			+ " vpgp.NAME as patrolmanname,r.arrive_time,r.start_time,r.end_time,  "
			+ " (trunc(to_number(END_TIME - START_TIME))||'天'||mod(trunc(to_number(END_TIME - START_TIME)*24),24) ||'小时'||mod(trunc(to_number(END_TIME - START_TIME)*24*60),60) ||'分'||mod(round(to_number(END_TIME - START_TIME)*24*60*60),60) ||'秒') as PRESSURE "
			+ " from wplan_executeresult r "
			+ " join WPLAN_PATROLRESOURCE pr  on pr.plan_id=r.plan_id and pr.resource_id=r.resource_id and pr.resource_type=r.resource_type "
			+ " and r.end_time=(select max(end_time) from wplan_executeresult where plan_id=r.plan_id "
			+ " and resource_id=r.resource_id and resource_type=r.resource_type and patrol_group_id=r.patrol_group_id) "
			+ " left join wplan_patrolinfo wp on wp.id=r.plan_id "
			+ " left join view_patrolgroupperson vpgp on r.patrolman_id = vpgp.ID and vpgp.OBJTYPE = 'MAN' "
			+ " left join rs_resourcerecord_v rrv on rrv.ID = r.resource_id and rrv.TYPE = r.resource_type "
			+ " left join view_sysdictionary vsd on rrv.type = vsd.codevalue and vsd.columntype = 'BUSINESSTYPE' "
			+ " where 1 = 1  ";

	/**
	 * 按区域统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
	 * @return LIST
	 */
	public Page statisticsByRegion(Map<String, Object> parameter, Page page) {
		String endpatrol_condition = "";
		String excption_condition = "";
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			endpatrol_condition = " and r.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
			excption_condition = " and ret.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
		}
		endpatrol_condition+=getUserCondition(parameter);
		excption_condition+=getUserCondition(parameter);
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.regionid,t.REGIONNAME,nvl(res1.nub ,0)as resnub,nvl(res2.nub ,0)as excnub,nvl(res3.nub ,0)as excitem from view_region t ");
		sb.append(" left join ( ");
		sb.append(getEndPatrolSql("region", endpatrol_condition));
		sb.append(" ) res1 on res1.regionid = t.regionid ");
		sb.append(" left join ( ");
		sb.append(getExceptionResSql("region", excption_condition));
		sb.append(" ) res2 on res2.regionid = t.regionid ");
		sb.append(" left join ( ");
		sb.append(getExceptionItemSql("region", excption_condition));
		sb.append(" ) res3 on res3.regionid = t.regionid ");
		sb.append(" where t.lv = 3 ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and t.regionid in "
					+ iterationRegion("'" + parameter.get("regionId") + "'"));
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 按组织统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
	 * @return LIST
	 */
	public Page statisticsByOrg(Map<String, Object> parameter, Page page) {
		String endpatrol_condition = "";
		String excption_condition = "";
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			endpatrol_condition = " and r.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
			excption_condition = " and ret.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
		}
		endpatrol_condition+=getUserCondition(parameter);
		excption_condition+=getUserCondition(parameter);
		StringBuffer sb = new StringBuffer();
		sb.append(" select reg.REGIONNAME,t.id,t.name,nvl(res1.nub ,0)as resnub,nvl(res2.nub ,0)as excnub,nvl(res3.nub ,0)as excitem from view_org t ");
		sb.append(" left join ( ");
		sb.append(getEndPatrolSql("org", endpatrol_condition));
		sb.append(") res1 on res1.orgid = t.id ");
		sb.append(" left join ( ");
		sb.append(getExceptionResSql("org", excption_condition));
		sb.append(" ) res2 on res2.orgid = t.id");
		sb.append(" left join ( ");
		sb.append(getExceptionItemSql("org", excption_condition));
		sb.append(" ) res3 on res3.orgid = t.id ");
		sb.append("left join view_region reg on t.regionid = reg.regionid ");
		sb.append("where t.orgtype = '2' ");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and t.regionid in "
					+ iterationRegion("'" + parameter.get("regionId") + "'"));
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and t.id in (select id from base_organize start with id = '"
					+ parameter.get("orgId")
					+ "' connect by prior id=parentid) ");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 按巡检组统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
	 * @return LIST
	 */
	public Page statisticsByPatrolGroup(Map<String, Object> parameter, Page page) {
		String endpatrol_condition = "";
		String excption_condition = "";
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			endpatrol_condition = " and r.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
			excption_condition = " and ret.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
		}
		endpatrol_condition+=getUserCondition(parameter);
		excption_condition+=getUserCondition(parameter);
		StringBuffer sb = new StringBuffer();
		sb.append(" select reg.REGIONNAME,t.orgname,t.id,t.name,nvl(res1.nub ,0)as resnub,nvl(res2.nub ,0)as excnub,nvl(res3.nub ,0)as excitem from view_patrolgroup t ");
		sb.append(" left join ( ");
		sb.append(getEndPatrolSql("patrolgroup", endpatrol_condition));
		sb.append(") res1 on res1.PARENTID = t.id ");
		sb.append(" left join ( ");
		sb.append(getExceptionResSql("patrolgroup", excption_condition));
		sb.append(" ) res2 on res2.PATROLGROUPID = t.id");
		sb.append(" left join ( ");
		sb.append(getExceptionItemSql("patrolgroup", excption_condition));
		sb.append(" ) res3 on res3.PATROLGROUPID = t.id ");
		sb.append("left join view_region reg on t.regionid = reg.regionid ");
		sb.append("where 1=1");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and t.regionid in "
					+ iterationRegion("'" + parameter.get("regionId") + "'"));
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and t.parentid in (select id from base_organize start with id = '"
					+ parameter.get("orgId")
					+ "' connect by prior id=parentid) ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and t.id = '" + parameter.get("patrolGroupId") + "'");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 按巡检员统计
	 * 
	 * @param parameter
	 *            参数
	 * @param page
	 *            Page
	 * @return LIST
	 */
	public Page statisticsByPatrolMan(Map<String, Object> parameter, Page page) {
		String endpatrol_condition = "";
		String excption_condition = "";
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			endpatrol_condition = " and r.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
			excption_condition = " and ret.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ";
		}
		endpatrol_condition+=getUserCondition(parameter);
		excption_condition+=getUserCondition(parameter);
		StringBuffer sb = new StringBuffer();
		sb.append("select reg.REGIONNAME,t.ORGNAME,t.PARENTNAME,t.id,t.NAME,nvl(res1.nub ,0)as resnub,nvl(res2.nub ,0)as excnub,nvl(res3.nub ,0)as excitem from view_patrolgroupperson t ");
		sb.append(" left join ( ");
		sb.append(getEndPatrolSql("patrolman", endpatrol_condition));
		sb.append(") res1 on res1.id = t.id ");
		sb.append(" left join ( ");
		sb.append(getExceptionResSql("patrolman", excption_condition));
		sb.append(" ) res2 on res2.patrolmanid = t.id");
		sb.append(" left join ( ");
		sb.append(getExceptionItemSql("patrolman", excption_condition));
		sb.append(" ) res3 on res3.patrolmanid = t.id ");
		sb.append("left join view_region reg on t.regionid = reg.regionid ");
		sb.append("where t.objtype = 'MAN'");
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and t.regionid in "
					+ iterationRegion("'" + parameter.get("regionId") + "'"));
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and t.orgid in (select id from base_organize start with id = '"
					+ parameter.get("orgId")
					+ "' connect by prior id=parentid) ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and t.parentid = '" + parameter.get("patrolGroupId")
					+ "'");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 查询资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameter
	 *            参数
	 * @return LIST
	 */
	public Page searchDetailed(Map<String, Object> parameter, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(detailed_sql);
		sb.append(getUserCondition(parameter));
		if (StringUtils.isNotBlank((String) parameter.get("regionId"))) {
			sb.append(" and vpgp.regionid in  "
					+ iterationRegion("'" + parameter.get("regionId") + "'"));
		}
		if (StringUtils.isNotBlank((String) parameter.get("orgId"))) {
			sb.append(" and vpgp.orgid = '" + parameter.get("orgId") + "' ");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolGroupId"))) {
			sb.append(" and vpgp.parentid = '" + parameter.get("patrolGroupId")
					+ "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("patrolManId"))) {
			sb.append(" and vpgp.id = '" + parameter.get("patrolManId") + "'");
		}
		if (StringUtils.isNotBlank((String) parameter.get("startTime"))
				&& StringUtils.isNotBlank((String) parameter.get("endTime"))) {
			sb.append(" and r.start_time between to_date('"
					+ parameter.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameter.get("endTime") + "','yyyy-MM-dd') ");
		}
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 根据条件生成巡检站点 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public String getEndPatrolSql(String groupBy, String condition) {
		String queryKey = "";
		String queryCondition = "";
		String queryGorupBy = "";
		if ("region".equals(groupBy)) {
			queryKey = " VPGP.REGIONID, ";
			queryGorupBy = " GROUP BY VPGP.REGIONID ";
			queryCondition = condition;
		}
		if ("org".equals(groupBy)) {
			queryKey = " VPGP.ORGID, ";
			queryGorupBy = " GROUP BY VPGP.ORGID  ";
			queryCondition = condition;
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " VPGP.PARENTID, ";
			queryGorupBy = " GROUP BY VPGP.PARENTID  ";
			queryCondition = condition;
		}
		if ("patrolman".equals(groupBy)) {
			queryKey = " VPGP.ID, ";
			queryGorupBy = " GROUP BY VPGP.ID  ";
			queryCondition = condition;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  ");
		sb.append(queryKey);
		sb.append(" COUNT(*) AS NUB FROM WPLAN_EXECUTERESULT R ");
		sb.append(" JOIN WPLAN_PATROLRESOURCE PR  ON PR.PLAN_ID=R.PLAN_ID AND PR.RESOURCE_ID=R.RESOURCE_ID AND PR.RESOURCE_TYPE=R.RESOURCE_TYPE ");
		sb.append(" AND R.END_TIME=(SELECT MAX(END_TIME) FROM WPLAN_EXECUTERESULT WHERE PLAN_ID=R.PLAN_ID ");
		sb.append(" AND RESOURCE_ID=R.RESOURCE_ID AND RESOURCE_TYPE=R.RESOURCE_TYPE AND PATROL_GROUP_ID=R.PATROL_GROUP_ID) ");
		sb.append(" LEFT JOIN VIEW_PATROLGROUPPERSON VPGP ON R.PATROLMAN_ID = VPGP.ID AND VPGP.OBJTYPE = 'MAN' ");
		sb.append(" JOIN WPLAN_PATROLINFO WP ON PR.PLAN_ID=WP.ID");
		sb.append(" WHERE 1=1 ");
		sb.append(queryCondition);
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 根据条件生成异常站点 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public String getExceptionResSql(String groupBy, String condition) {
		String queryKey = "";
		String queryCondition = "";
		String queryGorupBy = "";
		if ("region".equals(groupBy)) {
			queryKey = " RET.REGIONID, ";
			queryGorupBy = " GROUP BY RET.REGIONID ";
			queryCondition = condition;
		}
		if ("org".equals(groupBy)) {
			queryKey = " RET.ORGID, ";
			queryGorupBy = " GROUP BY RET.ORGID  ";
			queryCondition = condition;
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " RET.PATROLGROUPID, ";
			queryGorupBy = " GROUP BY RET.PATROLGROUPID  ";
			queryCondition = condition;
		}
		if ("patrolman".equals(groupBy)) {
			queryKey = " RET.PATROLMANID, ";
			queryGorupBy = " GROUP BY RET.PATROLMANID  ";
			queryCondition = condition;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(queryKey);
		sb.append(" COUNT(DISTINCT WPRC.EXECUTERESULT_ID) AS NUB FROM WPLAN_PATROLRECORD WPRC ");
		sb.append(" JOIN WPLAN_PATROLSUBITEM WPSI ON WPSI.ID = WPRC.SUBITEM_ID ");
		sb.append(" JOIN ( ");
		sb.append(" SELECT WER.*,VPGP.REGIONID,VPGP.ORGID,VPGP.PARENTNAME AS PATROLGROUPNAME,VPGP.PARENTID AS PATROLGROUPID,VPGP.ID AS PATROLMANID,VPGP.NAME AS PATROLNAME FROM WPLAN_EXECUTERESULT WER ");
		sb.append(" JOIN WPLAN_PATROLRESOURCE PR  ON PR.PLAN_ID=WER.PLAN_ID AND PR.RESOURCE_ID=WER.RESOURCE_ID AND PR.RESOURCE_TYPE=WER.RESOURCE_TYPE ");
		sb.append(" AND WER.END_TIME=(SELECT MAX(END_TIME) FROM WPLAN_EXECUTERESULT WHERE PLAN_ID=WER.PLAN_ID ");
		sb.append(" AND RESOURCE_ID=WER.RESOURCE_ID AND RESOURCE_TYPE=WER.RESOURCE_TYPE AND PATROL_GROUP_ID=WER.PATROL_GROUP_ID) ");
		sb.append(" LEFT JOIN VIEW_PATROLGROUPPERSON VPGP ON WER.PATROLMAN_ID = VPGP.ID AND VPGP.OBJTYPE = 'MAN' ");
		sb.append(" ) RET ON RET.ID = WPRC.EXECUTERESULT_ID ");
		sb.append(" JOIN WPLAN_PATROLINFO WP ON RET.PLAN_ID=WP.ID");
		sb.append(" WHERE WPSI.EXCEPTION_VALUE IS NOT NULL AND WPRC.SUBITEM_PATROL=WPSI.EXCEPTION_VALUE ");
		sb.append(queryCondition);
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 根据条件生成异常项 SQL
	 * 
	 * @param groupBy
	 *            查询标识
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public String getExceptionItemSql(String groupBy, String condition) {
		String queryKey = "";
		String queryCondition = "";
		String queryGorupBy = "";
		if ("region".equals(groupBy)) {
			queryKey = " RET.REGIONID, ";
			queryGorupBy = " GROUP BY RET.REGIONID ";
			queryCondition = condition;
		}
		if ("org".equals(groupBy)) {
			queryKey = " RET.ORGID, ";
			queryGorupBy = " GROUP BY RET.ORGID  ";
			queryCondition = condition;
		}
		if ("patrolgroup".equals(groupBy)) {
			queryKey = " RET.PATROLGROUPID, ";
			queryGorupBy = " GROUP BY RET.PATROLGROUPID  ";
			queryCondition = condition;
		}
		if ("patrolman".equals(groupBy)) {
			queryKey = " RET.PATROLMANID, ";
			queryGorupBy = " GROUP BY RET.PATROLMANID  ";
			queryCondition = condition;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(queryKey);
		sb.append(" COUNT(WPRC.ID) AS NUB FROM WPLAN_PATROLRECORD WPRC ");
		sb.append(" JOIN WPLAN_PATROLSUBITEM WPSI ON WPSI.ID = WPRC.SUBITEM_ID ");
		sb.append(" JOIN ( ");
		sb.append(" SELECT WER.*,VPGP.REGIONID,VPGP.ORGID,VPGP.PARENTNAME AS PATROLGROUPNAME,VPGP.PARENTID AS PATROLGROUPID,VPGP.ID AS PATROLMANID,VPGP.NAME AS PATROLNAME FROM WPLAN_EXECUTERESULT WER ");
		sb.append(" JOIN WPLAN_PATROLRESOURCE PR  ON PR.PLAN_ID=WER.PLAN_ID AND PR.RESOURCE_ID=WER.RESOURCE_ID AND PR.RESOURCE_TYPE=WER.RESOURCE_TYPE ");
		sb.append(" AND WER.END_TIME=(SELECT MAX(END_TIME) FROM WPLAN_EXECUTERESULT WHERE PLAN_ID=WER.PLAN_ID ");
		sb.append(" AND RESOURCE_ID=WER.RESOURCE_ID AND RESOURCE_TYPE=WER.RESOURCE_TYPE AND PATROL_GROUP_ID=WER.PATROL_GROUP_ID) ");
		sb.append(" LEFT JOIN VIEW_PATROLGROUPPERSON VPGP ON WER.PATROLMAN_ID = VPGP.ID AND VPGP.OBJTYPE = 'MAN' ");
		sb.append(" ) RET ON RET.ID = WPRC.EXECUTERESULT_ID ");
		sb.append(" JOIN WPLAN_PATROLINFO WP ON RET.PLAN_ID=WP.ID");
		sb.append(" WHERE WPSI.EXCEPTION_VALUE IS NOT NULL AND WPRC.SUBITEM_PATROL=WPSI.EXCEPTION_VALUE ");
		sb.append(queryCondition);
		sb.append(queryGorupBy);
		return sb.toString();
	}

	/**
	 * 封装区域递归
	 * 
	 * @param regionId
	 *            区域id
	 * @return sql
	 */
	public String iterationRegion(String regionId) {
		return " (select regionid from base_region start with regionid= "
				+ regionId + " connect by prior regionid=parentid) ";
	}

	/**
	 * 获取用户专业查询条件
	 * 
	 * @param parameters
	 *            Map<String, Object>
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private String getUserCondition(Map<String, Object> parameters) {
		UserInfo user = (UserInfo) parameters.get("user");
		if (user == null) {
			return "";
		}
		List<Map<String, Object>> businessTypeList = user.getBusinessTypes();
		String businessTypeStr = "";
		StringBuffer sqlBuffer = new StringBuffer("");
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
			sqlBuffer.append(" AND wp.BUSINESS_TYPE IN ( ");
			sqlBuffer.append(businessTypeStr);
			sqlBuffer.append(" )");
		}
		return sqlBuffer.toString();
	}
}