package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleProcessInTimeRateDao;
import com.cabletech.business.analysis.service.TroubleProcessInTimeRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 故障处理及时率统计接口实现
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleProcessInTimeRateServiceImpl extends BaseServiceImpl
		implements TroubleProcessInTimeRateService {
	@Resource(name = "troubleProcessInTimeRateDao")
	private TroubleProcessInTimeRateDao troubleProcessInTimeRateDao;

	/**
	 * 故障处理及时率主统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleProcessInTimeMainRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return troubleProcessInTimeRateDao
				.getTroubleProcessInTimeRateListByOrg(parameters);
	}

	/**
	 * 故障处理及时率从统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleProcessInTimeSubRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return troubleProcessInTimeRateDao
				.getTroubleProcessInTimeRateListByPatrolGroup(parameters);
	}

	/**
	 * 处理及时故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getInTimeTroubleList(Map<String, String> parameters, Page page) {
		return troubleProcessInTimeRateDao.getInTimeTroubleList(page,
				parameters);
	}

	/**
	 * 处理超时故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getOverTimeTroubleList(Map<String, String> parameters, Page page) {
		return troubleProcessInTimeRateDao.getOverTimeTroubleList(page,
				parameters);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
