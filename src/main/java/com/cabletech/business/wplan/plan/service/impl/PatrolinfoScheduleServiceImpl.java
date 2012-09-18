package com.cabletech.business.wplan.plan.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.business.wplan.plan.dao.PatrolinfoScheduleDao;
import com.cabletech.business.wplan.plan.service.PatrolinfoScheduleService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检进度查询
 * 
 * @author wj
 * 
 */
@Service
@Transactional
public class PatrolinfoScheduleServiceImpl extends BaseServiceImpl implements PatrolinfoScheduleService {
	
	/**
	 * Dao
	 */
	@Resource(name = "patrolinfoScheduleDao")
	private PatrolinfoScheduleDao patrolinfoScheduleDao;

	@Override
	protected BaseDao getBaseDao() {
		return patrolinfoScheduleDao;
	}

	@Override
	public Page searchDetailed(Map<String, Object> parameter, Page page) {
		return patrolinfoScheduleDao.searchDetailed(parameter,page);
	}

	@Override
	public Page statisticsByOrg(Map<String, Object> parameter, Page page) {
		return patrolinfoScheduleDao.statisticsByOrg(parameter,page);
	}

	@Override
	public Page statisticsByPatrolGroup(Map<String, Object> parameter, Page page) {
		return patrolinfoScheduleDao.statisticsByPatrolGroup(parameter,page);
	}

	@Override
	public Page statisticsByPatrolMan(Map<String, Object> parameter, Page page) {
		return patrolinfoScheduleDao.statisticsByPatrolMan(parameter,page);
	}

	@Override
	public Page statisticsByRegion(Map<String, Object> parameter, Page page) {
		return patrolinfoScheduleDao.statisticsByRegion(parameter,page);
	}
}