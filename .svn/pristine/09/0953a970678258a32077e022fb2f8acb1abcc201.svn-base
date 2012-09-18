package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.WorkOrderProcessTimeLengthDao;
import com.cabletech.business.analysis.service.WorkOrderProcessTimeLengthService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 工单时长统计接口实现
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class WorkOrderProcessTimeLengthServiceImpl extends BaseServiceImpl
		implements WorkOrderProcessTimeLengthService {
	@Resource(name = "workOrderProcessTimeLengthDao")
	private WorkOrderProcessTimeLengthDao workOrderProcessTimeLengthDao;

	/**
	 * 工单时长统计
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWorkOrderProcessMainTimeLengthList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return workOrderProcessTimeLengthDao
				.getWorkOrderProcessTimeLengthListByOrg(parameters);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
