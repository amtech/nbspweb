package com.cabletech.business.analysis.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 巡检异常项统计分析Dao
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class ProblemPatrolItemAnalyseDao extends BaseDao {
	/**
	 * 获取 巡检异常项统计分析列表
	 * 
	 * @param parameters
	 *            Map<String, String> 参数
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("unchecked")
	public void getProblemPatrolItem(Map<String, Object> parameters, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT wpi.ID,wps.ID AS SUBITEM_ID,wpi.ITEM_NAME, ");
		sqlBuffer.append(" wps.SUBITEM_NAME,count(wer.ID) AS ERROR_NUM ");
		sqlBuffer.append(" FROM WPLAN_PATROLRECORD wpr ");
		sqlBuffer.append(" JOIN WPLAN_PATROLSUBITEM wps ");
		sqlBuffer.append(" ON wpr.SUBITEM_ID=wps.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLITEM wpi ON wps.ITEM_ID=wpi.ID ");
		sqlBuffer.append(" JOIN WPLAN_EXECUTERESULT wer ");
		sqlBuffer.append(" ON wpr.EXECUTERESULT_ID=wer.ID ");
		sqlBuffer.append(" JOIN WPLAN_PATROLINFO wp ON wer.PLAN_ID=wp.ID ");
		sqlBuffer.append(" JOIN VIEW_PATROLGROUP vp ");
		sqlBuffer.append(" ON wp.PATROL_GROUP_ID=vp.ID ");
		sqlBuffer.append(" WHERE wpr.SUBITEM_PATROL=wps.EXCEPTION_VALUE ");
		getConditionString(parameters, sqlBuffer);
		getUserCondition(parameters, sqlBuffer);
		sqlBuffer.append(" GROUP BY wpi.ID,wps.ID, ");
		sqlBuffer.append(" wpi.ITEM_NAME,wps.SUBITEM_NAME ");
		sqlBuffer.append(" ORDER BY count(wer.ID) DESC ");
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