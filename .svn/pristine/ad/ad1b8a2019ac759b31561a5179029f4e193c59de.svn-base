package com.cabletech.business.workflow.wmaintain.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 维修作业计划中计划维护站点编号查询条件生成器接口实现
 * 
 * @author 杨隽 2012-04-18 创建
 * 
 */
@Component
public class WmPlanSiteIdConditionGenerateImpl extends BaseConditionGenerate {
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
		parameter.setColumnName("id");
		parameter.setValue(parameter.getId());
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.RS_NAME,business_table.SUBITEM_NAME,business_table.ID ";
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
}
