package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.WorkOrderProcessOverTimeRateDao;
import com.cabletech.business.analysis.service.WorkOrderProcessOverTimeRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 超时工单统计接口实现
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class WorkOrderProcessOverTimeRateServiceImpl extends BaseServiceImpl
		implements WorkOrderProcessOverTimeRateService {
	@Resource(name = "workOrderProcessOverTimeRateDao")
	private WorkOrderProcessOverTimeRateDao workOrderProcessOverTimeRateDao;

	/**
	 * 超时工单统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWorkOrderProcessOverTimeMainRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return workOrderProcessOverTimeRateDao
				.getWorkOrderProcessOverTimeRateListByOrg(parameters);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
