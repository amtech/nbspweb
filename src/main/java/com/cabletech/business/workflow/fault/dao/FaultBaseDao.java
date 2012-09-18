package com.cabletech.business.workflow.fault.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 故障DAO基类
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2012-02-07 将getSql()方法改为getBusinessTableSql()方法并修改传入的参数
 * @author 杨隽 2012-02-07 添加queryPageForSql()方法
 * @author 杨隽 2012-02-07 将queryForList()方法改为queryListForSql()方法
 * 
 */
public abstract class FaultBaseDao<T, PK extends Serializable> extends
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
	 * 根据查询条件获取故障数据信息列表
	 * 
	 * @param conditionGenerate
	 *            ConditionGenerate 查询条件生成器
	 * @return List<Map<String, Object>> 故障数据信息列表
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
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	public abstract String getBusinessTableSql();
}
