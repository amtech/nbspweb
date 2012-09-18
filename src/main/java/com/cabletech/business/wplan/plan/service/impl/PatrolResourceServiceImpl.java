package com.cabletech.business.wplan.plan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.wplan.plan.dao.PatrolResourceDao;
import com.cabletech.business.wplan.plan.model.PatrolResource;
import com.cabletech.business.wplan.plan.service.PatrolResourceService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检资源服务实现
 * @author zhaobi
 *
 */
@Service
@Transactional
public class PatrolResourceServiceImpl extends BaseServiceImpl<PatrolResource, String> implements PatrolResourceService{

	@Resource(name = "patrolResourceDao")
	private PatrolResourceDao patrolResourceDao;
	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolResourceService#getPatrolResource(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> getPatrolResource(String businesstype,
			String patrolgroupid) {
		// TODO Auto-generated method stub
		return patrolResourceDao.getPatrolResource(businesstype, patrolgroupid);
	}
	


	@Override
	protected BaseDao<PatrolResource, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolResourceDao;
	}



	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolResourceService#deleteResource(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteResource(String planid) {
		patrolResourceDao.deleteResource(planid);
	}




	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolResourceService#getPlanResource(java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page getPlanResource(String planid, Page page) {
		// TODO Auto-generated method stub
		return patrolResourceDao.getPatrolResourceByPlanid(planid,page);
	}



	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolResourceService#getPatrolResourceInfo(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getPatrolResourceInfo(String rid) {
		// TODO Auto-generated method stub
		return patrolResourceDao.getPatrolResourceInfo(rid);
	}



	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolResourceService#getPatrolResourceByPlanid(java.lang.String)
	 */
	@Override
	public List<Map<String,Object>> getPatrolResourceByPlanid(String planid) {
		// TODO Auto-generated method stub
		return patrolResourceDao.getPatrolResourceByPlanid(planid);
	}

}
