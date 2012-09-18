package com.cabletech.business.ah.rating.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.rating.model.RatingFormItem;

/**
 * 考核表子项DAO
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
@Repository
public class RatingFormItemDao extends
		RatingFormBaseDao<RatingFormItem, String> {
	/**
	 * 获取表单数据信息的sql语句
	 * 
	 * @return String 获取表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT t.* ");
		sqlBuf.append(" FROM AH_RATINGFORMITEM t ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}
}