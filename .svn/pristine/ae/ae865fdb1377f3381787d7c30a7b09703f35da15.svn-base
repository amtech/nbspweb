package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.WorkOrderProcessInTimeRateDao;
import com.cabletech.business.analysis.service.WorkOrderProcessInTimeRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 工单处理及时率统计接口实现
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class WorkOrderProcessInTimeRateServiceImpl extends BaseServiceImpl
		implements WorkOrderProcessInTimeRateService {
	@Resource(name = "workOrderProcessInTimeRateDao")
	private WorkOrderProcessInTimeRateDao workOrderProcessInTimeRateDao;

	/**
	 * 工单处理及时率统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWorkOrderProcessInTimeMainRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return workOrderProcessInTimeRateDao
				.getWorkOrderProcessInTimeRateListByOrg(parameters);
	}

	/**
	 * 处理及时工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getInTimeWorkOrderList(Map<String, String> parameters, Page page) {
		return workOrderProcessInTimeRateDao.getInTimeWorkOrderList(page,
				parameters);
	}

	/**
	 * 处理超时工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getOverTimeWorkOrderList(Map<String, String> parameters,
			Page page) {
		return workOrderProcessInTimeRateDao.getOverTimeWorkOrderList(page,
				parameters);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
