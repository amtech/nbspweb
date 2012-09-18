package com.cabletech.business.wplan.plan.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.business.wplan.plan.dao.PatrolinfoResultDao;
import com.cabletech.business.wplan.plan.service.PatrolinfoResultService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class PatrolinfoResultServiceImpl extends BaseServiceImpl implements
		PatrolinfoResultService {

	@Resource(name = "patrolinfoResultDao")
	private PatrolinfoResultDao patrolinfoResultDao;

	@Override
	protected BaseDao getBaseDao() {
		return patrolinfoResultDao;
	}

	@Override
	public Page statisticsByOrg(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.statisticsByOrg(parameter, page);
	}

	@Override
	public Page statisticsByPatrolGroup(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.statisticsByPatrolGroup(parameter, page);
	}

	@Override
	public Page statisticsByRegion(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.statisticsByRegion(parameter, page);
	}

	@Override
	public Page statisticProblemStation(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.getExceptionResDetail(parameter, page);
	}

	@Override
	public Page statisticStationError(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.getExceptionItemsDetail(parameter, page);
	}

	@Override
	public Page noPatrolDetails(Map<String, Object> parameter, Page page) {
		String problemType = (String) parameter.get("problemType");
		if ("01".equals(problemType)) {
			return patrolinfoResultDao.getCqResDetail(parameter, page);
		}
		if ("02".equals(problemType)) {
			return patrolinfoResultDao.getJfResDetail(parameter, page);
		}
		if ("03".equals(problemType)) {
			return patrolinfoResultDao.getQtResDetail(parameter, page);
		}
		return page;
	}

	@Override
	public Page getPlanInfo(Map<String, Object> parameter, Page page) {
		return patrolinfoResultDao.getPlanInfo(parameter, page);
	}
}