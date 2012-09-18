package com.cabletech.business.analysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 问题站点统计分析Dao
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class ProblemStationAnalyseDao extends BaseDao {
	/**
	 * 获取问题站点统计分析列表
	 * 
	 * @param parameters
	 *            Map<String, String> 参数
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("unchecked")
	public void getProblemStations(Map<String, Object> parameters, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" SELECT rz.ZYMC AS STATION_NAME,w.ERROR_NUM,w.BUSINESS_TYPE AS BUSINESSTYPE, ");
		sqlBuffer.append(" dic.LABLE AS BUSINESS_TYPE,rz.XTBH AS STATION_ID, ");
		sqlBuffer.append(" rz.DZ AS ADDRESS,vo.NAME AS ORG_NAME, ");
		sqlBuffer.append(" vo.LINKMANINFO AS ORG_LINKMAN, ");
		sqlBuffer.append(" vo.LINKMANTEL AS ORG_TEL ");
		sqlBuffer.append(" FROM ( ");
		sqlBuffer.append(" SELECT wer.RESOURCE_ID,wp.BUSINESS_TYPE, ");
		sqlBuffer.append(" COUNT(DISTINCT wpr.ID) AS ERROR_NUM ");
		sqlBuffer.append(" FROM WPLAN_PATROLRECORD wpr ");
		sqlBuffer.append(" JOIN WPLAN_PATROLSUBITEM wps ");
		sqlBuffer.append(" ON wps.ID=wpr.SUBITEM_ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLITEM wpi ON wpI.ID=wps.ITEM_ID ");
		sqlBuffer.append(" JOIN WPLAN_EXECUTERESULT wer ");
		sqlBuffer.append(" ON wpr.EXECUTERESULT_ID=wer.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN VIEW_PATROLGROUP vp ");
		sqlBuffer.append(" ON wp.PATROL_GROUP_ID=vp.ID ");
		sqlBuffer.append(" WHERE wps.EXCEPTION_VALUE=wpr.SUBITEM_PATROL ");
		getConditionString(parameters, sqlBuffer);
		sqlBuffer.append(" GROUP BY wer.RESOURCE_ID,wp.BUSINESS_TYPE ");
		sqlBuffer.append(" ) w");
		sqlBuffer.append(" JOIN RES_ZDXX rz ON rz.XTBH=w.RESOURCE_ID ");
		sqlBuffer.append(" JOIN RES_MAINTENANCE rm ON rz.XTBH=rm.RS_ID ");
		sqlBuffer.append(" AND rm.RS_TYPE=w.BUSINESS_TYPE ");
		sqlBuffer.append(" JOIN VIEW_ORG vo ON rm.MAINTENANCE_ID=vo.ID ");
		sqlBuffer.append(" JOIN BASE_SYSDICTIONARY dic ");
		sqlBuffer.append(" ON dic.CODEVALUE=w.BUSINESS_TYPE ");
		sqlBuffer.append(" AND dic.COLUMNTYPE='BUSINESSTYPE' ");
		getUserCondition(parameters, sqlBuffer);
		sqlBuffer.append(" ORDER BY w.ERROR_NUM DESC,rz.XTBH ");
		super.getSQLPageAll(page, sqlBuffer.toString());
	}

	/**
	 * 获取站点巡检结果统计分析列表
	 * 
	 * @param parameters
	 *            Map<String, String> 参数
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("unchecked")
	public void getProblemStationItems(Map<String, Object> parameters, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT wer.RESOURCE_ID,wp.BUSINESS_TYPE, ");
		sqlBuffer.append(" wp.PLAN_NAME,wpi.ITEM_NAME,wps.SUBITEM_NAME, ");
		sqlBuffer.append(" TO_CHAR(wpr.PATROL_TIME,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS REPORT_DATE_DIS,wpr.EXCEPTION_DESC, ");
		sqlBuffer.append(" DECODE(wr.ID,NULL,'否','','否','是') ");
		sqlBuffer.append(" AS IS_PROCESSED,wr.MAINTAIN_RESULT, ");
		sqlBuffer.append(" TO_CHAR(wr.MAINTAIN_DATE,'yyyy-mm-dd hh24:mi:ss') ");
		sqlBuffer.append(" AS PROCESSED_DATE_DIS, ");
		sqlBuffer.append(" vpm.NAME AS PROCESSED_PATROLGROUP ");
		sqlBuffer.append(" FROM WPLAN_PATROLRECORD wpr ");
		sqlBuffer.append(" JOIN WPLAN_PATROLSUBITEM wps ");
		sqlBuffer.append(" ON wps.ID=wpr.SUBITEM_ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLITEM wpi ON wpI.ID=wps.ITEM_ID ");
		sqlBuffer.append(" JOIN WPLAN_EXECUTERESULT wer ");
		sqlBuffer.append(" ON wpr.EXECUTERESULT_ID=wer.ID ");
		sqlBuffer.append(" LEFT JOIN WMAINTAIN_RESULT wr ");
		sqlBuffer.append(" ON wr.PATROLRECORD_ID=wpr.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN VIEW_PATROLGROUP vp ");
		sqlBuffer.append(" ON wp.PATROL_GROUP_ID=vp.ID ");
		sqlBuffer.append(" LEFT JOIN VIEW_PATROLGROUP vpm ");
		sqlBuffer.append(" ON wr.PATROLMAN_ID=vpm.ID ");
		sqlBuffer.append(" WHERE wps.EXCEPTION_VALUE=wpr.SUBITEM_PATROL ");
		getConditionString(parameters, sqlBuffer);
		sqlBuffer.append(" ORDER BY wp.END_TIME DESC,wp.ID DESC ");
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
			sqlBuffer.append(" AND w.BUSINESS_TYPE IN ( ");
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
		getTimeConditionString(parameters, sqlBuffer);
		parameter.setAlias("wer");
		parameter.setColumnName("RESOURCE_ID");
		parameter.setValue((String) parameters.get("id"));
		sqlBuffer.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
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
	}
}