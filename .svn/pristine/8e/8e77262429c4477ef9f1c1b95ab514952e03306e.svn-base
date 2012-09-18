package com.cabletech.business.workflow.electricity.security.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;

/**
 * 断电告警单查询条件生成器接口实现
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-07
 *         实现setQuerySql()方法，并添加putAlarmDateCondition()和putUserCondition()方法
 * 
 */
@Component
public class OeAlarmListConditionGenerateImpl extends BaseConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		if (QueryParameter.isNull(parameter)) {
			return;
		}
		OeDispatchTask oeDispatchTask = (OeDispatchTask) parameter.getEntity();
		if (!QueryParameter.isNull(oeDispatchTask)) {
			parameter.setAlias("res");
			parameter.setColumnName("NAME");
			parameter.setValue(oeDispatchTask.getStationName());
			super.businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
			putAlarmDateCondition(oeDispatchTask);
			parameter.setUser(oeDispatchTask.getLoginUser());
			putUserCondition(parameter);
		}
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.CREATE_TIME DESC,business_table.ID DESC ";
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 * 
	 * @return String 变动连接表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * 把告警单日期查询条件存放到缓冲区中
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 发电派单查询参数
	 */
	private void putAlarmDateCondition(OeDispatchTask oeDispatchTask) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("ooa");
		parameter.setColumnName("BLACKOUT_TIME");
		parameter.setValue(oeDispatchTask.getCreateDateMin());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setValue(oeDispatchTask.getCreateDateMax());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}

	/**
	 * 将当前登录用户查询条件放到查询条件缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	private void putUserCondition(QueryParameter parameter) {
		UserInfo userInfo = parameter.getUser();
		if (QueryParameter.isNull(userInfo)) {
			return;
		}
		if (userInfo.isCityContractor()) {
			parameter.setAlias("rm");
			parameter.setColumnName("MAINTENANCE_ID");
			parameter.setValue(userInfo.getOrgId());
			businessDataCondition.append(BusinessConditionUtils
					.getOrgCondition(parameter));
		}
		if (userInfo.isCityMobile()) {
			parameter.setAlias("res");
			parameter.setColumnName("REGIONID");
			parameter.setValue(userInfo.getRegionId());
			businessDataCondition.append(BusinessConditionUtils
					.getRegionCondition(parameter));
		}
	}
}
