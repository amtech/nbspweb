package com.cabletech.business.workflow.fault.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;

/**
 * 所有故障派单查询条件生成器接口实现
 * 
 * @author 杨隽 2011-11-02 创建
 * @author 杨隽 2011-11-03 添加“故障专业”查询条件
 * @author 杨隽 2012-02-06 添加getJoinTableSql()方法
 * @author 杨隽 2012-02-06 将getCondition()方法改为getBusinessTableDataCondition ()方法
 * @author 杨隽 2012-02-08 提取查询条件生成器的公共方法和传入参数判断公共方法
 * 
 */
@Component
public class DispatchFaultListConditionGenerateImpl extends
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
		if (super.preSetParameter(parameter)) {
			parameter.setAlias("wa");
			super.getConditionSqlByParameter((FaultQueryParameter) parameter,
					super.businessDataCondition);
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
