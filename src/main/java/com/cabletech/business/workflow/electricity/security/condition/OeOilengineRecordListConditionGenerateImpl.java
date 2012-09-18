package com.cabletech.business.workflow.electricity.security.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 油机发电记录查询条件生成器接口实现
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Component
public class OeOilengineRecordListConditionGenerateImpl extends
		BaseConditionGenerate {
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
		if (!QueryParameter.isNull(parameter)) {
			parameter.setAlias("oor");
			parameter.setColumnName("TASK_ID");
			parameter.setValue(parameter.getId());
			super.businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
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
		return " ORDER BY business_table.ID DESC ";
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
