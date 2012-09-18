package com.cabletech.business.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.PatrolGroup;
import com.cabletech.common.base.BaseDao;

/**
 * 巡检组DAO
 * 
 * @author zhaobi
 * 
 */
@Repository
public class PatrolGroupDao extends BaseDao<PatrolGroup, String> {

	/**
	 * 
	 * @param staffid
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getPatrolGroupByStaffid(String staffid) {
		String sql = "select * from base_patrolpersonrel where personid='"
				+ staffid + "'";
		return this.getSQLALL(sql);
	}
}
