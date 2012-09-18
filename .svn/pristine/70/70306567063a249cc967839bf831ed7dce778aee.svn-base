package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.analysis.dao.PollingAccomplishRateDao;
import com.cabletech.business.analysis.service.PollingAccomplishRateService;
import com.cabletech.common.util.Page;

/**
 * 巡检完成率
 * 
 * @author wangjie 2012-03-19
 * 
 */
@Service
public class PollingAccomplishRateServiceImpl implements
		PollingAccomplishRateService {

	@Resource(name = "pollingAccomplishRateDao")
	private PollingAccomplishRateDao pollingAccomplishRateDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.analysis.service.PollingAccomplishRateService#
	 * getMainList(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getMainList(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return pollingAccomplishRateDao
				.getPollingAccomplishRateByOrgList(parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.analysis.service.PollingAccomplishRateService#
	 * getSubList(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getSubList(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return pollingAccomplishRateDao
				.getPollingAccomplishRateByPatrolList(parameters);
	}

	/**
	 * 巡检完成率 --未覆盖资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getUnPlannedResDetailList(Page page,
			Map<String, String> parameters) {
		return pollingAccomplishRateDao.getUnPlannedResDetailList(page,
				parameters);
	}

	/**
	 * 巡检完成率 --已巡资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getPollingAccomplishResDetailList(Page page,
			Map<String, String> parameters) {
		return pollingAccomplishRateDao.getPollingAccomplishResDetailList(page,
				parameters);
	}

}