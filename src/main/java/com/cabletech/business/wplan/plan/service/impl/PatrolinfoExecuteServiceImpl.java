package com.cabletech.business.wplan.plan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.wplan.plan.dao.PatrolinfoExecuteDao;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolResourceService;
import com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检计划执行服务实现
 * 
 * @author zhaobi
 * 
 */
@Service
@Transactional
public class PatrolinfoExecuteServiceImpl extends
		BaseServiceImpl<Patrolinfo, String> implements PatrolinfoExecuteService {

	/**
	 * 巡检计划信息DAO
	 */
	@Resource(name = "patrolinfoExecuteDao")
	private PatrolinfoExecuteDao patrolinfoExecuteDao;

	@Resource(name = "patrolResourceServiceImpl")
	private PatrolResourceService patrolResourceService;

	@Override
	protected BaseDao<Patrolinfo, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#
	 * getAllPatrolScheduleInfo
	 * (com.cabletech.business.wplan.plan.model.Patrolinfo,
	 * com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getAllPatrolResultInfo(Patrolinfo patrolinfo, Page page) {
		patrolinfo.setCondition(" and  end_time<sysdate");
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getAllPatrolScheduleForSearch(patrolinfo,
				page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#
	 * getAllLostDetail(java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getAllLostDetail(String planid, Page page) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getAllLostDetail(planid, page);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getAllOverDetail(java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getAllOverDetail(String planid, Page page) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getAllOverDetail(planid, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#
	 * getPatrolResourceInfo(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getPatrolResourceDetail(String rid) {
		// TODO Auto-generated method stub
		return patrolResourceService.getPatrolResourceInfo(rid);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getOverRFIDDetail(java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getOverRFIDDetail(String rid, Page page) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getOverRFIDDetail(rid, page);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getLostRFIDDetail(java.lang.String, java.lang.String, java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getLostRFIDDetail(String rid, String resourceid,
			String resourcetype, Page page) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getLostRFIDDetail(rid, resourceid, resourcetype, page);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getExceptionItemCount(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getExceptionItemCount(String rid) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getExceptionItemCount(rid);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getItemDetail(java.lang.String, com.cabletech.common.util.Page)
	 */
	@Override
	public Page getItemDetail(String rid, Page page) {
		// TODO Auto-generated method stub
		return  patrolinfoExecuteDao.getItemDetail(rid,page);
	}
	
	/**
	 * 获取巡检项巡检结果明细 EXCEL导出使用 无分页
	 * 
	 * 后期优化
	 * 
	 * @param rid
	 *            String
	 * @return
	 */
	public List getItemDetailForExport(String rid){
		return patrolinfoExecuteDao.getItemDetailForExport(rid);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoExecuteService#getAllPatrolScheduleInfo(com.cabletech.business.wplan.plan.model.Patrolinfo, com.cabletech.common.util.Page)
	 */
	@Override
	public Page getAllPatrolScheduleInfo(Patrolinfo patrolinfo, Page page) {
		patrolinfo
		.setCondition(" and  sysdate between start_time and end_time");
		return patrolinfoExecuteDao.getAllPatrolScheduleForSearch(patrolinfo,
				page);
	}

}
