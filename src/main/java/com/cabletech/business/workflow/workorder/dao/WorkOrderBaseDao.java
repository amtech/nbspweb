package com.cabletech.business.workflow.workorder.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 通用工单的DAO基类
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-02-06 将getSql()方法改为getBusinessTableSql()方法
 * @author 杨隽 2012-02-06
 *         修改queryPageForSql()方法和queryListForSql()方法以支持业务表与多个不同的连接数据表的关联
 * @author 杨隽 2012-02-06 修改conditionGenerate.getCondition()为conditionGenerate.
 *         getBusinessTableDataCondition()
 * @param <T>
 * @param <PK>
 */
public abstract class WorkOrderBaseDao<T, PK extends Serializable> extends
		BaseDao<T, PK> {
	/**
	 * 根据查询条件获取表单的分页数据信息
	 * 
	 * @param conditionGenerate
	 *            ConditionGenerate 查询条件生成器
	 * @return Page<Map<String, Object>> 表单的分页数据信息
	 */
	@SuppressWarnings("unchecked")
	public Page<Map<String, Object>> queryPageForSql(
			ConditionGenerate conditionGenerate) {
		StringBuffer sql = getSqlBuffer(conditionGenerate);
		return (Page<Map<String, Object>>) super.getSQLPageAll(
				conditionGenerate.getPage(), sql.toString());
	}

	/**
	 * 根据查询条件获取表单的数据信息
	 * 
	 * @param conditionGenerate
	 *            ConditionGenerate 查询条件生成器
	 * @return List<Map<String, Object>> 表单的数据信息
	 */
	public List<Map<String, Object>> queryListForSql(
			ConditionGenerate conditionGenerate) {
		StringBuffer sql = getSqlBuffer(conditionGenerate);
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取根据查询条件生成的sql语句
	 * 
	 * @param conditionGenerate
	 *            ConditionGenerate 查询条件生成器
	 * @return StringBuffer 根据查询条件生成的sql语句
	 */
	public StringBuffer getSqlBuffer(ConditionGenerate conditionGenerate) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT * FROM ( ");
		sql.append(getBusinessTableSql());
		sql.append(conditionGenerate.getBusinessTableDataCondition());
		sql.append(" ) business_table ");
		sql.append(conditionGenerate.getJoinTableSql());
		sql.append(conditionGenerate.getOrder());
		return sql;
	}

	/**
	 * 获取业务表单数据信息的sql语句
	 * 
	 * @return String 业务表单数据信息的sql语句
	 */
	public abstract String getBusinessTableSql();
	
	
}
