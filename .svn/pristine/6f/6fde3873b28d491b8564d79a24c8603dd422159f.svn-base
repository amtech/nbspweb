package com.cabletech.business.analysis.dao;

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
 * 站点巡检结果统计分析Dao
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class StationPatrolResultAnalyseDao extends BaseDao {
	/**
	 * 获取站点巡检结果统计分析列表
	 * 
	 * @param parameters
	 *            Map<String, String> 参数
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("unchecked")
	public void getStationPatrolResult(Map<String, Object> parameters, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" SELECT wp.ID AS PLAN_ID,wer.ID AS RESULT_ID,rz.ZYMC AS STATION_NAME, ");
		sqlBuffer.append(" vp.PARENTNAME AS ORG_NAME,vp.NAME AS PATROL_NAME, ");
		sqlBuffer.append(" wp.PLAN_NAME,dic.LABLE AS BUSINESS_TYPE, ");
		sqlBuffer
				.append(" DECODE(wer.ID,NULL,'否',decode(replace(wer.RESOURCE_ID,wpr.RESOURCE_ID),'','是','否'))  ");
		sqlBuffer.append(" AS IS_PATROLED, ");
		sqlBuffer.append(" TO_CHAR(wer.END_TIME,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS PATROL_TIME_DIS, ");
		sqlBuffer.append(" vpg.NAME AS PATROL_MAN ");
		sqlBuffer.append(" FROM WPLAN_PATROLINFO wp ");
		sqlBuffer.append(" JOIN WPLAN_PATROLRESOURCE wpr ");
		sqlBuffer.append(" ON wpr.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN RES_ZDXX rz ON rz.XTBH=wpr.RESOURCE_ID ");
		sqlBuffer.append(" JOIN VIEW_PATROLGROUP vp ");
		sqlBuffer.append(" ON wp.PATROL_GROUP_ID=vp.ID ");
		sqlBuffer.append(" JOIN BASE_SYSDICTIONARY dic ");
		sqlBuffer.append(" ON dic.CODEVALUE=wp.BUSINESS_TYPE ");
		sqlBuffer.append(" AND dic.COLUMNTYPE='BUSINESSTYPE' ");
		sqlBuffer.append(" LEFT JOIN ( ");
		sqlBuffer
				.append(" SELECT we.PLAN_ID,we.END_TIME,we.RESOURCE_ID,we.RESOURCE_TYPE, ");
		sqlBuffer.append(" we.ID,we.PATROL_GROUP_ID,we.PATROLMAN_ID ");
		sqlBuffer.append(" FROM WPLAN_EXECUTERESULT we ");
		sqlBuffer.append(" WHERE we.END_TIME=( ");
		sqlBuffer.append(" SELECT MAX(w.END_TIME) FROM WPLAN_EXECUTERESULT w ");
		sqlBuffer
				.append(" WHERE w.PLAN_ID=we.PLAN_ID AND we.RESOURCE_ID=w.RESOURCE_ID ");
		sqlBuffer.append(" ) ");
		sqlBuffer
				.append(" ) wer ON wer.PLAN_ID=wp.ID AND wer.RESOURCE_ID=wpr.RESOURCE_ID ");
		sqlBuffer.append(" LEFT JOIN VIEW_PATROLGROUPPERSON vpg ");
		sqlBuffer.append(" ON vpg.ID=wer.PATROLMAN_ID ");
		sqlBuffer.append(" WHERE wp.PLAN_STATE='" + SysConstant.PASSED_STATE
				+ "' ");
		getUserCondition(parameters, sqlBuffer);
		getConditionString(parameters, sqlBuffer);
		sqlBuffer
				.append(" ORDER BY wp.END_TIME DESC,wer.END_TIME DESC,wp.ID DESC ");
		super.getSQLPageAll(page, sqlBuffer.toString());
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
			sqlBuffer.append(" AND wp.BUSINESS_TYPE IN ( ");
			sqlBuffer.append(businessTypeStr);
			sqlBuffer.append(" )");
		}
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
		if (StringUtils.isNotBlank((String) parameters.get("startTime"))
				|| StringUtils.isNotBlank((String) parameters.get("endTime"))) {
			sqlBuffer.append(" AND ((wer.END_TIME IS NULL) ");
			sqlBuffer.append(" OR ( 1=1 ");
			parameter.setAlias("wer");
			parameter.setColumnName("END_TIME");
			parameter.setValue((String) parameters.get("startTime"));
			parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
			sqlBuffer.append(ConditionGenerateUtils
					.getConditionDateTimeByAndLogicOperator(parameter));
			parameter.setValue((String) parameters.get("endTime"));
			parameter.setOperator(ConditionGenerateUtils.LE_OPERATOR);
			sqlBuffer.append(ConditionGenerateUtils
					.getConditionDateTimeByAndLogicOperator(parameter));
			sqlBuffer.append(" ) ");
			sqlBuffer.append(" ) ");
		}
		parameter.setAlias("rz");
		parameter.setColumnName("ZYMC");
		parameter.setValue((String) parameters.get("stationName"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
	}
}