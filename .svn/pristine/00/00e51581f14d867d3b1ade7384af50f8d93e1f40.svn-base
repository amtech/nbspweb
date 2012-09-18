package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.WorkOrderApprovePassedRateDao;
import com.cabletech.business.analysis.service.WorkOrderApprovePassedRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 工单审核通过率统计接口实现
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class WorkOrderApprovePassedRateServiceImpl extends BaseServiceImpl
		implements WorkOrderApprovePassedRateService {
	@Resource(name = "workOrderApprovePassedRateDao")
	private WorkOrderApprovePassedRateDao workOrderApprovePassedRateDao;

	/**
	 * 工单审核通过率统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWorkOrderApprovePassedRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return workOrderApprovePassedRateDao
				.getWorkOrderApprovedPassedRateListByOrg(parameters);
	}

	/**
	 * 获取n次审核通过工单列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @param approveTimes
	 *            int
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getNTimesApprovePassedWorkOrderList(
			Map<String, String> parameters, int approveTimes, Page page) {
		return workOrderApprovePassedRateDao
				.getNTimesApprovePassedWorkOrderList(page, parameters,
						approveTimes);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
