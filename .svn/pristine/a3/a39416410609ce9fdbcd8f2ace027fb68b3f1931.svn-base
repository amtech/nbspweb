package com.cabletech.business.wplan.plan.dao;

import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.plan.model.PatrolTemplate;
import com.cabletech.common.base.BaseDao;

/**
 * 巡检计划模板关系
 * 
 * @author zhaobi
 * 
 */
@Repository
public class PatrolTemplateDao extends BaseDao<PatrolTemplate, String> {

	/**
	 * 删除巡检计划资源
	 * 
	 * @param planid
	 *            String
	 */
	public void deleteTemplate(String planid) {
		String hql = "delete from PatrolTemplate pr where pr.planid=? ";
		this.batchHQLExecute(hql, planid);

	}
}
