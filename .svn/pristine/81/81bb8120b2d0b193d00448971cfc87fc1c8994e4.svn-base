package com.cabletech.business.base.condition;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 业务查询条件生成工具类
 * 
 * @author 杨隽 2012-07-12 创建
 * 
 */
public class BusinessConditionUtils {
	/**
	 * 根据当前用户获取已办查询条件的sql查询语句
	 * 
	 * @param parameter
	 *            QueryParameter 当前用户信息存放参数
	 * @param joinDataCondition
	 *            StringBuffer 待办查询条件的sql查询语句存放区
	 */
	public static void getWaitHandledCondition(QueryParameter parameter,
			StringBuffer joinDataCondition) {
		// 当前用户待办查询条件
		UserInfo user = parameter.getUser();
		joinDataCondition.append(" ");
		joinDataCondition.append(ConditionGenerateUtils.AND_LOGIC_OPERATOR);
		joinDataCondition.append(ConditionGenerateUtils.LEFT_PARENTHESIS);
		parameter.setColumnName("assignee_");
		parameter.setValue(user.getPersonId());
		joinDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByNoLogicOperator(parameter));
		parameter.setColumnName("userid_");
		parameter.setValue(user.getPersonId());
		joinDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByOrLogicOperator(parameter));
		parameter.setColumnName("groupid_");
		parameter.setValue(user.getOrgId());
		joinDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByOrLogicOperator(parameter));
		joinDataCondition.append(ConditionGenerateUtils.RIGHT_PARENTHESIS);
		joinDataCondition.append(" ");
	}

	/**
	 * 根据当前用户获取已办查询条件的sql查询语句
	 * 
	 * @param parameter
	 *            QueryParameter 当前用户信息存放参数
	 * @param joinDataCondition
	 *            StringBuffer 已办查询条件的sql查询语句存放区
	 */
	public static void getHandledCondition(QueryParameter parameter,
			StringBuffer joinDataCondition) {
		// 当前用户已办查询条件
		UserInfo user = parameter.getUser();
		parameter.setColumnName("assignee");
		parameter.setValue(user.getPersonId());
		joinDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 根据区域编号获取区域级联查询条件的sql查询语句
	 * 
	 * @param parameter
	 *            QueryParameter 当前用户信息存放参数
	 * @return StringBuffer 区域级联查询条件的sql查询语句
	 */
	public static StringBuffer getRegionCondition(QueryParameter parameter) {
		StringBuffer queryCondition = new StringBuffer("");
		if (StringUtils.isNotBlank(parameter.getValue())) {
			queryCondition.append(" AND EXISTS ( ");
			queryCondition.append(" SELECT r.REGIONID FROM REGION r ");
			queryCondition.append(" WHERE 1=1 ");
			if (StringUtils.isNotBlank(parameter.getColumnName())) {
				queryCondition
						.append(ConditionGenerateUtils.AND_LOGIC_OPERATOR);
				queryCondition.append(" ");
				if (StringUtils.isNotBlank(parameter.getAlias())) {
					queryCondition.append(parameter.getAlias());
					queryCondition.append(".");
				}
				queryCondition.append(parameter.getColumnName());
				queryCondition.append(" = r.REGIONID ");
				queryCondition.append(" START WITH r.REGIONID='");
				queryCondition.append(parameter.getValue());
				queryCondition.append("' ");
				queryCondition
						.append(" CONNECT BY PRIOR r.REGIONID=r.PARENTREGIONID ");
			}
			queryCondition.append(" ) ");
		}
		return queryCondition;
	}

	/**
	 * 根据组织编号获取组织级联查询条件的sql查询语句
	 * 
	 * @param parameter
	 *            QueryParameter 当前用户信息存放参数
	 * @return StringBuffer 组织级联查询条件的sql查询语句
	 */
	public static StringBuffer getOrgCondition(QueryParameter parameter) {
		StringBuffer queryCondition = new StringBuffer("");
		if (StringUtils.isNotBlank(parameter.getValue())) {
			queryCondition.append(" AND EXISTS ( ");
			queryCondition.append(" SELECT vo.ID FROM VIEW_ORG vo ");
			queryCondition.append(" WHERE 1=1 ");
			if (StringUtils.isNotBlank(parameter.getColumnName())) {
				queryCondition
						.append(ConditionGenerateUtils.AND_LOGIC_OPERATOR);
				queryCondition.append(" ");
				if (StringUtils.isNotBlank(parameter.getAlias())) {
					queryCondition.append(parameter.getAlias());
					queryCondition.append(".");
				}
				queryCondition.append(parameter.getColumnName());
				queryCondition.append(" = vo.ID ");
				queryCondition.append(" START WITH vo.ID='");
				queryCondition.append(parameter.getValue());
				queryCondition.append("' ");
				queryCondition.append(" CONNECT BY PRIOR vo.ID=vo.PARENTID ");
			}
			queryCondition.append(" ) ");
		}
		return queryCondition;
	}

	/**
	 * 根据当前用户获取专业类型查询条件的sql查询语句
	 * 
	 * @param parameter
	 *            QueryParameter 当前用户信息存放参数
	 * @return StringBuffer 用户专业类型查询条件
	 */
	public static StringBuffer getBusinessTypeCondition(QueryParameter parameter) {
		StringBuffer queryCondition = new StringBuffer("");
		if (QueryParameter.isNull(parameter.getUser())) {
			return queryCondition;
		}
		List<Map<String, Object>> businessTypeList = parameter.getUser()
				.getBusinessTypes();
		String businessTypeStr = "";
		if (CollectionUtils.isEmpty(businessTypeList)) {
			return queryCondition;
		}
		for (int i = 0; i < businessTypeList.size(); i++) {
			Map<String, Object> map = businessTypeList.get(i);
			businessTypeStr += "'";
			businessTypeStr += map.get("CODEVALUE");
			businessTypeStr += "'";
			if (i < businessTypeList.size() - 1) {
				businessTypeStr += ",";
			}
		}
		parameter.setValue(businessTypeStr);
		queryCondition.append(ConditionGenerateUtils
				.getConditionInByAndLogicOperator(parameter));
		return queryCondition;
	}
}
