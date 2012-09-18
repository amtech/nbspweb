package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleResponseInTimeRateDao;
import com.cabletech.business.analysis.service.TroubleResponseInTimeRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 故障响应及时率统计接口实现
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleResponseInTimeRateServiceImpl extends BaseServiceImpl
		implements TroubleResponseInTimeRateService {
	@Resource(name = "troubleResponseInTimeRateDao")
	private TroubleResponseInTimeRateDao troubleResponseInTimeRateDao;

	/**
	 * 故障响应及时率统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleResponseInTimeMainRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleResponseInTimeRateDao
				.getTroubleResponseInTimeRateListByOrg(parameters);
		return list;
	}

	/**
	 * 故障响应及时率统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleResponseInTimeSubRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleResponseInTimeRateDao
				.getTroubleResponseInTimeRateListByPatrolGroup(parameters);
		return list;
	}

	/**
	 * 响应及时故障列表
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
		return troubleResponseInTimeRateDao.getInTimeTroubleList(page,
				parameters);
	}

	/**
	 * 响应超时故障列表
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
		return troubleResponseInTimeRateDao.getOverTimeTroubleList(page,
				parameters);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
