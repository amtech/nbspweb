package com.cabletech.business.workflow.workorder.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 通用工单编号查询条件生成器接口实现
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-02-06 添加getJoinTableSql()方法
 * 
 */
@Component
public class TaskIdConditionGenerateImpl extends BaseConditionGenerate {
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
		parameter.setAlias("wo");
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
		return " ORDER BY business_table.CREATE_DATE DESC,business_table.ID DESC ";
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
