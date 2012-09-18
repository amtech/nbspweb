package com.cabletech.business.wplan.patrolitem.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.model.PatrolSubItem;

/**
 * 巡检子项DAO
 * 
 * @author 杨隽 2012-02-14 创建
 * 
 */
@Repository
public class PatrolSubItemDao extends PatrolItemBaseDao<PatrolSubItem, String> {
	/**
	 * 获取表单数据信息的sql语句
	 * 
	 * @return String 获取表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT wpsi.*,wpi.ITEM_NAME AS ITEMNAME,wpi.BUSINESS_TYPE,decode(wpsi.STATE, '");
		sqlBuf.append(PatrolItem.ITEM_SCRAP_STATE);
		sqlBuf.append("','Y','N') AS IS_FORBIDDEN_STATE ");
		sqlBuf.append(" FROM WPLAN_PATROLSUBITEM wpsi ");
		sqlBuf.append(" LEFT JOIN WPLAN_PATROLITEM wpi ON wpsi.ITEM_ID = wpi.ID ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}

	/**
	 * 启用巡检项目
	 * 
	 * @param ids
	 *            String
	 */
	public void startUsing(String ids) {
		String sql = "update wplan_patrolsubitem t set t.state='"
				+ PatrolItem.ITEM_START_USING_STATE + "' where t.id in(" + ids
				+ ") ";
		super.getJdbcTemplate().execute(sql);
	}

	/**
	 * 作废巡检项目
	 * 
	 * @param ids
	 *            String
	 */
	public void deleteLogic(String ids) {
		String sql = "update wplan_patrolsubitem t set t.state='"
				+ PatrolItem.ITEM_SCRAP_STATE + "' where t.id in(" + ids + ") ";
		super.getJdbcTemplate().execute(sql);
	}
}