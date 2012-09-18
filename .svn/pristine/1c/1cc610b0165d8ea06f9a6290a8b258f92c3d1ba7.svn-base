package com.cabletech.business.workflow.wmaintain.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 隐患维修DAO基类
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * @author 杨隽 2012-04-11 创建
 * 
 */
public abstract class WMaintainBaseDao<T, PK extends Serializable> extends
		BaseDao<T, PK> {
	/**
	 * 内部查询条件
	 */
	private StringBuffer innerCondition;

	public StringBuffer getInnerCondition() {
		return innerCondition;
	}

	public void setInnerCondition(StringBuffer innerCondition) {
		this.innerCondition = innerCondition;
	}

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
	 * 根据查询条件获取隐患维修数据信息列表
	 * 
	 * @param conditionGenerate
	 *            ConditionGenerate 查询条件生成器
	 * @return List<Map<String, Object>> 隐患维修数据信息列表
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
		putInnerCondition(conditionGenerate.getBusinessTableDataInCondition());
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
	 * 将内部查询条件存放到缓冲区中
	 * 
	 * @param inCondition
	 *            String 内部查询条件
	 */
	public void putInnerCondition(String inCondition) {
		innerCondition = new StringBuffer("");
		innerCondition.append(inCondition);
	}

	/**
	 * 根据查询条件获取sql语句
	 * 
	 * @return String 生成后的sql语句
	 */
	public abstract String getBusinessTableSql();
}
