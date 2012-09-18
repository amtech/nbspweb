package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleApprovePassedRateDao;
import com.cabletech.business.analysis.service.TroubleApprovePassedRateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 故障审核通过率统计接口实现
 * 
 * @author 杨隽 2012-03-22 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleApprovePassedRateServiceImpl extends BaseServiceImpl
		implements TroubleApprovePassedRateService {
	@Resource(name = "troubleApprovePassedRateDao")
	private TroubleApprovePassedRateDao troubleApprovePassedRateDao;

	/**
	 * 故障审核通过率统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleApprovePassedMainRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleApprovePassedRateDao
				.getTroubleApprovePassedRateListByOrg(parameters);
		return list;
	}

	/**
	 * 故障审核通过率统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleApprovePassedSubRateList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleApprovePassedRateDao
				.getTroubleApprovePassedRateListByPatrolGroup(parameters);
		return list;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
