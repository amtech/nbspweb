package com.cabletech.business.satisfy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 客户满意度评价Dao
 * 
 * @author 杨隽 2012-04-21 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class SatisfyDao extends BaseDao {
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
	@SuppressWarnings("unchecked")
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
	public String getBusinessTableSql() {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" SELECT ws.*,res.GROUP_NAME, ");
		sqlBuffer.append(" vr.REGIONNAME,vp.ORGNAME, ");
		sqlBuffer.append(" vp.PARENTNAME AS PATROLGROUP_NAME, ");
		sqlBuffer.append(" vp.NAME AS PERSON_NAME ");
		sqlBuffer.append(" FROM WSATISFACTION_SURVEY ws ");
		sqlBuffer.append(" JOIN RS_GROUPCUSTOMER res  ");
		sqlBuffer.append(" ON res.ID=ws.GROUPCUSTOMER_ID ");
		sqlBuffer.append(" JOIN VIEW_PATROLGROUPPERSON vp  ");
		sqlBuffer.append(" ON vp.ID=ws.PATROLER_ID ");
		sqlBuffer.append(" JOIN VIEW_REGION vr  ");
		sqlBuffer.append(" ON vr.REGIONID=res.REGIONID ");
		sqlBuffer.append(" WHERE 1=1 ");
		return sqlBuffer.toString();
	}
}
