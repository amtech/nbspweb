package com.cabletech.business.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * DAO基类
 * 
 * @author 杨隽 2012-01-09 创建
 * @author 杨隽 2012-02-06 修改conditionGenerate.getCondition()为conditionGenerate.
 *         getBusinessTableDataCondition()
 * @param <T>
 * @param <PK>
 */
public abstract class CommonBaseDao<T, PK extends Serializable> extends
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
		sql.append(getSql());
		sql.append(conditionGenerate.getBusinessTableDataCondition());
		sql.append(conditionGenerate.getOrder());
		return sql;
	}

	/**
	 * 获取表单数据信息的sql语句
	 * 
	 * @return String 获取表单数据信息的sql语句
	 */
	public abstract String getSql();
}
