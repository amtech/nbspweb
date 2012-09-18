package com.cabletech.business.wplan.plan.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.plan.model.PatrolApprove;
import com.cabletech.common.base.BaseDao;

/**
 * 巡检计划审批
 * 
 * @author zhaobi
 * 
 */
@Repository
public class PatrolApproveDao extends BaseDao<PatrolApprove, String> {

	/**
	 * 删除巡检计划审批
	 * 
	 * @param planid
	 *            String
	 */
	public void deleteApprove(String planid) {
		String hql = "delete from PatrolApprove pa where pa.planid=? ";
		this.batchHQLExecute(hql, planid);
	}
}
