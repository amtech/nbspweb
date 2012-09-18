package com.cabletech.business.base.condition.impl;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.util.Page;

/**
 * 空查询条件生成器业务实现
 * 
 * @author 杨隽 2012-06-26 添加getBusinessTableDataInCondition()方法
 * 
 */
@Service
public class EmptyConditionGenerate implements ConditionGenerate {
	private StringBuffer condition;
	private StringBuffer order;
	private Page page;

	@Override
	public void setQuerySql(QueryParameter parameter) {
		condition = new StringBuffer("");
		order = new StringBuffer("");
	}

	/**
	 * 获取查询条件sql语句
	 */
	@Override
	public String getBusinessTableDataCondition() {
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
		return order.toString();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String getJoinTableSql() {
		return "";
	}
}
