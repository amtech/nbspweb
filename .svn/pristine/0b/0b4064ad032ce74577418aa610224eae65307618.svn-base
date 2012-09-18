package com.cabletech.business.base.condition.impl;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.LocaleProcess;
import com.cabletech.common.util.Page;

/**
 * 现场处理过程查询条件生成器业务实现
 * 
 * @author 杨隽 2012-01-09 创建
 * @author 杨隽 2012-02-06 添加getJoinTableSql()方法
 * @author 杨隽 2012-02-06 将getCondition()方法改为getBusinessTableDataCondition ()方法
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
@Service
public class LocaleConditionGenerate implements ConditionGenerate {
	private StringBuffer condition;
	private StringBuffer order;

	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		condition = new StringBuffer("");
		order = new StringBuffer("");
		LocaleProcess localeProcess = (LocaleProcess) parameter.getEntity();
		if (!QueryParameter.isNull(localeProcess)) {
			parameter.setAlias("p");
			parameter.setColumnName("task_id");
			parameter.setValue(localeProcess.getTaskId());
			condition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			parameter.setAlias("p");
			parameter.setColumnName("task_type");
			parameter.setValue(localeProcess.getTaskType());
			condition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			order.append(" ORDER BY ");
			order.append(localeProcess.getOrderColumn());
		}
	}

	/**
	 * 获取查询条件sql语句
	 */
	@Override
	public String getBusinessTableDataCondition() {
		// TODO Auto-generated method stub
		return condition.toString();
	}

	/**
	 * 获取业务表单内联查询条件sql语句
	 */
	public String getBusinessTableDataInCondition() {
		return "";
	}

	/**
	 * 获取排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return order.toString();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Page getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPage(Page page) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
	}
}
