package com.cabletech.business.wplan.patrolitem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;

/**
 * 巡检项DAO
 * 
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 * @author 杨隽 2011-10-25 添加启用巡检项目、修改逻辑删除为作废，废弃物理删除
 * @author 杨隽 2012-02-14 重构巡检项Dao类
 */
@Repository
public class PatrolItemDao extends PatrolItemBaseDao<PatrolItem, String> {
	/**
	 * 获取表单数据信息的sql语句
	 * 
	 * @return String 获取表单数据信息的sql语句
	 */
	@Override
	public String getBusinessTableSql() {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT wpi.ID,wpi.ITEM_NAME,wpi.ID AS ITEM_ID ");
		sqlBuf.append(" FROM WPLAN_PATROLITEM wpi ");
		sqlBuf.append(" WHERE 1=1 ");
		return sqlBuf.toString();
	}

	/**
	 * 根据导入巡检项数据（项目名称、专业类型和区域编号）获取巡检项目列表
	 * 
	 * @param oneCell
	 *            PatrolItemTemp 导入巡检项数据
	 * @return List<PatrolItem> 巡检项目列表
	 */
	@SuppressWarnings("unchecked")
	public List<PatrolItem> getPatrolItemList(PatrolItemTemp oneCell) {
		Criteria criteria = getSession().createCriteria(PatrolItem.class);
		criteria.add(Restrictions.eq("itemName", oneCell.getItemName()));
		criteria.add(Restrictions.eq("businessType", oneCell.getBusinessType()));
		criteria.add(Restrictions.eq("regionId", oneCell.getRegionId()));
		List<PatrolItem> list = criteria.list();
		return list;
	}
}