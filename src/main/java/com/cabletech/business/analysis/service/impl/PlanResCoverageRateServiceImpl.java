package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cabletech.business.analysis.dao.WplanResCoverageRateDao;
import com.cabletech.business.analysis.service.PlanResCoverageRateService;
import com.cabletech.common.util.Page;

/**
 * 计划资源覆盖率 --serviceimpl
 * 
 * @author wangjie 2012-03-19
 * 
 */
@Service
public class PlanResCoverageRateServiceImpl implements
		PlanResCoverageRateService {

	@Resource(name = "wplanResCoverageRateDao")
	private WplanResCoverageRateDao wplanResCoverageRateDao;

	/**
	 * 计划资源覆盖率
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public List<Map<String, Object>> getMainList(Map<String, String> parameters) {
		return wplanResCoverageRateDao
				.getWplanResCoverageRateByOrgList(parameters);
	}

	/**
	 * 计划资源覆盖率
	 * 
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public List<Map<String, Object>> getSubList(Map<String, String> parameters) {
		return wplanResCoverageRateDao
				.getWplanResCoverageRateByPatrolList(parameters);
	}

	/**
	 * 计划资源覆盖率 --计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getPlannedResDetailList(Page page,
			Map<String, String> parameters) {
		return wplanResCoverageRateDao
				.getPlannedResDetailList(page, parameters);
	}

	/**
	 * 计划资源覆盖率 --未计划资源明细
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            参数
	 * @return list
	 */
	public Page getUnPlannedResDetailList(Page page,
			Map<String, String> parameters) {
		return wplanResCoverageRateDao.getUnPlannedResDetailList(page,
				parameters);
	}

}
