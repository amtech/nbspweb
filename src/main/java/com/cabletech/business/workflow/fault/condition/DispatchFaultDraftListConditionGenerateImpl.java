package com.cabletech.business.workflow.fault.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.model.FaultAlert;

/**
 * 故障派单草稿箱查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Component
public class DispatchFaultDraftListConditionGenerateImpl extends
		BaseConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		UserInfo user = parameter.getUser();
		if (super.preSetParameter(parameter)) {
			parameter.setAlias("wa");
			super.getConditionSqlByParameter((FaultQueryParameter) parameter,
					super.businessDataCondition);
		}
		parameter.setAlias("wa");
		parameter.setColumnName("IGNORE_STATE");
		parameter.setValue(FaultAlert.NOT_SUBMITED_STATE);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		super.businessDataCondition.append(super.getSelfCreatedCondition(user));
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.TROUBLE_TIME DESC,business_table.ID DESC ";
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 * 
	 * @return String 变动表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
	}
}
