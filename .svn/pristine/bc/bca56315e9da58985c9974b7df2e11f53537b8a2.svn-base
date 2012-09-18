package com.cabletech.business.desktop.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.PatrolStatisticDao;
import com.cabletech.business.desktop.service.PatrolStatisticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 今日巡检统计分析实现
 * 
 * @author zhaobi
 * @date 2012-8-15
 */
@Service
public class PatrolStatisticServiceImpl extends BaseServiceImpl implements
		PatrolStatisticService {

	/**
	 * 巡检统计DAO
	 */
	@Resource(name = "patrolStatisticDao")
	private PatrolStatisticDao patrolStatisticDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.desktop.service.PatrolStatisticService#
	 * getPatrolStatistic(com.cabletech.baseinfo.business.entity.UserInfo,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getPatrolStatistic(UserInfo user) {
		// TODO Auto-generated method stub
		return patrolStatisticDao.getPatrolStatistic(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.desktop.service.PatrolStatisticService#getWtrouble
	 * (com.cabletech.baseinfo.business.entity.UserInfo, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getWtrouble(UserInfo user) {
		// TODO Auto-generated method stub
		return patrolStatisticDao.getWtrouble(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.common.base.BaseServiceImpl#getBaseDao()
	 */
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return patrolStatisticDao;
	}

}
