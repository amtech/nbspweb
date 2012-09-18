package com.cabletech.business.base.condition;

import com.cabletech.common.util.Page;

/**
 * 查询条件生成器接口
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-01-16 添加setPage方法
 * @author 杨隽 2012-02-06 添加getJoinTableSql()方法
 * @author 杨隽 2012-02-06 将getCondition()方法改为getBusinessTableDataCondition ()方法
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
public interface ConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	void setQuerySql(QueryParameter parameter);

	/**
	 * 获取变动连接表单数据信息的sql语句
	 * 
	 * @return String 变动表单数据信息的sql语句
	 */
	String getJoinTableSql();

	/**
	 * 获取业务表单查询条件sql语句
	 * 
	 * @return
	 */
	String getBusinessTableDataCondition();

	/**
	 * 获取业务表单内联查询条件sql语句
	 * 
	 * @return
	 */
	String getBusinessTableDataInCondition();

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return
	 */
	String getOrder();

	/**
	 * 获取分页信息数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page getPage();

	/**
	 * 设置分页信息数据
	 * 
	 * @param page
	 *            Page 分页信息数据
	 */
	@SuppressWarnings("rawtypes")
	void setPage(Page page);
}
