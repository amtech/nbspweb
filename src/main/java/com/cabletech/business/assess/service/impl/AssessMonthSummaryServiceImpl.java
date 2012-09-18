package com.cabletech.business.assess.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.assess.dao.AssessMonthSummaryDao;
import com.cabletech.business.assess.service.AssessMonthSummaryService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 月考核汇总服务实现
 * @author zhaobi
 * @date 2012-8-9 
 */
@Service
@Transactional
public class AssessMonthSummaryServiceImpl extends BaseServiceImpl implements AssessMonthSummaryService{
	@Resource(name = "assessMonthSummaryDao")
	private AssessMonthSummaryDao assessMonthSummaryDao;


	/* (non-Javadoc)
	 * @see com.cabletech.business.assess.service.AssessMonthSummaryService#getMonthSummary(java.util.Map)
	 */
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getMonthSummary(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return assessMonthSummaryDao.getMonthSummary(map);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.assess.service.AssessMonthSummaryService#getMonthRank(java.util.Map)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getMonthRank(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return assessMonthSummaryDao.getMonthRank(map);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return assessMonthSummaryDao;
	}
	


}
